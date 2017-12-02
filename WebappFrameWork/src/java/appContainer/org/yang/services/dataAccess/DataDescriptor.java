/* Generated by Together */

package org.yang.services.dataAccess;

import java.io.Serializable;

public class DataDescriptor implements Serializable, Comparable
{
   private String key_id = null;
   public void setId(String key_id) { this.key_id = key_id; }
   public String  getId(){return key_id;}

   private String col_displayName = "Display Name";
   public void setDisplayName(String col_displayName) { this.col_displayName = col_displayName; }
   public String getDisplayName() { return col_displayName; }

   private int col_displayOrder = 1000;
   public void setDisplayOrder(int col_displayOrder) { this.col_displayOrder = col_displayOrder; }
   public int getDisplayOrder() { return col_displayOrder; }

   private String col_briefDisplayName = "Display Name";
   public void setBriefDisplayName(String col_briefDisplayName) { this.col_briefDisplayName = col_briefDisplayName; }
   public String getBriefDisplayName() { return col_briefDisplayName; }

   private String col_unit = "";
   public void setUnit(String col_unit) { this.col_unit = col_unit; }
   public String getUnit() { return col_unit; }

   private String col_type = "String";
   public void setType(String col_type) { this.col_type = col_type; }
   public String getType() { return col_type; }

   private String col_width = null;
   public void setWidth(String col_width) { this.col_width = col_width; }
   public String getWidth() { return col_width; }

   private String[] col_possibleValue = null;
   public void setPossibleValue(String[] col_possibleValue) { this.col_possibleValue = col_possibleValue; }
   public String[] getPossibleValue() { return col_possibleValue; }

   private String col_processorName = null;
   public void setProcessorName(String col_processorName) { this.col_processorName = col_processorName; }
   public String getProcessorName() { return col_processorName; }

   private String col_cprocessorName = null;
   public void setCprocessorName(String col_cprocessorName) { this.col_cprocessorName = col_cprocessorName; }
   public String getCprocessorName() { return col_cprocessorName; }

   private String col_displayerName = null;
   public void setDisplayerName(String col_displayerName) { this.col_displayerName = col_displayerName; }
   public String getDisplayerName() { return col_displayerName; }

   private boolean col_availForClient = false;
   public void setAvailForClient(boolean col_availForClient) { this.col_availForClient = col_availForClient; }
   public boolean getAvailForClient() { return col_availForClient; }

   private boolean col_onMasterTable = false;
   public void setOnMasterTable(boolean col_onMasterTable) { this.col_onMasterTable = col_onMasterTable; }
   public boolean getOnMasterTable() { return col_onMasterTable; }

   private boolean col_isSortable = false;
   public void setIsSortable(boolean col_isSortable) { this.col_isSortable = col_isSortable; }
   public boolean getIsSortable() { return col_isSortable; }

   private boolean col_onDataSheet = true;
   public void setOnDataSheet(boolean col_onDataSheet) { this.col_onDataSheet = col_onDataSheet; }
   public boolean getOnDataSheet() { return col_onDataSheet; }

   private boolean col_isMultiInstance = false;
   public void setIsMultiInstance(boolean col_isMultiInstance) { this.col_isMultiInstance = col_isMultiInstance; }
   public boolean getIsMultiInstance() { return col_isMultiInstance; }

   private boolean col_isRequired = false;
   public void setIsRequired(boolean col_isRequired) { this.col_isRequired = col_isRequired; }
   public boolean getIsRequired() { return col_isRequired; }

   public DataDescriptor() {}

   public int compareTo(Object obj)
   {
      if(null==obj||!(obj instanceof Data))
         return 0;
      return (col_displayOrder-((DataDescriptor)obj).col_displayOrder);
   }
}