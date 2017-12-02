package org.yang.customized.gtf.services.dataAccess.TypeIDAOs;

import java.io.Serializable;
import org.yang.services.dbService.DataAccessException;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import org.yang.customized.gtf.services.dataAccess.ScheduleDAO;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.services.dataAccess.TypeIDAOImpl;
import org.yang.services.dbService.RDBMS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.yang.util.ExceptionBroadcast;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.Collections;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.services.dbService.DBConnectionCount;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeIScheduleDAOImpl extends TypeIDAOImpl implements ScheduleDAO
{
   public TypeIScheduleDAOImpl(String type, RDBMS rdbms)
   {
      super("schedule", rdbms);
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
                         .append(", timetableId varchar(255)")
                         .append(", projectType varchar(255)")
                         .append(", name varchar(255)")
                         .append(", displayName varchar(255)")
                         .append(", day int")
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
   public String[] loadTimetableIdsByDate(java.sql.Date dateFrom,
                                          java.sql.Date dateTo )throws ProjectDataAccessException
   {
      ArrayList timetableIdList = new ArrayList();
      StringBuffer sql = new StringBuffer("select  timetableId from ")
                             .append(tablename)
                             .append(" where dueDate >= ?")
                             .append(" AND dueDate <= ?");
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
        throw new ProjectDataAccessException ("can not connect to database");
     }

      try
      {
         Stmt.setDate(1, dateFrom);
         Stmt.setDate(2, dateTo);
         rs = Stmt.executeQuery();

         if(rs == null)
            return new String[0];

         String tid = null;
         while(rs.next())
         {
            try
            {
               tid = rs.getString("timetableId");
               if(!timetableIdList.contains(tid))
                  timetableIdList.add(tid);
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
      {
      	/*
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
               DBConnectionCount.printConnectionCount("TypeIScheduleDAOImpl::loadTimetableIdsByDate");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}              
      }

      return (String[])timetableIdList.toArray(new String[] {});
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule load(String id)throws ProjectDataAccessException
   {
      Schedule[] temp = load(new String[]{id});
      if(null!=temp&&0<temp.length)
         return temp[0];
      return null;
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule[] load(String[] ids)throws ProjectDataAccessException
   {
      if(null==ids||0==ids.length)
         return new Schedule[0];
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
   public Schedule[] loadByTimetable(String tid)throws ProjectDataAccessException
   {
      return load(new String[]{"timetableId"}, new String[]{tid}, true);
   }

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule[] load(String[] fields, String[] ids, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList scheduleList = new ArrayList();
      StringBuffer sql = new StringBuffer("select ")
                         .append("id")
                         .append(", timetableId")
                         .append(", projectType")
                         .append(", name")
                         .append(", displayName")
                         .append(", day")
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
            return new Schedule[0];

         Schedule p = null;
         String id = null;
         String type = null;
         String name = null;
         while(rs.next())
         {
            try
            {
               type = rs.getString("projectType");
               name = rs.getString("name");
               p = ProjectFactory.getFactory().getScheduleTemplate(type, name);
               p.setId(rs.getString("id"));
               p.setTimetableId(rs.getString("timetableId"));
               p.setProjectType(type);
               p.setName(name);
               p.setDisplayName(rs.getString("displayName"));
               p.setDay(rs.getInt("day"));
               p.setDueDate(rs.getDate("dueDate"));

               scheduleList.add(p);
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
               DBConnectionCount.printConnectionCount("TypeIScheduleDAOImpl::load");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}               
      }

      Collections.sort(scheduleList);
      return (Schedule[])scheduleList.toArray(new Schedule[] {});
   }

   /**
   * Insert new group data into data base.
   *
   * @exception: <{DataAccessException}>
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Schedule s)throws ProjectDataAccessException
   {
      if (null==s)
      {
         System.out.println("Timetable object is null");
         return false;
      }

      if(null!=load(s.getId()))
         return false;

      StringBuffer sql = new StringBuffer("insert into ").append(tablename).append("(")
                         .append("timetableId")
                         .append(", projectType")
                         .append(", name")
                         .append(", displayName")
                         .append(", day")
                         .append(", dueDate")
                         .append(", id")
                         .append(") values (?,?,?,?,?,?,?)");

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
         Stmt.setString(i++, s.getTimetableId());
         Stmt.setString(i++, s.getProjectType());
         Stmt.setString(i++, s.getName());
         Stmt.setString(i++, s.getDisplayName());
         Stmt.setInt(i++, s.getDay());
         Stmt.setDate(i++, s.getDueDate());
         Stmt.setString(i++, s.getId());
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
               DBConnectionCount.printConnectionCount("TypeIScheduleDAOImpl::insert");   
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
   public boolean update(Schedule schedule)throws ProjectDataAccessException
   {
      return update(new Schedule[] {schedule});
   }

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Schedule[] schedules)throws ProjectDataAccessException
   {
      if (null==schedules||0==schedules.length)
      {
         System.out.println("No project object need to be updated.");
         return false;
      }

      StringBuffer sql = new StringBuffer("update ").append(tablename).append(" set ")
                         .append("timetableId = ?")
                         .append(", projectType = ?")
                         .append(", name = ?")
                         .append(", displayName = ?")
                         .append(", day = ?")
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
         for(int j=0; j<schedules.length; j++)
         {
            if(null==load(schedules[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, schedules[j].getTimetableId());
            Stmt.setString(i++, schedules[j].getProjectType());
            Stmt.setString(i++, schedules[j].getName());
            Stmt.setString(i++, schedules[j].getDisplayName());
            Stmt.setInt(i++, schedules[j].getDay());
            Stmt.setDate(i++, schedules[j].getDueDate());
            Stmt.setString(i++, schedules[j].getId());
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

   public boolean deleteByTimetable(String timetableId)throws DataAccessException
   {
      return true;
   }

   public boolean deleteByName(String name) throws ProjectDataAccessException
   {
      return true;
   }
}