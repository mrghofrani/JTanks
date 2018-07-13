package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject implements Exploder,MiddlePart {
    protected double angle;
    protected int speed;
    protected BattleField battleField;
    private BufferedImage[] explodeImages;

    public Bullet(){
        try {
            image = ImageIO.read(new File("files\\Images\\giphy.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        explodeImages = new BufferedImage[10];
//        for(int i = 1 ; i < explodeImages.length + 1 ; i++){
//            try {
//                explodeImages[i - 1] = ImageIO.read(new File("files\\Images\\explode\\f" + i + ".gif"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
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
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);

        battleField.collisionTest(this);
    }

    @Override
    public void act() {

    }

    @Override
    public void explode() {
        // inja
        playSound("files\\Sounds\\EnemyBulletToMyTank.wav");
//        for(int i = 0 ; i < explodeImages.length ; i++){
//            image = explodeImages[i];
//        }
        try {
            image = ImageIO.read(new File("files\\Images\\giphy.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
