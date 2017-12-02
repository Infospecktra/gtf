package org.yang.util.xml;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//import com.sun.xml.tree.*;

public class DomManipulater
{
   Node doc  = null;
   DomManipulatingHandler dh = null;

   public DomManipulater(){}

   public void setDocument(Node doc)
   {
      if(doc==null)
         System.out.println("throw new NullPointerException()");

      this.doc = doc;
   }

   public void setHandler(DomManipulatingHandler dh)
   {
      if(dh==null)
         System.out.println("throw new NullPointerException()");

      this.dh = dh;
   }

   public void startToWalk()
   {
      dh.documentStart(doc);
      NodeList childnodes = doc.getChildNodes();
//System.out.println("In DM: " + doc);
      int      len        = childnodes.getLength();

      for (int i = 0; i < len; i ++)
      {
         walk(childnodes.item(i));
      }
      dh.documentEnd(doc);
   }

   public void walk(Node node)
   {
      if(dh.nodeStart(node))
      {
         NodeList childnodes = node.getChildNodes();
         int      len        = childnodes.getLength();

         for (int i = 0; i < len; i ++)
         {
            walk(childnodes.item(i));
         }
         dh.nodeEnd(node);
      }
   }
}