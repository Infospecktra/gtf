<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id    = "projectNotesBean" 
             class = "org.yang.customized.gtf.services.projectManager.web.ProjectNotesBean" 
             scope = "session"/>

<html>
  <head>
    <title>Projects Manager::Data sheet</title>
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
          <font color="#1D459D">Projects Data Sheet (Project type:<%=projectNotesBean.getProjectType()%>)</font>
        </td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#1D459D"><img src="/wf/default/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          View data sheet.
        </td>
      </tr>
    </table>
    <!-----form starts here ----->
    <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">

      <wf:WebControl beanID = "projectNotesBean" 
                     controlBuilderName = "org.yang.customized.gtf.services.projectManager.web.DataSheetBuilder" />

    </table>
  </body>
</html>