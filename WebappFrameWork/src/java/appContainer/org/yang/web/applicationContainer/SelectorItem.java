/* Generated by Together */

package org.yang.web.applicationContainer;

public class SelectorItem implements Comparable
{
   private String id = "";
   public void setId(String id) { this.id = id; }
   public String getId() { return id; }

   private String value = "";
   public void setValue(String value) { this.value = value; }
   public String getValue() { return value; }

   public int compareTo(Object obj)
   {
      if(null==obj||null==id)
         return 0;
      return id.compareTo(((SelectorItem)obj).id);
   }
}