package pl.edu.utp;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceHikariCP {
	
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
        config.setJdbcUrl("jdbc:hsqldb:file:db/test;ifexists=true");
        config.setUsername( "student" );
        config.setPassword( "student" );
        config.setMaximumPoolSize(1);
        ds = new HikariDataSource( config );
    }
 
    private DataSourceHikariCP() {}
 
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
