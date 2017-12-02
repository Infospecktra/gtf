package org.yang.web.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.Beans;
import org.yang.util.ExceptionBroadcast;

public abstract class BaseHandler implements RequestHandler 
{
   protected Properties    myproperties = null;
   private   ServletConfig myconfig     = null;
   protected String        myerrorPage  = null;

   /**
    * Default Construtor
    */
   public BaseHandler(){}

   /**
    * init method called by controller
    */
   public void init(ServletConfig config, 
                    Properties properties) throws ServletException
   {
      this.myproperties = properties;
      //&System.out.println("[BaseHandler::init] properties" + myproperties);
   }

   /**
    * setting value method for child class
    */
   protected abstract String doProcess(Properties prop, 
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       HttpServlet servlet,
                                       BeanArrayList myBeans) throws Exception;

   /**
    * main handler class called by controller
    */
   public String handleRequest(HttpServletRequest request, 
                               HttpServletResponse response, 
                               HttpServlet servlet) throws ServletException, IOException, Exception
   {
      BeanArrayList myBeans = new BeanArrayList();
      String targetPage = null;
      try
      {
         targetPage = doProcess(myproperties, request, response, servlet, myBeans);

         int bSize = myBeans.size();
         for(int i=0; i<bSize; i++)
         {
            // Set Bean Scope
            BaseBean absBean = myBeans.get(i);
            String   scope   = absBean.getBeanScope();
            String   bid     = absBean.getBeanID();
            if(null!=bid && !"".equals(bid))
            {
               if(BaseBean.REQUEST.equals(scope))
               {
                  if(null==request.getAttribute(bid))
                     request.setAttribute(bid, absBean);
               }
               else if(BaseBean.SESSION.equals(scope))
               {
                  if(null==request.getSession().getAttribute(bid))
                     request.getSession().setAttribute(bid, absBean);
               }   
               else if(null==servlet.getServletContext().getAttribute(bid))
               {
                  servlet.getServletContext().setAttribute(bid, absBean);
               }
            }
         }
      }
      catch (Throwable t)
      {
      	// need to fix it!
         ExceptionBroadcast.print(t);
         throw new Exception(t.getMessage());
      }

      if(null==targetPage)
         targetPage = "no_page";

      return targetPage;
   }

   protected String getTimeoutPage() 
   {
      return "no_page";//"/defaultskin/share/top.jsp?go=index.scrm?msg=nosession";
   }
      
   protected BaseBean createBean(String beanName,
                                 String beanClassName,
                                 String beanScope,
                                 HttpServletRequest request) throws CreateBeanException
   {
      if(null==beanName)
        throw new CreateBeanException("Bean name is null.");
      if(null==beanClassName)
        throw new CreateBeanException("Bean class name is null.");
      if(null==beanScope)
        throw new CreateBeanException("Bean scope is null.");

      BaseBean bean = null;
      try
      {
         //System.out.println("[BaseHandler::createBean] ---------- Create Bean ----------");
         //System.out.println("[BaseHandler::createBean] 1. Bean Name : " + beanName);
         //System.out.println("[BaseHandler::createBean] 2. Bean Class: " + beanClassName);
         //System.out.println("[BaseHandler::createBean] 3. Bean Scope: " + beanScope);

         // Check if this bean has already been created!
         HttpSession session = request.getSession();
         if(BaseBean.SESSION.equals(beanScope))
         {
            bean = (BaseBean)session.getAttribute(beanName);
         }   
         else if(BaseBean.REQUEST.equals(beanScope))
         {  
            bean = (BaseBean)request.getAttribute(beanName);
         }   
         else
         { 
            bean = (BaseBean)myconfig.getServletContext().getAttribute(beanName);
         }

         // If this bean has not been created!
         if(bean==null)
         {
            bean = (BaseBean)Beans.instantiate(this.getClass().getClassLoader(), beanClassName);
            bean.setBeanID(beanName);
            bean.setBeanScope(beanScope);
         }
         return bean;
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new CreateBeanException(e.getMessage());
      }
   }
}