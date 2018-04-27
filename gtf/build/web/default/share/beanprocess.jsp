<%
	actionForm.setRequest(request);
	actionForm.setResponse(response);
	actionForm.setServlet(this);

	actionForm.process();

	//** If getSkipPageOutput equals false, do not output the JSP page
	if (actionForm.getSkipPageOutput()) return;
%>