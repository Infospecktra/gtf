<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
<HEAD>
<link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>
/css/swbase_English.css">
<script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
<style type="text/css">
<!--
.style1 {font-weight: bold; color: #683400;font-size: 16px;}
.style2 {font-weight: bold; color: #C52211;font-size: 14px;}
.style3 {font-size: 12px;color: #ca0000;}

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
			Read the introduction for "Service preparation" and "Storage Locator".Please <font class="style3">press the buttons </style>: 
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
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchIntro_1.gif" width="283" height="214"></td>
                <td valign="top" ><p><font class="style1"><strong>Enetr Storage locator:</strong></font></p>
  <p>1. One click &quot;<strong>storage locator node</strong>&quot; on the service tree to enter the storage locator service.</p>
  <p>2. The <strong>search</strong> area displays at the left-bottom corner,the <strong>Storage master table</strong> for displaying searching  outcome and the <strong>data import panel</strong> on the right frame.</p>
  <p>&nbsp; </p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_1.gif" width="283" height="213"></td>
		<td valign="top" ><p><font class="style1"><strong>Search:</strong></font></p>
	    <p>1.(1)Select the option for the lab name (and investigator) selection list,<p> 
	    <p>&nbsp;&nbsp;&nbsp;(2)and  fill in the search keywords in <strong>keywords</strong> text field .If there are more than one keys ,then seperate them  by  space, "|"  . </p>
	    <p>2.Press  <strong>Submit </strong>button,and you can see search outcome right to display on right-upper frame.</p>
	    <p>3.Users can see the matching keywords of data record are hightlighted in blue color. </p>
	    <p><strong>Note:1.</strong>Users can browse all storage records by leaving the selecting options of the lab name and the investigator dropdowns to "Please select" ,and the search text field blank. </p>
	    <p><strong>Note:2.</strong>Users can search storage records by selecting the options of the lab name and the investigator dropdowns with filling in keywords ,or only by submitting keywords at text field (not accept labName/investigator data). </p>
	    <p><strong>Note:3.</strong>If the lab names and investigators users imported are invalid one , the background color of the invalid options in selection list shall display in blue.Users can select those options, and correct the labname and investigator name one by one at storage edit table. </p></td>

	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchLogics_1.gif" ></td>
		<td valign="top" ><p><font class="style1"><strong>Search rules-1</strong></font></p>
	    <p>1.As for the search rules in detail,please check the left table . </p>
	    <p>2.The service searches data based on the <font class="style2"><strong>and</strong></font>-logic by default. </p>
	    <p>3.If users still can't find the correct record, please fill in one keyword <font class="style2"><strong>or </strong></font> in any place of the text field, and  the search outcome shall display the matching data based on or-logic in search text field.</p>
	    </td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchLogics_2.gif" ></td>
		<td valign="top" ><p><font class="style1"><strong>Search rules-2</strong></font></p>
	    <p>1.As for the search rules in detail,please check the left table . </p>
	    </td>
	</tr>	
       <tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_6.gif" width="283" height="213"></td>
		<td valign="top" ><p><font class="style1"><strong>Remove:</strong></font></p>
	    <p>1.Click one or more <strong>check boxes</strong> to select the record to be removed at the <strong>storage master table</strong>, and this record shall be highlighted,as (1). </p>
	    <p>2.(Or , you can click the link at <strong>All </strong> word on  top of the check
	     boxes,and all of the check boxes in this page shall be selected.For clear the marks you
             selected ,just click the <strong>All</strong> sign once more to erase the selection.) </p>	    
	    <p>3.The <strong>Button Remove</strong> shows on the right bottom corner,as (2).</p>
	    <p>4.Click this remove button to complete the data removed procedure.</p></td>
	</tr>
	
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_3.gif" width="283" height="215"></td>
		<td valign="top" ><p><font class="style1"><strong>Update:</strong></font></p>
		<p>1.Click the link of the <strong>cell</strong> attribute at the <strong>storage master table </strong> on right upper frame .<p>
	        <p>2.Then ,you can see the data information showing to the <strong>storage editing table </strong>at right bottom corner.</p>
                </td>
        </tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_4.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Modify the data attribute you want (For example,click freezingDate text field ,and select a new date from the calendar displays to modify the freezingDate.).</p>
	        </td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_5.gif"> </td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Click the <string>Button Update</strong> at the bottom to complete the update procedure.</p>
	    <p></p>
	       </td>
	</tr>
        <!---->
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_7.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Create data by filling in data from  menu input:Create1</strong></font></p>
            <p>1.<strong>There are two ways to create new storage data by menu input .</strong>The first way is :</p>
	    <p>2.Press the <strong>Button <font class="style3">Cancel</font></strong> to clear all the table fields
	   and  shift the mode to create,as (1)</p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/searchService/searchService_8.gif"></td>
		<td valign="top" ><p>3.Therefore, you can see the buttons change to<strong> Create</strong>
	        and <strong>Cancel</strong> at the lower right corner .</p>
	    <p>4.Fill in all the fields and press <strong>Button Create</strong> 
                to add new record.</p>
                </td>
        </tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/inputFromGUI/searchInputFromGUI_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Create:2</strong></font></p>
	    <p>1.Select the radio button of <strong> Data input from menu </strong> on <Strong> storage input/editing area </strong>at the right bottom corner.</p>
	    <p>2.The storage table displays , and users can filled in the data and press the <strong>create button</strong>.</p>
	</tr>
	
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/fromExcelFile/searchExcelInput_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Create data by importing excel files</strong></font></p>
	    <p>1.Click the radio button of the <strong>Data input from excel file </strong>option (This is the default page).</p>
	    </td>
	</tr>
	<!---->
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/fromExcelFile/searchExcelInput_2.gif" width="283" height="213"></td>
		<td valign="top" ><p><font class="style1"><strong>:</strong></font></p>
	    <p>1.Press <strong>Button Browse</strong> , and there is a popup window for browsing directory files.</p>
	    <p>2.Select the excel data file .</p>
	    <p>3.Click <strong>open</strong> button.</p>
	    <p>4.Press <strong>Button Create </strong> to submit the modified data.</p></td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/fromExcelFile/searchExcelInput_4.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Create</strong></font></p>
	    <p>1.After the data import processing complete, the <strong> prompt of the  uploaded data size </strong> shall display next to <strong>browse</strong> button .</p>
            <p>2.Click the <strong>prompt link </strong>, and the uploaded storage data shall display on the <strong>storage  master table</strong> at the upper-right frame.</p>     
            <p><strong>Note</strong>:Be sure that if users import one excel file which has the<strong> same data location</strong> as some data records in database ,then <font class="style2"><strong>the new input data shall overwrite the old data </strong></font>in database.   </p>       
                </td>
	</tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/fromExcelFile/searchExcelInput_5.gif" width="283" height="215"></td>
		<td valign="top" ><p><font class="style1"><strong>Remove</strong></font></p>
		<p>1.Users can select one or more <strong>check boxes</strong> of the storage records in storage master table,and the <strong> remove button</strong> displays at the right-bottom corner.    </p>
	        <p>2.Press <strong>Button Remove</strong> to remove uploaded data.</p>
	        </td>
        </tr>

	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/printerFriendly/searchPrinterFriendly_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Printer freindly</strong></font></p>
	    <p>1.Press <strong>printer friendly link</strong> on the top right corner of the storage master table.</p></td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/printerFriendly/searchPrinterFriendly_2.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Print configuration panel shall pop up.</p>
	    <p> 2.Select the  specific printer name (as 1),and click OK button.</p></td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/printerFriendly/searchPrinterFriendly_3.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Users can print a clear storage master table out.</td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/ID_Update/ID_Update_1.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong>Check storages devoid of the data of parentalLine,location,rowColumn or boxNumber ,and update those  values.</strong></font></p>
	    <p>1.Fill in the word "<font class="style2"><strong>id=NA</strong></font>" (case ignore) in the keywords text field.</p>
        <p>2.Click Button Submit .</p>
        <p>3.The storage records devoid of the data of <strong>ParentalLine,Location,RowColumn or BoxNumber </strong>shall display at the Storage Master Table.</p>
        </td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/ID_Update/ID_Update_2.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Move your mouse courser over the link of cell column, and being able see the storage id of the record show .</p>
	    <p>2.Click the link of cell data, and you can see the data information right display on at the right lower frame with the id  named <font class="style2"><strong> NA_.... </strong></font>,which mean one or more data of <strong>parentalLine,location,rowColumn,and boxNumber </strong> atrributes are lacked. </p>

        </td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/ID_Update/ID_Update_3.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Fill in the lack attribute to change ID NA_ .For example,update one field at the selected storage table , fill in "TEST" to the parentalLine field.</p>
	    <p>2.Press Button Submit.</p>
      </td>
    </tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/ID_Update/ID_Update_4.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.The storage ID NA_.. shall change to the new updated value.</td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/introduction/inventory/ID_Update/ID_Update_5.gif" width="283" height="215"></td>
                <td valign="top" ><p><font class="style1"><strong></strong></font></p>
	    <p>1.Following the example above,type the word "TEST" at keywords text field ,and press submit button.</p>
	    <p>2.Check the  modified data at the storage master table on the upper-right frame.</p>  
        </td>
	</tr>
	<tr onMouseOver="javascript:style.background='#E9E9E9'" onMouseOut="javascript:style.background='#FFFFFF'">
		<td valign="top">&nbsp;</td>
		<td valign="top" >&nbsp;</td>
	</tr>
	</table>

</BODY>
</HTML>
