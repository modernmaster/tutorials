package isb.player;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;

@Component
public class SnowflakeNoJPA
{
    public void connect()  throws Exception
    {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT PROVIDER_ID, LICENSEE_ID, count(*) FROM ISOFTBET_STAGE.EXTERNAL.V_GAP_FETCH_ACCOUNTING_PLAYERS_ARCHIVES_BY_SESSIONID WHERE SERVER_ID = 14 GROUP BY PROVIDER_ID, LICENSEE_ID");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        int totalColumnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i < totalColumnCount; i++) {
                System.out.print(resultSet.getString(i)+",");
            }
            System.out.println("");
        }
        statement.close();
    }
    private static Connection getConnection()
            throws SQLException
    {
        try
        {
            Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Driver not found");
        }
        // build connection properties
//        #    url: jdbc:snowflake://pr28215.eu-central-1.snowflakecomputing.com/
//#    username: GAPREADER
//#    password: 3aREPTFm2bpGHtuq
//

        Properties properties = new Properties();
        properties.put("user", "GAPREADER");     // replace "" with your username
        properties.put("password", "3aREPTFm2bpGHtuq"); // replace "" with your password
        properties.put("account", "pr28215");  // replace "" with your account name
        properties.put("db", "ISOFTBET_STAGE");       // replace "" with target database name
        properties.put("schema", "EXTERNAL");   // replace "" with target schema name
        //properties.put("tracing", "on");

        // create a new connection
        String connectStr = "jdbc:snowflake://pr28215.eu-central-1.snowflakecomputing.com"; // replace accountName with your account name
        return DriverManager.getConnection(connectStr, properties);
    }
}