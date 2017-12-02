package org.yang.util.template;

import java.util.Iterator;
import java.util.Properties;
import java.util.ArrayList;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestDom2XMLHandler 
 */
public class Dom2XMLHandler implements DomManipulatingHandler
{
   private String     smmile     = null;
   private Properties targetPath = null;
   private XPath      myXPath    = new XPath();
   
  public Dom2XMLHandler(){}

  public Dom2XMLHandler(Properties targetPath)
  {
     this.targetPath=targetPath;
  }
  
  public void documentStart(Node document)
  {
     smmile = "";
  }

  public void documentEnd(Node document){}

  public boolean nodeStart(Node node)
  {
     String name = node.getNodeName();
     int    type = node.getNodeType();
     myXPath.appendPath(name);

     if(!checkPath(myXPath))
     {
     	myXPath.removeLastPath();
        return false;
     }
     
     String att = "";

     switch(type)
     {
        case org.w3c.dom.Node.ATTRIBUTE_NODE:
             break;
        case org.w3c.dom.Node.CDATA_SECTION_NODE:
             break;
        case org.w3c.dom.Node.COMMENT_NODE:
             
             break;
        case org.w3c.dom.Node.DOCUMENT_FRAGMENT_NODE:
             break;
        case org.w3c.dom.Node.DOCUMENT_NODE: 
             break;
        case org.w3c.dom.Node.DOCUMENT_TYPE_NODE:
             return true;
        case org.w3c.dom.Node.ELEMENT_NODE:
             NamedNodeMap attributes = node.getAttributes();
             if(null==attributes||attributes.getLength()==0)
                break;
             for(int i=0; i<attributes.getLength(); i++)
             {
                Node attribute = attributes.item(i);
                att = att + " " + attribute.getNodeName() +
                          "=\"" + XMLHelper.getNormalizedXMLString(attribute.getNodeValue()) + "\"";
             }
             break;
        case org.w3c.dom.Node.ENTITY_NODE: 
             break;
        case org.w3c.dom.Node.ENTITY_REFERENCE_NODE:
             break;
        case org.w3c.dom.Node.NOTATION_NODE:
             break;
        case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
             break;
        case org.w3c.dom.Node.TEXT_NODE:
             smmile = smmile + XMLHelper.getNormalizedXMLString(node.getNodeValue());
             return true;
     }

     NodeList childnodes = node.getChildNodes();

     if(childnodes.getLength()!=0)
     {
        smmile = smmile + "<" + name + att + ">";     
     }
     else
     {
        smmile = smmile + "<" + name + att + "/>";     
     }

     return true;
//System.out.println("Node Start :" + smmile);
  }
  
  public void nodeEnd(Node node)
  {
     String name = node.getNodeName();
     myXPath.removeLastPath();
     NodeList childnodes = node.getChildNodes();

     if(childnodes.getLength()!=0)
     {
        smmile = smmile + "</" + name + ">";     
     }
//System.out.println("Node End :" + smmile);
  }
  
  public boolean checkPath(XPath path)
  {
     if(null==targetPath)
        return true;

     Iterator i = targetPath.values().iterator();
     String temp = null;
     while(i.hasNext())
     {
     	temp = (String)i.next();
  	if(path.isParentOf(temp)||
  	   path.isChildOf(temp))
           return true;
     }	
     return false;
  }
  
  public String getXMLString()
  {
     return smmile;
  }
  
  class XPath
  {
     ArrayList fullPath = new ArrayList();	
     
     public void appendPath(String path)
     {
        fullPath.add(path);
     }
     
     public void removeLastPath()
     {
        if(fullPath.size()>0)
           fullPath.remove(fullPath.size()-1);
     }

     public String getFullPath()
     {
     	String temp = "";
     	
     	for(int i=0; i<fullPath.size(); i++ )
     	{
     	   if(i==0)
     	      temp = "/" + fullPath.get(0);
     	   else
     	      temp = temp + "/" + fullPath.get(i);
     	}
     	
     	return temp;
     }
     
     public boolean equals(String path)
     {
        return getFullPath().equals(path);
     }
     
     public boolean isChildOf(String path)
     {
        return getFullPath().startsWith(path);
     }

     public boolean isParentOf(String path)
     {
     	if(null==path)
     	   return false;
        return path.startsWith(getFullPath());
     }
  }
  
  public static void main(String[] argc)
  {
     try
     {
        DomManipulater dm = new DomManipulater();
        //InputStream is = dm.getClass().getClassLoader()
        //                              .getResourceAsStream("./test.xml");
        FileInputStream fis = new FileInputStream("./test.xml");
  
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmldoc = db.parse(fis);
     
        Properties targetPath = new Properties();
        targetPath.setProperty("title","content/article/title");
        targetPath.setProperty("body","content/article/body");
     
        Dom2XMLHandler d2x = new Dom2XMLHandler(targetPath);
        dm.setHandler(d2x);
        dm.setDocument(xmldoc);
        dm.startToWalk();
     
        String doc = d2x.getXMLString();
     
        System.out.println(doc);
     }
     catch(Exception e)
     {
        e.printStackTrace();	
     }
  }
}