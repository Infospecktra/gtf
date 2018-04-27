<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<HTML>
  <HEAD>
    <title>WAF::Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <style type="text/css">
      .BASESTYL {font-family: Arial,Helvetica,sans-serif; font-size: 10pt; font-weight:bold; color:#990000}
      .ROOTSTYL {font-family: Arial,Helvetica,sans-serif; font-size: 9pt; color:#000033; font-weight:bold}
      .NODESTYL {font-family: Arial,Helvetica,sans-serif; font-size: 9pt}
      .LEAFSTYL {font-family: Arial,Helvetica,sans-serif,PMingLiU; font-size: 9pt}
      .ONNODESTYL {color:#FFFFFF ;background-color: #006699}
    </style>
    <link rel=stylesheet type="text/css" href="/wf/default/css/swbase_English.css">
  </HEAD>
  <BODY bgcolor="#FFFFFF" leftmargin="5" rightmargin="5" topmargin="5" marginwidth="5" marginheight="5" onLoad="window.history.forward()">

    <wf:WebControl beanID = "projectsTree" 
                   controlBuilderName = "org.yang.customized.gtf.services.projectManager.web.ActiveProjectsTreeBuilder" />

  </BODY>
</HTML>