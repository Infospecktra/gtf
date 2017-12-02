package org.yang.web.applicationContainer;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.Collection;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.yang.web.controller.GenericBean;
import org.yang.services.domainMgr.DomainManager;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.services.domainMgr.Domain;
import org.yang.services.servicemgr.Service;
import org.yang.services.accountmgr.User;
import org.yang.web.controller.SessionPhrase;
import org.yang.web.controller.BackendService;
import org.yang.web.controller.ApplicationContainer;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company: SCRM
 * @author Jason Wang
 * @version 1.0
 * Modified Date: 3/15/2002
 */

public class PassportBean extends GenericBean implements Passport
{
   static final long serialVersionUID = 4711296382979764900L;

   private final String LOGIN_FAILURE_FORWARD = "login-fail-forward";
   private Properties runtimeProperties = null;

   private transient ApplicationContainer myContainer = null;
   private transient DomainManager domainManager = null;

   private String altPageKey = null;

   /**
    *  form set and get methods
    *  got to have this method (Steven Yang - Dec 20 2002)
    */

   private boolean hasLogon = false;
   public boolean getHasLogon() { return hasLogon; }

   private String password = "";
   public void setPassword(String newPassword) { password = newPassword; }
   public String getPassword() { if (password == null) return ""; else return password; }

   private String username = "";
   public void setUsername(String un) { username = un; }
   public String getUsername() { if (username == null) return ""; else return username; }

   private String[] groups = null;
   public void setGroups(String[] groups) { this.groups = groups; }
   public String[] getGroups() { return groups; }

   private String domain = "";
   public void setDomain(String dom) { domain = dom.toUpperCase(); }
   public String getDomain() { return domain; }

   private String currentServiceID = null;
   public void setCurrentServiceID(String currentServiceID) { this.currentServiceID = currentServiceID; }
   public String getCurrentServiceID() { return currentServiceID; }

   private ServiceDescriptor[] availableServiceDescriptors = null;
   public ServiceDescriptor[] getAvailableServiceDescriptors() { return availableServiceDescriptors; }

   private ServiceDescriptor[] allServiceDescriptors = null;
   public ServiceDescriptor[] getAllServiceDescriptors() {  return allServiceDescriptors; }

   public ServiceDescriptor getCurrentServiceDescriptor()
   {
      for(int i=0; i<allServiceDescriptors.length; i++)
      {
         //System.out.println("currentId:" + currentServiceID + ", serviceId:" + allServiceDescriptors[i].getName());
         if(currentServiceID.equals(allServiceDescriptors[i].getName()))
            return allServiceDescriptors[i];
      }

      return null;
   }

   public Map getAllServiceDescriptorsMap()
   {
      HashMap map = new HashMap();
      for(int i=0; i<allServiceDescriptors.length; i++)
      {
         map.put(allServiceDescriptors[i].getName(), allServiceDescriptors[i]);
      }
      return map;
   }

   private PropUtil userProp = null;
   public void setUserPropUtil(PropUtil userProp) {this.userProp = userProp; }
   public PropUtil getUserPropUtil() { return userProp; }

   private PropUtil defaultProp = null;
   public void setDefaultPropUtil(PropUtil defaultProp) { this.defaultProp = defaultProp; }
   public PropUtil getDefaultPropUtil() { return defaultProp; }

   private PropUtil sysProp = null;
   public void setSysPropUtil(PropUtil sysProp) {this.sysProp = sysProp; }
   public PropUtil getSysPropUtil() { return sysProp; }

   public PassportBean()
   {
      super();
      runtimeProperties = new Properties();
   }

  /*
   public boolean passSecurityCheck(HttpServletRequest request)
   {
       System.out.println("[PassportBean::passSecurityCheck] Enter security check.");
       if()
       {
          System.out.println("[SecuredBean::passSecurityCheck] After security check --- Pass!");
          return true;
       }

       System.out.println("[SecuredBean::passSecurityCheck] After security check --- Fail!");
       return false;
   }
   */

   
   
   public Domain getMyDomain()
   {
      return getDomain(domain);
   }

   public Domain getDomain(String aDomain)
   {
      if(null==domainManager)
         domainManager = (DomainManager)loadService("EngineersDesk");
      return domainManager.getDomain(aDomain);
   }

   public String[] getDomains()
   {
      if(null==domainManager)
         domainManager = (DomainManager)loadService("EngineersDesk");
      return domainManager.getAllDomainNames();
   }

   public int getDomainsCount()
   {
      return getDomains().length;
   }

   public String[] getDomainUserNames(String domainName)
   {
      try
      {
         return myContainer.getUserManager(domainName).getAllUserNames();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public Service loadService(String id)
   {
      //&System.out.println("Current Service : " + currentServiceID + ", Requested Service : " + id);
      try
      {
         if(null!=id)//&&id.equals(currentServiceID))
            return myContainer.getServiceManager().getService(id, domain, this);
      }
      catch(Exception e)
      {
         System.out.println("[PassportBean::loadService] The service you want to load is null.");
      }
      return null;
   }

   public BackendService loadBackendService(String serviceName)
   {
      return myContainer.getServiceManager().getBackendService(serviceName);
   }

   public String[] getServiceAreas(String servicename)
   {
      try
      {
         return myContainer.getUserManager(domain)
                           .getUserResource(username, servicename)
                           .getAllAreas();
      }
      catch(Exception e)
      {
         return new String[0];
      }
   }

   public String[] getAreaOperations(String servicename, String areaname)
   {
      try
      {
         return myContainer.getUserManager(domain)
                           .getUserResource(username, servicename)
                           .getOperationsInArea(areaname);
      }
      catch(Exception e)
      {
         return new String[0];
      }
   }

   public boolean hasArea(String servicename, String areaname)
   {
      String[] areas = getServiceAreas(servicename);
      for(int i=0; i<areas.length; i++)
      {
         if(areas[i].equals(areaname))
            return true;
      }
      return false;
   }

   public boolean hasAreaOperation(String servicename, String areaname, String operationname)
   {
      String[] operations = getAreaOperations(servicename, areaname);
      for(int i=0; i<operations.length; i++)
      {  
	     //System.out.println("[PassportBean::hasAreaOperation]---------->operationname="+operationname);
	     //System.out.println("[PassportBean::hasAreaOperation]---------->operations["+i+"]="+operations[i]);
	  
         if(operations[i].equals(operationname))
            return true;
      }
      return false;
   }

   public String getUserProperty(String k)
   {
      return userProp.getProperty(k);
   }

   public Collection getUserProperties(String k)
   {
      return userProp.getValues(k);
   }

   public String getDefaultProperty(String k)
   {
      return defaultProp.getProperty(k);
   }

   public Collection getDefaultProperties(String k)
   {
      return defaultProp.getValues(k);
   }

   public String getSystemProperty(String k)
   {
      return sysProp.getProperty(k);
   }

   public Collection getSystemProperties(String k)
   {
      return sysProp.getValues(k);
   }

   public void setRuntimeProperty(String k, String val)
   {
      runtimeProperties.setProperty(k, val);
   }

   public String getRuntimeProperty(String k)
   {
      return runtimeProperties.getProperty(k);
   }

   public String removeRuntimeProperty(String k)
   {
      return (String)runtimeProperties.remove(k);
   }

   public long getCurrentTime()
   {
      return System.currentTimeMillis();
   }

   public String calculateURL(String url)
   {
      if(null==url)
         return "n/a";
      if(url.startsWith("http://"))
         return url;
      else
         return new StringBuffer(getSystemProperty("appbase")).append(getSystemProperty("skin")).append(url).toString();
   }

   public User loaUserInfo()
   {
      try
      {
         return myContainer.getUserManager(this.getDomain()).getUser(this.username);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public boolean saveUserInfo(User me)
   {
      try
      {
         return myContainer.getUserManager(this.getDomain()).updateUser(me);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return false;
      }
   }

   public String whoIsIt(String domain, String username)
   {
      try
      {
         //System.out.println("[PassportBean::whoIsIt] query domain:" + domain + ", user:" + username);
         User user = myContainer.getUserManager(domain).getUser(username);
         if(null==user)
         {
            //System.out.println("[PassportBean::whoIsIt] No such user in database.");
            return username;
         }
         return user.getLastName() + ", " + user.getFirstName();
      }
      catch(Exception e)
      {
         System.out.println("[PassportBean::whoIsIt] Exception happen :" + e.getMessage());
         return username;
      }
   }

   public String[] whatIsPhoneNumber(String domain, String username)
   {
      try
      {
         //System.out.println("[PassportBean::whoIsIt] query domain:" + domain + ", user:" + username);
         User user = myContainer.getUserManager(domain).getUser(username);
         String phone = user.getTelephoneNumber();
         if(null==phone||"".equals(phone))
            phone = "N/A";
         String cell = user.getCellphoneNumber();
         if(null==cell||"".equals(cell))
            cell = "N/A";
         return new String[]{phone, cell};
      }
      catch(Exception e)
      {
         System.out.println("[PassportBean::whoIsIt] Exception happen :" + e.getMessage());
         return new String[]{"N/A"};
      }
   }

   /*************************************
    *  Implement Serializable's method  *
    *************************************/

   protected void ensureResource() throws Exception
   {
      if(null==myContainer)
         myContainer =  ApplicationContainer.getContainer();
   }

   protected void init() throws Exception
   {
       ensureResource();
       myContainer.prepareLoginInformation(this);
   }

   protected void destroy()
   {
      if(null!=myContainer)
         myContainer.logout(this);
   }

   protected boolean allowNoActionAccess()
   {
      return true;
   }

   protected String altPage()
   {
      return altPageKey;
   }

   protected String debug()
   {
      String temp = "Domain = " + domain +
                    " / User = " + username +
                    " / Password = " + password +
                    " / userProperties = " + userProp +
                    " / sysProperties = " + sysProp;
      if(hasLogon)
         temp = "Login successfully! -- " + temp;
      else
         temp = "Login fail -- " + temp;

      return temp;
   }

   /***************************************
    *           my action method          *
    ***************************************/

   public void logon() throws Exception
   {
      try
      {
         if(_session != null)
         {
            _session.invalidate();
            _session = _request.getSession();
         }

         handleCookie();

         if(true==(hasLogon=myContainer.login(this)))
         {
            refreshService();
            altPageKey = currentServiceID;
         }
         else
         {
            this.setMsg("Login fail, please try it again!");
            actiontype = null;
            altPageKey = LOGIN_FAILURE_FORWARD;
         }
         this.setState(this.PASS);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         this.setState(this.ERR);
      }
   }

   public void switchService()
   {
      altPageKey = currentServiceID;
      refreshService();
   }

   /***************************
    *  My own private methods *
    ***************************/

   private void handleCookie() throws Exception
   {
      //*** read cookies
      Cookie[] cookies = _request.getCookies();
      String cookieUsername = null;
      String cookieDomain = null;
      if(cookies != null) 
      {
         for(int i=0; i<cookies.length; i++) 
         {
            Cookie c = (Cookie)cookies[i];
            if ("".equals(domain))
               if (c.getName().equals(SessionPhrase.COOKIE_DOMAIN)) 
                  cookieDomain = c.getValue();
            if ("".equals(username))
               if (c.getName().equals(SessionPhrase.COOKIE_USER))
                  cookieUsername = c.getValue();
         }
      }

      //**** set cookies to client's browser
      Cookie domCookie = new Cookie(SessionPhrase.COOKIE_DOMAIN, cookieDomain);
      Cookie nameCookie = new Cookie(SessionPhrase.COOKIE_USER, cookieUsername);
      domCookie.setMaxAge(15768000); // half year
      nameCookie.setMaxAge(15768000); // half year
      _response.addCookie(domCookie);
      _response.addCookie(nameCookie);
   }

   private void refreshService()
   {
      try
      {
         allServiceDescriptors = myContainer.getServiceManager().getAllServices();

         Set availableIDSet = myContainer.getUserManager(domain).getResourceIDSetByUID(username);

         availableServiceDescriptors = new ServiceDescriptor[availableIDSet.size()];
         int availableSeviceCount = 0;

         //System.out.println("[PassportBean::refreshService] Current Service : " + currentServiceID);

         for(int i=0; i<allServiceDescriptors.length; i++)
         {
            if(availableIDSet.contains(allServiceDescriptors[i].getName()))
            {
               // an available service is found
               allServiceDescriptors[i].setIsDisable(false);
               availableServiceDescriptors[availableSeviceCount] = allServiceDescriptors[i];
               availableSeviceCount++;
            }
            else
            {
               // an unavailable service is found
               allServiceDescriptors[i].setIsDisable(true);
            }

            if(currentServiceID.equals(allServiceDescriptors[i].getName()))
            {
               // Found current service
               allServiceDescriptors[i].setIsCurrent(true);
            }
            else
            {
               // not current service
               allServiceDescriptors[i].setIsCurrent(false);
            }
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}