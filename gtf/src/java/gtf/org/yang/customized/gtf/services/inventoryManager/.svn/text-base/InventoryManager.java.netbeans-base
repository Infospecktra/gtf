/**
 * @ author:  Huei Wen
 * @ version: 1.0  June 20, 2001
 * @ company: 
 */
package org.yang.customized.gtf.services.inventoryManager;

import org.yang.customized.gtf.services.inventoryManager.web.cache.GenericCacheManager;
import org.yang.customized.gtf.services.inventoryManager.web.cache.CacheEntry;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.services.dataAccess.genericDAO.GenericDAO;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.customized.gtf.services.dataAccess.Record;
import org.yang.customized.gtf.services.dataAccess.ProbeCase;
import org.yang.customized.gtf.services.dataAccess.Karyotype;
import org.yang.customized.gtf.services.dataAccess.Storage;
import org.yang.customized.gtf.services.dataAccess.StorageSource;
import org.yang.services.dataAccess.genericDAO.StorableList;
import org.yang.services.servicemgr.Area;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.yang.services.servicemgr.Service;
import java.util.Collections;
import java.util.StringTokenizer;
import java.sql.Array;
import java.util.Calendar;
import java.sql.Date;
import java.util.*;

public class InventoryManager implements Service
{
   public static final String CACHEKEY_PRE_FILE_DATA = "preUploadedFileData";   	
   public static final String CACHEKEY_PRE_ALL_DATA = "preALLData";   	
   public static final String CACHEKEY_PRE_RELOAD_BY_ID_DATA = "preLoadByID";

   private GenericDAO gdao = null;
   private String domain = null;
   private String tableName1 = "ALL_inventory_pronuclearInj";
   private String tableName2 = "ALL_inventory_probeTest";
   private String tableName3 = "ALL_inventory_storages";
   private String tableName4 = "ALL_inventory_storageSource";
   private String tableName5 = "ALL_inventory_karyotypeRequest";
   
   private GenericCacheManager cacheManager = null;

   private ArrayList allDomains = null;

   public InventoryManager(){}
   /**
    *  inherit methods  from Service
    */

   protected String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return null; }

   /**
      *  each domain has one UserManager
      */
   public InventoryManager (String dname)
   {
      //super(domain);

      try
      {
      	  cacheManager= GenericCacheManager.getInstance();
         //create the tables.
          domain = dname;
          allDomains = new ArrayList();
          gdao = new GenericDAO(RDBMSFactory.getInstance().getDataRDBMS());

          StorageSource storageSource = new StorageSource();
          storageSource.setTablename(tableName4);
          gdao.createTable(storageSource);
          
          Record record = new Record();
          record.setTablename(tableName1);
          gdao.createTable(record);

          ProbeCase probeCase = new ProbeCase();
          probeCase.setTablename(tableName2);
          gdao.createTable(probeCase);

          Karyotype karyotype = new Karyotype();
          karyotype.setTablename(tableName5);
          gdao.createTable(karyotype);

/*
          probeCase.setId(generateId());
          probeCase.setProjectName("HSKD9");
          probeCase.setProbeName("probeCase1");
          probeCase.setInvestigator("TOME");
          probeCase.setLabName("Papavaslion");
          probeCase.setPhone("1-212-3875372");
          probeCase.setProbeNumber(1);
          probeCase.setTestBy("Salon");
          probeCase.setResult("Result1");
          probeCase.setNote("");
          probeCase.setBillableType("no");
          probeCase.setResultType("good");

*/
          Storage storage = new Storage();
          storage.setTablename(tableName3);
          gdao.createTable(storage);
/*
          storage.setId(generateId());
          storage.setCell("PHN2#166");
          storage.setParentalLine("E14");
          storage.setInvestigator("Wolfgang");
          storage.setLabName("Wolfgang");
          storage.setProjectCode("2004");
          storage.setMedium("ES");
          storage.setDrugSelection("G418");
          storage.setLocation("LN#D");
          storage.setComment("");
          storage.setBoxNumber(1);
          storage.setRowColumn("1/2");
          Calendar ca = Calendar.getInstance();
          ca.set(2010,6,5); //2010-7-5
          java.sql.Date d1 = new java.sql.Date(ca.getTime().getTime());
          storage.setFreezingDate(d1);
          storage.setComment("");

          createStorage(storage);
 
          Calendar ca = Calendar.getInstance();
          ca.set(2010,6,5); //2010-7-5
          Date d1 = new Date(ca.getTime().getTime());
          probeCase.setTestDate(d1);
          ca.set(2010,8,6); //2010-8-5
          Date d3 = new Date(ca.getTime().getTime());
          probeCase.setClosedDate(d3);
          createProbeCase(probeCase);

          ProbeCase probeCase2 = new ProbeCase();
          probeCase2.setId(generateId());
          probeCase2.setProjectName("J9KD9");
          probeCase2.setProbeName("D311B");
          probeCase2.setInvestigator("Julia");
          probeCase2.setLabName("Nussentzuig");
          probeCase2.setPhone("1-212-3874372");
          probeCase2.setProbeNumber(1);
          probeCase2.setNote("");
          probeCase2.setTestBy("Mary");
          probeCase2.setResult("result 2");
          probeCase2.setBillableType("no");
          probeCase2.setResultType("notGood");

          ca.set(2009,6,5); //2009-7-5
          Date dd1 = new Date(ca.getTime().getTime());
          probeCase2.setTestDate(dd1);
          ca.set(2010,9,16); //2010-8-5
          Date dd4 = new Date(ca.getTime().getTime());
          probeCase.setClosedDate(dd4);

          createProbeCase(probeCase2);

          record.setId(generateId());
          record.setProjectName("HSKD9");
          record.setPlasmidBAC("plasmid");
          record.setInvestigator("TOME");
          record.setLabName("Papavaslion");
          record.setPhone("1-212-3875372");
          record.setNumber(1);
          record.setMouseHost("C57B6");
          record.setNote("");
          record.setBillableType("yes");

          ca.set(2010,6,5); //2010-7-5
          Date d11 = new Date(ca.getTime().getTime());
          record.setReceivedDate(d11);
          ca.set(2010,7,5); //2010-8-5
          Date d12 = new Date(ca.getTime().getTime());
          record.setPurifiedDate(d12);
          ca.set(2010,8,6); //2010-8-5
          Date d13 = new Date(ca.getTime().getTime());
          record.setClosedDate(d13);

          createRecord(record);

          Record record2 = new Record();
          record2.setId(generateId());
          record2.setProjectName("J9KD9");
          record2.setPlasmidBAC("D311B");
          record2.setInvestigator("Julia");
          record2.setLabName("Nussentzuig");
          record2.setPhone("1-212-3874372");
          record2.setNumber(1);
          record2.setNote("");
          record2.setMouseHost("C09K6");
          record2.setBillableType("no");

          ca.set(2009,3,5); //2009-4-5
          Date dd11 = new Date(ca.getTime().getTime());
          record2.setReceivedDate(dd11);
          ca.set(2010,6,15); //2010-7-15
          Date dd12 = new Date(ca.getTime().getTime());
          record2.setPurifiedDate(dd12);
          ca.set(2010,8,5); //2010-9-5
          Date dd13 = new Date(ca.getTime().getTime());
          record2.setInjectedDate(dd13);
          ca.set(2010,9,16); //2010-8-5
          Date dd14 = new Date(ca.getTime().getTime());
          record.setClosedDate(dd14);
          createRecord(record2);
  */
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void initialize(Properties prop, Passport passport)
   {
      String[] domains = passport.getDomains();
      for(int i=0; i<domains.length; i++)
      {
         allDomains.add(domains[i]);
      }
      Collections.sort(allDomains);
   }

   public void destroy() {}

 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Record[] getRecordsByDateType(String dataType,String oneDayBeforeStartDate,String oneDayAfterEndDate) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Record[] records = null;
      try
      {
         StringBuffer sb = new StringBuffer(" (");
         sb.append(dataType).append("> '").append(oneDayBeforeStartDate).append("') and ('")
           .append(oneDayAfterEndDate).append("' > ").append(dataType).append(")");
         Record record = new Record();
         record.setTablename(tableName1);
         records = (Record[])gdao.load(record,new String[]{sb.toString()},false).toArray(new Record[]{});
         Calendar cat = Calendar.getInstance();
        
      }
      catch(Exception e)
      {
         e.getMessage();
      }
      
      if(records==null)
         return new Record[]{};

      return records;
   }


   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */

   public Record[] getRecords(String[] conditions, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Record record = new Record();
      record.setTablename(tableName1);
      return (Record[])gdao.load(record, conditions, isAnd).toArray(new Record[]{});
   }

 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */

   public Record[] getAllRecords() throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Record record = new Record();
      record.setTablename(tableName1);
      return (Record[])gdao.load(record).toArray(new Record[]{});
   }

   public Record getRecord(String recordid) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Record record1 = null;
      //System.out.println("[InventoryManager::getRecord]----->recordid="+recordid);
      if(recordid==null||"".equals(recordid))
         return new Record();
      try
      {
         Record record = new Record();
         record.setTablename(tableName1);
         record1 = ((Record[])gdao.load(record, "id="+recordid).toArray(new Record[]{}))[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return record1;
   }

    public void removeRecords(HashSet recordIds) throws ProjectDataAccessException
    {
       if(recordIds==null)
          throw new ProjectDataAccessException ("[RecordManager::removeRecords] recordIds are null." );
       Iterator iterator = recordIds.iterator();

       try
       {
       	  //remove task in record first.
          while(iterator.hasNext())
          {
             removeRecord((String)iterator.next());
          }
       }
       catch(Exception e)
       {
           //throw new ProjectDataAccessException ("[RecordManager::removeRecords]--Fail to remove tasks info in record("+recordId+")--"+ e.getMessage());
           e.printStackTrace();
       }
    }
   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeRecord(String id) throws org.yang.services.dbService.DataAccessException
   {
      boolean isSuccessful = false;
      try
      {
         Record record = new Record();
         record.setTablename(tableName1);
         isSuccessful = gdao.delete(record, "id='"+id+"'");
      }
      catch(Exception e)
      {
         e.getMessage();
      }

      return isSuccessful;
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public String createRecord(Record record) throws org.yang.services.dbService.DataAccessException
   {
      if(null==record)
         return "";
      record.setTablename(tableName1);
      String created_id = this.generateId();
      record.setId(created_id);
      gdao.insert(record);
      return created_id;
   }
   
 /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
 
   public boolean updateRecord(Record record) throws org.yang.services.dbService.DataAccessException
   {
      if(null==record)
         return false;
      record.setTablename(tableName1);
      return gdao.update(record);
   }

   /*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*/


 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public ProbeCase[] geProbeCasesByDateType(String dataType,String oneDayBeforeStartDate,String oneDayAfterEndDate) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      ProbeCase[] probeCases = null;
      try
      {
         StringBuffer sb = new StringBuffer(" (");
         sb.append(dataType).append("> '").append(oneDayBeforeStartDate).append("') and ('")
           .append(oneDayAfterEndDate).append("' > ").append(dataType).append(")");
         //System.out.println("[InventoryManager::getProbeCasesByDateType]---conditon="+sb.toString());
         ProbeCase probeCase = new ProbeCase();
         probeCase.setTablename(tableName2);
         probeCases = (ProbeCase[])gdao.load(probeCase,new String[]{sb.toString()},false).toArray(new ProbeCase[]{});
         Calendar cat = Calendar.getInstance();

      }
      catch(Exception e)
      {
         e.getMessage();
      }
      if(probeCases==null)
         return new ProbeCase[]{};

      return probeCases;
   }


   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */

   public ProbeCase[] getProbeCases(String[] conditions, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      ProbeCase probeCase = new ProbeCase();
      probeCase.setTablename(tableName2);
      return (ProbeCase[])gdao.load(probeCase, conditions, isAnd).toArray(new ProbeCase[]{});
   }

 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */

   public ProbeCase[] getAllProbeCases() throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      ProbeCase probeCase = new ProbeCase();
      probeCase.setTablename(tableName2);
      return (ProbeCase[])gdao.load(probeCase).toArray(new ProbeCase[]{});
   }

   public ProbeCase getProbeCase(String probeCaseid) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      ProbeCase probeCase1 = null;
      if(probeCaseid==null||"".equals(probeCaseid))
         return new ProbeCase();
      try
      {
         ProbeCase probeCase = new ProbeCase();
         probeCase.setTablename(tableName2);
         probeCase1 = ((ProbeCase[])gdao.load(probeCase, "id="+probeCaseid).toArray(new ProbeCase[]{}))[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return probeCase1;
   }

    public void removeProbeCases(HashSet probeCaseIds) throws ProjectDataAccessException
    {
       if(probeCaseIds==null)
          throw new ProjectDataAccessException ("[InventoryManager::removeProbeCases] probeCaseIds are null." );
       Iterator iterator = probeCaseIds.iterator();

       try
       {
       	  //remove task in probeCase first.
          while(iterator.hasNext())
          {
             removeProbeCase((String)iterator.next());
          }
       }
       catch(Exception e)
       {
           //throw new ProjectDataAccessException ("[InventoryManager::removeProbeCases]--Fail to remove tasks info in probeCase("+probeCaseId+")--"+ e.getMessage());
           e.printStackTrace();
       }
    }
   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeProbeCase(String id) throws org.yang.services.dbService.DataAccessException
   {
      boolean isSuccessful = false;
      try
      {
         ProbeCase probeCase = new ProbeCase();
         probeCase.setTablename(tableName2);
         isSuccessful = gdao.delete(probeCase, "id='"+id+"'");
      }
      catch(Exception e)
      {
         e.getMessage();
      }

      return isSuccessful;
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public String createProbeCase(ProbeCase probeCase) throws org.yang.services.dbService.DataAccessException
   {
      if(null==probeCase)
         return "";
      probeCase.setTablename(tableName2);
      String created_id = this.generateId();
      probeCase.setId(created_id);
      gdao.insert(probeCase);
      return created_id;
   }

 /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */

   public boolean updateProbeCase(ProbeCase probeCase) throws org.yang.services.dbService.DataAccessException
   {
      if(null==probeCase)
         return false;
      probeCase.setTablename(tableName2);
      return gdao.update(probeCase);
   }
 
 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Karyotype[] geKaryotypesByDateType(String dataType,String oneDayBeforeStartDate,String oneDayAfterEndDate) throws org.yang.services.dbService.DataAccessException
   {
      System.out.println("[InventoryManager::geKaryotypesByDateType]///----->dataType="+dataType);
      if(null==gdao)
         return null;
      Karyotype[] karyotypes = null;
      try
      {
         StringBuffer sb = new StringBuffer(" (");
         sb.append(dataType).append("> '").append(oneDayBeforeStartDate).append("') and ('")
           .append(oneDayAfterEndDate).append("' > ").append(dataType).append(")");
         System.out.println("[InventoryManager::getKaryotypesByDateType]---conditon="+sb.toString());
         Karyotype karyotype = new Karyotype();
         karyotype.setTablename(tableName5);
         karyotypes = (Karyotype[])gdao.load(karyotype,new String[]{sb.toString()},false).toArray(new Karyotype[]{});
         Calendar cat = Calendar.getInstance();

      }
      catch(Exception e)
      {
         e.getMessage();
      }
      if(karyotypes==null)
         return new Karyotype[]{};

      return karyotypes;
   }


   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */

   public Karyotype[] getKaryotypes(String[] conditions, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Karyotype karyotype = new Karyotype();
      karyotype.setTablename(tableName5);
      return (Karyotype[])gdao.load(karyotype, conditions, isAnd).toArray(new Karyotype[]{});
   }

 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */

   public Karyotype[] getAllKaryotypes() throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Karyotype karyotype = new Karyotype();
      karyotype.setTablename(tableName5);
      return (Karyotype[])gdao.load(karyotype).toArray(new Karyotype[]{});
   }

   public Karyotype getKaryotype(String karyotypeid) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Karyotype karyotype1 = null;
      if(karyotypeid==null||"".equals(karyotypeid))
         return new Karyotype();
      try
      {
         Karyotype karyotype = new Karyotype();
         karyotype.setTablename(tableName5);
         karyotype1 = ((Karyotype[])gdao.load(karyotype, "id="+karyotypeid).toArray(new Karyotype[]{}))[0];
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return karyotype1;
   }

    public void removeKaryotypes(HashSet karyotypeIds) throws ProjectDataAccessException
    {
       if(karyotypeIds==null)
          throw new ProjectDataAccessException ("[InventoryManager::removeKaryotypes] karyotypeIds are null." );
       Iterator iterator = karyotypeIds.iterator();

       try
       {
       	  //remove task in karyotype first.
          while(iterator.hasNext())
          {
             removeKaryotype((String)iterator.next());
          }
       }
       catch(Exception e)
       {
           //throw new ProjectDataAccessException ("[InventoryManager::removeKaryotypes]--Fail to remove tasks info in karyotype("+karyotypeId+")--"+ e.getMessage());
           e.printStackTrace();
       }
    }
   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeKaryotype(String id) throws org.yang.services.dbService.DataAccessException
   {
      boolean isSuccessful = false;
      try
      {
         Karyotype karyotype = new Karyotype();
         karyotype.setTablename(tableName5);
         isSuccessful = gdao.delete(karyotype, "id='"+id+"'");
      }
      catch(Exception e)
      {
         e.getMessage();
      }

      return isSuccessful;
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public String createKaryotype(Karyotype karyotype) throws org.yang.services.dbService.DataAccessException
   {
      if(null==karyotype)
         return "";
      karyotype.setTablename(tableName5);
      String created_id = this.generateId();
      karyotype.setId(created_id);
      gdao.insert(karyotype);
      return created_id;
   }

 /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */

   public boolean updateKaryotype(Karyotype karyotype) throws org.yang.services.dbService.DataAccessException
   {
      if(null==karyotype)
         return false;
      karyotype.setTablename(tableName5);
      return gdao.update(karyotype);
   }
   
   /*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*/

 private String[] inputSearchKeys = null;
 public  String[] getInputSearchKeys(){ return inputSearchKeys;}
 public void setInputSearchKeys(ArrayList arry_keys)
 {
    if(arry_keys!=null)	
       inputSearchKeys=(String[])arry_keys.toArray(new String[]{});
 }  
 
  public HashSet loadAllPersistentLabNames()
 {
    Storage[] storages = null;  	         
    HashSet labNameSet = new HashSet();
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        storages =
           (Storage[])gdao.load(storage).toArray(new Storage[]{});//   storage).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
        for(int i=0;i<storages.length;i++)
        {	
           labNameSet.add(storages[i].getLabName().toUpperCase());  
        }  
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return labNameSet;	
 }	  

  public HashSet loadAllPersistentInvestigators(String labName)
 {	
    Storage[] storages = null;  	         
    HashSet investigatorSet = new HashSet();
    String[] conditions = {" labName='"+labName+"' "};
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});  
        if(storages==null)
            storages =new Storage[]{};
        for(int i=0;i<storages.length;i++)
        {	
           if(null!=storages[i]&&!"".equals(storages[i])) 	
              investigatorSet.add(storages[i].getInvestigator());//.toUpperCase());  
        }  
    System.out.println("[InventoryManager::loadAllPersistentInvestigators]---investigatorSet.size()="+investigatorSet.size());    
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return investigatorSet;	
 }	  
 
 public Storage[] loadAllStorages()
 {
 	         
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        storages =
           (Storage[])gdao.load(storage).toArray(new Storage[]{});  
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	  

 public Storage[] loadStoragesByLocation()
 {
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        
        String [] conditions = {"id like '%'  order by location,boxNumber,rowColumn"};  
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	  
 
 public Storage[] loadStoragesByLocation(String location)
 {
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        
        String [] conditions = {"location = '"+location+"'  order by location,boxNumber,rowColumn"};  
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	 
 
 public Storage[] loadStoragesByBoxNumber(String boxNumber)
 {
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        
        String [] conditions = {"boxNumber = '"+boxNumber+"'  order by location,boxNumber,rowColumn"};  
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	  
 
 public Storage[] loadStoragesByRowColumn(String rowColumn)
 {
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        
        String [] conditions = {"rowColumn = '"+rowColumn+"'"};  
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	   

 public Storage[] loadStoragesByLocationBoxNumberOrRowColumn(String iKey)
 {
    Storage[] storages = null;  	         
    try
    {
        Storage storage = new Storage();
        storage.setTablename(tableName3);
        
        String [] conditions = {"location = '"+iKey+"'","boxNumber = '"+iKey+"'","id = '"+iKey+"'"};  
        storages =
           (Storage[])gdao.load(storage,conditions,false).toArray(new Storage[]{});   
        if(storages==null)
            storages =new Storage[]{};
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }		
    return storages;	
 }	   
 
 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Storage[] getStoragesBySearchKey(String labName,String investigator,String searchKey) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
            
      Storage[] storages = null;
      StringTokenizer  st = null;
      String[] seperators ={" ","|",","};
      ArrayList arry_keys = null;
      Hashtable fitCounterTable = new Hashtable();
      Storage[] searchResult = null;
      String yearCompareSign = "";
      String dateKey = "";
      String a = "";
      String delim = "";
      //String logicRL = "and";
      boolean isId_NA = false; 
      int z = 0;
      boolean isNoValueForLabNameInvestigator = false;
      if("  Please select".equals(labName)&&"  Please select".equals(investigator))
         isNoValueForLabNameInvestigator = true;
      if(isNoValueForLabNameInvestigator&&(searchKey==null||"".equals(searchKey)))
      {   
         return loadAllStorages();
      } 
      //System.out.println("[InventoryManager::getStoraesBySearchKey]----->labName="+labName);
      //System.out.println("[InventoryManager::getStoraesBySearchKey]----->investigator="+investigator);
      //System.out.println("[InventoryManager::getStoraesBySearchKey]----->searchKey="+searchKey);

      do{
         
         st = new StringTokenizer(searchKey,seperators[z++]);
         arry_keys = new ArrayList();
        
         while(st.hasMoreTokens())
         {
            String sKey=(String)st.nextToken().trim();
            //System.out.println("[InventoryManager::getStoraesBySearchKey]--st.hasMoreTokens()--->sKey="+sKey);
            //if("or".equals(sKey)||"||".equals(sKey))
            //   logicRL = "or";
            //else if("and".equals(sKey)||"&&".equals(sKey)||"&".equals(sKey))
            //   logicRL = "and";               
            //else 
            if("<".equals(sKey)||">".equals(sKey)||
               "<=".equals(sKey)||">=".equals(sKey)||
               "=<".equals(sKey)||"=>".equals(sKey)||"=".equals(sKey))
            {
               if(sKey.indexOf("=<")>-1)
                   sKey="<=";	
               else if(sKey.indexOf("=>")>-1)
                   sKey=">=";
                       
               yearCompareSign = sKey;
               //System.out.println("[InventoryManager::getStoraesBySearchKey]----->yearCompareSign="+yearCompareSign);
            }   
            else if(sKey.indexOf("20")>-1||sKey.indexOf("19")>-1)
            {
               //System.out.println("[InventoryManager::getStoraesBySearchKey]----->freezingdate_sKey="+sKey);
               if(sKey.indexOf("-")>-1)
                  delim = "-";
               else if(sKey.indexOf("/")>-1)
                  delim = "/";                	
               StringTokenizer strTokenizer = new StringTokenizer(sKey,delim);	
               int delimCounts = strTokenizer.countTokens();
               //System.out.println("[InventoryManager::getStoraesBySearchKey]----->delimCounts="+delimCounts);
               String[] keysFrag = new String[delimCounts];   
               int i=0;
               
               if(delimCounts>0)	
               {  
               	  while(strTokenizer.hasMoreTokens())
                  {
                     a=strTokenizer.nextToken();	
                  
                     if(a.length()==4)
                         keysFrag[delimCounts-1]=a;
                     else
                     {
                         keysFrag[i]=a;    
                         i++;
                     }     
                  }		
                  
                  dateKey=keysFrag[delimCounts-1];
                  //System.out.println("[InventoryManager::getStoraesBySearchKey]----->dateKey_part="+dateKey);
                  for(int k=0;k<delimCounts-1;k++)
                  {                  
                     dateKey+="-"+keysFrag[k];
                  }
                  //System.out.println("[InventoryManager::getStoraesBySearchKey]----->yearCompareSign="+yearCompareSign);
                  arry_keys.add(dateKey);
               }
               else  
               {            	
                  dateKey = sKey;
                  arry_keys.add(dateKey);
                  //System.out.println("[InventoryManager::getStoraesBySearchKey]----->2 dateKey="+dateKey);
                  //System.out.println("[InventoryManager::getStoraesBySearchKey]----->1 arry_keys.size()="+arry_keys.size());
               }   

            }
            else if("id=NA".equalsIgnoreCase(sKey))
               isId_NA = true;
            else
            {
              if(null!=sKey&&!"".equals(sKey))	  
                 arry_keys.add(sKey);
            }  
         }
         //System.out.println("[InventryManager::getStorageBySearchKey]--->seperators["+z+"]="+seperators[z]);
         //System.out.println("[InventryManager::getStorageBySearchKey]--->arry_keys.size()="+arry_keys.size());
        
      }while(arry_keys.size()==0&&z<seperators.length);
      
      setInputSearchKeys(arry_keys);
      Storage[] result = null;
      Hashtable table = new Hashtable();
      try
      {
         StringBuffer sb12 = new StringBuffer("");
         sb12.append(" (id like 'NA%')");
         int len12 = sb12.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon12="+sb12.toString());
         String[] conditions4
                 = {" ("+sb12.toString()+") "};

         Storage storage = new Storage();
         storage.setTablename(tableName3);
         //System.out.println("[InventoryManager::getStorageBySearchKey]------------------------>dateKey="+dateKey);
         //System.out.println("[InventoryManager::getStorageBySearchKey]------------------------>isId_NA="+new Boolean(isId_NA));

         if(isId_NA)
         {
            storages =
               (Storage[])gdao.load(storage,conditions4,true).toArray(new Storage[]{});   
            if(storages==null)
               storages =new Storage[]{};
         //System.out.println("[InventoryManager::getStorageBySearchKey]------------------------>storages.length="+storages.length);
            return storages;
         }
         
         StringBuffer sb = new StringBuffer("");
         StringBuffer sb2 = new StringBuffer("");
         StringBuffer sb3 = new StringBuffer("");
         StringBuffer sb4 = new StringBuffer("");
         StringBuffer sb5 = new StringBuffer("");
         StringBuffer sb6 = new StringBuffer("");
         StringBuffer sb7 = new StringBuffer("");
         StringBuffer sb8 = new StringBuffer("");
         StringBuffer sb9 = new StringBuffer("");
         StringBuffer sb10 = new StringBuffer("   ");
         StringBuffer sb11 = new StringBuffer("   ");
         if(null!=investigator&&!"".equals(investigator)&&
            !"  Please select".equals(investigator))
         {   
            sb.append(" investigator='").append(investigator).append("' and"); //
            //arry_keys.add(investigator);
         }
         
         if(null!=labName&&!"".equals(labName)&&
            !"  Please select".equals(labName))
         {
            sb3.append(" (labName='").append(labName).append("') ");     //
         }
        
         for(int i=0;i<arry_keys.size();i++)
         {
             sb2.append(" (cell like '%").append(arry_keys.get(i)).append("%') or");
             sb4.append(" (projectCode='").append(arry_keys.get(i)).append("') or");
             sb5.append(" (parentalLine='").append(arry_keys.get(i)).append("') or");
             sb6.append(" (medium='").append(arry_keys.get(i)).append("') or");
             sb7.append(" (drugSelection='").append(arry_keys.get(i)).append("') or");
             sb8.append(" (location='").append(arry_keys.get(i)).append("') or");
             sb9.append(" (boxNumber='").append(arry_keys.get(i)).append("') or");
             sb10.append(" (rowColumn='").append(arry_keys.get(i)).append("') or");
             //sb11.append(" (freezingDate ").append(yearCompareSign).append(" '").append(dateKey).append("') or");
         }
         int len11 = 0;
         if(yearCompareSign!=null&&!"".equals(yearCompareSign))
             sb11.append(" (freezingDate ").append(yearCompareSign).append(" '").append(dateKey).append("') or");
         else
         {
            len11 = sb11.length();
            sb11.replace(len11-3, len11, "");
         }   
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon11="+sb11.toString());

         int len = sb.length();
         int len2 = sb2.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon2="+sb2.toString());
         int len3 = sb3.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon3="+sb3.toString());
         int len4 = sb4.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon4="+sb4.toString());
         int len5 = sb5.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon5="+sb5.toString());
         int len6 = sb6.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon6="+sb6.toString());
         int len7 = sb7.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon7="+sb7.toString());
         int len8 = sb8.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon8="+sb8.toString());
         int len9 = sb9.length();
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon9="+sb9.toString());
         int len10 = sb10.length();
         sb10.replace(len10-3, len10, "");
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon10="+sb10.toString());
         
         //sb.append(sb2).append(sb3).append(sb4).append(sb5).append(sb6).append(sb7).append(sb8).append(sb9).append(sb10);
         sb2.append(sb4).append(sb5).append(sb6).append(sb7).append(sb8).append(sb9).append(sb10) ;
         sb.append(sb3);//.append("(").append(sb2).append(")");   //*** for labName and Investigator  

         StringBuffer sb20 = new StringBuffer("");
         if(sb.toString().trim().length()>1)
         {   
             if(sb11.length()>0)	
                sb20.append("(").append(sb).append(") and (").append(sb2).append(")").append(" and (").append(sb11).append(") ");  
             else
                sb20.append("(").append(sb).append(") and (").append(sb2).append(")");      
         }
         else
         {
             if(sb11.length()>0)	
                sb20.append("(").append(sb2).append(")").append(" and (").append(sb11).append(") ");  
             else
                 sb20.append("(").append(sb2).append(")");          
         } 
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon sb="+sb.toString());
         StringBuffer sb21 = new StringBuffer("");
         if(sb.toString().length()>1)
            sb21.append(sb11).append(" and ").append(sb).append("");        
         else
            sb21.append(sb11).append(" ");        
         StringBuffer sb22 = new StringBuffer("");
         if(sb.toString().length()>1)
            sb22.append("").append(sb11).append(" and ").append(sb).append(" and (").append(sb2).append(")");        
         else
            sb22.append("").append(sb11).append(" and (").append(sb2).append(")");        
         
         //System.out.println("[InventoryManager::getStoragesByDateType]---conditon sb20="+sb20.toString());
         String[] conditions0 = {sb20.toString()}; // sb sb11 sb2
         String[] conditions1 = {sb21.toString()}; // sb sb11
         String[] conditions2
                 = {" ("+sb.toString()+") "};      //sb
         String[] conditions3
                 = {" ("+sb11.toString()+") "};    //sb11
         String[] conditions5
                 = {" ("+sb.toString()+")"," ("+sb2.toString()+")"}; //sb sb2
         String[] conditions6
                 = {" { "+sb2.toString()+" }"};         
         //System.out.println("[InventoryManager::getStorageBySearchKey]//////------------->dateKey="+dateKey);
                 
         if(null!=dateKey&&!"".equals(dateKey))
         {        
            //System.out.println("[InventoryManager::getStorageBySearchKey]------------------------>arry_keys.size()="+arry_keys.size());
            //if freezingDate  is one of the keys, take the interset with other union set;    
            //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX sb2.toString() ="+sb2.toString());
             if(null!=sb2.toString()&&!"".equals(sb2.toString()))
             {
                storages =
                     (Storage[])gdao.load(storage,new String[]{sb2.toString()},false).toArray(new Storage[]{});   
             }
             else
                storages = null;
            
             if(storages!=null&&storages.length>0)
             {
                if(arry_keys.size()>1)
                {
                   storages =
                     (Storage[])gdao.load(storage,conditions0,false).toArray(new Storage[]{});   // sb20-->sb sb11 sb2
                //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX conditions0="+sb20.toString());

                }
               
             }
             else
             {
                  storages =
                     (Storage[])gdao.load(storage,conditions1,false).toArray(new Storage[]{});////sb21--> sb sb11   
                //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX conditions1="+sb21.toString());
                      
             }	        
           //System.out.println("[InventoryManager::getStorageBySearchKey]--------------------->2");
            
         }
         else
         {
              //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX sb2.toString() ="+sb2.toString());
             if(null!=sb2.toString()&&!"".equals(sb2.toString()))
                storages =(Storage[])gdao.load(storage,new String[]{sb2.toString()},false).toArray(new Storage[]{});   
             else
                storages = null;
             
             if(storages!=null&&storages.length>0)
             {
                if(!isNoValueForLabNameInvestigator)
                {	
                   storages =
                     (Storage[])gdao.load(storage,conditions5,true).toArray(new Storage[]{}); //sb sb2 
                //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX conditions5=("+sb2.toString()+") AND ("+sb.toString()+")");
                }//else storges result based on sb2   
                                  
             }   
             else
             {
                storages =
                  (Storage[])gdao.load(storage,conditions2,false).toArray(new Storage[]{});  //sb 
                //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX conditions_2="+sb.toString());
             }   
        }
        
         //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX---storages.length="+storages.length);
         HashSet set_storages = new HashSet();
         if(storages!=null&&storages.length!=0)
         {
            for(int j=0;j<storages.length;j++)
            {
               set_storages.add(storages[j]);
            }
         }
         
         //System.out.println("[InventoryManager::geStoragesBySearchKey]XXXX--(after merge)-set_storages.size()="+set_storages.size());
         Iterator iterator1 = set_storages.iterator();
         if(storages!=null&&storages.length!=0)
         {
            while(iterator1.hasNext())
            {
               ((Storage)iterator1.next()).fitValuesCompareToSearchKeys(arry_keys);
            }
         }
         Storage s = null;
         iterator1 = set_storages.iterator();
         while(iterator1.hasNext())
         {
            s =  (Storage)iterator1.next();
            String fitCount = s.getNumberOfFitValuesWithSearchKeys()+"";
            if(!"".equals(dateKey))
            {
               int c = new Integer(fitCount).intValue()+1;
               fitCount = c+""; 	
            }	
            
            //System.out.println("[InventoryManager::geStoragesBySearchKey]---fitCount="+fitCount);
            ArrayList x = (ArrayList)fitCounterTable.get(fitCount);
            if(x==null||x.size()==0)
            {
              ArrayList xx =new ArrayList();
              xx.add(s);
              fitCounterTable.put(fitCount,xx);
            }
            else
            {
               x.add(s);
            }
            //System.out.println("[InventoryManager::geStoragesBySearchKey]---(after)storage Array size (ArrayList)fitCounterTable.get(fitCount).size()="+((ArrayList)fitCounterTable.get(fitCount)).size());
         }
         //System.out.println("[InventoryManager::geStoragesBySearchKey]---fitCounterTable.size()="+fitCounterTable.size());
         //System.out.println("[InventoryManager::geStoragesBySearchKey]---arry_keys.size()="+arry_keys.size());
         if(fitCounterTable.size()==1)
            searchResult = storages;
         else //if("and".equals(logicRL))
            searchResult = searchStoragesOfHighestProximity(arry_keys.size(),fitCounterTable);
         //else if("and".equals(logicRL))
         //   searchResult = searchStoragesOfHighestProximity(arry_keys.size(),fitCounterTable);
         //else if("or".equals(logicRL))
         //   searchResult = mergeSearchStoragesByProximity(arry_keys.size(),fitCounterTable);
          //System.out.println("[InventoryManager::getStorageBySearchKey]--------------------->8");
           
      }
      catch(Exception e)
      {
         e.getMessage();
      }
      if(searchResult==null)
         return new Storage[]{};
      //System.out.println("[InventoryManager::geStoragesBySearchKey]---searchResult.length="+searchResult.length);

      return searchResult;
   }


   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */

   public Storage[] getStorages(String[] conditions, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Storage storage = new Storage();
      storage.setTablename(tableName3);
      return (Storage[])gdao.load(storage, conditions, isAnd).toArray(new Storage[]{});
   }
 
   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */

   public Storage[] getStoragesBySourceFileName(String fileName, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Storage storage = null;   
      String str_conditions = "";
      StringBuffer conditions = new StringBuffer("");   
      Storage[] storageArray = new Storage[0]; 
      
      try
      {   
         StorageSource storageSource = new StorageSource();
         storageSource.setTablename(tableName4);
         StorableList sss = gdao.load(storageSource, new String[]{"source='"+fileName+"'"}, false);
         for(int i=0;i<sss.size();i++)
         {
            StorageSource ss = (StorageSource)sss.get(i);
            storage = new Storage();
            storage.setTablename(tableName3);
            conditions.append(" (id='"+ss.getId()+"')  or ");
         }	
         str_conditions  = (conditions.toString()).substring(0,conditions.length()-3);
         //System.out.println("[InventoryManager::getStoragesBySourceFileName]---str_conditions=" + str_conditions);
         storageArray = (Storage[])gdao.load(storage, new String[]{str_conditions}, isAnd).toArray(new Storage[]{});
         cacheManager.addElementToCache(CACHEKEY_PRE_FILE_DATA,storageArray);
         int cacheSize = cacheManager.size();
         //System.out.println("[InventoryManager::getStoragesBySourceFileName]------->storageArray.length="+storageArray.length);
         //System.out.println("[InventoryManager::getStoragesBySourceFileName]------->cacheSize="+cacheSize);
         fetchStoragesFromCache(CACHEKEY_PRE_FILE_DATA);///

      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }
      return storageArray;
   }
 
 public void addStoragesToCache(String myKey,Storage[] ss){cacheManager.addElementToCache(myKey,ss);} 
 public Storage[] fetchStoragesFromCache(String myKey)
 {
    Storage[] allStorages = new Storage[0];	
    try
    {	
       Object objs = cacheManager.fetchElementFromCache(myKey);
       if(objs!=null)
       {
          CacheEntry cacheEntry = (CacheEntry)objs;
          allStorages = (Storage[])cacheEntry.getElement();
       }
       else
          return new Storage[0];
    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }	
    return allStorages;
 } 

 public Storage[] removeStoragesFromCache(String myKey)
 {
    Object objs = null;	
    Storage[]  storages = null;
     
    try
    {	
        objs = cacheManager.removeElementFromCache(myKey);
        if(objs!=null)
        {
           CacheEntry cacheEntry = (CacheEntry)objs;
           storages = (Storage[])cacheEntry.getElement();

        }
        else
           return new Storage[0];

    }
    catch(Exception e)
    {
       e.printStackTrace();	
    }	
    return  storages;
   
} 

 /**
 * this method will get all group object in one domian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Storage[] getAllStorages() throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Storage[] storageArray = new Storage[0];
      try
      {   
         Storage storage = new Storage();
         storage.setTablename(tableName3);
         storageArray = (Storage[])gdao.load(storage).toArray(new Storage[]{});
          
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      
      return storageArray;
   }

   public Storage getStorage(String storageid) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Storage storage1 = null;
      if(storageid==null||"".equals(storageid))
         return new Storage();
      try
      {
      	 //System.out.println("[InventoryManager::getStorage]-----"+storageid);
         Storage storage = new Storage();
         storage.setTablename(tableName3);
         Storage[] ss = (Storage[])gdao.load(storage, "id='"+storageid+"'").toArray(new Storage[]{});
         if(ss!=null&&ss.length==1)
            storage1 = ss[0];   
         //System.out.println("[InventoryManager::getStorage]-----storage1.getMedium()"+storage1.getMedium());
         //System.out.println("[InventoryManager::getStorage]-----storage1.getLabName()"+storage1.getLabName());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return storage1;
   }

    public void removeStorages(HashSet storageIds) throws ProjectDataAccessException
    {
       if(storageIds==null)
          throw new ProjectDataAccessException ("[InventoryManager::removeStorages] storageIds are null." );
       Iterator iterator = storageIds.iterator();
      
       try
       {
       	  //remove task in storage first.
          while(iterator.hasNext())
          {
             removeStorage((String)iterator.next());
          }
       }
       catch(Exception e)
       {
           //throw new ProjectDataAccessException ("[InventoryManager::removeStorages]--Fail to remove tasks info in storage("+storageId+")--"+ e.getMessage());
           e.printStackTrace();
       }
    }
/**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeStorage(String id) throws org.yang.services.dbService.DataAccessException
   {
      boolean isSuccessful = false;
      try
      {
         Storage storage = new Storage();
         storage.setTablename(tableName3);
         isSuccessful = gdao.delete(storage, "id='"+id+"'");
         //System.out.println("[InventoryManager::removeStorage]---------->remove id="+id+" from all_inventory_storages table."); 
         StorageSource ss = new StorageSource();
         ss.setId(id);
         ss.setTablename(tableName4);  
         gdao.delete(ss, "id='"+id+"'");           
         //System.out.println("[InventoryManager::removeStorage]---------->remove id="+id+" from all_inventory_storageSource table."); 

      }
      catch(Exception e)
      {
         e.getMessage();
      }

      return isSuccessful;
   }

   // for unpdate id for storages to remove old id and get the source name form storageSource table
   public String removeStorageForUpdateID(String id) throws org.yang.services.dbService.DataAccessException
   {
      String storageSourceName = "";
      try
      {
         Storage storage = new Storage();
         storage.setTablename(tableName3);
          gdao.delete(storage, "id='"+id+"'");
         //System.out.println("[InventoryManager::removeStorageForUpdateID]---------->remove id="+id+" from all_inventory_storages table."); 
         StorageSource ss = new StorageSource();
         ss.setId(id);
         ss.setTablename(tableName4);  
         StorageSource[] ssArray = (StorageSource[])gdao.load(ss, "id='"+id+"'").toArray(new StorageSource[]{});
         if(ssArray!=null&&ssArray.length==1)
            storageSourceName = ssArray[0].getSource();   
         gdao.delete(ss, "id='"+id+"'");           
         //System.out.println("[InventoryManager::removeStorageForUpdateID]---------->remove id="+id+" from storageSource="+storageSourceName); 

      }
      catch(Exception e)
      {
         e.getMessage();
      }

      return storageSourceName;
   }

   public void removeStorageSource(String id) throws org.yang.services.dbService.DataAccessException
   {
      try
      {
         StorageSource ss = new StorageSource();
         ss.setId(id);
         ss.setTablename(tableName4);  
         gdao.delete(ss, "id='"+id+"'");           
         //System.out.println("[InventoryManager::removeStorageForUpdateID]---------->remove id="+id+" from storageSource"); 
      }
      catch(Exception e)
      {
         e.getMessage();
      }

   }

   public void createStorageSource(String storageId,String fileName) throws org.yang.services.dbService.DataAccessException
   {
      try
      {
      	 
         StorageSource ss = new StorageSource();
         ss.setId(storageId);
         ss.setSource(fileName);
         ss.setTablename(tableName4);         
         gdao.insert(ss);

      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
   }   
   
 
   public String createStorage(Storage storage,String fileName) throws org.yang.services.dbService.DataAccessException
   {
      if(null==storage)
         return "";
      String id = "";   
      try
      {
      	 storage.setTablename(tableName3);
         String parentalLine = storage.getParentalLine();
         String location = storage.getLocation();
         String boxNumber = storage.getBoxNumber()+"";
         String rowColumn = storage.getRowColumn();
                     
         id = createStorageId(storage);
         //System.out.println("[InventoryManager::createStorage]------->id="+id);

         removeStorage(id);  // remove before insert
         
         storage.setId(id);
         gdao.insert(storage);
         
         StorageSource ss = new StorageSource();
         ss.setId(id);
         ss.setSource(fileName);
         ss.setTablename(tableName4);         
         gdao.delete(ss, "id='"+id+"'");           
         ///System.out.println("[InventoryManager::createStorage]---------->remove id="+id+" from storageSource"); 
         gdao.insert(ss);

      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
      return id;
   }   
 /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public void createStorages(StorableList storages,String fileName) throws org.yang.services.dbService.DataAccessException
   {
      String id ="";     	
      try
      {
      	 int fileSize = storages.size();
         for(int i=0;i<fileSize;i++)
         {   
            Storage storage = (Storage)storages.get(i);
            String parentalLine = storage.getParentalLine();
            String location = storage.getLocation();
            String boxNumber = storage.getBoxNumber()+"";
            String rowColumn = storage.getRowColumn();
                     
            //id = parentalLine+"_"+location+"_"+boxNumber+"_"+rowColumn; 
            id = createStorageId(storage);
            //System.out.println("[InventoryManager::createStorage]------->id="+id);
            removeStorage(id); ///remove this data before insert 
             
            storage.setId(id);
            storage.setTablename(tableName3);
            
            StorageSource ss = new StorageSource();
            ss.setId(id);
            ss.setSource(fileName);
            ss.setTablename(tableName4);
            //---
            gdao.delete(ss, "id='"+id+"'");           
            //System.out.println("[InventoryManager::createStorages]---------->remove id="+id+" from storageSource"); 
            //--- 
            gdao.insert(ss);
         }
         gdao.insert(storages);
         
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }	
   }

 /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */

   public boolean updateStorage(Storage storage) throws org.yang.services.dbService.DataAccessException
   {
      if(null==storage)
         return false;
      storage.setTablename(tableName3);
      return gdao.update(storage);
   }



   // StandAlone 
   static public String createStorageId(Storage storage)
   {
      String id = "";
      try
      {
      	 String cell =storage.getCell();
         String investigator = storage.getInvestigator();
         String labName = storage.getLabName();
         String projectCode = storage.getProjectCode();
         String parentalLine = storage.getParentalLine();
         String medium = storage.getMedium();
         String drugSelection = storage.getDrugSelection();
         String location = storage.getLocation();
         int boxNumber = storage.getBoxNumber();
         String rowColumn = storage.getRowColumn();
         Date freezingDate = storage.getFreezingDate();
         String comment = storage.getComment();
         String temp_id = "";
         /*  
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->parentalLine="+parentalLine);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->location="+location);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->boxNumber="+boxNumber);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->rowColumn="+rowColumn);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->projectCode="+projectCode);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->cell="+cell);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->investigator="+investigator);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->labName="+labName);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->medium="+medium);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->drugSelection="+drugSelection);
            //System.out.println("[InventoryManager::createStorageId]-------------------------------------->freezingDate="+freezingDate);
         */
         if(parentalLine!=null&&!"".equals(parentalLine)&&
            location!=null&&!"".equals(location)&&
            rowColumn!=null&&!"".equals(rowColumn)&&
            boxNumber!=0)
         {   
            id = parentalLine+"_"+location+"_"+boxNumber+"_"+rowColumn; //storage.getId();
         }
         else //the id controll for lack of data value  
         {
            if(parentalLine==null||"".equals(parentalLine))
               temp_id+="parentalLine";
            if(location==null||"".equals(location))
               temp_id+="_location";
            if(boxNumber==0)
               temp_id+="_boxNumber";
            if(rowColumn==null||"".equals(rowColumn))
               temp_id+="_rowColumn";
            temp_id +=cell+"_"+investigator+"_"+labName+"_"+projectCode;
            temp_id+=medium+"_"+drugSelection;   
            temp_id+="_"+freezingDate;
            id = "NA_"+temp_id;                	
         }
         //System.out.println("[InventoryManager::createStorageId]-------------------------->id="+id);
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
      return id ;	
   }	
   	

   /****************************
    *  User permission & right *
    ****************************/

   public Area[] allAreas()
   {
      if(allDomains==null)
      {
         //System.out.println("[InventoryManager::allAreas]-----allDomains is null.");
         return new Area[]{};
      }
      int size = allDomains.size();
      InventoryManagerArea[] areas = new InventoryManagerArea[size];
      InventoryManagerOperation[] ops = null;
      
      for(int i=0; i<size; i++)
      {
         ops = new InventoryManagerOperation[0];
         //ops[0] = new InventoryManagerOperation(this.DELETE);
         //ops[1] = new InventoryManagerOperation(this.UPDATE);
         //ops[2] = new InventoryManagerOperation(this.CREATE);
         areas[i] = new InventoryManagerArea();
         areas[i].setName((String)allDomains.get(i));
         areas[i].setAllOperations(ops);
      }
      
      return areas;
       
   }

   public boolean containArea(String areaname)
   {
      return allDomains.contains(areaname);
   }

   private String generateId()
   {
      int n = (int)(100.0 * Math.random());	
      String  s = System.currentTimeMillis() + n + "";
      return s;
   }

   private Storage[] searchStoragesOfHighestProximity(int searchKeyNumber, Hashtable fitCounterTable)
   {
      if(fitCounterTable==null)
         return null;
      ArrayList ss = new ArrayList();
      int i = searchKeyNumber;

      do
      {
         Collection a = (ArrayList)fitCounterTable.get(i+"");
         if(a!=null)
            ss.addAll(a);
         //System.out.println("[InvestoryManager::mergeSearchStoragesByProximity]---ss.size="+ss.size());
         i--; 
      }while(ss.isEmpty()&&i>0);
      
      return (Storage[])ss.toArray(new Storage[]{});
   }

   private Storage[] mergeSearchStoragesByProximity(int searchKeyNumber, Hashtable fitCounterTable)
   {
      if(fitCounterTable==null)
         return null;
      ArrayList ss = new ArrayList();
      for(int i=searchKeyNumber;i>0;i--)
      {
         Collection a = (ArrayList)fitCounterTable.get(i+"");
         if(a!=null)
            ss.addAll(a);
         //System.out.println("[InvestoryManager::mergeSearchStoragesByProximity]---ss.size="+ss.size());
      }
      return (Storage[])ss.toArray(new Storage[]{});
   }



   public static void main(String[] args)
   {
   }
}
