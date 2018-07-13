package Bullet.Cannon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class CannonBullet {
    protected int speed = 5;
    protected int degree;
    protected int damage;
    protected  BufferedImage bullet;
    protected static String IMAGE_PATH  = "files\\Images\\";
    protected int locationX;
    protected int locationY;

    protected abstract void initialize(int locationX,int locationY, int degree);
    public abstract void move();

    public BufferedImage getBullet() {
        return bullet;
    }

    public  void setBullet(BufferedImage bullet) {
        this.bullet = bullet;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }
}
