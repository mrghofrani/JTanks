package GameObjects.MiddlePart.Tank.PlayerTank;

import Bullet.Cannon.CannonBullet;
import Bullet.Cannon.MyCannonBullet2;
import GameObjects.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerTank extends GameObject {

    protected BufferedImage tankBody;

//    private final ArrayList<Image> BODY_IMAGES;
    private int bodyType;
//    private final ArrayList<Image> GUN_IMAGES;
    protected int gunType = 1;

    private Double health = 3d;

    private final int MAX_NUMBER_OF_CARTRIDGE = 1000;
    private int numberOfCannonCartridges = 50;
    private int numberOfMachineGunCartridges = 300;




    public PlayerTank(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "playerTank_1.png";
        setImage();
    }

    public abstract void fire();
    public abstract void rotateTank(boolean direction);
    public abstract void rotateGun();
    public abstract void promoteWeapon();
    public abstract void switchGun();
    public abstract void destruct();
    public abstract void fullDestruct();
    public abstract void reNewTank();
   // public abstract void render(Graphics2D g2d);

    public double getTankX() {
        return tankX;
    }

    public double getTankY() {
        return tankY;
    }

    public void setTankGun(String tankGunPath) throws IOException {
        this.tankGun = ImageIO.read(new File(tankGunPath ));
    }


    @Override
    public void act() {

    }

    @Override
    protected void setImage() {
        File file = new File(IMAGE_PATH);
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error while reading the file " + IMAGE_PATH,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
