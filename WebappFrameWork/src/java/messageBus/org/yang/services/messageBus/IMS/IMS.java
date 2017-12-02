/* Generated by Together */

package org.yang.services.messageBus.IMS;

import java.util.HashMap;
import org.yang.services.messageBus.MessageReceiver;
import org.yang.services.messageBus.Message;
import org.yang.services.messageBus.MessageTransmitException;
import org.yang.services.messageBus.IMS.queue.ListQueue;
import java.util.Iterator;
import org.yang.services.messageBus.DataReceivingException;

public class IMS implements Runnable
{
   protected static IMS myInstance = null;
   private static HashMap queueMap = null;
   private static HashMap receiverMap = null;
   private static Object lock = new Object();

   private boolean quit = false;

   protected IMS()
   {
      queueMap = new HashMap();
      receiverMap = new HashMap();
      //new Thread(this).start();
   }

   public static IMS getInstance()
   {
      if(null==myInstance)
      {
         synchronized(lock)
         {
            if(null==myInstance)
            {
               myInstance = new IMS();
            }
         }
      }

      return myInstance;
   }

   public void sendMessage(String routeId, Message msg) throws MessageTransmitException
   {
      IMSReceiverImpl receiver = ((IMSReceiverImpl)receiverMap.get(routeId));
      if(null==receiver)
          throw new MessageTransmitException("[IMS::sendMessage] No receiver defined for route : " + routeId);
      if(receiver.getIsSync())
      {
         syncTypeSend(routeId, msg, receiver);
      }
      else
      {
         AsyncTypeSend(routeId, msg);
      }
   }

   public void notifyRoute(String routeId) throws MessageTransmitException
   {
      ListQueue queue = (ListQueue)queueMap.get(routeId);

      if(null==queue)
          throw new MessageTransmitException("[IMS::sendMessage] No receiver defined for route : " + routeId);

      synchronized(queue)
      {
         queue.notifyAll();
      }
   }

   public Message getMessage(String routeId) throws DataReceivingException
   {
      ListQueue queue = (ListQueue)queueMap.get(routeId);

      if(null==queue)
          throw new DataReceivingException("[IMS::getMessage] No data repositry defined for route : " + routeId);

      Message msg = null;
      while(null!=queue&&null==(msg=(Message)queue.deq()))
      {
         try
         {
            synchronized(queue)
            {
               queue.notifyAll();
               queue.wait();
            }

            queue = (ListQueue)queueMap.get(routeId);           

            synchronized(queue)
            {
               queue.notifyAll();
            }
         }
         catch(Exception e)
         {
            System.out.println("[IMS::getMessage] Exception happen when waiting at " + routeId + " :" + e.getMessage());
         }
      }

      return msg;
   }

   public Message getMessage(String routeId, long timeout) throws DataReceivingException
   {
//System.out.println("[IMS::getMessage(nonblocking)] Get message from " + routeId);
      ListQueue queue = (ListQueue)queueMap.get(routeId);

      if(null==queue)
          throw new DataReceivingException("[IMS::getMessage] No data repositry defined for route : " + routeId);

      Message msg = null;
      while(null!=queue&&null==(msg=(Message)queue.deq())&&(timeout>=0))
      {
         try
         {
            Thread.currentThread().sleep(100);
            timeout -= 100;
            queue = (ListQueue)queueMap.get(routeId);
            synchronized(queue)
            {
               queue.notifyAll();
            }
         }
         catch(Exception e)
         {
            System.out.println("[IMS::getMessage] Exception happen when waiting at " + routeId + " :" + e.getMessage());
         }
      }

      return msg;
   }

   public void setReceiver(String routeId, MessageReceiver receiver)
   {
      if(null!=receiver)
         receiverMap.put(routeId, receiver);
      queueMap.put(routeId, new ListQueue());
      ((IMSReceiverImpl)receiver).start();
   }

   public void removeReceiver(String routeId)
   {
      receiverMap.remove(routeId);
      Object queue = queueMap.remove(routeId);
      synchronized(queue)
      {
        queue.notifyAll();
      }
   }

   public void clear()
   {
      receiverMap.clear();
      queueMap.clear();
   }
   
   public void run()
   {
      try
      {
         Thread.currentThread().sleep(10000);
      } catch(Exception e) {}
      
      ListQueue queue = null;
      while(!quit)
      {
      	 Iterator it = queueMap.keySet().iterator();
      	 while(it.hasNext())
      	 {
            String qKey = (String)it.next();
            queue = (ListQueue)queueMap.get(qKey);
      	    System.out.println("Queue name : " + qKey + ", size : " + queue.length());
      	 }
      	 
         try
         {
            Thread.currentThread().sleep(10000);
         } catch(Exception e) {}

      	
      }	
   }

   private void syncTypeSend(String routeId, Message msg, MessageReceiver receiver)
   {
      try
      {
         receiver.onMessage(msg);
      }
      catch(Exception e)
      {
          System.out.println("[IMS::syncTypeSend] Exception happen when deliver IMS :" + e.getMessage());
      }
      catch(Throwable t)
      {
         System.out.println("[IMS::syncTypeSend] Fatal Error happen when deliver IMS :" + t.getMessage());
      }
   }

   private void AsyncTypeSend(String routeId, Message msg)throws MessageTransmitException
   {
      ListQueue queue = (ListQueue)queueMap.get(routeId);

      if(null==queue)
          throw new MessageTransmitException("[IMS::sendMessage] No receiver queue defined for route : " + routeId);

      while(queue.length()>=50)
      {
         try
         {
            synchronized(queue)
            {
               queue.notifyAll();
               queue.wait();
            }
         } catch(Exception e) {}
      }

      queue.enq(msg);

      synchronized(queue)
      {
         queue.notifyAll();
      }
   }
}