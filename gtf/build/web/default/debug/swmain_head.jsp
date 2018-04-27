<%@ page errorPage="../share/error.jsp" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>

<html>
<head>
<title>MainDesk Head</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">
	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/headpagebg<swapp:message key="" logo="SHOW"/>.gif">

<%@ include file="../share/servicebar.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#E9E9E9">
  <tr>
	<td><img src="../images/../images/1PIX.gif" height="1"></td>
  </tr>
</table>

  <table width="100%" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td width=100%>&nbsp;</td>
      <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" width="1" height="40"></td>
    </tr>
  </table>
<!----- shadow-------->
<table width="100%" border="0" cellspacing="0" cellpadding="0" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/shadowbg.gif">
  <tr>
    <td align="right"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="8" width="1"></td>
  </tr>
</table>


</body>
</html>
