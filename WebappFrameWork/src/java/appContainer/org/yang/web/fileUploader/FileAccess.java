package org.yang.web.fileUploader;

import org.yang.web.controller.RequestHandler;
import javax.servlet.ServletConfig;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.DiskFileUpload;

public class FileAccess implements RequestHandler
{
   String workingDirectory = null;
   String forward = null;

   public void init(ServletConfig config, Properties properties) throws ServletException
   {
      workingDirectory = properties.getProperty("working-directory");
      forward = properties.getProperty("upload-forward");
   }

   public String handleRequest(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpServlet servlet) throws ServletException, IOException, Exception
   {
      //&System.out.println("-------------------------------------------->" + request.getMethod());
      if("POST".equals(request.getMethod()))
         return upload(request, response);
      else
      {
         String filename = request.getParameter("filename");
         return download(filename, response);
      }
   }

   private String upload(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException, Exception
   {
      DiskFileUpload fu = new DiskFileUpload();
      // maximum size before a FileUploadException will be thrown
      fu.setSizeMax(1000000);
      // maximum size that will be stored in memory
      fu.setSizeThreshold(4096);
      // the location for saving data that is larger than getSizeThreshold()
      fu.setRepositoryPath(workingDirectory);

      List fileItems = fu.parseRequest(request);
      // assume we know there are two files. The first file is a small
      // text file, the second is unknown and is written to a file on
      // the server
      Iterator i = fileItems.iterator();

      i.next();

      FileItem fi = (FileItem)i.next();

      // filename on the client
      String fileName = fi.getString();
      fileName += ".jpg";

      fi = (FileItem)i.next();
      String oName = fi.getName();

      // save comment and filename to database

      //System.out.println("[FileAccess::upload] Working directory :" + (new File(workingDirectory)).getCanonicalPath());
      //System.out.println("[FileAccess::upload] File name         :" + fileName);
      //System.out.println("[FileAccess::upload] Forward           :" + forward + "&id=" + fileName);

      // write the file
      fi.write(new File(workingDirectory + fileName));

      if(null!=forward)
         return forward + "?filename=" + fileName;
      else
         return download(fileName, response);
   }

   private String download(String filename,
                           HttpServletResponse response) throws ServletException, IOException, Exception
   {

      try
      {
         String localURL = "file://" + workingDirectory + filename;
         //&System.out.println("[FileAccess::download] Local URL : " + localURL);
         URL aURL = new URL(localURL);
         inputStream2OutputStream(aURL.openStream(), response.getOutputStream());
      }
      catch(Exception e)
      {
      	 inputStream2OutputStream(new ByteArrayInputStream("[FileDownloader::handleRequest] File not found".getBytes()),
      	                          response.getOutputStream());
         e.printStackTrace();
      }
      
      return "no_page";
   }

   private void inputStream2OutputStream(InputStream is, OutputStream os) throws IOException
   {
      byte[] buf = new byte[10240];
      int len = 0;
      while(-1!=(len=is.read(buf)))
      {
         os.write(buf, 0, len);   
      }
      is.close();
      os.flush();
      os.close();
   }
}