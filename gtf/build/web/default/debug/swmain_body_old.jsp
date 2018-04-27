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

<script language="JavaScript" type="text/javascript">
	function switchDIV(showthis,showimg)
	{
		var dom = null;
		var domary = new Array();

		if (isIE4)
		{
			domary[0] = eval('document.all.content0.style');
			domary[1] = eval('document.all.content1.style');
			domary[2] = eval('document.all.content2.style');
			domary[3] = eval('document.all.content3.style');
			domary[4] = eval('document.all.content4.style');
			domary[5] = eval('document.all.content5.style');
			domary[6] = eval('document.all.content6.style');
			dom = eval('document.all.'+showthis+'.style');
		}
		else if (isNS4)
		{
			domary[0] = eval('document.content0');
			domary[1] = eval('document.content1');
			domary[2] = eval('document.content2');
			domary[3] = eval('document.content3');
			domary[4] = eval('document.content4');
			domary[5] = eval('document.content5');
			domary[6] = eval('document.content6');
			dom = eval('document.'+showthis);
		}

		for (i=0; i<domary.length; i++){
			domary[i].visibility = 'hidden';
		}
		dom.visibility = 'visible';


		document.main_engineer.src = '../images/main_engineer_off.gif';
		document.main_admin.src = '../images/main_admin_off.gif';
		document.main_manager.src = '../images/main_manager_off.gif';
		document.main_editor.src = '../images/main_editor_off.gif';
		document.main_writer.src = '../images/main_writer_off.gif';
		document.main_report.src = '../images/main_report_off.gif';

		if (showthis!='content0') {
			var img = null;
			img = eval('document.'+showimg);
			img.src='../images/'+showimg+'_on.gif';
		}

	}
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onResize="document.location.reload()">
<script language="JavaScript" type="text/javascript">
	var leftPos=0;

	if (isIE4)
		leftPos = document.body.clientWidth/2-215;
	else if (isNS4)
		leftPos = self.innerWidth/2-215;

	if (leftPos<150) leftPos=145;

	var cstr = new Array();
	cstr[0] = '#content0 {position:absolute; left:' + leftPos + '; top:110px; visibility:visible; z-index:3; }';
	cstr[1] = '#content1 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';
	cstr[2] = '#content2 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';
	cstr[3] = '#content3 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';
	cstr[4] = '#content4 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';
	cstr[5] = '#content5 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';
	cstr[6] = '#content6 {position:absolute; left:' + leftPos + '; top:110px; visibility:hidden; z-index:3; }';

	document.open();
	document.write('<style type="text/css">');
	document.write(cstr[0]);
	document.write(cstr[1]);
	document.write(cstr[2]);
	document.write(cstr[3]);
	document.write(cstr[4]);
	document.write(cstr[5]);
	document.write(cstr[6]);
	document.write('</style>');
	document.close();
</script>

<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td><a href="#" onMouseOver="switchDIV('content0','')"><img src="../images/main_title1.gif" width="342" height="110" border="0"></a></td>
    <td><a href="#" onMouseOver="switchDIV('content0','')"><img src="../images/main_title2<swapp:message key="" logo="VERSION"/>.gif" width="308" height="110" border="0"></a></td>
    <td width="100%">&nbsp;</td>
   </tr>
</table>

<table width="700" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
        	 <td width="60"><img src="../images/sidebar<swapp:message key="" logo="VERSION"/>.gif"></td>
          <td><a href="<%=serviceBar.validService("EditorsDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/editordesk/entry.jsp"):"#"%>" onMouseOver="switchDIV('content4','main_editor')" <%=serviceBar.validService("EditorsDesk")?"target=\"_top\"":""%>><img src="../images/main_editor_off.gif" name="main_editor" width="85" height="80" border="0"></a><br>
          	  <a href="<%=serviceBar.validService("WritersDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/writerdesk/writer.jsp"):"#"%>" onMouseOver="switchDIV('content5','main_writer')" <%=serviceBar.validService("WritersDesk")?"target=\"_top\"":""%>><img src="../images/main_writer_off.gif" name="main_writer" width="85" height="80" border="0"></a><br>
              <a href="<%=serviceBar.validService("ContentManager")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/managerdesk/manager.jsp"):"#"%>" onMouseOver="switchDIV('content3','main_manager')" <%=serviceBar.validService("ContentManager")?"target=\"_top\"":""%>><img src="../images/main_manager_off.gif" name="main_manager" width="85" height="80" border="0"></a><br>
              <a href="<%=serviceBar.validService("AccountManager")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/admindesk/accounts.jsp"):"#"%>" onMouseOver="switchDIV('content2','main_admin')" <%=serviceBar.validService("AccountManager")?"target=\"_top\"":""%>><img src="../images/main_admin_off.gif" name="main_admin" width="85" height="80" border="0"></a><br>
              <a href="<%=serviceBar.validService("Reports")?(messageBean.getKeyValue("appbase")+"/log.scrm"):"#"%>" onMouseOver="switchDIV('content6','main_report')" <%=serviceBar.validService("Reports")?"target=\"_top\"":""%>><img src="../images/main_report_off.gif" name="main_report" width="85" height="80" border="0"></a><br>
              <a href="<%=serviceBar.validService("EngineersDesk")?(messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/engineerdesk/engineer.jsp"):"#"%>" onMouseOver="switchDIV('content1','main_engineer')" <%=serviceBar.validService("EngineersDesk")?"target=\"_top\"":""%>><img src="../images/main_engineer_off.gif" name="main_engineer" width="85" height="80" border="0"></a></td>
          <td width="550" background="../images/main_bg_on.gif">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<span id="content0">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="swmain_body.swtitle"/></font></h3>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.swtitle.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>

<span id="content1">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.EngineersDesk"/></font></h3>
					<p><font color="#cc0000"><b>
						<%=serviceBar.validService("EngineersDesk")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
						</b></font></p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.engineersdesk.note"/>
            </td>
          </tr>




        </table>
    </td>
  </tr></table>
</span>

<span id="content2">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.AccountManager"/></font></h3>
						<p><font color="#cc0000"><b>
							<%=serviceBar.validService("AccountManager")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
							</b></font>
						</p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.accountmanager.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>

<span id="content3">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.ContentManager"/></font></h3>
						<p><font color="#cc0000"><b>
							<%=serviceBar.validService("ContentManager")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
							</b></font>
						</p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.writersdesk.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>

<span id="content4">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.EditorsDesk"/></font></h3>
						<p><font color="#cc0000"><b>
							<%=serviceBar.validService("EditorsDesk")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
							</b></font>
						</p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.editorsdesk.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>

<span id="content5">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.WritersDesk"/></font></h3>
						<p><font color="#cc0000"><b>
							<%=serviceBar.validService("WritersDesk")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
							</b></font>
						</p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.contentmanager.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>

<span id="content6">
  <table width="550" border="0" cellspacing="0" cellpadding="1"><tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="15">
          <tr>
            <td><h3><font color="#000066"><swapp:message key="siteware.Reports"/></font></h3>
						<p><font color="#cc0000"><b>
							<%=serviceBar.validService("Reports")?messageBean.getKeyValue("servicebar.hasservice"):messageBean.getKeyValue("servicebar.noservice")%>
							</b></font>
						</p>
            </td>
          </tr>
          <tr>
            <td class="bigger"><swapp:message key="swmain_body.contentmanager.note"/>
            </td>
          </tr>
        </table>
    </td>
  </tr></table>
</span>


<p>&nbsp;</p>


<object classid="CLSID:D45FD31B-5C6E-11D1-9EC1-00C04FD7081F" id="hkdownload" width="32" height="32">
</object>

<script>
/*
var MerlinID;
var MerlinACS;
hkdownload.Connected = true;
MerlinLoaded = LoadLocalAgent(MerlinID, MerlinACS);
Merlin = hkdownload.Characters.Character(MerlinID);
Merlin.Show();
Merlin.Play("Surprised");
Merlin.Speak("Welcome to SITE Ware!!");
Merlin.Speak("Site Ware team is lead by Al Ellmen and Brian McGuinty");
Merlin.Speak("and members are Steven Yang, Lei Liu, Hsin Hsien Wu, Celina Yang");
Merlin.Speak("Ava Liu, Yao Cheng, Hui Chang, Sejin Kim and");
Merlin.Play("Surprised");
Merlin.Speak("Jason Wang.");
Merlin.Play("DoMagic2");
Merlin.Play("GestureLeft");
Merlin.Speak("There must be a lot fun stuff in here...");
Merlin.Play("Pleased");
Merlin.Think("I will like to try it out...");
Merlin.Play("GestureDown");
Merlin.Speak("Enjoy yourself!");
Merlin.Play("Wave");
Merlin.Hide();
function LoadLocalAgent(CharID, CharACS) {
	LoadReq = hkdownload.Characters.Load(CharID, CharACS);
	return(true);
}
*/
</script>

</body>
</html>
