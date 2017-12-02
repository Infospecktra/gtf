<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id    = "passport" 
             class = "org.yang.web.applicationContainer.PassportBean" 
             scope = "session"/>

<%
   String projectType = passport.removeRuntimeProperty("projectType");
   String appbase     = passport.getSystemProperty("appbase");
   String right       = passport.removeRuntimeProperty("right");
   
   if(null==right)
      right = "/wf/stageNotes.wf?actiontype=enter";
   else
      right = appbase + right;
      
   if(null!=projectType )
   {
      right = right + "&projectType=" + projectType;
      //System.out.println("///////////////////////////////////////////////" + right);
   }
%>

<HTML>
  <HEAD>
  <title>Projects Manager::Body</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </HEAD>
  <FRAMESET cols="18%,*" border="3" framespacing="2" bordercolor="#E9E9E9" frameborder="yes">    
    <FRAME src="<wf:property type="system" key="appbase"/>/projectsTree.wf?actiontype=load" 
           name="leftFrame">
    <FRAME src=<%=right%> 
           name="rightFrame">
  </FRAMESET>
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</HTML>

