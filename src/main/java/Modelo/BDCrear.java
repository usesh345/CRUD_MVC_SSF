package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BDCrear {
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root"; // Modificar si el usuario es distinto
    private String pass = "contraseña"; // Modificar si es distinta

    public boolean crearBaseDatos() {
        String sql = "CREATE DATABASE IF NOT EXISTS tienda";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {
            st.execute(sql);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean crearTablas() {
        String sqlCategorias = "CREATE TABLE IF NOT EXISTS categorias (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(80) NOT NULL" +
                ");";

        String sqlProductos = "CREATE TABLE IF NOT EXISTS productos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(80) NOT NULL, " +
                "precio DOUBLE NOT NULL, " +
                "stock INT NOT NULL, " +
                "id_categoria INT NOT NULL, " +
                "CONSTRAINT fk_id_categorias FOREIGN KEY (id_categoria) REFERENCES categorias(id)" +
                ")";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {
            st.execute(sqlCategorias);
            st.execute(sqlProductos);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean insertarDatosTabla() {
        String sqlInsertarCategorias = "INSERT INTO categorias (nombre) VALUES " +
                "('Bebidas'),('Comida'),('Limpieza')";

        String sqlInsertarProductos = "INSERT INTO productos (nombre, precio, stock, id_categoria) VALUES " +
                "('Agua 1.5L', 0.80, 50, 1)," +
                "('Cola 2L', 2.10, 30, 1)," +
                "('Zumo Naranja', 1.25, 20, 1)," +
                "('Pasta', 1.10, 40, 2)," +
                "('Arroz', 1.20, 60, 2)," +
                "('Atún', 1.50, 35, 2)," +
                "('Pan de molde', 1.60, 25, 2)," +
                "('Lejía', 1.30, 15, 3)," +
                "('Detergente', 4.50, 10, 3)," +
                "('Lavavajillas', 2.80, 12, 3)";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {
            st.execute(sqlInsertarCategorias);
            st.execute(sqlInsertarProductos);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
