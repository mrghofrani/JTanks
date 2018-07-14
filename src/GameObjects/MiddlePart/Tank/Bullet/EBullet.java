package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;

public class EBullet extends GameObject implements MiddlePart,Exploder,Bullet {
    protected double angle;
    protected int speed;
    protected BattleField battleField;
    protected int damage;
    private ArrayList<String> explodeImages = new ArrayList<>(9);
    protected int savedLocationX;
    protected int savedLocationY;

    protected EBullet(){
        for(int i = 0 ; i < 9; i++) {
            explodeImages.add("files" + File.separator + "Images" + File.separator + "explode" + File.separator + "f" + (i + 1) + ".png");
        }
    }

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
        for (String item: explodeImages) {
            IMAGE_PATH = item;
            setImage();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        playSound("EnemyBulletToMyTank.wav");
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
