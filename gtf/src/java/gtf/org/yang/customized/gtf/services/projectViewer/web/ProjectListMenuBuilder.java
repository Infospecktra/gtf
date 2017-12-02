/* Generated by Together */
package org.yang.customized.gtf.services.projectViewer.web;

import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.navigationList.NavigationList;
import org.yang.web.view.controls.jsStyle.navigationList.CheckListItem;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import org.yang.web.view.controls.jsStyle.navigationList.List;
import org.yang.web.view.controls.jsStyle.panel.GenericPanelWithDropdownController;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;
import org.yang.web.view.controls.jsStyle.dataSheet.SelectableItemWithoutForm;
import org.yang.customized.gtf.services.projectViewer.ProjectViewerManager;

public class ProjectListMenuBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      ProjectListMenuBean projectListMenuBean = (ProjectListMenuBean)bean;
      String codeBase = projectListMenuBean.getAppBase() + projectListMenuBean.getGuiBase();
      GenericPanelWithDropdownController panel = null;
      //if(null==(list=(NavigationList)projectListMenuBean.getControl("projectList")))
      //{
      panel = createPanel(projectListMenuBean, projectListMenuBean.getAppBase(), codeBase);
      //   projectListMenuBean.setControl("projectList", list);
      //}

      return panel;
   }

   private GenericPanelWithDropdownController createPanel(ProjectListMenuBean projectListMenuBean, String appBase, String codeBase)
   {
      String[] allTabs  = {"Project Viewer"};
      String currentTab = "Project Viewer";

      GenericPanelWithDropdownController panel = new GenericPanelWithDropdownController();
      panel.setId("projectlist_form");
      panel.setText("");
      panel.setCodeBase(codeBase);
      panel.setColor("#C0C0C0");
      panel.setActionLink(appBase + "/projectListMenu.wf");

      TabPanelElement typeTab = null;
      for(int i=0; i<allTabs.length; i++)
      {
         // project list
         NavigationList list = createList(projectListMenuBean, appBase, codeBase);
         typeTab = new TabPanelElement();
         typeTab.setText(allTabs[i]);
         typeTab.setCodeBase(codeBase);
         //typeTab.setTabSwitchingLink("/wf/projectListMenu.wf?actiontype=changeType&type=" + allTabs[i]);
         typeTab.addASubcontrol(list);

         if(allTabs[i].equals(currentTab))
            typeTab.setOnFocus(true);
         else
            typeTab.setOnFocus(false);

         panel.addASubcontrol(typeTab);
      }
      String[] viewMode_displayNames = {"Project Results"};
      String[] viewMode_values = {ProjectViewerManager.DATA_ON_TABLE_NAME_3};
      if("GTF".equals(projectListMenuBean.getLoginDomain()))  
      {
         viewMode_displayNames = new String[]{"Project Summary","Southern Analysis","Project Results"};
         viewMode_values = new String[]{ProjectViewerManager.DATA_ON_TABLE_NAME_1,ProjectViewerManager.DATA_ON_TABLE_NAME_2,ProjectViewerManager.DATA_ON_TABLE_NAME_3};
      }
      SelectableItemWithoutForm  viewMode = new SelectableItemWithoutForm();
      viewMode.setId("viewMode");
      viewMode.setName("viewMode");
      viewMode.setCaption("Select data mode");
      viewMode.setOnChange("javascript:submitFormThenTargetTo('projectlist_form','changePage','_self')");
      
      viewMode.setDisplayNames(viewMode_displayNames);
      viewMode.setValues(viewMode_values);
      
      viewMode.setSelectedValue(projectListMenuBean.getViewMode());
      viewMode.setCssStyle("width : 130px;\"font-family : monospace; font-size : 8pt");
      viewMode.setIsWithoutForm(true);
      viewMode.setNeedPromptOption(false);
      panel.setSelector(viewMode);

      return panel;
   }

   private NavigationList createList(ProjectListMenuBean projectListMenuBean, String appBase, String codeBase)
   {
      NavigationList list = new NavigationList(new List());
      list.setTitle(null);
      list.setCodeBase(codeBase);
      list.setId("projectList");
      String[] selectedCheckIds = projectListMenuBean.getTargetIds();
      Project[] projects = projectListMenuBean.getDisplayProjects();
      CheckListItem item = null;
      String id = null;
      String projectNumber = null;
      String investigator = null;
      String labName = null;
      //System.out.println("[ProjectListMenuBuilder::createList]----->selectedCheckIds.length="+selectedCheckIds.length);
      //System.out.println("[ProjectListMenuBuilder::createList]----->projects.length="+projects.length);
      
      for(int i=0; i<projects.length; i++)  
      {
         id = projects[i].getId();
         if(id==null)
            id="";
         investigator = projects[i].getInvestigator();
         labName = projects[i].getDomain(); 
         //System.out.println("[ProjectListMenuBuilder::createList]----->id="+id);
         //System.out.println("[ProjectListMenuBuilder::createList]----->investigator/lab="+investigator+"/"+labName);

         item = new CheckListItem();
         item.setId(id);
         item.setText(projects[i].getName());
         item.setControlName("targetIds");
         item.setDescriptionPrompt("Investigator");
         item.setDescriptionValue(investigator);
                 
         for(int v=0;v<selectedCheckIds.length;v++)
         { 
             if(selectedCheckIds[v].equals(id))
               item.setIsChecked(true);	
         }	        
                 
         //item.setOnClick("javascript:selectCheckBoxesOnClick('projectlist_form','targetIds',"+i+",'changeSelectedItems','_parent')");
         list.addListItem(item);
      }
      
      for(int j = projects.length; j<20; j++)
      {
         list.addListItem(new CheckListItem());  
      }
 
 
      ButtonElement button1 = new ButtonElement();
      button1.setAction("javascript:setCheckBoxAll_Mode2('projectlist_form','targetIds',1)");
      button1.setOnIcon(codeBase + "/images/ico_chkall.gif");
      button1.setOffIcon(codeBase + "/images/ico_chkall.gif");
      button1.setId("checkAll");
      button1.setName("checkAll");
      button1.setAlt("Check all");
      list.addButton(button1);    
      ButtonElement button2 = new ButtonElement();
      button2.setAction("javascript:setCheckBoxAll_Mode2('projectlist_form','targetIds',0)");
      button2.setOnIcon(codeBase + "/images/ico_unchkall.gif");
      button2.setOffIcon(codeBase + "/images/ico_unchkall.gif");
      button2.setId("uncheckAll");     
      button2.setName("uncheckAll");
      button2.setAlt("Uncheck all");
      list.addButton(button2);

      return list;
   }
}