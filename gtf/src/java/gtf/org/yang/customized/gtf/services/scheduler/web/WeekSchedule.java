/* Generated by Together */

package org.yang.customized.gtf.services.scheduler.web;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.yang.web.view.controls.WebControl;
import java.util.ArrayList;
import java.util.Iterator;

public class WeekSchedule extends WebControl
{
   private String id = null;
   public void setId(String id) { this.id = id; }

   private String title = "YYYY MM WW";
   public void setTitle(String title) { this.title = title; }

   private String actionLink = null;
   public void setActionLink(String actionLink) { this.actionLink = actionLink; }

   private String iconLink = null;
   public void setIconLink(String iconLink) { this.iconLink = iconLink; }

   private String imageCodeBase = null;
   public void setImageCodeBase(String imageCodeBase) { this.imageCodeBase = imageCodeBase; }

   private boolean listMode = false;
   public void setListMode(boolean listMode) { this.listMode = listMode; }

   public WeekSchedule() {}

   public void render(JspWriter writer) throws Exception
   {

      writer.println("<table align=\"center\" width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\" >");
      writer.println("  <tr><td><br></td></tr>");
      writer.println("  <tr align='center' >");
      writer.println("    <td id=\"centercolumn\">");
      writer.println("      <table align=\"center\" border='0' cellspacing='0' cellpadding='0' width='100%'>");
      writer.println("        <tr>");
      writer.println("          <td class='calframe'>");
      writer.println("          <!-- header -->");
      writer.println("            <table align=\"center\" border='0' cellspacing='0' cellpadding='0' width='100%' style='border-collapse:collapse;'>");
      writer.println("              <tr>");
      writer.println("                <td nowrap='nowrap' colspan='7' align='center' bgcolor='#8A92D7'>");
      writer.println("                  <table align=\"center\" border='0' cellspacing='0' cellpadding='0' width='100%'>");
      writer.println("                    <tr>");
      writer.println("                      <td width='30%' align='center' valign='middle' style='vertical-align:middle;'>");
      writer.println("                      </td>");
      writer.println("                      <td width='40%' align='center' valign='middle' nowrap='nowrap' style='vertical-align:middle;'>");
      writer.println("                        <font size='4' color='#ffffff'><b><span class='calhead'>" + title + "</span></b></font>");
      writer.println("                      </td>");
      writer.println("                      <td width='30%' align='right' valign='middle' style='vertical-align:middle;'>");
      writer.println("                        <font size='1'><a href='"+actionLink+"'>");
      writer.println("                          <img src='" + iconLink + "' border='0' width='30' height='24' alt='Calendar View' title='Calendar View' /></a>");
      writer.println("                        </font>");
      writer.println("                      </td>");
      writer.println("                    </tr>");
      writer.println("                  </table>");
      writer.println("                </td>");
      writer.println("              </tr>");
      writer.println("            </table>");
      writer.println("           <table align=\"center\" border='0' cellspacing='0' cellpadding='0' width='100%' style='border-collapse:collapse;margin:0px;'>");
      writer.println("           <tr>");
      writer.println("             <td><br/></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='40' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='40' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='60' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='80' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='80' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='80' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='80' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='60' height='10' /></td>");
      //writer.println("             <td><img src='"+ imageCodeBase + "/spacer.gif' alt='' width='60' height='10' /></td>");
      writer.println("           </tr>");

      Iterator it = subcontrols();
      DaySchedule control = null;
      while(it.hasNext())
      {
         control = (DaySchedule)it.next();
         control.setImageCodeBase(imageCodeBase);
         control.render(writer);
      }

      writer.println("            </table>");
      writer.println("          </td>");
      writer.println("        </tr>");
      writer.println("      </table>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("</table>");
   }
}
