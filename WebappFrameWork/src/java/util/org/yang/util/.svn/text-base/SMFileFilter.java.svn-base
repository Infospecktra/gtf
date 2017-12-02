/**
 *Title:       SMFileFilter
 *@version:
 *Copyright:   Copyright (c) 2000
 *@author:      Hui Ouyang
 *Company:     Screaming Media
 *Description: utility class for filtering file
 *             created on May 01, 2000
 *             add support for the file filter based on the modified timestamp on June 16, 2000
 */ 
package org.yang.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;

public class SMFileFilter extends Object implements FileFilter, FilenameFilter{

  public final static int SUFFIXMATCH = 1;

  public final static int PREFIXMATCH = 3;

  public final static int TIMESTAMPMATCH = 11;

  public int imatchpattern = SUFFIXMATCH; //1-20:filename match, 21- other match pattern

  public String smatchedstring;

  public Date dtstart;  //null: before

  public Date dtend; //null: after


  public SMFileFilter() {

  }

  public SMFileFilter(Date dtstart, Date dtend) {
    this.dtend = dtend;
    this.dtstart = dtstart;
    imatchpattern = TIMESTAMPMATCH;

  }

  public SMFileFilter(int imatchpattern, String smatchstring) {
    this.imatchpattern = imatchpattern;
    this.smatchedstring = smatchstring;
  }


  /** implement the method from the FilenameFilter */
  public boolean accept(File dir, String name)
  {
    //file name match method only suitable for name match pattern
    //this should be case sensitive
    if (imatchpattern > 20) return false;

    if (imatchpattern > 10)
    {
      String spath = dir.getName();
      File file = new File(SMFile.SPATHFORFILTER + File.separator + spath + File.separator + name);
      return accept(file);
    }

    if (name == null) return false;
    if (dir == null) return false;
    if(!dir.exists()) return false;



    String smatchedstringuppercase = smatchedstring;

    switch (imatchpattern)
    {
      case SUFFIXMATCH:
        if (smatchedstring == null) return true; //all files
        if (File.pathSeparator.equals("\\"))
          return name.toUpperCase().endsWith(smatchedstringuppercase);
        else
          return name.endsWith(smatchedstring);

      case PREFIXMATCH:
        if (smatchedstring == null) return true; //all files

        if (File.pathSeparator.equals("\\"))
          return name.toUpperCase().startsWith(smatchedstringuppercase);
        else
          return name.startsWith(smatchedstring);


      default:
        return false;


    }


  }
  /** implement the method from the FileFilter
   *  this should be implemented in the future to support the file properties filter
   */
  public boolean accept(File pathname)
  {
    //file name match method only suitable for name match pattern
    //this should be case sensitive
    if (imatchpattern > 20) return false;

    if (imatchpattern < 10) return false;

    long ldt = pathname.lastModified();

    Date dt = new Date(pathname.lastModified());

 
    switch (imatchpattern)
    {
      case TIMESTAMPMATCH:
        if ((dtstart == null)&&(dtend == null))
          return true; //all files
        if ((dtstart == null)&&(dtend != null))
          return dt.before(dtend);//before
        if ((dtstart != null)&&(dtend == null))
          return dt.after(dtstart);//after
        if ((dtstart != null)&&(dtend != null))
        {
          boolean bincluded = (dt.after(dtstart))&&(dt.before(dtend));
          return bincluded;//within
        }

        break;
      default:
        return false;

    }
    return false;

  }

}

 