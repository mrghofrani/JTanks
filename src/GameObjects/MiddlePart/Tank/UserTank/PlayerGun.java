package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class PlayerGun {

    protected int locationX;
    protected int locationY;
    protected BufferedImage image;
    protected String IMAGE_PATH = "files" + File.separator + "Images" + File.separator;



    public abstract void doRendering(Graphics2D g2d,int locationX,int locationY, double angle);
    public abstract void shot(BattleField battleField,int mouseX,int mouseY);


    public void setImage(){
        File file = new File(IMAGE_PATH);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occurred while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
