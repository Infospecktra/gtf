/* by Celina Yang */

package org.yang.services.dataAccess.genericDAO;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.yang.services.dbService.DataAccessException;

public interface SerialSQLQueryIF extends SQLQueryIF 
{   
   boolean  hasMoreCommand();
}