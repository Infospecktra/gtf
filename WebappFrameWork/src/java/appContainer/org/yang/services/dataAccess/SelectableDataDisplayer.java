/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.FormElement;
import org.yang.web.view.controls.jsStyle.SelectElement;

public class SelectableDataDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      return (String)indata;
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);
      String[] possibleValue = data.getPossibleValue();

      SelectElement display = new SelectElement();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      if(null==value||"".equals(value))
         display.setSelectedValue(possibleValue[0]);
      else
         display.setSelectedValue(value);
      display.setValues(possibleValue);
      display.setDisplayNames(possibleValue);

      return display;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      return null;
   }
}