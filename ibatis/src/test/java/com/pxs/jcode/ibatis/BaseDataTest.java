package com.pxs.jcode.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;



public abstract class BaseDataTest {
    
    public static final String BLOG_PROPERTIES = "com/pxs/jcode/ibatis/database/blog-derby.properties";
    public static final String BLOG_DDL = "com/pxs/jcode/ibatis/database/blog-derby-schema.sql";
    public static final String BLOG_DATA = "com/pxs/jcode/ibatis/database/blog-derby-dataload.sql";

  
    public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
      }
    
      public static PooledDataSource createPooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        PooledDataSource ds = new PooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
      }
    
      public static void runScript(DataSource ds, String resource) throws IOException, SQLException {
        try (Connection connection = ds.getConnection()) {
          ScriptRunner runner = new ScriptRunner(connection);
          runner.setAutoCommit(true);
          runner.setStopOnError(false);
          runner.setLogWriter(null);
          runner.setErrorLogWriter(null);
          runScript(runner, resource);
        }
      }
    
      public static void runScript(ScriptRunner runner, String resource) throws IOException, SQLException {
        try (Reader reader = Resources.getResourceAsReader(resource)) {
          runner.runScript(reader);
        }
      }
    
      public static DataSource createBlogDataSource() throws IOException, SQLException {
        DataSource ds = createUnpooledDataSource(BLOG_PROPERTIES);
        runScript(ds, BLOG_DDL);
        runScript(ds, BLOG_DATA);
        return ds;
      }
    

}
