<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="projectList" 
             class="org.yang.customized.gtf.services.scheduleManager.web.ScheduleServiceBean" 
             scope="session"/>

<html>
  <head>
    <title>Web Framework::panel</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>  
    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function setStay(frm,act)
      {
        fm = eval('top.mainFrame.leftFrame.leftTopFrame.document.'+ frm);
        fm.actiontype.value = act;

        sele = document.temp_form.stayhere;
        sele_idx = sele.selectedIndex;
        var v = sele.options[sele_idx].value;

        fm.stay.value = sele.options[sele_idx].value; // stay is a hidden field in addtable.jsp
        top.mainFrame.leftFrame.leftTopFrame.validateChkBox(frm,'cids',act);
      }
    </script>
  </head>
  <body bgcolor="#E9E9E9" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

  <form name="temp_form">
    <!-- 1 pixel line -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#999999">
      <tr><td><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1"></td></tr>
    </table>
    <table width="98%" align="right" border="0" cellspacing="0" cellpadding="5">
      <tr> 
      
        <td nowrap valign="top" align="right">
        <!--Delete-->
        <%if(projectList.gotPermit("ScheduleManager", projectList.getType(), "delete")){%>
          <a href="javascript:top.mainFrame.leftFrame.leftTopFrame.validateChkBox('projectlist_form','targetIds','delete')" 
             onMouseOut="MM_swapImgRestore()" 
             onMouseOver="MM_swapImage('img_delete','','../images/English/btn_delete_on.gif',1);">
          <img name="img_delete" 
               src="../images/English/btn_delete.gif" 
               border="0" 
               alt="Delete projects"></a>
        <%}%>
        <!--Add-->
        <%if(projectList.gotPermit("ScheduleManager", projectList.getType(), "activate")){%>
          <a href="javascript:top.mainFrame.leftFrame.leftTopFrame.validateChkBox('projectlist_form','targetIds','activate')" 
             onMouseOut="MM_swapImgRestore()" 
             onMouseOver="MM_swapImage('img_add','','../images/English/btn_activate_on.gif',1)">
          <img name="img_add" 
               src="../images/English/btn_activate.gif" 
               border="0" alt="Activate projects"></a>
        <%}%>
        <!--Help-->
          <a href="javascript:top.topFrame.openSWHelpWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/help/English/help.jsp?right=editor_add.htm')" 
             onMouseOut="MM_swapImgRestore()" 
             onMouseOver="MM_swapImage('img_help','','../images/English/btn_help_on.gif',1)">
          <img name="img_help" 
               src="../images/English/btn_help.gif" 
               border="0" 
               alt="Help"></a>
        </td>
        <td nowrap>&nbsp;&nbsp;</td>
        </tr>
      </table>
    </form>
  </body>
</html>
