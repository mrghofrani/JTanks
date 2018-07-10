package GameObjects.MiddlePart.Tank.UserTank;

import Bullet.Cannon.CannonBullet;
import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Gun;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class CannonGun extends PlayerGun implements Gun {

    private int numberOfBullet;
    private Image image;
    private String IMAGE_PATH = "files" + File.separator + "Images" + File.separator;


    public CannonGun(){
        IMAGE_PATH += "playerCannonGun0.png";
        setImage();
        numberOfBullet = 100;
    }


    public void doRendering(Graphics2D g2d,int locationX, int locationY, double angle){
        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + locationX + " locationY: " + locationY);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(locationX ,locationY);
        at.rotate(angle);
        g2d.drawImage(image,at,null);
    }

    @Override
    public void fire() {
//        numberOfBullet--;
//        BattleField.addObject((new CannonBullet(image.getWidth(),int locationY,double))

    }


    public void setImage(){
        File file = new File(IMAGE_PATH);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occurred while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
