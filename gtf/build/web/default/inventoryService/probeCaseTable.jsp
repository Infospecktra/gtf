<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="org.yang.customized.gtf.services.inventoryManager.utility.Utility" %>
<%@ page import="org.yang.customized.gtf.services.dataAccess.ProbeCase" %>
<jsp:useBean id="probeCaseBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.ProbeCaseBean"
             scope="session"/>
<%
    ProbeCase probeCase = null;
    String testDate = probeCaseBean.getTestDate();
    String closedDate = probeCaseBean.getClosedDate();   
    String projectName = probeCaseBean.getProjectName();
    String labName = probeCaseBean.getLabName();
    String investigator = probeCaseBean.getInvestigator();
    String phone = probeCaseBean.getPhone();
    String probeNumber = probeCaseBean.getProbeNumber();
    String probeName = probeCaseBean.getProbeName();
    String testBy = probeCaseBean.getTestBy();
    String result = probeCaseBean.getResult();
    String note = probeCaseBean.getNote();
    String billableType = probeCaseBean.getBillableType();
    String resultType = probeCaseBean.getResultType();
            
    String id = probeCaseBean.getId();
    String[] domains = probeCaseBean.getAllDomainNames();
    if(null==domains)
       domains = new String[0];
    String thisDomain = probeCaseBean.getDomain();

    //System.out.println("[probeCaseTable.jsp]----(id="+id+")--->//// LastActiontype  is ?--->"+probeCaseBean.getLastActiontype());
    if(null!=id&&!"".equals(id)&&
       !"changeDate".equals(probeCaseBean.getLastActiontype()))//edit
    {
       probeCase = probeCaseBean.getCurrentProbeCase();
       projectName = Utility.NAFormat(probeCase.getProjectName());
       labName = Utility.NAFormat(probeCase.getLabName());
       investigator = Utility.NAFormat(probeCase.getInvestigator());
       phone = Utility.NAFormat(probeCase.getPhone());
       probeName = Utility.NAFormat(probeCase.getProbeName());
       probeNumber = probeCase.getProbeNumber()+"";
       testBy = Utility.NAFormat(probeCase.getTestBy());
       result = Utility.NAFormat(probeCase.getResult());
       note = Utility.NAFormat(probeCase.getNote());
       billableType = Utility.NAFormat(probeCase.getBillableType());
       resultType = Utility.NAFormat(probeCase.getResultType());
       
       if(testDate != null)
           testDate = Utility.dateFormat(probeCase.getTestDate(),"MM/dd/yyyy");
       else
           testDate = "------";
           
       if(closedDate != null)
          closedDate = Utility.dateFormat(probeCase.getClosedDate(),"MM/dd/yyyy");
       else
          closedDate = "------";

    }
    /*
    System.out.println("[probeCaseTable]----->id="+id);
    System.out.println("[probeCaseTable]----->projectName="+projectName);
    System.out.println("[probeCaseTable]----->record="+record);
    System.out.println("[probeCaseTable]----->labName="+labName);
    System.out.println("[probeCaseTable]----->investigator="+investigator);
    System.out.println("[probeCaseTable]----->phone="+phone);
    System.out.println("[probeCaseTable]----->probeName="+probeName);
    System.out.println("[probeCaseTable]----->probeNumber="+probeNumber);
    System.out.println("[probeCaseTable]----->testBy="+testBy);
    System.out.println("[probeCaseTable]----->result="+result);
    System.out.println("[probeCaseTable]@@----->note="+note);
    */
    String[] billableTypeValues = {"yes","no"};
    String[] billableTypeNames = {"Yes","No"};
    String[] resultTypeValues = {"n/a","good","notGood","repeat"};
    String[] resultTypeNames = {"N/A","Good","Not Good","Repeat"};
    
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Probe Test Table</title>

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
	var dp_cal2;
        window.onload = function () {
	dp_cal  = new Epoch('epoch_popup','popup',document.getElementById('popup_container'));
	dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container2'));
 
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

     function selectDate(frm,object,k){
        fm = eval('document.'+ frm);
        fm.actiontype.value = 'changeDate';

        if(k==7)
           fm.y_testDate.value=object.options[object.selectedIndex].value;
        else if(k==8)
           fm.m_testDate.value=object.options[object.selectedIndex].value;
        else if(k==9)
           fm.d_testDate.value=object.options[object.selectedIndex].value;
        else if(k==10)
           fm.y_closedDate.value=object.options[object.selectedIndex].value;
        else if(k==11)
           fm.m_chargeDate.value=object.options[object.selectedIndex].value;
        else if(k==12)
           fm.d_closedDate.value=object.options[object.selectedIndex].value;

        fm.submit();
     }
    
</script>
<style type="text/css">
<!--
.style0 {bgcolor:#1D459D;}
.style1 {width: 120px;}
.style2 {font-size: 12px;
}

.style3 {font-size: 12px;
         color: #ca0000;
}
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
<form id="probeCaseForm" name="probeCaseForm" method="post" action="/wf/probeCase.wf" target="rightFrame2">
 <input type="hidden" name="actiontype" value="" ID="Hidden1">
   <table width="100%" hight="100%" border="0" align="center" cellpadding="0">
    <tr>
      <td colspan="3">
       <!-- Lab name-->
        <table border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#4682b4">
                      <span class="style2"><span class="style35">Lab name</span> <span class="style4">:</span>
                        <label>
                            <select name="domain" id="select2" class="style1">
             <%
                       for(int i=0;i<domains.length;i++)
                       {
             %>
                          <option value="<%=domains[i]%>" <% if(domains[i].equals(thisDomain)) out.print("selected") ; %>><%=domains[i]%></option>
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
    </tr>
    <tr>
      <td colspan="4" bgcolor="#1D459D"><img class="style0" src="/wf/default/images/1PIX.gif" height="2" width="1" /> </td>
    </tr>
    <tr>
      <td colspan="3"><span class="style2">Probe Case :ID: <font class="style3"><%=id%></font></span></td>
    </tr>
    <tr>
      <td width="33%">
       <!-- Investigator/Phone/Billable -->
          <table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" height="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">
                 <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Investigator</span></td>
                  <td align="center">
                      <label>
                          <input type="text"  name="investigator" id="2" value="<%=investigator%>"  size="16"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Phone</span></td>
                  <td align="center">
                      <label>
                          <input type="text" name="phone" id="3" value="<%=phone%>" size="16"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td height="41" bgcolor="#DFDFDF"><span class="style34">Billable</span></td>
                  <td >
          <%    for(int k=0;k<billableTypeNames.length;k++){  %>
           <span class="style20">
                <label>
           <input name="billableType" type="radio" class="style4" id="billableType"  value="<%=billableTypeValues[k]%>" <% if(billableTypeValues[k].equals(billableType)) out.print("checked") ; %> />
                 <%=billableTypeNames[k]%>
                </label>
           </span>  
           <%    }       %>
                  </td>
              </tr>
              </table>
            </td>
          </tr>
      </table>
     <!--The end of Investigator/Phone/Billable -->
      </td>
      <td width="33%">
       <!-- ProjectName/ProbeName/ProbeNo -->
          <table width="100%" height="100%" border="0"  cellpadding="1" bgcolor="#000060">
          <tr>
            <td height="103">
              <table width="100%" height="100%" border="1"  bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td width="80" bgcolor="#DFDFDF"><span class="style34">Project Name</span></td>
                  <td align="center">
                    <label>
                       <input name="projectName" type="text" id="4" value="<%=projectName%>" size="15" />
                    </label>
                  </td>
                </tr>
                 <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Probe Name</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="probeName" id="5" value="<%=probeName%>" size="15"/>
                     </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Probe No.</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="probeNumber" id="5" value="<%=probeNumber%>" size="15"/>
                     </label>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
      <!--The end of ProjectName/ProbeName/ProbeNo -->
      </td>
      <td width="34%">
       <!-- testedDate/closedDate/testBy -->
        <table width="100%"  border="0">
          <tr>
            <td>
            <table width="100%" height="100%" bgcolor="#000060">
                <tr >
                  <td>
                  <table width="100%" height="100%" cellpadding="2"  border="1"  bgcolor="#FFFFFF">
                   <tr>
                        <td  bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Test </span></td>
                        <td bordercolor="#CCCCCC" align="center">
                        <span class="style2">
                          <label>
                          <input id="popup_container" type="text" name="testDate"  value="<%=testDate%>" size="20"/>
                          </label>
                        </span>
                        </td>
                      </tr>

                      <tr>
                        <td  bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Closed </span></td>
                        <td bordercolor="#CCCCCC" align="center">
                        <span class="style2">
                          <label>
                          <input id="popup_container2" type="text" name="closedDate"  value="<%=closedDate%>" size="20"/>
                          </label>
                        </span>
                        </td>
                      </tr>
                      <tr>
                        <td height="38" bgcolor="#DFDFDF"><span class="style34">Test By</span></td>
                        <td align="center">
                            <label>
                            <input name="testBy" type="text" id="6" value="<%=testBy%>" size="20" />
                            </label>
                        </td>
                     </tr>
                  </table>
                  </td>
                </tr>
            </table>
            <!--the end of testedDate/closedDate/testBy -->
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
                  <td bgcolor="#DFDFDF"><span class="style34">Result</span></td>
                  <td bgcolor="#DFDFDF" align="left" width="90">
          <%    for(int k=0;k<resultTypeNames.length;k++){  %>
           <span class="style34">
                <label>
           <input name="resultType" type="radio" class="style34" id="resultType" value="<%=resultTypeValues[k]%>" <% if(resultTypeValues[k].equals(resultType)) out.print("checked") ; %> />
                 <%=resultTypeNames[k]%>
                </label>
           </span> <br>
           <%    }       %>

                  </td>
                  <td align="left">
                    <label>
                        <textarea  id = "result" name = "result"  rows = "5" wrap = "SOFT" cols = "80" ><%=result%></textarea>
                    </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Note</span></td>
                  <td colspan="2" align="center">
                    <label>
                        <textarea  id = "note" name = "note"  rows = "5" wrap = "SOFT" cols = "80"><%=note%></textarea>
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
          <a href="javascript:submitForm('probeCaseForm','createProbeCase')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_create','','/wf/default/images/English/btn_create_on.gif',1)">
            <img name="img_create"
            src="/wf/default/images/English/btn_create.gif"
            border="0"
            alt="Create probeCase">
          </a>
                    </label>
  <%
           }
           else
           {
  %>
                   <label>
          <a href="javascript:submitForm('probeCaseForm','updateProbeCase')"
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
          <a href="javascript:submitForm('probeCaseForm','clear')"
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
