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

public class ServiceFunctionPanel extends WebControl
{
  /**
   * The key of the session-scope bean we look for.
   */
   public ServiceFunctionPanel()
   {
      super();
   }

   public void render(JspWriter writer) throws Exception
   {
      try
      {
	     writer.println("    <td>" );
	     writer.println("      <table border=\"0\" cellspacing=\"2\" cellpadding=\"1\">" );
	     writer.println("        <tr>");

         this.renderSubcontrols(writer);

	     writer.println("        </tr>");
	     writer.println("      </table>");
	     writer.println("    </td>" );
	  }
      catch (Exception e)
      {
	     throw new JspException (e.toString());
	  }
   }
}