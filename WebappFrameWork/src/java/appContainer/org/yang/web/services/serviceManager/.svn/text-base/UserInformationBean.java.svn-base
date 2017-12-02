package org.yang.web.services.serviceManager;

import org.yang.web.applicationContainer.SecuredBean;
import java.util.HashMap;
import org.yang.services.accountmgr.User;
import java.util.Collection;
import org.yang.services.accountmgr.AccountDataAccessException;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
public class UserInformationBean extends SecuredBean
{
   static final long serialVersionUID = 4711296382979764922L;

   /**
    *  form set and get methods
    */
   private HashMap userDatas = null;
   public void setUserDatas(HashMap userDatas) { this.userDatas = userDatas; }

   private User currentUser = null;

   public UserInformationBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      //   throw new Exception("User manager is null.");
   }

   public void ensureResource() throws Exception
   {
      //   throw new Exception("User manager is null.");
   }

   protected String altPage()
   {
      return null;
   }

   public void destroy()
   {}

   /****************************************
    *   implements methods from Selector   *
    ****************************************/

   public User getCurrentUser()
   {
      return currentUser;
   }

   public Collection getAllLanguages() throws AccountDataAccessException
   {
      //Collection c = LicenseKey.getInstance().getDomainLanguages((String)_session.getAttribute(SessionPhrase.LOGIN_DOMAIN));
      //if (c==null) return new ArrayList();
      //else return c;
      return null;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load() throws AccountDataAccessException
   {
      currentUser = passport.loaUserInfo();
      if(null==currentUser)
         throw new AccountDataAccessException("User is null : " + passport.getUsername());
   }

   public void update() throws AccountDataAccessException
   {
      try
      {
         String password = getUserData("password");
         if(null==password||"".endsWith(password))
         {
            setState(ERR);
            setMsg("Update user error: password can not be empty.");
            return;
         }

         if(!password.endsWith(getUserData("confirmpass")))
         {
            setState(ERR);
            setMsg("Update user error: passwords doesn't match.");
            return;
         }

         if (!"**********************".equals(password))
         {
            currentUser.setPassword(password, true);
         }

         currentUser.setFirstName(getUserData("firstname"));
         currentUser.setLastName(getUserData("lastname"));
         currentUser.setAddress1(getUserData("address1"));
         currentUser.setAddress2(getUserData("address2"));
         currentUser.setCellphoneNumber(getUserData("mobilenumber"));
         currentUser.setCity(getUserData("city"));
         currentUser.setCountry(getUserData("country"));
         currentUser.setDescription(getUserData("description"));
         currentUser.setEMail(getUserData("email"));
         currentUser.setFaxNumber(getUserData("faxnumber"));
         currentUser.setLanguageSet(getUserData("language"));
         currentUser.setZipCode(getUserData("zipcode"));
         currentUser.setTelephoneNumber(getUserData("telephonenumber"));
         currentUser.setTemplateSet(getUserData("template"));
         currentUser.setState(getUserData("usstate"));
         //currentUser.setDefaultService(getUserData("_serv"));

         //*** save result to database
         if (!passport.saveUserInfo(currentUser))
         {
            setState(ERR);
            setMsg("Update user error: database error.");
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         setState(ERR);
         setMsg("Update user error.");
      }
   }

   private String getUserData(String key)
   {
      String value = (String)userDatas.get(key);
      return value;
   }
}