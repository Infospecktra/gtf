/* Generated by Together */
package org.yang.customized.gtf.services.messageManager.web;

import org.yang.web.view.controls.WebControlBuilder;
import org.yang.web.view.controls.WebControl;
import org.yang.web.controller.GenericBean;
import org.yang.web.view.controls.jsStyle.navigationList.NavigationList;
import org.yang.web.view.controls.jsStyle.navigationList.ListItem;
import org.yang.web.view.controls.jsStyle.ButtonElement;
import org.yang.web.view.controls.jsStyle.navigationList.List;
import java.util.Collection;
import java.util.Iterator;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.util.DateFormatter;
import org.yang.web.view.controls.jsStyle.HiddenField;
import org.yang.web.view.controls.jsStyle.panel.GenericPanel;
import org.yang.web.view.controls.jsStyle.panel.TabPanelElement;
import org.yang.customized.gtf.services.messageManager.web.MessageListBean;
import org.yang.customized.gtf.services.dataAccess.Message;
import org.yang.web.view.controls.jsStyle.navigationList.MessageList;
import java.util.Date;
import org.yang.customized.gtf.services.messageManager.MessageManager;


public class MessageListBuilder implements WebControlBuilder
{
   public WebControl build(GenericBean bean) throws Exception
   {
      MessageListBean scheduleBean = (MessageListBean)bean;
      String codeBase = scheduleBean.getAppBase() + scheduleBean.getGuiBase();

      GenericPanel panel = null;
      //if(null==(list=(NavigationList)scheduleBean.getControl("projectList")))
      //{
         panel = createPanel(scheduleBean, scheduleBean.getAppBase(), codeBase);
      //   scheduleBean.setControl("projectList", list);
      //}

      return panel;
   }

   private GenericPanel createPanel(MessageListBean messageListBean, String appBase, String codeBase)
   {
      String[] allTabs  = messageListBean.getAllAvailableTypes();
      String currentTab = messageListBean.getType();

      GenericPanel panel = new GenericPanel();
      panel.setId("messagelist_form");
      panel.setText("Managing messages.");
      panel.setCodeBase(codeBase);
      panel.setColor("#C0C0C0");
      panel.setActionLink(appBase + "/messageList.wf");

      TabPanelElement typeTab = null;
      for(int i=0; i<allTabs.length; i++)
      {
         // project list
         typeTab = new TabPanelElement();
         typeTab.setText(allTabs[i]);
         typeTab.setCodeBase(codeBase);
         typeTab.setTabSwitchingLink("/wf/messageList.wf?actiontype=changeType&type=" + allTabs[i]);

         if(allTabs[i].equals(currentTab))
         {
            typeTab.addASubcontrol(createList(messageListBean, appBase, codeBase, currentTab));
            typeTab.setOnFocus(true);
         }
         else
            typeTab.setOnFocus(false);

         panel.addASubcontrol(typeTab);
      }

      return panel;
   }

   private NavigationList createList(MessageListBean messageBean, String appBase, String codeBase, String currentTab)
   {
      NavigationList list = new NavigationList(new List());
      list.setTitle(null);
      list.setCodeBase(codeBase);
      list.setId("messageList");
      //list.setActionLink(appBase + "/projectList.wf");

      Message[] messages = messageBean.getAllAvailableMessages();
      MessageList item = null;
      String domain = null;
      String user = null;
      long id = 0;
      for(int i=0; i<messages.length; i++)
      {
         id = messages[i].getId();
         item = new MessageList();
         item.setId(id+"");
         domain = messages[i].getDomainFrom();
         user = messages[i].getFromUser();
         if(MessageManager.RECEIVE.equals(currentTab))
            item.setText("Title:" + messages[i].getTitle() +
                         "<br>From:"+ messageBean.whoIsIt(domain, user) + "(" + domain + ")");
         else
            item.setText("Title:" + messages[i].getTitle());

         item.setControlName("targetIds");
         //item.setDisplayedDate(DateFormatter.getDateTimeString(new Date(messages[i].getId()), "yyyy-MM-dd'T'HH:mm:ss"));
         item.setDisplayedDate(messages[i].getTheDate()+"T"+messages[i].getTheTime());
         item.setActionLink(appBase + "/message.wf?actiontype=load&id=" + id );

         list.addListItem(item);
      }

      for(int j = messages.length; j<20; j++)
      {
         list.addListItem(new ListItem());
      }

      ButtonElement button1 = new ButtonElement();
      button1.setAction("javascript:setCheckBoxAll('projectlist_form','targetIds',1)");
      button1.setOnIcon(codeBase + "/images/ico_chkall.gif");
      button1.setOffIcon(codeBase + "/images/ico_chkall.gif");
      button1.setId("checkAll");
      button1.setName("checkAll");
      button1.setAlt("Check all");
      list.addButton(button1);
      ButtonElement button2 = new ButtonElement();
      button2.setAction("javascript:setCheckBoxAll('projectlist_form','targetIds',0)");
      button2.setOnIcon(codeBase + "/images/ico_unchkall.gif");
      button2.setOffIcon(codeBase + "/images/ico_unchkall.gif");
      button2.setId("uncheckAll");
      button2.setName("uncheckAll");
      button2.setAlt("Uncheck all");
      list.addButton(button2);

      return list;
   }
}