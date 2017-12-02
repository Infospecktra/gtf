package org.yang.services.dbService;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.yang.services.config.Config;
import org.yang.util.xml.*;
import org.yang.util.SMFile;
import org.yang.util.ExceptionBroadcast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @testcase org.test.org.yang.services.dbService.TestRDBMS 
 */
public interface RDBMS
{
    void checkDBConnection() throws DataAccessException;
    void checkDBTables() throws DataAccessException;
    Connection getDBConnection()  throws DataAccessException;
    void commit(Connection conn);
    void abort(Connection conn);
    void onException(Exception e);
    void close();
    // will delete later
    boolean executeUpdate(String sql) throws DataAccessException;
    void executeQuery(String sql)  throws DataAccessException;
    boolean executeUpdate(String sql, String[] values) throws DataAccessException;
    void commitAndCloseConnection(Connection conn)  throws DataAccessException;
}