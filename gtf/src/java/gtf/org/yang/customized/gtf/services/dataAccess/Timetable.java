package org.yang.customized.gtf.services.dataAccess;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.HashMap;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.DataUnavailableException;
import org.yang.services.dataAccess.DataContainer;
import java.util.ArrayList;
import java.util.Collections;

public class Timetable extends DataContainer implements Serializable, Comparable
{
   static final long serialVersionUID = 4711296382979764999L;

   private HashMap map = null;

   private String id ="";
   public void setId(String id){ this.id = id; }
   public String  getId(){return id;}

   private String projectType = "GT";
   public void setProjectType(String projectType) { this.projectType = projectType; }
   public String getProjectType() { return projectType; }

   private String projectId ="";
   public void setProjectId(String projectId) { this.projectId = projectId; }
   public String  getProjectId(){return projectId;}

   private java.sql.Date dueDate = null;
   public void setDueDate(java.sql.Date dueDate) { this.dueDate = dueDate; }
   public java.sql.Date getDueDate() { return dueDate; }

   private Schedule[] schedules = null;
   public void setSchedules(Schedule[] schedules) { this.schedules = schedules; }
   public Schedule[] getSchedules() { return schedules; }

   public Timetable(DataGroup[] datas) throws DataUnavailableException
   {
      super(datas);
   }

   protected HashMap getDataGroupTemplate()
   {
      //try
      //{
      //   Timetable template = ProjectFactory.getFactory().getTimetableTemplate(projectType);

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
      Timetable temp = (Timetable)o;
      if(null==id||null==temp.id)
         return false;
      else if(id.equals(temp.id))
         return true;
      else
         return false;
   }

   public int compareTo(Object obj)
   {
      return 0;
   }
}