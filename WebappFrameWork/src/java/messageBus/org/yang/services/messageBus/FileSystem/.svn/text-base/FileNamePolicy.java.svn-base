package org.yang.services.messageBus.FileSystem;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class FileNamePolicy
{
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public static final String TIME = "TIME";
    public static final String SEQNO = "SEQNO";

    public static final String SECTION = "SECTION";
    public static final String PATH = "PATH";
    public static final String FILENAME = "FILENAME";

    private Calendar cal = null;

    int counter = 0;

    HashMap map = null;

    String filename;
    String proPath;
    String proFilename;
    
    String policyStr;
    String specifier;
    String section;

    public FileNamePolicy(String thePolicyStr) 
    {
        policyStr = thePolicyStr;

        if (policyStr == null) policyStr ="";
        if (specifier == null) specifier = "*";

        counter = 0;
    }

    /**
     * Get filename from directory and policy.
     */
    public String getFilename(String filename) {

        this.filename = filename;
        SMKeywordMapper smkm = new SMKeywordMapper();       
        smkm.setReplaceHandler(this);
        smkm.setDocument(policyStr);
        smkm.setLeftTag('%');
        smkm.setRightTag('%');
        smkm.mapping();

        return getResult();
    }
    
    public void setKeyword(String key, String val)
    {
       if(null==key||null==val)
          return;

       if(null == map)
          map = new HashMap();

       map.put(key, val);
    }
    
    public void startToMap()
    {
       cal = Calendar.getInstance();
       proPath = null;
       proFilename = null;
    }

    public String replace(String keyword)
    {
       try
       {
          if(null==keyword)
             return "";
          
          String temp = keyword;
          StringBuffer kwd = new StringBuffer("get");
          StringBuffer par = new StringBuffer("");

          int index = temp.indexOf("(");

          if(-1==index)
          {
             index = temp.length();
             temp = temp + "()";
          }

          for(int i=0; i<index; i++)
          {
             kwd.append(temp.charAt(i));
          }
          
          for(int i=index+1; i<temp.length()-1; i++)
          {
             par.append(temp.charAt(i));
          }

          Class[]  cl = {"".getClass()};
          Object[] ob = {par.toString()};
          
          String function = kwd.toString();
          
          //System.out.println("function  : " + function);
          //System.out.println("parameter : " + par.toString());
          
          Method m = this.getClass().getMethod(function, cl);
          return (String)m.invoke(this, ob);
       }
       catch(NoSuchMethodException nme)
       {
          return (String)map.get(keyword);	
       }
       catch(SecurityException se){}
       catch(Exception e){}
       return "";
    }
  
    public void endOfMapping(String document)
    {
    	this.filename = document;
    }
  
    public String getResult()
    {
    	return filename;
    }
       
    public String getYEAR(String para)
    {
        String yearItem = ""+cal.get(Calendar.YEAR);

        // ensure format 'yy'
        if(yearItem.length()==1)
           yearItem = "0" + yearItem;    	
        return yearItem;
    }
    
    public String getMONTH(String para)
    {
        String monthItem = ""+(cal.get(Calendar.MONTH)+1);

        // ensure format 'mm'
        if(monthItem.length()==1)
           monthItem = "0" + monthItem;
        return monthItem;
    }

    public String getDAY(String para)
    {
        String dayItem = ""+cal.get(Calendar.DAY_OF_MONTH);

        // ensure format 'dd'
        if(dayItem.length()==1)
           dayItem = "0" + dayItem;
        return dayItem;
    }

    public String getTIME(String para)
    {
       return ""+Calendar.getInstance().getTime().getTime();
    }
    
    public String getSEQNO(String para)
    {
       return "" + (counter++);
    }

    public String getSECTION(String para)
    {
       return section;
    }

    public String getPATH(String para)
    {
       if(null==proPath)
          makeFilepath();
       return proPath;
    }

    public String getFILENAME(String para)
    {
       if(null==proFilename)
          makeFilepath();
       return proFilename;
    }

    public String getDATE(String para)
    {
       java.text.SimpleDateFormat formatter
          = new java.text.SimpleDateFormat (para);
      
       return formatter.format(cal.getTime());    	
    }
    
    private void makeFilepath()
    {
        int last1 = filename.lastIndexOf('/');
        int last2 = filename.lastIndexOf('\\');
        int last = last1 > last2 ? last1 : last2;
//System.out.println("++++++++++++++++++++++++++++++" + filename);
        if (last >=0 ) {
            proPath = path(filename.substring(0,last));
            proFilename = filename.substring(last+1);
        } else proFilename = filename;
    }
    
    private String path(String path)
    {
       int first1 = path.indexOf('/');
       int first2 = path.indexOf('\\');
       int first = first1 < first2 ? first1 : first2;
       
       if(first>=0)
          return path.substring(first+1);

       first = first1 > first2 ? first1 : first2;

       if(first>=0)
          return path.substring(first+1);

       return path;
    }
    
    
    public static void main(String[] argc)
    {
       FileNamePolicy fnp = new FileNamePolicy(argc[0]);
       fnp.setKeyword("HAPPY", "fellsgood");
       //System.out.println(fnp.getFilename(argc[1]));
    }
}


