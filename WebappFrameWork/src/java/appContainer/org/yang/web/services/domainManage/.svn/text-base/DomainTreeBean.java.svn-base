package org.yang.web.services.domainManage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;import java.util.Properties;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.services.dbService.DataAccessException;
import org.yang.web.controller.SessionPhrase;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.domainMgr.DomainManager;
import org.yang.services.domainMgr.Domain;
import org.yang.services.domainMgr.DomainDataAccessException;
import org.yang.services.accountmgr.UserManager;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationTree;
//import org.yang.services.licenseService.Initialize;

/**
 * Title:        P(oor)P(rogramer)
 * Description:  SITEWare 4.0 v2.0
 * Copyright:    Copyright (c) 2001
 * Company:      ScreamingMedia
 * @author Jason Wang
 * @version 1.0
 */

public class DomainTreeBean extends DomainBean
{
   static final long serialVersionUID = 4711296382979764921L;

   /**
   *  form set and get methods
   */
   private String targetId = null;
   public void setTargetId(String targetId) { this.targetId = targetId; }

   public DomainTreeBean()
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
         throw new Exception("Domain manager shall not be null.");
   }

   public void destroy() {}

   protected String altPage()
   {
      //System.out.println("[DomainTreeBean::altPage] reload page :" + isReload );
      //if(isReload)
      //{
      //   System.out.println("[ProjectInformationBean::altPage] My default page :" + this.targetPage);
      //   passport.setRuntimeProperty("right", this.targetPage);
      //   return "reload-forward";
      //}
      //else
      //   return this.targetPage;
      return null;
   }
   /****************************************
    *             for builder              *
    ****************************************/

   public String[] getAllDomainNames() throws DomainDataAccessException
   {
      try
      {
         return domainMgr.getAllDomainNames();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return new String[0];
   }

   public String[] getAllUserNames(String domainId) throws DomainDataAccessException
   {
      try
      {
         return domainMgr.getAllUserNames(domainId);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return new String[0];
   }

   /****************************************
    *             All my actions           *
    ****************************************/

   public void load()
   {
      this.removeControl("domainTree");
   }

   public void open()
   {
      ((NavigationTree)this.getControl("domainTree")).openNode(targetId);
   }

   public void close()
   {
      ((NavigationTree)this.getControl("domainTree")).closeNode(targetId);
   }
}