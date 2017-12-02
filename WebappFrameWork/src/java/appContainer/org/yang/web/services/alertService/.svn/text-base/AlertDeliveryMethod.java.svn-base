/**
 * This class is used to manage client to access a domain and to manipulate some services
 * within each domain.
 * each domain has one UserManager object. Although, we donot define an
 * domain class, the domain concept will appear in database to form an
 * unit of users. Their relationship is: a set of users can belong to another
 * set of groups, which in turn form a domain. A domain will be passed to database
 * as a string
 * @ title  UserManager class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
package org.yang.web.services.alertService;

import org.yang.services.servicemgr.Operation;
import java.util.Properties;
import java.util.ArrayList;

abstract class AlertDeliveryMethod implements Operation
{
   protected ArrayList deliveryList = null;

   private String name = "";
   public String getName() { return name; }

   protected Properties properties = null;
   public void setProperties (Properties properties) { this.properties = properties; }

   AlertDeliveryMethod()
   {
      deliveryList = new ArrayList();
   }

   public void addDeliveries(Delivery[] deliveries)
   {
      for(int i=0; i<deliveries.length; i++)
         addDelivery(deliveries[i]);
   }

   public void addDelivery(Delivery delivery)
   {
      deliveryList.add(delivery);
   }

   /**
    * Send the alert.
    *
    * @param	receiver - those who receive the alert.
    *		log	 - the alert message.
    * @see	com.screamingmedia.siteware.services.alertService.AlertReceiver
    * @see	com.screamingmedia.siteware.webService.reports.buslogic.cat.sys.SysLogObj
    */	   	
   abstract public void deliver(Alert alert) throws AlertSendingException;
}