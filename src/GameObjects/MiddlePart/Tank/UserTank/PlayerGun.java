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

    protected BufferedImage image;
    protected double angle;
    protected String IMAGE_PATH = "files" + File.separator + "Images" + File.separator;


    public abstract void shot(BattleField battleField,int locationX,int locationY,int mouseX,int mouseY);
    public abstract void aim(int locationX,int locationY, int mouseX,int mouseY);


    public void doRendering(Graphics2D g2d, int locationX, int locationY) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle,locationX  + 50, locationY + image.getHeight()/2);
        g2d.transform(at);
        g2d.drawImage(image,locationX + 10,locationY ,null);
        g2d.setTransform(backup);
    }

    public void setImage(){
        File file = new File(IMAGE_PATH);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occurred while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void outOfBulletSound(){
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
