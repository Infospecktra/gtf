package org.yang.util.xml;
import org.w3c.dom.Node;


public interface DomManipulatingHandler
{
   public void documentStart(Node document);

   public void documentEnd(Node document);

   public boolean nodeStart(Node node);

   public void nodeEnd(Node node);
}