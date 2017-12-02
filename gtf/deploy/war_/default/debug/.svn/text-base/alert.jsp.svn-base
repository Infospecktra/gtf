<%@ page errorPage="../share/error.jsp" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>
<jsp:useBean id="serviceBar" class="com.screamingmedia.siteware.webService.globle.ServiceBar" scope="session"/>
<html>

<head>
	<title><swapp:message key="alert.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">

	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">

	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/form_action.js" type="text/javascript"></script>

	<script language="JavaScript">
		//self.moveTo(0,0);
		//self.resizeTo(screen.availWidth,screen.availHeight);

	</script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="window.history.forward()">

<table width="100%" border="0" cellspacing="0" cellpadding="0" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/headpagebg<swapp:message key="" logo="SHOW"/>.gif">
  <tr>
    <td align="right" valign="top">&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br></td>
  </tr>
</table>
<!----- shadow-------->
<table width="100%" border="0" cellspacing="0" cellpadding="0" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/shadowbg.gif">
  <tr>
    <td align="right"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="5" width="1"></td>
  </tr>
</table>
<!----- main content -------->
<div align="center">
	<h5><font color="#CC0000"><b><swapp:message key="alert.u_got_msg"/></b></font></h5>
<swapp:alertMessage sender="Console" domain="<%=(String)session.getAttribute(SessionPhrase.LOGIN_DOMAIN)%>" userID="<%=serviceBar.getUserID()%>">
 		<table border="0" cellspacing="5" cellpadding="0" align="center">
        <td align="center"></b></td>
      <tr>
      	<td bgcolor="#1D459D"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
      </tr>
      <tr>
      	<td class="smallest" align="center">&nbsp;</td>
      </tr>

		<tr>
			<td>
		      <table border="1" cellspacing="0" cellpadding="5" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
		      <tr>
		        <td align="right" class="bigger"><b>Type:</b></td>
		        <td>
		        	<%=message.getType()%>
		        </td>
		      </tr>
		      <tr>
		        <td align="right" class="bigger"><b>Code:</b></td>
		        <td>
		        	<%=message.getCodeNum()%>
		        </td>
		      </tr>
		      <tr>
		        <td align="right"  class="bigger"><b>Time:</b></td>
		        <td>
		        	<%=message.getMonth()+"." +message.getDay()+"."+message.getYear()+":"+message.getTime() +"&nbsp" + message.getTimeZone()%>
		        </td>
		      </tr>
		      <tr>
		        <td align="right"  class="bigger"><b>Source:</b></td>
		        <td>
		        	<%=message.getSourceID()%>
		        </td>
		      </tr>		      
		      <tr>
		        <td align="right"  class="bigger"><b>UserID:</b></td>
		        <td>
		        	<%=message.getUserID()%>
		        </td>
		      </tr>		      		      
		      <tr>
		        <td align="right"  class="bigger"><b>Message:</b></td>
		        <td>
		        	<%=message.getMessage()%>
		        </td>
		      </tr>		      			      
		      </table>
      	</td>
      </tr>
      <tr>
      	<td bgcolor="#1D459D"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
</swapp:alertMessage>    
</div>

</body>
</html>
