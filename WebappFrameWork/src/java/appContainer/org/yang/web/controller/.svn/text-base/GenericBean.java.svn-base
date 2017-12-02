package org.yang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import org.yang.util.ExceptionBroadcast;
import javax.servlet.http.HttpSessionBindingEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.Servlet;

public abstract class GenericBean extends BaseBean implements HttpSessionBindingListener, HttpSessionActivationListener
{
   public static final int NEW = 0;
   public static final int PASS = 1;
   public static final int ERR = -1;

   private static final Class[] allSupportedTypes = {(new HashMap()).getClass(),
                                                     "".getClass(),
                                                     (new String[1]).getClass(),
                                                     Integer.TYPE,
                                                     (new int[1]).getClass(),
                                                     Boolean.TYPE,
                                                     (new boolean[1]).getClass(),
                                                     Character.TYPE,
                                                     (new char[1]).getClass(),
                                                     Byte.TYPE,
                                                     (new byte[1]).getClass(),
                                                     Short.TYPE,
                                                     (new short[1]).getClass(),
                                                     Long.TYPE,
                                                     (new long[1]).getClass(),
                                                     Float.TYPE,
                                                     (new float[1]).getClass(),
                                                     Double.TYPE,
                                                     (new double[1]).getClass() };

   private transient HashMap myWebSupportMethods = null;

   protected transient HttpServletRequest _request;
   protected transient HttpSession _session;
   public void setRequest (HttpServletRequest newRequest) { _request = newRequest; _session = newRequest.getSession(); }

   protected transient HttpServletResponse _response;
   public void setResponse (HttpServletResponse newResponse) { _response = newResponse; }

   protected transient HttpServlet _servlet;
   public void setServlet (Servlet newServlet) { _servlet = (HttpServlet)newServlet; }

   private int _state; // current state
   protected void setState(int newState) { _state = newState; }
   public int getState() { return _state; }

   private String _msg; // current message that is being appended during validation
   protected void setMsg (String addErrorMsg) { _msg = addErrorMsg; }
   protected void resetMsg () { _msg = null; }
   public String getMsg () { if (_msg == null) return ""; else return _msg; }

   private boolean _skipPageOutput; // should the page output be skipped
   public void setSkipPageOutput (boolean skipPageOutput) { _skipPageOutput = skipPageOutput; }
   public boolean getSkipPageOutput () { return _skipPageOutput; }

   protected String beanID=null;
   public void setBeanID(String bid) { beanID=bid; }
   public String getBeanID() { return beanID; }

   protected String beanScope=null;
   public void setBeanScope(String scope) { beanScope=scope; }
   public String getBeanScope() { return beanScope; }

   protected Properties prop = null;
   public void setProp(Properties prop) { this.prop = prop; }

   private Field[] allFields = null;
   public void setAllFields(Field[] allFields) { this.allFields=allFields; }
   public Field[] getAllFields() { return allFields; }

   protected String actiontype = "";
   public void setActiontype(String actiontype) { this.actiontype = actiontype; }
   public String getActiontype() { return actiontype; }

   protected String lastActiontype = "";
   public String getLastActiontype() { return lastActiontype; }

   protected HashMap myControls = new HashMap();
   public void setControl(String id, Object control) { myControls.put(id, control); }
   public Object getControl(String id) { return myControls.get(id); }
   public Object removeControl(String id) { return myControls.remove(id); }

   protected String defaultPage = null;
   public void setDefaultPage(String defaultPage) { this.defaultPage = defaultPage; }

   protected boolean isReload = false;

   public GenericBean()
   {
      try
      {
         setState(NEW);
         buildAvailableMethods();
         //&System.out.println("[GenerciBean::GenerciBean] Create a bean object : " + this.getClass().getName());
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   public GenericBean lookupBean(String beanScope, String beanName)
   {
      BaseBean bean = findBean(beanScope, beanName);
      if(bean instanceof GenericBean)
         return (GenericBean)bean;
      return null;
   }

   public void setupBean(String beanScope, String beanName, GenericBean bean)
   {
      setBean(beanScope, beanName, bean);
   }

   public Method getWebSupportMethod(String methodName)
   {
      return (Method)myWebSupportMethods.get(normalizedKey(methodName));
   }

   public void printMyMethods()
   {
      Iterator it = myWebSupportMethods.keySet().iterator();
      while(it.hasNext())
      {
         System.out.println("[GenericBean::printMyMethods] Action method : " + it.next());
      }
   }

   public static String normalizedKey(String key)
   {
      return key.toLowerCase();
   }

   /******************************
    *  All my protected methods  *
    ******************************/

   abstract protected void init() throws Exception;

   abstract protected void destroy() throws Exception;

   abstract protected void ensureResource() throws Exception;

   protected void beforeSetParameters()
   {
      isReload = false;
      this.setState(this.NEW);
      this.setMsg("");
   }

   protected void beforeAction() throws Exception
   {

   }

   protected void beforeForward()
   {
   
   }

   protected void process() throws Exception
   {
      String action = getActiontype();
      if(action==null||"".equals(action))
      {
         if(!allowNoActionAccess())
         {
            //&System.out.println("[GenericBean::envokeAction] No action type found - Not allow.");
            setActiontype("");
   	        throw new Exception();
         }
         else
         {
            //&System.out.println("[GenericBean::envokeAction] No action type found - pass.");
            return;
         }
      }

      try
      {
         beforeAction();
         envokeAction(action);
      }
      catch(Exception e)
      {
         System.out.println("[GenericBean::envokeAction] Fail to activate action:" + e.getMessage());
         throw new Exception(e.getMessage());
      }
      finally
      {
         lastActiontype = actiontype;
         setActiontype("");
      }
   }

   protected boolean allowNoActionAccess()
   {
      return false;
   }

   protected String altPage()
   {
      return null;
   }

   protected String dynamicPage()
   {
      return null;	
   }

   protected String debug()
   {
      return "Please implement debug() in your bean.";
   }

   /***************************************************
    *  Implement HttpSessionBindingListener's method  *
    ***************************************************/

   public void valueBound(HttpSessionBindingEvent event)
   {
      //System.out.println("[GenericBean::valueBound] entering!");
   }

   public void valueUnbound(HttpSessionBindingEvent event)
   {
      try
      {
         System.out.println("[GenericBean::valueUnbound] entering!");
         destroy();
      }
      catch(Exception e)
      {
         System.out.println("[GenericBean::valueUnbound] Exception happen : " + e.getMessage());
      }
   }

   /***************************************************
    *  Implement HttpSessionBindingListener's method  *
    ***************************************************/

   public void sessionWillPassivate(HttpSessionEvent se)
   {
      System.out.println("[GenericBean::sessionWillPassivate] entering!");
   }

   public void sessionDidActivate(HttpSessionEvent se)
   {
      System.out.println("[GenericBean::sessionDidActivate] entering!");
      try
      {
         ensureResource();
      }
      catch(Exception e)
      {
         System.out.println("[GenericBean::valueBound] Exception happen : " + e.getMessage());
      }
   }

   /*************************************
    *  Implement Serializable's method  *
    *************************************/

   private void writeObject(ObjectOutputStream out) throws IOException
   {
      //&System.out.println("[GenericBean::writeObject] entering!");
      // Take care of this class's field first by calling defaultWriteObject
      out.defaultWriteObject();
      //out.writeObject(temp);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
   {
      try
      {
         //&System.out.println("[GenericBean::readObject] entering!");
         // Take care of this class's field first by calling defaultWriteObject
         in.defaultReadObject();
         buildAvailableMethods();
      	 //in.readObject();
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   /****************************
    *  All my private methods  *
    ****************************/
   
   private void envokeAction(String action) throws InvocationActionException
   {
      try
      {
         //&System.out.println("[GenericBean::envokeAction] Current action type is :" + action);
         Class[]  cl = {};
         Object[] ob = {};

         Method m = this.getClass().getDeclaredMethod(action, cl);
         m.invoke(this, ob);
      }
      catch(NoSuchMethodException nme)
      {
         throw new InvocationActionException("Action is not supported:" + action);
      }
      catch(InvocationTargetException ite)
      {
         Throwable tempe = ite.getTargetException();
         if(tempe instanceof InvocationActionException)
            throw (InvocationActionException)tempe;
         else
            throw new InvocationActionException(tempe.getMessage());
      }
      catch(Exception e)
      {
         throw new InvocationActionException(e.getMessage());
      }   	
   }
   
   private void buildAvailableMethods()
   {
      Class me = this.getClass();
      myWebSupportMethods = new HashMap();
      ArrayList methods = null;
      String methodName = null;
      HashMap targetMethods = getAllPossibleMethods(me);
      Method targetMethod = null;
      Iterator it = targetMethods.keySet().iterator();
      while(it.hasNext())
      {
         methodName = (String)it.next();
         //System.out.println("[GenericBean::buildAvailableMethods] fount a method - " + methodName );
         methods = (ArrayList)targetMethods.get(methodName);

         if(null==methods)
            continue;

         int methodsSize = methods.size();
         int typeSize = allSupportedTypes.length;
         boolean gotTarget = false;
         String key = null;
         for(int k=0; k<typeSize; k++)
         {
            for(int j=0; j<methodsSize; j++)
            {
               targetMethod = (Method)methods.get(j);
               if(allSupportedTypes[k].equals((targetMethod.getParameterTypes())[0]))
               {
                  key = methodName2Key(methodName);
                  //System.out.println("[Genericbean::buildAvailableMethods] - ok - " + key);
                  myWebSupportMethods.put(key, targetMethod);
                  gotTarget = true;
                  break;
               }
            }

            if(gotTarget)
               break;
         }
      }
   }

   private String methodName2Key(String methodName)
   {
      StringBuffer theKey = new StringBuffer(20);
      theKey.append(Character.toLowerCase(methodName.charAt(3)))
            .append(methodName.substring(4));
      return normalizedKey(theKey.toString());
   }

   private HashMap getAllPossibleMethods(Class bean)
   {
      HashMap methodNameMap = new HashMap();
      ArrayList targetMethods = getAllMethods(bean);
      ArrayList temp = null;
      int methodsSize = targetMethods.size();
      String methodName = null;
      Class[] para = null;
      Method method = null;
      for(int i=0; i<methodsSize; i++)
      {
         // Check if it's a set method
         method = (Method)targetMethods.get(i);
         methodName = method.getName();
         
         if(!methodName.startsWith("set"))
            continue;

         // Check if it's a single parameter method
         para = method.getParameterTypes();
         if(null==para||1!=para.length)
            continue;

         if(null==(temp=(ArrayList)methodNameMap.get(methodName)))
         {
            temp = new ArrayList();
            methodNameMap.put(methodName, temp);
         }
         temp.add(method);
      }
      return methodNameMap;
   }

   private ArrayList getAllMethods(Class bean)
   {
      ArrayList methods = new ArrayList();
      getMethods(bean, methods);
      return methods;
   }

   private void getMethods(Class bean, ArrayList methods)
   {
      Class superClass = bean.getSuperclass();
      if(null!=superClass)
      {
         getMethods(superClass, methods);
      }

      Method[] myMethods = bean.getMethods();
      int len = myMethods.length;
      for(int i=0; i<len; i++)
      {
         methods.add(myMethods[i]);
      }
   }

   private BaseBean findBean(String beanScope, String beanName)
   {
      //&System.out.println("[GenericBean::findBean] Looking for bean name : " + beanName + ", scope : " + beanScope);
      if(BaseBean.SESSION.equals(beanScope))
      {
         //&System.out.println("[GenericBean::findBean] Session level!");
         return (BaseBean)_session.getAttribute(beanName);
      }
      else if(BaseBean.REQUEST.equals(beanScope))
      {
         //&System.out.println("[GenericBean::findBean] Request level!");
         return (BaseBean)_request.getAttribute(beanName);
      }
      else
      {
         //&System.out.println("[GenericBean::findBean] Application level!");
         return (BaseBean)_servlet.getServletContext().getAttribute(beanName);
      }
   }

   private void setBean(String beanScope, String beanName, BaseBean bean)
   {
      //&System.out.println("[GenericBean::setBean] Set bean, name : " + beanName + ", scope : " + beanScope);
      if(BaseBean.SESSION.equals(beanScope))
      {
         //&System.out.println("[GenericBean::setBean] Session level!");
         _session.setAttribute(beanName, bean);
      }
      else if(BaseBean.REQUEST.equals(beanScope))
      {
         //&System.out.println("[GenericBean::setBean] Request level!");
         _request.setAttribute(beanName, bean);
      }
      else
      {
         //&System.out.println("[GenericBean::setBean] Application level!");
         _servlet.getServletContext().setAttribute(beanName, bean);
      }
   }
}