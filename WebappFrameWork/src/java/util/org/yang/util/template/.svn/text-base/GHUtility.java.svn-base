package org.yang.util.template;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestGHUtility 
 */
public class GHUtility
{

   /********************************
    *     public level method      *
    ********************************/


   /**
    * Get full filename for a file in the Siteware Working Directory.
    */
   public static String getFullNameFromWD(String filename, String WorkDir){
        String root = WorkDir;
        if ( (root.endsWith("/")) || (root.endsWith("\\")) == false) root+="/";
        String fullname;
        if (filename.startsWith("/") || filename.startsWith("\\")) fullname = filename;
        else fullname = root + filename;
        //&System.out.println("[SMUtility::loadFromClassPath] Full filename = "+fullname);
        return fullname;
   }

   //----< constructor >--------------------------------------------------------
   /**
    * Takes a String and some token and parses the string
    * returning its Tokens as a string array.
    *
    * @see
    * @exception
    */
   //
   public static String[] splitByToken (String source, String separator)
   {
      StringTokenizer token = new StringTokenizer (source,separator);
      String[] temp = new String [token.countTokens()];
      int counter = 0 ;
      while (token.hasMoreTokens())
      {
         temp[counter] = token.nextToken().trim();
         counter ++;
      }
      token = null ;
      return temp ;
   }
   //

   //----< constructor >--------------------------------------------------------
   /**
    * Takes a String and some token and parses the string
    * returning its Tokens as a string array.
    *
    * @see
    * @exception
    */
   //
   public static String[] splitByToken (String source, String separator, boolean countEmptyToken)
   {
      int sourceLen = source.length();
      int separatorLen = separator.length();
      ArrayList tokens = new ArrayList();
      StringBuffer temp = new StringBuffer();

      for(int i=0; i<sourceLen; i++)
      {
         for(int j=0; j<separatorLen; j++)
      	 {
      	    if(source.charAt(i)==separator.charAt(j))
      	    {
               if(temp.length()>0)
                  tokens.add(temp.toString());
               else if(countEmptyToken)
                  tokens.add(temp.toString());

      	       temp.delete(0, temp.length());
               if(i==sourceLen-1)
               {
                  if(temp.length()>0)
                     tokens.add(temp.toString());
                  else if(countEmptyToken)
                     tokens.add(temp.toString());
               }
      	       break;
      	    }
            else
            {
               temp.append(source.charAt(i));

               if(i==sourceLen-1)
               {
                  if(temp.length()>0)
                     tokens.add(temp.toString());
                  else if(countEmptyToken)
                     tokens.add(temp.toString());
               }
            }
      	 }
      }

      int size = tokens.size();
      String[] result = new String[size];
      for(int k=0; k<size; k++)
      {
         result[k]=(String)tokens.get(k);
      }

      return result;
   }
   //

   //----< constructor >--------------------------------------------------------
   /**
    * Will replace the first occurance of oldStr in the larger
    * string content with newStr. If the boolean replaceAll is
    * set to "true" it will replace all occurences of oldStr
    * with newStr.
    *
    * @see
    * @exception
    */
   //
   public static String replace(String  content,
                                String  oldStr,
                                String  newStr,
                                boolean replaceAll)
   {
      try{
      String newContent= new String();
      int p=-1;
      while( (p=content.indexOf(oldStr))!= -1 )
      {
         newContent= newContent+ content.substring(0, p);
         content= content.substring(content.indexOf(oldStr)+oldStr.length(),
                                    content.length());
         newContent= newContent+ newStr;
         if(!replaceAll) break;
      }
      newContent= newContent+ content;
      return newContent;
      }
      catch(Exception e)
      {
         return oldStr;
      }
   }
   //

   public static String getDefaultEncoding() {
	return System.getProperty("file.encoding");
   }

   public static String readFileAsString(String filename) {
      return readFileAsString(filename, null);
   }

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
      if ((encoding == null) || (encoding.equalsIgnoreCase("Default")))
      	  encoding = getDefaultEncoding();

      BufferedReader br = null;

      StringBuffer file = new StringBuffer();

      try
      {
	InputStreamReader isr = new InputStreamReader(in, encoding);


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
      }
   }

   public static String[] VtoS(Vector vec)
   {
      String[] temps = new String[vec.size()];

      for(int i=0; i<vec.size(); i++)
         temps[i] = (String) vec.elementAt(i);
      return temps;
   }

   public static Vector StoV(String[] str)
   {
      Vector tempv = new Vector();

      for(int i=0; i<str.length; i++)
         tempv.addElement(str[i]);
      return tempv;
   }

   public static void arraySort(String[] str)
   {
      int counter = -1;
      while(counter != 0)
      {
         counter = 0;

         for(int i=0; i<str.length-1; i++)
         {
            if(str[i].compareTo(str[i+1])>0)
            {
               String temps = str[i];
               str[i] = str[i+1];
               str[i+1] = temps;
               counter++;
            }
         }
         //System.out.println(counter);
      }
   }

   public static void strVectorSort(Vector vec)
   {
      int counter = -1;
      while(counter != 0)
      {
         counter = 0;

         for(int i=0; i<vec.size()-1; i++)
         {
            String elmi  = (String)vec.elementAt(i);
            String elmii = (String)vec.elementAt(i+1);

            if(elmi.compareTo(elmii)>0)
            {
               String temps = elmi;
               vec.setElementAt(elmii,i);
               vec.setElementAt(temps,i+1);
               counter++;
            }
         }
      }
   }

   public static void inutStreamToFile(InputStream is)
   {
      inputStreamToFile(is, "test.txt");
   }

   public static void inputStreamToFile(InputStream is, String filename) {
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

       }
       finally
       {
          try {fos.close();} catch (IOException e) {}

       }
   }

}