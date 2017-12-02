package org.yang.customized.gtf.services.reportManager.web;

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
import org.yang.customized.gtf.services.reportManager.ReportManager;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.services.dataAccess.Data;
import java.util.HashMap;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ReportsListBean extends ReportsBean
{
   static final long serialVersionUID = 4711296382979764910L;

   private Stage currentStage = null;
   private Project currentProject = null;
   private String altPage = null;

   private Project[] projects = null;

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private String projectId ="";
   public void setProjectId(String projectId) { this.projectId = projectId; }
   public String  getProjectId(){return projectId;}

   private String stageName ="";
   public void setStageName(String stageName) { this.stageName = stageName; }
   public String  getStageName(){return stageName;}

   private String lastSortBy = null;
   private String sortBy = "name";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }

   // for browsing and searching
   private String dateType = "";
   public void setDateType(String dateType) { this.dateType = dateType; }

   private	String institute ="";
   public void setInstitute(String institute) { this.institute = institute; }
   public String getInstitute() { return institute; }

   private String projectType ="";
   public String  getProjectType(){return projectType;}
   public void setProjectType(String projectType) { this.projectType = projectType; }

   // for searching
   private int searchMonthFrom;
   public void setSearchMonthFrom(int searchMonthFrom){ this.searchMonthFrom = searchMonthFrom; }
   public int getSearchMonthFrom(){ return searchMonthFrom; }

   private int searchYearFrom;
   public void setSearchYearFrom(int searchYearFrom){ this.searchYearFrom = searchYearFrom; }
   public int getSearchYearFrom(){ return searchYearFrom; }

   private int searchDayFrom;
   public void setSearchDayFrom(int searchDayFrom){ this.searchDayFrom = searchDayFrom; }
   public int getSearchDayFrom(){ return searchDayFrom; }

   private int searchMonthTo;
   public void setSearchMonthTo(int searchMonthTo){ this.searchMonthTo = searchMonthTo; }
   public int getSearchMonthTo(){ return searchMonthTo; }

   private int searchYearTo;
   public void setSearchYearTo(int searchYearTo){ this.searchYearTo = searchYearTo; }
   public int getSearchYearTo(){ return searchYearTo; }

   private int searchDayTo;
   public void setSearchDayTo(int searchDayTo){ this.searchDayTo = searchDayTo; }
   public int getSearchDayTo(){ return searchDayTo; }

   private String searchBy = "";
   public void setSearchBy(String searchBy) { this.searchBy = searchBy; }

   private String keywords = "";
   public void setKeywords(String keywords) { this.keywords = keywords; }

   private boolean isAnd = true;
   public void setIsAnd(boolean isAnd) { this.isAnd = isAnd; }

   //private String[] displayItems = null;
   //public void setDisplayItems(String[] displayItems) { this.displayItems = displayItems; }
   //public String[] getDisplayItems() { return displayItems; }

   // for browsing
   private int calendarView = 2;
   public void setCalendarView(int calendarView) { this.calendarView = calendarView; }
   public int getCalendarView() { return calendarView; }

   private int browseMonth = 0;
   public void setBrowseMonth(int browseMonth){ this.browseMonth = browseMonth; }
   public int getBrowseMonth(){ return browseMonth; }

   private int browseYear = 0;
   public void setBrowseYear(int browseYear){ this.browseYear = browseYear; }
   public int getBrowseYear(){ return browseYear; }

   private int browseDay = 0;
   public void setBrowseDay(int browseDay){ this.browseDay = browseDay; }
   public int getBrowseDay(){ return browseDay; }

   // ????????????????
   private	String labHead ="";
   public void setLabHead (String labHead) { this.labHead = labHead; }
   public String getLabHead(){ return labHead; }

   private String investigator = "";
   public void setInvestigator(String investigator) { this.investigator = investigator; }
   public String getInvestigator() { return investigator; }

   private	String projectName ="";
   public void setProjectName (String projectName) { this.projectName = projectName; }
   public String getProjectName(){ return projectName; }

   private long currentTime = 0;
   public void setCurrentTime(long currentTime) { this.currentTime = currentTime; }
   public long getCurrentTime() { return currentTime; }

   private	String accountId ="";
   public void setAccountId(String accountId) { this.accountId = accountId; }
   public String getAccountId() { return accountId; }

   private String searchField = null;
   public void setSearchField(String searchField) { this.searchField = searchField; }

   private String targetOwner = null;
   public void setTargetOwner(String targetOwner) { this.targetOwner = targetOwner; }
   public String getTargetOwner() { return targetOwner; }

   /**
    *  form set and get methods
    */

   public ReportsListBean()
   {
      super();
   }

   /*************************************
    *  Implement NavigationTree method  *
    *************************************/

   public Project[] getMatchedProjects()
   {
      return projects;
   }

   public Stage[] getAllStageTemplates()
   {
      return reportMgr.getAllAvailableStages(projectType);
   }

   public HashMap getCurrentAvailableStages()
   {
      HashMap pid2Pjs = new HashMap();
      try
      {
         Stage[] stages = reportMgr.getStagesByType(projectType);
         if(null==stages||0==stages.length)
            return pid2Pjs;

         HashMap pj2stages = null;
         String projectId = null;
         for(int i=0; i<stages.length; i++)
         {
            projectId = stages[i].getProjectId();
            if(null==(pj2stages=(HashMap)pid2Pjs.get(projectId)))
            {
               pj2stages = new HashMap();
               pid2Pjs.put(projectId, pj2stages);
            }
            pj2stages.put(stages[i].getName(), stages[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return pid2Pjs;
   }

   public HashMap getCurrentAvailableStages(String pId)
   {
      HashMap temp = new HashMap();
      try
      {
         Stage[] stages = reportMgr.getStages(pId);
         if(null==stages||0==stages.length)
            return temp;

         for(int i=0; i<stages.length; i++)
         {
            temp.put(stages[i].getName(), stages[i]);
            Data[] datas = stages[i].getDatas();
            //for(int j=0; j<datas.length; j++)
            //{
            //   System.out.println("data name:" + datas[j].getName() + ", data value:" + datas[j].getValue());
            //   System.out.println("data name:" + datas[j].getName() + ", data value:" + datas[j].getValue(passport.getUsername()));
            //}
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return temp;
   }

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public Stage getCurrentStage()
   {
      return currentStage;
   }

   public Project getCurrentProject()
   {
      return currentProject;
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==reportMgr)
         throw new Exception("Report manager should not be null.");
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ReportsListBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[ReportsListBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return altPage;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load() throws ProjectDataAccessException
   {
      removeControl("reportMasterTable");
   }

   public void browse() throws ProjectDataAccessException
   {
      removeControl("reportMasterTable");
      //if(null!=displayItems)
      //{
      //   for(int i=0; i<displayItems.length; i++)
      //   {
      //      System.out.println("displayItems[" + i + "]" + displayItems[i]);
      //   }
      //}

      System.out.println("Browse by:" + dateType);
      System.out.println("From:" + browseYear + "/" + browseMonth + "/" + browseDay);

      // need to build upon combination
      // 1. xuser  , xdomain  : getProjects(type, null, null, null)   -> assistant
      // 2. xuser  , ~xdomain : getProjects(type, domain, null, null) -> lab manager
      // 3. ~xuser , xdomain  : getProjects(type, null, null, user)   -> xdomain user
      // 4. ~xuser , ~xdomain : getProjects(type, domain, null, user) -> normal user
      projects = reportMgr.browseProjects(calendarView,
                                          institute ,
                                          projectType,
                                          dateType,
                                          new Date(browseYear,browseMonth, browseDay));
      /*
	  for(int i=0; i<projects.length; i++)
      {
         System.out.println("project id = " + projects[i].getId());
      }
	  */
   }

   public void search() throws ProjectDataAccessException
   {
      removeControl("reportMasterTable");
      System.out.println("Search by:" + searchBy + ", keywords:" + keywords + ", isAnd:" + isAnd);
      System.out.println("From:" + searchYearFrom + "/" + searchMonthFrom + "/" + searchDayFrom);
      System.out.println("To  :" + searchYearTo + "/" + searchMonthTo + "/" + searchDayTo);
      if(null==searchBy)
         return;

      //if(null!=displayItems)
      //{
      //   for(int i=0; i<displayItems.length; i++)
      //   {
      //      System.out.println("displayItems[" + i + "]" + displayItems[i]);
      //   }
      //}

      try
      {
         // need to build upon combination
         // 1. xuser  , xdomain  : getProjects(type, null, null, null)   -> assistant
         // 2. xuser  , ~xdomain : getProjects(type, domain, null, null) -> lab manager
         // 3. ~xuser , xdomain  : getProjects(type, null, null, user)   -> xdomain user
         // 4. ~xuser , ~xdomain : getProjects(type, domain, null, user) -> normal user
         projects = reportMgr.searchProjects(searchBy,
                                             keywords,
                                             isAnd,
                                             dateType,
                                             new Date(searchYearFrom-1900, searchMonthFrom-1, searchDayFrom),
                                             new Date(searchYearTo-1900, searchMonthTo-1, searchDayTo));
         /*
         for(int i=0; i<projects.length; i++)
            System.out.println("project id = " + projects[i].getId());
	      */		
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void changePage() throws ProjectDataAccessException
   {
      altPage = null;
   }

   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
      {
         isAcending = !isAcending;
      }
      else
      {
         isAcending = true;
      }

      lastSortBy = sortBy;
      altPage = null;//"masterTable";
   }
}