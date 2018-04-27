package org.yang.customized.gtf.services.dataAccess;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.DataUnavailableException;
import org.yang.services.dataAccess.DataContainer;

public class Schedule extends DataContainer implements Serializable, Comparable
{
   static final long serialVersionUID = 4711296382979764920L;

    private String projectType = "GT";
    public void setProjectType(String projectType) { this.projectType = projectType; }
    public String getProjectType() { return projectType; }

    private String id ="";
    public void setId(String id) { this.id = id; }
    public String  getId(){return id;}

    private String timetableId ="";
    public void setTimetableId(String timetableId) { this.timetableId = timetableId; }
    public String  getTimetableId(){return timetableId;}

    private int day = 0;
    public void setDay(int day) { this.day = day; }
    public int getDay() { return day; }

    private	String name ="";
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private	String displayName ="";
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }

    private java.sql.Date dueDate = null;
    public void setDueDate(java.sql.Date dueDate) { this.dueDate = dueDate; }
    public java.sql.Date getDueDate() { return dueDate; }

    public Schedule(DataGroup[] datas) throws DataUnavailableException
    {
       super(datas);
    }

    protected HashMap getDataGroupTemplate()
    {
       //try
       //{
       //   Schedule template = ProjectFactory.getFactory().getScheduleTemplate(projectType, name);
       //   if(null!=template)
       //   {
       //      return template.dataGroups;
       //   }
       //}
       //catch(Exception e)
       //{
       //   e.printStackTrace();
       //}
       return new HashMap();
    }

    public boolean equals(Object o)
    {
       Schedule temp = (Schedule)o;
       if(null==id||null==temp.id)
          return false;
       else if(id.equals(temp.id))
          return true;
       else
          return false;
    }

    public int compareTo(Object obj)
    {
       Schedule temp = (Schedule)obj;
       if(day>temp.day)
          return 1;
       else if(day==temp.day)
          return 0;
       else
          return -1;
    }
}