package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;
import ThreadPool.ThreadPool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MyBullet extends GameObject implements Exploder,MiddlePart,Bullet {
    protected double angle;
    protected int speed;
    protected int damage;
    protected BattleField battleField;
    protected int savedLocationX;
    protected int savedLocationY;

    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        move();
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle - Math.PI/2,locationX + XOffset, locationY + YOffset);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset + image.getWidth(),locationY + YOffset,null);
        g2d.setTransform(backup);
    }

    public void move(){
        savedLocationX = locationX;
        savedLocationY = locationY;
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        battleField.collision(this);
    }

    @Override
    public void act() {

    }

    @Override
    public void explode() {
        dispose();
        stop();
    }

    public void stop(){
        locationX = savedLocationX;
        locationY = savedLocationY;
    }

    @Override
    public void dispose() {
        playSound("recosh.wav");
        damage = 0;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}