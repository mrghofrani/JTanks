package GameObjects.MiddlePart.Tank.UserTank;

import Bullet.Cannon.CannonBullet;
import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Gun;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CannonGun extends PlayerGun implements Gun {

    private int numberOfBullet;


    public CannonGun(){
        this.IMAGE_PATH += "playerCannonGun0.png";
        setImage();
        numberOfBullet = 100;
    }


    public void doRendering(Graphics2D g2d,int locationX , int locationY, double angle){
//        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + locationX + " locationY: " + locationY);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(locationX+ 54 ,locationY + 50);
        at.rotate(angle);
        at.translate(-44,-48);
//        System.out.println(this.getClass().getName() + " line 113" + " gunLocationX: " + locationX  + " gunLocationY: " + locationY );
        g2d.drawImage(image,at ,null);
    }

    @Override
    public void fire() {
//        numberOfBullet--;
//        BattleField.addObject((new CannonBullet(image.getWidth(),int locationY,double))

    }



}
