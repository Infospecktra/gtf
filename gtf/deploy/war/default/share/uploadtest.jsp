<%@ page import = "com.screamingmedia.siteware.webService.util.*" %>

<html>
<head>
<title>File Upload</title>
</head>
<body>
Here is it:

<%
FileUploadUtil TheBean= new FileUploadUtil();

try{

	TheBean.setSavePath("/fileupload/test/");
	TheBean.setMaxLength(1500000);
	if(TheBean.doUpload(request)){
		out.println("Filename:" + TheBean.getFilename());
	}else{
		out.println("Upload fail! Content too large...");
	}

}catch(Exception e){e.printStackTrace();}

%>

</body>
</html>