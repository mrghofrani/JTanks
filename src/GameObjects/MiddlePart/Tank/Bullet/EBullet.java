package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class EBullet extends GameObject implements MiddlePart,Exploder,Bullet {
    protected double angle;
    protected int speed;
    protected BattleField battleField;
    protected int damage;
    protected int savedLocationX;
    protected int savedLocationY;
    protected Runnable actions;

    /**
     * this method draw's enemy bullet and it' moving
     * @param g2d  is graphics
     * @param XOffset is x coordinate of object
     * @param YOffset is y coordinate of object
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
     * manage's move of enemy bullet
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
     * handle's exploding of enemy bullet
     */
    @Override
    public void explode() {
        dispose();
        stop();
    }
    /**
     * stop's bullet
     */
    protected void stop(){
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
     * manage's deleting bullet
     */
    @Override
    public void dispose() {
        playSound("recosh.wav");
        isDeleted = true;
    }
}
