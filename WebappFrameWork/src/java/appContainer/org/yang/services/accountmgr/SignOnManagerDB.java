package org.yang.services.accountmgr;

import java.util.List;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DataAccessException;


/**
 * <p>Title: SignOnManager DB version</p>
 * <p>Description: This is the clustering-safe version of SignOnManager. It is
 * slower when used in single server, since everything goes into DB</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: screamingmedia inc</p>
 * @author Lei Liu
 * @version 1.0
 * @testcase org.test.org.yang.services.accountmgr.TestSignOnManagerDB
 */


public class SignOnManagerDB extends SignOnManager{

  private String domain = null;

  SignOnDAO dao = null;


  /**  Constructor
   *   @param d domain ID*/
  SignOnManagerDB(String d){
    domain=d;
    dao = new SignOnDAO(domain);
  }

  /**
   * This method enable user session to occupy a group of sections. It succeed only
   * if it successfully got all sections. When succeed, return true. If not, release any
   * occupied sections and return false.
   * @param sec_ids: the section ids that user wants to occupy.
   * @param session_id: the user session id that wants to occupy the section.
   */
  public synchronized boolean occupy(String[] sec_ids, String session_id, String userID){
    if (sec_ids==null) return true;
    if (sec_ids.length == 0) return true;
    boolean result = false; 
    SignOnOccupy[] occupy = new SignOnOccupy[sec_ids.length];

    //Try occupy(insert), if fail, exception will be thrown.
    for (int i=0; i<sec_ids.length; i++) {
      occupy[i] = new SignOnOccupy();
      occupy[i].sectionID = sec_ids[i];
      occupy[i].sessionID = session_id;
      occupy[i].userID = userID;
    }

    try {
      result = dao.occupySections(occupy);
    } catch (Exception e) {
       ExceptionBroadcast.print(e);
    }

    return result;
  }

  /**
   * This method will check if the section is occupied by any user. If yes,
   * return the user id that occupies the section; if not, return null.
   * @param sec_id: the section id to check for occupation.
   * @return User ID.
   */
  public synchronized String checkOccupy(String sec_id) {
    if (sec_id==null) return null;

    try {
      return dao.getUserBySection(sec_id);
    } catch (DataAccessException e) {
      ExceptionBroadcast.print(e);
      return null;
    }
  }

  /**
   * This method will check if the section is occupied by session_id.
   * If not, return false; if yes, remove the session_id/sec_id mapping from
   * SecOccupyMap and return true.
   * @param sec_id: the section id that user wants to release.
   * @param session_id: the user session id that to release the section.
   */
  public synchronized boolean release(String sec_id, String session_id) {
    if (sec_id==null) return true;
    try {
      return dao.deleteSection(sec_id, session_id);
    }  catch (DataAccessException e) {
      ExceptionBroadcast.print(e);
      return false;
    }
  }

  /**
   * This method will return the id of all the sections occupying by a session.
   * Return null if the session dosen't occupy any section.
   */
/*
  public synchronized Set getAllSecBySessionID(String session_id) {
    if(! SecOccupyMap.containsValue(session_id)) return null;

    Set sections = new HashSet();

    Set keys = SecOccupyMap.keySet();
    Iterator iter = keys.iterator();
    while (iter.hasNext()) {
      String key = (String) iter.next();
      String value = (String) SecOccupyMap.get(key);
      if((value != null) && value.equals(session_id))
        sections.add(key);
    }

    if(sections.size() != 0) return sections;
    else return null;
  }
*/

  /**
   * This method will release all the sections occupying by a session.
   * Do nothing if there is no sections occupying by the session.
   */
  public void releaseAllSecBySessionID(String session_id) {
    try {
      dao.releaseBySession(session_id);
    } catch (DataAccessException e) {
       ExceptionBroadcast.print(e);
    }
  }

  public void clearAll() {
    dao.dropTables();
    dao.createTables();
  }

  public void removeDomain() {
    dao.dropTables();
    //dao = null;
  }

  /**
   * This method will add a session_id/uid map when user logs in.
   */

  public synchronized void login(String uid, String session_id) {
    if((uid != null) && (session_id != null)) {
      SignOnUser user = new SignOnUser();
      user.userID = uid;
      user.sessionID = session_id;
      try {
        dao.insertUser(user);
      } catch (DataAccessException e) {
         ExceptionBroadcast.print(e);
      }

    }
  }

   /**
   * This method will remove the session_id/uid map when user logs out.
   */
  public synchronized void logout(String session_id){
    try {
      dao.deleteUserBySession(session_id);
    }catch(Exception e) {
       ExceptionBroadcast.print(e);
    }
  }

  /**
   * return the login count of uid; return zero if no login entry is found.
   */
  public int getLoginCount(String uid){
    try {
      return dao.countUser(uid);
    }catch(Exception e) {
       ExceptionBroadcast.print(e);
    }
    return 0;
  }

  /**
   *
   * @param uid
   * @return List of section IDs. If error, return null.
   */
  public List getOccupiedSections(String uid) {
    try {
      return dao.getOccupiedSections(uid);
    }catch (Exception e) {
       ExceptionBroadcast.print(e);
    }
    return null;
  }

  /** test */
  /*
  public static void main(String[] args) {
    SignOnManager som_new = SignOnManager.getInstance("new");

    som_new.login("yao", "111");
    som_new.login("yao", "222");
    som_new.login("yao", "333");
    System.out.println("login count of uid \"yao\": "+ som_new.getLoginCount("yao"));
    som_new.logout("222");
    System.out.println("new login cnt of uid \"yao\": "+ som_new.getLoginCount("yao"));
    som_new.logout("333");
    System.out.println("newer login cnt of uid \"yao\": "+ som_new.getLoginCount("yao"));

    System.out.println("session that occupy sec1="+som_new.occupy("sec1","111"));
    System.out.println("session that occupy sec1="+som_new.occupy("sec1","222"));
    System.out.println("uid="+som_new.checkOccupy("sec1")+" occupy sec1");

    System.out.println("release sec1 for 111: "+som_new.release("sec1", "111"));
    System.out.println("release sec1 for 111: "+som_new.release("sec1", "000"));
    System.out.println("uid="+som_new.checkOccupy("sec1")+" occupy sec1");

    som_new.login("yao", "111");
    som_new.login("yao", "222");
    som_new.login("yao", "333");
    System.out.println("session id="+som_new.occupy("sec1","111"));
    System.out.println("session id="+som_new.occupy("sec2","222"));
    System.out.println("session id="+som_new.occupy("sec3","333"));
    System.out.println("session id="+som_new.occupy("sec4","111"));

    som_new.releaseAllSecBySessionID("111");
    Set test_set = som_new.getAllSecBySessionID("111");
    if(test_set != null) {
      Iterator iter = test_set.iterator();
      while(iter.hasNext())
        System.out.println("---"+iter.next()+"---");
    }

  }//end of main.

*/
}