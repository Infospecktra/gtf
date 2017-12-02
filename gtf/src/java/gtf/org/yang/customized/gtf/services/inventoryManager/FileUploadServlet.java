/* Generated by Together */

package org.yang.customized.gtf.services.inventoryManager;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.ServletException;
//import java.io.IOException;
//import java.io.OutputStream;
//import javax.servlet.ServletConfig;
//import org.yang.services.dbService.DataAccessException;
//import org.yang.web.controller.RequestHandler;
import javax.servlet.ServletConfig;
//import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.DiskFileUpload;
import org.yang.customized.gtf.services.dataAccess.Storage;
import org.yang.services.dataAccess.genericDAO.StorableList;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.inventoryManager.utility.ExcelReader;
import org.yang.web.controller.GenericBean;
import org.yang.customized.gtf.services.inventoryManager.web.StorageBean;
/**
 * @ version : 1.0 (since 11-30-2010)
 * @ company : 
 * @ author  : Huei-Wen(Celina) Yang
 * @ summary : 
 * 
 */
public class FileUploadServlet extends HttpServlet{
    //final public static  String workingLocation = VariableArchives.workingLocation;//"remote";
    private String registeredID = "";
    private String templateName = "";
    String forward = null;
    private GenericBean  genericBean = null;
    private String  userHome;
    private String workingDirectory;
    public void init(ServletConfig config)throws ServletException
    {
       super.init(config);
       
    }

    public  void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
       doPost(request,response);
    }

    public void  doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
       try
       {
          upload(request,response);
       }
       catch(Exception e)
       {
          e.printStackTrace();	
       }	   
    }

   private void upload(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException, Exception
   {
      /* 
       if("remote".equals(workingLocation))
           workingDirectory = VariableArchives.PATH_REMOTE; 
       else
           workingDirectory = VariableArchives.PATH_LOCAL;
      */
       loadWorkingDir();
       System.out.println("[FileUploadServlet::upload]----->workingDirectory="+workingDirectory);	

      genericBean = (GenericBean)request.getSession().getAttribute("storageBean");
      StorageBean  storageBean = (StorageBean)genericBean;
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
      String workingFile_fullPath = "";
      while(i.hasNext())
      {
          FileItem fi = (FileItem)i.next();
          if(fi.isFormField()) {
      	    String fieldName = fi.getFieldName();
            //System.out.println("[FileUploadServlet::upload] fieldName="+fieldName);
          }
          else
          {
            // filename on the client
            //System.out.println("[FileUploadServlet::upload]---->not FormField! --file?");
            String fileName = fi.getString();
            //System.out.println("[FileUploadServlet::upload]---->fileName="+fileName);//@@
            fileName += ".xls";
            if(".xls".equals(fileName))
            {   //System.out.println("[FileUploadServlet::upload]---->changeToIMputFromGUI(request,response,storageBean)");
                changeToINputFromGUI(request,response,storageBean);//
                return ;
            }

            System.out.println("[FileUploadServlet::upload] getServletContext().getRealPath(/)        :" + getServletContext().getRealPath("/"));
            String oName = fi.getName();
            File fullFile = new File(oName);
            
            ///System.out.println("[FileUploadServlet::upload] File name      :" + fileName);
            System.out.println("[FileUploadServlet::upload] FullFile name  :" + fullFile.getName());
            System.out.println("[FileUploadServlet::upload] O name         :" + oName);
            
            // write the file
            workingFile_fullPath =workingDirectory + fullFile.getName();
            fi.write(new File(workingFile_fullPath));
            
          }
      }
      uploadedExcelFile=workingFile_fullPath; 
      createDataFileFromExcelFile(storageBean,workingFile_fullPath);//
      getTotalDataSize();
      System.out.println("[FileUploadServer::upload]------>totalDataSize()="+totalDataSize);
      storageBean.setTotalDataSize(totalDataSize);
      storageBean.setIsUploadCompleted(true);
      storageBean.setUploadedExcelFile(uploadedExcelFile);
      storageBean.setUploadedFormSetting("action=\"/wf/storage.wf\"");
      response.sendRedirect("/wf/default/inventoryService/inputDataFromFiles.jsp");
   }

   private String fileUpload = ""; 
   public String  getFileUpload(){return fileUpload;}
   public void setFileUpload(String fileUpload){this.fileUpload=fileUpload;}  
   
   private String uploadedExcelFile = "";
   public String getUploadedExcelFile(){return uploadedExcelFile;}

   public void changeToINputFromGUI(HttpServletRequest request,
                         HttpServletResponse response,StorageBean  storageBean) throws ServletException, IOException, Exception
   {
      try
      {
      	  storageBean.setRawDataInputType("inputDataByGUI");
          storageBean.setUploadedFormSetting("action=\"/wf/storage.wf\"");
          response.sendRedirect("/wf/default/inventoryService/storageTable.jsp");
      } 
      catch(Exception e)
      {
         e.printStackTrace();	
      }			
   }	 

   private int totalDataSize = 0;
   public int  getTotalDataSize(){return totalDataSize=myReader.getTotalDataSize();}  
   
   private ExcelReader myReader = null;
   public void createDataFileFromExcelFile(StorageBean storageBean, String fileUpload) throws ProjectDataAccessException
   {   	
      try
      {
      	 //System.out.println("[FieUploadServlet::createDataFromExcelFile]----->fileUpload="+fileUpload);
      	 myReader = new ExcelReader(fileUpload);
      	 StorableList storages = myReader.readExcelFile(new StorableList());
      	 uploadedExcelFile = myReader.getOutputDataFileName();
      	 int fileDataSize = myReader.getTotalDataSize();
      	 
      	 for(int i=0;i<fileDataSize;i++)
      	 {
      	    Storage s = (Storage)storages.get(i);
      	    String id = storageBean.insertStorageToDB(s,uploadedExcelFile);
      	    //System.out.println("[FieUploadServlet::createDataFromExcelFile]----->create storage id="+id);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new ProjectDataAccessException();
      }
   }   
   public void destroy()
   {}
   
   private void loadWorkingDir(){
           userHome=System.getProperty("user.home");
           System.out.println("[FileUploadServlet::loadWorkingDir]--ProjectViewer---->System.getProperty-user.home="+userHome);
         StringBuffer stringBf_workingDirectory =new StringBuffer(userHome);
         if(userHome.indexOf(":")>-1)
        {    
           stringBf_workingDirectory.append('\\')
                                    .append("gtf_storage_xls").append('\\'); 
    	   workingDirectory =  stringBf_workingDirectory.toString();
    	 
        }else{
           stringBf_workingDirectory.append('/')
                                    .append("gtf_storage_xls").append('/'); 
    	   workingDirectory =  stringBf_workingDirectory.toString();
    	    
        }
        System.out.println("[FileUploadServlet::loadWorkingDir]--ProjectViewer---->workingDirectory="+workingDirectory);
  }

}