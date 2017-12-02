package org.yang.web.applicationContainer;

import java.util.HashMap;
import java.io.Serializable;

public class MessageManager implements Serializable
{
   static final long serialVersionUID = -7671447514644416751L;

   public static final String MESSAGE_RESOURCE = "MessageResource";
   public static final String VERSION_INFO = "VersionInfo";
   public static final String SYSTEM = "System";
   public static final String ENGLISH = "English";

   private HashMap msgMap = new HashMap();

   /**  Constructor  */
   public MessageManager() {}

   public MessageManager(String type, String lang) 
   {
      getProp(type + lang);
   }

   public PropUtil getLangProp(String lang) 
   {
      return getProp(this.MESSAGE_RESOURCE + lang);
   }

   public PropUtil getVersionProp(String lang) 
   {
      return getProp(this.VERSION_INFO + lang);
   }

   public PropUtil getProp(String propKey)
   {
      if(!msgMap.containsKey(propKey))
      {
         synchronized(this)
         {
      	    if(!msgMap.containsKey(propKey))
            {
               try
               {
                  msgMap.put(propKey, loadProperties(propKey));
               }
               catch(Exception e)
               {
                  System.out.println("[MessageManager::PropUtil] Error loading message properties[" + propKey + "]");
               }
            }
         }
      }

      return (PropUtil)msgMap.get(propKey);
   }

   private PropUtil loadProperties(String propKey) throws Exception
   {
      String propFile = propKey + ".properties";
      PropUtil p = new PropUtil();
      p.load (this.getClass().getClassLoader().getResourceAsStream(propFile));
      return p;
   }
}