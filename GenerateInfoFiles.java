package ventas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

public static void createProductsFile(int productsCount) {

    try {

        FileWriter writer = new FileWriter("products.txt");
        Random random = new Random();

        for (int i = 1; i <= productsCount; i++) {

            int price = 1000 + random.nextInt(9000);
            writer.write(i + ";Producto" + i + ";" + price + "\n");
        }

        writer.close();
        System.out.println("Archivo products.txt creado");

    } catch (IOException e) {

        System.out.println("Error al crear products.txt: " + e.getMessage());
    }
}

public static void createSalesManInfoFile(int salesmanCount) {

    try {

        FileWriter writer = new FileWriter("salesmen_info.txt");
        Random random = new Random();

        String[] nombres = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Pedro"};
        String[] apellidos = {"Perez", "Gomez", "Lopez", "Rodriguez"};

        for (int i = 0; i < salesmanCount; i++) {

            int id = 10000 + random.nextInt(90000);
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];

            writer.write("CC;" + id + ";" + nombre + ";" + apellido + "\n");
        }

        writer.close();
        System.out.println("Archivo salesmen_info.txt creado");

    } catch (IOException e) {

        System.out.println("Error al crear salesmen_info.txt: " + e.getMessage());
    }
}

public static void createSalesMenFile(int randomSalesCount, String name, long id) {

    try {

        FileWriter writer = new FileWriter("sales_" + id + ".txt");
        Random random = new Random();

        writer.write("CC;" + id + "\n");

        for (int i = 0; i < randomSalesCount; i++) {

            int productId = 1 + random.nextInt(10);
            int quantity = 1 + random.nextInt(20);

            writer.write(productId + ";" + quantity + "\n");
        }

        writer.close();

        System.out.println("Archivo ventas creado: sales_" + id + ".txt");

    } catch (IOException e) {

        System.out.println("Error creando archivo ventas: " + e.getMessage());
    }
}

public static void main(String[] args) {

    createProductsFile(10);

    createSalesManInfoFile(5);

    createSalesMenFile(8, "Juan", 13177);
    createSalesMenFile(6, "Ana", 55465);
    createSalesMenFile(10, "Carlos", 38045);
    createSalesMenFile(5, "Juan", 31320);
    createSalesMenFile(7, "Maria", 86266);

    System.out.println("Archivos generados correctamente");
}

}
