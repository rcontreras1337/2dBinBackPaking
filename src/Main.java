import backpaking.Bin2dBackPacking;

import java.util.Scanner;

import static backpaking.Bin2dBackPacking.calculateMaxObjects;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar dimensiones del techo
        System.out.print("Ingrese el ancho del techo: ");
        int areaWidth = scanner.nextInt();

        System.out.print("Ingrese el alto del techo: ");
        int areaHeight = scanner.nextInt();

        // Dimensiones de un panel solar
        Bin2dBackPacking.ObjectDimensions panelSolar = new Bin2dBackPacking.ObjectDimensions(1, 2);

        // Calcular el número máximo de paneles solares que caben
        int maxPanels = calculateMaxObjects(areaWidth, areaHeight, panelSolar);
        System.out.println("El número máximo de paneles solares que caben en el techo es: " + maxPanels);

        scanner.close();
    }
}
