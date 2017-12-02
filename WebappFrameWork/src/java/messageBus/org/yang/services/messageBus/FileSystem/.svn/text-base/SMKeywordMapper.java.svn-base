package org.yang.services.messageBus.FileSystem;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

class SMKeywordMapper
{
  boolean keywordOnly = false;
  String  template    = null;
  String  slefttag    = "$";
  String  srighttag   = "$";

  FileNamePolicy mh = null;

  public SMKeywordMapper()
  {}

  public void setDocument(String template)
  {
     if(template==null)
        throw new NullPointerException();
     
     this.template = template;
  }
  
  public void setKeywordOnly(boolean keywordOnly)
  {
     this.keywordOnly = keywordOnly;
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

  public void setReplaceHandler(FileNamePolicy mh)
  {
     if(mh==null)
       throw new NullPointerException();
       
     this.mh = mh;
  }

  public void mapping()
  {
     StringBuffer document = new StringBuffer("");
     StringBuffer keyword  = new StringBuffer("");

     int slefttaglen  = slefttag.length();
     int srighttaglen = srighttag.length();

     boolean scope    = false;
     boolean boundary = false;

     mh.startToMap();

     for(int i=0; i<template.length(); i++)
     {
     	// maybe find a keyword beginning Tag - now isn't in a keyword
        if(!scope && template.charAt(i)==slefttag.charAt(0))
        {
           if(isATag(i, slefttag))
           {
              keyword = new StringBuffer("");
              scope   = true;
           }
        }
        // maybe find a keyword ending Tag - now in a keyword
        else if(scope && template.charAt(i)==srighttag.charAt(0))
        {
           if(isATag(i, srighttag))
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
           if(!scope&&!keywordOnly)
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
  
  private boolean isATag(int i, String tag)
  {
    for(int j=0; j<tag.length(); j++)
    {
      if(tag.charAt(j)!=template.charAt(i+j))
      {
        return false;
      }
    }
    return true;
  }
}