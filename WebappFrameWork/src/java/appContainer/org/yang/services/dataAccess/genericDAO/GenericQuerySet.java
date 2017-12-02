/* by Celina Yang */

package org.yang.services.dataAccess.genericDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class GenericQuerySet implements QuerySet
{
   ArrayList querySet = new ArrayList();

   protected String tablename = null;
   public String getTablename() { return tablename; }
   public void setTablename(String tablename) { this.tablename = tablename;}

   public GenericQuerySet() {}

   /*********************
    *   My Own Methods  *
    *********************/

   public void addSQLCommand(String sqlCmd)
   {
      querySet.add(sqlCmd);
   }

   /*********************
    *   From QuerySet   *
    *********************/

   public String nextSQLCommand()
   {
      if(querySet.size()>0)
         return (String)querySet.remove(0);
      return null;
   }

   public abstract boolean handleResultSet(ResultSet rs);

}