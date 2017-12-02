<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="org.yang.customized.gtf.services.projectViewer.ProjectViewerManager" %>
<jsp:useBean id="projectMasterBean" class="org.yang.customized.gtf.services.projectViewer.web.ProjectViewerMasterBean" scope="session"/>
<jsp:useBean id="excelTransferBean" class="org.yang.customized.gtf.services.projectViewer.web.ExcelFileTransferBean" scope="session"/>
<jsp:useBean id="projectListMenu" class="org.yang.customized.gtf.services.projectViewer.web.ProjectListMenuBean" scope="session"/>
<%
      String[] displayViewModes = new String[] {"Project Results"};
      String[] viewModes = new String[] {ProjectViewerManager.DATA_ON_TABLE_NAME_3};
      String displayViewMode = "";
      for(int i=0;i<viewModes.length;i++)
      {
         if(viewModes[i].equals(projectMasterBean.getViewMode()))
            displayViewMode = displayViewModes[i];
      }     
      excelTransferBean.setProjects(projectMasterBean.getDisplayProjects());
    
%>

<html>
  <head>
    <title>ProjectViewer::master table</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
    <script language="JavaScript" type="text/javascript">
      function openWindow(url,w,h) 
      {
        if(w==0)
           w = 700;
        if(h==0)
           h = 450;   
        popupWin = window.open(url,'new_page','width='+w+',height='+h+',scrollbars=yes,toolbars=yes,menubar=no,resizable=yes')
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
        <td  colspan="4" class="title" align="left">
          <font color="#1D459D">Project Master Table (<%=displayViewMode%>)</font>
        </td>
       <td align="right">
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projectViewer/downloadPage.jsp',550,300)">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/b_rpt_tab.gif" width="18" height="19" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/>/excelTransfer.wf?actiontype=writerToExcelFile',550,300)">Convert to Excel</a>
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projectViewer/printtableProjectResultsTable.jsp',0,0)">
             <img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/icon_print.gif" width="16" height="17" border="0" alt="" title="" align="absmiddle" />
          </a>
          &nbsp;
          <a href="JavaScript:openWindow('<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/projectViewer/printtableProjectResultsTable.jsp',0.0)">Printer Friendly</a>
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
     if(projectMasterBean.getDisplayProjectsSize()==0)
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
  
    <wf:WebControl beanID = "projectMasterBean" 
                   controlBuilderName = "org.yang.customized.gtf.services.projectViewer.web.ProjectResultsMasterBuilder" />

    </table>
  </body>
</html>

