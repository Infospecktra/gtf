/* Generated by Together */
package org.yang.customized.gtf.services.projectManager.web;

import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.UIForm;
import org.yang.web.view.controls.jsStyle.TextField;
import org.yang.web.view.controls.jsStyle.PassElement;
import org.yang.web.view.controls.jsStyle.SelectElement;
import org.yang.web.view.controls.jsStyle.TextAreaElement;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationNode;
import java.util.Iterator;
import org.yang.services.accountmgr.Group;
import org.yang.services.accountmgr.User;
import org.yang.web.view.controls.jsStyle.navigationTree.NavigationTree;
import java.util.Collection;
import java.util.Map;
import org.yang.services.servicemgr.ServiceDescriptor;
import org.yang.web.services.accountManage.AccountManagerBean;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.Stage;
import org.yang.web.view.controls.jsStyle.panel.GenericPanel;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;

public class ActiveProjectsTreeBuilder implements WebControlBuilder
{
   private String projectsOpen = null;
   private String projectsClose = null;
   private String projectIcon = null;
   private String stagesOpen = null;
   private String stagesClose = null;
   private String stageIcon = null;

   String[] allTabs  = null;
   String currentTab = null;

   public WebControl build(GenericBean bean) throws Exception
   {
      ActiveProjectsTreeBean projectsBean = (ActiveProjectsTreeBean)bean;
      String appBase = projectsBean.getAppBase();
      String codeBase = projectsBean.getGuiBase();
      allTabs  = projectsBean.getAllAvailableProjectTypes();
      currentTab = projectsBean.getTargetType();

      GenericPanel panel = null;
      if(null==(panel=(GenericPanel)projectsBean.getControl("projectsTreePanel")))
      {
         projectsOpen = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.projects.open"));
         projectsClose = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.projects.close"));
         projectIcon = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.project"));

         stagesOpen = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.stages.open"));
         stagesClose = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.stages.close"));
         stageIcon = projectsBean.calculateURL(projectsBean.getCurrentServiceProperty("ServiceTree.Icon.stage"));

         panel = createPanel(projectsBean, appBase, codeBase);
         projectsBean.setControl("projectsTreePanel", panel);
      }

      return panel;
   }

   private GenericPanel createPanel(ActiveProjectsTreeBean projectsBean, String appBase, String codeBase)
   {

      GenericPanel panel = new GenericPanel();
      panel.setId("projectsTreePanel");
      panel.setText("Managing ongoing project list.");
      panel.setCodeBase(codeBase);
      panel.setColor("#C0C0C0");
      panel.setActionLink(appBase + "/projectsList.wf");

      TabPanelElement typeTab = null;
      for(int i=0; i<allTabs.length; i++)
      {
         typeTab = new TabPanelElement();
         typeTab.setId(allTabs[i]);
         typeTab.setText(allTabs[i]);
         typeTab.setCodeBase(appBase + codeBase);

         if(allTabs[i].equals(currentTab))
         {
            // project list
            NavigationTree tree = createTree(projectsBean, appBase + codeBase);
            tree.openNodes(projectsBean.getTargetID());
            typeTab.setColor("#F0F0F0");
            typeTab.setOnFocus(true);
            typeTab.addASubcontrol(tree);
         }
         else
         {
            typeTab.setTabSwitchingLink("/wf/projectsTree.wf?actiontype=changeType&targetType=" + allTabs[i]);
            typeTab.setOnFocus(false);
         }

         panel.addASubcontrol(typeTab);
      }

      return panel;
   }

   private NavigationTree createTree(ActiveProjectsTreeBean projectsBean, String codeBase)
   {
      NavigationNode root = NavigationNode.createNewNode(NavigationNode.BASE_STYLE);

      root.setId("root");
      root.setCaption(projectsBean.getDomain());
      root.setDescription("Account Information");
      root.setActionLink("/wf/projectNotes.wf?actiontype=accessDatasheet&targetPage=datasheet&projectType=" + currentTab);
      root.setType(NavigationNode.BASE_STYLE);
      root.setIsExpanded(true);
      root.setIconOpen(projectsBean.calculateURL("/images/domain.gif"));
      root.setIconClose(projectsBean.calculateURL("/images/domain.gif"));
      root.setCodeBase(codeBase);
      root.setLevel(0);
      root.setIsExpanded(true);
      root.setIsTheLastNode(true);

      NavigationNode projectsRoot = NavigationNode.createNewNode(NavigationNode.ROOT_STYLE);
      projectsRoot.setId("root.projects");
      projectsRoot.setCaption("Projects");
      projectsRoot.setNavigationActionLink("projectsTree.wf");
      projectsRoot.setType(NavigationNode.ROOT_STYLE);
      projectsRoot.setIsExpanded(true);
      projectsRoot.setIconOpen(projectsOpen);
      projectsRoot.setIconClose(projectsClose);
      projectsRoot.setCodeBase(codeBase);
      projectsRoot.setLevel(1);
      projectsRoot.setIsExpanded(false);
      projectsRoot.setParent(root);

      root.addChildNode(projectsRoot);

      NavigationNode stagesRoot = NavigationNode.createNewNode(NavigationNode.ROOT_STYLE);
      stagesRoot.setId("root.stages");
      stagesRoot.setCaption("Stages");
      stagesRoot.setNavigationActionLink("projectsTree.wf");
      stagesRoot.setType(NavigationNode.ROOT_STYLE);
      stagesRoot.setIsExpanded(true);
      stagesRoot.setIconOpen(stagesOpen);
      stagesRoot.setIconClose(stagesClose);
      stagesRoot.setIsTheLastNode(true);
      stagesRoot.setCodeBase(codeBase);
      stagesRoot.setLevel(1);
      stagesRoot.setIsExpanded(false);
      // the last node of current level
      stagesRoot.setIsTheLastNode(true);
      stagesRoot.setParent(root);

      root.addChildNode(stagesRoot);

      try
      {
         String projectsRootID = projectsRoot.getId();
         String stagesRootID = stagesRoot.getId();

         Project[] projects = projectsBean.getAllAvailableProjects();
         int projectsSize = projects.length;
         NavigationNode projectNode = null;
         String pid = null;
         NavigationNode stageNode = null;
         Stage stage = null;
         String sid = null;

         for(int i=0; i<projectsSize; i++)
         {
            pid = projects[i].getId();
            String projectNodeID = projectsRootID + "." + pid;
            projectNode = NavigationNode.createNewNode(NavigationNode.NODE_STYLE);
            projectNode.setId(projectNodeID);
            projectNode.setCaption(projects[i].getName());
            projectNode.setActionLink("/wf/projectNotes.wf?actiontype=load&id=" + pid + "&projectType=" + currentTab + "&targetPage=application");
            projectNode.setNavigationActionLink("projectsTree.wf");
            projectNode.setType(NavigationNode.NODE_STYLE);
            projectNode.setIconOpen(projectIcon);
            projectNode.setIconClose(projectIcon);
            // the last node of this level
            projectNode.setCodeBase(codeBase);
            if(i==projectsSize-1)
               projectNode.setIsTheLastNode(true);
            projectNode.setLevel(2);
            projectNode.setParent(projectsRoot);

            projectsRoot.addChildNode(projectNode);

            Stage[] stages = projectsBean.getAllAvailableStagesUnderProject(pid);
            for(int j=0; j<stages.length; j++)
            {
               sid = stages[j].getId();
               String stageNodeID = projectNodeID + "." + sid;
               stageNode = NavigationNode.createNewNode(NavigationNode.LEAF_STYLE);
               stageNode.setId(stageNodeID);
               stageNode.setCaption(stages[j].getName());
               stageNode.setActionLink("/wf/stageNotes.wf?actiontype=load&projectId=" + pid + "&name=" + stages[j].getName() + "&projectType=" + currentTab);
               stageNode.setType(NavigationNode.LEAF_STYLE);
               stageNode.setIconOpen(stageIcon);
               stageNode.setIconClose(stageIcon);
               stageNode.setCodeBase(codeBase);
               stageNode.setLevel(3);
               //if(i==projectsSize-1)
               //  stageNode.setIsParentTheLastNode(true);
               // the last node of this level
               if(j==stages.length-1)
                  stageNode.setIsTheLastNode(true);
               stageNode.setParent(projectNode);

               projectNode.addChildNode(stageNode);
            }
         }

         Stage[] stages = projectsBean.getAllAvailableStages();
         int stagesSize = stages.length;
         String sname = null;
         for(int i=0; i<stages.length; i++)
         {
            sname = stages[i].getName();
            stageNode = NavigationNode.createNewNode(NavigationNode.NODE_STYLE);
            String stageNodeID = stagesRootID + "." + sname;
            stageNode.setId(stageNodeID);
            stageNode.setCaption(stages[i].getName());
            //stageNode.setActionLink("/wf/stageNotes.wf?actiontype=load&id=" + sid);
            stageNode.setNavigationActionLink("projectsTree.wf");
            stageNode.setType(NavigationNode.LEAF_STYLE);
            stageNode.setIconOpen(stageIcon);
            stageNode.setIconClose(stageIcon);
            stageNode.setCodeBase(codeBase);
            //stageNode.setIsParentTheLastNode(true);
            // the last node of this level
            if(i==stagesSize-1)
               stageNode.setIsTheLastNode(true);
            stageNode.setLevel(2);
            stageNode.setParent(stageNode);

            stagesRoot.addChildNode(stageNode);

            projects = projectsBean.getAllAvailableProjectsHaveStage(sname);
            for(int j=0; j<projects.length; j++)
            {
               pid = projects[j].getId();
               String projectNodeID = stageNodeID + "." + pid;
               projectNode = NavigationNode.createNewNode(NavigationNode.LEAF_STYLE);
               projectNode.setId(projectNodeID);
               projectNode.setCaption(projects[j].getName());
               projectNode.setActionLink("/wf/stageNotes.wf?actiontype=load&projectId=" + pid + "&name=" + sname + "&projectType=" + currentTab);
               projectNode.setType(NavigationNode.LEAF_STYLE);
               projectNode.setIconOpen(projectIcon);
               projectNode.setIconClose(projectIcon);
               projectNode.setCodeBase(codeBase);
               projectNode.setLevel(3);
               //if(i==stagesSize-1)
               //   projectNode.setIsParentTheLastNode(true);
               // the last node of this level
               if(j==projects.length-1)
                  projectNode.setIsTheLastNode(true);
               projectNode.setParent(stageNode);

               stageNode.addChildNode(projectNode);
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