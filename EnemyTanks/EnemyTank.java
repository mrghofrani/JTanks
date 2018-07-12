package GameObjects.MiddlePart.Tank.EnemyTanks;

import Bullet.Cannon.CannonBullet;
import Bullet.Cannon.MyCannonBullet2;
import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class EnemyTank extends GameObject implements MiddlePart {

    protected int health;
    protected int speed;
    protected BufferedImage tankBody;
    protected String GUN_IMAGE_PATH = "files" + File.separator + "Images" + File.separator;
    protected BufferedImage gunImage;

//    private final ArrayList<Image> BODY_IMAGES;
    private int bodyType;
//    private final ArrayList<Image> GUN_IMAGES;
    protected int gunType = 1;

    private Double health = 3d;

    private final int MAX_NUMBER_OF_CARTRIDGE = 1000;
    private int numberOfCannonCartridges = 50;
    private int numberOfMachineGunCartridges = 300;


    public abstract void fire();
    public abstract void rotateTank(boolean direction);

   // public abstract void render(Graphics2D g2d);

//    public double getTankX() {
//        return tankX;
//    }
//
//    public double getTankY() {
//        return tankY;
//    }
//
      public void setGunImage() {
          try {
              gunImage = ImageIO.read(new File(GUN_IMAGE_PATH));
          } catch (IOException e) {
              JOptionPane.showMessageDialog(null,"Following file not found " + GUN_IMAGE_PATH , "Error",JOptionPane.ERROR_MESSAGE);
          }
      }


    @Override
    public void act() {

    }

}
