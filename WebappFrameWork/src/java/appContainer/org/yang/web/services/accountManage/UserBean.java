package org.yang.web.services.accountManage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.yang.web.controller.GenericBean;
import org.yang.services.accountmgr.UserManager;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.Resource;
import org.yang.services.accountmgr.User;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import org.yang.web.applicationContainer.SelectorItem;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.servicemgr.ServiceDescriptor;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class UserBean extends AccountBean
{
   static final long serialVersionUID = 4711296382979764919L;

   /**
    *  form set and get methods
    */

   private String _id = "";
   public void setId(String s) { _id=s; }
   public String getId() 
   { 
	   //&System.out.println("my id : " + _id);
	   return _id;
   }

   private String _password = "";
   public void setPass(String s) { _password=s; }
   //public String getPassword() { return _password; }

   private String _fname = "";
   public void setFirstname(String s) { _fname=s; }
   //public String getFirstname() { return _fname; }

   private String _lname = "";
   public void setLastname(String s) { _lname=s; }
   //public String getLastname() { return _lname; }

   private String _addr1 = "";
   public void setAddress1(String s) { _addr1=s; }
   //public String getAddress1() { return _addr1; }

   private String _addr2 = "";
   public void setAddress2(String s) { _addr2=s; }
   //public String getAddress2() { return _addr2; }

   private String _city = "";
   public void setCity(String s) { _city=s; }
   //public String getCity() { return _city; }

   private String _usstate = "";
   public void setUsstate(String s) { _usstate=s; }
   //public String getUsstate() { return _usstate; }

   private String _zipcode = "";
   public void setZipcode(String s) { _zipcode=s; }
   //public String getZipcode() { return _zipcode; }

   private String _country = "";
   public void setCountry(String s) { _country=s; }
   //public String getCountry() { return _country; }

   private String _tele = "";
   public void setTelephonenumber(String s) { _tele=s; }
   //public String getTelephonenumber() { return _tele; }

   private String _fax = "";
   public void setFaxnumber(String s) { _fax=s; }
   //public String getFaxnumber() { return _fax; }

   private String _mobile = "";
   public void setMobilenumber(String s) { _mobile=s; }
   //public String getMobilenumber() { return _mobile; }

   private String _email = "";
   public void setEmail(String s) { _email=s; }
   //public String getEmail() { return _email; }

   private String _template = "";
   public void setTemplate(String s) { _template=s; }
   //public String getTemplate() { return _template; }

   private String _language = "";
   public void setLanguage(String s) { _language=s; }
   //public String getLanguage() { return _language; }

   private String _desc = "";
   public void setDescription(String s) { _desc=s; }
   //public String getDescription() { return _desc; }

   private String _serv = "";
   public void setServ(String s) { _serv=s; }
   //public String getServ() { return _serv; }

   private User currentUser = null;

   private String[] selectedgroups = null;
   public void setSelectedgroups(String[] selectedgroups) { this.selectedgroups = selectedgroups; }

   private String[] unselectedgroups = null;
   public void setUnselectedgroups(String[] unselectedgroups) { this.unselectedgroups = unselectedgroups; }

   public UserBean()
   {
      super();
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

   protected String altPage()
   {
      //&System.out.println("[UserBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         //&System.out.println("[UserBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return null;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

   public User getCurrentUser()
   {
      return currentUser;
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

   public Group[] getNotMyGroups()
   {
      try
      {
         ArrayList ary = new ArrayList();

         Iterator iAll = userMgr.getAllGroups().iterator();
         Collection selectedGroups = userMgr.getGroupsByUID(_id);
         Group group = null;
         while (iAll.hasNext())
         {
            group = (Group)iAll.next();
            if(!selectedGroups.contains(group))
            {
               ary.add(group);
            }
         }
         return (Group[])ary.toArray(new Group[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new Group[0];
   }

   public Group[] getMyGroups()
   {
      try
      {
         ArrayList ary = new ArrayList();
         Iterator iAll = userMgr.getGroupsByUID(_id).iterator();
         while (iAll.hasNext())
         {
            ary.add(iAll.next());
         }
         return (Group[])ary.toArray(new Group[]{});
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return new Group[0];
   }

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

   public Collection getAllGroups() throws AccountDataAccessException
   {
      Collection c = userMgr.getAllGroups();
      if (c==null)
         return new ArrayList();
      else
         return c;
   }

   public Collection getAllUsers() throws AccountDataAccessException
   {
      Collection c = userMgr.getAllUsers();
      if (c==null)
         return new ArrayList();
      else
         return c;
   }

   public Collection getAllLanguages() throws AccountDataAccessException
   {
      //Collection c = LicenseKey.getInstance().getDomainLanguages((String)_session.getAttribute(SessionPhrase.LOGIN_DOMAIN));
      //if (c==null) return new ArrayList();
      //else return c;
      return null;
   }

   public Collection getServices(String id) throws AccountDataAccessException
   {
      Collection c = userMgr.getResourceIDSetByUID(id);
      if (c==null)
         return new ArrayList();
      else
         return c;
   }

   public ServiceDescriptor[] getAllServiceDescriptors() throws AccountDataAccessException
   {
      return passport.getAllServiceDescriptors();
   }

   public Collection getServices() throws AccountDataAccessException
   {
      return getServices(_id);
   }

   public Resource getUserService(String uid,String sid) throws AccountDataAccessException
   {
      return userMgr.getUserResource(uid,sid);
   }

   public Resource getUserService(String sid) throws AccountDataAccessException
   {
      return userMgr.getUserResource(_id,sid);
   }

   public int getUsersCount() throws AccountDataAccessException
   {
      Collection c = userMgr.getAllUsers();
      if (c==null) return 0;
      else return c.size();
   }

   public int getMaxUsers() throws AccountDataAccessException
   {
      return 20;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void clear() throws AccountDataAccessException
   {
      currentUser = new User();
      currentUser.setDefaultService("MainDesk");
   }

   public void load() throws AccountDataAccessException
   {
      User user = userMgr.getUser(_id);
      if (null!=user)
      {
         currentUser = user;
      }
      else
         throw new AccountDataAccessException("User is null : " + _id);
   }

   public void create() throws AccountDataAccessException
   {
      _id = _id.toUpperCase();
      if (userMgr.getUser(_id)==null)
      {
         currentUser.setID(_id);
         currentUser.setPassword(_password,true);
         currentUser.setFirstName(_fname);
         currentUser.setLastName(_lname);
         currentUser.setAddress1(_addr1);
         currentUser.setAddress2(_addr2);
         currentUser.setCellphoneNumber(_mobile);
         currentUser.setCity(_city);
         currentUser.setCountry(_country);
         currentUser.setDescription(_desc);
         currentUser.setEMail(_email);
         currentUser.setFaxNumber(_fax);
         currentUser.setLanguageSet(_language);
         currentUser.setZipCode(_zipcode);
         currentUser.setTelephoneNumber(_tele);
         currentUser.setTemplateSet(_template);
         currentUser.setState(_usstate);
         currentUser.setDefaultService(_serv);

         //*** save result to database
         if (userMgr.createUser(currentUser))
         {
            _password = "**********************"; // 22*

            setMsg("Create user sucessful.");
            isReload = true;
            return;
         }
         else
         {
            setState(ERR);
            setMsg("Create user error: database update error.");
            return;
         }
      }
      else
      {
         setState(ERR);
         setMsg("Create user error: user ID exists.");
         return;
      }
   }

   public void update() throws AccountDataAccessException
   {
      if (null!=userMgr.getUser(_id))
      {
         if (!"**********************".equals(_password))
         {
            currentUser.setPassword(_password, true);
         }

         currentUser.setFirstName(_fname);
         currentUser.setLastName(_lname);
         currentUser.setAddress1(_addr1);
         currentUser.setAddress2(_addr2);
         currentUser.setCellphoneNumber(_mobile);
         currentUser.setCity(_city);
         currentUser.setCountry(_country);
         currentUser.setDescription(_desc);
         currentUser.setEMail(_email);
         currentUser.setFaxNumber(_fax);
         currentUser.setLanguageSet(_language);
         currentUser.setZipCode(_zipcode);
         currentUser.setTelephoneNumber(_tele);
         currentUser.setTemplateSet(_template);
         currentUser.setState(_usstate);
         currentUser.setDefaultService(_serv);

         //*** save result to database
         if (userMgr.updateUser(currentUser))
         {
            _password = "**********************"; // 22*
            return;
         }
         else
         {
            setState(ERR);
            setMsg("Update user error: database error.");
            return;
         }
      }
      else
      {
         setState(ERR);
         setMsg("Update user error: user ID does not exist.");
         return;
      }
   }

   public void removeUser() throws AccountDataAccessException
   {
      if (null!=userMgr.getUser(_id))
      {
         userMgr.removeUser(_id);
         isReload = true;
         clear();
         return;
      }
      else
      {
         setState(ERR);
         setMsg("Remove user error: user ID does NOT exist.");
         return;
      }
   }

   public void selectGroups()
   {
      isReload = true;
      try
      {
         if (selectedgroups!=null)
         {
            for (int i=0; i<selectedgroups.length; i++)
               userMgr.addUserIntoGroup(_id,selectedgroups[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void deselectGroups()
   {
      isReload = true;
      try
      {
         if (unselectedgroups!=null)
         {
            for (int i=0; i<unselectedgroups.length; i++)
               userMgr.removeUserFromGroup(_id,unselectedgroups[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}