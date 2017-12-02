package org.yang.customized.gtf.services.projectManager.web.PDA;

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
import org.yang.customized.gtf.services.projectManager.ProjectManager;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.util.DateFormatter;
import java.util.HashMap;
import org.yang.services.dataAccess.Data;
import java.util.Arrays;
import org.yang.customized.gtf.services.dataAccess.ProjectComparator;
import org.yang.customized.gtf.services.projectManager.web.ProjectsBean;

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
   static final long serialVersionUID = 4711296982979764906L;

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

    private	long doneDate = -1;
    public long getDoneDate (){ return doneDate; }

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

   private String sortBy = null;
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

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
      return null;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

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

   public void load() throws ProjectDataAccessException
   {
      loadStage();
   }

   public void update() throws ProjectDataAccessException
   {
      isReload = true;
      updateStage();
   }

   public void done() throws ProjectDataAccessException
   {
      isReload = true;
      currentStage.setDoneDate(System.currentTimeMillis());
      currentStage.setStatus(Stage.DONE);
      updateStage();
      createStage();
   }

   public void changeDataOwner() throws ProjectDataAccessException
   {
      altPage = "stageNotes";
      //do nothing
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

         if(null!=datas)
            currentStage.setDatasMap(targetOwner, datas);

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
            project.setStartDate(doneDate);
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