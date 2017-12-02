package org.yang.services.accountmgr;

import java.io.Serializable;
import java.util.Collection;

/**
 * @testcase org.test.org.yang.services.accountmgr.TestPermissionDAO
 
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface PermissionDAO extends Serializable
{
  /**
   * create a new permission table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
  public boolean createPermissionTable();//throws AccountDataAccessException

  /**
   * Drop a permission table in database
   *@exception:AccountDataAccessException
   *@param
   *@return: boolean
   */
  public boolean dropPermissionTable();// throws AccountDataAccessException

  /**
   * loading permission object by permission id
   * @exception: AccountDataAccessException
   * @param      String pid
   * @return:    Permission object
   */
    public Permission loadByID(String pid)throws AccountDataAccessException;

  /**
   * Insert the new data of Permission object  into data base.
   *
   * @exception: AccountDataAccessException
   * @param:     Permission p
   * @return:    boolean
   */
   public boolean insert(Permission p)throws AccountDataAccessException;

  /**
   * Delete a permission data from data base
   *@exception: AccountDataAccessException
   *@param    : String pid
   *@return:    boolean
   */
    public boolean delete(String pid)throws AccountDataAccessException;

  /**
   * Delete a permission record by Area ID.
   * Author: lei Liu
   *@exception: AccountDataAccessException
   *@param    : String aid
   *@return:    boolean
   */
    public boolean deleteByArea(String aid)throws AccountDataAccessException;

  /**
   * Update a permission data from data base
   *@exception: AccountDataAccessException
   *@param    : Permission p
   *@return:    boolean
   */
    public boolean update(Permission p)throws AccountDataAccessException;

  /**
   * Delete all permissions in this domain
   *@exception: AccountDataAccessException
   *@param    :
   *@return   :  boolean
   */
  public boolean deleteAllPermissions()throws AccountDataAccessException;

  /**
   * Loading all the permission objects in this domain
   *@exception: AccountDataAccessException
   *@param    :
   *@return   : Collection : The collesction of Permission objects
   */
   public Collection loadAllPermissions()throws AccountDataAccessException;

  /**
   * Loading all the permission objects by group id and sid
   *@exception: AccountDataAccessException
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Collection loadByGidSid(String gid,String sid)throws AccountDataAccessException;

  /**
   * Loading all the permission objects by group id
   *@exception: AccountDataAccessException
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Collection loadByGid(String gid)throws AccountDataAccessException;

   public Collection loadGIDByService(String sid, String area, String operation)throws AccountDataAccessException;

  /**
   * Loading the permission objects by component service id (in areas column  )
   *@exception: AccountDataAccessException
   *@param    :
   *@return   : Collection : The collection of Permission objects
   */
   public Permission loadByComponentServiceID(String csid)throws AccountDataAccessException;
}