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
import org.yang.services.servicemgr.Area;
import org.yang.services.servicemgr.Operation;


abstract class AlertService implements Area
{
   private String code = null;
   public void setCode(String code) { this.code = code; }
   public String getCode() { return code; }

   private String type = null;
   public void setType(String type) { this.type = type; }
   public String getType() { return type; }

   private String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private Operation[] operations = null;
   public void setAllOperations(Operation[] operations) { this.operations = operations; }
   public Operation[] getAllOperations() { return operations; }

   public void trigger(Alert alert)
   {
      for(int i=0; i<operations.length; i++)
      {
         ((AlertDeliveryMethod)operations[i]).addDeliveries(createDeliveries(operations[i].getName()));
      }
   }
   
   abstract public Delivery[] createDeliveries(String operationName);
   
   //abstract public void setAlertSenders(Hashtable senders);


   public boolean containOperation(String operationname)
   {
      for(int i=0; i<operations.length; i++)
      {
         if(operations[i].getName().equals(operationname))
             return true;
      }
      return false;
   }
}


