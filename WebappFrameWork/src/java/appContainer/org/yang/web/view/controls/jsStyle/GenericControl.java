/* Generated by Together */

package org.yang.web.view.controls.jsStyle;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.yang.web.view.controls.WebControl;
import java.util.ArrayList;

public abstract class GenericControl extends WebControl
{
   protected ArrayList myButtons = null;
   public void addAButton(Button button) { myButtons.add(button); }

   protected String note = "";
   public void setNote(String note) { this.note = note; }

   protected String caption = "";
   public void setCaption(String caption) { this.caption = caption; }

   protected boolean isButtonRight = true;
   public void setIsButtonRight(boolean isButtonRight) { this.isButtonRight = isButtonRight; }

   protected boolean gotGoTopButton = true;
   public void setGotGoTopButton(boolean gotGoTopButton) { this.gotGoTopButton = gotGoTopButton; }

   public GenericControl()
   {
      myButtons = new ArrayList();
   }

   protected void printformHead(JspWriter writer) throws Exception
   {

   }

   protected void printformTail(JspWriter writer) throws Exception
   {

   }

   abstract protected void printBody(JspWriter writer) throws Exception;

   public void render(JspWriter writer) throws Exception
   {

      printformHead(writer);

      writer.println("     <tr>");
      writer.println("       <td bgcolor    = \"#000066\"");
      writer.println("           background = \"/wf/default/images/bar_bk.gif\"");
      writer.println("           colspan    = \"2\"");
      writer.println("           class      = \"bigger\">");
      writer.println("           <font color=\"#FFFFFF\"><b><a name=\"GenericControl\"></a>" + caption + "</b></font>");
      writer.println("      </td>");
      writer.println("     </tr>");
      writer.println("     <tr>");
      writer.println("       <td class=\"smallest\" align=\"center\">" + note + "</td>");
      writer.println("     </tr>");

      printBody(writer);

      writer.println("     <tr>");
      writer.println("       <td>");
      writer.println("         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" ID=\"Table10\">");
      writer.println("           <tr valign=\"top\">");

      if(myButtons.size()>0)
      {
         if(gotGoTopButton)
         {
            writer.println("             <td class=\"smallest\"><b><a href=\"#top\"><img src=\"/wf/default/images/top.gif\" border=\"0\" alt=\"Go to top of page\"></a></b>");
            writer.println("             </td>");
         }

         if(isButtonRight)
            writer.println("             <td valign=\"top\" align=\"right\">");
         else
            writer.println("             <td valign=\"top\" align=\"left\">");

         printActionButton(writer);

         writer.println("             </td>");
      }
      writer.println("          </tr>");
      writer.println("        </table>");
      writer.println("      </td>");
      writer.println("    </tr>");

      printformTail(writer);
   }

   protected void printActionButton(JspWriter writer) throws Exception
   {
      for(int i=0; i<myButtons.size(); i++)
      {
         ((Button)myButtons.get(i)).render(writer);
      }
   }
}