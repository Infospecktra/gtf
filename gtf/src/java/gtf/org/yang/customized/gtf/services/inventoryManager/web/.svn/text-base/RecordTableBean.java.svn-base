package org.yang.customized.gtf.services.inventoryManager.web;

import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.inventoryManager.InventoryManager;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.dataAccess.Record;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.inventoryManager.utility.Utility;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class RecordTableBean extends InventoryServiceBean
{
   static final long serialVersionUID = 4711396060977734913L;
   private Record currentRecord = null;
   private String altPage = null;

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private String projectName ="";
   public void setProjectName(String projectName) { this.projectName = projectName; }
   public String getProjectName(){return projectName;}

   private String labName ="";
   public void setLabName(String labName) { this.labName = labName; }
   public String  getLabName(){return labName;}

   private String domain ="";
   public void setDomain(String domain) { this.domain = domain; }
   public String  getDomain(){return domain;}

   private String investigator ="";
   public void setInvestigator(String investigator) { this.investigator = investigator; }
   public String  getInvestigator(){return investigator;}

   private String phone ="";
   public void setPhone(String phone) { this.phone = phone; }
   public String getPhone(){return phone;}

   private String plasmidBAC ="";
   public void setPlasmidBAC(String plasmidBAC) { this.plasmidBAC = plasmidBAC; }
   public String getPlasmidBAC(){return plasmidBAC;}

   private String note ="";
   public void setNote(String note) { this.note = note; }
   public String getNote(){return note;}

   private String dateType = "receivedDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType) { this.dateType = dateType; }

   private String billableType = "no";
   public String getBillableType(){return billableType;}
   public void setBillableType(String billableType){this.billableType = billableType;}

   private String number ="0";
   public void setNumber(String number) { this.number = number; }
   public String getNumber(){return number;}

   private String mouseHost ="";
   public void setMouseHost(String mouseHost) { this.mouseHost = mouseHost; }
   public String getMouseHost(){return mouseHost;}

   private String receivedDate ="----------";
   public void setReceivedDate(String receivedDate) { this.receivedDate = receivedDate; }
   public String getReceivedDate(){return receivedDate;}

   private String purifiedDate ="----------";
   public void setPurifiedDate(String purifiedDate) { this.purifiedDate = purifiedDate; }
   public String getPurifiedDate(){return purifiedDate;}

   private String injectedDate ="------------";
   public void setInjectedDate(String injectedDate) { this.injectedDate = injectedDate; }
   public String getInjectedDate(){return injectedDate;}

   private String closedDate ="----------";
   public void setClosedDate(String closedDate) { this.closedDate = closedDate; }
   public String getClosedDate(){return closedDate;}

   /**
    *  form set and get methods
    */

   public RecordTableBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==inventoryMgr)
         throw new Exception("Inventory manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[RecordTableBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[RecordTableBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload";
      }
      else
         return altPage;
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   /****************************************
    *             for builder              *
    ****************************************/

   public Record getCurrentRecord()
   {
      return currentRecord;
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
         id = "";
         projectName = "";
         labName = "";
         investigator = "";
         phone = "";
         plasmidBAC = "";
         number = "0";
         mouseHost = "";
         note = "";
         billableType = "no";
         Calendar cal = Calendar.getInstance();
         Date now = new Date(cal.getTime().getTime());
         receivedDate = Utility.dateFormat(now,"MM/dd/yyyy");
         purifiedDate = "----------";
         injectedDate = "----------";
         closedDate = "----------";
        
         if(null==currentRecord)
            currentRecord = new Record();
         currentRecord.setId("");
         currentRecord.setProjectName("");
         currentRecord.setNote("");
         currentRecord.setLabName("");
         currentRecord.setDomain(this.passport.getDomain());
         currentRecord.setInvestigator("");
         currentRecord.setPhone("");
         currentRecord.setNumber(0);
         currentRecord.setMouseHost("");
         currentRecord.setReceivedDate(now);
         currentRecord.setBillableType("no");

         altPage = null;

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void refreshRight() throws ProjectDataAccessException
   {
       altPage = "refreshRight";
   }

   public void edit() throws ProjectDataAccessException
   {
       altPage = "refreshRight";
   }

   public void showRemoveCtrlPanel()
   {
      altPage = "removeCtrPanel" ;
   }
   
   public void browse() throws ProjectDataAccessException
   {
       load();
       altPage = null;
   }

   public void createRecord() throws ProjectDataAccessException
   {
      try
      {
         Record[] allRecords = getAllRecords();
         for(int i=0;i<allRecords.length;i++)
         {
            String projectName2  = allRecords[i].getProjectName();
            if(projectName2.equals(this.projectName))
            {
                clear();
                id = "Sorry !! This project name exists or invalid.";
                edit();

                return ;
            }
         }

         //System.out.println("[RecordTableBean::create]---------->billableType="+billableType);

         currentRecord = new Record();
         currentRecord.setProjectName(projectName);
         currentRecord.setLabName(domain);
         currentRecord.setDomain(domain);
         currentRecord.setInvestigator(investigator);
         currentRecord.setPhone(phone);
         currentRecord.setNumber(new Integer(number).intValue());
         currentRecord.setMouseHost(mouseHost);
         currentRecord.setPlasmidBAC(plasmidBAC);
         currentRecord.setNote(note);
         currentRecord.setBillableType(billableType);
         String[] receivedDateArray  = null;
         String[] purifiedDateArray = null;
         String[] injectedDateArray  = null;
         String[] closedDateArray = null;
         
         if(null!=receivedDate&&!"".equals(receivedDate)&&!"----------".equals(receivedDate))
         {	 
            receivedDateArray  = Utility.parsingString("/",receivedDate);
            currentRecord.setReceivedDate(
                 Utility.stringDateConvertToDate(receivedDateArray[2],receivedDateArray[0],receivedDateArray[1]));
         }
         
         //System.out.println("[RecordTableBean::createRecord]-----currentRecord.getReceivedDate()="+currentRecord.getReceivedDate());
         if(null!=purifiedDate&&!"".equals(purifiedDate)&&!"----------".equals(purifiedDate))
         {	 
            purifiedDateArray = Utility.parsingString("/",purifiedDate);
            currentRecord.setPurifiedDate(
                 Utility.stringDateConvertToDate(purifiedDateArray[2],purifiedDateArray[0],purifiedDateArray[1]));
         }
         //System.out.println("[RecordTableBean::createRecord]-----currentRecord.getPurifiedDate()="+currentRecord.getPurifiedDate());
         
         if(null!=injectedDate&&!"".equals(injectedDate)&&!"----------".equals(injectedDate))
         {	 
            injectedDateArray  = Utility.parsingString("/",injectedDate);
            currentRecord.setInjectedDate(
                 Utility.stringDateConvertToDate(injectedDateArray[2],injectedDateArray[0],injectedDateArray[1]));
         }
         
         //System.out.println("[RecordTableBean::createRecord]-----currentRecord.getInjectedDate()="+currentRecord.getInjectedDate());
         //System.out.println("[RecordTableBean::createRecord]-----closedDate="+closedDate);
         if(null!=closedDate&&!"".equals(closedDate)&&!"----------".equals(closedDate))
         {	 
            closedDateArray = Utility.parsingString("/",closedDate);
            currentRecord.setClosedDate(
                 Utility.stringDateConvertToDate(closedDateArray[2],closedDateArray[0],closedDateArray[1]));
         }
         //System.out.println("[RecordTableBean::createRecord]-----currentRecord.getClosedDate()="+currentRecord.getClosedDate());

         id = inventoryMgr.createRecord(currentRecord);
         load();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
      edit();
   }

   public void load() throws ProjectDataAccessException
   {
      try
      {
         if(null!=id&&!"".equals(id))
             currentRecord = inventoryMgr.getRecord(id);
         if(currentRecord==null)
         {
            currentRecord = new Record();
            domain = this.passport.getDomain();
            Calendar cal = Calendar.getInstance();
            Date now = new Date(cal.getTime().getTime());
            receivedDate = Utility.dateFormat(now,"MM/dd/yyyy");
            purifiedDate = "----------";
            injectedDate = "----------";
            closedDate = "----------";

         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void updateRecord() throws ProjectDataAccessException
   {
      /*
       System.out.println("[RecordTableBean::update]--->rec_y="+y_receivedDate
                                                              +"|rec_m="+m_receivedDate
                                                              +"|rec_d="+d_receivedDate);
       System.out.println("[RecordTableBean::update]---pur_y="+ y_purifiedDate
                                                              +"|rec_m="+m_purifiedDate
                                                              +"|rec_d="+d_purifiedDate);
       System.out.println("[RecordTableBean::update]--->rec_y="+y_injectedDate
                                                              +"|rec_m="+m_injectedDate
                                                              +"|rec_d="+d_injectedDate);
       System.out.println("[RecordTableBean::update]--->cha_y="+y_closedDate
                                                              +"|cha_m="+m_closedDate
                                                              +"|cha_d="+d_closedDate);
      */
      try
      { 
         currentRecord = new Record();
         currentRecord.setId(id);
         currentRecord.setProjectName(projectName);
         currentRecord.setLabName(domain);
         currentRecord.setDomain(domain);
         currentRecord.setInvestigator(investigator);
         currentRecord.setPhone(phone);
         currentRecord.setNumber(new Integer(number).intValue());
         currentRecord.setMouseHost(mouseHost);
         currentRecord.setPlasmidBAC(plasmidBAC);
         currentRecord.setNote(note);
         currentRecord.setBillableType(billableType);
         String[] receivedDateArray  = null;
         String[] purifiedDateArray = null;
         String[] injectedDateArray  = null;
         String[] closedDateArray = null;
        if(null!=receivedDate&&!"".equals(receivedDate)&&!"n/a".equals(receivedDate)&&!"----------".equals(receivedDate))
        {	 
            receivedDateArray  = Utility.parsingString("/",receivedDate);
            currentRecord.setReceivedDate(
                 Utility.stringDateConvertToDate(receivedDateArray[2],receivedDateArray[0],receivedDateArray[1]));
         }
         
         if(null!=purifiedDate&&!"".equals(purifiedDate)&&!"n/a".equals(purifiedDate)&&!"----------".equals(purifiedDate))
         {	 
            purifiedDateArray = Utility.parsingString("/",purifiedDate);
            currentRecord.setPurifiedDate(
                 Utility.stringDateConvertToDate(purifiedDateArray[2],purifiedDateArray[0],purifiedDateArray[1]));
         }
         //System.out.println("[RecordTableBean::createRecord]-----injectedDate="+injectedDate);
         if(null!=injectedDate&&!"".equals(injectedDate)&&!"n/a".equals(injectedDate)&&!"----------".equals(injectedDate))
         {	 
            injectedDateArray  = Utility.parsingString("/",injectedDate);
            currentRecord.setInjectedDate(
                 Utility.stringDateConvertToDate(injectedDateArray[2],injectedDateArray[0],injectedDateArray[1]));
         }

         if(null!=closedDate&&!"".equals(closedDate)&&!"n/a".equals(closedDate)&&!"----------".equals(closedDate))
         {	 
            closedDateArray = Utility.parsingString("/",closedDate);
            currentRecord.setClosedDate(
                 Utility.stringDateConvertToDate(closedDateArray[2],closedDateArray[0],closedDateArray[1]));
         }
         inventoryMgr.updateRecord(currentRecord);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      edit();
   }

   public  void changeDate(){altPage=null;}

   private Record[]  getAllRecords() throws ProjectDataAccessException
   {
      Record[] allRecords = null;
      try
      {
         allRecords = inventoryMgr.getRecordsByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return allRecords;
   }

}