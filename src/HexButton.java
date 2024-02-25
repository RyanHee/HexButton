

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class HexButton extends JButton {

    private Polygon hexagon;
    private int x, y, width, height;
    private int[]xPoints, yPoints;
    private BufferedImage img;
    public HexButton(String label){
        super(label);
        xPoints = new int[6];
        yPoints = new int[6];
        System.out.println(Arrays.toString(xPoints));
        System.out.println(Arrays.toString(yPoints));
        hexagon = new Polygon(xPoints, yPoints, 6);
        try{
            img = ImageIO.read(Panel.class.getResource("tile.png"));
        }
        catch (Exception e){
            System.out.println("fuck");
        }
    }

    public HexButton(String label, int x, int y, double width, double height){
        super(label);
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


    @Override
    public void setBounds(int xx, int yy, int w, int h){
        x=xx;
        y=yy;
        width=w;
        height = h;
        for(int i = 0; i < 6; i++) {
            double v = i*Math.PI/3;
            xPoints[i] = x + (int)Math.round(-width*Math.sin(v + Math.PI/2));
            yPoints[i] = y + (int)Math.round(height*Math.cos(v + Math.PI/2));
        }
        hexagon = new Polygon(xPoints, yPoints, 6);
        //Graphics g = getGraphics();
        //System.out.println(g);
        //g.fillPolygon(hexagon);
        System.out.println(Arrays.toString(xPoints));
        System.out.println(Arrays.toString(yPoints));
    }


    protected void paintComponent(Graphics g) {
        // Let the standard button painting occur
        super.paintComponent(g);

        // Set the color of the hexagon
        //g.setColor(Color.BLACK);

        // Draw the hexagon
        //g.fillPolygon(hexagon);
        System.out.println(x+", "+y);
        g.drawImage(img, x, y, width, height, null);
    }

    @Override
    public boolean contains(int x1, int y1) {
        if (hexagon==null){
            int[] xlst = new int[6];
            int[] ylst = new int[6];

            int x = getSize().width/2;
            int y = getSize().height/2;
            for (int i=0;i<6;i++){
                double v = Math.PI/3;
                xlst[i] = x + (int)Math.round((getWidth()/2)*Math.cos(v));
                ylst[i] = y + (int)Math.round((getHeight()/2)*Math.sin(v));
            }
            hexagon=new Polygon(xlst, ylst, 6);
        }
        return hexagon.contains(x1, y1);
    }

}