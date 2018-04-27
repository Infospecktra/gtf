<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<html>
  <head>
    <title>SELECT ADD CONTENT</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>  
    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function submitForm(frm,act)
      {
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
  <body bgcolor="#FFFFFF" leftmargin="15" topmargin="15" marginwidth="5" marginheight="5">
    <wf:WebControl beanID = "scheduledProjectList" 
                   controlBuilderName = "org.yang.customized.gtf.services.scheduler.web.ScheduledProjectListBuilder" />
  </body>
</html>