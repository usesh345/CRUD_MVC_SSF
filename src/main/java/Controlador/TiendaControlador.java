package Controlador;

import Modelo.Conexion;
import Modelo.ConsultasTienda;
import Modelo.Producto;
import Vista.VentanaTienda;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TiendaControlador {
    private final VentanaTienda vista;
    private final ConsultasTienda modelo;
    private final Producto producto;

    public TiendaControlador(VentanaTienda vista, ConsultasTienda modelo, Producto producto) {
        this.vista = vista;
        this.modelo = modelo;
        this.producto = producto;

        this.vista.getBtnGuardar().addActionListener(e -> guardarProducto());
        this.vista.getBtnEliminar().addActionListener(e -> eliminarProducto());
        this.vista.getBtnBuscar().addActionListener(e -> buscarProducto());
        this.vista.getBtnModificar().addActionListener(e -> modificarProducto());
        this.vista.getBtnLimpiar().addActionListener(e -> limpiarCampos());
        this.vista.getBtnSalir().addActionListener(e -> System.exit(0));

        this.vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int filaSeleccionada = vista.getTabla().getSelectedRow();
            if (filaSeleccionada == -1) {
                return;
            }

            int modeloFila = vista.getTabla().convertRowIndexToModel(filaSeleccionada);

            vista.getTxtId().setText(vista.getModeloTabla().getValueAt(modeloFila, 0).toString());
            vista.getTxtNombre().setText(vista.getModeloTabla().getValueAt(modeloFila, 1).toString());
            vista.getTxtPrecio().setText(vista.getModeloTabla().getValueAt(modeloFila, 2).toString());
            vista.getTxtCantidad().setText(vista.getModeloTabla().getValueAt(modeloFila, 3).toString());

            String categoria = vista.getModeloTabla().getValueAt(modeloFila, 4).toString();
            switch (categoria) {
                case "Bebidas" -> vista.getRbBebidas().setSelected(true);
                case "Comida" -> vista.getRbComida().setSelected(true);
                case "Limpieza" -> vista.getRbLimpieza().setSelected(true);
            }
        });

        listar(vista.getModeloTabla());
    }

    private void guardarProducto() {
        try {
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
            int id_categoria = 0;

            verificarCampos();
            id_categoria = verificarCategoria(id_categoria);

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(cantidad);
            producto.setId_categoria(verificarCategoria(id_categoria));

            if(modelo.insertar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto guardado con exito.");
                limpiarCampos();
                listar(vista.getModeloTabla());
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el producto.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto. Por favor, revise los datos ingresados.");
            System.err.println(ex);
        }
    }

    private void eliminarProducto() {
        try {
            int id = Integer.parseInt(vista.getTxtId().getText());
            producto.setId(id);
            if(modelo.eliminar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado con exito.");
                limpiarCampos();
                listar(vista.getModeloTabla());
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto. Por favor, ingrese un ID válido.");
            System.err.println(ex);
        }
    }

    private void buscarProducto() {
        try {
            String texto = vista.getTxtBuscar().getText().trim();

            if(texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID para buscar.");
                return;
            }

            int id = Integer.parseInt(texto);
            producto.setId(id);

            if(modelo.buscar(producto)) {
                String categoria = String.valueOf(producto.getId_categoria());
                switch (categoria) {
                    case "1" -> categoria = "Bebidas";
                    case "2" -> categoria = "Comida";
                    case "3" -> categoria = "Limpieza";
                    default -> categoria = "Desconocida";
                }
                String mensaje = "ID: " + producto.getId() + "\n" + "Nombre: " + producto.getNombre() + "\n" + "Precio: " + producto.getPrecio() + "\n" + "Stock: " + producto.getStock() + "\n " + "Categoria: " + categoria;
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el producto.");
            System.err.println(ex);
        }
    }

    private void modificarProducto() {
        try {
            int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
            int id_categoria = 0;

            verificarCampos();
            id_categoria = verificarCategoria(id_categoria);

            producto.setId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(cantidad);
            producto.setId_categoria(verificarCategoria(id_categoria));

            if(modelo.actualizar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto actualizado con exito.");
                limpiarCampos();
                listar(vista.getModeloTabla());
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto. Por favor, revise los datos ingresados.");
            System.err.println(ex);
        }
    }

    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtCantidad().setText("");
        vista.getBttnGroupCategoria().clearSelection();
    }

    private void listar(DefaultTableModel modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = new Conexion().getConexion();

        String sql = "SELECT * FROM productos";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            modelo.setRowCount(0);
            while(rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("id_categoria")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al listar productos en la tabla.");
            System.err.println(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }

    private void verificarCampos() {
        if(vista.getTxtNombre().getText().isEmpty() || vista.getTxtPrecio().getText().isEmpty() || vista.getTxtCantidad().getText().isEmpty() || (!vista.getRbBebidas().isSelected() && !vista.getRbComida().isSelected() && !vista.getRbLimpieza().isSelected())) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }
    }

    private int verificarCategoria(int id_categoria) {
        int categoria = 0;
        if (vista.getRbBebidas().isSelected()) {
            categoria = 1;
        } else if (vista.getRbComida().isSelected()) {
            categoria = 2;
        } else if (vista.getRbLimpieza().isSelected()) {
            categoria = 3;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una categoría.");
        }
        return categoria;
    }
}
