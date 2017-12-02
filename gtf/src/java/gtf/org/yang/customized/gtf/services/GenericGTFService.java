/**
 * This class is used to manage client to access a domain and to manipulate some services
 * within each domain.
 * each domain has one UserManager object. Although, we donot define an
 * domain class, the domain concept will appear in database to form an
 * unit of users. Their relationship is: a set of users can belong to another
 * set of groups, which in turn form a domain. A domain will be passed to database
 * as a string
 * @ title  UserManager class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
package org.yang.customized.gtf.services;
import java.io.Serializable;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import java.util.Properties;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.services.dataAccess.Data;
import java.util.ArrayList;
import org.yang.web.applicationContainer.Passport;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;

public abstract class GenericGTFService implements GTFService, Serializable
{
   protected static ArrayList typeList = null;

   protected String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return null; }

   protected String  dName ="";
   protected DAOFactory daof = null;

   /**
      *  each domain has one UserManager
      */
   public GenericGTFService (String dname)
   {
      if(dname==null||"".equals(dname))
          return;
             
      dName =dname;
      Properties prop = new Properties();
      prop.setProperty("DAOFactory.domain", dname);
      prop.setProperty("DAOFactory.classname", "org.yang.customized.gtf.services.dataAccess.TypeIDAOs.TypeIDAOFactoryImpl");
      daof = DAOFactory.getFactory(prop);
      try
      {
         String[] types = ProjectFactory.getFactory().getProjectTypes();
         typeList = new ArrayList();
         for(int i=0; i<types.length; i++)
         {
            typeList.add(types[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void initialize(Properties prop, Passport passport)
   {
      System.out.println("[GenericGTFService::GenericGTFService] Create a GTF service : " + name);
      //create the tables.
   }

   public void destroy()
   {

   }

   /*******************************************
    *  My public method - will used by beans  *
    *******************************************/

   public Project getProjectTemplateByType(String projectType)
   {
      try
      {
         Project project = ProjectFactory.getFactory().createProject(projectType);
         return project;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

   public Stage[] getAllAvailableStages(String projectType)
   {
      try
      {
         Stage[] stages = (ProjectFactory.getFactory().createProject(projectType)).getStageTemplates();
         return stages;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return new Stage[0];
      }
   }

   public Timetable getTimetableTemplate(String projectType)
   {
      try
      {
         Timetable timetable = ProjectFactory.getFactory().getTimetableTemplate(projectType);
         return timetable;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

   public Schedule[] getSchedulesTemplate(String projectType)
   {
      try
      {
         return ProjectFactory.getFactory().getSchedulesTemplate(projectType);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

   public Schedule getScheduleTemplate(String projectType, String name)
   {
      try
      {
         return ProjectFactory.getFactory().getScheduleTemplate(projectType, name);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

   /******************************************************
    *  My protected method - will used by my off spring  *
    ******************************************************/

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] getProjects(String daoType, String type, String domain, String group, String user)throws ProjectDataAccessException
   {
      if(null==type)
         return new Project[0];

      ArrayList keys = new ArrayList();
      ArrayList vals = new ArrayList();
      keys.add("type");
      vals.add(type);

      if(null!=domain)
      {
         keys.add("domain");
         vals.add(domain);
      }

      if(null!=group)
      {
         keys.add("group");
         vals.add(group);
      }

      if(null!=user)
      {
         keys.add("investigator");
         vals.add(user);
      }

      System.out.println("(1) domain:" + domain + ", group:" + group + ", user:" + user);
      Project[] projects = daof.getProjectDAO(daoType)
                               .load((String[])keys.toArray(new String[]{}),
                                     (String[])vals.toArray(new String[]{}), true);
      rebuild(daoType, projects);
      return projects;
   }

   public Project[] getProjects(String daoType, String type, String domain, String group, String user, String condition)throws ProjectDataAccessException
   {
      ArrayList conditions = new ArrayList();
      if(null!=condition&&!"".equals(conditions))
         conditions.add(condition);

      if(null!=type)
         conditions.add("type = '" + type + "'");

      if(null!=domain)
         conditions.add("domain = '" + domain + "'");

      if(null!=group)
         conditions.add("group = '" + group + "'");

      if(null!=user)
         conditions.add("investigator = '" + user + "'");

      System.out.println("(1) domain:" + domain + ", group:" + group + ", user:" + user);
      Project[] projects = daof.getProjectDAO(daoType)
                               .load((String[])conditions.toArray(new String[]{}), true);
      rebuild(daoType, projects);
      return projects;
   }
   
   public Project[] getProjects(String daoType, String type, String domain, String group, String user, String condition,String sortBy)throws ProjectDataAccessException
   {
      ArrayList conditions = new ArrayList();
      if(null!=condition&&!"".equals(conditions))
         conditions.add(condition);

      if(null!=type)
         conditions.add("type = '" + type + "'");

      if(null!=domain)
         conditions.add("domain = '" + domain + "'");

      if(null!=group)
         conditions.add("group = '" + group + "'");

      if(null!=user)
         conditions.add("investigator = '" + user + "'");

      System.out.println("(1) domain:" + domain + ", group:" + group + ", user:" + user);
      Project[] projects = daof.getProjectDAO(daoType)
                               .loadProjectsOrderingByAttribute((String[])conditions.toArray(new String[]{}), true,sortBy);
      rebuild(daoType, projects);
      return projects;
   }
   
   public Project[] getProjects(String DAOType, String[] pids)throws ProjectDataAccessException
   {
      if(null==pids||null==daof)
         return null;
      Project[] projects = daof.getProjectDAO(DAOType).load(pids);
      rebuild(DAOType, projects);
      return projects;
   }

   public Project getProject(String DAOType, String pid)throws ProjectDataAccessException
   {
      if(null==pid)
         return null;
      Project[] projects = getProjects(DAOType, new String[]{pid});
      if(null==projects||0>=projects.length)
         return null;
      return projects[0];
   }

   protected Stage getStage(String DAOType, String pid, String name) throws ProjectDataAccessException
   {
      if(null==pid||null==name)
         return null;
      String[] fields = new String[] {"projectId", "name"};
      String[] vals   = new String[] {pid, name};
      Stage[] stages = daof.getStageDAO(DAOType).load(fields, vals);
      if(null==stages||0==stages.length)
         return null;
      rebuild(DAOType, pid, stages[0]);
      return stages[0];
   }

   protected Stage[] getStages(String DAOType, String pid) throws ProjectDataAccessException
   {
      if(null==pid)
         return new Stage[0];

      Stage[] stages = daof.getStageDAO(DAOType).loadByProject(pid);
      for(int i=0; i<stages.length; i++)
      {
         rebuild(DAOType, pid, stages[i]);
      }

      return stages;
   }

   protected boolean createStage(String DAOType, Stage stage) throws ProjectDataAccessException
   {
      try
      {
         if(null==stage||null==stage.getId()||-1==stage.getOrder())
            return false;
         System.out.println("==================== Create Stage ======================");
         System.out.println(stage.getId());
         System.out.println(stage.getProjectId());
         System.out.println(stage.getName());
         System.out.println(stage.getDescription());
         System.out.println(stage.getStatus());
         System.out.println(stage.getDoneDate());
         System.out.println(stage.getLastUpdateDate());
         System.out.println(stage.getNote());
         System.out.println(stage.getOrder());
         System.out.println("==================== Create Stage ======================");
         // update datas
         saveDatas(DAOType, stage.getDatas(), stage.getProjectId() + ":" +stage.getOrder(), true);
         daof.getStageDAO(DAOType).insert(stage);
         return true;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   protected boolean updateStage(String DAOType, Stage stage) throws ProjectDataAccessException
   {
      try
      {
         if(null==stage||null==stage.getProjectId())
            return false;
         //System.out.println("==================== Update Stage 1 ======================");
         //System.out.println("id:" + stage.getId());
         //System.out.println("pid:" + stage.getProjectId());
         //System.out.println(stage.getName());
         //System.out.println(stage.getDescription());
         //System.out.println(stage.getStatus());
         //System.out.println(stage.getDoneDate());
         //System.out.println(stage.getLastUpdateDate());
         //System.out.println(stage.getNote());
         //System.out.println(stage.getOrder());
         //System.out.println("==================== Update Stage 1 ======================");
         // update datas
         saveDatas(DAOType, stage.getDatas(), stage.getProjectId() + ":" +stage.getOrder(), false);
         // update stage - ongoing
         daof.getStageDAO(DAOType).update(stage);

         return true;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   protected void saveDatas(String DAOType, Data[] datas, String idPrifix, boolean isInsert) throws ProjectDataAccessException
   {
      try
      {
         for(int i=0; i<datas.length; i++)
            datas[i].setId(idPrifix + ":" + datas[i].getName());

         // all datas are in ongoing table
         DAOType = DAOFactory.ONGOING;
         if(isInsert)
         {
            daof.getDataDAO(DAOType).insert(datas);
            daof.getDataDAO(DAOType).update(datas);
         }
         else
         {
            daof.getDataDAO(DAOType).update(datas);
            daof.getDataDAO(DAOType).insert(datas);
         }
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   protected void rebuild(String DAOType, String pid, Stage stage) throws ProjectDataAccessException
   {
      try
      {
         // all datas are in ongoing table
         DAOType = DAOFactory.ONGOING;
         stage.rebuildDatas(daof.getDataDAO(DAOType).loadByProjectIdAndStageOrder(pid, stage.getOrder()+""));
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   protected void rebuild(String DAOType, Project project) throws ProjectDataAccessException
   {
      DAOType = DAOFactory.ONGOING;
      rebuild(DAOType, new Project[] {project});
   }

   protected void rebuild(String DAOType, Project[] projects) throws ProjectDataAccessException
   {
      if(null==projects)
         return;

      // all datas are in ongoing table
      DAOType = DAOFactory.ONGOING;
      for(int i=0; i<projects.length; i++)
      {
         try
         {
            if(null==projects[i])
               continue;
            projects[i].rebuildDatas(daof.getDataDAO(DAOType).loadByProjectIdAndStageOrder(projects[i].getId(), "-1"));
         }
         catch(Exception e)
         {
            System.out.println("[GenericGTFService::rebuild] Rebuild data failed:" + e.getMessage());
            //e.printStackTrace();
         }
      }
   }
}