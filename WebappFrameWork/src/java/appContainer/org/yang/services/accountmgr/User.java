/**
 * This class is used to define a user in security package
 * the user represent an entity
 * @ title:          User class in SITEWare security package
 * @ architecture:   Liu Le
 * @ author:         Hui Zhang
 * @ version:        1.0  June 20, 2001
 * @ company:        Screamingmedia Inc.
 */
package org.yang.services.accountmgr;
import java.io.Serializable;

/**
 * @testcase org.test.org.yang.services.accountmgr.TestUser 
 */
public class User  implements Serializable
{
   static final long serialVersionUID = 4711296382979765001L;

   /**
    *User class contains following fields that will be persistent
    */
   private  String uID            ="";
   private  String uFName         ="";
   private  String uLName         ="";
   private  String upassword      ="";
   private  String uDescription   ="";
   private  String uAdds1         ="";
   private  String uAdds2         ="";
   private  String uCity          ="";
   private  String uState         ="";
   private  String uPCode         ="";
   private  String uCountry       ="";
   private  String uTelNumber     ="";
   private  String uFaxNumber     ="";
   private  String uCellNumber    ="";
   private  String uEMail         ="";
   private  String uTemplateSet   ="";
   private  String uLanguageSet   ="";
   private  String defaultService = "";

   public void setDefaultService(String service) {defaultService = service;}
   public String getDefaultService() {return defaultService;}

	public      User                ()             {}
    public void setID               (String id)    {uID=id;}
	public String  getID              () {return uID;}

    public void setFirstName        (String fname) {uFName=fname;}
    public String  getFirstName       () {return uFName;}

    public void setLastName         (String lname) {uLName=lname;}
    public String  getLastName        () {return uLName;}

	public void setAddress1         (String uadd1) {uAdds1 =uadd1;}
	public String  getAddress1        () {return uAdds1;  }

    public void setAddress2         (String uadd2) {uAdds2 =uadd2;}
    public String  getAddress2        () {return uAdds2 ; }

    public void setCity             (String ucity) {uCity  =ucity;}
    public String  getCity            () {return uCity ;}

	public void setState            (String ustate){uState= ustate;}
	public String  getState           () {return uState;}

    public void setZipCode          (String upc)   {uPCode= upc;}
    public String  getZipCode         () {return uPCode;}

    public void setCountry          (String ucty)  {uCountry=ucty;}
    public String  getCountry         () {return uCountry;}

	public void setTelephoneNumber  (String utln)  {uTelNumber=utln;}
	public String  getTelephoneNumber () {return uTelNumber;}

    public void setFaxNumber        (String ufn)   {uFaxNumber=ufn;}
    public String  getFaxNumber       () {return uFaxNumber;}

    public void setCellphoneNumber  (String ucell) {uCellNumber=ucell;}
    public String  getCellphoneNumber () {return uCellNumber;}

	public void setEMail            (String uem)   {uEMail=uem;}
	public String  getEMail           () {return uEMail;}

    public void setTemplateSet      (String uts)   {uTemplateSet=uts;}
    public void setLanguageSet      (String ulag)  {uLanguageSet=ulag;}

	public void setDescription      (String udp)   {uDescription=udp;}
    public String  getTemplateSet     () {return uTemplateSet;}

    public String  getDescription     () {return uDescription;}
    public String  getLanguageSet     () {return uLanguageSet;}

    public String  getPassword        () {return upassword;}
	public void setPassword   (String pw, boolean encrypt)
	{
       if(encrypt==true)
	   {
          upassword=Encrypt.encrypt(pw);
       }
       else
          upassword=pw;
    }

	// from original method
    public String encryptpwd=Encrypt.encrypt(upassword);

   //  used by one cases:  only check the encrypted password
   public boolean checkPassword(String p)
   {
      if(upassword == null)
         return true; //no password set, return OK.
      String encryptedPasswd = Encrypt.encrypt(p);
      return (upassword.equals(encryptedPasswd));
   }


    public boolean equals(Object o)
    {
       User temp = (User)o;
       if(null==uID||null==temp.uID)
          return false;
       else if(uID.equals(temp.uID))
          return true;
       else
          return false;
    }

	public static void main(String[] args)
	{
		User u= new User();
		u.setID("hui1233333");
		u.setFirstName("HUI");
		u.setLastName("ZHANG");
		u.setPassword("4hui",true);
		u.setDescription("where are you ?");

		u.setAddress1  ("Flushing");
        u.setAddress2  ("Soodside") ;
        u.setCity      ("Shanghai");

 	    u.setState      ("Maine");
        u.setZipCode      ("11355") ;
        u.setCountry    ("Taiwan");

	    u.setTelephoneNumber  ("607-721-0583");
		u.setFaxNumber  (" 718-359-0681");
        u.setCellphoneNumber ("917-488-0133");

	    u.setEMail       ("sunrayh@hotamil.com");
        u.setTemplateSet ("template2");
        u.setLanguageSet ("Spanishi") ;


		System.out.println(u.getID());
		System.out.println(u.getFirstName()+"1");
        System.out.println(u.getLastName()+"2");
		System.out.println(u.getPassword()+"3");
		System.out.println(u.getDescription()+"4");
		System.out.println(u.checkPassword("4hui")+"5");

		System.out.println(u.getAddress1()+"6");
		System.out.println(u.getAddress2()+"7");
        System.out.println(u.getCity()+"8");
		System.out.println(u.getState()+"9");
		System.out.println(u.getZipCode  ()+"10");
		System.out.println(u. getCountry ()+"11");

         System.out.println(u.getTelephoneNumber()+"12");
		System.out.println(u. getFaxNumber()+"13");
        System.out.println(u.getCellphoneNumber()+"14");
		System.out.println(u.getEMail()+"15");
		System.out.println(u.getTemplateSet()+"16");
		System.out.println(u.getLanguageSet()+"17");

		System.out.println("Hello World!");
	}
}