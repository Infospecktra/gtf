<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>

<%@ page isErrorPage="true" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">

	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<br>

<table width="90%" border="0" cellspacing="0" cellpadding="8" align="center">
  <tr bgcolor="#EEEEEE">
    <td rowspan="7" valign="top" bgcolor="#FFFFFF"><!--<img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/err.gif" width="131" height="99">-->&nbsp;</td>
    <td width="100%"><b><swapp:message key="error.siteware_run_error"/></b></td>
  </tr>
  <tr>
    <td><b><font color="#CC0000"><%= exception %></font></b></td>
  </tr>
  <tr bgcolor="#EEEEEE">
    <td><b><swapp:message key="error.detail_message"/></b></td>
  </tr>
  <tr>
    <td class="smallest"><% exception.printStackTrace(new PrintWriter(out)); %></td>
  </tr>
  <tr bgcolor="#EEEEEE">
    <td><b><swapp:message key="error.what_to_do"/></b></td>
  </tr>
  <tr>
    <td class="smallest">1. <swapp:message key="error.note1.try_to"/> <a href="javascript:self.location.reload()"><swapp:message key="error.note1.reload_the_page"/></a> <swapp:message key="error.note1.first"/>.<br><br>
    	  2. <swapp:message key="error.note2"/>.<br>
        3. <swapp:message key="error.note3"/>.<br>
        4. <swapp:message key="error.note4.contact"/> <a href="mailto:siteware@screamingmedia.com"><swapp:message key="content_report.sourceid"/></a>.<br>
        5. <swapp:message key="error.note5.go_to"/> <a href="<swapp:message key="appbase"/><swapp:message key="skin"/>/share/top.jsp?go=<swapp:message key="skin"/>/maindesk/logout.jsp"><swapp:message key="error.note5.logout_siteware"/></a> <swapp:message key="error.note5.and_try_to_login_again"/>
      </td>
  </tr>
</table>

</body>
</html>