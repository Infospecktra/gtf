package org.yang.customized.gtf.services.scheduleManager.web;

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

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ProjectInformationBean extends ScheduleBean
{
   static final long serialVersionUID = 4711296382979764913L;

   private Project currentProject = null;

   private String type ="";
   public void setType(String type) { this.type = type; }
   public String  getType(){return type;}

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private	String name ="";
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private	String accountId ="";
   public void setAccountId(String accountId) { this.accountId = accountId; }
   public String getAccountId() { return accountId; }

   private String protocolNumber = "";
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

      if(null==scheduleMgr)
         throw new Exception("User manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
      if(null!=currentProject)
      {
         currentProject.setAction(Project.UPDATE);
      }
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
      return passport.getServiceAreas("ScheduleManager");
   }

   public String[][] getAvailableProjectTypes()
   {
      try
      {
         String[][] typeNames = new String[2][];
         ProjectFactory factory = ProjectFactory.getFactory();
         String[] types = passport.getServiceAreas("ScheduleManager");
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
      //System.out.println("===================================================> clear, domain = " + domain + ", type = " + type);
      try
      {
         if("".equals(domain)||null==domain)
         {
            domain = passport.getDomain();
            investigator = passport.getUsername();
         }

         if("".equals(type))
         {
            String[] types = passport.getServiceAreas("ScheduleManager");
            if(null!=types&&0!=types.length)
               type = types[0];
         }

         currentProject = ProjectFactory.getFactory().createProject(type);
         currentProject.setDomain(domain);
         currentProject.setAccountId(((GTFDomain)passport.getDomain(domain)).getAccountId());
         currentProject.setProtocolNumber("");//passport.getMyDomain().getProtocolNumber());
         currentProject.setInvestigator(investigator);
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
         currentProject.setAction(Project.CREATE);
         currentProject.setName(name);
         currentProject.setAccountId(accountId);
         currentProject.setProtocolNumber(protocolNumber);
         currentProject.setDescription(description);
         currentProject.setSerialNumber(serialNumber);
         currentProject.setInvestigator(investigator);
         currentProject.setDomain(domain);
         currentProject.setDatasMap(passport.getUsername(), datas);

         //String accountId = currentProject.getAccountId();

         //String protocolNumber = currentProject.getProtocolNumber();
         if (null==protocolNumber||"".equals(protocolNumber))
         {
            setState(ERR);
            setMsg("Create domain error: no valid protocol number.");
            return;
         }

         if (null==accountId||"".equals(accountId))
         {
            setState(ERR);
            setMsg("Create domain error: no valid account ID.");
            return;
         }

         if(!currentProject.isDataQualified())
         {
            System.out.println("Data is not qualified!");
            setState(ERR);
            this.setMsg("Some required field is empty, Please submit it again.");
            return;
         }

         //*** save result to database
         if (!scheduleMgr.createProject(currentProject))
         {
            setState(ERR);
            setMsg("Create user error: database update error.");
            return;
         }

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
      currentProject = scheduleMgr.getProject(id);
      if (null==currentProject)
         throw new ProjectDataAccessException("Project is null : " + id);

      String accountId = currentProject.getAccountId();
      if(null==accountId||"".equals(accountId))
         currentProject.setAccountId(((GTFDomain)passport.getMyDomain()).getAccountId());
      String protocolNumber = currentProject.getProtocolNumber();
      if(null==protocolNumber||"".equals(protocolNumber))
         currentProject.setProtocolNumber(((GTFDomain)passport.getMyDomain()).getProtocolNumber());
      currentProject.setAction(Project.UPDATE);
   }
   
   public void copy() throws ProjectDataAccessException
   {
      //System.out.println("===================================================> copy");
      
	  try
      {
		 load(); 
         currentProject.setAction(Project.CREATE);
         currentProject.setName(name+"_copy");
         //String accountId = currentProject.getAccountId();
         if (null==accountId||"".equals(accountId))
         {
            setState(ERR);
            setMsg("Create domain error: no valid account id.");
            return;
         }

         //String protocolNumber = currentProject.getProtocolNumber();
         if (null==protocolNumber||"".equals(protocolNumber))
         {
            setState(ERR);
            setMsg("Create domain error: no valid protocol number.");
            return;
         }

         if (null==accountId||"".equals(accountId))
         {
            setState(ERR);
            setMsg("Create domain error: no valid account ID.");
            return;
         }

         if(!currentProject.isDataQualified())
         {
            System.out.println("Data is not qualified!");
            setState(ERR);
            this.setMsg("Some required field is empty, Please submit it again.");
            return;
         }

         //*** save result to database
         if (!scheduleMgr.createProject(currentProject))
         {
            setState(ERR);
            setMsg("Create user error: database update error.");
            return;
         }

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
   
   public void update() throws ProjectDataAccessException
   {
      try
      {
         currentProject.setName(name);
         currentProject.setAccountId(accountId);
         currentProject.setProtocolNumber(protocolNumber);
         currentProject.setDescription(description);
         currentProject.setSerialNumber(serialNumber);
         currentProject.setDatasMap(passport.getUsername(), datas);
         if(!currentProject.isDataQualified())
         {
            load();
            System.out.println("Data is not qualified!");
            this.setState(ERR);
            this.setMsg("Some required field is empty, Please submit it again.");
            return;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!scheduleMgr.updateProject(currentProject))
      {
         setState(ERR);
         setMsg("Update project error: database error.");
         return;
      }
      isReload = true;
   }

   public void remove() throws ProjectDataAccessException
   {
      //System.out.println("===================================================> remove");
      if (null==scheduleMgr.removeProject(id))
      {
         setState(ERR);
         setMsg("Remove user error: project ID does NOT exist.");
         return;
      }
      clear();
   }
}