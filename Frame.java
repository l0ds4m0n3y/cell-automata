import java.util.Random;

import javax.swing.JFrame;

public class Frame extends JFrame{
    public static final int RANDOM = 0;
    public static final int SINGULAR = 1;
    public static final int EDGES = 2;
    Random rand = new Random();
    int seed;

    Frame(int seed){
        this.seed = seed;
        setTitle("Rule: " + seed);
        getContentPane().add(new MyPanel(seed));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    Frame(){
        this.seed = rand.nextInt(256);
        setTitle("Rule " + seed);
        getContentPane().add(new MyPanel(seed));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    Frame(int seed, int pattern){
        setTitle("Rule " + seed);
        getContentPane().add(new MyPanel(seed, pattern));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
