import backpaking.Bin2dBackPacking;

import java.util.Scanner;

import static backpaking.Bin2dBackPacking.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            // Solicitar dimensiones del techo
            System.out.print("Ingrese el ancho del techo: ");
            int areaWidth = scanner.nextInt();

            System.out.print("Ingrese el alto del techo: ");
            int areaHeight = scanner.nextInt();

            // Dimensiones de un panel solar
            ObjectDimensions panelSolar = new ObjectDimensions(1, 1, 2);

            // Crear una matriz para representar el espacio
            int[][] space = new int[areaHeight][areaWidth];

            // Calcular el número máximo de paneles solares que caben
            int maxPanels = calculateMaxObjects(areaWidth, areaHeight, panelSolar, space);
            System.out.println("El número máximo de paneles solares que caben en el techo es: " + maxPanels);

            // Mostrar la representación gráfica
            displaySpace(space, 50);

            // Preguntar al usuario si desea continuar
            System.out.println("¿Desea realizar otro cálculo?");
            System.out.println("1: Sí");
            System.out.println("2: No");
            int opcion = scanner.nextInt();
            if (opcion != 1) {
                continuar = false;
            }
        }

        scanner.close();
    }
}
