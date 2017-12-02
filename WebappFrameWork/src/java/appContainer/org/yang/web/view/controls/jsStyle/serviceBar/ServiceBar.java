package org.yang.web.view.controls.jsStyle.serviceBar;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.controller.SessionPhrase;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.web.tagLib.serviceBar.ServiceButton;
import org.yang.web.view.controls.WebControl;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      SCRM
 * @author Jason Wang
 * @version 1.0
 */

public class ServiceBar extends WebControl
{
  /**
   * The key of the session-scope bean we look for.
   */
   private String key = "";
   public String getKey() { return (this.key); }
   public void setKey(String key) { this.key = key; }

   private String type = "";
   public String getType() { return (this.type); }
   public void setType(String type) { this.type = type; }

   private String appBase = null;
   public void setAppBase(String appBase) { this.appBase = appBase; }

   private String skin = null;
   public void setSkin(String skin) { this.skin = skin; }

   private String currentUser = "Uknown User";
   public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }

   private String userInfoLink = null;
   public void setUserInfoLink(String userInfoLink) { this.userInfoLink = userInfoLink; }

   public ServiceBar()
   {
      super();
   }

   public void render(JspWriter writer) throws Exception
   {
      try
      {

         writer.println("<!------------ Begin for Description Layer DHTML---------------------------->");
         writer.println("<div id=\"overDiv\" style=\"position:absolute; visibility:hide; z-index:999\"></div>");
         writer.println("<script language=\"JavaScript\" src=\"" + appBase + "/js/overlib.js\"></script>");
         writer.println("<script language=\"JavaScript\" src=\"" + appBase + "/js/alert.js\"></script>");
         //writer.println("<script language=\"JavaScript\">");
         //writer.println("  function openSWHelpWindow(link)");
         //writer.println("  {");
         //writer.println("    helpwin = window.open(link,\"SWHELPWIN\",\"width=750,height=550,scrollbars=yes,menubar=no,toolbar=no,status=yes,resizable=yes\");" );
         //writer.println("    helpwin.focus();" );
         //writer.println("  }");
         //writer.println("</script>");
         writer.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" background=\"" + appBase + skin + "/images/btnoff_bg.gif\" ID=\"Table1\">");
         writer.println("  <tr>");


         this.renderSubcontrols(writer);


         writer.print("    <td noWrap>");
         writer.print("<IMG src=\"" + appBase + skin + "/images/1PIX.gif\" width=\"15\">");
         writer.print("</td>" );
         writer.print("<td align=\"middle\" width=\"100%\">&nbsp;</td>" );
         writer.println("<td>&nbsp;</td>" );
         writer.print("    <td>");
         writer.print("<A onmouseover=\"MM_swapImage('refreshimg','','" + appBase + skin + "/images/head_refresh_on.gif',1);dispDivT('Refresh','Refresh current page',-175,5)\" ");
         writer.print(" onmouseout=\"MM_swapImgRestore();nd()\" ");
         writer.print(" href=\"javascript:top.document.location.reload()\">");
         writer.print("<IMG src=\"" + appBase + skin + "/images/head_refresh_off.gif\" border=\"0\" name=\"refreshimg\">");
         writer.print("</A>");
         writer.print("</td>");
         writer.println("<td>&nbsp;</td>");
         writer.print("    <td>");
         writer.print("<A onmouseover=\"MM_swapImage('helpinmg','','" + appBase + skin + "/images/head_help_on.gif',1);dispDivT('Help','Context-sensitive help',-175,5)\" ");
         writer.print(" onmouseout=\"MM_swapImgRestore();nd()\" ");
         writer.print(" href=\"javascript:top.topFrame.openSWHelpWindow('" + appBase + skin + "/help/English/help.jsp')\">" );
         writer.print("<IMG src=\"" + appBase + skin + "/images/head_help_off.gif\" border=\"0\" name=\"helpimg\">");
         writer.print("</A>");
         writer.print("</td>" );
         writer.println("<td>&nbsp;</td>" );
         writer.print("    <td>");
         writer.print("<A onmouseover=\"MM_swapImage('logoutimg','','" + appBase + skin + "/images/head_logout_on.gif',1);dispDivT('Log out','Be sure to log out when finished',-175,5)\" ");
         writer.print(" onmouseout=\"MM_swapImgRestore();nd()\" href=\"" + appBase + skin + "/maindesk/logout.jsp\" ");
         writer.print(" target=\"_top\">");
         writer.print("<IMG src=\"" + appBase + skin + "/images/head_logout_off.gif\" border=\"0\" name=\"logoutimg\">");
         writer.print("</A>");
         writer.print("</td>");
         writer.println("<td>&nbsp;</td>" );
         writer.print("    <td>");
         writer.print("<IMG src=\"" + appBase + skin + "/images/btnoff_end.gif\">");
         writer.println("</td>" );
         writer.print("    <td>");
         writer.print("<IMG src=\"" + appBase + skin + "/images/bar_user.gif\" useMap=\"#creditslink\" border=\"0\">");
         writer.print("</td>" );
         writer.print("<td class=\"smaller\" noWrap>Welcome, ");
         writer.print("<A onmouseover=\"dispDivT('User information','Modify user information',-175,5)\" ");
         writer.print(" onmouseout=\"nd()\" ");
         writer.print(" href=\"" + userInfoLink + "\" ");
         writer.print(" target=\"mainFrame\">");         
         ////writer.print("href='javascript:openWin( \""+userInfoLink+"\",400,400)' ");
         ////writer.print(currentUser);
         writer.print("<b>" + currentUser + "</b>");
         writer.print("</A>");
         writer.print("</td>" );
         writer.println("<td>&nbsp;</td>" );
         //writer.print("    <td noWrap>");
         //writer.print("<IMG src=\"" + appBase + skin + "/images/alertnote.gif\" align=\"absMiddle\">");
         //writer.print("</td>" );
         writer.print("<td>&nbsp;</td>" );
         //writer.print("<td><b><font color=\"#cc0000\">7</font></b></td>" );
         writer.print("<td>&nbsp;&nbsp;</td>");
         writer.println("</tr>");
         writer.println("</table>");
      }
      catch (Exception e)
      {
         throw new JspException (e.toString());
      }
   }
}