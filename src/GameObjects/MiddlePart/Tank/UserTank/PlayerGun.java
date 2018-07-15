package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class PlayerGun {
    /**
     * This class is a basis of a gun
     */
    protected BufferedImage image;
    protected double angle;
    protected int numberOfBullet;
    protected String IMAGE_PATH = "files" + File.separator + "Images" + File.separator;
    protected int level;

    /**
     *
     * @param battleField
     * @param locationX
     * @param locationY
     */
    public abstract void shot(BattleField battleField,int locationX,int locationY);
    /**
     * This methods focuses the gun into the target
     */
    public abstract void aim(int locationX,int locationY, int mouseX,int mouseY);
    /**
     * By using this method we draw this
     * component on the screen.
     * @param g2d the brush which we use for drawing
     * @param locationX x location of where to draw
     * @param locationY y location of where to draw
     */
    public void doRendering(Graphics2D g2d, int locationX, int locationY) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle,locationX  + 50, locationY + image.getHeight()/2);
        g2d.transform(at);
        g2d.drawImage(image,locationX + 10,locationY ,null);
        g2d.setTransform(backup);
    }
    /**
     * Using this method we
     * set a specific image to
     * this component on the screen.
     */
    public void setImage(){
        File file = new File(IMAGE_PATH);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occurred while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * When we have no bullets in our gun
     * this method invokes to help us image
     * this is true
     */
    protected void outOfBulletSound(){
        try {
            File soundFile = new File("files" + File.separator + "Sounds" + File.separator + "emptyGun.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method upgrades the gun
     * and its damage
     */
    public abstract void promote();

    /**
     * @return number of bullets remained
     */
    public int getNumberOfBullet() {
        return numberOfBullet;
    }
    /**
     * This is used in cheat mode
     * and sets number of bullets to initial value
     */
    public abstract void setNumberOfBulletsToDefault();
    /**
     * This is used in cheat mode
     * and sets number of bullets into Integer.Max_Value
     */
    public void setNumberOfBulletsToInfinite(){
        numberOfBullet = Integer.MAX_VALUE;
    }

}
