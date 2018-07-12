package GameObjects.MiddlePart.Tank.Bullet;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject implements Exploder,MiddlePart {
    protected double angle;
    protected int speed = 10;


    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        move();
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle - Math.PI/2,locationX + XOffset, locationY + YOffset);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset,locationY + YOffset,null);
        g2d.setTransform(backup);
    }

    protected void move(){
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
    }

    @Override
    public void act() {

    }
}
