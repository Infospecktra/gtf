package org.yang.services.dataAccess.genericDAO;
import java.sql.PreparedStatement;
import org.yang.services.dbService.DataAccessException;
import java.sql.ResultSet;


public class LoadDirector extends GenericDBExcutionDirector
{
   public LoadDirector() {}

   public String getSQLCommand()
   {
      return getTemplate().prepareSQLLoad(conditions);
   }

   public void execue(PreparedStatement Stmt) throws DataAccessException
   {
      ResultSet rs = null;
      try
      {
         rs = Stmt.executeQuery();
         Storable temp = null;
         list.clear();
         while(rs.next())
         {
            try
            {
               temp = getTemplate().create();
               temp.importData(rs);
               list.add(temp);
            }
            catch(Exception e)
            {
               System.out.println("[LoadDirector::execute] Exception happen when import data :" + e.getMessage());
            }
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