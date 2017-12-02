package org.yang.services.dbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DBConnectionCount;
//import com.screamingmedia.swapi.core.contentStore.ContentDBMS;
/**
 * Title:        Siteware Enterprise
 * Description:  Siteware Enterprise Edition.
 * Apr 2001
 * Copyright:    Copyright (c) 2000
 * Company:      ScreamingMedia
 * @author Lei Liu
 * @version 1.0
 * @testcase org.test.org.yang.services.dbService.TestIDGenerator
 */


public class IDGenerator {
  public static final String ID_SEQUENCE_TABLE_NAME = "Content_IDSequence";
  private static final Random rand = new Random();
  private static final int RANDOM_LIMIT = 10000;

  /**
   * Create unique ID. User provide a String parameter as the prefix,
   * the method will create an ID. The ID looks like "prefix_123456789_234234523".
   * The total length will be less than length(prefix)+28
   */
  public static String getUniqueIDLong(String prefix) {
    StringBuffer sb = new StringBuffer(prefix+"_");
    long time = Calendar.getInstance().getTime().getTime();

    sb.append(time+"_");

    String randomStr = "" + rand.nextInt();
    randomStr = randomStr.replace('-', 'M');

    sb.append(randomStr);
    return sb.toString();
  }

  /**
   * Create unique ID. User provide a String parameter as the prefix,
   * the method will create a unique ID. The ID looks like "prefix_123".
   * This is an updated version that uses a db table, to reduce the ID length, because
   * oracle has table size limit.
   */
  public static String getUniqueID(String prefix) {
    try {
      int next = rand.nextInt(RANDOM_LIMIT) % 100;
      String key = prefix;
      if ( (key==null) || (key.equals(""))) key = "default";
      String value = getIDSequence(key,1)+"_"+next;
      if ((prefix==null) || (prefix.equals(""))) return value;
      else return prefix + "_" + value;
    } catch (DataAccessException e) {
      System.out.println("error reading db in IDGenerator.");
      return getUniqueIDLong(prefix);
    }
  }

  /**
   * Return true if table already exists; return false if not.
   */
  private static boolean isTableExist(){
    RDBMS dbms = RDBMSFactory.getInstance().getDataRDBMS();
    try{
      StringBuffer sbSQL = new StringBuffer();
      sbSQL.append("SELECT COUNT(*) FROM ").append(ID_SEQUENCE_TABLE_NAME);
      dbms.executeQuery(sbSQL.toString());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Return true if table entry corresponding to queueID already exists;
   * return false if not.
   */
  private static boolean isQueueExist(String qID){
    RDBMS dbms = RDBMSFactory.getInstance().getDataRDBMS();
    Connection conn = null;
    Statement stat = null;
    ResultSet oRS = null;
    try{
      conn = dbms.getDBConnection();
      stat = conn.createStatement();

      StringBuffer sbSQL = new StringBuffer();
      sbSQL.append("SELECT nextID FROM ").append(ID_SEQUENCE_TABLE_NAME)
           .append(" WHERE queueID = '").append(qID).append("'");
      oRS = stat.executeQuery(sbSQL.toString());

      if(oRS.next()){
        if(oRS.getInt(1) != 0) return true;
      }
    } catch (Exception e) {
     ExceptionBroadcast.print(e);
    } finally {
      try {
          if (oRS!=null) oRS.close();
      } catch (Exception e) {ExceptionBroadcast.print(e);}
      try {
          if (stat !=null) stat.close();
      } catch (Exception e) {ExceptionBroadcast.print(e);}
      try {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("IDGenerator::isQueueExist");   
            }                
     } catch (Exception e) {ExceptionBroadcast.print(e);}

    }
    return false;
  }

  /**
   * Return true if table entry corresponding to queueID already exists;
   * return false if not.
   */
  private static void updateIDSequence(String qID, int nextID) throws DataAccessException{
    RDBMS dbms = RDBMSFactory.getInstance().getDataRDBMS();
    try{
      StringBuffer sbSQL = new StringBuffer();
      sbSQL.append("UPDATE ").append(ID_SEQUENCE_TABLE_NAME).
            append(" SET nextID = ").append(nextID).
            append(" WHERE queueID = '").append(qID).append("'");
      dbms.executeUpdate(sbSQL.toString());

    } catch (Exception e) {
      throw new DataAccessException(e.getMessage());
    }
  }

  /**
   * This method will get the next ID number from a specific queue that's
   * stored in a database table; then the nextID column of that queue will
   * be updated according to the number of ids requested.
   * The valid next ID number will be returned.
   *
   * @qID: the queue ID used to identify the specific queue to work on.
   * @num: number of IDs requested by user.
   */
  public static synchronized int getIDSequence(String qID, int num) throws DataAccessException{
    RDBMS dbms = RDBMSFactory.getInstance().getDataRDBMS();
    Statement stat = null;
    Connection conn = null;
    ResultSet oRS = null;
    int nextID = 0;
    try{

      //1. If the ID sequence table doesnt exist, create one.
      if(! isTableExist()){
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append("CREATE TABLE ").append(ID_SEQUENCE_TABLE_NAME).
              append(" (queueID varchar(255) not null,").
              append("nextID integer,").
              append("primary key (queueID))");

        dbms.executeUpdate(sbSQL.toString());
      }

      //2. If the entry corresponding to qID doesnt exist, create a line and
      //   initialize it.
      if(! isQueueExist(qID)){
        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append("INSERT INTO ").append(ID_SEQUENCE_TABLE_NAME).
              append(" (queueID, nextID)").append(" VALUES('").
              append(qID).append("',1)");

        dbms.executeUpdate(sbSQL.toString());
      }

      //Get DB connection: connect to content db.
      conn = dbms.getDBConnection();

      //3. Search the ID sequence table for the next ID; update the nextID
      //   column accordingly.
      StringBuffer sbSQL = new StringBuffer();

      sbSQL.append("SELECT nextID FROM ").append(ID_SEQUENCE_TABLE_NAME).
            append(" WHERE queueID = '").append(qID).append("'");

      stat = conn.createStatement();

      oRS = stat.executeQuery(sbSQL.toString());
      if(oRS.next()){
        nextID = oRS.getInt(1);
        int newID = nextID + num;
        updateIDSequence(qID, newID);
      }

      return nextID;

    } catch (Exception e) {
      ExceptionBroadcast.print(e);
      throw new DataAccessException(e.getMessage());
    }finally {
      try{
        if (oRS!=null) oRS.close();
      }catch (Exception e){
        ExceptionBroadcast.print(e);
      }
      try {
        if (stat!=null) stat.close();
      }catch (Exception e){
        ExceptionBroadcast.print(e);
      }
      try {
            if (conn!=null)
            {
               conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("IDGenerator::getIDSequence");   
            }    
      }catch (Exception e){
        ExceptionBroadcast.print(e);
      }
    }//end of finally block.
  }

  public static void main(String[] args) {
    System.out.println(getUniqueID("puller"));
    try{
      int id = getIDSequence("yao_test",2);
      System.out.println("id= "+id);
    }catch(Exception e){
     ExceptionBroadcast.print(e);
    }
  }
}