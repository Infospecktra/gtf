<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="org.yang.customized.gtf.services.inventoryManager.utility.Utility" %>
<%@ page import="org.yang.customized.gtf.services.dataAccess.Karyotype" %>
<jsp:useBean id="karyotypeBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.KaryotypeBean"
             scope="session"/>
<%
    Karyotype karyotype = null;
    String sentDate = karyotypeBean.getSentDate();
    String billing = karyotypeBean.getBilling();   
    String projectName = karyotypeBean.getProjectName();
    String projectCode = karyotypeBean.getProjectCode();
    String labName = karyotypeBean.getLabName();
    String investigator = karyotypeBean.getInvestigator();
    String cellSource = karyotypeBean.getCellSource();
    //String phone = karyotypeBean.getPhone();
    String parentalLine = karyotypeBean.getParentalLine();
    String requestedBy = karyotypeBean.getRequestedBy();
    String result = karyotypeBean.getResult();
    String note = karyotypeBean.getNote();
    String resultType = karyotypeBean.getResultType();
            
    String id = karyotypeBean.getId();
    String[] domains = karyotypeBean.getAllDomainNames();
    if(null==domains)
       domains = new String[0];
    String thisDomain = karyotypeBean.getDomain();

    //System.out.println("[karyotypeTable.jsp]----(id="+id+")--->//// LastActiontype  is ?--->"+karyotypeBean.getLastActiontype());
    if(null!=id&&!"".equals(id)&&
       !"changeDate".equals(karyotypeBean.getLastActiontype()))//edit
    {
       karyotype = karyotypeBean.getCurrentKaryotype();
       projectName = Utility.NAFormat(karyotype.getProjectName());
       labName = Utility.NAFormat(karyotype.getLabName());
       investigator = Utility.NAFormat(karyotype.getInvestigator());
       cellSource = Utility.NAFormat(karyotype.getCellSource());
       //phone = Utility.NAFormat(karyotype.getPhone());
       projectCode = Utility.NAFormat(karyotype.getProjectCode());
       parentalLine = karyotype.getParentalLine()+"";
       requestedBy = Utility.NAFormat(karyotype.getRequestedBy());
       billing = Utility.NAFormat(karyotype.getBilling());
       result = Utility.NAFormat(karyotype.getResult());
       note = Utility.NAFormat(karyotype.getNote());
       resultType = Utility.NAFormat(karyotype.getResultType());
       
       if(sentDate != null)
           sentDate = Utility.dateFormat(karyotype.getSentDate(),"MM/dd/yyyy");
       else
           sentDate = "------";
           

    }
    /*
    System.out.println("[karyotypeTable]----->id="+id);
    System.out.println("[karyotypeTable]----->projectName="+projectName);
    System.out.println("[karyotypeTable]----->labName="+labName);
    System.out.println("[karyotypeTable]----->cellSource="+cellSource);
    System.out.println("[karyotypeTable]----->investigator="+investigator);
    System.out.println("[karyotypeTable]----->projectCode="+projectCode);
    System.out.println("[karyotypeTable]----->parentalLine="+parentalLine);
    System.out.println("[karyotypeTable]----->requestedBy="+requestedBy);
    System.out.println("[karyotypeTable]----->result="+result);
    System.out.println("[karyotypeTable]@@----->note="+note);
    */
    String[] billableTypeValues = {"yes","no"};
    String[] billableTypeNames = {"Yes","No"};
    String[] resultTypeValues = {"n/a","good","notGood"};
    String[] resultTypeNames = {"N/A","Good","Not Good"};
    
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
           fm.y_sentDate.value=object.options[object.selectedIndex].value;
        else if(k==8)
           fm.m_sentDate.value=object.options[object.selectedIndex].value;
        else if(k==9)
           fm.d_sentDate.value=object.options[object.selectedIndex].value;
        else if(k==10)
           fm.y_billing.value=object.options[object.selectedIndex].value;
        else if(k==11)
           fm.m_chargeDate.value=object.options[object.selectedIndex].value;
        else if(k==12)
           fm.d_billing.value=object.options[object.selectedIndex].value;

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
<form id="karyotypeForm" name="karyotypeForm" method="post" action="/wf/karyotype.wf" target="rightFrame2">
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
      <td colspan="3"><span class="style2">Project Code: <font class="style3"><%=projectCode%></font></span></td>
    </tr>
    <tr>
      <td width="33%">
       <!-- ProjectName/Investigator -->
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
                  <td bgcolor="#DFDFDF"><span class="style34">Project Code</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="projectCode" id="5" value="<%=projectCode%>" size="15"/>
                     </label>
                  </td>
                </tr>                 
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Investigator</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="investigator" id="5" value="<%=investigator%>" size="15"/>
                     </label>
                  </td>
                </tr>

              </table>
            </td>
          </tr>
      </table>
      </td>
      <td width="33%">
       <!-- ParentalLine/RequestedBy -->
          <table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td height="103">
                <table width="100%" height="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">
                 <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Cell Source</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="cellSource" id="5" value="<%=cellSource%>" size="16"/>
                     </label>
                  </td>
                </tr>
                   <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Parental Line</span></td>
                  <td align="center">
                      <label>
                          <input type="text"  name="parentalLine" id="2" value="<%=parentalLine%>"  size="16"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Requested By</span></td>
                  <td align="center">
                      <label>
                          <input type="text" name="requestedBy" id="3" value="<%=requestedBy%>" size="16"/>
                      </label>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
     <!--The end of ParentalLine/RequestedBy -->
      </td>
      <td width="34%">
       <!-- sentDate/billing -->
        <table width="100%"  border="0">
          <tr>
            <td>
            <table width="100%" height="100%" bgcolor="#000060">
                <tr>
                  <td height="103">
                  <table width="100%" height="100%" cellpadding="2"  border="1"  bgcolor="#FFFFFF">
                      <tr>
                        <td  bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Billing </span></td>
                        <td bordercolor="#CCCCCC" align="center">
                        <span class="style2">
                          <label>
                          <input  type="text" name="billing"  value="<%=billing%>" size="20"/>
                          </label>
                        </span>
                        </td>
                      </tr>
                      <tr>
                        <td bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Sent Date</span></td>
                        <td bordercolor="#CCCCCC" align="center">
                        <span class="style2">
                          <label>
                          <input id="popup_container" type="text" name="sentDate"  value="<%=sentDate%>" size="20"/>
                          </label>
                        </span>
                        </td>
                      </tr>
                  </table>
                  </td>
                </tr>
            </table>
            <!--the end of sentDate/billing -->
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
          <a href="javascript:submitForm('karyotypeForm','createKaryotype')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_create','','/wf/default/images/English/btn_create_on.gif',1)">
            <img name="img_create"
            src="/wf/default/images/English/btn_create.gif"
            border="0"
            alt="Create karyotype">
          </a>
                    </label>
  <%
           }
           else
           {
  %>
                   <label>
          <a href="javascript:submitForm('karyotypeForm','updateKaryotype')"
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
          <a href="javascript:submitForm('karyotypeForm','clear')"
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
