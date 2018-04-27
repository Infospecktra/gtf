<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="org.yang.customized.gtf.services.inventoryManager.utility.Utility" %>
<%@ page import="org.yang.customized.gtf.services.dataAccess.Storage" %>

<jsp:useBean id="storageBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.StorageBean"
             scope="session"/>

<%

    String[] domains = {"------"}; // don't need domain name  in this page.  
    String labName = "------";
    String uploadedExcelFile ="";
    String  rawDataInputType = storageBean.getRawDataInputType();
    //System.out.println("[inputDataFromFiles]@@----->rawDataIputType="+rawDataInputType);
    String fileUpload = storageBean.getFileUpload();
    //System.out.println("[inputDataFromFiles]--------------------------------->fileUpload="+fileUpload);  
    String[] dataInputTypeValues = {"inputDataByExcel","inputDataByGUI"};
    String[] dataInputTypeNames = {"From excel files","Menu input"};
    int totalDataSize1 = -99;
    //System.out.println("[inputDataFromFiles]@@----->storageBean.getIsUploadCompleted()="+storageBean.getIsUploadCompleted());
    //System.out.println("[inputDataFromFiles]@@----->storageBean.getLastActiotype()="+storageBean.getLastActiontype());
    //System.out.println("[inputDataFromFiles]--------------------------------->lastActiontype="+storageBean.getLastActiontype());  
       
    if(storageBean.getIsUploadCompleted()&&!"changePage".equals(storageBean.getLastActiontype()))
    {
        rawDataInputType = "inputDataByExcel";
        totalDataSize1 = storageBean.getTotalDataSize();
        uploadedExcelFile = storageBean.getUploadedExcelFile();
    }
    
    if(rawDataInputType==null||rawDataInputType.equals(""))
       rawDataInputType = "inputDataByExcel";
    
    String formActionStub = "";
    String tmp_formSetting = storageBean.getUploadedFormSetting(); 
    String sess_formSetting = (String)session.getAttribute("uploadedFormSetting");
    String createActionType = ""; 
    
    if("inputDataByGUI".equals(rawDataInputType))
    {
       formActionStub = "action=\"/wf/storage.wf\"";
       session.setAttribute("uploadedFormSetting",formActionStub); 
//System.out.println("[inputDataFromFiles]---------->inputDataByGUI.equals(rawDataInputType)");
    }
    else if(null!=sess_formSetting&&!"".equals(sess_formSetting))
    {
        formActionStub = sess_formSetting;
//System.out.println("[inputDataFromFiles]---------->formActionStub = sess_formSetting");

    }
    else if(null!=tmp_formSetting&&!"".equals(tmp_formSetting))
    {
        formActionStub = tmp_formSetting;
        session.setAttribute("uploadedFormSetting",formActionStub); 
//System.out.println("[inputDataFromFiles]---------->formActionStub = tmp_formSetting;session.setAttribute(uploadedFormSetting,formActionStub)");

    }    
    else
        formActionStub = "enctype=\"multipart/form-data\"  action=\"/wf/fileUpload/fileUpload.servlet\""; 

    if("changePage".equals(storageBean.getLastActiontype()))
        formActionStub = "enctype=\"multipart/form-data\"  action=\"/wf/fileUpload/fileUpload.servlet\""; 

    
    if(formActionStub.indexOf("storage.wf")>-1)
        createActionType = "createDataFileFromExcelFile"; 
    else  
        createActionType = "goUploadServlet";
        
    //System.out.println("[inputDataFromFiles]@@----->formActionStub ="+formActionStub);
    //System.out.println("[inputDataFromFiles]@@----->createActionType ="+createActionType);
    
    /*  from javascript    
     function testBrowser(){
        alert('navigator application code = '+navigator.appCodeName+'|| navigator name = '+navigator.appName);
     } 
     
     */
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Storage Table:inputDataFromExcelFiles</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>

    <script language="JavaScript" type="text/javascript">
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
        //fm.browserName.value = navigator.appName;
        fm.submit();
      }


      function confirmUpload(frm,act) {
        fm = eval('document.'+ frm);
        //alert("act="+act);
        if('createDataFileFromExcelFile'==act)
           act = 'goUploadServlet';
        //alert ("act=" + act);                              
        if(fm.fileUpload.value!="")
        {
            submitForm(frm,act);
        }
        else
           alert("Sorry! Please select the uploaded file.");
       }
       
       function confirmUpload2(frm,act) {
        fm = eval('document.'+ frm);
        //alert("[confirmUpload2]---->act="+act);
        if('createDataFileFromExcelFile'==act)
           act = 'goUploadServlet';
        //alert ("act=" + act);                              
        
        if(fm.fileUpload.value!="")
        {
            submitForm(frm,act);
        }
        else
        {
          fm.fileUpload.value='<%=fileUpload%>'; 
          submitForm(frm,act);
        }
        if(fm.fileUpload.value=="") 
           alert("Sorry! Please select the uploaded file.");
        
      }
    //---------------------------------------
       function changeDataInputType(frm,act)
       {
           fm = eval('document.'+ frm);
           fm.actiontype.value = act;
              
           for (var i=0; i<2; i++)
   	   {
 	      if (fm.rawDataInputType[i].checked)
		   fm.rawDataInputType.value  = fm.rawDataInputType[i].value;
 	   }
           fm.submit();

       }
    </script>
<style type="text/css">
<!--
.style0 {bgcolor:#1D459D;}
.style1 {width: 120px;}
.style2 {font-size: 12px;}
.style4 {color: #003399;font-size: 14px;}
.style20 { font-size: 10 px;color: #666666;letter-spacing: 0.05em;}
.style33 {padding: 20px;}
.style34 {color: #003399; font-size: 12px; }
.style35 {font-weight: bold; color: #003399;}
.style55 {padding-top: 13px;padding-left: 35px;align:center;}

-->
</style>
</head>

<body <% if(null!=fileUpload&&!"".equals(fileUpload) ) {%> onLoad="confirmUpload2('filesImportForm','<%=createActionType%>')"; <%} else {%> onLoad="MM_preloadImages('/wf/default/images/English/btn_create_on.gif');<% } %>">
<form id="filesImportForm" name="filesImportForm" method="POST" <%=formActionStub%>>
 <input type="hidden" name="actiontype" value="" ID="Hidden1">
 <input type="hidden" name="browserName" value="" ID="Hidden2">
   <table width="100%" hight="100%" border="0" align="center" cellpadding="0">
    <tr>
      <td width="17%">  <p>&nbsp;</p> 
       <!-- Lab name-->
        <table border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#4682b4">
                      <span class="style2"><span class="style35">Lab name</span> <span class="style4">:</span>
                        <label>
                            <select name="labName" id="select2" class="style1">
             <%
                       for(int i=0;i<domains.length;i++)
                       {
             %>
                          <option value="<%=domains[i]%>" <% if(domains[i].equals(labName)) out.print("selected") ; %>><%=domains[i]%></option>
             <%
                       }
             %>
                        </select>
                        </label>
                     </span>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
       <!--end of Lab name-->
      </td>
      <td class="smallest" colspan="2" align="center">
        <table>
          <tr>
            <td width="100%">
              <fieldset>
                <legend> <span class="style20">Data Input</span> </legend>
                <%    for(int k=0;k<dataInputTypeNames.length;k++){  %>
                  <span class="style20">
                  <label>
                    <input name="rawDataInputType" type="radio" class="style4"  onclick="changeDataInputType('filesImportForm','changePage')" value="<%=dataInputTypeValues[k]%>" <% if(dataInputTypeValues[k].equals(rawDataInputType)) out.print("checked") ; %> />
                       <%=dataInputTypeNames[k]%>&nbsp;
                  </label>
                 </span>
                 <%    }       %>
              </fieldset>
            </td>
          </tr>
        </table>      
      </td>
    </tr>
    <tr>
      <td colspan="4" bgcolor="#1D459D"><img class="style0" src="/wf/default/images/1PIX.gif" height="2" width="1" /></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="62%">
       <!-- i -->
        <table>
        <tr>
        <td width="15%"><p>&nbsp;</p> </td>
        <td>
        <table  border="0"  cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
              <table width="100%" height="13%" border="1"  bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td width="16%" cellpadding="10" height="38" bgcolor="#DFDFDF">
                  <span class="style34">Import Files</span></td>
                  <td class="style55" width="84%" >
                    
                    <label class="style20">                    
                    <input align="center" valign="middle" name="fileUpload" type="file" id="4"  size="30"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <%
                          /*~! For load all import storages records  !~!~!~!~!*/                    
                          String labName2 = "Please select"; 
                          String investigator2 = "Please select"; 
                       
                          if(totalDataSize1 != -99){%> 
                              <a href="/wf/storages.wf?actiontype=toRightFrameService&&labName=<%=labName2%>&&investigator=<%=investigator2%>&&uploadedExcelFile=<%=uploadedExcelFile%>&&uploadedFormSetting=<%="enctype='multipart/form-data'  action='/wf/fileUpload/fileUpload.servlet'"%>" target="_parent"><font color="#1D459D">Total data size is <%=totalDataSize1%>.</font></a>
                        <% } %>      
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

      <!--The end of  -->
      </td>
      <td width="25%">       
        <table>
          <tr>
            <td>
        <table width="190" border="0"  bgcolor="#000060">
          <tr align="right" valign="middle">
            <td width="186" height="53">
            <table width="100%" border="0" cellpadding="16"  bgcolor="#FFFFFF">
                <tr>
                  <td height="38"  align="center" bgcolor="#FFFFFF"><label> <a href="javascript:confirmUpload('filesImportForm','<%=createActionType%>')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_create','','/wf/default/images/English/btn_create_on.gif',1)"> <img name="img_create"
            src="/wf/default/images/English/btn_create.gif"
            border="0"
            alt="Create storage"> </a> </label>
                  </td>
                </tr>
            </table></td>
          </tr>
        </table>
            </td>
            <td class="style33">
               <p>&nbsp;</p> 
            </td> 
          </tr>
        </table>     
       </td>
     </tr>
<!-- -->
   <tr>
      <td colspan="3" align="right">&nbsp;</td>
    </tr>
  </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
