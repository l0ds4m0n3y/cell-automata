import javax.swing.JFrame;

public class Frame extends JFrame{
    Frame(int seed){
        super("Rule: " + seed);
        getContentPane().add(new MyPanel(seed));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
