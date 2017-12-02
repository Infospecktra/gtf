package org.yang.customized.gtf.services.dataAccess;

import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;
public class StorageSource extends GenericStorableObject
{
   static final long serialVersionUID = 4621331425373175884L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_source ="";
   public void setSource(String col_source)
   {
       this.col_source = col_source;
   }
   public String  getSource(){return col_source;}

   public StorageSource()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", source varchar(255)")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new StorageSource();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}