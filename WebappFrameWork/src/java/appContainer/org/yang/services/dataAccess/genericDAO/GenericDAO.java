package org.yang.services.dataAccess.genericDAO;
import org.yang.services.dbService.RDBMS;
import java.io.BufferedReader;
import org.yang.services.dbService.DataAccessException;
import org.yang.util.SMUtility;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.Connection;
import org.yang.util.ExceptionBroadcast;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import org.yang.services.dbService.DBConnectionCount;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class GenericDAO
{
    protected RDBMS rdbms = null;
    protected boolean isDebuging = false;

   /**
    * Constructor
    */
   public GenericDAO(RDBMS rdbms)
   {
      this(rdbms, false);
   }

   public GenericDAO(RDBMS rdbms, boolean isDebuging)
   {
      if(null==rdbms)
      {
         System.out.println("[GenericDAO::GenericDAO] RDBMS cann't be null. ");
         return ;
      }

      this.rdbms = rdbms;
      this.isDebuging = isDebuging;
   }

   public void close()
   {
      rdbms.close();
   }
   
   public boolean createTable(Storable storable)
   {
      try
      {
         rdbms.executeUpdate(storable.prepareSQLCreateTable());
      }
      catch(Exception e)
      {
         //System.out.println("[GenericDAO::createTable] Exception happen while create table " + storable.getTablename() + ":" + e.getMessage());
         return false;
      }
      return true;
   }

   /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropTable(Storable storable)
   {
      try
      {
         rdbms.executeUpdate(storable.prepareSQLDropTable());
      }
      catch(Exception e)
      {
         System.out.println("[GenericDAO::dropTable] Exception happen while create table.");
         return false;
      }
      return true;
   }

   public StorableList load(Storable storable, BufferedReader in)throws DataAccessException
   {
      if(null==storable)
         throw new DataAccessException("Storable type template cannot be null.");

      String temp = null;
      try
      {
         if(null==(temp=in.readLine()))
            throw new DataAccessException("Cannot find defination of columns.");

         String[] columnNames = SMUtility.splitByToken(temp, ";", true);
         int columnLength = columnNames.length;
         String[] columnValues = null;
         HashMap dataMap = new HashMap();
         Storable newStorable = null;
         StorableList list = new StorableList();
         while(null!=(temp=in.readLine()))
         {
            columnValues = SMUtility.splitByToken(temp, ";", true);
            if(columnLength!=columnValues.length)
               throw new DataAccessException("Column length doesn't match, check your datas.");
            dataMap.clear();
            for(int i=0; i<columnLength; i++)
            {
               dataMap.put(columnNames[i], columnValues[i]);
            }
            newStorable = ((Storable)SMUtility.cloneObject(storable));
            newStorable.importData(dataMap);
            list.add(newStorable);
         }
         return list;
      }
      catch(DataAccessException dae)
      {
         throw dae;
      }
      catch(Exception e)
      {
         throw new DataAccessException("Unable to read data from reader:" + e.getMessage());
      }
   }

   public Storable loadStorable(Storable storable)throws DataAccessException
   {
      String keyName = storable.getPrimaryKey();
      StorableList list = load(storable, keyName+"='"+storable.get(keyName)+"'");
      if(null==list||0==list.size())
         return null;
      return list.get(0);
   }

   public StorableList load(Storable storable)throws DataAccessException
   {
      return load(storable, (String)null);
   }

   public StorableList load(Storable storable, String[] conditions, boolean isAnd)throws DataAccessException
   {
      if(null==conditions)
      {
         return load(storable);
      }

      String conditionString = "";
      String logic = " AND ";
      if(!isAnd)
         logic = " OR ";

      for(int i=0; i<conditions.length; i++)
      {
         conditionString += conditions[i];
         if(i!=conditions.length-1)
            conditionString += logic;
      }
      ////&System.out.println("[GenricDAO::load]-----conditionString="+conditionString);
      return load(storable, conditionString);
   }

   public StorableList load(Storable storable, String conditions)throws DataAccessException
   {
      if (null==storable)
      {
         throw new DataAccessException("Storable object can't be null .");
      }

      //LoadDirector director = new LoadDirector();
      DBExcutionDirector director = null;
      if(null==(director=storable.getLoadDirector()))
      {
         director = new LoadDirector();
      }

      director.setConditions(conditions);
      director.setTemplate(storable);
      excuteSQL(director);

      return director.getStorableList();
   }

   public StorableList load(LoadDirector storableLoader, String conditions)throws DataAccessException
   {
      if (null==storableLoader)
      {
         throw new DataAccessException("Load Director object can't be null .");
      }

      storableLoader.setConditions(conditions);
      excuteSQL(storableLoader);

      StorableList list = storableLoader.getStorableList();

      return list;
   }

   public boolean insert(Storable storable)throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::insert] Storable object shall not be null");
         return false;
      }

      InsertDirector director = new InsertDirector();
      director.addStorable(storable);
      excuteSQL(director);

      return true;
   }

   public boolean insert(Storable storable, String conditions)throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::insert] Storable object shall not be null");
         return false;
      }

      InsertDirector director = new InsertDirector();
      director.setConditions(conditions);
      director.addStorable(storable);
      excuteSQL(director);

      return true;
   }

   public boolean insert(StorableList storables)throws DataAccessException
   {
      if(null==storables)
      {
         System.out.println("[GenericDAO::insert] Storable object shall not be null");
         return false;
      }

      if(0==storables.size())
         return true;

      InsertDirector director = new InsertDirector();
      director.addStorableList(storables);
      excuteSQL(director);

      return true;
   }

   public boolean update(Storable storable)throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::update] Storable object shall not be null");
         return false;
      }

      UpdateDirector director = new UpdateDirector();
      director.addStorable(storable);
      excuteSQL(director);

      return true;
   }

   public boolean update(StorableList storables)throws DataAccessException
   {
      if(null==storables)
      {
         System.out.println("[GenericDAO::update] StorableList object shall not be null");
         return false;
      }

      if(0==storables.size())
         return true;

      UpdateDirector director = new UpdateDirector();
      director.addStorableList(storables);
      excuteSQL(director);

      return true;
   }

   public boolean update(Storable storable, String conditions)throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::update] Storableobject shall not be null");
         return false;
      }

      UpdateDirector director = new UpdateDirector();
      director.setConditions(conditions);
      director.addStorable(storable);
      excuteSQL(director);

      return true;
   }

   public boolean delete(Storable storable) throws DataAccessException
   {
////&System.out.println(">>>>>>>>>>>>>>>>" + storable);
      String idKey = null;
      Object idVal = null;
      if (null==storable||
          null==(idKey=storable.getPrimaryKey())||
          null==(idVal=storable.get(idKey)))
      {
         System.out.println("[GenericDAO::delete] Primary key can't be null.");
         return false;
      }

      return delete(storable, idKey + "='" + idVal + "'");
   }

   public boolean deleteAll(Storable storable) throws DataAccessException
   {
      return delete(storable, (String[])null, (String[])null);
   }

   public boolean delete(Storable storable, String field, String val)throws DataAccessException
   {
      if (null==field||null==val)
      {
         System.out.println("[GenericDAO::delete] Deleted field can't be null .");
         return false;
      }
      return delete(storable, new String[]{field}, new String[]{val});
   }

   public boolean delete(Storable storable, String[] fields, String[] vals)throws DataAccessException
   {
      String[] conditions = new String[fields.length];
      for(int i=0; i<conditions.length; i++)
      {
         conditions[i] = fields[i] + "='" + vals[i] + "'";
      }
      return delete(storable, conditions, true);
   }

   public boolean delete(Storable storable, String[] conditions, boolean isAnd)throws DataAccessException
   {
      StringBuffer condition = new StringBuffer("");
      if(null!=conditions&&conditions.length>0)
      {
         for(int i=0; i<conditions.length; i++)
         {
            if(isAnd)
            {
               if(0==i)
                  condition.append(" ").append(conditions[i]);
               else
                  condition.append(" AND ").append(conditions[i]);
            }
            else
            {
               if(0==i)
                  condition.append(" ").append(conditions[i]);
               else
                  condition.append(" OR ").append(conditions[i]);
            }
         }

         return delete(storable, condition.toString());
      }
      else if(null==conditions||0==conditions.length)
      {
         return delete(storable, null);
      }

      throw new DataAccessException("Illegal query parameter.");
   }

   public boolean delete(Storable storable, String conditions)throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::delete] Storable object shall not be null");
         return false;
      }

      DeleteDirector director = new DeleteDirector();
      director.setConditions(conditions);
      director.setTemplate(storable);

      return excuteSQL(director);
   }

   public boolean exists(Storable storable, String conditions) throws DataAccessException
   {
      if(null==storable)
      {
         System.out.println("[GenericDAO::exists] Storable object shall not be null");
         return false;
      }

      if(null==conditions)
      {
         String idKey = null;
         Object idVal = null;
         if (null==storable||
             null==(idKey=storable.getPrimaryKey())||
             null==(idVal=storable.get(idKey)))
         {
            System.out.println("[GenericDAO::delete] Both storable and primary key can't be null.");
            return false;
         }
         conditions = idKey + "='" + idVal + "'";
      }

      CheckExistDirector director = new CheckExistDirector();
      director.setConditions(conditions);
      director.setTemplate(storable);

      excuteSQL(director);

      return director.getTargetIsExist();
   }

   public boolean excuteSQL(DBExcutionDirector director)throws DataAccessException
   {
      Connection        Conn = null;
      PreparedStatement Stmt = null;
      try
      {
         Conn = rdbms.getDBConnection();
         ////&System.out.println("[GenericDAO::excuteSQL]director.getSQLCommand()="+director.getSQLCommand());
         Stmt = Conn.prepareStatement(director.getSQLCommand());
         director.execue(Stmt);

         try { Conn.commit(); }catch(Exception e) {}
      }
      catch (DataAccessException dae)
      {
         if(isDebuging)
            System.out.println("[GenericDAO::excuteSQL] DataAccessException happen, print SQL command : " + director.getSQLCommand());
         throw dae;
      }
      catch (Exception e)
      {
         rdbms.onException(e);
         if(isDebuging)
            System.out.println("[GenericDAO::excuteSQL] Exception happen, print SQL command : " + director.getSQLCommand());
         throw new DataAccessException ("[GenericDAO::excuteSQL] Unable to handle data : " + e.getMessage());
      }
      finally
      {
         try
         {
            if(null!=Stmt)
               Stmt.close();
            if(null!=Conn)
               Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("GenericDAO::excuteSQL");   
      }

      return true;
   }

   public StorableList executeStatisticsSQL(SQLQueryIF sqso) throws DataAccessException
   {   
       Connection      Conn = null;
       PreparedStatement Stmt = null;          	
       ResultSet rs = null;
       StorableList slist = null; 
        
       if(sqso==null)
       	   throw new DataAccessException ("[GenericDAO::executeStatisticsSQL] StorableGHMessage object can't be null. ");
       	                   
       String sqlCommand = "";
       try
       {
          Conn = rdbms.getDBConnection();
          slist = new StorableList();
          if(!(sqso instanceof SerialSQLQueryIF))
          {   
             try
             {
                 ////&System.out.println("[GenericDAO::executeStatisticsSQL] NonSerialSQLQueryIF scope: ");
                 sqlCommand = sqso.getSQLCommand();
                 Stmt = Conn.prepareStatement(sqlCommand);
                 ////&System.out.println("[GenericDAO::executeStatisticsSQL] Print SQL command : " + sqlCommand);    	
              
                 rs = Stmt.executeQuery();
                 try { Conn.commit(); }catch(Exception e) {}
                 
                 rs = sqso.setResultSet(rs);
                 
                 if(null!=rs)
                    rs.close();	
                 if(null!=Stmt)
                   Stmt.close();
               if(sqso instanceof Storable)
                  slist.add((Storable)sqso);                    
             }
             catch(Exception e)
             {
                e.printStackTrace();	
             }
          }
          else 
          {
             ////&System.out.println("[GenericDAO::executeStatisticsSQL] SerialSQLQueryIF scope: ");
          	
             int i = 0;          
             try
             {
                SerialSQLQueryIF serialSQLObject = (SerialSQLQueryIF)sqso;     
          
               
                while(serialSQLObject.hasMoreCommand())
                {
                   sqlCommand = serialSQLObject.getSQLCommand();
                   ////&System.out.println("[GenericDAO::executeStatisticsSQL] Print SQL command : " + sqlCommand);
                   Stmt = Conn.prepareStatement(sqlCommand);
                   rs = Stmt.executeQuery();
            
                   try { Conn.commit(); }catch(Exception e) {}
        
                   rs = serialSQLObject.setResultSet(rs);
                   if(null!=rs)
                      rs.close();	
                   if(null!=Stmt)
                      Stmt.close();                   
                }
                
                slist.add((Storable)serialSQLObject);
               
             }
             catch(Exception e)
             {
                e.printStackTrace();	
             }		       	
          }  
          return slist;           
      }
      catch (DataAccessException dae)
      {
         if(isDebuging)
            System.out.println("[GenericDAO::statisticsExcuteSQL] DataAccessException happen, print SQL command : " + sqso.getSQLCommand());
         throw dae;
      }
      catch (Exception e)
      {
         rdbms.onException(e);
         if(isDebuging)
            System.out.println("[GenericDAO::statisticsExcuteSQL] Exception happen, print SQL command : " + sqso.getSQLCommand());
         throw new DataAccessException ("[GenericDAO::statisticsExcuteSQL] Unable to handle data : " + e.getMessage());
      }
      finally
      {
         try
         {
            if(null!=rs)
               rs.close();	
            if(null!=Stmt)
               Stmt.close();
            
            if(null!=Conn)
               Conn.close();
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("GenericDAO::executeStatisticsSQL");   
               
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }  
   }
   
   public StorableList executeStatisticsSQLs(StorableList storables) throws DataAccessException
   {
      	
       Connection  Conn = null;       
       PreparedStatement[] Stmts = null;          	
       ResultSet[] rss = null;
       StorableList slist = null; 
       int sqlNum = 0;     	
        
       if(storables==null)
       {
       	   // throw new DataAccessException ("[GenericDAO::executeStatisticsSQL] StorableGHMessage object can't be null. ");
           return null;           
       }
       
       SQLQueryIF queryComponent = null; 
       
       try
       {         
           Conn = rdbms.getDBConnection();
           slist = new StorableList();
           
           for(int i =0;i<storables.size();i++)
           {
               queryComponent = (SQLQueryIF)storables.get(i);	
                             
               String[] sqlCommands = queryComponent.getSQLCommands();
               try
               {  
                  if(sqlCommands==null)
                      throw new DataAccessException("[GenericDAO::executeStatisticsSQLs]-----sqlComments can't be null.");   

                  sqlNum = sqlCommands.length;   
                  ////&System.out.println("[GenericDAO::executeStatisticsSQLs]-----sqlCommands.length="+sqlNum);
               
                  Stmts = new PreparedStatement[sqlNum];
                  rss = new ResultSet[sqlNum];             
                  for (int j = 0; j < sqlNum;j++)
                  {
                      //System.out.println("[GenericDAO::executeStatisticsSQLs] Print SQL command["+j+"] : " + sqlCommands[j]);
                      
                      Stmts[j] = Conn.prepareStatement(sqlCommands[j]);
                      rss[j] = Stmts[j].executeQuery();                    
                      try { Conn.commit(); }catch(Exception e) {}
        
                  }              
                  rss = queryComponent.setResultSets(rss);
                  ////&System.out.println("[GenericDAO::executeStatisticsSQLs] returned rss = " + rss);    	
               }
               catch(DataAccessException dae)
               {
               	    dae.printStackTrace();
                   //throw dae;	
               }
               
               slist.add((Storable)queryComponent);	               
               for (int k = 0;k < sqlNum;k++)
               { 	
                  if(null!=rss[k])
                     rss[k].close();	
                  if(null!=Stmts[k])
                     Stmts[k].close();
               }               
           }       
           if(null!=Conn)
               Conn.close();
   
      }
      catch (DataAccessException dae)
      {
         System.out.println("[GenericDAO::statisticsExcuteSQLs] DataAccessException happen, print SQL command : " + queryComponent.getSQLCommand());
         throw dae;
      }
      catch (Exception e)
      {
         rdbms.onException(e);
         //&System.out.println("[GenericDAO::statisticsExcuteSQLs] Exception happen, print SQL command : " + queryComponent.getSQLCommand());
         throw new DataAccessException ("[GenericDAO::statisticsExcuteSQLs] Unable to handle data : " + e.getMessage());
      }
      finally
      {
         try
         {
 	
            for (int i = 0;i < sqlNum;i++)
            { 	
               if(null!=rss[i])
                  rss[i].close();	
               if(null!=Stmts[i])
                  Stmts[i].close();
            }
            if(null!=Conn)
                Conn.close();
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("GenericDAO::executeStatisticsSQLs");   
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      ////&System.out.println("[GenericDAO::]executeStatisticsSQLs(storables)-----StorableList.size()---"+slist.size());
      return slist;
        
   }

   public boolean executeUpdateQuerySet(QuerySet querySet) throws DataAccessException
   {
      //&System.out.println("[GenericDAO::executeUpdateQuerySet] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      Connection Conn = null;
      PreparedStatement Stmt = null;
      String sql = null;
      try
      {
         Conn = rdbms.getDBConnection();
         while(null!=(sql=querySet.nextSQLCommand()))
         {
            Stmt = Conn.prepareStatement(sql);
            Stmt.executeUpdate();
            try { Conn.commit(); }catch(Exception e) {}
            querySet.handleResultSet(null);
         }
      }
      catch (DataAccessException dae)
      {
         dae.printStackTrace();
         if(isDebuging)
            System.out.println("[GenericDAO::executeUpdateQuerySet] DataAccessException happen, print SQL command : " + sql);
         throw dae;
      }
      catch (Exception e)
      {
         e.printStackTrace();
         rdbms.onException(e);
         if(isDebuging)
            System.out.println("[GenericDAO::executeUpdateQuerySet] Exception happen, print SQL command : " + sql);
         throw new DataAccessException ("[GenericDAO::executeUpdateQuerySet] Unable to handle data : " + e.getMessage());
      }
      finally
      {
         try
         {
            if(null!=Stmt)
               Stmt.close();
            if(null!=Conn)
               Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("GenericDAO::executeUpdateQuerySet");   
      }

      return true;
   }

   public boolean executeQuerySet(QuerySet querySet) throws DataAccessException
   {   
      Connection Conn = null;
      PreparedStatement Stmt = null;
      ResultSet result = null;
      String sql = null;
      try
      {
         Conn = rdbms.getDBConnection();
         while(null!=(sql=querySet.nextSQLCommand()))
         {
            Stmt = Conn.prepareStatement(sql);
            result = Stmt.executeQuery();
            try { Conn.commit(); }catch(Exception e) {}
            if(!querySet.handleResultSet(result))
            {
               try
               {
                  if(!result.wasNull())	
                     result.close();
   
                  if(null!=Stmt)
                     Stmt.close();
               } catch(Exception e) { e.printStackTrace(); }

               break;
            }
         }
      }
      catch (DataAccessException dae)
      {
         dae.printStackTrace();
         if(isDebuging)
            System.out.println("[GenericDAO::excuteSQL] DataAccessException happen, print SQL command : " + sql);
         throw dae;
      }
      catch (Exception e)
      {
         e.printStackTrace();
         rdbms.onException(e);
         if(isDebuging)
            System.out.println("[GenericDAO::excuteSQL] Exception happen, print SQL command : " + sql);
         throw new DataAccessException ("[GenericDAO::excuteSQL] Unable to handle data : " + e.getMessage());
      }
      finally
      {
         try
         {
            if(!result.wasNull())	
               result.close();
            if(null!=Stmt)
               Stmt.close();
            if(null!=Conn)
               Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         if(DBConnectionCount.ConnectCount>0)
            DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
         DBConnectionCount.printConnectionCount("GenericDAO::excuteSQL");   
      }

      return true;
   }

   public static void main(String[] argv)
   {
      org.yang.services.dbService.RDBMS rdbms = org.yang.services.dbService.RDBMSFactory.getInstance()
                       .getRDBMS("jdbc:mysql://10.13.13.46:3306/ghdata", "imds", "cashbird", "com.mysql.jdbc.Driver");
      try
      {
         GenericDAO gdao = new GenericDAO(rdbms);

         TestStorableObject test = new TestStorableObject();
         test.setTablename("TestGDAO");

         gdao.createTable(test);

         test.setId("test_id");
         long now = System.currentTimeMillis();
         test.setDate(new Date(now));
         test.setTime(new Time(now));

         try
         {
            gdao.update(test);
         }
         catch(Exception e) { e.printStackTrace(); }

         try
         {
            gdao.insert(test);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }

         try
         {
            gdao.insert(test);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }


         if(true)
         {
            StorableList list = gdao.load(test);
            for(int i=0; i<list.size(); i++)
            {
               try
               {
                  test = (TestStorableObject)list.get(i);
                  //&System.out.println("(" + i + ") id ----> " + test.getId());
               }
               catch(Exception e)
               {
                  e.printStackTrace();
               }
            }
         }

         //gdao.dropTable(test);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         //&System.out.println("data access exception: \n");
      }
   }
}