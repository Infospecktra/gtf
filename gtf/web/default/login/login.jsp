<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="passport" class="org.yang.web.applicationContainer.PassportBean" scope="session"/>

<%
   //** will read incoming parameter -- msg
   String msg = request.getParameter("msg");
   if("nosession".equals(msg))
     msg = passport.getSystemProperty("login.nosession");
   else if("noservice".equals(msg))
     msg = passport.getSystemProperty("login.noservice");
   else if("authendicationNeed".equals(msg))
     msg = "** Please login your username/password !";
   else if(null==msg)
     msg = "";
%>

<html>
  <head>
    <title><wf:property key="login.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=<wf:property type="system" key="siteware.charset" />">
    <meta http-equiv="Expires" content="0">
    <link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_<wf:property type="system" key="siteware.lang"/>.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
    <style>
	    .version{text-align:right;color:#D6D6D6;font-size:18px;margin-right:20px;margin-bottom:5px;}
	</style> 
  </head>
  <body bgcolor="#FFFFFF" background="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/main_bk.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="window.history.forward()">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
      <tr>
        <td align="center" valign="middle">
          <table width="400" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td bgcolor="#003366" width="100%">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">  
                  <tr>
                    <td background="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/headpagebgL.gif"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" width="1" height="70"><div class="version"><wf:property type="system" key="version"/></div></td>
                  </tr>
                  <tr>
                    <td background="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/shadowbg.gif" ><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="5" width="1"></td>
                  </tr>
                  <tr>
                    <td bgcolor="#FFFFFF">
                      <table border="0" cellspacing="5" cellpadding="0" align="center">
                        <form name="login_form" action="<wf:property type="system" key="appbase"/>/main.wf" method="post" onSubmit="toUpper('login_form','username')">
                          <input type="hidden" name="actiontype" value="logon" ID="hidden1">
                          <tr>
                            <td align="center"><font color="#CC0000"><b><%= msg %></b></font></td>
                          </tr>
                          <tr>
                            <td class="smallest" align="center"><font color="#006600"><b><jsp:getProperty name="passport" property="msg"/></b></font>&nbsp;</td>
                          </tr>
                          <tr>
                            <td>
                              <table border="1" cellspacing="0" cellpadding="5" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
                                <tr>
                                  <td align="right" class="bigger"><b><wf:property key="login.domain"/>:</b></td>
                                  <td>
                                      <%
                                      if ("off".equals(passport.getSystemProperty("IS_DOMAIN_INPUT")))
                                      {%>
                                      <select name="domain" size="1">
                                      <%
                                          String[] domains = passport.getDomains();
                                          for(int i=0; i<domains.length; i++) 
                                          {
                                             %>
                                             <option value="<%=domains[i]%>" <%=(domains[i].equals(passport.getDomain())?"selected":"")%>><%=domains[i]%></option>
                                             <%
                                          }
                                       %>
                                       </select>
                                      <%}else{%>
                                      <input type="text" name="domain" size="15" value="<jsp:getProperty name="passport" property="domain"/>">
                                      <%}%>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right" class="bigger"><b><wf:property key="login.username"/>:</b></td>
                                  <td>
                                    <input type="text" name="username" size="15" value="<jsp:getProperty name="passport" property="username"/>">
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right"  class="bigger"><b><wf:property key="login.password"/>:</b></td>
                                  <td>
                                    <input type="password" name="password" size="15" value="<jsp:getProperty name="passport" property="password"/>">
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td align="center">
                              <input class="actionbtn" type="submit" name="submit" value=" <wf:property key="login.button1"/> ">
                              <input class="removebtn" type="button" name="reset" value=" <wf:property key="login.button2"/> " onClick="clearForm('login_form')">
                              <br><br>
                            </td>
                          </tr>
                          <tr>
                           <td bgcolor="#999999"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
                          </tr>
                          <tr>
                            <td align="center" class="smallest"><wf:property key="login.info"/></td>
                          </tr>
                        </form>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
              <td>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <script language="JavaScript1.2" type="text/javascript">
      var now = new Date();
      var snowsrc=null;
        if (now.getMonth()==11 && now.getDate()==25)
          snowsrc="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/snwflk2.gif";
        else
          snowsrc="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif";
    </script>
    <script language="JavaScript1.2" src="<wf:property type="system" key="appbase"/>/js/drop.js" type="text/javascript"></script>
  </body>
</html>