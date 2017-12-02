package org.yang.customized.gtf.services.tglScheduler.web;

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
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.domainManager.GTFDomain;
import org.yang.customized.gtf.services.scheduleManager.web.ScheduleBean;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import java.util.Calendar;
import org.yang.util.SMUtility;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ProjectInformationBean extends SchedulerBean
{
   static final long serialVersionUID = 4711296662979764913L;

   private Project currentProject = null;
   private Timetable[] currentTimetables = null;

   private final String type ="TGL";
   //public void setType(String type) { this.type = type; }
   public String  getType(){return type;}

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private	String name ="";
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private	String accountId ="N/A";
   public void setAccountId(String accountId) { this.accountId = accountId; }
   public String getAccountId() { return accountId; }

   private String protocolNumber = "N/A";
   public void setProtocolNumber(String protocolNumber) { this.protocolNumber = protocolNumber; }
   public String getProtocolNumber() { return protocolNumber; }

   private	String description ="";
   public void setDescription (String description) { this.description = description; }
   public String getDescription (){ return description; }

   private long serialNumber = 0;
   public void setSerialNumber(long serialNumber) { this.serialNumber = serialNumber; }
   public long getSerialNumber() { return serialNumber; }

   private String domain = "";
   public String getDomain() { return domain; }
   public void setDomain(String domain) { this.domain = domain; }

   private String labHead = "";
   public String getLabHead() { return labHead; }

   private String investigator = "";
   public String getInvestigator() { return investigator; }
   public void setInvestigator(String investigator) { this.investigator = investigator; }

   private HashMap eDate = null;
   public void setEDate(HashMap eDate) { this.eDate = eDate; }
   public HashMap getEDate() { return eDate; }

   private HashMap tDate = null;
   public void setTDate(HashMap tDate) { this.tDate = tDate; }
   public HashMap getTDate() { return tDate; }

   private HashMap datas = null;
   public void setDatas(HashMap datas) { this.datas = datas; }
   public HashMap getDatas() { return datas; }

   /**
    *  form set and get methods
    */

   public ProjectInformationBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==scheduler)
         throw new Exception("User manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
      if(null!=datas)
         datas.clear();
      //if(null!=currentProject)
      //{
      //   currentProject.setAction(Project.UPDATE);
      //}
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ProjectInformationBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[ProjectInformationBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return null;
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   /****************************************
    *             for builder              *
    ****************************************/

   public Project getCurrentProject()
   {
      return currentProject;
   }

   public String[] getAllAvailableProjectTypes()
   {
      return passport.getServiceAreas("Scheduler");
   }

   public String[][] getAvailableProjectTypes()
   {
      try
      {
         String[][] typeNames = new String[2][];
         ProjectFactory factory = ProjectFactory.getFactory();
         String[] types = passport.getServiceAreas("Scheduler");
         ArrayList typeList = new ArrayList();
         ArrayList nameList = new ArrayList();
         for(int i=0; i<types.length; i++)
         {
            typeList.add(types[i]);
            nameList.add(factory.getProjectTemplate(types[i]).getDisplayTypeName());
         }

         typeNames[0] = (String[])typeList.toArray(new String[]{});
         typeNames[1] = (String[])nameList.toArray(new String[]{});

         return typeNames;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return new String[1][0];
      }
   }

   public String[] getAllDomainNames()
   {
      return passport.getDomains();
   }

   public String[] getAllInvestigatorNames(String domainName)
   {
      return passport.getDomainUserNames(domainName);
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void clear()
   {
      try
      {
         if("".equals(domain)||null==domain)
         {
            domain = passport.getDomain();
            investigator = passport.getUsername();
         }

         currentProject = ProjectFactory.getFactory().createProject(type);
         currentProject.setDomain(domain);
         currentProject.setAccountId(((GTFDomain)passport.getDomain(domain)).getAccountId());
         currentProject.setProtocolNumber("0001");
         currentProject.setInvestigator(investigator);

         //if(null!=datas&&0!=datas.size())
         //   currentProject.setDatasMap(passport.getUsername(), datas);

         // Tell build to show Create button
         currentProject.setAction(Project.CREATE);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void create() throws ProjectDataAccessException
   {
      //System.out.println("===================================================> create");
      try
      {
         ArrayList timetableList = new ArrayList();
         Timetable timetable = null;

         updateCommonData();

         String type = currentProject.getDataValue("type");
         String sOnly = currentProject.getDataValue("surrogateOnly");
         if("B-Injection".equals(type)||
            "P-Injection".equals(type)||
            //"Cryopreservation".equals(type)||
            "Aggregation".equals(type))
         {
            if(!"yes".equals(sOnly))
            {
               if(!currentProject.isDataQualified())
               {
                  System.out.println("Data is not qualified!");
                  setState(ERR);
                  this.setMsg("Some required field is empty, Please submit it again.");
                  return;
               }

               timetable = scheduler.getTimetableTemplate(currentProject.getType() + ":" +
                                                                         type + ":Donor");
               timetable.setDueDate(stringToDate(currentProject.getDataValue("experimentDate")));
               timetableList.add(timetable);
            }
            else
            {
               if(!currentProject.isDataQualified("surrogateMother"))
               {
                  System.out.println("Data is not qualified!");
                  setState(ERR);
                  this.setMsg("Some required field is empty, Please submit it again.");
                  return;
               }
            }

            timetable = scheduler.getTimetableTemplate(currentProject.getType() + ":" +
                                                                  type + ":Surrogate" +
                                   currentProject.getDataValue("surrogateMotherType"));
            timetable.setDueDate(stringToDate(currentProject.getDataValue("transferDate")));
            timetableList.add(timetable);
         }
         else
         {
            if(!currentProject.isDataQualified("donorMother"))
            {
               System.out.println("Data is not qualified!");
               setState(ERR);
               this.setMsg("Some required field is empty, Please submit it again.");
               return;
            }

            timetable = scheduler.getTimetableTemplate(currentProject.getType() + ":" +
                                                                      type + ":Donor");
            timetable.setDueDate(stringToDate(currentProject.getDataValue("experimentDate")));
            timetableList.add(timetable);
         }

         currentProject.setStartDate((new Date()).getTime());
         //*** save result to database
         if (!scheduler.createProject(currentProject))
         {
            setState(ERR);
            setMsg("Create user error: database update error.");
            return;
         }

         createTimetables(currentProject, (Timetable[])timetableList.toArray(new Timetable[]{}));

         // Tell build to show 'Update' button
         currentProject.setAction(Project.UPDATE);

         isReload = true;
         id = currentProject.getId();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
   }

   public void load() throws ProjectDataAccessException
   {
      currentProject = scheduler.getProject(id);
      if (null==currentProject)
         throw new ProjectDataAccessException("Project is null : " + id);

      String accountId = currentProject.getAccountId();
      if(null==accountId||"".equals(accountId))
         currentProject.setAccountId(((GTFDomain)passport.getMyDomain()).getAccountId());
      String protocolNumber = currentProject.getProtocolNumber();
      if(null==protocolNumber||"".equals(protocolNumber))
         currentProject.setProtocolNumber(((GTFDomain)passport.getMyDomain()).getProtocolNumber());

      // Tell build to show 'Update' button
      currentProject.setAction(Project.UPDATE);
   }

   public void update() throws ProjectDataAccessException
   {
      Timetable[] timetables = null;
      try
      {
         updateCommonData();
         timetables = scheduler.getTimetableByProject(currentProject.getId());

         String type = currentProject.getDataValue("type");
         String sOnly = currentProject.getDataValue("surrogateOnly");
         if("B-Injection".equals(type)||
            "P-Injection".equals(type)||
            //"Cryopreservation".equals(type)||
            "Aggregation".equals(type))
         {
            if(!"yes".equals(sOnly))
            {
               if(!currentProject.isDataQualified())
               {
                  System.out.println("Data is not qualified!");
                  setState(ERR);
                  this.setMsg("Some required field is empty, Please submit it again.");
                  return;
               }
               timetables[0].setDueDate(stringToDate(currentProject.getDataValue("experimentDate")));
               timetables[1].setDueDate(stringToDate(currentProject.getDataValue("transferDate")));

            }
            else
            {
               if(!currentProject.isDataQualified("surrogateMother"))
               {
                  System.out.println("Data is not qualified!");
                  setState(ERR);
                  this.setMsg("Some required field is empty, Please submit it again.");
                  return;
               }
               timetables[0].setDueDate(stringToDate(currentProject.getDataValue("transferDate")));
            }
         }
         else
         {
            if(!currentProject.isDataQualified("donorMother"))
            {
               System.out.println("Data is not qualified!");
               setState(ERR);
               this.setMsg("Some required field is empty, Please submit it again.");
               return;
            }
            timetables[0].setDueDate(stringToDate(currentProject.getDataValue("experimentDate")));
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!scheduler.updateProject(currentProject))
      {
         setState(ERR);
         setMsg("Update project error: database error.");
         return;
      }

      updateTimetables(timetables);

      // Tell build to show Update button
      currentProject.setAction(Project.UPDATE);
      isReload = true;
   }

   public void onChange()
   {
      updateCommonData();
   }

   public void remove() throws ProjectDataAccessException
   {
      if (null==scheduler.removeProject(id))
      {
         setState(ERR);
         setMsg("Remove user error: project ID does NOT exist.");
         return;
      }
      clear();
      isReload = true;
   }

   public void createTimetables(Project project, Timetable[] timetables) throws ProjectDataAccessException
   {
      try
      {
         String id = project.getId();
         for(int i=0; i<timetables.length; i++)
         {
            timetables[i].setId(id+":"+i);
            timetables[i].setProjectId(id);
            Schedule[] schedules = scheduler.getSchedulesTemplate(timetables[i].getProjectType());
            for(int j=0; j<schedules.length; j++)
            {
               schedules[j].setId(timetables[i].getId()+":"+schedules[j].getName());
               schedules[j].setTimetableId(timetables[i].getId());
               schedules[j].setDueDate(scheduleDueDate(timetables[i].getDueDate(), schedules[j].getDay()));
               scheduler.createSchedule(schedules[j]);
            }
            scheduler.createTimetable(timetables[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
   }

   public void updateTimetables(Timetable[] timetables) throws ProjectDataAccessException
   {
      try
      {
         if(null==timetables)
            return;
         for(int i=0; i<timetables.length; i++)
         {
            Schedule[] schedules = scheduler.getSchedules(timetables[i].getId());
            for(int j=0; j<schedules.length; j++)
            {
               schedules[j].setDueDate(scheduleDueDate(timetables[i].getDueDate(), schedules[j].getDay()));
               scheduler.updateSchedule(schedules[j]);
            }
            scheduler.updateTimetable(timetables[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
   }

   private java.sql.Date stringToDate(String dt)
   {
      Calendar calendar = Calendar.getInstance();
      String[] dates = SMUtility.splitByToken(dt, "/", true);
      int year = Integer.parseInt(dates[0]);
      int month = Integer.parseInt(dates[1]);
      int date = Integer.parseInt(dates[2]);
      calendar.set(year, month, date);
      return new java.sql.Date(calendar.getTimeInMillis());
   }

   private java.sql.Date scheduleDueDate(java.sql.Date timetableDueDate, int offset)
   {
      return (new java.sql.Date(timetableDueDate.getTime() + ((long)offset)*86400*1000));
   }

   private void updateCommonData()
   {
      try
      {
         if(null!=eDate)
            datas.put("experimentDate", eDate.get("year")+"/"+eDate.get("month")+"/"+eDate.get("date"));
         if(null!=tDate)
            datas.put("transferDate", tDate.get("year")+"/"+tDate.get("month")+"/"+tDate.get("date"));
         if(null!=domain)
            currentProject.setDomain(domain);
         if(null!=name)
            currentProject.setName(name);
         if(0!=serialNumber)
            currentProject.setSerialNumber(serialNumber);
         if(null!=accountId)
            currentProject.setAccountId(accountId);
         if(null!=protocolNumber)
            currentProject.setProtocolNumber(protocolNumber);
         if(null!=description)
            currentProject.setDescription(description);
         if(null!=investigator)
            currentProject.setInvestigator(investigator);
         currentProject.setDatasMap(datas);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}