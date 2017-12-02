/**
 *This class contains the operations and areas and their maping
 * using the Hashtable it is easy to search
 * for any given Permission object we can construct a service and make four querys:
 * all areas in this service
 * all operations in this service
 * for any given area, get all operations
 * for any opeation, get all areas
 * @ title:          Service class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version:        1.0  June 20, 2001
 * @ company:        Screamingmedia Inc.
 */
package org.yang.services.accountmgr;
import java.util.*;
import org.yang.services.accountmgr.Permission;
import java.io.Serializable;
/**
 *  this class object will be constructed by usermanger
 *  the data it needs will come from permission object
 *  two hashMaps will be constructed one by area as key and other one the operation will be as key
 *  the hashMap value will be set which can remove the duplication.
 * @testcase org.test.org.yang.services.accountmgr.TestResource
 */
public class Resource  implements Serializable
{
   static final long serialVersionUID = 4711296382979765002L;

    public static final String EngineersDesk = "EngineersDesk";
    public static final String AccountManager = "AccountManager";
    public static final String AlertEvent = "AlertEvent";
    public static final String EditorsDesk = "EditorsDesk";
    public static final String WritersDesk = "WritersDesk";
    public static final String ContentManager = "ContentManager";
    public static final String Reports = "Reports";
    public static final String Syndicaster = "Syndicaster";
    public static final String Diagnosis = "Diagnosis";
    public static final String ContentServer = "ContentServer"; //Celina

    public static final String[] SERVICEKEYS  = {
        EngineersDesk,
        AccountManager,
        ContentManager,
        EditorsDesk,
        WritersDesk,
        Reports,
        Syndicaster,
        Diagnosis,
        AlertEvent,
        ContentServer //Celina
    };

	private  String  sid                =null;
	private  Map     operationsByArea   = new  HashMap();
	private  Map     areasByOperation   = new  HashMap();
    private  Set     operations         = new  HashSet();
    private  Set     areas              = new  HashSet();
    private  Set     tmp                = new  HashSet();

/**
 * this method will add each permission into two hasmaps
 * the first one is to make a hashMap that area as key and the second one that operation as key
 * also to create two sets one contain all operations and another contains all areas
 * set is used here because it can eliminate the duplication
 * @param  pm permission object
 * @return
 * @exception
 */
public void   addPermision(Permission pm) {
              sid               =pm.getServiceId();
              String operation  = pm.getOperationsConcat();
              String area       = pm.getAreasConcat();
              if ((operation == null) || (area == null) ) return;
              /**
               *thesse two tokens will only be used one time
               */
               StringTokenizer ar1= new StringTokenizer(area, ",");
               StringTokenizer op2= new StringTokenizer(operation, ",");
               /**
                * this method will populate the operationByArea hashMap
                * take the set out of hashmap and check it, put new one inside
                */
                String tmpS="";
                   while (ar1.hasMoreElements())
                      {
                          StringTokenizer op1  = new StringTokenizer(operation, ",");
                          String          okey =(String)ar1.nextToken();
                          HashSet checkbyArea  =(HashSet)operationsByArea.get(okey);
                          if (checkbyArea==null)
                          {checkbyArea= new HashSet();}
                          while(op1.hasMoreElements()){
                              tmpS=op1.nextToken();
                              checkbyArea.add(tmpS);
                              operations.add(tmpS);
                            }
                          operationsByArea.put(okey,checkbyArea);
                       }
                  /**
                   *this method will populate the  areasByOperation    hashMap
                   * take the set out of hashmap and check it, put new one inside
                   */
                   while (op2.hasMoreElements())
                      {
                          StringTokenizer ar2  = new StringTokenizer(area, ",");
                          String          akey =(String)op2.nextToken();
                          HashSet checkbyOp    =(HashSet)areasByOperation.get(akey);
                          if (checkbyOp==null)
                          {checkbyOp= new HashSet();}
                          while(ar2.hasMoreElements()){
                              tmpS =ar2.nextToken();
                              checkbyOp.add(tmpS);
                              areas.add(tmpS);
                           }
                          areasByOperation.put(akey, checkbyOp);
                       }
           }
/**
 * this method will get all areas in this service object
 * @param
 * @return   an array of areas
 * @exception
 */
public   String [] getAllAreas(){
             String [] allAs = new String[areas.size()];
             Iterator  all   = sortedIterator(areas);
             int       count = 0;
             String tmp="";
			 while(all.hasNext())
                {
                     tmp= (String)all.next();
                     if (!("*".equals(tmp))) { allAs[count]=tmp; }
                     count++;
                 }
              return allAs;
         }
/**
 * this method will get all areas in this service object
 * @param
 * @return   an array of areas
 * @exception
 */
 public   String [] getAllOperations(){
                        String [] allOs= new String[operations.size()];
                         Iterator  all   = sortedIterator(operations);
                         int       count = 0;
                         String    tmp   = "";
						 while(all.hasNext())
                         {
                             tmp= (String)all.next();
					         if (!("*".equals(tmp))) { allOs[count] =tmp; }
							 count++;
                         }
                        return allOs;
          }
/**
 * this method can query operations in any perticular area
 * @param    ar area name
 * @return   an array of operations
 * @exception
 */
public   String [] getOperationsInArea(String ar ) {
            Set operation   = (HashSet)operationsByArea.get(ar);
            if (operation==null||areas.contains("*"))
			  {
			       return new String [0];
			   }
			else
			{
			   String [] ops   = new String[operation.size()];
               Iterator  it    = sortedIterator(operation);
               int     count   =0;
               String    tmp   = "";
			   while(it.hasNext())
               {
                  tmp= (String)it.next();
				  if (!("*".equals(tmp))) {ops[count]=tmp; }
				  count++;
               }
			   return ops;
			}
   }
 /**
 * this method can query areas for any perticular operation
 * @param    op operation  name
 * @return   an array of areas
 * @exception
 */
 public   String [] getAreasForOperation(String op ) {
             Set       area  = (HashSet)areasByOperation.get(op);
             String [] ars   = new String[area.size()];
             Iterator  it    = sortedIterator(area);
             int       count =0;
             String    tmp   = "";
			  while(it.hasNext())
              {
                  tmp= (String)it.next();
				  if (!("*".equals(tmp))) {ars[count]=tmp; }
				  count++;
              }
           return ars;
      }
/**
 * this method is used to check is there any permission for this couple
 * three conditions as allow: these two has exactly match; if in areas contain "*";
 * or if in operations has "*"
 * @param    op operation  name, ar area name
 * @return   boolean true mean has permission false means doesnot
 * @exception
 */
 public boolean hasPermission(String op, String ar){
        boolean   success=false;
        String [] has    =   getOperationsInArea( ar );
		Set       check  = new HashSet();
		if(has.length==0&&areas.contains("*")) success=true;
		else {
			   for(int i=0;i<has.length;i++)
				 {
			       check.add(has[i]) ;
			     }
               success=check.contains(op) ;
               if((success==false)&&(operations.contains("*")))
               success=true;
			 }
         return success;
      }
      
      private Iterator sortedIterator(Set set)
      {
         ArrayList temp = new ArrayList(set);
         Collections.sort(temp);
         return temp.iterator();
      }
      
	public static void main(String[] args)
	{
	     Resource service = new Resource();
             Permission    pm = new Permission();
             pm.setOperationsConcat("a1,a2,a3,");
             pm.setAreasConcat("b1,b2,b3");
             Permission    pn = new Permission();
             pn.setOperationsConcat("a1,a4,a5,");
             pn.setAreasConcat("b1,b4,b3");
             Permission    p3= new Permission();
             p3.setOperationsConcat("*,a4,a6");
             p3.setAreasConcat("*,b3,b7");
            service.addPermision( pm);
            service.addPermision( pn);
            service.addPermision(p3);
            System.out.println("Hello World!");
            String [] area=service.getAllAreas();
            String [] operation=service.getAllOperations();
            String []  opByA   =service.getOperationsInArea("b2");
            String []  arbyOp  =service.getAreasForOperation("a4");
            System.out.println("this method will give you areas by operation");
             for(int i=0; i<arbyOp.length;i++)
                  {
                     System.out.println(arbyOp[i]);
                 }
            System.out.println("this method will give you operations by area");
            for(int i=0; i<opByA.length;i++)
                  {
                    System.out.println(opByA[i]);
                  }
            System.out.println("this method will give you all operations in this service");
            for(int i=0; i<operation.length;i++)
                  {
                     System.out.println(operation[i]);
                  }
           System.out.println("this method will give you all areas in this method");
            for(int i=0; i<area.length;i++)
                  {
                     System.out.println(area[i]);
                }
             System.out.println("this is to show haspermission "+service.hasPermission("a","b"));
           }
}
