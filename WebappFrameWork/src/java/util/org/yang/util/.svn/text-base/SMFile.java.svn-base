/**
 *Title:       SMFile
 */

package org.yang.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 *@version:
 *Copyright:   Copyright (c) 2000
 *@author:     Steven Yang (original searchByFilename method from class FSEngine), Hui Ouyang
 *Company:     Screaming Media
 *Description: utility class for file and directory handling
 *              created on April 2, 2000
 *              modified on May 01, 2000 to support filename filter and make it a utility
 *              modified on June 16, 2000 to support file filter and properties list
 *              modified on June 20, 2000 to support the bListDirectChildrenOnly flag
 */
public class SMFile
{

/**
 * If dir is not ended by FilenameSeparator, make it so.
 */
  public static String appendSeparatorIfNecessary(String dir) {
        if (dir == null) return null;
        if (dir.endsWith("\\") || dir.endsWith("/")) return dir;
        else  return ( dir + "/");
  }

  /**
   * remove the ending filenameSeparators.
   */
  public static String trimEndingSeparator(String dir) {
        while (dir.endsWith("\\") || dir.endsWith("/")) dir = dir.substring(0, dir.length()-1);
        return dir;
  }

   /**
    * Escape the special chars in filename.
    */
   public static String escapeFilename(String name) {
        name = name.replace('/','_');
        name = name.replace('\\','_');
        name = name.replace(':','_');
        name = name.replace('*','_');
        name = name.replace('?','_');
        name = name.replace('"','_');
        name = name.replace('<','_');
        name = name.replace('>','_');
        name = name.replace('|','_');
        name = name.replace(' ','_');
        return name;
  }

  /** absolute path for the root directory or file */
  public String sroot      = null;
  /** File object for the root directory or file */
  public File   froot      = null;
  /** vector contains all the files and subdirectories within this root directory */
  public Vector vfiledirs  = null;
  /** vector contains all the subdirectories within this root directory */
  public Vector vdirs      = null;
  /** vector contains all the files within this root directory */
  public Vector vfiles     = null;
  /** filter to filter some files and directorys */
  public SMFileFilter smfilter;
  /** standard path separator */
  public static char PATHSEPERATOR = '.';

  public static String SPATHFORFILTER = null;

  /** properties contains all the subdirectories within this root directory */
  public Properties propdirs      = new Properties();
  /** properties contains all the files within this root directory */
  public Properties propfiles     = new Properties();

  /*separator for the attritue */
  public static String sseparator = "|";

  /** whether only list the directories and files directly belong to the root directory */
  public boolean bListDirectChildrenOnly = false;


/**     this is used to choose mode
  */
    private  static   Set       binary      = new HashSet();
    static String [] asciiEntries  ={"html","htm", "txt", "xml","java",
                "c",  "cpp", "csv", "xsl", "css", "text", "js", "bat","ini","h","hpp",
                "pl","pm","log","src","php","php3","inf","nfo","wml","smml","asp" };

  /** end of line */
  public static String ENDLINE = "\n";
  static{
    if (File.separator.equals("/"))
      ENDLINE = "\n";
    else
      ENDLINE = "\r\n";
  }

  /** default construct */
  public SMFile( )
  {
    froot     = new File(File.separator);
    sroot     = froot.getAbsolutePath();
    vfiledirs  = new Vector();
	  vdirs     = new Vector();
	  vfiles    = new Vector();
    smfilter  = null;

    SPATHFORFILTER = sroot;

    propdirs      = new Properties();
    propfiles      = new Properties();

    searchByFilename(sroot, smfilter);
  }

  /** construct the class from the specified path
   *@exception:
   *@param
   *      String spath: the full path string
   *      SMFileFilter smfilter: filter to filte the directory
   *@return
   */
  public SMFile(String spath, SMFileFilter smfilter)
  {
    froot     = new File(spath);
    sroot     = froot.getAbsolutePath();
    vfiledirs  = new Vector();
	  vdirs     = new Vector();
	  vfiles    = new Vector();
    this.smfilter = smfilter;
    propdirs      = new Properties();
    propfiles      = new Properties();
    SPATHFORFILTER = sroot;

    searchByFilename(sroot, smfilter);

  }

  /** construct the class from the specified path
   *@exception:
   *@param
   *      String spath: the full path string
   *      SMFileFilter smfilter: filter to filte the directory
   *@return
   */
  public SMFile(String spath, SMFileFilter smfilter, boolean bListDirectChildrenOnly)
  {
    froot     = new File(spath);
    sroot     = froot.getAbsolutePath();
    vfiledirs  = new Vector();
	  vdirs     = new Vector();
	  vfiles    = new Vector();
    this.smfilter = smfilter;
    propdirs      = new Properties();
    propfiles      = new Properties();
    SPATHFORFILTER = sroot;
    this.bListDirectChildrenOnly = bListDirectChildrenOnly;
    searchByFilename(sroot, smfilter);

  }

  /** construct the class from the specified path
   *@exception:
   *@param
   *      String spath: the full path string
   *@return
   */
  public SMFile(String spath )
  {
    froot     = new File(spath);
    sroot     = froot.getAbsolutePath();
    vfiledirs  = new Vector();
	  vdirs     = new Vector();
	  vfiles    = new Vector();
    smfilter  = null;

    propdirs      = new Properties();
    propfiles      = new Properties();
    searchByFilename(sroot, smfilter);

  }


  /** construct the class for select content creation mode
   * added by Hui Zhang
   *@exception:
   *@param
   *String spath: the full path string
   *@return
   */
  public SMFile( int mode )
  {
  }


  public void finalize()
  {
    froot     = null;
    sroot     = null;
    vfiledirs = null;
	  vdirs     = null;
  }

  /** search to get the file and directory list based on the filename filter
   *if the filter is null get all of the file and directory list
   *@exception:
   *@param
   *      String sfullpath: the target path to be listed
   *      SMFileFilter smfilter: filter to filte the directory
   *@return: false if error
   */
  private boolean searchByFilename(String sfullpath, SMFileFilter smfilter)
  {
    if(!froot.exists()) return false;  //root directory is not there

    File fsub = new File(sfullpath);

    String snewpath;
    File   fnewpath;
    String[] filelists;


    /** get the all the file lists in the current directory */
    if (smfilter == null)
      filelists = fsub.list();
    else
    {
      filelists = fsub.list(smfilter);  //need the method from filenameFilter
     //if (smfilter.imatchpattern == smfilter.TIMESTAMPMATCH)
        //filelists = fsub.list(new FileFilter(boolean accept(return ))
    }


    String sattribute = null;
    long ldt = 0;
    long lsize = 0;
    String saccess = null;
    for(int i = 0; i < filelists.length; i++)
    {
      snewpath = sfullpath+File.separator+filelists[i];
      vfiledirs.addElement(snewpath);
      fnewpath = new File(snewpath);

		  if (fnewpath.isFile())
		  {
			  vfiles.addElement(snewpath);
        ldt = fnewpath.lastModified();
        lsize = fnewpath.length();
        saccess = "F";
        if (fnewpath.isHidden()) saccess = saccess + "H";
        else saccess = saccess + "-";
        if (fnewpath. canRead ()) saccess = saccess +"R";
        else saccess = saccess + "-";
        if (fnewpath.canWrite()) saccess = saccess +"W";
        else saccess = saccess + "-";

        sattribute = "" + lsize + sseparator + ldt + sseparator + saccess;

        propfiles.put(snewpath, sattribute);
		  }


    } //end of for:

    /** get the all the subdirectories in the current directory */
    filelists = fsub.list();

    for(int i = 0; i < filelists.length; i++)
    {
      snewpath = sfullpath+File.separator+filelists[i];
      fnewpath = new File(snewpath);

      if(fnewpath.isDirectory())
      {
        vdirs.addElement(snewpath);
        ldt = fnewpath.lastModified();
        lsize = fnewpath.length();
        saccess = "D";
        if (fnewpath.isHidden()) saccess = saccess + "H";
        else saccess = saccess + "-";
        if (fnewpath. canRead ()) saccess = saccess +"R";
        else saccess = saccess + "-";
        if (fnewpath.canWrite()) saccess = saccess +"W";
        else saccess = saccess + "-";
        sattribute = "" + lsize + sseparator + ldt + sseparator + saccess;

        propdirs.put(snewpath, sattribute);

        if (!bListDirectChildrenOnly)
          searchByFilename(snewpath, smfilter);
      }

    } //end of for:



	  return true;
  }

  /** get all the subdirectories within the root directory
   *@exception:
   *@param
   *@return: directory name array within the root directory
   */
  public String[] getDirList()
  {

    return getDirList(null);
  }

  /** get all the files within the root directory and its subdirectories
   *@exception:
   *@param
   *@return: file name array within the root directory after filting
   */
  public String[] getFileList()
  {
    return getFileList(null);
  }

  /** get all the subdirectories within the root directory based on the
   *  new filter, the SMFile object may change after this calling
   *@exception:
   *@param
   *@return: directory name array within the root directory after filting
   */
  public String[] getDirList(SMFileFilter smfilter)
  {

    this.smfilter = smfilter;
    if (smfilter != null)
    {
      if (!searchByFilename(sroot, smfilter))
      {
        return null;
      }
    }
    String[] sdirs = new String[vdirs.size()];
    for (int i = 0; i < vdirs.size(); i++)
    {
      sdirs[i] =   vdirs.elementAt(i).toString();
    }
    return sdirs;
  }

  /** get all the files within the root directory and its subdirectories based on the
   *       new filter, the SMFile object may change after this calling
   *@exception:
   *@param
   *@return: file name array within the root directory after filting
   */
  public String[] getFileList(SMFileFilter smfilter)
  {
    this.smfilter = smfilter;
    if (smfilter != null)
    {
      if (!searchByFilename(sroot, smfilter))
      {
        return null;
      }
    }
    String[] sfiles = new String[vfiles.size()];
    for (int i = 0; i < vfiles.size(); i++)
    {
      sfiles[i] =   vfiles.elementAt(i).toString();
    }
    return sfiles;

  }

   public static boolean fileMove(String oldfile, String newfile)
   {
      if(fileCopy(oldfile, newfile))
      {
         return (new File(oldfile)).delete();
      }
      else
      {
         return false;
      }
   }

   public static boolean fileCopy(String oldfile, String newfile)
   {
      try
      {
         // check target file's existing and prepare buffer
         if(!(new File(oldfile)).exists())
            return false;

         // check destination is exist or not
         int pos1 = newfile.lastIndexOf("/");
         int pos2 = newfile.lastIndexOf("\\");
         int pos = pos1>pos2?pos1:pos2;
         File path = new File(newfile.substring(0,pos));
         if(!path.exists())
         {
            path.mkdirs();
         }

         FileInputStream in= new FileInputStream(oldfile);
         FileOutputStream out= new FileOutputStream(newfile);

         int len    = -1;
         int offset = 0;
         byte []buf= new byte[4096];
         while( (len = in.read(buf))!=-1 )
         {
            out.write(buf, offset, len);
         }
         in.close();
         out.close();
      }
      catch(Exception e)
      {
         ExceptionBroadcast.print(e);
         return false;
      }

      return true;
   }

  /** copy file
   *@exception:
   *@param
   *      String sfilesource: the original source file name
   *      String sfiletarget: the destination file name
   *@return: true if successful
   */
  public static boolean copyFile(String sfilesource, String sfiletarget)
  {
    BufferedReader insource = null;
    PrintWriter pwtarget = null;
    try{
      //check whether the source file exists
      File fsource = new File(sfilesource);

      if (!fsource.exists())
      {
        return false;
      }

      /** if the target file does not exist, create it, otherwise just overwrite the file */
      File ftarget = new File(sfiletarget);
      if (!ftarget.exists())
      {
        ftarget.createNewFile();
      }
      String sline;
      insource = new BufferedReader(new FileReader(fsource));
      sline = insource.readLine(); //read the first line
      pwtarget = new PrintWriter(new BufferedWriter(new FileWriter(sfiletarget)));

      /** read each line from source file and export into target file */
      while(sline != null)
      {
        pwtarget.println(sline);

        sline = insource.readLine();
      }

      if (insource != null) insource.close();
      if (pwtarget != null) pwtarget.close();

    }
    catch(IOException ioe){
      ioe.printStackTrace();
      return false;

    }
    finally{
      try{
       if (insource != null) insource.close();
       if (pwtarget != null) pwtarget.close();
      }
      catch (Exception e){};

    }
    return true;
  }

  /** write the message to the end of the file
   *  If the file does not exist, create it.
   *@exception:
   *@param
   *      String sfilename: the file name
   *      String smessage: the message will be appended to the file
   *@return: true if successful
   */
  public static boolean appendToFile(String sfilename, String smessage)
  {
    File fp = null;
    PrintWriter pw = null;
    try{
      /** check whether the file exist, if not create it */
      fp = new File(sfilename);
      if (!fp.exists())
      {
        boolean bcreated = fp.createNewFile();
      }

      pw = new PrintWriter(new BufferedWriter(new FileWriter(sfilename, true)));
      pw.println(smessage);

      if ( pw != null) pw.close();


    } //end of try
    catch(Exception e){
      System.err.println("Can not write the file!"+ e);
      ExceptionBroadcast.print(e);
      return false;
    }
    finally{
      if ( pw != null) pw.close();

    }
    return true;
  }
  /** write the messages to the end of the file
   *  If the file does not exist, create it.
   *@exception:
   *@param
   *      String sfilename: the file name
   *      String[] smessages: the message will be appended to the file
   *@return: true if successful
   */
  public static boolean appendToFile(String sfilename, String[] smessages)
  {
    try{
      int ilength = smessages.length;
      for (int i = 0; i < ilength; i++)
      {
        appendToFile(sfilename, smessages[i]);
      }

    } //end of try
    catch(Exception e){
      System.err.println("Can not write the file!"+ e);
      ExceptionBroadcast.print(e);
      return false;
    }
    finally{

    }
    return true;
  }
  /** write the messages to the end of the file
   *  If the file does not exist, create it.
   *@exception:
   *@param
   *      String sfilename: the file name
   *      Properties prop: the key and value will be appended to the file
   *@return: true if successful
   */
  public static boolean appendToFile(String sfilename, Properties prop)
  {
    try{
      Set keyset = prop.keySet();
      Object[] objkeys = keyset.toArray();
      int ilength = objkeys.length;
      String smessage;
      String skey, svalue;
      for (int i = 0; i < ilength; i++)
      {
        if (objkeys[i] == null) continue;
        skey = objkeys[i].toString();
        svalue = (String)prop.get(skey);
        smessage = skey + "=" + svalue;
        appendToFile(sfilename, smessage);
      }

    } //end of try
    catch(Exception e){
      System.err.println("Can not write the file!"+ e);
      ExceptionBroadcast.print(e);
      return false;
    }
    finally{


    }
    return true;
  }
  /** convert plantform dependent path into platform independent standard path
   *@exception:
   *@param
   *      String sospath: OS native path, for NT, it may contain "c:\". It can also
   *      be relative path
   *@return: standard path, replace all the "\" or "/" with ".", replace original "."
   *         with "\.". For NT, "c:" is still remained. This should be a new String.
   */
  public static String OSPathToStandardPath(String sospath)
  {
    if (sospath == null) return null;

    String sstandpath = "";
    int ipos;

    //add '\'  to the front of the original '.' within the filename
    ipos = sospath.indexOf(PATHSEPERATOR);
    while(ipos!= -1)
    {

      if (ipos == 0 ) //for the case "..\temp\temp.txt
      {
        sstandpath = sstandpath + "\\" + PATHSEPERATOR;
        sospath = sospath.substring(ipos + 1);
        ipos = sospath.indexOf(PATHSEPERATOR);
        continue;
      }

      //check whether this dot is the path separator
      if (sospath.charAt(ipos - 1) == '\\')
      { // keep the '\\' and dot
        sstandpath = sstandpath + sospath.substring(0, ipos + 1 );
        sospath = sospath.substring(ipos + 1);
      }
      else
      {
        sstandpath = sstandpath + sospath.substring(0, ipos) + "\\" + PATHSEPERATOR;
        sospath = sospath.substring(ipos + 1);

      }

      ipos = sospath.indexOf(PATHSEPERATOR);
    }//end of while
    sstandpath = sstandpath + sospath;

    /** now convert '\' or '/' to dot */
    sospath = sstandpath;
    sstandpath = "";

    /** replace '\' or '/' with '.' */
    ipos = sospath.indexOf(File.separator);
    while(ipos!= -1)
    {
      /** whether it is the end of the string */
      if (ipos == sospath.length() - 1)
      {
        sstandpath = sstandpath + sospath.substring(0, ipos) + PATHSEPERATOR;
        sospath = "";
        break;
      }


      if ((File.separatorChar == '\\')&&( sospath.charAt(ipos + 1) == PATHSEPERATOR))
      {
        sstandpath = sstandpath + sospath.substring(0, ipos + 2);
        if (ipos == sospath.length() - 2 )
        {
          sospath ="";
          break;
        }
        sospath = sospath.substring(ipos + 2);
        ipos = sospath.indexOf(File.separator);
        continue;

      }
      sstandpath = sstandpath + sospath.substring(0, ipos ) + PATHSEPERATOR;
      sospath = sospath.substring(ipos + 1);


      ipos = sospath.indexOf(File.separator);
    }//end of while


    sstandpath = sstandpath + sospath;

    return sstandpath;


  }


  /** convert platform independent standard path into  plantform dependent path
   *@exception:
   *@param: standard path, replace all the "\" or "/" with ".", replace original "."
   *         with "\.". For NT, "c:" is still remained.
   *@return
   *      String sospath: OS native path, for NT, it may contain "c:\". It can also
   *      be relative path
   */
  public static String standardPathToOSPath(String sstandpath)
  {
    if (sstandpath == null) return null;

    String sospath = "";
    int ipos;

    /** replace '.' with '\' or '/' */
    ipos = sstandpath.indexOf(PATHSEPERATOR);
    while(ipos!= -1)
    {
      if (ipos == 0 ) //for the case ".temp.temp\.txt
      {
        sospath =  sospath + File.separator;
        sstandpath = sstandpath.substring(ipos + 1);
        ipos = sstandpath.indexOf(PATHSEPERATOR);
        continue;
      }

      /** check whether this dot is the path separator */
      if (sstandpath.charAt(ipos - 1) == '\\')
      { //skip the '\\', keep the dot
        sospath = sospath + sstandpath.substring(0, ipos - 1 ) + PATHSEPERATOR;
        sstandpath = sstandpath.substring(ipos + 1);
      }
      else
      { //replace the dot with OS seperator
        sospath = sospath + sstandpath.substring(0, ipos) + File.separator;
        sstandpath = sstandpath.substring(ipos + 1);
      }
      ipos = sstandpath.indexOf(PATHSEPERATOR);
    }//end of while


    sospath = sospath + sstandpath;

    return sospath;




  }

 /** reading the lines from the file into string array,
   *@exception:
   *@param
   *      String sfilename: source file name
   *@return: string array,if file not exists, null will
   *  will be returnded; if the file is empty, null will be returned
   */
  public static String[] parseLinesFromFile(String sfilename)
  {
    Vector vlines = new Vector();
    File fsource = new File(sfilename);
    String slinesource;
    BufferedReader insource = null;
    if (!fsource.exists())
    {
      //System.out.println(sfilename + " file does not exist!");
      return null;
    }
    /** parse line by line to compare  */
    try{
      insource = new BufferedReader(new FileReader(sfilename));
      slinesource = insource.readLine(); //read the first line


      /** put the lines to be matched into vector */
      while(slinesource != null)
      {
        vlines.addElement(slinesource);
        slinesource = insource.readLine(); //read the next line

      }//end of while
    }
    catch(Exception e){
      System.err.println("Can not open or read the file " + sfilename);
      ExceptionBroadcast.print(e);

    }
    finally{
      try{
      if (insource != null) insource.close();
      }
      catch (Exception e){};

    }

    if (vlines == null) return null;

    String[] slines = new String[vlines.size()];
    int inum = vlines.size();
    for (int i = 0; i < inum; i++)
    {
      slines[i] =   vlines.elementAt(i).toString();
    }
    return slines;



  }

 /** reading the lines from the file into a string ,
   *@exception:
   *@param
   *      String sfilename: source file name
   *@return: string array,if file not exists, null will
   *  will be returnded; if the file is empty, null will be returned
   */
  public static String parseFromTextFile(String sfilename)
  {
    String[] slines = parseLinesFromFile(sfilename);
    if (slines == null) return null;
    int ilines = slines.length;
    StringBuffer sbuff = new StringBuffer();
    for (int i = 0; i < ilines; i ++)
    {
      sbuff.append(slines[i] + ENDLINE);
    }
    return new String(sbuff);
  }

/**
 * Read binary file.
 */
  public static int readBinaryFile(byte[] result, String sfilename, int startpos)
  {
    return readBinaryFile(result, new File(sfilename), startpos);
  }

   /**
    * Read binary file.
    */
  public static int readBinaryFile(byte[] result, File f, int startpos)
  {
    BufferedInputStream bis = null;
    int x = 0;
    try {
        bis = new BufferedInputStream (new FileInputStream(f));
        bis.skip(startpos);
        x = bis.read(result, 0, result.length);
    } catch (Exception e) {
       ExceptionBroadcast.print(e);
        return 0;
    }
    return x;
  }

   /**
    * Read binary file.
    */
  public static byte[] readBinaryFile(File f)
  {
//try
//{
//System.out.println("---------------------------------> readBinaryFile file=" + f.getCanonicalPath());
//}catch(Exception e){}

    if(!f.exists())
       return new byte[0];

    int len = (int)f.length();
    byte[] temp = new byte[len];
    InputStream bis = null;
//System.out.println("---------------------------------> readBinaryFile");
    try {
        bis = new FileInputStream(f);
        int r = 0;
        int offset = 0;
//System.out.println("---------------------------------> readBinaryFile");
        while(offset<len &&
              -1!=(r=bis.read(temp, offset, len-offset)))
        {
//System.out.println("---------------------------------> readBinaryFile, r=" + r);
           offset = offset + r;
        }
    } catch (Exception e) {
       ExceptionBroadcast.print(e);
    } finally {
    	try{
    	   bis.close();
    	} catch(Exception e){}
    }
    return temp;
  }

  /**
   * Write binary file.
   */
  public static boolean writeBinaryFile(String sfilename, byte[] content, boolean append)
  {
    if (content == null) return false;

    File fsource = new File(sfilename);

    BufferedOutputStream bof = null;

    /** parse line by line to compare */
    try{

      bof = new BufferedOutputStream(new FileOutputStream(sfilename, append));
      bof.write(content);
      bof.close();
    }
    catch(Exception e){
      System.err.println("Can not write to the file"  + sfilename);
      //e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Write binary file.
   */
  public static boolean writeBinaryFile(String sfilename, InputStream ins, boolean append)
  {
    if (ins == null) return false;

    File fsource = new File(sfilename);

    BufferedOutputStream bof = null;
    BufferedInputStream bif = null;

    /** parse line by line to compare */
    try{
      bof = new BufferedOutputStream(new FileOutputStream(sfilename, append));
      bif = new BufferedInputStream(ins);

/*    byte[] buffer = new byte[1000];
      int count = -1;
      do {
        count = bif.read(buffer);
        bof.write(buffer);
      } while (count>=0);*/

      int len    = -1;
      int offset = 0;
      byte []buf= new byte[1024];
      while( (len = bif.read(buf))!=-1 )
      {
         bof.write(buf, offset, len);
      }

      bof.close();
      bif.close();
    }
    catch(Exception e){
      System.err.println("Can not write to the file"  + sfilename);
      //e.printStackTrace();
      return false;
    }

    return true;
  }


  /** write the string array to overwrite the target file
   *@exception:
   *@param
   *      String sfilename: target file name
   *      String slines: string will be stored into target file one by one
   *@return:
   */
  public static boolean writeToFile(String sfilename, String sline)
  {
    if (sline == null) return false;

    File fsource = new File(sfilename);

    PrintWriter pwlog = null;

    /** parse line by line to compare */
    try{

      pwlog = new PrintWriter(new BufferedWriter(new FileWriter(sfilename)));

      /** put the lines to be matched into vector */


        pwlog.println(sline );


      pwlog.close();
    }
    catch(Exception e){
      System.err.println("Can not write to the file  + sfilename");
      ExceptionBroadcast.print(e);
      return false;

    }
    finally{
      if (pwlog != null) pwlog.close();

    }

    return true;





  }
  /** write the string array to overwrite the target file
   *@exception:
   *@param
   *      String sfilename: target file name
   *      String[] slines: string will be stored into target file one by one
   *@return:
   */
  public static boolean writeToFile(String sfilename, String[] slines)
  {
    if (slines == null) return false;

    File fsource = new File(sfilename);

    PrintWriter pwlog = null;

    /** parse line by line to compare */
    try{

      pwlog = new PrintWriter(new BufferedWriter(new FileWriter(sfilename)));
      int ilength =  slines.length;
      /** put the lines to be matched into vector */
      for (int i = 0; i < ilength; i++)
      {
        pwlog.println(slines[i] );

      }//end of for
      pwlog.close();
    }
    catch(Exception e){
      System.err.println("Can not write to the file  + sfilename");
      ExceptionBroadcast.print(e);
      return false;

    }
    finally{
      if (pwlog != null) pwlog.close();

    }

    return true;


  }

  /** write the object array to overwrite the target file
   *@exception:
   *@param
   *      String sfilename: target file name
   *      Object[] olines: object will be stored into target file one by one
   *@return:
   */
  public static boolean writeToFile(String sfilename, Object[] olines)
  {
    if (olines == null) return false;

    File fsource = new File(sfilename);

    PrintWriter pwlog = null;

    /** parse line by line to compare */
    try{

      pwlog = new PrintWriter(new BufferedWriter(new FileWriter(sfilename)));
      int ilength =  olines.length;
      /** put the lines to be matched into vector */
      for (int i = 0; i < ilength; i++)
      {
        if (olines[i] != null)
          pwlog.println(olines[i].toString() );

      }//end of for
      pwlog.close();
    }
    catch(Exception e){
      System.err.println("Can not write to the file  + sfilename");
     ExceptionBroadcast.print(e);
      return false;

    }
    finally{
      if (pwlog != null) pwlog.close();

    }

    return true;
  }

  /**
   *  the method is used to decide which type content should be created
   *  added by Hui Zhang
   */
  public static String modeSelect (String fileName )
  {
     if (fileName==null)
        return null;
     
     int     indexOfLastDot = fileName.lastIndexOf(".");
     String  extension      = fileName.substring(indexOfLastDot+1);

     for (int i=0; i<asciiEntries.length; i++) 
     {
        if(asciiEntries[i].equalsIgnoreCase(extension)) 
           return "ascii";
     }

     return "binary";  //known binary.
  }

  /**
   * Return the MIME type of the file with file name "fileName";
   * return "application/octet-stream" if the data type can not be determined.
   */
  public static String getMIMEType(String fileName){
     if(null==fileName)
        return null;

     int dot = -1;
     if(-1!=(dot=fileName.lastIndexOf(".")))
        return mimeMap.getProperty(fileName.substring(dot+1).toLowerCase());
     else
        return "application/octet-stream";
  }

  private static Properties mimeMap = new Properties();
  static
  {
     try
     {
        InputStream in = SMUtility.loadFromClassPath("classconf/mime.properties");
        mimeMap.load(in);
     }
     catch(Exception e)
     {
        mimeMap.setProperty("doc", "application/msword");
        mimeMap.setProperty("xsl", "application/msexcel");
        mimeMap.setProperty("txt", "text/plain");
        mimeMap.setProperty("html", "text/html");
        mimeMap.setProperty("htm", "text/html");
        mimeMap.setProperty("xml", "text/xml");
        mimeMap.setProperty("gif", "image/gif");
        mimeMap.setProperty("jpg", "image/jpeg");
        mimeMap.setProperty("jpe", "image/jpeg");
        mimeMap.setProperty("jpeg", "image/jpeg");
        mimeMap.setProperty("java", "text/plain");
        mimeMap.setProperty("body", "text/html");
        mimeMap.setProperty("rtx", "text/richtext");
        mimeMap.setProperty("tsv", "text/tab-separated-values");
        mimeMap.setProperty("etx", "text/x-setext");
        mimeMap.setProperty("ps", "application/x-postscript");
        mimeMap.setProperty("class", "application/java");
        mimeMap.setProperty("csh", "application/x-csh");
        mimeMap.setProperty("sh", "application/x-sh");
        mimeMap.setProperty("tcl", "application/x-tcl");
        mimeMap.setProperty("tex", "application/x-tex");
        mimeMap.setProperty("texinfo", "application/x-texinfo");
        mimeMap.setProperty("texi", "application/x-texinfo");
        mimeMap.setProperty("t", "application/x-troff");
        mimeMap.setProperty("tr", "application/x-troff");
        mimeMap.setProperty("roff", "application/x-troff");
        mimeMap.setProperty("man", "application/x-troff-man");
        mimeMap.setProperty("me", "application/x-troff-me");
        mimeMap.setProperty("ms", "application/x-wais-source");
        mimeMap.setProperty("src", "application/x-wais-source");
        mimeMap.setProperty("zip", "application/zip");
        mimeMap.setProperty("bcpio", "application/x-bcpio");
        mimeMap.setProperty("cpio", "application/x-cpio");
        mimeMap.setProperty("gtar", "application/x-gtar");
        mimeMap.setProperty("shar", "application/x-shar");
        mimeMap.setProperty("sv4cpio", "application/x-sv4cpio");
        mimeMap.setProperty("sv4crc", "application/x-sv4crc");
        mimeMap.setProperty("tar", "application/x-tar");
        mimeMap.setProperty("ustar", "application/x-ustar");
        mimeMap.setProperty("dvi", "application/x-dvi");
        mimeMap.setProperty("hdf", "application/x-hdf");
        mimeMap.setProperty("latex", "application/x-latex");
        mimeMap.setProperty("bin", "application/octet-stream");
        mimeMap.setProperty("oda", "application/oda");
        mimeMap.setProperty("pdf", "application/pdf");
        mimeMap.setProperty("ps", "application/postscript");
        mimeMap.setProperty("eps", "application/postscript");
        mimeMap.setProperty("ai", "application/postscript");
        mimeMap.setProperty("rtf", "application/rtf");
        mimeMap.setProperty("nc", "application/x-netcdf");
        mimeMap.setProperty("cdf", "application/x-netcdf");
        mimeMap.setProperty("cer", "application/x-x509-ca-cert");
        mimeMap.setProperty("exe", "application/octet-stream");
        mimeMap.setProperty("gz", "application/x-gzip");
        mimeMap.setProperty("Z", "application/x-compress");
        mimeMap.setProperty("z", "application/x-compress");
        mimeMap.setProperty("hqx", "application/mac-binhex40");
        mimeMap.setProperty("mif", "application/x-mif");
        mimeMap.setProperty("ief", "image/ief");
        mimeMap.setProperty("tiff", "image/tiff");
        mimeMap.setProperty("tif", "image/tiff");
        mimeMap.setProperty("ras", "image/x-cmu-raster");
        mimeMap.setProperty("pnm", "image/x-portable-anymap");
        mimeMap.setProperty("pbm", "image/x-portable-bitmap");
        mimeMap.setProperty("pgm", "image/x-portable-graymap");
        mimeMap.setProperty("ppm", "image/x-portable-pixmap");
        mimeMap.setProperty("rgb", "image/x-rgb");
        mimeMap.setProperty("xbm", "image/x-xbitmap");
        mimeMap.setProperty("xpm", "image/x-xpixmap");
        mimeMap.setProperty("xwd", "image/x-xwindowdump");
        mimeMap.setProperty("au", "audio/basic");
        mimeMap.setProperty("snd", "audio/basic");
        mimeMap.setProperty("aif", "audio/x-aiff");
        mimeMap.setProperty("aiff", "audio/x-aiff");
        mimeMap.setProperty("aifc", "audio/x-aiff");
        mimeMap.setProperty("wav", "audio/x-wav");
        mimeMap.setProperty("mpeg", "video/mpeg");
        mimeMap.setProperty("mpg", "video/mpeg");
        mimeMap.setProperty("mpe", "video/mpeg");
        mimeMap.setProperty("qt", "video/quicktime");
        mimeMap.setProperty("mov", "video/quicktime");
        mimeMap.setProperty("avi", "video/x-msvideo");
        mimeMap.setProperty("movie", "video/x-sgi-movie");
        mimeMap.setProperty("avx", "video/x-rad-screenplay");
        mimeMap.setProperty("wrl", "x-world/x-vrml");
        mimeMap.setProperty("rm", "audio/x-pn-realaudio");
        mimeMap.setProperty("ram", "audio/x-pn-realaudio");
        mimeMap.setProperty("ra", "audio/x-pn-realaudio");
    }
  }

  /*public static String getMIMEType(String fileName){
    if(null==fileName)  return null;

    File file = new File(fileName);
    DataSource ds = new FileDataSource(file);
    DataHandler dh = new DataHandler(ds);

    return dh.getContentType();
  }*/

public static String getRelativePath(String url_a, String ref_url2 )
{
	String relative_path=null;
	String protocol1="",protocol2="";
	String host1="", host2="";
	String path1_1="",path2_1="";
	String relative_P="";
     try
	{
	   URL input_url = new URL(url_a);
	   URL ref_url   = new URL(ref_url2 );

	   protocol1 = input_url.getProtocol();
	   protocol2 = ref_url.getProtocol() ;

	   host1 = input_url.getHost() ;
	   host2 = ref_url.getHost();

	   path1_1 = input_url.getPath();
	   path2_1 = ref_url.getPath() ;

	   int  port1 = input_url.getPort(), port2 = ref_url.getPort();

	   boolean b2 = protocol1.equalsIgnoreCase(protocol2) ;
	   boolean b3 = host1.equalsIgnoreCase(host2) ;

	   if(!b2||!b3||port1!=port2) return url_a;

	   StringTokenizer token1=new StringTokenizer(path1_1,"/") ;
	   StringTokenizer token2=new StringTokenizer(path2_1,"/") ;
	// trip off all same path and go futher from first path
	  while (token1.hasMoreTokens()&& token2.hasMoreTokens() )
	   {
	       String tmp1 = token1.nextToken();
	       String tmp2 = token2.nextToken();
	       if(!tmp1.equalsIgnoreCase(tmp2))
	   	 {
	   	    relative_P=relative_P+"../";
	   	    while (token2.hasMoreTokens())
	   	      {
	   	        relative_P=relative_P+"../";
	   	        String dumy=token2.nextToken();
	   	      }
	   	    String change_D=tmp1;
	   	    while(token1.hasMoreTokens())
	   	     {
	   	      change_D=change_D+"/"+token1.nextToken();
	   	     }
	   	   relative_path =  relative_P+ change_D;
   	         }// end if

	   }// end while
	   // when we do not need go any futher from the first url
	 if((token1.hasMoreTokens()==false)&&(token2.hasMoreTokens()))
	   {
	     	relative_P=relative_P+"../";
	   	while (token2.hasMoreTokens())
	   	   {
	   	      relative_P  = relative_P+"../";
	   	      String dumy = token2.nextToken();
	   	   }
	   	relative_path =  relative_P;
	  }
       }
      catch( MalformedURLException e){return url_a;}
	return  relative_path;
}

 public static boolean diff(File f1, File f2) throws IOException{
    if ((f1 == null) && (f2 !=null)) return true;
    if ((f1 != null) && (f2 ==null)) return true;
    if ((f1 ==null) && (f2 == null)) return false;

    //now f1 != null, f2!= null

    //if same file, no diff
    if (f1.compareTo(f2) == 0)  return false;

    //if any file not exists, return true
    if ( (f1.exists()==false) || (f2.exists()==false)) return true;

    //first check length
    if (f1.length() != f2.length()) return true;

    BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(f1));
    BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(f2));

//    byte[] buffer1 = new byte[1024];
//    byte[] buffer2 = new byte[1024];

    try {
      while (true) {
        int c1 = in1.read();
        int c2 = in2.read();
        if (c1 != c2) return true;
        if (c1 == -1) break;
      }
    }finally {
      in1.close();
      in2.close();
    }
    return false;
 }

 public static void main( String[] args )
 {
   try {
      File f1 = new File("d:\\swdir\\us5day.xml");
      File f2 = new File("d:\\swdir\\us5day1.xml");
      System.out.println(diff(f1,f2));
      System.out.println("rename"+f1.renameTo(f2));

      System.out.println(f2.renameTo(f1));
   }catch (Exception e) {ExceptionBroadcast.print(e);}
 }

}