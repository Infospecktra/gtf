package org.yang.util.xml;

import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
//import com.sun.xml.tree.*;

public class DomExtractingHandler implements DomManipulatingHandler
{
   private ArrayList thepath = null;
   private int       pathlen = 0;
   private ArrayList nodes   = null;
   private int       scope   = 0;
   private boolean   isStop  = false;

   public DomExtractingHandler()
   {
      thepath = new ArrayList();
   }

   public void setNodePath(String path)
   {
      if(null==path)
         path = "/";

      StringTokenizer tok     = new StringTokenizer(path, "/");
      thepath = new ArrayList();

      while (tok.hasMoreTokens())
      {
         thepath.add(tok.nextToken());
      }

      pathlen = thepath.size();
//System.out.println(path+" "+pathlen);
   }

  public void documentStart(Node document)
  {
     nodes = new ArrayList();
     if(pathlen==0)
     {
        nodes.add(document);
        isStop = true;
     }
//System.out.println("Document Start :" + scope + " " +isStop);
  }

  public void documentEnd(Node document)
  {
//System.out.println("Document End :" + scope);
  }

  public boolean nodeStart(Node node)
  {
     scope++;

     if(isStop)
        return true;

     if(pathlen>=scope &&
        node.getNodeName().equals((String)thepath.get(scope-1)))
     {
     	if(pathlen==scope)
     	{
           nodes.add(node);
//System.out.println("Get Node :" + node.getNodeName());
        }
     }
     return true;
//System.out.println("Node Start :" + scope);
  }

  public void nodeEnd(Node node)
  {
     scope--;
//System.out.println("Node End :" + scope);
  }

  public ArrayList getMatchedNodes()
  {
     return nodes;
  }

  public ArrayList getMatchedNodes(Properties attributes)
  {
      ArrayList matched_nodes = new ArrayList();
      boolean   matched = true;

//System.out.println(attributes);

      for(int i=0; i<nodes.size(); i++)
      {
//System.out.println("<" + i + ">" + ((Node)nodes.get(i)).getNodeName());
         NamedNodeMap atts = ((Node)nodes.get(i)).getAttributes();
         if(null!=atts && 0!=atts.getLength())
         {
            for(int j=0; j<atts.getLength(); j++)
            {
               Node attribute = atts.item(j);
               String value = attributes.getProperty(attribute.getNodeName());
//System.out.println(attribute.getNodeName() + ":" + attribute.getNodeValue());

               if(null!=value&&!value.equals(attribute.getNodeValue()))
               {
                  matched = false;
                  break;
               }
            }

            if(matched)
            {
               matched_nodes.add(nodes.get(i));
            }
         }
         matched = true;
      }

//System.out.println(matched_nodes.size());
      return matched_nodes;
  }

   public ArrayList getAttributesOfMatchedNodes()
   {
      ArrayList  attributes = new ArrayList();
      Properties temp       = null;

      for(int i=0; i<nodes.size(); i++)
      {
         temp = new Properties();
         NamedNodeMap atts = ((Node)nodes.get(i)).getAttributes();
         if(null!=atts && 0!=atts.getLength())
         {
            for(int j=0; j<atts.getLength(); j++)
            {
               Node attribute = atts.item(i);
               temp.setProperty(attribute.getNodeName(),
                                attribute.getNodeValue());
            }
         }
         attributes.add(temp);
      }
      return attributes;
   }
}