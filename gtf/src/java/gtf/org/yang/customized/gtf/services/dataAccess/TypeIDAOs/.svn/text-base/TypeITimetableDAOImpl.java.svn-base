package org.yang.customized.gtf.services.dataAccess.TypeIDAOs;

import java.io.Serializable;
import org.yang.services.dbService.DataAccessException;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.TimetableDAO;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.services.dataAccess.TypeIDAOImpl;
import org.yang.services.dbService.RDBMS;
import org.yang.util.ExceptionBroadcast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.Collections;
import org.yang.services.dbService.DBConnectionCount;

//import com.sun.enterprise.deployment.Project;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeITimetableDAOImpl extends TypeIDAOImpl implements TimetableDAO
{
   public TypeITimetableDAOImpl(String type, RDBMS rdbms)
   {
      super("timetable", rdbms);
   }

   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable()
   {
      StringBuffer sql = new StringBuffer("CREATE TABLE ").append(tablename)
                         .append(" (id varchar(255) not null")
                         .append(", projectId varchar(255)")
                         .append(", projectType varchar(255)")
                         .append(", dueDate date")
                         .append(", primary key(id))");

      try
      {
         rdbms.executeUpdate(sql.toString());
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
         System.out.println("[TypeIProjectDAOImpl::createTable] Exception happen while create project table.");
      }
      return true;
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable[] loadByProject(String pid)throws ProjectDataAccessException
   {
      if(null==pid)
         return new Timetable[0];
      String[] fields = new String[]{"projectId"};
      return load(fields, new String[]{pid} , false);
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable load(String id)throws ProjectDataAccessException
   {
      Timetable[] temp = load(new String[]{id});
      if(null!=temp&&0<temp.length)
         return temp[0];
      else
         return null;
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable[] load(String[] ids)throws ProjectDataAccessException
   {
      if(null==ids||0==ids.length)
         return new Timetable[0];
      String[] fields = new String[ids.length];
      for(int i=0; i<fields.length; i++)
      {
         fields[i] = "id";
      }
      return load(fields, ids, false);
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable[] load(String[] fields, String[] ids, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList projectList = new ArrayList();
      StringBuffer sql = new StringBuffer("select ")
                        .append("id")
                        .append(", projectId")
                        .append(", projectType")
                        .append(", dueDate")
                        .append(" from ").append(tablename);

      if(null!=fields&&null!=ids&&
         fields.length==ids.length&&
         fields.length>0)
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
            return new Timetable[0];

         Timetable p = null;
         String id = null;
         String type = null;
         while(rs.next())
         {
            try
            {
               id = rs.getString("id");
               type = rs.getString("projectType");
               p = ProjectFactory.getFactory().getTimetableTemplate(type);
               p.setId(id);
               p.setProjectId(rs.getString("projectId"));
               p.setProjectType(type);
               p.setDueDate(rs.getDate("dueDate"));

               projectList.add(p);
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
               DBConnectionCount.printConnectionCount("TypeITimetableDAOImpl::load");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }

      Collections.sort(projectList);
      return (Timetable[])projectList.toArray(new Timetable[] {});
   }

   /**
   * Insert new group data into data base.
   *
   * @exception: <{DataAccessException}>
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Timetable timetable)throws ProjectDataAccessException
   {
      if (null==timetable)
      {
         System.out.println("Timetable object is null");
         return false;
      }

      if(null!=load(timetable.getId()))
         return false;

      StringBuffer sql = new StringBuffer("insert into ").append(tablename).append("(")
                         .append("projectId")
                         .append(", projectType")
                         .append(", dueDate")
                         .append(", id")
                         .append(") values (?,?,?,?)");

      System.out.println("Inserting into table");

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
         Stmt.setString(i++, timetable.getProjectId());
         Stmt.setString(i++, timetable.getProjectType());
         Stmt.setDate(i++, timetable.getDueDate());
         Stmt.setString(i++, timetable.getId());
         Stmt.executeUpdate();
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
               DBConnectionCount.printConnectionCount("TypeITimetableDAOImpl::insert");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Timetable timetable)throws ProjectDataAccessException
   {
      return update(new Timetable[] {timetable});
   }

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Timetable[] timetables)throws ProjectDataAccessException
   {
      if (null==timetables||0==timetables.length)
      {
         System.out.println("No project object need to be updated.");
         return false;
      }

      StringBuffer sql = new StringBuffer("update ").append(tablename).append(" set ")
                         .append("projectId = ?")
                         .append(", projectType = ?")
                         .append(", dueDate = ?")
                         .append(" where id = ?");

      System.out.println("Updating project table...");

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
         for(int j=0; j<timetables.length; j++)
         {
            if(null==load(timetables[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, timetables[j].getProjectId());
            Stmt.setString(i++, timetables[j].getProjectType());
            Stmt.setDate(i++, timetables[j].getDueDate());
            Stmt.setString(i++, timetables[j].getId());
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
               DBConnectionCount.printConnectionCount("TypeIScheduleDAOImpl::update");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}            }
      return true;
   }

  /**
   * Delete a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws DataAccessException
   {
      return delete("id", id);
   }
}