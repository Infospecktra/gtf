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
package org.yang.customized.gtf.services.reportManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Properties;

import org.yang.services.dbService.IDGenerator;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.servicemgr.Service;
import org.yang.services.servicemgr.Area;
import java.io.Serializable;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import org.yang.services.servicemgr.Operation;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.StageDAO;
import org.yang.util.SMUtility;
import org.yang.customized.gtf.services.GTFService;
import org.yang.customized.gtf.services.GenericGTFService;
import java.util.Calendar;

public class ReportManager extends GenericGTFService
{
   private ReportManagerArea[] areas = null;

   /**
      *  each domain has one UserManager
      */
   public ReportManager (String dname)
   {
      super(dname);
      int size = typeList.size();
      areas = new ReportManagerArea[size];
      ReportManagerOperation[] ops = null;

      for(int i=0; i<size; i++)
      {
         ops = new ReportManagerOperation[8];
         ops[0] = new ReportManagerOperation(this.CROSS_DOMAIN);
         ops[1] = new ReportManagerOperation(this.CROSS_USER);
         ops[2] = new ReportManagerOperation(this.CLIENT);
         ops[3] = new ReportManagerOperation(this.CREATE);
         ops[4] = new ReportManagerOperation(this.DELETE);
         ops[5] = new ReportManagerOperation(this.UPDATE);
         ops[6] = new ReportManagerOperation(this.ACTIVATE);
         ops[7] = new ReportManagerOperation(this.HISTORY);
         areas[i] = new ReportManagerArea();
         areas[i].setName((String)typeList.get(i));
         areas[i].setAllOperations(ops);
      }
   }

   /**
  * Remove the user tables of a domain.
  */
   public void removeDomain() {}

   public Project[] getWaitingProjects()
   {
      return null;
   }

   public Project[] getOngoingProjects()
   {
      return null;
   }

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] browseProjects(int calendarView,
                                   String domain,
                                   String projectType,
                                   String browseBy,
                                   Date date) throws ProjectDataAccessException
   {
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.DONE);
      long dateFrom = 0;
      long dateTo = 0;

      switch(calendarView)
      {
         case 2: 
         {
            // date from
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            dateFrom = date.getTime();

            // date to
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
            dateTo = date.getTime();

            break;
         }
         case 1: 
         {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1900 + date.getYear(),
                         date.getMonth(),
                         1,
                         0,
                         0,
                         0);
            dateFrom = calendar.getTime().getTime();

            calendar.set(1900 + date.getYear(),
                         date.getMonth(),
                         calendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                         23,
                         59,
                         59);
            dateTo = calendar.getTime().getTime();
            break;
         }
         case 0:
         { 
            Calendar calendar = Calendar.getInstance();
            calendar.set(1900 + date.getYear(),
                         0,
                         1,
                         0,
                         0,
                         0);
            dateFrom = calendar.getTime().getTime();

            calendar.set(1900 + date.getYear(),
                         11,
                         calendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                         23,
                         59,
                         59);
            dateTo = calendar.getTime().getTime();
            break;
         }
      }
System.out.println("Origin date:" + date + ",From date:" + new Date(dateFrom) + ", To date:" + new Date(dateTo));

      String[] conditions = null;
      if(null!=domain&&
        !"ALL".equals(domain))
      {
         if(null!=projectType&&
            !"ALL".equals(projectType))
            conditions = new String[]{"domain='" + domain + "'",
      	                              "type='" + projectType + "'",
                                      browseBy + ">='" + dateFrom + "'",
                                      browseBy + "<='" + dateTo + "'"};
         else
            conditions = new String[]{"type='" + projectType + "'",
                                      browseBy + ">='" + dateFrom + "'",
                                      browseBy + "<='" + dateTo + "'"};
      }
      else
      {
         if(null!=domain&&!
            "ALL".equals(domain))
            conditions = new String[]{"domain='" + domain + "'",
                                      browseBy + ">='" + dateFrom + "'",
                                      browseBy + "<='" + dateTo + "'"};
         else
            conditions = new String[]{browseBy + ">='" + dateFrom + "'",
                                      browseBy + "<='" + dateTo + "'"};
      }

      return pdao.load(conditions, true);
   }

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Project[] searchProjects(String searchBy,
                                   String keywords,
                                   boolean isAnd,
                                   String dateType,
                                   Date dateFrom,
                                   Date dateTo)throws ProjectDataAccessException
   {
      ProjectDAO pdao = daof.getProjectDAO(DAOFactory.DONE);

      dateFrom.setHours(0);
      dateFrom.setMinutes(0);
      dateFrom.setSeconds(0);
      long from = dateFrom.getTime();

      dateTo.setHours(23);
      dateTo.setMinutes(59);
      dateTo.setSeconds(59);
      long to = dateTo.getTime();

      String[] keys = SMUtility.splitByToken(keywords, ";" , false);
      String[] conditions = null;

      if(0<keys.length)
         conditions = new String[keys.length];
      else
         conditions = new String[1];

      conditions[0] = "(" + dateType + ">" + from + " AND " + dateType + "<" + to + ")";

      if(0<keys.length)
      {
         if(keys[0].startsWith("%")||keys[0].endsWith("%"))
            conditions[0] += " AND " + searchBy + " LIKE '" + keys[0] + "'";
         else
            conditions[0] += " AND " + searchBy + "='" + keys[0] + "'";
      }


      for(int i=1; i<keys.length; i++)
      {
         conditions[i] = searchBy + " LIKE '" + keys[i] + "'";
         if(keys[0].startsWith("%")||keys[0].endsWith("%"))
            conditions[i] = searchBy + " LIKE '" + keys[i] + "'";
         else
            conditions[i] = searchBy + "='" + keys[0] + "'";
      }

      for(int i=0; i<conditions.length; i++)
         System.out.println("Search condition ("+i+") :"+ conditions[i]);

      return pdao.load(conditions, isAnd);
   }

   public Project getProject(String pid)throws ProjectDataAccessException
   {
      return getProject(DAOFactory.DONE, pid);
   }

   public Project[] getProjects(String[] pids)throws ProjectDataAccessException
   {
      return getProjects(DAOFactory.DONE, pids);
   }

   public Stage[] getStagesByType(String projectType) throws ProjectDataAccessException
   {
      return null;//getStagesByType(DAOFactory.DONE, projectType);
   }

   public Stage[] getStages(String pid) throws ProjectDataAccessException
   {
      return getStages(DAOFactory.DONE, pid);
   }

   public Stage getStage(String pid, String name) throws ProjectDataAccessException
   {
      return getStage(DAOFactory.DONE, pid, name);
   }

   public boolean createStage(Stage stage) throws ProjectDataAccessException
   {
      return createStage(DAOFactory.DONE, stage);
   }

   public boolean updateStage(Stage stage) throws ProjectDataAccessException
   {
      return updateStage(DAOFactory.DONE, stage);
   }

   public Area[] allAreas()
   {
      return areas;
   }

   public boolean containArea(String areaname)
   {
      return typeList.contains(areaname);
   }
   
   //=====
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
   //=====

   public static void main(String[] args)
   {
   }
}
