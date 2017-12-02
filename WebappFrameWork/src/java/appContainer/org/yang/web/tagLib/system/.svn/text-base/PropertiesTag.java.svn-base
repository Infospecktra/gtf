package org.yang.web.tagLib.system;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.yang.web.applicationContainer.Passport;
import org.yang.web.controller.SessionPhrase;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      SCRM
 * @author Jason Wang
 * @version 1.0
 */

public class PropertiesTag extends TagSupport
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

   public PropertiesTag()
   {
      super();
   }

   public int doStartTag() throws JspException
   {
	  try
      {
         HttpSession session = pageContext.getSession();
         Passport pass = (Passport)session.getAttribute("passport");
         if(null==pass)
            throw new JspException("No valid passport for this session.");
	     String message = null;
         if("user".equals(type))
         {
            message = pass.getUserProperty(key);
            if (null==message)
               message = "MISSING_USER_PROP";
         }
         if("system".equals(type))
         {
            message = pass.getSystemProperty(key);
            if(null==message)
               message = "MISSING_SYSTEM_PROP";
         }
         else
         {
            message = pass.getDefaultProperty(key);
            if (null==message)
               message = "MISSING_DEFAULT_PROP";
         }

         type = "";
	     //*** Print the retrieved message to our output writer
	     JspWriter writer = pageContext.getOut();
         ////&System.out.println("property: key = " + key + ", value = " + message);
	     writer.print(message);
	  }
      catch (Exception e)
      {
	     throw new JspException (e.toString());
	  }

      return (SKIP_BODY);
   }
}