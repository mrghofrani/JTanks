package GameObjects.TopPart;

import GameObjects.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Grass extends GameObject implements TopPart {

    public Grass(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "grass.png";
        setImage();
    }
    /**
     * This method is using to force an object to be act
     */
    @Override
    public void act() {
        // Rest
    }
    /**
     * For setting an image for each
     */
    @Override
    protected void setImage() {
        File file = new File(IMAGE_PATH);
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occured while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
