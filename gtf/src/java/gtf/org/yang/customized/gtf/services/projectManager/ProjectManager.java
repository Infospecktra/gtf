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
package org.yang.customized.gtf.services.projectManager;
import org.yang.services.servicemgr.Service;
import java.io.Serializable;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import java.util.Properties;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import org.yang.services.servicemgr.Area;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.services.servicemgr.Operation;
import org.yang.customized.gtf.services.dataAccess.StageDAO;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.services.dataAccess.Data;
import java.util.ArrayList;
import java.util.HashMap;
import org.yang.services.dataAccess.DataDAO;
import org.yang.customized.gtf.services.GTFService;
import org.yang.customized.gtf.services.GenericGTFService;
import org.yang.services.dataAccess.TypeIDAOImpl;


public class ProjectManager extends GenericGTFService
{
   private ProjectManagerArea[] areas = null;

   private String loginDomain = null;
   public String getLoginDomain(){return loginDomain;}
    
     
   /**
      *  each domain has one UserManager
      */
   public ProjectManager (String dname)
   {
      super(dname);
      //create the tables.
      loginDomain = dname;
      daof.getProjectDAO(DAOFactory.DONE).createTable();
      daof.getStageDAO(DAOFactory.DONE).createTable();
      daof.getDataDAO(DAOFactory.DONE).createTable();

      int size = typeList.size();
      areas = new ProjectManagerArea[size];
      ProjectManagerOperation[] ops = null;

      for(int i=0; i<size; i++)
      {
         ops = new ProjectManagerOperation[9];
         ops[0] = new ProjectManagerOperation(this.CROSS_DOMAIN);
         ops[1] = new ProjectManagerOperation(this.CROSS_USER);
         ops[2] = new ProjectManagerOperation(this.CLIENT);
         ops[3] = new ProjectManagerOperation(this.CREATE);
         ops[4] = new ProjectManagerOperation(this.DELETE);
         ops[5] = new ProjectManagerOperation(this.UPDATE);
         ops[6] = new ProjectManagerOperation(this.SYSTEM_DATA);
         ops[7] = new ProjectManagerOperation(this.ACTIVATE);
         ops[8] = new ProjectManagerOperation(this.HISTORY);
         areas[i] = new ProjectManagerArea();
         areas[i].setName((String)typeList.get(i));
         areas[i].setAllOperations(ops);
      }
   }

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] getProjects(String type, String domain, String group, String user, String condition)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.ONGOING, type, domain, group, user, condition);
   }

   /**
 * this method will get all group object in one doamian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Project[] getAllProjects()throws ProjectDataAccessException
   {
      if(null==daof)
         return null;
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.ONGOING);
      Project[] projects = pdao.loadAll();
      rebuild(DAOFactory.ONGOING, projects);
      return projects;
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Project[] getProjects(String[] pids)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.ONGOING, pids);
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Project getProject(String pid)throws ProjectDataAccessException
   {
      return getProject(DAOFactory.ONGOING, pid);
   }

   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public Project removeProject(String pid) throws ProjectDataAccessException
   {
      try
      {
         if(null==pid||null==dName)
            return null;

         Project target = null;
         if(null==(target=getProject(pid)))
            return null;

         daof.getProjectDAO(DAOFactory.ONGOING).delete(pid);
         return target;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   public Project[] getProjectsByStageName(String type, String sname, String domain) throws ProjectDataAccessException
   {
      //System.out.println("[ProjectManager::getProjectsByStageName] Load project for stage:" + sname);
      if(null==sname)
         return new Project[0];

      Stage[] stages = daof.getStageDAO(DAOFactory.ONGOING).loadByName(sname);
      if(null==stages||0==stages.length)
         return new Project[0];

      int stagesLen = stages.length;
      String[] fields = new String[stagesLen];
      String[] vals = new String[stagesLen];

      for(int i=0 ; i<stagesLen; i++)
      {
         fields[i] = "id";
         vals[i] = stages[i].getProjectId();
      }

      Project[] ps = daof.getProjectDAO(DAOFactory.ONGOING).load(fields, vals, false);

      ArrayList pList = new ArrayList();
      if(null!=domain)
      {
         for(int i=0; i<ps.length; i++)
         {
            if(domain.endsWith(ps[i].getDomain())&&type.equals(ps[i].getType()))
            {
               rebuild(DAOFactory.ONGOING, ps[i]);
               pList.add(ps[i]);
            }
         }
      }
      else
      {
         for(int i=0; i<ps.length; i++)
         {
            if(type.equals(ps[i].getType()))
            {
               rebuild(DAOFactory.ONGOING, ps[i]);
               pList.add(ps[i]);
            }
         }
      }
      return (Project[])pList.toArray(new Project[]{});
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean createProject(Project project) throws ProjectDataAccessException
   {
      if(null==project)
         return false;

      try
      {
         // insert datas
         this.saveDatas(DAOFactory.ONGOING, project.getDatas(), project.getId() + ":-1", true);
         // insert project
         daof.getProjectDAO(DAOFactory.ONGOING).insert(project);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
   public boolean updateProject(Project project)throws ProjectDataAccessException
   {
      if(null==project)
         return false;

      try
      {
         // update datas
         this.saveDatas(DAOFactory.ONGOING, project.getDatas(), project.getId() + ":-1", false);
         // update project
         daof.getProjectDAO(DAOFactory.ONGOING).update(project);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   public boolean closeProject(String pid)throws ProjectDataAccessException
   {
      Project target = null;
      Stage[] stages = null;
      Data[] datas = null;
      try
	  {
      if(null==(stages=removeStages(pid)))
         return false;
   
      for(int i=0; i<stages.length; i++)
      {
         daof.getStageDAO(DAOFactory.DONE).insert(stages[i]);
         saveDatas(DAOFactory.DONE, stages[i].getDatas(), stages[i].getProjectId() + ":" +stages[i].getOrder(), true);//	  
	  }
	  removeDatas(DAOFactory.ONGOING,pid);	

      if(null==(target=this.removeProject(pid)))
         return false;

	 
      target.setEndDate((new Date()).getTime());
      daof.getProjectDAO(DAOFactory.DONE).insert(target);
	  }
	  catch(Exception e){e.printStackTrace();}
      return true;
   }

   public Stage getStage(String pid, String name) throws ProjectDataAccessException
   {
      return getStage(DAOFactory.ONGOING, pid, name);
   }

   public Stage[] getStages(String pid) throws ProjectDataAccessException
   {
      return getStages(DAOFactory.ONGOING, pid);
   }

   public Stage[] getStagesByType(String projectType) throws ProjectDataAccessException
   {
      return null;//getStagesByType(DAOFactory.ONGOING, projectType);
   }

   public boolean createStage(Stage stage) throws ProjectDataAccessException
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
         saveDatas(DAOFactory.ONGOING, stage.getDatas(), stage.getProjectId() + ":" +stage.getOrder(), true);
         // insert stage - ongoing
         daof.getStageDAO(DAOFactory.ONGOING).insert(stage);
         return true;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   public boolean updateStage(Stage stage) throws ProjectDataAccessException
   {
      return updateStage(DAOFactory.ONGOING, stage);
   }

   public Stage[] removeStages(String pid) throws ProjectDataAccessException
   {
      try
      {
         if(null==pid)
            return new Stage[0];
         StageDAO sdao = daof.getStageDAO(DAOFactory.ONGOING);
         Stage[] stages = null;
         stages = sdao.loadByProject(pid);
         sdao.deleteByProject(pid);
		 
         return stages;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

  public Data[] removeDatas(String daoType,String pid) throws ProjectDataAccessException
   {
      if(null==pid)
         return new Data[0];
      try
	  { 
         ((TypeIDAOImpl)daof.getDataDAO(daoType)).deleteByProject(pid);//ex:daoType=DAOFactory.ONGOING
	  
      //Data[] datas = ddao.loadByProject(id);
      //sdao.deleteByProject(pid);
      //return stages;
	  }
	  catch(Exception e){}
	  
      return null;
   }
   public Area[] allAreas()
   {
      return areas;
   }

   public boolean containArea(String areaname)
   {
      return typeList.contains(areaname);
   }

   public static void main(String[] args)
   {


   }
}
