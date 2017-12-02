package org.yang.customized.gtf.services.tglScheduler.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.scheduleManager.ScheduleManager;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.scheduleManager.web.ScheduleBean;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import java.util.Calendar;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class TimetableBean extends SchedulerBean
{
   static final long serialVersionUID = 4711296382979764913L;

   private String targetPage = null;
   public void setTargetPage(String targetPage) { this.targetPage = targetPage; }

   private String projectType ="";
   public void setProjectType(String projectType) { this.projectType = projectType; }
   public String  getProjectType(){return projectType;}

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private	String name ="";
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private	String description ="";
   public void setDescription (String description) { this.description = description; }
   public String getDescription (){ return description; }

   private String domain = "";
   public String getDomain() { return domain; }
   public void setDomain(String domain) { this.domain = domain; }

   private int year = 1900;
   public void setYear(int year) { this.year = year; }
   public int getYear() { return year; }

   private int month = 0;
   public void setMonth(int month) { this.month = month; }
   public int getMonth() { return month; }

   private int date = 1;
   public void setDate(int date) { this.date = date; }
   public int getDate() { return date; }

   private HashMap datas = null;
   public void setDatas(HashMap datas) { this.datas = datas; }
   public HashMap getDatas() { return datas; }

   /**
    *  form set and get methods
    */

   public TimetableBean()
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
      if(null!=currentProject)
      {
         currentProject.setAction(Project.UPDATE);
      }
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ProjectInformationBean::altPage] reload page :" + isReload );
      if(isReload)
      {
         System.out.println("[ProjectInformationBean::altPage] My default page :" + this.targetPage);
         passport.setRuntimeProperty("right", this.targetPage);
         return "reload-forward";
      }
      else
         return this.targetPage;
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   /****************************************
    *             for builder              *
    ****************************************/

   private Project currentProject = null;
   public Project getCurrentProject()
   {
      return currentProject;
   }

   private Timetable currentTimetable = null;
   public Timetable getCurrentTimetable()
   {
      return currentTimetable;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load() throws ProjectDataAccessException
   {

      currentTimetable = scheduler.getTimetable(id);
      if (null==currentTimetable)
         throw new ProjectDataAccessException("Timetable can not be null : " + id);
      if(null==currentTimetable.getDueDate())
         currentTimetable.setDueDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
      currentTimetable.setSchedules(scheduler.getSchedules(currentTimetable.getId()));
      currentProject = scheduler.getProject(currentTimetable.getProjectId());
   }

   public void update() throws ProjectDataAccessException
   {
      try
      {
         Schedule[] schedules = currentTimetable.getSchedules();
         for(int i=0; i<schedules.length; i++)
         {
            scheduler.updateSchedule(schedules[i]);
         }
         currentTimetable.setDatasMap(datas);
         scheduler.updateTimetable(currentTimetable);

         //if(!currentProject.isDataQualified())
         //{
         //   load();
         //   System.out.println("Data is not qualified!");
         //   this.setState(ERR);
         //   this.setMsg("Some required field is empty, Please submit it again.");
         //   return;
         //}
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!scheduler.updateTimetable(currentTimetable))
      {
         setState(ERR);
         setMsg("Update project error: database error.");
         return;
      }
      isReload = true;
   }

   public void onChange()
   {
      Calendar calendar = Calendar.getInstance();
      calendar.set(year, month, date);
      long dueDate = calendar.getTimeInMillis();
      long offset = 0;
      try
      {
         currentTimetable.setDatasMap(datas);
      }
      catch(Exception e)
      {}
      currentTimetable.setDueDate(new java.sql.Date(dueDate));
      Schedule[] schedules = currentTimetable.getSchedules();
      for(int i=0; i<schedules.length; i++)
      {
         offset = ((long)schedules[i].getDay())*86400*1000;
         schedules[i].setDueDate(new java.sql.Date(dueDate+offset));
      }
   }
}