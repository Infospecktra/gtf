<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<html>
  <head>
    <title>Web Framework::leftFrame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <FRAMESET rows="60%,*" border="0" framespacing="0" frameborder="yes">
    <FRAME name="rightTopFrame"
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projects/masterTable.jsp" >
    <FRAME name="rightBottomFrame"
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projects/stageNotes.jsp" >
  </FRAMESET>
</HTML>