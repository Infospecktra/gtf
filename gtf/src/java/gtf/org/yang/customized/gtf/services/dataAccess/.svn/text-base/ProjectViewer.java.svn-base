package org.yang.customized.gtf.services.dataAccess;

import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;
public class ProjectViewer extends GenericStorableObject
{
   static final long serialVersionUID = 4631332425173174885L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_viewMode ="";
   public void setViewMode(String col_viewMode)
   {
       this.col_viewMode = col_viewMode;
   }
   public String  getViewMode(){return col_viewMode;}

   public ProjectViewer()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", viewMode varchar(255)")
                                            .append(",primary key(id,viewMode))");
                                           
      return sql.toString();
   }

   public Storable create()
   {
      return new ProjectViewer();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}