/* Generated by Together */

package org.yang.services.messageBus;

import java.util.HashMap;
import java.util.Iterator;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.io.Serializable;

public class Route implements Serializable
{
   static final long serialVersionUID = 3434526529444252199L;
   private TransportationServer primaryServer = null;
   private TransportationServer secondaryServer = null;

   private String key_name = null;
   public void setName(String key_name) { this.key_name=key_name; }
   public String getName() { return key_name; }

   private String col_type  = null;
   public void setType(String col_type) { this.col_type=col_type; }
   public String getType() { return col_type; }

   private String col_sender = null;
   public void setSender(String col_sender) { this.col_sender=col_sender; }
   public String getSender() { return col_sender; }

   private String col_receiver = null;
   public void setReceiver(String col_receiver) { this.col_receiver=col_receiver; }
   public String getReceiver() { return col_receiver; }

   private String col_trancieverFactory = null;
   public void setTrancieverFactory(String col_trancieverFactory) { this.col_trancieverFactory = col_trancieverFactory; }
   public String getTrancieverFactory() { return col_trancieverFactory; }

   private String typeDes  = null;
   public void setTypeDes(String typeDes) { this.typeDes=typeDes; }
   public String getTypeDes() { return typeDes; }

   private transient MessageBus bus = null;
   public void setMessageBus(MessageBus bus) { this.bus=bus; }  

   private transient MessageTransmitter tr = null;
   public void setMessageTransmitter(MessageTransmitter tr) { this.tr=tr; }
   public MessageTransmitter getMessageTransmitter() { return tr; }

   private transient MessageReceiver   rc = null;
   public void setMessageReceiver(MessageReceiver rc) { this.rc=rc; }
   public MessageReceiver getMessageReceiver() { return rc; }
   public MessageReceiver removeMessageReceiver() { MessageReceiver myRc = rc; rc = null; return myRc; }

   private Properties prop = null;
   public void setRouteProperties(Properties prop) { this.prop = prop; }
   public Properties getRouteProperties() { return prop; }

   public Route()
   {
      primaryServer = new TransportationServer();
   }

   public Properties getProperties()
   {
      if(bus.getIsPrimaryRoute())
         return primaryServer.getProperties();
      else
         return secondaryServer.getProperties();
   }

   public void setProperty(String aKey, String aValue)
   {
      setProperty(0, aKey, aValue);
   }

   public void setProperty(int priority, String aKey, String aValue)
   {
      switch(priority)
      {
         case 0:
            if(null==primaryServer)
               primaryServer = new TransportationServer();
            primaryServer.setProperty(aKey, aValue);
            break;
         case 1:
            if(null==secondaryServer)
               secondaryServer = new TransportationServer();
            secondaryServer.setProperty(aKey, aValue);
            break;
      }
   }

   public String getProperty(String aKey)
   {
      return getProperty(0, aKey);
   }

   public String getProperty(int priority, String aKey)
   {
      switch(priority)
      {
         case 0:
            if(null==primaryServer)
               return null;
            return primaryServer.getProperty(aKey);
         case 1:
            if(null==secondaryServer)
               return null;
            return secondaryServer.getProperty(aKey);
      }
      return null;
   }

   public String getProperty(String aKey, String defaultValue)
   {
      return primaryServer.getProperty(aKey, defaultValue);
   }

   public String getProperty(int priority, String aKey, String defaultValue)
   {
      switch(priority)
      {
         case 0:
            if(null==primaryServer)
               return defaultValue;
            return primaryServer.getProperty(aKey, defaultValue);
         case 1:
            if(null==secondaryServer)
               return defaultValue;
            return secondaryServer.getProperty(aKey, defaultValue);
      }
      return null;
   }

   public boolean equals(Object o)
   {
      if(null==o)
         return false;
      Route temp = (Route)o;
      return (temp.key_name.equals(key_name)&&
              temp.col_sender.equals(col_sender)&&
              temp.col_receiver.equals(col_receiver)&
              temp.col_type.equals(col_type));
   }

   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("Route Name : " + key_name + "\n");
      if(null!=primaryServer)
      {
         sb.append("   Primary Server : \n");
         sb.append(primaryServer.getProperties() + "\n");
      }
      if(null!=secondaryServer)
      {
         sb.append("   Secondary Server : \n");
         sb.append(secondaryServer.getProperties() + "\n");
      }
      return sb.toString();
   }

   class TransportationServer
   {
      public String name = null;

      private Properties prop = null;
      public Properties getProperties() { return prop; }

      TransportationServer()
      {
         prop = new Properties();
      }

      public void setProperty(String aKey, String aValue)
      {
         prop.setProperty(aKey, aValue);
      }

      public String getProperty(String aKey)
      {
         return prop.getProperty(aKey);
      }

      public String getProperty(String aKey, String defaultValue)
      {
         return prop.getProperty(aKey, defaultValue);
      }
   }
}