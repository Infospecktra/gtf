package org.yang.services.licenseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.yang.util.ExceptionBroadcast;

/**
 * Title:        LicenseKeyCipher
 * Description:
 *   This class implements password -based encryption and decryption of
 *   LicenseKey.  A DES (Data Encryption Standard) cipher in Electronic
 *   Codebook mode, with PKCS#5-style padding is used.
 *
 *   Note: that permission should be granted to the JCE 1.2.1 framework when a
 *   security manager is installed when the JCE 1.2.1 framework and/or providers
 *   are not installed extensions (check installation instructions for detail).
 */


public class LicenseKeyCipher implements Serializable
{
   //Dynamically register the SunJCE provider .
   static
   {
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
   }

   //The default algorithm used by PBE cipher.
   private static final String defaultAlgorithm = "PBEWithMD5AndDES";
   //Hardcoded passwd for encryption and decryption.
   private static final char[] passwd = {'o','c','m','d'};

   private String pbeAlgorithm = null;
   private PBEParameterSpec pbeParamSpec = null;

   /**
   * initialize the pbe algorithm and parameter spec.
   */
   public LicenseKeyCipher(String pbeAlg)
   {
      try
      {
         pbeAlgorithm = pbeAlg;

         //Initialize PBE parameter spec.
         byte[] salt = { (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                         (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99 };
         int count = 20;   // Iteration count
         pbeParamSpec = new PBEParameterSpec(salt, count);
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   /**
   * Default constructor, used the default algorithm (DES).
   */
   public LicenseKeyCipher()
   {
      this(defaultAlgorithm);
   }

   /**
   *  Derive the key from password; return null if failed.
   */
   private SecretKey getKey(char[] passwd)
   {
      SecretKey pbeKey = null;
      try
      {
         PBEKeySpec pbeKeySpec = new PBEKeySpec(passwd);
         SecretKeyFactory keyFac = SecretKeyFactory.getInstance(pbeAlgorithm);
         pbeKey = keyFac.generateSecret(pbeKeySpec);
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         return null;
      }
      return pbeKey;
   }

   /**
   * Encrypt/decrypt the content of a file and saved the result back to the file.
   * Do encryption when mode == 0;
   * Do decryption when mode == 1.
   */
   public void transform(String filename, int mode) throws Exception
   {
      int fileLen = (int) new File(filename).length();
      byte[] oContent = new byte[fileLen];
      FileInputStream fis = new FileInputStream(filename);
      fis.read(oContent);

      SecretKey pbeKey = this.getKey(passwd);

      //Instantiate a pbe cipher.
      Cipher pbeCipher = Cipher.getInstance(pbeAlgorithm);

      //System.out.println("=== JCE Provider is: ");
      //System.out.println("==="+pbeCipher.getProvider());

      if (mode == 0)
      {// Initialize PBE Cipher for encryption.
         pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
      }
      else
      {// Initialize PBE Cipher for decryption.
         pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
      }

      // transform the text.
      byte[] tContent = pbeCipher.doFinal(oContent);

      //write the transformed content back to the original file.
      FileOutputStream fos = new FileOutputStream(filename);
      fos.write(tContent);
   }

   /**
   *  Encrypt a byte array and return the encypted result in byet array;
   *  return null if failed.
   */
   public byte[] encrypt(byte[] input )
   {
      try
      {
         //Initialize PBE cipher for encryption.
         Cipher pbeCipher = Cipher.getInstance(pbeAlgorithm);

         //System.err.println("\n[JCE Provider is:]");
         //System.err.println("==="+pbeCipher.getProvider());

         SecretKey pbeKey = this.getKey(passwd);
         pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

         // Encrypt the input text.
         byte[] ciphertext = pbeCipher.doFinal(input);
         return ciphertext;
      }
      catch (Exception e)
      {
         System.out.println("Encryption exceptions:");
         ExceptionBroadcast.print(e);
         return null;
      }
   }

   /**
   * Decrypt a byte array and return the decypted result in byet array.
   */
   public byte[] decrypt(byte[] input )
   {
      try
      {
         //Initialize PBE cipher for decryption.
         Cipher pbeCipher = Cipher.getInstance(pbeAlgorithm);

         //System.err.println("\n[JCE Provider is]");
         //System.err.println("==="+pbeCipher.getProvider());

         SecretKey pbeKey = this.getKey(passwd);
         pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

         // Decrypt the input text.
         byte[] cleartext = pbeCipher.doFinal(input);

         return cleartext;
      }
      catch (Exception e)
      {
         System.out.println("Decryption exceptions: ");
         ExceptionBroadcast.print(e);
         return null;
      }
   }

   public static void main (String[] args)
   {
      LicenseKeyCipher c = new LicenseKeyCipher();
//    Provider pro = Security.getProvider("SunJCE");
//    System.out.println(pro.getName());
//    System.out.println(pro.toString());

      byte[] test = "harry_potter".getBytes();
      System.out.println("original text: " + new String(test));
      byte[] ciphertext = c.encrypt(test);
      System.out.println("encrypted text: " + new String(ciphertext));
      byte[] cleartext = c.decrypt(ciphertext);
      System.out.println("decrypted text: " + new String(cleartext));
   }
}