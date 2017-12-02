/* Generated by Together */

package org.yang.util.calendar;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CalendarHelper
{
   public static final String[] DAY_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

   private Calendar calendar = null;

   public CalendarHelper(String dateTime, String format)
   {
      try
      {
         SimpleDateFormat formater
               = new SimpleDateFormat (format);
         Date date = formater.parse(dateTime);
         calendar = Calendar.getInstance();
         //&System.out.println(year+"/"+month+"/"+day+"T"+hour+":"+minute+":"+second);
         calendar.set(date.getYear()+1900,
                      date.getMonth(),
                      date.getDate(), 
                      date.getHours(), 
                      date.getMinutes(), 
                      date.getSeconds());
      }
      catch(Exception e) 
      {
         e.printStackTrace();
      }
   }

   public CalendarHelper(Calendar calendar)
   {
      this.calendar = calendar;
   }

   public CalendarHelper(int year, int month, int day, int hour, int minute, int second)
   {
      calendar = Calendar.getInstance();
      //&System.out.println(year+"/"+month+"/"+day+"T"+hour+":"+minute+":"+second);
      calendar.set(year, month, day, hour, minute, second);
   }

   public void setYear(int year) { calendar.set(Calendar.YEAR, year); }
   public int getYear() { return calendar.get(Calendar.YEAR); }

   public void setMonth(int month) { calendar.set(Calendar.MONTH, month); }
   public int getMonth() { return calendar.get(Calendar.MONTH); }

   public void setDate(int date) { calendar.set(Calendar.DAY_OF_MONTH, date); }
   public int getDate() { return calendar.get(Calendar.DAY_OF_MONTH); }

   public void setHours(int hours) { calendar.set(Calendar.HOUR_OF_DAY, hours); }
   public int getHours() { return calendar.get(Calendar.HOUR_OF_DAY); }

   public void setMinutes(int minutes) { calendar.set(Calendar.MINUTE, minutes); }
   public int getMinutes() { return calendar.get(Calendar.MINUTE); }

   public void setSeconds(int seconds) { calendar.set(Calendar.SECOND, seconds); }
   public int getSeconds() { return calendar.get(Calendar.SECOND); }

   public int dayOfWeek() { return calendar.get(Calendar.DAY_OF_WEEK); }

   public String dayOfWeekString() { return DAY_OF_WEEK[dayOfWeek()-1]; }

   public Calendar toCalendar()
   {
      Calendar calendar = Calendar.getInstance();
      calendar.set(getYear(), 
                   getMonth(), 
                   getDate(), 
                   getHours(), 
                   getMinutes(), 
                   getSeconds());
      return calendar;
   }
   
   public Date toDate()
   {
      return calendar.getTime();
   }

   public String toDateString(String format)
   {
      SimpleDateFormat formater = new SimpleDateFormat (format);
      return formater.format(toDate());
   }

   public void yearForeward(int year)
   {
      for(int i=0; i<year; i++)
      {
         calendar.roll(Calendar.YEAR, 1);
      }
   }

   public void monthForeward(int month)
   {
      for(int i=0; i<month; i++)
      {
         if(11!=calendar.get(Calendar.MONTH))
            calendar.roll(Calendar.MONTH, 1);
         else
         {
            yearForeward(1);
            calendar.set(Calendar.MONTH, 0);
         }
      }
   }

   public void dayForeward(int day)
   {
      for(int i=0; i<day; i++)
      {
         if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)!=calendar.get(Calendar.DAY_OF_MONTH))
            calendar.roll(Calendar.DAY_OF_MONTH, 1);
         else
         {
            monthForeward(1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
         }
      }
   }

   public void hourForeward(int hour)
   {
      for(int i=0; i<hour; i++)
      {
         if(calendar.getActualMaximum(Calendar.HOUR_OF_DAY)!=calendar.get(Calendar.HOUR_OF_DAY))
            calendar.roll(Calendar.HOUR_OF_DAY, 1);
         else
         {
            dayForeward(1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
         }
      }
   } 
   
   public void minuteForeward(int minute)
   {
      for(int i=0; i<minute; i++)
      {
         if(calendar.getActualMaximum(Calendar.MINUTE)!=calendar.get(Calendar.MINUTE))
            calendar.roll(Calendar.MINUTE, 1);
         else
         {
            hourForeward(1);
            calendar.set(Calendar.MINUTE, 0);
         }
      }
   }
   
   public void yearBackward(int year)
   {
      for(int i=0; i<year; i++)
      {
         calendar.roll(Calendar.YEAR, -1);
      }
   }

   public void monthBackward(int month)
   {
      for(int i=0; i<month; i++)
      {
         if(0!=calendar.get(Calendar.MONTH))
            calendar.roll(Calendar.MONTH, -1);
         else
         {
            yearBackward(1);
            calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
         }
      }
   }

   public void dayBackward(int day)
   {
      for(int i=0; i<day; i++)
      {
         if(1!=calendar.get(Calendar.DAY_OF_MONTH))
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
         else
         {
            monthBackward(1);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
         }
      }
   }

   public static void oneDayForward(Calendar cal)
   {
      (new CalendarHelper(cal)).dayForeward(1);
   }

   public static void oneDayBackward(Calendar cal)
   {
      (new CalendarHelper(cal)).dayBackward(1);
   }
   
   public static int dayOfWeek(int year, int month, int day)
   {
      Calendar cal = Calendar.getInstance();
      cal.set(year, month, day, 0, 0, 0);
      return (new CalendarHelper(cal)).dayOfWeek();
   }

   public static String dayOfWeekString(int year, int month, int day)
   {
      return DAY_OF_WEEK[dayOfWeek(year, month, day)-1];
   }
   
   public static void main(String[] args)
   {
      //CalendarHelper cal = new CalendarHelper(Calendar.getInstance());
      CalendarHelper cal = new CalendarHelper("2007-04-15T16:17:00", "yyyy-MM-dd'T'HH:mm:ss");
      System.out.println(cal.toCalendar().getTime());
      cal.minuteForeward(1);
      System.out.println(cal.toCalendar().getTime());
      cal.minuteForeward(1);
      System.out.println(cal.toCalendar().getTime());
      cal.minuteForeward(1);
      System.out.println(cal.toCalendar().getTime());
      cal.minuteForeward(1);
      System.out.println(cal.toCalendar().getTime());
      //for(int i=0; i<900; i++)
      //{
       //  cal.minuteForeward(1);
       //  System.out.println("date:" + cal.toDate());
      //}
      
      //for(int i=0; i<10; i++)
      //{
      //   cal.dayForeward(1);
         //&System.out.println("date:" + cal.toDate());
      //}

   }
}