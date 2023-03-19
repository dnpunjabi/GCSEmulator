package com.sohamkamani.cloudsql;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class CloudSqlConnectionPoolFactory {
  // I'm defining my database credentials here for convenience
  // but in practice, you should use environment variables
  // (https://docs.oracle.com/javase/tutorial/essential/environment/env.html)
  // or Cloud Secret Manager (https://cloud.google.com/secret-manager)
  private static final String INSTANCE_CONNECTION_NAME =
      "sohamkamani-website:us-central1:my-sample-database";
  private static final String DB_USER = "postgres";
  private static final String DB_PASS = "<password>";
  private static final String DB_NAME = "bird_encyclopedia";

  public static DataSource createConnectionPool() {
    // create a new configuration and set the database credentials
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(String.format("jdbc:postgresql:///%s", DB_NAME));
    config.setUsername(DB_USER);
    config.setPassword(DB_PASS);
    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
    config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);

    // Initialize the connection pool using the configuration object.
    return new HikariDataSource(config);
  }
}
