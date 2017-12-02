package org.yang.services.dataAccess.genericDAO;

import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;

class DeleteDirector extends GenericDBExcutionDirector
{
   public DeleteDirector() {}

   public String getSQLCommand()
   {
      return getTemplate().prepareSQLDelete(conditions);
   }

   public void execue(PreparedStatement Stmt) throws DataAccessException
   {
      try
      {
         Stmt.executeUpdate();
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage() + ":sql=" + getTemplate().prepareSQLDelete(conditions));
      }
   }
}