package org.yang.util.template;

import java.io.*;
import java.util.*;
import java.net.*;

//import com.sun.xml.tree.*;
import org.w3c.dom.*;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestDomManipulater 
 */
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