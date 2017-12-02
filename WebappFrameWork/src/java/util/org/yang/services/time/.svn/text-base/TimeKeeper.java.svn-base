package org.yang.services.time;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import org.yang.util.ExceptionBroadcast;

// for test

public class TimeKeeper implements Runnable
{
   private static TimeKeeper myInstance = null;
   private static Object lock = new Object();
   private boolean isStop = false;
   
   private Thread  myThread = null;
   private Date    now      = null;
   
   private TimeKeeper()
   {
      now = new Date();
   }

   public static TimeKeeper getInstance()
   {
      if(null==myInstance)
      {
         synchronized(lock)
         {
            if(null==myInstance)
               myInstance = new TimeKeeper();
         }	
      }
      return myInstance;
   }
   
   public Date getTime()
   { 	
      return now;   	
   }
   
   public Date getTime(TimeZone tz)
   {
      return Calendar.getInstance(tz).getTime();
   }
   
   public void run()
   {
      int i=0;
      while(!isStop)
      {
         now = new Date();
         
         try
         {
            myThread.sleep(1000);
            if(i==60*10)
            {
               //&System.out.println("[TimeKeeper::run] Me:" + this + ", Current Time:" + now);
               i=0;
            }
            i++;
         }
         catch(Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
   }

   /*********************
    * My Private Method *
    *********************/
}