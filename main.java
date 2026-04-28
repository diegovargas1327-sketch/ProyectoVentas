package ventas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**

Clase principal que procesa archivos de productos,
vendedores y ventas para generar reportes.
Proyecto Politécnico Grancolombiano
*/

class Producto {

int id;
String nombre;
int precio;
int cantidadVendida = 0;

public Producto(int id, String nombre, int precio) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
}

}

class Vendedor {

String tipoDoc;
String id;
String nombre;
String apellido;
long totalVentas = 0;

public Vendedor(String tipoDoc, String id, String nombre, String apellido) {
    this.tipoDoc = tipoDoc;
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
}

}

public class main {

static ArrayList<Producto> productos = new ArrayList<>();
static ArrayList<Vendedor> vendedores = new ArrayList<>();

public static void main(String[] args) {

    try {

        cargarProductos();
        cargarVendedores();
        procesarVentas();
        generarReporteVendedores();
        generarReporteProductos();

        System.out.println("Reportes generados correctamente");

    } catch (Exception e) {

        System.out.println("Error en ejecución: " + e.getMessage());
    }
}

public static void cargarProductos() throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("products.txt"));
    String linea;

    while ((linea = br.readLine()) != null) {

        String[] datos = linea.split(";");

        int id = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        int precio = Integer.parseInt(datos[2]);

        productos.add(new Producto(id, nombre, precio));
    }

    br.close();
}

public static void cargarVendedores() throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("salesmen_info.txt"));
    String linea;

    while ((linea = br.readLine()) != null) {

        String[] datos = linea.split(";");

        vendedores.add(
                new Vendedor(
                        datos[0],
                        datos[1],
                        datos[2],
                        datos[3]
                )
        );
    }

    br.close();
}

public static void procesarVentas() throws IOException {

    File carpeta = new File(".");
    File[] archivos = carpeta.listFiles();

    for (File archivo : archivos) {

        if (archivo.getName().startsWith("sales_")) {

            BufferedReader br = new BufferedReader(new FileReader(archivo));

            String linea = br.readLine();

            String[] encabezado = linea.split(";");
            String idVendedor = encabezado[1];

            Vendedor vendedor = buscarVendedor(idVendedor);

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                int idProducto = Integer.parseInt(datos[0]);
                int cantidad = Integer.parseInt(datos[1]);

                Producto producto = buscarProducto(idProducto);

                if (producto != null && vendedor != null) {

                    producto.cantidadVendida += cantidad;
                    vendedor.totalVentas += cantidad * producto.precio;
                }
            }

            br.close();
        }
    }
}

public static Producto buscarProducto(int id) {

    for (Producto p : productos) {

        if (p.id == id)
            return p;
    }

    return null;
}

public static Vendedor buscarVendedor(String id) {

    for (Vendedor v : vendedores) {

        if (v.id.equals(id))
            return v;
    }

    return null;
}

public static void generarReporteVendedores() throws IOException {

    Collections.sort(vendedores, new Comparator<Vendedor>() {

        public int compare(Vendedor a, Vendedor b) {
            return Long.compare(b.totalVentas, a.totalVentas);
        }
    });

    FileWriter writer = new FileWriter("reporte_vendedores.csv");

    for (Vendedor v : vendedores) {

        writer.write(
                v.nombre + " " + v.apellido +
                        ";" + v.totalVentas + "\n"
        );
    }

    writer.close();
}

public static void generarReporteProductos() throws IOException {

    Collections.sort(productos, new Comparator<Producto>() {

        public int compare(Producto a, Producto b) {
            return b.cantidadVendida - a.cantidadVendida;
        }
    });

    FileWriter writer = new FileWriter("reporte_productos.csv");

    for (Producto p : productos) {

        writer.write(
                p.nombre +
                        ";" + p.precio +
                        ";" + p.cantidadVendida + "\n"
        );
    }

    writer.close();
}

}