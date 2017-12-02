/**
 * This class is used to manage client to access a domain and to manipulate some services
 * within each domain.
 * each domain has one UserManager object. Although, we donot define an
 * domain class, the domain concept will appear in database to form an
 * unit of users. Their relationship is: a set of users can belong to another
 * set of groups, which in turn form a domain. A domain will be passed to database
 * as a string
 * @ title  UserManager class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
package org.yang.customized.gtf.services.messageManager;

import org.yang.services.dataAccess.genericDAO.GenericDAO;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.customized.gtf.services.dataAccess.Message;
import org.yang.services.servicemgr.Area;
import org.yang.web.applicationContainer.Passport;
import java.util.Properties;
import java.util.HashSet;
import java.util.ArrayList;
import org.yang.services.servicemgr.Service;
import java.util.Collections;

public class MessageManager implements Service
{
   public static String CROSS_DOMAIN = "xdomain";
   public static String CROSS_USER   = "xuser";
   public static String UPDATE       = "update";
   public static String DELETE       = "delete";
   public static String RECEIVE      = "receive";
   public static String SEND         = "send";

   private GenericDAO gdao = null;
   private String domain = null;
   private String tableName = "ALL_messageTable";
   private ArrayList allDomains = null;

   protected String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return null; }

   /**
      *  each domain has one UserManager
      */
   public MessageManager (String dname)
   {
      //create the tables.
      domain = dname;
      allDomains = new ArrayList();
      gdao = new GenericDAO(RDBMSFactory.getInstance().getDataRDBMS());
      Message msg = new Message();
      msg.setTablename(tableName);
      gdao.createTable(msg);
   }

   public void initialize(Properties prop, Passport passport)
   {
      String[] domains = passport.getDomains();
      for(int i=0; i<domains.length; i++)
      {
         allDomains.add(domains[i]);
      }
      Collections.sort(allDomains);
   }

   public void destroy() {}

   /**
    * this method will get all group object in one doamian
    * @param
    * @return     a set of group object
    * @exception  DataAccessEception
    */
   public Message[] getMessages(String[] conditions, boolean isAnd) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Message msg = new Message();
      msg.setTablename(tableName);
      return (Message[])gdao.load(msg, conditions, isAnd).toArray(new Message[]{});
   }

   /**
 * this method will get all group object in one doamian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Message[] getAllMessages() throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Message msg = new Message();
      msg.setTablename(tableName);
      return (Message[])gdao.load(msg).toArray(new Message[]{});
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Message[] getMessages(long[] ids) throws org.yang.services.dbService.DataAccessException
   {
      if(null==ids||null==gdao)
         return null;
      String[] conditions = new String[ids.length];
      for(int i=0; i<ids.length; i++)
      {
         conditions[i] = "id=" + ids[i];
      }

      Message msg = new Message();
      msg.setTablename(tableName);
      return (Message[])gdao.load(msg, conditions, false).toArray(new Message[]{});
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public Message getMessage(long id) throws org.yang.services.dbService.DataAccessException
   {
      if(null==gdao)
         return null;
      Message msg = new Message();
      msg.setTablename(tableName);
      return ((Message[])gdao.load(msg, "id="+id).toArray(new Message[]{}))[0];
   }

   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeMessage(long id) throws org.yang.services.dbService.DataAccessException
   {
      Message msg = new Message();
      msg.setTablename(tableName);
      return gdao.delete(msg, "id="+id);
   }

   /**
 * this method will delete a permisiion where its gid ,sid and area equals input gid ,sid and area
 * Note: this method can only be used to resulve one area element collumm in permission table
 * for multiple elements areas can not be used here
 * @param      gid  group   id
 * @param      sid  service id
 * @param      area the area name . Note: only singal area name
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean createMessage(Message msg) throws org.yang.services.dbService.DataAccessException
   {
      if(null==msg)
         return false;
      msg.setTablename(tableName);
      msg.setId(this.generateId());
      return gdao.insert(msg);
   }

   /**
 * This method will create the user,group,usergroup and
 * permission tables automatically and reset the
 * administrator data in data base.
 * @param
 * @return     true :Succeed to reset administrator data in data base
 *             false:Fail to reset administrator data in data base
 * @exception  DataAccessEception
 */
   public boolean updateMessage(Message msg) throws org.yang.services.dbService.DataAccessException
   {
      if(null==msg)
         return false;
      msg.setTablename(tableName);
      return gdao.update(msg);
   }

   public Area[] allAreas()
   {
      int size = allDomains.size();
      MessageManagerArea[] areas = new MessageManagerArea[size];
      MessageManagerOperation[] ops = null;

      for(int i=0; i<size; i++)
      {
         ops = new MessageManagerOperation[5];
         ops[0] = new MessageManagerOperation(this.DELETE);
         ops[1] = new MessageManagerOperation(this.UPDATE);
         ops[2] = new MessageManagerOperation(this.RECEIVE);
         ops[3] = new MessageManagerOperation(this.CROSS_DOMAIN);
         ops[4] = new MessageManagerOperation(this.CROSS_USER);
         areas[i] = new MessageManagerArea();
         areas[i].setName((String)allDomains.get(i));
         areas[i].setAllOperations(ops);
      }

      return areas;
   }

   public boolean containArea(String areaname)
   {
      return allDomains.contains(areaname);
   }

   private long generateId()
   {
      return System.currentTimeMillis();
   }

   public static void main(String[] args)
   {
   }
}
