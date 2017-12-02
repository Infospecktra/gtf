package org.yang.util.template;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestXMLHelper 
 */
public class XMLHelper
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


   public static String getNormalizedXMLString(String s)
   {
      if (s == null) return null;
      int ilength = s.length();
      if ( ilength == 0) return s;
      String sapos = "&apos;";
      String slt = "&lt;";
      String sgt = "&gt;";
      String samp = "&amp;";
      String squot = "&quot;";
      StringBuffer sbuff =  new StringBuffer(s);

      for (int i = ilength -1 ; i >= 0  ; i --)
      {
         switch  (sbuff.charAt(i))
         {
            case '\'':
             sbuff.replace(i, i + 1, sapos);
               break;
            case '<':
               sbuff.replace(i, i + 1, slt);
               break;
            case '>':
               sbuff.replace(i, i + 1, sgt);
               break;
            case '&':
               sbuff.replace(i, i + 1, samp);
               break;
            case '"':
               sbuff.replace(i, i + 1, squot);
               break;
            default:
               break;
         }
      }
      return new String(sbuff);
   }
}