package org.yang.customized.gtf.services.dataAccess;

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
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.AccountDataAccessException;
import org.yang.services.dbService.DataAccessException;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface StageDAO extends Serializable
{
   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable() ;

   /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropTable();

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage load(String id)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String[] sids)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] loadByType(String type)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] loadByProject(String project)throws ProjectDataAccessException;

   /**
   * Loading all the group objects in this domain
   *@exception: <{DataAccessException}>
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Stage[] loadByName(String name)throws ProjectDataAccessException;

   /**
   * Loading all the group objects in this domain
   *@exception: <{DataAccessException}>
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Stage[] loadAll()throws ProjectDataAccessException;


  /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String field, String id)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Stage[] load(String[] fields, String[] ids)throws ProjectDataAccessException;

   /**
   * Insert new group data into data base.
   *
   * @exception: <{DataAccessException}>
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Stage s)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage s)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage s, boolean updateSN)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage[] stages)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Stage[] s,  boolean updateSN)throws ProjectDataAccessException;

  /**
   * Delete a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws DataAccessException;

   public boolean deleteByProject(String project)throws DataAccessException;

   public boolean deleteByName(String name) throws ProjectDataAccessException;

   /**
   * Delete all groups in this domain
   *@exception: <{DataAccessException}>
   *@param    :
   *@return   :  boolean
   */
   public boolean deleteAll() throws DataAccessException;
}