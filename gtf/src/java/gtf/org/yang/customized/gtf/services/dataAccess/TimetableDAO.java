package org.yang.customized.gtf.services.dataAccess;

import java.io.Serializable;
import org.yang.services.dbService.DataAccessException;

/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface TimetableDAO extends Serializable
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

   public Timetable[] loadByProject(String pid)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable load(String id)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Timetable[] load(String[] sids)throws ProjectDataAccessException;

   /**
   * Insert new group data into data base.
   *
   * @exception: <{DataAccessException}>
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Timetable timetable)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Timetable timetable)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Timetable[] timetables)throws ProjectDataAccessException;

  /**
   * Delete a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws DataAccessException;
}