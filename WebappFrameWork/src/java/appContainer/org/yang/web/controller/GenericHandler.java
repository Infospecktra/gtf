package org.yang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletOutputStream;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.yang.util.ExceptionBroadcast;
import java.util.Arrays;

public class GenericHandler extends BaseHandler
{
   public GenericHandler() 
   {
      super();
   }

   /*****************************
    * My major process sequence *
    *****************************/

   protected String doProcess(Properties prop,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              HttpServlet servlet,
                              BeanArrayList bal) throws Exception 
   {  
      if("yes".equals(request.getParameter("DEBUG")))
      {  
         return debugOutput(request, response);
      }

      try
      {
         if(!createBean(prop, request, response, bal))
            return "/default/share/top.jsp?go=index.htm";
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }

      int sta = 100;
      BaseBean base = null;
      GenericBean myBean = null;
     
      if(sta>-1&&bal.size()>0)
      {
         base = bal.get(0);
         if(base instanceof GenericBean)
         {
            myBean = (GenericBean)base;
            if (myBean!=null) 
            {
               myBean.setRequest(request);
               myBean.setResponse(response);
               myBean.setServlet(servlet);
               try
               {
                  myBean.process();
               }
               catch(Exception e)
               {
                  return "/default/share/top.jsp?go=index.htm";
               }

               sta = myBean.getState();
            }
         }
         else
         {
            throw new Exception("Current bean wasn't supported.");
         }
      }

      String targetPage = prop.getProperty("default-forward");
      myBean.setDefaultPage(targetPage);
      System.out.println("[GenericHandler::doProcess] Process forward page begin ..........");
      System.out.println("[GenericHandler::doProcess] The default-forward is :" + targetPage);

      String rtnKey = null;
      String rtnPage = null;
      if(null!=(rtnPage=myBean.dynamicPage()))
      {
         System.out.println("[GenericHandler::doProcess] Got a dynamic page :" + rtnPage);
         targetPage = rtnPage;
      }
      else if(null!=((rtnKey=myBean.altPage())))
      {
         System.out.println("[GenericHandler::doProcess] The alt page key is :" + rtnKey);

         if("no_page".equals(rtnKey))
         {
            targetPage = rtnKey;
         }
         else if(null!=(rtnPage=prop.getProperty(rtnKey)))
         {
            System.out.println("[GenericHandler::doProcess] The alt page value is :" + rtnPage);
            targetPage = rtnPage;
         }
      }
      System.out.println("[GenericHandler::doProcess] Process forward page end ............");
      
      myBean.beforeForward();

      if("debug".equals(targetPage))
      {
         targetPage = beanDebugOutput(bal, response);
      }

      if (sta==GenericBean.ERR)
      {
        targetPage = prop.getProperty("fail-forward");
        //&System.out.println("[GenericHandler::doProcess] The fail-forward page is :" + targetPage);
      }

      //&System.out.println("[GenericHandler::doProcess] Current forward page is :" + targetPage);
      return targetPage;
   }

   /********************
    * All my processes *
    ********************/

   protected boolean createBean(Properties prop,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                BeanArrayList myBeans) throws CreateBeanException
   {
      try
      {
         GenericBean ab = (GenericBean)createBean(prop.getProperty("bean-name"),
                                                  prop.getProperty("bean-class"),
                                                  prop.getProperty("bean-scope"),
                                                  request);

         if(ab instanceof Secured)
         {
            if(!((Secured)ab).passSecurityCheck(request))
            {
               // Fail to pass security check
               return false;
            }
         }

         // Pass or actually we don't need do security check.
         synchronized(ab)
         {
            setFields(ab, request);
            if(ab.getIsNew())
            {
               //ab.setRequest(request);
               //ab.setResponse(response); //we need ?
               //ab.setServlet(null);    we need ?
               ab.setProp(prop);
               ab.init();
               ab.setIsNew(false);
            }
         }
         myBeans.add(ab);
         return true;
      }
      catch(CreateBeanException cbe)
      {
         cbe.printStackTrace();
         throw cbe;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new CreateBeanException(e.getMessage());
      }
   }

   protected String resetTargetPage(Properties prop, HttpServletRequest request) 
   {
      return null;
   }
   
   /********************************
    * My own private helper method *
    ********************************/

   private void setFields(BaseBean aBean, HttpServletRequest request)
   {
      try
      {
         GenericBean bean = (GenericBean) aBean;
         bean.beforeSetParameters();
         Object para = null;
         Class beanClass = bean.getClass();

         String temp = null;
         Method targetMethod = null;
         Object[] ob = null;

         HashMap parameters = rebuildParameters(request);
         Iterator fields = parameters.keySet().iterator();

         while(fields.hasNext())
         {
            temp = (String)fields.next();
            if(null!=(para=parameters.get(temp)))
            {
               try
               {
                  //System.out.println("[GenericFormHandler::setField] try to set parameter - " + temp );
                  if(null==(targetMethod=(Method)bean.getWebSupportMethod(temp)))
                  {
                     continue;
                  }

                  //System.out.println("[GenericFormHandler::setField] found method ! ");
                  if(!(para instanceof HashMap))
                     ob = getParameterObjs(((Class[])targetMethod.getParameterTypes())[0], (String[])para);
                  else
                  {
                     ob = new Object[1];
                     ob[0] = para;
                  }
                  targetMethod.invoke(bean, ob);
                  //System.out.println("[GenericFormHandler::setField] parameter got set ! ");
               }
               catch(Exception e)
               {
                  //System.out.println("[GenericFormHandler::setField] Unexpected exception:" + e.getMessage());
                  ExceptionBroadcast.print(e);
               }   	               
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   private HashMap rebuildParameters(HttpServletRequest request)
   {
      HashMap parameters = new HashMap();
      String temp = null;
      int index = -1;
      Enumeration fields = request.getParameterNames();
      while(fields.hasMoreElements())
      {
         temp = (String)fields.nextElement();

         if(-1==(index=temp.indexOf("_")))
         {
            // Array type
            parameters.put(temp, request.getParameterValues(temp));
         }
/*         else
         {
            // Hashtable type
            String key = temp.substring(0, index);
            String value = temp.substring(index+1);
            Object map = null;
            if(null==(map=parameters.get(key))||!(map instanceof HashMap))
            {
               map = new HashMap();
               parameters.put(key, map);
            }

            ((HashMap)map).put(value, request.getParameterValues(temp));
         }*/
         else
         {
            // Hashtable type
            String key = temp.substring(0, index);
            String value = temp.substring(index+1);

            Object map = null;
            if(null==(map=parameters.get(key))||!(map instanceof HashMap))
            {
               map = new HashMap();
               parameters.put(key, map);
            }

            String[] paras = request.getParameterValues(temp);
            if(paras.length>1)
               ((HashMap)map).put(value, paras);
            else
               ((HashMap)map).put(value, paras[0]);
         }
      }
      return parameters;
   }

   private void setParameter(HashMap parameters, String[] keys, String[] values)
   {
      Object map = null;
      if(null==(map=parameters.get(keys[0]))||!(map instanceof HashMap))
      {
         // if there is know such map then create one and put it into parameters
         if(1<keys.length)
         {
            String[] newKeys = new String[keys.length-1];
            System.arraycopy(keys, 1, newKeys, 0, newKeys.length);
            map = createHashMap(newKeys, values);
         }
         else
         {
            map = new HashMap();
            ((HashMap)map).put(keys[0], values);
         }
         parameters.put(keys[0], map);
      }
      else
      {
         if(1<keys.length)
         {
            String[] newKeys = new String[keys.length-1];
            System.arraycopy(keys, 1, newKeys, 0, newKeys.length);
            setParameter((HashMap)map, newKeys, values);
         }
         else
            ((HashMap)map).put(keys[0], values);
      }
   }

   private HashMap createHashMap(String[] keys, String[] values)
   {
      HashMap temp = null;
      if(1<keys.length)
      {
         String[] newKeys = new String[keys.length-1];
         System.arraycopy(keys, 1, newKeys, 0, newKeys.length);
         temp = createHashMap(newKeys, values);
      }
      else
      {
         temp = new HashMap();
         temp.put(keys[0], values);
      }

      return temp;
   }

   private Object[] getParameterObjs(Class type, String[] para)
   {
      try
      {
         Object[] temp = new Object[1];
         int len = para.length;
         
         if(type.isArray())
         {
            Object[] paraObject = null;
            if((new String[1]).getClass().equals(type))
            {
               paraObject = new String[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = para[i];
               }
            }
            else if((new int[1]).getClass().equals(type))
            {
               paraObject = new Integer[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Integer(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] int[] type.");
            }
            else if((new long[1]).getClass().equals(type))
            {
               paraObject = new Long[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Long(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] long[] type.");
            }
            else if((new float[1]).getClass().equals(type))
            {
               paraObject = new Float[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Float(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] float[] type.");
            }
            else if((new double[1]).getClass().equals(type))
            {
               paraObject = new Double[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Double(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] double[] type.");
            }
            else if((new boolean[1]).getClass().equals(type))
            {
               paraObject = new Boolean[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Boolean(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] boolean[] type.");
            }
            else if((new short[1]).getClass().equals(type))
            {
               paraObject = new Short[len];
               for(int i=0; i<len; i++)
               {
                  paraObject[i] = new Short(para[i]);
               }
               //System.out.println("[GenericFormHandler::setField] short[] type.");
            }
            temp[0]=paraObject;
            //System.out.println("[GenericFormHandler::setField] Array.");     
         }
         else
         {
            Object paraObject = null;
            if((new String()).getClass().equals(type))
            {
               paraObject = new String[1];
               paraObject = para[0];
               //System.out.println("[GenericFormHandler::setField] String type.");
            }
            else if(Integer.TYPE.equals(type))
            {
               paraObject = new Integer[1];
               paraObject = new Integer(para[0]);
               //System.out.println("[GenericFormHandler::setField] int type.");
            }
            else if(Long.TYPE.equals(type))
            {
               paraObject = new Long[1];
               paraObject = new Long(para[0]);
               //System.out.println("[GenericFormHandler::setField] long type.");
            }
            else if(Float.TYPE.equals(type))
            {
               paraObject = new Float[1];
               paraObject = new Float(para[0]);
               //System.out.println("[GenericFormHandler::setField] float type.");
            }
            else if(Double.TYPE.equals(type))
            {
               paraObject = new Double[1];
               paraObject = new Double(para[0]);
               //System.out.println("[GenericFormHandler::setField] double type.");
            }
            else if(Boolean.TYPE.equals(type))
            {
               paraObject = new Boolean[1];
               paraObject = new Boolean(para[0]);
               //System.out.println("[GenericFormHandler::setField] boolean type.");
            }
            else if(Short.TYPE.equals(type))
            {
               paraObject = new Short[1];
               paraObject = new Short(para[0]);
               //System.out.println("[GenericFormHandler::setField] short type.");
            }
            temp[0]=paraObject;
            //System.out.println("[GenericFormHandler::setField] Scalar.");
         }
       
         return temp;
      }
      catch(Exception e)
      {
         return null;	
      }
   }

   private Class[] getParameterTypes(Field field)
   {
      try
      {
         if(null!=field)
         {
            Class[] cl = {field.getType()};
            return cl;
         }
      }
      catch(Exception e)
      {}   
      return null;
   }

   private String debugOutput(HttpServletRequest request, HttpServletResponse response)
   {
      try
      {
         String head = "<html><head><title>GenericHandler in debug mode</title></head><body>";
         String tail = "</body></html>";
         ServletOutputStream out = response.getOutputStream();
         out.print(head);
         Enumeration e = request.getParameterNames();
         String key = null;
         while(e.hasMoreElements())
         {
            key = (String)e.nextElement();
            out.print(key + "=" + request.getParameter(key) + "<p>");
         }
         out.print(tail);
         out.flush();
         out.close();
         return "no_page";
      }
      catch(Exception e)
      {
         return "no_page";
      }
   }

   private String beanDebugOutput(BeanArrayList bal, HttpServletResponse response)
   {
      try
      {
         String head = "<html><head><title>GenericHandler in bean debug mode</title></head><body>";
         String tail = "</body></html>";
         ServletOutputStream out = response.getOutputStream();
         out.print(head);

         int size = bal.size();
         if(0==size)
         {
            out.print("No bean found!<p>");
         }
         else
         {
            for(int i=0; i<size; i++)
            {
               out.print("<p>" + bal.get(i).getBeanID() + "<p>");
               out.print(bal.get(i).getBeanScope() + "<p>");
               out.print(((GenericBean)bal.get(i)).debug() + "<p>");
            }
         }
         out.print(tail);
         out.flush();
         out.close();
         return "no_page";
      }
      catch(Exception e)
      {
         return "no_page";
      }
   }

   private void printMethod(Method m)
   {
      //System.out.println(" method name -------------------->" + m.getName());
      Class[] para = m.getParameterTypes();
      for(int i=0 ;i<para.length; i++)
      {
         if(para[i].isArray())
            System.out.print(" Array");
         //System.out.println(" para" + i + ":" + para[i].getName());
      }
   }
}