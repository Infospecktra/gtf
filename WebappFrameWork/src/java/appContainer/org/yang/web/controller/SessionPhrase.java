package org.yang.web.controller;

/*******************************************************
 *                                                     *
 *  @(#)PlainContent.java 1.0 99/12/20                 *
 *  Copyright 1999- by Screaming Media, Inc.,          *
 *  601 west 26 street 13 floor New York 10001, U.S.A. *
 *                                                     *
 *******************************************************/

public interface SessionPhrase
{
   //*** cookie
   public static final String COOKIE_DOMAIN     = "cookie.domain";
   public static final String COOKIE_USER       = "cookie.user";

   //*** login
   public static final String PASSPORT          = "org.yang.web.controller.keys.passport";

   //*** for object
   public static final String LOGIN_USER          = "LoginUser";
   public static final String COMPONENTSERVICE_MANAGER = "ComponentServiceManager";
// public static final String MESSAGE_RESOURCE    = "MessageResource";
   public static final String CURRENT_MESSAGE     = "CurrentMessage";
   public static final String MEDIA_CART          = "MediaCart";
   public static final String SESSION_BOUND       = "SessionBound";

   //*** for string
   public static final String LOGIN_DOMAIN        = "LoginDomain";
   public static final String CURRENT_SERVICE     = "CurrentService";
   public static final String OCCUPIED_STAGE      = "OccupiedStage";
   public static final String IS_SECTION_OCCUPIED = "IsSectionOccupied"; // "YES","NO"
   public static final String CURRENT_SECTION     = "CurrentSection";
   public static final String CURRENT_CID         = "CurrentCID";
   public static final String CURRENT_POS         = "CurrentPos";
   public static final String SUPER_DOMAIN        = "SuperDomain";
   public static final String SUPER_USER          = "SuperUser";

   public static final String DEFAULT_SERVICE     = "DefaultService";
}