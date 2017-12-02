package org.yang.customized.gtf.services.projectViewer.web;

import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.projectViewer.ProjectViewerManager;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.services.dataAccess.Data;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectViewer;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author:Celina Yang
 * @version 1.0
 */

public class ProjectViewerMasterBean extends ProjectViewerBean
{
   static final long serialVersionUID = 491539223357728912L;
    
   private String altPage = null;
   private Project currentProject = null;
   private Project[] allProjects = null;
   
   public ProjectViewerMasterBean()
   {
      super();
   }

   /**
    *  form set and get methods
    */
   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}
   
   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private String loginDomain = null;
   public String getLoginDomain()
   {
      try
      {
      	 //loginDomain=projectViewerMgr.getLoginDomain();
      }
      catch(Exception e)
      {
      }			
      return loginDomain;
   }
   
   private String lastSortBy = null;
   private String sortBy = "projectNumber";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }
   
   public int getDisplayProjectsSize()
   {
       if(displayProjects==null||displayProjects.length==0)
          return 0;
       else
       {
          displayProjects = filterProjectByLoginDomain(displayProjects);
          return displayProjects.length;
       }   
   }
   
   private Project[] displayProjects = null;
   public Project[] getDisplayProjects() throws ProjectDataAccessException
   {  
      load();
      displayProjects = filterProjectByLoginDomain(displayProjects);
      return displayProjects;
   }
   public void setDisplayProjects(Project[] ps){this.displayProjects=ps;}

   private Project[] filterProjectByLoginDomain(Project[] projects)
   {
      ArrayList arry_Projects = new ArrayList();
      String targetDomain = "";
      //System.out.println("[ProjectViewerMasterBuilder::createList]BBBBBB----->loginDomain="+loginDomain);
      getLoginDomain(); 
      if(!loginDomain.equalsIgnoreCase("GTF"))
      {
      	 	
         for(int i=0; i<projects.length; i++)
         {
      	    targetDomain = projects[i].getDomain().trim();
      	    //System.out.println("[PojectViewerMasterBuilder::createList]nnnn----->targetDomain="+targetDomain);
      	    while(!targetDomain.equalsIgnoreCase(loginDomain)&&(i<projects.length-1))
      	    {   
      	         i++;
                 targetDomain = projects[i].getDomain().trim();
      //System.out.println("[ProjectViewwerMasterBean::]----(i="+i+")------>targetDomain="+targetDomain);
            }  
            if(targetDomain.equalsIgnoreCase(loginDomain))
      	       arry_Projects.add(projects[i]);
         }
      
         projects = (Project[])arry_Projects.toArray(new Project[0]);
         if(projects==null)
            projects = new Project[0];
         //System.out.println("[ProjectViewwerMasterBean::]---------->projects="+projects);
      }
      return projects;
   }	
   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/
    
   public void init() throws Exception
   {
      ensureResource();
      String[] areas = passport.getServiceAreas("ProjectViewerManager");
      loginDomain=projectViewerMgr.getLoginDomain();
      if(null==projectViewerMgr)
         throw new Exception("ProjectViewer manager is null.");
      load();	
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      altPage = null;
   }

   protected String altPage()
   {
      //System.out.println("[ProjectViewerMasterBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload";
      }
      else
         return altPage;
   }

   /****************************************
    *   inherits  methods                  *
    ****************************************/

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public Project getCurrentProject()
   {
      return currentProject;
   }

   /****************************************
    *             All my actions           *
    ****************************************/
    private String viewMode = ProjectViewerManager.DATA_ON_TABLE_NAME_1;
    public String getViewMode(){return viewMode;}
    public void setViewMode(String viewMode) { this.viewMode = viewMode; }
    
    public void loadMasterTableData()
    { 
       load();
       removeControl("projectViewerMaster");
       if(!viewMode.equals(ProjectViewerManager.DATA_ON_TABLE_NAME_3))
           altPage = null; //projectViewerMasterTable.jsp
       else
           altPage = "projectResults"; //projectResultsMasterTable.jsp
       //System.out.println("[ProjectViewerMasterBean::loadMasterTableData]----->altPage="+altPage);    
   }

   public Stage loadStage(String projectId,String name) //throws ProjectDataAccessException
   {
      Stage stage = null;
      try
      {
         stage = projectViewerMgr.getStage(projectId, name);
      }
      catch(Exception e)
      {
      }	  
      return stage;
   }
   
   public void load()
   {
      try
      {
         loadAllProjects();
         loadProjectViewerData();
         displayProjects = filterProjectByViewMode();    
         if(displayProjects==null)
            displayProjects = new Project[0];
         ArrayList projs = new ArrayList();
         Hashtable project_table = new Hashtable();
         String projectCode = "";
         for(int i=0;i<displayProjects.length;i++)
         {
            projectCode=displayProjects[i].getAccountId();//set acountId as projectCode 	
			
			if(projectCode==null||"null".equals(projectCode))
				projectCode="NA_"+displayProjects[i].getId(); 
            if(project_table.containsKey(projectCode))
			    projectCode=displayProjects[i].getAccountId()+"_"+displayProjects[i].getId();
			projs.add(projectCode);
            project_table.put(projectCode,displayProjects[i]);
         }	   
         Collections.sort(projs);
         Iterator iterator = projs.iterator();
         Collection col_projects = new ArrayList();
         while(iterator.hasNext())
         {
            projectCode = (String)iterator.next();
            col_projects.add(project_table.get(projectCode));	
         }
         displayProjects = (Project[])col_projects.toArray(new Project[]{});	
         //System.out.println("[ProjectViewerMasterBean::load]----->displayProjects.length="+displayProjects.length);	
      }
      catch(Exception e)
      {
         //e.printStackTrace();	
      }	
   }
   public void clear()
   {
      allProjects = null;
      altPage = null;
   }

   public void changePage() throws ProjectDataAccessException
   {
      load();
      altPage = null;
   }
   
   public void enter() throws ProjectDataAccessException
   {
      load();
      page = "1";

      removeControl("projectViewerMaster");
   }
   public void toRightFrameService() throws ProjectDataAccessException
   {
      try
      {    
        altPage = null;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void toUploadeFileMasterTable() throws ProjectDataAccessException
   {
      try
      {    
         altPage = "storageMasterTable";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
 
   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
         isAcending = !isAcending;
      else
         isAcending = true;
      lastSortBy = sortBy;
      altPage = "storageMasterTable";

   }

   //------
   public HashMap currentAvailableStages = null;
   public HashMap getCurrentAvailableStages(String pId)
   {
      HashMap temp = new HashMap();
      try
      {
         Stage[] stages = projectViewerMgr.getStagesByType(pId);
         if(null==stages||0==stages.length)
            return temp;

         for(int i=0; i<stages.length; i++)
         {
            temp.put(stages[i].getName(), stages[i]);
            Data[] datas = stages[i].getDatas();
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return temp;
   }
   
   public Stage[] getAllStageTemplates(String projectType)
   {
      return projectViewerMgr.getAllAvailableStages(projectType);
   }
   /*****************************************************
    *   private method                                  *
    *****************************************************/
   private Project[] filterProjectByViewMode()
   {	
      ArrayList projects = new ArrayList();	
      Collection   pvs =  null;
      String pid = "";
      try
      {  
         for(int i=0;i< allProjects.length;i++)
         {
            pid = allProjects[i].getId();
            for(int j=0;j<projectViewerData.length;j++)
            {
               if((projectViewerData[j].getId()).equals(pid))
                  projects.add(allProjects[i]);	
            }		
         }	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
      return (Project[])projects.toArray(new Project[0]);	
   }

   private ProjectViewer[] projectViewerData = null;
   public ProjectViewer[] getProjectViewerData(){return projectViewerData;}    
   private void loadProjectViewerData()
   {
      try
      {
      	  if(!"GTF".equalsIgnoreCase(loginDomain)) 
      	     viewMode=ProjectViewerManager.DATA_ON_TABLE_NAME_3;
      	  //System.out.println("[ProjectViewerMasterBean::loadProjectViewerData]--->viewMode="+viewMode);
      	  projectViewerData = projectViewerMgr.loadProjectViewerDataByViewMode(viewMode);
          if(projectViewerData==null)
             projectViewerData = new ProjectViewer[0];
          //System.out.println("[ProjectViewerMasterBean::loadProjectViewerData]--->projectViewerData.length="+projectViewerData.length);   
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }
   private Project[] loadAllProjects()
   {
      try
      {
         allProjects = projectViewerMgr.getProjectsByType(ProjectViewerManager.PROJECT_TYPE_GT); 
         if(allProjects==null)
             allProjects = new Project[0];
        //System.out.println("[ProjectViewerMasterBean::loadAllProjects]--->allProjects.length="+allProjects.length);   
      }
      catch(Exception e)
      {
         //e.printStackTrace();	
      }
      return allProjects;			
   }	
    	
}