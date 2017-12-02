package org.yang.web.controller;

import java.io.Serializable;
import javax.servlet.http.HttpServlet;

public abstract class BaseBean implements Serializable
{
   public static final String SESSION = "SESSION";
   public static final String REQUEST = "REQUEST";

   protected boolean isNew = true;
   public boolean getIsNew() { return isNew; }
   public void setIsNew(boolean isNew) { this.isNew = isNew; }

   abstract public void setBeanID(String bid);
   abstract public String getBeanID();
   abstract public void setBeanScope(String scope);
   abstract public String getBeanScope();
}