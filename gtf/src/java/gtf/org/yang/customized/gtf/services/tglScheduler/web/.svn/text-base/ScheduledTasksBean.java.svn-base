package org.yang.customized.gtf.services.tglScheduler.web;
import java.util.HashMap;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.tglScheduler.Scheduler;
import org.yang.customized.gtf.services.dataAccess.Project;
import java.util.Calendar;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ScheduledTasksBean extends SchedulerBean
{
   static final long serialVersionUID = 4711296382009764913L;

   private boolean listMode = true;
   public boolean getListMode() { return listMode; }
   public void setListMode(boolean listMode) { this.listMode = listMode; }

   /**
    *  form set and get methods
    */

   public ScheduledTasksBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==scheduler)
         throw new Exception("User manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ProjectInformationBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[ProjectInformationBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return null;
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public String whatIsPhoneNumber(String domain, String username)
   {
      return (passport.whatIsPhoneNumber(domain, username))[0];
   }

   /****************************************
    *             for builder              *
    ****************************************/

   public String[] getAllAvailableProjectTypes()
   {
      return passport.getServiceAreas("Scheduler");
   }

   public Project[] getAllAvailableProjects(java.sql.Date dateFrom, java.sql.Date dateTo) throws ProjectDataAccessException
   {
      String[] ids = scheduler.getProjectIdByTime(dateFrom, dateTo);
      for(int i=0; i<ids.length; i++)
         System.out.println("llllllllllllllllllll:" + ids[i]);
      return scheduler.getProjects(DAOFactory.ONGOING, ids);
   }

   public Timetable[] getTimetablesUnderProject(Project project)
   {
      Timetable[] timetables = null;
      try
      {
         timetables = scheduler.getTimetableByProject(project.getId());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return timetables;
   }

   public Schedule[] getSchedulesUnderProject(Timetable timetable)
   {
      Schedule[] schedules = null;
      try
      {
         schedules = scheduler.getSchedules(timetable.getId());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return schedules;
   }

   public String[] getAllInvestigatorNames(String domainName)
   {
      return passport.getDomainUserNames(domainName);
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load() throws ProjectDataAccessException
   {
      if(listMode)
      {

      }
   }

   private void loadList()
   {
      
   }
}