## Heroku JDBC

This is a helper library for parsing `DATABASE_URL` from the environment and
turning it into a JDBC connection.

### Usage

For PostgreSQL (the default):

```java
Connection conn = DatabaseUrl.extract().getConnection();
```

For other Database vendors:

```java
Connection conn = DatabaseUrl.extract().getConnection("mysql://");
```

For Spring or Apache DBCP:

```java
DatabaseUrl dbUrl = DatabaseUrl.extract();

BasicDataSource basicDataSource = new BasicDataSource();
basicDataSource.setUrl(dbUrl.jdbcUrl(););
basicDataSource.setUsername(dbUrl.username());
basicDataSource.setPassword(dbUrl.password());
```

### License

MIT
