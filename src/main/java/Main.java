import Controlador.TiendaControlador;
import Modelo.BDCrear;
import Modelo.ConsultasTienda;
import Modelo.Producto;
import Vista.VentanaTienda;

public class Main {
    public static void main(String [] args) {
        Producto producto = new Producto();
        ConsultasTienda modelo = new ConsultasTienda();

        VentanaTienda ventana = new VentanaTienda();

        TiendaControlador controlador = new TiendaControlador(ventana, modelo, producto);
        ventana.setVisible(true);

        // BDCrear bdCrear = new BDCrear();
        // bdCrear.crearBD(); Ejecutar solo la primera vez para crear la base de datos y las tablas

        // Para crear la base de datos, tablas y datos iniciales descomentar las dos lineas anteriores, ejecutar el programa solo una vez y luego volver a comentarlas
        // AVISO modificar los datos de usuario y contraseña en la clase BDCrear a los datos propios.
    }
}
