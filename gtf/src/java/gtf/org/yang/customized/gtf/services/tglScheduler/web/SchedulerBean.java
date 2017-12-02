/* Generated by Together */

package org.yang.customized.gtf.services.tglScheduler.web;

import org.yang.web.applicationContainer.SecuredBean;
import org.yang.customized.gtf.services.tglScheduler.Scheduler;

abstract public class SchedulerBean extends SecuredBean
{
   static final long serialVersionUID = 4711296382979764977L;

   protected int calendarView = 2;
   public void setCalendarView(int calendarView){ this.calendarView = calendarView;}
   public int getCalendarView(){ return calendarView; }

   protected int displayedMonth = 0;
   public void setDisplayedMonth(int displayedMonth){ this.displayedMonth = displayedMonth; }
   public int getDisplayedMonth(){ return displayedMonth; }

   protected int displayedYear = 0;
   public void setDisplayedYear(int displayedYear){ this.displayedYear = displayedYear; }
   public int getDisplayedYear(){ return displayedYear; }

   protected int displayedDay = 0;
   public void setDisplayedDay(int displayedDay){ this.displayedDay = displayedDay; }
   public int getDisplayedDay(){ return displayedDay; }

   protected transient Scheduler scheduler = null;

   public SchedulerBean()
   {
      super();
   }

   /***************************************
    *  Implement GenericHandler's method  *
    ***************************************/

   protected void ensureResource() throws Exception
   {
      System.out.println("[ScheduleBean::ensureResource] entering!");

      if(null==scheduler)
         scheduler = (Scheduler)passport.loadService("Scheduler");
   }
}