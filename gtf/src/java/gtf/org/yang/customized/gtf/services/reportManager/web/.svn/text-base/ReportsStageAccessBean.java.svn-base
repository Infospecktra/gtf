package org.yang.customized.gtf.services.reportManager.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Date;

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
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.util.DateFormatter;
import java.util.HashMap;
import org.yang.services.dataAccess.Data;
import java.util.Arrays;
import org.yang.customized.gtf.services.dataAccess.ProjectComparator;
import org.yang.customized.gtf.services.reportManager.ReportManager;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ReportsStageAccessBean extends ReportsBean
{
   static final long serialVersionUID = 4711296382979764912L;

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

    private	String name ="No stage slected";
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private	String description ="";
    public void setDescription (String description) { description = description; }
    public String getDescription (){ return description; }

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

   public ReportsStageAccessBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      String[] areas = passport.getServiceAreas("ReportManager");
      if(null!=areas&&0<areas.length)
      {
         projectType = areas[0];
      }

      if(null==reportMgr)
         throw new Exception("Report manager is null.");
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

   public Stage[] getAllStageTemplates()
   {
      return reportMgr.getAllAvailableStages(projectType);
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

   /****************************************
    *             All my actions           *
    ****************************************/

   public void showStage() throws ProjectDataAccessException
   {
      loadStage();
   }

   public void showBrief() throws ProjectDataAccessException
   {
      altPage = "project-forward";
   }

   public void update() throws ProjectDataAccessException
   {
      updateStage();
   }

   public void changeDataOwner() throws ProjectDataAccessException
   {
      //do nothing
   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadStage() throws ProjectDataAccessException
   {
      currentProject = reportMgr.getProject(projectId);
      if(null==(currentStage=reportMgr.getStage(projectId, name)))
         currentStage = createStage();
   }

   private Stage createStage() throws ProjectDataAccessException
   {
      try
      {
         Project project = reportMgr.getProject(projectId);
         Stage stage = project.getStageTemplate(name);

         // check stage is existing ....
         stage.setId(projectId + stage.getOrder());
         stage.setProjectId(projectId);
         stage.setStatus(Stage.DONE);
         stage.setDoneDate(project.getEndDate());

         //*** save result to database
         if (!reportMgr.createStage(stage))
         {
            setState(ERR);
            setMsg("Create stage error: database update error.");
            return null;
         }
         else
         {
            return stage;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   private void updateStage() throws ProjectDataAccessException
   {
      try
      {
         lastUpdateDate = System.currentTimeMillis();
         currentStage.setLastUpdateDate(lastUpdateDate);

         if(null==targetOwner)
            targetOwner = passport.getUsername();

         if(null!=datas)
            currentStage.setDatasMap(targetOwner, datas);

         if(null!=updateNote&&
           !"".equals(updateNote.trim()))
         {
            note = "\nDate: " +
                   DateFormatter.getDateTimeString(new Date(lastUpdateDate), "yyyy/MM/DD hh:mm:ss") +
                   "\n" +
                   passport.whoIsIt(passport.getDomain(), passport.getUsername()) +
                   " update:\n" +
                   updateNote +
                   "\n\n" +
                   note;
            currentStage.setNote(note);
         }

         //*** save result to database
         if (!reportMgr.updateStage(currentStage))
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
}