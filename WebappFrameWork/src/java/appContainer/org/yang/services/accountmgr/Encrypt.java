package org.yang.services.accountmgr;

import java.security.MessageDigest;
/**
 * Title:        Login<p>
 * Description:  Provides tools to do password encyption<p>
 * Copyright:    Copyright (c) Lei Liu<p>
 * Company:      SCRM<p>
 * @author Lei Liu
 * @version 1.0
 */

/**
 * This class provides some static methods that can do some kinds of encryption.
 * @testcase org.test.org.yang.services.accountmgr.TestEncrypt
 */
public class Encrypt {

    /**
     * This method does one way encryption using MD5 Message Digest algorithm.
     * The output byte array is then converted to a Hex String that can be
     * easily stored in file. <p>
     *
     * The initial purpose of this method is to encrypt password and store the
     * encrypted result as clear text in file or database. To verify a
     * user-entered password, we just encrypt this password and compare with
     * the stored (also encrypted) password. Because the MD5 is said to be
     * "one-way encryption", which means it's very hard to go from the encrypted
     * message to the original one, even when somebody sees the stored encrypted
     * password, he can not use that password to enter the system.<p>
     *
     * Unix system also use this scheme to store user passwords.
     */
    public static String encrypt( String s) {
	try {
            if ( s==null) return "";
            if ( s.equals("")) return "";
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] mydigest = md.digest(s.getBytes());
	   // System.out.println("digest = " + toHexString(mydigest));
            return (toHexString(mydigest));
	}
	catch (Exception e) {
	    System.out.println(e);
            return null;
	}
    }

    /*
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    static private void byte2hex(byte b, StringBuffer buf) {
	char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	int high = ((b & 0xf0) >> 4);
	int low = (b & 0x0f);
	buf.append(hexChars[high]);
	buf.append(hexChars[low]);
   }

    /*
     * Converts a byte array to hex string
     */
    static private String toHexString(byte[] block) {
	StringBuffer buf = new StringBuffer();

	int len = block.length;

	for (int i = 0; i < len; i++) {
	    byte2hex(block[i], buf);
	}
	return buf.toString();
    }
}


