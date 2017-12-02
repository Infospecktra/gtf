/* Generated by Together */

package org.yang.web.view.controls.jsStyle;
import javax.servlet.jsp.JspWriter;
import java.util.ArrayList;

public class CompoundElement extends FormElement 
{
   private ArrayList elements = null;
   public void addSubelement(FormElement element) { elements.add(element); }

   public CompoundElement()
   {
      elements = new ArrayList();
   }

   public void printBody(JspWriter writer) throws Exception
   {
      for(int i=0; i<elements.size(); i++)
      {
         ((FormElement)elements.get(i)).printBody(writer);
      }
   }
}