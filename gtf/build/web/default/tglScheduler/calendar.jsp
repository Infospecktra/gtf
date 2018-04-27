<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="calendar1" 
             class="org.yang.customized.gtf.services.tglScheduler.web.CalendarBean" 
             scope="session"/>

<HTML>
  <HEAD>
    <title>Scheduler::calendar</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel=stylesheet type="text/css" href="/wf/default/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" type="text/javascript">     
      function loadOverlook()
      { 
         parent.parent.rightFrame.location = 
         '<wf:property type="system" key="appbase"/>/scheduledTasks.wf?actiontype=load&calendarView=<%=calendar1.getCalendarView()+""%>&displayedYear=<%=calendar1.getDisplayedYear()%>&displayedMonth=<%=calendar1.getDisplayedMonth()%>&displayedDay=<%=calendar1.getDisplayedDay()%>' ; 
      }
    </script>
  </HEAD>
  <BODY bgcolor="#eaeaea" 
        leftmargin="15" 
        topmargin="15" 
        marginwidth="5" 
        marginheight="5" 
        onLoad="loadOverlook();">
    <wf:WebControl beanID             = "calendar1" 
                   controlBuilderName = "org.yang.customized.gtf.services.tglScheduler.web.CalendarBuilder" />
  </BODY>
</HTML>