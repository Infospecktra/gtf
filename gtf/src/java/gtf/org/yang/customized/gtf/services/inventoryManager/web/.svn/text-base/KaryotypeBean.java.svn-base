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
import org.yang.customized.gtf.services.dataAccess.Karyotype;
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

public class KaryotypeBean extends InventoryServiceBean
{
   static final long serialVersionUID = 4511396263177234213L;
   private Karyotype currentKaryotype = null;
   private String altPage = null;

   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private String projectName ="";
   public void setProjectName(String projectName) { this.projectName = projectName; }
   public String getProjectName(){return projectName;}

   private String projectCode ="0";
   public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
   public String getProjectCode(){return projectCode;}

   private String labName ="";
   public void setLabName(String labName) { this.labName = labName; }
   public String  getLabName(){return labName;}

   private String domain ="";
   public void setDomain(String domain) { this.domain = domain; }
   public String  getDomain(){return domain;}

   private String investigator ="";
   public void setInvestigator(String investigator) { this.investigator = investigator; }
   public String  getInvestigator(){return investigator;}
  
   private String cellSource ="";
   public void setCellSource(String cellSource) { this.cellSource = cellSource; }
   public String getCellSource(){return cellSource;}
   
   private String parentalLine ="";
   public void setParentalLine(String parentalLine) { this.parentalLine = parentalLine; }
   public String getParentalLine(){return parentalLine;}

   private String note ="";
   public void setNote(String note) { this.note = note; }
   public String getNote(){return note;}

   private String dateType = "sentDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType) { this.dateType = dateType; }

   private String requestedBy ="";
   public void setRequestedBy(String requestedBy) { this.requestedBy = requestedBy; }
   public String getRequestedBy(){return requestedBy;}

   private String result ="";
   public void setResult(String result) { this.result = result; }
   public String getResult(){return result;}

   private String resultType = "n/a";
   public String getResultType(){return resultType;}
   public void setResultType(String resultType){this.resultType = resultType;}

   private String sentDate ="------";
   public void setSentDate(String sentDate) { this.sentDate = sentDate; }
   public String getSentDate(){return sentDate;}

   private String billing ="";
   public void setBilling(String billing) { this.billing = billing; }
   public String getBilling(){return billing;}
 
   /**
    *  form set and get methods
    */

   public KaryotypeBean()
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
         throw new Exception("Karyotype manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[KaryotypeBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[KaryotypeBean::altPage] My default page :" + this.defaultPage);
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

   public Karyotype getCurrentKaryotype()
   {
      return currentKaryotype;
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
         //phone = "";
         cellSource = "";
         parentalLine = "";
         projectCode = "";
         requestedBy = "";
         result = "";
         resultType = "n/a";
         note = "";
         //billableType = "no";
         sentDate = "------";
         billing = "";
         
         if(null==currentKaryotype)
            currentKaryotype = new Karyotype();
         currentKaryotype.setId("");
         currentKaryotype.setProjectName("");
         currentKaryotype.setNote("");
         currentKaryotype.setLabName("");
         currentKaryotype.setDomain(this.passport.getDomain());
         currentKaryotype.setInvestigator("");
         currentKaryotype.setCellSource("");
         //currentKaryotype.setPhone("");
         currentKaryotype.setProjectCode("");
         currentKaryotype.setRequestedBy("");
         currentKaryotype.setResult("");
         //currentKaryotype.setBillableType("no");
         currentKaryotype.setResultType("n/a");
         //currentKaryotype.setSentDate(now);
         currentKaryotype.setBilling("");

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

   public void createKaryotype() throws ProjectDataAccessException
   {  
      try
      {
         Karyotype[] allKaryotypes = getAllKaryotypes();
         for(int i=0;i<allKaryotypes.length;i++)
         {
            String projectName2  = allKaryotypes[i].getProjectName();
            if(projectName2.equals(this.projectName))
            {
                clear();
                id = "Sorry !! This project name exists or invalid.";
                edit();
                return ;
            }
         }
         currentKaryotype = new Karyotype();
         currentKaryotype.setProjectName(projectName);
         currentKaryotype.setLabName(domain);
         currentKaryotype.setDomain(domain);
         currentKaryotype.setInvestigator(investigator);
         currentKaryotype.setCellSource(cellSource);
         currentKaryotype.setBilling(billing);
         //currentKaryotype.setPhone(phone);
         currentKaryotype.setProjectCode(projectCode);
         currentKaryotype.setParentalLine(parentalLine);
         currentKaryotype.setRequestedBy(requestedBy);
         currentKaryotype.setResult(result);
         currentKaryotype.setResultType(resultType);
         currentKaryotype.setNote(note);
         String[] sentDateArray  = null;
         //System.out.println("[KaryotypeBean::createKaryotype]-----billing="+billing);
         if(null!=sentDate&&!"".equals(sentDate)&&!"------".equals(sentDate))
         {	 
            sentDateArray  = Utility.parsingString("/",sentDate);
            currentKaryotype.setSentDate(
                    Utility.stringDateConvertToDate(sentDateArray[2],sentDateArray[0],sentDateArray[1]));
         }
                 
         id = inventoryMgr.createKaryotype(currentKaryotype);
         clear();
         ///load();
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
             currentKaryotype = inventoryMgr.getKaryotype(id);
         if(currentKaryotype==null)
         {
            currentKaryotype = new Karyotype();
            domain = this.passport.getDomain();
            Calendar cal = Calendar.getInstance();
            Date now = new Date(cal.getTime().getTime());
            sentDate   = "------";
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void updateKaryotype() throws ProjectDataAccessException
   {  /*
       System.out.println("[KaryotypeBean::update]--->rec_y="+y_sentDate
                                                              +"|rec_m="+m_sentDate
                                                              +"|rec_d="+d_sentDate);
       System.out.println("[KaryotypeBean::update]--->cha_y="+y_billing
                                                              +"|cha_m="+m_billing
                                                              +"|cha_d="+d_billing);
      */
      try
      { 
         currentKaryotype = new Karyotype();
         currentKaryotype.setId(id);
         currentKaryotype.setProjectName(projectName);
         currentKaryotype.setLabName(domain);
         currentKaryotype.setDomain(domain);
         currentKaryotype.setCellSource(cellSource);
         currentKaryotype.setInvestigator(investigator);
         //currentKaryotype.setPhone(phone);
         currentKaryotype.setProjectCode(projectCode);
         currentKaryotype.setParentalLine(parentalLine);
         currentKaryotype.setRequestedBy(requestedBy);
         currentKaryotype.setResult(result);
         currentKaryotype.setNote(note);
         currentKaryotype.setBilling(billing);
         currentKaryotype.setResultType(resultType);
         String[] sentDateArray  = null;
         //System.out.println("[KaryotypeBean::updateKaryotype]-----billing="+billing);
         if(null!=sentDate&&!"".equals(sentDate)&&!"n/a".equals(sentDate)&&!"------".equals(sentDate))
         {	 
             sentDateArray  = Utility.parsingString("/",sentDate);
        	 currentKaryotype.setSentDate(
                    Utility.stringDateConvertToDate(sentDateArray[2],sentDateArray[0],sentDateArray[1]));
         }
         
         inventoryMgr.updateKaryotype(currentKaryotype);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      edit();
      
   }

   public  void changeDate(){ altPage=null; }

   private Karyotype[]  getAllKaryotypes() throws ProjectDataAccessException
   {
      Karyotype[] allKaryotypes = null;
      try
      {
         allKaryotypes = inventoryMgr.geKaryotypesByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return allKaryotypes;
   }
   private String  isRefreshRight = "false";
   public String getIsRefreshRight(){ return isRefreshRight;}
   public void setIsRefreshRight(String isRefreshRight){this.isRefreshRight = isRefreshRight;}

}