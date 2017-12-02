/* Generated by Together */
package org.yang.customized.gtf.services.projectViewer.web;

import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.UIForm;
import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.PassElement;
import org.yang.web.view.controls.jsStyle.SelectElement;
import org.yang.web.view.controls.jsStyle.TextAreaElement;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import org.yang.web.view.controls.jsStyle.dataSheet.DataSheet;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.web.view.controls.jsStyle.dataSheet.DataRow;
import org.yang.web.view.controls.jsStyle.dataSheet.DataItem;
import org.yang.util.DateFormatter;
import java.util.Date;
import org.yang.web.view.controls.jsStyle.dataSheet.TextItem;
import org.yang.web.view.controls.jsStyle.dataSheet.AccessIcon;
import org.yang.web.view.controls.jsStyle.dataSheet.DataSheetSet;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.services.dataAccess.Data;
import java.util.HashMap;
import org.yang.services.dataAccess.DataUnavailableException;
import org.yang.web.view.controls.jsStyle.misc.MessageDisplayingBanner;
import org.yang.customized.gtf.services.projectViewer.ProjectViewerManager;
import java.util.ArrayList;
import java.util.HashSet;
import org.yang.web.view.controls.jsStyle.dataSheet.SelectableItem;

public class ProjectViewerMasterBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      ProjectViewerMasterBean projectMasterBean = (ProjectViewerMasterBean)bean;

      // if no project available
      Project[] projects = null;
      try
      {
          projects = projectMasterBean.getDisplayProjects(); //need to be changed
      }
      catch(Exception e){}
      if(null==projects||0>=projects.length)
      {
         MessageDisplayingBanner banner = new MessageDisplayingBanner();
         banner.setText("No matched project is available.");
         banner.setColor("#c0c0c0");
         return banner;
      }      
      String codeBase = projectMasterBean.getAppBase() + projectMasterBean.getGuiBase();
      DataSheetSet reportDetail = null;
      if(null==(reportDetail=(DataSheetSet)projectMasterBean.getControl("projectViewerMaster")))
      {
         if(null==(reportDetail=buildDataSheetSet(projectMasterBean, projects, codeBase)))
         {
            MessageDisplayingBanner banner = new MessageDisplayingBanner();
            banner.setText("No matched project is available.");
            banner.setColor("#c0c0c0");
            return banner;
         }
         projectMasterBean.setControl("projectViewerMaster", reportDetail);
      }
      else
      {
         reportDetail.getDatasheet("projectViewerMaster").getPageNumberSelector().setSelectedValue(projectMasterBean.getPage());
      }
      reportDetail.getDatasheet("projectViewerMaster").sort(projectMasterBean.getSortBy(), projectMasterBean.getIsAcending());

      return reportDetail;
   }

   private DataSheetSet buildDataSheetSet(ProjectViewerMasterBean projectMasterBean, Project[] projects, String codeBase)
   {
      DataSheetSet reportDetail = new DataSheetSet();
      reportDetail.addDataSheet(createReportDetail(projectMasterBean, projects, codeBase));
      return reportDetail;
   }

   private DataSheet createReportDetail(ProjectViewerMasterBean projectMasterBean, Project[] projects, String codeBase)
   {
      DataSheet sheet = new DataSheet();
      sheet.setId("projectViewerMaster");
      sheet.setActionLink("/wf/projectViewerMaster.wf");
      sheet.setRowsPerPage(20);
      sheet.setDataSizeNote("Total size : "+projectMasterBean.getDisplayProjectsSize());

      SelectableItem paging = new SelectableItem();
      paging.setId("page");
      paging.setName("page");
      paging.setCaption("Select page");
      paging.setActionLink("/wf/projectViewerMaster.wf");
      paging.setOnChange("javascript:submitForm('" + paging.getId() + "','changePage')");
      paging.setSelectedValue(projectMasterBean.getPage());
      paging.setNeedPromptOption(false);
      sheet.setPageNumberSelector(paging);

      DataRow row = null;
      TextItem item = null;
      String value = null;
      String id = "";
      String projectNumber = "";
      String investigator = "";
      String labName = "";
      String projectName = "";
      StringBuffer strBuffer = null;
      String[] groupNames = {ProjectViewerManager.DATAGROUP_NAME_1,ProjectViewerManager.DATAGROUP_NAME_2,ProjectViewerManager.DATAGROUP_NAME_3}; 
      String[] onTableNames = {ProjectViewerManager.DATA_ON_TABLE_NAME_1,ProjectViewerManager.DATA_ON_TABLE_NAME_2,ProjectViewerManager.DATA_ON_TABLE_NAME_3};
      String[] keys1 = {"esCelline","linearizationEnzyme","positiveSelection","negativeSelection","numColonies"}; 
      String[] columnNames1 = {"ESC","L Enzyme","+ Selection","- Selection","Colony#"}; 
      String[] keys2 = {"enzyme","probename","probDnaAmount","wtFragmentSize","mtFragmentSize"}; 
      String[] columnNames2 = {"RE","Probe","Probe DNA amt.","REF","REF"}; //REF = "wtFragmentSize/mtFragmentSize"
    
      String viewMode = projectMasterBean.getViewMode();
      for(int i=0; i<projects.length; i++)
      { 
         try
         {
        
            StringBuffer personalInfo = new StringBuffer();
            Data[] myData = null;
            row = new DataRow();
            
            projectNumber = projects[i].getAccountId();
            if(projectNumber==null||"null".equals(projectNumber))
            	projectNumber="&nbsp;";
            id=projects[i].getId();     
            investigator = projects[i].getInvestigator();
            labName = projects[i].getDomain();
            personalInfo.append(investigator).append(" /").append(labName);
            projectName = projects[i].getName(); 

            //Storage  informations
            row.setId(id+"");
            strBuffer = new StringBuffer("");
            strBuffer.append("ID : ")
                     .append(NAFormat(id));
                     
            // 1. projectNumber
            item = new TextItem();
            item.setName("Project#");
            item.setId("projectNumber");
            item.setText(projectNumber);
            item.setColor("#000000");
            item.setAltText(strBuffer.toString());
            ///item.setBgcolor(bgcolor);
            item.setBold(true);
            row.addElement(item);

            // 2. projectName
            item = new TextItem();
            item.setName("Project Name");
            item.setId("projectName");
            String fm_projectName = NAFormat(projectName);
            item.setText(fm_projectName);
            if(!"n/a".equals(fm_projectName)&&
               !"n /a".equals(fm_projectName))
               item.setColor("#000000");
            else
               item.setColor("#c71585");
            ///item.setBgcolor(bgcolor);   
            item.setAltText(strBuffer.toString());
            item.setBold(true);
            row.addElement(item);
            ArrayList dataNames = new ArrayList(); 
            
            // 3. Investigator/Lab
            if(viewMode.equals(onTableNames[0]))
            {
               String fm_investigatorLab = NAFormat(personalInfo.toString());
               item = new TextItem();
               item.setName("Investigator/Lab");
               item.setId("investigatorLab");
               item.setText(fm_investigatorLab);
               item.setAltText(strBuffer.toString());
               if(!"n/a".equals(fm_investigatorLab)&&
            	  !"n /a".equals(fm_investigatorLab))
                  item.setColor("#000000");
               else
                  item.setColor("#ff00ff");
               ///item.setBgcolor(bgcolor);   
               row.addElement(item);
              ///System.out.println("-------------------------------------->investigator/lab="+personalInfo);
            } 
            String REF_name = ""; 
            String REF_value2 = "";
            String dataName = "";
            String dataValue = "";
            String briefName = "";
            String thisOnTableName = "";
            //id = URLEncoder.encode(projects[i].getId());
            for(int x=0;x<groupNames.length;x++)
            {  
               myData = projects[i].getDatas(groupNames[x]);          
               //System.out.println("-------------------------------------->groupNames["+x+"]="+groupNames[x]);
               //System.out.println("-------------------------------------->viewMode="+viewMode);
               //System.out.println("-------------------------------------->myData.length="+myData.length);
               if(myData==null)
                  myData = new Data[0];	   
               for(int k=0;k<myData.length;k++)
               {   
                  //System.out.println("-------------------------------------->myData[k].getOnTableName()="+myData[k].getOnTableName());
                  //System.out.println("-------------------------------------->viewMode="+viewMode);
                  thisOnTableName = myData[k].getOnTableName();
                  if(thisOnTableName!=null&&(thisOnTableName).indexOf(viewMode)!=-1)
            	  {
                  //System.out.println("-------------------------------------->myData[k].getOnTableName()="+myData[k].getOnTableName());
                  //System.out.println("-------------------------------------->myData["+k+"].getBriefDisplayName()="+myData[k].getBriefDisplayName());
                  //System.out.println("-------------------------------------->myData["+k+"].getValue()="+myData[k].getValue());
                     dataName = myData[k].getName();
                     dataValue = myData[k].getValue();
                     briefName = myData[k].getBriefDisplayName();
                     if(keys2[4].equals(dataName))
                     {	  
                	     dataName=REF_name+"/"+dataName;
                	     dataValue=REF_value2+"/"+dataValue;
                	     briefName= "REF";
                     }
                     
                     if(!keys2[3].equals(dataName))
                     {
                         dataNames.add(dataName);
                         item = new TextItem();
                         item.setName(briefName);
                         item.setId(dataName);
                         String fm_value = NAFormat(dataValue);
                         item.setText(fm_value);
                         if(!"n/a".equals(fm_value)&&
                            !"n /a".equals(fm_value))
                            item.setColor("#000000");
                         else
                            item.setColor("#c71585");
                         ///item.setBgcolor(bgcolor);//#9370db   
                         row.addElement(item);
                     }
                     else
                     {	  
                	     REF_name = keys2[3];
                	     REF_value2 = dataValue;
                     }	  
                  }
               }
            }
           
         }
         catch(Exception e)
         {
              e.printStackTrace();
         }
         sheet.addDataRow(row);
        
      }
      return sheet;
   }
   
   public String NAFormat(String str)
   {

      if(str==null)
         return "n/a";
      str=str.trim();
      if("".equals(str))
         return "n/a";
      return str;
   }

}