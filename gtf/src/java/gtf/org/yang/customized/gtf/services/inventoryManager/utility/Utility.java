package org.yang.customized.gtf.services.inventoryManager.utility;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Celina
 */
public class Utility {
   
   static public String dateFormat(Date theDate)
   {
      String dd ="";
      if(theDate==null)
         return "n/a";
      try
      {
          dd = dateFormat(theDate,"yyyy-MM-dd");
      }
      catch(Exception e)
      {
	 e.printStackTrace();
      }
      return dd;
   }
/*
   static public String dateFormat(Date theDate,String formate)
   {
      String dd ="";
      if(theDate==null)
         return "n/a";
      try
      {
          SimpleDateFormat formater = new SimpleDateFormat(formate);
          dd = formater.format(theDate);
      }
      catch(Exception e)
      {
	 e.printStackTrace();
      }
      return dd;
   }
*/
   static public String dateFormat(Date theDate,String formate)
   {
      String dd ="";
      if(theDate==null)
         return "n/a";
      StringBuffer  strBuff = new StringBuffer(""); 
      try
      {
      	 if(!"".equals("MM/dd/yyyy"))
         {
             SimpleDateFormat formater = new SimpleDateFormat(formate);
             dd = formater.format(theDate);
         }	      	 
      	 else
      	 {
            strBuff.append(theDate.getMonth()+1+"").append("/")
                   .append(theDate.getDate()+"").append("/")
                   .append(theDate.getYear()+1900+"");
            dd=strBuff.toString();
         } 
      }
      catch(Exception e)
      {
	 e.printStackTrace();
      }
      return dd;
   }
   
   static public String[] parsingDateString(String token,String dateString)
   {
       //System.out.println("[Utility::parsingDateString]----->dateString="+dateString);
      StringTokenizer tokenizer = new StringTokenizer(dateString,token);
      String[] dateArray = new String[3]; 
      int i =0;
      try
      {
      	 if(dateString.indexOf(token)==-1) //if dateString contains no token ,then suppose dateString equals to yyyy data    
      	 {
      	    dateArray[2] = dateString;  	
      	 }	
      	 else 
      	 {
            while(tokenizer.hasMoreTokens())
            {
               dateArray[i] = tokenizer.nextToken(); 
       //System.out.println("[Utility::parsingDateString]----->dateArray["+i+"]="+dateArray[i]);
               i++;	
            }
         }   	   	
      }	
      catch(Exception e)
      {
         e.printStackTrace();	
      }
      return dateArray;
   }	  

   static public String[] parsingString(String token,String parsedString)
   {
       //System.out.println("[Utility::parsingString]----->parsedString="+parsedString);
      StringTokenizer tokenizer = new StringTokenizer(parsedString,token);
      String[] dateArray = new String[3]; 
      ArrayList temp = new ArrayList();
      int i =0;
      try
      {
         while(tokenizer.hasMoreTokens())
         {
            temp.add(tokenizer.nextToken()); 
       //System.out.println("[Utility::parsingString]----->dateArray["+i+"]="+dateArray[i]);
              	
         }
         dateArray = (String[])temp.toArray(new String[]{});
      }	
      catch(Exception e)
      {
         e.printStackTrace();	
      }
      return dateArray;
   }	  
   
   static public Date stringDateConvertToDate(String y,String m,String d)
   {

       //System.out.println("[Utility::stringDateConvertToDate]----->y="+y+"-m="+m+"-d="+d);
      
      if("----".equals(y)||"--".equals(m)||"--".equals(d))
         return null;
         
      Date dd = null;
      try
      {
         Calendar cal = Calendar.getInstance();
         cal.set(Integer.parseInt(y),Integer.parseInt(m)-1,Integer.parseInt(d));
         dd = new Date(cal.getTime().getTime());

      }
      catch(Exception e)
      {
	 e.printStackTrace();
      }
      return dd;
   }

   static public String[] getYMDStringArray(Date theDate)
   {
      int int_date = 0;
      String[] myDate = new String[0];
      if(theDate==null)
         return new String[]{"----","--","--"};
      try
      {
         String [] d = {theDate.getYear()+1900+"",
                        theDate.getMonth()+1+"",
                        theDate.getDate()+""
         };
         myDate = d ;
      }
      catch(Exception e)
      {
	 e.printStackTrace();
      }
      return myDate;
   }

   static public String NAFormat(String str)
   {

      if(str==null)
         return "n/a";
      str=str.trim();
      if("".equals(str))
         return "n/a";
      return str;
   }

   
}
