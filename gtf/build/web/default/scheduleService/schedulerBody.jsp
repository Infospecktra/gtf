<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" 
             class="org.yang.web.applicationContainer.PassportBean" 
             scope="session"/>

<%
   String appBase = passport.getSystemProperty("appbase");
   String skin    = passport.getSystemProperty("skin");
   String right   = passport.removeRuntimeProperty("right");
   String type    = passport.removeRuntimeProperty("type");

   if(null==right)
   {
      right = appBase + skin + "/scheduler/blank.html";
      //right = appBase + "/scheduledTasks.wf?actiontype=clear";
   }
   else
      right = appBase + right;

   if(null!=type)
   {
      right = right + "&type=" + type;
   }
%>

<html>
  <head>
  <title>SWEE::Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <frameset cols="20%,*" border="3" framespacing="3" bordercolor="#E9E9E9" frameborder="yes">
    <frame name="leftFrame"
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/scheduler/schedulerBodyLeft.jsp">
    <frame name="rightFrame"
           src="<%=right%>" >
  </frameset>
  
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>

