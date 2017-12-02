package org.yang.services.dataAccess;
import java.io.Serializable;
import org.yang.services.dbService.DataAccessException;


/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public interface DataDAO extends Serializable
{
   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable() ;

   public boolean dropTable();

   public Data load(String id)throws DataAccessException;

   public Data[] load(String[] sids)throws DataAccessException;

   public Data[] loadByName(String name)throws DataAccessException;

   public Data[] loadAll()throws DataAccessException;

   public Data[] load(String field, String value)throws DataAccessException;

   public Data[] load(String[] fields, String[] values)throws DataAccessException;

   // for gtf project
   public Data[] loadByProjectIdAndStageOrder(String pid, String stageorder) throws DataAccessException;

   public Data[] loadByProjectIdAndScheduleName(String pid, String scheduleName) throws DataAccessException;

   public Data[] loadByTimetableDatsByProjectId(String pid) throws DataAccessException;

   public boolean insert(Data d)throws DataAccessException;

   public boolean insert(Data[] d)throws DataAccessException;

   public boolean update(Data d)throws DataAccessException;

   public boolean update(Data[] d)throws DataAccessException;

   public boolean delete(String id)throws DataAccessException;

   public boolean deleteAll() throws DataAccessException;
}