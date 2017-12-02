/* by Steven Yang */

package org.yang.services.dataAccess;

import org.yang.web.view.controls.jsStyle.Photo;
import org.yang.web.view.controls.jsStyle.FormElement;
import org.yang.web.view.controls.jsStyle.TextField;

public class PhotoDataDisplayer implements DataDisplayer
{
   public String rebuildData(Data data, Object indata)
   {
      return (String)indata;
   }

   public FormElement getDisplay(Data data, String owner, boolean display)
   {
      String name = data.getName();
      String value = data.getValue(owner);

      Photo photo = new Photo();
      photo.setTitle(data.getDisplayName());
      if(null==value||"".equals(value))
      {
         value = data.getId();
         photo.setHasPhoto(false);
      }
      photo.setValue(value);
      photo.setUploadAction("/wf/photoUpload.wf?actiontype=upload&photoId=" + value);
      photo.setConfirmAction("/wf/stageNotes.wf?actiontype=update&datas_"+ name + "=" + value);
      photo.setBrowseAction("/wf/photoUpload.wf?actiontype=browse&photoId=" + value );
      photo.setDeleteAction("/wf/stageNotes.wf?actiontype=update&datas_"+ name + "=");
      photo.setName("photo");
      return photo;
   }

   public FormElement getDisplayPDA(Data data, String owner, boolean displayOnly)
   {
      //return getDisplay(data, owner, displayOnly);
      try
      {
      String name = data.getName();
      TextField display = new TextField();
      display.setTitle(data.getDisplayName());
      display.setId("datas_" + name);
      display.setName("datas_" + name);
      display.setValue("not available for PDA");
      display.setSize("25");
      display.setDisplayOnly(true);
      return display;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return getDisplay(data, owner, displayOnly);
   }
}