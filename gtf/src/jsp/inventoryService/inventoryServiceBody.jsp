<!--%@ page errorPage="../share/error.jsp" %-->

<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>

<jsp:useBean id="passport" 
             class="org.yang.web.applicationContainer.PassportBean" 
             scope="session"/>
<jsp:useBean id="inventoryBean"
             class="org.yang.customized.gtf.services.inventoryManager.web.InventoryBean"
             scope="session"/>

<%
   String rightService = "";
   if(inventoryBean!=null)
      rightService = inventoryBean.getServiceType();
   
   String appBase = passport.getSystemProperty("appbase");
   String skin    = passport.getSystemProperty("skin");
   String right   = passport.removeRuntimeProperty("right");

   if(null==right&&
      !"PI".equals(rightService)&&
      !"PT".equals(rightService)&&
      !"KR".equals(rightService)&&
      !"SLocator".equals(rightService))
   {
      right = appBase + skin + "/inventoryService/introduction.jsp";
   }
   else
   {
      right = appBase + skin + "/inventoryService/inventoryServiceBodyRight.jsp"  ; //right;
   }
   //System.out.println("right service---------------------------------------->" + rightService);
   //System.out.println("right ==///////////////////////////////////////////////" + right);

%>

<html>
  <head>
  <title>SWEE::Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <frameset cols="28%,*" border="3" framespacing="3" bordercolor="#E9E9E9" frameborder="yes">
    <frame name="leftFrame"
           src="<wf:property type="system" key="appbase"/><wf:property type="system" key="skin"/>/inventoryService/inventoryServiceBodyLeft.jsp" >
    <frame name="rightFrame2"
           src="<%=right%>" >
  </frameset>
  
  <noframes>
    <body bgcolor="#FFFFFF">
      Your browser doesn't support frame.
    </body>
  </noframes>
</html>

