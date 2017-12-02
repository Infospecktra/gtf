package org.yang.services.licenseService;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.util.ExceptionBroadcast;
import org.yang.services.dbService.DataAccessException;
import org.yang.util.xml.XMLUtil;
import org.yang.util.xml.XMLUtilException;
import org.yang.services.config.Config;
import org.yang.util.SMFile;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Node;

/**
 * Title: LicenseKey.java
 * Description:
 *     LicenseKey class provides methods to access and modify every single
 *     global and domain-specific configuration settings that are stored in
 *     license key table as xml String.
 *     The implementation gets the xml String from database license key table,
 *     then uses an XMLRegistry object to read data into DOM objects for
 *     manipulation; after modification, write the updated data back to XML
 *     String and saved back to the license key table entry. Note that 2 document
 *     objects will be generated from 2 license key table entries respectively.
 *     Class methods are specific to either doc1 or doc2.
 */

/**
 * LicenseKey is a singleton class that ensure there is only one instance of it
 * at any time. Two document objects will be initialized when a LicenseKey
 * object is created. One will be stored as encrypted xml String and the other
 * as un-encrypted xml String in the license key table in database.
 * Methods are provided to manipulate siteware and domain level properties.
 * Currently only properties in String and integer are supported.
 */
public class LicenseKeyx  implements Serializable
{
   private static LicenseKeyx myInstance = null;
   private static Object lock = new Object();
   private static final String DOMAIN_TAG = "domain";
   public static final String LICENSE_KEY1_FILENAME = "licensekey.enc";

   private static final String INITXML1_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<SITEWare ID=\"";
   private static final String INITXML1_2 = new StringBuffer().append
    ("\" maxDomains=\"20\" startDate=\"1999/01/04\" stopDate=\"2002/12/31\">").append
    ("</SITEWare>").toString();

   private static final String INITXML2 = new StringBuffer().append
    ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append
    ("<SITEWare ID=\"licensekey2\" enableAutoUpdates=\"true\" enableSSL=\"true\" ").append
    ("SSLKeyFile=\"kFile\" SSLPswd=\"123\" timeout=\"15\" SWHostname=\"alien\" SWPort=\"1357\" ").append
    ("ServWHostname=\"serv\" ServWPort=\"2468\" checkServWConn=\"true\">").append
    ("</SITEWare>").toString();

   public static final String LICENSE_KEY_TABLE = "license_key";
   public static final String ID_1 = "id1"; //id for license key 1 in license_key table.
   public static final String ID_2 = "id2"; //id for license key 2 in license_key table.

   static final boolean cipher1 = true;
   //static final boolean cipher1 = false; //encryption is turned off for now.
   static final boolean cipher2 = false;

   //siteware level attribute names for license key 1.
   public static final String swID = "ID";
   public static final String swMaxDomains = "maxDomains";
   public static final String swStartDate = "startDate";
   public static final String swStopDate = "stopDate";
   public static final String swWorkingDir ="workingDirectory";
   public static final String[] swAttr1 = {swID,swMaxDomains,swStartDate,
                                          swStopDate,swWorkingDir};

   //siteware level attribute names for license key 2.
   public static final String swEnableAutoUpdates = "enableAutoUpdates";
   public static final String swEnableSSL = "enableSSL";
   public static final String swSSLKeyFile = "kFile";
   public static final String swSSLPswd = "SSLPswd";
   public static final String swTimeout = "timeout";
   public static final String swHostname = "SWHostname";
   public static final String swPort = "SWPort";
   public static final String swServWHostname = "ServWHostname";
   public static final String swServWPort = "ServWPort";
   public static final String swCheckServWConn = "checkServWConn";
   public static final String[] swAttr2 = {swEnableAutoUpdates,swEnableSSL,swSSLKeyFile,
                                          swSSLPswd,swTimeout,swHostname,swPort,
                                          swServWHostname,swServWPort, swCheckServWConn};

   //domain level property tag names for license key 1.
   public static final String dSessionTimeOut = "sessionTimeOut";
   public static final String dVersionImg = "versionImage";
   public static final String dLogo = "logo";
   public static final String dStartDate = "startDate";
   public static final String dStopDate = "stopDate";
   public static final String dContentStartDate = "contentStartDate";
   public static final String dContentStopDate = "contentStopDate";
   public static final String dMaxUserAccounts = "maxUserAccounts";
   public static final String dMaxSources = "maxSources";
   public static final String dOverridingDefaultEditableValue = "overridingDefaultEditableValue";
   public static final String dOverridingSpecificEditableValue = "overridingSpecificEditableValue";
   public static final String dMaxSections = "maxSections";
   public static final String dMaxRenditions = "maxRenditions";
   public static final String dHandsfreeEnabled = "handsfreeEnabled";
   public static final String dMaxProcessors = "maxProcessors";
   public static final String dMaxGrabbers = "maxGrabbers";
   public static final String dMaxDepositors = "maxDepositors";
   public static final String dMaxPullerThreads = "maxPullerThreads";
   public static final String dMaxPublishThreads = "maxPublishThreads";
   public static final String dPurchaseLog = "purchaseLog";

   public static final String[] dTag1 = {dSessionTimeOut,dStartDate,dStopDate,dContentStartDate,
                                         dContentStopDate,dMaxUserAccounts,dMaxSources,
                                         dOverridingDefaultEditableValue,dLogo,
                                         dOverridingSpecificEditableValue,
                                         dMaxSections,dMaxRenditions,dVersionImg,
                                         dHandsfreeEnabled, dMaxProcessors,
                                         dMaxGrabbers, dMaxDepositors,
                                         dMaxPullerThreads,dMaxPublishThreads,dPurchaseLog};

   //domain level property tag names for license key 2.
   public static final String dDisplayName = "displayName";
   public static final String dMaxViewableContents = "maxViewableContents";
   public static final String dSecExpPolicy = "secExpPolicy";
   public static final String dSMTPHostname = "smtpHostname";
   public static final String dSMTPPort = "smtpPort";
   public static final String dAlertEvents = "alertEvents";
   public static final String dAlertEvent = "alertEvent";
   public static final String[] dTag2 = {dDisplayName,dMaxViewableContents,
                                         dSecExpPolicy,dSMTPHostname,
                                         dSMTPPort,dAlertEvents};


   //DOM object that should be encrypted, corresponding to ID_1.
   transient private Document doc1 = null;
   //DOM object that's in plain text, corresponding to ID_2.
   transient private Document doc2 = null;

   //root node of the doc1 object.
   transient private Element root1 = null;
   //root node of the doc2 object.
   transient private Element root2 = null;
   ////
   //   private String sitewareID = "noID";

   private static LicenseKeyDAO dao = null;

   private LicenseKeyx() throws DataAccessException
   {
      try
      {
         dao = new LicenseKeyDAO(LICENSE_KEY_TABLE);
         load();
      }
      catch (DataAccessException e)
      {
         throw e;
      }
   }

   /**
   * Public method providing access to the single instance of LicenseKey class.
   */
   public static LicenseKeyx getInstance()throws DataAccessException
   {
      if(null==myInstance)
      {
         synchronized(lock)
         {
            if(null==myInstance)
            {
               myInstance = new LicenseKeyx();
               init();
            }
         }
      }

      // Use the following to disable caching of LK. --- Nov 14th, 2002
      myInstance.load();

      return myInstance;
   }

   public void load() throws DataAccessException
   {
      String xmlStr1Enc = dao.get(ID_1);
      doc1 = XMLRegistry.str2dom(xmlStr1Enc, cipher1);

      if (doc1!=null)
         root1 = doc1.getDocumentElement();

      String xmlStr2 = dao.get(this.ID_2);
      doc2 = XMLRegistry.str2dom(xmlStr2, cipher2);

      if (doc2!=null)
         root2 = doc2.getDocumentElement();
   }

   public static void init() throws DataAccessException
   {
      init(null);
   }

   /**
   * 1. Make sure the table exists. If not, create it.
   * 2. Load LicenseKey1 from file, update license_key table accordingly then delete
   * the file; if the file doesnt exist, load from db.
   */
   public static void init(String theSWID) throws DataAccessException
   {
      if(! dao.isTableExist())
      {//license key table doesn't exist.
         dao.createTable();

         //necessary - YES for now
         String INITXML1 = INITXML1_1+theSWID+INITXML1_2;
         Document initDoc = null;

         try
         {
            initDoc = XMLUtil.xml2dom(INITXML1);
         }
         catch(XMLUtilException e)
         {
            ExceptionBroadcast.print(e);
         }

         String INITXML1Enc = XMLRegistry.dom2str(initDoc,cipher1);
         dao.insert(ID_1, INITXML1Enc);
         dao.insert(ID_2, INITXML2);
      }

      String dir = Config.getInstance().getWORKDIR() + "/import";
      try
      {
         (new File(dir)).mkdirs();
      }
      catch(Exception e)
      {}

      String xmlStr1Enc = SMFile.parseFromTextFile(dir + "/"+LICENSE_KEY1_FILENAME);
      File licenseKey1File = new File(dir, LICENSE_KEY1_FILENAME);
      if(licenseKey1File.exists())
         licenseKey1File.delete();

      if (xmlStr1Enc != null)
      {
         dao.update(ID_1,xmlStr1Enc);
         String msg = "LicenseKey imported from file";
      }
   }

   /**
    * write the data in doc1 and doc2 back to the XML files respectively.
    */
   public void store() throws DataAccessException
   {
      System.out.println("saving licensekey..");
      String xmlStr1 = XMLRegistry.dom2str(doc1, cipher1);
      dao.update(this.ID_1,xmlStr1);

      String xmlStr2 = XMLRegistry.dom2str(doc2, cipher2);
      dao.update(this.ID_2,xmlStr2);
   }

   //Export LicenseKey1 to the default file from database table.
   public void exportToFile()
   {
      String dir = Config.getInstance().getWORKDIR() + "/export";
      try
      {
         (new File(dir)).mkdirs();
      }
      catch (Exception e)
      {
         String msg = "Failed to create export dir: " + dir;
         System.out.println(msg);
         return;
      }
      exportToFile(dir + "/" + LICENSE_KEY1_FILENAME,null);
   }

   private void exportToFile(String fileName1, String fileName2)
   {
      try
      {
         String xmlStr1 = dao.get(ID_1);
         if ((xmlStr1 != null)&&(fileName1 != null))
            SMFile.writeToFile(fileName1, xmlStr1);
         String msg = "LicenseKey exported to file";
      }
      catch(DataAccessException e)
      {
         String msg = "Failed to exort LicenseKey to file: ";
         ExceptionBroadcast.print(e);
      }

      try
      {
         String xmlStr2 = dao.get(ID_2);
         if ((xmlStr2 != null)&&(fileName2 != null))
            SMFile.writeToFile(fileName2, xmlStr2);
      }
      catch(DataAccessException e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   /**
   * Return 1 if the attribute belongs to license key 1;
   * return 2 if it belongs to license key 2;
   * return 0 if it doesn't belong to either.
   */
   private int isAttr(String attr)
   {
      if(attr == null) return 0;
      for (int i= 0; i<swAttr1.length; i++)
         if(attr.equals(swAttr1[i])) return 1;
      for (int i=0; i<swAttr2.length; i++)
         if(attr.equals(swAttr2[i])) return 2;
      return 0;
   }

   /**
   * Return 1 if the tag belongs to license key 1;
   * return 2 if it belongs to license key 2;
   * return 0 if it doesn't belong to either.
   */
   private int isTag(String tagName)
   {
      if(tagName == null)
         return 0;
      for (int i= 0; i<dTag1.length; i++)
         if(tagName.equals(dTag1[i])) return 1;
      for (int i=0; i<dTag2.length; i++)
         if(tagName.equals(dTag2[i])) return 2;
      return 0;
   }

   /**
   * Return the siteware attribute value that is in String.
   * The attribute name is supplied as parameter.
   * Valid tagnames are listed as static final constants of this class.
   */
   public String getSWProperty(String attr)
   {
      Element root = null;
      if(isAttr(attr) == 0)
         return null;
      else if(isAttr(attr) == 1)
         root = root1;
      else
         root = root2;

      if(root != null)
      {
         String value = root.getAttribute(attr);
         if(!value.equals("")) return value;
      }
      return null;
   }

   /**
   * Set the siteware attribute values that are in String.
   * The attribute name is supplied as parameter.
   * Valid tagnames are listed as static final constants of this class.
   */
   public void setSWProperty(String attr, String value)
   {
      Element root = null;
      if(isAttr(attr) == 0)
         return;
      else if(isAttr(attr) == 1)
         root = root1;
      else
         root = root2;

      if(root != null)
         root.setAttribute(attr, value);
   }

   /**
   * Return the siteware attribute value that is in integer.
   * @att: attribute name
   * Valid tagnames are listed as static final constants of this class.
   */
   public int getSWPropertyInt(String attr)
   {
      Element root = null;
      if(isAttr(attr) == 0)
         return -1;
      else if(isAttr(attr) == 1)
         root = root1;
      else
         root = root2;

      if (root != null)
      {
         if(root.getAttribute(attr).equals(""))
            return -1;
         Integer IValue = new Integer(root.getAttribute(attr));
         int iValue = IValue.intValue();
         return iValue;
      }
      else return -1;
   }

   /**
   * Set the SITEWare attribute value that is in integer.
   * The attribute name is supplied as parameter.
   * Valid tagnames are listed as static final constants of this class.
   */
   public void setSWPropertyInt(String attr, int iValue)
   {
      Element root = null;
      if(isAttr(attr) == 0)
         return;
      else if(isAttr(attr) == 1)
         root = root1;
      else
         root = root2;

      if (root != null)
         root.setAttribute(attr, iValue+"");
   }

   /**
   *   return the actual number of domains in the SITEWare;
   *   return zero if there is no domain in the SITEWare.
   */
   public int getDomainCount()
   {
      String tagName = DOMAIN_TAG;
      NodeList domains = root1.getElementsByTagName(tagName);
      if(domains != null)
         return domains.getLength();
      else
         return 0;
   }

   /**
   *   return the collection of domain keys in the SITEWare.
   */
   public Collection getDomainKeys()
   {
      String tagName = DOMAIN_TAG;
      String attrName = "key";
      NodeList domains = root1.getElementsByTagName(tagName);
      if (domains == null)
         return null;
      else
      {
         ArrayList dKeys = new ArrayList();
         for (int i= 0; i<domains.getLength(); i++)
         {
            Element domain = (Element) domains.item(i);
            dKeys.add(domain.getAttribute(attrName));
         }
         return dKeys;
      }
   }

   /**
   *   return the domain of license key 1 when supplied with domain key.
   *   return null if one doesn't exist.
   */
   private Element getDomain(String dKey)
   {
      String tagName = DOMAIN_TAG;
      String attrName = "key";
      Element domain = null;

      NodeList domains = root1.getElementsByTagName(tagName);
      if (domains != null)
      {
         for (int i=0; i<domains.getLength(); i++)
         {
            domain = (Element) domains.item(i);
            if(domain.getAttribute(attrName).equals(dKey))
               return domain;
         }
      }
      return null; //domain with the specified key can't be found.
   }

   /**
   *   return the domain of license key 2 when supplied with domain key.
   *   return null if one doesn't exist.
   */
   private Element getDomain2(String dKey)
   {
      String tagName = DOMAIN_TAG;
      String attrName = "key";
      Element domain = null;

      NodeList domains = root2.getElementsByTagName(tagName);
      if (domains != null)
      {
         for (int i=0; i<domains.getLength(); i++)
         {
            domain = (Element) domains.item(i);
            if(domain.getAttribute(attrName).equals(dKey))
               return domain;
         }
      }
      return null; //domain with the specified key can't be found.
   }

   /**
   * Add a new domain with domain key set to dKey.
   * Return true if succeed, return false if one already exists.
   * Note: only an empty domain will be created if succeed. User should use
   *       provided methods to add properties under the domain.
   */
   public boolean addDomain(String dKey)
   {
      String tagName = DOMAIN_TAG;
      String attrName = "key";

      if (getDomain(dKey) != null)
         return false;
      else
      { //doesn't exist yet.
         Element domain1 = doc1.createElement(tagName);
         domain1.setAttribute(attrName,dKey);
         root1.appendChild(domain1);
         Element domain2 = doc2.createElement(tagName);
         domain2.setAttribute(attrName,dKey);
         root2.appendChild(domain2);

         return true;
      }
   }

   /**
   * If a domain with the specified key is found, return true;
   * return false if not found.
   */
   public boolean isDomain(String dKey)
   {
      if (getDomain(dKey) != null)
         return true;
      else
         return false;
   }

   /**
   * Remove both domains with the specified key in the 2 license keys respectively;
   * return true if succeed false otherwise.
   */
   public boolean removeDomain (String dKey)
   {
      Element domain1 = getDomain(dKey);
      Element domain2 = getDomain2(dKey);
      if (domain1 == null) return false;
      else
      {
        root1.removeChild(domain1);
        if(domain2 != null)
           root2.removeChild(domain2);
        return true;
      }
   }

   /**
   *   return the child Element object with the specified tagName from the parent.
   *   return null if the element with the tagName can't be found.
   */
   private Element getElement(String tagName, Element parent)
   {
      if (parent == null) return null;
      NodeList children = parent.getElementsByTagName(tagName);

     /* For the structure we use, each tagName in the domain is unique.
      * Either the tagName can't be found, return null;
      * either find ONE, return that one.
      */
      if (children == null)
         return null;
      else
         return (Element) children.item(0);
   }

   /**
   *   Return domain child element property that is in String; return null if
   *   the property can't be found.
   *   Note:
   *   1. Only apply to domain properties that contain single value.
   *   2. Valid tagnames are listed as static final constants of this class.
   *   3. The tag name of child element is supplied as parameter.
   */
   public String getDomainProperty(String dKey, String tagName)
   {
      Element domain = null;
      if(isTag(tagName) == 0)
         return null;
      else if(isTag(tagName) == 1)
         domain = getDomain(dKey);
      else
         domain = getDomain2(dKey);

      try
      {
         Element  cElement = getElement(tagName, domain);
         return cElement.getFirstChild().getNodeValue();
      }
      catch (Exception e)
      {
         return null;
      }
   }

   /**
   *   Set domain child element property that is in String.
   *   The tag name of child element is supplied as parameter.
   *   Valid tagnames are listed as static final constants of this class.
   *   Note:
   *   1. Only apply to domain properties that contain single value.
   *   2. If sValue is null or an empty String, remove the property (element).
   *   3. If the property can't be found, add it and set the value.
   */
   public void setDomainProperty(String dKey, String tagName, String sValue)
   {
      Document doc = null;
      Element domain = null;
      if(isTag(tagName) == 0)
         return;
      else if(isTag(tagName) == 1)
      {
         domain = getDomain(dKey);
         doc=doc1;
      }
      else
      {
         domain = getDomain2(dKey);
         doc=doc2;
      }

      if (domain == null)
         return;
      else
      {//domain exists.
         Element cElement = getElement(tagName, domain);
         if (cElement == null)
         {//property doesn't exist.
            if ((sValue == null) || (sValue.length()==0))
               return;
            else
            {//create a new property Element and set the value.
               Element p = doc.createElement(tagName);
               Text pText = doc.createTextNode(sValue);
               p.appendChild(pText);
               domain.appendChild(p);
            }
         }
         else
         {//property exists.
            if ((sValue == null)||(sValue.length()==0))
            {//remove the property Element.
               domain.removeChild(cElement);
            }
            else
            { //reset the property value.
               cElement.getFirstChild().setNodeValue(sValue);
            }
         }
      }//end of the outmost else.
   }

   /**
   *   Return domain child element property that is an integer; return a
   *   negative value(-1) if the property can't be found.
   *   Note:
   *   1. Only apply to domain properties that contain single value.
   *   2. The tag name of child element is supplied as parameter.
   *   3. Valid tagnames are listed as static final constants of this class.
   */
   public int getDomainPropertyInt(String dKey, String tagName)
   {
      Element domain = null;
      if(isTag(tagName) == 0)
         return -1;
      else if(isTag(tagName) == 1)
         domain = getDomain(dKey);
      else
         domain = getDomain2(dKey);

      try
      {
         Element cElement = getElement(tagName, domain);
         int iValue = new Integer(cElement.getFirstChild().getNodeValue().trim()).intValue();
         return iValue;
      }
      catch (Exception e)
      {
         return -1;
      }
   }

   /**
   *   Set domain child element property that is in integer.
   *   The tag name of child element is supplied as parameter.
   *   Valid tagnames are listed as static final constants of this class.
   *   Note:
   *   1. Only apply to domain properties that contain single value.
   *   2. If iValue is negative, remove the property (element).
   *   3. If the property can't be found, add it and set the value.
   */
   public void setDomainPropertyInt(String dKey, String tagName, int iValue)
   {
      Element domain = null;
      if(isTag(tagName) == 0)
         return;
      else if(isTag(tagName) == 1)
         domain = getDomain(dKey);
      else
         domain = getDomain2(dKey);

      if (domain == null)
         return;
      else {//domain exists.
         Element cElement = getElement(tagName, domain);
         if (cElement == null)
         {//property doesn't exist.
            if(iValue<0) return;
            else
            {//create property element and set the value.
               Element p = doc1.createElement(tagName);
               Text pText = doc1.createTextNode(iValue+"");
               p.appendChild(pText);
               domain.appendChild(p);
            }
         }
         else
         { //property exists.
            if (iValue < 0)
            {//remove property.
               domain.removeChild(cElement);
            }
            else
            {
               cElement.getFirstChild().setNodeValue(iValue+"");
            }
         }
      }//end of outmost else.
   }


   /**
   *  return all the templates that belong to the domain as a collection;
   *  return null if there is no template in the domain.
   */
   public Collection getDomainTemplates(String dKey)
   {
      ArrayList list = new ArrayList();
      Element domain = getDomain(dKey);
      String pTagName = "templates";
      String sTagName = "template";
      Element templates = getElement(pTagName,domain);
      if (templates != null)
      {
         NodeList children = templates.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                 list.add(child.getNodeValue());
            }
            return list;
         }
      }
      return null;
   }

   /**
   *  Remove one template from templates under the domain.
   *  Return true if succeed; false if can't be found.
   */
   public boolean removeDomainTemplate(String dKey, String temp)
   {
      Element domain = getDomain(dKey);
      String pTagName = "templates";
      String sTagName = "template";
      Element templates = getElement(pTagName,domain);
      try
      {
         NodeList children = templates.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(temp))
            {
               templates.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch (Exception e)
      {
        ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one template to the domain under templates element; if templates
   *  element doesn't exist, create one and then do the append.
   *
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainTemplate(String dKey, String temp)
   {
      Element domain = getDomain(dKey);
      if(domain == null)
         return false;

      String pTagName = "templates";
      String sTagName = "template";
      Element templates = getElement(pTagName,domain);
      if (templates == null)
      {//create the templates element.
         templates = doc1.createElement(pTagName);
         domain.appendChild(templates);
      }
      else
      {//check if the new template element already existed.
         NodeList children = templates.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               if(children.item(i).getFirstChild().getNodeValue().trim().equals(temp))
                  return false; //already existed.
            }
         }
      }//end of else.

      //add the new template.
      Element template = doc1.createElement(sTagName);
      Text tempText = doc1.createTextNode(temp);
      template.appendChild(tempText);
      templates.appendChild(template);
      return true;
   }

   /**
   *  Clear the content of templates element, then add templates to it.
   *  If templates element doesn't exist yet, create templates element then add
   *  templates to it.
   *  Return true if succeed; false if it already exists.
   */
   public boolean setDomainTemplates(String dKey, String[] temps)
   {
      Element domain = getDomain(dKey);
      if (domain == null)
         return false;

      String pTagName = "templates";
      String sTagName = "template";
      Element templates = getElement(pTagName,domain);
      if (templates == null)
      {//create template element.
         templates = doc1.createElement(pTagName);
         domain.appendChild(templates);
      }
      else
      {//clear the content of templates element.
         NodeList children = templates.getElementsByTagName(sTagName);
         for (int i=children.getLength(); i>0; i--)
            templates.removeChild(children.item(i-1));
     }

      Element template = null;
      Text tempText = null;
      for (int i=0; i<temps.length; i++)
      {
         template = doc1.createElement(sTagName);
         tempText = doc1.createTextNode(temps[i]);
         template.appendChild(tempText);
         templates.appendChild(template);
      }
      return true;
   }

   /**
   *  return all the languages that're allowed in the domain as a collection.
   *  return null if no language is listed.
   */
   public Collection getDomainLanguages(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain(dKey);
      String pTagName = "languages";
      String sTagName = "language";
      Element languages = getElement(pTagName,domain);
      if (languages != null)
      {
         NodeList children = languages.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                 list.add(child.getNodeValue());
            }
            return list;
         }
      } //end of outmost if.
      return null;
   }

   /**
   *  remove one language from languages under the domain.
   *  return true if succeed; false if can't be found.
   */
   public boolean removeDomainLanguage(String dKey, String lang)
   {
      Element domain = getDomain(dKey);
      String pTagName = "languages";
      String sTagName = "language";
      Element languages = getElement(pTagName,domain);
      try
      {
         NodeList children = languages.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(lang))
            {
               languages.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one language to the domain under languages; if languages doesn't
   *  exist, create languages first then do the append.
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainLanguage(String dKey, String lang)
   {
      Element domain = getDomain(dKey);
      if (domain == null)
         return false;

      String pTagName = "languages";
      String sTagName = "language";
      Element languages = getElement(pTagName,domain);
      if(languages == null)
      {//create languages element and added to domain.
         languages = doc1.createElement(pTagName);
         domain.appendChild(languages);
      }
      else
      {//check if the new language element already existed.
         NodeList children = languages.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(lang))
            return false;  //return false if already exists.
         }
      }

      Element language = doc1.createElement(sTagName);
      Text lanText = doc1.createTextNode(lang);
      language.appendChild(lanText);
      languages.appendChild(language);
      return true;
   }

   /**
   *  Clear the content of languages element, then add languages to it.
   *  If languages element doesn't exist yet, create languages element then add
   *  languages to it.
   *  Return true if succeed; false if it already exists.
   */
   public boolean setDomainLanguages(String dKey, String[] langs)
   {
      Element domain = getDomain(dKey);
      if (domain == null)
         return false;

      String pTagName = "languages";
      String sTagName = "language";
      Element languages = getElement(pTagName,domain);
      if (languages == null)
      {//create language element.
         languages = doc1.createElement(pTagName);
         domain.appendChild(languages);
      }
      else
      {//clear the content of languages element.
         NodeList children = languages.getElementsByTagName(sTagName);
         for (int i=children.getLength(); i>0; i--)
            languages.removeChild(children.item(i-1));
      }

      Element language = null;
      Text langText = null;
      for (int i=0; i<langs.length; i++)
      {
         language = doc1.createElement(sTagName);
         langText = doc1.createTextNode(langs[i]);
         language.appendChild(langText);
         languages.appendChild(language);
      }
      return true;
   }

   /**
   *  return all the services that're allowed in the domain as a collection;
   *  return null if no service can be found.
   */
   public Collection getDomainServices(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain(dKey);
      String pTagName = "services";
      String sTagName = "service";
      Element services = getElement(pTagName,domain);
      if (services != null)
      {
         NodeList children = services.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null) list.add(child.getNodeValue());
            }
            return list;
         }
      }
      return null;
   }

   /**
   *  remove one service from services under the domain.
   *  return true if succeed; false if can't be found.
   */
   public boolean removeDomainService(String dKey, String serv)
   {
      Element domain = getDomain(dKey);
      String pTagName = "services";
      String sTagName = "service";
      Element services = getElement(pTagName,domain);
      try
      {
         NodeList children = services.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(serv))
            {
               services.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one service to the domain under services; if services element does
   *  not exist yet, creat services element then do the append.
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainService(String dKey, String serv)
   {
      Element domain = getDomain(dKey);
      if (domain == null)
         return false;

      String pTagName = "services";
      String sTagName = "service";
      Element services = getElement(pTagName,domain);
      if (services == null)
      {//create service element.
         services = doc1.createElement(pTagName);
         domain.appendChild(services);
      }
      else
      {//check if the new service elment already exists.
         NodeList children = services.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(serv))
               return false; //return false if already exists.
         }//end of for loop.
      }

      Element service = doc1.createElement(sTagName);
      Text servText = doc1.createTextNode(serv);
      service.appendChild(servText);
      services.appendChild(service);
      return true;
   }

   /**
   *  Clear the content of services element, then add services to it.
   *  If services element doesn't exist yet, create services element then add
   *  services to it.
   *  Return true if succeed; false if it already exists.
   */
   public boolean setDomainServices(String dKey, String[] servs)
   {
      Element domain = getDomain(dKey);
      if (domain == null)
         return false;

      String pTagName = "services";
      String sTagName = "service";
      Element services = getElement(pTagName,domain);
      if (services == null)
      {//create service element.
         services = doc1.createElement(pTagName);
         domain.appendChild(services);
      }
      else
      {//clear the content of services element.
         NodeList children = services.getElementsByTagName(sTagName);
         for (int i=children.getLength(); i>0; i--)
            services.removeChild(children.item(i-1));
      }

      Element service = null;
      Text servText = null;
      for (int i=0; i<servs.length; i++)
      {
         service = doc1.createElement(sTagName);
         servText = doc1.createTextNode(servs[i]);
         service.appendChild(servText);
         services.appendChild(service);
      }
      return true;
   }

   /**
   *  return all the session IDs in the domain as a collection;
   *  return null if no session ID can be found.
   */
   public Collection getDomainSessionIDs(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain(dKey);
      String pTagName = "sessionIDs";
      String sTagName = "sessionID";
      Element sessionIDs = getElement(pTagName,domain);
      if (sessionIDs != null)
      {
         NodeList children = sessionIDs.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                 list.add(child.getNodeValue());
            }
            return list;
         }
      }
      return null;
   }

   /**
   *  remove one sessionID from sessionIDs under the domain.
   *  return true if succeed; false if can't be found.
   */
   public boolean removeDomainSessionID(String dKey, String sID)
   {
      Element domain = getDomain(dKey);
      String pTagName = "sessionIDs";
      String sTagName = "sessionID";
      Element sessionIDs = getElement(pTagName,domain);
      try
      {
         NodeList children = sessionIDs.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(sID))
            {
               sessionIDs.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one sessionID to the domain under sessionIDs; if sessionIDs element
   *  doesn't exist, create the sessionIDs element then do the append.
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainSessionID(String dKey, String sID)
   {
      Element domain = getDomain(dKey);
      if(domain == null)
         return false;

      String pTagName = "sessionIDs";
      String sTagName = "sessionID";
      Element IDs = getElement(pTagName,domain);
      if(IDs == null)
      {//create sessionIDs element.
         IDs = doc1.createElement(pTagName);
         domain.appendChild(IDs);
      }
      else
      {//check if the new sessionID element already exists.
         NodeList children = IDs.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(sID))
               return false;  //return false if already exists.
         }
      }

      Element ID = doc1.createElement(sTagName);
      Text IDText = doc1.createTextNode(sID);
      ID.appendChild(IDText);
      IDs.appendChild(ID);
      return true;
   }

   /**
   *  Return all the editable content providers that're allowed in the domain
   *  as a collection; return null if no provider is found.
   */
   public Collection getDomainECProviders(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain(dKey);
      String pTagName = "editableContentProviders";
      String sTagName = "provider";
      Element providers = getElement(pTagName,domain);
      if (providers != null)
      {
         NodeList children = providers.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                  list.add(child.getNodeValue());
            }
            return list;
         }
      }
      return null;
   }

   /**
   *  Remove one editable content provider from editableContentProviders under
   *  the domain.
   *  Return true if succeed; false if can't be found.
   */
   public boolean removeDomainECProvider(String dKey, String p)
   {
      Element domain = getDomain(dKey);
      String pTagName = "editableContentProviders";
      String sTagName = "provider";
      Element providers = getElement(pTagName,domain);
      try
      {
         NodeList children = providers.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(p))
            {
               providers.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one editable content provider to the domain under
   *  editableContentProviders; if the parent node doesn't exist, create one
   *  then do the append.
   *
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainECProvider(String dKey, String p)
   {
      Element domain = getDomain(dKey);
      if(domain == null)
         return false;

      String pTagName = "editableContentProviders";
      String sTagName = "provider";
      Element providers = getElement(pTagName,domain);
      if(providers == null)
      {//create the parent node.
         providers = doc1.createElement(pTagName);
         domain.appendChild(providers);
      }
      else
      {//check if the new provider element already exists.
         NodeList children = providers.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(p))
               return false;  //return false if already exists.
         }
      }
      Element provider = doc1.createElement(sTagName);
      Text pText = doc1.createTextNode(p);
      provider.appendChild(pText);
      providers.appendChild(provider);
      return true;
   }

   /**
   *  return all the alertEvents that belong to the domain as a collection;
   *  return null if there is no alertEvent in the domain.
   */
   public Collection getDomainAlertEvents(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain2(dKey);
      String pTagName = "alertEvents";
      String sTagName = "alertEvent";
      Element alertEvents = getElement(pTagName,domain);
      if (alertEvents != null)
      {
         NodeList children = alertEvents.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                  list.add(child.getNodeValue());
            }
            return list;
         }
      }
      return null;
   }

   /**
   *  Remove one alertEvent from alertEvents under the domain.
   *  Return true if succeed; false if can't be found.
   */
   public boolean removeDomainAlertEvent(String dKey, String event)
   {
      Element domain = getDomain2(dKey);
      String pTagName = "alertEvents";
      String sTagName = "alertEvent";
      Element alertEvents = getElement(pTagName,domain);
      try
      {
         NodeList children = alertEvents.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(event))
            {
               alertEvents.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
        ExceptionBroadcast.print(e);
      }
      return false;
   }

   /**
   *  Append one alertEvent to the domain under alertEvents element; if alertEvents
   *  element doesn't exist, create one and then do the append.
   *
   *  Return true if succeed; false if it already exists.
   */
   public boolean addDomainAlertEvent(String dKey, String event)
   {
      Element domain = getDomain2(dKey);
      if(domain == null)
         return false;

      String pTagName = "alertEvents";
      String sTagName = "alertEvent";
      Element alertEvents = getElement(pTagName,domain);
      if (alertEvents == null)
      {//create the alertEvents element.
         alertEvents = doc2.createElement(pTagName);
         domain.appendChild(alertEvents);
      }
      else
      {//check if the new alertEvent element already existed.
         NodeList children = alertEvents.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               if(children.item(i).getFirstChild().getNodeValue().trim().equals(event))
                  return false; //already existed.
            }
         }
      }//end of else.

      //add the new alertEvent.
      Element alertEvent = doc2.createElement(sTagName);
      Text eventText = doc2.createTextNode(event);
      alertEvent.appendChild(eventText);
      alertEvents.appendChild(alertEvent);
      return true;
   }

   /**
   *  Clear the content of alertEvents element, then add alertEvents to it.
   *  If alertEvents element doesn't exist yet, create alertEvents element
   *  then add alertEvents to it.
   *  Return true if succeed; false if it already exists.
   */
   public boolean setDomainAlertEvents(String dKey, String[] events)
   {
      Element domain = getDomain2(dKey);
      if (domain == null)
         return false;

      String pTagName = "alertEvents";
      String sTagName = "alertEvent";
      Element alertEvents = getElement(pTagName,domain);
      if (alertEvents == null)
      {//create alertEvent element.
         alertEvents = doc2.createElement(pTagName);
         domain.appendChild(alertEvents);
      }
      else
      {//clear the content of alertEvents element.
         NodeList children = alertEvents.getElementsByTagName(sTagName);
         for (int i=children.getLength(); i>0; i--)
            alertEvents.removeChild(children.item(i-1));
      }

      Element alertEvent = null;
      Text eventText = null;
      for (int i=0; i<events.length; i++)
      {
         alertEvent = doc2.createElement(sTagName);
         eventText = doc2.createTextNode(events[i]);
         alertEvent.appendChild(eventText);
         alertEvents.appendChild(alertEvent);
      }
      return true;
   }

   /**
   *  return all the grabbers that're allowed in the domain as a collection.
   *  return null if no grabber is listed.
   */
   public Collection getDomainGrabbers(String dKey)
   {
      ArrayList list = new ArrayList();

      Element domain = getDomain(dKey);
      String pTagName = "grabbers";
      String sTagName = "grabber";
      Element grabbers = getElement(pTagName,domain);
      if (grabbers != null)
      {
         NodeList children = grabbers.getElementsByTagName(sTagName);
         if (children != null)
         {
            for (int i=0; i<children.getLength(); i++)
            {
               Node child = children.item(i).getFirstChild();
               if(child != null)
                  list.add(child.getNodeValue());
            }
            return list;
         }
      } //end of outmost if.
      return null;
   }

   /**
   *  remove one grabber from grabbers under the domain.
   *  return true if succeed; false if can't be found.
   */
   public boolean removeDomainGrabber(String dKey, String grab)
   {
      Element domain = getDomain(dKey);
      String pTagName = "grabbers";
      String sTagName = "grabber";
      Element grabbers = getElement(pTagName,domain);
      try
      {
         NodeList children = grabbers.getElementsByTagName(sTagName);
         for (int i=0; i<children.getLength(); i++)
         {
            if(children.item(i).getFirstChild().getNodeValue().trim().equals(grab))
            {
               grabbers.removeChild(children.item(i));
               return true;
            }
         }
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return false;
   }

  /**
   *  Append one grabber to the domain under grabbers; if grabbers doesn't
   *  exist, create grabbers first then do the append.
   *  Return true if succeed; false if it already exists.
   */
  public   boolean addDomainGrabber(String dKey, String grab) {
    Element domain = getDomain(dKey);
    if (domain == null) return false;

    String pTagName = "grabbers";
    String sTagName = "grabber";
    Element grabbers = getElement(pTagName,domain);
    if(grabbers == null) {//create grabbers element and added to domain.
      grabbers = doc1.createElement(pTagName);
      domain.appendChild(grabbers);
    }
    else {//check if the new grabber element already existed.
      NodeList children = grabbers.getElementsByTagName(sTagName);
      for (int i=0; i<children.getLength(); i++) {
        if(children.item(i).getFirstChild().getNodeValue().trim().equals(grab))
          return false;  //return false if already exists.
      }
    }

    Element grabber = doc1.createElement(sTagName);
    Text grabText = doc1.createTextNode(grab);
    grabber.appendChild(grabText);
    grabbers.appendChild(grabber);
    return true;
  }

   /**
   *  Clear the content of grabbers element, then add grabbers to it.
   *  If grabbers element doesn't exist yet, create grabbers element then add
   *  grabbers to it.
   *  Return true if succeed; false if it already exists.
   */
  public   boolean setDomainGrabbers(String dKey, String[] grabs) {
    Element domain = getDomain(dKey);
    if (domain == null) return false;

    String pTagName = "grabbers";
    String sTagName = "grabber";
    Element grabbers = getElement(pTagName,domain);
    if (grabbers == null) {//create grabber element.
      grabbers = doc1.createElement(pTagName);
      domain.appendChild(grabbers);
    }
    else {//clear the content of grabbers element.
      NodeList children = grabbers.getElementsByTagName(sTagName);
      for (int i=children.getLength(); i>0; i--)
        grabbers.removeChild(children.item(i-1));
    }

    Element grabber = null;
    Text grabText = null;
    for (int i=0; i<grabs.length; i++) {
      grabber = doc1.createElement(sTagName);
      grabText = doc1.createTextNode(grabs[i]);
      grabber.appendChild(grabText);
      grabbers.appendChild(grabber);
    }
    return true;
  }

  /**
   *  return all the depositors that're allowed in the domain as a collection.
   *  return null if no depositor is listed.
   */
  public   Collection getDomainDepositors(String dKey) {
    ArrayList list = new ArrayList();

    Element domain = getDomain(dKey);
    String pTagName = "depositors";
    String sTagName = "depositor";
    Element depositors = getElement(pTagName,domain);
    if (depositors != null) {
      NodeList children = depositors.getElementsByTagName(sTagName);
      if (children != null) {
        for (int i=0; i<children.getLength(); i++) {
           Node child = children.item(i).getFirstChild();
          if(child != null) list.add(child.getNodeValue());
        }
        return list;
      }
    } //end of outmost if.
    return null;
  }

  /**
   *  remove one depositor from depositors under the domain.
   *  return true if succeed; false if can't be found.
   */
  public   boolean removeDomainDepositor(String dKey, String deposit) {
    Element domain = getDomain(dKey);
    String pTagName = "depositors";
    String sTagName = "depositor";
    Element depositors = getElement(pTagName,domain);
    try{
      NodeList children = depositors.getElementsByTagName(sTagName);
      for (int i=0; i<children.getLength(); i++) {
        if(children.item(i).getFirstChild().getNodeValue().trim().equals(deposit)){
          depositors.removeChild(children.item(i));
          return true;
        }
      }

    } catch(Exception e){ExceptionBroadcast.print(e);}
    return false;
  }

  /**
   *  Append one depositor to the domain under depositors; if depositors doesn't
   *  exist, create depositors first then do the append.
   *  Return true if succeed; false if it already exists.
   */
  public   boolean addDomainDepositor(String dKey, String deposit) {
    Element domain = getDomain(dKey);
    if (domain == null) return false;

    String pTagName = "depositors";
    String sTagName = "depositor";
    Element depositors = getElement(pTagName,domain);
    if(depositors == null) {//create depositors element and added to domain.
      depositors = doc1.createElement(pTagName);
      domain.appendChild(depositors);
    }
    else {//check if the new depositor element already existed.
      NodeList children = depositors.getElementsByTagName(sTagName);
      for (int i=0; i<children.getLength(); i++) {
        if(children.item(i).getFirstChild().getNodeValue().trim().equals(deposit))
          return false;  //return false if already exists.
      }
    }

    Element depositor = doc1.createElement(sTagName);
    Text depoText = doc1.createTextNode(deposit);
    depositor.appendChild(depoText);
    depositors.appendChild(depositor);
    return true;
  }

   /**
   *  Clear the content of depositors element, then add depositors to it.
   *  If depositors element doesn't exist yet, create depositors element then add
   *  depositors to it.
   *  Return true if succeed; false if it already exists.
   */
  public   boolean setDomainDepositors(String dKey, String[] deposits) {
    Element domain = getDomain(dKey);
    if (domain == null) return false;

    String pTagName = "depositors";
    String sTagName = "depositor";
    Element depositors = getElement(pTagName,domain);
    if (depositors == null) {//create depositor element.
      depositors = doc1.createElement(pTagName);
      domain.appendChild(depositors);
    }
    else {//clear the content of depositors element.
      NodeList children = depositors.getElementsByTagName(sTagName);
      for (int i=children.getLength(); i>0; i--)
        depositors.removeChild(children.item(i-1));
    }

    Element depositor = null;
    Text depositText = null;
    for (int i=0; i<deposits.length; i++) {
      depositor = doc1.createElement(sTagName);
      depositText = doc1.createTextNode(deposits[i]);
      depositor.appendChild(depositText);
      depositors.appendChild(depositor);
    }
    return true;
  }


  /**
   * All the tests are performed in main(). For all the methods provided
   * in the class, there is a corresponding test. After the modification, the
   * new results are wrote back into the XML file.
   * NOTE: the path to the XML file is hardcoded. Need to be modified when
   *        actually perform the test.
   */
  public static void main(String[] args) {
    //get the instance of LicenseKey.
    LicenseKeyx key = null;
    try {
      key = getInstance();
    } catch (Exception e) {
      ExceptionBroadcast.print(e);
      return;
    }

    String fileName1 = "d:\\shared\\licensekey1";
    String fileName2 = "d:\\shared\\licensekey2";
    key.exportToFile(fileName1, fileName2);

    String dKey = "SCRM";  //sample domain key value.
    Iterator ite = null;  //used to iterate child nodes.

    //test on siteware property in String.
    String swID = key.getSWProperty(key.swID);
    System.out.println("id: "+swID);
    key.setSWProperty(key.swID,"screaming");
    System.out.println("new id: "+key.getSWProperty(key.swID));
    String swSSLPswd = key.getSWProperty(key.swSSLPswd);
    System.out.println("password: "+swSSLPswd);
    key.setSWProperty(key.swSSLPswd,"spy");
    System.out.println("new password: "+key.getSWProperty(key.swSSLPswd));


    //test on siteware property in integer.
    int maxD = key.getSWPropertyInt(key.swMaxDomains);
    System.out.println("maxDomains: "+maxD);
    key.setSWPropertyInt(key.swMaxDomains,19);
    System.out.println("new maxDomains: "+key.getSWPropertyInt(key.swMaxDomains));
    int timeout = key.getSWPropertyInt(key.swTimeout);
    System.out.println("timeout: "+timeout);
    key.setSWPropertyInt(key.swTimeout,50);
    System.out.println("new timeout: "+key.getSWPropertyInt(key.swTimeout));


    //test on the actual number of domains in siteware
    int domainCount = key.getDomainCount();
    System.out.println("actual number of domains: " + domainCount);

    //test on the list of domain keys in siteware.
    Collection dKeys = key.getDomainKeys();
    ite = dKeys.iterator();
    System.out.println("a list of domain keys in siteware: ");
    while (ite.hasNext())
      System.out.println(ite.next());


    //test on domain property in String.
    String dStart = key.getDomainProperty(dKey,key.dStartDate);
    System.out.println("domain "+dKey+" start date: "+dStart);
    key.setDomainProperty(dKey, key.dStartDate, "01/04/98");
    System.out.println("domain "+dKey+" new start date:"
                      +key.getDomainProperty(dKey,key.dStartDate));
    String dPolicy = key.getDomainProperty(dKey,LicenseKeyx.dSecExpPolicy);
    System.out.println("domain "+dKey+" sec expiration policy: "+dPolicy);
    key.setDomainProperty(dKey, LicenseKeyx.dSecExpPolicy, "30 seconds");
    System.out.println("domain "+dKey+" new sec expire policy:"
                      +key.getDomainProperty(dKey,LicenseKeyx.dSecExpPolicy));



    //test on domain property in integer.
    int maxU = key.getDomainPropertyInt(dKey,key.dMaxUserAccounts);
    System.out.println("domain "+dKey+" max user accounts: "+maxU);
    key.setDomainPropertyInt(dKey,key.dMaxUserAccounts,98);
    System.out.println("domain "+dKey+" new max user accounts: "
            +key.getDomainPropertyInt(dKey,key.dMaxUserAccounts));
    int maxVC = key.getDomainPropertyInt(dKey,LicenseKeyx.dMaxViewableContents);
    System.out.println("domain "+dKey+" max viewable contents: "+maxVC);
    key.setDomainPropertyInt(dKey,LicenseKeyx.dMaxViewableContents,1000);
    System.out.println("domain "+dKey+" new max viewable contents: "
            +key.getDomainPropertyInt(dKey,LicenseKeyx.dMaxViewableContents));

    //test on domain alert events.
    Collection alertEvents = key.getDomainAlertEvents(dKey);
    if(alertEvents != null) {
      ite = alertEvents.iterator();
      System.out.println("domain "+dKey+" allowed alertEvents: ");
      for (int i=0; i<alertEvents.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainAlertEvent(dKey,"alert event 2");
    }
    key.addDomainAlertEvent(dKey, "alert event 4");
    alertEvents = key.getDomainAlertEvents(dKey);
    ite = alertEvents.iterator();
    System.out.println("domain "+dKey+" new allowed alertEvents: ");
    for (int i=0; i<alertEvents.size(); i++) {
      System.out.println(ite.next());
    }

    //test on domain allowed templates.
    Collection templates = key.getDomainTemplates(dKey);
    if(templates != null) {
      ite = templates.iterator();
      System.out.println("domain "+dKey+" allowed templates: ");
      for (int i=0; i<templates.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainTemplate(dKey,"t2");
    }
    key.addDomainTemplate(dKey, "t4");
    templates = key.getDomainTemplates(dKey);
    ite = templates.iterator();
    System.out.println("domain "+dKey+" new allowed templates: ");
    for (int i=0; i<templates.size(); i++) {
      System.out.println(ite.next());
    }

    //test on domain allowed languages.
    Collection languages = key.getDomainLanguages(dKey);
    if(languages != null) {
      ite = languages.iterator();
      System.out.println("domain "+dKey+" allowed languages: ");
      for (int i=0; i<languages.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainLanguage(dKey,"l2");
    }
    key.addDomainLanguage(dKey, "l5");
    languages = key.getDomainLanguages(dKey);
    ite = languages.iterator();
    System.out.println("domain "+dKey+" new allowed languages: ");
    for (int i=0; i<languages.size(); i++) {
      System.out.println(ite.next());
    }


    //test on domain allowed services.
    Collection services = key.getDomainServices(dKey);
    if(services != null) {
      ite = services.iterator();
      System.out.println("domain "+dKey+" allowed services: ");
      for (int i=0; i<services.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainService(dKey,"s2");
    }
    key.addDomainService(dKey, "s5");
    services = key.getDomainServices(dKey);
    ite = services.iterator();
    System.out.println("domain "+dKey+" new allowed services: ");
    for (int i=0; i<services.size(); i++) {
      System.out.println(ite.next());
    }
    String[] servs = {"serv_test1", "serv_test2"};
    key.setDomainServices(dKey, servs);

    //test on domain contained session IDs.
    Collection sIDs = key.getDomainSessionIDs(dKey);
    if(sIDs != null) {
      ite = sIDs.iterator();
      System.out.println("domain "+dKey+" contained session IDs: ");
      for (int i=0; i<sIDs.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainSessionID(dKey,"ID2");
    }
    key.addDomainSessionID(dKey, "ID5");
    sIDs = key.getDomainSessionIDs(dKey);
    ite = sIDs.iterator();
    System.out.println("domain "+dKey+" new contained session IDs: ");
    for (int i=0; i<sIDs.size(); i++) {
      System.out.println(ite.next());
    }

    //test on domain editable content providers.
    Collection providers = key.getDomainECProviders(dKey);
    if(providers != null) {
      ite = providers.iterator();
      System.out.println("domain "+dKey+" allowed editable content providers: ");
      for (int i=0; i<providers.size(); i++) {
        System.out.println(ite.next());
      }
      key.removeDomainECProvider(dKey,"p2");
    }
    key.addDomainECProvider(dKey, "p5");
    providers = key.getDomainECProviders(dKey);
    ite = providers.iterator();
    System.out.println("domain "+dKey+" new allowed editable content providers: ");
    for (int i=0; i<providers.size(); i++) {
      System.out.println(ite.next());
    }

    //test on create new domain in siteware.
    key.addDomain("newdomain");
    key.setDomainProperty("newdomain","startDate","11/17/80");
    String newStartDate = key.getDomainProperty("newdomain","startDate");
    System.out.println("newdomain start date : "+newStartDate);

    key.setDomainPropertyInt("newdomain","maxSections",49);
    int newMaxSections = key.getDomainPropertyInt("newdomain","maxSections");
    System.out.println("newdomain max sections : "+newMaxSections);

    //test on domain allowed languages.
    key.addDomainLanguage("newdomain","chinese");
    key.addDomainLanguage("newdomain","french");
    languages = key.getDomainLanguages("newdomain");
    ite = languages.iterator();
    System.out.println("newdomain allowed languages: ");
    for (int i=0; i<languages.size(); i++) {
      System.out.println(ite.next());
    }
    key.removeDomainLanguage("newdomain","chinese");
    languages = key.getDomainLanguages("newdomain");
    ite = languages.iterator();
    System.out.println("newdomain newly allowed languages: ");
    for (int i=0; i<languages.size(); i++) {
      System.out.println(ite.next());
    }

    key.removeDomain("newdomain");


    //write the DOM object back to the XML file.
    try {
      key.store();
    } catch (Exception e) {
      ExceptionBroadcast.print(e);
      return;
    }

    key.setDomainProperty(dKey, "logon","yao");
    System.out.println("---"+key.getDomainProperty(dKey, "logon"));

  }//end of main().

}
