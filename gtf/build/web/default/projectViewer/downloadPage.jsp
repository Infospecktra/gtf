<%
 /** for the link  <a href="/archives/projectResultsOutput.xls">  
     need to configure JBoss
     1.open /JBoss/3.2/server/default/deploy/jbossweb-tomcat41.sar/META-INF/jboss-service.xml
     2.Define new context under <Host name="localhost".......> tag for external folder, like
       <Host name="localhost">
         <Context path="/archives" docBase="C:\\GTFWorkingDir\\" reloadable="true"> </Context>
     3.restart server
     4.can access file  ProjectResultsOutput.xls via 
       http://localhost:8080/archives/ProjectResultsOutput.xls   
       
  **/
%>
<html>
<style type="text/css">
       a {text-decoration:none}
       a:link{ color:#CCCCCC;}
       a:hover{color:#CCCCCC;}
       a:visited{color:#CCCCCC;}
<!--
.style2 {color: #cccccc}
.style6 {font-size: 16px}
-->
</style>
<body bgcolor="#6699CC">

<style type="text/css">
<!--
.style1 {
	color: #000000;
	font-size: 18px;
}
.style2 {color: #CCCCCC}
-->
</style>
<table width="100%" height="100%"  cellpadding="1"  border="0" align="center">
  <tr>
    <td >
    <table width="164%"  height="100%"  cellpadding="20" bgcolor="#CCCCCC"  border="1">
      <tr>
        <td><table bgcolor="#6699CC" width="372" border="1" align="center">
          <tr>
            <td width="100%" height="100%"><table width="368" border="1" cellpadding="20">
                <tr>
                  <td width="320"><p class="style1 style6"><strong>Please click following link to download</strong></p>
                    <p class="style1 style6"><strong><span class="style2"><a href="/archives/ProjectResultsOutput.xls"><tt>&gt;&gt;ProjectResultsOutput.xls&lt;&lt;</tt></a> </span></strong></p></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>