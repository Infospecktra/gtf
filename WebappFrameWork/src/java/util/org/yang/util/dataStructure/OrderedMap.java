/* by Steven Yang */

package org.yang.util.dataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;

public class OrderedMap extends HashMap
{
   public Collection keys()
   {
      Iterator it = this.keySet().iterator();
      ArrayList list = new ArrayList();
      while(it.hasNext())
      {
         list.add(it.next());
      }

      return list;
   }

   public Collection values()
   {
      ArrayList keys = (ArrayList)this.keys();
      Collections.sort(keys);
      Iterator it = keys.iterator();
      ArrayList list = new ArrayList();
      while(it.hasNext())
      {
         list.add(this.get(it.next()));
      }

      return list;
   }
}