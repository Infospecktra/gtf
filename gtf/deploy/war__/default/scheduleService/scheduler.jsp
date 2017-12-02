<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>
<%

    String domain = passport.getDomain();
	String username = passport.getUsername();
	if("".equals(domain)&&"".equals(username))
       response.sendRedirect("/wf/login.wf?msg=authendicationNeed");
%>
<html>
  <head>
    <title>Scheduler</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/ns_reload.js" type="text/javascript"></script>
  </head>

  <frameset rows="87,*" frameborder="no" border="0" framespacing="0" onLoad="window.history.forward()">
    <frame name="topFrame" 
           scrolling="NO" 
           noresize 
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/maindesk/head.jsp" >
    <frame name="mainFrame" 
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/scheduler/schedulerBody.jsp">
  </frameset>

  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>
