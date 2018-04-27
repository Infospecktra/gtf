package org.yang.customized.gtf.services.dataAccess;

import java.net.URL;
import java.sql.Date;
import org.yang.services.dataAccess.genericDAO.Storable;
import org.yang.services.dataAccess.genericDAO.GenericStorableObject;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class Storage extends GenericStorableObject
{
   static final long serialVersionUID = 4611330422371175884L;

   private String key_id = "";
   public void setId(String key_id) { this.key_id = key_id; }
   public String getId() { return key_id; }

   private String col_cell ="";
   public void setCell(String col_cell)
   {
       this.col_cell = col_cell;
   }
   public String  getCell(){return col_cell;}

   private String col_investigator ="";
   public void setInvestigator(String col_investigator)
   {
       this.col_investigator = col_investigator;
   }
   public String  getInvestigator(){return col_investigator;}

   private String col_labName ="";
   public void setLabName(String col_labName) { this.col_labName = col_labName; }
   public String  getLabName(){return col_labName;}

   private String col_projectCode ="";
   public void setProjectCode(String col_projectCode)
   {
       this.col_projectCode = col_projectCode;
   }
   public String  getProjectCode(){return col_projectCode;}

   private String col_parentalLine ="";
   public void setParentalLine(String col_parentalLine)
   {
       this.col_parentalLine = col_parentalLine;
   }
   public String  getParentalLine(){ return col_parentalLine; }

   private String col_medium ="";
   public void setMedium(String col_medium)
   {
       this.col_medium = col_medium;
   }
   public String  getMedium(){return col_medium;}

   private String col_drugSelection = "";
   public void setDrugSelection(String col_drugSelection)
   {
       this.col_drugSelection = col_drugSelection;
   }
   public String  getDrugSelection(){ return col_drugSelection; }

   protected String col_location = "";
   public String getLocation(){ return col_location; }
   public void setLocation(String col_location)
   {
       this.col_location = col_location;
   }

   protected int col_boxNumber = 0;
   public int getBoxNumber(){ return col_boxNumber; }
   public void setBoxNumber(int col_boxNumber)
   {
       this.col_boxNumber = col_boxNumber;
   }

   protected String col_rowColumn = "";
   public String getRowColumn(){ return col_rowColumn; }
   public void setRowColumn(String col_rowColumn) { this.col_rowColumn = col_rowColumn; }

   protected Date col_freezingDate = null;
   public Date getFreezingDate(){ return col_freezingDate; }
   public void setFreezingDate(Date col_freezingDate)
   {
       this.col_freezingDate = col_freezingDate;
   }

   protected String col_comment = "";
   public String getComment(){ return col_comment; }
   public void setComment(String col_comment) { this.col_comment = col_comment; }

   protected boolean isSelected = false;
   public boolean getIsSelected(){ return isSelected; }
   public void setIsSelected(boolean isSelected){ this.isSelected = isSelected; }
 
   public Storage()
   {
      super();
      storageValues = new HashSet();
   }
   public String prepareSQLCreateTable()
   {
      StringBuffer sql =
           new StringBuffer("CREATE TABLE ").append(this.getTablename())
                                            .append(" (id varchar(255) not null")
                                            .append(", cell varchar(255)")
                                            .append(", investigator varchar(255)")
                                            .append(", labName varchar(255)")
                                            .append(", projectCode varchar(255)")
                                            .append(", parentalLine varchar(128)")
                                            .append(", medium varchar(255)")
                                            .append(", drugSelection varchar(255)")
                                            .append(", location varchar(255)")
                                            .append(", boxNumber int")
                                            .append(", rowColumn varchar(255)")
                                            .append(", freezingDate date")
                                            .append(", comment text")
                                            .append(", primary key(id))");
      return sql.toString();
   }

   private int  numberOfFitValuesWithSearchKeys = 0;
   public int getNumberOfFitValuesWithSearchKeys(){return numberOfFitValuesWithSearchKeys;}
   public void setNumberOfFitValuesWithSearchKeys(int n){numberOfFitValuesWithSearchKeys=n;}
   
   private HashSet storageValues = null;
   public int fitValuesCompareToSearchKeys(ArrayList searchKeys)
   {
       storageValues.clear();
       storageValues.add(col_cell.toLowerCase());
       storageValues.add(col_investigator.toLowerCase());
       storageValues.add(col_labName.toLowerCase());
       storageValues.add(col_projectCode.toLowerCase());
       storageValues.add(col_parentalLine.toLowerCase());
       storageValues.add(col_drugSelection.toLowerCase());
       storageValues.add(col_medium.toLowerCase());
       storageValues.add(col_location.toLowerCase());
       storageValues.add(col_rowColumn.toLowerCase());
       storageValues.add(col_boxNumber+"");
       
       /*
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_cell="+col_cell);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_investigator="+col_investigator);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_labName="+col_labName);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_projectCode="+col_projectCode);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_parentalLine="+col_parentalLine);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_medium="+col_medium);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_location="+col_location);
      //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->col_boxNumber="+col_boxNumber);
       */
       //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->storageValues.size()="+storageValues.size());
       //System.out.println("----------");
       numberOfFitValuesWithSearchKeys = 0;
       Iterator iterator = searchKeys.iterator();

       while(iterator.hasNext())
       {
          String x = (String)iterator.next();
          if((col_cell.toLowerCase()).indexOf(x)>-1)
          {
            //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->fitValue="+x);
             numberOfFitValuesWithSearchKeys++;
          }
          //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->searchKeys="+x);
          if(storageValues.contains((x).toLowerCase()))
          {
             //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->fitValue="+x);
             numberOfFitValuesWithSearchKeys++;
          }
       }
       //System.out.println("[Storage::fitValuesCompareToSearchKeys]----->numberOfFitValuesWithSearchKeys="+numberOfFitValuesWithSearchKeys);
       return numberOfFitValuesWithSearchKeys;
   }

   public Storable create()
   {
      return new Storage();
   }

   public boolean equals(Object o)
   {
      return false;
   }
}