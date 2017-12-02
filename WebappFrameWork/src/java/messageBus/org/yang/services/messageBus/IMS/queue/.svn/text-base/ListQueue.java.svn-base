package org.yang.services.messageBus.IMS.queue ;
/*******************************************************
 *                                                     *
 *  @(#)LinkedListQueue.java 1.0 99/12/20              *
 *  Copyright 1999- by Screaming Media, Inc.,          *
 *  601 west 26 street 13 floor New York 10001, U.S.A. *
 *                                                     * 
 *******************************************************/
 
import java.util.ArrayList;
import java.util.Properties;

public class ListQueue implements Queue
{
   static final long serialVersionUID = 4711293984479678969L;

   protected final ArrayList repositry =  new ArrayList();
   
   //----< constructor >--------------------------------------------------------
   /**
    * defaule constructor.
    */
   //
   public ListQueue(){}
   //

   //----< constructor >--------------------------------------------------------
   /**
    * defaule constructor.
    */
   //
   public ListQueue(String ID, Properties prop)
   {}
   //

   //----< finalize >-----------------------------------------------------------
   /**
    * final procedule to clean memory in this object.
    */
   //
   public void finalize()
   {
      repositry.clear();
   }
   //

   //----< get a Document >-----------------------------------------------------
   /**
    * insert an object into the first available space of the LinkedListQueue LinkedListQueue.
    *
    * @param      The object which will be inserted into the LinkedListQueue. 
    * @return     true if the action is successful.
    */
   //
   public boolean enq(Object obj)
   {
      if(null==obj)
         return false;

      try
      {
         synchronized(this)
         {
//System.out.println("before enq length=" + length());
            repositry.add(obj);
//System.out.println("after enq length=" + length());
         }
      }
      catch(Exception e)
      {
//com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
         return false;	
      }     
      return true;
   }
   //

   //----< get a Document >-----------------------------------------------------
   /**
    * Get the first available object in the LinkedListQueue.
    *
    * @return     The first available object in the LinkedListQueue, if there is nothing 
    *             available return null.
    */
   //
   public Object deq()
   {
      Object temp = null;
      synchronized(this)
      {
//System.out.println("before deq length=" + length());
         if(0>=length())
            return temp;

         temp = repositry.remove(0);//len-1);      
//System.out.println("after deq length=" + length());
      }
      return temp;
   }
   //

   //----< get a Document >-----------------------------------------------------
   /**
    * Get the current lenth of this LinkedListQueue.
    *
    * @return     the current length of this LinkedListQueue.
    */
   //
   public int length()
   {
      return repositry.size();
   }
   //
   
   //----< test stub >-------------------------------------------------------------------
   //
   public static void main(String[] argc)
   {
      ListQueue q = new ListQueue();

      q.enq("a");
      q.enq("b");
      q.enq("c");
      q.enq("d");
      q.enq("e");
      q.enq("f");

      System.out.println("Q length : " + q.length());

      System.out.println("deq : " + q.deq());
      System.out.println("Q length : " + q.length());
      System.out.println("deq : " + q.deq());
      System.out.println("Q length : " + q.length());
      System.out.println("deq : " + q.deq());
      System.out.println("Q length : " + q.length());
   }
}