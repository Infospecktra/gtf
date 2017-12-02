package org.yang.web.applicationContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
public class PropUtil extends Properties
{
   static final long serialVersionUID = 4711296382979764904L;

   public PropUtil() { super(); }

   public Collection getValues(String key)
   {
      String msg = getProperty(key);
	  if (msg==null)
        return null;

      StringTokenizer st = new StringTokenizer(msg,",");

      ArrayList rslt = new ArrayList();
      while (st.hasMoreTokens())
      {
         rslt.add(st.nextToken());
      }
      return rslt;
   }
}