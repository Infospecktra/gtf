package org.yang.services.accountmgr;
import java.io.Serializable;

/**
 * <p>Title: actrellis</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: screamingmedia inc</p>
 * @author Lei Liu
 * @version 1.0
 * @testcase org.test.org.yang.services.accountmgr.TestSignOnUser
 */

public class SignOnUser  implements Serializable
{
   static final long serialVersionUID = 4711296382979765012L;
  public String userID;
  public String sessionID;

}