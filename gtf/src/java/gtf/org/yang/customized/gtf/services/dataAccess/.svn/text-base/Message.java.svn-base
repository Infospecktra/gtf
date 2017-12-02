package org.yang.customized.gtf.services.dataAccess;
import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;

public class Message extends GenericStorableObject
{
   static final long serialVersionUID = 4711200382979764924L;

   private long key_id = 0;
   public void setId(long key_id) { this.key_id = key_id; }
   public long getId() { return key_id; }

   private String col_title ="";
   public void setTitle(String col_title) { this.col_title = col_title; }
   public String  getTitle(){return col_title;}

   private String col_domainFrom ="";
   public void setDomainFrom(String col_domainFrom) { this.col_domainFrom = col_domainFrom; }
   public String  getDomainFrom(){return col_domainFrom;}

   private String col_domainTo ="";
   public void setDomainTo(String col_domainTo) { this.col_domainTo = col_domainTo; }
   public String  getDomainTo(){return col_domainTo;}

   private String col_fromUser ="";
   public void setFromUser(String col_fromUser) { this.col_fromUser = col_fromUser; }
   public String  getFromUser(){ return col_fromUser; }

   private String col_toUser ="";
   public void setToUser(String col_toUser) { this.col_toUser = col_toUser; }
   public String  getToUser(){ return col_toUser; }

   protected Date col_theDate = null;
   public Date getTheDate(){ return col_theDate; }
   public void setTheDate(Date col_theDate) { this.col_theDate = col_theDate; }

   protected Time col_theTime = null;
   public Time getTheTime(){ return col_theTime; }
   public void setTheTime(Time col_theTime) { this.col_theTime = col_theTime; }

   private String col_message ="";
   public void setMessage(String col_message) { this.col_message = col_message; }
   public String  getMessage(){return col_message;}

   private String col_attachmentUrlStr ="";
   public void setAttachmentUrlStr(String col_attachmentUrlStr) { this.col_attachmentUrlStr = col_attachmentUrlStr; }
   public String getAttachmentUrlStr(){return col_attachmentUrlStr;}

   protected boolean col_isSent = false;
   public boolean getIsSent(){ return col_isSent; }
   public void setIsSent(boolean col_isSent) { this.col_isSent = col_isSent; }


   public URL[] getAttachmentUrl()
   {
      return null;
   }

   public Message()
   {
      super();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id bigint not null")
                                            .append(", title varchar(64)")
                                            .append(", domainFrom varchar(32)")
                                            .append(", domainTo varchar(32)")
                                            .append(", fromUser varchar(32)")
                                            .append(", toUser varchar(32)")
                                            .append(", theDate date")
                                            .append(", theTime time")
                                            .append(", message text")
                                            .append(", attachmentUrlStr text")
                                            .append(", isSent bool")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   public Storable create()
   {
      return new Message();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}