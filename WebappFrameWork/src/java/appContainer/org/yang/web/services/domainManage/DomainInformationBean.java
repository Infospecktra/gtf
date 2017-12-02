package org.yang.web.services.domainManage;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.domainMgr.DomainManager;
import org.yang.services.domainMgr.Domain;
import java.util.HashMap;
import org.yang.services.domainMgr.DomainDataAccessException;
import java.util.Iterator;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DomainInformationBean extends DomainBean
{
   static final long serialVersionUID = 4711296382979764920L;

   private Domain currentDomain = null;

   private String domainId ="";
   public void setDomainId(String domainId) { this.domainId = domainId; }
   public String  getDomainId(){return domainId;}

   private	String accountId ="";
   public void setAccountId(String accountId) { this.accountId = accountId; }
   public String getAccountId() { return accountId; }

   private String protocolNumber = "";
   public void setProtocolNumber(String protocolNumber) { this.protocolNumber = protocolNumber; }
   public String getProtocolNumber() { return protocolNumber; }

   private String description ="";
   public void setDescription(String description) { this.description = description; }
   public String  getDescription(){return description;}

   private HashMap datas = null;
   public void setDatas(HashMap datas) { this.datas = datas; }
   public HashMap getDatas() { return datas; }

   public DomainInformationBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      ensureResource();

      if(null==domainMgr)
         throw new Exception("Domain manager is null.");
   }

   public void beforeSetParameters()
   {
      super.beforeSetParameters();
      if(null!=currentDomain)
      {
         currentDomain.setAction(Domain.UPDATE);
      }
   }

   public void destroy(){}

   protected String altPage()
   {
      //&System.out.println("[ProjectInformationBean::altPage] Do alt page :" + isReload);
      if(isReload)
      {
         //&System.out.println("[ProjectInformationBean::altPage] My default page :" + this.defaultPage);
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

   public Domain getCurrentDomain()
   {
      return currentDomain;
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void clear()
   {
      currentDomain = this.domainMgr.newDomain();
   }

   public void create() throws DomainDataAccessException
   {
      try
      {
         currentDomain.setAction(Domain.CREATE);
         Iterator it = datas.keySet().iterator();
         String key = null;
         Object value = null;
		 while(it.hasNext())
         {
            key = (String)it.next();
			value = datas.get(key);
			//System.out.println("[DomainInformationBean::create].......>(key="+key+",value="+value+")");
			if("id".equals(key))
			{
			   value=((String)value).toUpperCase();//Convert domain to uppercase for persistence
			}  
            currentDomain.set(key, value);
         }

         domainMgr.createDomain(currentDomain);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         throw new DomainDataAccessException();
      }
      isReload = true;
   }

   public void load() throws DomainDataAccessException
   {
      //&System.out.println("///////////////////////////////////////" + domainId);
      currentDomain = domainMgr.getDomain(domainId);
      if (null==currentDomain)
         throw new DomainDataAccessException("Domain is null : " + domainId);
      currentDomain.setAction(Domain.UPDATE);
   }

   public void update() throws DomainDataAccessException
   {
      try
      {
         Iterator it = datas.keySet().iterator();
         String key = null;
         while(it.hasNext())
         {
            key = (String)it.next();
            if("id".equals(key))
               continue;
            currentDomain.set(key, datas.get(key));
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      //*** save result to database
      if (!domainMgr.updateDomain(currentDomain))
      {
         setState(ERR);
         setMsg("Update domain error: database error.");
         return;
      }

      isReload = true;
   }

   public void remove() throws DomainDataAccessException
   {
      if (null==domainMgr.removeDomain(domainId))
      {
         setState(ERR);
         setMsg("Remove domain error: domain ID does NOT exist.");
         return;
      }
      clear();
      isReload = true;
   }
}