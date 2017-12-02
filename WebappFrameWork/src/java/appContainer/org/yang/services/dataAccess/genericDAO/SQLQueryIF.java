/* by Celina Yang */

package org.yang.services.dataAccess.genericDAO;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.yang.services.dbService.DataAccessException;

public interface SQLQueryIF 
{    
   String getSQLCommand();
   
   String[] getSQLCommands();
   
   ResultSet setResultSet(ResultSet rs) throws DataAccessException;
   
   ResultSet[] setResultSets(ResultSet[] rss) throws DataAccessException;  
  
}