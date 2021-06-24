package com.hwloser;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestConnect {

  private static final String connectionUrl = "jdbc:mysql://archlinux:3306/dist_lock";
  private static final String userName = "hive";
  private static final String passwd = "hive";


  public static void main(String[] args) throws Exception {
    Connection c = MysqlUtils.buildConnection(connectionUrl, userName, passwd);
    CallableStatement cs = c.prepareCall("show databases");
    System.out.println(cs);

    ResultSet query = cs.executeQuery();
    System.out.println("query.getMetaData() -- " + query.getMetaData());

    System.out.println(" ------------------------- ");

    while (query.next()) {
      int numColumns = query.getMetaData().getColumnCount();
      for (int i = 1; i <= numColumns; i++) {
        System.out.println( "COLUMN " + i + " = " + query.getObject(i) );
      }
    }
  }
}
