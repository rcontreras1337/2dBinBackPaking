package backpaking;

public class Bin2dBackPacking {
    // Clase para representar un objeto con dimensiones x e y
    public static class ObjectDimensions {
        int width;
        int height;
        int id;

        public ObjectDimensions(int id, int width, int height) {
            this.id = id;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "ObjectDimensions{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                '}';
        }
    }

    // Método auxiliar para verificar si un panel cabe en una posición dada
    public static boolean canPlace(int[][] space, int startX, int startY, int width, int height) {
        if (startX + height > space.length || startY + width > space[0].length) return false;

        for (int i = startX; i < startX + height; i++) {
            for (int j = startY; j < startY + width; j++) {
                if (space[i][j] != 0) return false;
            }
        }
        return true;
    }

    // Método auxiliar para colocar un panel en la matriz
    public static void place(int[][] space, int startX, int startY, int width, int height, int id) {
        for (int i = startX; i < startX + height; i++) {
            for (int j = startY; j < startY + width; j++) {
                space[i][j] = id;
            }
        }
    }

    // Método para calcular el número máximo de objetos optimizando el área
    public static int calculateMaxObjects(int areaWidth, int areaHeight, ObjectDimensions obj, int[][] space) {
        int maxPanels = 0;
        int panelId = 1;

        // Rellenar el área usando una estrategia greedy
        for (int i = 0; i < areaHeight; i++) {
            for (int j = 0; j < areaWidth; j++) {
                // Intentar colocar en orientación horizontal
                if (canPlace(space, i, j, obj.width, obj.height)) {
                    place(space, i, j, obj.width, obj.height, panelId++);
                    maxPanels++;
                }
                // Intentar colocar en orientación vertical
                else if (canPlace(space, i, j, obj.height, obj.width)) {
                    place(space, i, j, obj.height, obj.width, panelId++);
                    maxPanels++;
                }
            }
        }

        return maxPanels;
    }

    // Método para imprimir el área con los objetos posicionados
    public static void printSpace(int[][] space) {
        System.out.println("\nEspacio del techo con paneles:");
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[0].length; j++) {
                System.out.print(space[i][j] == 0 ? ". " : space[i][j] + " ");
            }
            System.out.println();
        }
    }
}
