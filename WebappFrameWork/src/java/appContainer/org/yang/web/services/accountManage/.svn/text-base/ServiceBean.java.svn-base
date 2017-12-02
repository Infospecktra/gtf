package org.yang.web.services.accountManage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Resource;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.Permission;
import org.yang.services.dbService.IDGenerator;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.services.servicemgr.Area;
import org.yang.services.servicemgr.Operation;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      SCRM
 * @author Jason Wang
 * @version 1.0
 */

public class ServiceBean extends AccountBean
{
   static final long serialVersionUID = 4711296382979764918L;

   /**
    *  form set and get methods
    */
   private String _sid = "";
   public void setSid(String s) { _sid=s; }
   public String getSid() { return _sid; }

   private String _gid = "";
   public void setGid(String s) { _gid=s; }
   public String getGid() { return _gid; }

   private String[] selectedareas = null;
   public void setSelectedareas(String[] selectedareas) { this.selectedareas = selectedareas; }

   private String[] unselectedareas = null;
   public void setUnselectedareas(String[] unselectedareas) { this.unselectedareas = unselectedareas; }

   private HashMap operationMap = null;
   public void setOperationMap(HashMap operationMap)
   {
      this.operationMap = operationMap;
   }

   private String _email = "";

   public ServiceBean() 
   {
      super();
   }

   public String getName(String id) throws AccountDataAccessException
   {
      return null;
   }

   /**************************************************
    *  get current permission info from user manager *
    **************************************************/

   public String[] getOperations(String ar) throws AccountDataAccessException
   {
      Resource s = userMgr.getResourceByGIDSID(_gid,_sid);
      return s.getOperationsInArea(ar);
   }

   public Set getOperationsNameSet(String ar) throws AccountDataAccessException
   {
      String[] ops = this.getOperations(ar);
      HashSet temp = new HashSet();
      for(int i=0; i<ops.length; i++)
      {
         temp.add(ops[i]);
      }
      return temp;
   }

   public String[] getAreas(String gid, String sid) throws AccountDataAccessException
   {
      Resource s = userMgr.getResourceByGIDSID(gid,sid);
      if (s==null) 
      {
         return new String[0];
      }
      else 
      {
         return s.getAllAreas();
      }
   }

   public String[] getAreas() throws AccountDataAccessException
   {
      return getAreas(_gid,_sid);
   }
   public SelectorItem[] allUnselctedItems()
   {
      try
      {
         ArrayList ary = new ArrayList();
         String[] allAreas = getAllAreaNames();
         String[] areas = userMgr.getResourceByGIDSID(_gid, _sid).getAllAreas();

         SelectorItem item = null;
         for(int i=0; i<allAreas.length; i++)
         {
            if(!contain(areas, allAreas[i]))
            {
               item = new SelectorItem();
               item.setId(allAreas[i]);
               item.setValue(allAreas[i]);
               ary.add(item);
            }
         }
         return (SelectorItem[])ary.toArray(new SelectorItem[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new SelectorItem[0];
   }

   public SelectorItem[] allSelectedItems()
   {
      try
      {
         ArrayList ary = new ArrayList();
         Resource service = userMgr.getResourceByGIDSID(_gid, _sid);
         String[] areas = service.getAllAreas();
         SelectorItem item = null;
         for(int i=0; i<areas.length; i++)
         {
            item = new SelectorItem();
            item.setId(areas[i]);
            item.setValue(areas[i]);
            ary.add(item);
         }
         return (SelectorItem[])ary.toArray(new SelectorItem[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new SelectorItem[0];
   }

   /**********************************************
    *  get all service info from service manager *
    **********************************************/

   public String[] getAllAreaNames() throws AccountDataAccessException
   {
      ServiceDescriptor[] services = passport.getAvailableServiceDescriptors();
      ArrayList temp = new ArrayList();
      Area[] allAreas = null;
      for(int i=0; i<services.length; i++)
      {
         if(_sid.equals(services[i].getName()))
         {
            allAreas = passport.loadService(services[i].getName()).allAreas();
            for(int j=0; j<allAreas.length; j++)
            {
               temp.add(allAreas[j].getName());
            }
            break;
         }
      }
      return (String[])temp.toArray(new String[]{});
   }

   public Area[] getAllAreas() throws AccountDataAccessException
   {
      ServiceDescriptor[] services = passport.getAllServiceDescriptors();//passport.getAvailableServiceDescriptors();
      for(int i=0; i<services.length; i++)
      {
         if(_sid.equals(services[i].getName()))
         {
            return passport.loadService(services[i].getName()).allAreas();
         }
      }
      return new Area[0];
   }

   public Map getAllAreasMap() throws AccountDataAccessException
   {
      HashMap map = new HashMap();
      Area[] allAreas = null;
      ServiceDescriptor[] services = passport.getAvailableServiceDescriptors();
      for(int i=0; i<services.length; i++)
      {
        //System.out.println("????????????????? service:" + services[i].getName() + ", current service:" + _sid);
         if(_sid.equals(services[i].getName()))
         {
            allAreas = passport.loadService(services[i].getName()).allAreas();
            for(int j=0; j<allAreas.length; j++)
            {
               System.out.println("??????????????????????? areas:" + allAreas[j].getName()); //please don't hash 
               map.put(allAreas[j].getName(), allAreas[j]);
            }
            break;
         }
      }
      return map;
   }


   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==userMgr)
         throw new Exception("User manager is null.");
   }

   public void destroy()
   {}

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load() throws AccountDataAccessException {}

   public void selectAreas() throws AccountDataAccessException
   {
      if (selectedareas!=null)
      {
         for (int i=0; i<selectedareas.length; i++)
         {
            Permission p = new Permission();
            p.setId(IDGenerator.getUniqueID("servFm_"));
            p.setGroupId(_gid);
            p.setServiceId(_sid);
            p.setAreasConcat(selectedareas[i]);
            p.setOperationsConcat(getAllOperationsConcat(_sid, selectedareas[i]));
            userMgr.addPermission(p);
         }
      }
   }

   public void deselectAreas() throws AccountDataAccessException
   {
      //&System.out.println("removeAreas()");
      if (unselectedareas!=null)
      {
         for (int i=0; i<unselectedareas.length; i++)
         {
            ///////// //&System.out.println("_tempAreas[i]"+_tempAreas[i]);
            userMgr.removeAreasByGIDSIDAREA(_gid, _sid, unselectedareas[i]);
         }
      }
   }

   public boolean updateOperations() throws AccountDataAccessException
   {
      if (null!=operationMap)
      {
         Iterator areaIt = operationMap.keySet().iterator();
         while(areaIt.hasNext())
         { //** each element is an string array
            String areaName = (String)areaIt.next();
            String[] opers = (String[])operationMap.get(areaName);

            if (opers.length>0) 
            {
               String operName = "";
               for (int j=0; j<opers.length; j++) 
               {
                  operName += opers[j] + ",";
               }
               userMgr.updateOperationByGIDSIDAREA(_gid,_sid,areaName,operName);
            }
         }
         //marked by
         return true;
      }
      return false;
   }

   /***********************
    *  my private methods *
    ***********************/

   private String getAllOperationsConcat(String service, String area)
   {
      Area[] allAreas = passport.loadService(service).allAreas();
      //&System.out.println("[ServiceBean::getAllOperationsConcat] All Areas :" + allAreas);
      for(int i=0; i<allAreas.length; i++)
      {
         //&System.out.println("[ServiceBean::getAllOperationsConcat] Area :" + allAreas[i].getName());
         if(allAreas[i].getName().equals(area))
         {
            Operation[] ops = allAreas[i].getAllOperations();
            StringBuffer concat = new StringBuffer();
            for(int j=0; j<ops.length; j++)
            {
               concat.append(ops[j].getName()).append(",");
            }
            //&System.out.println("[ServiceBean::getAllOperationsConcat] Operations Concat :" + concat.toString());
            return concat.toString();
         }
      }
      return "";
   }

   private boolean contain(String[] servs, String targetServ)
   {
      for (int i=0; i<servs.length; i++)
      {
         if(targetServ.equals(servs[i]))
            return true;
      }
      return false;
   }
}