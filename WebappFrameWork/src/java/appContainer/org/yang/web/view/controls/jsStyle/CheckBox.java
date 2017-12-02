/* Generated by Together */

package org.yang.web.view.controls.jsStyle;
import javax.servlet.jsp.JspWriter;
import java.util.HashSet;

public class CheckBox extends FormElement 
{
   private String id = "";
   public void setId(String id) { this.id = id; }

   private String name = null;
   public void setName(String name) { this.name = name; }

   private boolean isChecked = false;
   public void setIsChecked(boolean isChecked) { this.isChecked = isChecked; }
   public boolean getIsChecked() { return isChecked; }

   private String onClick = null;
   public void setOnClick(String onClick) { this.onClick = onClick; }

   private String value = null;
   public void setValue(String value) { this.value = value; }
   public String getValue() { return value; }

   private String displayName = null;
   public void setDisplayName(String displayName) { this.displayName = displayName; }

   public void printBody(JspWriter writer) throws Exception
   {
      writer.print("<tr>");
      writer.print("<td class=\"smallest\" noWrap align=\"left\">");
      if(isChecked)
      {
         writer.print( "<input id=\"" + id + "\" ");
         writer.print( "       onclick=\"" + onClick + "\" ");
         writer.print( "       type=\"checkbox\" name=\"" + name + "\" value=\"" + value + "\" checked  >" + displayName + "</input>");
      }
      else
      {
         writer.print( "<input id=\"" + id + "\" ");
         writer.print( "       onclick=\"" + onClick + "\" ");
         writer.print( "       type=\"checkbox\" name=\"" + name + "\" value=\"" + value + "\" >" + displayName + "</input>");
      }
      writer.print("</td>");
      writer.print("</tr>");
   }
}