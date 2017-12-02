package org.yang.customized.gtf.services.inventoryManager.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.sql.Date;
import java.util.Calendar;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.customized.gtf.services.dataAccess.Karyotype;
import org.yang.customized.gtf.services.inventoryManager.InventoryManager;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.util.DateFormatter;
import org.yang.services.dataAccess.Data;
import java.util.HashSet;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author:Celina Yang
 * @version 1.0
 */

public class KaryotypeMasterTableBean extends InventoryServiceBean
{
   static final long serialVersionUID = 471329263917718901L;

   static public final String[] dateTypes = {"sentDate"};
   private String altPage = null;
   private Karyotype currentKaryotype = null;
   private Karyotype[] allKaryotypes = null;
   private HashSet selectedKaryotypeIds = null;

   /**
    *  form set and get methods
    */
   private String id ="";
   public void setId(String id) { this.id = id; }
   public String  getId(){return id;}
   
   private String page = "1";
   public void setPage(String page) { this.page = page; }
   public String getPage() { return page; }

   private String lastSortBy = null;
   private String sortBy = "projectName";
   public void setSortBy(String sortBy) { this.sortBy = sortBy; }
   public String getSortBy() { return sortBy; }

   private boolean isAcending = true;
   public boolean getIsAcending() { return isAcending; }

   private boolean isAllChecked = false;
   public boolean getIsAllChecked (){ return this.isAllChecked; }

   private String dateType = "sentDate";
   public String getDateType() {return dateType;}
   public void setDateType(String dateType){this.dateType = dateType;}

   private int availableKaryotypeSize =0;
   public int getAvailableKaryotypeSize(){return availableKaryotypeSize;}
   
   public KaryotypeMasterTableBean()
   {
      super();
      selectedKaryotypeIds = new HashSet();

   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();
      String[] areas = passport.getServiceAreas("InventoryManager");

      if(null==inventoryMgr)
         throw new Exception("Inventory manager is null.");
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      altPage = null;
   }

   protected String altPage()
   {
      System.out.println("[KaryotypeBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload";
      }
      else
         return altPage;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/
   /*
    * This method will query data by dateType
    */
   public Karyotype[] getAvailableKaryotypes() throws ProjectDataAccessException
   {
      try
      {
          loadKaryotypes();
          return allKaryotypes;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new Karyotype[0];
   }

   public String getUsername()
   {
      return passport.getUsername();
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   public Karyotype getCurrentKaryotype()
   {
      return currentKaryotype;
   }

   /****************************************
    *             All my actions           *
    ****************************************/
   
   public void clear()
   {
      allKaryotypes = null;
      altPage = null;
   }

   public void changePage() throws ProjectDataAccessException
   {
      loadKaryotypes();
      altPage = null;
   }
   
   public void enter() throws ProjectDataAccessException
   {
      loadKaryotypes();
      page = "1";
      removeControl("karyotypeMasterTable");
   }

   public void karyotypeTable() throws ProjectDataAccessException
   {
      altPage = "karyotypeTable";
   }

   public void loadKaryotypes() throws ProjectDataAccessException
   {
      try
      {
         allKaryotypes = inventoryMgr.geKaryotypesByDateType(dateType,getOneDayBeforeStartDate(),getOneDayAfterEndDate());
         setSelectedInfoForKaryotypes();
         if(allKaryotypes!=null)
            availableKaryotypeSize=allKaryotypes.length;
         System.out.println("[KaryotypeBean::loadKaryotypes]///----->availableKaryotypeSize="+availableKaryotypeSize);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void loadMasterTableData() throws ProjectDataAccessException
   {
      try
      {
         loadKaryotype();
         loadKaryotypes();
         removeControl("karyotypeMasterTable");
         altPage = "karyotypeMasterTable";
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void select() throws ProjectDataAccessException
   {
      if(null!=id&&!"".equals(id))
         selectedKaryotypeIds.add(id);
      //System.out.println("[KaryotypeBean::select]--id="+id);
      loadKaryotypes();
      altPage = null;
   }

   public void deselect() throws ProjectDataAccessException
   {
      //System.out.println("[KaryotypeBean::deselect]--id="+id);
      deselect(id);
      altPage = null;
   }

   public void selectAll() throws ProjectDataAccessException
   {
      for (int i=0 ; i<allKaryotypes.length;i++)
      {
          selectedKaryotypeIds.add(allKaryotypes[i].getId()+"");
      }
      isAllChecked = true;
      altPage = "karyotypeMasterTable";
   }

   public void deselectAll() throws ProjectDataAccessException
   {
 
      for (int i=0 ; i<allKaryotypes.length;i++)
      {
          selectedKaryotypeIds.remove(allKaryotypes[i].getId()+"");
      }
      isAllChecked = false;
      altPage = "karyotypeMasterTable";

   }

   public void deleteKaryotypes() throws ProjectDataAccessException
   {
       ArrayList temp = new ArrayList();
       try
       {
           inventoryMgr.removeKaryotypes(selectedKaryotypeIds);
           for(int i=0;i<allKaryotypes.length;i++)
           {
              if(!selectedKaryotypeIds.contains(allKaryotypes[i].getId()))
                  temp.add(allKaryotypes[i]);
           }
           allKaryotypes = (Karyotype[])(temp.toArray(new Karyotype[]{}));
           selectedKaryotypeIds.clear();
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }

       altPage = null;
   }

   /*****************************
    *     My private methods    *
    *****************************/

   private void deselect(String id) throws ProjectDataAccessException
   {
      //System.out.println("[KaryotypeBean]--deselect id="+id);
      if(null==id||"".equals(id))
         return;

      for (int i=0 ; i<allKaryotypes.length;i++)
      {
          selectedKaryotypeIds.remove(id);
      }
      loadKaryotypes();
   }

   private void setSelectedInfoForKaryotypes()
   {
        String recordid = "";
    	if(allKaryotypes==null)
    	   return;
        for(int i=0; i<allKaryotypes.length; i++)
        {
       	   recordid = allKaryotypes[i].getId()+"";
           
           Iterator it = selectedKaryotypeIds.iterator();
           if(selectedKaryotypeIds.contains(recordid))
              allKaryotypes[i].setIsSelected(true);
           else
              allKaryotypes[i].setIsSelected(false);
       }
       //System.out.println("[KaryotypeBean::setSelectedInfoForKaryotypes]--> selectedKaryotypeIds.size="+selectedKaryotypeIds.size());
   }
 
   public void sortMasterTable() throws ProjectDataAccessException
   {
      if(sortBy.equals(lastSortBy))
         isAcending = !isAcending;
      else
         isAcending = true;
      lastSortBy = sortBy;
      altPage = "karyotypeMasterTable";
   }

   /*****************************************************
    *   private method, may be moved to ProjectManager  *
    *****************************************************/

   private void loadKaryotype() throws ProjectDataAccessException
   {
      try
      {
         currentKaryotype = inventoryMgr.getKaryotype(id);

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public boolean getNeedToRemoveKaryotype()
   {
      return (selectedKaryotypeIds.size()>0);

   }

   public int getAllKaryotypesSize()
   {
       if(allKaryotypes==null||allKaryotypes.length==0)
          return 0;
       else
          return allKaryotypes.length;
   }

}