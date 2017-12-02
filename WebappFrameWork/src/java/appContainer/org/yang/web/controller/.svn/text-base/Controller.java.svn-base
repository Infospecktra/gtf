package org.yang.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.config.Config;

/**
 * @undefined
 * @servletName Controller 
 */
public class Controller extends HttpServlet
{
   // should from initArgs
   private String REQUEST_MANAGER = "org.yang.web.controller.BaseRequestManager";
   private String name = "org.yang.web.controller.Controller";
   private RequestManager requestManager = null;

   public void init(ServletConfig config) throws ServletException
   {
      super.init(config);
      startProcedure(config);
   }

   public void destroy()
   {
      shutDownProcedure();
      super.destroy();
   }

   public void service(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException
   {
      try
      {
    	 System.out.println("[Controller::service] remote address : " + request.getRemoteAddr());
      	 RequestHandler handler = requestManager.getRequestHandler(request);
      	
      	 if(null!=handler)
      	 {
            String targetPage = handler.handleRequest(request, response, this);

            if(!"no_page".equals(targetPage))
               request.getRequestDispatcher(targetPage).forward(request, response);
            else if("no_change".equals(targetPage))
               ;
         }
         else
         {
            System.out.println("[Controller::service] Handler is not found, request = " + request.getRequestURI());
         }
      }
      catch(IOException ie)
      {
         ExceptionBroadcast.print(ie);
      	 throw new IOException(ie.getMessage());
      }
      catch(ServletException se)
      {
         ExceptionBroadcast.print(se);
      	 throw new ServletException(se.getMessage());
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
         return;
      }
      catch(OutOfMemoryError oome)
      {
         ExceptionBroadcast.print(oome);
         return;
      }
      catch(Throwable t)
      {
         System.out.println("A throwable was caught!");
         return;
      }
   }
   
   /**********************
    * My private methods *
    **********************/
    
   public String startProcedure(ServletConfig config) throws ServletException
   {
      loadConfig(config);

      prepareHandlers();

      ApplicationContainer.getContainer().init();

      try
      {
         Thread.currentThread().sleep(5000);
      }
      catch(Exception e)
      {}
      return null;
   }
   
   public String shutDownProcedure() //throws ServletException
   {
      ApplicationContainer.getContainer().destory();

      try
      {
         Thread.currentThread().sleep(10000);
      }
      catch(Exception e)
      {}

      requestManager = null;

      return null;
   }

   private String loadConfig(ServletConfig config) throws ServletException
   {
      String configFileName = config.getInitParameter("configFileName");
      if(null==configFileName)
        throw new ServletException("Unable to find the name of configuration file. name = " + configFileName);
      Config.getInstance(configFileName);
      return null;
   }

   public String prepareHandlers()
   {
      REQUEST_MANAGER = this.getInitParameter("RequestManager");
      requestManager  = RequestManager.getManager(REQUEST_MANAGER);

      if(null==requestManager)
      {
         System.out.println("No Request Manager Error!");
      }

      requestManager.init(this);
      return null;
   }
   
   public String name()
   {
      return name;
   }
}