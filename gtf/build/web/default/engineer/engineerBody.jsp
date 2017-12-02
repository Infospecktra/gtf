<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<%
   String appBase = passport.getSystemProperty("appbase");
   String skin    = passport.getSystemProperty("skin");
   String right   = passport.removeRuntimeProperty("right");

   if(null==right)
      right = appBase + skin + "/engineer/introduction.jsp";
   else
      right = appBase + right;
%>

<html>
  <head>
  <title>engineer body</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <frameset cols="35%,*" border="3" framespacing="3" bordercolor="#E9E9E9" frameborder="yes">
    <frame name="leftFrame"
           src="<wf:property type="system" key="appbase"/>/domainTree.wf?actiontype=load" >
    <frame name="rightFrame"
           src="<%=right%>" >
  </frameset>
  
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>