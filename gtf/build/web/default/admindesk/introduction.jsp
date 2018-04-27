<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
<HEAD>
<link rel=stylesheet type="text/css" href="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/css/swbase_English.css">
<script language="JavaScript" src="<wf:property type="system" key="appbase"/>/js/img_rollover.js" type="text/javascript"></script>
</HEAD>

<BODY bgcolor="#FFFFFF" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10">


  <table width="100%" border="0" cellspacing="2" cellpadding="0">
    <tr>
      <td align="right" class="title"><font color="#990000">User Accounts</font></td>
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
			<b>Group</b>&nbsp;&nbsp; <a href="/wf/group.wf?actiontype=clear"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/English/btn_create.gif" border="0"></a><br>
			Groups allow you to assign the same permissioning rules to multiple users.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/usernode.gif"></td>
		<td valign="top" >
			<b>User</b>&nbsp;&nbsp; <a href="<wf:property type="system" key="appbase"/>/wf/user.wf?actiontype=clear"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/English/btn_create.gif" border="0"></a><br>
			Allows users to perform specific information-management tasks.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/node_AccountManager.gif"></td>
		<td valign="top" >
			<b>Account Service </b><br>
			Set up and maintain user and group permissioning.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/node_ScheduleManager.gif"></td>
		<td valign="top" >
			<b>Schedule Service</b><br>
	  Create, activate projects for management.	  </td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/node_ProjectManager.gif"></td>
		<td valign="top" >
			<b>Project Service</b><br>
			Remote administration - for technical support use only.
		</td>
	</tr>

	<tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
		<td valign="top"><img src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/images/gtf/node_Reports.gif"></td>
		<td valign="top" >
			<b>Report Service</b><br>
			Generate system and publication reports.
		</td>
	</tr>

	</table>

</BODY>
</HTML>
