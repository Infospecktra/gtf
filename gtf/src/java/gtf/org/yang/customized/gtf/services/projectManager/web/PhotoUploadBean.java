package org.yang.customized.gtf.services.projectManager.web;
import org.yang.web.applicationContainer.SecuredBean;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PhotoUploadBean extends SecuredBean
{
   static final long serialVersionUID = 4711296382979764908L;

  /**
    *  form set and get methods
    */
   private String altPage = null;
   private String mimeType = null;

   private String photoId ="";
   public void setPhotoId(String photoId) { this.photoId = photoId; }
   public String  getPhotoId(){return photoId;}

   public PhotoUploadBean()
   {
      super();
   }

   /*******************************************
    *   implements methods from GenericBean   *
    *******************************************/

   public void init() throws Exception
   {
      //mimeType = this.prop.getProperty("mimeType");
   }

   public void ensureResource() throws Exception
   {
      //   throw new Exception("User manager is null.");
   }

   public void destroy() {}

   public void beforeSetParameters()
   {
      altPage = null;
   }

   protected String altPage()
   {
      return altPage;
   }

   /*****************************
    *   my data access method   *
    *****************************/



   /****************************************
    *             All my actions           *
    ****************************************/

   public void upload() throws ProjectDataAccessException {}

   public void browse() throws ProjectDataAccessException
   {
      try
      {
         String photoUrl = this.prop.getProperty("working-url") + photoId;
         System.out.println("Photo URL :" + photoUrl);
         this._response.sendRedirect(photoUrl+".jpg");
         mimeType = this.prop.getProperty("mimeType");
         if(null!=mimeType)
            this._response.setContentType(mimeType);
         altPage = "no_page";
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException("Redirect fail.");
      }
   }

   public void confirm() throws ProjectDataAccessException
   {
      altPage = "confirm_upload";
   }

   public void delete() throws ProjectDataAccessException
   {
      System.out.println("I'm here .............................");
   }
}