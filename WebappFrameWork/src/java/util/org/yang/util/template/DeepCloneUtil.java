package org.yang.util.template;

/**
 * Title:        Siteware Enterprise
 * Description:
 * Copyright:    Copyright (c) 2000
 * @author Lei Liu
 * @version 1.0
 */

import java.io.*;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestDeepCloneUtil 
 */
public class DeepCloneUtil {

    public static Object deepClone(Serializable obj ) {
      try {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);

        byte [] array = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object ret = ois.readObject();

        return ret;
      } catch (Exception e) {}
      return null;
    }

}