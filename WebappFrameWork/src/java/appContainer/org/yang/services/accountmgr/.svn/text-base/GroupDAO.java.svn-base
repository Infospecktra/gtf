package org.yang.services.accountmgr;

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

/**
 * @testcase org.test.org.yang.services.accountmgr.TestGroupDAO
 
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface GroupDAO extends Serializable
{
  /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
  public boolean createGroupTable(); //throws AccountDataAccessException{

  /**
   * create a new usergroup table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
  public boolean createUsergroupTable();//throws AccountDataAccessException{

  /**
   * create a new service table in database
   *@exception:DataAccessException
   *@param
   *@return:   boolean
   */
  public boolean createServiceTable();//throws AccountDataAccessException{

  /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
  public boolean dropGroupTable() ;//throws AccountDataAccessException{

  /**
   * Drop a usergroup table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
  public boolean dropUsergroupTable();// throws AccountDataAccessException{

  /**
   * Drop a service table in database
   *@exception:DataAccessException
   *@param
   *@return:   boolean
   */
  public boolean dropServiceTable();//throws AccountDataAccessException{

  /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    Group
   */
    public Group loadByID(String gid)throws AccountDataAccessException;

  /**
   * Insert new group data into data base.
   *
   * @exception: DataAccessException
   * @param:     Group g
   * @return:    boolean
   */
    public boolean insert(Group g)throws AccountDataAccessException;

  /**
   * Delete a group data from data base
   *@exception: DataAccessException
   *@param    : String gid
   *@return:    boolean
   */
    public boolean delete(String id)throws AccountDataAccessException;

  /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
    public boolean update(Group g)throws AccountDataAccessException;

  /**
   * Delete all groups in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   :  boolean
   */
  public boolean deleteAllGroups()throws AccountDataAccessException;

  /**
   * Loading all group objects of a user
   *@exception: DataAccessException
   *@param    : String uid : user id
   *@return   : Collection : the collection of group objects
   */
   public Collection loadGroupsByUser(String uid)throws AccountDataAccessException;

  /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Collection loadAllGroups()throws AccountDataAccessException;

  /**
   * Add a user to a group (insert the data to usergroup table)
   *@exception: DataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean addUserToGroup(String uid,String gid)throws AccountDataAccessException;

  /**
   * remove the data from usergroup table
   *@exception: DataAccessException
   *@param    : String uid,String gid
   *@return:    boolean
   */
   public boolean removeUserFromGroup(String uid,String gid)throws AccountDataAccessException;

   }

