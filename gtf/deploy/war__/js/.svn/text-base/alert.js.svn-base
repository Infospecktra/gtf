var checked = false ;

function checkMessage (e)
{
	if (!checked)
	{
		/*
		var src = document.images[e].src ;
		var index = src.lastIndexOf ("/") ;

		if (index >1)
		{
			var path = src.substring (0,index+1);

			var on_image_name = src.substring (src.lastIndexOf ("/")+1) ;
			var off_image_name = getOffImageName (on_image_name) ;

			document.images[e].src = path + off_image_name ;
		}
		*/
		openMessageWindow  (e);
		checked = true ;
	}
}

function openMessageWindow (e)
{
	var NN4=(document.layers);
	var IE4=(document.all);

//	var x,y ;
		window.open (e,"alert_window","width=450,height=400,scrollbars=yes,menubar=no,toolbar=no,resizable=yes") ;

}


function getOffImageName (g)
{
	var off_image_name = g ;
	var index = g.lastIndexOf (".") ;
	if (index >0)
	{
		off_image_name = g.substring (0,index) ;
		off_image_name = off_image_name + "_off" + g.substring (index) ;
	}
	return off_image_name ;
}


