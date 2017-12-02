package org.yang.services.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.StringTokenizer;
import org.yang.util.ExceptionBroadcast;
/**
 * Title:        Siteware Enterprise
 * Description:  All the constants.
 * Apr 2001
 * Copyright:    Copyright (c) 2000
 * Company:      ScreamingMedia
 * @author Lei Liu
 * @version 1.0
 */

public class Config
{
   private static Object lock = new Object();
   private static Config myInstance = null;

   private String PROPFILENAME   = "SWPROP.txt";
   private String CONFIGFILENAME = "SWConfig";

   // Default database setting.
   public String DBTYPE   = "Not define yet";
   public String DATADB   = "Not define yet";
   public String SYSTEMDB = "Not define yet";

   public String serverID = "*";
   public boolean isPrimary = false;

   public final String DefaultWORKDIR = "/swdir";

   public Properties prop       = null;
   public Properties configProp = null;

   public String DT_BLOB      = null;
   public String DT_CLOB      = null;
   public String DT_TEXT      = null;
   public String DT_VARCHAR   = null;
   public String DT_TIMESTAMP = null;
   public String DT_INTEGER   = null;
   public String DT_TIME      = null;
   public String DT_DATE      = null;
   public String DT_DOUBLE    = null;

   public String workdir = null;
   public String getWORKDIR() { return workdir; }

   private Config(String configFileName)
   {
      prop = new Properties();
      load(configFileName);
   }

   public static Config getInstance(String configFileName)
   {
      if(null==myInstance)
      {
         synchronized(lock)
         {
            if(null==myInstance)
            {
                myInstance = new Config(configFileName);
            }
         }
      }

      return myInstance;
   }

   public static Config getInstance()
   {
      return getInstance(null);
   }

   public void load(String configFileName)
   {
      if(null!=configFileName)
      {
         PROPFILENAME   = configFileName + ".properties";
         CONFIGFILENAME = configFileName + ".config";
      }

      try
      {
         //&System.out.println("[Config::load] Loading config file: "+PROPFILENAME);
         InputStream ins = Config.class.getClassLoader().getResourceAsStream(PROPFILENAME);

         java.net.URL abcurl = Config.class.getClassLoader().getResource(PROPFILENAME);
         //&System.out.println("[Config::load] config file url: "+abcurl);

         if (ins!=null)
         {
            prop = readProperties(ins);

            DBTYPE   = prop.getProperty("DBTYPE", DBTYPE);
            SYSTEMDB = prop.getProperty("SYSTEMDB", SYSTEMDB);
            DATADB   = prop.getProperty("DATADB", DATADB);

            DT_BLOB      = prop.getProperty(DBTYPE+"BLOB");
            DT_CLOB      = prop.getProperty(DBTYPE+"CLOB");
            DT_TEXT      = prop.getProperty(DBTYPE+"TEXT");
            DT_VARCHAR   = prop.getProperty(DBTYPE+"VARCHAR");
            DT_TIMESTAMP = prop.getProperty(DBTYPE+"TIMESTAMP");
            DT_INTEGER   = prop.getProperty(DBTYPE+"INTEGER");
            DT_TIME      = prop.getProperty(DBTYPE+"TIME");
            DT_DATE      = prop.getProperty(DBTYPE+"DATE");
            DT_DOUBLE    = prop.getProperty(DBTYPE+"DOUBLE");

            // load working directory information
            workdir = prop.getProperty("WORKDIR");
            if(null!=workdir)
            {
               workdir = (new File(workdir)).getCanonicalPath();
               workdir = removeSeparator(workdir);
            }
         }
         else
         {
            System.out.println("[Config::load] Error reading SWProp.txt");
         }
      }
      catch (Exception e)
      {
         //com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
         System.out.println("[Config::load] Exception happen while loading confs from SWPROP.txt: " + e.getMessage());
      }

      try
      {
         //&System.out.println("[Config::load] Loading config file " + CONFIGFILENAME);
         InputStream ins = Config.class.getClassLoader().getResourceAsStream(CONFIGFILENAME);
         if (ins != null)
         {
	        //&System.out.println("[Config::load] SWConfig loaded.");
            configProp = readProperties(ins);

            configProp.list(System.out);
            DBTYPE   = configProp.getProperty("DBTYPE", DBTYPE);
            SYSTEMDB = configProp.getProperty("SYSTEMDB", SYSTEMDB);
            DATADB   = configProp.getProperty("DATADB", DATADB);
              
            // load working directory information
            workdir = configProp.getProperty("WORKDIR", workdir);
            workdir = (new File(workdir)).getCanonicalPath();
            workdir = removeSeparator(workdir);

            String versionControlStr = configProp.getProperty("VERSIONCONTROL");

            serverID = configProp.getProperty("SERVERID");
            isPrimary = true;
         }
         else
            System.out.println("[Config::load] Can not load SWConfig file...Error.");
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
      }

      //Make sure to create the working dir.
      try
      {
         if (workdir == null) workdir = DefaultWORKDIR;

         File file = new File(workdir);
         file.mkdirs();

         if (file.exists() == false)
         {
            System.out.println("[Config::load] *Fatal Error!!* Can not create WORKDIR: " + workdir);
            workdir = System.getProperty("user.home");
         }

         workdir = removeSeparator(workdir);
         //&System.out.println("[Config::load] Working Dir is set to "+workdir);
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   private String removeSeparator(String workdir)
   {
      if (workdir == null) return workdir;
      if (workdir.endsWith("/") || workdir.endsWith("\\"))
            workdir = workdir.substring(0, workdir.length()-1);
      return workdir;
   }

   private Properties readProperties(InputStream in)
   {
      try
      {
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         String line = null;
         Properties p = new Properties();
         while ((line = br.readLine())!=null)
         {
            line = line.trim();
            if (line.startsWith("#")) continue;
            if (line.equals("")) continue;

            try
            {
               StringTokenizer st = new StringTokenizer(line, "=:", false);
               String part1 = st.nextToken();
               String part2 = line.substring(part1.length()+1);

               if ((part1 != null) && (part2!=null) )
               p.setProperty(part1.trim(), part2.trim());
            }
            catch(Exception e) { }
         }

         return p;
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
      }
      return null;
   }
}