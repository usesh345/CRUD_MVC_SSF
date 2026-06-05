package Modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultasTienda extends Conexion {
    public boolean insertar(Producto prod) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO productos (nombre, precio, stock, id_categoria) VALUES (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getNombre());
            ps.setDouble(2, prod.getPrecio());
            ps.setInt(3, prod.getStock());
            ps.setInt(4, prod.getId_categoria());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public boolean buscar(Producto prod) {
        PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT id, nombre, precio, stock, id_categoria FROM productos WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prod.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));
                prod.setId_categoria(rs.getInt("id_categoria"));
                return true;
            }
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public boolean actualizar(Producto producto) throws SQLException {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE productos SET nombre=?, precio=?, stock=?, id_categoria=? WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getId_categoria());
            ps.setInt(5, producto.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualziar el producto: " + ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Producto prod) throws SQLException {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM productos WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prod.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

}
