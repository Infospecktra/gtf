/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.Iterator;
import org.yang.util.SMUtility;

public class TableDataSOBProcessor implements DataProcessor
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
         if(null!=dataArray[0])
         {
            dataArray = SMUtility.splitByToken(dataArray[0], ",", true);
            out.append(dataArray[dataArray.length-1]);
         }
         out.append(",");
      }
      return out.toString();
   }
}