<%@ page errorPage="../share/error.jsp" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>

<%
	//** will read incoming parameter -- msg

	String msg = WebUtil.nullFilter(request.getParameter("msg"));

%>
<html>

<head>

	<title>System Error Message</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">

	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">

	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/form_action.js" type="text/javascript"></script>

</head>

<body bgcolor="#EFEFEF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="window.history.forward()">


            <table border="0" cellspacing="0" cellpadding="0" width="100%">
              <tr>
                <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/headpagebgL.gif"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" width="1" height="80"></td>
              </tr>
              <tr>
                <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/shadowbg.gif" ><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="5" width="1"></td>
              </tr>
              <tr>
                <td bgcolor="#FFFFFF">

								<table width="100%" border="0" cellspacing="0" cellpadding="15" align="center">
								<tr>
									<td rowspan="2" valign="top" bgcolor="#EFEFEF"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/erricon.gif"></td>
									<td align="left" class="title" nowrap><font color="#CC0000"><swapp:message key="errorinfo.title"/></font></td>
								</tr>
								<tr>
									<td valign="top" width="100%"><%=msg%></td>
								</tr>
								</table>
                </td>
              </tr>
            </table>


</body>
</html>
