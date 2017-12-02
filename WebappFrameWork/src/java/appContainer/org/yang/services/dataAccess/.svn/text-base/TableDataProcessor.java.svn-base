/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.Iterator;
import org.yang.util.SMUtility;

public class TableDataProcessor implements DataProcessor
{
   public String process(HashMap userDataMap)
   {
      if(null==userDataMap)
         return "";
      int size = userDataMap.size();
      if(0==size)
         return "";
      Iterator it = userDataMap.values().iterator();
      StringBuffer out = new StringBuffer();
      String datas = null;
      String[] dataArray = null;
      int data = 0;
      while(it.hasNext())
      {
         datas = ((String)it.next()).trim();
         if("".equals(datas))
            continue;
         dataArray = SMUtility.splitByToken(datas, "!", false);
         for(int i=0; i<dataArray.length; i++)
         {
            if(null!=dataArray[i])
            {
               out.append(dataArray[i]);
               if(i<dataArray.length-1)
                  out.append("/");
            }
         }
         out.append("/");
      }
      return out.toString();
   }
}