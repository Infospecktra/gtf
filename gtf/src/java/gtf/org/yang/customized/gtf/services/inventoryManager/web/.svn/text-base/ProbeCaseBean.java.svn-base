package org.yang.customized.gtf.services.inventoryManager.web;

import java.sql.Date;
import java.util.Calendar;
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
import org.yang.customized.gtf.services.dataAccess.ProbeCase;
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

public class ProbeCaseBean extends InventoryServiceBean
{
   static final long serialVersionUID = 4511396260177734913L;
   private ProbeCase currentProbeCase = null;
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

   private String probeName ="";
   public void setProbeName(String probeName) { this.probeName = probeName; }
   public String getProbeName(){return probeName;}

   private String note ="";
   public void setNote(String note) { this.note = note; }
   public String getNote(){return note;}

   private String dateType = "receivedDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType) { this.dateType = dateType; }

   private String probeNumber ="0";
   public void setProbeNumber(String probeNumber) { this.probeNumber = probeNumber; }
   public String getProbeNumber(){return probeNumber;}

   private String testBy ="";
   public void setTestBy(String testBy) { this.testBy = testBy; }
   public String getTestBy(){return testBy;}

   private String result ="";
   public void setResult(String result) { this.result = result; }
   public String getResult(){return result;}

   private String billableType = "no";
   public String getBillableType(){return billableType;}
   public void setBillableType(String billableType){this.billableType = billableType;}

   private String resultType = "n/a";
   public String getResultType(){return resultType;}
   public void setResultType(String resultType){this.resultType = resultType;}


   private String testDate ="------";
   public void setTestDate(String testDate) { this.testDate = testDate; }
   public String getTestDate(){return testDate;}

   private String closedDate ="------";
   public void setClosedDate(String closedDate) { this.closedDate = closedDate; }
   public String getClosedDate(){return closedDate;}
 
   /**
    *  form set and get methods
    */

   public ProbeCaseBean()
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
         throw new Exception("ProbeCase manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ProbeCaseBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[ProbeCaseBean::altPage] My default page :" + this.defaultPage);
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

   public ProbeCase getCurrentProbeCase()
   {
      return currentProbeCase;
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
         probeName = "";
         probeNumber = "0";
         testBy = "";
         result = "";
         note = "";
         billableType = "no";
         testDate = "------";
         closedDate = "------";
         
         if(null==currentProbeCase)
            currentProbeCase = new ProbeCase();
         currentProbeCase.setId("");
         currentProbeCase.setProjectName("");
         currentProbeCase.setNote("");
         currentProbeCase.setLabName("");
         currentProbeCase.setDomain(this.passport.getDomain());
         currentProbeCase.setInvestigator("");//this.passport.getUsername());
         currentProbeCase.setPhone("");
         currentProbeCase.setProbeNumber(0);
         currentProbeCase.setTestBy("");
         currentProbeCase.setResult("");
         currentProbeCase.setBillableType("no");
         currentProbeCase.setResultType("n/a");
         //currentProbeCase.setTestDate(now);
         //currentProbeCase.setClosedDate(now);

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

   public void createProbeCase() throws ProjectDataAccessException
   {  
      try
      {
         ProbeCase[] allProbeCases = getAllProbeCases();
         for(int i=0;i<allProbeCases.length;i++)
         {
            String projectName2  = allProbeCases[i].getProjectName();
            if(projectName2.equals(this.projectName))
            {
                clear();
                id = "Sorry !! This project name exists or invalid.";
                edit();
                return ;
            }
         }
         currentProbeCase = new ProbeCase();
         currentProbeCase.setProjectName(projectName);
         currentProbeCase.setLabName(domain);
         currentProbeCase.setDomain(domain);
         currentProbeCase.setInvestigator(investigator);
         currentProbeCase.setPhone(phone);
         currentProbeCase.setProbeNumber(new Integer(probeNumber).intValue());
         currentProbeCase.setProbeName(probeName);
         currentProbeCase.setTestBy(testBy);
         currentProbeCase.setResult(result);
         currentProbeCase.setBillableType(billableType);
         currentProbeCase.setResultType(resultType);
         currentProbeCase.setNote(note);
         String[] testDateArray  = null;
         String[] closedDateArray = null;
         //System.out.println("[ProbeCaseBean::createProbeCase]-----closedDate="+closedDate);
         if(null!=testDate&&!"".equals(testDate)&&!"------".equals(testDate))
         {	 
            testDateArray  = Utility.parsingString("/",testDate);
            currentProbeCase.setTestDate(
                    Utility.stringDateConvertToDate(testDateArray[2],testDateArray[0],testDateArray[1]));
         }
         if(null!=closedDate&&!"".equals(closedDate)&&!"------".equals(closedDate))
         {	 
            closedDateArray = Utility.parsingString("/",closedDate);
            currentProbeCase.setClosedDate(
                    Utility.stringDateConvertToDate(closedDateArray[2],closedDateArray[0],closedDateArray[1]));
         }         
         id = inventoryMgr.createProbeCase(currentProbeCase);
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
             currentProbeCase = inventoryMgr.getProbeCase(id);
         if(currentProbeCase==null)
         {
            currentProbeCase = new ProbeCase();
            domain = this.passport.getDomain();
            Calendar cal = Calendar.getInstance();
            Date now = new Date(cal.getTime().getTime());
            testDate   = "------";
            closedDate = "------";
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void updateProbeCase() throws ProjectDataAccessException
   {  /*
       System.out.println("[ProbeCaseBean::update]--->rec_y="+y_testDate
                                                              +"|rec_m="+m_testDate
                                                              +"|rec_d="+d_testDate);
       System.out.println("[ProbeCaseBean::update]--->cha_y="+y_closedDate
                                                              +"|cha_m="+m_closedDate
                                                              +"|cha_d="+d_closedDate);
      */
      try
      { 
         currentProbeCase = new ProbeCase();
         currentProbeCase.setId(id);
         currentProbeCase.setProjectName(projectName);
         currentProbeCase.setLabName(domain);
         currentProbeCase.setDomain(domain);
         currentProbeCase.setInvestigator(investigator);
         currentProbeCase.setPhone(phone);
         currentProbeCase.setProbeNumber(new Integer(probeNumber).intValue());
         currentProbeCase.setProbeName(probeName);
         currentProbeCase.setTestBy(testBy);
         currentProbeCase.setResult(result);
         currentProbeCase.setNote(note);
         currentProbeCase.setBillableType(billableType);
         currentProbeCase.setResultType(resultType);
         String[] testDateArray  = null;
         String[] closedDateArray = null;
         //System.out.println("[ProbeCaseBean::updateProbeCase]-----closedDate="+closedDate);
         if(null!=testDate&&!"".equals(testDate)&&!"n/a".equals(testDate)&&!"------".equals(testDate))
         {	 
             testDateArray  = Utility.parsingString("/",testDate);
        	 currentProbeCase.setTestDate(
                    Utility.stringDateConvertToDate(testDateArray[2],testDateArray[0],testDateArray[1]));
         }
         
         if(null!=closedDate&&!"".equals(closedDate)&&!"n/a".equals(closedDate)&&!"------".equals(closedDate))
         {	 
             closedDateArray = Utility.parsingString("/",closedDate);
        	 currentProbeCase.setClosedDate(
                    Utility.stringDateConvertToDate(closedDateArray[2],closedDateArray[0],closedDateArray[1]));
         }
         inventoryMgr.updateProbeCase(currentProbeCase);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      edit();
      //altPage = "serviceBody" ;
      
   }

   public  void changeDate(){ altPage=null; }

   private ProbeCase[]  getAllProbeCases() throws ProjectDataAccessException
   {
      ProbeCase[] allProbeCases = null;
      try
      {
         allProbeCases = inventoryMgr.geProbeCasesByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return allProbeCases;
   }
   private String  isRefreshRight = "false";
   public String getIsRefreshRight(){ return isRefreshRight;}
   public void setIsRefreshRight(String isRefreshRight){this.isRefreshRight = isRefreshRight;}

}