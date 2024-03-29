/* Generated by Together */
package org.yang.customized.gtf.services.inventoryManager.web;
import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.panel.Panel;
import org.yang.web.view.controls.jsStyle.panel.GenericPanel;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import org.yang.web.view.controls.jsStyle.SelectElement;
import org.yang.web.view.controls.jsStyle.calendar.WebCalendar;

public class CalendarBuilder implements WebControlBuilder
{
   String codeBase = null;

   public WebControl build(GenericBean bean) throws Exception
   {
      CalendarBean calendarBean = (CalendarBean)bean;
      codeBase = calendarBean.getAppBase() + calendarBean.getGuiBase();

      WebCalendar calendar = null;
      if(null==(calendar=(WebCalendar)calendarBean.getControl("calendar")))
      {
         calendar = createCalendar(calendarBean);
         calendarBean.setControl("calendar", calendar);
      }
      calendar.setDisplayedYear(calendarBean.getDisplayedYear());
      calendar.setDisplayedMonth(calendarBean.getDisplayedMonth());
      //calendar.setDisplayedDay(calendarBean.getDisplayedDay());

      return calendar;
   }

   private WebCalendar createCalendar(CalendarBean accountBean)
   {
      WebCalendar calendar = WebCalendar.create(WebCalendar.GTF, accountBean.getCalendarView());
      calendar.setCodeBase(codeBase);
      calendar.setSwitchViewLink("/wf/inventoryCalendar.wf?actiontype=switchView&");
      calendar.setSwitchTimeActionLink("/wf/inventoryCalendar.wf?actiontype=switchTime");
      calendar.setBackwardActionLink("/wf/inventoryCalendar.wf?actiontype=backward");
      calendar.setForwardActionLink("/wf/inventoryCalendar.wf?actiontype=forward");

      return calendar;
   }
}