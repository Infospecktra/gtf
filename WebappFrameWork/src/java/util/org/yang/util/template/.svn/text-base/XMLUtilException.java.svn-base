package org.yang.util.template;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 * @testcase com.test.com.IMDSTrading.marketDecisionEngine.util.TestXMLUtilException
 */

public class XMLUtilException extends Exception{
  private Throwable cause = null;

  public XMLUtilException() {
    super();
  }

  public XMLUtilException(String message) {
    super(message);
  }

  public XMLUtilException(String message, Throwable cause) {
    super(message);
    this.cause = cause;
  }

  public Throwable getCause() {
    return cause;
  }

  public void printStackTrace() {
    super.printStackTrace();
    if (cause != null) {
      System.err.println("Caused by:");
      cause.printStackTrace();
    }
  }

  public void printStackTrace(java.io.PrintStream ps) {
    super.printStackTrace(ps);
    if (cause != null) {
      ps.println("Caused by:");
      cause.printStackTrace(ps);
    }
  }

  public void printStackTrace(java.io.PrintWriter pw) {
    super.printStackTrace(pw);
    if (cause != null) {
      pw.println("Caused by:");
      cause.printStackTrace(pw);
    }
  }

}