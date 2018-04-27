function replaceData(data){
	alert('replaceData(data)');

	if (data != null) {

		var strFileName = 'testFile.txt';
	    	    strFileName = '/actrellis/doc/elj_config/custom/testFile.txt';
		var nNumLines = 0; //number of lines in file	
		var typedWord = "";
		var replaceWord = "";

		document.FileReader.readFile(strFileName);
		nNumLines = document.FileReader.getNumLines();

		for (var i=0; i<nNumLines; i++){	
			regLine = document.FileReader.getLine(i);
			typedWord = regLine.substring(0,regLine.lastIndexOf('='));
			replaceWord = regLine.substring(regLine.lastIndexOf('=')+1);
			while (data.lastIndexOf(typedWord) != -1) {
				data = data.replace(typedWord, replaceWord);
			}
		}
	} else {
		alert('data is null');
	}

	//alert(data);
	return data;
}
