package org.yang.customized.gtf.services.dataAccess.TypeIDAOs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.RDBMS;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.accountmgr.Group;
import org.yang.customized.gtf.services.dataAccess.Stage;
import java.util.Collections;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.sql.Blob;
import org.yang.services.dataAccess.TypeIDAOImpl;
import org.yang.services.dbService.DataAccessException;
import org.yang.customized.gtf.services.dataAccess.StageDAO;
import org.yang.services.dbService.DBConnectionCount;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeIStageDAOImpl extends TypeIDAOImpl implements StageDAO
{
   //private TypeIDataDAOImpl ddao = null;

   /**
    * Constructor
    */
   public TypeIStageDAOImpl(String type, RDBMS rdbms)
   {
      super(type + "_stage", rdbms);
      //ddao = new TypeIDataDAOImpl(type, rdbms);
   }

   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable() //throws AccountDataAccessException
   {
      StringBuffer sql
        = new StringBuffer("CREATE TABLE ").append(tablename)
                                           .append(" (id varchar(255)not null, ")
                                           .append("projectId varchar(255), ")
                                           .append("name varchar(255), ")
                                           .append("description varchar(255), ")
                                           .append("status int, ")
                                           .append("doneDate bigint, ")
                                           .append("lastUpdateDate bigint, ")
                                           //.append("note varchar(255), ")
                                           .append("note text, ")
                                           .append("projectType varchar(255), ")
                                           .append("eOrder int, ")
                                           .append("primary key(id))");

      try
      {
         rdbms.executeUpdate(sql.toString());
         createStatusStage();
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
         //e.printStackTrace();
         //System.out.println("[TypeIStageDAOImpl::createTable] Exception happen while create project table.");
      }
      return true;
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage load(String id)throws ProjectDataAccessException
   {
      if (id == null)
      {
         System.out.println("Project id can't be null .");
         return null;
      }

      Stage[] stages = load("id", id);

      if(null!=stages)
      {
         switch(stages.length)
         {
            case 0: return null;
            case 1: return stages[0];
         }
      }

      throw new ProjectDataAccessException();
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String[] sids)throws ProjectDataAccessException
   {
      return null;
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] loadByType(String type)throws ProjectDataAccessException
   {
      if (null==type)
      {
         System.out.println("ProjectType can't be null .");
         return null;
      }

      return load("projectType", type);
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] loadByProject(String projectId)throws ProjectDataAccessException
   {
      if (null==projectId)
      {
         System.out.println("ProjectId can't be null .");
         return null;
      }

      return load("projectId", projectId);
   }

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Stage[] loadByName(String name)throws ProjectDataAccessException
   {
      if (null==name)
      {
         System.out.println("Stagename can't be null .");
         return null;
      }

      return load("name", name);
   }

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Stage[] loadAll()throws ProjectDataAccessException
   {
      return load((String[])null, (String[])null);
   }


  /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String field, String id)throws ProjectDataAccessException
   {
      if(null!=field&&null!=id)
         return load(new String[]{field}, new String[]{id});
      else if(null==field&&null==id)
         return load((String[])null, (String[])null);
      return new Stage[0];
   }

   public Stage[] load(String[] fields, String[] ids)throws ProjectDataAccessException
   {
      return load(fields, ids, true);
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String[] fields, String[] ids, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList stageList = new ArrayList();
      StringBuffer sql = new StringBuffer("select ")
                         .append("projectType, eOrder, name, id, projectId, description, status, doneDate, lastUpdateDate, note")
                         .append(" from ").append(tablename);

      if(null!=fields&&null!=ids&&fields.length==ids.length&&fields.length>0)
      {
        sql.append(" where");
        for(int i=0; i<fields.length; i++)
         {
            if(isAnd)
            {
               if(0==i)
                  sql.append(" ").append(fields[i]).append(" = ?");
               else
                  sql.append(" AND ").append(fields[i]).append(" = ?");
            }
            else
            {
               if(0==i)
                  sql.append(" ").append(fields[i]).append(" = ?");
               else
                  sql.append(" OR ").append(fields[i]).append(" = ?");
            }
         }
      }
      else if(null==fields&&null==ids)
         sql.append(" where ").append("id").append(" != 0");
      else
         throw new ProjectDataAccessException("Illegal query parameter.");

//System.out.println("-----------------------------------------------------------------------------------------------");
//System.out.println(sql.toString());
//System.out.println("-----------------------------------------------------------------------------------------------");

     Connection Conn = null;
     PreparedStatement Stmt;
     ResultSet rs = null;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql.toString());
     }
     catch (Exception e)
     {
        e.printStackTrace();
        throw new ProjectDataAccessException ("can not connect to database");
     }

      try
      {
         if(null!=fields&&null!=ids)
         {
            for(int i=0; i<ids.length; i++)
            {
               Stmt.setString(i+1, ids[i]);
            }
         }
         rs = Stmt.executeQuery();

         if(rs == null)
            return new Stage[0];

         Stage s = null;
         String projectType = null;
         int order = 0;
         while(rs.next())
         {
            try
            {
               int i=1;
               projectType = rs.getString(i++);
               order = rs.getInt(i++);
               s = ProjectFactory.getFactory().getStageTemplate(projectType, order);
               s.setProjectType(projectType);
               s.setOrder(order);
               s.setName(rs.getString(i++));
               s.setId(rs.getString(i++));
               s.setProjectId(rs.getString(i++));
               s.setDescription(rs.getString(i++));
               s.setStatus(rs.getInt(i++));
               s.setDoneDate(rs.getLong(i++));
               s.setLastUpdateDate(rs.getLong(i++));
               s.setNote(rs.getString(i++));
               stageList.add(s);
            }
            catch(Exception e)
            {
               throw new ProjectDataAccessException(e.getMessage());
            }
         }
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("TypeIStageDAOImpl::load");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }

      Collections.sort(stageList);
      return (Stage[])stageList.toArray(new Stage[] {});
   }

   /**
   * Insert new group data into data base.
   *
   * @exception: DataAccessException
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Stage s)throws ProjectDataAccessException
   {
      if (s == null)
      {
         System.out.println("Stage object is null");
         return false;
      }

      if(null!=load(s.getId()))
         return false;

      StringBuffer sql = new StringBuffer("insert into ").append(tablename).append("(")
                         .append("id, projectId, name, description, status, doneDate, lastUpdateDate, note, projectType, eOrder")
                         .append(") values (?,?,?,?,?,?,?,?,?,?)");

      System.out.println("Inserting stage into table");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException ("Can not connect to database.");
      }

      System.out.println("Connected");

      try
      {
         int i = 1;
         Stmt.setString(i++, s.getId());
         Stmt.setString(i++, s.getProjectId());
         Stmt.setString(i++, s.getName());
         Stmt.setString(i++, s.getDescription());
         Stmt.setInt(i++, s.getStatus());
         Stmt.setLong(i++, s.getDoneDate());
         Stmt.setLong(i++, s.getLastUpdateDate());
         Stmt.setString(i++, s.getNote());
         Stmt.setString(i++, s.getProjectType());
         Stmt.setInt(i++, s.getOrder());
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("TypeIStageDAOImpl::insert");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage s)throws ProjectDataAccessException
   {
      return update(s, true);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage s, boolean updateSN)throws ProjectDataAccessException
   {
      if (null==s)
      {
         System.out.println("Stage object is null");
         return false;
      }

      if(null==load(s.getId()))
         return false;

      Stage[] stages = new Stage[1];
      stages[0] = s;
      return update(stages, updateSN);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage[] stages)throws ProjectDataAccessException
   {
      return update(stages, true);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage[] s,  boolean updateSN)throws ProjectDataAccessException
   {
      if (null==s||0==s.length)
      {
         System.out.println("No stage object need to be updated.");
         return false;
      }

      StringBuffer sql = new StringBuffer("update ").append(tablename).append(" set ")
                        .append("id =? , projectId = ?, name =?, ")
                        .append("description = ?, status = ?, doneDate = ?," )
                        .append("lastUpdateDate = ?, note = ?, projectType = ?, eOrder = ?")
                        .append(" where id = ?");

      System.out.println("Updating stage table...");

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
         throw new ProjectDataAccessException ("Can not connect to database");
      }

      System.out.println("Connected");

      try
      {
         for(int j=0; j<s.length; j++)
         {
            if(null==load(s[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, s[j].getId());
            Stmt.setString(i++, s[j].getProjectId());
            Stmt.setString(i++, s[j].getName());
            Stmt.setString(i++, s[j].getDescription());
            Stmt.setInt(i++, s[j].getStatus());
            Stmt.setLong(i++, s[j].getDoneDate());
            Stmt.setLong(i++, s[j].getLastUpdateDate());
            Stmt.setString(i++, s[j].getNote());
            Stmt.setString(i++, s[j].getProjectType());
            Stmt.setInt(i++, s[j].getOrder());
            Stmt.setString(i++, s[j].getId());
            Stmt.executeUpdate();
         }
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("TypeIStageDAOImpl::update");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

   public boolean deleteByProject(String project)throws DataAccessException
   {
      if (null==project)
      {
         System.out.println("Project can't be null .");
         return false;
      }

      return delete("projectId", project);
   }

   public boolean deleteByName(String name) throws ProjectDataAccessException
   {
      try
      {
         if (null==name)
         {
            System.out.println("Stage name can't be null.");
            return false;
         }

         return delete("name", name);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   private boolean createStatusStage() throws ProjectDataAccessException
   {
      try
      {
         Stage s = new Stage(new DataGroup[0]);
         s.setId("0");
         s.setName("System State");
         s.setProjectId("n/a");
         s.setDescription("This record keep project table state.");
         s.setStatus(0);
         s.setDoneDate(0); // it's domain
         s.setLastUpdateDate(0);
         s.setNote("n/a");
         s.setOrder(0);
         return insert(s);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

  /**
   *   test stub
   */
   public static void main(String[] args)
   {
      try
      {
      RDBMS rdbms = RDBMSFactory.getInstance().getRDBMS("jdbc:mysql://localhost:3306/content",
                                                        "jumbo",
                                                        "02200220",
                                                        "org.gjt.mm.mysql.Driver");

      TypeIStageDAOImpl dao = new TypeIStageDAOImpl(org.yang.customized.gtf.services.dataAccess.DAOFactory.WAITING,
                                                    rdbms);
      // Create table
      dao.createTable();

      // Insert Project
      Stage s1 = new Stage(new DataGroup[0]);
      s1.setId("132432432423431");
      s1.setName("My First Stage");
      s1.setProjectId("34rffcw33445");
      s1.setDescription("It's my First Project.");
      s1.setDoneDate(0);
      s1.setStatus(0);
      s1.setDoneDate(0);
      s1.setOrder(0);
      s1.setNote("EsCelline");

      Stage s2 = new Stage(new DataGroup[0]);
      s2.setId("132432432423432");
      s2.setName("My Second Stage");
      s2.setProjectId("34rffcw33445");
      s2.setDescription("It's my First Project.");
      s2.setDoneDate(0);
      s2.setStatus(0);
      s2.setDoneDate(0);
      s2.setOrder(0);
      s2.setNote("EsCelline");
      try
      {
         dao.insert(s1);
         System.out.println("==========================================");
         dao.insert(s2);
         System.out.println("==========================================");
      } catch(Exception e) { e.printStackTrace(); }

      // Load Project
      Stage[] s3 = null;
      try
      {
         s3 = dao.loadByProject("34rffcw33445");
         //s3 = dao.loadAll();
         System.out.println("==========================================");
      } catch(Exception e) { e.printStackTrace(); }

      for(int i=0; i<s3.length; i++)
      {
         System.out.println("==========================================");
         System.out.println(s3[i].getId());
         System.out.println(s3[i].getProjectId());
         System.out.println(s3[i].getName());
         System.out.println(s3[i].getDescription());
         System.out.println(s3[i].getStatus());
         System.out.println(s3[i].getDoneDate());
         System.out.println(s3[i].getLastUpdateDate());
         System.out.println(s3[i].getNote());
         System.out.println(s3[i].getOrder());
      }

      System.out.println("Done!");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}