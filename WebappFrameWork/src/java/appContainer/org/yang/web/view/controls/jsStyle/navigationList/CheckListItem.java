/* Generated by Together */
package org.yang.web.view.controls.jsStyle.navigationList;

import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;

/**
 * @testcase org.test.org.yang.web.view.controls.jsStyle.TestListItem 
 */
public class CheckListItem extends ListItem
{
   private String id = null;
   public void setId(String id) { this.id = id; }
   public String getId() { return id; }

   private String controlName = null;
   public void setControlName(String controlName) { this.controlName = controlName; }

   private String text = null;
   public void setText(String text) { this.text = text; }
   /*
   private String displayedDate = null;
   public void setDisplayedDate(String displayedDate) { this.displayedDate = displayedDate; }
   */
   protected String actionLinkUp = null;
   public void setActionLinkUp(String actionLinkUp) { this.actionLinkUp = actionLinkUp; }
   public String getActionLinkUp() { return actionLinkUp; }

   protected String iconUp = null;
   public void setIconUp(String iconUp) { this.iconUp = iconUp; }
   public String getIconUp() { return iconUp; }

   protected String actionLinkDown = null;
   public void setActionLinkDown(String actionLinkDown) { this.actionLinkDown = actionLinkDown; }
   public String getActionLinkDown() { return actionLinkDown; }

   protected String iconDown = null;
   public void setIconDown(String iconDown) { this.iconDown = iconDown; }
   public String getIconDown() { return iconDown; }

   protected String onClick = null;
   public void setOnClick(String onClick) { this.onClick = onClick; }
   public String getOnClick() { return onClick; }

   protected boolean isChecked = false;
   public void setIsChecked(boolean isChecked) { this.isChecked = isChecked; }
   public boolean getIsChecked() { return isChecked; }

   protected String descriptionPrompt = null;
   public void setDescriptionPrompt(String descriptionPrompt) { this.descriptionPrompt = descriptionPrompt; }
   public String getDescriptionPrompt() { return descriptionPrompt; }

   protected String descriptionValue = null;
   public void setDescriptionValue(String descriptionValue) { this.descriptionValue = descriptionValue; }
   public String getDescriptionValue() { return descriptionValue; }

   /*
   protected String actionLink = null;
   public void setActionLink(String actionLink) { this.actionLink = actionLink; }
   public String getActionLink() { return actionLink; }
   */
   protected String color = "#e9e9e9";
   public void setColor(String color) { this.color = color; }
   public String getColor() { return color; }

   public CheckListItem()
   {
      super();
   }

   public void render(JspWriter writer) throws Exception
   {
      writer.println( "<tr bgcolor=\"" + color + "\" valign=\"top\">");
      writer.println( "  <td>");
      //if(null!=actionLink)
      //{
      if(null!=onClick)
      {
         writer.println( "    <input type=\"checkbox\" name=\"" + controlName ); 
         writer.println( "\"  onclick=\"" + onClick + "\"  value=\"" + id + "\"");
         if(isChecked)
           writer.println( "checked" );    
         writer.println(   " id=\"" + id + "\">");
      }
      else
      {
         writer.println( "    <input type=\"checkbox\" name=\"" + controlName + "\"" );
         if(isChecked)
           writer.println( " checked " );    
         writer.println(" value=\"" + id + "\" id=\"" + id + "\">");
      }
      writer.println( "  </td>");
      //}
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
      if(null!=text)
      {
         if(null==actionLink)
     	    writer.println( "    <font color='#3366F9'>" + text + "</font>");
         else  	 
    	    writer.println( "    <a href=\"" + actionLink + "\" target =\"rightFrame\" >" + text + "</a>");
      }
      
      if(null!=descriptionPrompt)
      {
         writer.println( "    <br>");
         writer.println( "    " + descriptionPrompt + ": " + descriptionValue);
      }

      writer.println( "  </td>");
      writer.println( "</tr>");
   }
}