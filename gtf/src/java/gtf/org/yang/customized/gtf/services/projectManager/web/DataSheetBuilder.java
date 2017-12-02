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
import org.yang.web.view.controls.jsStyle.misc.MessageDisplayingBanner;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.Data;

public class DataSheetBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      ProjectNotesBean projectBean = (ProjectNotesBean)bean;
      String codeBase = projectBean.getAppBase() + projectBean.getGuiBase();

      // ThirdTable
      Project[] projects = null;
      try
      {
         projects = projectBean.getAllProjects();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      if(null==projects||0>=projects.length)
      {
         MessageDisplayingBanner banner = new MessageDisplayingBanner();
         banner.setText("No project is available.");
         banner.setColor("#c0c0c0");
         return banner;
      }

      DataSheetSet reportDetail = null;
      if(null==(reportDetail=(DataSheetSet)projectBean.getControl("dataSheet")))
      {
         reportDetail = createReportDetail(projects, codeBase);
         projectBean.setControl("dataSheet", reportDetail);
      }

      return reportDetail;
   }

   private DataSheetSet createReportDetail(Project[] projects, String codeBase)
   {
      DataSheetSet reportDetail = new DataSheetSet();
      DataSheet[] sheets = null;
      DataRow row = null;
      DataItem item = null;
      DataGroup[] groups = null;
      Data[] datas = null;
      for(int i=0; i<projects.length; i++)
      {
         try
         {
            groups = projects[i].getGroups();
            if(null==sheets)
               sheets = new DataSheet[groups.length];
            for(int j=0; j<groups.length; j++)
            {
               row = new DataRow();
               item = new TextItem();
               item.setName("Project");
               ((TextItem)item).setText(projects[i].getName());
               row.addElement(item);

               datas = groups[j].getDatas();
               for(int k=0; k<datas.length; k++)
               {
                  if(datas[k].getOnDataSheet())
                  {
                     item = new TextItem();
                     item.setName(datas[k].getBriefDisplayName());
                     ((TextItem)item).setText(datas[k].getValue());
                     row.addElement(item);
                  }
               }

               if(null==sheets[j])
                  sheets[j] = new DataSheet();
               sheets[j].addDataRow(row);
            }
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }

      for(int i=0; i<sheets.length; i++)
         reportDetail.addDataSheet(sheets[i]);

      return reportDetail;
   }
}