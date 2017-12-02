/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.FormElement;
import org.yang.web.view.controls.jsStyle.TextAreaElement;

public class TextAreaDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      return (String)indata;
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);

      TextAreaElement display = new TextAreaElement();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      display.setRows("10");
      display.setCols("60");
      display.setValue(value);
      display.setIsReadOnly(displayOnly);

      return display;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      return getDisplay(data, owner, displayOnly);
   }
}