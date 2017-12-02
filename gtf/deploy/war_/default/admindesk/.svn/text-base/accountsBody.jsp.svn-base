<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<%
   String appBase = passport.getSystemProperty("appbase");
   String skin    = passport.getSystemProperty("skin");
   String right   = passport.removeRuntimeProperty("right");
   if(null==right)
   {  
      right = appBase + skin + "/admindesk/introduction.jsp";
   }
   else
   {
      right = appBase + right;
   }
%>

<HTML>
  <HEAD>
  <title>SWEE::Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </HEAD>
  <FRAMESET cols="27%,*" border="3" framespacing="2" bordercolor="#E9E9E9" frameborder="no">    
    <FRAME src="<wf:property type="system" key="appbase"/>/accountTree.wf?actiontype=load" name="leftFrame">
    <FRAME src=<%=right%> name="rightFrame">
  </FRAMESET>
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</HTML>

