package org.yang.util.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
/*******************************************************
 *                                                     *
 *  @(#)XmlRWUtil.java 2001/06/02                      *
 *  Copyright 2000- by Screaming Media, Inc.,          *
 *  601 west 26 street 13 floor New York 10001, U.S.A. *
 *                                                     *
 *******************************************************/




//import org.jboss.util.DOMWriter;

public class XmlRWUtil
{
   private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

   public static Document read(InputStream in)
      throws Exception
   {
      //factory.setValidating(true);
      //factory.setNamespaceAware(true);
      DocumentBuilder builder = null;
      synchronized(factory)
      {
         builder = factory.newDocumentBuilder();
      }
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