<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id    = "scheduledTasks" 
             class = "org.yang.customized.gtf.services.scheduler.web.ScheduledTasksBean" 
             scope = "session"/>

<html>
  <head>
    <title>Schedule Service</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>

    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function confirmDel(frm,act,bool) 
      {
        if (bool==1) 
        {
          if (confirm('This is NOT recoverable.\nAre you sure?'))
            submitForm(frm,act);
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
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="print()">
      <wf:WebControl beanID             = "scheduledTasks" 
                     controlBuilderName = "org.yang.customized.gtf.services.scheduler.web.ScheduledTasksBuilder" />
  </body>
</html>