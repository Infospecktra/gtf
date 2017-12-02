/* Generated by Together */

package org.yang.customized.gtf.services.inventoryManager.web;

import org.yang.web.applicationContainer.SecuredBean;
import org.yang.customized.gtf.services.inventoryManager.InventoryManager;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

abstract public class InventoryServiceBean extends SecuredBean
{
   static final long serialVersionUID = 4711694382279869901L;

   protected transient InventoryManager inventoryMgr = null;


   protected int calendarView = 0; //1 = monthly mode;0 = yearly mode
   public void setCalendarView(int calendarView)
   {
       if (calendarView==2)
           return;
       this.calendarView = calendarView;
   }
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

   public InventoryServiceBean()
   {
      super();
      Date now = new Date();
      displayedYear = now.getYear();
      displayedMonth = now.getMonth();
      displayedDay = now.getDate();
      //System.out.println("||||||||||||||||||||||||||||||||||"+ displayedYear + ":" + displayedMonth + ":" + displayedDay);
   }

   /***************************************
    *  Implement GenericHandler's method  *
    ***************************************/

   protected void ensureResource() throws Exception
   {
      //System.out.println("[InventoryServiceBean::ensureResource] entering!");

      if(null==inventoryMgr)
         inventoryMgr = (InventoryManager)this.passport.loadService("InventoryManager");
   }

   public String whoIsIt(String domain, String user)
   {
      return passport.whoIsIt(domain, user);
   }

   /***************************************
    *  utilities method  *
    ***************************************/

    protected String getOneDayBeforeStartDate()
    {

       java.sql.Date date ;
       String formatedDate ="";
       Calendar calendar = Calendar.getInstance();
       try
       {
          if(calendarView==0)
          {
             calendar.set(displayedYear+1899,11,31);
          }
          else
          {
             calendar.set(displayedYear+1900,displayedMonth-1,1);
             int actualMaxDaysOfThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
             calendar.set(displayedYear+1900,displayedMonth-1,actualMaxDaysOfThisMonth);
          }
          date = new java.sql.Date(calendar.getTime().getTime());
          SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
          formatedDate = formater.format(date);
          /*
          System.out.println("[InventoryServiceBean::startDate]-----calendarView="+calendarView);
          System.out.println("[InventoryServiceBean::startDate]-----displayedYear="+displayedYear);
          System.out.println("[InventoryServiceBean::startDate]-----displayedMonth="+displayedMonth);
          System.out.println("[InventoryServiceBean::startDate]-----date="+formatedDate);
          */
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       return formatedDate;

    }


    protected String getOneDayAfterEndDate()
    {
       java.sql.Date date ;
       String formatedDate ="";
       Calendar calendar = Calendar.getInstance();
       try
       {
          if(calendarView==0) // year mode
          {
             calendar.set(displayedYear+1901,0,1);//1,31);
          }
          else
          {
             if(displayedMonth!=11)
               calendar.set(displayedYear+1900,displayedMonth+1,1);
             else
               calendar.set(displayedYear+1901,1,1);
          }
          date = new java.sql.Date(calendar.getTime().getTime());
          SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
          formatedDate = formater.format(date);
          /*
           System.out.println("[InventoryServiceBean::startDate]-----calendarView="+calendarView);
           System.out.println("[InventoryServiceBean::startDate]-----displayedYear="+displayedYear);
           System.out.println("[InventoryServiceBean::startDate]-----displayedMonth="+displayedMonth);
           System.out.println("[InventoryServiceBean::endDate]-----endDate="+formatedDate);
          */
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       return formatedDate;

    }

}