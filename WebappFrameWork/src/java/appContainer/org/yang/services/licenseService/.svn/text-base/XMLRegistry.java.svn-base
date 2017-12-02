package org.yang.services.licenseService;

import java.io.IOException;
import java.io.Serializable;
import org.yang.util.xml.XMLUtil;
import org.yang.util.ExceptionBroadcast;
import org.yang.util.xml.XMLUtilException;
import org.w3c.dom.Document;
import org.apache.commons.codec.binary.Base64;  

/**
 * Title: XMLRegistry
 * Description:
 *   An XMLRegistry object can be constructed with an XML String.
 *   Methods are provided to read the data in an XML String into a DOM object
 *   and to write out a DOM as an XML String.
 *   Note: XML String can be stored as encrypted on request. When it's encrypted,
 *         it need to be decrypted before converting to DOM.
 */

/**
 *  This class provides methods to construct an DOM object from an XML String
 *  and store the DOM back to XML String; encryption is performed per request.
 */
public class XMLRegistry implements Serializable
{
   /**
   * default constructor.
   */
   public XMLRegistry() {}

   /**
   * This method reads data in the XML String into a DOM Object.
   * The content of the XML String can be decrypted on request.
   * A Document object will be returned.
   */
   public static Document str2dom(String xmlStr, boolean decrypt)
   {
      String str = xmlStr;
      if(xmlStr == null)
         return null;
      if (decrypt)
      { //decryption of the xml file.
         LicenseKeyCipher c = new LicenseKeyCipher();

         byte[] ciphertext = null;
         try
         {
            //ciphertext = new sun.misc.BASE64Decoder().decodeBuffer(str); //in old version based on jdk1.4.2 api
            ciphertext = Base64.decodeBase64(new String(str).getBytes()); //in new version based on jdk1.6.0_20 api 
			
         }
         catch (IllegalArgumentException e)
         {
            ExceptionBroadcast.print(e);
         }

         byte[] cleartext = c.decrypt(ciphertext);
         str = new String(cleartext);
      }

      //parse the decrypted content stored in str.
      Document doc = null;
      try{doc = XMLUtil.xml2dom(str);} catch(XMLUtilException e) {return null;}
      return doc;
   }

   /**
   * This method writes out a DOM object as an XML String.
   * The DOM object is passed as parameter.
   * The content can be encrypted on request before saved to a xml String.
   */
   public static String dom2str(Document d, boolean encrypt)
   {
      String xmlStr = null;
      try
      {
         xmlStr = XMLUtil.dom2xml(d);} catch (Exception e) {
         ExceptionBroadcast.print(e);
         return null;
       }

       if (encrypt)
       {
          LicenseKeyCipher c = new LicenseKeyCipher();
          byte[] text = xmlStr.getBytes();
          text = c.encrypt(text);
          //xmlStr = new sun.misc.BASE64Encoder().encode(text);//old version based  on jdk1.4.2
		  xmlStr = new String(Base64.encodeBase64(new String(text).getBytes())); // new version based on jdk1.6.0_20
       }
       return xmlStr;
   }
}