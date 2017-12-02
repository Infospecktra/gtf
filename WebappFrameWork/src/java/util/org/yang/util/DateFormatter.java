/* Generated by Together */

package org.yang.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFormatter
{
   public static String getDateTimeString(Date date)
   {
      return getDateTimeString(date, "yyyyMMddHHmmss");
   }

   public static String getDateTimeString(Date date, String format)
   {
      SimpleDateFormat formater
            = new SimpleDateFormat (format);
      return formater.format(date);
   }

   public static String convertDateTimeString(String datetime, String newFormatText)
   {
      return convertDateTimeString(datetime, "yyyyMMddHHmmss", newFormatText);
   }

   public static String convertDateTimeString(String datetime, String oldFormatText, String newFormatText)
   {
      try
      {
         SimpleDateFormat newFormat
               = new SimpleDateFormat (newFormatText);
         SimpleDateFormat oldFormat
               = new SimpleDateFormat (oldFormatText);
         return newFormat.format(oldFormat.parse(datetime));
      }
      catch(Exception e)
      {
         return datetime;
      }
   }
}