package org.yang.util.template;

import java.util.*;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestKeywordReplaceHandler 
 */
public class KeywordReplaceHandler implements MappingHandler
{
  String     result     = null;
  Properties keywordMap = null;
  
  public KeywordReplaceHandler()
  {
     keywordMap = new Properties();
  }
  
  public void setKeyword(String key, String val)
  {
     keywordMap.setProperty(key, val);	
  }
   
  public void startToMap()
  {}

  public String replace(String keyword)
  {
     return keywordMap.getProperty(keyword);
  }
  
  public void endOfMapping(String document)
  {
     result = document;
  }
  
  public String getResult()
  {
    return result;	
  }
}