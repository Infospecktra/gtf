<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="karyotypeMasterTableBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.KaryotypeMasterTableBean"
             scope="session"/>

<%

  String  dateType = karyotypeMasterTableBean.getDateType();
  String[] dateTypeValues = {"sentDate"};
  String[] dateTypeNames = {"Sent"};
  
%>

<html>
  <head>
    <title>Karyotype Master Table</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
    <script language="JavaScript" type="text/javascript">
      function openWindow(url) 
      {
        popupWin = window.open(url,'new_page','width=700,height=450,scrollbars=yes,toolbars=yes,menubar=yes,resizable=yes')
      }
    </script>
    <script language="JavaScript" type="text/javascript">    
       function submitForm(frm,act) 
       {
          fm = eval('document.'+ frm);
          fm.actiontype.value = act;			 			
          fm.submit();
       }
    </script>
    <script language="JavaScript" type="text/javascript">    
    
       function changeDateType(frm,act)
       {

           fm = eval('document.'+ frm);
           //alert('act='+act);
           for (var i=0; i<2; i++)
   	   {
 	      if (fm.dateType[i].checked)
		   fm.dateType.value  = fm.dateType[i].value;
 	   }
           fm.submit();
           
       }
    </script>
    <style type="text/css">    
       .style20 {
	   font-size: 11 px;
	   color: #666666;
           letter-spacing: 0.05em;
       }        
    </style>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()" >
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td  colspan="4" height="40" class="title" align="left">
          <font color="#1D459D">Karyotype Table</font>
        </td>
        <!---------->
       <td align="right">
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtableKaryotypeMasterTable.jsp')">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/icon_print.gif" width="16" height="17" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtableKaryotypeMasterTable.jsp')">Printer Friendly</a>
       <!--------->
        </td>
      </tr>
      <tr>
        <td colspan="6" bgcolor="#1D459D" align="right"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
      </tr>
    </table>

     <!-----form starts here ----->
    <table width="80%" border="0" cellspacing="3" cellpadding="2" align="center" ID="Table3">
  <%
     if(karyotypeMasterTableBean.getAllKaryotypesSize()==0)
     {
  %>
        <tr>
          <td>
          &nbsp; 
          </td>
        </tr>
  <%
     }
  %>
        <wf:WebControl beanID = "karyotypeMasterTableBean"
                     controlBuilderName = "org.yang.customized.gtf.services.inventoryManager.web.KaryotypeMasterTableBuilder" />

    </table>
  </body>
</html>