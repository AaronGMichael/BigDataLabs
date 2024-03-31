package org.example.j2eeversion;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DbConnection {
    public static DataSource getConnection(){
        PGSimpleDataSource ds = new PGSimpleDataSource() ;  // Empty instance.
        ds.setServerName( "localhost" );
        ds.setDatabaseName( "lab3" );
        ds.setUser( "postgres" );
        ds.setPassword( "root" );
        DataSource dataSource = ds;
        return dataSource;
    }
}
