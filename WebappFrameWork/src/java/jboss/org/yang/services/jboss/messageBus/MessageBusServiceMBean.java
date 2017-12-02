/* Generated by Together */

package org.yang.services.jboss.messageBus;

/**
 * This MBean service will load GHMessageBusService
 *
 * following xml chunk is the deploy descriptor for this MBean
 * which shall be bput into jboss.jcml file
 * (ignore the pre tags if visable)<br><br>
 *
 *   <pre>
 *   <mbean code="com.IMDSTrading.GHMessageBus.GHMessageBusService" name="GHMessageBusService:name=JMS">
 *     <attribute name="BusName">JMSMsgBus</attribute>
 *     <attribute name="BusClassName">com.IMDSTrading.GHMessageBus.JMSGHMessageBusImpl</attribute>
 *     <attribute name="RouteMapFile">GHMessageRoute.xml</attribute>
 *   </mbean>
 *   </pre><br><br>
 * 
 *
 * Attributes:
 *   BusName - (required) - ...
 *   BusClassName - (required) - ...
 *   RouteFile - (required) - ...
 *
 */
 
import org.jboss.util.ServiceMBean;

public interface MessageBusServiceMBean extends org.jboss.util.ServiceMBean 
{
   static final long serialVersionUID = 3439536523255252199L;

   public void setRouteMapFile(String mapFile);
   public String getRouteMapFile();

   public void setBusName(String busName);
   public String getBusName();
   
   public void setBusClassName(String busClassName);
   public String getBusClassName();   
}