<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="probeCaseBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.ProbeCaseBean"
             scope="session"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Remove ProbeCase Button Panel</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
    
</script>
<style type="text/css">
<!--
.style2 {font-size: 12px}
.style4 {
	color: #003399;
	font-size: 14px;
}
.style34 {color: #003399; font-size: 12px; }
.style35 {font-weight: bold; color: #003399;}
-->
</style>
</head>

<body>
<form id="removeProbeCaseForm" name="removeProbeCaseForm" method="post" action="/wf/probeTest.wf" target="rightFrame2">
 <input type="hidden" name="actiontype" value="" ID="Hidden1">
  <table  border="0" align="center" cellpadding="0">
    <tr>
      <td height="40" colspan="3">
   <table>
   <tr>
      <td width="100%" colspan="6" bgcolor="#1D459D" align="right">
         <img src="/wf/default/images/1PIX.gif" height="1" width="1" /> 
       </td>
   </tr>
   <tr>
      <td colspan="3">&nbsp;</td>
  </tr>
   <tr>
      <td width="300">&nbsp;</td>
      <td width="300" align="right">&nbsp;</td>
      <td align="right">
        <table width="180" border="0" cellpadding="2"  bgcolor="#1D459D">
          <tr align="center" valign="middle">
            <td width="178">
              <table width="100%" border="0"  bgcolor="#FFFFFF">
                <tr>
                  <td align="center" bgcolor="#FFFFFF">
   
                     <label>
          <a href="javascript:confirmDel('removeProbeCaseForm','deleteProbeCases',1)"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_remove','','/wf/default/images/English/btn_remove_on.gif',1)">
            <img name="img_remove"
            src="/wf/default/images/English/btn_remove.gif"
            border="0"
            alt="Remove probe case">
          </a>
                      </label>
                   </td>
                </tr>
            </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
