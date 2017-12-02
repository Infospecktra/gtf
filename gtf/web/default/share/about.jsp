<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>

<%@ page import = "java.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.globle.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>

<jsp:useBean id="serviceBar" class="com.screamingmedia.siteware.webService.globle.ServiceBar" scope="session"/>
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>

<%Properties p = serviceBar.getVersionInfo();%>

<%
	Properties sysPro = System.getProperties();
	Runtime runt = Runtime.getRuntime();
	System.gc();

//	System.out.println("p="+p);
//	System.out.println("syspro="+sysPro);

%>
<html>
<head>
<title><%=p.getProperty("pm.page.title","About Actrellis")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
<script language="JavaScript">
		self.moveTo(screen.availWidth/2-250,100);
		self.resizeTo(450,420);
		self.focus();
</script>
</head>

<body bgcolor="#F9F9F9" text="#000000">
<table width="100%" border="0" cellspacing="0" cellpadding="10">
  <tr>
    <td valign="top"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/alogo.gif"></td>
    <td width="100%">
			<font color="#990000"><b><%=p.getProperty("pm.product.info","Product Information:")%></b></font><br>
			<%=p.getProperty("product","Actrellis Integration Server")%><br>
			<%=p.getProperty("version","unknown")%><br>
			<%=p.getProperty("copyright","ScreamingMedia Inc.")%><br>
			<br>
			<font color="#990000"><b><%=p.getProperty("pm.license.info","This product is licensed to:")%></b></font><br>
			<%=p.getProperty("licenseto1","unknown")%><br>
			<%=p.getProperty("licenseto2","unknown")%><br>

		<br>
		<hr>
			<font color="#990000"><b><%=p.getProperty("pm.system.info","System Information:")%></b></font><br>
			<table border="0" cellspacing="0" cellpadding="4" bgcolor="#FFFFFF">
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.jvm.version","JVM version:")%></td>
				<td class="smallest"><%=sysPro.getProperty("java.vm.version")%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.jvm.vendor","JVM vendor:")%></td>
				<td class="smallest"><%=sysPro.getProperty("java.vm.vendor")%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.os.name","OS name:")%></td>
				<td class="smallest"><%=sysPro.getProperty("os.name")%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.os.version","OS version:")%></td>
				<td class="smallest"><%=sysPro.getProperty("os.version")%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.os.arch","OS architecture:")%></td>
				<td class="smallest"><%=sysPro.getProperty("os.arch")%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.total.memory","Total memory:")%></td>
				<td class="smallest"><%=runt.totalMemory()%></td>
			</tr>
			<tr>
				<td nowrap class="smallest"><%=p.getProperty("pm.free.memory","Free memory:")%></td>
				<td class="smallest"><%=runt.freeMemory()%></td>
			</tr>
			</table>

    </td>
  </tr>
</table>
</body>
</html>
