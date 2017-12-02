<!--%@ page errorPage="../share/error.jsp" %-->
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="projectListMenu" class="org.yang.customized.gtf.services.projectViewer.web.ProjectListMenuBean" scope="session"/>
<%
      String viewMode = projectListMenu.getViewMode();
%>
<html>
  <head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Expires" content="0">
    
    <link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>  
    <script language="JavaScript" type="text/javascript">
       /*~* for onload the master table on right frame *~*/
       window.onload=function loadProjectViewerMasterTable() 
       {  
          parent.parent.rightFrame.location = "/wf/projectViewerMaster.wf?actiontype=loadMasterTableData&viewMode=<%=viewMode%>";
       }
        
    //---------------------------------------
      function submitForm(frm,act)
      {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.submit();
      }
   //---------------------------------------
      function initAct(frm,ck,act) 
      {
         var count =0;
	 var i=0;
	 chks = eval('document.'+ frm +'.'+ ck);
	 len = chks.length;
	 for(i=0; i<len; i++)
	 {
	    if(chks[i].checked==0)
	       count++;
	 }
         //alert('[projectListMenu::validateChkBox]count='+count);     
	 fm = eval('document.'+ frm);
	 if(count==len) //for delete the final entry --> send a message to bean
            fm.selectCheckBoxMode.value=0;
         fm.actiontype.value = act;
	 fm.submit();
          
      }   
   //---------------------------------------
     function setCheckBoxAll_Mode2(frm,ck,val)
     {
	chks = eval('document.'+ frm +'.'+ ck);

	if (chks.length)
	{
		len = chks.length;
		var i=0;
		for(i=0; i<len; i++)
		{
		    chks[i].checked=val;
		}
	}
	else
	{
		chks.checked=val;
	}
	fm = eval('document.'+ frm);
	fm.selectCheckBoxMode.value=val;
     }      
    </script>  
  </head>
  <body>
     <wf:WebControl beanID = "projectListMenu" controlBuilderName = "org.yang.customized.gtf.services.projectViewer.web.ProjectListMenuBuilder" />
  </body>
</html>