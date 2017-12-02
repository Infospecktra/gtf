/* Generated by Together */

package org.yang.services.jboss.messageBus;

import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.Name;
import javax.naming.Context;
import org.jboss.util.ServiceMBeanSupport;
import java.util.Properties;
import org.yang.services.messageBus.MessageBus;
import org.apache.log4j.Category;

public class MessageBusService extends ServiceMBeanSupport implements MessageBusServiceMBean
{
   static final long serialVersionUID = 3439536529457252199L;

   /** Instance logger. */
   private transient Category log = Category.getInstance(this.getClass());

   private String mapFile = null;
   public void setRouteMapFile(String mapFile) { this.mapFile=mapFile; }
   public String getRouteMapFile() { return mapFile; }

   private String busName = null;
   public void setBusName(String busName) { this.busName=busName; }
   public String getBusName() { return busName; }
   
   private String busClassName = null;
   public void setBusClassName(String busClassName) { this.busClassName=busClassName; }
   public String getBusClassName() { return busClassName; }

   public void init() throws java.lang.Exception {}

   public void start() throws java.lang.Exception
   {
      try
      {
         initializeBus();
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }
   }

   public void stop() {}
   
   public void destroy() {}

   public String getName()
   {
      return "MessageBusService";
   }
   
   private void initializeBus() throws NamingException
   {
      // Bind in JNDI
      String jndiname = busName;
      Context ctx = new InitialContext();
      Properties env = new Properties();     
      env.setProperty(MessageBus.BUS_CLASS, busClassName);
      env.setProperty(MessageBus.ROUTE_MAP, mapFile);
      bind(ctx, jndiname, MessageBus.getMessageBus(env));
      log.info("[initializeBus] bound adapter GHMessageBus to " + jndiname);
   }

   private void bind(Context ctx, String name, Object val) throws NamingException 
   {
      // Bind val to name in ctx, and make sure that all intermediate contexts exist
      Name n = ctx.getNameParser("").parse(name);
      while (n.size() > 1) 
      {
         String ctxName = n.get(0);
         System.out.println("Context name : " + ctxName);
         try 
         {
            ctx = (Context)ctx.lookup(ctxName);
         }
         catch (NameNotFoundException e) 
         {
            ctx = ctx.createSubcontext(ctxName);
         }
         n = n.getSuffix(1);
      }
      ctx.bind(n.get(0), val);
   }
   
   private void showTree(String indent, Context ctx, int depth, int maxDepth) throws javax.naming.NamingException
   {
       if( depth == maxDepth )
           return;
       javax.naming.NamingEnumeration enum = ctx.list(indent);
       while (enum.hasMoreElements())
       {
           javax.naming.NameClassPair ncp = (javax.naming.NameClassPair)enum.next();
           System.out.println(indent+ncp);
           if (ncp.getClassName().indexOf("Context") != -1)
              showTree(indent+ncp.getName()+"/", (Context)ctx.lookup(ncp.getName()), depth+1, maxDepth);
       }
   }   
}