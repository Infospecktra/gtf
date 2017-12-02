/* Generated by Together */

package org.yang.web.view.controls.jsStyle.panel;
import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;

public class TabPanelElement extends WebControl
{
   private String ICON_LEFT_ON  = "/images/tagon_left.gif";
   private String ICON_BG_ON    = "/images/tagon_bg.gif";
   private String ICON_RIGHT_ON = "/images/tagon_right.gif";

   private String ICON_LEFT_OFF  = "/images/tagoff_left.gif";
   private String ICON_BG_OFF    = "/images/tagoff_bg.gif";
   private String ICON_RIGHT_OFF = "/images/tagoff_right.gif";

   private String tabIconLeft  = ICON_LEFT_OFF;
   private String tabIconBg    = ICON_BG_OFF;
   private String tabIconRight = ICON_RIGHT_OFF;

   protected String tabSwitchingLink = null;
   public void setTabSwitchingLink(String tabSwitchingLink) { this.tabSwitchingLink = tabSwitchingLink; }
   public String getTabSwitchingLink() { return tabSwitchingLink; }

   protected String text = null;
   public void setText(String text) { this.text = text; }
   public String getText() { return text; }

   protected String target = null;
   public void setTarget(String target) { this.target = target; }

   protected boolean onFocus = false;
   public void setOnFocus(boolean onFocus) { this.onFocus = onFocus; }
   public boolean getOnFocus() { return onFocus; }

   protected String color = "#FFFFFF";
   public void setColor(String color) { this.color = color; }
   public String getColor() { return color; }

   protected String codeBase = null;
   public void setCodeBase(String codeBase) { this.codeBase = codeBase; }

   protected boolean isDisable = false;
   public void setIsDisable(boolean isDisable){this.isDisable=isDisable;}
   public boolean  getIsDisable(){return isDisable;} 
   
   public void renderTap(JspWriter writer) throws Exception
   {
      
      if(onFocus&&!isDisable)
      {
         tabIconLeft  = ICON_LEFT_ON;
         tabIconBg    = ICON_BG_ON;
         tabIconRight = ICON_RIGHT_ON;
      }
      else
      {
         tabIconLeft  = ICON_LEFT_OFF;
         tabIconBg    = ICON_BG_OFF;
         tabIconRight = ICON_RIGHT_OFF;
      }

      writer.print( "      	         <td>");
      writer.print( "<img src=\"" + codeBase + tabIconLeft + "\" width=\"12\" height=\"22\">");
      writer.println( "</td>");
      writer.print( "                <td background=\"" + codeBase + tabIconBg + "\" class=\"smallest\" nowrap>");
      writer.print( "<b>");

      if(!onFocus&&!isDisable)
      {
         writer.print( "<a href=\"" + tabSwitchingLink + "\"" );
         if(null!=target)
            writer.print(   " target=\"" + target + "\"");
         writer.print( " >");
         writer.print( "<font color=\"#FFFFFF\">" + text + "</font>");
         writer.print( "</a>");
      }
      else
      {
         writer.print( "<font color=\"#FFFFFF\">" + text + "</font>");
      }

      writer.print( "</b>");
      writer.println( "</td>");
      writer.print( "                <td>");
      writer.print( "<img src=\"" + codeBase + tabIconRight + "\" width=\"12\" height=\"22\">");
      writer.print( "</td>");
   }

   public void render(JspWriter writer) throws Exception
   {
      if(0<mySubcontrols.size())
      {
         writer.println( "      <table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\" bgcolor=\"" + color + "\">");
         this.renderSubcontrols(writer);
         writer.println( "      </table>");
      }
   }
}
