package org.yang.web.services.accountManage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.Permission;
import org.yang.services.dbService.IDGenerator;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.services.servicemgr.Area;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import org.yang.services.servicemgr.Service;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class GroupBean extends AccountBean
{
   static final long serialVersionUID = 4711296382979764917L;

   /**
    *  form set and get methods
    */
   private String id = "";
   public void setId(String s) { id=s; }
   //public String getId() { return id; }

   private String name = "";
   public void setName(String s) { name=s; }
   //public String getName() { return name; }

   private String description = "";
   public void setDescription(String s) { description=s; }
   //public String getDescription() { return description; }

   private String[] selectedmembers = null;
   public void setSelectedmembers(String[] selectedmembers) { this.selectedmembers = selectedmembers; }

   private String[] unselectedmembers = null;
   public void setUnselectedmembers(String[] unselectedmembers) { this.unselectedmembers = unselectedmembers; }

   private String[] _tempUsers = null;
   public void setUsers(String[] ary)
   {
      if (ary==null)
         _tempUsers = new String[0];
      else
         _tempUsers = ary;
   }

   private String[] _tempServices = null;
   public void setServ(String[] ary)
   {
      if (ary==null)
         _tempServices = new String[0];
      else
         _tempServices = ary;
   }

   private Group currentGroup = null;

   public GroupBean()
   {
      super();
   }

   /***********************************
    * methods for group info builders *
    ***********************************/

   public Group getCurrentGroup()
   {
      return currentGroup;
   }

   /***********************************
    * methods for group info builders *
    ***********************************/

   public Collection getUsers(String id) throws AccountDataAccessException
   {
      if (userMgr.getUsersByGID(id)==null) return new ArrayList();
      else return userMgr.getUsersByGID(id);
   }

   /********************************************
    * methods for group user selector builders *
    ********************************************/

   public User[] getNonmembers()
   {
      try
      {
         ArrayList ary = new ArrayList();

         Iterator iAll = userMgr.getAllUsers().iterator();
         Collection selectedUsers = userMgr.getUsersByGID(currentGroup.getID());
         User user = null;
         while (iAll.hasNext())
         {
            user = (User)iAll.next();
            if(!selectedUsers.contains(user))
            {
               ary.add(user);
            }
         }
         return (User[])ary.toArray(new User[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new User[0];
   }

   public User[] getMembers()
   {
      try
      {
         ArrayList ary = new ArrayList();
         Iterator iAll = userMgr.getUsersByGID(currentGroup.getID()).iterator();
         while (iAll.hasNext())
         {
            ary.add(iAll.next());
         }
         return (User[])ary.toArray(new User[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new User[0];
   }

   /********************************************
    * methods for group user selector builders *
    ********************************************/

   public Collection getAllGroups() throws AccountDataAccessException
   {
      if (userMgr.getAllGroups()==null) return new ArrayList();
      else return userMgr.getAllGroups();
   }

   public ServiceDescriptor[] getAllServiceDescriptors() throws AccountDataAccessException
   {
      return passport.getAllServiceDescriptors();
   }

   public String[] getAvailableServiceNames() throws AccountDataAccessException
   {
      Collection c = userMgr.getResourceIDSByGID(currentGroup.getID());
      if (c==null)
        return new String[0];
      else
        return (String[])c.toArray(new String[]{});
   }

   public Collection getServices(String gid) throws AccountDataAccessException
   {
      Collection c = userMgr.getResourceIDSByGID(gid);
      if (c==null) return new ArrayList();
      else return c;
   }

   public Collection getServices() throws AccountDataAccessException
   {
      return getServices(currentGroup.getID());
   }

   public Resource getGroupService(String gid,String sid) throws AccountDataAccessException
   {
      return userMgr.getResourceByGIDSID(gid,sid);
   }

   public Resource getGroupService(String sid) throws AccountDataAccessException
   {
      return getGroupService(currentGroup.getID(), sid);
   }

   public Map getAllAreasMap(String sid) throws AccountDataAccessException
   {
      HashMap map = new HashMap();
      Area[] allAreas = null;
      ServiceDescriptor[] services = passport.getAvailableServiceDescriptors();
      for(int i=0; i<services.length; i++)
      {
         if(sid.equals(services[i].getName()))
         {
            allAreas = passport.loadService(services[i].getName()).allAreas();
            for(int j=0; j<allAreas.length; j++)
            {
               map.put(allAreas[j].getName(), allAreas[j]);
            }
            break;
         }
      }
      return map;
   }

   public boolean hasArea(String sid) throws AccountDataAccessException
   {
      Area[] temp = null;
      Service service = null;
      if(null!=(service=passport.loadService(sid)))
      {
         temp = service.allAreas();
      }
      return (!(null==temp)&&(0!=temp.length));
   }

   public String[] getOperations(String sid, String ar) throws AccountDataAccessException
   {
      Resource s = userMgr.getResourceByGIDSID(currentGroup.getID(), sid);
      return s.getOperationsInArea(ar);
   }

   public Set getOperationsNameSet(String sid, String ar) throws AccountDataAccessException
   {
      String[] ops = this.getOperations(sid, ar);
      HashSet temp = new HashSet();
      for(int i=0; i<ops.length; i++)
      {
         temp.add(ops[i]);
      }
      return temp;
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

   protected void beforeSetParameters()
   {
      _tempUsers = null;
   }

   protected String altPage()
   {
      //&System.out.println("[GroupBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         //&System.out.println("[GroupBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return null;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void clear()
   {
      currentGroup = new Group();
   }

   public void create() throws AccountDataAccessException
   {
      Group g = userMgr.getGroup(currentGroup.getID());
      if (g==null)
      {
         g = new Group();
         g.setID(name.toUpperCase());
         g.setName(name);
         g.setDescription(description);

         //*** save result to database
         if (userMgr.createGroup(g))
         {
            setMsg("Create group sucessful.");
            isReload = true;
         }
         else
         {
            throw new AccountDataAccessException("Unable to create group.");
         }
      }
      else
      {
         throw new AccountDataAccessException("Unable to create group.");
      }
   }

   public void load() throws AccountDataAccessException
   {
      currentGroup = userMgr.getGroup(id);
      if (null==currentGroup)
      {
         throw new AccountDataAccessException("Unable to load group.");
      }
   }

   public void updateInfo() throws AccountDataAccessException
   {
      if (currentGroup!=null)
      {
         currentGroup.setName(name);
         currentGroup.setDescription(description);

         //*** save result to database
         if (userMgr.updateGroup(currentGroup))
         {
            setMsg("Update group sucessful.");
         }
         else
         {
            throw new AccountDataAccessException("Unable to update group.");
         }
      }
      else
      {
         throw new AccountDataAccessException("Unable to update an uninitialized group.");
      }
   }

   public void updateServices() throws AccountDataAccessException
   {
      isReload = true;
      if (_tempServices!=null)
      {
         //** 1: remove service
         Collection c = getServices();
         Iterator i = c.iterator();
         while (i.hasNext())
         {
            boolean del = true;
            String s = (String)i.next();
            //System.out.println("[GroupBean::updateServices]---------->service="+s);
            for (int k=0; k<_tempServices.length; k++)
            {
            //System.out.println("[GroupBean::updateServices]**---------->_tempServices["+k+"]="+_tempServices[k]);
               if (s.equals(_tempServices[k]))
                  del = false;
            }
            if (del)
               userMgr.removeResourceFromGroup(currentGroup.getID(),s);
         }

         //** create service
         for (int k=0; k<_tempServices.length; k++)
         {
            c = getServices();
            i = c.iterator();
            boolean crt = true;
            while (i.hasNext())
            {
               String s = (String)i.next();
               if (s.equals(_tempServices[k]))
               crt = false;
           }
           if (crt)
           {
              Permission p = new Permission();
              p.setId(IDGenerator.getUniqueID(currentGroup.getID()+"gFm"));
              p.setGroupId(currentGroup.getID());
              p.setServiceId(_tempServices[k]);
              p.setAreasConcat("");
              p.setOperationsConcat("");
              userMgr.addPermission(p);
           }
         }
         //** 1: remove service
      }
   }

   public void removeGroup() throws AccountDataAccessException
   {
      Group g = userMgr.getGroup(currentGroup.getID());
      if (g!=null)
      {
         userMgr.removeGroup(currentGroup.getID());
         isReload = true;
      }
      else
      {
         throw new AccountDataAccessException("Unable to remove group.");
      }
   }

   public void selectMembers() throws AccountDataAccessException
   {
      isReload = true;
      if (selectedmembers!=null)
      {
         for (int i=0; i<selectedmembers.length; i++)
            userMgr.addUserIntoGroup(selectedmembers[i], currentGroup.getID());
      }
   }

   public void deselectMembers() throws AccountDataAccessException
   {
      isReload = true;
      if (unselectedmembers!=null)
      {
         for (int i=0; i<unselectedmembers.length; i++)
            userMgr.removeUserFromGroup(unselectedmembers[i], currentGroup.getID());
      }
   }
}