<%@ page errorPage="../share/error.jsp" %>
<%@ page import="java.util.*" %>

<HTML>
<HEAD>
<title>SWEE::HELP</title>
<meta http-equiv="Content-Type" content="text/html; charset=<swapp:message key="siteware.charset" />">
<meta http-equiv="The JavaScript Source" content="no-cache">
<script language="JavaScript" src="../../js/folder_tree.js" type="text/javascript"></script>

<% String exp = request.getParameter("exp"); %>

<script language="JavaScript" type="text/javascript">
	/**
	 * document level variable and called functions
	 *
	 */
	var allLinkTarget = "rightFrame"; //** will use in the target value
	var treeTarget = "top.leftFrame"; //** tree target
	var treeTargetParent = "top"; //** treeTarget_Parent == top.xxx.xxx

	var treeBgProperty='bgcolor="#F1F1F1" leftmargin="5" topmargin="5" marginwidth="5" marginheight="5" onLoad="window.history.forward()"';
	var aHoverCCS='{background-color: #FFFFFF}';

	/**
	 * node style:
	 *  1) base node -- CANNOT close, has its own CCS
	 *  2) root node -- open/close, has its own CCS
	 *  3) folder node -- open/close, has its own CCS
	 *  4) doc node -- CANNOT open/close, has its own CCS
	 *
	 *  5) on node -- when the node is clicked
	 */
	var baseNodeCCS = '{font-family: Arial,Helvetica,sans-serif; font-size: 10pt; font-weight:bold; color:#999999}';
	var rootNodeCCS = '{font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 8pt; color:#006600}';
	var folderNodeCCS = '{font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 8pt}';
	var docNodeCCS = '{font-family: Verdana,Arial,Helvetica,sans-serif,PMingLiU; font-size: 8pt}';
	var onNodeCCS = '{color:#FFFFFF ;background-color: #006699}';
	var linkedCCS = '../css/swbase_English.css';

	/**
	 * Building the data in the tree
	 */
	function generateTree() {

		mybase = baseNode("SITEWare HELP","","","../images/bar_main.gif"); //** base node will always open

		engineer = folderNode("Engineers Desk","","",1,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		admin = folderNode("Account Manager","","",1,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		manager = folderNode("Content Manager","","",1,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		editor = folderNode("Editors Desk","","",1,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		writer = folderNode("Writers Desk","","",1,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");

		appendChild(mybase, engineer);
		appendChild(mybase, admin);
		appendChild(mybase, manager);
		appendChild(mybase, editor);
		appendChild(mybase, writer);

		//--- engineer
		appendChild(engineer, leafNode("LincenseKey Configuration","#","","../images/node_help.gif"));
		appendChild(engineer, leafNode("Domain Configuration","#","","../images/node_help.gif"));
		appendChild(engineer, leafNode("Secret Path","#","","../images/node_help.gif"));

		//--- admin
		appendChild(admin, leafNode("Group Configuration","#","","../images/node_help.gif"));
		appendChild(admin, leafNode("User Configuration","#","","../images/node_help.gif"));
		appendChild(admin, leafNode("Service Configuration","#","","../images/node_help.gif"));


		//--- manager
		manager_element = folderNode("Elements Configuration","","",0,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		appendChild(manager, manager_element);
		appendChild(manager_element, leafNode("Service Element","#","","../images/node_help.gif"));
		appendChild(manager_element, leafNode("Schedule Element","#","","../images/node_help.gif"));

		manager_resource = folderNode("Resource Configuration","","",0,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		appendChild(manager, manager_resource);
		appendChild(manager_resource, leafNode("Source","#","","../images/node_help.gif"));
		appendChild(manager_resource, leafNode("Rendition","#","","../images/node_help.gif"));

		manager_section = folderNode("Sections and Folders","","",0,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		appendChild(manager, manager_section);
		appendChild(manager_section, leafNode("Source Set Configuration","#","","../images/node_help.gif"));
		appendChild(manager_section, leafNode("Section Configuration","#","","../images/node_help.gif"));
		appendChild(manager_section, leafNode("Folder Configuration","#","","../images/node_help.gif"));

		//--- editor
		editor_add = folderNode("Add Content","","",0,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		appendChild(editor, editor_add);
		appendChild(editor_add, leafNode("Select and Add Content","#","","../images/node_help.gif"));
		appendChild(editor_add, leafNode("Search Content","#","","../images/node_help.gif"));
		appendChild(editor_add, leafNode("Suggest Content","#","","../images/node_help.gif"));

		editor_edit = folderNode("Edit Content","","",0,"../images/fld_help_cls.gif","../images/fld_help_opn.gif");
		appendChild(editor, editor_edit);
		appendChild(editor_edit, leafNode("Add Media","#","","../images/node_help.gif"));
		appendChild(editor_edit, leafNode("Edit Metadata","#","","../images/node_help.gif"));
		appendChild(editor_edit, leafNode("Edit Index Content Text","#","","../images/node_help.gif"));
		appendChild(editor_edit, leafNode("Edit Regular Content Text","#","","../images/node_help.gif"));

		appendChild(editor, leafNode("Publish Content Text","#","","../images/node_help.gif"));

		//--- writer
		appendChild(writer, leafNode("Create Content and Manage Personal Folder","#","","../images/node_help.gif"));
		appendChild(writer, leafNode("Manage Shared Folders","#","","../images/node_help.gif"));
		appendChild(writer, leafNode("Shared Folder and Source Mapping","#","","../images/node_help.gif"));



		<%if(exp!=null){%>
			openBranchName('<%=exp%>');
		<%}%>
	}

</script>

</HEAD>
<FRAMESET cols="30%,*" onLoad="initializeTree();" border="3" framespacing="2" bordercolor="#E9E9E9" frameborder="yes">
        <FRAME src="../share/loadtree.htm" name="leftFrame">
        <FRAME SRC="../share/dummy.htm" name="rightFrame">
</FRAMESET>
</HTML>
