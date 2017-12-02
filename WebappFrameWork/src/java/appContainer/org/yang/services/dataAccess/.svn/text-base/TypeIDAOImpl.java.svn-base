package org.yang.services.dataAccess;

import org.yang.services.dbService.RDBMS;
import org.yang.services.dbService.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DBConnectionCount;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeIDAOImpl
{
    protected String tablename = null;
    protected RDBMS rdbms = null;
    protected CreatedTableNameArchives tableNameArchives = null;	

   /**
    * Constructor
    */
   public TypeIDAOImpl(String tablename, RDBMS rdbms)
   {
      if((tablename==null)||(tablename.equals("")))
      {
         System.out.println("Tablename cann't be null or space. ");
         return ;
      }
      tableNameArchives = CreatedTableNameArchives.getInstance();
      this.tablename = tablename;
      this.rdbms = rdbms;
   }

   /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropTable()//throws AccountDataAccessException
   {
      String sql ="DROP TABLE " + tablename ;
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }

   public boolean delete(String id)throws DataAccessException
   {
      if (null==id)
      {
         System.out.println("Stage id can't be null .");
         return false;
      }

      return delete("id", id);
   }

   public boolean deleteAll() throws DataAccessException
   {
      return delete((String[])null, (String[])null);
   }

   protected boolean delete(String field, String val)throws DataAccessException
   {
      if (null==field||null==val)
      {
         System.out.println("Deleted field can't be null .");
         return false;
      }
      return delete(new String[]{field}, new String[]{val});
   }

   protected boolean delete(String[] fields, String[] vals)throws DataAccessException
   {
      String[] conditions = new String[fields.length];
      for(int i=0; i<conditions.length; i++)
      {
         conditions[i] = fields[i] + "='" + vals[i] + "'";
      }
      return delete(conditions, true);
   }

   public boolean deleteByProject(String pid) throws DataAccessException
   {
      String[] conditions = new String[1];
      conditions[0] = "id LIKE '" + pid + ":%'";
      return delete(conditions, true);
   }

   protected boolean delete(String[] conditions, boolean isAnd)throws DataAccessException
   {
      StringBuffer sql = new StringBuffer("delete from ").append(tablename);

      if(null!=conditions&&conditions.length>0)
      {
         sql.append(" where");
         for(int i=0; i<conditions.length; i++)
         {
            if(isAnd)
            {
               if(0==i)
                  sql.append(" ").append(conditions[i]);
               else
                  sql.append(" AND ").append(conditions[i]);
            }
            else
            {
               if(0==i)
                  sql.append(" ").append(conditions[i]);
               else
                  sql.append(" OR ").append(conditions[i]);
            }
         }
      }
      else if(null==conditions)
      {
         ;//unconditional do nothing
      }
      else
         throw new DataAccessException("Illegal query parameter.");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt  = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         throw new DataAccessException ("Can not connect to database");
      }

      try
      {
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage());
      }
      finally
      {/*
         try
         {
            Stmt.close();
            Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
       */
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIDAOImpl::delete");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}      	
      }
      return true;
   }
}