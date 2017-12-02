/* by Celina Yang */

package org.yang.services.dataAccess.genericDAO;

import java.sql.ResultSet;
import java.io.Serializable;

public interface QuerySet extends Serializable
{
   String nextSQLCommand();
   
   boolean handleResultSet(ResultSet rs);
}