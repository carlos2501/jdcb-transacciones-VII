package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL="jdbc:postgresql://localhost:5432/jardineria";
    private static final String USUARIO= "jardinero";
    private static final String CLAVE="jardinero";
    // esta variable contiene el objeto conexión -es el SINGLETON-
    private static Connection conex;

    public static Connection creaConexion() throws SQLException {
        if (conex == null || conex.isClosed()) {
            conex = DriverManager.getConnection(URL, USUARIO, CLAVE);
        }
        return conex;
    }

    public static DataSource getDataSource(int numconex) throws SQLException {
        HikariConfig hcnf = new HikariConfig();
        hcnf.setJdbcUrl(URL);
        hcnf.setUsername(USUARIO);
        hcnf.setPassword(CLAVE);
        hcnf.setMaximumPoolSize(numconex);

        return new HikariDataSource(hcnf);
    }
}
