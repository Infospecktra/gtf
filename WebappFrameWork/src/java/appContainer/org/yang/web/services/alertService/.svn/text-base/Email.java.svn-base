package org.yang.web.services.alertService;

import java.net.URL;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.mail.Session;
import org.yang.services.time.TimeKeeper;
import org.yang.util.SMUtility;

public class Email extends AlertDeliveryMethod
{
   private Context context ;
   
   protected String Sender = "Actrellis Integration Server" ;
   protected String Mail_Source_Name = "java:/Mail" ;
   
   private URL background_img_url ;
   
   /**
	 * Constructor. Get resources/Email.properties from super class. <br>
	 * Instantiate NamingContext for accecssing Mail session.
	 */	   
   public Email ()
   {
   	  super () ;
   	  try
   	  {
         context = new InitialContext () ;
   	  }
   	  catch (NamingException ne)
   	  {
         //com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(ne);
   	  }
   }
   
   /**
	 * Override the method, return the name "Email".
	 *
	 * @return 	return "Email" 
	 */   
   public String getName ()
   {
   	  return "Email" ;
   }
   
   private Session getSession (String naming) throws NamingException
   {
      javax.mail.Session session ;
      try
      {
         synchronized (context)
         {
            session = (javax.mail.Session) context.lookup (naming);
         }
      }
      catch (NamingException ne)
      {
         //System.out.println (naming + " lookup fail, check jboss.jcml.");
         throw ne ;
      }
      return session ;
   }

	/**
	* Send the alert.
	*
	* @param	receiver - those who receive the alert.
	*		log	 - the alert message.
	* @see	com.screamingmedia.siteware.services.alertService.AlertReceiver
	* @see	com.screamingmedia.siteware.webService.reports.buslogic.cat.sys.SysLogObj
	*/	   		   	
   public void deliver(Alert alert) throws AlertSendingException
   {
      try
      {
         String sender   = Sender ;
         String jndi     = Mail_Source_Name ;
         String img_name = "headpagebg.gif";
		
         if (background_img_url == null)
         {
            background_img_url = this.getClass().getResource (img_name);
            //background_img_url = Thread.currentThread().getContextClassLoader().getResource ("resources/" + img_name);
         }

         Delivery delivery = null;
         javax.mail.Session session = getSession  (jndi);
         MimeMessage message =  null;
         for(int i=0; i<deliveryList.size(); i++)
         {
            delivery = (Delivery)deliveryList.get(i);
            message = new MimeMessage(session);
            message.setSubject("BioLynx Alert Message");
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                                  InternetAddress.parse(delivery.getProperty("user.email"),
                                  false));
            message.setHeader("Content-Type","text/html");
            message.setHeader("X-Mailer", "SMTPService");
            message.setSentDate(TimeKeeper.getInstance().getTime());
			 
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent (getMessageBody(delivery, alert), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
			
            // Part two is attachment
            //messageBodyPart = new MimeBodyPart();
            //messageBodyPart.setDataHandler(new DataHandler(background_img_url));
            //messageBodyPart.setFileName(img_name);
            //multipart.addBodyPart(messageBodyPart);
			
            // Put parts in message
            message.setContent(multipart);
            Transport.send(message);
         }
      }
      catch (Exception e)
      {
         //com.screamingmedia.swapi.utility.debug.ExceptionBroadcast.print(e);
         throw new AlertSendingException("Fail sending mail alert message:" + e.getMessage());
      }
   }
   
   private String getMessageBody (Delivery delivery, Alert alert)
   {
   	  String body = "<html><head><title>SITEWare Enterprise :: Alert Service</title></head><body bgcolor=\"#FFFFFF\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" background=\"headpagebg.gif\"><tr><td align=\"right\">&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;</td></tr></table><div align=\"center\"><h3><font color=\"#1D459D\">Alert Service.</font></h3><table width=\"50%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" ;
      body += "<table width=\"50%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" ;
   	  body += "<tr><td colsspan=\"2\"><font color=\"#1D459D\">" + "Attention: " + delivery.getProperty("user.firstname") + "&nbsp;" + delivery.getProperty("user.lastname") + "<br>The following system alert was generated by the BioLynx System." + "</font></td></tr>" ;
   	  body += "</table><br>" ;
   	
   	  body += "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" bordercolorlight=\"#CCCCCC\" bordercolor=\"#CCCCCC\" bordercolordark=\"#FFFFFF\" bgcolor=\"#EAEAEA\" align=\"center\">" ;
   	
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>Type:</b></td><td>" + alert.getType() + "</td></tr>" ;
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>Code:</b></td><td>" + alert.getCode() + "</td></tr>" ;
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>Time:</b></td><td>"+ alert.getMonth()+ "." +alert.getDay()+ "." + alert.getYear() + ":" + alert.getTime() + "&nbsp" + alert.getTimeZone() + "</td></tr>" ;
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>Source:</b></td><td>" + alert.getSourceID() + "</td></tr>" ;
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>UserID:</b></td><td>" + alert.getUserID() + "</td></tr>" ;
   	  String[] msgs = SMUtility.splitByToken(alert.getMessage(), "|");
   	  body += "<tr><td align=\"right\" class=\"bigger\"><b>Message:</b></td><td>";
   	
   	  for(int i=0; i<msgs.length; i++)
   	     body += "<p>" + msgs[i] + "</p>";
   	
   	  body += "</td></tr>" ;
   	  body += "</table></div></body></html>" ;
   	
//   	try
//   	{
//   	   return (new String(body.getBytes("iso-8859-1"),"iso-8859-1")) ;
//   	}
//   	catch(Exception e)
//   	{
   	   return body;	
//   	}
   }
}