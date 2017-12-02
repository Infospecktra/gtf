package org.yang.services.dataAccess.genericDAO;

import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;
import java.sql.ResultSet;

class CheckExistDirector extends GenericDBExcutionDirector
{
   private boolean targetIsExist = false;
   boolean getTargetIsExist() { return targetIsExist; }

   public CheckExistDirector() {}

   public String getSQLCommand()
   {
      return getTemplate().prepareSQLCheckExist(conditions);
   }

   public void execue(PreparedStatement Stmt) throws DataAccessException
   {
      ResultSet rs = null;
      try
      {
         rs = Stmt.executeQuery();
         rs.next();
         try
         {
            targetIsExist = (0<rs.getInt("ct"));
         }
         catch(Exception e)
         {
            System.out.println("[LoadDirector::execute] Exception happen when extracting data :" + e.getMessage());
         }
      }
      catch (Exception e)
      {
         throw new DataAccessException(e.getMessage()+":sql="+getTemplate().prepareSQLLoad(conditions));
      }
      finally
      {
        try{ rs.close(); } catch(Exception e) {}
      }
   }
}