/* Generated by Together */

package org.yang.web.view.controls.jsStyle;
import javax.servlet.jsp.JspWriter;

public class TextField extends FormElement 
{
   private String name = null;
   public void setName(String name) { this.name = name; }

   private String value = null;
   public void setValue(String value) { this.value = value; }

   private String size = null;
   private String unit = null;

   public void setSize(String size) { this.size = size; }

   public void setUnit(String unit) { this.unit = unit; }

   private String maxLength = null;
   public void setMaxLength(String maxLength) { this.maxLength = maxLength; }

   private boolean displayOnly = false;
   public void setDisplayOnly(boolean displayOnly) { this.displayOnly = displayOnly; }


   protected void printBody(JspWriter writer) throws Exception
   {
      if(displayOnly)
      {
         writer.print("    <b><font color = \"#cc0000\">" + value + "</font></b>");
      }
      else
      {
         if(isReadOnly)
         {
            writer.print("    <b><font color = \"#cc0000\">" + value + "</font></b>");
            writer.print("    <input type  = \"hidden\"");
         }
         else
         {
            writer.print("    <input type  = \"text\"");
            writer.print("           size  = \"" + size + "\"");
         }

         if(null!=maxLength)
         {
            writer.print("           maxLength = \"" + maxLength + "\"");
         }

         writer.print("           name  = \"" + name + "\"");
         if(null!=onChange)
            writer.print("           onchange = \"" + onChange + "\"");
         writer.print("           value = \"" + value + "\">");
      }

      if(null!=unit)
         writer.print(" " + unit);
   }
}