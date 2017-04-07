package uk.co.jamesmcguigan.patterns.creational.objectpool;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

public class ObjectPoolTest {

    @Test
    @Ignore
    public void testPoolingOfJdbcConnections() {
        // Create the ConnectionPool:
        JDBCConnectionPool pool = new JDBCConnectionPool(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
                "sa", "secret");

        // Get a connection:
        Connection con = pool.checkOut();

        // Use the connection

        // Return the connection:
        pool.checkIn(con);
    }

}
