/* by Steven Yang */

package org.yang.services.messageBus.JMS;

import javax.jms.Connection;
import javax.naming.NamingException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueConnection;

abstract public class JMSHelper
{
   private static Hashtable jndiContexts = new Hashtable();
   private static Object lock = new Object();

   public static Connection createTopicConnection(String userID,
                                                  String userPasswd,
                                                  String factoryJNDI,
                                                  Hashtable jndi_props)
   {
      try
      {
         TopicConnectionFactory  topicConnectionFactory = (TopicConnectionFactory)jndiLookUp(factoryJNDI, jndi_props);

         TopicConnection topicConnection = null;
         if(null==userID||null==userPasswd)
      	 {
            System.out.println("[JMSHelper::createTopicConnection] Trying create new connection with username and password.");
            topicConnection = topicConnectionFactory.createTopicConnection();
            topicConnection.setClientID("TEST");
            System.out.println("[JMSHelper::createTopicConnection] New connection (with username and password) : " + topicConnection);
         }
         else
         {
            System.out.println("[JMSHelper::createTopicConnection] Trying create new connection without username and passpord.");
            topicConnection = topicConnectionFactory.createTopicConnection(userID,userPasswd);
            System.out.println("[JMSHelper::createTopicConnection] New connection (without username and password) : " + topicConnection);
         }
         return topicConnection;
      }
      catch (Exception e)
      {
         System.out.println("[JMSHelper::createTopicConnection] Exception occurred: " + e.getMessage());
      }
      
      return null;
   }

   public static Connection createQueueConnection(String userID,
                                                  String userPasswd,
                                                  String factoryJNDI,
                                                  Hashtable jndi_props)
   {
      try
      {
         QueueConnectionFactory  queueConnectionFactory = (QueueConnectionFactory)jndiLookUp(factoryJNDI, jndi_props);
         QueueConnection queueConnection = null;

         if(null==userID||null==userPasswd)
      	 {
            System.out.println("[JMSHelper::createQueueConnection] Trying create new connection with username and password.");
            queueConnection = queueConnectionFactory.createQueueConnection();
         }
         else
         {
            System.out.println("[JMSHelper::createTopicConnection] Trying create new connection without username and password.");
            queueConnection = queueConnectionFactory.createQueueConnection(userID,userPasswd);
         }
         return queueConnection;
      }
      catch (Exception e)
      {
         System.out.println("[JMSHelper::createQueueConnection] Exception occurred: " + e.getMessage());
      }
      
      return null;
   }

   public static Object jndiLookUp(String name, Hashtable jndi_props) throws NamingException
   {
      Context jndiContext = null;
      String key = jndi_props.toString();
      int namingErrorCount = 0;
      try
      {
         System.out.println("[JMSHelper::jndiLookUp] jndi name : " + name);
         System.out.println("[JMSHelper::jndiLookUp] jndi key  : " + key);

         if(null==(jndiContext=(Context)jndiContexts.get(key)))
         {
            synchronized(lock)
            {
               if(null==(jndiContext=(Context)jndiContexts.get(key)))
               {
                  System.out.println("************************************************************");
                  System.out.println(jndi_props);
                  System.out.println("************************************************************");
                  jndiContext = new InitialContext(jndi_props);
                  jndiContexts.put(key, jndiContext);
               }
            }
         }
      }
      catch(Exception e)
      {
         //e.printStackTrace();
         System.out.println("[JMSHelper::jndiLookUp] Exception occurred: " + e.getMessage());
         throw new NamingException(e.getMessage());
      }

      try
      {
         Object obj = jndiContext.lookup(name);
         System.out.println("[JMSHelper::jndiLookUp] New connection factory : " + obj);
         return obj;
      }
      catch(NamingException ne)
      {
         namingErrorCount++;
         if(namingErrorCount>5)
         {
            jndiContext = null;
         }
         throw ne;
      }
   }
}