package org.yang.customized.gtf.services.projectManager.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.Date;
import java.util.Calendar;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.projectManager.ProjectManager;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.util.DateFormatter;
import java.util.HashMap;
import org.yang.services.dataAccess.Data;
import java.util.Arrays;
import org.yang.customized.gtf.services.dataAccess.ProjectComparator;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class StageNotesBean extends ProjectsBean
{
   static final long serialVersionUID = 4711296382979764906L;

   private Stage currentStage = null;
   private Project currentProject = null;
   private String altPage = null;

   /**
    *  form set and get methods
    */

    private String id ="";
    public void setId(String id) { this.id = id; }
    public String  getId(){return id;}

    private String projectId ="";
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String  getProjectId(){return projectId;}

    private String projectType ="";
    public void setProjectType(String projectType) { this.projectType = projectType; }
    public String  getProjectType(){return projectType;}

    private int order = 0;
    public void setOrder(int order) { this.order = order; }
    public int getOrder() { return order; }

    private	String name ="No stage slected";
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private	String description ="";
    public void setDescription (String description) { description = description; }
    public String getDescription (){ return description; }

    private int status = Stage.IN_PROGRESS;
    public void setStatus(int status) { this.status = status; }
    public int getStatus() { return status; }

    private	String doneDate = "";
    public String getDoneDate (){ return doneDate;}
    public void  setDoneDate (String doneDate){this.doneDate=doneDate;}

    private	long lastUpdateDate = -1;
    public long getLastUpdateDate (){ return lastUpdateDate; }

    private	String note ="";
    public void  setNote(String note) { this.note = note; }
    public String getNote (){ return note; }

    private	String updateNote ="";
    public void  setUpdateNote(String updateNote) { this.updateNote = updateNote; }
    public String getUpdateNote (){ return updateNote; }

    private String accessRecords = "";
    public String getAccessRecords() { return accessRecords; }

    private HashMap datas = null;
    public void setDatas(HashMap datas) { this.datas = datas; }
    public HashMap getDatas() { return datas; }

   private String targetOwner = null;
   public void setTargetOwner(String targetOwner) { this.targetOwner = targetOwner; }
   public String getTargetOwner() { return targetOwner; }

   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private String lastSortBy = null;
   private String sortBy = "name";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }

   public StageNotesBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      String[] areas = passport.getServiceAreas("ProjectManager");
      if(null!=areas&&0<areas.length)
      {
         projectType = areas[0];
      }

      if(null==projectMgr)
         throw new Exception("Project manager is null.");
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      name = "";
      projectId = "";
      altPage = null;
   }

   protected String altPage()
   {
      System.out.println("[UserBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload";
      }
      else
         return altPage;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

   public Project[] getAllProjects() throws AccountDataAccessException
   {
      try
      {
          String cDomain = null;
          if(!gotPermit("ProjectManager", projectType, ProjectManager.CROSS_DOMAIN))
             cDomain = passport.getDomain();
          String cGroup = null;
          //if(!gotPermit("ProjectManager", targetType, ProjectManager.CROSS_GROUP))
          //   cGroup = getGroup();
          String cUser = null;
          if(!gotPermit("ProjectManager", projectType, ProjectManager.CROSS_USER))
             cUser = passport.getUsername();

          String condition = null;
          if(gotPermit("ProjectManager", projectType, ProjectManager.HISTORY))
             condition = "name LIKE '*%'";
          else
             condition = "name NOT LIKE '*%'";

          // combination
          // 1. xuser  , xdomain  : getProjects(type, null, null, null, condition)   -> assistant
          // 2. xuser  , ~xdomain : getProjects(type, domain, null, null, condition) -> lab manager
          // 3. ~xuser , xdomain  : getProjects(type, null, null, user, condition)   -> xdomain user
          // 4. ~xuser , ~xdomain : getProjects(type, domain, null, user, condition) -> normal user
          return projectMgr.getProjects(projectType, cDomain, cGroup, cUser, condition);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return new Project[0];
   }

   public Stage[] getAllStageTemplates()
   {
      return projectMgr.getAllAvailableStages(projectType);
   }

   public HashMap getCurrentAvailableStages()
   {
      HashMap pid2Pjs = new HashMap();
      try
      {
         Stage[] stages = projectMgr.getStagesByType(projectType);
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
         Stage[] stages = projectMgr.getStages(pId);
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

   /****************************************
    *             All my actions           *
    ****************************************/

   public void changePage() throws ProjectDataAccessException
   {
      altPage = null;
      //removeControl("masterTable");
   }

   public void showStage() throws ProjectDataAccessException
   {
      loadStage();
      altPage = "reload";
      //altPage = "stageNotes";
   }

   public void enter() throws ProjectDataAccessException
   {
      loadStage();
      page = "1";
      removeControl("masterTable");
   }

   public void load() throws ProjectDataAccessException
   {
      loadStage();
      //removeControl("masterTable");
   }

   public void update() throws ProjectDataAccessException
   {
      isReload = true;
      updateStage();
      //altPage = "stageNotes";
      removeControl("masterTable");
   }

   public void done() throws ProjectDataAccessException
   {
      isReload = true;
      currentStage.setDoneDate(System.currentTimeMillis());
      currentStage.setStatus(Stage.DONE);
      updateStage();
      createStage();
      removeControl("masterTable");
   }

   public void changeDataOwner() throws ProjectDataAccessException
   {
      altPage = "stageNotes";
      //do nothing
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
      altPage = "masterTable";
   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadStage() throws ProjectDataAccessException
   {
      currentProject = projectMgr.getProject(projectId);
      currentStage = projectMgr.getStage(projectId, name);
   }

   private void updateStage() throws ProjectDataAccessException
   {
      try
      {

         lastUpdateDate = System.currentTimeMillis();
         currentStage.setLastUpdateDate(lastUpdateDate);

		 
         if(null==targetOwner)
            targetOwner = passport.getUsername();

		 if((currentStage.getDoneDate()!=-1)&&gotPermit("ProjectManager", getProjectType(), ProjectManager.SYSTEM_DATA)) 
         {
		    int y=0,m=0,d=0,h=0,minute=0;
			Calendar calendar = Calendar.getInstance();
			StringTokenizer tokenizer = new StringTokenizer(doneDate,"/");
			String temp1 = "",temp2="";
			if(doneDate.indexOf(":")==-1)//yyyy/mm/dd (without hh:mm)
			{
			   if(tokenizer.hasMoreTokens())
			   {
			      y = new Integer(tokenizer.nextToken()).intValue();
			      m = new Integer(tokenizer.nextToken()).intValue();
			      d = new Integer(tokenizer.nextToken()).intValue();
			   }
		       calendar.set(y,m-1,d,h,minute);
			}
            else   //yyyy/mm/dd hh:MM
            {
			   if(tokenizer.hasMoreTokens())
			   {
			      y = new Integer(tokenizer.nextToken()).intValue();
			      m = new Integer(tokenizer.nextToken()).intValue();
			      temp1=tokenizer.nextToken();
			   }
			   tokenizer = new StringTokenizer(temp1," ");
			   if(tokenizer.hasMoreTokens())
		  	   {
			      d = new Integer(tokenizer.nextToken()).intValue();
			      temp2=tokenizer.nextToken();
			   }
			
			   tokenizer = new StringTokenizer(temp2,":");
			   if(tokenizer.hasMoreTokens())
			   {
			      h = new Integer(tokenizer.nextToken()).intValue();
			      minute = new Integer(tokenizer.nextToken()).intValue();
			   }
		       calendar.set(y,m-1,d,h,minute);
            }  			
			/*
			System.out.println("[StageNotesBean::updateStage]**************--------->y="+y);
			System.out.println("[StageNotesBean::updateStage]**************--------->m="+m);
			System.out.println("[StageNotesBean::updateStage]**************--------->d="+d);
			System.out.println("[StageNotesBean::updateStage]**************--------->h="+h);
			System.out.println("[StageNotesBean::updateStage]**************--------->minute="+minute);
			System.out.println("[StageNotesBean::updateStage]**************--------->calendar.getTime()="+calendar.getTime());
			*/
			currentStage.setDoneDate(calendar.getTime().getTime()); //modify
			//System.out.println("[StageNotesBean::updateStage]**************--------->currentStage.getName()="+currentStage.getName());
			//System.out.println("[StageNotesBean::updateStage]**************--------->currentStage.getOrder()="+currentStage.getOrder());
	     }
		 
         if(null!=datas)
         {
            //Iterator it = datas.keySet().iterator();
            //while(it.hasNext())
            //{
            //   String key = (String)it.next();
            //   System.out.println(key + "--------->" + datas.get(key));
            //}
            currentStage.setDatasMap(targetOwner, datas);
         }

         if(null!=updateNote&&
           !"".equals(updateNote.trim()))
         {
            note = "\nDate: " +
                   DateFormatter.getDateTimeString(new Date(lastUpdateDate), currentStage.getDateDisplayFormat()) +
                   "\n" +
                   passport.whoIsIt(passport.getDomain(), passport.getUsername()) +
                   " update:\n" +
                   updateNote +
                   "\n\n" +
                   note;
            currentStage.setNote(note);
         }

         //*** save result to database
         if (!projectMgr.updateStage(currentStage))
         {
            setState(ERR);
            setMsg("Update stage error: database error.");
            return;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   private void createStage() throws ProjectDataAccessException
   {
      try
      {
         //It's tricky ... need have order first
         int nextOrder = currentStage.getOrder() + 1;
         String projectId = currentStage.getProjectId();

         Project project = projectMgr.getProject(projectId);
         // the firt stage done
         if(1==order)
         {
            project.setStartDate(new Date(doneDate).getTime()); //modify
            projectMgr.updateProject(project);
         }

         Stage nextStage = project.getStageTemplate(nextOrder);

         // the last stage
         if(null==nextStage)
         {
            projectMgr.closeProject(projectId);
            return;
         }

         // check stage is existing ....
         nextStage.setId(projectId + nextOrder);
         nextStage.setProjectId(projectId);
         nextStage.setStatus(Stage.IN_PROGRESS);
         nextStage.setOrder(nextOrder);

         //*** save result to database
         if (!projectMgr.createStage(nextStage))
         {
            setState(ERR);
            setMsg("Create stage error: database update error.");
            return;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
      }
   }
}