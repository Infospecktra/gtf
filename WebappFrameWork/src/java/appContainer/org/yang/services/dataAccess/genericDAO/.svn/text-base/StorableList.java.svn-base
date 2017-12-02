/* by Steven Yang */

package org.yang.services.dataAccess.genericDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StorableList
{
   private ArrayList list = new ArrayList();

   public void add(Storable storable)
   {
      list.add(storable);
   }

   public void addAll(StorableList storableList)
   {
      for(int i=0; i<storableList.size(); i++)
      {
         list.add(storableList.get(i));
      }
   }

   public Storable get(int index)
   {
      return (Storable)list.get(index);
   }
   
   public void remove(int index)
   {
      list.remove(index);	
   }   

   public int size()
   {
      return list.size();
   }

   public void clear()
   {
      list.clear();
   }

   public void sort()
   {
      Collections.sort(list);
   }
   
   public void sort(Comparator comparator)
   {
         Collections.sort(list, comparator);
   }

   public Object[] toArray(Object[] array)
   {
      return list.toArray(array);
   }
}