<%@ taglib uri="/WEB-INF/wf.tld" prefix="wf" %>
<jsp:useBean id="calendar" 
             class="org.yang.customized.gtf.services.messageManager.web.CalendarBean" 
             scope="session"/>

<html>
  <head>
    <title>Web Framework::leftFrame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="The JavaScript Source" content="no-cache">
  </head>
  <FRAMESET rows="*,180" border="0" framespacing="0" frameborder="no">
    <FRAME name="leftTopFrame"
           src="<wf:property type="system" key="appbase"/>/messageList.wf?actiontype=load&displayedYear=<%=calendar.getDisplayedYear()%>&displayedMonth=<%=calendar.getDisplayedMonth()%>&displayedDay=<%=calendar.getDisplayedDay()%>">
    <FRAME name="leftBottomFrame" 
           scrolling="NO" 
           src="<wf:property type="system" key="appbase"/>/calendar.wf?actiontype=load">
  </FRAMESET>
</HTML>
 