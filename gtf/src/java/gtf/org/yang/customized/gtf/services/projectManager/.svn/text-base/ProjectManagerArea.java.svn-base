/**
 * This class is used to manage client to access a domain and to manipulate some services
 * within each domain.
 * each domain has one UserManager object. Although, we donot define an
 * domain class, the domain concept will appear in database to form an
 * unit of users. Their relationship is: a set of users can belong to another
 * set of groups, which in turn form a domain. A domain will be passed to database
 * as a string
 * @ title  UserManager class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
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

class ProjectManagerArea implements Area
{
   private String name = null;
   public void setName(String name) { this.name = name; }
   public String getName() { return name; }

   private Operation[] operations = null;
   public void setAllOperations(Operation[] operations) { this.operations = operations; }
   public Operation[] getAllOperations() { return operations; }

   public boolean containOperation(String operationname)
   {
      for(int i=0; i<operations.length; i++)
      {  
	     
		 //System.out.println("[ProjctManagerArea::containOperation]----------operations["+i+"].getName()="+operations[i].getName()); 
	     //System.out.println("[ProjctManagerArea::containOperation]----------operationname="+operationname); 
		 if(operations[i].getName().equals(operationname))
             return true;
      }
      return false;
   }
}


