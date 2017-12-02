<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="serviceBean" class="org.yang.web.services.accountManage.ServiceBean" scope="session"/>
<jsp:useBean id    = "passport" 
             class = "org.yang.web.applicationContainer.PassportBean" 
             scope = "session"/>
<%

      passport.switchService();	

%>
<html>
  <head>
    <title>Service Detail</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
      <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
      <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
      <script language="JavaScript" type="text/javascript">
        //---------------------------------------
        function submitForm(frm,act) {
          fm = eval('document.'+ frm);
          fm.actiontype.value = act;
          fm.submit();
        }
 
     </script>
  </head>
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" >
    <a name="top"></a>
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td class="title" align="left"><font color="#1D459D">Service Details</font></td>
      </tr>
      <tr>
        <td bgcolor="#1D459D"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          Service
        </td>
      </tr>
    </table>
    <form name="service_form" action="<wf:property type="system" key="appbase"/>/service.wf" method="post" ID="Form1">
      <input type="hidden" name="actiontype" value="" ID="Hidden1"> 
      <input type="hidden" name="gid" value="<jsp:getProperty name="serviceBean" property="gid"/>" ID="Hidden2">
      <input type="hidden" name="sid" value="<jsp:getProperty name="serviceBean" property="sid"/>" ID="Hidden3">
      <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table4">
              <tr>
                <td nowrap class="bigger"><b>Report Manager</b> of group <font color="#CC0000"><b>ENGINEER</b></font></td>
              </tr>
            </table>
          </td>
        </tr>
        
        <wf:WebControl beanID = "serviceBean" controlBuilderName = "org.yang.web.services.accountManage.ServiceAreaInfoBuilder" />
        <wf:WebControl beanID = "serviceBean" controlBuilderName = "org.yang.web.services.accountManage.AreaConfigurationFormBuilder" />   
    
      </table>
    </form>
  </body>
</html>