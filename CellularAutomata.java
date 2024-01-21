import java.util.Random;

import javax.swing.JFrame;

public class CellularAutomata extends JFrame{
    public static final int RANDOM = 0;
    public static final int SINGULAR = 1;
    public static final int EDGES = 2;
    public static final int RANDOM_SINGULAR = 3;
    Random rand = new Random();

    void generateGUI(int seed){
        setTitle("Rule: " + seed);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    CellularAutomata(int seed){
        getContentPane().add(new MyPanel(seed));
        generateGUI(seed);
    }

    CellularAutomata(){
        int seed = rand.nextInt(256);
        getContentPane().add(new MyPanel(seed));
        generateGUI(seed);
    }

    CellularAutomata(int seed, int pattern){
        getContentPane().add(new MyPanel(seed, pattern));
        generateGUI(seed);
    }
}
