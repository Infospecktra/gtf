/* Generated by Together */
package org.yang.web.view.controls.jsStyle.misc;

import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;

/**
 * @testcase org.test.org.yang.web.view.controls.jsStyle.TestNavigationList 
 */
public class MessageDisplayingBanner1 extends WebControl
{
   private String id = null;
   public void setId(String id) { this.id = id; }
   public String getId() { return id; }

   protected String text = null;
   public void setText(String text) { this.text = text; }
   public String getText() { return text; }

   protected String color = "#FFFFFF";
   public void setColor(String color) { this.color = color; }
   public String getColor() { return color; }

   protected int width = 90;
   public void setWidth(int width) { this.width = width; }
   public int getWidth() { return width; }

   protected String codeBase = null;
   public void setCodeBase(String codeBase) { this.codeBase = codeBase; }

   public MessageDisplayingBanner1()
   {
      super();
   }

   public void render(JspWriter writer) throws Exception
   {
      writer.println( "        <tr><td>");
      writer.println( "          <table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"10\"  color=\"" + color + "\">");
      writer.println( "            <tr>");
      writer.println( "              <td align=\"center\" class=\"bigger\">");
      writer.println( "                <font color=\"" + color + "\"><b>" + text + "</b></font>");
      writer.println( "              </td>");
      writer.println( "            </tr>");
      writer.println( "          </table>");
      writer.println( "        </td></tr>");
   }
}