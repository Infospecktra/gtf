
package org.yang.customized.gtf.services.projectManager;
import org.yang.services.servicemgr.Service;
import java.io.Serializable;
import org.yang.customized.gtf.services.dataAccess.DAOFactory;
import java.util.Properties;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import org.yang.services.servicemgr.Area;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.services.servicemgr.Operation;
import org.yang.customized.gtf.services.dataAccess.StageDAO;
import java.util.Date;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;

class ProjectManagerOperation implements Operation
{
   private String name = "";
   public String getName() { return name; }

   ProjectManagerOperation(String name)
   {
      this.name = name;
   }
}


