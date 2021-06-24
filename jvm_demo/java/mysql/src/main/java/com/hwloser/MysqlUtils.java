package com.hwloser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUtils {

  public static Connection buildConnection(String connectionUrl, String userName, String passwd)
      throws ClassNotFoundException, SQLException {
    // load com.mysql.cj.jdbc.Driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection c = DriverManager.getConnection(connectionUrl, userName, passwd);
    return c;
  }
}
