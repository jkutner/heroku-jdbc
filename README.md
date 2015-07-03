## Heroku JDBC [ ![Download](https://api.bintray.com/packages/heroku/maven/jdbc/images/download.svg) ](https://bintray.com/heroku/maven/jdbc/_latestVersion)

This is a helper library for parsing `DATABASE_URL` from the environment and
turning it into a JDBC connection.

### Usage

In your `pom.xml`:

```xml
<repositories>
  <repository>
    <snapshots>
      <enabled>false</enabled>
    </snapshots>
    <id>bintray-heroku-maven</id>
    <name>bintray</name>
    <url>http://dl.bintray.com/heroku/maven</url>
  </repository>
</repositories>
...
<dependency>
  <groupId>com.heroku.sdk</groupId>
  <artifactId>heroku-jdbc</artifactId>
  <version>${heroku-jdbc.version}</version>
</dependency>
```

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
