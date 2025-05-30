import util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacciones {
    public static void main(String[] args) throws SQLException {
        // Comprobamos que la conexión funciona
        String qry = "SELECT * FROM producto";
        try(Connection conn = ConexionBD.creaConexion();
            Statement st = conn.createStatement())  {
            ResultSet rs = st.executeQuery(qry);
            while(rs.next()) {
                System.out.println("Producto: " + rs.getString("codigo_producto"));
            }
        }
    }
}
