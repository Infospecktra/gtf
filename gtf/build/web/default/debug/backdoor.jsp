<%@ page errorPage="../share/error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.common.buslogic.contentMan.*" %>
<%@ page import = "com.screamingmedia.swapi.core.content.*" %>
<%@ page import = "com.screamingmedia.swapi.core.content.html.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>


<jsp:useBean id="backDoorForm" class="com.screamingmedia.siteware.webService.mainDesk.BackDoorForm" scope="session"/>
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>

<html>
<head>
<title>SELECT ADD CONTENT</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">
	
</head>

<body bgcolor="#FFFFFF">

<h4><font color="#CC0000"><b><jsp:getProperty name="backDoorForm" property="msg"/></b></font></h4>

<!------------------------------------------------>
<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">

<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
	<tr>
		<td>Domain:</td>
		<td><input type="text" name="v1" size="30"><br></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Reset Domain Config"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
	<tr>
		<td>Domain:</td>
		<td><input type="text" name="v1" size="30"><br></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Delete Publish History"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Show Tasks"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">

	<tr>
		<td>Domain:</td>
		<td><input type="text" name="v1" size="30"><br></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Clear Login Table"></td>
	</tr>
</table>
</form>


<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
	<tr>
		<td>Domain:</td>
		<td><input type="text" name="v1" size="30"><br></td>
	</tr>

	<tr>
		<td>Source:</td>
		<td><input type="text" name="v2" size="30"><br></td>
	</tr>

	<tr>
		<td>Section:</td>
		<td><input type="text" name="v3" size="30"><br></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Drop Table"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Show Config"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="Verify Database Connection"></td>
	</tr>
</table>
</form>

<form name="backdoor_form"  action="<swapp:message key="appbase"/>/backdoor.scrm" method="post">
<table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">

	<tr>
		<td colspan="2" align="center"><input class="actionbtn" type="submit" name="actiontype" value="TEST"></td>
	</tr>
</table>
</form>


<p><a href="<swapp:message key="appbase"/><swapp:message key="skin"/>/maindesk/swmain.jsp">back to siteware</a></p>




</body>
</html>

