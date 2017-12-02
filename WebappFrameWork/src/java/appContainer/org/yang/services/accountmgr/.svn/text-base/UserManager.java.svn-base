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
package org.yang.services.accountmgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Properties;

import org.yang.services.dbService.IDGenerator;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.servicemgr.Service;
import org.yang.services.servicemgr.Area;
import org.yang.web.applicationContainer.Passport;

/**
 * @testcase org.test.org.yang.services.accountmgr.TestUserManager 
 */
public class UserManager implements Service, java.io.Serializable
{
   private String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return null; }

   private  String  dName ="";
   private DAOFactory daof = null;

   /**
      *  each domain has one UserManager
      */
   public UserManager (String dname)
   {
      if(dname==null||"".equals(dname))
          return;
             
      dName =dname;
   }

   public void initialize(Properties prop, Passport passport) 
   {
      prop.setProperty("DAOFactory.domain", dName);
      //prop.setProperty("DAOFactory.classname", "org.yang.services.accountmgr.SimpleDAOFactory");
      daof = DAOFactory.getFactory(prop);  	
   }

   public void destroy() {}

   public void initDomain() throws AccountDataAccessException
   {
      //create the tables.
      UserDAO udao = daof.getUserDAO();
      udao.createUserTable();

      GroupDAO gdao = daof.getGroupDAO();
      gdao.createGroupTable();
      gdao.createUsergroupTable();
      gdao.createServiceTable();

      PermissionDAO pdao = daof.getPermissionDAO();
      pdao.createPermissionTable();

      //create the default user.
      User u = new User();
      u.setID("ADMIN");
      u.setFirstName("account");
      u.setLastName("manager");
      u.setPassword("admin",true);
      u.setDescription("Account manager who create accounts for this domain. Please change the default password.");

      createUser(u);

      Group g = new Group();
      g.setID("ACCOUNTMANAGER");
      g.setName("AccountManager");
      g.setDescription("The account manager group");

      createGroup(g);

      addUserIntoGroup(u.getID(), g.getID());

      Permission p = new Permission();
      p.setId("P001");
      p.setGroupId(g.getID());
      p.setServiceId(Resource.AccountManager);

      addPermission(p);
 }

   /**
  * Remove the user tables of a domain.
  */
   public void removeDomain()
   {
      //create the tables.
      UserDAO udao = daof.getUserDAO();
      udao.dropUserTable();

      GroupDAO gdao = daof.getGroupDAO();
      gdao.dropGroupTable();
      gdao.dropUsergroupTable();
      gdao.dropServiceTable();

      PermissionDAO pdao = daof.getPermissionDAO();
      pdao.dropPermissionTable();

      SignOnManager som = SignOnManager.getInstance(dName);
      som.removeDomain();
   }


   /**
  * Should create this engineer in the SCRM domain.
  */
   public static void createEngineer() throws AccountDataAccessException
   {
      //create the default user.
      UserManager um = new UserManager("SCRM");

      User u = new User();
      u.setID("SUPER");
      u.setFirstName("Super");
      u.setLastName("User");
      u.setLanguageSet("English");
      u.setPassword("111",true);
      u.setDescription("I am the super user. Dont delete my account ever.");

      um.createUser(u);

      Group g = new Group();
      g.setID("SUPER");
      g.setName("super group");
      g.setDescription("The super user group");

      um.createGroup(g);

      um.addUserIntoGroup(u.getID(), g.getID());

      Permission p = new Permission();
      p.setId("P000");
      p.setGroupId(g.getID());
      p.setServiceId(Resource.EngineersDesk);

      um.addPermission(p);
   }

   /**
  * Create this user for each domain.
  */
   public void createAccountManager() throws AccountDataAccessException
   {
      //create the default user.
      User u = new User();
      u.setID("SUPER");
      u.setFirstName("Super");
      u.setLastName("User");
      u.setPassword("111",true);
      u.setDescription("I am the super user. Dont delete my account ever.");

      createUser(u);

      Group g = new Group();
      g.setID("SUPER");
      g.setName("super group");
      g.setDescription("The super user group");

      createGroup(g);

      addUserIntoGroup(u.getID(), g.getID());

      Permission p = new Permission();
      p.setId("P001");
      p.setGroupId(g.getID());
      p.setServiceId(Resource.EngineersDesk);

      addPermission(p);
   }

   /**
  * This method will be used by client whenever a client want to logon the system
  * if he can successfully logon, he will be able to do some work, otherwise he need to logon again
  * when any one use this method should extremely careful about encrypt flag setting
  * @param uidlog the user login name, pwd  the user login password
  * @return me an user object
  * @exception DataAccessEception
  */
   public User  logon(String uidLog, String pwd) throws AccountDataAccessException
   {
      UserDAO udao  = daof.getUserDAO();
      User    me    = new User();
 
      me=udao.loadByID(uidLog);
      if (me==null)
         return me; 
         
      if(!me.checkPassword(pwd))
      {
         me=null;
      }

      return me;
   }

   /**
 * this method will retrieve a set of sid from any given group id
 * @param      gid  the group id
 * @return     a  set of service ids
 * @exception  DataAccessEception
 */
   public Set getResourceIDSByGID(String gid)throws AccountDataAccessException
   {
      Set sid = new HashSet();
      if(null==gid||"".equals(gid))
        return sid;

      Collection pm = daof.getPermissionDAO().loadByGid(gid);
      if (pm == null)
         return sid;

      Iterator it   = pm.iterator();
      while(it.hasNext() )
      {
         String sidS=  ((Permission)it.next()).getServiceId();
         sid.add(sidS);
      }
      return sid;
   }

   /**
 * this method will retrieve a set of sid from any given uid
 * @param      uid  the user id
 * @return     a set of service ids
 * @exception  DataAccessEception
 */
   public String[] getResourceIDSByUID(String uid)throws AccountDataAccessException
   {
      return (String[])getResourceIDSetByUID(uid).toArray(new String[]{});
   }

  /**
 * this method will retrieve a set of sid from any given uid
 * @param      uid  the user id
 * @return     a set of service ids
 * @exception  DataAccessEception
 */
   public Set getResourceIDSetByUID(String uid)throws AccountDataAccessException
   {
      /** this collection contains a set of group object
       * and to get each group id
       */
      Collection gids = getGroupsByUID(uid);
      Set sid  = new HashSet();

      if(gids == null)
         return sid;

      Iterator   it   = gids.iterator();

      while (it.hasNext())
      {
         String gid    = ((Group)it.next()).getID();
         Set    tmpsid = getResourceIDSByGID(gid);

         if(tmpsid == null)
            continue;

         Iterator tmpsidit = tmpsid.iterator();
         while(tmpsidit.hasNext())
         {
            String tmpsidS= (String)tmpsidit.next();
            sid.add(tmpsidS);
         }
      }

      return sid;
   }

   /**
 * Added by the request of steven, Oct 21, 2002
 * @param service Service ID
 * @param area Area ID
 * @param operation Operation ID
 * @return Collection of User objects that have the permission. No duplicates.
 */
   public Collection getUsersByPermission(String service, String area, String operation)throws AccountDataAccessException
   {
      PermissionDAO  pdao = daof.getPermissionDAO();
      Set result = new HashSet();
      try
      {
         Collection gids = pdao.loadGIDByService(service, area, operation);
 
         if ((gids ==null) || (gids.isEmpty())) return null;
         Iterator i = gids.iterator();
   
         while (i.hasNext())
         {
            String gid = (String) i.next();
            Collection users = getUsersByGID(gid);
            result.addAll(users);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return result;
   }

   /**
* this method will construct a  service object from group id and service id
* @param      gid  the group id , sid the user id
* @return     a set of service object
* @exception  DataAccessEception
*/
   public Resource  getResourceByGIDSID(String gid, String sid)throws AccountDataAccessException
   {
      Collection allps = getPermissionsByGIDSID(gid, sid);
      return mergeResource(allps);
   }

   /**
 * this method will return a set of user objects
 * @param      gid  the group id
 * @return     a set of user objects
 * @exception  DataAccessEception
 */
   public Collection getUsersByGID(String gid) throws AccountDataAccessException
   {
      UserDAO    udao  = daof.getUserDAO();
      Collection users = udao.loadUsersFromGroup(gid);
      return users;
   }

   /**
 * this method will return a set of group objects
 * @param      uid the user id
 * @return     a set of group objects
 * @exception  DataAccessEception
 */
   public Collection  getGroupsByUID(String uid) throws AccountDataAccessException
   {
      GroupDAO    gdao = daof.getGroupDAO();
      Collection  ctmp = gdao.loadGroupsByUser(uid);
      return ctmp;
   }

   /**
* this method will return a set of permission objects
* @param      gid  the group id, sid the servie id
* @return     a set of permission objects, but can be null if gid or sid is wrong
* @exception  DataAccessEception
*/
   public Collection getPermissionsByGIDSID(String gid, String sid) throws AccountDataAccessException
   {
      PermissionDAO  pdao = daof.getPermissionDAO();
      Collection     ctmp = pdao.loadByGidSid(gid,sid);
      return         ctmp;
   }

   /**
* this method eliminate the duplication of services
* @param      ps a set of permissions
* @return     a service objects
*/
   public Resource mergeResource(Collection ps)
   {
      if (ps == null) return null;
      Permission  tmpPm   = null;
      Iterator    itp     = ps.iterator();
      Resource    service = null;

      if(ps.size()!=0)
      {
         service= new Resource();
         while(itp.hasNext())
         {
            tmpPm = (Permission)itp.next();
            service.addPermision(tmpPm);
         }
      }
      return service;
   }

   /**
 * this method will construct a service object from any given user id and service id
 * @param      gid  the group id, sid the servie id
 * @return     a service object
 * @exception  DataAccessEception
 */
   public Resource getUserResource(String uid, String sid) throws AccountDataAccessException
   {
      // get all group ids
      Collection  groupC = getGroupsByUID( uid);
      if (groupC == null) return null;

      String      gid    = "";
      Iterator  itg=groupC.iterator();
      // get all persmissions
      Collection allPms  = new ArrayList();
      while(itg.hasNext())
      {
         gid = ((Group)(itg.next())).getID();
//System.out.println("in UserManager gid"+gid+"sid"+sid);
         Collection tmp = getPermissionsByGIDSID( gid, sid);
         Iterator   it  = tmp.iterator();
         while(it.hasNext())
         {
            allPms.add(it.next());
         }
      }

      return mergeResource(allPms);
   }

   /**
 * this method will create a user object and put it into user table in database
 * @param      userInfo contains alll user infomations such as uid user id, fname fast name, lname last name,
 *              dep description, pw password, the String [] sequence follow the user input page sequence
 *              encrypt using non_encryption mode.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean  createUser(String [] userInfo ,boolean encrypt) throws AccountDataAccessException
   {
      // create a new user and initiate it
      User  newuser = new User();

      newuser.setID              (userInfo[0]);
      newuser.setPassword        (userInfo[1],encrypt);
      newuser.setFirstName       (userInfo[2]);
      newuser.setLastName        (userInfo[3]);
      newuser.setAddress1        (userInfo[4]);
      newuser.setAddress2        (userInfo[5]) ;
      newuser.setCity            (userInfo[6]);
      newuser.setState           (userInfo[7]);
      newuser.setZipCode         (userInfo[8]) ;
      newuser.setCountry         (userInfo[9]);
      newuser.setTelephoneNumber (userInfo[10]);
      newuser.setFaxNumber       (userInfo[11]);
      newuser.setCellphoneNumber (userInfo[12]);
      newuser.setEMail           (userInfo[13]);
      newuser.setTemplateSet     (userInfo[14]);
      newuser.setLanguageSet     (userInfo[15]) ;
      newuser.setDescription     (userInfo[16]);
      newuser.setDefaultService(userInfo[17]);

      // pass the new user into userDAO
      UserDAO udao    = daof.getUserDAO();
      boolean success = udao.insert(newuser);
      return success;
   }

   /**
 * this method will create a user object and put it into user table in database
 * @param      user object         encrypt using non_encryption mode.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean  createUser(User newuser) throws AccountDataAccessException
   {
      UserDAO udao = daof.getUserDAO();
      boolean success = false;
      if (udao==null)
      {
         success=false;
      }
      else  success = udao.insert(newuser);
      return success;
   }

   /**
 * this method replace an old user and put it into user table in database
 * @param      user object         encrypt using non_encryption mode.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean  updateUser(User newuser) throws AccountDataAccessException
   {
      UserDAO udao = daof.getUserDAO();
      boolean success = false;
      if (udao==null)
      {
         success=false;
      }
      else  success = udao.update( newuser) ;
      return success;
   }

   /**
 * this method will create a groupr object and put it into group table in database
 * @param      gid group id, gname group name, gdp group description
 *             encryption mode.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean createGroup(String gid, String gname, String gdp ) throws AccountDataAccessException
   {
      if (null==gid||null==gname||null==gdp||null==dName) return false;
      // create a new group and initiate it
      Group  newgroup = new Group();
      newgroup.setID(gid);
      newgroup.setName(gname);
      newgroup.setDescription(gdp);
      // pass it into GroupDAO
      GroupDAO gdao    = daof.getGroupDAO();
      boolean  success = gdao.insert(newgroup);
      return  success;
   }

   /**
 * this method will create a groupr object and put it into group table in database
 * @param      newgroup  the inserted group object
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean createGroup(Group newgroup ) throws AccountDataAccessException
   {
      GroupDAO gdao = daof.getGroupDAO();
      boolean  success =false;
      if (gdao==null)
         success=false;
      else
         success = gdao.insert(newgroup);
      return  success;
   }

   /**
 * this method will replace an old groupr object and put the new one into group table in database
 * @param      newgroup  the inserted group object
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean updateGroup(Group newgroup ) throws AccountDataAccessException
   {
      GroupDAO gdao  = daof.getGroupDAO();
      boolean  success =false;
      if (gdao==null)
         success=false;
      else
         success = gdao.update(newgroup);
      return  success;
   }

   /**
 * this method will insert a user into a group put the couple into group_map table in database
 * @param      uid user id, gid group .
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean addUserIntoGroup(String uid, String gid)throws AccountDataAccessException
   {
      if (null==uid||null==gid||null==dName) return false;
      GroupDAO gdao = daof.getGroupDAO();
      boolean  success = gdao.addUserToGroup(uid,gid);
      return  success ;
   }

   /**
 * this method will delete t a user from a group in group_map table in database
 * @param      uid user id, gid group .
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean removeUserFromGroup(String uid, String gid)throws AccountDataAccessException
   {
      if (null==uid||null==gid||null==dName) return false;
      GroupDAO gdao = daof.getGroupDAO();
      boolean  success = gdao.removeUserFromGroup(uid,gid);
      return  success ;
   }

   /**
 * this method will delete t a user from user table in database
 * @param      uid user id.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean removeUser(String uid) throws AccountDataAccessException
   {
      if (null==uid||null==dName) return false;
      UserDAO udao = daof.getUserDAO();
      boolean success = udao.delete(uid) ;
      return success;
   }

   /**
 * this method will delete t a group from group table in database
 * @param      gid group id.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean removeGroup(String gid) throws AccountDataAccessException
   {
      if (null==gid||null==dName) return false;
      GroupDAO  gdao = daof.getGroupDAO();
      boolean   success = gdao.delete(gid);
      return success;
   }

   /**
 * this method will get a set of permission objects from permission table in database
 * @param      gid group id.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public Collection getAllPermissions()throws AccountDataAccessException
   {
      PermissionDAO pdao = daof.getPermissionDAO();
      Collection    allPms  = pdao.loadAllPermissions();
      return allPms;
   }

   /**
 * this method will add a  permission object into permission table in database
 * @param      p permission object.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean addPermission(Permission p) throws AccountDataAccessException
   {
      if (null==p||null==dName) return false;
      PermissionDAO pdao = daof.getPermissionDAO();
      boolean success = pdao.insert(p);
      return success;
   }

   /**
 * this method will delete a  permission object from permission table in database
 * @param      pid  permission id.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean removePermission(String pid)throws AccountDataAccessException
   {
      if (null==pid||null==dName) return false;
      PermissionDAO pdao = daof.getPermissionDAO();
      boolean success= pdao.delete(pid);
      return success;
   }

   public boolean removePermissionByArea(String aid)throws AccountDataAccessException
   {
      if (null==aid||null==dName) return false;
      PermissionDAO pdao = daof.getPermissionDAO();
      boolean success= pdao.deleteByArea(aid);
      return success;
   }

   /**
 * this method will update a  permission object from permission table in database
 * @param      p  permission object.
 * @return     a flag to indicate whether you are success
 * @exception  DataAccessEception
 */
   public boolean modifyServiceGroup(Permission p)throws AccountDataAccessException
   {
      if (null==p||null==dName) return false;
      PermissionDAO pdao = daof.getPermissionDAO();
      boolean success = pdao.update(p);
      return  success;
   }

   /**
 * this method will get all group object in one doamian
 * @param
 * @return     a set of group object
 * @exception  DataAccessEception
 */
   public Collection getAllGroups()throws AccountDataAccessException
   {
      GroupDAO   gdao = daof.getGroupDAO();
      Collection gc   = gdao.loadAllGroups();
      return gc;
   }

   /**
 * this method will get all user object in one domain
 * @param
 * @return     a set of user object
 * @exception  DataAccessEception
 */
   public Collection getAllUsers()throws AccountDataAccessException
   {
      UserDAO  udao = daof.getUserDAO();
      Collection  uc = udao.loadAllUsers();
      return uc;
   }

   public String[] getAllUserNames()throws AccountDataAccessException
   {
      UserDAO  udao = daof.getUserDAO();
      Iterator uit = udao.loadAllUsers().iterator();
      ArrayList userNames = new ArrayList();
      while(uit.hasNext())
      {
         userNames.add(((User)uit.next()).getID());
      }

      return (String[])userNames.toArray(new String[]{});
   }

   /**
 * this method will get an user object in one domain
 * @param      uid user id
 * @return     an user object if wrong domain or wrong id will return null
 * @exception  DataAccessEception
 */
   public User getUser(String uid)throws AccountDataAccessException
   {
      if (null==uid||null==dName) return null;
      UserDAO  udao  = daof.getUserDAO();
      User     me    = new User();
      if(udao==null ) {  me=null;}
      else   me=udao.loadByID(uid);
      return me;
   }

   /**
 * this method will get a group object in one domain
 * @param      gid  group  id
 * @return     an group object if wrong domain or wrong gid will return null
 * @exception  DataAccessEception
 */
   public Group getGroup(String gid)throws AccountDataAccessException
   {
      if (null==gid||null==dName) return null;
      GroupDAO  gdao  = daof.getGroupDAO();
      Group     group = new Group();
      if( gdao==null ) {  group=null;}
      else    group=gdao.loadByID(gid);
      return  group;
   }

   /**
 * this method will delete a permission where its gid and sid equals input gid and sid
 * @param      gid  group   id
 * @param      sid  service id
 * @return     true means all associated permssions were deleted
 * @exception  DataAccessEception
 */
   public boolean removeResourceFromGroup(String gid, String sid) throws AccountDataAccessException
   {
      boolean success=false;
      if (null==gid||null==sid||null==dName) return success;

      PermissionDAO  pdao = daof.getPermissionDAO();

      if (null==pdao) return success;

      Collection   allPM = pdao.loadByGidSid(gid,sid);

      if (null==allPM) return success;

      Iterator   it   = allPM.iterator();

      while(it.hasNext())
      {
         Permission p= (Permission)it.next();

         if (!(null==p))
         {
            String pid=p.getId();
            if (!(null==pid)) success = pdao.delete(pid) ;
         }
      }
      return success;
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
   public boolean removeAreasByGIDSIDAREA(String gid, String sid, String area) throws AccountDataAccessException
   {
      boolean success=false;
      if (null==gid||null==sid||null==dName||null==area) return success;

      PermissionDAO  pdao = daof.getPermissionDAO();

      if (null==pdao) return success;

      Collection   allPM = pdao.loadByGidSid(gid,sid);

      if (null==allPM) return success;

      Iterator   it   = allPM.iterator();

      while (it.hasNext())
      {
         Permission  p = (Permission)it.next();// does the collections contanins any null before the end ?
         if (!(null==p))
         {
            String areas= p.getAreasConcat() ;
            if (!(null==areas))
            {
               if(areas.equals(area) )
               {
                  success = pdao.delete(p.getId());// save the change to database
               }
            }
         }
      }
      return success;
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
   public boolean resetAdminByDomain()throws AccountDataAccessException
   {
      if((dName == null)||(dName.equals("")))  return false;
      try
      {
         UserDAO       udao = daof.getUserDAO();
         GroupDAO      gdao = daof.getGroupDAO();
         PermissionDAO pdao = daof.getPermissionDAO();

         User u = new User();
         u.setID("ADMIN");
         u.setFirstName("Admin");
         u.setLastName("Admin");
         u.setPassword("admin",true);
         u.setDescription("user description");

         Group g = new Group();
         g.setID("ADMINISTRATORS");
         g.setName("Administrators");
         g.setDescription("group description");
         String pid = IDGenerator.getUniqueID("PID");

         Permission p = new Permission();
         p.setId(pid);
         p.setGroupId("ADMINISTRATORS");
         p.setServiceId("AccountManager");
         p.setAreasConcat("area1");
         p.setOperationsConcat("operation1");
         udao.delete("ADMIN");

         gdao.delete("ADMINISTRATORS");

         udao.insert(u);
         gdao.insert(g);
         pdao.insert(p);
         gdao.addUserToGroup(u.getID(),g.getID());

         return true;
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException("Fail to reset administrator data.");
      }
   }

   /**
* this method will update a  permission object from matching group id ,service id and area
* @param      gid  the group id
* @param      sid the user id
* @param      area the area name
* @param      operatin the new operation name
* @return     boolean
* @exception  DataAccessEception
*/
   public boolean  updateOperationByGIDSIDAREA(String gid, String sid, String area, String operation)throws AccountDataAccessException
   {
      boolean success=false;

      if (null==gid||null==sid||null==area||null==operation||null==dName) return success;

      PermissionDAO  pdao  = daof.getPermissionDAO();

      if (null==pdao) return success;
//System.out.println("UserManager"+gid+"sid"+sid);
      Collection     allPM = pdao.loadByGidSid(gid,sid);

      if (null==allPM) return success;

      Iterator   it  = allPM.iterator();
      while (it.hasNext())
      {
         Permission  p = (Permission)it.next();// does the collections contanins any null before the end ?
         if (!(null==p))
         {
            String areas= p.getAreasConcat() ;
            if (!(null==areas))
            {
               if(areas.equals(area) )
               {
                  p.setOperationsConcat(operation);
                  success = pdao.update(p);// save the change to database
               }
            }
          }
      }
      return success;
   }

   public Area[] allAreas()
   {
      return new Area[]{};
   }

   public boolean containArea(String areaname)
   {
      return false;
   }

    public static void main(String[] args)
   {

      UserManager um= new UserManager("testdomain");

      /*
      User user1 =new User();
      user1.setID("uid001");
      user1.setFirstName("hui");
      user1.setLastName ("zhang");
      user1.setPassword("4hui",true);
      user1.setDescription("User1 Description ?");
      
      User user2 =new User();
      user2.setID("uid002");
      user2.setFirstName("celina");
      user2.setLastName ("yang");
      user2.setPassword("A005",true);
      user2.setDescription("User2 Description ?");      
      
      User user3 =new User();
      user3.setID("uid003");
      user3.setFirstName("Edith");
      user3.setLastName ("lee");
      user3.setPassword("eee",true);
      user3.setDescription("User3 Description ?");   
      
      User user4 =new User();
      user4.setID("uid004");
      user4.setFirstName("Smile");
      user4.setLastName ("yang");
      user4.setPassword("sss",true);
      user4.setDescription("User4 Description ?");         
      */
                
      User user1 =new User();
      user1.setID("ADMIN");
      user1.setFirstName("admin");
      user1.setLastName ("admin");
      user1.setPassword("admin",true);
      user1.setDescription("Admin");   
      /*            
                Group group1 =new Group ();
      group1.setID("gid001");
      group1.setName("group1");
           group1.setDescription("this is group1 ");
           
                Group group2 =new Group ();
      group2.setID("gid002");
      group2.setName("group2");
           group2.setDescription("this is group2 ");      
      */
      
                Group group1 =new Group ();
      group1.setID("ADMIN");
      group1.setName("ADMIN");
           group1.setDescription("ADMIN");
                 
      boolean b= true;
      System.out.println("are you here?");
      //-------------------------------------------  
       try
       {         
          UserDAO udao = null;//new UserDAO("testdomain");
          GroupDAO gdao = null;//new GroupDAO("testdomain");
          PermissionDAO pdao = null;//new PermissionDAO("testdomain");
                
   
          
                //=-=-=-=-=-=-=-*< step2 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
              // 1.Create user_testdomain table
                // 2.Create group_testdomain table
                // 3.Create usergroup_testdomain table
                // 4.Create service_testdomain table

                //System.out.println("Create a user table? " + udao.createUserTable());
              //System.out.println("Create a new group table? --->"+ gdao.createGroupTable());
                //System.out.println("Create a new usergroup table? --->"+ gdao.createUsergroupTable());
              //System.out.println("Create a new Service table? --->"+ gdao.createServiceTable());
                System.out.println("Create a permission table? " + pdao.createPermissionTable());
                //=-=-=-=-=-7=-=-*< step3 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
                // 1.createUser(User newuser)               
         // 2.createGroup(Group g)
         // 3.getUser(String uid)
         // 4.getAllUsers()
         // 5.getAllGroups()
         System.out.println(um.createUser(user1));
           /*
           System.out.println(um.createUser(user2));     
        System.out.println(um.createUser(user3));
           System.out.println(um.createUser(user4));  
           */                       
       System.out.println(um.createGroup(group1));
           //System.out.println(um.createGroup(group2));   
           
           //System.out.println("the fisrt name of uid001 = " + um.getUser("uid001").getFirstName());
           /*
           Collection users_ = um.getAllUsers();     
                    
                if(users_ != null)
                {
                    Iterator iterator__ = users_.iterator();
                    while(iterator__.hasNext())
                    {
                        User uu = (User)iterator__.next();
                        System.out.println("userid=" + uu.getID());
                        System.out.println("first name   =" + uu.getFirstName());
                    }       
                }              
           
           Collection groups_ = um.getAllGroups();     
                    
                if(groups_ != null)
                { 
                    Iterator iterator_ = groups_.iterator();
                    while(iterator_.hasNext())
                    {
                        Group gg = (Group)iterator_.next();
                        System.out.println("groupid=" + gg.getID());
                        System.out.println("name   =" + gg.getName());
                    }       
                }   
                */ 
                  //System.out.println("Start to reset administraor data----"+um.resetAdminByDomain());
          
                  //=-=-=-=-=-=-=-*< step1 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
                  // 1.logon(String ID, String password)
                  /*       
          User user= um.logon("uid002", "A005");
          if(user==null)
         System.out.println("user object is null");
               else
                 System.out.println("*********the logon user name is" +user.getFirstName());
                  */
                  //=-=-=-=-=-=-=-*< step2 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
                   //  this part is used to test the group_user relationship         
                  // 1.addUserIntoGroup(String uid,String groupid);      
        // 2.getUSerByGID(String groupid); 
        // 3.getGroupsByUID(String uid)
                 
              boolean ug1 =um.addUserIntoGroup("ADMIN", "ADMIN");
              /*
         boolean ug2 =um.addUserIntoGroup("uid002", "gid001");
         boolean ug3 =um.addUserIntoGroup("uid003", "gid002");
         boolean ug4 =um.addUserIntoGroup("uid004", "gid002");

         
         Collection  users1 = um.getUsersByGID("gid001");
         if(users1!=null)
         {
             Iterator it1 = users1.iterator();
             while(it1.hasNext())
             {
                 User u1 = (User)it1.next();
                 System.out.println("uid1=" + u1.getID());   
             }       
         }
         else{System.out.println("There is no user in gid001");}       
       
              Collection groups1 =  um.getGroupsByUID("uid002");
              if(groups1!=null)
              {
             Iterator it2 = groups1.iterator();
             while(it2.hasNext())
             {
                 Group g1 = (Group)it2.next();
                 System.out.println("gid1=" + g1.getID());   
             }                        
              }   
         else{System.out.println("uid002 doesn't join in any group.");}
       */   
         
                  //=-=-=-=-=-=-=-*< step3 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
                   /*
          * this part is used to test the relationship among permisions,  service ,operations and areas
          */
         //1. addPermission(Permission p) 
         //2. getAllPermissions()
         //2. removePermission(String pid)
         //3. getServiceIDSByGID(String gid)
         //4. getServiceIDSByUID(String uid)
         //5. removeServiceFromGroup(String gid, String sid)
         //6. getUserService("uid001", "sid001")
         //7. mergeResource(Collection permissions)
         
           Permission p1= new Permission();
           p1.setId("pid001");
           p1.setGroupId("ADMIN");
           p1.setServiceId(org.yang.web.controller.ApplicationPhrase.USER_MANAGER);
           //p1.setAreasConcat("a1,a2,a3");
           //p1.setOperationsConcat("op1,op2,op3");         
                          /*         
           Permission p1= new Permission();
           p1.setId("pid001");
           p1.setGroupId("gid001");
           p1.setServiceId("sid001");
           p1.setAreasConcat("a1,a2,a3");
           p1.setOperationsConcat("op1,op2,op3");
              
           Permission p2= new Permission();
           p2.setId("pid002");
           p2.setGroupId("gid002");
           p2.setServiceId("sid002");
           p2.setAreasConcat("a1,a4");
           p2.setOperationsConcat("op1,op4,op5");
           
           Permission p3= new Permission();
           p3.setId("pid003");
           p3.setGroupId("gid001");
           p3.setServiceId("sid001");
           p3.setAreasConcat("a1,a4,a5");
           p3.setOperationsConcat("op1,op4");            
         
                  
                     System.out.println("addPermission p1?"+um.addPermission(p1));
                     System.out.println("addPermission p2?"+um.addPermission(p2));      
                          System.out.println("addPermission p3?"+um.addPermission(p3));
                       
                          
                     Collection permissions_ = um.getAllPermissions();     
                    
                          if(permissions_ != null)
                          { 
                              Iterator iterator = permissions_.iterator();
                              while(iterator.hasNext())
                              {
                                  Permission pp = (Permission)iterator.next();
                                  System.out.println("pid=" + pp.getId());
                                  System.out.println("gid=" + pp.getGroupId());
                                  System.out.println("sid=" + pp.getServiceId());
                              }       
                            }     
                              
                          //Service service2 = um.mergeResource(permissions_);    
                          
                          Service service2 = um.getUserService("uid001", "sid001"); 
                          if(service2==null)
                              System.out.println("there is no service");
                          else
                              System.out.println("get the service object.");                  
                          //System.out.println("removePermission p3?"+um.removePermission("pid003"));
                           //----------------------------------------------------------         
           /*
           Collection sids1 = um.getServiceIDSByGID("gid002");
           Iterator it3 = sids1.iterator();
           while(it3.hasNext())
           {
               String sid1 = (String)it3.next();
               System.out.println("serviceid of gid001 = " + sid1);              
           }
           */    
           //----------------------------------------------------------
           /*
           Collection sids2 = um.getServiceIDSByUID("uid003");
           Iterator it4 = sids2.iterator();
           while(it4.hasNext())
           {
               String sid2 = (String)it4.next();
               System.out.println("serviceid of uid002 = " + sid2);              
           }    
           */           
           //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
           // 1.getUsersByPermission(String serviceid,String areaid,String operationid)
           // 2.getPermissionsByGIDSID(String gid, String sid)
           // 3.getServiceByGIDSID(String gid, String sid)
           /*
           Collection  o_users1 = um.getUsersByPermission("sid002", "a1,a4","op1,op4,op5");           
           if(o_users1==null)
           {
               System.out.println("There is no user with this permission. ");   
               return ;
           }    
           
           Iterator it5 = o_users1.iterator();
           while(it5.hasNext())
           {
               User u2 = (User)it5.next();
               System.out.println("userid = " + u2.getID() + "\n\r");
               System.out.println("first name " + u2.getFirstName());              
           }
          
           Service service1 = um.getServiceByGIDSID("gid002", "sid002"); 
           if(service1==null)
               System.out.println("There is no service by gid002/sid002");
           else
               System.out.println("get service object .");   
          */     
                         
          //----------------------------------------------------------
           /*
           Collection permissions1 = um.getPermissionsByGIDSID("gid001","sid01");
           Iterator it6 = permissions1.iterator();
           while(it6.hasNext())
           {
               Permission perm = (Permission)it6.next();
               System.out.println("permissionid = " + perm.getId() + "\n\r");
               System.out.println("areas = " + perm.getAreasConcat());              
           }    
           */              
           //System.out.println("remove sid001 from gid001" + um.removeServiceFromGroup("gid001", "sid001"));
           //System.out.println("remove a1 from gid001/sid001/a1,a4,a5 " + um.removeAreasByGIDSIDAREA("gid001", "sid001", "a1,a4,a5"));
           //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*                 
           //1.removeUserFromGroup(String uid, String gid);
           //2.addUserIntoGroup(String uid, String gid);
           //3.removeUser(String uid)
           //4.removeGroup(String gid)
           //5.setLastName(User user)
           //6.updateGroup(Group newgroup )
           
            //System.out.println("remove u/g pair "+um.removeUserFromGroup("uid004", "gid002"));      
            //System.out.println("add u/g pair "+um.addUserIntoGroup("uid004", "gid002"));      
            /*
              user1.setLastName("Change");
              group1.setName("group_name1");
                            
              System.out.println("update uid001 = " + um.updateUser(user1));
              System.out.println("update gid001 = " + um.updateGroup(group1));
              System.out.println("remove uid003 = " + um.removeUser("uid003"));      
              System.out.println("remove gid002 = " + um.removeGroup("gid002"));
             */ 
           //----------------------------------------------------------    
           // Service service =um.getUserService("tom1", "sv1");
           /*String [] areas  =service.getAllAreas();
           String [] ops    = service.getAllOperations();
           String [] asInOP =service.getAreasForOperation("op4" );
           String [] opsInA =service.getOperationsInArea("a5" ); */
         /*  for(int i=0; i<asInOP.length;i++)
              {
                  //System.out.println(asInOP[i]);
              }*/
       // for(int i=10;i>0;i--)
         //{
             //User user1_return =udao.loadByID("tom"+i);
             //user1.setuID("tom"+i);
            // b=udao.insert(user1);
            //group.setgID("tony"+i);
            //gdao.insert(group);

         // }

          //Collection alluser=um.getAllUsers();
          //Iterator it=alluser.iterator();
          // Collection allgroups=um.getAllGroups();
          // Iterator it=allgroups.iterator();
          // Collection  groupbyuser=um.getGroupsByUID("user5");
          // Iterator it=groupbyuser.iterator();
          //udao.delete("tom10");
         // Collection c =um.getUsersByGID("group4");
           //Iterator   it=c.iterator();
          //int        number=c.size();
          //Set  s =um.getServiceIDSByGID("group5");

              // Set  s= um.getServiceIDSByUID("user5");
            //Iterator   it=s.iterator();
         /*   Service     c  = um.getServiceByGIDSID("group5","WriterDesk");
               String [] ars = c.getAllAreas();
              for(int i=0; i<ars.length;i++)
              {
                     System.out.println("the areas are "+ars[i]);
              }
              String [] ops = c.getAllOperations();
              for(int ii=0;ii<ops.length; ii++)
              {
                        System.out.println("the operations are  "+ops[ii]);
              }
            */
         /* while(it.hasNext())
         {

             //String sid= (String)it.next();
            //System.out.println("the sid is "+sid);
          // User user2=(User)it.next();
           //udao.delete(user2.getUID());
         // System.out.println(user2.getUID()+" the number is "+number);
              //Group group2= (Group)it.next();
           //gdao.delete(group2.getGID());
           //System.out.println(group2.getGID());
         } */
      }
      catch (AccountDataAccessException e)
      {
               e.getMessage();
      }
   //   System.out.println("it is true or false ?"+b);
   //   System.out.println("Hello World!");
   }
}
