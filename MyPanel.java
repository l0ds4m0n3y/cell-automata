import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
    Integer[] cells = {0,0,0,0,0,0,0,0,0,1};
    ArrayList<Integer[]> generations = new ArrayList<>();
    int[] ruleset;
    final int width = 400;
    final int height = 800;
    int seed = 40;

    int cellSize = 40;

    MyPanel(int seed){
        generateRuleSet(seed);
        createGenerations(generations, cells, 0);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        this.seed = seed;
    }

    private void createGenerations(ArrayList<Integer[]> generations, Integer[] cells, int i) {
        if(i >= height / cellSize){
            return;
        }
        else
        {
            Integer[] next = nextGen(ruleset, cells);
            generations.add(next);
            createGenerations(generations, next, i+1);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(0,0,100,100);
        int height = 0;
        for(Integer[] gen : generations){
            paintRow(g2d, gen, height * cellSize);
            height++;
            System.out.println(Arrays.toString(gen));
        }
    }

    public void paintRow(Graphics2D g, Integer[] row, int height){
        for(int i = 0; i < 10; i++){
            int x = cellSize * i;
            if(row[i] == 1){
                g.setPaint(Color.BLACK);
            }else{
                g.setPaint(Color.WHITE);
            }
            g.fillRect(x, height, cellSize, cellSize);
        }
    }

    private void generateRuleSet(int seed) {
        this.ruleset = new int[8];
        Arrays.fill(ruleset, 0);
        String seedStr = String.format("%8s", Integer.toBinaryString(seed)).replace(' ', '0');
        for(int i = 0; i < 8; i++){
            ruleset[i] = seedStr.charAt(i) - 48;
        }
    }

    private Integer[] nextGen(int[] ruleset, Integer[] cells) {
        Integer[] nextGenereation = new Integer[10];
        nextGenereation[0] = 0;
        nextGenereation[9] = 0;
        for(int i = 0; i < 10; i++){
            int index;
            if(i == 0){
                index = Integer.parseInt(String.valueOf("" + cells[9] + cells[0] + cells[1]), 2);
            }else if (i == 9){
                index = Integer.parseInt(String.valueOf("" + cells[8] + cells[9] + cells[0]), 2);
            }
            else index = Integer.parseInt(String.valueOf(""+cells[i-1] +""+ cells[i] +""+ cells[i+1]), 2);
            System.out.println(index);
            nextGenereation[i] = ruleset[7 - index];
        }
        // for(int i = 1; i < 9; i++){
        //     if ( cells[i-1] == 1 && cells[i] == 1 && cells[i+1] == 1 ) nextGenereation[i] = ruleset[0];
        //     if ( cells[i-1] == 1 && cells[i] == 1 && cells[i+1] == 0 ) nextGenereation[i] = ruleset[1];
        //     if ( cells[i-1] == 1 && cells[i] == 0 && cells[i+1] == 1 ) nextGenereation[i] = ruleset[2];
        //     if ( cells[i-1] == 1 && cells[i] == 0 && cells[i+1] == 0 ) nextGenereation[i] = ruleset[3];
        //     if ( cells[i-1] == 0 && cells[i] == 1 && cells[i+1] == 1 ) nextGenereation[i] = ruleset[4];
        //     if ( cells[i-1] == 0 && cells[i] == 1 && cells[i+1] == 0 ) nextGenereation[i] = ruleset[5];
        //     if ( cells[i-1] == 0 && cells[i] == 0 && cells[i+1] == 1 ) nextGenereation[i] = ruleset[6];
        //     if ( cells[i-1] == 0 && cells[i] == 0 && cells[i+1] == 0 ) nextGenereation[i] = ruleset[7];
        // }
        return nextGenereation;
    }
}
