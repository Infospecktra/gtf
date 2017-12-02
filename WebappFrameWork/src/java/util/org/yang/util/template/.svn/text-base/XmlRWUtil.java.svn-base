package org.yang.util.template;
/*******************************************************
 *                                                     *
 *  @(#)XmlRWUtil.java 2001/06/02                      *
 *  Copyright 2000- by Screaming Media, Inc.,          *
 *  601 west 26 street 13 floor New York 10001, U.S.A. *
 *                                                     *
 *******************************************************/

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  

import org.w3c.dom.Document;
import org.w3c.dom.Node;

//import org.jboss.util.DOMWriter;


/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestXmlRWUtil 
 */
public class XmlRWUtil
{
   public static Document read(InputStream in)
      throws java.lang.Exception
   {
      DocumentBuilderFactory factory =
          DocumentBuilderFactory.newInstance();
      //factory.setValidating(true);   
      //factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(in);
   }
   
/*   public static void write(Writer out, Document dom)
      throws java.lang.Exception
   {
      DOMWriter writer = new DOMWriter(out,false);
      writer.print(dom, true);
   }*/
   
   public static ArrayList getNodesFromDocument(Node doc, String nodepath, String cattr, String cvalue)
   {
      DomManipulater dm = new DomManipulater();
      DomExtractingHandler deh = new DomExtractingHandler();
      deh.setNodePath(nodepath);
      dm.setHandler(deh);
      dm.setDocument(doc);
      dm.startToWalk();

      ArrayList nodes = null;

      if(null==cattr||null==cvalue)
      {
         nodes = deh.getMatchedNodes();
      }
      else
      {
         Properties condition_attri = new Properties();
         condition_attri.setProperty(cattr, cvalue);
         nodes = deh.getMatchedNodes(condition_attri);
      }

      int len = nodes.size();

      return nodes;
   }
   
   public static ArrayList getNodesFromDocument(Node doc, String nodepath)
   {
      return getNodesFromDocument(doc, nodepath, null, null);
   }

   public static Node getNodeFromDocument(Node doc, String nodepath)
   {
      ArrayList temp = getNodesFromDocument(doc, nodepath, null, null);
      if(0==temp.size())
         return null;
      else
         return (Node)(temp.get(0));
   }
   
   public static Node getTextNode(Node node)
   {
      return (Node)node.getFirstChild();
   }
}