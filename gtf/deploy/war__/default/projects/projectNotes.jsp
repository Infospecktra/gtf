<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="org.yang.customized.gtf.services.GTFService" %>

<jsp:useBean id    = "projectNotesBean" 
             class = "org.yang.customized.gtf.services.projectManager.web.ProjectNotesBean" 
             scope = "session"/>

<html>
  <head>
    <title>ProjectManager::Project notes</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>

    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function confirmDel(frm,act,bool) {
        if (bool==1) {
          if (confirm('This is NOT recoverable.\nAre you sure?'))
          {  
              submitForm(frm,act);
          }  
        }
        else
          submitForm(frm,act);
      }
      
      //---------------------------------------
      function submitForm(frm,act) {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }
      
      //---------------------------------------
      function openWindow(url) 
      {
        popupWin = window.open(url,'new_page','width=640,height=525,scrollbars=yes,toolbars=yes,menubar=yes,resizable=yes')
      }
      
    </script>
  </head>
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()">
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <%
          String id = projectNotesBean.getCurrentProject().getId();
          if(null==id||"".equals(id))
            id = "N/A";
        %>
        <td class="title" align="left">
          <font color="#1D459D">Project Application Form (<%=id%>)</font>
        </td>
        <td align="right">

	  <%
        if(projectNotesBean.gotPermit("ProjectManager", projectNotesBean.getCurrentProject().getType(), "delete"))
        {  
      %>
          <a href="/wf/projectNotes.wf?actiontype=delete&id=<%=id%>" target="_parent" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_remove','','/wf/default/images/English/btn_remove_on.gif',1)">
            <img name="img_remove" src="/wf/default/images/English/btn_remove.gif" border="0" alt="Remove the project">
          </a>
      <%
        }
    
        if(projectNotesBean.gotPermit("ProjectManager", projectNotesBean.getCurrentProject().getType(), GTFService.SYSTEM_DATA));//"systemData"))
        {   
		
      %>
	
          <a href="/wf/projectNotes.wf?actiontype=forceClose&id=<%=id%>" target="_parent" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_close','','/wf/default/images/English/btn_forceClose_on.gif',1)">
            <img name="img_close" src="/wf/default/images/English/btn_forceClose.gif" border="0" alt="Force close the project">
          </a>
      <%
        }
      %>    	  
        </td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#1D459D"><img src="/wf/default/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          View project information.
        </td>
        <td class="smallest" align="right">
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projects/printableProjectNotes.jsp')">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/icon_print.gif" width="16" height="17" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projects/printableProjectNotes.jsp')">Printer Friendly</a>
        </td>
      </tr>
    </table>
    <!-----form starts here ----->

      <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table4">
              <tr>
                <td class="smallest">
                  <font color="#CC0000"><b>* Required field(s)</b></font>
                </td>
              </tr>
            </table>
          </td>
        </tr>

    <form name="projectInformation" action="<wf:property type="system" key="appbase"/>/projectNotes.wf" method="post" ID="Form1">
      <input type="hidden" name="actiontype" value="" ID="Hidden1">
        <wf:WebControl beanID = "projectNotesBean" 
                       controlBuilderName = "org.yang.customized.gtf.services.projectManager.web.ProjectNotesFormBuilder" />
    </form>
      </table>
  </body>
</html>