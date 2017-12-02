<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<%@ page import="java.util.*" %>
<%@ page import = "org.yang.web.controller.*" %>
<%@ page import = "org.yang.services.accountmgr.*" %>

<jsp:useBean id="userContact" class="org.yang.web.services.serviceManager.UserInformationBean" scope="session"/>
<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<html>
  <head>
    <title>User Conf for Admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>

    <script language="JavaScript" type="text/javascript">

      //---------------------------------------
      function submitForm(frm,act) {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }
      
      //---------------------------------------
      function validateForm(frm,act,ary) {
        if (checkFieldRequired(frm,ary) && checkPass(frm))
          submitForm(frm,act);
      }

      //---------------------------------------
      function checkPass(frm)  {
        fm = eval('document.'+ frm);
        v1 = fm.userDatas_password.value;
        v2 = fm.userDatas_confirmpass.value;
        if (v1 != v2) {
          alert('Passwords do not match.');
          fm.userDatas_confirmpass.value = '';
          return false;
        }
        else
          return true;
      }

    </script>
    <script language="JavaScript1.1" type="text/javascript">
      var reqA = new Array("userDatas_password" ,"userDatas_confirmpass" ,"userDatas_firstname","userDatas_lastname","userDatas_email");
      var notAllowedChar = new Array(" ","|","'","\"","#","%","&","@","-");
    </script>
    
  </head>
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()">
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td class="title" align="left"><font color="#1D459D">My Contact Information</font></td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#1D459D"><img src="/wf/default/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          View individual user information.
        </td>
      </tr>
    </table>
    <p><font color="#cc0000"><b></b></font></p>
    <!-----form starts here ----->
    <table width="70%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table4">
            <tr>
              <td class="smallest">
                <font color="#CC0000"><b>* Required field(s)</b></font>
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <wf:WebControl beanID = "userContact" controlBuilderName = "org.yang.web.services.serviceManager.UserContactInfoBuilder" />

    </table>
  </body>
</html>
<script language="JavaScript">
  formTextNoEnter(document.user_form);
</script>