
/**
 * This class is used to define a group in security package
 * the group can be used to work as reference to a group people
 * later we can give  some permision to this group and map some user into a group
 * @ title  Group class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version: 1.0  June 20, 2001
 * @ company: Screamingmedia Inc.
 */
package org.yang.services.accountmgr;
import java.io.Serializable;

/**
 * @testcase org.test.org.yang.services.accountmgr.TestGroup 
 */
public class Group  implements Serializable
{
   static final long serialVersionUID = 4711296382979765003L;

    private String ID ="";
    public void setID(String ID) { this.ID=ID; }
    public String  getID(){return ID;}

    private	String gName ="";
    public void setName(String name) {gName=name;}
    public String getName() {return gName;}

    private	String gDescription ="";
    public void setDescription (String dp) {gDescription=dp;}
    public String getDescription (){return gDescription;}

    public Group() {}

    public boolean equals(Object o)
    {
       Group temp = (Group)o;
       if(null==ID||null==temp.ID)
          return false;
       else if(ID.equals(temp.ID))
          return true;
       else
          return false;
    }

   public static void main(String[] args)
	{
		Group g = new Group();
		g.setID("123445");
		g.setName("white trash");
		g.setDescription("this is a extra cheap white trash");
		System.out.println(g.getID());
		System.out.println(g.getName());
		System.out.println(g.getDescription());
		System.out.println("Hello World!");
	}
}
