package org.yang.web.controller;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import org.yang.util.ExceptionBroadcast;

public class RequestParameters
{
   protected Hashtable parameters = null;

   /**
    * Call the constructor and set the id which represents this content.
    *
    * @param      id     the String indicates identification of this content.
    */
   //
   public RequestParameters(String query)
   {
      parameters = parseQueryString(query);
   }

   public Enumeration getAllNames()
   {
      return parameters.keys();
   }


   public void clear()
   {
      parameters.clear();
   }

   public void set(String aName, String aValue)
   {
      if(aName != null && aValue != null)
      {
         String[] temp = new String[1];
         temp[0] = aValue;
         parameters.put(aName,temp);
      }
   }

   public void setAll(String aName, String[] aValues)
   {
      if(aName != null && aValues != null)
         parameters.put(aName,aValues);
   }


   public String get(String aName)
   {
      Object obj = parameters.get(aName);
      
      if(obj==null)
         return null;

      String[] temp = (String[])obj;
      if(temp != null && temp.length > 0)
         return temp[0];
      return null;
   }


   public String[] getAll(String aName)
   {
      return (String[])parameters.get(aName);
   }


   public void remove(String aName)
   {
      parameters.remove(aName);
   }

   public static Hashtable parseQueryString(String s)
   {
      Hashtable ht = new Hashtable();
      
      if(null==s)
         return ht;
      
      String[] valArray = null;
      if(s == null)
         return ht;
      StringBuffer sb = new StringBuffer();
      StringTokenizer st = new StringTokenizer(s,"&");
      while(st.hasMoreTokens())
      {
         try
         {
            String pair = (String)st.nextToken();
            int pos = pair.indexOf('=');
            if(pos == -1)
               continue;
            String key = URLDecoder.decode(pair.substring(0,pos));
            String val = URLDecoder.decode(pair.substring(pos + 1,pair.length()));
            if(ht.containsKey(key))
            {
               String[] oldVals = (String[])ht.get(key);
               valArray = new String[oldVals.length + 1];
               for(int i = 0;i < oldVals.length;i++)
                  valArray[i] = oldVals[i];
               valArray[oldVals.length] = val;
            }
            else
            {
               valArray = new String[1];
               valArray[0] = val;
            }
            ht.put(key,valArray);
         }
         catch(Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return ht;
   }   
}
//
