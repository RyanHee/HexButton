import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class Panel extends JPanel implements ActionListener {
    private int[]xlst;
    private int[]ylst;
    private int angle;
    private BufferedImage img;
    private HexButton hexButton;
    public Panel(){
        try{
            img = ImageIO.read(Panel.class.getResource("tile.png"));
        }
        catch (Exception e){
            System.out.println(1231);
        }
        angle=60;
        hexButton=new HexButton("");
        hexButton.addActionListener(this);
        add(hexButton);
        //hexButton.setBounds(200*getWidth()/1600, 100*getHeight()/900, 50, 50);
        //hexButton.setVisible(true);
    }


    public void paint(Graphics g){
        super.paint(g);
        hexButton.setBounds(100, 10, 58, 58);
        hexButton.paintComponent(g);
        int x = 10;
        int y = 10;
        int w = 50;
        int h = 58 * getHeight() / 900;
        h = w * 58 / 50;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(Math.toRadians(angle), x + w / 2, y + h / 2);
        g2.drawImage(img, x, y, w, h, null);

        // Dispose the Graphics2D object to release resources
        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==hexButton){
            angle+=60;
            if (angle==360)
                angle=0;
            System.out.println(angle);
            repaint();
        }
    }
}