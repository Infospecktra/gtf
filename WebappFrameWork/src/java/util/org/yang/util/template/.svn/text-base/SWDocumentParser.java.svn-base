package org.yang.util.template;

/**
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestSWDocumentParser 
 */
public class SWDocumentParser
{

   private String [] document = null ;

   public SWDocumentParser ()
   {
   }
 
   public SWDocumentParser (String doc)
   {
      init (doc);
   } 

   public void init (String doc)
   {
      doc = doc.trim().replace('\r',' ').replace('\t',' ');

      boolean flag = true ;

      while (flag)
      {
         int pivotX = doc.indexOf("\n\n") ;
         int pivotY = doc.indexOf("\n ") ;

         int pivot = -1 ;

         if (pivotX >0)
         {
            if (pivotY>0)
               pivot = (pivotX>pivotY)?pivotY:pivotX ;
            else
               pivot = pivotX ;
         }
         else 
            pivot = pivotY ;
         
         if (pivot != -1)
            doc = extract (doc, pivot);
         else 
         {
            addElement (doc);
            flag = false ;
         }
      }
   }

   public String [] getDocument ()
   {
      return document ;
   }
   
   private String extract (String doc, int pivot)
   {
      String paragraph = doc.substring(0,pivot);
      addElement(paragraph);
      return doc.substring(pivot).trim();
   }


   private void addElement (String paragraph)
   {
      int len = (document == null)?0:document.length ;
      String[] tmp = new String[len+1] ;
      if (len >0)
         System.arraycopy (document,0,tmp,0,document.length);
      tmp[len] = paragraph ;
      document = tmp ;
      tmp = null ;
   }
}