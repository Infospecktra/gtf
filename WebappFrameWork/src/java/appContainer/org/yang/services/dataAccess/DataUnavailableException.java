package org.yang.services.dataAccess;

/**
 * Title:        Siteware Enterprise
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      ScreamingMedia
 * @author Lei Liu
 * @version 1.0
 */


public class DataUnavailableException extends Exception{
   public DataUnavailableException() {
        super();
    }

    public DataUnavailableException(String msg) {
        super(msg);
    }
}