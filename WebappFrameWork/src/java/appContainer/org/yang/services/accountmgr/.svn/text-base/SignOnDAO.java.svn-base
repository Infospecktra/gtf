package org.yang.services.accountmgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.yang.services.dbService.RDBMS;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.DataAccessException;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DBConnectionCount;


/**
 * <p>Title: actrellis</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: screamingmedia inc</p>
 * @author Lei Liu
 * @version 1.0
 * @testcase org.test.org.yang.services.accountmgr.TestSignOnDAO
 */

public class SignOnDAO  implements java.io.Serializable{

   RDBMS rdbms = RDBMSFactory.getInstance().getSystemRDBMS();

   private static final String columnNamesUser = " userID, sessionID ";
   private static final String columnNamesOccupy = " sessionID, sectionID, userID ";
   private  String domain = null;

   private  String usertable = null;
   private  String occupytable = null;

  /**
   * Constructor
   */
   public SignOnDAO(String domain){
   	if((domain==null)||(domain.equals("")))
	{
	    System.out.println("The String of domain name cann't be null or space. ");
	    return ;
	}

        this.domain = domain;
        usertable= this.domain + "_signon_user" ;
        occupytable= this.domain + "_signon_occupy" ;

        createTables();
        patch();
   }

  /**
   * Patch - for compatibility
   */
  public boolean patch(){//throws AccountDataAccessException{

  	String	sql1 ="ALTER TABLE " + occupytable +
                     " add userID varchar(255) ";
        try{
        	rdbms.executeUpdate(sql1);
      	}catch(Exception e){
                //e.printStackTrace();
 		//throw new AccountDataAccessException(e.getMessage());
   	}
   	return true;
  }

////
//    /**
//    * set the value of User object.
//    *@exception:
//    *@param
//    *@return:
//    */
// private void setUserValue(ResultSet rs, SignOnUser user){
//    	try{
//    	        int i=1;
//    		user.userID = rs.getString(i++);
//    		user.sessionID = rs.getString(i++);
//    	}catch(Exception e){ com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);}
//    }


  /**
   * create a new user table in database
   *@exception:AccountDataAccessException
   *@param
   *@return:   boolean
   */
  public boolean createTables(){//throws AccountDataAccessException{

  	String	sql ="CREATE TABLE " + usertable +
                     "(userID varchar(255) not null , sessionID varchar(255) not null," +
                     " primary key(sessionID))";
        try{
        	rdbms.executeUpdate(sql);
      	}catch(Exception e){
                //e.printStackTrace();
 		//throw new AccountDataAccessException(e.getMessage());
   	}

  	String	sql1 ="CREATE TABLE " + occupytable +
                     "(sessionID varchar(255) not null , sectionID varchar(255) not null," +
                     " userID varchar(255) not null, primary key(sectionID))";
        try{
        	rdbms.executeUpdate(sql1);
      	}catch(Exception e){
                //e.printStackTrace();
 		//throw new AccountDataAccessException(e.getMessage());
   	}
   	return true;
  }

  /**
   * Drop a user table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
  public boolean dropTables() {//throws AccountDataAccessException{

  	String	sql1 ="DROP TABLE " + usertable ;
  	String	sql2 ="DROP TABLE " + occupytable ;

        try{
        	rdbms.executeUpdate(sql1);
        	rdbms.executeUpdate(sql2);

      	}catch(Exception e){
 		//throw new AccountDataAccessException(e.getMessage());
   	}
   	return true;
  }


/**
 * Note: for mysql, rollback does not work for ISAM table type.
 *
 * @param occupy
 * @return
 * @throws AccountDataAccessException
 */
  public boolean occupySections(SignOnOccupy[] occupy)throws AccountDataAccessException,DataAccessException{

      Connection conn;
      if (occupy.length == 0) return true;
      String session = occupy[0].sessionID;
      try{
          conn = rdbms.getDBConnection();
      }
      catch(Exception e)
      {
          throw new DataAccessException("Fail to connect database. ");
      }

      String sql = "insert into "+occupytable+"("
                + columnNamesOccupy+") values (?,?,?)";
      try {
        PreparedStatement Stmt = conn.prepareStatement(sql);
        for (int i=0; i<occupy.length;i++) {
              Stmt.setString(1, occupy[i].sessionID);
              Stmt.setString(2, occupy[i].sectionID);
              Stmt.setString(3, occupy[i].userID);
              Stmt.executeUpdate();
        }
        //conn.commit();
      }catch (SQLException e) {
        //&System.out.println("<--Occupy Failed--roll back.-->");
        try {
          //Can not rollback, because MYSQL does not support rollback.
          //conn.rollback();

          //alternative
          releaseBySession(session);
        } catch (Exception ee) {
          ee.printStackTrace();
          throw new AccountDataAccessException ("Transaction Rollback Failed");
        }
        return false;
      }finally {
         try
         {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SignOnDAO::occupySections");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}      	
      	
        //try { conn.close(); } catch (Exception e) {ExceptionBroadcast.print(e);}
        
      }
      //&System.out.println(" -- Session occupied. -- ");
      return true;
  }

  public String getUserBySection(String section) throws DataAccessException {
      Connection conn;
      try{
          conn = rdbms.getDBConnection();
      }
      catch(Exception e)
      {
          throw new DataAccessException ("Connect  to database Failed");
      }

      String sql = "select userID from "+occupytable
        +" where sectionID = ?";

      String result = null;

      try {
        PreparedStatement Stmt = conn.prepareStatement(sql);
        Stmt.setString(1, section);
        ResultSet rs = Stmt.executeQuery();

        if (rs.next()) result = rs.getString(1);

        rs.close();
        //conn.commit();
      }catch (SQLException e) {
        ExceptionBroadcast.print(e);
        return null;
      }finally {
        //try { conn.close(); } catch (Exception e) {ExceptionBroadcast.print(e);}
         try
         {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SignOnDAO::getUserBySection");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}              
      }

      return result;
  }

  public boolean deleteSection(String section, String session)throws DataAccessException{
      boolean result = false;
      try{
          if (session != null)
              result = deleteSection2(section,session);
          else
              result = deleteSection1(section);
      }
      catch(Exception e)
      {
          throw new DataAccessException("Delete section failed");
      }
      return result;
  }

  boolean deleteSection2(String section, String session)throws DataAccessException{
    String sql = "delete from "+occupytable+" where sectionID = ? and sessionID = ?";
    String[] values = new String[2];
    values[0] = section;
    values[1] = session;
    try{
        rdbms.executeUpdate(sql, values);
    }
    catch(Exception  e)
    {
        throw new DataAccessException ("Delete section data failed");
    }
    return true;
  }

  boolean deleteSection1(String section)throws DataAccessException {
    String sql = "delete from "+occupytable+" where sectionID = ?";
    String[] values = new String[1];
    values[0] = section;
    try{
        rdbms.executeUpdate(sql, values);
    }
    catch(Exception  e)
    {
        throw new DataAccessException ("Delete section data failed");
    }
    return true;
  }

  public void insertUser(SignOnUser user)throws DataAccessException{
    String sql = "insert into "+usertable + "(" + columnNamesUser+")"
                + "values (?, ?)";
    String[] values = new String[2];
    values[0] = user.userID;
    values[1] = user.sessionID;
    try{
        rdbms.executeUpdate(sql, values);
    }
    catch(Exception  e)
    {
        throw new DataAccessException ("Delete section data failed");
    }

  }

  public void deleteUserBySession(String session)throws DataAccessException{
    String sql = "delete from " + usertable + " where sessionID = ? ";
    String[] values = new String[1];
    values[0] = session;
    try{
        rdbms.executeUpdate(sql, values);
    }
    catch(Exception  e)
    {
        throw new DataAccessException ("Delete section data failed");
    }
  }

  public void releaseBySession(String session)throws DataAccessException{
    String sql = "delete from " + occupytable + " where sessionID = ? ";
    String[] values = new String[1];
    values[0] = session;
    try{
        rdbms.executeUpdate(sql, values);
    }
    catch(Exception  e)
    {
        throw new DataAccessException ("Delete section data failed");
    }
  }

  public List getOccupiedSections(String uid)throws DataAccessException{
      Connection conn ;

      try{
          conn = rdbms.getDBConnection();
      }
      catch(Exception e)
      {
          throw new DataAccessException(e.getMessage());
      }
      String sql = "select sectionID from "+ occupytable
        +" where userID = ? ";

      List result = new ArrayList();

      try {
        PreparedStatement Stmt = conn.prepareStatement(sql);
        Stmt.setString(1, uid);
        ResultSet rs = Stmt.executeQuery();

        while (rs.next()) {
          String section = rs.getString(1);
          result.add(section);
        }

        rs.close();
        return result;
      }catch (SQLException e) {
        ExceptionBroadcast.print(e);
        return null;
      }finally {
        //try { conn.close(); } catch (Exception e) {ExceptionBroadcast.print(e);}
         try
         {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SignOnDAO::getOccupiedSections");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}     
     }

  }


  public int countUser(String uid)throws AccountDataAccessException{
      Connection conn;

      try{
          conn = rdbms.getDBConnection();
      }catch(Exception e)
      {
           throw new AccountDataAccessException ("Database Connection Failed");
      }
      String sql = "select count(userID) from " + usertable
        +" where userID = ? ";

      int result = 0;

      try {
        PreparedStatement Stmt = conn.prepareStatement(sql);
        Stmt.setString(1, uid);
        ResultSet rs = Stmt.executeQuery();

        if (rs.next()) result = rs.getInt(1);

        rs.close();
        //conn.commit();
      }catch (SQLException e) {
        ExceptionBroadcast.print(e);
        return 0;
      }finally {
         try
         {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("SignOnDAO::countUser");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}     
         //try { conn.close(); } catch (Exception e) {ExceptionBroadcast.print(e);}
      }

      return result;

  }

}