<%@ page import = "com.screamingmedia.siteware.webService.common.buslogic.userMan.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>
<swapp:checkLogon desk="MainDesk" />

<% User uu = (User)session.getAttribute(SessionPhrase.LOGIN_USER); %>
<html>
<head>
<title>SITEWare :: MainDesk</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">
</head>

<frameset rows="87,*" frameborder="no" border="0" framespacing="0">
  <frame name="topFrame" scrolling="NO" noresize src="<swapp:message key="appbase"/><swapp:message key="skin"/>/maindesk/swmain_head.jsp">
  <frame name="mainFrame" src="<swapp:message key="appbase"/>/m_user.scrm?id=<%=uu.getID()%>&actiontype=LOAD">
</frameset>

<noframes>
<body bgcolor="#FFFFFF">
<p>Your browser doesn't support frame.</p>
</body>
</noframes>
</html>
