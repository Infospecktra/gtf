/* Generated by Together */

package org.yang.customized.gtf.services.scheduler.web;
import java.util.Properties;
import java.util.Collection;
import java.util.Iterator;

import org.yang.web.applicationContainer.SecuredBean;
import org.yang.web.applicationContainer.Passport;

import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.User;
import org.yang.services.accountmgr.UserManager;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationTree;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.servicemgr.Area;
import java.util.Map;
import org.yang.customized.gtf.services.dataAccess.Project;
//import org.yang.customized.gtf.services.projectManager.ProjectManager;
//import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.web.view.controls.jsStyle.panel.GenericPanel;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import org.yang.customized.gtf.services.scheduler.Scheduler;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
//import org.yang.customized.gtf.services.projectManager.web.ProjectsBean;

public class ScheduledProjectListBean extends SchedulerBean
{
   static final long serialVersionUID = 4711296382979764999L;

   private static int OPEN_NODE = 0;
   private static int CLOSE_NODE = 1;

   private int event = 0;
   public int getEvent() { return event; }

   private String targetID = null;
   public void setTargetID(String targetID) { this.targetID = targetID; }
   public String getTargetID() { return targetID; }

   private String targetType = null;
   public void setTargetType(String targetType) { this.targetType = targetType; }
   public String getTargetType() { return targetType; }

   public ScheduledProjectListBean()
   {
      super();
   }

   /*************************************
    *  Implement NavigationTree method  *
    *************************************/

   public String getDomain()
   {
      return passport.getDomain();
   }

   public String[] getAllAvailableProjectTypes()
   {
      return passport.getServiceAreas("Scheduler");
   }

   public Project[] getAllAvailableProjects()
   {
       try
       {
          String cDomain = null;
          if(!gotPermit("Scheduler", targetType, Scheduler.CROSS_DOMAIN))
             cDomain = getDomain();
          String cGroup = null;
          //if(!gotPermit("ProjectManager", targetType, ProjectManager.CROSS_GROUP))
          //   cGroup = getGroup();
          String cUser = null;
          if(!gotPermit("Scheduler", targetType, Scheduler.CROSS_USER))
             cUser = passport.getUsername();

          String condition = "name NOT LIKE '*%'";

          // combination
          // 1. xuser  , xdomain  : getProjects(type, null, null, null, condition)   -> assistant
          // 2. xuser  , ~xdomain : getProjects(type, domain, null, null, condition) -> lab manager
          // 3. ~xuser , xdomain  : getProjects(type, null, null, user, condition)   -> xdomain user
          // 4. ~xuser , ~xdomain : getProjects(type, domain, null, user, condition) -> normal user
          return scheduler.getProjects(targetType, cDomain, cGroup, cUser, condition);
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       return new Project[0];
   }

   public Timetable getTimetableUnderProject(Project project)
   {
      Timetable timetable = null;
      try
      {
         timetable = scheduler.getTimetable(project.getId());
         if(null==timetable)
         {
            timetable = scheduler.getTimetableTemplate(project.getType());
            this.createTimetable(project, timetable);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return timetable;
   }

   public Schedule[] getSchedulesUnderTimetable(String tid)
   {
      try
      {
         return scheduler.getSchedules(tid);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new Schedule[0];
   }

   /***************************************
    *  Implement GenericHandler's method  *
    ***************************************/

   protected void init() throws Exception
   {
      ensureResource();

      String[] areas = passport.getServiceAreas("Scheduler");
      if(null!=areas&&0<areas.length)
      {
         targetType = areas[0];
      }

      if(null==scheduler)
         throw new Exception("Project manager is null.");
   }

   protected String altPage()
   {
      if(isReload)
      {
         return "reload-forward";
      }
      else
         return null;
   }

   /***************************************
    *           my action method          *
    ***************************************/
   public void load()
   {
      this.removeControl("projectsTreePanel");
   }

   protected void destroy()
   {
      scheduler = null;
   }

   public void changeType()
   {
      passport.setRuntimeProperty("projectType", targetType);
      targetID = null;
      isReload = true;
   }

   public void open()
   {
      NavigationTree tree = (NavigationTree)((TabPanelElement)((GenericPanel)this.getControl("projectsTreePanel")).getSubcontrol(targetType)).getSubcontrol("projectsTree");
      tree.openNode(targetID);
   }

   public void close()
   {
      NavigationTree tree = (NavigationTree)((TabPanelElement)((GenericPanel)this.getControl("projectsTreePanel")).getSubcontrol(targetType)).getSubcontrol("projectsTree");
      tree.closeNode(targetID);
   }

   /*********************
    *  private
    *********************/

   public void createTimetable(Project project, Timetable timetable) throws ProjectDataAccessException
   {
      try
      {
         String id = project.getId();
         timetable.setId(id);
         timetable.setProjectId(id);

         Schedule[] schedules = scheduler.getSchedulesTemplate(project.getType());
         for(int i=0; i<schedules.length; i++)
         {
            schedules[i].setId(id+":"+schedules[i].getName());
            schedules[i].setTimetableId(id);
            scheduler.createSchedule(schedules[i]);
         }
         scheduler.createTimetable(timetable);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
   }
}