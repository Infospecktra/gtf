<%@ page errorPage="../share/error.jsp" %>
<%@ taglib uri="/WEB-INF/swapp.tld" prefix="swapp" %>
<swapp:checkLogon desk="MainDesk" />

<jsp:useBean id="serviceBar" class="com.screamingmedia.siteware.webService.globle.ServiceBar" scope="session"/>
<jsp:useBean id="messageBean" class="com.screamingmedia.siteware.webService.globle.MessageBean" scope="session"/>
<html>
<head>
	<title>Untitled Document</title>
	<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
	<link rel=stylesheet type="text/css" href="<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css">
	<script language="JavaScript" src="<swapp:message key="appbase"/>/js/detect_browser.js" type="text/javascript"></script>

	<style type="text/css">
	  #superball { position:absolute; left:0; top:0; visibility:hide; visibility:hidden; width:40; height:40; z-index:10; }
	</style>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!--
<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr>
    <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_title1.gif" width="342" height="110" border="0"></td>
    <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_title2<swapp:message key="" logo="VERSION"/>.gif" width="308" height="110" border="0"></td>
    <td width="100%">&nbsp;</td>
</tr>
</table>
-->

<!---Main----->

<br>
<table width="80%" border="0" cellspacing="5" cellpadding="0" align="center">


<!--- 1st part ------>
<tr>
    <td class="title"><font color="#000066"><b>&nbsp;<swapp:message key="swmain_body.content_composing"/></b></font></td>
</tr>

<tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_left.gif" width="16" height="2"></td>
          <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_bk.gif" width="100%"><img src="images/1PIX.gif" width="1" height="2"></td>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_right.gif" width="16" height="2"></td>
      </tr>
      </table>
    </td>
</tr>

<tr>
    <td>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("EditorsDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.EditorsDesk")):"#"%>" <%=serviceBar.validService("EditorsDesk")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_editor.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("EditorsDesk")){%><font color="#CCCCCC"><%}%>
	                	<b><swapp:message key="siteware.EditorsDesk"/></b><br>
	                	<swapp:message key="swmain_body.edit_desk_desc"/>
							<br><%if(!serviceBar.validService("EditorsDesk")){%></font><%}%>
							<font color="#CC0000"><%=serviceBar.validService("EditorsDesk")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>

          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("WritersDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.WritersDesk")):"#"%>" <%=serviceBar.validService("WritersDesk")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_writer.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("WritersDesk")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.WritersDesk"/></b><br>
                	<swapp:message key="swmain_body.writer_desk_desc"/>
						<br><%if(!serviceBar.validService("WritersDesk")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("WritersDesk")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
      </tr>
      </table>

    </td>
</tr>




<!--- section part ------>

<tr>
    <td class="title"><font color="#000066"><b>&nbsp;<swapp:message key="swamin_body.sys_config"/></b></font></td>
</tr>

<tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_left.gif" width="16" height="2"></td>
          <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_bk.gif" width="100%"><img src="images/1PIX.gif" width="1" height="2"></td>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_right.gif" width="16" height="2"></td>
      </tr>
      </table>
    </td>
</tr>

<tr>
    <td>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("EngineersDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.EngineersDesk")):"#"%>" <%=serviceBar.validService("EngineersDesk")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_engineer.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("EngineersDesk")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.EngineersDesk"/></b><br>
                	<swapp:message key="swmain_body.engineer_desk_desc"/>
                	<br><%if(!serviceBar.validService("EngineersDesk")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("EngineersDesk")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("AccountManager")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.AccountManager")):"#"%>" <%=serviceBar.validService("AccountManager")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_admin.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("AccountManager")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.AccountManager"/></b><br>
                	<swapp:message key="swmain_body.acc_manager_desc"/>
						<br><%if(!serviceBar.validService("AccountManager")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("AccountManager")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
      </tr>
      <tr>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("ContentManager")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.ContentManager")):"#"%>" <%=serviceBar.validService("ContentManager")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_manager.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("ContentManager")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.ContentManager"/></b><br>
                	<swapp:message key="swmain_body.content_control_desc"/>
						<br><%if(!serviceBar.validService("ContentManager")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("ContentManager")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
          
          <!-- Content Server begins-->
          
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("ContentServer")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.ContentServer")):"#"%>" <%=serviceBar.validService("ContentServer")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_cserver.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("ContentServer")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.ContentServer"/></b><br>
                	<swapp:message key="swmain_body.content_server_desc"/>
                	<br><%if(!serviceBar.validService("ContentServer")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("ContentServer")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
          
          <!-- Content Server ends-->          
      </tr>
      </table>

    </td>
</tr>

<!------------------ 3rd part ------>
<tr>
    <td class="title"><font color="#000066"><b>&nbsp;<swapp:message key="swmain_body.sys_monitor"/></b></font></td>
</tr>

<tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_left.gif" width="16" height="2"></td>
          <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_bk.gif" width="100%"><img src="images/1PIX.gif" width="1" height="2"></td>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_right.gif" width="16" height="2"></td>
      </tr>
      </table>
    </td>
</tr>

<tr>
    <td>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("Reports")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.Reports")):"#"%>" <%=serviceBar.validService("Reports")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_report.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("Reports")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.Reports"/></b><br>
                	<swapp:message key="swmain_body.reports_desc"/>
						<br><%if(!serviceBar.validService("Reports")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("Reports")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("Diagnosis")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.Diagnosis")):"#"%>" <%=serviceBar.validService("Diagnosis")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_diagnos.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("Diagnosis")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.Diagnosis"/></b><br>
                	<swapp:message key="swmain_body.diagnosis_desc"/>
						<br><%if(!serviceBar.validService("Diagnosis")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("Diagnosis")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>

          </td>
      </tr>
      </table>


    </td>
</tr>

<!--- 4th part ------>
<tr>
    <td class="title"><font color="#000066"><b>&nbsp;<swapp:message key="swmain_body.content_syndication"/></b></font></td>
</tr>

<tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_left.gif" width="16" height="2"></td>
          <td background="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_bk.gif" width="100%"><img src="images/1PIX.gif" width="1" height="2"></td>
          <td><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/line_right.gif" width="16" height="2"></td>
      </tr>
      </table>
    </td>
</tr>

<tr>
    <td>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td valign="top" width="50%">
            <table width="100%" border="0" cellspacing="0" cellpadding="10">
            <tr onmouseover="javascript:style.background='#E9E9E9'" onmouseout="javascript:style.background='#FFFFFF'">
                <td valign="top"><a href="<%=serviceBar.validService("Syndicaster")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("page.Syndicaster")):"#"%>" <%=serviceBar.validService("Syndicaster")?"target=\"_top\"":""%>><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/main_syn.gif" border="0"></a></td>
                <td valign="top" width="100%"><%if(!serviceBar.validService("Syndicaster")){%><font color="#CCCCCC"><%}%>
                	<b><swapp:message key="siteware.Syndicaster"/></b><br>
                	<swapp:message key="swmain_body.syndicaster_desc"/>
						<br><%if(!serviceBar.validService("Syndicaster")){%></font><%}%>
						<font color="#CC0000"><%=serviceBar.validService("Syndicaster")?"":messageBean.getKeyValue("servicebar.noservice")%></font>
                </td>
            </tr>
            </table>
          </td>
          <td valign="top" width="50%">
				&nbsp;
			 </td>
      </tr>
      </table>


    </td>
</tr>
</table>

<span id="superball"><a href="javascript:openBallWin('blank.htm')" onClick="cleanBall();"><img name="superballImage" src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/scrmball.gif" border="0"></a></span>
<script language="JavaScript" src="<swapp:message key="appbase"/>/js/bouncing_ball.js" type="text/javascript"></script>

<OBJECT ID="MSAgent" CLASSID="CLSID:D45FD31B-5C6E-11D1-9EC1-00C04FD7081F" width=0 height=0 CODEBASE="#VERSION=2,0,0,0"></OBJECT>
<script language="JavaScript" src="<swapp:message key="appbase"/>/js/msagent.js" type="text/javascript"></script>

</body>
</html>

<script language="JavaScript" type="text/javascript">

document.onkeydown = runThisHaha;
var KK_1 = "";
var KK_2 = "";

function runThisHaha(e) {
	  var press = null ;

	  if (isNS4) {
	  		press = e.which ;
	  }
	  else if (isIE4) {
	     	e = window.event ;
	     	press = e.keyCode ;
	  }

	  //**************** for merlin
	  if (press==74) {
	  		//*** J
			KK_1="J";
			KK_2="";
	  }
	  else if (press==83 && KK_1=="J") {
	  		//*** S
			loadAgent();
			KK_1="";
	  }
	  //**************** for ball
	  else if (press==73 || press==105) {
	  		//*** I
			KK_2="I";
			KK_1="";
	  }
	  else if ((press==81&&KK_2=="I") || (press==113&&KK_2=="I")) {
	  		//*** Q
        if (document.BallState==0)
        {
          document.BallSpeed=10;
          playBall();
          document.BallState=1;
        }
        else if (document.BallState==1)
        {
          document.BallSpeed=0;
          document.BallState=2;
        }
        else if (document.BallState==2)
        {
          KK_2="";
  		  	 hideBall();
          document.BallState=0;
        }

	  }
	  else if (press==32 && document.BallState!=0) {
	  		cleanBall();
	  }
}


//***********************************
function cleanBall()  {
			KK_2="";
			document.BallState=0;
			document.BallSpeed=0;
			hideBall();
}

//***********************************
function openBallWin(s)  {
   newFunWin = window.open(s,"SITEWareFun",'status=no,menubar=no,toolbar=no,resizable=yes,scrollbars=yes,location=no');
   newFunWin.focus();
}

</script>

