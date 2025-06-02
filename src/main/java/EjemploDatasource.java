import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploDatasource {
    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource ps = new PGSimpleDataSource();
        ps.setServerName("localhost");
        ps.setUser("jardinero");
        ps.setPassword("jardinero");
        ps.setDatabaseName("jardineria");
        Connection con = ps.getConnection();


        con.setAutoCommit(false);
    }
}
