package org.yang.services.accountmgr;

import java.io.Serializable;
import java.util.Collection;

/**
 * @testcase org.test.org.yang.services.accountmgr.TestUserDAO
 
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface UserDAO extends Serializable
{
  /**
   * create a new user table in database
   *@exception:AccountDataAccessException
   *@param
   *@return:   boolean
   */
  public boolean createUserTable(); //throws AccountDataAccessException;

  /**
   * Drop a user table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
  public boolean dropUserTable() ; //throws AccountDataAccessException;

  /**
   * loading user object by user id
   * @exception: AccountDataAccessException
   * @param   String id
   * @return: User object
   */
   public User loadByID(String id)throws AccountDataAccessException;

  /**
   * Insert new user data to data base.
   *
   * @exception: AccountDataAccessException
   * @param: User user
   * @return: boolean
   */
  public boolean insert(User user)throws AccountDataAccessException;

  /**
   * Delete a user data from data base
   *@exception: AccountDataAccessException
   *@param    : String id
   *@return:    boolean
   */
   public boolean delete(String id)throws AccountDataAccessException;

  /**
   * Update a user data from data base
   *@exception: AccountDataAccessException
   *@param    : User user
   *@return:    boolean
   */
   public boolean update(User user)throws AccountDataAccessException;

  /**
   * Delete all users in one domain
   *@exception: AccountDataAccessException
   *@param    :
   *@return   :  boolean
   */
  public boolean deleteAllUsers()throws AccountDataAccessException;

  /**
   * Loading all the User objects in a specific group
   *@exception: AccountDataAccessException
   *@param    : String gid :The group id
   *@return   : Collection : The colection of User objects
   */
  public Collection loadUsersFromGroup(String gid)throws AccountDataAccessException;

  /**
   * Loading all the User objects in this domain
   *@exception: AccountDataAccessException
   *@param    :
   *@return   : Collection : The collection of User objects
   */
   public Collection loadAllUsers()throws AccountDataAccessException;

  /**
   * Add a user to a group (insert the data to usergroup table
   *@exception: AccountDataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean addToGroup(String uid,String gid)throws AccountDataAccessException;

  /**
   * remove the data from usergroup table
   *@exception: AccountDataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean removeFromGroup(String uid,String gid)throws AccountDataAccessException;
}



