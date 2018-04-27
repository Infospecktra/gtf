<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="inventoryTree"
             class="org.yang.customized.gtf.services.inventoryManager.web.InventoryTreeBean"
             scope="session"/>

<HTML>
  <HEAD>
    <title>Inventory Service::</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <style type="text/css">
      .BASESTYL {font-family: Arial,Helvetica,sans-serif; font-size: 10pt; font-weight:bold; color:#990000}
      .ROOTSTYL {font-family: Arial,Helvetica,sans-serif; font-size: 9pt; color:#000033; font-weight:bold}
      .NODESTYL {font-family: Arial,Helvetica,sans-serif; font-size: 9pt}
      .LEAFSTYL {font-family: Arial,Helvetica,sans-serif,PMingLiU; font-size: 9pt}
      .ONNODESTYL {color:#FFFFFF ;background-color: #006699}
    </style>
    <link rel=stylesheet type="text/css" href="/wf/default/css/swbase_English.css">
	<style type="text/css">
    <!--    
       a.converter {color:#50A4c8;text-decoration:none}
       a.converter:hover{color:#999999;}
       a.converter:visited{color:#999999;} 
     -->
    </style>
    <script language="JavaScript" type="text/javascript">
      function openWindow(url,w,h) 
      {
        if(w==0)
           w = 800;
        if(h==0)
           h = 550;   
        popupWin = window.open(url,'new_page','width='+w+',height='+h+',scrollbars=yes,toolbars=yes,menubar=no,resizable=yes')
      }
    </script>
  </HEAD>
  <BODY bgcolor="#FFFFFF" leftmargin="5" rightmargin="5" topmargin="5" marginwidth="5" marginheight="5" onLoad="window.history.forward()">

    <wf:WebControl beanID = "inventoryTree" 
                   controlBuilderName = "org.yang.customized.gtf.services.inventoryManager.web.InventoryTreeBuilder" />

  </BODY>
</HTML>