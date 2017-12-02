package org.yang.util.template;

import java.io.*;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestSMKeywordMapper 
 */
public class YangKeywordMapper
{
  String template = null;
  String slefttag  = "$";
  String srighttag = "$";

  MappingHandler mh = null;

  public YangKeywordMapper()
  {}

  public void setDocument(String template)
  {
     if(template==null)
        throw new NullPointerException();
     
     this.template = template;
  }

  public void setLeftTag(char lt)
  {
     slefttag = (new StringBuffer()).append(lt).toString();
  }

  public void setRightTag(char rt)
  {
     srighttag = (new StringBuffer()).append(rt).toString();	
  }
  
  public void setLeftTag(String slt)
  {
     slefttag = slt;	
  }

  public void setRightTag(String srt)
  {
     srighttag = srt;	
  }

  public void setReplaceHandler(MappingHandler mh)
  {
     if(mh==null)
       throw new NullPointerException();
       
     this.mh = mh;
  }

  public void mapping()
  {
     StringBuffer document = new StringBuffer("");
     StringBuffer keyword  = null;

     int slefttaglen  = slefttag.length();
     int srighttaglen = srighttag.length();

     boolean scope    = false;
     boolean boundary = false;

     mh.startToMap();

     for(int i=0; i<template.length(); i++)
     {
        if(!scope && template.charAt(i)==slefttag.charAt(0))
        {
           boolean match = true;
           for(int j=0; j<slefttaglen; j++)
           {
              if(slefttag.charAt(j)!=template.charAt(i+j))
              {
                 match=false;
                 break;	
              }
           }
           
           if(match)
           {
              keyword = new StringBuffer("");
              scope   = true;
           }
        }
        else if(scope && template.charAt(i)==srighttag.charAt(0))
        {
           boolean match = true;
           for(int j=0; j<srighttaglen; j++)
           {
              if(srighttag.charAt(j)!=template.charAt(i+j))
              {
                 match=false;
                 break;	
              }
           }

           if(match)
           {
              for(int k=0; k<srighttaglen-1; k++)
                 i++;
              
              String skeyword = keyword.substring(slefttaglen, keyword.length());
              document.append(mh.replace(skeyword));
              boundary = true;
              scope    = false;
           }
        }
        
        if(!boundary)
        {
           if(!scope)
              document.append(template.charAt(i));	
           else
              keyword.append(template.charAt(i));
        }
        else
        {
           boundary = false;
        }
     }
     
     mh.endOfMapping(document.toString());
  }
   
  public static void main(String[] argc)
  {
     if(argc.length!=1)
     {
     	System.out.println("usage:java SMKeywordMapper template_file_name");
        System.exit(1);
     }

     try
     {
        File template = new File(argc[0]);
        if(template.exists())
        {
           int    len = (int)template.length();
           byte[] buf = new byte[len];
           FileInputStream fis = new FileInputStream(template);
           // need to be fixed
           fis.read(buf);
           fis.close();
           fis = null;
           // using system encode
           
           
           YangKeywordMapper smkm = new YangKeywordMapper();
           smkm.setDocument(new String(buf));
           smkm.setLeftTag('$');
           smkm.setRightTag('$');
           smkm.mapping();
        }
     }
     catch(Exception e)
     {}
  }
}
