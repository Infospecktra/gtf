package org.yang.services.accountmgr.SimpleDAOs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import java.util.*;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.RDBMS;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.accountmgr.UserDAO;
import org.yang.services.accountmgr.User;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.dbService.DBConnectionCount;

/**
 * @testcase org.test.org.yang.services.accountmgr.SimpleDAOs.TestSimpleUserDAOImpl
 
 *
 * @ version 1.0
 * @ Date: 06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class SimpleUserDAOImpl implements UserDAO
{
   private static final String columnNamesConcat1
           = "id,password,firstname,lastname"+
             ",address1,address2,city,state,postal_code,country,telephone_no"+
             ",fax_no,mobile_no,email_address,template_set,language_set"+
             ",description, defaultService";

   private static final String columnNamesConcat2 = "userid,gid";
   private  String domain = null;
   private  String tablename1 = null;
   private  String tablename2 = null;
 
   RDBMS rdbms = null;

   /**
    * Constructor
    */
   public SimpleUserDAOImpl(String domain, RDBMS rdbms)
   {
      if((domain==null)||(domain.equals("")))
      {
         System.out.println("The String of domain name cann't be null or space. ");
         return ;
      }

      this.domain = domain;
      tablename1= this.domain + "_user_user" ;
      tablename2= this.domain + "_user_usergroup" ;
      this.rdbms = rdbms;
   }

   /**
 * set the value of User object.
 *@exception:
 *@param
 *@return:
 */
   private void setUserValue(ResultSet rs,User user)
   {
      try
      {
         int i=1;
         user.setID(rs.getString(i++));
         user.setPassword(rs.getString(i++),false);
         user.setFirstName(rs.getString(i++));
         user.setLastName(rs.getString(i++));
         user.setAddress1(rs.getString(i++));
         user.setAddress2(rs.getString(i++));
         user.setCity(rs.getString(i++));
         user.setState(rs.getString(i++));
         user.setZipCode(rs.getString(i++));
         user.setCountry(rs.getString(i++));
         user.setTelephoneNumber(rs.getString(i++));
         user.setFaxNumber(rs.getString(i++));
         user.setCellphoneNumber(rs.getString(i++));
         user.setEMail(rs.getString(i++));
         user.setTemplateSet(rs.getString(i++));
         user.setLanguageSet(rs.getString(i++));
         user.setDescription(rs.getString(i++));
         user.setDefaultService(rs.getString(i++));
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   /**
 * create a new user table in database
 *@exception:AccountDataAccessException
 *@param
 *@return: boolean
 */
   public boolean createUserTable()//throws AccountDataAccessException
   {
      String sql = "CREATE TABLE " + tablename1 +
                   "(id char(255)not null , password char(255) not null," +
                   "firstname char(255) not null,lastname char(255) not null," +
                   "address1  char(255),address2 char(255),city char(255)," +
                   "state char(255),postal_code char(255) ,country char(255)," +
                   "telephone_no char(255),fax_no char(255),mobile_no char(255),"+
                   "email_address char(255),template_set char(255),"+
                   "language_set char(255),description char(255),"+
                   "defaultService char(255),primary key(id))";
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
         return false;
      }
      return true;
   }

   /**
 * Drop a user table in database
 *@exception:AccountDataAccessException
 *@param
 *@return: boolean
 */
   public boolean dropUserTable()//throws AccountDataAccessException
   {
      String sql ="DROP TABLE " + tablename1 ;
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true ;
   }

   /**
 * loading user object by user id
 * @exception: <{org.yang.services.accountmgr.AccountDataAccessException}>
 * @param String id
 * @return: User object
 */
   public User loadByID(String id)throws AccountDataAccessException
   {
      if(id == null)
      {
         System.out.println("id is null.");
         return null;
      }

      User user = new User();
      user.setID(id);

      String sql = "select "+ columnNamesConcat1 + " from " + tablename1 + " where id = ? ";

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      //System.out.println("Connected");
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, id);
         rs = Stmt.executeQuery();
         if(rs == null) return null;
         if(!(rs.next())) return null;
         else{setUserValue(rs,user);}
      }
      catch (Exception e)
      {
         //e.printStackTrace();
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (rs!=null) rs.close();
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::loadByID");   
            }            
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return user;
   }

   /**
 * Insert new user data to data base.
 *
 * @exception: <{AccountDataAccessException}>
 * @param: User user
 * @return: boolean
 */
   public boolean insert(User user)throws AccountDataAccessException
   {
      if (user == null)
      {
         System.out.println("user object is null");
         return false;
      }

      if(loadByID(user.getID())!=null)
         return false;

      String sql = "insert into " + tablename1 +
                   "("+ columnNamesConcat1+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

      //System.out.println("Inserting into table");

      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database.");
      }

      //System.out.println("Connected");

      try
      {
         Stmt.setString(1, user.getID());
         Stmt.setString(2, user.getPassword());
         Stmt.setString(3, user.getFirstName());
         Stmt.setString(4, user.getLastName());
         Stmt.setString(5, user.getAddress1());
         Stmt.setString(6, user.getAddress2());
         Stmt.setString(7, user.getCity());
         Stmt.setString(8, user.getState());
         Stmt.setString(9, user.getZipCode());
         Stmt.setString(10, user.getCountry());
         Stmt.setString(11, user.getTelephoneNumber());
         Stmt.setString(12, user.getFaxNumber());
         Stmt.setString(13, user.getCellphoneNumber());
         Stmt.setString(14, user.getEMail());
         Stmt.setString(15, user.getTemplateSet());
         Stmt.setString(16, user.getLanguageSet());
         Stmt.setString(17, user.getDescription());
         Stmt.setString(18, user.getDefaultService());

         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::insert");   
            }            
         }
         catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
 * Delete a user data from data base
 *@exception: <{AccountDataAccessException}>
 *@param  : String id
 *@return:  boolean
 */
   public boolean delete(String id)throws AccountDataAccessException
   {
      if (id == null)
      {
         System.out.println("user id can't be null .");
         return false;
      }

      String sql1 = "delete from " + tablename1 + " where id = ?";
      String sql2 = "delete from " + tablename2 + " where userid = ?";

      Connection Conn;
      PreparedStatement Stmt1;
      PreparedStatement Stmt2;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt1 = Conn.prepareStatement(sql1);
         Stmt2 = Conn.prepareStatement(sql2);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      try
      {
         Stmt1.setString(1, id);
         Stmt1.executeUpdate();
         Stmt2.setString(1, id);
         Stmt2.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt1!=null) Stmt1.close();
            if (Stmt2!=null) Stmt2.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::delete");   
            }            
         }
         catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
 * Update a user data from data base
 *@exception: <{AccountDataAccessException}>
 *@param  : User user
 *@return:  boolean
 */
   public boolean update(User user)throws AccountDataAccessException
   {
      if (user == null)
      {
         System.out.println("user object is null");
         return false;
      }

      if(loadByID(user.getID())==null)
      return false;

      String sql = "update " + tablename1 + " set " +
                   "password = ?, firstname = ?, lastname = ?, address1=?, "+
                   "address2=?, city=?, state=?, postal_code=? ," +
                   "country=?, telephone_no=?, fax_no=?, mobile_no=? ," +
                   "email_address=?, template_set=?, language_set=?, description=?, " +
                   "defaultService=? where id = ?";

      //System.out.println("Updating table...");

      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException (e.getMessage());
      }

      //System.out.println("Connected");

      try
      {
         Stmt.setString(1, user.getPassword());
         Stmt.setString(2, user.getFirstName());
         Stmt.setString(3, user.getLastName());
         Stmt.setString(4, user.getAddress1());
         Stmt.setString(5, user.getAddress2());
         Stmt.setString(6, user.getCity());
         Stmt.setString(7, user.getState());
         Stmt.setString(8, user.getZipCode());
         Stmt.setString(9, user.getCountry());
         Stmt.setString(10, user.getTelephoneNumber());
         Stmt.setString(11, user.getFaxNumber());
         Stmt.setString(12, user.getCellphoneNumber());
         Stmt.setString(13, user.getEMail());
         Stmt.setString(14, user.getTemplateSet());
         Stmt.setString(15, user.getLanguageSet());
         Stmt.setString(16, user.getDescription());
         Stmt.setString(17, user.getDefaultService());
         Stmt.setString(18, user.getID());
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         System.out.println("Update failed");
         throw new AccountDataAccessException ("Update failed!");
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::update");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
 * Delete all users in one domain
 *@exception: <{AccountDataAccessException}>
 *@param  :
 *@return :  boolean
 */
   public boolean deleteAllUsers()throws AccountDataAccessException
   {
      String sql1 = "delete from "+tablename1;
      String sql2 = "delete from "+tablename2;

      try
      {
         rdbms.executeUpdate(sql1);
         rdbms.executeUpdate(sql2);
         return true;
      }
      catch(Exception e)
      {
         throw new AccountDataAccessException(e.getMessage());
      }
   }

   /**
 * Loading all the User objects in a specific group
 *@exception: <{AccountDataAccessException}>
 *@param  : String gid :The group id
 *@return : Collection : The colection of User objects
 */
   public Collection loadUsersFromGroup(String gid)throws AccountDataAccessException
   {
      if (gid == null)
      {
         System.out.println("gid can't be null ");
         return null;
      }
      Collection usersInAGroup = new ArrayList();
      String sql = "select "+ columnNamesConcat1 + " from " + tablename1 + ", " + tablename2 +
                   " where "+ tablename1 + ".id = " + tablename2 + ".userid" +
                   " and " +tablename2+".gid=? ";

      //System.out.println("Inserting into table");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

     //System.out.println("Connected");
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, gid);
         rs = Stmt.executeQuery();
         while (rs.next())
         {
            User u = new User();
            setUserValue(rs, u);
            usersInAGroup.add(u);
         }
      }
      catch (Exception e)
      {
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (rs!=null) rs.close();
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::loadUsersFromGroup");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
      return usersInAGroup;
   }

   /**
 * Loading all the User objects in this domain
 *@exception: <{AccountDataAccessException}>
 *@param  :
 *@return : Collection : The collection of User objects
 */
   public Collection loadAllUsers()throws AccountDataAccessException
   {
      Collection usersInDomain = new ArrayList();
      String sql = "select "+ columnNamesConcat1 + " from " + tablename1;
      Connection Conn = null;
      Statement Stmt = null;
      ResultSet rs = null;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.createStatement();
         rs = Stmt.executeQuery(sql);

         while (rs.next())
         {
            User u = new User();
            setUserValue(rs, u);
            usersInDomain.add(u);
         }
      }
      catch(Exception e)
      {
         throw new AccountDataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            if (rs!=null) rs.close();
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::loadAllUsers");   
            }            
         }
         catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return usersInDomain;
   }

   /**
 * Add a user to a group (insert the data to usergroup table
 *@exception: <{AccountDataAccessException}>
 *@param  : String uid,String gid
 *@return:  boolean
 */
   public boolean addToGroup(String uid,String gid)throws AccountDataAccessException
   {
      if ((uid == null)&&(gid == null))
      {
         System.out.println("gid or uid cann't be  null or space");
         return false;
      }

      String sql1 ="select "+ columnNamesConcat2 + " from " + tablename2 +
                   " where userid = ? and gid=?" ;

      String sql2 = "insert into " + tablename2 +
                    "("+ columnNamesConcat2+ ") values (?,?)";

      //System.out.println("Inserting into table");

      Connection Conn;
      PreparedStatement Stmt1;
      PreparedStatement Stmt2;
      boolean result = true;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt1 = Conn.prepareStatement(sql1);
         Stmt2 = Conn.prepareStatement(sql2);
      }
      catch (Exception e)
      {
         result =  false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database.");
      }

      //System.out.println("Connected");

      try
      {
         Stmt1.setString(1, uid);
         Stmt1.setString(2, gid);

         ResultSet rs = Stmt1.executeQuery();
         if(rs.next())
         {
           Stmt1.close();
           return false;
         }

         Stmt2.setString(1, uid);
         Stmt2.setString(2, gid);
         Stmt2.executeUpdate();

         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt1!=null) Stmt1.close();
            if (Stmt2!=null) Stmt2.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::addToGroup");   
            }            
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return (result);
   }

   /**
 * remove the data from usergroup table
 *@exception: <{AccountDataAccessException}>
 *@param  : String uid,String gid
 *@return:  boolean
 */
   public boolean removeFromGroup(String uid,String gid)throws AccountDataAccessException
   {
      if ((uid == null)&&(gid == null))
      {
         System.out.println("gid or uid cann't be null ");
         return false;
      }

      String sql ="delete from " + tablename1 + " where userid = ? and gid = ?";

      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      try
      {
         Stmt.setString(1, uid);
         Stmt.setString(2,gid);
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException (e.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleUserDAOImpl::removeFromGroup");   
            }            
         }
         catch (Exception e) {ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
 * test stub
 */
   public static void main(String[] args)
   {
      try
      {
         //=-=-=-=-=-=-=-*< step1 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.new User object
         // 2.new UserDAO object
         User u1 = new User();
         u1.setID("EDITH");
         u1.setFirstName("Edith");
         u1.setLastName("Yang");
         u1.setPassword("111",true);
         u1.setAddress1("address1");
         u1.setAddress2("address2");
         u1.setCity("NYC");
         u1.setState("NY");
         u1.setZipCode("10021");
         u1.setCountry("USA");
         u1.setTelephoneNumber("111111111");
         u1.setFaxNumber("222222222");
         u1.setCellphoneNumber("333333333");
         u1.setEMail("email1");
         u1.setTemplateSet("t1");
         u1.setLanguageSet("L1");
         u1.setDescription("description1");
         u1.setDefaultService("service1");

         User u2 = new User();
         u2.setID("DODO");
         u2.setFirstName("Dodo");
         u2.setLastName("Lui");
         u2.setPassword("222",true);
         u2.setAddress1("address1");
         u2.setAddress2("address2");
         u2.setCity("NJC");
         u2.setState("NY");
         u2.setZipCode("13333");
         u2.setCountry("USA");
         u2.setTelephoneNumber("111");
         u2.setFaxNumber("222");
         u2.setCellphoneNumber("333");
         u2.setEMail("email2");
         u2.setTemplateSet("t2");
         u2.setLanguageSet("L2");
         u2.setDescription("employee2");
         u2.setDefaultService("service2");
      
         User user1 =new User();
         user1.setFirstName("hui");
         user1.setLastName ("zhang");
         user1.setPassword("4hui",true);
         user1.setDescription("User1 Description ?");
         User user2 =new User();
         user2.setID("111");
         user2.setFirstName("celina");
         user2.setLastName ("yang");
         user2.setPassword("A005",true);
         user2.setDescription("User2 Description ?");

         UserDAO udao = null;//new UserDAO("testdomain");
         //=-=-=-=-=-=-=-*< step2 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.Create a new user table ok

         //System.out.println("Create a new user table? --->"+udao.createUserTable()); //m1

         //=-=-=-=-=-=-=-*< step3 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.Insert user data to the user table (ok)
  
         //System.out.println(udao.insert(u1));//m2 ok
         //System.out.println(udao.insert(u2));//
         //System.out.println(udao.insert(user1));//m2 ok
         //System.out.println(udao.insert(user2));//
   
         //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.loadAllUsers() (OK)
         /*
         Collection users = udao.loadAllUsers();
         Iterator iterator = users.iterator();
         while (iterator.hasNext())
         {
            User u = (User)iterator.next();
            System.out.println("id = "+ u.getID());
            System.out.println("password = "+ u.getPassword());
            System.out.println("first name = "+ u.getFirstName());
            System.out.println("address1 = "+ u.getAddress1());
            System.out.println("address2 = "+ u.getAddress2());
            System.out.println("city = "+ u.getCity());
            System.out.println("state = "+ u.getState());
            System.out.println("postal code = "+ u.getZipCode());
            System.out.println("country = "+ u.getCountry());
            System.out.println("telNo = " + u.getTelephoneNumber());
            System.out.println("FaxNo = " + u.getFaxNumber());
            System.out.println("CellNo = "+ u.getCellphoneNumber());
            System.out.println("email = "+ u.getEMail());
            System.out.println("templateSet = "+ u.getTemplateSet());
            System.out.println("languageSet = "+ u.getLanguageSet());
            System.out.println("description = "+ u.getDescription());
         }
         */
        //=-=-=-=-=-=-=-*< step5 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
        // 1.Modify user data of the user table (OK)
        /*
        u1.setFirstName("smile");
        u1.setLastName("Y");
        u1.setDescription("director1");
        System.out.println("Update the user data? "+udao.update(u1));//m3  ok
        */
        //=-=-=-=-=-=-=-*< step6 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
        // 1.Delete user data of the user table (OK)
        // 2.loadByID()
        //if(udao.delete("EDITH"))  //m4 ok
        //  System.out.println(udao.loadByID("DODO")); //should be null  m5  ok

        //=-=-=-=-=-=-=-*< step7 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
        //1.deleteAllUser();ok
        System.out.println("Delete all users? --->"+ udao.deleteAllUsers());// m6 ok

       //=-=-=-=-=-=-=-*< step8 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
       //1.drop the table
       //System.out.println("Drop the table? --->"+ udao.dropUserTable());
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }
}