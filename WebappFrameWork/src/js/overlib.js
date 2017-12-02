////////////////////////////////////////////////////////////////////////////////////
//
//    ATTENTION NETSCAPE NAVIGATOR 3.0 USERS!!!
//
//    If you see this text while using the site and you did NOT click on
//    View -> Source, you're using a buggy browser.
//
//    FOLLOW THESE STEPS
//
//    Read through these two steps before doing them.
//    1. Press your BACK BUTTON.
//    2. Click on REFRESH or RELOAD.
//
//    You should now be able to use the site without seeing this message.
//    This problem can however return if your browser does not cache this
//    document correctly.
//
//    UPGRADE YOUR BROWSER
//
//    Upgrade your browser to Netscape's latest and you will not have this
//    problem any more.
//
//    Netscape browsers can be found at http://home.netscape.com/
//
////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////
//  overLIB 2.22  --  Please leave this notice.
//
//  By Erik Bosrup (erik@bosrup.com)  Last modified 1999-03-31
//  Portions by Dan Steinman, Landon Bradshaw and Gnowknayme.
//
//  Get the latest version at http://www.bosrup.com/web/overlib/
//
//  This script library was created for my personal usage from the start
//  but then it became so popular I made an easy to use version. It's that
//  version you're using now. Since this is free please don't try to sell
//  this solution to a company claiming it is yours. Give me credit where
//  credit is due and I'll be happy. And I'd love to see any changes you've
//  done to the code. Free to use - don't abuse.
////////////////////////////////////////////////////////////////////////////////////

//*** Global Properties
//----------------------------------------------------------------------------------------------------------------

////////////////////////////////////////////////////////////////////////////////////
// CONFIGURATION
////////////////////////////////////////////////////////////////////////////////////

if (typeof fcolor == 'undefined') { var fcolor = "#CCCCFF";} // Main background color (the large area)
if (typeof backcolor == 'undefined') { var backcolor = "#FFFFFF";} // Border color and color of caption
if (typeof textcolor == 'undefined') { var textcolor = "#000066";} // Text color
if (typeof capcolor == 'undefined') { var capcolor = "#FFFFFF";} // Color of the caption text
if (typeof closecolor == 'undefined') { var closecolor = "#9999FF";} // Color of "Close" when using Sticky

if (typeof width == 'undefined') { var width = "300";} // Width of the popups in pixels, 100-300 pixels is typical
if (typeof border == 'undefined') { var border = "1";}  // How thick the border should be in pixels, 1-3 pixels is typical

//** How many pixels to the right/left of the cursor to show the popup
//** Values between 3 and 12 are best

if (typeof OL_offsetx == 'undefined') { var OL_offsetx = 10;}

//** How many pixels to the below the cursor to show the popup
//** Values between 3 and 12 are best

if (typeof OL_offsety == 'undefined') { var OL_offsety = -10;}
if (typeof OL_textsize == 'undefined') { var OL_textsize = "2";}

////////////////////////////////////////////////////////////////////////////////////
// END CONFIGURATION
////////////////////////////////////////////////////////////////////////////////////

OL_ns4 = (document.layers)? true:false
OL_ie4 = (document.all)? true:false
OL_dom = (document.getElementById) ? true : false;

//*** global vars

var OL_x = 0; //*** current x
var OL_y = 0; //*** current y
var OL_show = false; //*** 0: is hidden, 1: is visible
var OL_callNoDisp = false;
var OL_align = 1; //*** based on cursor --> 0:left, 1:right, 2:center

//*** overDiv is predefined before this script is called

//*** define over var OL_DivObj
OL_DivObj = null;
OL_StyleObj = null;

if (OL_ns4) OL_DivObj = document.overDiv;
if (OL_ie4) OL_DivObj = document.all.overDiv;
if (OL_dom) OL_DivObj = document.getElementById("overDiv");

if (OL_ns4) OL_StyleObj = document.overDiv;
if (OL_ie4) OL_StyleObj = document.all.overDiv.style;
if (OL_dom) OL_StyleObj = document.getElementById("overDiv").style;

//*** catch mouse event
document.onmousemove = mouseMove; //*** IE and DOM
if (OL_ns4) document.captureEvents(Event.MOUSEMOVE); //*** NS


//*** functions
//----------------------------------------------------------------------------------------------------------------
// Public functions to be used on pages.

function dispImg(link,text,x,y) {
	OL_offsetx = x;
	OL_offsety = y;

	img = '<img src="'+link+'">';
	txt = '<TABLE WIDTH="100" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#660000"><TR><TD><TABLE WIDTH="100%" BORDER="0" CELLPADDING="2" CELLSPACING="0" BGCOLOR="#FFFFCC"><TR><TD ALIGN="CENTER">'
			+ img +
			'<BR><span style="font-family:Arial,Helvetica,sans-serif; font-size:8pt; color:#660000">'
			+ text +
			'</span></TD></TR></TABLE></TD></TR></TABLE>';

	layerWrite(txt);
	OL_align = 1;
	prepareDisp();
}


function dispDiv(text,x,y) {
	OL_offsetx = x;
	OL_offsety = y;
	OL_textsize = "1";

	dispSimpleText(1,text);
}

function dispDivT(title,text,x,y) {
	OL_offsetx = x;
	OL_offsety = y;
	OL_textsize = "1";

	dispCapText(1,text,title);
}

/*

// Simple popup right
function drs(text) {	dispSimpleText(1,text); }

// Caption popup right
function drc(text, title) { dispCapText(1,text,title); }

// Sticky caption right
function src(text,title) {	stickyTextCap(1,text,title); }

// Simple popup left
function dls(text) {	dispSimpleText(0,text); }

// Caption popup left
function dlc(text, title) { dispCapText(0,text,title); }

// Sticky caption left
function slc(text,title) {	stickyTextCap(0,text,title); }

// Simple popup center
function dcs(text) {	dispSimpleText(2,text); }

// Caption popup center
function dcc(text, title) { dispCapText(2,text,title); }

// Sticky caption center
function scc(text,title) {	stickyTextCap(2,text,title); }

*/

// Clears popups if appropriate
function nd() {
	OL_callNoDisp = true;

	if (!inLayer()) {
		hideObject(OL_StyleObj);
		OL_callNoDisp = false;
		OL_show = false;
	}
}

// Non public functions. These are called by other functions etc.

/*
// Simple popup
function dispSimpleText(d,text) {

	txt = '<TABLE WIDTH="160" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#000066"><TR><TD><TABLE WIDTH="100%" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#CCCCFF"><TR><TD><span style="font-family:Arial,Helvetica,sans-serif; font-size:9pt; color:#000066">'+text+'</span></TD></TR></TABLE></TD></TR></TABLE>';
	layerWrite(txt);
	OL_align = d;
	prepareDisp();
}

// Caption popup
function dispCapText(d,text, title) {
	txt = '<TABLE WIDTH="160" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#000066"><TR><TD><TABLE WIDTH="100%" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#CCCCFF"><TR><TD BGCOLOR="#000066" ALIGN="center"><span style="font-family:Verdana,Arial,Helvetica,sans-serif; font-size:8pt; color:#FFFFFF">'+title+'</span></TD></TR><TR><TD><span style="font-family:Arial,Helvetica,sans-serif; font-size:9pt; color:#000066">'+text+'</span></TD></TR></TABLE></TD></TR></TABLE>';
	layerWrite(txt);
	OL_align = d;
	prepareDisp();
}
*/

//*** Simple popup
function dispSimpleText(d,text) {

	txt = '<TABLE WIDTH="160" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#660000"><TR><TD><TABLE WIDTH="100%" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#FFFFCC"><TR><TD ALIGN="center"><span style="font-family:Arial,Helvetica,sans-serif; font-size:9pt; color:#660000">'
			+ text +
			'</span></TD></TR></TABLE></TD></TR></TABLE>';

	layerWrite(txt);
	OL_align = d;
	prepareDisp();
}

//*** Caption popup
function dispCapText(d,text, title) {
	txt = '<TABLE WIDTH="160" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#660000"><TR><TD><TABLE WIDTH="100%" BORDER="0" CELLPADDING="1" CELLSPACING="0" BGCOLOR="#FFFFCC"><TR><TD BGCOLOR="#660000" ALIGN="center"><span style="font-family:Verdana,Arial,Helvetica,sans-serif; font-size:8pt; color:#FFFFFF"><b>'
			+ title +
			'</b></span></TD></TR><TR><TD ALIGN="center"><span style="font-family:Arial,Helvetica,sans-serif; font-size:9pt; color:#660000">'
			+ text +
			'</span></TD></TR></TABLE></TD></TR></TABLE>';

	layerWrite(txt);
	OL_align = d;
	prepareDisp();
}

//*** Sticky
function stickyTextCap(d,text, title) {
	//OL_SW = 1;
	// OL_count = 0;
	txt = "<TABLE WIDTH="+width+" BORDER=0 CELLPADDING="+border+" CELLSPACING=0 BGCOLOR=\""+backcolor+"\"><TR><TD><TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD><SPAN ID=\"PTT\"><B><FONT COLOR=\""+capcolor+"\">"+title+"</FONT></B></SPAN></TD><TD ALIGN=RIGHT><A HREF=\"/\" onMouseOver=\"cClick();\" ID=\"PCL\"><FONT COLOR=\""+closecolor+"\">Close</FONT></A></TD></TR></TABLE><TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR=\""+fcolor+"\"><TR><TD><SPAN ID=\"PST\"><FONT COLOR=\""+textcolor+"\">"+text+"</FONT><SPAN></TD></TR></TABLE></TD></TR></TABLE>"
	layerWrite(txt);
	OL_align = d;
	prepareDisp();
	//OL_show = 0;
}

// Common calls
function prepareDisp() {

	//if (!OL_show) 	{
		if (OL_align == 2) { // Center
			moveTo(OL_StyleObj, OL_x+OL_offsetx-(width/2), OL_y+OL_offsety);
		}
		if (OL_align == 1) { // Right
			moveTo(OL_StyleObj, OL_x+OL_offsetx, OL_y+OL_offsety);
		}
		if (OL_align == 0) { // Left
			moveTo(OL_StyleObj, OL_x-OL_offsetx-width, OL_y+OL_offsety);
		}

		showObject(OL_StyleObj);
		OL_show = true;
	//}

	// Here you can make the text goto the statusbar.
}

// Moves the layer
function mouseMove(e) {
	if (OL_ns4) {
		OL_x = e.pageX;
		OL_y = e.pageY;
	}
	if (OL_ie4) {
		OL_x = event.x;
		OL_y = event.y;
	}
	if (OL_dom && !OL_ie4) { // NS6
		OL_x = e.pageX;
		OL_y = e.pageY;
	}

	// if want the DIV follow mouse movement
	/*
	if (OL_show) {
		if (OL_align == 2) { // Center
			moveTo(OL_DivObj, OL_x+OL_offsetx-(width/2), OL_y+OL_offsety);
		}
		if (OL_align == 1) { // Right
			moveTo(OL_DivObj, OL_x+OL_offsetx, OL_y+OL_offsety);
		}
		if (OL_align == 0) { // Left
			moveTo(OL_DivObj, OL_x-OL_offsetx-width, OL_y+OL_offsety);
		}
	}
	*/

	//*** hide layer if out of the layer

	if (OL_callNoDisp) {
		if (!inLayer()) {
			hideObject(OL_StyleObj);
			OL_show = false;
		}
	}
}

// The Close onMouseOver function for Sticky
function cClick() {
	hideObject(OL_StyleObj);
}

//*** detect if a mouse is inside a layer
function inLayer() {

	if (OL_ns4) {
   	if (OL_x>OL_DivObj.left && OL_x<(OL_DivObj.left+OL_DivObj.clip.width) && OL_y>OL_DivObj.top && OL_y<(OL_DivObj.top+OL_DivObj.clip.height)){
   		return true;
   	}
	}
	else if (OL_ie4 || OL_dom) { // for IE and NS6
		var tt = parseInt(OL_StyleObj.top);
		var ll = parseInt(OL_StyleObj.left);

   	if (OL_x>ll && OL_x<(ll+OL_DivObj.offsetWidth) && OL_y>tt && OL_y<(tt+OL_DivObj.offsetHeight)) {
   		return true;
   	}
	}

	return false;
}


//*** Writes to a layer
function layerWrite(txt) {
	if (OL_ns4) {
   	var lyr = OL_DivObj.document;
      lyr.write(txt);
      lyr.close();
	}
	else
		OL_DivObj.innerHTML = txt;
}

//*** Make an object visible
function showObject(obj) {
	if (OL_ns4)
		obj.visibility = "show";
   else
   	obj.visibility = "visible";
}

//*** Hides an object
function hideObject(obj) {
	if (OL_ns4)
		obj.visibility = "hide";
   else
   	obj.visibility = "hidden";
}

//*** Move a layer, div
function moveTo(obj,xL,yL) {
	obj.left = xL;
   obj.top = yL;
}
