package GameObjects.MiddlePart.Tank.Bullet;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Cannon1 extends GameObject implements Exploder,MiddlePart {

    private double angle;
    private int mouseX;
    private int mouseY;
    private int speed = 10;


    public Cannon1(int locationX, int locationY, int mouseX, int mouseY){
        this.locationX = locationX + 50;
        this.locationY = locationY + 50;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
        this.IMAGE_PATH += "Cannon1.png";
        setImage();
        act();
    }

    @Override
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        move();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(locationX + XOffset ,locationY + YOffset);
        at.rotate(angle - Math.PI/2);
        g2d.drawImage(image,at,null);
        g2d.drawLine(locationX,locationY,mouseX,mouseY);
    }

    @Override
    public void act() {
    }

    public void move(){
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
    }


}
