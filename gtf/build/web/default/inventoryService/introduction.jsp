<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
<HEAD>
<link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>
/css/swbase_English.css">
<script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
<style type="text/css">
<!--
.style1 {font-weight: bold; color: #683400;font-size: 16px;}
-->
</style>
</HEAD>

<BODY bgcolor="#FFFFFF" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10">


  <table width="100%" border="0" cellspacing="2" cellpadding="0">
    <tr>
      <td align="right" class="title"><font color="#990000">Inventory Service</font></td>
    </tr>
    <tr>
      <td bgcolor="#990000"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
    </tr>
  </table>

	<table width="100%" border="0" cellspacing="0" cellpadding="5"  bgcolor="#FFFFFF" align="center">
	<tr>
		<td class="smallest" align="left">
			Read the introduction for "Service preparation" and "Storage Locator".Please press the buttons: 
			<label>         
			<a href="/wf/default/inventoryService/introduction.jsp">
                          <img src="/wf/default/images/English/btn_servicePreparation_on.gif" title="Show introduction of service preparation" border="0">        
                       </a>  
			<a href="/wf/default/inventoryService/introduction2.jsp">
                          <img src="/wf/default/images/English/btn_storageLocator_on.gif" title="Show introduction of storage locator." border="0">        
                       </a>                                
                        </label>
                        
		</td>
	</tr>
	</table>

	<!---  information -->
	<br>

	<table width="90%" border="0" cellspacing="0" cellpadding="8" align="center">

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/introduction_1.gif" width="283" height="214"></td>
                <td valign="top" ><p><font class="style1"><strong>Introduction buttons:</strong></font></p>
  <p>1. Click &quot;<strong>Srv.</strong> <strong>preparation</strong>&quot; button to enter the introduction for selected service(default mode).</p>
  <p>2. Click &quot;<strong>StorageLocator</strong><strong></strong>&quot; button to enter the introduction for selected service.</p>
  <p>&nbsp;</p>
  <p>&nbsp; </p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/intro_inventory_brow.gif" width="283" height="214"></td>
                <td valign="top" ><p><font class="style1"><strong>Browse:</strong></font></p>
  <p>1. Click &quot;<strong>service node</strong>&quot; to enter your selected service.</p>
  <p>2. Select <strong>Calendar</strong> (2) and <strong>Date</strong> radio buttons (3) to sift the
  service data in spread sheet on upper right corner.</p>
  <p>3.Move your mouse cursor over the specific <strong>project name,</strong>
   and the <strong>contact list</strong> will display.</p>
  <p>&nbsp; </p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/intro_inventory_update_1.gif" width="283" height="213"></td>
		<td valign="top" ><p><font class="style1"><strong>Update:</strong></font></p>
	    <p>1.Click the link at your selected <strong>project name</strong>,</p>
	    <p>2. And this highlighted record will display in the <strong>service </strong>
	    <strong>edit table </strong>below.</p>
	    <p>3.You can modify the  attribute values for this record,
	    and move the scroller bar to the lower part of this table. </p>
	    <p>4.Press <strong>Button Update </strong> to submit the modified data.</p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/intro_inventory_create_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Create</strong></font></p>
	    <p>1.Press the <strong>Button Cancel</strong> to clear all the table fields
	   and  shift the mode to create.</p>

                </td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/intro_inventory_create_2.gif" width="181" height="42"></td>
		<td valign="top" ><p>2.Therefore, you can see the buttons change to<strong> Create</strong>
	        and <strong>Cancel</strong> at the lower right corner .</p>
	    <p>3.Fill in all the fields and press <strong>Button Create</strong> 
                to add new record.(Be sure that not giving an existed project name to the new project to make it work.</p>
                </td>
        </tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/intro_inventory_remove_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Remove</strong></font></p>
	    <p>1.Select one or multiple check boxes in front of the records on the spreadsheet .</p>
	    <p> 2. (Or , you can click the link at <strong>All </strong> word on  top of the check
	     boxes,and all of the check boxes in this page shall be selected.For clear the marks you
             selected ,just click the <strong>All</strong> sign once more to erase the selection.) </p>
	    <p>3.The panel of <strong>Button Remove</strong> shows , and  click this button to remove record in database.</p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top">&nbsp;</td>
		<td valign="top" >&nbsp;</td>
	</tr>
	</table>

</BODY>
</HTML>
