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

public class EBullet extends GameObject implements MiddlePart,Exploder,Bullet {
    protected double angle;
    protected int speed;
    protected BattleField battleField;
    protected int damage;
    protected int savedLocationX;
    protected int savedLocationY;
    protected Runnable actions;


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
        actions = new Runnable() {
            @Override
            public void run() {
                Timer moveTimer = new Timer(90, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(damage != 0) {
                            move();
                        }
                    }
                });
                moveTimer.start();
            }
        };
        ThreadPool.execute(actions);
    }

    @Override
    public void explode() {
        dispose();
        stop();
    }

    protected void stop(){
        locationX = savedLocationX;
        locationY = savedLocationY;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void dispose() {
        playSound("recosh.wav");
        damage = 0;
    }
}
