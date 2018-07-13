package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;
import ThreadPool.ThreadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class ESBullet extends GameObject implements MiddlePart {
    protected double angle;
    protected int speed;
    protected BattleField battleField;

    public ESBullet(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 8;
        this.IMAGE_PATH += "esbullet.png";
        setImage();
        playSound("esbullet.wav");

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

    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle - Math.PI/2,locationX + XOffset + 20, locationY + YOffset + 20);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset + image.getWidth(),locationY + YOffset,null);
        g2d.setTransform(backup);
    }

    private void move(){
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
    }

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
}
