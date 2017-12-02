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
import java.util.Calendar;
import org.yang.customized.gtf.services.dataAccess.Timetable;
import org.yang.customized.gtf.services.dataAccess.Schedule;
import java.sql.Date;
import org.yang.util.SMUtility;

public class ScheduledTasksBuilder implements WebControlBuilder
{
   private static String[] MONS = {"January", "Febuary", "March", "April",
                                   "May", "June", "July", "Augst", "September",
                                   "October", "December", "November"};
   private static String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday",
                                   "Thursday", "Friday", "Saturday"};
   public WebControl build(GenericBean bean) throws Exception
   {
      ScheduledTasksBean tasksBean = (ScheduledTasksBean)bean;
      String codeBase = tasksBean.getAppBase() + tasksBean.getGuiBase();
      int year = tasksBean.getDisplayedYear();
      int month = tasksBean.getDisplayedMonth();
      int date = tasksBean.getDisplayedDay();

      WebControlGroup controlGroup = new WebControlGroup();
      if(false)
      {
         MessageDisplayingBanner1 banner = new MessageDisplayingBanner1();
         banner.setText(tasksBean.getMsg());//"Some required field is empty, Please submit it again.");
         banner.setColor("#cc0000");
         controlGroup.addASubcontrol(banner);
      }

      Calendar calendar = Calendar.getInstance();
      calendar.set(year+1900, month, date);

      WeekSchedule wSchedule = new WeekSchedule();
      wSchedule.setImageCodeBase(codeBase + "/images");


      if(tasksBean.getListMode())
      {
         buildListView(tasksBean, calendar, wSchedule);
         wSchedule.setImageCodeBase(codeBase + "/images");
         wSchedule.setIconLink(codeBase + "/images/day_on.gif");
         wSchedule.setActionLink(tasksBean.getAppBase()+
                                      "/scheduledTasks.wf?actiontype=load" +
                                                          "&listMode=false" +
                                                     "&displayedYear=" + year +
                                                    "&displayedMonth=" + month +
                                                      "&displayedDay=" + date);
      }
      else
      {
         buildCalendarView(tasksBean, calendar, wSchedule);
         wSchedule.setImageCodeBase(codeBase + "/images");
         wSchedule.setIconLink(codeBase + "/images/list_on.gif");
         wSchedule.setActionLink(tasksBean.getAppBase() +
                                          "/scheduledTasks.wf?actiontype=load" +
                                                              "&listMode=true" +
                                                         "&displayedYear=" + year +
                                                        "&displayedMonth=" + month +
                                                          "&displayedDay=" + date);
      }

      return wSchedule;
   }

   private void buildListView(ScheduledTasksBean tasksBean, Calendar calendar, WeekSchedule wSchedule)
   {
      wSchedule.setTitle("Tasks in next 10 days");
      FutureTasks tasks = new FutureTasks();

      java.sql.Date dateFrom = new java.sql.Date(calendar.getTimeInMillis());
      foreward(calendar, 10);
      java.sql.Date dateTo = new java.sql.Date(calendar.getTimeInMillis());
      Project[] projects = null;
      try
      {
         projects = tasksBean.getAllAvailableProjects(dateFrom, dateTo);
      }
      catch(Exception e) { e.printStackTrace(); }

      HashMap map = null;
      Timetable[] timetables = null;
      Schedule[] schedules = null;
      Schedule schedule = null;
      String sOnly = null;
      boolean isFirst = true;
      FutureTask task = null;
      String userdomain = null;
      String username = null;
      for(int i=0; i<projects.length; i++)
      {
         try
         {
            sOnly = projects[i].getDataValue("surrogateOnly");
         }
         catch(Exception e)
         {
            sOnly = "no";
         }

         timetables = tasksBean.getTimetablesUnderProject(projects[i]);
         for(int j=0; j<timetables.length; j++)
         {
            if(timetables[j].getId().endsWith(":0"))
               isFirst = true;
            else if(timetables[j].getId().endsWith(":1"))
               isFirst = false;
            schedules = tasksBean.getSchedulesUnderProject(timetables[j]);
            map = new HashMap();
            for(int k=0; k<schedules.length; k++)
            {
               map.put(schedules[k].getName(), schedules[k]);
            }

            task = new FutureTask();
            String[] type = SMUtility.splitByToken(timetables[j].getProjectType(), ":", true);

            if(!isFirst||"yes".equals(sOnly))
               task.setType(projects[i].getName()+"<br>"+type[1].replace('-','_')+"<br>"+type[2]);
            else
               task.setType(projects[i].getName()+"<br>"+type[1].replace('-','_'));

            if("yes".equals(sOnly))
            {
               task.setExpDate("--");
               task.setXferDate(date2String(timetables[j].getDueDate()));
            }
            else
            {
               if(isFirst)
               {
                  task.setExpDate(date2String(timetables[j].getDueDate()));
                  task.setXferDate("--");
               }
               else
               {
                  task.setExpDate("--");
                  task.setXferDate(date2String(timetables[j].getDueDate()));
               }
            }

            try
            {
               if("yes".equals(sOnly))
               {
                  task.setQuantity(projects[i].getDataValue("smQuantity"));
               }
               else
               {
                  if(isFirst)
                     task.setQuantity(projects[i].getDataValue("dmQuantity"));
                  else
                     task.setQuantity(projects[i].getDataValue("smQuantity"));
               }
            } catch(Exception e) {e.printStackTrace();}

            try
            {
               if("yes".equals(sOnly))
               {
                  task.setStrain(projects[i].getDataValue("smStrain"));
               }
               else
               {
                  if(isFirst)
                     task.setStrain(projects[i].getDataValue("dmStrain"));
                  else
                     task.setStrain(projects[i].getDataValue("smStrain"));
               }
            } catch(Exception e) {e.printStackTrace();}

            //try
            //{
            //   task.setOrder(((Schedule)map.get("orderMice")).getDueDate().toString());
            //} catch(Exception e) {e.printStackTrace();}

            try
            {
               if(null!=(schedule=(Schedule)map.get("mate")))
                  task.setHcgMate(date2String(schedule.getDueDate()));
               else
                  task.setHcgMate("--");
            } catch(Exception e) {e.printStackTrace();}

            try
            {
               if(null!=(schedule=(Schedule)map.get("plug")))
                  task.setPlugDate(date2String(schedule.getDueDate()));
               else
                  task.setPlugDate("--");
            } catch(Exception e) {e.printStackTrace();}

            try
            {
               if(null!=(schedule=(Schedule)map.get("pms")))
                  task.setPms(date2String(schedule.getDueDate()));
               else
                  task.setPms("--");
            } catch(Exception e) {e.printStackTrace();}

            tasks.addASubcontrol(task);
         }
      }
      wSchedule.addASubcontrol(tasks);
   }

   private void buildCalendarView(ScheduledTasksBean tasksBean, Calendar calendar, WeekSchedule wSchedule)
   {
      wSchedule.setTitle(calendar.get(Calendar.YEAR) + " " +
                         MONS[calendar.get(Calendar.MONTH)] +
                         " (Week " + calendar.get(Calendar.WEEK_OF_MONTH) + ")");
      int day = calendar.get(Calendar.DAY_OF_WEEK);
      backward(calendar, day-1);
      DaySchedule dSchedule = null;
      DayTask task = null;
      int tempDay = 0;
      Project[] projects = null;
      Timetable[] timetables = null;
      Schedule[] schedules = null;
      Schedule schedule = null;
      String sOnly = null;
      boolean isFirst = true;
      HashMap map = null;
      java.sql.Date currentDate = null;
      for(int i=0; i<7; i++)
      {
         tempDay = calendar.get(Calendar.DAY_OF_WEEK);

         dSchedule = new DaySchedule();
         dSchedule.setTitle((calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE)+"<br>("+DAYS[i]+")");

         if(day==tempDay)
         {
            dSchedule.setBgcolor("#CCFF99");
            dSchedule.setTitleColor("#000066");
         }
         else if(1==tempDay)
         {
            dSchedule.setBgcolor("#FFEEEE");
            dSchedule.setTitleColor("#CC0000");
         }
         else if(7==tempDay)
         {
            dSchedule.setBgcolor("#FFEEEE");
            dSchedule.setTitleColor("#CC0000");
         }

         try
         {
            currentDate =new java.sql.Date(calendar.getTimeInMillis());
            projects = tasksBean.getAllAvailableProjects(currentDate, currentDate);
            boolean show = false;
            for(int j=0; j<projects.length; j++)
            {
               try
               {
                  sOnly = projects[j].getDataValue("surrogateOnly");
               }
               catch(Exception e)
               {
                  sOnly = "no";
               }

               timetables = tasksBean.getTimetablesUnderProject(projects[j]);
               for(int k=0; k<timetables.length; k++)
               {
                  show = false;
                  if(timetables[k].getId().endsWith(":0"))
                     isFirst = true;
                  else if(timetables[k].getId().endsWith(":1"))
                     isFirst = false;
                  schedules = tasksBean.getSchedulesUnderProject(timetables[k]);
                  map = new HashMap();
                  for(int l=0; l<schedules.length; l++)
                  {
                     map.put(schedules[l].getName(), schedules[l]);
                  }

                  task = new DayTask();
                  String[] type = SMUtility.splitByToken(timetables[k].getProjectType(), ":", true);
                  if(!isFirst||"yes".equals(sOnly))
                     task.setProject(projects[j].getName()+"<br>"+type[1].replace('-','_')+"<br>"+type[2]);
                  else
                     task.setProject(projects[j].getName()+"<br>"+type[1].replace('-','_'));

                  try
                  {
                     if("yes".equals(sOnly))
                     {
                        task.setStrain(projects[j].getDataValue("smStrain"));
                     }
                     else
                     {
                        if(isFirst)
                           task.setStrain(projects[j].getDataValue("dmStrain"));
                        else
                           task.setStrain(projects[j].getDataValue("smStrain"));
                     }
                  } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if("yes".equals(sOnly))
                     {
                        task.setQuantity(projects[j].getDataValue("smQuantity"));
                     }
                     else
                     {
                        if(isFirst)
                           task.setQuantity(projects[j].getDataValue("dmQuantity"));
                        else
                           task.setQuantity(projects[j].getDataValue("smQuantity"));
                     }
                  } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if(null!=(schedule=(Schedule)map.get("mate"))&&
                        currentDate.toString().equals(schedule.getDueDate().toString()))
                     {
                        show = true;
                        task.setHcgMate("<b>V</b>");
                     }
                     else
                        task.setHcgMate("");
                  } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if(null!=(schedule=(Schedule)map.get("plug"))&&
                        currentDate.toString().equals(schedule.getDueDate().toString()))
                     {
                        show = true;
                        task.setPlugDate("<b>V</b>");
                     }
                     else
                        task.setPlugDate("");
                 } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if(null!=(schedule=(Schedule)map.get("pms"))&&
                        currentDate.toString().equals(schedule.getDueDate().toString()))
                     {
                        show = true;
                        task.setPms("<b>V</b>");
                     }
                     else
                        task.setPms("");
                  } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if("yes".equals(sOnly))
                     {
                        task.setExpDate("");
                     }
                     else
                     {
                        if(isFirst&&currentDate.toString().equals(timetables[k].getDueDate().toString()))
                        {
                           show = true;
                           task.setExpDate("<b>V</b>");
                        }
                        else
                           task.setExpDate("");
                     }
                  } catch(Exception e) {e.printStackTrace();}

                  try
                  {
                     if("yes".equals(sOnly))
                     {
                        if(currentDate.toString().equals(timetables[k].getDueDate().toString()))
                        {
                           show = true;
                           task.setXferDate("<b>V</b>");
                        }
                     }
                     else
                     {
                        if(!isFirst&&currentDate.toString().equals(timetables[k].getDueDate().toString()))
                        {
                           task.setXferDate("<b>V</b>");
                           show = true;
                        }
                        else
                           task.setXferDate("");
                     }
                  } catch(Exception e) {e.printStackTrace();}

                  if(show)
                     dSchedule.addASubcontrol(task);
               }
            }
         }
         catch(Exception e) { e.printStackTrace(); }

         foreward(calendar, 1);
         wSchedule.addASubcontrol(dSchedule);
      }
   }

   private void backward(Calendar calendar, int day)
   {
      for(int i=0; i<day; i++)
      {
         if(1!=calendar.get(Calendar.DAY_OF_MONTH))
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
         else
         {
            calendar.roll(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
         }
      }
   }

   private void foreward(Calendar calendar, int day)
   {
      for(int i=0; i<day; i++)
      {
         if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)!=calendar.get(Calendar.DAY_OF_MONTH))
            calendar.roll(Calendar.DAY_OF_MONTH, 1);
         else
         {
            calendar.roll(Calendar.DAY_OF_MONTH, 1);
            calendar.roll(Calendar.MONTH, 1);
         }
      }
   }

   private String date2String(Date date)
   {
      StringBuffer sb = new StringBuffer();
      int month = date.getMonth()+1;
      if(month<10)
         sb.append(0).append(month).append("-");
      else
         sb.append(month).append("-");
      int day = date.getDate();
      if(day<10)
         sb.append(0).append(day).append("-");
      else
         sb.append(day).append("-");
      int year = (date.getYear()+1900)%100;
      if(year<10)
         sb.append(0).append(year);
      else
         sb.append(year);
      return sb.toString();
   }
}