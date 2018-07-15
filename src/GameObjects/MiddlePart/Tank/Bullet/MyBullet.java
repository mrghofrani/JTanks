package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;
import ThreadPool.ThreadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    protected Runnable actions;
    /**
     * draw's MyBullet
     * @param g2d
     * @param XOffset
     * @param YOffset
     */

    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        move();
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle - Math.PI/2,locationX + XOffset, locationY + YOffset);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset + image.getWidth(),locationY + YOffset,null);
        g2d.setTransform(backup);
    }
    /**
     * this method handles moving bullet
     */
    public void move(){
        savedLocationX = locationX;
        savedLocationY = locationY;
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        battleField.collision(this);
    }

    /**
     * in each turn each of the participants
     * have right to play and enjoy :)
     */
    @Override
    public void act() {

    }

    /**
     * handle's exploding bullet
     */
    @Override
    public void explode() {
        dispose();
        stop();
    }
    /**
     *  stop's bullet
     */
    public void stop(){
        locationX = savedLocationX;
        locationY = savedLocationY;
    }
    /**
     * handles deleting bullet
     */
    @Override
    public void dispose() {
        playSound("recosh.wav");
        isDeleted = true;
    }
    /**
     * @return damage value
     */
    @Override
    public int getDamage() {
        return damage;
    }
}
