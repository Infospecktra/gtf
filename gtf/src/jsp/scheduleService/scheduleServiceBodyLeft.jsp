<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<html>
  <head>
    <title>Web Framework::leftFrame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <FRAMESET rows="*,32" border="0" framespacing="0" frameborder="no">
    <FRAME name="leftTopFrame"
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/scheduleService/projectList.jsp">
    <FRAME name="leftBottomFrame" 
           scrolling="NO" 
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/scheduleService/panel.jsp">
  </FRAMESET>
</HTML>
 