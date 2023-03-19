package com.sohamkamani.cloudsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class App {
  public static void main(String[] args) throws SQLException {
    // Get a new datasource from the method we defined before
    DataSource dataSource = CloudSqlConnectionPoolFactory.createConnectionPool();

    // Run a query and get the result
    ResultSet rs =
        dataSource.getConnection().prepareStatement("select * from birds").executeQuery();

    // print the results to the console
    while (rs.next()) {
      System.out
          .println("name: " + rs.getString("bird") + " description:" + rs.getString("description"));
    }
  }
}
