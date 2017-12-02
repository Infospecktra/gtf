<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
  <HEAD>
  <title>Projects Manager::Body</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </HEAD>
  <FRAMESET cols="27%,*" border="3" framespacing="2" bordercolor="#E9E9E9" frameborder="yes">    
    <FRAME src="<wf:property type="system" key="appbase"/>/reportsPanel.wf?actiontype=load" 
           name="leftFrame">
    <FRAME src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/reports/reportsBodyRight.jsp"
           name="rightFrame">
  </FRAMESET>
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</HTML>

