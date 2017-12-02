package org.yang.customized.gtf.services.inventoryManager.web;

import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.dataAccess.Storage;
import org.yang.customized.gtf.services.inventoryManager.InventoryManager;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.inventoryManager.web.cache.GenericCacheManager;
import org.yang.customized.gtf.services.inventoryManager.web.cache.CacheEntry;
import org.yang.util.DateFormatter;
import org.yang.services.dataAccess.Data;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author:Celina Yang
 * @version 1.0
 */

public class StorageMasterTableBean extends InventoryServiceBean
{
   static final long serialVersionUID = 491539223357728912L;

   private String altPage = null;
   private Storage currentStorage = null;
   private Storage[] allStorages = null;
   private HashSet selectedStorageIds = null;

   /**
    *  form set and get methods
    */
   public Storage []  getAllStorages(){return allStorages;}
    
   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}
   
   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private String lastSortBy = null;
   private String sortBy = "cell";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }

   private boolean isAllChecked = false;
   public boolean getIsAllChecked (){ return this.isAllChecked; }

   private String searchKeys = "";
   public void setSearchKeys(String searchKeys) { this.searchKeys = searchKeys; }
   public String getSearchKeys() { return searchKeys; }

   private boolean selectLoadCacheData = false;
   public void setSelectLoadCacheData(boolean selectLoadCacheData){this.selectLoadCacheData=selectLoadCacheData;}
   public boolean getSelectLoadCacheData(){return selectLoadCacheData;}
   
   private String labName = "  Please select";
   public String getLabName(){return labName;}
   public void setLabName(String labName){this.labName=labName;}
   
   private String investigator = "  Please select";
   public String getInvestigator(){return investigator;}
   public void setInvestigator(String investigator){this.investigator=investigator;}

   
   private String[] investigators = null;
   public String[] getAvailableInvestigators(){return investigators;} 

   private int availableStorageSize =0;
   public int getAvailableStorageSize(){return availableStorageSize;}
   
   private String[] inputSearchKeys = null;
   public  String[] getInputSearchKeys(){ return inventoryMgr.getInputSearchKeys();}
   
   public StorageMasterTableBean()
   {
      super();
      selectedStorageIds = new HashSet();

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
      //System.out.println("[StorageMasterTableBean::altPage] Do alt page :" + isReload);
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
   public Storage[] getAvailableStorages() throws ProjectDataAccessException
   {
      try
      {
      	  String lastActiontype = this.getLastActiontype();
      	  /*
      	  System.out.println("[StorageMAsterBean::getAvailableStages]////-----lastActiontype="+lastActiontype);  
          System.out.println("[StorageMAsterBean::getAvailableStages]////-----searchKeys="+searchKeys);
          System.out.println("[StorageMAsterBean::getAvailableStages]////-----uploadedExcelFile="+uploadedExcelFile);
          */
          if(null==uploadedExcelFile||
      	     "".equals(uploadedExcelFile))  
          {  	 
             if(!"search".equals(this.getLastActiontype())&&
                "".equals(searchKeys)) // after 1. click upload file size link ,then keep file data on Master table.   
             {
             	
                //System.out.println("[StorageMAsterBean::getAvailableStages]//// 111-----uploadedFile=='' && act!=search && searchKeys=''  !!");
                if("".equals(this.getLastActiontype())||"clear".equals(this.getLastActiontype()))
                {
                   //System.out.println("[StorageMasterTable::getAvailableStorages]------>actionType=clear!!");  	            
                   //System.out.println("[StorageMasterTable::getAvailableStorages]------>inventoryMgr="+inventoryMgr);
                   if(inventoryMgr!=null)
                      inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
                   allStorages = new Storage[0];
                   labName="";
                   investigator="";
                }
        	    else
        	    {
                  //System.out.println("[StorageMasterTable::getAvailableStorages]------>get cache of  data from uploaded excel file.");  	            
                  allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
                  setSelectedInfoForStorages(); 
                  //System.out.println("[StorageMasterTable::getAvailableStorages]--get the data of pre-uploaded file from cache -->allStorages.length="+allStorages.length);
                }
             }
             else  // for  click search button 
             {   
          //System.out.println("[StorageMAsterBean::getAvailableStages]//// 222-----uploadedFile=='' (CLICK SEARCH btn!!)");
                loadStorages();
             }
          }
          else  // for clicking  button Create for uploading file , then click "Total data size is xxx." link         
          {  
             //System.out.println("[StorageMAsterBean::getAvailableStages]//// 333-----uploadedFile!='' (CLICK data size link !!)");
             searchKeys = "";
             uploadedExcelFile= "";  
          }     
          
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      
      return allStorages;
   }

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public Storage getCurrentStorage()
   {
      return currentStorage;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void search()
   {
      //System.out.println("[StorageMasterTableBean::search]--->searchKeys="+searchKeys);
      try
      {
      	 uploadedExcelFile="";
         loadStorages();
         altPage = null;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void clear()
   {
      try
      {
         getAvailableStorages();
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }
       
      altPage = null;
   }

   public void changePage() throws ProjectDataAccessException
   {
      loadStorages();
      altPage = null;
   }
   
   public void enter() throws ProjectDataAccessException
   {
      loadStorages();
      page = "1";
      removeControl("storageMasterTable");
   }
   
   public void setAllStorages(Storage[] storages){allStorages=storages;}
   
   public void  setReloadStorages(Storage[] storages)
   {
      try
      {
      	allStorages = storages;
        inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
        inventoryMgr.addStoragesToCache(InventoryManager.CACHEKEY_PRE_FILE_DATA,allStorages);///
        if(allStorages==null)
           allStorages = new Storage[0];
        //System.out.println("[StorageMasterTableBean::setReloadStorages]///----->allStorages.length="+allStorages.length);
      	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }
   }
   
   public void  reloadStorages()
   {
      altPage = "storageMasterTable";	
   }	
    
   public void loadStorages() throws ProjectDataAccessException
   {
      try
      {
         //System.out.println("[StorageMasterTableBean::loadStorages]///----->searchKeys="+searchKeys);
         //System.out.println("[StorageMasterTableBean::loadStorages]///----->labName="+labName);
         //System.out.println("[StorageMasterTableBean::loadStorages]///----->investigator="+investigator);

         allStorages = inventoryMgr.getStoragesBySearchKey(labName,investigator,searchKeys);
         setSelectedInfoForStorages();
         if(allStorages!=null)
             availableStorageSize=allStorages.length;
         inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
         inventoryMgr.addStoragesToCache(InventoryManager.CACHEKEY_PRE_FILE_DATA,allStorages);///
         //System.out.println("[StorageMasterTableBean::loadStorages]///----->availableStorageSize="+availableStorageSize);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   private String uploadedExcelFile = "";
   public String getUploadedExcelFile(){return uploadedExcelFile;} 
   public void setUploadedExcelFile(String uploadedExcelFile){this.uploadedExcelFile=uploadedExcelFile;}
    
   public void loadStoragesByFileName() throws ProjectDataAccessException
   {
      try
      {    
         //System.out.println("[StorageMasterTableBean::loadStoragesByFileName]///----->uploadedExcelFile="+uploadedExcelFile);
         allStorages = inventoryMgr.getStoragesBySourceFileName(uploadedExcelFile, false); // refresh new data to Cache in inventoryMgr
         setSelectedInfoForStorages();
         if(allStorages!=null)
            availableStorageSize=allStorages.length;
         //System.out.println("[StorageMasterTableBean::loadStoragesByFileName]///----->availableStorageSize="+availableStorageSize);
        
         altPage = null; 
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public void loadStoragesByFileName(String excelFile) throws ProjectDataAccessException
   {
      try
      {    
         //System.out.println("[StorageMasterTableBean::loadStoragesByFileName]///----->excelFile="+excelFile);
         if(null!=excelFile&&!"".equals(excelFile))
         {
            allStorages = inventoryMgr.getStoragesBySourceFileName(excelFile, false);
            setSelectedInfoForStorages();
            if(allStorages!=null)
               availableStorageSize=allStorages.length;
         }
         else
            allStorages = new Storage[0];
         //System.out.println("[StorageMasterTableBean::loadStoragesByFileName]///----->availableStorageSize="+availableStorageSize);
         removeControl("storageMasterTable");
         altPage="storageMasterTable";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }   
  
   public void toRightFrameService() throws ProjectDataAccessException
   {
        altPage = null;
   }

   public void toUploadeFileMasterTable() throws ProjectDataAccessException
   {
       altPage = "storageMasterTable";
   }

   public void  loadCacheData() throws ProjectDataAccessException
   {  
      try
      {
         loadStorage();
         allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
         //System.out.println("[StorageMasterTable::loadCacheData]--get the data of pre-uploaded file from cache -->allStorages.length="+allStorages.length);
         removeControl("storageMasterTable");
         getAvailableStorages(); 
         altPage = "storageMasterTable";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }


   public void loadSpreadSheetData()  throws ProjectDataAccessException
   {  
      try
      {
         loadStorage();
         if(!selectLoadCacheData)
            loadStorages();
         else
             allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
       	 
         removeControl("storageMasterTable");
         getAvailableStorages(); 
         altPage = "storageMasterTable";
         
      }
      catch(Exception e)
      {
         e.printStackTrace(); 
      }
   }

   public void select() throws ProjectDataAccessException
   {
      if(null!=id&&!"".equals(id))
         selectedStorageIds.add(id);
      //System.out.println("[StorageMasterTableBean::select]--id="+id);
      ///loadStorages();
      allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
      altPage = null;
   }

   public void deselect() throws ProjectDataAccessException
   {
      //System.out.println("[StorageMasterTableBean::deselect]--id="+id);
      deselect(id);
      altPage = null;
   }

   public void selectAll() throws ProjectDataAccessException
   {
      allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);///
	      
      for (int i=0 ; i<allStorages.length;i++)
      {
          selectedStorageIds.add(allStorages[i].getId()+"");
      }
      isAllChecked = true;
      setSelectedInfoForStorages();
      altPage = "storageMasterTable";
   }

   public void deselectAll() throws ProjectDataAccessException
   {
      for (int i=0 ; i<allStorages.length;i++)
      {
          selectedStorageIds.remove(allStorages[i].getId()+"");
      }
      Storage[] storages= inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
      ArrayList sList = new ArrayList();
      for(int i=0;i<storages.length;i++)
      {
         if(!id.equals(storages[i].getId()))
             sList.add(storages[i]); 
      }	      
      
      Storage[] ss=(Storage[])sList.toArray(new Storage[0]);
      inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
      inventoryMgr.addStoragesToCache(InventoryManager.CACHEKEY_PRE_FILE_DATA,ss);
      setSelectedInfoForStorages();
      isAllChecked = false;
      altPage = "storageMasterTable";

   }

   public void deleteStorages() throws ProjectDataAccessException
   {
       ArrayList sList = new ArrayList();
       ArrayList temp = new ArrayList();
       Storage[] storages= inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
      
       try
       {
           //System.out.println("[StorageMasterTableBean::deleteStorages]----->selectedStorageIds.size()="+selectedStorageIds.size());
           inventoryMgr.removeStorages(selectedStorageIds);
           for(int i=0;i<allStorages.length;i++)
           {
              if(!selectedStorageIds.contains(allStorages[i].getId()))
                  temp.add(allStorages[i]);
           }
           
           for(int i=0;i<storages.length;i++)
           {
              if(!id.equals(storages[i].getId()))
                  sList.add(storages[i]); 
           }
           
           Storage[] ss = (Storage[])sList.toArray(new Storage[0]);
           inventoryMgr.removeStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
           inventoryMgr.addStoragesToCache(InventoryManager.CACHEKEY_PRE_FILE_DATA,ss);
           allStorages = (Storage[])(temp.toArray(new Storage[]{}));
           selectedStorageIds.clear();
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
      //System.out.println("[StorageMasterTableBean]--deselect id="+id);
      if(null==id||"".equals(id))
         return;
      allStorages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);///
      
      for (int i=0 ; i<allStorages.length;i++)
      {
          selectedStorageIds.remove(id);
      }
   }

   private void setSelectedInfoForStorages()
   {
        String storageid = "";
    	if(allStorages==null)
    	   return;
        for(int i=0; i<allStorages.length; i++)
        {
       	   storageid = allStorages[i].getId()+"";
           Iterator it = selectedStorageIds.iterator();
           if(selectedStorageIds.contains(storageid))
              allStorages[i].setIsSelected(true);
           else
              allStorages[i].setIsSelected(false);
       }
       //System.out.println("[StorageMasterTableBean::setSelectedInfoForStorages]--> selectedStorageIds.size="+selectedStorageIds.size());
   }

   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
         isAcending = !isAcending;
      else
         isAcending = true;
      lastSortBy = sortBy;
      altPage = "storageMasterTable";

   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadStorage() throws ProjectDataAccessException
   {
      try
      {
         currentStorage = inventoryMgr.getStorage(id);

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
  
   public boolean getNeedToRemoveStorage()
   {
      return (selectedStorageIds.size()>0);
   }

   public int getAllStoragesSize()
   {
       if(allStorages==null||allStorages.length==0)
          return 0;
       else
          return allStorages.length;
   }

   private String uploadedFormSetting = "";
   public void setUploadedFormSetting(String uploadedFormSetting){this.uploadedFormSetting=uploadedFormSetting;}
   public String getUploadedFormSetting(){return uploadedFormSetting;}

  
}