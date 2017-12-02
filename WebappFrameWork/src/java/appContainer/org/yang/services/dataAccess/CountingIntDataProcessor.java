/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.Iterator;
import org.yang.util.SMUtility;

public class CountingIntDataProcessor implements DataProcessor
{
   public String process(HashMap userDataMap)  
   {
      if(null==userDataMap)
         return "";
      int size = userDataMap.size();
      if(0==size)
         return "";
      Iterator it = userDataMap.values().iterator();
      int count = -1;
      String datas = null;
      String[] dataArray = null;
      int data = 0;
      while(it.hasNext())
      {
         datas = ((String)it.next()).trim();
         if("".equals(datas))
            continue;
         if(-1==count)
            count = 0;
         dataArray = SMUtility.splitByToken(datas, ",/", false);
         try
         {	
            for(int i=0; i<dataArray.length; i++)
            {
               if(null!=dataArray[i])
               {
                  try
                  {
                     data = Integer.parseInt(dataArray[i]);	
                     //data = caculateData(dataArray[i]);
               	  }
                  catch(Exception e)
                  {
               	     System.out.println("[CountingDataProcessor.java]---data="+dataArray[i]); 
                     return "data error";
                  }
               }
               count += data;
 	    }
         }
         catch(Exception e)
         {
            e.printStackTrace();	
         }
      }
      if(-1==count)
        return "";
      return count + "";
   }
   
   private int caculateData(String data)
   {
       int value = 0;	
       String[] dataArray = null;  	
       
       try
       {
          if (data.indexOf("-")!=-1)
          {
             dataArray = SMUtility.splitByToken(data, "-", false);
             value = Integer.parseInt(dataArray[0]) - Integer.parseInt(dataArray[1]);
          }	
          else if (data.indexOf("+")!=-1)
          {
             dataArray = SMUtility.splitByToken(data, "+", false);
             value = Integer.parseInt(dataArray[0]) + Integer.parseInt(dataArray[1]);
          }			
          else
             value = Integer.parseInt(data);
       }
       catch(Exception e)
       {
           e.printStackTrace();	
       }		   	
       
       return value;   	
   }	
}