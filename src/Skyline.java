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

public class Skyline extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public int radius;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }

    public Skyline() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        radius = 1;
    }

    Random rand = new Random();


    public int[] starLocation() {
        int[] loc = new int[600];
        for (int i = 0; i < 600; i += 2) {
            loc[i] = rand.nextInt(1024);
            loc[i + 1] = rand.nextInt(768);
        }
        return loc;
    }

    public int[] starClusterLocation() {
        int[] loc = new int[200];
        int xStartPoint = rand.nextInt(400) + 300;
        int yStartPoint = rand.nextInt(300) + 100;
        for (int i = 0; i < 200; i += 2) {
            int newLocationX = (int) (rand.nextGaussian() * 20);
            int newLocationY = (int) (rand.nextGaussian() * 20);
            loc[i] = xStartPoint + newLocationX;
            loc[i + 1] = yStartPoint + newLocationY;
        }
        return loc;
    }

    public int[] mountainLocation() {
        int[] loc = new int[WIDTH];
        int start = rand.nextInt(20) + 350;
        int placehold = start;
        loc[0] = HEIGHT - placehold;
        for (int i = 1; i < 768; i++) {
            int current = placehold + rand.nextInt(10) - 5;
            loc[i] = HEIGHT - current;
            placehold = current;
        }
        return loc;
    }

    int[] starLoc = starLocation();
    int[] starClusterLoc = starClusterLocation();
    int[] mountLoc = mountainLocation();

    @Override
    public void paintComponent(Graphics gOri) {
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet = new GradientPaint(0, 0, new Color(127, 0, 255), 0, HEIGHT, new Color(252, 48, 150));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        //Random rand = new Random();

        //Your code here


        // shooting star

        //stars
        g.setColor(Color.WHITE);
        for (int i = 0; i < 600; i += 2) {
            //g.fillOval(rand.nextInt(1024), rand.nextInt(768), radius, radius) ;
            g.fillOval(starLoc[i], starLoc[i + 1], radius, radius);
        }


        //star cluster
        //int xStartPoint = rand.nextInt(400)+300;
        //int yStartPoint = rand.nextInt(300) + 100;
        for (int i = 0; i < 200; i += 2) {
            //int newLocationX = (int)(rand.nextGaussian()*20);
            //int newLocationY = (int)(rand.nextGaussian()*20);
            g.fillOval(starClusterLoc[i], starClusterLoc[i + 1], radius, radius);
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        if (radius >= 2) {
            radius--;
        } else {
            radius++;
        }


        //horizon
        g.setColor(Color.DARK_GRAY);
        //int start = rand.nextInt(20) + 350;
        //int placehold = start;
        //g.fillRect(0, HEIGHT-(start), 1, start);


        for (int i = 0; i < WIDTH; i++) {
            //int current = placehold + rand.nextInt(10)-5;
            g.fillRect(i, mountLoc[i], 1, mountLoc[i]);
            //placehold = current;
        }

        g.setColor(Color.GRAY);
        //start = rand.nextInt(20) + 250;
        //g.fillRect(WIDTH, HEIGHT-start, 1, start);
        //placehold = start;

        for (int j = WIDTH; j > 0; j--) {
            //int current = placehold + rand.nextInt(10)-5;
            g.fillRect(j, mountLoc[WIDTH - j], 1, mountLoc[WIDTH - j]);
            //placehold = current;
        }

        repaint();


    }
}
