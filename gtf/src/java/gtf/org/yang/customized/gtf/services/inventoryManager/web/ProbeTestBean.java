package org.yang.customized.gtf.services.inventoryManager.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.sql.Date;
import java.util.Calendar;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.dataAccess.ProbeCase;
import org.yang.customized.gtf.services.inventoryManager.InventoryManager;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.util.DateFormatter;
import org.yang.services.dataAccess.Data;
import java.util.HashSet;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author:Celina Yang
 * @version 1.0
 */

public class ProbeTestBean extends InventoryServiceBean
{
   static final long serialVersionUID = 471329263977768902L;

   static public final String[] dateTypes = {"testDate","closedDate"};
   private String altPage = null;
   private ProbeCase currentProbeCase = null;
   private ProbeCase[] allProbeCases = null;
   private HashSet selectedProbeCaseIds = null;

   /**
    *  form set and get methods
    */
   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}
   
   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private String lastSortBy = null;
   private String sortBy = "projectName";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }

   private boolean isAllChecked = false;
   public boolean getIsAllChecked (){ return this.isAllChecked; }

   private String dateType = "testDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType){this.dateType = dateType;}

   private int availableProbeCaseSize =0;
   public int getAvailableProbeCaseSize(){return availableProbeCaseSize;}
   
   public ProbeTestBean()
   {
      super();
      selectedProbeCaseIds = new HashSet();

   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();
      String[] areas = passport.getServiceAreas("InventoryManager");

      if(null==inventoryMgr)
         throw new Exception("Inventory manager is null.");
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      altPage = null;
   }

   protected String altPage()
   {
      System.out.println("[ProbeCaseBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload";
      }
      else
         return altPage;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/
   /*
    * This method will query data by dateType
    */
   public ProbeCase[] getAvailableProbeCases() throws ProjectDataAccessException
   {
      try
      {
          loadProbeCases();
          return allProbeCases;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new ProbeCase[0];
   }

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public ProbeCase getCurrentProbeCase()
   {
      return currentProbeCase;
   }

   /****************************************
    *             All my actions           *
    ****************************************/
   
   public void clear()
   {
      allProbeCases = null;
      altPage = null;
   }

   public void changePage() throws ProjectDataAccessException
   {
      loadProbeCases();
      altPage = null;
   }
   
   public void enter() throws ProjectDataAccessException
   {
      loadProbeCases();
      page = "1";
      removeControl("probeTestSpreadSheet");
   }

   public void probeCaseTable() throws ProjectDataAccessException
   {
      altPage = "probeTestTable";
   }

   public void loadProbeCases() throws ProjectDataAccessException
   {
      try
      {
         allProbeCases = inventoryMgr.geProbeCasesByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
         setSelectedInfoForProbeCases();
         if(allProbeCases!=null)
            availableProbeCaseSize=allProbeCases.length;
         //System.out.println("[ProbeCaseBean::loadProbeCases]///----->availableProbeCaseSize="+availableProbeCaseSize);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void loadSpreadSheetData() throws ProjectDataAccessException
   {
      try
      {
         loadProbeCase();
         loadProbeCases();
         removeControl("probeTestSpreadSheet");
         altPage = "probeTestSpreadSheet";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void select() throws ProjectDataAccessException
   {
      if(null!=id&&!"".equals(id))
         selectedProbeCaseIds.add(id);
      //System.out.println("[ProbeCaseBean::select]--id="+id);
      loadProbeCases();
      altPage = null;
   }

   public void deselect() throws ProjectDataAccessException
   {
      //System.out.println("[ProbeCaseBean::deselect]--id="+id);
      deselect(id);
      altPage = null;
   }

   public void selectAll() throws ProjectDataAccessException
   {
      //System.out.println("[ProbeCaseBean::selectAll]----->enter selectAll!!");
      for (int i=0 ; i<allProbeCases.length;i++)
      {
          selectedProbeCaseIds.add(allProbeCases[i].getId()+"");
      }
      isAllChecked = true;
      altPage = "probeTestSpreadSheet";
   }

   public void deselectAll() throws ProjectDataAccessException
   {
      //System.out.println("[ProbeCaseBean::deselectAll]----->enter deselectAll!!");

      for (int i=0 ; i<allProbeCases.length;i++)
      {
          selectedProbeCaseIds.remove(allProbeCases[i].getId()+"");
      }
      isAllChecked = false;
      altPage = "probeTestSpreadSheet";

   }

   public void deleteProbeCases() throws ProjectDataAccessException
   {
       ArrayList temp = new ArrayList();
       try
       {
           inventoryMgr.removeProbeCases(selectedProbeCaseIds);
           for(int i=0;i<allProbeCases.length;i++)
           {
              if(!selectedProbeCaseIds.contains(allProbeCases[i].getId()))
                  temp.add(allProbeCases[i]);
           }
           allProbeCases = (ProbeCase[])(temp.toArray(new ProbeCase[]{}));
           selectedProbeCaseIds.clear();
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       altPage = null;
   }

   /*****************************
    *     My private methods    *
    *****************************/

   private void deselect(String id) throws ProjectDataAccessException
   {
      //System.out.println("[ProbeCaseBean]--deselect id="+id);
      if(null==id||"".equals(id))
         return;

      for (int i=0 ; i<allProbeCases.length;i++)
      {
          selectedProbeCaseIds.remove(id);
      }
      loadProbeCases();
   }

   private void setSelectedInfoForProbeCases()
   {
        String recordid = "";
    	if(allProbeCases==null)
    	   return;
        for(int i=0; i<allProbeCases.length; i++)
        {
       	   recordid = allProbeCases[i].getId()+"";
           
           Iterator it = selectedProbeCaseIds.iterator();
           if(selectedProbeCaseIds.contains(recordid))
              allProbeCases[i].setIsSelected(true);
           else
              allProbeCases[i].setIsSelected(false);
       }
       //System.out.println("[ProbeCaseBean::setSelectedInfoForProbeCases]--> selectedProbeCaseIds.size="+selectedProbeCaseIds.size());
   }
 
   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
         isAcending = !isAcending;
      else
         isAcending = true;
      lastSortBy = sortBy;
      altPage = "probeTestSpreadSheet";
   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadProbeCase() throws ProjectDataAccessException
   {
      try
      {
         currentProbeCase = inventoryMgr.getProbeCase(id);

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public boolean getNeedToRemoveProbeCase()
   {
      return (selectedProbeCaseIds.size()>0);

   }

   public int getAllProbeCasesSize()
   {
       if(allProbeCases==null||allProbeCases.length==0)
          return 0;
       else
          return allProbeCases.length;
   }
/*
   private String  time = "0";
   public String getTime(){ return time;}
   public void setTime(String time){this.time = time;}

   private String  isRefreshRight = "false";
   public String getIsRefreshRight(){ return isRefreshRight;}
   public void setIsRefreshRight(String isRefreshRight){this.isRefreshRight = isRefreshRight;}
*/
}