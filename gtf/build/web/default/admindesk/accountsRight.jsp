<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
<HEAD>
<link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
<script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
</HEAD>

<BODY bgcolor="#FFFFFF" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10">


  <table width="100%" border="0" cellspacing="2" cellpadding="0">
    <tr>
      <td align="right" class="title"><font color="#990000">User Administrator</font></td>
    </tr>
    <tr>
      <td bgcolor="#990000"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/1PIX.gif" height="1" width="1"></td>
    </tr>
  </table>

	<table width="100%" border="0" cellspacing="0" cellpadding="5"  bgcolor="#FFFFFF" align="center">
	<tr>
		<td class="smallest" align="left">
			Set up and maintain user and group permissioning.
		</td>
	</tr>
	</table>

	<!---  information -->
	<br>

	<table width="90%" border="0" cellspacing="0" cellpadding="8" align="center">

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/groupnode.gif"></td>
		<td valign="top" >
			<b>Group</b>&nbsp;&nbsp; <a href="/gif/group.scrm?actiontype=clear"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/English/btn_create.gif" border="0"></a><br>
			Groups allow you to assign the same permissioning rules to multiple users.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/usernode.gif"></td>
		<td valign="top" >
			<b>User</b>&nbsp;&nbsp; <a href="<wf:property type="system" key="appbase"/>/user.scrm?actiontype=clear"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/English/btn_create.gif" border="0"></a><br>
			Allows users to perform specific information-management tasks.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_EditorsDesk.gif"></td>
		<td valign="top" >
			<b>Editor's Desk</b><br>
			Select, edit, illustrate, arrange and publish information.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_WritersDesk.gif"></td>
		<td valign="top" >
			<b>Writer's Desk</b><br>
			Create original content.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_AccountManager.gif"></td>
		<td valign="top" >
			<b>User Administrator</b><br>
			Set up and maintain user and group permissioning.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_ContentManager.gif"></td>
		<td valign="top" >
			<b>Information Processor</b><br>
			Create rules for information workflow and management.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_EngineersDesk.gif"></td>
		<td valign="top" >
			<b>Engineer's Desk</b><br>
			Remote administration - for technical support use only.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_Reports.gif"></td>
		<td valign="top" >
			<b>Report Manager</b><br>
			Generate system and publication reports.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_Diagnosis.gif"></td>
		<td valign="top" >
			<b>System Monitor</b><br>
			Evaluate and diagnose any Actrellis issues.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_Syndicaster.gif"></td>
		<td valign="top" >
			<b>Syndication Manager</b><br>
			Manage syndication networks.
		</td>
	</tr>


	</table>

</BODY>
</HTML>
