<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id    = "timetable" 
             class = "org.yang.customized.gtf.services.tglScheduler.web.TimetableBean" 
             scope = "session"/>

<html>
  <head>
    <title>STime table</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>  
    <script language="JavaScript" type="text/javascript">
      //---------------------------------------
      function submitForm(frm,act)
      {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }
      //---------------------------------------
      function validateChkBox(frm,ck,act) {     
        if (isCheckBoxChecked(frm,ck)) {
          submitForm(frm,act);
        }
        else
          alert('Please select a project and try again.');
      }
    </script>
  </head>
    <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" >
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td class="title" align="left">
          <font color="#1D459D">Project Prepration Form (<%=timetable.getId()%>)</font>
        </td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#1D459D"><img src="/wf/default/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" ID="Table2">
      <tr>
        <td class="smallest" align="left">
          View roject information.
        </td>
      </tr>
    </table>
    <!-----form starts here ----->
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

      <form name="timetable" action="<wf:property type="system" key="appbase"/>/timetable.wf" method="post" ID="Form1">
        <input type="hidden" name="actiontype" value="" ID="Hidden1">
        <wf:WebControl beanID = "timetable" 
                       controlBuilderName = "org.yang.customized.gtf.services.tglScheduler.web.TimetableBuilder" />
      </form>

    </table>
  </body>
</html>