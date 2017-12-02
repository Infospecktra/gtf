package org.yang.services.dataAccess;
import org.yang.services.dbService.RDBMS;
import org.yang.services.dbService.DataAccessException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DBConnectionCount;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeIDataDAOImpl extends TypeIDAOImpl implements DataDAO
{
   public TypeIDataDAOImpl(String type, RDBMS rdbms)
   {	
      super(type + "_data", rdbms);
   }

   public boolean createTable()
   {
      	
      boolean isTableExist = tableNameArchives.checkExist(tablename);	
      //System.out.println("[TypeIDataDAOImple::createTable]----->isTableExist="+isTableExist);
      if(isTableExist)
         return true;
      else
      {
         tableNameArchives.addTable(tablename);
         //System.out.println("[TypeIDataDAOImple::createTable]----->add table ("+tablename+") to archives.");
         
      } 
        
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(tablename)
                                            .append(" (id varchar(255)not null")
                                            .append(", name varchar(64)")
                                            .append(", value varchar(255)")
                                            //.append(", owner varchar(16)")
                                            .append(", primary key(id))");

      try
      {
         rdbms.executeUpdate(sql.toString());
      }
      catch(Exception e)
      {
         System.out.println("[TypeIDataDAOImpl::createTable] Exception happen while create project table.");
      }
      return true;
   }

   public Data load(String id)throws DataAccessException
   {
      Data[] datas = load("id", id);
      if(null==datas||0>=datas.length)
        return null;
      else
        return datas[0];
   }

   public Data[] load(String[] ids)throws DataAccessException
   {
      if(null==ids||0>=ids.length)
         return new Data[0];
      String[] fields = new String[ids.length];
      for(int i=0; i<fields.length; i++)
      {
         fields[i] = "id";
      }
      return load(fields, ids);
   }

   public Data[] loadByName(String name)throws DataAccessException
   {
      return load("name", name);
   }

   public Data[] loadAll()throws DataAccessException
   {
      return load((String[])null, (String[])null, true);
   }

   public Data[] load(String field, String value)throws DataAccessException
   {
      if(null==field||null==value)
         return new Data[0];
      return load(new String[]{field}, new String[]{value});
   }

   public Data[] load(String[] fields, String[] values)throws DataAccessException
   {
      return load(fields, values, true);
   }

   public Data[] load(String[] fields, String[] ids, boolean isAnd)throws DataAccessException
   {
      String[] conditions = new String[fields.length];
      for(int i=0; i<conditions.length; i++)
      {
         conditions[i] = fields[i] + "='" + ids[i] + "'";
      }
      return load(conditions, isAnd);
   }

   public Data[] loadByProjectIdAndStageOrder(String pid, String stageOrder) throws DataAccessException
   {
      String[] conditions = new String[1];
      conditions[0] = "id LIKE '" + pid + ":" + stageOrder + ":%'";
      return load(conditions, true);
   }

   public Data[] loadByTimetableDatsByProjectId(String pid) throws DataAccessException
   {
      String[] conditions = new String[1];
      conditions[0] = "id LIKE '" + pid + ":timetable:%'";
      return load(conditions, true);
   }

   public Data[] loadByProjectIdAndScheduleName(String pid, String scheduleName) throws DataAccessException
   {
      String[] conditions = new String[1];
      conditions[0] = "id LIKE '" + pid + ":" + scheduleName + ":%'";
      return load(conditions, true);
   }

   public Data[] load(String[] conditions, boolean isAnd)throws DataAccessException
   {
      ArrayList dataList = new ArrayList();
      StringBuffer sql =
         new StringBuffer("select ").append("id")
                                    .append(", name")
                                    .append(", value")
                                    //.append(", owner")
                                    .append(" from ")
                                    .append(tablename);

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
     ResultSet rs = null;
     Connection Conn = null;
     PreparedStatement Stmt;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql.toString());
     }
     catch (Exception e)
     {
        e.printStackTrace();
        throw new DataAccessException ("can not connect to database");
     }

      try
      {
         rs = Stmt.executeQuery();

         if(rs == null)
            return new Data[0];

         Data data = null;
         while(rs.next())
         {
            try
            {
               int i=1;
               data = new Data();

               data.setId(rs.getString(i++));
               data.setName(rs.getString(i++));
               data.fromString(rs.getString(i++));
               //data.setOwner(rs.getString(i++));
               dataList.add(data);
            }
            catch(Exception e)
            {
               throw new DataAccessException(e.getMessage());
            }
         }
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage());
      }
      finally
      {/*
         try
         {
            rs.close();	
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
            if (rs!=null) rs.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
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
               DBConnectionCount.printConnectionCount("TypeIDataDAOImpl::load");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}      	
      }

      return (Data[])dataList.toArray(new Data[] {});
   }

   public boolean insert(Data data)throws DataAccessException
   {
      return insert(new Data[]{data});
   }

   public boolean insert(Data[] datas)throws DataAccessException
   {
      if (null==datas||0>=datas.length)
      {
         System.out.println("Data object array shall not be null");
         return false;
      }

      StringBuffer sql =
        new StringBuffer("insert into ").append(tablename).append("(")
                                        .append("id")
                                        .append(", name")
                                        .append(", value")
                                        //.append(", owner")
                                        .append(") values (?,?,?)");

      //&System.out.println("Inserting data into table");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         throw new DataAccessException ("Can not connect to database.");
      }

      //&System.out.println("Connected");

      try
      {
         for(int j=0; j<datas.length; j++)
         {
            if(null!=load(datas[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, datas[j].getId());
            Stmt.setString(i++, datas[j].getName());
            Stmt.setString(i++, datas[j].toString());
            //Stmt.setString(i++, datas[j].getOwner());
            Stmt.executeUpdate();
         }
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         e.printStackTrace();
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
               DBConnectionCount.printConnectionCount("TypeIDataDAOImpl::insert");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

   public boolean update(Data[] datas)throws DataAccessException
   {
      if (null==datas||0==datas.length)
      {
         System.out.println("No Data object need to be updated.");
         return false;
      }

      StringBuffer sql =
        new StringBuffer("update ").append(tablename)
                                   .append(" set ")
                                   .append("id = ?")
                                   .append(", name = ?")
                                   .append(", value = ?")
                                   //.append(", owner = ?")
                                   .append(" where id = ?");

      //&System.out.println("Updating data table...");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new DataAccessException ("Can not connect to database");
      }

      //&System.out.println("Connected");

      try
      {
         for(int j=0; j<datas.length; j++)
         {
            if(null==load(datas[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, datas[j].getId());
            Stmt.setString(i++, datas[j].getName());
            Stmt.setString(i++, datas[j].toString());
            //Stmt.setString(i++, datas[j].getOwner());
            Stmt.setString(i++, datas[j].getId());
            Stmt.executeUpdate();
//            System.out.println("id=" + datas[j].getId());
//            System.out.println("name=" + datas[j].getName());
//            System.out.println("value=" + datas[j].toString());
         }
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
               DBConnectionCount.printConnectionCount("TypeIDataDAOImpl::update");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

   public boolean update(Data data)throws DataAccessException
   {
      return update(new Data[]{data});
   }
}