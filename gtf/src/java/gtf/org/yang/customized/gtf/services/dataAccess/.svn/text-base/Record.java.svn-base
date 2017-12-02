package org.yang.customized.gtf.services.dataAccess;
import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;

public class Record extends GenericStorableObject
{
   static final long serialVersionUID = 4711300482679774914L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_projectName ="";
   public void setProjectName(String col_projectName) { this.col_projectName = col_projectName; }
   public String  getProjectName(){return col_projectName;}

   private String col_plasmidBAC ="";
   public void setPlasmidBAC(String col_plasmidBAC) { this.col_plasmidBAC = col_plasmidBAC; }
   public String  getPlasmidBAC(){return col_plasmidBAC;}

   private String col_billableType = "";
   public String getBillableType() {return col_billableType;}
   public void setBillableType(String col_billableType) { this.col_billableType = col_billableType; }

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

   private int col_number = 0;
   public void setNumber(int col_number) { this.col_number = col_number; }
   public int  getNumber(){ return col_number; }

   protected String col_mouseHost = "";
   public String getMouseHost(){ return col_mouseHost; }
   public void setMouseHost(String col_mouseHost) { this.col_mouseHost = col_mouseHost; }

   protected String col_note = "";
   public String getNote(){ return col_note; }
   public void setNote(String col_note) { this.col_note = col_note; }

   protected Date col_receivedDate = null;
   public Date getReceivedDate(){ return col_receivedDate; }
   public void setReceivedDate(Date col_receivedDate) { this.col_receivedDate = col_receivedDate; }

   protected Date col_purifiedDate = null;
   public Date getPurifiedDate(){ return col_purifiedDate; }
   public void setPurifiedDate(Date col_purifiedDate) { this.col_purifiedDate = col_purifiedDate; }

   protected Date col_injectedDate = null;
   public Date getInjectedDate(){ return col_injectedDate; }
   public void setInjectedDate(Date col_injectedDate) { this.col_injectedDate = col_injectedDate; }

   protected Date col_closedDate = null;
   public Date getClosedDate(){ return col_closedDate; }
   public void setClosedDate(Date col_closedDate) { this.col_closedDate = col_closedDate; }

   protected boolean isSelected = false;
   public boolean getIsSelected(){ return isSelected; }
   public void setIsSelected(boolean isSelected){ this.isSelected = isSelected; }

   
   public Record()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", projectName varchar(255)")
                                            .append(", plasmidBAC varchar(255)")
                                            .append(", investigator varchar(255)")
                                            .append(", domain varchar(255)")
                                            .append(", labName varchar(255)")
                                            .append(", phone varchar(128)")
                                            .append(", number bigint(20)")
                                            .append(", mouseHost varchar(255)")
                                            .append(", billableType varchar(255)")
                                            .append(", receivedDate date")
                                            .append(", purifiedDate date")
                                            .append(", injectedDate date")
                                            .append(", closedDate date")
                                            .append(", note text")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new Record();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}