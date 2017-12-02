<%@ page errorPage="../share/error.jsp" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>
<swapp:checkLogon desk="MainDesk" />
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>
<%
	//** will read incoming parameter -- msg

	String msg = request.getParameter("msg");
	if (msg == null)
		msg = messageBean.getKeyValue("logout.msg.null");
  	else if (msg.equals("nosession"))
  		msg = messageBean.getKeyValue("logout.msg.nosession");
  	else if (msg.equals("noservice"))
  		msg = messageBean.getKeyValue("logout.msg.noservice");
  	else
  		msg = "";
%>
<html>

<head>
	<title>SITEWare Enterprise :: Logout Screen</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<%--@ page contentType="text/html; charset=big5" --%>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Refresh" content="5;URL=<swapp:message key="appbase" /><swapp:message type="SYSTEM" key="page.login" />">
	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="window.history.forward()">

	<table width="100%" border="0" cellspacing="0" cellpadding="0" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/headpagebgL.gif">
	  <tr>
	    <td align="right"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" width="1" height="80"></td>
	    <!--<td align="right"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/newlogo.gif"></td>-->
	  </tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" background="../images/shadowbg.gif">
	  <tr>
	    <td align="right"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="5" width="1"></td>
	  </tr>
	</table>


	<div align="center">
	  <h3><font color="#1D459D"><%= msg %></font></h3>
	  <p><swapp:message key="logout.redirect"/><br>
	    <a href="<swapp:message type="SYSTEM" key="appbase" /><swapp:message type="SYSTEM" key="page.login" />"><swapp:message key="logout.click"/></a> <swapp:message key="logout.noreload"/>.
	  </p>
	</div>


</body>
</html>

<%
  if (session != null)
  {
      session.setAttribute("logout","true");
      session.invalidate();
      session = null;
  }
%>