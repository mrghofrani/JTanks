package GameObjects;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GameObject {
    protected int locationX; // X location of the object
    protected int locationY; // Y location of the object
    protected String IMAGE_PATH = "files" + File.separator + "Images" + File.separator; // Main part of image of the object
    protected String SOUND_PATH = "files" + File.separator + "Images" + File.separator; // Main part of sound of the object
    protected BufferedImage image;// image of the object
    protected double health;

    /**
     * in each turn each of the participants
     * have right to play and enjoy :)
     */
    public abstract void act();

    /**
     * For setting an image for each
     */
    protected void setImage(){
        File file = new File(IMAGE_PATH);
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occured while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method gets {@param soundState} and
     * play a sound while creating an object
     */
    protected synchronized void playSound(boolean soundState, String fileName) {
        if(soundState) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        File soundFile = new File(SOUND_PATH + fileName);
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
                }
            }).start();
        }
    }
    /**
     * @return returns the full boundary of an image in Rectangle
     */
    public Rectangle getBounds(){
        return new Rectangle(locationX,locationY,getBoundX() - 5,getBoundY() - 5);
    }

    /**
     * @return the width of the game object's image
     */
    public int getBoundX(){
        return image.getWidth() - 5;
    }

    /**
     * @return the height of the game object's image
     */
    public int getBoundY(){
        return image.getHeight() - 5;
    }

    /**
     * This method is used to draw each
     * GameObject on the screen. {@param g} is
     * used to get the bufferStrategy's brush to
     * draw each element of GameObject on Screen.
     */
    public void doRendering(Graphics2D g){
        g.drawImage(image,locationX,locationY,null);
    }

    /**
     * @returns the health of the object
     */
    public double getHealth(){
        return health;
    }

    /**
     * @returns X location of the object
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * @returns Y location of the object
     */
    public int getLocationY() {
        return locationY;
    }
}