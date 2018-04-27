<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="org.yang.customized.gtf.services.inventoryManager.utility.Utility" %>
<%@ page import="org.yang.customized.gtf.services.dataAccess.Record" %>
<jsp:useBean id="recordBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.RecordTableBean"
             scope="session"/>
<%
    Record record = null;
    String receivedDate = recordBean.getReceivedDate();
    String purifiedDate = recordBean.getPurifiedDate();   
    String injectedDate = recordBean.getInjectedDate();
    String closedDate = recordBean.getClosedDate();   
    String projectName = recordBean.getProjectName();
    String labName = recordBean.getLabName();
    String investigator = recordBean.getInvestigator();
    String phone = recordBean.getPhone();
    String number = recordBean.getNumber();
    String plasmidBAC = recordBean.getPlasmidBAC();
    String mouseHost = recordBean.getMouseHost();
    String note = recordBean.getNote();
    String billableType = recordBean.getBillableType();

    String id = recordBean.getId();
    String[] domains = recordBean.getAllDomainNames();
    if(null==domains)
       domains = new String[0];
    String thisDomain = recordBean.getDomain();

    //System.out.println("[recordTable]----(id="+id+")--->//// LastActiontype  is ?--->"+recordBean.getLastActiontype());
    if(null!=id&&!"".equals(id)&&
       !"changeDate".equals(recordBean.getLastActiontype()))//edit
    {
       record = recordBean.getCurrentRecord();
       projectName = Utility.NAFormat(record.getProjectName());
       labName = Utility.NAFormat(record.getLabName());
       investigator = Utility.NAFormat(record.getInvestigator());
       phone = Utility.NAFormat(record.getPhone());
       plasmidBAC = Utility.NAFormat(record.getPlasmidBAC());
       number = record.getNumber()+"";
       mouseHost = Utility.NAFormat(record.getMouseHost());
       note = Utility.NAFormat(record.getNote());
       billableType = Utility.NAFormat(record.getBillableType());

       if(receivedDate != null)
           receivedDate = Utility.dateFormat(record.getReceivedDate(),"MM/dd/yyyy");
       else
           receivedDate = "------";
           
       if(purifiedDate != null)
           purifiedDate = Utility.dateFormat(record.getPurifiedDate(),"MM/dd/yyyy");
       else
           purifiedDate = "------";

       if(injectedDate != null)
           injectedDate = Utility.dateFormat(record.getInjectedDate(),"MM/dd/yyyy");
       else
           injectedDate = "------";
          
       if(closedDate != null)
           closedDate = Utility.dateFormat(record.getClosedDate(),"MM/dd/yyyy");
       else
           closedDate = "------";
                     
    }
    if("".equals(plasmidBAC))
       plasmidBAC = "plasmid";
    if("".equals(billableType))
       billableType = "no";

       /*
    System.out.println("[recordTable]----->id="+id);
    System.out.println("[recordTable]----->projectName="+projectName);
    System.out.println("[recordTable]----->record="+record);
    System.out.println("[recordTable]----->labName="+labName);
    System.out.println("[recordTable]----->investigator="+investigator);
    System.out.println("[recordTable]----->phone="+phone);
    System.out.println("[recordTable]----->plasmidBAC="+plasmidBAC);
    System.out.println("[recordTable]----->number="+number);
    System.out.println("[recordTable]@@----->note="+note);
    */
    
    String[] injectTypeValues = {"plasmid","BAC"};
    String[] injectTypeNames = {"Plasmid","BAC"};


    String[] billableTypeValues = {"yes","no"};
    String[] billableTypeNames = {"Yes","No"};

%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Record Table</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/key_handler.js" type="text/javascript"></script>
    <script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/form_action.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/epoch_styles.css" />
    <script type="text/javascript" src="<wf:property type="system" key="appbase"/>/js/epoch_classes.js"></script>
    <script type="text/javascript">
       /*You can also place this code in a separate file and link to it like epoch_classes.js*/
	var dp_cal1;
	var dp_cal2;
	var dp_cal3;
	var dp_cal4;
	window.onload = function () {
	dp_cal1  = new Epoch('epoch_popup','popup',document.getElementById('popup_container1'));
	dp_cal2  = new Epoch('epoch_popup','popup',document.getElementById('popup_container2'));
	dp_cal3  = new Epoch('epoch_popup','popup',document.getElementById('popup_container3'));
	dp_cal4  = new Epoch('epoch_popup','popup',document.getElementById('popup_container4'));
 
       };

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

        if(k==1)
           fm.y_receivedDate.value=object.options[object.selectedIndex].value;
        else if(k==2)
           fm.m_receivedDate.value=object.options[object.selectedIndex].value;
        else if(k==3)
           fm.d_receivedDate.value=object.options[object.selectedIndex].value;
        else if(k==4)
           fm.y_purifiedDate.value=object.options[object.selectedIndex].value;
        else if(k==5)
           fm.m_purifiedDate.value=object.options[object.selectedIndex].value;
        else if(k==6)
           fm.d_purifiedDate.value=object.options[object.selectedIndex].value;
        else if(k==7)
           fm.y_injectedDate.value=object.options[object.selectedIndex].value;
        else if(k==8)
           fm.m_injectedDate.value=object.options[object.selectedIndex].value;
        else if(k==9)
           fm.d_injectedDate.value=object.options[object.selectedIndex].value;
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
<form id="recordForm" name="recordForm" method="post" action="/wf/record.wf" target="rightFrame2">
 <input type="hidden" name="actiontype" value="" ID="Hidden1">
 <table width="100%" width="100%" border="0" align="center" cellpadding="0">
    <tr>
      <td colspan="3">
        <table border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table border="0" bordercolor="#000000" bgcolor="#FFFFFF">
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
      </td>
    </tr>
    <tr>
      <td colspan="4" bgcolor="#1D459D"  align="right"><img class="style0" src="/wf/default/images/1PIX.gif" height="2" width="1" /> </td>
</tr>
    <tr>
      <td colspan="3"><p class="style2">Record table :ID: <font class="style3"><%=id%></font></p></td>
   </tr><tr><td width="33%"><table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" height="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Project</span></td>
                  <td align="center">
                 <label>
                       <input name="projectName" type="text" id="4" value="<%=projectName%>" size="15" />
                    </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Investigator</span></td>
                  <td align="center">
              <label>
                          <input type="text" name="investigator" id="2" value="<%=investigator%>"  size="15"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Phone</span></td>
                  <td align="center">
      <label>
                          <input type="text" name="phone" id="3" value="<%=phone%>" size="15"/>
                      </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Billable</span></td>
                  <td >
          <%    for(int k=0;k<billableTypeNames.length;k++){  %>
           <span class="style20">
                <label>
           <input name="billableType" type="radio" class="style4" id="billableType" value="<%=billableTypeValues[k]%>" <% if(billableTypeValues[k].equals(billableType)) out.print("checked") ; %> />
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
      </td>
      <td width="33%">
          <table width="100%" border="0"  cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" height="100%" border="1" cellpadding="3" bordercolor="#999999" bgcolor="#FFFFFF">
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">PlasmidBAC</span></td>
                  <td>
          <%    for(int k=0;k<injectTypeNames.length;k++){  %>
           <span class="style20">
                <label>
           <input name="plasmidBAC" type="radio" class="style4" id="injectType_0"  value="<%=injectTypeValues[k]%>" <% if(injectTypeValues[k].equals(plasmidBAC)) out.print("checked") ; %> />
                 <%=injectTypeNames[k]%>
                </label>
           </span>&nbsp; <br>
           <%    }       %>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Number</span></td>
                  <td align="center">
                     <label>
                          <input type="text" name="number" id="5" value="<%=number%>" size="12"/>
                     </label>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#DFDFDF"><span class="style34">Mouse host</span></td>
                  <td align="center">
              <label>
                          <input name="mouseHost" type="text" id="6" value="<%=mouseHost%>" size="12" />
                    </label>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
      </td>
      <td width="34%">
        <table width="100%" border="0" cellpadding="1" bgcolor="#000060">
          <tr>
            <td>
                <table width="100%" height="100%" border="1" bordercolor="#999999" bgcolor="#FFFFFF">

                  <tr>
                        <td bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Received </span></td>
                        <td align="center" bordercolor="#CCCCCC">
                        <span class="style2">
                           <label>
                              <input id="popup_container1" type="text" name="receivedDate"  value="<%=receivedDate%>" size="15"/>
                           </label>
                        </span>
                        </td>
                    </tr>
                      <tr>
                        <td bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Purified </span></td>
                        <td align="center" bordercolor="#CCCCCC"><span class="style2">
                          <label>
                          <input id="popup_container2" type="text" name="purifiedDate"  value="<%=purifiedDate%>" size="15"/>
                          </label>
                        </span>
                        </td>
                    </tr>
                      <tr>
                        <td bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Injected </span></td>
                        <td align="center" bordercolor="#CCCCCC"><span class="style2">
                      <label>
                          <input id="popup_container3" type="text" name="injectedDate"  value="<%=injectedDate%>" size="15"/>
                          </label>
                        </span>
                        </td>
                    </tr>
            <tr>
                        <td bordercolor="#CCCCCC" bgcolor="#DFDFDF"><span class="style34">Closed </span></td>
                        <td align="center" bordercolor="#CCCCCC">
                         <span class="style2">
                        <label>
                          <input id="popup_container4" type="text" name="closedDate"  value="<%=closedDate%>" size="15"/>
                          </label>
                        </span>
                        </td>
                    </tr>
                  </table>
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
                  <td bgcolor="#DFDFDF"><span class="style34">Note</span></td>
                  <td align="center">
                    <label>
                        <textarea  id = "note" name = "note"  rows = "5" wrap = "SOFT" cols = "77" ><%=note%></textarea>
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
      <td colspan="3" valign="right">
        <table width="180" align="right" border="0" cellpadding="2"  bgcolor="#000060">
          <tr align="center" valign="middle">
            <td width="178">
              <table width="100%" border="0"  bgcolor="#FFFFFF">
                <tr>
                  <td align="center" bgcolor="#FFFFFF">
    <%
           if(null==id||"".equals(id))
           {
    %>
                   <label>
          <a href="javascript:submitForm('recordForm','createRecord')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_create','','/wf/default/images/English/btn_create_on.gif',1)">
            <img name="img_create"
            src="/wf/default/images/English/btn_create.gif"
            border="0"
            alt="Create record">
          </a>
                  </label>
  <%
           }
           else
           {
  %>
                   <label>
          <a href="javascript:submitForm('recordForm','updateRecord')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_update','','/wf/default/images/English/btn_update_on.gif',1)">
            <img name="img_update"
            src="/wf/default/images/English/btn_update.gif"
            border="0"
            alt="Update record">
          </a>
                    </label>
  <%
           }
  %>
                     <label>
          <a href="javascript:submitForm('recordForm','clear')"
             onMouseOut="MM_swapImgRestore()"
             onMouseOver="MM_swapImage('img_cancel','','/wf/default/images/English/btn_cancel_on.gif',1)">
            <img name="img_cancel"
            src="/wf/default/images/English/btn_cancel.gif"
            border="0"
            alt="Cancel record">
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
