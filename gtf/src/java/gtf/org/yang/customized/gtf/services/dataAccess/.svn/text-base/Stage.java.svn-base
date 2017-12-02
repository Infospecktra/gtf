package org.yang.customized.gtf.services.dataAccess;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.DataUnavailableException;
import org.yang.services.dataAccess.DataContainer;

public class Stage extends DataContainer implements Serializable, Comparable
{
   static final long serialVersionUID = 4711296382979764924L;

    public static int WAITING = 0;
    public static int IN_PROGRESS = 1;
    public static int DONE = 2;

    private String templateId ="";
    public void setTemplateId(String templateId) { this.templateId = templateId; }
    public String  getTemplateId(){return templateId;}

    private String id ="";
    public void setId(String id) { this.id = id; }
    public String  getId(){return id;}

    private String projectType = "GT";
    public void setProjectType(String projectType) { this.projectType = projectType; }
    public String getProjectType() { return projectType; }

    private String projectId ="";
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String  getProjectId(){return projectId;}

    private int order = 0;
    public void setOrder(int order) { this.order = order; }
    public int getOrder() { return order; }

    private	String name ="";
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private	String description ="";
    public void setDescription (String description) { description = description; }
    public String getDescription (){ return description; }

    private int status = WAITING;
    public void setStatus(int status) { this.status = status; }
    public int getStatus() { return status; }

    private	long doneDate = -1;
    public void setDoneDate (long doneDate) { this.doneDate = doneDate; }
    public long getDoneDate (){ return doneDate; }

    private	long lastUpdateDate = -1;
    public void setLastUpdateDate (long lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }
    public long getLastUpdateDate (){ return lastUpdateDate; }

    private String accessRecords = "";
    public void setaccessRecords(String accessRecords) { this.accessRecords = accessRecords; }
    public String getAccessRecords() { return accessRecords; }

    private	String note ="";
    public void  setNote(String note) { this.note = note; }
    public String getNote (){ return note; }

    private String ownerType = "server";
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }
    public String getOwnerType() { return ownerType; }

    private String bgnColorOnMasterTable = null;
    public void setBgnColorOnMasterTable(String bgnColorOnMasterTable) { this.bgnColorOnMasterTable = bgnColorOnMasterTable; }
    public String getBgnColorOnMasterTable() { return bgnColorOnMasterTable; }

    private boolean dateOnMasterTable = false;
    public void setDateOnMasterTable(boolean dateOnMasterTable) { this.dateOnMasterTable = dateOnMasterTable; }
    public boolean getDateOnMasterTable() { return dateOnMasterTable; }

    private String dateDisplayName = "Done date";
    public void setDateDisplayName(String dateDisplayName) { this.dateDisplayName = dateDisplayName; }
    public String getDateDisplayName() { return dateDisplayName; }

    private String dateDisplayFormat = "yyyy/MM/dd HH:mm";
    public void setDateDisplayFormat(String dateDisplayFormat) { this.dateDisplayFormat = dateDisplayFormat; }
    public String getDateDisplayFormat() { return dateDisplayFormat; }

    public Stage(DataGroup[] datas) throws DataUnavailableException
    {
       super(datas);
    }

    protected HashMap getDataGroupTemplate()
    {
       try
       {
          Stage template = ProjectFactory.getFactory().getStageTemplate(projectType, this.name);
          if(null!=template)
          {
             return template.dataGroups;
          }
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }
       return new HashMap();
    }

    public boolean equals(Object o)
    {
       Stage temp = (Stage)o;
       if(null==id||null==temp.id)
          return false;
       else if(id.equals(temp.id))
          return true;
       else
          return false;
    }

    public int compareTo(Object obj)
    {
       Stage temp = (Stage)obj;
       return (int)(this.order-temp.order);
    }
}