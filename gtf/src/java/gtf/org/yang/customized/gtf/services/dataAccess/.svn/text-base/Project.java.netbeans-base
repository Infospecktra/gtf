package org.yang.customized.gtf.services.dataAccess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.DataUnavailableException;
import org.yang.services.dataAccess.DataContainer;

public class Project extends DataContainer implements Serializable, Comparable 
{
   static final long serialVersionUID = 4711296382979764923L;

    public static int CREATE = 0;
    public static int UPDATE = 1;

    private transient ProjectFactory factory = null;

    private String templateId ="";
    public void setTemplateId(String templateId) { this.templateId = templateId; }
    public String  getTemplateId(){return templateId;}

    private int action = CREATE;
    public void setAction(int action) { this.action = action; }
    public int getAction() { return action; }

    private String type = "GT";
    public void setType(String type) { this.type = type; }
    public String getType() { return type; }

    private String displayTypeName = "GT";
    public void setDisplayTypeName(String displayTypeName) { this.displayTypeName = displayTypeName; }
    public String getDisplayTypeName() { return displayTypeName; }

    private String id ="Not assigned yet";
    public void setId(String id) { this.id = id; }
    public String  getId(){return id;}

    private	String name ="";
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private String protocolNumber = "";
    public void setProtocolNumber(String protocolNumber) { this.protocolNumber = protocolNumber; }
    public String getProtocolNumber() { return protocolNumber; }

    private	String accountId ="";
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getAccountId() { return accountId; }

    private	String description ="";
    public void setDescription (String description) { this.description = description; }
    public String getDescription (){ return description; }

    private long serialNumber = 0;
    public void setSerialNumber(long serialNumber) { this.serialNumber = serialNumber; }
    public long getSerialNumber() { return serialNumber; }

    private String domain = "";
    public void setDomain(String domain) { this.domain = domain; }
    public String getDomain() { return domain; }

    private String investigator = "";
    public String getInvestigator() { return investigator; }
    public void setInvestigator(String investigator){ this.investigator = investigator; }

    private String labHead = "";
    public String getLabHead() { return labHead; }
    public void setLabHead(String labHead){ this.labHead = labHead; }

    private long fileInDate = 0;
    public void setFileInDate(long fileInDate) { this.fileInDate = fileInDate; }
    public long getFileInDate() { return fileInDate; }

    private long startDate = 0;
    public void setStartDate(long startDate) { this.startDate = startDate; }
    public long getStartDate() { return startDate; }

    private long endDate = 0;
    public void setEndDate(long endDate) { this.endDate = endDate; }
    public long getEndDate() { return endDate; }

    public Project(String type, DataGroup[] datas) throws ProjectInitializationException, DataUnavailableException
    {
       super(datas);
       if(null==type)
          throw new ProjectInitializationException("Project type can not be null");

       this.type  = type;
    }

    public void setFactory(ProjectFactory factory)
    {
       this.factory = factory;
    }

    public Stage[] getStageTemplates()
    {
       if(null==type||"".equals(type))
          type = "GT";
       return  factory.getStageTemplates(type);
    }

    public Stage getStageTemplate(int order)
    {
       if(null==type||"".equals(type))
          type = "GT";
       return factory.getStageTemplate(type, order);
    }

    public Stage getStageTemplate(String name)
    {
       if(null==type||"".equals(type))
          type = "GT";
       return factory.getStageTemplate(type, name);
    }

    public Timetable getTimetableTemplates()
    {
       if(null==type||"".equals(type))
          type = "GT";
       return  factory.getTimetableTemplate(type);
    }

    protected HashMap getDataGroupTemplate()
    {
       try
       {
          return this.dataGroups;
       }
       catch(Exception e)
       {
          e.printStackTrace();
          return new HashMap();
       }
    }

    public boolean isDataQualified()
    {
       return (null!=name)&&(!"".equals(name))&&(null!=accountId)&&
              (!"".equals(accountId))&&(super.isDataQualified());
    }


    public boolean isDataQualified(String dataGroup)
    {
       return (null!=name)&&(!"".equals(name))&&(null!=accountId)&&
              (!"".equals(accountId))&&(super.isDataQualified(dataGroup));
    }

    public boolean equals(Object obj)
    {
       Project temp = (Project)obj;
       if(null==id||null==temp.id)
          return false;
       else if(id.equals(temp.id))
          return true;
       else
          return false;
    }

    public int compareTo(Object obj)
    {
       Project temp = (Project)obj;
       return (int)(this.serialNumber-temp.serialNumber);
    }
}