package GameObjects.MiddlePart.Tank.UserTank;

import Bullet.Cannon.CannonBullet;
import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Cannon1;
import GameObjects.MiddlePart.Tank.Gun;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CannonGun extends PlayerGun {

    private int numberOfBullet;


    public CannonGun(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
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
    public void shot(BattleField battleField, int mouseX, int mouseY) {
        if(numberOfBullet > 0) {
            battleField.add(new Cannon1(locationX, locationY, mouseX, mouseY));
        }else{
            try {
                File soundFile = new File("files" + File.separator + "Sounds" + File.separator + "emptyGun.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }




}
