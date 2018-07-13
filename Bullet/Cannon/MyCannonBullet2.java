package Bullet.Cannon;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MyCannonBullet2 extends MyCannonBullet {

    private static String defPicPath = IMAGE_PATH + "LightBullet.png";
    public MyCannonBullet2(int locationX, int locationY, int degree){
        super();
        initialize(locationX,locationY,degree);
        try {
            File soundFile = new File(Explode_Sound_PATH);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            //e.printStackTrace();
        } catch (IOException e) {
           // e.printStackTrace();
        } catch (LineUnavailableException e) {
           // e.printStackTrace();
        }

        // after creation the bullet is moving

    }

    /**
     * This method initializes the variables
     * declared in the parent class
     * @param locationX the X location of where the bullet was shot
     * @param locationY the X location of where the bullet was shot
     * @param degree degree to rotate the bullet and determine the speed of bullet in both X and Y directions
     */
    protected void initialize(int locationX,int locationY, int degree){
//        IMAGE_PATH += "MyCannonBullet2.png";
        Explode_Sound_PATH += "MineBoom.wav";
        Recosh_Sound_PATH += "recosh.wav";
        try {
            bullet = ImageIO.read(new File(defPicPath));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Following file not found \n" + IMAGE_PATH, "Error",JOptionPane.ERROR_MESSAGE );
        }
        speed = 8;
        this.degree = degree;
        damage = 2;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public static void changeBulletType(int gunTypeNumber){
        if(gunTypeNumber == 1){
            defPicPath = IMAGE_PATH + "LightBullet.png";
        }else if(gunTypeNumber == 2){
            defPicPath = IMAGE_PATH + "HeavyBullet.png";
        }
    }

}
