//Source file: d:\\sw\\src\\com\\screamingmedia\\sw.app\\security\\SignOnManager.java

package org.yang.services.accountmgr;

import java.util.HashMap;
import java.util.List;


/**
 * There will be single SignOnManager instance for each domain. SignOnManager
 * class provides synchronized methods to record user login/logout as indicated
 * by session ids; and for each session, to occupy or release sections.
 * @testcase org.test.org.yang.services.accountmgr.TestSignOnManager
 */
public abstract class SignOnManager implements java.io.Serializable{

  public static final String type = "DB";

  private static HashMap SOMMap = new HashMap();

  /**
   * Get the instance corresponding to the domain name if it exists;
   * otherwise create a new instance and store the mapping in SOMMap.
   * */

  public static SignOnManager getInstance(String d){
    SignOnManager instance = null;
    if(SOMMap.containsKey(d))
      return (SignOnManager) SOMMap.get(d);
    else {
       // Steven - minimize the synchronized scope
       synchronized(SOMMap) {
          if(SOMMap.containsKey(d))
             return (SignOnManager) SOMMap.get(d);
          instance = new SignOnManagerDB(d);
          SOMMap.put(d,instance);
       }
    }

    return instance;
  }

  /**
   * Tmp: to support remove table in the middle of run.
   * */
   /*
  public synchronized static SignOnManager getInstance(String d){
     return new SignOnManagerDB(d);
  }
*/
  /**
   * This method enable user session to occupy a group of sections. It succeed only
   * if it successfully got all sections. When succeed, return true. If not, release any
   * occupied sections and return false.
   * @param sec_ids: the section ids that user wants to occupy.
   * @param session_id: the user session id that wants to occupy the section.
   */
  abstract public boolean occupy(String[] sec_ids, String session_id, String uid);

  /**
   * This method will check if the section is occupied by any user. If yes,
   * return the user id that occupies the section; if not, return null.
   * @param sec_id: the section id to check for occupation.
   */
  abstract public  String checkOccupy(String sec_id);

  /**
   * This method will check if the section is occupied by session_id.
   * If not, return false; if yes, remove the session_id/sec_id mapping from
   * SecOccupyMap and return true.
   * @param sec_id: the section id that user wants to release.
   * @param session_id: the user session id that to release the section.
   */
  abstract public  boolean release(String sec_id, String session_id);

  /**
   * This method will return the id of all the sections occupying by a session.
   * Return null if the session dosen't occupy any section.
   */
//  abstract public Set getAllSecBySessionID(String session_id);

  /**
   * This method will add a session_id/uid map when user logs in.
   */
  abstract public void login(String uid, String session_id);

   /**
   * This method will remove the session_id/uid map when user logs out.
   */
  abstract public  void logout(String session_id);

  /**
   * return the login count of uid; return zero if no login entry is found.
   */
  abstract public int getLoginCount(String uid);

  /**
   * Get the sections occupied by a user.
   * @param uid
   * @return List of Strings of Section ID
   */
  abstract public List getOccupiedSections(String uid);

  /**
   * This method will release all the sections occupying by a session.
   * Do nothing if there is no sections occupying by the session.
   */
  abstract public void releaseAllSecBySessionID(String session_id);

  /**
   * Clear all login information.
   */
  abstract public void clearAll();

  /**
   * used when the domain is removed.
   */
  abstract public void removeDomain();

  /** test */
  public static void main(String[] args) {
/*
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
*/
  }//end of main.

}
