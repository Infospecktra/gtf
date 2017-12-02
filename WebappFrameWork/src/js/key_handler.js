//*********************************************************
//** detect key down, disable ENTER key
//*********************************************************
function noEnterHandler(e) {
  var press = null ;

  if (isNS4) {
     press = e.which ;
  }
  else if (isIE4) {
     e = window.event ;
     press = e.keyCode ;
  }
  //** 13 = enter key
  if (press==13) return false;
  else return true;
}

//*********************************************************
function addNoEnterHandler(o) {
	o.onkeydown = noEnterHandler;
}

//*********************************************************
//** pass in a FORM object
//*********************************************************
function formTextNoEnter(f) {
	for (i=0; i<f.elements.length; i++) {
		if (f.elements[i].type=='text' || f.elements[i].type=='password' || f.elements[i].type=='textarea')
			addNoEnterHandler(f.elements[i]);
	}
}