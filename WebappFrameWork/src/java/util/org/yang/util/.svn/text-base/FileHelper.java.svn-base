package org.yang.util;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FileHelper
{

   /********************************
    *     public level method      *
    ********************************/

   /**
    * Read text file of any encoding.
    * Note: Java support encoding: UTF-8, ISO-8859-1, US-ASCII
    */
   public static String readFileAsString(String filename, String encoding)
   {
      try
      {
         if((new File(filename)).exists())
            return readInputStreamAsString(new FileInputStream(filename), encoding);
      }
      catch(Exception e)
      {}
      return null;
   }

   /**
    * Read text file of any encoding.
    * Note: Java support encoding: UTF-8, ISO-8859-1, US-ASCII
    */
   public static String readInputStreamAsString(InputStream in, String encoding)
   {
      BufferedReader br = null;
      StringBuffer file = new StringBuffer();

      try
      {
         InputStreamReader isr = null;
         if ((encoding == null) || (encoding.equalsIgnoreCase("Default")))
            isr = new InputStreamReader(in, encoding);
         else
            isr = new InputStreamReader(in);

         br = new BufferedReader(isr);
         String temp = null;
         while((temp=br.readLine())!=null)
         {
            file.append(temp).append(System.getProperty("line.separator", "\n"));
         }

         br.close();
      }
      catch(Exception e)
      {
         return null;
      }

      return file.toString();
   }

   public static int readInputStreamAsBinary(byte[] result, InputStream in, int startpos)
   {
      BufferedInputStream bis = null;
      int x = 0;
      try {
         bis = new BufferedInputStream (in);
         bis.skip(startpos);
         x = bis.read(result, 0, result.length);
      } catch (Exception e) {
         ExceptionBroadcast.print(e);
         return 0;
      }
      return x;
   }

   public static void fileCopy(String oldfile, String newfile)
   {
      try
      {
         int p= 0;
         byte []buf= new byte[1024];
         FileInputStream in= new FileInputStream(oldfile);
         FileOutputStream out= new FileOutputStream(newfile);
         while( (p= in.read(buf))!=-1 )
         {
            out.write(buf,0,p);
         }
         in.close();
         out.close();
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
      }
   }

   public static void inutStreamToFile(InputStream is)
   {
      inputStreamToFile(is, "test.txt");
   }

   public static void inputStreamToFile(InputStream is, String filename)
   {
      inputStreamToFile(is,filename,false);
   }

   public static void inputStreamToFile(InputStream is, String filename, boolean append)
   {
       FileOutputStream fos = null;
       try
       {
          fos = new FileOutputStream(filename,append);

          byte[] buffer = new byte[10240];

          int chunksize = 0;
          while((chunksize=is.read(buffer))!=-1)
          {
             fos.write(buffer, 0, chunksize);
          }
       }
       catch(Exception e)
       {
          ExceptionBroadcast.print(e);
       }
       finally
       {
          try {fos.close();} catch (IOException e) {}

       }
   }

   public static void deleteFilesUnder(File file)
   {
      File[] subfiles = null;
      if(file.isDirectory()&&
         null!=(subfiles=file.listFiles()))
      {
         for(int i=0; i<subfiles.length; i++)
         {
            deleteFilesUnder(subfiles[i]);
         }
      }
      file.delete();
   }

   public static void main(String[] argc) {}
}