package org.yang.services.licenseService;

import java.io.Serializable;
import java.sql.ResultSet;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DataAccessException;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.RDBMS;
import org.yang.services.config.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.yang.services.dbService.DBConnectionCount;

/**
 * Title:       Siteware Enterprise
 * Description: Siteware Enterprise Edition.
 *     The LicenseKeyDAO class provides methods to manage the license key table.
 *     The table name will be passed in from constructor.
 * Copyright:   Copyright (c) 2001
 * Company:     Screamingmedia
 * @author Yao Cheng
 * @version 1.0
 */


/**
 * This class provides methods to manage the license key table.
 */
public class LicenseKeyDAO  implements Serializable
{
   private RDBMS configRDBMS = null;

   private String tablename = null;
   public void setTablename(String tablename) { this.tablename = tablename; }

   /**
   * Construct the license key table with table name.
   */
   public LicenseKeyDAO (String tablename, RDBMS configRDBMS)
   {
      this.tablename = tablename;
      if(null!=configRDBMS)
        this.configRDBMS = configRDBMS;
      else
        this.configRDBMS = RDBMSFactory.getInstance().getSystemRDBMS();

   }

   /**
   * Construct the license key table with table name.
   */
   public LicenseKeyDAO (String tablename)
   {
      this(tablename, null);
   }

   /**
   * default constructor.
   */
   public LicenseKeyDAO()
   {
      //&System.out.println("----------------------------->" + configRDBMS);
   }

   /**
   * Return true if table already exists; return false if not.
   */
   public boolean isTableExist()
   {
      try
      {
         StringBuffer sbSQL = (new StringBuffer()).append("SELECT COUNT(*) FROM ")
                                                  .append(tablename);
         configRDBMS.executeQuery(sbSQL.toString());
         return true;
      }
      catch (Exception e)
      {
         return false;
      }
   }

   /**
 *     create the license key table.
   */
   public void createTable() throws DataAccessException
   {
      try
      {
         StringBuffer sbSQL = (new StringBuffer()).append("CREATE TABLE ")
                                                  .append(tablename)
                                                  .append(" (ID varchar(255) not null,")
                                                  .append("xmlStr "+Config.getInstance().DT_CLOB+",")
                                                  .append("primary key (ID))");

         configRDBMS.executeQuery(sbSQL.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new DataAccessException(e.getMessage());
      }
   }

   /**
 *     Drop the license key table.
   */
   public void dropTable() throws DataAccessException
   {
      try
      {
         StringBuffer sbSQL = (new StringBuffer()).append("DROP TABLE ")
                                                  .append(tablename);

         configRDBMS.executeUpdate(sbSQL.toString());
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage());
      }
   }

   /**
 *     Insert a row into the license key table.
  */
   public void insert(String id, String xml) throws DataAccessException
   {
      StringBuffer sbSQL = (new StringBuffer()).append("INSERT INTO ")
                                               .append(tablename)
                                               .append("(ID, xmlStr)")
                                               .append(" VALUES(?,?)");
      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn = configRDBMS.getDBConnection();
         Stmt = Conn.prepareStatement(sbSQL.toString());
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new DataAccessException ("can not connect to database.");
      }

      try
      {
         Stmt.setString(1, id);
         Stmt.setString(2, xml);

         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new DataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("LicenseKeyDAO::insert");   
            }            
         }
         catch (Exception e) { ExceptionBroadcast.print(e); }
      }
   }

   /**
 *     Load an xml String with ID equals to id from the license key table.
 *     Some tricks are used to handle different database servers.
   */
   public String get(String id) throws DataAccessException
   {
      StringBuffer sbSQL = (new StringBuffer()).append("SELECT xmlStr FROM ")
                                               .append(tablename)
                                               .append(" WHERE ID = ?");
      ResultSet rs = null; 
      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = configRDBMS.getDBConnection();
         Stmt = Conn.prepareStatement(sbSQL.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new DataAccessException ("can not connect to database");
      }

      try
      {
         Stmt.setString(1, id);
         rs = Stmt.executeQuery();
         if(rs.next())
         {
            return rs.getString(1);
         }
         else
            throw new DataAccessException();
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("LicenseKeyDAO::get");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
   }

   /**
 *     Remove a row with ID equals to id from the license key table.
   */
   public void remove(String id) throws DataAccessException
   {
      StringBuffer sbSQL = (new StringBuffer()).append("DELETE FROM ")
                                               .append(tablename)
                                               .append(" WHERE ID = ?");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn  = configRDBMS.getDBConnection();
         Stmt = Conn.prepareStatement(sbSQL.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new DataAccessException ("can not connect to database");
      }

      try
      {
         Stmt.setString(1, id);
         Stmt.executeUpdate();
         try
         {
            Conn.commit();
         }
         catch (Exception e)
         {};
      }
      catch (Exception e)
      {
         throw new DataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("LicenseKeyDAO::remove");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
   }

   /**
 *     Update the xml string in license key table with the specified ID.
  */
   public void update(String id, String xml) throws DataAccessException
   {
      StringBuffer sbSQL = (new StringBuffer()).append("UPDATE ")
                                               .append(tablename)
                                               .append(" SET xmlStr = ?")
                                               .append(" WHERE ID = ?");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = configRDBMS.getDBConnection();
         Stmt = Conn.prepareStatement(sbSQL.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new DataAccessException (e.getMessage());
      }

      try
      {
         Stmt.setString(1, xml);
         Stmt.setString(2, id);
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         System.out.println("Update failed");
         throw new DataAccessException ("Update failed!");
      }
      finally
      {
         try
         {
           
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("LicenseKeyDAO::update");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
   }

   public static void main(String[] args)
   {
      String url = "jdbc:mysql://localhost:3306/siteware";
      String user = "jumbo";
      String pass = "02200220";
      String driver = "org.gjt.mm.mysql.Driver";

      RDBMS testRDBMS = RDBMSFactory.getInstance().getRDBMS(url, user, pass, driver);
      LicenseKeyDAO dao = new LicenseKeyDAO("test_license_key", testRDBMS);
      try
      {
         dao.createTable();
         dao.insert("id1", "<domain key=\"scrm\"></domain>");
         dao.insert("id2", "<domain key=\"scrm\"></domain>");
         dao.update("id2","fake");
         dao.remove("id2");
         System.out.println(dao.get("id1"));
         //dao.dropTable();
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }
}