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
            System.out.println("Error");
        }
    }

    public static void createSalesManInfoFile(int salesmanCount) {
        try {
            FileWriter writer = new FileWriter("salesmen_info.txt");
            Random random = new Random();

            String[] nombres = {"Juan", "Maria", "Carlos", "Ana"};
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
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {

        createProductsFile(10);
        createSalesManInfoFile(5);

        System.out.println("Todo listo");
    }
}