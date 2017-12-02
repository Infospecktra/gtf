<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" 
             class="org.yang.web.applicationContainer.PassportBean" 
             scope="session"/>
             
<jsp:useBean id="projectListMenu" class="org.yang.customized.gtf.services.projectViewer.web.ProjectListMenuBean" scope="session"/>
<jsp:useBean id="projectMasterBean" class="org.yang.customized.gtf.services.projectViewer.web.ProjectViewerMasterBean" scope="session"/>


<%
   String appBase = passport.getSystemProperty("appbase");
   String skin    = passport.getSystemProperty("skin");
   String right   = passport.removeRuntimeProperty("right");
   String viewMode = projectListMenu.getViewMode();
   projectMasterBean.setDisplayProjects(projectListMenu.getAllAvailableProjects());
   right = appBase + "/projectViewerMaster.wf?actiontype=loadMasterTableData&&viewMode="+ viewMode ;
   //System.out.println("last action type ---------------------------------------->" + projectListMenu.getLastActiontype());
   //System.out.println("right ---------------------------------------->" + right);
   
%>

<html>
  <head>
  <title>SWEE::project viewer</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function submitForm(frm,act)
      {
        alert('act='+act); 
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }  
      //---------------------------------------
      function validateChkBox(frm,ck,act) {     
        if (isCheckBoxChecked(frm,ck)) {
          submitForm(frm,act);
        }
        else
          alert('Please select a project and try again.');
      }

       </script>  
    </head>
  <frameset cols="36%,*" border="3" framespacing="3" bordercolor="#E9E9E9" frameborder="yes">
    <frame name="leftFrame"
           src="<wf:property type="system" key="appbase"/>/projectListMenu.wf?actiontype=displayProjectList" >
    <frame name="rightFrame"
           src="<%=right%>" >
  </frameset>
  
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>

