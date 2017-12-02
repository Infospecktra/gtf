/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.Photo;
import org.yang.web.view.controls.jsStyle.DataSheetElement;
import org.yang.web.view.controls.jsStyle.FormElement;

public class TableDataDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
//&System.out.println("??????????????????????:" + indata);
      String[] datas = null;
      if(indata instanceof String)
         datas = new String[]{(String)indata};
      else
         datas = (String[])indata;
      int len = 0;
      try
      {
         len = data.getSubdatas().length;
      }
      catch(Exception e) {}

      String[] subdata = new String[len];
      int j= 0;
      int counter = 0;
      int totalLen = datas.length;
      StringBuffer sb = new StringBuffer();
      StringBuffer temp = null;
      for(int i=0; i<totalLen; i++)
      {
         j = i%len;
         subdata[j] = datas[i];
         if((len-1)==(i%len))
         {
            temp = new StringBuffer();
            for(int k=0; k<len; k++)
            {
               temp.append(subdata[k]);
               if(k!=len-1)
                  temp.append(",");
               // if any one of datas is not empty, counter will not be zero.
               if(!"".equals(subdata[k]))
               {
                  counter ++;
               }
            }

            // if counter is not zero, it's a valid data.
            if(0!=counter)
            {
               sb.append(temp);
               if(i!=totalLen-1)
                  sb.append("!");
            }
            counter = 0;
         }
         //   sb.append(datas[i]).append("!");
         //else
         //   sb.append(datas[i]).append(",");
      }
      return sb.toString();
   }

   public FormElement getDisplay(Data data, String owner, boolean displayOnly)
   {  
      DataSheetElement dataSheet = createDataSheet(data, owner, displayOnly);
 	  dataSheet.setCellSize(10);
      return dataSheet;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      DataSheetElement dataSheet = createDataSheet(data, owner, displayOnly);
      dataSheet.setCellSize(4);
      return dataSheet;
   }

   private DataSheetElement createDataSheet(Data data, String owner, boolean displayOnly)
   {
      DataSheetElement dataSheet = new DataSheetElement();

      dataSheet.setId("datas_" + data.getName());
      dataSheet.setName(data.getName());
      dataSheet.setTitle(data.getDisplayName());
      dataSheet.setColumns(data.getSubdatas());
      dataSheet.setIsReadOnly(displayOnly);
      dataSheet.setValue(data.getValue(owner));
       
      return dataSheet;
   }
 }