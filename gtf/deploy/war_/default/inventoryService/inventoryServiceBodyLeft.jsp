<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="passport"
             class="org.yang.web.applicationContainer.PassportBean"
             scope="session"/>
<jsp:useBean id="inventoryBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.InventoryBean"
             scope="session"/>
<jsp:useBean id="storageMasterBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBean"
             scope="session"/>
<jsp:useBean id="searchControl"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageSearchControlBean"
             scope="session"/>
<%

    String codeBase = passport.getSystemProperty("appbase");
    String myService = inventoryBean.getServiceType();
    String leftBottomFrame = "";
    String leftTopFrame = "";
    if(!"SLocator".equals(myService))
    {
       leftBottomFrame = codeBase + "/inventoryCalendar.wf?actiontype=load";
       leftTopFrame = codeBase + "/inventoryTree.wf?actiontype=load";   
       
    }
    else
    {
     
       leftBottomFrame = codeBase + "/storageSearch.wf?actiontype=load";
       leftTopFrame = codeBase + "/inventoryTree.wf?actiontype=load&serviceType=SLocator";   
    }

    //System.out.println("[inventoryServiceBodyLeft.jsp]----->myService="+myService);
    System.out.println("[inventoryServiceBodyLeft.jsp]----->leftBottomFrame="+leftBottomFrame);
    System.out.println("[inventoryServiceBodyLeft.jsp]----->leftTopFrame="+leftTopFrame);

%>

<html>
  <head>
    <title>Web Framework::leftFrame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <FRAMESET rows="*,45%" border="0" framespacing="0" frameborder="no">
     <FRAME name="leftTopFrame"
           src="<%=leftTopFrame%>">
     <FRAME name="leftBottomFrame"
               scrolling="NO"
               src="<%=leftBottomFrame%>">
  </FRAMESET>
</HTML>
 