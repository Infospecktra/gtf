/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.Iterator;

public class DefaultDataProcessor implements DataProcessor
{
   public String process(HashMap userDataMap)
   {
      if(null==userDataMap)
         return "";
      int size = userDataMap.size();
      if(0==size)
         return "";
      Iterator it = userDataMap.values().iterator();
      StringBuffer datas = new StringBuffer();
      int count = 0;
      String data = null;
      while(it.hasNext())
      {
         data = ((String)it.next()).trim();
         if("".equals(data))
            continue;
         if(0==count)
            datas.append(data);
         else
            datas.append(",").append(data);
         count ++;
      }
      return datas.toString();
   }
}