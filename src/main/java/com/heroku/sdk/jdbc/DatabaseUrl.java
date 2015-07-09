package com.heroku.sdk.jdbc;

import java.sql.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DatabaseUrl {

  private static String DEFAULT_SUB_PROTOCOL = "postgresql://";

  private String dbStr;

  private URI dbUri;

  private Boolean local;

  public static DatabaseUrl extract() throws URISyntaxException {
    return extract("DATABASE_URL");
  }

  public static DatabaseUrl extract(String envVar) throws URISyntaxException {
    return extract(envVar, false);
  }

  public static DatabaseUrl extract(Boolean local) throws URISyntaxException {
    return extract("DATABASE_URL", local);
  }

  public static DatabaseUrl extract(String envVar, Boolean local) throws URISyntaxException {
    return new DatabaseUrl(envVar, local);
  }

  public DatabaseUrl(String envVar, Boolean local) throws URISyntaxException {
    this.local = local;
    this.dbStr = System.getenv(envVar);
    if (this.dbStr == null || this.dbStr.isEmpty()) {
      throw new IllegalArgumentException("Could not find value for " + envVar);
    }
    this.dbUri = new URI(this.dbStr);
  }

  public String username() {
    if (dbUri.getUserInfo() != null) {
      return dbUri.getUserInfo().split(":")[0];
    } else {
      return null;
    }
  }

  public String password() {
    if (dbUri.getUserInfo() != null) {
      if (dbUri.getUserInfo().split(":").length > 1) {
        return dbUri.getUserInfo().split(":")[1];
      } else {
        return "";
      }
    } else {
      return null;
    }
  }

  public String host() {
    return dbUri.getHost();
  }

  public Integer port() {
    return dbUri.getPort();
  }

  public String path() {
    return dbUri.getPath();
  }

  public String jdbcUrl() {
    return jdbcUrl(DEFAULT_SUB_PROTOCOL);
  }

  public String jdbcUrl(String subProtocol) {
    return "jdbc:" + subProtocol + host() + ':' + port() + path() +
      (local ? "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory" : "");
  }

  public Connection getConnection() throws SQLException {
    return getConnection(DEFAULT_SUB_PROTOCOL);
  }

  public Connection getConnection(String subProtocol) throws SQLException {
    if (username() != null) {
      return DriverManager.getConnection(jdbcUrl(subProtocol), username(), password());
    } else {
      return DriverManager.getConnection(jdbcUrl(subProtocol));
    }
  }
}
