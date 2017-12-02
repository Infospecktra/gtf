package org.yang.web.controller;

import java.io.Serializable;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.yang.util.ExceptionBroadcast;

abstract public class RequestManager implements Serializable
{
   static final long serialVersionUID = 4711041382979764969L;

   public static RequestManager getManager(String myClassName)
   {
      return (RequestManager)getClass(myClassName);
   }

   public void init(ServletConfig config) {}

   abstract public RequestHandler getRequestHandler(HttpServletRequest  request);
   
   abstract protected void loadHandlerMap(String mapPath) throws Exception;
   
   protected static Object getClass(String name)
   {
      Object obj = null;
      try
      {
         obj = Class.forName(name).newInstance();
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }

      return obj;
   }
}