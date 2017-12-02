/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;

public class NOPDataProcessor implements DataProcessor
{
   public String process(HashMap userDataMap)
   {
      return "click for detail";
   }
}