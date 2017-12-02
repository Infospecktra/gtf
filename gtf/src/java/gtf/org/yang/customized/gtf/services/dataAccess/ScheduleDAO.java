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
public interface ScheduleDAO extends Serializable
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
   public String[] loadTimetableIdsByDate(java.sql.Date dateFrom,
                                          java.sql.Date dateTo )throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule load(String id)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule[] load(String[] sids)throws ProjectDataAccessException;

   /**
   * loading group object by group id
   * @exception: <{DataAccessException}>
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Schedule[] loadByTimetable(String tid)throws ProjectDataAccessException;

    /**
   * Insert new group data into data base.
   *
   * @exception: <{DataAccessException}>
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Schedule s)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Schedule s)throws ProjectDataAccessException;

   /**
   * Update a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Schedule[] schedules)throws ProjectDataAccessException;

  /**
   * Delete a group data from data base
   *@exception: <{DataAccessException}>
   *@param    : String gid
   *@return:    boolean
   */
   public boolean delete(String id)throws DataAccessException;

   public boolean deleteByTimetable(String timetableId)throws DataAccessException;

   public boolean deleteByName(String name) throws ProjectDataAccessException;
}