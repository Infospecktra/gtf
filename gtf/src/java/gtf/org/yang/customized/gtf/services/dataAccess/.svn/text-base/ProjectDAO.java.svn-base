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
public interface ProjectDAO extends Serializable
{
   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable();

   /**
   * Drop a group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean dropTable();

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project load(String pid)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String[] pids)throws ProjectDataAccessException;

   public Project[] load(String field, String id)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String[] fields, String[] ids)throws ProjectDataAccessException;

   public Project[] load(String[] fields, String[] ids, boolean isAnd)throws ProjectDataAccessException;

   public Project[] load(String[] conditions, boolean isAnd)throws ProjectDataAccessException;

   public Project[] loadProjectsOrderingByAttribute(String[] conditions, boolean isAnd,String sortBy)throws ProjectDataAccessException;
   /**
   * Insert new group data into data base.
   *
   * @exception: DataAccessException
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Project p)throws ProjectDataAccessException;

   /**
   * Delete a group data from data base
   *@exception: DataAccessException
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws DataAccessException;

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project p)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project p, boolean updateSN)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project[] projects)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project[] projects, boolean updateSN)throws ProjectDataAccessException;

   /**
   * Delete all groups in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   :  boolean
   */
   public boolean deleteAll()throws DataAccessException;

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Project[] loadByAccountId(String aid)throws ProjectDataAccessException;

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Project[] loadAll()throws ProjectDataAccessException;
}