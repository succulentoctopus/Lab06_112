import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

//challenges:
// 1. animate stars: make stars twinkle / random shooting stars
// 2. change gradient of sunset
// 3. make the code as efficient as possible

public class Skyline extends JPanel{
    public static final int WIDTH=1024;
    public static final int HEIGHT=768;
    public static void main(String[] args){
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }
    public Skyline(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics gOri){
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet= new GradientPaint(0, 0, new Color(127, 0, 255), 0, HEIGHT, new Color(252, 48, 150));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        Random rand = new Random();
        //Your code here

        //stars
        // animate
        // shooting star
        g.setColor(Color.WHITE);
        for (int i = 0; i < 300; i++) {
            g.fillOval(rand.nextInt(1024), rand.nextInt(768), 1, 1) ;
        }

        //star cluster
        int xStartPoint = rand.nextInt(1024);
        int yStartPoint = rand.nextInt(248) + 520;
        for (int i = 0; i < 100; i++) {
            g.fillOval((int)(xStartPoint*rand.nextGaussian()), (int)(yStartPoint*rand.nextGaussian()), 1, 1);
        }

        //horizon
        g.setColor(Color.GRAY);
        int start = rand.nextInt(20) + 250;
        int placehold = start;
        g.fillRect(0, HEIGHT-(start), 1, start);

        for (int i = 1; i <= WIDTH; i++) {
            int current = placehold + rand.nextInt(10)-5;
            g.fillRect(i, HEIGHT-(current), 1, current);
            placehold = current;
        }

        g.setColor(Color.DARK_GRAY);
        start = rand.nextInt(20) + 350;
        g.fillRect(WIDTH, HEIGHT-start, 1, start);
        placehold = start;

        for(int j = WIDTH; j > 0; j--) {
            int current = placehold + rand.nextInt(10)-5;
            g.fillRect(j, HEIGHT-(current), 1, current);
            placehold = current;
        }

    }
}
