package org.yang.services.accountmgr;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.User;
import org.yang.services.accountmgr.Permission;
import org.yang.services.accountmgr.UserDAO;
import org.yang.services.accountmgr.GroupDAO;
import org.yang.services.accountmgr.PermissionDAO;
import org.yang.util.ExceptionBroadcast;




/**
 * @testcase org.test.org.yang.services.accountmgr.TestBuildSuperData
 
 *  
 * @ version 1.0   
 * @ Date:   06/19/2001 
 * @ programmer: Huei-Wen(Celina) Yang 
 * @  
 */
public class BuildSuperData
{

  /**
   *   test stub
   */
   public static void main(String[] args)
   {

          try{

	        	 
              //=-=-=-=-=-=-=-*< step1 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
   	      //1. new user and group objects

   	  	Group g1 = new Group();
   	  	g1.setID("SUPERS");
   	  	g1.setName("Supers");
   		g1.setDescription("description1");
   		
   		User u1 = new User();
   	  	u1.setID("SUPER");
   	  	u1.setFirstName("Super");
   		u1.setLastName("Super");
   		u1.setPassword("111",true);
   		u1.setDescription("describe 1");

                Permission p1 = new Permission();
                p1.setId("SUPER1");
                p1.setGroupId("SUPERS");
                p1.setServiceId("EngineersDesk");
                p1.setAreasConcat("area1");
                p1.setOperationsConcat("operation1");
                
                Permission p2 = new Permission();
                p2.setId("SUPER2");
                p2.setGroupId("SUPERS");
                p2.setServiceId("AccountManager");
                p2.setAreasConcat("area2");
                p2.setOperationsConcat("operation2");                
              //=-=-=-=-=-=-=-*< step2 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
   	      // 1.new UserDAO , GroupDAO and PermissionDAO objects

        //DAOFactory daof = DAOFactory.getFactory();
   	  	UserDAO       udao = null;//new UserDAO("testdomain");
   	  	GroupDAO      gdao = null;//new GroupDAO("testdomain");
                PermissionDAO pdao = null;//new PermissionDAO("testdomain");
 
              //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
              // delete the original structure of "super" data in table
                           
  	        System.out.println("delete user1 data?--->"+udao.delete("SUPER"));
  	        System.out.println("delete group1 data?--->"+gdao.delete("SUPERS"));
  	        System.out.println("delete permission1 data?--->"+pdao.delete("SUPER1"));  
  	        System.out.println("delete permission2 data?--->"+pdao.delete("SUPER2"));   
  	        
              //=-=-=-=-=-=-=-*< step4 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
              // 1. insert user data
              // 2. insert group data  	      
                 	        	        		  	        		
   	        System.out.println("insert user1 data?--->"+udao.insert(u1));
                System.out.println("insert group1 data?--->"+gdao.insert(g1));
   	 	System.out.println("insert permission1 data?--->"+pdao.insert(p1));
   	 	System.out.println("insert permission2 data?--->"+pdao.insert(p2));  
   	 	 	 	
              //=-=-=-=-=-=-=-*< step5 >*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*
              // 1. add user&group data to  usergroup table
            
   	  	System.out.println(gdao.addUserToGroup(u1.getID(),g1.getID()));       //adding u1 to g1    	


   	  }catch(Exception e){ ExceptionBroadcast.print(e);}
      }

   }

