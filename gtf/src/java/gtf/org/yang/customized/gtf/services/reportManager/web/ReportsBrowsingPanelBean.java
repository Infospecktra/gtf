/* Generated by Together */

package org.yang.customized.gtf.services.reportManager.web;

import org.yang.web.applicationContainer.SecuredBean;
import org.yang.customized.gtf.services.reportManager.ReportManager;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import java.util.ArrayList;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.customized.gtf.services.dataAccess.Project;

public class ReportsBrowsingPanelBean extends ReportsBean
{
   static final long serialVersionUID = 4711296382979764911L;

   private int event = 0;
   public int getEvent() { return event; }

   // Tab switching

   private String projectType ="";
   private String tab = "browse";

   public String  getProjectType(){return projectType;}

   public void setProjectType(String projectType) { this.projectType = projectType; }

   public String getTab() { return tab; }

   // Common

   private String dateType = "";
   public void setDateType(String dateType) { this.dateType = dateType; }
   public String getDateType() { return dateType; }

   private String institute ="";
   public void setInstitute(String institute) { this.institute = institute; }
   public String getInstitute() { return institute; }
       // Not in use
   private String[] displayItems = {"projectName", "domain", ""};
   public void setDisplayItems(String[] displayItems) { this.displayItems = displayItems; }
   public String[] getDisplayItems() { return displayItems; }

   // Only for browsing

   private int calendarView = 1;
   public void setCalendarView(int calendarView) { this.calendarView = calendarView; }
   public int getCalendarView() { return calendarView; }

   private int displayedMonth = 0;
   public void setDisplayedMonth(int displayedMonth){ this.displayedMonth = displayedMonth; }
   public int getDisplayedMonth(){ return displayedMonth; }

   private int displayedYear = 0;
   public void setDisplayedYear(int displayedYear){ this.displayedYear = displayedYear; }
   public int getDisplayedYear(){ return displayedYear; }

   private int displayedDay = 0;
   public void setDisplayedDay(int displayedDay){ this.displayedDay = displayedDay; }
   public int getDisplayedDay(){ return displayedDay; }

   // Only for searching

   private int searchYearFrom = 0;
   public void setSearchYearFrom(int searchYearFrom){ this.searchYearFrom = searchYearFrom; }
   public int getSearchYearFrom(){ return searchYearFrom; }

   private int searchMonthFrom = 0;
   public void setSearchMonthFrom(int searchMonthFrom){ this.searchMonthFrom = searchMonthFrom; }
   public int getSearchMonthFrom(){ return searchMonthFrom; }

   private int searchDayFrom = 0;
   public void setSearchDayFrom(int searchDayFrom){ this.searchDayFrom = searchDayFrom; }
   public int getSearchDayFrom(){ return searchDayFrom; }

   private int searchYearTo = 0;
   public void setSearchYearTo(int searchYearTo){ this.searchYearTo = searchYearTo; }
   public int getSearchYearTo(){ return searchYearTo; }

   private int searchMonthTo = 0;
   public void setSearchMonthTo(int searchMonthTo){ this.searchMonthTo = searchMonthTo; }
   public int getSearchMonthTo(){ return searchMonthTo; }

   private int searchDayTo = 0;
   public void setSearchDayTo(int searchDayTo){ this.searchDayTo = searchDayTo; }
   public int getSearchDayTo(){ return searchDayTo; }

   private String searchBy = "";
   public void setSearchBy(String searchBy) { this.searchBy = searchBy; }
   public String getSearchBy() { return searchBy; }

   private String keywords = "";
   public void setKeywords(String keywords) { this.keywords = keywords; }
   public String getKeywords() { return keywords; }

   private String andOr = "";
   public void setAndOr(String andOr) { this.andOr = andOr; }
   public String getAndOr() { return andOr; }

   public ReportsBrowsingPanelBean()
   {
      super();
      Date now = new Date();
      searchYearFrom = now.getYear()+1900;
      searchMonthFrom = now.getMonth()+1;
      searchDayFrom = now.getDate();
      searchYearTo = searchYearFrom;
      searchMonthTo = searchMonthFrom;
      searchDayTo = searchDayFrom;
   }

   /*************************************
    *  Implement NavigationTree method  *
    *************************************/

   public String getDomain()
   {
      return passport.getDomain();
   }

   public String[] getAllDomains()
   {
      String[] domains = passport.getDomains();
      String[] temp = new String[domains.length];
      temp[0] = "ALL";
      for(int i=1; i<temp.length; i++)
      {
         temp[i] = domains[i-1];
      }
      return temp;
   }

   public Project getProjectTemplate(String selectedProjectType)
   {
      return reportMgr.getProjectTemplateByType(selectedProjectType);
   }

   public Stage[] getAllStageTemplates(String selectedProjectType)
   {
      return reportMgr.getAllAvailableStages(selectedProjectType);
   }

   public String[][] getAvailableProjectTypes()
   {
      try
      {
         String[][] typeNames = new String[2][];
         ProjectFactory factory = ProjectFactory.getFactory();
         String[] types = passport.getServiceAreas("ReportManager");
         ArrayList typeList = new ArrayList();
         ArrayList nameList = new ArrayList();
         //typeList.add("ALL");
         //nameList.add("All Types");
         for(int i=0; i<types.length; i++)
         {
            typeList.add(types[i]);
            nameList.add(factory.getProjectTemplate(types[i]).getDisplayTypeName());
         }

         typeNames[0] = (String[])typeList.toArray(new String[]{});
         typeNames[1] = (String[])nameList.toArray(new String[]{});

         return typeNames;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         return new String[1][0];
      }
   }

   /***************************************
    *  Implement GenericHandler's method  *
    ***************************************/

   protected void init() throws Exception
   {
      ensureResource();

      if(null==reportMgr)
         throw new Exception("Report manager should not be null.");
   }

   protected void destroy()
   {
      reportMgr = null;
   }

   /***************************************
    *           my action method          *
    ***************************************/

   public void load()
   {
      removeControl("panel");
      Date now = new Date();
      displayedYear = now.getYear();
      displayedMonth = now.getMonth();
      displayedDay = now.getDate();
      System.out.println("||||||||||||||||||||||||||||||||||" + displayedYear + ":" + displayedMonth + ":" + displayedDay);
   }

   public void switchView()
   {
      removeControl("panel");
   }

   public void switchTime()
   {
      removeControl("panel");
   }

   public void selectItems()
   {
      removeControl("panel");
   }

   public void forward()
   {
      switch(calendarView)
      {
         case 2:
         {
            displayedMonth++;
            break;
         }
         case 1:
         {
            displayedYear++;
            break;
         }
         case 0:
         {
            displayedYear++;
            break;
         }
      }
      removeControl("panel");
   }

   public void backward()
   {
      switch(calendarView)
      {
         case 2:
         {
            displayedMonth--;
            break;
         }
         case 1:
         {
            displayedYear--;
            break;
         }
         case 0:
         {
            displayedYear--;
            break;
         }
      }
      removeControl("panel");
   }

   public void toSearchMode()
   {
      tab = "search";
      removeControl("panel");
   }

   public void toBrowseMode()
   {
      tab = "browse";
      removeControl("panel");
   }

   public void updateData()
   {
      removeControl("panel");
   }
}