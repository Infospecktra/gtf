package org.yang.customized.gtf.services.inventoryManager.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;
import java.util.Calendar;
import java.util.StringTokenizer;
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
import org.yang.customized.gtf.services.inventoryManager.web.cache.GenericCacheManager;
import org.yang.customized.gtf.services.inventoryManager.web.cache.CacheEntry;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.dataAccess.Storage;
import org.yang.services.dataAccess.genericDAO.StorableList;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.inventoryManager.utility.Utility;
import org.yang.customized.gtf.services.inventoryManager.utility.ExcelReader;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
public class StorageBean extends InventoryServiceBean
{
   static final long serialVersionUID = 4512396162377134413L;

   private Storage currentStorage = null;
   private String altPage = null;

   private String id = "";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}

   private String projectCode ="";
   public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
   public String getProjectCode(){return projectCode;}

   private String labName ="  Please select";
   public void setLabName(String labName){this.labName = labName;}
   public String  getLabName(){return labName;}
 
   private String cell ="";
   public void setCell(String cell) { this.cell = cell; }
   public String getCell(){return cell;}

   private String parentalLine ="";
   public void setParentalLine(String parentalLine) { this.parentalLine = parentalLine; }
   public String getParentalLine(){return parentalLine;}

   private String comment ="";
   public void setComment(String comment) { this.comment = comment; }
   public String getComment(){return comment;}

   private String medium = "";
   public String getMedium() {return medium;}
   public void setMedium(String medium) { this.medium = medium; }

   private String drugSelection ="";
   public void setDrugSelection(String drugSelection) { this.drugSelection = drugSelection; }
   public String getDrugSelection(){return drugSelection;}

   private String location ="";
   public void setLocation(String location) { this.location = location; }
   public String getLocation(){return location;}

   private String boxNumber ="0";
   public void setBoxNumber(String boxNumber) { this.boxNumber = boxNumber; }
   public String getBoxNumber(){return boxNumber;}

   private String rowColumn = "";
   public String getRowColumn(){return rowColumn;}
   public void setRowColumn(String rowColumn){this.rowColumn = rowColumn;}
   
   private String freezingDate ="";
   public void setFreezingDate(String freezingDate) { this.freezingDate = freezingDate; }
   public String getFreezingDate(){return freezingDate;}
   
   private String[] inputSearchKeys = null;
   public  String[] getInputSearchKeys(){ return inventoryMgr.getInputSearchKeys();}
     
   /**
    *  form set and get methods
    */

   public StorageBean()
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
         throw new Exception("Storage manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[StorageBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[StorageBean::altPage] My default page :" + this.defaultPage);
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

   public Storage getCurrentStorage()
   {
      return currentStorage;
   }

   public String[] getpassportDomains()
   {
      return passport.getDomains();
   }


   private String[] domainsFromPassport = null; 
   public String[] getDomainsFromPassport()
   {
   	return domainsFromPassport;
   }   
   
   public String[] getAllDomainNames()
   {
      ArrayList temp = new ArrayList();
      try
      {	
         domainsFromPassport = passport.getDomains();
         HashSet set = inventoryMgr.loadAllPersistentLabNames();
         for(int i=0; i<domainsFromPassport.length; i++)
         {
            	
            set.add(domainsFromPassport[i]);
         }
         Iterator it = set.iterator();
         ArrayList domainList = new ArrayList();
         Object aa = ""; 
         while(it.hasNext())
         {
            aa = it.next(); 	
            if(aa!=null)
               domainList.add((String)aa);	
         }	
         Collections.sort(domainList);
         
         //int size = domainList.size()+1;
         //System.out.println("[StorageBean::getAllDomains]---size="+size);
         temp.add("  Please select");
         String labName = "";
         Iterator it2 = domainList.iterator();
         while(it2.hasNext())
         {
      	    labName = (String)it2.next();
      	    if(!"".equals(labName)) 
               temp.add(labName);	
            //System.out.println("[StorageBean::getAllDomains]---labName="+labName);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	   	
      return (String[])temp.toArray(new String[]{});
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
         projectCode = "";
         labName = "";
         investigator = "";
         cell = "";
         parentalLine = "";
         medium = "";
         drugSelection = "";
         location = "";
         boxNumber = "0";
         rowColumn = "";
         comment = "";
         freezingDate = Utility.dateFormat(new Date((new java.util.Date()).getTime()),"MM/dd/yyyy");
         if(null==currentStorage)
            currentStorage = new Storage();
         currentStorage.setId("");
         currentStorage.setProjectCode("");
         currentStorage.setLabName(this.passport.getDomain());
         currentStorage.setInvestigator("");
         currentStorage.setCell("");
         currentStorage.setMedium("");
         currentStorage.setDrugSelection("");
         currentStorage.setLocation("");
         currentStorage.setBoxNumber(0);
         currentStorage.setRowColumn("");
         currentStorage.setParentalLine("");
         currentStorage.setComment("");
         currentStorage.setFreezingDate(new Date(new java.util.Date().getTime()));
         altPage = null;

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }


   public void edit() throws ProjectDataAccessException
   {
      //System.out.println("[StorageBean::edit]---->id="+id);      
      load();
      rawDataInputType="inputDataByGUI";
      altPage = "refreshRight";
   }

   public void showRemoveCtrlPanel()
   {
      altPage = "removeCtrPanel" ;
   }
   
   public void browse() throws ProjectDataAccessException
   {
      //System.out.println("[StorageBean::browse]////////////////////---->id="+id);      
       load();
       altPage = null;
   }

   public void createStorage() throws ProjectDataAccessException
   {  
      try
      {
         Storage[] allStorages = inventoryMgr.getAllStorages();
         inventoryMgr.addStoragesToCache(InventoryManager.CACHEKEY_PRE_ALL_DATA,allStorages);
         
         currentStorage = new Storage();
         currentStorage.setProjectCode(projectCode);
         if(null==labName||"  Please select".equals(labName))
             labName = "";
         currentStorage.setLabName(labName.toUpperCase());
         //currentStorage.setDomain(this.passport.getDomain());
         if(null==investigator||"  Please select".equals(investigator))
        	 investigator = "";
         currentStorage.setInvestigator(investigator.toUpperCase());
         currentStorage.setCell(cell);
         currentStorage.setMedium(medium);
         currentStorage.setParentalLine(parentalLine);
         currentStorage.setDrugSelection(drugSelection);
         currentStorage.setLocation(location);
         currentStorage.setRowColumn(rowColumn);
         currentStorage.setBoxNumber(new Integer(boxNumber).intValue());
         currentStorage.setComment(comment);
         String[] dateArray = Utility.parsingDateString("/",freezingDate);
         if(dateArray[0]==null||"".equals(dateArray[0]))
            dateArray[0] = "01";
         if(dateArray[1]==null||"".equals(dateArray[1]))
            dateArray[1] = "01";
         currentStorage.setFreezingDate(Utility.stringDateConvertToDate(dateArray[2],dateArray[0],dateArray[1]));
         id = inventoryMgr.createStorage(currentStorage,"GUI");
         load();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
      edit();
   }
   
   public String insertStorageToDB(Storage storage,String fileName)
   {
   	
      if(storage==null)
         return null;	
            
      try
      {
         inventoryMgr.createStorage(storage,fileName);
         //System.out.println("[StorageBean::insertStorageToDB]----->create (storageId,fileName) = ("+id+","+fileName+")");
      }	 	
      catch(Exception e)
      { 
         e.printStackTrace();    
      }
      return id;
   }
   
  
   public void load() throws ProjectDataAccessException
   {
      System.out.println("[StorageBean::load]----->id ="+id);

      try
      {
         if(null!=id&&!"".equals(id))
         { 
             currentStorage = inventoryMgr.getStorage(id);
         }

         if(currentStorage==null)
         {	
            currentStorage = new Storage();
            labName = "  Please select";
         }
         //System.out.println("[StorageBean::load]xxx----->currentStorage.getLabName()="+currentStorage.getLabName());
         //System.out.println("[StorageBean::load]xxx----->currentStorage.getInvestigator()="+currentStorage.getInvestigator());
         
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void updateStorage() throws ProjectDataAccessException
   {  
      try
      { 
         currentStorage = new Storage();
         currentStorage.setProjectCode(projectCode);
         if(null==labName||"  Please select".equals(labName))
            labName = "";
         currentStorage.setLabName(labName.toUpperCase());
         //System.out.println("[StorageBean::updateStorage]------->labName="+labName);
         if(null==investigator||"  Please select".equals(investigator))
        	 investigator = "";
         currentStorage.setInvestigator(investigator.toUpperCase());
         currentStorage.setCell(cell);
         currentStorage.setMedium(medium);
         currentStorage.setParentalLine(parentalLine);
         currentStorage.setDrugSelection(drugSelection);
         currentStorage.setLocation(location);
         currentStorage.setBoxNumber(new Integer(boxNumber).intValue());
         currentStorage.setRowColumn(rowColumn);
         currentStorage.setComment(comment);
         
         String[] dateArray = Utility.parsingDateString("/",freezingDate);
         if(dateArray[0]==null||"".equals(dateArray[0]))
            dateArray[0] = "01";
         if(dateArray[1]==null||"".equals(dateArray[1]))
            dateArray[1] = "01";
         String sourceId = inventoryMgr.removeStorageForUpdateID(id);
         System.out.println("[StorageBean::updateStorage]------->updated data's  sourceId="+sourceId);
         currentStorage.setFreezingDate(Utility.stringDateConvertToDate(dateArray[2],dateArray[0],dateArray[1]));//y.m.d
         String newid = InventoryManager.createStorageId(currentStorage);
         System.out.println("[StorageBean::updateStorage]------->id="+id);
         currentStorage.setId(newid);
         inventoryMgr.createStorage(currentStorage,sourceId);
         Storage[] storages = inventoryMgr.fetchStoragesFromCache(InventoryManager.CACHEKEY_PRE_FILE_DATA);
         for(int i=0;i<storages.length;i++)
         {
            String targetId = storages[i].getId();
            if(id.equals(targetId))
                storages[i] = currentStorage; 	
         }	
         id = newid;
         projectCode = currentStorage.getProjectCode();
         labName = currentStorage.getLabName();
         //System.out.println("[StorageBean::updateStorage]------->currentStorage.getLabName()="+currentStorage.getLabName());
         investigator = currentStorage.getInvestigator();
         //System.out.println("[StorageBean::updateStorage]------->currentStorage.getInvestigator()="+currentStorage.getInvestigator());
         cell = currentStorage.getCell();
         parentalLine = currentStorage.getParentalLine();
         medium = currentStorage.getMedium();
         drugSelection = currentStorage.getDrugSelection();
         location = currentStorage.getLocation();
         boxNumber = currentStorage.getBoxNumber()+"";
         rowColumn = currentStorage.getRowColumn();
         comment = currentStorage.getComment();
         freezingDate = Utility.dateFormat(currentStorage.getFreezingDate(),"MM/dd/yyyy");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      edit();
   }

   public  void changeDate(){ altPage=null; }

   private String  isRefreshRight = "false";
   public String getIsRefreshRight(){ return isRefreshRight;}
   public void setIsRefreshRight(String isRefreshRight){this.isRefreshRight = isRefreshRight;}

 //----------
   public void changePage() throws ProjectDataAccessException
   {  
      System.out.println("[StorageBean::changePage]----->enter this scope!  rawDataInputType="+rawDataInputType);
      load();
      if("inputDataByGUI".equals(rawDataInputType))
      {
      	 clear();
         altPage = "refreshRight";
      }
      else
         altPage = "refreshRight";
      System.out.println("[StorageBean::changePage]QQ----->altPage="+altPage);
   }
   
   private String rawDataInputType="inputDataByExcel";
   public String getRawDataInputType(){return rawDataInputType;}
   public void setRawDataInputType(String rawDataInputType){this.rawDataInputType=rawDataInputType;}

   private String doKeepUploadFileMasterTable = "no";
   public String getDoKeepUploadFileMasterTable(){return doKeepUploadFileMasterTable;}
   public void setDoKeepUploadFileMasterTable(String doKeepUploadFileMasterTable){this.doKeepUploadFileMasterTable=doKeepUploadFileMasterTable;}

   private String fileUpload = ""; 
   public String  getFileUpload(){return fileUpload;}
   public void setFileUpload(String fileUpload){this.fileUpload=fileUpload;}  
   
   private String uploadedExcelFile = "";
   public String getUploadedExcelFile(){return uploadedExcelFile;}
   public void setUploadedExcelFile(String s){uploadedExcelFile=s;}
   
   public  void goUploadServlet()
   {       
      //System.out.println("[StorageBan::goUploadServlet]---->enter this scope!!");
      setUploadedFormSetting("enctype=\"multipart/form-data\"  action=\"/wf/fileUpload/fileUpload.servlet\"" );    	
      altPage = "inputDataFromFiles";	
   }
   
   public void toInputDataFromExcelFiles() throws ProjectDataAccessException
   {  
      altPage = "inputDataFromFiles";
   }   
   
   private boolean isUploadCompleted  = false;
   public boolean getIsUploadCompleted(){return isUploadCompleted;}  
   public void setIsUploadCompleted(boolean isUploadCompleted){this.isUploadCompleted= isUploadCompleted;}  
 
   private String uploadedFormSetting = "";
   public void setUploadedFormSetting(String uploadedFormSetting){this.uploadedFormSetting=uploadedFormSetting;}
   public String getUploadedFormSetting(){return uploadedFormSetting;}
     
   private int totalDataSize = 0;
   public int  getTotalDataSize(){return totalDataSize;}
   public void setTotalDataSize(int totalDataSize){this.totalDataSize=totalDataSize;}  
 
   private String investigator = "  Please select";
   public String getInvestigator(){return investigator;}
   public void setInvestigator(String investigator){this.investigator=investigator;}
 
   private String[] investigatorsInSelectedLab = null;
   public String[] getInvestigatorsInSelectedLab()
   {
   	if(investigatorsInSelectedLab==null)
   	  investigatorsInSelectedLab = new String[0];
   	return investigatorsInSelectedLab;
   }
   
   private String[] investigators = null;
   public String[] getInvestigators(){return investigators;}
   public String[] loadInvestigatorsByDomain(String labName)
   {
      ArrayList temp = new ArrayList();
      boolean isValidDomain = false;
      try
      {
      	 //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---labName="+labName);
      	 
      	 for (int ii=0; ii<domainsFromPassport.length; ii++)
      	 {
      	 //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---domainsFromPassport["+ii+"]="+domainsFromPassport[ii]);
      	 //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---labName="+labName);
      	    if(domainsFromPassport[ii].equalsIgnoreCase(labName))
      	       isValidDomain = true;	
      	 }
      	 //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---isValidDomain="+isValidDomain);
      	 	
      	 if(!isValidDomain) 
      	 {
            investigatorsInSelectedLab = new String[1];
            investigatorsInSelectedLab[0] = "  Please select";
      	 }
      	 else
      	 {
            String[] t =  passport.getDomainUserNames(labName); //from passport
      	    investigatorsInSelectedLab = new String[t.length+1];
      	    investigatorsInSelectedLab[0] = "  Please select";
      	    for(int x=1;x<investigatorsInSelectedLab.length;x++)
      	    {
      	      investigatorsInSelectedLab[x] = t[x-1];	
      	    }	
      	    //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---investigatorsInSelectedLab.length="+investigatorsInSelectedLab.length);
      	 }	
      		 
         HashSet set = inventoryMgr.loadAllPersistentInvestigators(labName);//from database
      	 //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---set.size()="+set.size());
         if(set==null)
            set = new HashSet();

         for(int i=0; i<investigatorsInSelectedLab.length; i++)
         {
            set.add(investigatorsInSelectedLab[i]);
         }
         
         Iterator it = set.iterator();
         ArrayList investigatorList = new ArrayList();
         String st = "";
         while(it.hasNext())
         {
            st =(String)it.next();	
            if(!"".equals(st))	
               investigatorList.add(st);	
         }	
         Collections.sort(investigatorList);
         
         int size = investigatorList.size()+1;
         //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]---size="+size);
         
         Iterator it3 = investigatorList.iterator(); 
         investigators = new String[investigatorList.size()];
         
         for(int j=0;j<investigators.length;j++)
         { 
            investigators[j]=(String)it3.next();	
            //System.out.println("[StorageMasterTableBean::loadInvestigatorsByDomain]--investigators["+j+"]="+investigators[j]);	
         }	  	
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }		   	
      return investigators;
   }
    
 
   public String[] getOptionStyles(String[] domainsFromPassport,String[] labs)  // 
   {
      if(labs==null)
      	labs = new String[0];
      
      if(domainsFromPassport==null)
        domainsFromPassport = new String[0];
      String[] styles = new String[labs.length];	

      try
      {
         for(int i=0;i<styles.length;i++) //the length = labs
         {
           for(int j=0;j<domainsFromPassport.length;j++)
           {	
              if(labs[i].equalsIgnoreCase(domainsFromPassport[j])||labs[i].equalsIgnoreCase("  Please select"))	
                 styles[i] = "style1";
           }      	
           
           if(null==styles[i]||"".equals(styles[i]))
              styles[i] = "style2";
         }	
      }	
      catch(Exception e)
      {
         e.printStackTrace();	
      }		
      //System.out.println("----->.styles.length="+styles.length);   	
      return styles;
   }

   public void updateDomain()
   {
      System.out.println("[StoragBean::updateDomain]----->labName="+labName);
      investigators=loadInvestigatorsByDomain(labName);
      altPage=null;
   }
   
   public void refresh()
   {
      altPage=null;
   }	  
    
}