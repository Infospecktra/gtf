<%@ page errorPage="../share/error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.common.buslogic.userMan.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>
<%@ page import = "com.screamingmedia.siteware.webService.globle.*" %>
<%@ page import = "com.screamingmedia.siteware.services.alertService.*" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>

<swapp:checkLogon desk="MainDesk" />

<jsp:useBean id="m_userForm" class="com.screamingmedia.siteware.webService.userAdmin.UserForm" scope="session"/>
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>
<html>
<head>
<title>User Conf for Admin</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<meta http-equiv="Expires" content="0">
	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/form_action.js" type="text/javascript"></script>

	<script language="JavaScript" type="text/javascript">
		//---------------------------------------
		function submitForm(frm,act) {
			fm = eval('document.'+ frm);
			fm.actiontype.value = act;
			fm.submit();
		}
		//---------------------------------------
		function validateForm(frm,act,ary) {
			if (act=='CREATE') {
				if (checkFieldRequired(frm,ary) && checkPass(frm) && checkExist(frm))
					submitForm(frm,act);
			}
			else {
				if (checkFieldRequired(frm,ary) && checkPass(frm))
					submitForm(frm,act);
			}
		}

		//---------------------------------------
		function checkPass(frm)	{
			fm = eval('document.'+ frm);
			v1 = fm.password.value;
			v2 = fm.confirmpass.value;
			if (v1 != v2) {
				alert('<swapp:message key="m_usertable.alert_js1"/>');
				fm.confirmpass.value = '';
				return false;
			}
			else
				return true;
		}
	</script>

	<script language="JavaScript1.1" type="text/javascript">
		var reqA = new Array("id","password","fname","lname","email");
		var notAllowedChar = new Array(" ","|","'","\"","#","%","&","@");
	</script>

</head>

<body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()">
<a name="top"></a>

<center>
<p><a href="<swapp:message key="appbase"/><swapp:message key="skin"/>/maindesk/swmain_body.jsp"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/back_btn.gif" border="0" alt="Back to Main Page" align="absmiddle"> <swapp:message key="m_usertable.back_to_main_desk"/></a>
	<font color="#cc0000">
	<br><jsp:getProperty name="m_userForm" property="msg"/></font>
</p>
</center>

<form name="user_form" action="<swapp:message key="appbase"/>/m_user.scrm" method="post">
<input type="hidden" name="actiontype" value="<jsp:getProperty name="m_userForm" property="actiontype"/>">


  <table width="70%" border="0" cellspacing="3" cellpadding="2" align="center">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="smallest">
           		<font color="#CC0000"><b><swapp:message key="m_usertable.notice"/></b></font>
            </td>
            <td align="right" valign="bottom" nowrap class="smallest"><a href="#basic"><swapp:message key="usertable.columnshort1"/></a> | <a href="#extra"><swapp:message key="usertable.columnshort2"/></a></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td  background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/bar_bk.gif" colspan="2" class="bigger"><font color="#FFFFFF"><b><a name="basic"></a><swapp:message key="usertable.basic_information"/></b></font></td>
    </tr>
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center" width="100%">
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.userid"/></b></td>
            <td nowrap>
            	<b><font color="#CC0000"><jsp:getProperty name="m_userForm" property="id"/></font></b>
            	<input type="hidden" name="id" value="<jsp:getProperty name="m_userForm" property="id"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.password"/></b></td>
            <td nowrap>
              <input type="password" name="password" size="20" maxlength="20" value="<jsp:getProperty name="m_userForm" property="password"/>">
              * </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><b><swapp:message
                key="usertable.confirm_password"/> &nbsp;&nbsp;</b></td>
            <td nowrap>
              <input type="password" name="confirmpass" size="20" maxlength="20" value="<jsp:getProperty name="m_userForm" property="password"/>" onBlur="checkPass('user_form')"> * </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><b><swapp:message
                key="usertable.firstname"/></b></td>
            <td nowrap>
              <input type="text" name="fname" size="20" value="<jsp:getProperty name="m_userForm" property="fname"/>">
              * </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.lastname"/></b></td>
            <td nowrap>
              <input type="text" name="lname" size="20" value="<jsp:getProperty name="m_userForm" property="lname"/>">
              * </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.address1"/></td>
            <td nowrap>
              <input type="text" name="addr1" size="25" value="<jsp:getProperty name="m_userForm" property="addr1"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.address2"/></td>
            <td nowrap>
              <input type="text" name="addr2" size="25" value="<jsp:getProperty name="m_userForm" property="addr2"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.city"/></td>
            <td nowrap>
              <input type="text" name="city" size="25" value="<jsp:getProperty name="m_userForm" property="city"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.state"/></td>
            <td nowrap>
              <input type="text" name="usstate" size="20" value="<jsp:getProperty name="m_userForm" property="usstate"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.postal_code"/></td>
            <td nowrap>
              <input type="text" name="zipcode" size="10" maxlength="10" value="<jsp:getProperty name="m_userForm" property="zipcode"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.country"/></td>
            <td nowrap>
              <input type="text" name="country" size="25" value="<jsp:getProperty name="m_userForm" property="country"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.telephone_number"/></td>
            <td nowrap>
              <input type="text" name="tele" size="15" value="<jsp:getProperty name="m_userForm" property="tele"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.fax_number"/></td>
            <td nowrap>
              <input type="text" name="fax" size="15" value="<jsp:getProperty name="m_userForm" property="fax"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.mobile_number"/></td>
            <td nowrap>
              <input type="text" name="mobile" size="15" value="<jsp:getProperty name="m_userForm" property="mobile"/>">
            </td>
          </tr>
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.email"/></b></td>
            <td nowrap>
              <input type="text" name="email" size="25" value="<jsp:getProperty name="m_userForm" property="email"/>">
              * </td>
          </tr>
          <!--
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.template_set"/></b></td>
            <td nowrap>
              <select name="template" size="1">
                <option value="Template 1" <%if("Template 1".equals(m_userForm.getTemplate())){%>selected<%}%>>Template 1</option>
                <option value="Template 2">Template 2</option>
                <option value="Template 3">Template 3</option>
              </select>
              * </td>
          </tr>
          -->
          <tr>
            <td nowrap class="bigger"><b><swapp:message key="usertable.language_set"/></b></td>
            <td nowrap>
              <select name="language" size="1">
						<%
							Collection clang = m_userForm.getAllLanguages();
							Iterator ilang = clang.iterator();
							while (ilang.hasNext()) {
								String ln = (String)ilang.next();
								%>
								<option value="<%=ln%>" <%if(ln.equals(m_userForm.getLanguage())){%>selected<%}%>><%=ln%></option>
								<%
							}
						%>
              </select>
              * </td>
          </tr>

          <tr>
            <td nowrap class="bigger"><swapp:message key="usertable.description"/></td>
            <td nowrap>
              <textarea name="desc" cols="25" rows="3" wrap="VIRTUAL"><jsp:getProperty name="m_userForm" property="desc"/></textarea>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><b><a href="#top"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/top.gif" border="0" alt="<swapp:message key="usertable.basic_information.alt1"/>"></a></b></td>
            <td valign="top" align="right">
              <%if(!"".equals(m_userForm.getId())){%>
              <a href="javascript:validateForm('user_form','UPDATE',reqA)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_save','','<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_save_on.gif',1)"><img name="img_save" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_save.gif" border="0" alt="<swapp:message key="usertable.basic_information.alt2"/>"></a>&nbsp;
              <!----<input class="actionbtn" type="button" name="save" value="Update User" onClick="return validateForm('user_form','UPDATE',reqA)">------->
              <%}%>
              <!--
              <a href="javascript:clearForm('user_form')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_reset','','<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_reset_on.gif',1)"><img name="img_reset" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_reset.gif" border="0" alt="Create new user"></a>&nbsp;
              -->
              <!---<input class="removebtn" type="button" name="reset" value="Reset" onClick="clearForm('user_form')">---->
              <a href="javascript:;" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_help','','<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_help_on.gif',1)"><img name="img_help" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_help.gif" border="0" alt="<swapp:message key="usertable.basic_information.alt3"/>"></a>&nbsp;
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr bgcolor="#000066">
      <td class="bigger" background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/bar_bk.gif"><font color="#FFFFFF"><b><a name="extra"></a><swapp:message key="usertable.service_details"/></b></font></td>
    </tr>
    <tr>
      <td>
			<%@ include file="m_userserviceinfo.jsp" %>
      </td>
    </tr>
    <tr align="right" valign="top">
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr valign="top">
            <td class="smallest"><b><a href="#top"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/top.gif" border="0" alt="<swapp:message key="usertable.service_details.alt1"/>"></a></b></td>
            <td valign="top" align="right">
              <%if(!"".equals(m_userForm.getId())){%>
              <a href="javascript:validateForm('user_form','UPDATE',reqA)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_save2','','<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_save_on.gif',1)"><img name="img_save2" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_save.gif" border="0" alt="<swapp:message key="usertable.basic_information.alt2"/>"></a>&nbsp;
              <%}%>
              <a href="javascript:;" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('img_help3','','<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_help_on.gif',1)"><img name="img_help3" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/<swapp:message key="siteware.lang"/>/btn_help.gif" border="0" alt="<swapp:message key="usertable.service_details.alt2"/>"></a>&nbsp;
            </td>

          </tr>
        </table>
      </td>
    </tr>
  </table>

</form>

</body>
</html>

<script language="JavaScript">
	formTextNoEnter(document.user_form);
</script>


