package org.yang.customized.gtf.services.projectManager.web.PDA;

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
import org.yang.customized.gtf.services.projectManager.ProjectManager;
import java.util.HashMap;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.yang.customized.gtf.services.projectManager.web.ProjectsBean;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PDAProjectNotesBean extends ProjectsBean
{
   static final long serialVersionUID = 4711296382979764907L;

   private Project currentProject = null;

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

    private	String accountId ="";
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getAccountId() { return accountId; }

    private	String description ="";
    public void setDescription (String description) { this.description = description; }
    public String getDescription (){ return description; }

    private long serialNumber = 0;
    public void setSerialNumber(long serialNumber) { this.serialNumber = serialNumber; }
    public long getSerialNumber() { return serialNumber; }

    private String domain = "";
    public void setDomain(String domain) { this.domain = domain; }
    public String getDomain() { return domain; }

    private HashMap datas = null;
    public void setDatas(HashMap datas) { this.datas = datas; }
    public HashMap getDatas() { return datas; }

   /**
    *  form set and get methods
    */

   public PDAProjectNotesBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==projectMgr)
         throw new Exception("Project manager is null.");
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

   /****************************************
    *             for builder              *
    ****************************************/

   public String[] getAllAvailableProjectTypes()
   {
      return passport.getServiceAreas("ProjectManager");
   }

   public Project[] getAllAvailableProjects()
   {
       try
       {
          String cDomain = null;
          if(!gotPermit("ProjectManager", projectType, ProjectManager.CROSS_DOMAIN))
             cDomain = getDomain();
          String cGroup = null;
          //if(!gotPermit("ProjectManager", targetType, ProjectManager.CROSS_GROUP))
          //   cGroup = getGroup();
          String cUser = null;
          if(!gotPermit("ProjectManager", projectType, ProjectManager.CROSS_USER))
             cUser = passport.getUsername();

          return projectMgr.getProjects(projectType, cDomain, cGroup, cUser, "name NOT LIKE '*%'");
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       return new Project[0];
   }

   public Project getCurrentProject()
   {
      return currentProject;
   }

   public Project[] getAllProjects() throws AccountDataAccessException
   {
      try
      {
         return projectMgr.getProjects(projectType, null, null, null, "name NOT LIKE '*%'");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return new Project[0];
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void list() throws ProjectDataAccessException
   {

   }

   public void load() throws ProjectDataAccessException
   {
      //currentProject = projectMgr.getProject(id);
      //if (null==currentProject)
      //   throw new ProjectDataAccessException("Project is null : " + id);
   }

   public void accessDatasheet()
   {
      removeControl("dataSheet");
   }

   public void update() throws ProjectDataAccessException
   {
      try
      {
         currentProject.setName(name);
         currentProject.setAccountId(accountId);
         currentProject.setDescription(description);
         currentProject.setSerialNumber(serialNumber);
         currentProject.setDomain(domain);
         currentProject.setDatasMap(passport.getUsername(), datas);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!projectMgr.updateProject(currentProject))
      {
         setState(ERR);
         setMsg("Update user error: database error.");
         return;
      }
   }

   public void delete() throws ProjectDataAccessException
   {
      if (null==projectMgr.removeProject(id))
      {
         setState(ERR);
         setMsg("Remove user error: user ID does NOT exist.");
         return;
      }
   }
}