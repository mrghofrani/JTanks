package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class EBullet extends GameObject implements MiddlePart,Exploder {
    protected double angle;
    protected int speed;
    protected BattleField battleField;

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
        if(!battleField.collisionTest(this)) {
            locationX += speed * Math.cos(angle);
            locationY += speed * Math.sin(angle);
        }
    }

    @Override
    public void act() {

    }

    @Override
    public void explode() {
        // inja
    }
}
