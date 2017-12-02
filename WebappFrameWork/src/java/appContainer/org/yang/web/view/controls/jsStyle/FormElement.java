/* Generated by Together */

package org.yang.web.view.controls.jsStyle;

import javax.servlet.jsp.JspWriter;
import org.yang.web.view.controls.WebControl;

public abstract class FormElement extends WebControl
{
    protected String title = null;
    public void setTitle(String title) { this.title = title; }

    protected boolean isVertical = false;
    public void setIsVertical(boolean isVertical) { this.isVertical = isVertical; }

    protected boolean isReadOnly = false;
    public void setIsReadOnly(boolean isReadOnly) { this.isReadOnly = isReadOnly; }

    protected String actionLink = null;
    public void setActionLink(String actionLink) { this.actionLink = actionLink; }
    public String getActionLink() { return actionLink; }

    protected String actionTarget = null;
    public void setActionTarget(String actionTarget) { this.actionTarget = actionTarget; }
    public String getActionTarget() { return actionTarget; }

    protected String onChange = null;
    public void setOnChange(String onChange) { this.onChange = onChange; }

    private boolean isMandatory = false;
    public void setIsMandatory(boolean isMandatory) { this.isMandatory = isMandatory; }

    public void render(JspWriter writer) throws Exception
    {
       writer.println("<tr>");
       if(isVertical)
          writer.println("<tr>");
       writer.println("  <td class=\"bigger\" noWrap>");
       writer.println(title);
       writer.println("  </td>");
       if(isVertical)
       {
          writer.println("</tr>");
          writer.println("<tr>");
       }

       if(null!=actionLink&&!isReadOnly&&null!=onChange)
       {
          writer.print("<form  ");
          if(null!=actionTarget)
             writer.print("target='" + actionTarget + "' ");
          writer.print("name=\"" + this.id + "\" action=\"" + this.actionLink + "\" method=\"post\" id=\"" + this.id + "\">");
          writer.print("<input type=\"hidden\" name=\"actiontype\" value=\"\" id=\"actiontype.hidden\">");
       }

       writer.println("  <td class=\"bigger\" noWrap>");
       printBody(writer);
       if(isMandatory)
          writer.println("    * ");
       writer.println("  </td>");

       if(null!=actionLink&&!isReadOnly&&null!=onChange)
          writer.print("</form>");

       if(isVertical)
          writer.println("</tr>");
       writer.println("</tr>");
    }

    abstract protected void printBody(JspWriter writer) throws Exception;
}