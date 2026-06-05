package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String base = "tienda";
    private String url;
    private String user;
    private String pass;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3306/" + this.base;
        this.user = "root";
        this.pass = "12345";
    }

    public Connection getConexion() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException e) {
            System.err.println(e);
        }

        return con;
    }
}
