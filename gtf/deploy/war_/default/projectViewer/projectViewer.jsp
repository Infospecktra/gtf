<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<html>
  <head>
    <title>Project Viewer</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/ns_reload.js" type="text/javascript"></script>
  </head>

  <frameset rows="87,*" frameborder="no" border="0" framespacing="0" onLoad="window.history.forward()">
    <frame name="topFrame" 
           scrolling="NO" 
           noresize 
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/maindesk/head.jsp" >
    <frame name="mainFrame" 
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projectViewer/projectViewerBody.jsp">
  </frameset>

  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>
