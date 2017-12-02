/* Generated by Together */

package org.yang.web.view.controls.jsStyle.navigationTree;
import java.util.ArrayList;
import java.util.Iterator;
import org.yang.web.view.controls.WebControl;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Collections;
import java.net.URLEncoder;

public abstract class NavigationNode extends WebControl implements Comparable
{
   public static final int BASE_STYLE = 0;
   public static final int ROOT_STYLE = 1;
   public static final int NODE_STYLE = 2;
   public static final int LEAF_STYLE = 3;

   // var FT_baseNodeCCS = '{font-family: Arial,Helvetica,sans-serif; font-size: 10pt; font-weight:bold; color:#990000}';
   // var FT_rootNodeCCS = '{font-family: Arial,Helvetica,sans-serif; font-size: 9pt; color:#000033; font-weight:bold}';
   // var FT_folderNodeCCS = '{font-family: Arial,Helvetica,sans-serif; font-size: 9pt}';
   // var FT_docNodeCCS = '{font-family: Arial,Helvetica,sans-serif,PMingLiU; font-size: 9pt}';
   // var FT_onNodeCCS = '{color:#FFFFFF ;background-color: #006699}';
   // var FT_linkedCCS = '<swapp:message key="appbase"/><swapp:message key="skin"/>/css/swbase_<swapp:message key="siteware.lang"/>.css';

   private String id = null;
   public void setId(String id) { this.id = id; }
   public String getId() { return id; }

   protected int level = 3;
   public void setLevel(int level) { this.level = level; }

   protected String codeBase = null;
   public void setCodeBase(String codeBase) { this.codeBase = codeBase; }

   protected String actionLink = null;
   public void setActionLink(String actionLink) { this.actionLink = actionLink; }
   public String getActionLink() { return actionLink; }

   protected String targetFrame = "rightFrame";
   public void setTargetFrame(String targetFrame) { this.targetFrame = targetFrame; }
   public String getTargetFrame() { return targetFrame; }

   protected String navigationActionLink = null;
   public void setNavigationActionLink(String navigationActionLink) { this.navigationActionLink = navigationActionLink; }

   protected String iconOpen = null;
   public void setIconOpen(String iconOpen) { this.iconOpen = iconOpen; }
   public String getIconOpen() { return iconOpen; }

   protected String iconClose = null;
   public void setIconClose(String iconClose) { this.iconClose = iconClose; }
   public String getIconClose() { return iconClose; }

   protected String caption = null;
   public void setCaption(String caption) { this.caption = caption; }
   public String getCaption() { return caption; }

   protected String captionColor = null;
   public void setCaptionColor(String captionColor) { this.captionColor = captionColor; }
   public String getCaptionColor() { return captionColor; }

   protected String description = null;
   public void setDescription(String description) { this.description = description; }
   public String getDescription() { return description; }

   protected boolean isTheLastNode = false;
   public void setIsTheLastNode(boolean isTheLastNode) { this.isTheLastNode = isTheLastNode; }
   public boolean getIsTheLastNode() { return isTheLastNode; }

   protected boolean isSubcontrolAutoSorted = false;
   public void setIsSubcontrolAutoSorted(boolean isSubcontrolAutoSorted) { this.isSubcontrolAutoSorted = isSubcontrolAutoSorted; }
   public boolean getIsSubcontrolAutoSorted() { return isSubcontrolAutoSorted; }

   protected boolean isExpanded = false;
   public void setIsExpanded(boolean isExpanded) { this.isExpanded = isExpanded; }
   public boolean getIsExpanded() { return isExpanded; }

   protected boolean isNewFramework = false;
   public void setIsNewFramework(boolean isNewFramework) { this.isNewFramework = isNewFramework; }
   public boolean getIsNewFramework() { return isNewFramework; }

   protected boolean onFocus = false;
   public void setOnFocus(boolean onFocus) { this.onFocus = onFocus; }
   public boolean getOnFocus() { return onFocus; }

   protected int type = 0;
   public void setType(int type) { this.type = type; }
   public int getType() { return type; }

   protected String title = null;
   public void setTitle(String title) { this.title = title; }
   public String getTitle() { return title; }
   
   private String markingIcon = null;
   public void setMarkingIcon (String markingIcon){this.markingIcon=markingIcon;}
   public String getMarkingIcon (){return markingIcon;}
    
   //protected boolean isParentTheLastNode = false;
   //public void setIsParentTheLastNode(boolean isParentTheLastNode) { this.isParentTheLastNode = isParentTheLastNode; }
   //public boolean getIsParentTheLastNode() { return isParentTheLastNode; }

   public NavigationNode()
   {
      super();
   }

   public int compareTo(Object obj)
   {
      if(null==obj||null==caption)
         return 0;
      NavigationNode target = (NavigationNode)obj;
      if(type==target.type)
         return caption.compareTo(target.caption);
      return type - target.type;
   }

   public static NavigationNode createNewNode(int style)
   {
      NavigationNode node = null;
      switch(style)
      {
         case NODE_STYLE:
         {
            node = new NodeStyleImpl();
            break;
         }
         case LEAF_STYLE:
         {
            node = new LeafStyleImpl();
            break;
         }
         case ROOT_STYLE:
         {
            node = new RootStyleImpl();
            break;
         }
         case BASE_STYLE:
         {
            node = new BaseStyleImpl();
            break;
         }
      }
      node.setType(style);
      return node;
   }

   protected abstract String getStyle();

   protected abstract void printMyIcon(JspWriter writer) throws IOException;

   protected abstract void printSpacer(JspWriter writer) throws IOException;

   protected abstract void printVertlineEnd(JspWriter writer) throws IOException;

   public String getParentId()
   {
      if(null!=parent)
         return parent.getId();
      return null;
   }

   public NavigationNode removeParent()
   {
      NavigationNode myParent = (NavigationNode)parent;
      parent = null;
      return myParent;
   }

   public void addChildNode(NavigationNode node)
   {
      addASubcontrol(node);
   }

   public NavigationNode removeChildNode(String id)
   {
      if(null==id)
        return null;
      for(int i=mySubcontrols.size()-1; i>=0; i--)
      {
         NavigationNode node = (NavigationNode)mySubcontrols.get(i);
         if(id.equals(node.getId()))
         {
            return (NavigationNode)mySubcontrols.remove(i);
         }
      }
      return null;
   }

   public void render(JspWriter writer) throws Exception
   {

      writer.print( "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" ID=\"" + id + "\">" );
      writer.print( "<tr>" );
      writer.print("<td valign=\"middle\" nowrap>");

      printVertline(writer);
      printVertlineEnd(writer);
      printMyIcon(writer);

      //printSpacer(writer);
      writer.print( "</td>" );
      writer.print( "<td valign=\"middle\" align=\"left\" nowrap " );
      if(onFocus)
         writer.print("bgcolor=\"#FFFF00\" ");
      writer.print(">");

      if(null!=actionLink)
      {
         if(isNewFramework)
            writer.print( "<a href    =\"" + navigationActionLink + "?actiontype=enter&targetId="+id+"&targetURL=" + URLEncoder.encode(actionLink) + "\" " );
         else
            writer.print( "<a href    =\"" + actionLink + "\" " );

         if(null!=targetFrame&&!isNewFramework)
            writer.print( " target  =\"" + targetFrame + "\"");

         if(null!=title)
            writer.print( " title  =\"" + title + "\"");

         writer.print(">" );
         writer.print( "<span class=\""+ getStyle() +"\">");

         if(null!=captionColor)
            writer.print("<font color=\""+captionColor+"\">");

         writer.print(caption);

         if(null!=captionColor)
            writer.print("</font>");
         
         if(null!=markingIcon)
            writer.print("<img src=\"/wf/default/images/"+markingIcon+"\">");

         writer.print("</span>" );
         writer.print( "</a>" );
      }
      else
      {
         writer.print( "<span class=\""+ getStyle() +"\">");
         if(null!=captionColor)
            writer.print("<font color=\""+captionColor+"\">");
         writer.print(caption);
         if(null!=captionColor)
            writer.print("</font>");
         writer.print("</span>" );
      }

      writer.print("</td>" );
      writer.print("</tr>" );
      writer.print("</table>" );

      if(isExpanded)
      {
         int size = 0;
         if(null!=mySubcontrols&&
            0<(size=mySubcontrols.size()))
         {
            if(isSubcontrolAutoSorted)
               Collections.sort(mySubcontrols);

            for(int i=0; i<size-1; i++)
               ((NavigationNode)mySubcontrols.get(i)).setIsTheLastNode(false);
            ((NavigationNode)mySubcontrols.get(size-1)).setIsTheLastNode(true);

            this.renderSubcontrols(writer);
         }
      }
   }

   private void printVertline(JspWriter writer) throws IOException
   {
      NavigationNode myParent = (NavigationNode)this.parent;
      if(null!=myParent)
      {
         myParent.printVertline(writer);
         String vertline = null;
         if(myParent.getIsTheLastNode())
            vertline = "/images/blank.gif";
         else
            vertline = "/images/vertline.gif";
         writer.print( "<img src=\"" + codeBase + vertline + "\"" );
         writer.print( " width=\"16\"" );
         writer.print( " height=\"22\">");
      }
   }
}