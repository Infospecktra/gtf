/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.FormElement;
import org.yang.web.view.controls.jsStyle.SelectElement;
import org.yang.web.view.controls.jsStyle.DateField;
import org.yang.util.SMUtility;

public class DateTimeDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      return (String)indata;
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);

      String[] date = SMUtility.splitByToken(value, "/", true);
      DateField display = new DateField();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      display.setYear(Integer.parseInt(date[0])+1900);
      display.setMonth(Integer.parseInt(date[1]));
      display.setDate(Integer.parseInt(date[2]));

      return display;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      return null;
   }
}