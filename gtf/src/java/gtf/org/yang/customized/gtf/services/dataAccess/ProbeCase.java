package org.yang.customized.gtf.services.dataAccess;

import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;

public class ProbeCase extends GenericStorableObject
{
   static final long serialVersionUID = 4711530432379174814L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_projectName ="";
   public void setProjectName(String col_projectName) { this.col_projectName = col_projectName; }
   public String  getProjectName(){return col_projectName;}

   private String col_investigator ="";
   public void setInvestigator(String col_investigator) { this.col_investigator = col_investigator; }
   public String  getInvestigator(){return col_investigator;}

   private String col_domain ="";
   public void setDomain(String col_domain) { this.col_domain = col_domain; }
   public String  getDomain(){return col_domain;}

   private String col_labName ="";
   public void setLabName(String col_labName) { this.col_labName = col_labName; }
   public String  getLabName(){return col_labName;}

   private String col_phone ="";
   public void setPhone(String col_phone) { this.col_phone = col_phone; }
   public String  getPhone(){ return col_phone; }

   private String col_probeName ="";
   public void setProbeName(String col_probeName) { this.col_probeName = col_probeName; }
   public String  getProbeName(){return col_probeName;}

   private int col_probeNumber = 0;
   public void setProbeNumber(int col_probeNumber) { this.col_probeNumber = col_probeNumber; }
   public int  getProbeNumber(){ return col_probeNumber; }

   protected String col_testBy = "";
   public String getTestBy(){ return col_testBy; }
   public void setTestBy(String col_testBy) { this.col_testBy = col_testBy; }

   protected String col_resultType = "";
   public String getResultType(){ return col_resultType; }
   public void setResultType(String col_resultType) { this.col_resultType = col_resultType; }

   protected String col_result = "";
   public String getResult(){ return col_result; }
   public void setResult(String col_result) { this.col_result = col_result; }

   protected String col_note = "";
   public String getNote(){ return col_note; }
   public void setNote(String col_note) { this.col_note = col_note; }

   private String col_billableType = "";
   public String getBillableType() {return col_billableType;}
   public void setBillableType(String col_billableType) { this.col_billableType = col_billableType; }

   protected Date col_testDate = null;
   public Date getTestDate(){ return col_testDate; }
   public void setTestDate(Date col_testDate) { this.col_testDate = col_testDate; }

   protected Date col_closedDate = null;
   public Date getClosedDate(){ return col_closedDate; }
   public void setClosedDate(Date col_closedDate) { this.col_closedDate = col_closedDate; }

   protected boolean isSelected = false;
   public boolean getIsSelected(){ return isSelected; }
   public void setIsSelected(boolean isSelected){ this.isSelected = isSelected; }
 
   public ProbeCase()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", projectName varchar(255)")
                                            .append(", investigator varchar(255)")
                                            .append(", domain varchar(255)")
                                            .append(", labName varchar(255)")
                                            .append(", phone varchar(128)")
                                            .append(", probeName varchar(255)")
                                            .append(", probeNumber bigint(20)")
                                            .append(", testBy varchar(255)")
                                            .append(", result varchar(255)")
                                            .append(", resultType varchar(255)")
                                            .append(", billableType varchar(255)")
                                            .append(", testDate date")
                                            .append(", closedDate date")
                                            .append(", note text")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new ProbeCase();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}