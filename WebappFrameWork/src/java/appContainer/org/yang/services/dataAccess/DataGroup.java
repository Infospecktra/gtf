/* Generated by Together */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.yang.util.SMUtility;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

/*
dataName1=aaa|dataName2=bbb|dataName3=ccc
*/

public class DataGroup implements Serializable, Comparable
{
   private String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private String displayName = null;
   public void setDisplayName(String displayName) { this.displayName = displayName; }
   public String getDisplayName() { return displayName; }

   private int displayOrder = 1000;
   public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
   public int getDisplayOrder() { return displayOrder; }

   private HashMap dataMap  = null;

   public DataGroup()
   {
      dataMap = new HashMap();
   }

   public Set getAllOwnerNames()
   {
      HashSet set = new HashSet();
      Iterator it = dataMap.values().iterator();
      while(it.hasNext())
      {
         set.addAll(((Data)it.next()).getAllOwnerNames());
      }

      return set;
   }

   public void setData(String name, Data data)
   {
      dataMap.put(name, data);
   }

   public Data getData(String name)
   {
      return (Data)dataMap.get(name);
   }

   public Data[] getDatas()
   {
      ArrayList temp = new ArrayList();
      Iterator it = dataMap.values().iterator();
      while(it.hasNext())
      {
         temp.add(it.next());
      }
      Collections.sort(temp);
      return (Data[])temp.toArray(new Data[]{});
   }

   public Collection allDatas()
   {
      return dataMap.values();
   }

   //public HashMap getDatasMap() throws DataUnavailableException
   //{
   //   return getDatasMap(Data.ALL_USER);
   //}

   public HashMap getDatasMap(String ownername) throws DataUnavailableException
   {
      HashMap temp = new HashMap();
      Iterator it = allDatas().iterator();
      Data data = null;
      String value = null;
      while(it.hasNext())
      {
         data = (Data)it.next();
         value = data.getValue(ownername);
         if(null==value)
            value = "";

         temp.put(data.getName(), value);
      }
      return temp;
   }

   public void setDatasMap(HashMap datasMap) throws DataUnavailableException
   {
      setDatasMap(Data.ANONYMOUS, dataMap);
   }

   public void setDatasMap(String ownername, HashMap datasMap) throws DataUnavailableException
   {
System.out.println("=============================================================in");
      if(null==dataMap)
         throw new DataUnavailableException("Input data map can not be null.");

      Iterator it = allDatas().iterator();
      Data data = null;
      Object valueObj = null;
      while(it.hasNext())
      {
         data = (Data)it.next();
         valueObj = datasMap.get(data.getName());
         System.out.println("data name = " + data.getName() + ", value = " + valueObj);
         if(null!=valueObj)
         {
            try
            {
               data.setValue(ownername, data.getDisplayer().rebuildData(data, valueObj));
            }
            catch(Exception e)
            {
               System.out.println("Unable to set data : " + e.getMessage());	
            }
         }
         else
            System.out.println("[DataGroup::setDatasMap] Got a null data, skip it. data nname = " + data.getName());
      }
System.out.println("=============================================================out");
   }

   public int size()
   {
      return dataMap.size();
   }

   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      Iterator it = dataMap.keySet().iterator();
      String key = null;
      String val = null;
      int count = 0;
      Data data = null;
      while(it.hasNext())
      {
         key = (String)it.next();
         data = (Data)dataMap.get(key);
         if(null==data)
            continue;
         val = data.getValue();
         if(null==val)
            val="";
         if(0==count)
            buffer.append(key).append("=").append(val);
         else
            buffer.append("|").append(key).append("=").append(val);
         count++;
      }

      return buffer.toString();
   }

   public void loadFromDataArray(Data[] datas)
   {
      String key = null;
      for(int i=0; i<datas.length; i++)
      {
         key = datas[i].getName();
         Data data = null;
         if(null==(data=(Data)dataMap.get(key)))
            continue;
         data.loadValueFrom(datas[i]);
      }
   }

   public void loadFromString(String datas)
   {
      String[] temp = SMUtility.splitByToken(datas, "|", false);
      for(int i=0; i<temp.length; i++)
      {
         int index = temp[i].indexOf("=");
         if(-1==index)
            continue;
         String key = temp[i].substring(0, index);
         String val = temp[i].substring(index+1);
         Data data = (Data)dataMap.get(key);
         if(null==data)
            continue;
         data.setValue(val);
      }
   }

   public int compareTo(Object obj)
   {
      if(null==obj||!(obj instanceof DataGroup))
         return 0;
      return (displayOrder-((DataGroup)obj).displayOrder);
   }
}