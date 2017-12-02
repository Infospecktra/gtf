/* by Steven Yang */

package org.yang.services.dataAccess;

import java.util.HashMap;
import java.io.Serializable;
import org.yang.web.view.controls.jsStyle.FormElement;

public interface DataDisplayer extends Serializable
{
   String rebuildData(Data data, Object indata);

   FormElement getDisplay(Data data, String owner, boolean displayOnly);

   FormElement getDisplayPDA(Data data, String owner, boolean displayOnly);
}