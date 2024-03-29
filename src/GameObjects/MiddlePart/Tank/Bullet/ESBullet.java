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
import java.io.File;
import java.util.ArrayList;

public class ESBullet extends GameObject implements MiddlePart,Exploder,Bullet {
    protected double angle;
    protected int speed;
    private int damage;
    protected BattleField battleField;
    private int savedLocationX;
    private int savedLocationY;

    public ESBullet(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 8;
        damage = 10;
        this.IMAGE_PATH += "esbullet.png";
        setImage();
        playSound("esbullet.wav");


/**
 * this is thread of aiming target
 */
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                Timer aimTimer = new Timer(100, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aim();
                    }
                });
                aimTimer.start();
            }
        };
        ThreadPool.execute(runnable3);
        /**
         * this is thread of moving bullet
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timer timer =  new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        move();
                    }
                });
                timer.start();
            }
        };
        ThreadPool.execute(runnable);
    }
    /**
     * this method job is drawing bullet
     * @param g2d
     * @param XOffset
     * @param YOffset
     */
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle - Math.PI/2,locationX + XOffset + 20, locationY + YOffset + 20);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset + image.getWidth(),locationY + YOffset,null);
        g2d.setTransform(backup);
    }
    /**
     * this method handles moving bullet
     */
    private void move(){
        savedLocationX = locationX;
        savedLocationY = locationY;
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        battleField.collision(this);
    }
    /**
     * this method handles aiming target by calculating angle
     */
    protected void aim(){
        angle = Math.atan2(battleField.getPlayerTank().getLocationY() - locationY,battleField.getPlayerTank().getLocationX() - locationX );
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
    private void stop(){
        locationX = savedLocationX;
        locationY = savedLocationY;
    }
    /**
     * @return damage value
     */
    @Override
    public int getDamage() {
        return damage;
    }
    /**
     * handles deleting bullet
     */
    @Override
    public void dispose() {
        playSound("recosh.wav");
        isDeleted = true;
    }
}
