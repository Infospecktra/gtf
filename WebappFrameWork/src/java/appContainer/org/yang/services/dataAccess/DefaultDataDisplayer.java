/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.FormElement;

public class DefaultDataDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      return (String)indata;
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);
      TextField display = new TextField();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      display.setValue(value);
      display.setUnit(data.getUnit());
      display.setSize("60");
      display.setDisplayOnly(displayOnly);
      return display;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);

      TextField display = new TextField();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      display.setValue(value);
      display.setUnit(data.getUnit());
      display.setSize("25");
      display.setDisplayOnly(displayOnly);
      return display;
   }
}