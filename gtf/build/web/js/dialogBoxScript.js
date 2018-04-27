//** this is not working in IE...
function dialogBoxScript() {
 	self.focus();
 	setTimeout('dialogBoxScript()',10);
}

dialogBoxScript();