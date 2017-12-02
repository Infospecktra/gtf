/* Generated by Together */

package org.yang.services.servicemgr;
import java.util.Properties;
import java.lang.reflect.Constructor;
import java.util.Iterator;

public class DefaultServiceWrapper extends ServiceWrapper
{
   static final long serialVersionUID = 4711296382979764903L;

   protected Properties prop = null;

   public void destroy() {}

   public Area[] allAreas()
   {
      //&System.out.println("[DefaultServiceWrapper::allAreas] Shall implement this method : class = " + this.getClass().getName());
      return new Area[]{};
   }

   public boolean containArea(String areaname)
   {
      //&System.out.println("[DefaultServiceWrapper::containArea] Shall implement this method : class = " + this.getClass().getName());
      return false;
   }

   public Service createServiceInstance(String domain)
   {
      Object obj = null;
      try
      {
         Class[]  cl = {domain.getClass()};
         Object[] ob = {domain};
          
         Constructor c = Class.forName(this.getClassName()).getConstructor(cl);
         Service service = (Service)c.newInstance(ob);
         return service;
      }
      catch(Exception e)
      {
         //e.printStackTrace();
         System.out.println("[DefaultServiceWrapper::createServiceInstance] Unable to load service : " + e.getMessage());
         return null;
      }
   }
}