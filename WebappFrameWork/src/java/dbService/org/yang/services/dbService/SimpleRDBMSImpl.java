package org.yang.services.dbService;

import java.io.Serializable;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import java.util.Hashtable;
import org.yang.services.dbService.DBConnectionCount;

public class SimpleRDBMSImpl implements RDBMS, Serializable
{
   private Hashtable jndiProp = null;
   private String jndiName = null;

   public  String jdbcDriver = "";
   private String dburl = "";
   private String dbUser = "";
   private String dbPassword = "";

   private boolean initialized = false;
   private boolean hasDataSoure = false;
   private transient DataSource ds = null;

   private int serveTimes = 0;
   private transient ConnectionProxy proxyConn = null;

   public SimpleRDBMSImpl() {}

   public SimpleRDBMSImpl(String jndiName, Hashtable jndiProp)throws DataAccessException
   {  
      try
      {
         this.jndiName = jndiName;
         this.jndiProp = jndiProp;
         ds = getDataSource();
         hasDataSoure = true;
      }
      catch(Exception e)
      {
	     throw new DataAccessException(e.getMessage());
      }
   }

   public SimpleRDBMSImpl(String jndiName)throws DataAccessException
   {
      try
      { 
  	     this.jndiName = jndiName;
         ds = getDataSource();
         hasDataSoure = true;
      }
      catch(Exception e)
      {
	     throw new DataAccessException(e.getMessage());
      }
   }
    
   public SimpleRDBMSImpl(String dburl,String dbUser,String dbPassword ,String jdbcDriver)
   {
      if("".equals(dburl)||dburl==null||"".equals(jdbcDriver)||jdbcDriver==null)
         return;
            
      this.dburl = dburl;
      this.dbUser = dbUser;
      this.dbPassword = dbPassword;
      this.jdbcDriver = jdbcDriver;
   }
	
   public void checkDBTables() throws DataAccessException
   {
      executeUpdate("SHOW TABLE STATUS");
   }

   public void checkDBConnection() throws DataAccessException
   {
      Connection conn = getDBConnection();
      try
      {
         if(hasDataSoure&&null!=conn)
         {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::checkDATables");   
                               
         }
         else
            throw new DataAccessException("Connection is null");
      }
      catch(Exception e)
      {
         System.out.println("Exception happen while closing database!");
      }
   }

   public synchronized Connection getDBConnection()  throws DataAccessException
   {
      if(hasDataSoure)
      {
         try
         {
            Connection conn = null;
            try
            {
               conn = ds.getConnection();
               proxyConn = new ConnectionProxy(this, conn);
               if(conn!=null)
                  DBConnectionCount.ConnectCount += 1;
               //System.out.println("[SimpleRDBMSImpl::getConnection] ref = " + Conn);
            }
            catch(Exception e)
            {
               ds = getDataSource();
               conn = ds.getConnection();
               proxyConn = new ConnectionProxy(this, conn);
            }
            DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::getConnection");   

            if (null==conn)
               throw new DataAccessException ("[SimpleRDBMSImpl::getDBConnection] Database connection not available");
            return conn;
         }
         catch(DataAccessException dae)
         {
            throw dae;
         }
         catch(Exception e)
         {
            System.out.println("[SimpleRDBMSImpl::getDBConnection] Error in getDBConnection : DataSource mode." );
            throw new DataAccessException(e.getMessage());
         }
      }
      else
      {
         try
         {
            serveTimes++;
            if(null!=proxyConn&&!proxyConn.isClosed())
            {
               if(serveTimes<500)
                  return proxyConn;
               try
               {
                  proxyConn.close();
               }
               catch(Exception e)
               {
                  e.printStackTrace();
               }
               serveTimes = 0;
            }

            if (initialized == false)
            {
               Class.forName(jdbcDriver).newInstance();
               initialized = true;
            }

            Connection conn = DriverManager.getConnection(dburl, dbUser, dbPassword);
            if(null==conn)
            {
               throw new DataAccessException ("Database connection not available");
            }

            proxyConn = new ConnectionProxy(this, conn);
            //System.out.println("[SimpleRDBMSImpl::getDBConnection] Connection instance : " + proxyConn);
            return proxyConn;
         }
         catch(DataAccessException dae)
         {
            throw dae;
         }
         catch(Exception e)
         {
            throw new DataAccessException(e.getMessage());
         }
      }
   }

   public void close()
   {
      if(hasDataSoure)
      {
         try
         {
            proxyConn.realClose();
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
         proxyConn = null;
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::close");   
       
      }
   }

   public void commit(Connection conn)
   {
      try
      {
         conn.commit();
      }
      catch (Exception e)
      {
         System.out.println("Error when commit:" + e.getMessage());
      }
   }
    
   public void abort(Connection conn)
   {
      //try {
      //  conn.rollback();
      //} catch (Exception e) {
      //  com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
      //};
   }

   public void onException(Exception e)
   {
      try
      {
         proxyConn.close();
      }
      catch(Exception e1)
      {
         System.out.println("[SimpleRDBMSImpl::onException] Exception happen when close connection : " + e1.getMessage());
      }
      proxyConn = null;
      if(DBConnectionCount.ConnectCount>0)
         DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
      DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::onException");   
   }

   public boolean executeUpdate(String sql)  throws DataAccessException
   {
      if (sql == null) return false;

      //System.out.println("[SimpleRDBMSImpl::executeUpdate] SQL = " + sql);
      Connection conn = null;
      Statement Stmt = null;
      try
      {
         conn = getDBConnection();
         Stmt = conn.createStatement();
         Stmt.executeUpdate(sql);
         //commit(Conn);
         return true;
      }
      catch (SQLException sqle)
      {
         //abort(Conn);
         //sqle.printStackTrace();
         throw new DataAccessException(sqle.getMessage());
      }
      catch (DataAccessException dae)
      {
         //abort(Conn);
         //dae.printStackTrace();
         throw dae;
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
         } catch (Exception e) {}
            
         try
         {
            if(hasDataSoure&&conn!=null) 
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::executeUpdate");   
            }   
         } catch (Exception e) {}
      }
   }

   public void executeQuery(String sql)  throws DataAccessException
   {
      if (sql == null) return;

      //System.out.println("SQL="+sql);
      Connection conn = null;
      Statement Stmt = null;
      ResultSet RS = null;
      try
      {
         conn = getDBConnection();
    	 Stmt = conn.createStatement();
         RS = Stmt.executeQuery(sql);
      }
      catch (SQLException e)
      {
         //e.printStackTrace();
         throw new DataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            if (RS!=null) RS.close();
         }
         catch (Exception e) {}
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {}
            
         try
         {
            if(hasDataSoure)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::executeQuery");   
            }   
         }
         catch (Exception e) {}
      }
   }

   public boolean executeUpdate(String sql, String[] values)  throws DataAccessException
   {
      if (sql == null) return false;

      Connection conn = null;
      PreparedStatement Stmt = null;
        
      try
      {
         conn = getDBConnection();
         Stmt = conn.prepareStatement(sql);
         for (int i=0; i<values.length;i++)
            Stmt.setString(i+1, values[i]);

         Stmt.executeUpdate();
         //commit(conn);
         return true;
      }
      catch (SQLException E)
      {
         //abort(conn);
         throw new DataAccessException(E.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
            
         try
         {
            if(hasDataSoure)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::executeUpdate");   
            }   
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   public void commitAndCloseConnection(Connection conn)  throws DataAccessException
   {
      if (conn == null) return;
      commit(conn);
      try
      {
         conn.close();
         if(DBConnectionCount.ConnectCount>0)
             DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("SimpleRDBMSImpl::commitAndCloseConnection");   
      }
      catch (Exception e)
      {
         //ExceptionBroadcast.print(e);
      }
   }

   private DataSource getDataSource() throws NamingException, SQLException
   {
      System.out.println("[SimpleRDBMSImpl] Performing JNDI lookup for Datasource:: "+jndiName);
      DataSource ds = null;
      if(null==jndiProp)
         ds = (DataSource)((new InitialContext()).lookup(jndiName));
      else
         ds = (DataSource)((new InitialContext(jndiProp)).lookup(jndiName));
       
      ds.setLoginTimeout(30000);
      return ds;
   }
}