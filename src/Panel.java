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
        hexButton=new HexButton(200, 200, 100, 50*Math.sqrt(3.0));
        hexButton.addActionListener(this);
    }


    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = img.createGraphics();
        AffineTransform t = new AffineTransform();
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(60), 50, 50);
        t.rotate(Math.toRadians(angle), 50,50);
        g2.setTransform(t);
        g2.drawImage(img, 0, 0,100, 100, null);
        //AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        //g2.drawImage(op.filter(img, null), 50, 50, null);

        //g2.drawImage(img, 200, 200, null);
        g2.dispose();
        add(hexButton);
        hexButton.paintComponent(g);
        //hexButton.setBounds(100, 100, 100, 100);
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
