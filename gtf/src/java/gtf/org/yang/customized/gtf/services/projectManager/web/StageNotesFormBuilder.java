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
import org.yang.util.DateFormatter;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.web.view.controls.jsStyle.InformationForm;
import java.util.HashMap;
import java.util.Iterator;
import org.yang.services.dataAccess.Data;
import org.yang.web.view.controls.jsStyle.misc.MessageDisplayingBanner;
import org.yang.web.view.controls.jsStyle.Photo;
import org.yang.web.view.controls.jsStyle.panel.GenericPanel;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;
import org.yang.customized.gtf.services.projectManager.ProjectManager;
import java.util.ArrayList;
import java.util.Set;

public class StageNotesFormBuilder implements WebControlBuilder
{
   boolean isClient = true;
   boolean isAlwaysAllowUpdate = false;
   boolean isAllowXUserUpdate = false;
   String stageOwnerType = "server";
   Stage currentStage = null;

   public WebControl build(GenericBean bean) throws Exception
   {
      StageNotesBean stageBean = (StageNotesBean)bean;
      String appBase = stageBean.getAppBase();
      String codeBase = stageBean.getGuiBase();
      currentStage = stageBean.getCurrentStage();

      if(null==currentStage)
      {
         MessageDisplayingBanner banner = new MessageDisplayingBanner();
         banner.setText("No stage slected, Please select a stage.");
         banner.setColor("#c0c0c0");
         return banner;
      }

      isClient = stageBean.gotPermit("ProjectManager", stageBean.getProjectType(), ProjectManager.CLIENT);
      stageOwnerType = currentStage.getOwnerType();
//System.out.println("---------------> is Client:" + isClient);
//System.out.println("---------------> stage owner type:" + stageOwnerType);

      if(isClient&&ProjectManager.SERVER.equals(stageOwnerType))
      {
         MessageDisplayingBanner banner = new MessageDisplayingBanner();
         banner.setText("No stage data is available.");
         banner.setColor("#c0c0c0");
         return banner;
      }

      return createPanel(stageBean, appBase, codeBase);
   }

   private GenericPanel createPanel(StageNotesBean stageBean, String appBase, String codeBase) throws Exception
   {
      String currentTab = stageBean.getTargetOwner();
      String currentUserName = stageBean.getUsername();
      if(null==currentTab||"".equals(currentTab))
        currentTab = currentUserName;

      ArrayList allTabs = new ArrayList();
      if(stageBean.gotPermit("ProjectManager", stageBean.getProjectType(), "xuser"))
      {
         Set set = currentStage.getAllOwnerNames();
         set.add(currentUserName);
         allTabs.addAll(set);
         if(!set.contains(currentTab))
         {
            currentTab = currentUserName;
         }
      }
      else
         allTabs.add(currentTab);

      GenericPanel panel = new GenericPanel();
      panel.setId("projectsTreePanel");
      panel.setDisableForm(true);
      panel.setText("Current project : " + stageBean.getCurrentProject().getName() + "&nbsp;/&nbsp;Current stage : " + currentStage.getName());
      panel.setCodeBase(codeBase);
      panel.setColor("#550000");
      panel.setBgColor("#ffffff");
      panel.setActionLink(appBase + "/projectsList.wf");

      TabPanelElement ownerTab = null;
      String aTab = null;
      for(int i=0; i<allTabs.size(); i++)
      {
         // project list
         aTab = (String)allTabs.get(i);
         ownerTab = new TabPanelElement();
         ownerTab.setId(aTab);
         if(isClient&&!aTab.equals(currentUserName))
            ownerTab.setText("Data");
         else
            ownerTab.setText(aTab);
         ownerTab.setCodeBase(appBase + codeBase);
         ownerTab.setColor("#F0F0F0");
         ownerTab.setTabSwitchingLink("/wf/stageNotes.wf?actiontype=changeDataOwner&targetOwner=" + aTab);

         if(aTab.equals(currentTab))
         {
            ownerTab.addASubcontrol(createForm(stageBean, appBase + codeBase, aTab));
            ownerTab.setOnFocus(true);
         }
         else
            ownerTab.setOnFocus(false);

         panel.addASubcontrol(ownerTab);
      }

      return panel;
   }

   private InformationForm createForm(StageNotesBean stageBean, String codeBase, String dataOwnerName) throws Exception
   {
      isAlwaysAllowUpdate = stageBean.gotPermit("ProjectManager", stageBean.getProjectType(), ProjectManager.UPDATE)&&
                            (stageBean.getUsername().equals(dataOwnerName)||
                             stageBean.gotPermit("ProjectManager", stageBean.getProjectType(), ProjectManager.APPROVE));

//System.out.println("---------------> is always allowing update:" + isAlwaysAllowUpdate);

      InformationForm form = new InformationForm();
      form.setCaption("Stage Notes");
      form.setActionLink(stageBean.getAppBase() + "/stageNotes.wf");
      form.setId("stageNotes");

      if(isAlwaysAllowUpdate||Stage.DONE!=currentStage.getStatus())
      {
         ButtonElement save = new ButtonElement();
         save.setAction("javascript:submitForm('stageNotes','update')");
         save.setAlt("Update stage notes");
         save.setName("save");
         save.setOffIcon(codeBase + "/images/English/btn_update.gif");
         save.setOnIcon(codeBase + "/images/English/btn_update_on.gif");
         form.addAButton(save);
      }

      if(Stage.DONE!=currentStage.getStatus())
      {
         // 1. if this stage is owned by both this button shall be shown.
         // 2. if this stage is owned by client and you are client this button shall be shown
         // 3. if this stage is owned by server and you are not client this button shall be shown
         if("both".equals(stageOwnerType)||
            (ProjectManager.CLIENT.equals(stageOwnerType)&&isClient)||
            ("server".equals(stageOwnerType)&&!isClient))
         {
            ButtonElement doneB = new ButtonElement();
            doneB.setAction("javascript:submitForm('stageNotes','done')");
            doneB.setAlt("Stage done");
            doneB.setName("done");
            doneB.setOffIcon(codeBase + "/images/English/btn_done.gif");
            doneB.setOnIcon(codeBase + "/images/English/btn_done_on.gif");
            form.addAButton(doneB);
         }
      }

      ButtonElement help = new ButtonElement();
      help.setAction("javascript:top.topFrame.openSWHelpWindow('" + codeBase + "/help/English/help.jsp?right=projects.htm#project')");
      help.setName("help");
      help.setOffIcon(codeBase + "/images/English/btn_help.gif");
      help.setOnIcon(codeBase + "/images/English/btn_help_on.gif");
      help.setAlt("Help");
      form.addAButton(help);

      String dateFormat = currentStage.getDateDisplayFormat();

      // done date (client)
      TextField doneDate = new TextField();
      doneDate.setTitle(currentStage.getDateDisplayName());
      doneDate.setId("doneDate");
      doneDate.setName("doneDate");
      long done = currentStage.getDoneDate();
      //System.out.println("[StageNotesFormBuilder::createForm]-------->done="+done);
      if(-1==done)
         doneDate.setValue("not done yet");
      else
         doneDate.setValue(DateFormatter.getDateTimeString(new Date(done), dateFormat));  //
		
	  
	  if((-1!=done)&&stageBean.gotPermit("ProjectManager", stageBean.getProjectType(), ProjectManager.SYSTEM_DATA)) 
	     doneDate.setDisplayOnly(false);
	  else
         doneDate.setDisplayOnly(true);
	  
	  form.addASubcontrol(doneDate);

      // last update date
      if(!isClient)
      {
         TextField lastUpdateDate = new TextField();
         lastUpdateDate.setTitle("Last update date");
         lastUpdateDate.setId("lastUpdateDate");
         lastUpdateDate.setName("lastUpdateDate");
         long lastUpdate = currentStage.getLastUpdateDate();

         if(-1==lastUpdate)
            lastUpdateDate.setValue("no update yet");
         else
            lastUpdateDate.setValue(DateFormatter.getDateTimeString(new Date(lastUpdate), dateFormat));
         lastUpdateDate.setDisplayOnly(true);
         form.addASubcontrol(lastUpdateDate);
      }

      // real datas
      Data[] datas = currentStage.getDatas();
      if(null!=datas)
      {
         String name = null;
         String value = null;
         for(int i=0; i<datas.length; i++)
         {
            //System.out.println("data name:" + datas[i].getName() + ", data avail. for client:" + datas[i].getAvailForClient());
            // client
            if(isClient&&!datas[i].getAvailForClient())
               continue;

            datas[i].setId(currentStage.getId() + "-" + datas[i].getName());
            // getDisplay(Data data, String dataOwner, boolean displayOnly)
            // So readonly -> if current user client and this stage is only for server
            form.addASubcontrol(datas[i].getDisplayer().getDisplay(datas[i],
                                                                   dataOwnerName,
                                                                   isClient&&"server".equals(stageOwnerType)));
         }
      }

      // note
      if(!isClient||ProjectManager.CLIENT.equals(stageOwnerType))
      {
         TextAreaElement note = new TextAreaElement();
         note.setTitle("Note history");
         note.setId("note");
         note.setName("note");

         if(Stage.DONE!=currentStage.getStatus())
         {
            note.setRows("5");
         }
         else
         {
            note.setRows("10");
         }
         note.setCols("60");
         note.setValue(currentStage.getNote());
         note.setIsReadOnly(true);
         form.addASubcontrol(note);

         if(isAlwaysAllowUpdate||Stage.DONE!=currentStage.getStatus())
         {
            TextAreaElement updateNote = new TextAreaElement();
            updateNote.setTitle("Update Note");
            updateNote.setId("updateNote");
            updateNote.setName("updateNote");
            updateNote.setRows("10");
            updateNote.setCols("60");
            updateNote.setValue("");
            updateNote.setIsReadOnly(false);
            form.addASubcontrol(updateNote);
         }
      }

      return form;
   }
}