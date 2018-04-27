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
package org.yang.customized.gtf.services.scheduleManager;
import org.yang.services.servicemgr.Service;
import java.io.Serializable;
import java.util.Properties;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import java.util.Collection;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.services.servicemgr.Area;
import java.util.ArrayList;
import org.yang.services.servicemgr.Operation;
import org.yang.util.DateFormatter;
import java.util.Date;
import java.util.Arrays;
import org.yang.customized.gtf.services.dataAccess.StageDAO;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.services.dataAccess.Data;
import org.yang.customized.gtf.services.GTFService;
import org.yang.customized.gtf.services.GenericGTFService;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;

public class ScheduleManager extends GenericGTFService
{
   private RequestsQueue rq = null;
   private ScheduleManagerArea[] areas = null;

   /**
      *  each domain has one UserManager
      */
   public ScheduleManager (String dname)
   {
      super(dname);
      //create the tables.

      daof.getProjectDAO(DAOFactory.WAITING).createTable();
      daof.getProjectDAO(DAOFactory.ONGOING).createTable();
      daof.getStageDAO(DAOFactory.ONGOING).createTable();
      daof.getDataDAO(DAOFactory.ONGOING).createTable();

      int size = typeList.size();
      areas = new ScheduleManagerArea[size];
      ScheduleManagerOperation[] ops = null;

      for(int i=0; i<size; i++)
      {
         ops = new ScheduleManagerOperation[8];
         ops[0] = new ScheduleManagerOperation(this.CROSS_DOMAIN);
         ops[1] = new ScheduleManagerOperation(this.CROSS_USER);
         ops[2] = new ScheduleManagerOperation(this.CLIENT);
         ops[3] = new ScheduleManagerOperation(this.CREATE);
         ops[4] = new ScheduleManagerOperation(this.DELETE);
         ops[5] = new ScheduleManagerOperation(this.UPDATE);
         ops[6] = new ScheduleManagerOperation(this.ACTIVATE);
         ops[7] = new ScheduleManagerOperation(this.HISTORY);
         areas[i] = new ScheduleManagerArea();
         areas[i].setName((String)typeList.get(i));
         areas[i].setAllOperations(ops);
         //System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::" + areas[i].getName());
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

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] getProjects(String type, String domain, String group, String user, String condition)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.WAITING, type, domain, group, user, condition);
   }

   /**
    * this method will get project objects in one doamian sorting by time(projectID)
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] getProjectsSortingByID(String type, String domain, String group, String user, String condition,String sortBy)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.WAITING, type, domain, group, user, condition,sortBy);
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
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.WAITING);
      Project[] projects = pdao.loadAll();
      rebuild(projects);
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
      if(null==pids||null==daof)
         return null;
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.WAITING);
      Project[] projects = pdao.load(pids);
      rebuild(projects);
      return projects;
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Project getProject(String pid)throws ProjectDataAccessException
   {
      if(null==pid)
         return null;
      Project[] projects = getProjects(new String[]{pid});
      if(null==projects||0>=projects.length)
      {
         return null;
      }
      return projects[0];
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

         ProjectDAO pdao = daof.getProjectDAO(DAOFactory.WAITING);
         pdao.delete(pid);
         return target;
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

 
   public boolean createProject(Project project) throws ProjectDataAccessException
   {
      if(null==project)
         return false;
      String id = this.generateId();
      project.setId(id);
      System.out.println("Before insert project : " + id);

      try
      {
         // insert datas
         this.saveDatas(project.getDatas(), id + ":-1", true);
         // insert project
         daof.getProjectDAO(DAOFactory.WAITING).insert(project);
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
         this.saveDatas(project.getDatas(), project.getId() + ":-1", false);
         // update project
         daof.getProjectDAO(DAOFactory.WAITING).update(project, false);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   public boolean startProject(String pid) throws ProjectDataAccessException
   {
      try
      {
         // Shall do safe transaction
         Project target = null;

         // remove project - waiting
         if(null==(target=removeProject(pid)))
            return false;

         // set starting date for project
         target.setStartDate(System.currentTimeMillis());

         // create the first stage
         Stage stage = target.getStageTemplate(0);
         stage.setId(target.getId()+"0");
         stage.setProjectId(target.getId());
         stage.setOrder(0);

         // insert datas
         saveDatas(stage.getDatas(), stage.getProjectId() + ":" +stage.getOrder(), true);

         // insert stage - ongoing
         daof.getStageDAO(DAOFactory.ONGOING).insert(stage);

         // insert project - ongoing
         daof.getProjectDAO(DAOFactory.ONGOING).insert(target);
         return true;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   public boolean swap(String pid1, String pid2) throws ProjectDataAccessException
   {
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.WAITING);
      Project[] projects = new Project[2];
      projects[0] = pdao.load(pid1);
      projects[1] = pdao.load(pid2);
      long sn = projects[0].getSerialNumber();
      projects[0].setSerialNumber(projects[1].getSerialNumber());
      projects[1].setSerialNumber(sn);
      return pdao.update(projects);
   }

   public Area[] allAreas()
   {
      return areas;
   }

   public boolean containArea(String areaname)
   {
      return typeList.contains(areaname);
   }

   private void saveDatas(Data[] datas, String idPrifix, boolean isInsert) throws ProjectDataAccessException
   {
      try
      {
         for(int i=0; i<datas.length; i++)
         {
            //System.out.println("==================== Save data ====================== id =" + idPrifix + ":" + datas[i].getName());
            datas[i].setId(idPrifix + ":" + datas[i].getName());
         }

         if(isInsert)
            daof.getDataDAO(DAOFactory.ONGOING).insert(datas);
         else
            daof.getDataDAO(DAOFactory.ONGOING).update(datas);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   private String generateId()
   {
      return DateFormatter.getDateTimeString(new Date(System.currentTimeMillis()));
   }

   private boolean sendCreateProjectRequest(Project project) throws ProjectDataAccessException
   {
      return false;
   }

   private boolean sendUpdateProject(Project project)throws ProjectDataAccessException
   {
      return false;
   }

   private boolean sendRemoveProjectRequest(String pid) throws ProjectDataAccessException
   {
      return false;
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

   public static void main(String[] args)
   {
      ScheduleManager smanager = new ScheduleManager("test_domain");

      // Load Project
      Project[] pl = null;
      try
      {
         pl = smanager.getAllProjects();
      } catch(Exception e) { e.printStackTrace(); }

      for(int i=0; i<pl.length; i++)
      {
         System.out.println("==========================================");
         System.out.println(pl[i].getId());
         System.out.println(pl[i].getName());
         System.out.println(pl[i].getAccountId());
         System.out.println(pl[i].getDescription());
         System.out.println(pl[i].getSerialNumber()+"");
         System.out.println(pl[i].getDomain());
      }

      try
      {
         smanager.swap("132432432423432", "132432432423434");
         pl = smanager.getAllProjects();
      } catch(Exception e) { e.printStackTrace(); }

      for(int i=0; i<pl.length; i++)
      {
         System.out.println("==========================================");
         System.out.println(pl[i].getId());
         System.out.println(pl[i].getName());
         System.out.println(pl[i].getAccountId());
         System.out.println(pl[i].getDescription());
         System.out.println(pl[i].getSerialNumber()+"");
         System.out.println(pl[i].getDomain());
      }
   }
}
