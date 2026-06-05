package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaTienda extends JFrame {

    private JTextField txtId, txtNombre, txtPrecio, txtCantidad, txtBuscar;
    private JRadioButton rbBebidas, rbComida, rbLimpieza;
    private ButtonGroup bttnGroupCategoria;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JButton btnGuardar, btnModificar, btnEliminar, btnLimpiar, btnSalir, btnBuscar;

    public VentanaTienda() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tienda");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel base =  new JPanel(new BorderLayout(10,10)); // Creo un panel base con BorderLayout y espacio entre componentes
        base.setBorder(BorderFactory.createEmptyBorder(12,12,12,12)); // Agrego este comando BorderFactory para agregar un EmptyBorder en el panel base, esto aplica un margen interno de 12x12 a todo el panel, lo que ayuda a tener todo mas separado
        setContentPane(base); // Establezco el panel base como el principal dentro del frame.

        JPanel norte = new JPanel(new BorderLayout(10,10)); // Creo un panel norte para el titulo
        base.add(norte, BorderLayout.NORTH);

        JLabel titulo = new JLabel("CRUD Tienda", JLabel.CENTER);
        norte.add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout()); // Creo un panel centro para el formulario y la tabla
        base.add(centro, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridBagLayout()); // Creo el panel formulario con un formato de GridBagLayout este se me enseño en las practicas es increible para maniobrar por los distintos componentes gracias a sus GridBagConstraints, siendo estos coordenadas pero con los objetos en vez de con el panel, permitiendo a si mover los objetos con mayor facilidad, estableciendo parametros como margen entre ellos y como se van insertando si vertical horizontal etc.
        form.setBorder(BorderFactory.createTitledBorder("Formulario de Producto")); // Agrego otro BorderFactory comando que genera bordes en los paneles, esta vez con el formato borde titulado este se encarga de poner un bonito marco con titulo intercalado al rededor del panel.
        centro.add(form, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints(); // Establezco la constante del GridBagLayout esta es la que manipulara los objetos es como la condicion para ellos.
        c.insets = new Insets(4,4,4,4); // Establezco un margen entre los objetos de 4x4 con el comando Insets, encargado de generar un espacio con el tamaño deseado entre los objetos.
        c.fill = GridBagConstraints.HORIZONTAL; // Establezco como se iran insertando los objetos dentro del panel con el comando fill, en su caso sera de forma horizontal, muy util ya que si no hay mas objetos en su derecha ocupara el resto del espacio disponible o se administrara el espacio de forma automatica entre los objetos de su fila gracias al insets.

        // Este comando lo aprendi por consejo de un trabajador de la empresa en donde hice practicas, le comente que tenia problemas a la hora de moverme dentro del JFrame e insertar los distintos componentes me explico y enseño este comando y me resulto de gran utilidad.

        c.gridx = 0; c.gridy = 0; c.weightx = 0; // Establezco las primeras coordenadas y parametros para el primer objeto gracias al comando GridBagConstraints, pongo el punto 0.0 es decir la esquina del formulario, y el peso en 0 para que no ocupe espacio extra si no hay mas objetos a su derecha, esto es muy util para que por ejemplo los textos no se deformen si no hay mas objetos a continuacion.
        JLabel lblId = new JLabel("ID:"); // Creo el label del ID
        form.add(lblId, c); // Introduzco dicho label dentro del panel del formulario pero ojo con la c a su lado, ya que esta es la que establecera como debe añadirse ese componente dentro del panel, como hemos dicho es el que se encarga de colocar los objetos como deseemos.

        c.gridx = 1; c.gridy=0; c.weightx = 1; // Aqui establecemos las coordenadas 1.0 con peso 1 es decir se pondra a la derecha del label por ello el x 1 en el mismo nivel de altura por el y 0 y por ultimo este sera capaz de ocupar el resto de espacio disponible gracias al peso que establecimos en el eje x
        txtId = new JTextField();
        txtId.setEditable(false); // Establecemos para que no se pueda editar.
        form.add(txtId, c); // Hacemos lo mismo añadimos con condicion.

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        JLabel lblNombre = new JLabel("Nombre:");
        form.add(lblNombre, c);

        c.gridx = 1; c.gridy = 1; c.weightx = 1;
        txtNombre = new JTextField();
        form.add(txtNombre, c);

        c.gridx = 0; c.gridy = 2; c.weightx = 0;
        JLabel lblPrecio = new JLabel("Precio:");
        form.add(lblPrecio, c);

        c.gridx = 1; c.gridy = 2; c.weightx = 0;
        txtPrecio = new JTextField();
        form.add(txtPrecio, c);

        c.gridx=0; c.gridy = 3; c.weightx = 0;
        JLabel lblCantidad = new JLabel("Cantidad:");
        form.add(lblCantidad, c);

        c.gridx = 1; c.gridy = 3; c.weightx = 1;
        txtCantidad = new JTextField();
        form.add(txtCantidad, c);

        c.gridx = 0; c.gridy = 4; c.weightx = 0;
        JLabel lblCategoria = new JLabel("Categoria:");
        form.add(lblCategoria, c);

        c.gridx = 1; c.gridy = 4; c.weightx = 1;
        JPanel panelBtnCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        form.add(panelBtnCategoria, c);

        bttnGroupCategoria = new ButtonGroup();
        rbBebidas = new JRadioButton("Bebidas");
        rbComida = new JRadioButton("Comida");
        rbLimpieza = new JRadioButton("Limpieza");
        bttnGroupCategoria.add(rbBebidas);
        bttnGroupCategoria.add(rbComida);
        bttnGroupCategoria.add(rbLimpieza);
        panelBtnCategoria.add(rbBebidas);
        panelBtnCategoria.add(rbComida);
        panelBtnCategoria.add(rbLimpieza);

        c.gridx = 0; c.gridy = 6; c.weightx = 0;
        JLabel lblBuscar = new JLabel("Buscar:");
        form.add(lblBuscar, c);

        c.gridx = 1; c.gridy = 6; c.weightx = 1;
        txtBuscar = new JTextField();
        form.add(txtBuscar, c);

        c.gridx = 2; c.gridy = 6; c.weightx = 0;
        btnBuscar = new JButton("Buscar");
        form.add(btnBuscar, c);

        modeloTabla = new DefaultTableModel(new Object[]{ "ID", "Nombre", "Precio", "Cantidad", "Catergoria"}, 0) { // Creo el modelo de la tabla este comando sirve para construir una tabla con columnas donde podemos poner un titulo sobre cada una de las caracteristica del producto siendo asi interactuable e inclusivo editable desde la propia tabla.
            // Establecemos las propias columnas con new Objetc[] significando que cada columna tendra un titulo, y el 0 al final es para indicar que las filas se iran añadiendo de forma progresiva es decir no tiene limite de filas.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            } // Sobrescribimos el metodo isCellEditable este metodo es el encargado de establecer si las celdas de la tabla seran editables o no, protegiendo asi los datos lo llamamos con override ya que pertenece a la clase DefaultTableModel y con ello modificamos su comportamiento.
        };

        // Este comando tambien se me enseño en las practicas para poder hacer una lista de productos mas bonitas e interactiva.

        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla); // Creo un JScrollPane para la tabla este comando añade un barra de desplazamiento vertical entre los datos
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Productos")); // Le asigno otra vez un borde titulado para que se vea mejor y organizado.
        centro.add(scrollTabla, BorderLayout.CENTER);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        base.add(sur, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");

        sur.add(btnGuardar);
        sur.add(btnModificar);
        sur.add(btnEliminar);
        sur.add(btnLimpiar);
        sur.add(btnSalir);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JRadioButton getRbBebidas() {
        return rbBebidas;
    }

    public JRadioButton getRbComida() {
        return rbComida;
    }

    public JRadioButton getRbLimpieza() {
        return rbLimpieza;
    }

    public ButtonGroup getBttnGroupCategoria() {
        return bttnGroupCategoria;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}
