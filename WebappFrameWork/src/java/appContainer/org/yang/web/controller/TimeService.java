package org.yang.web.controller;

import org.yang.services.time.TimeKeeper;
import java.util.Date;
import java.util.TimeZone;
import java.util.Properties;

public class TimeService implements BackendService
{
   private TimeKeeper time = TimeKeeper.getInstance();

   public TimeService() {}

   public void init(Properties prop) {}

   public void start() {}

   public void stop() {}

   public void destroy() {}

   public Date getTime()
   { 	
      return time.getTime();
   }
   
   public Date getTime(TimeZone tz)
   {
      return time.getTime(tz);
   }
}