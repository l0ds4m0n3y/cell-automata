import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

    int[] ruleset;
    Integer[] cells;
    ArrayList<Integer[]> generations = new ArrayList<>();
    final int WIDTH = 600;
    final int HEIGHT = 1000;
    final int cellSize = 2;
    int seed;
    int numCells = WIDTH / cellSize;
    Random rand = new Random();

    MyPanel(int seed, int pattern) {
        this.seed = seed;
        generateRuleSet(seed);

        this.cells = new Integer[numCells];
        Arrays.fill(this.cells, 0);

        switch (pattern) {
            case Frame.RANDOM:
                for (int i = 0; i < cells.length; i++) {
                    cells[i] = rand.nextInt(2);
                }
                break;
            case Frame.EDGES:
                cells[0] = 1;
                cells[cells.length - 1] = 1;
                break;
            case Frame.SINGULAR:
                cells[cells.length / 2] = 1;
                break;
            default:
                break;
        }

        buildGui();
    }

    MyPanel(int seed) {
        this.seed = seed;
        generateRuleSet(seed);
        this.cells = new Integer[numCells];
        Arrays.fill(this.cells, 0);
        cells[cells.length / 2] = 1;
        buildGui();
    }

    private void buildGui() {
        createGenerations(generations, cells, 0);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

    private void createGenerations(ArrayList<Integer[]> generations, Integer[] cells, int i) {
        if (i >= HEIGHT / cellSize) {
            return;
        } else {
            Integer[] next = nextGen(ruleset, cells);
            generations.add(next);
            createGenerations(generations, next, i + 1);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int height = 0;
        for (Integer[] gen : generations) {
            paintRow(g2d, gen, height * cellSize);
            height++;
            // System.out.println(Arrays.toString(gen));
        }
    }

    public void paintRow(Graphics2D g, Integer[] row, int height) {

        for (int i = 0; i < numCells; i++) {
            int x = cellSize * i;
            if (row[i] == 1) {
                g.setPaint(new Color(0, 0, 0));
            } else {
                g.setPaint(Color.WHITE);
            }
            g.fillRect(x, height, cellSize, cellSize);
        }
    }

    private void generateRuleSet(int seed) {
        this.ruleset = new int[8];
        Arrays.fill(ruleset, 0);

        String seedStr = String.format("%8s", Integer.toBinaryString(seed)).replace(' ', '0');

        for (int i = 0; i < 8; i++) {
            ruleset[i] = seedStr.charAt(i) - '0';
        }
    }

    private Integer[] nextGen(int[] ruleset, Integer[] cells) {
        Integer[] nextGeneration = new Integer[cells.length];

        for (int i = 0; i < cells.length; i++) {
            int index = Integer.parseInt(String.valueOf("" + cells[(i - 1 + cells.length) % cells.length] +
                    cells[i] +
                    cells[(i + 1) % cells.length]),
                    2);

            nextGeneration[i] = ruleset[7 - index];
        }

        return nextGeneration;
    }
}
