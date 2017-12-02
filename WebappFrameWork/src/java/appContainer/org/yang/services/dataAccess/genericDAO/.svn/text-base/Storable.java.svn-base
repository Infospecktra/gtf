/* by Steven Yang */

package org.yang.services.dataAccess.genericDAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Serializable;
import java.util.HashMap;
import org.yang.services.dbService.DataAccessException;

public interface Storable extends Cloneable, Serializable
{
   static final long serialVersionUID = 8615567360722008151L;

   String getPrimaryKey();

   String[] getAllColumns();

   String getTablename();

   void setTablename(String tablename);

   void overrideProcessColumns(String[] columns);

   String[] getProcessColumns();

   String prepareSQLCreateTable();

   String prepareSQLDropTable();

   String prepareSQLLoad(String conditions);

   DBExcutionDirector getLoadDirector();

   String prepareSQLInsert();

   String prepareSQLUpdate();

   String prepareSQLUpdate(String conditions);

   String prepareSQLDelete(String conditions);

   String prepareSQLCheckExist(String conditions);

   void set(String columnName, Object value)throws IllegalArgumentException;

   Object get(String columnName)throws IllegalArgumentException;

   void importData(ResultSet rs) throws DataAccessException;

   void importData(HashMap dataMap);

   void exportData(PreparedStatement ps);

   Storable create();
}