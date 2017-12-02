<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="storageMasterBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBean"
             scope="session"/>
<%

   boolean allChecked = storageMasterBean.getIsAllChecked();
   String actiontype = storageMasterBean.getLastActiontype();
   //System.out.println("storageMasterTable.jsp---->allChecked="+allChecked);
   //System.out.println("storageMasterTable.jsp---->actiontype="+actiontype);
   
%>
<html>
  <head>
    <title>Inventory Manager::storage master table</title>
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
        popupWin = window.open(url,'new_page','width=780,height=450,scrollbars=yes,toolbars=yes,menubar=yes,resizable=yes')
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
           for (var i=0; i<4; i++)
   	   {
 	      if (fm.dateType[i].checked)
		   fm.dateType.value  = fm.dateType[i].value;
 	   }
           fm.submit();
           
       }
       
     //---------------------------------------

      function loadStorageRemovePanel() {
         //alert('actiontype='+'<%=actiontype%>');
         if('selectAll'=='<%=actiontype%>')   
            top.mainFrame1.rightFrame2.rightBottomFrame.location='/wf/storage.wf?actiontype=showRemoveCtrlPanel';   
         else if('deselectAll'=='<%=actiontype%>')   
            top.mainFrame1.rightFrame2.rightBottomFrame.location='/wf/storage.wf?actiontype=toInputDataFromExcelFiles';   
      }
      
      window.onload=loadStorageRemovePanel;

    </script>
    <style type="text/css">    
       .style20 {
	   font-size: 11 px;
	   color: #666666;
           letter-spacing: 0.05em;
       }
    </style>
    <STYLE type="text/css">
      OPTION.style2{background-color:#A9A9F5; color:black};
      OPTION.style1{background-color:white; color:black};
    </STYLE>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20">
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td  colspan="4" class="title" align="left">
          <font color="#1D459D">Storage Location Table</font>
        </td>
        <td class="smallest" align="right">
        &nbsp;
        </td>
       <td align="right">
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtableStorageMasterTable.jsp')">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/icon_print.gif" width="16" height="17" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtableStorageMasterTable.jsp')">Printer Friendly</a>
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
     if(storageMasterBean.getAllStoragesSize()==0)
     {
  %>
        <tr>
          <td>
            <p>&nbsp;</p> 
          </td>
        </tr>
  <%
     }
  %>
        <wf:WebControl beanID = "storageMasterBean"
                     controlBuilderName = "org.yang.customized.gtf.services.inventoryManager.web.StorageMasterTableBuilder" />

    </table>
  </body>
</html>