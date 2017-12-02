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

    Storage storage = null;
    String projectCode = storageBean.getProjectCode();
    String labName = storageBean.getLabName();
    String investigator = storageBean.getInvestigator();
    String cell = storageBean.getCell();
    String parentalLine = storageBean.getParentalLine();
    String medium = storageBean.getMedium();
    String drugSelection = storageBean.getDrugSelection();
    String location = storageBean.getLocation();
    String comment = storageBean.getComment();
    String boxNumber = storageBean.getBoxNumber();
    String rowColumn = storageBean.getRowColumn();
    String freezingDate =storageBean.getFreezingDate();
    //System.out.println("[storageTable]///----->(storageBean) labName="+labName);
    //System.out.println("[storageTable]///----->(storageBean) investigator="+investigator);
        
    String id = storageBean.getId();
    //System.out.println("[storageTable.jsp]----(id="+id+")--->//// LastActiontype  is ?--->"+storageBean.getLastActiontype());

    if(null!=id&&!"".equals(id)&&
       !"changePage".equals(storageBean.getLastActiontype()))//edit
    {
       storage = storageBean.getCurrentStorage();
       projectCode = Utility.NAFormat(storage.getProjectCode());
       if(!"updateDomain".equals(storageBean.getLastActiontype()))
       {
          labName = Utility.NAFormat(storage.getLabName());
          investigator = Utility.NAFormat(storage.getInvestigator());
       }
       cell = Utility.NAFormat(storage.getCell()); 
       medium = Utility.NAFormat(storage.getMedium());
       parentalLine = storage.getParentalLine();
       drugSelection = Utility.NAFormat(storage.getDrugSelection());
       location = Utility.NAFormat(storage.getLocation());
       comment = Utility.NAFormat(storage.getComment());
       boxNumber = storage.getBoxNumber()+"";
       rowColumn = Utility.NAFormat(storage.getRowColumn());
       freezingDate = Utility.dateFormat(storage.getFreezingDate(),"MM/dd/yyyy");
    //System.out.println("[storageTable]///----->(storage) labName="+labName);
    //System.out.println("[storageTable]///----->(storage) investigator="+investigator);
 
    }
    /*
    System.out.println("[storageTable]----->id="+id);
    System.out.println("[storageTable]----->projectCode="+projectCode);
    System.out.println("[storageTable]----->boxNumber="+boxNumber);
    System.out.println("[storageTable]----->labName="+labName);
    System.out.println("[storageTable]----->investigator="+investigator);
    System.out.println("[storageTable]----->cell="+cell);
    System.out.println("[storageTable]----->medium="+medium);
    System.out.println("[storageTable]----->parentalLine="+parentalLine);
    System.out.println("[storageTable]----->drugSelection="+drugSelection);
    System.out.println("[storageTable]----->location="+location);
    System.out.println("[storageTable]----->comment="+comment);
    */
    String  rawDataInputType = storageBean.getRawDataInputType();
    String[] dataInputTypeValues = {"inputDataByExcel","inputDataByGUI"};
    String[] dataInputTypeNames = {"From excel files","Menu input"};
    
    String[] domains = storageBean.getAllDomainNames();
    String[] passportDomains= storageBean.getpassportDomains();
    String[] style_labNames =  storageBean.getOptionStyles(passportDomains,domains);
    //System.out.println("[storageTable]----------> 2 domains.length = " + domains.length);
    

    String[] investigators = storageBean.loadInvestigatorsByDomain(labName);
    String[] investigatorsInPassport = storageBean.getInvestigatorsInSelectedLab();
    String[] style_investigators =  storageBean.getOptionStyles(investigatorsInPassport,investigators);
    
     if(investigators==null)
     {
        investigators = new String[1];
        investigators[0] = "Please select"; 
        investigatorsInPassport = new String[1];
        investigatorsInPassport[0] = "Please select";
        style_investigators = new String[1];
        style_investigators[0] = "style1";
     }
    //System.out.println("[storageTable]----->investigators.length="+investigators.length);
    //System.out.println("[storageTable]----->investigatorsInPassport.length="+investigatorsInPassport.length);
    //System.out.println("[storageTable]----->style_investigators.length="+style_investigators.length);
  
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Storage Table</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/epoch_styles.css" />
    <script type="text/javascript" src="<wf:property type="system" key="appbase"/>/js/epoch_classes.js"></script>
    <script type="text/javascript">
       /*You can also place this code in a separate file and link to it like epoch_classes.js*/
	var dp_cal;      
        window.onload = function () {
	dp_cal  = new Epoch('epoch_popup','popup',document.getElementById('popup_container'));
       };
    </script>
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
        if(act=='clear')
           fm.target='_self';
        fm.submit();
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
       
      //---------------------------------------
      function submitFormThenTargetTo(frm, act, tar) {
        fm = eval('document.'+ frm);
        fm.actiontype.value = act;
        fm.target = tar;
        fm.submit();
      } 
    </script>
<style type="text/css">
<!--
.style0 {bgcolor:#1D459D;}
.style1 {width: 130px;}
.style2 {font-size: 12px;
}

.style3 {font-size: 12px;
         color: #ca0000;
}
.style4 {
	color: #003399;
	font-size: 14px;
}
.style20 {
	   font-size: 10 px;
	   color: #666666;
           letter-spacing: 0.05em;
}
.style34 {color: #003399; font-size: 12px; }
.style35 {font-weight: bold; color: #003399;}
.style38 {
	color: #666666;
	letter-spacing: 0.05em;
	font-size: 12px;
}


-->
</style>
<STYLE type="text/css">
       OPTION.style2{background-color:#A9A9F5; color:black;width:130px};
       OPTION.style1{background-color:white; color:black;width:130px};
</STYLE>

</head>
<body>
<form id="storageForm" name="storageForm" method="post" action="/wf/storage.wf" target="rightFrame2">
 <input type="hidden" name="actiontype" value="" ID="Hidden1">
   <table width="100%" hight="100%" border="0" align="center" cellpadding="0">
    <tr>
      <td width="33%" >

       <!-- Lab name-->
        <table border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#4682b4">
                      <span class="style2"><span class="style35">Lab name</span> <span class="style4">:</span>

                        <label>
                            <select name="labName" id="select2" class="style1" onChange="submitFormThenTargetTo('storageForm','updateDomain','_self')">
             <%

                       for(int i=0;i<domains.length;i++)
                       {

             %>
                          <option value="<%=domains[i]%>"  class="<%=style_labNames[i]%>"  <% if(domains[i].equalsIgnoreCase(labName)) out.print("selected") ; %>><%=domains[i]%></option>
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
<td class="smallest" colspan="2" align="right">
<table>
  <tr>
    <td width="394"><p>&nbsp;</p></td>
  <td width="419">
            <fieldset>
                <legend> <span class="style20">Data Input</span> </legend>
          <%    for(int k=0;k<dataInputTypeNames.length;k++){  %>
           <span class="style20">
                <label>
           <input name="rawDataInputType" type="radio" class="style4"  onclick="changeDataInputType('storageForm','changePage')" value="<%=dataInputTypeValues[k]%>" <% if(dataInputTypeValues[k].equals(rawDataInputType)) out.print("checked") ; %> />
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
      <td colspan="4" bgcolor="#1D459D"><img class="style0" src="/wf/default/images/1PIX.gif" height="2" width="1" /> </td>
    </tr>
    <tr>
      <td height="18" colspan="3"><span class="style2">Storage ID: <font class="style3"><%=id%></font></span></td>
    </tr>
    <tr>

      <td width="33%">
       <!-- Investigator/Phone/Billable -->
        <table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td height="130">
         <table width="100%" height="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">
                 <tr>
                  <td width="30%" bgcolor="#DFDFDF"><span class="style34">Cell</span></td>

                <td width="70%" align="center">
                      <label>
                          <input type="text" name="cell" id="3" value="<%=cell%>" size="18"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td  bgcolor="#DFDFDF"><span class="style34">Investigator</span></td>
                  <td align="center">
                        <label>
                            <select name="investigator" id="select2" class="style1">
             <%
                       for(int i=0;i<investigators.length;i++)
                       {   
             %>
                          <option value="<%=investigators[i]%>"  class="<%=style_investigators[i]%>"  <% if(investigators[i].equalsIgnoreCase(investigator)) out.print("selected") ; %>><%=investigators[i]%></option>
             <%
                       }
             %>
                           </select>
                        </label>
                   </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Project code</span></td>

              <td align="center">
                      <label>
                          <input type="text" name="projectCode" id="3" value="<%=projectCode%>" size="18"/>
                      </label>
                  </td>
                </tr>
              </table>
            </td>
          </tr>

      </table>
     <!--The end of Investigator/Phone/Billable -->
      </td>
      <td width="33%">
       <!-- ProjectCode/ProbeName/ProbeNo -->
        <table width="100%" border="0"  cellpadding="1" bgcolor="#000060">
          <tr>
            <td height="130">
              <table width="100%" height="100%" border="1"  bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td  bgcolor="#DFDFDF"><font color="#C52211">*</font><span class="style34">Freezing date</span></td>
                  <td align="center">
                     <label>
                          <input id="popup_container" type="text" name="FreezingDate"  value="<%=freezingDate%>" size="15"/>
                 
                     </label>
                  </td>
                </tr>
                <tr>
                  <td width="153" bgcolor="#DFDFDF"><span class="style34">Medium</span></td>
                  <td width="234" align="center">
                    <label>
                       <input name="medium" type="text" id="4" value="<%=medium%>" size="15" />
                    </label>
                  </td>
                </tr>
                 <tr>
                  <td  bgcolor="#DFDFDF"><span class="style34">Drug selection</span></td>
                 <td align="center">
                     <label>
                          <input type="text" name="drugSelection" id="5" value="<%=drugSelection%>" size="15"/>
                     </label>
                  </td>
                </tr>

              </table>
            </td>
          </tr>
      </table>
      <!--The end of ProjectCode/ProbeName/ProbeNo -->
      </td>
      <td width="34%">
       <!-- testedDate/closedDate/drugSelection -->
        <table width="400"  border="0">

          <tr>
            <td width="396" >
            <table width="387" height="100%" bgcolor="#000060">
                <tr >
                  <td width="387" height="130">
                    <table width="400" height="100%" cellpadding="2"  border="1"  bgcolor="#FFFFFF">
                     <tr>
                      <td width="81"  bgcolor="#DFDFDF"><font color="#C52211">*</font><span class="style34">Parental line</span></td>

                      <td colspan="3" align="left">
                         <label>
                             <input type="text" name="parentalLine" id="5" value="<%=parentalLine%>" size="20"/>
                         </label>
                      </td>
                     </tr>
                     <tr>
                        <td  bgcolor="#DFDFDF"><font color="#C52211">*</font><span class="style34">Location</span></td>

                  <td colspan="3" align="left">
                            <label>
                            <input name="location" type="text" id="6" value="<%=location%>" size="20" />
                            </label>
                        </td>
                     </tr>
                      <tr>
                        <td  bgcolor="#DFDFDF"><font color="#C52211">*</font><span class="style34">Box#</span></td>

                        <td width="90" align="center">
                     <label>
                               <input type="text" name="boxNumber" id="5" value="<%=boxNumber+""%>" size="6"/>
                            </label>
                        </td>
                        <td width="58" bgcolor="#DFDFDF"><font color="#C52211">*</font><span class="style34">Row/Column</span></td>
                        <td width="135" align="center">
                            <label>
                            <input name="rowColumn" type="text" id="6" value="<%=rowColumn%>" size="6" />
                            </label>
                        </td>
                      </tr>
                  </table>
                  </td>
                </tr>
            </table>
            <!--the end of testedDate/closedDate/drugSelection -->

            </td>
          </tr>
      </table>
      </td>
     
    </tr>
    <tr>
      <td colspan="3">
        <table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>

            <td>
             <table width="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Comment</span></td>
                  <td colspan="2" align="center">
                    <label>
                        <textarea  id = "comment" name = "comment"  rows = "5" wrap = "SOFT" cols = "82"><%=comment%></textarea>
                    </label>                  
                    </td>
                </tr>
            </table>
            </td>
          </tr>
        </table>
     </td>
   </tr>
   <tr>

      <td colspan="3" align="right">
        <table width="180" border="0" cellpadding="2"  bgcolor="#000060">
          <tr align="right" valign="middle">
            <td width="178">
              <table width="100%" border="0"  bgcolor="#FFFFFF">
                <tr>
                  <td align="center" bgcolor="#FFFFFF">
    <%
           if(null==id||"".equals(id))
           {
    %>
                   <label>
          <a href="javascript:submitForm('storageForm','createStorage')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_create','','/wf/default/images/English/btn_create_on.gif',1)">
            <img name="img_create"
            src="/wf/default/images/English/btn_create.gif"
            border="0"
            alt="Create storage">
          </a>
                    </label>
  <%
           }
           else
           {
  %>
                   <label>
          <a href="javascript:submitForm('storageForm','updateStorage')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_update','','/wf/default/images/English/btn_update_on.gif',1)">
            <img name="img_update"
            src="/wf/default/images/English/btn_update.gif"
            border="0"
            alt="Update probe case">
          </a>
                    </label>


  <%
           }
  %>
                     <label>
          <a href="javascript:submitForm('storageForm','clear')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_cancel','','/wf/default/images/English/btn_cancel_on.gif',1)">
            <img name="img_cancel"
            src="/wf/default/images/English/btn_cancel.gif"
            border="0"
            alt="Cancel probe case">
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
