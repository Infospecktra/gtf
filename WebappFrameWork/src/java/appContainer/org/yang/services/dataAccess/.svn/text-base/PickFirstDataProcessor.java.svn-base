/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.Iterator;

public class PickFirstDataProcessor implements DataProcessor
{
   public String process(HashMap userDataMap)
   {
      if(null==userDataMap)
         return "";
      int size = userDataMap.size();
      if(0==size)
         return "";
      Iterator it = userDataMap.values().iterator();
      String data = null;
      while(it.hasNext())
      {
         data = ((String)it.next()).trim();
         if("".equals(data))
            continue;
         return data;
      }
      return "";
   }
}