/* by Steven Yang */

package org.yang.services.dataAccess.genericDAO;
import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;

public interface DBExcutionDirector
{
   void setTemplate(Storable storable);

   void addStorable(Storable storable);

   void addStorableList(StorableList storableList);

   StorableList getStorableList();

   void setConditions(String conditions);

   String getSQLCommand();

   void execue(PreparedStatement Stmt) throws DataAccessException;
}
