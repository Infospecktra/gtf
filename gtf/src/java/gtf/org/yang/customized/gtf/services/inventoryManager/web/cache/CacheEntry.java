package org.yang.customized.gtf.services.inventoryManager.web.cache;

public class CacheEntry
{
   public CacheEntry(){}
  
   private long dataAccessingTime = 0;
   public long getDataAccessingTime(){return dataAccessingTime;}   
   public void setDataAccessingTime(long dataAccessingTime){this.dataAccessingTime = dataAccessingTime;}
   
   private Object element = null;
   public void setElement(Object element ){this.element = element;}
   public Object getElement(){return element;}
   
}