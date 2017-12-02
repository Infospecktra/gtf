package org.yang.services.dataAccess.genericDAO;
import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;

class InsertDirector extends GenericDBExcutionDirector
{
   public InsertDirector() {}

   public String getSQLCommand()
   {
      return getTemplate().prepareSQLInsert();
   }

   public void execue(PreparedStatement Stmt) throws DataAccessException
   {
      try
      {
         for(int i=0; i<list.size(); i++)
         {
            list.get(i).exportData(Stmt);
            Stmt.executeUpdate();
         }
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage()+":sql="+getTemplate().prepareSQLInsert());
      }
   }
}


