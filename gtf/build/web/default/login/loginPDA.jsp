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
  </head>
  <body>
    <TABLE id="Table1" cellSpacing="0" cellPadding="1" width="220" border="0">
      <TR>
        <TD width="100%" bgColor="#003366">
          <TABLE id="Table2" cellSpacing="0" cellPadding="0" width="100%" border="0">
            <TR>
              <TD background="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/head_PDA.gif"><IMG height="80" src="<wf:property type="system" key="appbase"/>/default/images/1PIX.gif" width="1"></TD>
            </TR>
            <TR>
              <TD background="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/shadowbg.gif"><IMG height="5" src="<wf:property type="system" key="appbase"/>/default/images/1PIX.gif" width="1"></TD>
            </TR>
            <TR>
              <TD bgColor="#ffffff">
                <TABLE id="Table3" cellSpacing="5" cellPadding="0" align="center" border="0">
                  <FORM id="Form1" name="login_form" onsubmit="toUpper('login_form','username')" action="<wf:property type="system" key="appbase"/>/mainPDA.wf" method="post">
                    <input type="hidden" name="actiontype" value="logon" ID="hidden1">
                    <TR>
                      <TD align="center"><FONT color="#cc0000"><B><%= msg %></B></FONT></TD>
                    </TR>
                    <TR>
                      <TD>
                        <table border="1" cellspacing="0" cellpadding="5" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center">
                          <tr>
                            <td align="right" class="bigger"><FONT size="2" color="#000000"><B><wf:property key="login.domain"/>:</B></FONT></td>
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
                                <input type="text" name="domain" size="10" value="<jsp:getProperty name="passport" property="domain"/>">
                                <%}%>
                            </td>
                          </tr>
                          <tr>
                            <td align="right" class="bigger"><FONT size="2" color="#000000"><B><wf:property key="login.username"/>:</B></FONT></td>
                            <td>
                              <input type="text" name="username" size="10" value="<jsp:getProperty name="passport" property="username"/>">
                            </td>
                          </tr>
                          <tr>
                            <td align="right"  class="bigger"><FONT size="2" color="#000000"><B><wf:property key="login.password"/>:</B></FONT></td>
                            <td>
                              <input type="password" name="password" size="10" value="<jsp:getProperty name="passport" property="password"/>">
                            </td>
                          </tr>
                        </table>
                      </TD>
                    </TR>
                    <TR>
                      <TD align="center"><INPUT class="actionbtn" id="Submit1" type="submit" value=" Submit " name="submit">
                        <INPUT class="removebtn" id="Button1" onclick="clearForm('login_form')" type="button" value=" Reset "
                          name="reset">
                      </TD>
                    </TR>
                    <TR>
                      <TD bgColor="#999999"><IMG height="1" src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" width="1"></TD>
                    </TR>
                    <TR>
                      <TD class="smallest" align="center">Please contact your administrator if you have any account problems.</TD>
                    </TR>
                  </FORM>
                </TABLE>
              </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
  </body>
</html>
