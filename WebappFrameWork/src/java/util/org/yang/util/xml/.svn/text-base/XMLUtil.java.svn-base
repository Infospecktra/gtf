package org.yang.util.xml;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.yang.util.ExceptionBroadcast;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.xml.sax.InputSource;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.XPathAPI;
/**
 * Title:         Siteware Enterprise
 * Description:   Siteware Enterprise Edition.
 * This class provides methods to convert between an xml String and a Document
 * object. It also provides method to retrieve element or attribute value when
 * supplied with a qualified path (conforms to XPath spec).
 *
 * Copyright:    Copyright (c) 2001
 * Company:      Screamingmedia
 * @author       Yao Cheng
 * @version 1.0
 */



public class XMLUtil {
  /**
   * default constructor.
   */
  public XMLUtil() {
  }

   /**
   * This method reads data in a XML string into a DOM Object.
   * A Document object will be returned.
   */
  public static Document xml2dom(String xmlStr) throws XMLUtilException{
    try{
      xmlStr = xmlStr.trim(); //Get rid off the preceding white space.

      // Step 1: create a DocumentBuilderFactory and configure it
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //possible FactoryConfigurationError.
      //dbf.setIgnoringElementContentWhitespace(true);

      // Step 2: create a DocumentBuilder that satisfies the constraints
      // specified by the DocumentBuilderFactory
      DocumentBuilder db = null;
      db = dbf.newDocumentBuilder();  //possible ParserConfigurationException.

      // Step 3: parse the content stored in the xml string.
      Reader xmlReader = new StringReader(xmlStr);
      InputSource is = new InputSource(xmlReader);
      Document doc = db.parse(is);  //possible SAXException, IOException
      return doc;
    } catch (Exception e) {
      throw new XMLUtilException(e.getMessage());
    }
  }

   /**
   * This method writes data in a Document object to an XML String, using the
   * platform default encoding.
   */
  public static String dom2xml(Document d) throws XMLUtilException{
    try{
      String xmlStr = dom2xml(d,null);
      return xmlStr;

    } catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

   /**
   * This method writes data in a  Document object to an XML String, using the
   * specified encoding "enc".
   */
  public static String dom2xml(Document d, String enc) throws XMLUtilException{
    try{
      // Use a Transformer for output
      TransformerFactory tFactory = TransformerFactory.newInstance(); //TransformerFactoryConfigurationError
      Transformer transformer = tFactory.newTransformer(); //TransformerConfigurationException
      if(enc != null)
        transformer.setOutputProperty("encoding", enc);

      DOMSource source = new DOMSource(d);

      StringWriter sw = new StringWriter();
      StreamResult result = new StreamResult(sw);
      transformer.transform(source, result); //TransformerException.

      return sw.toString();

    } catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

   /**
   * This method writes data in a Node object to an XML String, using the
   * platform default encoding.
   */
  public static String node2xml(Node n) throws XMLUtilException{
    try{
      String xmlStr = node2xml(n,null);
      return xmlStr;

    } catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

   /**
   * This method writes data in a Node object to an XML String, using encoding
   * specified by "enc".
   */
  public static String node2xml(Node n, String enc) throws XMLUtilException{
    try{
      // Use a Transformer for output
      TransformerFactory tFactory = TransformerFactory.newInstance(); //TransformerFactoryConfigurationError
      Transformer transformer = tFactory.newTransformer(); //TransformerConfigurationException
      if(enc != null) transformer.setOutputProperty("encoding",enc);

      DOMSource source = new DOMSource(n);

      StringWriter sw = new StringWriter();
      StreamResult result = new StreamResult(sw);
      transformer.transform(source, result); //TransformerException.

      return sw.toString();

    } catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

  /**
   * Return the value of the node specified by the XPath; only Element node and
   * Attribute node are supported for the moment.
   * <br>
   * Only the first node value will be returned if by any chance multiple such
   * nodes exist.
   * Return null if the node type is not recognized as either of the above.
   */
  public static String getValueByPath(String xmlStr, String xpath) throws XMLUtilException {
    if ((xmlStr == null) || (xpath == null)) return null;
    try{
      xmlStr = xmlStr.trim();
      Document doc = xml2dom(xmlStr);

      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
      Node n = nl.nextNode();

      if (n instanceof Attr) {
        return n.getNodeValue();
      }

      if (n instanceof Element) {
        Element e = (Element) n;
        Node child = e.getChildNodes().item(0);

        String value="";
        if(child != null) value = child.getNodeValue();

        return value;
      }

      return null; //return null if the node type is not Attr or Element.

    }catch (Exception e) {
//      com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
      throw new XMLUtilException(e.getMessage());
    }
  }

 /**
   * Return the values of the nodes specified by the XPath; only Element node and
   * Attribute node are supported for the moment.
   * <br>
   * A string array will be returned with all node values.
   */
  public static String[] getValuesByPath(String xmlStr, String xpath) throws XMLUtilException{
    if ((xmlStr == null) || (xpath == null)) return null;
    try{
      xmlStr = xmlStr.trim();
      Document doc = xml2dom(xmlStr);
      String[] values = XMLUtil.getValuesByPathFromDom(doc,xpath);
      return values;

    }catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

  /**
   * Return the values of the nodes specified by the XPath; only Element node and
   * Attribute node are supported for the moment.
   * <br>
   * A string array will be returned with all node values.
   */
  public static String[] getValuesByPathFromDom(Document doc, String xpath) throws XMLUtilException{
    if ((doc==null) || (xpath==null)) return null;
    try{
      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);

      List list = new ArrayList();
      Node n = null;
      while ((n = nl.nextNode()) != null){
        String value = null;

        if (n instanceof Attr) {
          value = n.getNodeValue();
        }

        if (n instanceof Element) {
          Element e = (Element) n;
          Node child = e.getChildNodes().item(0);
          if(child != null) value = child.getNodeValue();
          else value = "";
        }

        if(value != null) list.add(value.trim());
      }

      if(list.size()>0){
        int size = list.size();
        String[] values = new String[size];
        for(int i=0; i<size; i++){
          values[i] = (String) list.get(i);
        }
        return values;
      }
      return null;

    }catch (Exception e) {
//      com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
      throw new XMLUtilException(e.getMessage());
    }
  }


  /**
   * Set the value to the first node specified by the path only, if multiple nodes
   * exist.  The node type remains unchanged.
   * <br>
   * Currently only Attribute and Element node types are supported.
   */
  public static String setValueByPath(String xmlStr,String xpath,String value,String enc) throws XMLUtilException{
    if ((xmlStr==null) || (xpath==null) || (value==null)) return xmlStr;
    try{
      xmlStr = xmlStr.trim();
      Document doc = xml2dom(xmlStr);
      doc = setValueByPathToDom(doc,xpath,value);
      return dom2xml(doc,enc);

    }catch (Exception e) {
      ExceptionBroadcast.print(e);
      throw new XMLUtilException(e.getMessage());
    }
  }

    /**
   * Set the value to the first node specified by the path only, if multiple nodes
   * exist.  The node type remains unchanged.
   * <br>
   * Currently only Attribute and Element node types are supported.
   */
  public static Document setValueByPathToDom(Document doc,String xpath,String value) throws XMLUtilException{
//System.out.println("=================> xpath=" + xpath + " value=" + value);
    if ((doc==null) || (xpath==null) || (value==null)) return doc;
    try{
      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
      Node n = nl.nextNode();
//System.out.println("target node=" + n);
      if (n instanceof Attr) {
        n.setNodeValue(value);
      }

      if (n instanceof Element) {
        Element e = (Element) n;
        NodeList nodes = e.getChildNodes();
        Node firstChild = nodes.item(0);
        if(firstChild != null) {
          firstChild.setNodeValue(value);
        }
        else{
          Text t = doc.createTextNode(value);
          e.appendChild(t);
        }
      }

      return doc;

    }catch (Exception e) {
      ExceptionBroadcast.print(e);
      throw new XMLUtilException(e.getMessage());
    }
  }

    /**
   * Set the value to the first node specified by the path only, if multiple nodes
   * exist.  The node type remains unchanged.
   * <br>
   * Currently only Attribute and Element node types are supported.
   */
   public static Document setValueByPathToDom(Document doc,String xpath,String[] value) throws XMLUtilException
   {
      if ((doc==null)||(xpath==null)||(value==null)||value.length==0)
         return doc;
      try
      {
         NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
         Node preNode = null;
         Node curNode = null;
         Node parent  = null;
         Node nextSib = null;
         int curSize = value.length;
//System.out.println("=================> xpath=" + xpath + " value=" + value[0] + " len=" + value.length);
         for(int i=0; i<curSize; i++)
         {
//System.out.println("node #" + i);
            curNode = nl.nextNode();

            if(null!=curNode)
            {
//System.out.println("current node is not null!");
               updateNode(doc, curNode, value[i]);
            }
            else
            {
//System.out.println("current node is null!");
               if(null==preNode)
                  return doc;
//System.out.println("previous node is not null!");

               Node temp = null;
               if(preNode instanceof Element)
               {
                  parent = preNode.getParentNode();
                  curNode = preNode.cloneNode(true);
                  updateNode(doc, curNode, value[i]);
                  if(null!=(nextSib=preNode.getNextSibling()))
                     parent.insertBefore(curNode, nextSib);
                  else
                     parent.appendChild(curNode);
               }
               else if(preNode instanceof Attr)
               {
                  parent = ((Attr)preNode).getOwnerElement();
                  temp = parent.cloneNode(true);
                  curNode = temp.getAttributes().getNamedItem(preNode.getNodeName());
                  curNode.setNodeValue(value[i]);
                  preNode = parent;
               	  parent = parent.getParentNode();
                  if(null!=(nextSib=preNode.getNextSibling()))
                     parent.insertBefore(temp, nextSib);
                  else
                     parent.appendChild(temp);
               }               
            }
            preNode = curNode;
         }

         return doc;
      }
      catch (Exception e) 
      {
         ExceptionBroadcast.print(e);
         throw new XMLUtilException(e.getMessage());
      }
   }

   private static void updateNode(Document doc, Node curNode, String value)
   {
//System.out.println("target node=" + curNode);

      if(curNode instanceof Attr) 
      {
         curNode.setNodeValue(value);
      }
      else if(curNode instanceof Element) 
      {
         Element e = (Element) curNode;
         NodeList nodes = e.getChildNodes();
         Node firstChild = nodes.item(0);
         if(firstChild != null) 
         {
            firstChild.setNodeValue(value);
         }
         else
         {
            Text t = doc.createTextNode(value);
            e.appendChild(t);
         }
      }   	
   }

  /**
   * This method will insert an XML fragment into the position specified by
   * xpath; the modified xml string will be returned.
   * <br>
   * Note: the outmost tag of the xml fragment will be discarded and the child
   * nodes of the xml fragment will be inserted in a way that the sequence of
   * child nodes could be maintained.
   */
  public static String insertXMLFragByPath(String xmlStr,String xpath,String xmlFrag,String enc) throws XMLUtilException{
    if ((xmlStr==null) || (xpath==null) || (xmlFrag==null)) return xmlStr;
    try{
      xmlStr = xmlStr.trim();
      Document doc = xml2dom(xmlStr);
      doc = insertXMLFragByPathToDom(doc,xpath,xmlFrag);
      return dom2xml(doc,enc);

    }catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

    /**
   * This method will insert an XML fragment into the position specified by
   * xpath; the modified xml string will be returned.
   * <br>
   * Note: the outmost tag of the xml fragment will be discarded and the child
   * nodes of the xml fragment will be inserted in a way that the sequence of
   * child nodes could be maintained.
   */
  public static Document insertXMLFragByPathToDom(Document doc,String xpath,String xmlFrag) throws XMLUtilException{
    if ((doc==null) || (xpath==null) || (xmlFrag==null)) return doc;
    try{
      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
      Node n = nl.nextNode();

      //1. Remove child nodes of n one by one.
      NodeList cNL = n.getChildNodes();
      if(cNL != null)
        while(cNL.getLength()>0)  n.removeChild(cNL.item(0));

      //2. Get child nodes of the xml fragment.
      Document subDoc = XMLUtil.xml2dom(xmlFrag);
      Element subRoot = subDoc.getDocumentElement();
      NodeList sNL = subRoot.getChildNodes();

      //3. Add the child nodes of the xml fragment as child of node n one by one.
      for(int i=0; i<sNL.getLength(); i++){
        Node importDocFrag = doc.importNode(sNL.item(i), true);
        n.appendChild(importDocFrag);
      }

      return doc;

    }catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }


  /**
   * This method will set the value of nodes specified by xpath.
   *
   * For Element nodes:
   * it will first remove all the nodes specified by the xpath;
   * then construct new nodes with values from a string array;
   * finally the new nodes will be added to the xml string at the positions
   * specified by xpath.
   * Assumption:
   * 1. All the child nodes specified by the XPath belong to the same parent node.
   * 2. The newly added nodes type is the same as the old ones.
   *
   * For attribute Node:
   * The old attribute value will be replaced by the new one so that the String
   * array (values) should only contain one element. If more array elements are
   * supplied, they will simply be ingnored.
   */
  public static String setValuesByPath(String xmlStr,String xpath,String[] values,String enc) throws XMLUtilException{
    if ((xmlStr==null) || (xpath==null) || (values==null)) return xmlStr;
    try{
      xmlStr = xmlStr.trim();
      Document doc = xml2dom(xmlStr);
      doc = setValuesByPathToDom(doc,xpath,values);
      return dom2xml(doc,enc);

    }catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

    /**
   * This method will set the value of nodes specified by xpath to a DOM object.
   *
   * For Element nodes:
   * it will first remove all the nodes specified by the xpath;
   * then construct new nodes with values from a string array;
   * finally the new nodes will be added to the DOM at the positions specified by xpath.
   * Assumption:
   * 1. All the child nodes specified by the XPath belong to the same parent node.
   * 2. The newly added nodes type is the same as the old ones.
   *
   * For attribute Node:
   * The old attribute value will be replaced by the new one so that the String
   * array (values) should only contain one element. If more array elements are
   * supplied, they will simply be ingnored.
   */
  public static Document setValuesByPathToDom(Document doc,String xpath,String[] values) throws XMLUtilException{
    if ((doc==null) || (xpath==null) || (values==null)) return doc;
    try{
      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
      Node n = nl.nextNode();

      if ((n instanceof Attr)&&(values.length>0)) {
        n.setNodeValue(values[0]);
        return doc;
      }
      else if (n instanceof Element) {
        Node parent = null;
        //1. remove all old children specified by XPath.
        parent = n.getParentNode();
        parent.removeChild(n);  //remove the first child.
        Node node = null;
        nl = XPathAPI.selectNodeIterator(doc, xpath);
        while((node = nl.nextNode()) != null) {
          //System.out.println("inner remove---");
          parent = node.getParentNode();
          parent.removeChild(node);
          nl = XPathAPI.selectNodeIterator(doc, xpath);
        }
        //2. add the new nodes as children of parent node.
        Element e = (Element) n;
        String tagName = e.getTagName();
        for(int i=0; i<values.length; i++){
          Element c = doc.createElement(tagName);
          Text t = doc.createTextNode(values[i]);
          //System.out.println("---values:"+values[i]);
          c.appendChild(t);
          parent.appendChild(c);
        }
        //3. convert dom back to xml string and return.
        return doc;
      }
      else{
        //System.out.println("xpath: " + xpath);
        //System.out.println("----content can't be located----");
        return null;
      }

    }catch (Exception e) {
      //e.printStackTrace();
      throw new XMLUtilException(e.getMessage());
    }
  }

  /**
   * Remove the child node specified by xpath.
   * <br> Assume there is at most ONE such child exists.
   */
  public static Document removeEmptyNode(Document doc, String xpath) throws XMLUtilException{
    if((doc==null) || (xpath==null)) return doc;
    try{
      NodeIterator nl = XPathAPI.selectNodeIterator(doc, xpath);
      Node n = nl.nextNode();
      if( n==null)  return doc;

      if( n instanceof Attr){
        Attr a = (Attr) n;
        Element e = a.getOwnerElement();
        String attrName = a.getName();
        e.removeAttribute(attrName);
      }

      if(n instanceof Element){
        Node parent = n.getParentNode();
        parent.removeChild(n);
      }

      return doc;
    } catch (Exception e){
      ExceptionBroadcast.print(e);
      throw new XMLUtilException(e.getMessage());
    }
  }
}