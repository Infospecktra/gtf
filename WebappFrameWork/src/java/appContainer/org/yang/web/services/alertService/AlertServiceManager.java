package org.yang.web.services.alertService;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.servicemgr.Area;
import java.util.Set;
import java.util.HashSet;
import org.yang.util.xml.XmlRWUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.yang.services.servicemgr.Operation;
import java.util.Collection;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class AlertServiceManager implements Runnable
{
   private static AlertServiceManager myInstance = new AlertServiceManager();

   private Area[] allAlertServicesArray = null;
   private HashMap allAlertServicesMap = null;
   private Set allAlertServiceNames = null;

   private Thread myThread = null;
   private boolean isStop  = false;

   public static final String ALL_DOMAINS = "ALL";

   private AlertServiceManager()
   {
      allAlertServicesMap = new HashMap();
   }

   /***********************
    * implements Runnable *
    ***********************/
    
   public void run()
   {
      Alert alert = null;
      while(!isStop)
      {

      }
   }

   /********************
    * my public method *
    ********************/

   public static AlertServiceManager getInstance()
   {
/*
      if(null==alerts)
      {
         synchronized(senders)
         {
            if(null==alerts)
            {
               readAllAlertsFromFile();
            }
         }
      }
*/
      return myInstance;
   }

   public void stop()
   {
      isStop = true;
   }

   public void doAlert(Alert alert)
   {
      ((AlertService)allAlertServicesMap.get(alert.getType()+alert.getCode())).trigger(alert);
   }

   /*********************
    * My private method *
    *********************/

   private void loadTemplate(String mapPath)
   {
      try
      {
         InputStream is = Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream(mapPath);

         if(null==is)
         {
            System.out.println("[AlertServiceManager::loadTemplate] File " + mapPath + " is not found!");
         }

         Node mappings = XmlRWUtil.read(is);
         ArrayList deliveryMethods = XmlRWUtil.getNodesFromDocument(mappings, "/alertServiceManager/deliveryMethod");
         Operation[] alertDeliveryMethods = null;
         if(null!=deliveryMethods)
            alertDeliveryMethods = new Operation[deliveryMethods.size()];
         else
            alertDeliveryMethods = new Operation[0];

         AlertDeliveryMethod alertDeliveryMethod = null;
         for(int i=0; i<alertDeliveryMethods.length; i++)
         {
            alertDeliveryMethods[i] = createAlertDeliveryMethod((Node)deliveryMethods.get(i));
         }

         ArrayList alertServiceNodes = XmlRWUtil.getNodesFromDocument(mappings, "/alertServiceManager/alert");
         AlertService alertService = null;
         for(int i=0; i<alertServiceNodes.size(); i++)
         {
            alertService = createAlertService((Node)alertServiceNodes.get(i), alertDeliveryMethods);
            allAlertServicesMap.put(alertService.getType() + alertService.getCode(), alertService);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   private AlertService createAlertService(Node alertServiceNode, Operation[] alertDeliveryMethods)
   {
      try
      {
         NamedNodeMap atts = alertServiceNode.getAttributes();

      	 if(null==atts)
      	 {
            return null;
      	 }

         Node codeNode = atts.getNamedItem("code");
         Node typeNode = atts.getNamedItem("type");
         Node nameNode = atts.getNamedItem("name");

      	 if(null==codeNode||null==typeNode||null==nameNode)
      	 {
            return null;
      	 }

         String code = codeNode.getNodeValue();
         String type = typeNode.getNodeValue();
         String name = nameNode.getNodeValue();

         //&System.out.println("[AlertSeviceManager::createAlertService]Alert type : " + type + ", code : " + code + ", name : " + name);

         // create Alert service
         AlertService alertService = AlertServiceFactory.createService();
         alertService.setCode(code);
         alertService.setType(type);
         alertService.setName(name);
         alertService.setAllOperations(alertDeliveryMethods);

         return alertService;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

   private AlertDeliveryMethod createAlertDeliveryMethod(Node deliveryMethod)
   {
      return null;
   }

   public Area[] allAreas()
   {
      if(null==allAlertServicesArray)
      {
         Collection services = allAlertServicesMap.values();
         Iterator it = services.iterator();
         allAlertServicesArray = new Area[services.size()];
         int count = 0;
         while(it.hasNext())
         {
            allAlertServicesArray[count] = (Area)it.next();
            count++;
         }
      }
      return allAlertServicesArray;
   }

   public boolean containArea(String areaname)
   {
      if(null==allAlertServiceNames)
      {
         allAlertServiceNames = allAlertServicesMap.keySet();
         if(null==allAlertServiceNames)
            return false;
      }

      return allAlertServiceNames.contains(areaname);
   }
}