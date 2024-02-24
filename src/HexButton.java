import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class HexButton extends JButton {

    private Polygon hexagon;
    public HexButton(int x, int y, double width, double height){

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for(int i = 0; i < 6; i++) {
            double v = i*Math.PI/3;
            xPoints[i] = x + (int)Math.round(-width*Math.sin(v + Math.PI/2));
            yPoints[i] = y + (int)Math.round(height*Math.cos(v + Math.PI/2));
        }
        System.out.println(Arrays.toString(xPoints));
        System.out.println(Arrays.toString(yPoints));

        hexagon = new Polygon(xPoints, yPoints, 6);
    }



    protected void paintComponent(Graphics g) {
        // Let the standard button painting occur
        super.paintComponent(g);

        // Set the color of the hexagon
        g.setColor(Color.BLACK);

        // Draw the hexagon
        g.fillPolygon(hexagon);
    }


}
