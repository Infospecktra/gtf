/* Generated by Together */

package org.yang.customized.gtf.services.projectViewer.web;

import java.util.Collection;
import java.util.ArrayList;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectViewer;
import org.yang.customized.gtf.services.projectViewer.ProjectViewerManager;

public class ProjectListMenuBean extends ProjectViewerBean
{
   static final long serialVersionUID = 4711296332221794601L;
   public ProjectListMenuBean()
   {
      super();
   }

   /*********************************
    *  accessive methods used by Builder  *
    *********************************/
   //selected check Boxes
   private String[] targetIds = null;
   public void setTargetIds(String[] targetIds) 
   { 
   	this.targetIds = targetIds; 
        //System.out.println("[ProjectListMenuBean::setTargetIds]--(after)--targetIds.length="+targetIds.length);
   }
   public String[] getTargetIds() { 
       return targetIds; 
   }

   private String selectCheckBoxMode="";
   public String getSelectCheckBoxMode(){return selectCheckBoxMode;} 
   public void setSelectCheckBoxMode(String selectCheckBoxMode){this.selectCheckBoxMode=selectCheckBoxMode;}
   
   private String loginDomain = null;
   public String getLoginDomain()
   {
     
      try
      {
      	 loginDomain=projectViewerMgr.getLoginDomain();
      }
      catch(Exception e)
      {
      }
     			
      return loginDomain;
   }
   
   private String viewMode = ProjectViewerManager.DATA_ON_TABLE_NAME_1;
   public String getViewMode(){return viewMode;}
   /*
     when on click check boxes on ProjectListMenuBuilder 
     1. update the data in ALL_projectViewer tabe based on projectViewerMap  
     2. refresh reload data and projectViewerMap --> load()
   */
   public void setViewMode(String selectedViewMode) 
   { 
      //System.out.println("[ProjectListMenuBean::setViewMode]@@--///////////-->this.getLastActiontype()="+this.getLastActiontype());
      
      try
      {   
          this.viewMode = selectedViewMode; 
          //if(!"GTF".equalsIgnoreCase(loginDomain))
          //   this.viewMode = ProjectViewerManager.DATA_ON_TABLE_NAME_3;
          ////load();
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }
   
   private Project[] allAvailableProjects = null;
   public Project[] getAllAvailableProjects()
   {
      try
      {
      	 if(allAvailableProjects==null)
      	    allAvailableProjects = new Project[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
      return allAvailableProjects;
   }
  
   /***************************************
    *  Implement GenericHandler's method  *
    ***************************************/

   protected void init() throws Exception
   { 
      ensureResource();
      loginDomain=projectViewerMgr.getLoginDomain();      
      if(!"GTF".equalsIgnoreCase(loginDomain))
         this.viewMode = ProjectViewerManager.DATA_ON_TABLE_NAME_3;
      if(null==projectViewerMgr)
         throw new Exception("ProjectViewer manager should not be null.");
   }
   
   private String altPage = null; 
   protected String altPage()
   {
     System.out.println("[ProjectViewerServiceBean::altPage] reload page :" + isReload);
      if(isReload)
      {
         passport.setRuntimeProperty("right", "/projectListMenu.wf?actiontype=clear");
         return "reload";
      }
      else
         return altPage;
   }

   protected void destroy()
   {
      projectViewerMgr = null;
   }

   /***************************************
    *           my action method          *
    ***************************************/
   public void clear()
   {
     //System.out.println("[ProjectViewerServiceBean::clear]----->enter this scope");	
   }

   public void displayProjectList()
   {
      try
      {	
         load();
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	   	
      removeControl("projectList");
      altPage = "leftBodyFrame" ;
   }	

   //1.load all ongoing projecs
   //2.load selected projectViewerData -->data Structure
   public void load()
   {
      try
      {
         loadAllProjects();
         loadProjectViewerData();
         allAvailableProjects = filterProjectByViewMode(); //fro passing  to masterBean    
         //System.out.println("[ProjectListMenuBean::load]----->allAvailableProjects.length="+allAvailableProjects.length);	
         if(allAvailableProjects==null)
            allAvailableProjects = new Project[]{};
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
   }

   public Project[] getDisplayProjects()
   {      
   	allProjects=filterProjectByLoginDomain(allProjects);
   	return allProjects;
   }
   
   //for the ViewMode selection
   public void changePage()
   {
      //System.out.println("[ProjectListMenuBean::changePage]---------->viewMode="+viewMode);
      load();
      altPage="projectListMenu" ;	
   }	  
   
   //for btnPanel.jsp   
   public void commitData()
   {
      try 
      {
      	 //System.out.println("[ProjectListMenuBean::commitData]----targetIds.length="+targetIds.length);
      	 //System.out.println("[ProjectListMenuBean::commitViewerData]----selectCheckBoxMode="+selectCheckBoxMode);

         if("1".equals(selectCheckBoxMode))
         {
            selectAll();
            selectCheckBoxMode="";
         }   
         else if("0".equals(selectCheckBoxMode))
         {
            deselectAll();
            selectCheckBoxMode="";
         }    	

         updateProjectViewerData();
       
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      altPage="projectListMenu" ;
   }

   /***************************************
    *           my private method          *
    ***************************************/

//----------
   /**
    1.projectViewerMgr.removeProjectViewerByViewMode(this.viewMode)
    2.projectViewerMgr.createProjectViewers(pvs2)
    3.load()
    **/
   private void  updateProjectViewerData()
   {
      try
      {
      	  String pid = "";
          ArrayList pvs = new ArrayList();
      	  //System.out.println("[ProjectListMenuBean::updateProjectViewerData]----this.viewMode="+this.viewMode);
          //System.out.println("[ProjectListMenuBean::updateProjectViewerData]----allAvailableProjects.length="+allAvailableProjects.length);
      	  //System.out.println("[ProjectListMenuBean::updateProjectViewerData]----targetIds.length="+targetIds.length);

          projectViewerMgr.removeProjectViewerByViewMode(this.viewMode);	
          for(int i=0;i<targetIds.length;i++)
          {
                ProjectViewer pv = new ProjectViewer();
                pv.setId(targetIds[i]);
                pv.setViewMode(this.viewMode);
                pvs.add(pv);
          }
          ProjectViewer[] pvs2 = (ProjectViewer[])pvs.toArray(new ProjectViewer[]{});
          projectViewerMgr.createProjectViewers(pvs2);
      	  //System.out.println("[ProjectListMenuBean::updateProjectViewerData]----insert pvs.length=="+ pvs2.length);
          load();
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }	 

   /**
     Filtering projects by targetIds 
     1.tempo:use project id  instead of accustomerId
   **/
   private Project[] filterProjectByTargetIds()
   {   
      ArrayList projects = new ArrayList();	
      Collection pvs =  null;
      String projectId = "";
      if(targetIds==null)
         return new Project[0];
      try
      {  
         for(int i=0;i< allProjects.length;i++)
         {
            projectId = allProjects[i].getId();
            for(int j=0;j<targetIds.length;j++)
            {
               if(targetIds[j].equals(projectId))
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

   private String[] reloadTargetIdsByProjectViewerData()
   {
      ArrayList projectIds = new ArrayList(); 
      if(projectViewerData==null)
    	  return new String[0];
      try
      {
          for(int j=0;j<projectViewerData.length;j++)
          {
              projectIds.add(projectViewerData[j].getId());
          }   	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
      if(projectIds.size()==0)
         return new String[0];
      return (String[])projectIds.toArray(new String[0]); 
   }

   public ProjectViewer[] getProjectViewerData(){return projectViewerData;}    
   private ProjectViewer[] projectViewerData = null;
   private void loadProjectViewerData()
   {
      try
      {
          projectViewerData = projectViewerMgr.loadProjectViewerDataByViewMode(viewMode);
      	  if(projectViewerData==null)
             projectViewerData = new ProjectViewer[]{};
      	  //System.out.println("[ProjectListMenuBean::loadProjectViewerData]--->projectViewerData.length="+projectViewerData.length);   
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }
   
   /**
     Filtering projects by viewMode 
   **/
   private Project[] filterProjectByViewMode()
   {	
      ArrayList projects = new ArrayList();	
      Collection  pvs =  null;
      String pid = "";
      if(allProjects ==null)
         return new Project[0];	  
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
         targetIds = reloadTargetIdsByProjectViewerData();
         //System.out.println("[ProjectListMenuBean::filterProjectByViewMode]----->targetIds.length="+targetIds.length);
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
      return (Project[])projects.toArray(new Project[0]);	
   }
   
   private Project[] allProjects = null;
   private Project[] loadAllProjects()
   {
      ArrayList pjs = new ArrayList();
      try
      {
	 allProjects= projectViewerMgr.getProjectsByType(ProjectViewerManager.PROJECT_TYPE_GT); 
         if(allProjects==null)
             allProjects = new Project[0];
         //System.out.println("[ProjectListMenuBean::loadAllProjects]--->allProjects.length="+allProjects.length);   
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }
      return allProjects;			
   }	
   private void selectAll()
   {
      if(allProjects==null)
    	  allProjects = new Project[0];
	  
      ArrayList selectedIds = new ArrayList(); 
      try
      {
         for(int i=0;i<allProjects.length;i++)
         {
            selectedIds.add(allProjects[i].getId());	
         }
         targetIds = (String[])selectedIds.toArray(new String[]{});
         if(targetIds==null)
             targetIds = new String[]{};
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
   }
    
   private void deselectAll()
   {
      try
      {
         targetIds = new String[]{};	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
   }
   
   private Project[] filterProjectByLoginDomain(Project[] projects)
   {
      ArrayList arry_Projects = new ArrayList();
      String targetDomain = "";
      //System.out.println("[ProjectListViewerBean::createList]BBBBBB----->loginDomain="+loginDomain);
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

   
   //1.targetIds --> for project list
   /* The onclick action for select box 
   public void changeSelectedItems()
   {
      try
      {	
         updateProjectViewerData();
         altPage=null; //projectViewerBody.jsp	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	   
   }
   */	  
}