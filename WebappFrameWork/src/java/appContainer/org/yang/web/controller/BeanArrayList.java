package org.yang.web.controller;

import java.util.ArrayList;

public class BeanArrayList
{
   ArrayList myBeans = null;
   
   public BeanArrayList()
   {
      myBeans = new ArrayList();	
   }
   
   public boolean add(BaseBean bean)
   {
      if(myBeans.contains(bean))
         return false;
      return myBeans.add(bean);
   }
   
   public BaseBean get(int num)
   {
      return (BaseBean)myBeans.get(num);
   }
   
   public int size()
   {
      return myBeans.size();
   }
}