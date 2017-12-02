/* Generated by Together */

package org.yang.customized.gtf.services.tglScheduler.web;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.yang.web.view.controls.WebControl;
import java.util.ArrayList;

public class FutureTask extends WebControl
{
   private String type = null;
   public void setType(String type) { this.type = type; }

   private String expDate = null;
   public void setExpDate(String expDate) { this.expDate = expDate; }

   private String xferDate = null;
   public void setXferDate(String xferDate) { this.xferDate = xferDate; }

   private String strain = null;
   public void setStrain(String strain) { this.strain = strain; }

   private String quantity = null;
   public void setQuantity(String quantity) { this.quantity = quantity; }

   private String order = null;
   public void setOrder(String order) { this.order = order; }

   private String pms = null;
   public void setPms(String pms) { this.pms = pms; }

   private String hcgMate = null;
   public void setHcgMate(String hcgMate) { this.hcgMate = hcgMate; }

   private String plugDate = null;
   public void setPlugDate(String plugDate) { this.plugDate = plugDate; }

   public FutureTask() {}

   public void render(JspWriter writer) throws Exception
   {
      writer.println("                  <tr bgcolor='#ffffff'>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+type+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+expDate.replace('-', '/')+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+xferDate.replace('-', '/')+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+strain+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+quantity+"</font></td>");
      //writer.println("                    <td nowrap class='smaller' width='80' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+order+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+pms.replace('-', '/')+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+hcgMate.replace('-', '/')+"</font></td>");
      writer.println("                    <td nowrap class='smaller' width='40' height='25' bgcolor='#FFFFFF'><font color='#000000'>"+plugDate.replace('-', '/')+"</font></td>");
      writer.println("                  </tr>");
   }
}
