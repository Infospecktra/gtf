
var noCharInName = new Array("'","\"");
//*********************************************************
function checkFieldCase(frm,fld,ary)
{
	var f = eval('document.'+ frm +'.'+ fld);
	var v = f.value;
	var pass = true;
	var tempStr = '';

	for (i=0; i<ary.length; i++)
	{
		if (v.indexOf(ary[i])>=0)
		{
			pass = false;
		}
		tempStr += ' < '+ary[i]+' > ';
	}

	if (!pass)
	{
		alert('Special Character '+ tempStr +' is not allowed.');
		//f.value = '';
		//f.focus();
		f.select();
		return false;
	}
	else
	{
		return true;
	}
}

//*********************************************************
function checkFieldRequired(frm,ary)
{
	for (i=0; i<ary.length; i++)
	{
		fld = eval('document.'+ frm +'.'+ ary[i]);
		v = fld.value;
		if (v=='')
		{
			alert('Some required fields (*) are missing !');
			return false;
		}
	}
	return true;
}

//*********************************************************
function clearForm(frm)
{
	fm = eval('document.'+frm);
	for (i=0; i<fm.elements.length; i++)
	{
		if (fm.elements[i].type=='text' || fm.elements[i].type=='password' || fm.elements[i].type=='password' || fm.elements[i].type=='textarea')
			fm.elements[i].value='';
	}
}

//*********************************************************
function setCheckBoxAll(frm,ck,val)
{
	chks = eval('document.'+ frm +'.'+ ck);

	if (chks.length)
	{
		len = chks.length;
		var i=0;
		for(i=0; i<len; i++)
		{
				chks[i].checked=val;
		}
	}
	else
	{
		chks.checked=val;
	}
}
//*********************************************************
function isCheckBoxChecked(frm,ck)
{
	chkBox = eval('document.'+ frm +'.'+ ck);
	var chked = false;

	///alert(chkBox);
	///alert(chkBox.length);

	if (chkBox==null) return chked;

	if (chkBox.length)
	{
		for(i=0; i<chkBox.length; i++)
		{
			if (chkBox[i].checked)
			{
				chked = true;
			}
		}
	}
	else if (chkBox)
	{
		if (chkBox.checked)	chked = true;
	}

	return chked;
}

//*********************************************************
function isListSelected(frm,lst)
{
	sList = eval('document.'+ frm +'.'+ lst);
	var listIdx = sList.selectedIndex;

	if (listIdx < 0)
		return false;
	else
		return true;
}

//*********************************************************
function toUpper(frm,fld)
{
	ff = eval('document.'+ frm + '.' + fld);
	ff.value = ff.value.toUpperCase();
}
