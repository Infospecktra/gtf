<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<html>
  <head>
    <title>Reload all pages</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <script language="JavaScript">
      top.mainFrame.location='<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/admindesk/accountsBody.jsp?time=<%=passport.getCurrentTime()%>';
    </script>
  </head>
  <body>
  </body>
</html>