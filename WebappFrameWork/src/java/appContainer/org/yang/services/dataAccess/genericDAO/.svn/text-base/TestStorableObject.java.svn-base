/* by Steven Yang */

package org.yang.services.dataAccess.genericDAO;

import java.sql.Date;
import java.sql.Time;

public class TestStorableObject extends GenericStorableObject
{
   protected String tablename = null;
   public String getTablename(){ return tablename; }
   public void setTablename(String tablename){ this.tablename = tablename; }

   protected String key_id = null;
   public String getId(){ return key_id; }
   public void setId(String key_id){ this.key_id = key_id; }

   protected Date col_date = null;
   public Date getDate(){ return col_date; }
   public void setDate(Date date)
   {
      this.col_date = date;
   }

   protected Time col_time = null;
   public Time getTime(){ return col_time; }
   public void setTime(Time time)
   {
      this.col_time = time;
   }

   public TestStorableObject()
   {
      super();
   }

   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(12) not null")
                                            .append(", date date")
                                            .append(", time time")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new TestStorableObject();
   }

   public static void main(String[] argv)
   {
      org.yang.services.dbService.RDBMS rdbms = org.yang.services.dbService.RDBMSFactory.getInstance()
                       .getRDBMS("jdbc:mysql://10.13.13.46:3306/ghdata", "imds", "cashbird", "com.mysql.jdbc.Driver");
      try
      {
         GenericDAO gdao = new GenericDAO(rdbms);

         TestStorableObject test = new TestStorableObject();
         test.setTablename("GenericDAOTest");

         gdao.createTable(test);

         test.setId("test_id");
         long now = System.currentTimeMillis();
         test.setDate(new Date(now));
         test.setTime(new Time(now));

         try
         {
            gdao.insert(test);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }

         try
         {
            gdao.insert(test);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }






      }
      catch (Exception e)
      {
         e.printStackTrace();
         //&System.out.println("data access exception: \n");
      }
   }
}