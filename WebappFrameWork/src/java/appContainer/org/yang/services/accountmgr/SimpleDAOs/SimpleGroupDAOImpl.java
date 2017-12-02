package org.yang.services.accountmgr.SimpleDAOs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.RDBMS;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.accountmgr.GroupDAO;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.accountmgr.UserDAO;
import org.yang.services.accountmgr.User;
import org.yang.services.dbService.DBConnectionCount;

/**
 * @testcase org.test.org.yang.services.accountmgr.SimpleDAOs.TestSimpleGroupDAOImpl
 
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class SimpleGroupDAOImpl implements GroupDAO
{
    private static final String columnNamesConcat1 = "id,name,description"; //for group table
    private static final String columnNamesConcat2 = "userid,gid";          //for usergrouptable
    private  String tablename0 = null;
    private  String tablename1 = null;
    private  String tablename2 = null;
    private  String tablename3 = null;
    private  String tablename4 = null;
    private  String domain     = null;

    RDBMS rdbms = null;

   /**
    * Constructor
    */
   public SimpleGroupDAOImpl(String domain, RDBMS rdbms)
   {
      if((domain==null)||(domain.equals("")))
      {
         System.out.println("The String of domain name cann't be null or space. ");
         return ;
      }

      this.domain = domain;
      tablename0 = this.domain + "_user_user" ;
      tablename1 = this.domain + "_user_group";
      tablename2 = this.domain + "_user_usergroup";
      tablename3 = this.domain + "_user_service";
      tablename4 = this.domain + "_user_permission";
      this.rdbms = rdbms;
   }

   private void setGroupValue(ResultSet rs,Group gr)
   {
      try
      {
         int i=1;
         gr.setID(rs.getString(i++));
         gr.setName(rs.getString(i++));
         gr.setDescription(rs.getString(i++));
      }
      catch(Exception e)
      { ExceptionBroadcast.print(e);  }
   }

   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createGroupTable() //throws AccountDataAccessException
   {
      String sql ="CREATE TABLE " + tablename1 +
                  "(id char(255)not null, name char(255),description char(255),primary key(id))";
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }

   /**
   * create a new usergroup table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createUsergroupTable()//throws AccountDataAccessException
   {
      String sql ="CREATE TABLE " + tablename2 +
                  "(userid char(255) not null,gid char(255) not null)";
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //e.printStackTrace();
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }

   /**
   * create a new service table in database
   *@exception:DataAccessException
   *@param
   *@return:   boolean
   */
   public boolean createServiceTable()//throws AccountDataAccessException
   {
      String sql ="CREATE TABLE " + tablename3 +
                  "(id char(255)not null,name char(255),description char(255),primary key(id))";
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }

   /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropGroupTable()//throws AccountDataAccessException
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
      return true;
   }

   /**
   * Drop a usergroup table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropUsergroupTable()// throws AccountDataAccessException
   {
      String sql ="DROP TABLE " + tablename2 ;
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }


   /**
   * Drop a service table in database
   *@exception:DataAccessException
   *@param
   *@return:   boolean
   */
   public boolean dropServiceTable()//throws AccountDataAccessException
   {
      String sql ="DROP TABLE " + tablename3 ;
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return true;
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{Group}>
   */
   public Group loadByID(String gid)throws AccountDataAccessException
   {
      if (gid == null)
      {
         System.out.println("Group id can't be null .");
         return null;
      }

     Group gr = new Group();
     gr.setID(gid);

     String sql = "select " + columnNamesConcat1 + " from " + tablename1 +
                  " where id = ?";

     Connection Conn = null;
     PreparedStatement Stmt;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql);
     }
     catch (Exception e)
     {
        //e.printStackTrace();
        throw new AccountDataAccessException ("can not connect to database");
     }
     
     ResultSet rs = null;
     try
     {
        Stmt.setString(1, gid);
        rs = Stmt.executeQuery();

        if(rs == null) return null;
        if(!(rs.next())) return null;
        else{setGroupValue(rs,gr);}

      }
      catch (Exception e)
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::loadByID");   
            }   

         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return gr;
   }


   /**
   * Insert new group data into data base.
   *
   * @exception: DataAccessException
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Group g)throws AccountDataAccessException
   {
      if (g == null)
      {
         System.out.println("Group object is null");
         return false;
      }

      if(loadByID(g.getID())!=null)
         return false;

      String sql = "insert into " + tablename1 +
                   "("+ columnNamesConcat1+ ") values (?,?,?)";

      System.out.println("Inserting into table");

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
         //e.printStackTrace();
         throw new AccountDataAccessException ("Can not connect to database.");
      }

      //System.out.println("Connected");

      try
      {
         Stmt.setString(1, g.getID());
         Stmt.setString(2, g.getName());
         Stmt.setString(3, g.getDescription());
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::insert");   
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
   * Delete a group data from data base
   *@exception: DataAccessException
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws AccountDataAccessException
   {
      if (id == null)
      {
         System.out.println("Group id can't be null .");
         return false;
      }

      if(loadByID(id)==null)
         return (false);

      String sql1 = "delete from " + tablename1 + " where id=?";
      String sql2 = "delete from " + tablename2 + " where gid=?";
      String sql3 = "delete from " + tablename4 + " where groupid=?";

      Connection Conn;
      PreparedStatement Stmt1;
      PreparedStatement Stmt2;
      PreparedStatement Stmt3;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt1  = Conn.prepareStatement(sql1);
         Stmt2  = Conn.prepareStatement(sql2);
         Stmt3  = Conn.prepareStatement(sql3);
      }
      catch (Exception e)
      {
         result = false;
         //e.printStackTrace();
         throw new AccountDataAccessException ("Can not connect to database");
      }

      try
      {
         Stmt1.setString(1, id);
         Stmt1.executeUpdate();
         Stmt2.setString(1, id);
         Stmt2.executeUpdate();
         Stmt3.setString(1, id);
         Stmt3.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            Stmt1.close();
            Stmt2.close();
            Stmt3.close();
            Conn.close();
            if (Stmt1!=null) Stmt1.close();
            if (Stmt2!=null) Stmt2.close();
            if (Stmt3!=null) Stmt3.close();
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::delete");   
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
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Group g)throws AccountDataAccessException
   {
      if (g == null)
      {
         System.out.println("Group object is null");
         return (false);
      }

      if(loadByID(g.getID())==null)
         return (false);

      String sql = "update " + tablename1 + " set " +
                   "name = ?, description = ? where id = ?";

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
         throw new AccountDataAccessException ("Can not connect to database");
      }

       //System.out.println("Connected");

      try
      {
         Stmt.setString(1, g.getName());
         Stmt.setString(2, g.getDescription());
         Stmt.setString(3, g.getID());
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         throw new AccountDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::update");   
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
   * Delete all groups in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   :  boolean
   */
   public boolean deleteAllGroups()throws AccountDataAccessException
   {
      String sql1 = "delete from "+tablename1;
      String sql2 = "delete from "+tablename2;
      String sql3 = "delete from "+tablename4;

      try
      {
         rdbms.executeUpdate(sql1);
         rdbms.executeUpdate(sql2);
         rdbms.executeUpdate(sql3);

         return (true);

      }
      catch(Exception e)
      {
         throw new AccountDataAccessException(e.getMessage());
      }
   }

   /**
   * Loading all group objects of a user
   *@exception: DataAccessException
   *@param    : String uid : user id
   *@return   : Collection : the collection of group objects
   */
   public Collection loadGroupsByUser(String uid)throws AccountDataAccessException
   {
      if (uid == null)
      {
         System.out.println("User id  can't be null.");
         return null;
      }

      Collection groupsOfAUser = new ArrayList();

      String sql = "select "+ columnNamesConcat1 + " from " + tablename1 + ", " + tablename2 +
                   " where "+ tablename1 + ".id = " + tablename2 + ".gid" +
                   " and " +tablename2+".userid= ? ";

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
         throw new AccountDataAccessException ("Can not connect to database");
      }

      //System.out.println("Connected");
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, uid);
         rs = Stmt.executeQuery();
         while (rs.next())
         {
            Group g = new Group();
            setGroupValue(rs, g);
            groupsOfAUser.add(g);
         }
      }
      catch (Exception e)
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::loadGroupsByUser");   
            }   
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return groupsOfAUser;
   }

  /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Collection loadAllGroups()throws AccountDataAccessException
   {
      Collection groupsInDomain = new ArrayList();
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
            Group g = new Group();
            setGroupValue(rs, g);
            groupsInDomain.add(g);
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::loadAllGroups");   
            }            }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
      }
      return groupsInDomain;
   }

   /**
   * Add a user to a group (insert the data to usergroup table)
   *@exception: DataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean addUserToGroup(String uid,String gid)throws AccountDataAccessException
   {
      if ((uid == null)&&(gid == null))
      {
         System.out.println("gid or uid cann't be  null");
         return(false);
      }

      String sql1 ="select "+ columnNamesConcat2 + " from " + tablename2 +
                   " where userid = ? and gid=?" ;

      String sql2 ="insert into "+ tablename2 +" ("+columnNamesConcat2+") values (?,?)";

      //System.out.println("Inserting into table");
      boolean result = true;
      Connection Conn;
      PreparedStatement Stmt1;
      PreparedStatement Stmt2;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt1 = Conn.prepareStatement(sql1);
         Stmt2 = Conn.prepareStatement(sql2);
      }
      catch (Exception e)
      {
         result =false;
         //e.printStackTrace();
         throw new AccountDataAccessException ("Can not connect to database.");
      }

      //System.out.println("Connected");

      try
      {
         Stmt1.setString(1, uid);
         Stmt1.setString(2, gid);
         ResultSet rs = Stmt1.executeQuery();

         if(rs.next())
         {
            return (false);
         }

         Stmt2.setString(1, uid);
         Stmt2.setString(2, gid);
         Stmt2.executeUpdate();

         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result =false;
         throw new AccountDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::addUserToGroup");   
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
   *@exception: DataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean removeUserFromGroup(String uid,String gid)throws AccountDataAccessException
   {
      if ((uid == null)&&(gid == null))
      {  
         System.out.println("gid or uid cann't be null. ");
         return(false);
      }

      String sql ="delete from " + tablename2 + " where userid = ? and gid =?";

      Connection Conn = null;
      PreparedStatement Stmt = null;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result =  false;
         ExceptionBroadcast.print(e);
         // throw new AccountDataAccessException ("can not connect to database");
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
         //throw new AccountDataAccessException(e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimpleGroupDAOImpl::removeUserFromGroup");   
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
   *   test stub
   */
   public static void main(String[] args)
   {
      try
      {
         //=-=-=-=-=-=-=-*< step1 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         //1. new user and group objects

         Group g1 = new Group();
         g1.setID("SITWARE");
         g1.setName("Siteware");
         g1.setDescription("description1");

         Group g2 = new Group();
         g2.setID("CE2");
         g2.setName("ce2");
         g2.setDescription("description2");

         Group g3 = new Group();
         g3.setID("AMPA");
         g3.setName("ampa");
         g3.setDescription("description3");

         User u1 = new User();
         u1.setID("EDITH");
         u1.setFirstName("Edith");
         u1.setLastName("Super");
         u1.setPassword("111",true);
         u1.setDescription("d1");

         User u2 = new User();
         u2.setID("SUSAN");
         u2.setFirstName("Susan");
         u2.setLastName("Yen");
         u2.setPassword("smile",true);
         u2.setDescription("description for what?");

         User u3 = new User();
         u3.setID("TUNG");
         u3.setFirstName("Tung");
         u3.setLastName("lee");
         u3.setPassword("HHHH222",true);
         u3.setDescription("description for what?");

         //=-=-=-=-=-=-=-*< step2 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.new UserDAO and GroupDAO objects
         UserDAO  udao = null;//new UserDAO("testdomain");
         GroupDAO gdao = null;//new GroupDAO("testdomain");

         //=-=-=-=-=-=-=-*< step3 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*


         //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1. insert user data(OK)
         // 2. insert group data

         System.out.println("insert user1 data?--->"+udao.insert(u1));
         System.out.println("insert user1 data?--->"+udao.insert(u2));

         System.out.println("insert group1 data?--->"+gdao.insert(g1));//m4
         System.out.println("insert group2 data?--->"+gdao.insert(g2));//

         //=-=-=-=-=-=-=-*< step5 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1. add user&group data to  usergroup table(OK)

         //System.out.println(gdao.addUserToGroup(u1.getID(),g1.getID()));       //adding u1 to g1
         //System.out.println(gdao.addUserToGroup(u2.getID(),g1.getID()));       //adding u2 to g1
         //System.out.println(gdao.addUserToGroup(u2.getID(),g2.getID()));       //adding u2 to g2
         //System.out.println(gdao.addUserToGroup(u3.getID(),g3.getID()));       //adding u3 to g3

         //=-=-=-=-=-=-=-*< step6 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1. remove user&group data from usergroup table

         //  System.out.println(gdao.removeUserFromGroup(u2.getID(),g1.getID()));  //remove u1 to g2

         //=-=-=-=-=-=-=-*< step7 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.loadAllGroup()(OK)
         /*
         Collection groups = gdao.loadAllGroups();
         Iterator iterator = groups.iterator();
         while (iterator.hasNext())
         {
            Group g = (Group)iterator.next();
            System.out.println("gid = " + g.getID());
            System.out.println("name = "+ g.getName());
            System.out.println("description = "+ g.getDescription()+"\n");
         }
         */
         //=-=-=-=-=-=-=-*< step8 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.loadGroupsByUser()(Ok)
         /*
         Collection groups = gdao.loadGroupsByUser("EDITH");
         Iterator iterator = groups.iterator();
         while (iterator.hasNext())
         {
            Group g = (Group)iterator.next();
            System.out.println("gid = "+ g.getID());
            System.out.println("name = "+ g.getName());
            System.out.println("description = "+ g.getDescription());
         }
         */
         //=-=-=-=-=-=-=-*< step9 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.update(Group g);(OK)
               
         g1.setName("SITWARE");
         g1.setDescription("des1");
         System.out.println("Update the group data? "+ gdao.update(g1));
              
         //=-=-=-=-=-=-=-*< step10 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.delete(String gid); (OK)
         // 2.loadByID(String gid);
             
         //if(gdao.delete("SITWARE"))
         // System.out.println(gdao.loadByID("CE2"));
               
         //=-=-=-=-=-=-=-*< step11 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.deleteAllGroup(String gid);

         System.out.println("Delete all groups? --->" + gdao.deleteAllGroups());

         //=-=-=-=-=-=-=-*< step12 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
         // 1.UserDAO::dropUserTable();
         // 2.GroupDAO::dropGroupTable();
         // 3.GroupDAO::droupUserGroupTable();
         // 4.GroupDAO::dropServiceTable();
         /*
         System.out.println("Drop the user table? --->"+ udao.dropUserTable());
         System.out.println("Drop the group table? --->"+ gdao.dropGroupTable());
         System.out.println("Drop a new usergroup table? --->"+ gdao.dropUsergroupTable());
         System.out.println("Drop a new Service table? --->"+ gdao.dropServiceTable());
         */
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }
}