package org.yang.web.tagLib.serviceBar;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.controller.SessionPhrase;
import org.yang.services.servicemgr.ServiceDescriptor;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      SCRM
 * @author Jason Wang
 * @version 1.0
 */

public class ServiceBarTag extends TagSupport
{
   String appbase = null;
   String skin = null;

   ServiceButton[] sb = null;

  /**
   * The key of the session-scope bean we look for.
   */
   private String key = "";
   public String getKey() { return (this.key); }
   public void setKey(String key) { this.key = key; }

   private String type = "";
   public String getType() { return (this.type); }
   public void setType(String type) { this.type = type; }

   public ServiceBarTag()
   {
      super();
   }

   public int doStartTag() throws JspException
   {
      try
      {
         HttpSession session = pageContext.getSession();
         Passport pass = (Passport)session.getAttribute("passport");
         appbase = pass.getSystemProperty("appbase");
         skin    = pass.getSystemProperty("skin");

         String   currentService = pass.getCurrentServiceID();
         ServiceDescriptor[] allServices = pass.getAllServiceDescriptors();

         String text = "";
         String icon = null;
         int boundary = 0;
         int status = 0;
         int leftStatus = 0;
         sb = new ServiceButton[allServices.length];
         for(int i=0 ;i<allServices.length; i++)
         {
            sb[i] = new ServiceButton();
            sb[i].setCaption(allServices[i].getEnviromentParameter("ServiceBar.Caption"));
            sb[i].setDescription(allServices[i].getEnviromentParameter("ServiceBar.Description"));
            sb[i].setAppbase(appbase);
            sb[i].setSkin(skin);

            //&System.out.println("[ServiceBarTag::doStartTag] Service Name        : " + allServices[i].getName());
            //&System.out.println("[ServiceBarTag::doStartTag] Service isDisable   : " + allServices[i].getIsDisable());
            //&System.out.println("[ServiceBarTag::doStartTag] Service isCurrent   : " + allServices[i].getIsCurrent());

            if(allServices[i].getIsDisable())
            {
               icon = allServices[i].getEnviromentParameter("ServiceBar.Icon.disable");
               status = 0;
            }
            else
            {
               if(allServices[i].getIsCurrent())
               {
                  icon = allServices[i].getEnviromentParameter("ServiceBar.Icon.on");
                  status = 1;
               }
               else
               {
                  icon = allServices[i].getEnviromentParameter("ServiceBar.Icon.on");
                  status = 0;
               }
            }

            if(i>0&&allServices[i-1].getIsCurrent())
               leftStatus = 1;
            else
               leftStatus = 0;

            if(i<allServices.length-2&&
               allServices[i].getGroupIDNumber()!=allServices[i+1].getGroupIDNumber())
               boundary = 1;
            else
               boundary = 0;


            sb[i].setIcon(appbase + skin + icon);
            sb[i].setIconHeight(allServices[i].getEnviromentParameter("ServiceBar.Icon.Height"));
            sb[i].setIconWidth(allServices[i].getEnviromentParameter("ServiceBar.Icon.Width"));
            sb[i].setLink(appbase + allServices[i].getEnviromentParameter("ServiceBar.Icon.link"));

            //&System.out.println("[ServiceBarTag::doStartTag] Service Name        : " + allServices[i].getName());
            //&System.out.println("[ServiceBarTag::doStartTag] Service Status      : " + status);
            //&System.out.println("[ServiceBarTag::doStartTag] Service Left Status : " + leftStatus);

            sb[i].setMyStatus(boundary, status, leftStatus);

            text = text + sb[i].getString();
         }

	     JspWriter writer = pageContext.getOut();
	     writer.println("<!------------ Begin for Description Layer DHTML---------------------------->");
	     writer.println("<div id=\"overDiv\" style=\"position:absolute; visibility:hide; z-index:999\"></div>");
	     writer.println("<script language=\"JavaScript\" src=\"" + appbase + "/js/overlib.js\"></script>");
	     writer.println("<script language=\"JavaScript\" src=\"" + appbase + "/js/alert.js\"></script>");
	     writer.println("<script language=\"JavaScript\">");
	     writer.println("  function openSWHelpWindow(link)");
	     writer.println("  {");
	     writer.println("    helpwin = window.open(link,\"SWHELPWIN\",\"width=750,height=550,scrollbars=yes,menubar=no,toolbar=no,status=yes,resizable=yes\");" );
	     writer.println("    helpwin.focus();" );
	     writer.println("  }");
	     writer.println("</script>");
	     writer.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" background=\"" + appbase + skin + "/images/btnoff_bg.gif\" ID=\"Table1\">");
         writer.println("  <tr>");
	     writer.println(text);
	     writer.println("    <td noWrap>");
	     writer.println("      <IMG src=\"" + appbase + skin + "/images/1PIX.gif\" width=\"15\">");
	     writer.println("    </td>" );
	     writer.println("    <td align=\"middle\" width=\"100%\">&nbsp;</td>\r\n" );
	     writer.println("    <td>&nbsp;</td>\r\n" );
	     writer.println("    <td>");
	     writer.println("      <A onmouseover=\"MM_swapImage('refreshimg','','" + appbase + skin + "/images/head_refresh_on.gif',1);dispDivT('Refresh','Refresh current page',-175,5)\" ");
         writer.println("         onmouseout=\"MM_swapImgRestore();nd()\" ");
         writer.println("         href=\"javascript:top.document.location.reload()\">");
	     writer.println("        <IMG src=\"" + appbase + skin + "/images/head_refresh_off.gif\" border=\"0\" name=\"refreshimg\">");
	     writer.println("      </A>");
	     writer.println("    </td>");
	     writer.println("    <td>&nbsp;</td>\r\n");
	     writer.println("    <td>");
         writer.println("      <A onmouseover=\"MM_swapImage('helpinmg','','" + appbase + skin + "/images/head_help_on.gif',1);dispDivT('Help','Context-sensitive help',-175,5)\" ");
         writer.println("         onmouseout=\"MM_swapImgRestore();nd()\" ");
         writer.println("         href=\"javascript:top.topFrame.openSWHelpWindow('" + appbase + skin + "/help/English/help.jsp')\">" );
	     writer.println("        <IMG src=\"" + appbase + skin + "/images/head_help_off.gif\" border=\"0\" name=\"helpimg\">");
         writer.println("      </A>");
         writer.println("    </td>\r\n" );
	     writer.println("    <td>&nbsp;</td>\r\n" );
	     writer.println("    <td>");
         writer.println("      <A onmouseover=\"MM_swapImage('logoutimg','','" + appbase + skin + "/images/head_logout_on.gif',1);dispDivT('Log out','Be sure to log out when finished',-175,5)\" ");
         writer.println("         onmouseout=\"MM_swapImgRestore();nd()\" href=\"" + appbase + skin + "/maindesk/logout.jsp\" ");
         writer.println("         target=\"_top\">");
	     writer.println("        <IMG src=\"" + appbase + skin + "/images/head_logout_off.gif\" border=\"0\" name=\"logoutimg\">");
         writer.println("      </A>");
         writer.println("    </td>");
	     writer.println("    <td>&nbsp;</td>\r\n" );
	     writer.println("    <td>");
         writer.println("      <IMG src=\"" + appbase + skin + "/images/btnoff_end.gif\">");
         writer.println("    </td>\r\n" );
	     writer.println("    <td>");
         writer.println("      <IMG src=\"" + appbase + skin + "/images/bar_user.gif\" useMap=\"#creditslink\" border=\"0\">");
         writer.println("    </td>\r\n" );
	     writer.println("    <td class=\"smaller\" noWrap>Welcome, ");
         writer.println("      <A onmouseover=\"dispDivT('User information','Modify user information',-175,5)\" ");
         writer.println("         onmouseout=\"nd()\" ");
         writer.println("         href=\"" + appbase + skin + "/maindesk/m_user.jsp\" ");
         writer.println("         target=\"_top\">\r\n");
	     writer.println("        <b>account</b>");
         writer.println("      </A>");
         writer.println("    </td>\r\n" );

/*
	     writer.println("    <map name=\"creditslink\">\r\n" );
	     writer.println("      <area onclick=\"javascript:window.open('" + appbase + "/credits.htm','SWAT','width=500,height=250,scrollbars=no,menubar=no,toolbar=no,status=no,resizable=no')\" ");
         writer.println("            shape=\"RECT\" ");
         writer.println("            coords=\"5,1,7,3\" ");
         writer.println("            href=\"#\">\r\n" );
	     writer.println("      <area onclick=\"javascript:window.open('" + appbase + skin + "/share/about.jsp?xxx='+(new Date().getTime()),'SWAT','width=450,height=420,scrollbars=yes,menubar=no,toolbar=no,status=no,resizable=no')\" ");
         writer.println("            shape=\"RECT\" ");
         writer.println("            alt=\"About\" ");
         writer.println("            coords=\"0,9,13,28\" ");
         writer.println("            href=\"#\">" );
	     writer.println("    </map>" );
*/
	     writer.println("    <td>&nbsp;</td>" );
	     writer.println("    <td noWrap>");
         writer.println("      <IMG src=\"" + appbase + skin + "/images/alertnote.gif\" align=\"absMiddle\">");
         writer.println("    </td>" );
	     writer.println("    <td>&nbsp;</td>" );
	     writer.println("    <td><b><font color=\"#cc0000\">7</font></b></td>" );
	     writer.println("    <td>&nbsp;&nbsp;</td>");
         writer.println("  </tr>");
         writer.println("</table>");
	  }
      catch (Exception e)
      {
	     throw new JspException (e.toString());
	  }

      return (SKIP_BODY);
   }
}