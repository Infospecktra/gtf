//Source file: d:\\sw\\src\\com\\screamingmedia\\sw.app\\security\\SignOnManager.java

package org.yang.services.accountmgr;

import java.util.*;
import org.yang.services.accountmgr.SignOnManager;


/**
 * This is the in-memory version of SignOnManager. It is fast, but does not work
 * on clustered servers.
 *
 * There will be single SignOnManager instance for each domain. SignOnManager
 * class provides synchronized methods to record user login/logout as indicated
 * by session ids; and for each session, to occupy or release sections.
 * @testcase org.test.org.yang.services.accountmgr.TestSignOnManagerMem
 */
public class SignOnManagerMem extends SignOnManager{
  private HashMap SecOccupyMap = new HashMap(); //section_id/session_id map.
  private HashMap UserLoginMap = new HashMap(); //session_id/uid map.
  private String domain = null;

  /**  Constructor  */
  SignOnManagerMem(String d){
    domain=d;
  }

  /**
   * This method enable user session to occupy a group of sections. It succeed only
   * if it successfully got all sections. When succeed, return true. If not, release any
   * occupied sections and return false.
   * @param sec_ids: the section ids that user wants to occupy.
   * @param session_id: the user session id that wants to occupy the section.
   */
  public synchronized boolean occupy(String[] sec_ids, String session_id, String uid) {
    if (sec_ids==null) return true;
    if (sec_ids.length == 0) return true;

    for (int i=0; i<sec_ids.length; i++) {
      String session = (String) SecOccupyMap.get(sec_ids[i]);
      if ( (session != null) && (session.equals(session_id)==false)) return false;
    }

    //Now we know we can proceed -- all sections empty or occupied by itself.
    for (int i=0; i<sec_ids.length; i++) {
      SecOccupyMap.put(sec_ids[i], session_id);
    }

    return true;
  }

  /**
   * This method enable user session to occupy a section by adding
   * the sec_id/session_id mapping to SecOccupyMap and return session_id.
   * If the section's occupied, return the session_id that occupies the section.
   * @param sec_id: the section id that user wants to occupy.
   * @param session_id: the user session id that wants to occupy the section.
   */
  public synchronized String occupy(String sec_id, String session_id) {
    if(SecOccupyMap.containsKey(sec_id))
      return (String) SecOccupyMap.get(sec_id);
    else{
      SecOccupyMap.put(sec_id, session_id);
      return session_id;
    }
  }


  /**
   * This method will check if the section is occupied by any user. If yes,
   * return the user id that occupies the section; if not, return null.
   * @param sec_id: the section id to check for occupation.
   */
  public synchronized String checkOccupy(String sec_id) {
    if(SecOccupyMap.containsKey(sec_id)){
      String session_id = (String) SecOccupyMap.get(sec_id);
      return (String) UserLoginMap.get(session_id);
    }
    else return null;
  }

  /**
   * This method will check if the section is occupied by session_id.
   * If not, return false; if yes, remove the session_id/sec_id mapping from
   * SecOccupyMap and return true.
   * @param sec_id: the section id that user wants to release.
   * @param session_id: the user session id that to release the section.
   */
  public synchronized boolean release(String sec_id, String session_id) {
    if(SecOccupyMap.containsKey(sec_id)) {
      if(((String) SecOccupyMap.get(sec_id)).equals(session_id)) {
        SecOccupyMap.remove(sec_id);
        return true;
      }
    }
    return false;
  }

  /**
   * This method will return the id of all the sections occupying by a session.
   * Return null if the session dosen't occupy any section.
   */
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

  /**
   * This method will release all the sections occupying by a session.
   * Do nothing if there is no sections occupying by the session.
   */
  public synchronized void releaseAllSecBySessionID(String session_id) {
    if(! SecOccupyMap.containsValue(session_id)) return;

    Set entry_set = SecOccupyMap.entrySet();
    Iterator iter = entry_set.iterator();
    while (iter.hasNext()) {
        Map.Entry e = (Map.Entry) iter.next();
        String key = (String) e.getKey();
        String value = (String) SecOccupyMap.get(key);
        if((value != null) && value.equals(session_id))
          iter.remove();
    }
  }

  public void clearAll() {
    SecOccupyMap = new HashMap();
    UserLoginMap = new HashMap();
  }
  public void removeDomain() {
    SecOccupyMap = null;
    UserLoginMap = null;
  }

  /**
   * This method will add a session_id/uid map when user logs in.
   */
  public synchronized void login(String uid, String session_id){
    if((uid != null) && (session_id != null))
      UserLoginMap.put(session_id, uid);
  }

   /**
   * This method will remove the session_id/uid map when user logs out.
   */
  public synchronized void logout(String session_id){
    if(UserLoginMap.containsKey(session_id)){
      UserLoginMap.remove(session_id);
      //release all the sections occupied by this session.
      this.releaseAllSecBySessionID(session_id);
    } else System.out.println("[] -- Can not logout session: "+session_id);
  }

  /**
   * return the login count of uid; return zero if no login entry is found.
   */
  public synchronized int getLoginCount(String uid){
    int login_cnt = 0;
    Set keys = UserLoginMap.keySet();
    Iterator iter = keys.iterator();
    while(iter.hasNext()) {
      String value = (String) UserLoginMap.get(iter.next());
      if((value != null) && value.equals(uid)) login_cnt += 1;
    }
    return login_cnt;
  }

  public List getOccupiedSections(String uid) {
    List ret = new ArrayList();
    Set keys = UserLoginMap.keySet();
    Iterator iter = keys.iterator();
    while(iter.hasNext()) {
      String key = (String)iter.next();
      String value = (String) UserLoginMap.get(key);
      if((value != null) && value.equals(uid)) ret.add(key);
    }
    return ret;
  }

}
