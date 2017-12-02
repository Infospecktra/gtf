// http://www.shiningstar.net/articles/articles/javascript/checkboxes.asp?ID=AW
 
function versionValidate(field, action) {
	var isValid = true;

	//alert(action);

	if (action == "ROLLBACK") {
		isValid = validateRollback(radioCnt(field),field);
	} else if (action == "VIEW") {
		isValid = validateView(radioCnt(field));
	} else if (action == "COMPARE") {
		isValid = validateCompare(radioCnt(field));
	}

	//alert(isValid);
	return isValid;
}

function validateRollback(aCnt,field) {
   var thisCnt = 1;   
   if (aCnt != thisCnt) {
   	alert("Please select 1 version");
   	return false;
   } else {
    // if current version; do not permit rollback
    if (field[0].checked) { 
    	alert("This is the current version, it is not possible to rollback to it.");
    	return false;
    }
   	return true;
   }
}
function validateCompare(aCnt) {
   var thisCnt = 2;
   if (aCnt != thisCnt) {
	alert("Please select 2 version");
   	return false;
   } else {
   	return true;
   }
}
function validateView(aCnt) {
	// clicking hyperlink
	return true;
}

function radioCnt(field) {
   var radioCnt = 0;
   for (i = 0; i < field.length; i++) {
      if (field[i].checked) { radioCnt++; }
   }
   //alert(radioCnt);
   return radioCnt;
}
