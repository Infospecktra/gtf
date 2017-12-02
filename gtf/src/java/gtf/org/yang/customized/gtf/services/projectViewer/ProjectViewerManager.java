/**
 * @ author:  Huei Wen
 * @ version: 1.0  June 20, 2001
 * @ company: 
 */
package org.yang.customized.gtf.services.projectViewer;
//import org.yang.customized.gtf.services.projectViewer.cache.*;
import org.yang.customized.gtf.services.GTFService;
import org.yang.customized.gtf.services.GenericGTFService;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.ProjectComparator;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dataAccess.genericDAO.StorableList;
import org.yang.services.servicemgr.Area;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import org.yang.services.dataAccess.Data;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectViewer;
import org.yang.services.dataAccess.genericDAO.GenericDAO;
import org.yang.services.dbService.RDBMSFactory;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.services.servicemgr.Service;
import java.util.Collections;
import java.util.StringTokenizer;
import java.sql.Array;
import java.util.*;

public class ProjectViewerManager extends GenericGTFService//implements Service
{
   public static final String CACHEKEY_PV_ALL_PROJECTS = "allProjects";   	
   public static final String CACHEKEY_PV_SELECTED_PROJECTS = "selectedProjects";   	
   public static final String CACHEKEY_PV_PROJECTVIEW = "projectViewers";
   
   public static final String tableName1 = "ALL_projectViewer"; 	
   public static final String PROJECT_TYPE_GT = "GT";
   public static final String DATAGROUP_NAME_1 = "tVectorInfo";
   public static final String DATAGROUP_NAME_2 = "SCInfo";
   public static final String DATAGROUP_NAME_3 = "ESCInfo";
   public static final String DATAGROUP_NAME_4 = "data";

   public static final String DATA_ON_TABLE_NAME_1 = "projectSummary";
   public static final String DATA_ON_TABLE_NAME_2 = "southernAnalysis";
   public static final String DATA_ON_TABLE_NAME_3 = "projectResults";

   public static String UPDATE = "update";
   public static String DOMAIN = "GTF"; 
   
   private GenericDAO gdao = null;
   private String domain = null;

   private ArrayList allDomains = null;
   private ProjectViewerManagerArea[] areas = null;
   //private GenericCacheManager cacheManager = null;

   //public ProjectViewerManager(){}
   
   /**
    *  inherit methods  from Service
    */

   protected String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return null; }

   private String loginDomain = "";
   public  String getLoginDomain(){return loginDomain;}      	

   /**
      *  each domain has one ProjectViewer
      */
   public ProjectViewerManager(String dname)
   {
      super(dname);
      //create the tables.
      try
      {
          gdao = new GenericDAO(RDBMSFactory.getInstance().getDataRDBMS());
          ProjectViewer projectViewer = new ProjectViewer();
          projectViewer.setTablename(tableName1);
          gdao.createTable(projectViewer);
          allDomains = new ArrayList();
          loginDomain=dname;
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
   }
   
   public boolean hasMessage()
   {
      return false;
   }

   public void nextMessage()
   {
      return;
   }

   public void initialize(Properties prop, Passport passport)
   {
      String[] domains = passport.getDomains();
      for(int i=0; i<domains.length; i++)
      {
         allDomains.add(domains[i]);
         //System.out.println("[ProjectViewerManager::initialize]xxxx----->domains["+i+"]="+domains[i]);
      }
      Collections.sort(allDomains);
   }

   public void destroy() {}
   /****************************
    *  User permission & right *
    ****************************/

   public Area[] allAreas()
   {
      int size = allDomains.size();
      //System.out.println("[ProjectViewerManager::allArea]oooo----->domains  size="+size);
      areas = new ProjectViewerManagerArea[size];
      ProjectViewerManagerOperation[] ops = null;
      for(int i=0; i<size; i++)
      {
         ops = new ProjectViewerManagerOperation[1];
         ops[0] = new ProjectViewerManagerOperation(this.UPDATE);
         areas[i] = new ProjectViewerManagerArea();
         //areas[i].setName(DOMAIN);//
         areas[i].setName((String)allDomains.get(i));
         areas[i].setAllOperations(ops);
      }   	
      return areas;
   }
/*
   public boolean containArea(String areaname)
   {
      return typeList.contains(areaname);
   }
*/  
   public Project[] getAllOnGoingProjects()
   {
       try
       {
           if(null==daof)
              return null;
           ProjectDAO pdao = daof.getProjectDAO(DAOFactory.ONGOING);
           Project[] projects = pdao.loadAll();
           rebuild(DAOFactory.ONGOING, projects);
           Arrays.sort(projects, new ProjectComparator("name", true));
           return projects;       	 
       }
       catch(Exception e)
       {
         //System.out.println("[ProjectViewerServiceBean::getAllOnGoingProjects] Exception happen : " + e.getMessage());
       }
       return new Project[0];
   }   
  
   //for the pool of ongoing projects  
   //cache
   public Project[] getProjectsByType(String type) throws ProjectDataAccessException
   {
      ArrayList arry_projects = new ArrayList();
      Project[] projects = null;	
      String projectType = "";  
      try
      {
         projects = getAllOnGoingProjects();
        //System.out.println("[ProjectViewerManager::getProjects]xxxxxxxxxxx----->projects.length="+projects.length);	
         for(int i=0;i<projects.length;i++)
         {
             //System.out.println("[ProjectViewerManager::getProjects]xxxxxxxxxxx----->projects["+i+"].getName()="+projects[i].getName());	
        	projectType = projects[i].getType();
            if(projectType.equals(type))
            {
               //System.out.println("[ProjectViewerManager::getProjects]----->projectType="+projectType);	
               arry_projects.add(projects[i]);
            }   
         }	
         Collections.sort(arry_projects, new ProjectComparator("name", true));
         projects = (Project[])arry_projects.toArray(new Project[0]);	
        //System.out.println("[ProjectViewerManager::getProjects]xxxxxxxxxxx----->projects.length="+projects.length);	
         
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		     	
      return projects; 
   }	 
    
   public Data[] getDataArray(Project project,String dataGroupName,String onTableName) throws ProjectDataAccessException
   {
      if(project ==null)
         return null;
      ArrayList arry_data = new ArrayList();
      DataGroup[] dataGroups = new DataGroup[0];	
      String groupName = "";
      String tableName = "";  
      try
      {
      	 
         dataGroups = project.getGroups();
         for(int i=0;i<dataGroups.length;i++)
         {
            groupName = dataGroups[i].getName();
            if(dataGroupName.equals(groupName))
            {
               //System.out.println("[ProjectViewerManager::getDataArray]----->dataGroupName="+dataGroupName);	
               Data[] datas = project.getDatas(dataGroupName);
               for(int j=0;j<datas.length;j++)
               {
                  tableName = datas[i].getOnTableName();
                  if(onTableName.equals(tableName))
                     arry_data.add(datas[i]);   	
               }
            }   
         }	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		     	
      return (Data[])arry_data.toArray(new Data[]{});	
   }	 
   
   /**
      load ProjectViewer data by the viewMode    
   **/ 
   public ProjectViewer[] loadProjectViewerDataByViewMode(String  viewMode) throws ProjectDataAccessException
   {
      if(null==gdao)
         return null;
      StringBuffer sb_conditions = new StringBuffer();
      ProjectViewer[] pvs = null; 
      try
      {
      	 sb_conditions.append(" (viewMode='").append(viewMode).append("') ");
      	 String[] conditions = {sb_conditions.toString()}; 
         ProjectViewer projectViewer = new ProjectViewer();
         projectViewer.setTablename(tableName1);
         pvs = (ProjectViewer[])gdao.load(projectViewer, conditions, false).toArray(new ProjectViewer[]{});
         
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
      return pvs;
   }
   	   
   public void removeProjectViewerByViewMode(String viewMode)
   {
      try
      {
      	
         ProjectViewer pv = new ProjectViewer();
         pv.setTablename(tableName1);
         gdao.delete(pv, "viewMode='"+viewMode+"'");
	 //System.out.println("[ProjectViewerManager::removeProjectViewerByViewMode]--->after delete the pvs of viewMode="+viewMode+" ");
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }	 

   public void createProjectViewers(ProjectViewer[] pvs2) throws org.yang.services.dbService.DataAccessException
   {
      if(pvs2==null)
         return ;	
      try
      {
    	  
         for(int i=0;i<pvs2.length;i++)
         {
            pvs2[i].setTablename(tableName1);
            gdao.insert(pvs2[i]);
         }	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }
	
   public boolean containArea(String areaname)
   {
      return allDomains.contains(areaname);
   }
   
   private String generateId()
   {
      int n = (int)(100.0 * Math.random());	
      String  s = System.currentTimeMillis() + n + "";
      return s;
   }
   
   private void rebuild(Project project) throws ProjectDataAccessException
   {
      rebuild(new Project[] {project});
   }

   private void rebuild(Project[] projects) throws ProjectDataAccessException
   {
      if(null==projects)
         return;

      for(int i=0; i<projects.length; i++)
      {
         try
         {
            if(null==projects[i])
               continue;
            //Data[] datas = projects[i].getDatas();
            //for(int j=0; j<datas.length; j++)
            //{
            //   datas[j].showContent();
            //}
            projects[i].rebuildDatas(daof.getDataDAO(DAOFactory.ONGOING).loadByProjectIdAndStageOrder(projects[i].getId(), "-1"));
            //datas = projects[i].getDatas();
            //for(int j=0; j<datas.length; j++)
            //{
            //   datas[j].showContent();
            //}
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   
   public Stage getStage(String pid, String name) throws ProjectDataAccessException
   {
      return getStage(DAOFactory.ONGOING, pid, name);
   }
   
   public Stage[] getStagesByType(String projectId) throws ProjectDataAccessException
   {
      return getStages(DAOFactory.ONGOING, projectId); //null;
   }
/*  
   public void addProjectsToCache(String myKey,Project[] ps){cacheManager.addElementToCache(myKey,ps);} 
   public Project[] fetchProjectsFromCache(String myKey)
   {
      Project[] allProjects = new Project[0];	
      try
      {	
         Object objs = cacheManager.fetchElementFromCache(myKey);
         if(objs!=null)
         {
            CacheEntry cacheEntry = (CacheEntry)objs;
            allProjects = (Project[])cacheEntry.getElement();
         }
         else
            return new Project[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      return allProjects;
   } 

   public Project[] removeProjectsFromCache(String myKey)
   {
      Object objs = null;	
      Project[]  projects = null;
       
      try
      {	
          objs = cacheManager.removeElementFromCache(myKey);
          if(objs!=null)
          {
             CacheEntry cacheEntry = (CacheEntry)objs;
             projects = (Project[])cacheEntry.getElement();
          }
          else
             return new Project[0];
         //System.out.println("[ProjectViewerManager::fetchProjectsFromCache]--->projects.length="+projects.length);

      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      return  projects;
     
   }  
  
   public void addProjectViewersToCache(String myKey,ProjectViewer[] pvs)
   {
      cacheManager.addElementToCache(myKey,pvs);
   }
   
   public ProjectViewer[] fetchProjectViewersFromCache(String myKey)
   {
      ProjectViewer[] allProjectViewers = new ProjectViewer[0];	
      try
      {	
         Object objs = cacheManager.fetchElementFromCache(myKey);
         if(objs!=null)
         {
            CacheEntry cacheEntry = (CacheEntry)objs;
            allProjectViewers = (ProjectViewer[])cacheEntry.getElement();
           //System.out.println("[ProjectViewerManager::fetchProjectViewersFromCache]--->allProjectViewers.length="+allProjectViewers.length);
         }
         else
            return new ProjectViewer[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      return allProjectViewers;
   } 

   public ProjectViewer[] removeProjectViewersFromCache(String myKey)
   {
      Object objs = null;	
      ProjectViewer[]  ProjectViewers = null;
       
      try
      {	
          objs = cacheManager.removeElementFromCache(myKey);
          if(objs!=null)
          {
             CacheEntry cacheEntry = (CacheEntry)objs;
             ProjectViewers = (ProjectViewer[])cacheEntry.getElement();
          }
          else
             return new ProjectViewer[0];

      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      return  ProjectViewers;
   }
*/   
   //-------------   
   public static void main(String[] args)
   {
   }
}
