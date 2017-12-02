<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="inventoryBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.InventoryBean"
             scope="session"/>

<%

  String  dateType = inventoryBean.getDateType();
  String[] dateTypeValues = {"receivedDate","purifiedDate","injectedDate","closedDate"};
  String[] dateTypeNames = {"Received","Purified","Injected","Closed"};
  
%>

<html>
  <head>
    <title>Inventory Manager::SpreadSheet table</title>
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
    </script>
    <style type="text/css">    
       .style20 {
	   font-size: 11 px;
	   color: #666666;
           letter-spacing: 0.05em;
       }

       a[title=:"Domain"] { font-weight: bold;color : #730073; font-size: 12 px;}
       a[title=:"Lab Head"] { font-weight: bold;color : #730073; font-size: 12 px;}
       a[title=:"Phone"] { font-weight: bold;color : #730073; font-size: 12 px;}

    </style>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="20" marginwidth="20" onLoad="window.history.forward()" >
    <table width="100%" border="0" cellspacing="2" cellpadding="0" ID="Table1">
      <tr>
        <td  colspan="4" class="title" align="left">
          <font color="#1D459D">Pronuclear Injection</font>
        </td>
        <td class="smallest" align="right">
        <!---------->
          <form name="dateForm" action="<wf:property type="system" key="appbase"/>/inventory.wf?actiontype=changePage"  target="_parent" method="post" ID="Form1">
               <fieldset>
                  <legend> <span class="style20">Date</span> </legend>
          <%    for(int k=0;k<dateTypeNames.length;k++){  %>
           <span class="style20">
                <label>
           <input name="dateType" type="radio" class="style4" id="dateType_0" onclick="changeDateType('dateForm','changePage')" value="<%=dateTypeValues[k]%>" <% if(dateTypeValues[k].equals(dateType)) out.print("checked") ; %> />
                 <%=dateTypeNames[k]%>&nbsp;
                </label>
           </span>
           <%    }       %>
               </fieldset>
          </form>
         </td>
      <!---------->
       <td align="right">
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtablePronuclearInjectionTable.jsp')">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/icon_print.gif" width="16" height="17" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/printtablePronuclearInjectionTable.jsp')">Printer Friendly</a>
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
     if(inventoryBean.getAllRecordsSize()==0)
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
        <wf:WebControl beanID = "inventoryBean"
                     controlBuilderName = "org.yang.customized.gtf.services.inventoryManager.web.InventorySpreadSheetBuilder" />

    </table>
  </body>
</html>