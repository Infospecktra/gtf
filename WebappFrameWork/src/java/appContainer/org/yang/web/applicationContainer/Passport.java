/* Generated by Together */

package org.yang.web.applicationContainer;

import org.yang.services.servicemgr.Service;
import org.yang.services.servicemgr.ServiceDescriptor;
import java.util.Collection;
import java.util.Properties;
import java.util.Map;
import org.yang.services.servicemgr.Area;
import org.yang.services.servicemgr.Operation;
import org.yang.services.accountmgr.User;
import org.yang.services.domainMgr.Domain;
import java.io.Serializable;
import org.yang.web.controller.BackendService;

public interface Passport extends Serializable
{
   public static final String DEPLOY_KEY = "passport";

   String getUsername();

   String[] getGroups();

   Domain getMyDomain();

   Domain getDomain(String domainName);

   String getDomain();

   String[] getDomains();

   String[] getDomainUserNames(String domainName);

   /***********************
    *  service management *
    ***********************/

   String getCurrentServiceID();

   Service loadService(String serviceName);

   BackendService loadBackendService(String serviceName);

   ServiceDescriptor getCurrentServiceDescriptor();

   ServiceDescriptor[] getAvailableServiceDescriptors();

   ServiceDescriptor[] getAllServiceDescriptors();

   Map getAllServiceDescriptorsMap();

   String[] getServiceAreas(String servicename);

   String[] getAreaOperations(String servicename, String areaname);

   boolean hasAreaOperation(String servicename, String areaname, String operationname);

   boolean hasArea(String servicename, String areaname);

   String calculateURL(String url);

   /******************************
    * display message management *
    ******************************/
   PropUtil getUserPropUtil();

   PropUtil getSysPropUtil();

   String getUserProperty(String k);

   Collection getUserProperties(String k);

   String getDefaultProperty(String k);

   Collection getDefaultProperties(String k);

   String getSystemProperty(String k);

   Collection getSystemProperties(String k);

   void setRuntimeProperty(String k, String val);

   String getRuntimeProperty(String k);

   String removeRuntimeProperty(String k);

   long getCurrentTime();

   /**************************
    *    user information    *
    **************************/

   public User loaUserInfo();

   public boolean saveUserInfo(User user);

   public String whoIsIt(String domain, String username);

   public String[] whatIsPhoneNumber(String domain, String username);
}
