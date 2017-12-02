<%@ page errorPage="../share/error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.globle.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>

<%
	String errmsg = WebUtil.nullFilter(request.getParameter("errmsg"));
%>

<%if(errmsg!=null && !"".equals(errmsg)){
	String errMsgWin = messageBean.getSystemValue("appbase")+messageBean.getSystemValue("skin")+"/maindesk/errorinfo.jsp?msg=" + java.net.URLEncoder.encode(errmsg);
	%>
	<script language="JavaScript">
		errmsgwin = window.open('<%=errMsgWin%>',"ERRMSGWIN","width=400,height=300,scrollbars=yes,menubar=no,toolbar=no,status=no,resizable=no");
		errmsg.focus();
	</script>
<%}%>

<% String defaultServ = (String)session.getAttribute(SessionPhrase.DEFAULT_SERVICE); %>

<%if(defaultServ!=null && !"".equals(defaultServ)){%>

	<script language="JavaScript">
		top.document.location = '<%=(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page."+defaultServ))%>';
	</script>
	<%
	session.removeAttribute(SessionPhrase.DEFAULT_SERVICE);

}else{%>

	<swapp:checkLogon desk="MainDesk" />

	<html>
	<head>
	<title><swapp:message key="swmain.siteware_manager_title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
		<meta http-equiv="Expires" content="0">
	</head>

	<frameset rows="87,*" frameborder="no" border="0" framespacing="0">
	  <frame name="topFrame" scrolling="NO" noresize src="<swapp:message key="appbase"/><swapp:message key="skin"/>/maindesk/swmain_head.jsp">
	  <frame name="mainFrame" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/maindesk/swmain_body.jsp">
	</frameset>

	<noframes>
	<body bgcolor="#FFFFFF">
	<p>Your browser doesn't support frame.</p>
	</body>
	</noframes>
	</html>

<%}%>