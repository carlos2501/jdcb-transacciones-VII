import com.zaxxer.hikari.HikariDataSource;
import util.ConexionBD;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Pool {
    private static final int NUMCONEX = 5;
    private static final String URL="jdbc:postgresql://localhost:5432/jardineria";
    private static final String USUARIO= "jardinero";
    private static final String CLAVE="jardinero";

    /**
     * Conexiones     DM        Hikari
     *      1         464         450
     *     10       1.489          24
     *    100       9.006          19
     */

    public static void main(String[] args) throws SQLException {
        /*long inicio = System.currentTimeMillis();
        for (int i = 0; i < NUMCONEX; i++) {
            Connection con = ConexionBD.creaConexion();

            con.close();
        }
        System.out.println("Tiempo con DM: " + (System.currentTimeMillis()-inicio));
*/
        DataSource ds = ConexionBD.getDataSource(NUMCONEX);
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < NUMCONEX; i++) {
            Connection con1 = ds.getConnection();
            System.out.println("Conexión 1 abierta");
            Connection con2 = ds.getConnection();
            System.out.println("Conexión 2 abierta");
            Connection con3 = ds.getConnection();
            System.out.println("Conexión 3 abierta");
            Connection con4 = ds.getConnection();
            System.out.println("Conexión 4 abierta");
            Connection con5 = ds.getConnection();
            System.out.println("Conexión 5 abierta");
            con5.close();
            System.out.println("Conexión 5 cerrada");
            Connection con6 = ds.getConnection();
            System.out.println("Conexión 6 abierta");

            con1.close();
            System.out.println("Conexión 1 cerrada");
            con2.close();
            System.out.println("Conexión 2 cerrada");
            con3.close();
            System.out.println("Conexión 3 cerrada");
            con4.close();
            System.out.println("Conexión 4 cerrada");

            con6.close();
            System.out.println("Conexión 6 cerrada");
        }
        System.out.println("Tiempo con Hikari: " + (System.currentTimeMillis()-inicio));
    }
}
