import util.ConexionBD;

import java.sql.*;

public class Transacciones {
    public static void main(String[] args) throws SQLException {
        // Comprobamos que la conexión funciona
        /*String qry = "SELECT * FROM producto";
        try(Connection conn = ConexionBD.creaConexion();
            Statement st = conn.createStatement())  {
            ResultSet rs = st.executeQuery(qry);
            while(rs.next()) {
                System.out.println("Producto: " + rs.getString("codigo_producto"));
            }
        }*/
        System.out.println("----------- Insertamos un registro ----------------");
        String qry = "INSERT INTO cliente (codigo_cliente, nombre_cliente) VALUES (?, ?)";
        try(Connection conn = ConexionBD.creaConexion();
            PreparedStatement pst = conn.prepareStatement(qry)) {
            conn.setAutoCommit(false);

            Savepoint sp = null;
            try {
                pst.setInt(1, 100);
                pst.setString(2, "Judit");
                pst.executeUpdate();
                System.out.println("añadido el cliente 100");
                sp = conn.setSavepoint("punto1");

                pst.setInt(1, 101);
                pst.setString(2, "Sara");
                pst.executeUpdate();
                System.out.println("añadido el cliente 101");

                pst.setInt(1, 102);
                pst.setString(2, null);
                pst.executeUpdate();
                System.out.println("añadido el cliente 102");

            } catch (SQLException e) {
                if(sp != null) {
                    conn.rollback(sp);
                } else {
                    conn.rollback();
                }
                System.out.println("Se ha producido un error y se deshacen las transacciones pendientes");
            } finally {
                conn.commit();
            }
            conn.setAutoCommit(true);



           /* String qry2 = "Select * from cliente where codigo_cliente = 102";
            Statement st2 = conn.createStatement();
            ResultSet rst2 = st2.executeQuery(qry2);
            while(rst2.next()) {
                System.out.println("Nombre: " + rst2.getString("nombre_cliente"));
            }*/
        }
        String qry2 = "Select * from cliente where codigo_cliente = 102";
        try(Connection conn = ConexionBD.creaConexion();
            Statement st = conn.createStatement())  {
            ResultSet rs = st.executeQuery(qry2);
            while(rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre_cliente"));
            }
        }

    }
}
