/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.FormElement;
import org.yang.web.view.controls.jsStyle.RadioButtons;
import org.yang.web.view.controls.jsStyle.RadioButtonGroup;
import org.yang.web.view.controls.jsStyle.CompoundElement;
import java.util.ArrayList;

public class RadioDataDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      if(indata instanceof String)
      {
         return (String)indata;
      }

      String[] datas = (String[])indata;
      return datas[0]+","+datas[1];
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {
      String name = data.getName();
      String value = data.getValue(owner);
      String additionalValue = "";
      int index = -1;
      if(null!=value&&-1!=(index=value.lastIndexOf(",")))
      {
         additionalValue = value.substring(index+1);
         value = value.substring(0,index);
      }
      String[] possibleValue = data.getPossibleValue();
      boolean withExtraInfo = false;
      ArrayList possibleValueDisplay = new ArrayList();
      for(int i=0; i<possibleValue.length; i++)
      {
         if(possibleValue[i].endsWith(":i"))
         {
            withExtraInfo = true;
            possibleValueDisplay.add(possibleValue[i].substring(0,possibleValue[i].length()-2));
         }
         else
         {
            possibleValueDisplay.add(possibleValue[i]);
         }
      }
      CompoundElement display = new CompoundElement();
      display.setTitle(data.getDisplayName());

      RadioButtonGroup radio = new RadioButtonGroup();
      radio.setId("datas_" + name);
      radio.setName("datas_" + name);
      if(null==value||"".equals(value))
         radio.setSelectedValue(possibleValue[0]);
      else
         radio.setSelectedValue(value);
      radio.setValues(possibleValue);
      radio.setDisplayNames((String[])possibleValueDisplay.toArray(new String[]{}));
      radio.setIsVertical(true);
      display.addSubelement(radio);

      if(withExtraInfo)
      {
         TextField additional = new TextField();
         additional.setId("datas_" + name);
         additional.setName("datas_" + name);
         additional.setValue(additionalValue);
         display.addSubelement(additional);
      }

      return display;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      return null;
   }
}