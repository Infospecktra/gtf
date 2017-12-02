/* Generated by Together */
package org.yang.customized.gtf.services.reportManager.web;

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
import org.yang.customized.gtf.services.projectManager.ProjectManager;
import org.yang.customized.gtf.services.projectManager.web.StageNotesBean;
import java.util.ArrayList;
import java.util.HashSet;
import org.yang.web.view.controls.jsStyle.dataSheet.SelectableItem;

public class ReportMasterTableBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      ReportsListBean reportsListBean = (ReportsListBean)bean;

      // if no project available
      Project[] projects = null;
      try
      {
         projects = reportsListBean.getMatchedProjects();
      }
      catch(Exception e){}

      if(null==projects||0>=projects.length)
      {
         MessageDisplayingBanner banner = new MessageDisplayingBanner();
         banner.setText("No matched project is available.");
         banner.setColor("#c0c0c0");
         return banner;
      }

      String codeBase = reportsListBean.getAppBase() + reportsListBean.getGuiBase();
      DataSheetSet reportDetail = null;
      if(null==(reportDetail=(DataSheetSet)reportsListBean.getControl("reportMasterTable")))
      {
         if(null==(reportDetail=buildDataSheetSet(reportsListBean, projects, codeBase)))
         {
            MessageDisplayingBanner banner = new MessageDisplayingBanner();
            banner.setText("No matched project is available.");
            banner.setColor("#c0c0c0");
            return banner;
         }
         reportsListBean.setControl("reportMasterTable", reportDetail);
      }
      else
      {
         reportDetail.getDatasheet("reportMasterTable").getPageNumberSelector().setSelectedValue(reportsListBean.getPage());
      }
      reportDetail.getDatasheet("reportMasterTable").sort(reportsListBean.getSortBy(), reportsListBean.getIsAcending());

      return reportDetail;
   }

   private DataSheetSet buildDataSheetSet(ReportsListBean reportsListBean, Project[] projects, String codeBase)
   {
      DataSheetSet reportDetail = new DataSheetSet();
      ArrayList temp = new ArrayList();
      String type = reportsListBean.getProjectType();
      for(int i=0; i<projects.length; i++)
      {
         if(type.equals(projects[i].getType()))
            temp.add(projects[i]);
      }

      if(0==temp.size())
         return null;

      reportDetail.addDataSheet(createReportDetail(reportsListBean, (Project[])temp.toArray(new Project[]{}), codeBase));
      return reportDetail;
   }

   private DataSheet createReportDetail(ReportsListBean reportsListBean, Project[] projects, String codeBase)
   {
      //HashSet displayItemsSet = new HashSet();
      //String[] items = reportsListBean.getDisplayItems();
      //for(int i=0; i<items.length; i++)
      //{
      //   displayItemsSet.add(items[i]);
      //}

      boolean isClient = reportsListBean.gotPermit("ProjectManager", reportsListBean.getProjectType(), ProjectManager.CLIENT);

//System.out.println("---------------> is Client:" + isClient);
//System.out.println("---------------> stage owner type:" + stageOwnerType);

      DataSheet sheet = new DataSheet();
      sheet.setId("reportMasterTable");
      sheet.setActionLink("/wf/reportsList.wf");
      sheet.setRowsPerPage(10);

      SelectableItem paging = new SelectableItem();
      paging.setId("page");
      paging.setName("page");
      paging.setCaption("Select page");
      paging.setActionLink("/wf/reportsList.wf");
      //paging.setActionTarget("_parent");
      paging.setOnChange("javascript:submitForm('" + paging.getId() + "','changePage')");
      System.out.println("page----------------------->" + reportsListBean.getPage());
      paging.setSelectedValue(reportsListBean.getPage());
      paging.setNeedPromptOption(false);

      sheet.setPageNumberSelector(paging);

      DataRow row = null;
      DataItem item = null;

      Stage[] templates = reportsListBean.getAllStageTemplates();

      HashMap stages = null;
      Stage aStage = null;
      Data[] datas = null;
      String[] values = null;
      String value = null;
      String domain = null;
      String stageColor = null;
      String dataOwnerType = null;

      //Iterating all projects
      for(int i=0; i<projects.length; i++)
      {
         try
         {
            stages = reportsListBean.getCurrentAvailableStages(projects[i].getId());
            row = new DataRow();

            item = new TextItem();
            item.setName("Project Name");
            item.setId("name");
            //item.setActionLink("/wf/stageAccess.wf?actiontype=showBrief&projectId=" + projects[i].getId() +
            //                                       "&projectType=" + projects[i].getType());
            String url ="/wf/projectInfoAccess.wf?actiontype=load&id=" + projects[i].getId() +
			            "&projectType=" + projects[i].getType();
			item.setActionLink("JavaScript:openCustomizedWindow('"+url+"',750,700)");
                                                  
           
			((TextItem)item).setText(projects[i].getName());
            row.addElement(item);

            domain = projects[i].getDomain();

            //if(displayItemsSet.contains("domain"))
            {
               item = new TextItem();
               item.setName("Lab Name");
               item.setId("domain");
               ((TextItem)item).setText(domain);
               row.addElement(item);
            }

            //if(displayItemsSet.contains("investigator"))
            {
               item = new TextItem();
               item.setName("Investigator");
               item.setId("investigator");
               ((TextItem)item).setText(reportsListBean.whoIsIt(domain, projects[i].getInvestigator()));
               row.addElement(item);
            }

            // Iterating all project's datas
            datas = projects[i].getDatas();
            for(int ii=0; ii<datas.length; ii++)
            {
               if(!datas[ii].getOnMasterTable())//||!displayItemsSet.contains(datas[ii].getName()))
                  continue;
               item = new TextItem();
               item.setName(datas[ii].getBriefDisplayName());
               value = datas[ii].getValue();
               if(null==value||"".equals(value))
                  value = "n/a";
               ((TextItem)item).setText(value);
               row.addElement(item);
            }

            // Iterating all stages
            String stageName = null;
            for(int j=0; j<templates.length; j++)
            {
               stageName = templates[j].getName();
               aStage = (Stage)stages.get(stageName);
               stageColor = templates[j].getBgnColorOnMasterTable();

               if(templates[j].getDateOnMasterTable())//&&!displayItemsSet.contains("date_" + templates[j].getName()))
               {
                  item = new TextItem();
                  item.setBgcolor(stageColor);
                  item.setId(templates[j].getDateDisplayName().replace('#','N'));
                  item.setName(templates[j].getDateDisplayName());
                  if(null!=aStage&&-1!=aStage.getDoneDate())
                     value = DateFormatter.getDateTimeString(new Date(aStage.getDoneDate()), templates[j].getDateDisplayFormat());
                  if(null==value||"".equals(value))
                     value = "n/a";
                  //if(null!=aStage)
                  //{
                     item.setActionLink("/wf/stageAccess.wf?actiontype=showStage&projectId=" + projects[i].getId() +
                                                                               "&name=" + stageName +
                                                                               "&projectType=" + projects[i].getType());
                     item.setActionTarget("rightBottomFrame");
                     ((TextItem)item).setBold(true);
                  //}
                  ((TextItem)item).setText(value);
                  row.addElement(item);
               }

               datas = templates[j].getDatas();
               for(int k=0; k<datas.length; k++)
               {
                  // 1. data was defined shown on master table
                  // 2. the user either is not client
                  // 3. or user is client and data is available for client
                  if(datas[k].getOnMasterTable()&&
                     (!isClient||(isClient&&datas[k].getAvailForClient())))//&&displayItemsSet.contains(stageName + "_" + datas[k].getName()))
                  {
                     item = new TextItem();
                     ((TextItem)item).setColor("#000066");
                     item.setBgcolor(stageColor);
                     item.setId(datas[k].getBriefDisplayName().replace('#','N'));
                     item.setName(datas[k].getBriefDisplayName());
                     if(null!=aStage)
                     {
                        try
                        {
                           values = aStage.getData(datas[k].getName()).getAllValues();
                           item.setActionLink("/wf/stageAccess.wf?actiontype=showStage&projectId=" + projects[i].getId() +
                                                                                     "&name=" + aStage.getName() +
                                                                                     "&projectType=" + projects[i].getType());
                           item.setActionTarget("rightBottomFrame");
                           ((TextItem)item).setBold(true);
                           //System.out.println("data name:" + datas[k].getName() + ", data value:" + value);
                           if(null==values||0==values.length)
                           {
                              ((TextItem)item).setText("n/a");
                           }
                           else
                           {
                              ((TextItem)item).setTexts(values);
                           }
                        }
                        catch(DataUnavailableException e)
                        {
                           System.out.println("Data is not available, set it to empty string.");
                           ((TextItem)item).setText("n/a");
                        }
                     }
                     else
                     {
                        ((TextItem)item).setText("n/a");
                     }
                     row.addElement(item);
                  }
               }
            }

            sheet.addDataRow(row);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }

      return sheet;
   }
}