package com.sakindudehipitiya.lil.data.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.Connection;

public class DatabaseUtils {
  private static final String URL = "jdbc:postgresql://localhost:5432/localdb";
  private static final String USERNAME = "localdbuser";
  private static final String PASSWORD = "P@ssw0rd!";
  private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());
  private static final String exceptionFormat = "exception in %s, message: %s, code: %s";
  private static Connection connection;

  public static Connection getConnection(){
    if(connection==null){
      synchronized(DatabaseUtils.class){
        if(connection == null){
          try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          }catch(SQLException e){
            handleSqlException("DatabaseUtils.getConnection", e, LOGGER);
          }
        }
      }
    }
    return connection;
  }

  public static void handleSqlException(String method, SQLException e, Logger log) {
    log.warning(String.format(exceptionFormat, method, e.getMessage(), e.getErrorCode()));
    throw new RuntimeException(e);
  }
}
