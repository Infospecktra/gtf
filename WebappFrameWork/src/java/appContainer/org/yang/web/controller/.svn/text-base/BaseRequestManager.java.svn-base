package org.yang.web.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.yang.util.xml.XmlRWUtil;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.apache.log4j.Category;

public class BaseRequestManager extends RequestManager
{
   static final long serialVersionUID = 4711296394469764969L;

   protected Category log = Category.getInstance(this.getClass());
   private final String MAP_PATH = "BaseRequestManager.xml";

   private String  mapPath = MAP_PATH;
   private HashMap actionMap = null;
   private ServletConfig config = null;
   private int contextPathLength = 0;

   public BaseRequestManager()
   {
      //_container = ApplicationContainer.getContainer();
   }

   public void init(ServletConfig config)
   {
      this.config = config;

      try
      {
         loadSystemProperties(config);
         loadHandlerMap(mapPath);
      }
      catch(Exception e)
      {
         throw new RuntimeException("Action map cann't be loaded!" + e.toString());
      }
   }

   public RequestHandler getRequestHandler(HttpServletRequest request)
   {
      String uri = request.getRequestURI();
      if(0==contextPathLength)
      {
         contextPathLength = request.getContextPath().length();
      }

      uri = uri.substring(contextPathLength);

      if(isExpired())
      {
         uri = "/swexpired.wf";
      }  
      
      RequestHandler handler = (RequestHandler)actionMap.get(uri);

      if(null==handler)
      {
         handler = (RequestHandler)actionMap.get("/*");
      //   if(null==handler)
      //      throw new RuntimeException("No handler found!");
      }

      return handler;
   }

   protected void loadHandlerMap(String mapPath) throws Exception
   {
      try
      {
         if(actionMap==null)
         {
            InputStream is = Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResourceAsStream(mapPath);
            //InputStream is = this.getClass()
            //                     .getResourceAsStream(mapPath);
            if(null==is)
            {
               throw new Exception("File " + mapPath + " is not found!");
            }

            actionMap = new HashMap();
            Node mappings = XmlRWUtil.read(is);
            ArrayList uris = XmlRWUtil.getNodesFromDocument(mappings, "/mappings/uri");

            for(int i=0; i<uris.size(); i++)
            {
               String uriPath = null;
               String actionHandler = null;
               Node uri = (Node)uris.get(i);
               NamedNodeMap atts = uri.getAttributes();

      	       if(null==atts)
      	       {
  	          throw new Exception("Error in file " + mapPath + "!");
      	       }

      	       Node path = atts.getNamedItem("path");
               Node handler = atts.getNamedItem("handler");

      	       if(null==path||null==handler)
      	       {
      	          throw new Exception("Error in file " + mapPath + "!");
      	       }

      	       String deploymentPath = path.getNodeValue();
   	           String handlerClassName = handler.getNodeValue();
      	       RequestHandler rh = (RequestHandler)getClass(handlerClassName);

      	       if(null!=rh)
      	       {
                  Properties hprop = new Properties();
                  ArrayList props = XmlRWUtil.getNodesFromDocument(uri, "property");

                  for(int j=0; j<props.size(); j++)
                  {
                     Node prop = (Node)props.get(j);
                     NamedNodeMap patts = prop.getAttributes();
      	             if(null==patts)
      	             {
      	                break;
      	             }

      	             Node name = patts.getNamedItem("name");
                     Node value = patts.getNamedItem("value");

      	             if(null==name||null==value)
      	             {
      	                break;
      	             }

                     hprop.setProperty(name.getNodeValue(), value.getNodeValue());
                  }

                  rh.init(config, hprop);
      	          actionMap.put(deploymentPath, rh);
               }
               else
                  ;//System.out.println(" ******************************** Handler " + handlerClassName + " is not found!");
            }
         }
      }
      catch(Exception e)
      {
         throw new Exception("Load table failis!" + e.toString());
      }
   }

   private void loadSystemProperties(ServletConfig config)
   {
   }
   
   private long string2longDate(String date)
   {
      return java.sql.Date.valueOf(date.replace('/', '-')).getTime();
   }
   
   private boolean isExpired()
   {
      return false;
   }
}