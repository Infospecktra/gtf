	function clearForm (f)
	{	
		for (var i=0; i<f.elements.length; i++)
		{
			var e = f.elements[i] ;
			if (e.type == "checkbox")
				e.checked = false ;
			else if ( (e.type == "text") || (e.type == "password") )
				e.value = "" ;
			else if (e.type == "select-one")
				e.selectedIndex = -1 ;
			else if (e.type == "textarea")
				e.value = "" ;
		
		}
	}
	
	function checkSpecialCharOnForm (f)
	{
		var has = false ;
		for (var i=0; i<f.elements.length; i++)
		{
			var e = f.elements[i] ;
			if ( (e.type == "text") || (e.type == "password") || (e.type == "textarea"))
			{
				has = checkSpecialChar (e.value) ;
				if (has)
					break ;
			}
		
		}
		return has ;	
	}
	
	function checkSpecialChar (s)
	{
		var has = false ;
		var invalid_char_set = ["'","\""] ;
		for (var i=0; i<invalid_char_set.length; i++)
		{
			if (s.indexOf(invalid_char_set[i]) > -1)
			{
				has = true ;
				break ;
			}
		}
		return has ;
	}
	
	function trim (s)
	{
		if (s)
		{
			var b = 0 ;
			var e = s.length ;
			
			for (var i=0; i<s.length; i++)
			{
				b = i ;
				if (s.charAt (i) != ' ')
					break ;
			}
			for (var i=(s.length-1); i>=0; i--)
			{
				if (s.charAt (i) != ' ')
					break ;
				e = i ;
			}
			if (b >= e)
				s = "" ;
			else if ( (b >0 ) || (e <s.length) )
				s = s.substring (b,e);
			
			
		}
		return s ;
	}
	
	function checkAll (checkbox)
	{
		if (checkbox)
		{
			if (checkbox.type)
				checkbox.checked = true ;
			else if (checkbox.length >1)
			{
				for (var i=0 ; i<checkbox.length; i++)
					checkbox[i].checked = true ;
			}
			else
				alert ("Something wrong with the checkbox, size=" + checkbox.length);
		}
		else
			alert ("There's no " + checkbox );
		
	}
	
	function unCheckAll (checkbox)
	{
		if (checkbox)
		{
			if (checkbox.type)
				checkbox.checked = false ;
			else if (checkbox.length >1)
			{
				for (var i=0 ; i<checkbox.length; i++)
					checkbox[i].checked = false ;
			}
			else
				alert ("Something wrong with the checkbox, size=" + checkbox.length);
		}
		else
			alert ("There's no " + checkbox );
		
	}	