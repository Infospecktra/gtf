package org.yang.customized.gtf.services.inventoryManager.web;

import java.util.ArrayList;
import java.util.Iterator;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.dataAccess.Record;
import org.yang.customized.gtf.services.dataAccess.Storage;
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

public class InventoryBean extends InventoryServiceBean
{
   static final long serialVersionUID = 4711295382977768902L;

   static public final String[] dateTypes = {"receivedDate","purifiedDate","injectedDate","closedDate"};
   private String altPage = null;
   private Record currentRecord = null;
   private Record[] allRecords = null;
   private HashSet selectedRecordIds = null;

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

   private String dateType = "receivedDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType) { this.dateType = dateType; }


   private int availableRecordSize =0;
   public int getAvailableRecordSize(){return availableRecordSize;}
   
   public InventoryBean()
   {
      super();
      selectedRecordIds = new HashSet();

   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();
      String[] areas = passport.getServiceAreas("InventoryManager");

      if(null==inventoryMgr)
         throw new Exception("Record manager is null.");
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      altPage = null;
   }

   protected String altPage()
   {
      System.out.println("[InventoryBean::altPage] Do alt page :" + isReload);
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
   public Record[] getAvailableRecords() throws ProjectDataAccessException
   {
      try
      {
          loadRecords();
          return allRecords;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new Record[0];
   }

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public Record getCurrentRecord()
   {
      return currentRecord;
   }

   /****************************************
    *             All my actions           *
    ****************************************/
   
   public void clear()
   {  
      String r_service = passport.getRuntimeProperty("serviceType");
      if(null!=r_service&&!"".equals(r_service))
         serviceType = r_service;
      allRecords = null;
      altPage = null;
   }

   public void enterService()
   {
      allRecords = null;
      altPage ="changeService";
   }
   
   private String completeLoad="no";
   public String getCompleteLoad(){return completeLoad;}
   public void setCompleteLoad(String completeLoad){this.completeLoad=completeLoad;}
   
   private Storage[] reloadedStorages = null;
   public  Storage[] getReloadedStorages(){return reloadedStorages;} 
   public void reloadStorageMasterTable()
   {
      try
      {
      	  reloadedStorages = inventoryMgr.loadStoragesByLocationBoxNumberOrRowColumn(id);
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
      altPage ="changeService";
   }
   
   public void changePage() throws ProjectDataAccessException
   {
      loadRecords();
      altPage = null;
   }
   
   public void enter() throws ProjectDataAccessException
   {
      loadRecords();
      page = "1";
      removeControl("inventorySpreadSheet");
   }

   public void recordTable() throws ProjectDataAccessException
   {
      altPage = "recordTable";
   }

   public void loadRecords() throws ProjectDataAccessException
   {
      try
      {
        
         allRecords = inventoryMgr.getRecordsByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
         setSelectedInfoForRecords();
         if(allRecords!=null)
            availableRecordSize=allRecords.length;
         //System.out.println("[InventoryBean::loadRecords]///----->availableRecordSize="+availableRecordSize);
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
         loadRecord();
         loadRecords();
         removeControl("inventorySpreadSheet");
         altPage = "spreadSheet";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void select() throws ProjectDataAccessException
   {
      if(null!=id&&!"".equals(id))
         selectedRecordIds.add(id);
      //System.out.println("[InventoryBean::select]--id="+id);
      loadRecords();
      altPage = null;
   }

   public void deselect() throws ProjectDataAccessException
   {
      //System.out.println("[InventoryBean::deselect]--id="+id);
      deselect(id);
      altPage = null;
   }

   public void selectAll() throws ProjectDataAccessException
   {
      for (int i=0 ; i<allRecords.length;i++)
      {
          selectedRecordIds.add(allRecords[i].getId()+"");
      }
      isAllChecked = true;
      altPage = "spreadSheet";
   }

   public void deselectAll() throws ProjectDataAccessException
   {
      for (int i=0 ; i<allRecords.length;i++)
      {
          selectedRecordIds.remove(allRecords[i].getId()+"");
      }
      isAllChecked = false;
      altPage = "spreadSheet";

   }

   public void deleteRecords() throws ProjectDataAccessException
   {
       ArrayList temp = new ArrayList();
       try
       {
           inventoryMgr.removeRecords(selectedRecordIds);
           for(int i=0;i<allRecords.length;i++)
           {
              if(!selectedRecordIds.contains(allRecords[i].getId()))
                  temp.add(allRecords[i]);
           }
           allRecords = (Record[])(temp.toArray(new Record[]{}));
           selectedRecordIds.clear();
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       //loadRecords();
       
       altPage = null;
   }

   /*****************************
    *     My private methods    *
    *****************************/

   private void deselect(String id) throws ProjectDataAccessException
   {
      //System.out.println("[InventoryBean]--deselect id="+id);
      if(null==id||"".equals(id))
         return;

      for (int i=0 ; i<allRecords.length;i++)
      {
          selectedRecordIds.remove(id);
      }
      loadRecords();
   }

   private void setSelectedInfoForRecords()
   {
        String recordid = "";
    	if(allRecords==null)
    	   return;
        for(int i=0; i<allRecords.length; i++)
        {
       	   recordid = allRecords[i].getId()+"";
           
           Iterator it = selectedRecordIds.iterator();
           if(selectedRecordIds.contains(recordid))
              allRecords[i].setIsSelected(true);
           else
              allRecords[i].setIsSelected(false);
       }
       //System.out.println("[InventoryBean::setSelectedInfoForRecords]--> selectedRecordIds.size="+selectedRecordIds.size());
   }
 
   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
         isAcending = !isAcending;
      else
         isAcending = true;
      lastSortBy = sortBy;
      altPage = "spreadSheet";
   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadRecord() throws ProjectDataAccessException
   {
      try
      {
         currentRecord = inventoryMgr.getRecord(id);

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public boolean getNeedToRemoveRecord()
   {
      return (selectedRecordIds.size()>0);

   }

   public int getAllRecordsSize()
   {
       if(allRecords==null||allRecords.length==0)
          return 0;
       else
          return allRecords.length;
   }

   private String  time = "0";
   public String getTime(){ return time;}
   public void setTime(String time){this.time = time;}

   private String  isRefreshRight = "false";
   public String getIsRefreshRight(){ return isRefreshRight;}
   public void setIsRefreshRight(String isRefreshRight){this.isRefreshRight = isRefreshRight;}

   private String plasmidBAC ="plasmid";
   public void setPlasmidBAC(String plasmidBAC) { this.plasmidBAC = plasmidBAC; }
   public String getPlasmidBAC(){return plasmidBAC;}

   private String  serviceType = "";
   public String getServiceType(){ return serviceType;}
   public void setServiceType(String serviceType){this.serviceType = serviceType;}

}