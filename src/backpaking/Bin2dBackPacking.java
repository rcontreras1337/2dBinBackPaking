package backpaking;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    // Clase para representar un bloque único
    static class Block {
        int id;
        int startX;
        int startY;
        int width;
        int height;

        public Block(int id, int startX, int startY, int width, int height) {
            this.id = id;
            this.startX = startX;
            this.startY = startY;
            this.width = width;
            this.height = height;
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

    // Método para identificar bloques únicos en la matriz
    public static Map<Integer, Block> identifyBlocks(int[][] space) {
        Map<Integer, Block> blocks = new HashMap<>();

        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[0].length; j++) {
                int id = space[i][j];
                if (id != 0 && !blocks.containsKey(id)) {
                    // Encontrar límites del bloque
                    int startX = i;
                    int startY = j;
                    int width = 0;
                    int height = 0;

                    // Calcular el ancho del bloque
                    while (j + width < space[0].length && space[startX][j + width] == id) {
                        width++;
                    }

                    // Calcular la altura del bloque
                    while (i + height < space.length && space[i + height][startY] == id) {
                        height++;
                    }

                    blocks.put(id, new Block(id, startX, startY, width, height));
                }
            }
        }

        return blocks;
    }

    // Método para mostrar la representación gráfica en una ventana
    public static void displaySpace(int[][] space, int cellSize) {
        Map<Integer, Block> blocks = identifyBlocks(space);

        JFrame frame = new JFrame("Representación de Paneles Solares");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(space[0].length * cellSize + 100, space.length * cellSize + 100);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar fondo del área con bordes
                g.setColor(Color.WHITE);
                g.fillRect(30, 30, space[0].length * cellSize, space.length * cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(30, 30, space[0].length * cellSize, space.length * cellSize);
                // Dibujar bloques únicos
                for (Block block : blocks.values()) {
                    int x = block.startY * cellSize + 30;
                    int y = block.startX * cellSize + 30;
                    int width = block.width * cellSize;
                    int height = block.height * cellSize;

                    // Asignar color único
                    g.setColor(new Color((block.id * 50) % 255, (block.id * 80) % 255, (block.id * 110) % 255));
                    g.fillRect(x, y, width, height);

                    // Dibujar bordes y etiqueta
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, width, height);
                    g.drawString("ID: " + block.id, x + width / 4, y + height / 2 - 10);
                    g.drawString(block.width + "x," + block.height + "y", x + width / 4, y + height / 2 + 10);
                }
                // Dibujar ejes
                g.setColor(Color.BLACK);
                for (int i = 0; i <= space.length; i++) {
                    g.drawString(String.valueOf(i), 5, i * cellSize + 45);
                }
                for (int j = 0; j <= space[0].length; j++) {
                    g.drawString(String.valueOf(j), j * cellSize + 35, 20);
                }
            }
        };

        panel.setPreferredSize(new Dimension(space[0].length * cellSize + 50, space.length * cellSize + 50));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
