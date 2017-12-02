package org.yang.services.dataAccess.genericDAO;

import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;

class UpdateDirector extends GenericDBExcutionDirector
{
   public UpdateDirector() {}

   public String getSQLCommand()
   {
      //System.out.println("//////////////////////////////sql="+this.getTemplate().prepareSQLUpdate(conditions));
      return this.getTemplate().prepareSQLUpdate(conditions);
   }

   public void execue(PreparedStatement Stmt) throws DataAccessException
   {
      try
      {
         for(int i=0; i<list.size(); i++)
         {
            ((Storable)list.get(i)).exportData(Stmt);
            Stmt.executeUpdate();
         }
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage()+":sql="+getTemplate().prepareSQLUpdate(conditions));
      }
   }
}


