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

abstract public class DomainBean extends SecuredBean
{
   protected transient DomainManager domainMgr = null;

   public DomainBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void ensureResource() throws Exception
   {
      if(null==domainMgr)
         domainMgr = (DomainManager)passport.loadService("EngineersDesk");
   }
}