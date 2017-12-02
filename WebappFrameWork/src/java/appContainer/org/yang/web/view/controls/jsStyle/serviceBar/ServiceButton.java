/* Generated by Together */

package org.yang.web.view.controls.jsStyle.serviceBar;
import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;

public class ServiceButton extends WebControl
{
   public static int BT_ON = 1;
   public static int BT_OF = 0;
   public static int BT_DI = -1;
   public static int BN_ON = 1;
   public static int BN_OF = 0;

   private int myStatus = BT_ON;
   private int leftStatus = BT_OF;
   private int boundary = BN_OF;

   private String link = null;
   public void setLink(String link) { this.link = link;  }

   private String onIcon = null;
   public void setOnIcon(String onIcon){ this.onIcon = onIcon;}

   private String offIcon = null;
   public void setOffIcon(String offIcon){ this.offIcon = offIcon;}

   private String disableIcon = null;
   public void setDisableIcon(String disableIcon){ this.disableIcon = disableIcon;}

   private String iconHeight = "20";
   public void setIconHeight(String iconHeight) { this.iconHeight = iconHeight; }

   private String iconWidth = "20";
   public void setIconWidth(String iconWidth) { this.iconWidth = iconWidth; }

   private String capt = null;
   public void setCaption(String capt){ this.capt = capt;}

   private String desc = null;
   public void setDescription(String desc){ this.desc = desc;}

   private String appbase = null;
   public void setAppbase(String appbase){ this.appbase = appbase;}

   private String skin = null;
   public void setSkin(String skin){ this.skin = skin;}

   public ServiceButton() {}

   public void setMyStatus(int boundary, int myStatus, int leftStatus)
   {
      this.myStatus = myStatus;
      this.leftStatus = leftStatus;
      this.boundary = boundary;
   }

   public void render(JspWriter writer) throws Exception
   {
      if(BT_ON==myStatus)
      {
         // icon
	     writer.print("    <td nowrap background=\"" + appbase + skin + "/images/btnon_bg.gif\">");
         writer.print("<img src=\"" + onIcon + "\" border=\"0\">" );
	     writer.print("</td>");

         // text
	     writer.print("<td noWrap background=\"" + appbase + skin + "/images/btnon_bg.gif\">" );
	     writer.print("<b>" );
	     writer.print("<font color=\"#330000\">&nbsp;" + capt + "</font>" );
	     writer.print("</b>" );
	     writer.print("</td>");
      }
      else
      {
         // icon
	     writer.print("    <td noWrap>");
         if(BT_DI!=myStatus)
         {
            writer.print("<a href=\"" + link + "\" ");
            writer.print(" onmouseover=\"dispDivT('" + capt.replace('\'','`') + "','" + desc + "',15,5)\" ");
            writer.print(" onmouseout=\"nd()\" ");
            writer.print(" target=\"_top\">" );
         }

         if(BT_DI==myStatus)
	        writer.print("<img src=\"" + disableIcon + "\" border=\"0\">" );
         else
	        writer.print("<img src=\"" + offIcon + "\" border=\"0\">" );

         if(BT_DI!=myStatus)
	        writer.print("</a>");
	     writer.println("</td>");
      }

      // [isAttheBoundaryofGroup][myStatus][leftButtonStatus]
	  writer.print("    <td noWrap>");
      if(BN_OF==boundary)
      {
         if(BT_ON==myStatus)
         {
            // I'm on & not boundary
	        writer.print("<img src=\"" + appbase + skin + "/images/btnon_end.gif\">");
         }
         else
         {
            if(BT_ON==leftStatus)
            {
	           writer.print("<img src=\"" + appbase + skin + "/images/btnon_head.gif\">");
            }
            else
            {
	           writer.print("<img src=\"" + appbase + skin + "/images/1PIX.gif\" width=\"15\">");
            }
         }
      }
      else
      {
         if(BT_ON==myStatus)
         {
	        writer.print("<img src=\"" + appbase + skin + "/images/btnon_end_ball.gif\">");
         }
         else
         {
            if(BT_ON==leftStatus)
            {
	           writer.print("<img src=\"" + appbase + skin + "/images/btnon_head_ball.gif\">");
            }
            else
            {
	           writer.print("<img src=\"" + appbase + skin + "/images/btnoff_end_ball.gif\">");
            }
         }
      }
	  writer.println("</td>");
   }
}