package org.yang.util.template;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestMappingHandler 
 */
public interface MappingHandler
{
  public void startToMap();

  public String replace(String keyword);
  
  public void endOfMapping(String document);
  
  public String getResult();
}