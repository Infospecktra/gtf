package org.yang.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @undefined
 * @servletName JSPPrecompile 
 */
public class JSPPrecompile extends HttpServlet
{
   private ServletContext context=null;
	
   public void init(ServletConfig config)
   {
      try
      {
         super.init(config);
         context=config.getServletContext();
      }
      catch(Exception e)
      {
        //e.printStackTrace();
	  }
   }

   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      String dir=".."+File.separator+"tmp"+File.separator;
      InvokeJSP(dir,request,response);
   }


   private void InvokeJSP(String filepath,HttpServletRequest request, HttpServletResponse response)
   {
      try
      {
         File _file=new File(filepath);
         String[] flist=_file.list();
         for(int i=0;i<flist.length;i++)
         {
				File p=new File(_file,flist[i]);
				if(p.isDirectory()){
					String newpath=filepath+File.separator+flist[i];
					InvokeJSP(newpath,request,response);
				}
				if(_file.getPath().indexOf("defaultskin")!=-1 && !_file.getPath().endsWith(new String("defaultskin"))){								
					int start=_file.getPath().indexOf("defaultskin");
					String path=_file.getPath().substring(start,_file.getPath().length()); 
					StringBuffer rightPath=new StringBuffer();
					if(path.indexOf("\\")!=-1){  //for win pc
						StringTokenizer token=new StringTokenizer(path,"\\");
						while(token.hasMoreTokens()){
							rightPath.append(token.nextToken()).append("/");
						}	
					}	
					rightPath.append(flist[i]);
					if(rightPath.toString().endsWith(".jsp")){
					
						StringBuffer sb=new StringBuffer("").append(rightPath).append("?jsp_precompile=true");
						
//System.out.println("sb"+sb.toString());						
						
							
/*						try{
						 //RequestDispatcher dispatch=getServletContext().getRequestDispatcher("/defaultskin/admindesk/accounts.jsp?jsp_precompile=true");
						 //dispatch.forward(request,response);                      
						 System.out.print("1====send ==>redirector");
						 response.sendRedirect(sb.toString());
						 }catch(Exception e1){
							e1.printStackTrace();
						 }	
*/						
						try{
//						 System.out.print("2====dispatch:");
						 request.getRequestDispatcher(sb.toString()).include(request, response);
						 }catch(Exception e1){
							//e1.printStackTrace();
						}	
					
					}
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
		}		
	}

}