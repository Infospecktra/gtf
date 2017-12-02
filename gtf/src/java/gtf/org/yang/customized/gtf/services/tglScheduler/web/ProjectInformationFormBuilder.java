/* Generated by Together */
package org.yang.customized.gtf.services.tglScheduler.web;

import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.UIForm;
import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.PassElement;
import org.yang.web.view.controls.jsStyle.SelectElement;
import org.yang.web.view.controls.jsStyle.TextAreaElement;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import java.util.HashMap;
import java.util.Iterator;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.web.view.controls.jsStyle.WebControlGroup;
import org.yang.services.dataAccess.DataGroup;
import org.yang.services.dataAccess.Data;
import org.yang.web.view.controls.jsStyle.misc.MessageDisplayingBanner1;
import org.yang.customized.gtf.services.scheduleManager.ScheduleManager;
import org.yang.web.view.controls.jsStyle.RadioButtonGroup;
import org.yang.web.view.controls.jsStyle.CompoundElement;
import org.yang.web.view.controls.jsStyle.DateField;
import java.util.Date;
import org.yang.util.SMUtility;

public class ProjectInformationFormBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      ProjectInformationBean projectBean = (ProjectInformationBean)bean;
      String codeBase = projectBean.getAppBase() + projectBean.getGuiBase();
      Project currentProject = projectBean.getCurrentProject();

      WebControlGroup controlGroup = new WebControlGroup();
      if(GenericBean.ERR==projectBean.getState())
      {
         MessageDisplayingBanner1 banner = new MessageDisplayingBanner1();
         banner.setText(projectBean.getMsg());
         banner.setColor("#cc0000");
         controlGroup.addASubcontrol(banner);
      }

      // User permission
      boolean xdomain = projectBean.gotPermit("Scheduler", currentProject.getType(), ScheduleManager.CROSS_DOMAIN);

      UIForm form = new UIForm();
      form.setCaption("Basic Information");

      TextField projectType = new TextField();
      projectType.setTitle("project type");
      projectType.setId("type");
      projectType.setName("type");
      projectType.setValue(currentProject.getDisplayTypeName());
      projectType.setDisplayOnly(true);
      form.addASubcontrol(projectType);

      TextField projectId = new TextField();
      projectId.setTitle("project ID");
      projectId.setId("id");
      projectId.setName("id");
      projectId.setValue(currentProject.getId());
      projectId.setIsReadOnly(true);
      form.addASubcontrol(projectId);

      TextField projectname = new TextField();
      projectname.setTitle("project name");
      projectname.setId("name");
      projectname.setName("name");
      projectname.setValue(currentProject.getName());
      projectname.setIsMandatory(true);
      form.addASubcontrol(projectname);

      TextAreaElement description = new TextAreaElement();
      description.setTitle("description");
      description.setId("description");
      description.setName("description");
      description.setValue(currentProject.getDescription());
      form.addASubcontrol(description);
      controlGroup.addASubcontrol(form);

      DataGroup[] dataGroups = currentProject.getGroups();
      Data[] datas = null;

      ////////////////////////////
      //   Datas for general    //
      ////////////////////////////

      form = new UIForm();
      form.setCaption(dataGroups[0].getDisplayName());
      datas = currentProject.getDatas(dataGroups[0].getName());

      // type
      String name = datas[0].getName();
      String[] possibleValue = datas[0].getPossibleValue();
      String type = null;
      if(null==(type=datas[0].getValue())||"".equals(type))
         type = possibleValue[0];

      if(Project.CREATE==currentProject.getAction())
      {
         SelectElement pType = new SelectElement();
         pType.setTitle(datas[0].getDisplayName());
         pType.setOnChange("javascript:submitForm('projectInformation','onChange')");
         pType.setId("datas_" + name);
         pType.setName("datas_" + name);
         pType.setSelectedValue(type);
         pType.setValues(possibleValue);
         form.addASubcontrol(pType);
      }
      else
      {
         TextField pType = new TextField();
         pType.setTitle(datas[0].getDisplayName());
         pType.setId("datas_" + name);
         pType.setName("datas_" + name);
         pType.setValue(type);
         pType.setDisplayOnly(true);
         form.addASubcontrol(pType);
      }

      //surrogateOnly
      String sOnly = null;
      if("B-Injection".equals(type)||
         "P-Injection".equals(type)||
         //"Cryopreservation".equals(type)||
         "Aggregation".equals(type))
      {
         name = datas[1].getName();
         possibleValue = datas[1].getPossibleValue();

         if(null==(sOnly=datas[1].getValue())||"".equals(sOnly))
            sOnly = possibleValue[0];

         CompoundElement surrogateOnly = new CompoundElement();
         surrogateOnly.setTitle(datas[1].getDisplayName());
         RadioButtonGroup radio = new RadioButtonGroup();
         radio.setOnChange("javascript:submitForm('projectInformation','onChange')");
         radio.setId("datas_" + name);
         radio.setName("datas_" + name);
         radio.setSelectedValue(sOnly);
         radio.setValues(possibleValue);
         //radio.setDisplayNames((String[])possibleValueDisplay.toArray(new String[]{}));
         radio.setIsVertical(true);
         surrogateOnly.addSubelement(radio);
         form.addASubcontrol(surrogateOnly);
      }
      else
      {
         sOnly = "no";
      }

      //B-Injection|P-Injection|Cryopreservation|IVF|ICSI|Aggregation|Rederivation
      if("B-Injection".equals(type)||
         "P-Injection".equals(type)||
         "Aggregation".equals(type)||
         //"Cryopreservation".equals(type)||
         "yes".equals(sOnly))
      {
         name = datas[2].getName();
         String[] possibleValueDisplays = null;
         if("B-Injection".equals(type))
         {
            possibleValue = new String[]{"2.5"};
            possibleValueDisplays = new String[]{"E 2.5 days"};
         }
         else if("P-Injection".equals(type))
         {
            possibleValue = new String[]{"0.5", "0.5BAC"};
            possibleValueDisplays = new String[]{"E 0.5 days", "E 0.5 days BAC"};
         }
         else if("Aggregation".equals(type))
         {
            possibleValue = new String[]{"2.5"};
            possibleValueDisplays = new String[]{"E 2.5 days"};
         }
         //else if("Cryopreservation".equals(type))
         //{
         //   possibleValue = new String[]{"0.5", "2.5"};
         //   possibleValueDisplays = new String[]{"E 0.5 days", "E 2.5 days"};
         //}
         else if("yes".equals(sOnly))
         {
            possibleValue = new String[]{"0.5", "0.5BAC", "2.5"};
            possibleValueDisplays = new String[]{"E 0.5 days", "E 0.5 days BAC", "E 2.5 days"};
         }

         String value = null;
         if(Project.CREATE==currentProject.getAction()||
            null==(value=datas[2].getValue())||
            "".equals(value))
            value = possibleValue[0];

         SelectElement sMother = new SelectElement();
         sMother.setTitle(datas[2].getDisplayName());
         sMother.setOnChange("javascript:submitForm('projectInformation','onChange')");
         sMother.setId("datas_" + name);
         sMother.setName("datas_" + name);
         sMother.setSelectedValue(value);
         sMother.setValues(possibleValue);
         sMother.setDisplayNames(possibleValueDisplays);
         form.addASubcontrol(sMother);
      }
      controlGroup.addASubcontrol(form);

      /////////////////////////////////
      //   Datas for donor mother    //
      /////////////////////////////////

      if("no".equals(sOnly))
      {
         datas = currentProject.getDatas(dataGroups[1].getName());
         form = new UIForm();
         form.setCaption(dataGroups[1].getDisplayName());
         int j = 0;
         for(j=0; j<datas.length-1; j++)
         {
            form.addASubcontrol(datas[j].getDisplayer().getDisplay(datas[j],
                                                                   null,
                                                                   false));
         }

         String[] date = null;
         int year = 0;
         int month = 0;
         int day = 0;
         try
         {
            date = SMUtility.splitByToken(datas[j].getValue(), "/", true);
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
         }
         catch(Exception e)
         {
             Date now = new Date();
             year = 1900 + now.getYear();
             month = now.getMonth();
             day = now.getDate();
         }

         DateField dueDate = new DateField();
         dueDate.setIsYearFirst(false);
         dueDate.setTitle(datas[j].getDisplayName());
         dueDate.setPrefixOn(true);
         dueDate.setId("eDate");
         dueDate.setName(datas[j].getName());
         dueDate.setOnChangeLink("javascript:submitForm('projectInformation','onChange')");
         dueDate.setYear(year);
         dueDate.setMonth(month);
         dueDate.setDate(day);
         form.addASubcontrol(dueDate);

         controlGroup.addASubcontrol(form);
      }

      ////////////////////////////////////
      //   Datas for surrogate mother   //
      ////////////////////////////////////

      if("B-Injection".equals(type)||
         "P-Injection".equals(type)||
         "Aggregation".equals(type)||
         //"Cryopreservation".equals(type)||
         "yes".equals(sOnly))
      {
         datas = currentProject.getDatas(dataGroups[2].getName());
         form = new UIForm();
         form.setCaption(dataGroups[2].getDisplayName());
         int j = 0;
         for(j=0; j<datas.length-1; j++)
         {
            form.addASubcontrol(datas[j].getDisplayer().getDisplay(datas[j],
                                                                   null,
                                                                   false));
         }

         String[] date = null;
         int year = 0;
         int month = 0;
         int day = 0;
         try
         {
            date = SMUtility.splitByToken(datas[j].getValue(), "/", true);
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
         }
         catch(Exception e)
         {
             Date now = new Date();
             year = 1900 + now.getYear();
             month = now.getMonth();
             day = now.getDate();
         }

         DateField dueDate = new DateField();
         dueDate.setIsYearFirst(false);
         dueDate.setTitle(datas[j].getDisplayName());
         dueDate.setPrefixOn(true);
         dueDate.setId("tDate");
         dueDate.setName(datas[j].getName());
         dueDate.setOnChangeLink("javascript:submitForm('projectInformation','onChange')");
         dueDate.setYear(year);
         dueDate.setMonth(month);
         dueDate.setDate(day);
         form.addASubcontrol(dueDate);

         controlGroup.addASubcontrol(form);
      }

      ButtonElement save = new ButtonElement();
      if(Project.CREATE==currentProject.getAction()&&
         projectBean.gotPermit("Scheduler", currentProject.getType(), "create"))
      {
         save.setAction("javascript:submitForm('projectInformation','create')");
         save.setAlt("Create project");
         save.setName("save");
         save.setOffIcon(codeBase + "/images/English/btn_create.gif");
         save.setOnIcon(codeBase + "/images/English/btn_create_on.gif");
         form.addAButton(save);
      }
      else if(projectBean.gotPermit("Scheduler", currentProject.getType(), "update"))
      {
         save.setAction("javascript:submitForm('projectInformation','update')");
         save.setAlt("Update project information");
         save.setName("save");
         save.setOffIcon(codeBase + "/images/English/btn_update.gif");
         save.setOnIcon(codeBase + "/images/English/btn_update_on.gif");
         form.addAButton(save);

         ButtonElement remove = new ButtonElement();
         //remove.setAction("javascript:submitForm('projectInformation','remove')");
         remove.setAction("javascript:confirmSubmitForm('projectInformation', 'remove', 'Will delete this engine, are you sure?', true)");
         remove.setAlt("Delete current project");
         remove.setName("remove");
         remove.setOffIcon(codeBase + "/images/English/btn_delete.gif");
         remove.setOnIcon(codeBase + "/images/English/btn_delete_on.gif");
         form.addAButton(remove);
      }

      ButtonElement help = new ButtonElement();
      help.setAction("javascript:top.topFrame.openSWHelpWindow('" + codeBase + "/help/English/help.jsp?right=schedule.htm#project')");
      help.setName("help");
      help.setOffIcon(codeBase + "/images/English/btn_help.gif");
      help.setOnIcon(codeBase + "/images/English/btn_help_on.gif");
      help.setAlt("Help");
      form.addAButton(help);

      return controlGroup;
   }
}