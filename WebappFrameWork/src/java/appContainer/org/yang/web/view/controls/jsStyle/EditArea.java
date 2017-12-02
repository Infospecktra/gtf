/* Generated by Together */

package org.yang.web.view.controls.jsStyle;

import javax.servlet.jsp.JspWriter;

public class EditArea extends GenericControl 
{
   private String name = null;
   public void setName(String name) { this.name = name; }

   private String value = null;
   public void setValue(String value) { this.value = value; }

   private String rows = "3";
   public void setRows(String rows) { this.rows = rows; }

   private String cols = "30";
   public void setCols(String cols) { this.cols = cols; }

   protected String onChange = null;
   public void setOnChange(String onChange) { this.onChange = onChange; }

   protected boolean isReadOnly = false;
   public void setIsReadOnly(boolean isReadOnly) { this.isReadOnly = isReadOnly; }

   protected void printBody(JspWriter writer) throws Exception
   {
      writer.println("<tr>");
      writer.println(" <td>");
      writer.println("  <table id=\"Table5\" borderColor=\"#cccccc\" cellSpacing=\"0\" borderColorDark=\"#ffffff\" cellPadding=\"3\" width=\"100%\" align=\"center\" bgColor=\"#eaeaea\" borderColorLight=\"#cccccc\" border=\"1\">");
	  writer.println("   <tr>");
	  writer.println("    <td class=\"bigger\" noWrap>");
      writer.print("    <textarea id   = \"" + id + "\"");
      writer.print("              name = \"" + name + "\"");
      writer.print("              rows = \"" + rows + "\"");
      writer.print("              wrap = \"SOFT\"");
      writer.print("              cols = \"" + cols + "\"");
      writer.print("              onChange = \"" + onChange + "\"");
      if(isReadOnly)
         writer.print("           READONLY");
      writer.print("    >" + value + "</textarea>");
	  writer.println("    </td>");
	  writer.println("   </tr>");
	  writer.println("  </table>");
	  writer.println(" </td>");
	  writer.println(" </tr>");
   }
}