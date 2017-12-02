package org.yang.customized.gtf.services.messageManager.web;

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
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.messageManager.MessageManager;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.customized.gtf.services.dataAccess.Message;
import org.yang.services.dbService.DataAccessException;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class MessageContentBean extends MessageServiceBean 
{
   static final long serialVersionUID = 4711296030979764913L;

   private Message currentMessage = null;

   private long id =0;
   public void setId(long id) { this.id = id; }
   public long  getId(){return id;}

   private String title ="";
   public void setTitle(String title) { this.title = title; }
   public String  getTitle(){return title;}

   private String domainTo ="";
   public void setDomainTo(String domainTo) { this.domainTo = domainTo; }
   public String  getDomainTo(){return domainTo;}

   private String message ="";
   public void setMessage(String message) { this.message = message; }
   public String  getMessage(){return message;}

   private String attachmentUrlStr ="";
   public void setAttachmentUrlStr(String attachmentUrlStr) { this.attachmentUrlStr = attachmentUrlStr; }
   public String getAttachmentUrlStr(){return attachmentUrlStr;}


   /**
    *  form set and get methods
    */

   public MessageContentBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==msgMgr)
         throw new Exception("Message manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
   }

   public void destroy()
   {}

   protected String altPage()
   {
      System.out.println("[ProjectInformationBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         System.out.println("[ProjectInformationBean::altPage] My default page :" + this.defaultPage);
         passport.setRuntimeProperty("right", this.defaultPage);
         return "reload-forward";
      }
      else
         return null;
   }

   public String whoIsIt(String domain, String username)
   {
      return passport.whoIsIt(domain, username);
   }

   /****************************************
    *             for builder              *
    ****************************************/

   public Message getCurrentMessage()
   {
      return currentMessage;
   }

   public String[] getAllDomainNames()
   {
      return passport.getDomains();
   }

   public String[] getAllInvestigatorNames(String domainName)
   {
      return passport.getDomainUserNames(domainName);
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void clear()
   {
      try
      {
         if(null==currentMessage)
            currentMessage = new Message();
         currentMessage.setId(-1);
         currentMessage.setTitle("");
         currentMessage.setDomainFrom(this.passport.getDomain());
         currentMessage.setDomainTo("GTF");
         currentMessage.setFromUser(this.passport.getUsername());
         currentMessage.setToUser("admin");
         currentMessage.setMessage("");
         currentMessage.setIsSent(false);
/*
         if("".equals(domain)||null==domain)
         {
            domain = passport.getDomain();
            investigator = passport.getUsername();
         }

         if("".equals(type))
         {
            String[] types = passport.getServiceAreas("ScheduleManager");
            if(null!=types&&0!=types.length)
               type = types[0];
         }

         currentProject = ProjectFactory.getFactory().createProject(type);
         currentProject.setDomain(domain);
         currentProject.setAccountId(passport.getDomain(domain).getAccountId());
         currentProject.setProtocolNumber("");//passport.getMyDomain().getProtocolNumber());
         currentProject.setInvestigator(investigator);
*/
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public void create() throws DataAccessException
   {
      try
      {
         currentMessage = new Message();
         currentMessage.setTitle(title);
         currentMessage.setDomainFrom(this.passport.getDomain());
         currentMessage.setDomainTo("GTF");
         currentMessage.setFromUser(this.passport.getUsername());
         currentMessage.setToUser("admin");
         long now = System.currentTimeMillis();
         currentMessage.setTheDate(new java.sql.Date(now));
         currentMessage.setTheTime(new java.sql.Time(now));
            System.out.println("--------------------------------");
            System.out.println(this.getMessage());
            System.out.println("--------------------------------");
         currentMessage.setMessage(this.getMessage());

         //*** save result to database
         if (!msgMgr.createMessage(currentMessage))
         {
            setState(ERR);
            setMsg("Create user error: database update error.");
            return;
         }
         id = currentMessage.getId();
         isReload = true;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DataAccessException();
      }
   }

   public void load() throws DataAccessException
   {
      currentMessage = msgMgr.getMessage(id);
   }

   public void update() throws DataAccessException
   {
      try
      {
         if(!currentMessage.getIsSent())
         {
            currentMessage = new Message();
            currentMessage.setId(id);
            currentMessage.setTitle(title);
            currentMessage.setDomainFrom(this.passport.getDomain());
            currentMessage.setDomainTo("GTF");
            currentMessage.setFromUser(this.passport.getUsername());
            currentMessage.setToUser("admin");
            long now = System.currentTimeMillis();
            currentMessage.setTheDate(new java.sql.Date(now));
            currentMessage.setTheTime(new java.sql.Time(now));
            System.out.println("--------------------------------");
            System.out.println(this.getMessage());
            System.out.println("--------------------------------");
            currentMessage.setMessage(this.getMessage());
         }
         else
         {
             load();
             return;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!msgMgr.updateMessage(currentMessage))
      {
         setState(ERR);
         setMsg("Update project error: database error.");
         return;
      }
      isReload = true;
   }

   public void send() throws DataAccessException
   {
      try
      {
         currentMessage = new Message();
         currentMessage.setId(id);
         currentMessage.setTitle(title);
         currentMessage.setDomainFrom(this.passport.getDomain());
         currentMessage.setDomainTo("GTF");
         currentMessage.setFromUser(this.passport.getUsername());
         currentMessage.setToUser("admin");
         long now = System.currentTimeMillis();
         currentMessage.setTheDate(new java.sql.Date(now));
         currentMessage.setTheTime(new java.sql.Time(now));
         currentMessage.setMessage(this.getMessage());
         currentMessage.setIsSent(true);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!msgMgr.updateMessage(currentMessage))
      {
         setState(ERR);
         setMsg("Update project error: database error.");
         return;
      }
      isReload = true;
   }

   public void remove() throws DataAccessException
   {
      if (!msgMgr.removeMessage(id))
      {
         setState(ERR);
         setMsg("Remove user error: project ID does NOT exist.");
         return;
      }
      clear();
   }
}