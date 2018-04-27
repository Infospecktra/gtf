<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.lang.System" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.yang.customized.gtf.services.dataAccess.Record" %>

<jsp:useBean id="passport"
             class="org.yang.web.applicationContainer.PassportBean"
             scope="session"/>
<jsp:useBean id="inventoryCalendar"
             class="org.yang.customized.gtf.services.inventoryManager.web.CalendarBean" 
             scope="session"/>
<jsp:useBean id="recordBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.RecordTableBean"
             scope="session"/>
<jsp:useBean id="inventoryBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.InventoryBean"
             scope="session"/>
<jsp:useBean id="probeCaseBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.ProbeCaseBean"
             scope="session"/>
<jsp:useBean id="probeTestBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.ProbeTestBean"
             scope="session"/>
<jsp:useBean id="storageMasterBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBean"
             scope="session"/>
<jsp:useBean id="karyotypeBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.KaryotypeBean"
             scope="session"/>
<jsp:useBean id="karyotypeMasterTableBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.KaryotypeMasterTableBean"
             scope="session"/>

<jsp:useBean id="storageBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageBean"
             scope="session"/>

<%
   String rightService = inventoryBean.getServiceType();
   String dateType = "";
   String id = "";
   String actiontype = "";
   String rightTopFrame = "";
   String rightBottomFrame = "";

//System.out.println("[inventoryServiceBodyRight.jsp]----->rightService="+rightService);

  if("PI".equals(rightService)){
      dateType = inventoryBean.getDateType();
      id = recordBean.getId();
      //inventoryCalendar.setCalendarView(1);//for monthly mode
      if(!inventoryBean.getNeedToRemoveRecord()) //for right bottom frame
         actiontype="browse";
      else{
         actiontype="showRemoveCtrlPanel";
         recordBean.setId("");
      }
      if(0==(inventoryBean.getAllRecordsSize())) // when refresh calendar
         recordBean.setId("");

      inventoryBean.setDisplayedYear(inventoryCalendar.getDisplayedYear());
      inventoryBean.setDisplayedMonth(inventoryCalendar.getDisplayedMonth());
      inventoryBean.setDisplayedDay(inventoryCalendar.getDisplayedDay());
      rightTopFrame = "/wf/inventory.wf?actiontype=loadSpreadSheetData&&dateType="+dateType+"&&id="+id;
      rightBottomFrame = "/wf/record.wf?actiontype="+actiontype+"&&dateType="+dateType;
   }

   if("PT".equals(rightService))
   {
      dateType = probeTestBean.getDateType();
      id = probeCaseBean.getId();
      //inventoryCalendar.setCalendarView(1);//for monthly mode
      if(!probeTestBean.getNeedToRemoveProbeCase()) //for right bottom frame
         actiontype="browse";
      else{
         actiontype="showRemoveCtrlPanel";
         probeCaseBean.setId("");
      }
      if(0==(probeTestBean.getAllProbeCasesSize())) // when refresh calendar
         probeCaseBean.setId("");

      probeTestBean.setDisplayedYear(inventoryCalendar.getDisplayedYear());
      probeTestBean.setDisplayedMonth(inventoryCalendar.getDisplayedMonth());
      probeTestBean.setDisplayedDay(inventoryCalendar.getDisplayedDay());
      rightTopFrame = "/wf/probeTest.wf?actiontype=loadSpreadSheetData&&dateType="+dateType+"&&id="+id;
      rightBottomFrame = "/wf/probeCase.wf?actiontype="+actiontype+"&&dateType="+dateType;
   }

   if("KR".equals(rightService))
   {
      dateType = karyotypeMasterTableBean.getDateType();
      id = karyotypeBean.getId();
      if(!karyotypeMasterTableBean.getNeedToRemoveKaryotype()) //for right bottom frame
         actiontype="browse";
      else{
         actiontype="showRemoveCtrlPanel";
         karyotypeBean.setId("");
      }
      if(0==(karyotypeMasterTableBean.getAllKaryotypesSize())) // when refresh calendar
         karyotypeBean.setId("");
      //inventoryCalendar.setCalendarView(0);//for yearly mode
      karyotypeMasterTableBean.setDisplayedYear(inventoryCalendar.getDisplayedYear());
      karyotypeMasterTableBean.setDisplayedMonth(inventoryCalendar.getDisplayedMonth());
      karyotypeMasterTableBean.setDisplayedDay(inventoryCalendar.getDisplayedDay());
      rightTopFrame = "/wf/karyotypeMasterTable.wf?actiontype=loadMasterTableData&&dateType="+dateType+"&&id="+id;
      rightBottomFrame = "/wf/karyotype.wf?actiontype="+actiontype+"&&dateType="+dateType;
   }

//System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->1.passport.get:ServiceType="+rightService);

   if("SLocator".equals(rightService))
   {  
      id=URLEncoder.encode(storageMasterBean.getId());
      //System.out.println("[inventoryServiceBodyRight.jsp]----->id="+id);
      String uploadedExcelFile = storageMasterBean.getUploadedExcelFile(); 
      //System.out.println("[inventoryServiceBodyRight.jsp]----->uploadedExcelFile="+uploadedExcelFile);
      //inventoryCalendar.setCalendarView(1);//for monthly mode
      if(!storageMasterBean.getNeedToRemoveStorage())
      {
         if("inputDataByExcel".equals(storageBean.getRawDataInputType()))
            actiontype = "toInputDataFromExcelFiles";
         else 
            actiontype= "browse";
      }   
      else
      {
         actiontype="showRemoveCtrlPanel";
         storageBean.setId("");
      }
      
      if(0==(storageMasterBean.getAllStoragesSize())) // when refresh calendar
         storageBean.setId("");
     /*
      System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->id="+id);
      System.out.println("[inventoryServiceBodyRight.jsp]================================================start");
      System.out.println("[inventoryServiceBodyRight.jsp]");
      System.out.println("[inventoryServiceBodyRight.jsp]");
      System.out.println("[inventoryServiceBodyRight.jsp]---inventoryBean.getLastActiontype()    ="+inventoryBean.getLastActiontype()); 
      System.out.println("[inventoryServiceBodyRight.jsp]---storageMasterBean.getLastActiontype()="+storageMasterBean.getLastActiontype()); 
      System.out.println("[inventoryServiceBodyRight.jsp]");
      System.out.println("[inventoryServiceBodyRight.jsp]");
      System.out.println("[inventoryServiceBodyRight.jsp]================================================end");
      System.out.println("[inventoryServiceBodyRight.jsp]---storageBean.getLastActiontype()      ="+storageBean.getLastActiontype()); 
     */
      if("changePage".equals(storageBean.getLastActiontype()))
         storageMasterBean.setSearchKeys("");
      String forwardActionType = "";
      
      if("search".equals(storageMasterBean.getLastActiontype()))
      {
         uploadedExcelFile = "";
         forwardActionType = "loadSpreadSheetData";
      }
      else
      {
        forwardActionType = "loadSpreadSheetData";
        storageMasterBean.clear();  //For clicking specific cell name to display data in stoarge table with uncleared search string     
        //System.out.println("[inventoryServiceBodyRight.jsp]------> forwardActionType ="+forwardActionType);
      }
      
      if((null!=uploadedExcelFile&&!"".equals(uploadedExcelFile))||
         "changePage".equals(storageBean.getLastActiontype()))
      { 
         storageMasterBean.loadStoragesByFileName(uploadedExcelFile);
         int sSize = storageBean.getTotalDataSize();
         int ssSize = storageMasterBean.getAllStoragesSize();
         //System.out.println("[inventoryServiceBodyRight.jsp]----->sSize="+sSize);
         //System.out.println("[inventoryServiceBodyRight.jsp]----->ssSize="+ssSize);
         if((sSize== ssSize)&&sSize>0||"changePage".equals(storageBean.getLastActiontype()))
             storageBean.setTotalDataSize(-99);
         if("changePage".equals(storageBean.getLastActiontype()))
             storageMasterBean.setSearchKeys("");    
         storageBean.setIsUploadCompleted(true);
         String  jspUploadedFormSetting  = storageMasterBean.getUploadedFormSetting();
         storageBean.setUploadedFormSetting(jspUploadedFormSetting);    
         if(null!= jspUploadedFormSetting&&!"".equals(jspUploadedFormSetting))
            session.removeAttribute("uploadedFormSetting");
         if("toUploadeFileMasterTable".equals(storageMasterBean.getLastActiontype()))
            storageMasterBean.setSelectLoadCacheData(false);
            
         rightTopFrame    = "/wf/storages.wf?actiontype=toUploadeFileMasterTable"; 
         rightBottomFrame = "/wf/storage.wf?actiontype="+actiontype+"&&id="+id;
      }
      else
      {
         storageBean.setIsUploadCompleted(false);
         if("edit".equals(storageBean.getLastActiontype()))
            forwardActionType = "loadCacheData";
            
         String completeLoad= inventoryBean.getCompleteLoad();   
         //System.out.println("[inventoryServiceBodyRight.jsp]xxx---->completeLoad="+completeLoad);
         if("yes".equals(completeLoad))
         {   
            storageMasterBean.setReloadStorages(inventoryBean.getReloadedStorages());
            forwardActionType = "reloadStorages";
            inventoryBean.setCompleteLoad("no");
            storageMasterBean.setSelectLoadCacheData(true);
         }
         if("loadCacheData".equals(storageMasterBean.getLastActiontype())&&
            "updateStorage".equals(storageBean.getLastActiontype()))
            storageMasterBean.setSelectLoadCacheData(true);      
         if("search".equals(storageMasterBean.getLastActiontype()))
            storageMasterBean.setSelectLoadCacheData(false);
        /*     
         System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->id="+id);
         System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->storageMasterBean.getSearchKeys()="+storageMasterBean.getSearchKeys());
         System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->actiontype="+actiontype);
         System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->forwardActionType=" + forwardActionType);
         */
         rightTopFrame = "/wf/storages.wf?actiontype="+forwardActionType+"&&id="+id;
         id=URLEncoder.encode(storageBean.getId());
         rightBottomFrame = "/wf/storage.wf?actiontype="+actiontype+"&&id="+id;
      }
      //System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->actiontype="+actiontype);
   }
   
   passport.removeRuntimeProperty("serviceType");
   passport.setRuntimeProperty("serviceType", rightService);
   /*
   System.out.println("[inventoryServiceBodyRight.jsp]xxx---------->passport.getServiceType="+rightService);
   System.out.println("[inventoryServiceBodyRight.jsp]xxx-->rightTopFrame="+rightTopFrame);
   System.out.println("[inventoryServiceBodyRight.jsp]xxx-->rightBottomFrame="+rightBottomFrame);
   */
%>

<html>
  <head>
    <title>Inventory Service ::rightFrame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <!--300-->
  <FRAMESET rows="*,50%" border="0" framespacing="0" frameborder="no">
    <FRAME name="rightTopFrame"  src="<%=rightTopFrame%>">
    <FRAME name="rightBottomFrame" src="<%=rightBottomFrame%>">
  </FRAMESET>
</HTML>
 