package org.yang.customized.gtf.services.dataAccess;

import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;

public class Karyotype extends GenericStorableObject
{
   static final long serialVersionUID = 4411531432379378817L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_projectName ="";
   public void setProjectName(String col_projectName) { this.col_projectName = col_projectName; }
   public String  getProjectName(){return col_projectName;}

   private String col_projectCode = "";
   public void setProjectCode(String col_projectCode) { this.col_projectCode = col_projectCode; }
   public String  getProjectCode(){ return col_projectCode; }

   private String col_investigator ="";
   public void setInvestigator(String col_investigator) { this.col_investigator = col_investigator; }
   public String  getInvestigator(){return col_investigator;}

   private String col_domain ="";
   public void setDomain(String col_domain) { this.col_domain = col_domain; }
   public String  getDomain(){return col_domain;}

   private String col_labName ="";
   public void setLabName(String col_labName) { this.col_labName = col_labName; }
   public String  getLabName(){return col_labName;}
   /*
   private String col_phone ="";
   public void setPhone(String col_phone) { this.col_phone = col_phone; }
   public String  getPhone(){ return col_phone; }
   */
   private String col_parentalLine ="";
   public void setParentalLine(String col_parentalLine) { this.col_parentalLine = col_parentalLine; }
   public String  getParentalLine(){return col_parentalLine;}

   protected String col_requestedBy = "";
   public String getRequestedBy(){ return col_requestedBy; }
   public void setRequestedBy(String col_requestedBy) { this.col_requestedBy = col_requestedBy; }

   protected String col_cellSource = "";
   public String getCellSource(){ return col_cellSource; }
   public void setCellSource(String col_cellSource) { this.col_cellSource = col_cellSource; }

   protected String col_result = "";
   public String getResult(){ return col_result; }
   public void setResult(String col_result) { this.col_result = col_result; }

   protected String col_resultType = "";
   public String getResultType(){ return col_resultType; }
   public void setResultType(String col_resultType) { this.col_resultType = col_resultType; }

   protected String col_note = "";
   public String getNote(){ return col_note; }
   public void setNote(String col_note) { this.col_note = col_note; }
 /*
   private String col_billableType = "";
   public String getBillableType() {return col_billableType;}
   public void setBillableType(String col_billableType) { this.col_billableType = col_billableType; }
*/
   protected Date col_sentDate = null;
   public Date getSentDate(){ return col_sentDate; }
   public void setSentDate(Date col_sentDate) { this.col_sentDate = col_sentDate; }

   protected String col_billing = null;
   public String getBilling(){ return col_billing; }
   public void setBilling(String col_billing) { this.col_billing = col_billing; }

   protected boolean isSelected = false;
   public boolean getIsSelected(){ return isSelected; }
   public void setIsSelected(boolean isSelected){ this.isSelected = isSelected; }
 
   public Karyotype()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", projectName varchar(255)")
                                            .append(", projectCode varchar(255)")
                                            .append(", investigator varchar(255)")
                                            .append(", domain varchar(255)")
                                            .append(", labName varchar(255)")
                                            //.append(", phone varchar(128)")
                                            .append(", parentalLine varchar(255)")
                                            .append(", requestedBy varchar(255)")
                                            .append(", result varchar(255)")
                                            .append(", resultType varchar(255)")
                                            .append(", cellSource varchar(255)")
                                            //.append(", billableType varchar(255)")
                                            .append(", sentDate date")
                                            .append(", billing varchar(255)")
                                            .append(", note text")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new Karyotype();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}