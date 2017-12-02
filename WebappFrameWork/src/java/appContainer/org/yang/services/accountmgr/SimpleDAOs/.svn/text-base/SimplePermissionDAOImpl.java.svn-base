package org.yang.services.accountmgr.SimpleDAOs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;
import org.yang.services.dbService.RDBMS;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.accountmgr.PermissionDAO;
import org.yang.services.accountmgr.Permission;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.dbService.DBConnectionCount;

/**
 * @testcase org.test.org.yang.services.accountmgr.SimpleDAOs.TestSimplePermissionDAOImpl
 
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class SimplePermissionDAOImpl implements PermissionDAO
{
   private  String domain    = null;
   private  String tablename = null;
   private static final String columnNamesConcat = "id,groupid,serviceid,areas,operations";

   RDBMS rdbms = null;

   /**
   *   constructor
   */
   public SimplePermissionDAOImpl(String domain, RDBMS rdbms)
   {
      if((domain==null)||(domain.equals("")))
      {
         System.out.println("The String of domain name cann't be null or space. ");
         return ;
      }
      this.domain = domain;
      tablename   = this.domain + "_user_permission";
      this.rdbms = rdbms;
   }

   /**
   * Get the data from ResultSet object to set the permission value.
   *
   *@exception:AccountDataAccessException
   *@param : ResultSet rs ; Permission p
   *@return: boolean
   */
   private void setPermissionValue(ResultSet rs,Permission p)
   {
      try
      {
         int i=1;
         p.setId(rs.getString(i++));
         p.setGroupId(rs.getString(i++));
         p.setServiceId(rs.getString(i++));
         p.setAreasConcat(rs.getString(i++));
         p.setOperationsConcat((rs.getString(i++)));
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   /**
   * create a new permission table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
   public boolean createPermissionTable()//throws AccountDataAccessException
   {
      String sql ="CREATE TABLE " + tablename +
                  "(id char(255)not null, groupid char(255),serviceid char(255)" +
                  ",areas char(255) null, operations char(255) null,primary key(id))";
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return (true);
   }

   /**
   * Drop a permission table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropPermissionTable()// throws AccountDataAccessException
   {
      String sql ="DROP TABLE " + tablename ;
      try
      {
         rdbms.executeUpdate(sql);
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
      }
      return (true);
   }

   /**
   * loading permission object by permission id
   * @exception: <{org.yang.services.accountmgr.AccountDataAccessException}>
   * @param      String pid
   * @return:    Permission object
   */
   public Permission loadByID(String pid)throws AccountDataAccessException
   {
      Permission p = new Permission();
      p.setId(pid);

      String sql = "select " + columnNamesConcat + " from " + tablename +
                   " where id = ? ";

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
      if (pid == null)
      {
         System.out.println("permission id is null");
         return null;
      }
      
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, pid);
         rs = Stmt.executeQuery();

         if(rs == null) return null;
         if(!(rs.next())) return null;
         else{setPermissionValue(rs,p);}

      }
      catch (Exception e)
      {
         System.out.println("Loading failed:"+e.getMessage());
      }
      finally
      {
         try
         {
            rs.close();
            Stmt.close();
            Conn.close();
         }
         catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return p;
   }

   /**
   * Insert the new data of Permission object  into data base.
   *
   * @exception: <{AccountDataAccessException}>
   * @param:     Permission p
   * @return:    boolean
   */
   public boolean insert(Permission p)throws AccountDataAccessException
   {
      if (p == null)
      {
         System.out.println("permission object is null");
         return (false);
      }

      if(loadByID(p.getId())!=null)
         return (false);

      String sql = "insert into " + tablename +
                   "("+ columnNamesConcat+ ") values (?,?,?,?,?)";

      //System.out.println("Inserting into table");
      //System.out.println("sql in PermissionDAO"+sql);
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
         Stmt.setString(1, p.getId());
         Stmt.setString(2, p.getGroupId());
         Stmt.setString(3, p.getServiceId());
         Stmt.setString(4, p.getAreasConcat());
         Stmt.setString(5, p.getOperationsConcat());

         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         System.out.println("Insert failed:"+e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::insert");   
            }            

         } catch (Exception e) {ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
   * Delete a permission data from data base
   *@exception: <{AccountDataAccessException}>
   *@param    : String pid
   *@return:    boolean
   */
   public boolean delete(String pid)throws AccountDataAccessException
   {
      String sql = "delete from " + tablename + " where id = ?";

      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt  = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      if (pid == null||pid.equals(""))
      {
         System.out.println("permission id can't be null or space.");
         return (false);
      }

      try
      {
         Stmt.setString(1, pid);
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};

      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         //System.out.println("delete failed");
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::delete");   
            }            
         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return (result);
   }

  /**
   * Delete a permission record by Area ID.
   * Author: lei Liu
   *@exception: <{AccountDataAccessException}>
   *@param    : String aid
   *@return:    boolean
   */
   public boolean deleteByArea(String aid)throws AccountDataAccessException
   {
      String sql = "delete from " + tablename + " where areas = ?";

      Connection Conn;
      PreparedStatement Stmt;
      boolean result = true;
      try
      {
         Conn  = rdbms.getDBConnection();
         Stmt  = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      if (aid == null||aid.equals(""))
      {
         System.out.println("permission id can't be null or space.");
         return (false);
      }

      try
      {
         Stmt.setString(1, aid);
         Stmt.executeUpdate();
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         //System.out.println("delete failed");
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::deleteByArea");   
            }            
         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
   * Update a permission data from data base
   *@exception: <{AccountDataAccessException}>
   *@param    : Permission p
   *@return:    boolean
   */
   public boolean update(Permission p)throws AccountDataAccessException
   {
      if (p == null)
      {
         System.out.println("permission object is null");
         return false;
      }

      if(loadByID(p.getId())==null)
         return (false);

      String sql = "update " + tablename + " set " +
                   "groupid = ?, serviceid = ?, areas = ?, operations=?"+
                   " where id = ?";

      //System.out.println("Updating table...");
      boolean result = true;
      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql);
      }
      catch (Exception e)
      {
         result = false;
         ExceptionBroadcast.print(e);
         throw new AccountDataAccessException ("can not connect to database");
      }

      //System.out.println("Connected");

      try
      {
         Stmt.setString(1, p.getGroupId());
         Stmt.setString(2, p.getServiceId());
         Stmt.setString(3, p.getAreasConcat());
         Stmt.setString(4, p.getOperationsConcat());
         Stmt.setString(5, p.getId());
         Stmt.executeUpdate();

         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         result = false;
         //System.out.println("Update failed");
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::update");   
            }            
         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return (result);
   }

   /**
   * Delete all permissions in this domain
   *@exception: <{AccountDataAccessException}>
   *@param    :
   *@return   :  boolean
   */
   public boolean deleteAllPermissions()throws AccountDataAccessException
   {
      String sql = "delete from "+tablename;
      try
      {
         rdbms.executeUpdate(sql);
         return (true);
      }
      catch(Exception e)
      {
         throw new AccountDataAccessException(e.getMessage());
      }
   }

   /**
   * Loading all the permission objects in this domain
   *@exception: <{AccountDataAccessException}>
   *@param    :
   *@return   : Collection : The collesction of Permission objects
   */
   public Collection loadAllPermissions()throws AccountDataAccessException
   {
      Collection PermissionsInDomain = new ArrayList();
      String sql = "select "+ columnNamesConcat + " from " + tablename;
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
            Permission p = new Permission();
            setPermissionValue(rs, p);
            PermissionsInDomain.add(p);
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::loadAllPermissions");   
            }            
         } catch (Exception e) {ExceptionBroadcast.print(e); }
      }
      return PermissionsInDomain;
   }

   /**
   * Loading all the permission objects by group id and sid
   *@exception: <{AccountDataAccessException}>
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Collection loadByGidSid(String gid,String sid)throws AccountDataAccessException
   {
      Collection Permissions = new ArrayList();

      String sql = "select " + columnNamesConcat + " from " + tablename +
                   " where groupid = ? and serviceid =? " ;

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

      if ((gid == null)&&(gid.equals(""))&&(sid == null)&&(sid.equals("")))
      {
         System.out.println("gid or sid cann't be  null or space");
         return (null);
      }

      ResultSet rs = null;
      try
      {
         Stmt.setString(1, gid);
         Stmt.setString(2,sid);
         rs = Stmt.executeQuery();
         while (rs.next())
         {
            Permission p = new Permission();
            setPermissionValue(rs, p);
            Permissions.add(p);
         }
      }
      catch (Exception e)
      {
         System.out.println("Loading failed:"+e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::loadByGidSid");   
            }            
         } catch (Exception e) {ExceptionBroadcast.print(e); }
      }

      return Permissions;
   }

   /**
   * Loading all the permission objects by group id
   *@exception: <{AccountDataAccessException}>
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Collection loadByGid(String gid)throws AccountDataAccessException
   {
      Collection Permissions = new ArrayList();
      String sql = "select " + columnNamesConcat + " from " + tablename +
                   " where groupid = ?" ;
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

      if ((gid == null)&&(gid.equals("")))
      {
         System.out.println("gid cann't be  null or space");
         return (null);
      }
      
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, gid);
         rs = Stmt.executeQuery();
         while (rs.next())
         {
            Permission p = new Permission();
            setPermissionValue(rs, p);
            Permissions.add(p);
         }
      }
      catch (Exception e)
      {
         System.out.println("Loading failed:"+e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::loadByGid");   
            }            
         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return Permissions;
   }

   public Collection loadGIDByService(String sid, String area, String operation)throws AccountDataAccessException
   {
      Set result = new HashSet();
      String sql = "select groupid, operations from " + tablename +
                   " where serviceid=? and areas = ?" ;

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

      if ((sid == null)||(sid.equals("")))
      {
         System.out.println("csid cann't be  null or space");
         return (null);
      }
      
      ResultSet rs = null;
      try
      {
         Stmt.setString(1, sid);
         Stmt.setString(2, area);
         rs = Stmt.executeQuery();
         if(rs == null) return null;

         while(rs.next())
         {
            String gid = rs.getString(1);
            String op = rs.getString(2);
            
            if ((op!=null)&& (op.indexOf(operation) >=0 )) //match
               result.add(gid);
          }
          return result;
      }
      catch (Exception e)
      {
         System.out.println("Loading failed:"+e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::loadGidByService");   
            }            


         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
   }

   /**
   * Loading the permission objects by component service id (in areas column  )
   *@exception: <{AccountDataAccessException}>
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Permission loadByComponentServiceID(String csid)throws AccountDataAccessException
   {
      Permission p = null;
      String sql = "select " + columnNamesConcat + " from " + tablename +
                   " where areas = ?" ;
      //&System.out.println("sql = " + sql);
      ////&System.out.println("Inserting into table");

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

      //&System.out.println("Connected");

      if ((csid == null)&&(csid.equals("")))
      {
          System.out.println("csid cann't be  null or space");
          return (null);
      }
      
      ResultSet rs = null;
      try
      {
         //&System.out.println("csid = " + csid);
         Stmt.setString(1, csid);
         //&System.out.println("Stmt = " + Stmt);
         rs = Stmt.executeQuery();

         p = new Permission();
         if(rs == null) return null;
         if(!(rs.next())) return null;
         else{setPermissionValue(rs,p);}
      }
      catch (Exception e)
      {
         System.out.println("Loading failed:"+e.getMessage());
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
               DBConnectionCount.printConnectionCount("SimplePermissionDAOImpl::loadByComponentSerivceID");   
            }            

         } catch (Exception e) { ExceptionBroadcast.print(e); }
      }
      return p;
   }

   /**
   *   test stub
   */
   public static void main(String[] args)
   {
      try
      {
         //*-=-=-=-=-=-=-=-=-=-<step1: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         //1.new Permission object
         Permission p1 = new Permission();
         p1.setId("P1");
         p1.setGroupId("AUDIT");
         p1.setServiceId("");
         p1.setAreasConcat("");
         p1.setOperationsConcat("");

         Permission p2 = new Permission();
         p2.setId("P2");
         p2.setGroupId("DB");
         p2.setServiceId("Edit");
         p2.setAreasConcat("news,music");
         p2.setOperationsConcat("add photo,add title");

         Permission p3 = new Permission();
         p3.setId("P3");
         p3.setGroupId("WR");
         p3.setServiceId("Write");
         p3.setAreasConcat("");
         p3.setOperationsConcat("add content body");

         Permission p4 = new Permission();
         p4.setId("P004");
         //p4.setGroupId("G002");
         p4.setServiceId("Write");
         p4.setAreasConcat("null");
         p4.setOperationsConcat("add content body");

         //*-=-=-=-=-=-=-=-=-=-<step2: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         //  1.new PermissionDAO        (OK)
         //  2.Creating permission table
         //  3.PermissionDAO::insert(Permission)
         //
         PermissionDAO  pdao = null;//new PermissionDAO("testdomain");
         System.out.println("Create a permission table? " + pdao.createPermissionTable());
         System.out.println("Insert p1?"+pdao.insert(p1));
         System.out.println("Insert p2?"+pdao.insert(p2));
         System.out.println("Insert p3?(null)"+pdao.insert(p3));
         System.out.println("Insert p4?(\"null\")"+pdao.insert(p1));

         //*-=-=-=-=-=-=-=-=-=-<step3: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         //  1.PermissionDAO::loadAllPermissions()

          Collection permissions = pdao.loadAllPermissions();
          Iterator iterator = permissions.iterator();
          while (iterator.hasNext())
          {
             Permission p = (Permission)iterator.next();
             System.out.println("id = " + p.getId());
             System.out.println("gid = " + p.getGroupId());
             System.out.println("service id = " + p.getServiceId());
             System.out.println("areas = " + p.getAreasConcat());
             System.out.println("operations = " + p.getOperationsConcat());
          }

         //*-=-=-=-=-=-=-=-=-=-<step4: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         //  1.PermissionDAO::loadByGid(String gid)  (OK)
               
         Collection permissions1 = pdao.loadByGid(p3.getGroupId()); //load p2
         Iterator iterator1= permissions1.iterator();
         while (iterator1.hasNext())
         {
            Permission p = (Permission)iterator1.next();
            System.out.println("id = " + p.getId());
            System.out.println("gid = " + p.getGroupId());
            System.out.println("service id = " + p.getServiceId());
            System.out.println("areas = " + p.getAreasConcat());
            System.out.println("operations = " + p.getOperationsConcat());
         }
               
         //*-=-=-=-=-=-=-=-=-=-<step4: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         //  1.PermissionDAO::loadByGidSid(String gid, String sid)
         /*
         Collection permissions = pdao.loadByGidSid(p3.getGroupId(), p1.getServiceId()); //load p2
         Iterator iterator = permissions.iterator();
         while (iterator.hasNext())
         {
            Permission p = (Permission)iterator.next();
            System.out.println("id = " + p.getId());
            System.out.println("gid = " + p.getGroupId());
            System.out.println("service id = " + p.getServiceId());
            System.out.println("areas = " + p.getAreasConcat());
            System.out.println("operations = " + p.getOperationsConcat());
         }
         */
         //*-=-=-=-=-=-=-=-=-=-<step4: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         // 1.PermissionDAO::update(Permission)(OK)
         /*
         p2.setGroupId("G003");
         p2.setServiceId("Select");
         p2.setAreasConcat("law,commercial");
         p2.setOperationsConcat("select photo,select content");
         System.out.println("Update the permission data? "+ pdao.update(p2));//m3
         */
         //*-=-=-=-=-=-=-=-=-=-<step5: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         // 1.PermissionDAO::delete(String pid)   (OK)
         // 2.PermissionDAO::loadByID(String pid)

         //if(pdao.delete("P2"))  //m5
         // System.out.println(pdao.loadByID("P2")); //should be null  m6

         //*-=-=-=-=-=-=-=-=-=-<step6: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         // 1.PermissionDAO::deleteAllPermissions();

         //System.out.println("Delete all permissions? --->"+ pdao.deleteAllPermissions());// m6

         //*-=-=-=-=-=-=-=-=-=-<step7: >-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=@@
         // 1.PermissionDAO::dropPermissionTable()
         //System.out.println("Drop the table? --->"+ pdao.dropPermissionTable());
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }
}