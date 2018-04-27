<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
  <HEAD>
    <title>Order Tracker::Order Access Panel</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel=stylesheet type="text/css" href="/wf/default/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
  </HEAD>
  <BODY bgcolor="#eaeaea" 
        leftmargin="15" 
        topmargin="15" 
        marginwidth="5" 
        marginheight="5">
    <wf:WebControl beanID             = "calendar" 
                   controlBuilderName = "org.yang.customized.gtf.services.messageManager.web.CalendarBuilder" />
  </BODY>
</HTML>