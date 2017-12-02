/* Generated by Together */

package org.yang.web.view.controls.jsStyle;
import javax.servlet.jsp.JspWriter;
import java.util.HashSet;

public class RadioButtons extends FormElement 
{
   private String id = "";
   public void setId(String id) { this.id = id; }

   private String name = null;
   public void setName(String name) { this.name = name; }

   private boolean isChecked = false;
   public void setIsChecked(boolean isChecked) { this.isChecked = isChecked; }

   private String onChange = null;
   public void setOnChange(String onChange) { this.onChange = onChange; }

   private String value = null;
   public void setValue(String value) { this.value = value; }

   private String displayName = null;
   public void setDisplayName(String displayName) { this.displayName = displayName; }

   public void printBody(JspWriter writer) throws Exception
   {
      writer.print("<tr>");
      writer.print("<td class=\"smallest\" noWrap align=\"left\">");
      if(isChecked)
      {
         writer.print( "<input id=\"" + id + "\" ");
         writer.print( "       onclick=\"" + onChange + "\" ");
         writer.print( "       type=\"checkbox\" name=\"" + name + "\" value=\"" + value + "\" checked  >" + displayName + "</input>");
      }
      else
      {
         writer.print( "<input id=\"" + id + "\" ");
         writer.print( "       onclick=\"" + onChange + "\" ");
         writer.print( "       type=\"checkbox\" name=\"" + name + "\" value=\"" + value + "\" >" + displayName + "</input>");
      }
      writer.print("</td>");
      writer.print("</tr>");
   }
}