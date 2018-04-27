<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<%@ page import="java.util.*" %>
<%@ page import = "org.yang.web.controller.*" %>
<%@ page import = "org.yang.services.accountmgr.*" %>

<jsp:useBean id="userBean" class="org.yang.web.services.accountManage.UserBean" scope="session"/>
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
      function confirmDel(frm,act,bool) {
        if (bool==1) {
          if (confirm('This is NOT recoverable.\nAre you sure?'))
            submitForm(frm,act);
        }
        else
          submitForm(frm,act);
      }

      //---------------------------------------
      function submitForm(frm,act) {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }
      
      //---------------------------------------
      function validateForm(frm,act,ary,id) {
        if (act=='create') {
        var id2  = '';
        for(i=0;i<id.length;i++)
        {
           if(id[i]!='')
              id2 += id[i];
        }  
        if(id != id2)
          alert('invalid characters in id.');
        if (checkFieldRequired(frm,ary) && checkPass(frm) && checkExist(frm))
          submitForm(frm,act);
        }
        else {
          if (checkFieldRequired(frm,ary) && checkPass(frm))
            submitForm(frm,act);
        }
      }

      //---------------------------------------
      function checkPass(frm)  {
        fm = eval('document.'+ frm);
        v1 = fm.pass.value;
        v2 = fm.confirmpass.value;
        if (v1 != v2) {
          alert('Passwords do not match.');
          fm.confirmpass.value = '';
          return false;
        }
        else
          return true;
      }
    
      //---------------------------------------
      function checkExist(frm) {
         var allIDs = new Array;
         <% //** all users list
            Collection tempC = userBean.getAllUsers();
            Iterator tempI = tempC.iterator();
            int iii=0;
            while (tempI.hasNext()) {
               User tempU = (User)tempI.next();
            }
         %>
         fm = eval('document.'+ frm);
         v1 = fm.id.value;
         for (i=0; i<allIDs.length; i++) {
            if (v1 == allIDs[i]) {
               alert('<swapp:message key="usertable.alert_js3"/>');
               return false;
            }
         }
         return true;
      }
    </script>
    <script language="JavaScript1.1" type="text/javascript">
      var reqA = new Array("id","pass","firstname","lastname","email");
      var notAllowedChar = new Array(" ","|","'","\"","#","%","&","@","-");
    </script>
  </head>
  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()">
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <%
          String id = userBean.getCurrentUser().getID();
          if(null==id||"".equals(id))
            id = "N/A";
        %>
        <td class="title" align="left"><font color="#1D459D">User Configuration (<%=id%>)</font></td>
        <td align="right">
          <a href="javascript:confirmDel('user_form','removeUser',1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_remove','','/wf/default/images/English/btn_remove_on.gif',1)">
            <img name="img_remove" src="/wf/default/images/English/btn_remove.gif" border="0" alt="Remove user"></a>
        </td>
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
    <form name="user_form" action="<wf:property type="system" key="appbase"/>/user.wf" method="post" ID="Form1">
      <input type="hidden" name="actiontype" value="" ID="Hidden1"> <a name="top"></a>
      <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">
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

        <wf:WebControl beanID = "userBean" controlBuilderName = "org.yang.web.services.accountManage.UserInfoAccessFormBuilder" />

        <wf:WebControl beanID = "userBean" controlBuilderName = "org.yang.web.services.accountManage.UserGroupInfoBuilder" />

        <wf:WebControl beanID = "userBean" controlBuilderName = "org.yang.web.services.accountManage.DefaultServiceSetupFormBuilder" />

      </table>
    </form>
  </body>
</html>
<script language="JavaScript">
  formTextNoEnter(document.user_form);
</script>