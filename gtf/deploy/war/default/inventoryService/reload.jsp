<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<html>
  <head>
    <title>Reload all pages</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <script language="JavaScript">
      top.mainFrame1.location='<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/inventoryServiceBody.jsp?time=<%=passport.getCurrentTime()%>';
    </script>
  </head>
  <body>
  </body>
</html>