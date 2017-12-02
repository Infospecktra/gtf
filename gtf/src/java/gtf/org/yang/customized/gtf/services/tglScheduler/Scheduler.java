/**
 * This class is used to manage client to access a domain and to manipulate some services
 * within each domain.
 * each domain has one UserManager object. Although, we donot define an
 * domain class, the domain concept will appear in database to form an
 * unit of users. Their relationship is: a set of users can belong to another
 * set of groups, which in turn form a domain. A domain will be passed to database
 * as a string
 * @ title  UserManager class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
package org.yang.customized.gtf.services.tglScheduler;
import org.yang.services.servicemgr.Service;
import java.io.Serializable;
import java.util.Properties;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import java.util.Collection;
import org.yang.services.servicemgr.Area;
import java.util.ArrayList;
import org.yang.services.servicemgr.Operation;
import org.yang.util.DateFormatter;
import java.util.Date;
import java.util.Arrays;
import org.yang.services.dataAccess.Data;
import org.yang.customized.gtf.services.GTFService;
import org.yang.customized.gtf.services.GenericGTFService;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.TimetableDAO;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import org.yang.customized.gtf.services.dataAccess.ScheduleDAO;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import java.util.HashSet;
import org.yang.services.dataAccess.TypeIDAOImpl;

public class Scheduler extends GenericGTFService
{
   //private RequestsQueue rq = null;
   private SchedulerArea[] areas = null;

   /**
      *  each domain has one UserManager
      */
   public Scheduler (String dname)
   {
      super(dname);
      //create the tables.

      daof.getTimetableDAO().createTable();
      daof.getScheduleDAO().createTable();

      int size = typeList.size();
      areas = new SchedulerArea[size];
      SchedulerOperation[] ops = null;

      for(int i=0; i<size; i++)
      {
         ops = new SchedulerOperation[8];
         ops[0] = new SchedulerOperation(this.CROSS_DOMAIN);
         ops[1] = new SchedulerOperation(this.CROSS_USER);
         ops[2] = new SchedulerOperation(this.CLIENT);
         ops[3] = new SchedulerOperation(this.CREATE);
         ops[4] = new SchedulerOperation(this.DELETE);
         ops[5] = new SchedulerOperation(this.UPDATE);
         ops[6] = new SchedulerOperation(this.ACTIVATE);
         ops[7] = new SchedulerOperation(this.HISTORY);
         areas[i] = new SchedulerArea();
         areas[i].setName((String)typeList.get(i));
         areas[i].setAllOperations(ops);
         //System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::" + areas[i].getName());
      }
   }

   public boolean hasMessage()
   {
      return false;
   }

   public void nextMessage()
   {
      return;
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean createProject(Project project) throws ProjectDataAccessException
   {
      if(null==project)
         return false;
      String id = this.generateId();
      project.setId(id);
      System.out.println("Before insert project : " + id);

      try
      {
         // insert datas
         this.saveDatas(project.getDatas(), id + ":-1", true);
         // insert project
         daof.getProjectDAO(DAOFactory.ONGOING).insert(project);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
   public boolean updateProject(Project project)throws ProjectDataAccessException
   {
      if(null==project)
         return false;

      try
      {
         // update datas
         this.saveDatas(project.getDatas(), project.getId() + ":-1", false);
         // update project
         daof.getProjectDAO(DAOFactory.ONGOING).update(project, false);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public Project removeProject(String pid) throws ProjectDataAccessException
   {
      try
      {
         if(null==pid||null==dName)
            return null;

         Project target = null;
         if(null==(target=getProject(pid)))
            return null;

         removeTimetables(pid);
         ((TypeIDAOImpl)daof.getDataDAO(DAOFactory.ONGOING)).deleteByProject(pid);

         ProjectDAO pdao = daof.getProjectDAO(DAOFactory.ONGOING);
         pdao.delete(pid);
         System.out.println("Delete project ---->" + pid);
         return target;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] getProjects(String type, String domain, String group, String user, String condition)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.ONGOING, type, domain, group, user, condition);
   }

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project getProject(String id)throws ProjectDataAccessException
   {
      return getProject(DAOFactory.ONGOING, id);
   }

   /**
 * this method will get all group object in one doamian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 * 
 *    public Timetable[] getAllTimetables()throws ProjectDataAccessException
 *    {
 *       if(null==daof)
 *          return null;
 *       TimetableDAO tdao = daof.getTimetableDAO();
 *       Timetable[] timetables = tdao.loadAll();
 *       rebuild(timetables);
 *       return timetables;
 *    }*/

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Timetable[] getTimetables(String[] tids)throws ProjectDataAccessException
   {
      if(null==tids||null==daof)
         return null;
      TimetableDAO pdao = daof.getTimetableDAO();
      Timetable[] timetables = pdao.load(tids);
      rebuild(timetables);
      return timetables;
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Timetable getTimetable(String tid)throws ProjectDataAccessException
   {
      if(null==tid)
         return null;
      Timetable[] timetables = getTimetables(new String[]{tid});
      if(null==timetables||0>=timetables.length)
      {
         return null;
      }
      return timetables[0];
   }


   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Timetable[] getTimetableByProject(String pid)throws ProjectDataAccessException
   {
      if(null==pid)
         return null;
      Timetable[] timetables = daof.getTimetableDAO().loadByProject(pid);
      if(null==timetables)
      {
         return new Timetable[0];
      }
      return timetables;
   }

   /**
    * this method will get an user object in one domain
    * @param      uid user id
    * @return     an user object if wrong domain or wrong id will return null
    * @exception  DataAccessEception
    */
   public String[] getProjectIdByTime(java.sql.Date dateFrom, java.sql.Date dateTo)throws ProjectDataAccessException
   {
      if(null==dateFrom||null==dateTo)
         return new String[0];
      ScheduleDAO pdao = daof.getScheduleDAO();
      String[] ids = pdao.loadTimetableIdsByDate(dateFrom, dateTo);
      HashSet idSet = new HashSet();
      for(int i=0; i<ids.length; i++)
      {
         idSet.add(ids[i].substring(0, ids[i].indexOf(":")));
      }
      return (String[])idSet.toArray(new String[]{});
   }


   /**
    * this method will get an user object in one domain
    * @param      uid user id
    * @return     an user object if wrong domain or wrong id will return null
    * @exception  DataAccessEception
    */
   public Schedule[] getSchedules(String tid)throws ProjectDataAccessException
   {
      if(null==tid||null==daof)
         return null;
      ScheduleDAO pdao = daof.getScheduleDAO();
      Schedule[] schedules = pdao.loadByTimetable(tid);
      rebuild(schedules);
      return schedules;
   }

   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public void removeTimetables(String pid) throws ProjectDataAccessException
   {
      try
      {
         if(null==pid)
            return;

         Timetable[] targets = null;
         if(null==(targets=getTimetableByProject(pid))||
            0==targets.length)
            return;

         TimetableDAO tdao = daof.getTimetableDAO();
         for(int i=0; i<targets.length; i++)
         {
            try
            {
               removeSchedules(targets[i].getId());
               tdao.delete(targets[i].getId());
               System.out.println("Delete timetable ---->" + targets[i].getId());
            }
            catch(Exception e) { e.printStackTrace(); }
         }
         return;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   public void removeSchedules(String tid) throws ProjectDataAccessException
   {
      try
      {
         if(null==tid)
            return;

         Schedule[] targets = null;
         if(null==(targets=this.getSchedules(tid))||
            0==targets.length)
            return;

         ScheduleDAO sdao = daof.getScheduleDAO();
         for(int i=0; i<targets.length; i++)
         {
            try
            {
               sdao.delete(targets[i].getId());
               System.out.println("Delete schedule ---->" + targets[i].getId());
            }
            catch(Exception e) { e.printStackTrace(); }
         }
         return;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }




   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean createTimetable(Timetable timetable) throws ProjectDataAccessException
   {
      if(null==timetable)
         return false;
      System.out.println("Before insert timetable : " + timetable.getId());

      try
      {
         // insert datas
         //this.saveDatas(timetable.getDatas(), timetable.getId() + ":timetable", true);
         // insert project
         daof.getTimetableDAO().insert(timetable);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean createSchedule(Schedule schedule) throws ProjectDataAccessException
   {
      if(null==schedule)
         return false;
      System.out.println("Before insert schedule : " + schedule.getId());

      try
      {
         // insert datas
         //this.saveDatas(schedule.getDatas(), schedule.getTimetableId()+":"+schedule.getName(), true);
         // insert project
         daof.getScheduleDAO().insert(schedule);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
   public boolean updateTimetable(Timetable timetable)throws ProjectDataAccessException
   {
      if(null==timetable)
         return false;

      try
      {
         // update datas
         this.saveDatas(timetable.getDatas(), timetable.getId() + ":timetable", false);
         // update project
         daof.getTimetableDAO().update(timetable);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
   public boolean updateSchedule(Schedule schedule)throws ProjectDataAccessException
   {
      if(null==schedule)
         return false;

      try
      {
         // update datas
         this.saveDatas(schedule.getDatas(), schedule.getTimetableId() + ":" + schedule.getName(), false);
         // update project
         daof.getScheduleDAO().update(schedule);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   public Area[] allAreas()
   {
      return areas;
   }

   public boolean containArea(String areaname)
   {
      return typeList.contains(areaname);
   }

   private void saveDatas(Data[] datas, String idPrifix, boolean isInsert) throws ProjectDataAccessException
   {
      try
      {
         for(int i=0; i<datas.length; i++)
         {
            System.out.println("==================== Save data ====================== id =" + idPrifix + ":" + datas[i].getName());
            datas[i].setId(idPrifix + ":" + datas[i].getName());
         }

         if(isInsert)
            daof.getDataDAO(DAOFactory.ONGOING).insert(datas);
         else
            daof.getDataDAO(DAOFactory.ONGOING).update(datas);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   /*
   private void removeDatas(Data[] datas, String idPrifix, boolean isInsert) throws ProjectDataAccessException
   {
      try
      {
         for(int i=0; i<datas.length; i++)
         {
            System.out.println("==================== Save data ====================== id =" + idPrifix + ":" + datas[i].getName());
            datas[i].setId(idPrifix + ":" + datas[i].getName());
         daof.getDataDAO(DAOFactory.ONGOING).deleteByProject(String pid)
         }
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }
*/

   private String generateId()
   {
      return DateFormatter.getDateTimeString(new Date(System.currentTimeMillis()));
   }

   private void rebuild(Timetable timetable) throws ProjectDataAccessException
   {
      rebuild(new Timetable[] {timetable});
   }

   private void rebuild(Timetable[] timetables) throws ProjectDataAccessException
   {
      if(null==timetables)
         return;

      Data[] datas = null;
      for(int i=0; i<timetables.length; i++)
      {
         try
         {
            if(null==timetables[i])
               continue;

            datas =daof.getDataDAO(DAOFactory.ONGOING)
                       .loadByTimetableDatsByProjectId(timetables[i].getProjectId());
            if(null!=datas)
            {
               timetables[i].rebuildDatas(datas);

               //datas = timetables[i].getDatas();
               //for(int j=0; j<datas.length; j++)
               //{
               //   datas[j].showContent();
               //}
            }
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   private void rebuild(Schedule schedule) throws ProjectDataAccessException
   {
      rebuild(new Schedule[] {schedule});
   }

   private void rebuild(Schedule[] schedules) throws ProjectDataAccessException
   {
      if(null==schedules)
         return;

      Data[] datas = null;
      for(int i=0; i<schedules.length; i++)
      {
         try
         {
            if(null==schedules[i])
               continue;

            datas =daof.getDataDAO(DAOFactory.ONGOING)
                       .loadByProjectIdAndScheduleName(schedules[i].getTimetableId(), schedules[i].getName());
            if(null!=datas)
            {
               schedules[i].rebuildDatas(datas);

               //datas = schedules[i].getDatas();
               //for(int j=0; j<datas.length; j++)
               //{
               //   datas[j].showContent();
               //}
            }
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   public static void main(String[] args)
   {
   }
}