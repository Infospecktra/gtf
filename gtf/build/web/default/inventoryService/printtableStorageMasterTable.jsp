<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="storageMasterBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBean"
             scope="session"/>

<html>
  <head>
    <title>Inventory Manager::storage master table</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
  </head>
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="print()">
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td class="title" align="left">
          <font color="#1D459D">Storage Location Table</font>
        </td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#1D459D"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          View probe test table.
        </td>
      </tr>
    </table>
   
    <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">

        <wf:WebControl beanID = "storageMasterBean"
                     controlBuilderName = "org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBuilder" />

    </table>
  </body>
</html>