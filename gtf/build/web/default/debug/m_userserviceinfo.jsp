<!--
	service info for the user
	used by usertable.jsp
	refer to the object in usertable.jsp page...
--->
  <table border="1" cellspacing="0" cellpadding="3" bordercolorlight="#CCCCCC" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" bgcolor="#EAEAEA" align="center" width="100%">
  <tr>
     <td bgcolor="#CCCCCC"><input type="radio" name="serv" value="MainDesk" <%="MainDesk".equals(m_userForm.getServ())?"checked":""%>></td>
     <td valign="top" nowrap class="bigger"> <img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/small_ico_MainDesk.gif" align="absmiddle">
     		<b><swapp:message key="siteware.MainDesk"/></b></td>
     <td nowrap class="smallest" width="100%"> --- </td>
  </tr>
	 <%
	 	Collection cl = m_userForm.getServices();
		Iterator iServ = cl.iterator();
		while (iServ.hasNext()) {
			String serv = (String)iServ.next();
			String servName = "siteware."+serv;
			String imgChk = messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/images/checked.gif";
			String imgUnchk = messageBean.getKeyValue("appbase")+messageBean.getKeyValue("skin")+"/images/unchecked.gif";
			%>
         <tr>
			<!--- 1st [td] --->
				<%if("AlertEvent".equals(serv)){%>
					<td bgcolor="#CCCCCC">&nbsp;</td>
				<%}else{%>
					<td bgcolor="#CCCCCC"><input type="radio" name="serv" value="<%=serv%>" <%=serv.equals(m_userForm.getServ())?"checked":""%>></td>
				<%}%>

            <td nowrap class="bigger"> <img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/small_ico_<%=serv%>.gif" align="absmiddle"><b>
              <swapp:message key="<%=servName%>" /></b> </td>

         <% if ("Reports".equals(serv)) {
					Service servObj = m_userForm.getUserService(serv);
					String[] areas = servObj.getAllAreas();

					if (areas.length>0) {%>
		            <td>
		              <table border="0" cellspacing="0" cellpadding="0">
							<%for (int i=0; i<areas.length; i++) { %>
			                <tr>
			                  <td nowrap class="smallest"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/serv_report.gif" width="24" height="22" align="absmiddle">
			                    <font color="#666666"><%=messageBean.getKeyValue("servicetable.area."+areas[i])%></font></td>
			                  <td nowrap class="smallest" align="right">&nbsp;
			                  	<%
											String[] opers = servObj.getOperationsInArea(areas[i]);
											if (opers.length>0) { %>
													<img src="<%=(hasOperation(opers,"broswe"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.browse"/>
													<img src="<%=(hasOperation(opers,"delete"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.delete"/>
											<% }
											else
												out.println("No Reports");
			                  	%>
			                  </td>
			                </tr>
							 <%}%>
		              </table>
		            </td>
					<%} else {%>
 						<td nowrap class="smallest"> <swapp:message key="userserviceinfo.no_report"/> </td>
					<%}%>


			<%} else if ("EditorsDesk".equals(serv)) {
					Service servObj = m_userForm.getUserService(serv);
					String[] areas = servObj.getAllAreas();

					if (areas.length>0) {%>
		            <td>
		              <table border="0" cellspacing="0" cellpadding="0">
							<%for (int i=0; i<areas.length; i++) { %>
			                <tr>
			                  <td nowrap class="smallest"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/serv_section.gif" width="24" height="22" align="absmiddle">
			                    <font color="#666666"><%=m_userForm.getSectionName(areas[i])%></font></td>
			                  <td nowrap class="smallest" align="right">&nbsp;
			                  	<%
											String[] opers = servObj.getOperationsInArea(areas[i]);
											if (opers.length>0) { %>
													<img src="<%=(hasOperation(opers,"add"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.add"/>
													<img src="<%=(hasOperation(opers,"edit"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.edit"/>
													<img src="<%=(hasOperation(opers,"publish"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.publish"/>
	                                                                                                <img src="<%=(hasOperation(opers,"stage"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.stage"/>												
	                                                                                                <img src="<%=(hasOperation(opers,"unlock"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.unlock"/>														
											<% }
											else
												out.println("No Sections");
			                  	%>
			                  </td>
			                </tr>
							 <%}%>
		              </table>
		            </td>
					<%} else {%>
						<td nowrap class="smallest"> <swapp:message key="userserviceinfo.no_section"/> </td>
					<%}%>


			<%} else if ("WritersDesk".equals(serv)) {
					Service servObj = m_userForm.getUserService(serv);
					String[] areas = servObj.getAllAreas();

					if (areas.length>0) {%>
		            <td>
		              <table border="0" cellspacing="0" cellpadding="0">
							<%for (int i=0; i<areas.length; i++) { %>
			                <tr>
			                  <td nowrap class="smallest"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/serv_folder.gif" width="24" height="22" align="absmiddle">
			                    <font color="#666666"><%=m_userForm.getFolderName(areas[i])%></font></td>
			                  <td nowrap class="smallest" align="right">&nbsp;
			                  	<%
											String[] opers = servObj.getOperationsInArea(areas[i]);
											if (opers.length>0){
													%>
													<img src="<%=(hasOperation(opers,"read"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.read"/>
													<img src="<%=(hasOperation(opers,"write"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.write"/>
													<%
												}
											else
											 out.println("No Folders");
			                  	%>
			                  </td>
			                </tr>
							 <%}%>
		              </table>
		            </td>
					<%} else {%>
						<td nowrap class="smallest"> <swapp:message key="userserviceinfo.no_folder"/> </td>
					<%}%>


			<%} else if ("AlertEvent".equals(serv)) {

					Service servObj = m_userForm.getUserService(serv);
					String[] areas = servObj.getAllAreas();

					if (areas.length>0) { %>
	            <td>
	              <table border="0" cellspacing="0" cellpadding="0">
						<%for (int i=0; i<areas.length; i++) { %>
		                <tr>
		                  <td nowrap class="smallest"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/serv_event.gif" width="24" height="22" align="absmiddle">
		                    <font color="#666666"><%=areas[i]%></font></td>
		                  <td nowrap class="smallest" align="right">&nbsp;
		                  	<%
										String[] opers = servObj.getOperationsInArea(areas[i]);
										if (opers.length>0){
												Iterator ai = AlertServiceManager.getInstance().getServiceSenderNames();
												while(ai.hasNext()){
													String aType = (String)ai.next();
													%>
													<img src="<%=(hasOperation(opers,aType))?imgChk:imgUnchk%>" align="absmiddle"><%=messageBean.getKeyValue("servicetable.operations."+aType)%>
													<%
												}

											}
										else
										 out.println("No Events");
		                  	%>
		                  </td>
		                </tr>
						 <%}%>
	              </table>
	            </td>
					<%} else {%>
						<td nowrap class="smallest" width="100%"> <swapp:message key="userserviceinfo.no_event"/> </td>
					<%}%>


         <%} else if ("ContentManager".equals(serv)) {

					Service servObj = m_userForm.getUserService(serv);
					String[] areas = servObj.getAllAreas();

					if (areas.length>0) {%>
		            <td width="100%">
		              <table border="0" cellspacing="0" cellpadding="0">
							<%for (int i=0; i<areas.length; i++) { %>
			                <tr>
			                  <td nowrap class="smallest"><img src="<swapp:message key="appbase"/><swapp:message key="skin"/>/images/serv_content.gif" width="24" height="22" align="absmiddle">
			                    <font color="#666666"><%=messageBean.getKeyValue("servicetable.area."+areas[i])%></font></td>
			                  <td nowrap class="smallest" align="right">&nbsp;
			                  	<%
											String[] opers = servObj.getOperationsInArea(areas[i]);
											if (opers.length>0) { %>
													<img src="<%=(hasOperation(opers,"simple"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.simple"/>
													<img src="<%=(hasOperation(opers,"advance"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.advance"/>
													<img src="<%=(hasOperation(opers,"export"))?imgChk:imgUnchk%>" align="absmiddle"><swapp:message key="servicetable.operations.export"/>
											<% }
											else
												out.println("No Setting");
			                  	%>
			                  </td>
			                </tr>
							 <%}%>
		              </table>
		            </td>
					<%} else {%>
 						<td nowrap class="smallest"> <swapp:message key="e_userserviceinfo.no_actionright"/> </td>
					<%}%>

			<%} else { %>
	            <td nowrap class="smallest" width="100%"> --- </td>
			<%}%>

			</tr>
		<%
		}
		%>
  </table><!---service info for the user end--->

<%!
public boolean hasOperation(String[] opers, String oper) {
	boolean result = false;
	for (int i=0; i<opers.length; i++) {
		if (oper.equals(opers[i]))	result=true;
	}
	return result;
}
%>