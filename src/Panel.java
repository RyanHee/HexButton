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
        angle=0;
        hexButton=new HexButton("button");
        hexButton.addActionListener(this);
        //hexButton.setBounds(200*getWidth()/1600, 100*getHeight()/900, 50, 50);
        //hexButton.setVisible(true);
    }


    public void paint(Graphics g){
        super.paint(g);
        add(hexButton);
        int x=10*getWidth()/1600;
        int y=10*getHeight()/900;
        int w = 58*getWidth()/1600;
        int h = 58*getHeight()/900;
        h = w*58/50;
        hexButton.setBounds(200*getWidth()/1600, 100*getHeight()/900, 50*getWidth()/1600, 50*getHeight()/900);
        hexButton.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        AffineTransform oldTransform = g2.getTransform();
        AffineTransform t = new AffineTransform();
        t.rotate(Math.toRadians(angle), x+w/2,y+h/2);
        g2.setTransform(t);
        g2.drawImage(img, x, y,w,h , null);
        g2.dispose();
        g2.setTransform(oldTransform);

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
