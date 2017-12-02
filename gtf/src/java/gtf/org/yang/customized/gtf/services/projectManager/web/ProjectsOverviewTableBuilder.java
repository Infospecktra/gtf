/* Generated by Together */
package org.yang.customized.gtf.services.projectManager.web;

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

public class ProjectsOverviewTableBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      StageNotesBean stageBean = (StageNotesBean)bean;
      String codeBase = stageBean.getAppBase() + stageBean.getGuiBase();
      DataSheetSet reportDetail = null;
      if(null==(reportDetail=(DataSheetSet)stageBean.getControl("dataSheet")))
      {
         reportDetail = createReportDetail(stageBean, codeBase);
         stageBean.setControl("dataSheet", reportDetail);
      }

      return reportDetail;
   }

   private DataSheetSet createReportDetail(StageNotesBean stageBean, String codeBase)
   {
      DataSheetSet reportDetail = new DataSheetSet();
      DataSheet sheet = null;
      DataRow row = null;
      DataItem item = null;

      // ThirdTable
      Project[] projects = null;
      try
      {
         projects = stageBean.getAllProjects();
      }
      catch(Exception e)
      {
         projects = new Project[0];
      }

      sheet = new DataSheet();
      for(int i=0; i<projects.length; i++)
      {
         try
         {
            row = new DataRow();

            item = new TextItem();
            item.setName("Project");
            ((TextItem)item).setText(projects[i].getName());
            row.addElement(item);

            item = new TextItem();
            item.setName("Investigator");
            ((TextItem)item).setText(projects[i].getInvestigator());
            row.addElement(item);

            item = new TextItem();
            item.setName("ES");
            ((TextItem)item).setText(projects[i].getDataValue("esCelline"));
            row.addElement(item);

            item = new TextItem();
            item.setName("Linearization");
            ((TextItem)item).setText(projects[i].getDataValue("linearizationEnzyme"));
            row.addElement(item);

            item = new TextItem();
            item.setName("Neg. sele.");
            ((TextItem)item).setText(projects[i].getDataValue("negativeSelection"));
            row.addElement(item);

            item = new TextItem();
            item.setName("Colony#");
            ((TextItem)item).setText(projects[i].getDataValue("numberOfColonies"));
            row.addElement(item);

            sheet.addDataRow(row);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      reportDetail.addDataSheet(sheet);

      return reportDetail;
   }
}