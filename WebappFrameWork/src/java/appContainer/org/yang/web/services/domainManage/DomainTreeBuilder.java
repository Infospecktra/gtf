/* Generated by Together */
package org.yang.web.services.domainManage;
import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationTree;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationNode;

public class DomainTreeBuilder implements WebControlBuilder
{
   private String domainIcon = null;
   private String userIcon   = null;

   public WebControl build(GenericBean bean) throws Exception
   {
      DomainTreeBean domainBean = (DomainTreeBean)bean;
      String appBase  = domainBean.getAppBase();
      String codeBase = domainBean.getGuiBase();

      NavigationTree panel = null;
      if(null==(panel=(NavigationTree)domainBean.getControl("domainTree")))
      {
         domainIcon = domainBean.calculateURL(domainBean.getCurrentServiceProperty("ServiceTree.Icon.Domain"));
         userIcon   = domainBean.calculateURL(domainBean.getCurrentServiceProperty("ServiceTree.Icon.User"));

         panel = createTree(domainBean, appBase + codeBase);
         domainBean.setControl("domainTree", panel);
      }

      return panel;
   }

   private NavigationTree createTree(DomainTreeBean domainBean, String codeBase)
   {
      NavigationNode root = NavigationNode.createNewNode(NavigationNode.BASE_STYLE);

      root.setId("root");
      root.setCaption("All Domains");
      root.setDescription("Domain Information");
      //root.setActionLink("/wf/projectNotes.wf?actiontype=accessDatasheet&targetPage=datasheet&projectType=" + currentTab);
      root.setType(NavigationNode.BASE_STYLE);
      root.setIsExpanded(true);
      root.setIconOpen(domainBean.calculateURL("/images/ico_key.gif"));
      root.setIconClose(domainBean.calculateURL("/images/ico_key.gif"));
      root.setCodeBase(codeBase);
      root.setLevel(0);
      root.setIsExpanded(true);
      root.setIsTheLastNode(true);


      try
      {
         String[] domains = domainBean.getAllDomainNames();
         int domainsSize = domains.length;
         NavigationNode domainNode = null;
         NavigationNode userNode = null;
         String[] users = null;

         for(int i=0; i<domainsSize; i++)
         {
            String projectNodeID = domains[i];
            domainNode = NavigationNode.createNewNode(NavigationNode.NODE_STYLE);
            domainNode.setId(projectNodeID);
            domainNode.setCaption(domains[i]);
            domainNode.setActionLink("/wf/domain.wf?actiontype=load&domainId=" + domains[i]);
            domainNode.setNavigationActionLink("domainTree.wf");
            domainNode.setType(NavigationNode.NODE_STYLE);
            domainNode.setIconOpen(domainIcon);
            domainNode.setIconClose(domainIcon);
            // the last node of this level
            domainNode.setCodeBase(codeBase);
            if(i==domainsSize-1)
               domainNode.setIsTheLastNode(true);
            domainNode.setLevel(1);
            domainNode.setParent(root);

            root.addChildNode(domainNode);

            users = domainBean.getAllUserNames(domains[i]);
            for(int j=0; j<users.length; j++)
            {
               userNode = NavigationNode.createNewNode(NavigationNode.LEAF_STYLE);
               userNode.setId(projectNodeID + "." + users[j]);
               userNode.setCaption(users[j]);
               userNode.setActionLink("/wf/userInfo.wf?actiontype=load&domainId=" + domains[j] + "&userId=" + users[j]);
               userNode.setType(NavigationNode.LEAF_STYLE);
               userNode.setIconOpen(userIcon);
               userNode.setIconClose(userIcon);
               userNode.setCodeBase(codeBase);
               userNode.setLevel(2);
               if(j==users.length-1)
                  userNode.setIsTheLastNode(true);
               userNode.setParent(domainNode);

               domainNode.addChildNode(userNode);
            }
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      NavigationTree tree = new NavigationTree();
      tree.setId("projectsTree");
      tree.setRoot(root);
      return tree;
   }
}