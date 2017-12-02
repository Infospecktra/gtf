/* Generated by Together */
package org.yang.web.view.controls.jsStyle.navigationList;

import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;

/**
 * @testcase org.test.org.yang.web.view.controls.jsStyle.TestListItem 
 */
public class MessageList extends ListItem
{
   private String id = null;
   public void setId(String id) { this.id = id; }
   public String getId() { return id; }

   private String controlName = null;
   public void setControlName(String controlName) { this.controlName = controlName; }

   private String text = null;
   public void setText(String text) { this.text = text; }

   private String displayedDate = null;
   public void setDisplayedDate(String displayedDate) { this.displayedDate = displayedDate; }

   public MessageList()
   {
      super();
   }

   public void render(JspWriter writer) throws Exception
   {
      writer.println( "<tr bgcolor=\"" + color + "\" valign=\"top\">");
      writer.println( "  <td>");
      if(null!=actionLink)
      {
         writer.println( "    <input type=\"checkbox\" name=\"" + controlName + "\" value=\"" + id + "\" id=\"" + id + "\">");
         writer.println( "  </td>");
      }
      writer.println( "  <td>");
      if(null!=actionLinkUp)
      {
         writer.println( "    <a href=\"" + actionLinkUp + "\" >");
         writer.println( "    <img src=\"" + iconUp + "\" border=\"0\" alt=\"Move up\"></a>");
      }
      writer.println( "  </td>");
      writer.println( "  <td>");
      if(null!=actionLinkDown)
      {
         writer.println( "    <a href=\"" + actionLinkDown + "\" >");
         writer.println( "    <img src=\"" + iconDown + "\" border=\"0\" alt=\"Move down\"></a>");
      }
      writer.println( "  </td>");
      writer.println( "  <td class=\"smallest\" width=\"100%\">");
      if(null!=actionLink)
      {
         writer.println( "    <a href=\"" + actionLink + "\" target =\"rightFrame\" >" + text + "</a>");
         writer.println( "    <br>");
         writer.println( "    Date: " + displayedDate);
      }
      writer.println( "  </td>");
      writer.println( "</tr>");
   }
}