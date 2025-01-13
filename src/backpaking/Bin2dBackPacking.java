package backpaking;

public class Bin2dBackPacking {
    // Clase para representar un objeto con dimensiones x e y
    public static class ObjectDimensions {
        int width;
        int height;

        public ObjectDimensions(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "ObjectDimensions{" +
                "width=" + width +
                ", height=" + height +
                '}';
        }
    }

    // Método auxiliar para verificar si un panel cabe en una posición dada
    public static boolean canPlace(boolean[][] space, int startX, int startY, int width, int height) {
        if (startX + height > space.length || startY + width > space[0].length) return false;

        for (int i = startX; i < startX + height; i++) {
            for (int j = startY; j < startY + width; j++) {
                if (space[i][j]) return false;
            }
        }
        return true;
    }

    // Método auxiliar para colocar un panel en la matriz
    public static void place(boolean[][] space, int startX, int startY, int width, int height) {
        for (int i = startX; i < startX + height; i++) {
            for (int j = startY; j < startY + width; j++) {
                space[i][j] = true;
            }
        }
    }

    // Método para calcular el número máximo de objetos optimizando el área
    public static int calculateMaxObjects(int areaWidth, int areaHeight, ObjectDimensions obj) {
        // Crear una matriz para rastrear el espacio disponible
        boolean[][] usedSpace = new boolean[areaHeight][areaWidth];

        int maxPanels = 0;

        // Rellenar el área usando una estrategia greedy
        for (int i = 0; i < areaHeight; i++) {
            for (int j = 0; j < areaWidth; j++) {
                // Intentar colocar en orientación horizontal
                if (canPlace(usedSpace, i, j, obj.width, obj.height)) {
                    place(usedSpace, i, j, obj.width, obj.height);
                    maxPanels++;
                }
                // Intentar colocar en orientación vertical
                else if (canPlace(usedSpace, i, j, obj.height, obj.width)) {
                    place(usedSpace, i, j, obj.height, obj.width);
                    maxPanels++;
                }
            }
        }

        return maxPanels;
    }
}
