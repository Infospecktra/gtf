  /*
	*
	* Code available at: http://javascript.internet.com/foldertree/foldertree.zip
	* Author: Marcelino Alves Martins (martins@hks.com)
	* Version control:  Creation: January/97. Last changes: June/97
	* for other information refer to http://www.geocities.com/Paris/LeftBank/2178/foldertree.html
	*
	* ------------------------------------------------------------------------------
	* Modified by JASON WANG, 05/2001
	*
	* a. There can be 4 types of nodes:
	*  1) base node -- CANNOT close, has its own CCS
	*  2) root node -- open/close, has its own CCS
	*  3) folder node -- open/close, has its own CCS
	*  4) doc node -- CANNOT open/close, has its own CCS
	*
	* b. Every node can has it's own LINK and ICON Image
	*
	*/


	/**
	 * Globle Attributes
	 */
	var globleCounter = 0; //** UID for each node
	var timeOutId = 0;
	var rootNameAry = new Array; //** record root nodes
	var globleNodeRecorder=-10; //** record for no one

	/**
	 * Each node in the tree is an Array with 4+n positions
	 * node[0] is 0/1 when the node is closed/open ----> will use for node ID
	 * node[1] is 0/1 when the folder is closed/open
	 * node[2] is 1 if the children of the node are documents, no more children
	 * node[3] is the name of the folder
	 * node[4] is link for this folder
	 * node[5] is java script code
	 * node[6] is close img for this folder
	 * node[7] is open img for this folder
	 * node[8]...node[8+n] are the n children nodes
	 */

	/**
	 * Auxiliary function to build the node
	 */
	//*------------------------------------------------------------------
	function folderNode(name,link,jscript,root,cimg,oimg) {
		var arrayAux = new Array;

		arrayAux[0] = globleCounter++;
		arrayAux[1] = 0;
		arrayAux[2] = 0;
		arrayAux[3] = name;
		arrayAux[4] = link;
		arrayAux[5] = jscript;
		arrayAux[6] = cimg;
		arrayAux[7] = oimg;


		if (root) {	rootNameAry[rootNameAry.length] = globleCounter-1;	}
		return arrayAux;
	}

	//*------------------------------------------------------------------
	function leafNode(name,link,jscript,img) {
		var arrayAux = new Array;

		arrayAux[0] = globleCounter++;
		arrayAux[1] = 0;
		arrayAux[2] = 1;
		arrayAux[3] = name;
		arrayAux[4] = link;
		arrayAux[5] = jscript;
		arrayAux[6] = img;


		return arrayAux;
	}

	/**
	 *  Link node together
	 */
	//*------------------------------------------------------------------
	function appendChild(theParent, child) {
		if (theParent[2]==0) { //** only when theParent is folderNode can be add an child
			theParent[theParent.length] = child;
		}
		return child;
	}

	/**
	 * these are the last entries in the hierarchy, the local and remote links to html documents
	 *
	 * Modified by JASON WANG. 05/2001.
	 */
	//*------------------------------------------------------------------
	/*
	function generateDocEntry(docDescription, link, img) {
		var arrayAux = new Array;

		arrayAux[0] = globleCounter++;
		arrayAux[1] = docDescription;
		arrayAux[2] = link;
		arrayAux[3] = img;

		return arrayAux;
	}
	*/

	/**
	 * display functions
	 * redraws the left frame
	 *
	 * Modified by JASON WANG. 05/2001.
	 */
	//*------------------------------------------------------------------
	function redrawTree() {
		var doc = eval(treeTarget + '.' + 'document'); //** treeTarget is a globle val
		doc.clear();
		drawDocHeader(doc);

		redrawAlltrees(doc);

		drawDocFooter(doc);
		doc.close();
	}

	//*------------------------------------------------------------------
	/**
	 * recursive function over the tree structure called by redrawTree
	 */
	function redrawNode(foldersNode, doc, level, lastNode, leftSide) {
		var j=0;
		var i=0;

		doc.write('<table border="0" cellspacing="0" cellpadding="0">');
		doc.write('<tr><td valign="middle" nowrap>');
		doc.write(leftSide);

		if (level>0) {
			if (lastNode) //* the last 'brother' in the children array
			{
				if (foldersNode[2]==1) {
					doc.write('<img src="../../images/lastnode.gif" width="16" height="22">');
				}
				else {

					if (foldersNode[1]) {
						var ss = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Close folder\'; return true">';
						doc.write(ss + '<img src="../../images/lastnode_cls.gif" border="0"></a>');
					}
					else {
					   var ss = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Open folder\'; return true">';
					   doc.write(ss + '<img src="../../images/lastnode_opn.gif" border="0"></a>');
					}
				}
				leftSide = leftSide + '<img src="../../images/blank.gif" width="16" height="22">';
			}
			else
			{
				if (foldersNode[2]==1) {
					doc.write('<img src="../../images/node.gif" width="16" height="22">');
				}
				else {
					if (foldersNode[1]) {
						var ss = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Close folder\'; return true">';
						doc.write(ss + '<img src="../../images/node_cls.gif" border="0"></a>');
					}
					else {
					   var ss = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Open folder\'; return true">';
					   doc.write(ss + '<img src="../../images/node_opn.gif" border="0"></a>');
					}
				}
				leftSide = leftSide + '<img src="../../images/vertline.gif" width="16" height="22">';
			}
		}

		displayIconAndLabel(foldersNode, doc);
		doc.write('</td></tr></table>');

		if (foldersNode.length>8 && foldersNode[1]) //there are sub-nodes and the folder is open
		{
			if (!foldersNode[2]) //for folders with folders
			{
				level=level+1;
				for (i=8; i<foldersNode.length;i++)
					if (i==foldersNode.length-1)
						redrawNode(foldersNode[i], doc, level, 1, leftSide);
					else
						redrawNode(foldersNode[i], doc, level, 0, leftSide);
			}
			/*
			else //for leaf node
			{
		      for (i=7; i<foldersNode.length; i++)
		      {
					doc.write('<table border="0" cellspacing="0" cellpadding="0" valign="center">');
					doc.write('<tr><td nowrap>');
					doc.write(leftSide);
					if (i==foldersNode.length-1)
						doc.write('<img src="../../images/lastnode.gif" width="16" height="22">');
					else
					   doc.write('<img src="../../images/node.gif" width="16" height="22">');

					//doc.write(foldersNode[i]);
					displayDocNode(foldersNode[i], doc);
					doc.write('</td></tr></table>');
		      }
			}
			*/
		}
	}

	//*------------------------------------------------------------------
	function displayLeafNode(docNode, doc) {
		var onNode = false;
		if (globleNodeRecorder==docNode[0]) { onNode=true; }

		var retString = '';
		var linkString ='';

		if (docNode[4]!='') { //** will not generate link if there is no link
			linkString = '<a href="' + docNode[4] + '" target="' + allLinkTarget + '" ' + docNode[5] +' onClick="javascript:' + treeTargetParent + '.openBranch(\'unexpand\',' + docNode[0] + ',\'' + docNode[3] + '\')">'; //** allLinkTarget is a globle val
		}

		retString = '<img src="' + docNode[6] + '" alt="' + docNode[3] + '" border="0"></td><td nowrap>' + (linkString==''?'':linkString) + '<span class="LEAFSTYL">' + (onNode?'<span class="ONNODESTYL">':'') + docNode[3] + (onNode?'</span>':'') + '</span>' + (linkString==''?'':'</a>');

		doc.write(retString);
	}

	/**
	 * builds the html code to display a folder and its label
	 *
	 * modified by JASON WANG
	 */
	//*------------------------------------------------------------------
	function displayIconAndLabel(foldersNode, doc) {
		if (foldersNode[2]==1) { //** leaf node
			displayLeafNode(foldersNode, doc);
		}
		else {
			var onNode = false;
			if (globleNodeRecorder==foldersNode[0]) { onNode=true; }

			if (foldersNode[0]==-1) { //* special case: base node
				doc.write('<img src="' + foldersNode[6] + '" border="0">');
				doc.write('</td><td valign="middle" align="left" nowrap>');
				if (foldersNode[4]=='')
					doc.write('<span class="BASESTYL">'+(onNode?'<span class="ONNODESTYL">':'')+foldersNode[3]+(onNode?'</span>':'')+'</span>');
				else
					doc.write('<a href="' + foldersNode[4] + '" target="' + allLinkTarget + '" ' + foldersNode[5] + ' onClick="javascript:'+treeTargetParent+'.openBranch(\'unexpand\','+foldersNode[0]+',\''+foldersNode[3]+'\')"><span class="BASESTYL">'+(onNode?'<span class="ONNODESTYL">':'')+foldersNode[3]+(onNode?'</span>':'')+'</span></a>');
			}
			else {
				var rootNode = false;
				var tempstr = '';
				var linkString = '';

				if (isARoot(foldersNode[0])) { rootNode = true; }

				if (foldersNode[1]) {
					linkString = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Close folder\'; return true">';
					doc.write(linkString + '<img src="' + foldersNode[7] + '" border="0"></a>');
				}
				else {
				   linkString = '<a href="javascript:' + treeTargetParent + '.openBranch(\'onlyexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" onMouseOver="window.status=\'Open folder\'; return true">';
				   doc.write(linkString + '<img src="' + foldersNode[6] + '" border="0"></a>');
				}

				linkString = '';
				//** case 1: click on the Link String, the node will NOT close after it opened
				if (!foldersNode[1]) {
					tempstr= treeTargetParent + '.openBranch(\'allexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')';
				}
				else {
					tempstr= treeTargetParent + '.openBranch(\'unexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')';
				}
				//** case 2: click on the Link String, the node will close/open
				/*
					tempstr=' onClick="javascript:' + treeTargetParent + '.openBranch(\'allexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')" ';
				*/

				if (foldersNode[4]!='') { // has link, open/close folder based on case 1 or 2, and go to link in the right frame
					linkString = '<a href="' + foldersNode[4] + '" target="' + allLinkTarget + '" ' + foldersNode[5] + ' onClick="' + tempstr + '">';
				}
				else {
					//** no link, open or close folder only
					linkString = '<a href="javascript:'+treeTargetParent + '.openBranch(\'allexpand\',' + foldersNode[0] + ',\'' + foldersNode[3] + '\')'+'">';
				}

				doc.write('</td><td valign="middle" align="left" nowrap>');
				doc.write((linkString==''?'':linkString) + (rootNode==true?'<span class="ROOTSTYL">':'<span class="NODESTYL">') + (onNode?'<span class="ONNODESTYL">':'') + foldersNode[3] + (onNode?'</span>':'') + '</span>' + (linkString==''?'':'</a>'));
			}
		}
	}


	/**
	 * Recursive functions
	 * when a parent is closed all children also are
	 *
	 * modified by JASON WANG
	 */
	/*
	function closeFolders(foldersNode) {

		var i=0;
		if (!foldersNode[2])
		{
		    for (i=7; i< foldersNode.length; i++)
		    	closeFolders(foldersNode[i]);
		}
		foldersNode[1] = 0;
	}
	*/

	/**
	 * recursive over the tree structure
	 * called by openbranch
	 *
	 */
	function clickOnFolderRec(foldersNode, id, folderName) {
		var i=0;
		if (foldersNode[0] == id) {

			//** if node is closed, open it, vice versa
			if (foldersNode[1]) {
				//closeFolders(foldersNode);  //* this is for recurrsive closed action
				foldersNode[1] = 0;
				// alert('MATCH---->f[0]=close');
			}
			else {
			   foldersNode[1] = 1;
			   // alert('MATCH---->f[0]=open');
			}
			return true;
		}
		else {

		   if (!foldersNode[2])
		   	for (i=8; i< foldersNode.length; i++){
		      	if(clickOnFolderRec(foldersNode[i], id, folderName) ) {
		      		// alert('return true');
		      		return true;
		      	}
		      }
		}
	}
	//*------------------------------------------------------------------
	function clickOnFolderRecName(foldersNode, folderName) {
		var i=0;
		if (foldersNode[3] == folderName) {
			//** if node is closed, open it, vice versa
			if (foldersNode[1]) {
				//closeFolders(foldersNode);
				foldersNode[1] = 0;
			}
			else {
			   foldersNode[1] = 1;
			}
		}
		else {
		   if (!foldersNode[2])
		   	for (i=8; i< foldersNode.length; i++)
		      	clickOnFolderRecName(foldersNode[i], folderName);
		}
	}

	/**
	 * called when the user clicks on a folder
	 *
	 * 3 kind of open branch
 	 */
	function openBranch(action,id,branchName) {
		// allexpand: expand tree and change node color
		// unexpand: change node color only
		// onlyexpand: expand tree only
		if (action=='allexpand') {
			globleNodeRecorder = id;
			checkAllClickOnRec(id,branchName);
		}
		else if (action=='unexpand') {
			globleNodeRecorder = id;
		}
		else if (action=='onlyexpand') {
			checkAllClickOnRec(id,branchName);
		}

		timeOutId = setTimeout("redrawTree()",100);
	}

	/**
	 * called when the user clicks on a folder by Folder Name
	 *
	 * 3 kind of open branch
 	 */
	function openBranchName(branchName) {
		globleNodeRecorder = -10;
		clickOnFolderRecName(mybase, branchName);

		timeOutId = setTimeout("redrawTree()",100);
	}

	//* called after this html file is loaded
	function initializeTree() {
		generateTree();
		redrawTree();
	}


	/*--------------------------------------------------
	 * The following Codes are added by JASON WANG
	 *--------------------------------------------------
	 *
	 * node style:
	 *  1) base node -- CANNOT close, has its own CCS
	 *  2) root node -- open/close, has its own CCS
	 *  3) folder node -- open/close, has its own CCS
	 *  4) doc node -- CANNOT open/close, has its own CCS
	 *
	 *  5) on node -- when the node is clicked
	 */
	function drawDocHeader(doc) {
		doc.writeln('<html>');
		doc.writeln('<head>');
		doc.writeln('<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">');
		doc.writeln('<style type="text/css">');

		//doc.writeln('a {text-decoration: none}');
		//doc.writeln('a:hover {color:#CC0000; text-decoration: underline}');
		//doc.writeln('a:hover '+aHoverCCS);

		doc.writeln('.BASESTYL ' + baseNodeCCS);
		doc.writeln('.ROOTSTYL ' + rootNodeCCS);
		doc.writeln('.NODESTYL ' + folderNodeCCS);
		doc.writeln('.LEAFSTYL ' + docNodeCCS);
		doc.writeln('.ONNODESTYL' + onNodeCCS);

		doc.writeln('</style>');

		doc.writeln('<link rel=stylesheet type="text/css" href="'+linkedCCS+'">');

		doc.writeln('</head>');
		doc.writeln('<body '+treeBgProperty+'>');
	}

	//------------------------------------
	function drawDocFooter(doc) {
		doc.writeln('</body>');
		doc.writeln('</html>');
	}
	//------------------------------------
	function isARoot(no) {
		var i=0;
		for (i=0; i<rootNameAry.length; i++)
			if (no==rootNameAry[i])
				return true;

		return false;
	}
	//------------------------------------
	function baseNode(name,link,jscript,img) {
		var arrayAux = new Array;
		arrayAux[0] = -1;
		arrayAux[1] = 1;
		arrayAux[2] = 0;
		arrayAux[3] = name;
		arrayAux[4] = link;
		arrayAux[5] = jscript;
		arrayAux[6] = img;
		arrayAux[7] = img;

		return arrayAux;
	}

	//---------------------------------------------------
	//
	// the following code need to be modified
	// for the setting based on your code
	//
	//---------------------------------------------------

	//------------------------------------
	function redrawAlltrees(doc) {

		redrawNode(mybase, doc, 0, 1, "");

		/* code without base
		redrawNode(Tree1, doc, 0, 1, "");
		redrawNode(Tree2, doc, 0, 1, "");
		redrawNode(Tree3, doc, 0, 1, "");
		*/
	}
	//------------------------------------
	function checkAllClickOnRec(id, branchName) {

		clickOnFolderRec(mybase, id, branchName);

		/* code without base
		clickOnFolderRec(Tree1, id, branchName);
		clickOnFolderRec(Tree2, id, branchName);
		clickOnFolderRec(Tree3, id, branchName);
		*/
	}

	/**
	 * base node and root node:
	 *
	 */
	var mybase = null;