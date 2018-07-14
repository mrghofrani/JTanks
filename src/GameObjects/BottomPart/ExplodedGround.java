package GameObjects.BottomPart;

import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ExplodedGround extends GameObject implements MiddlePart {

    private double angle;

    public ExplodedGround(int locationX,int locationY,double angle){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "explodedGround.png";
        this.angle = angle;
        setImage();
    }

    @Override
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle,locationX  + XOffset + 50, locationY + YOffset + image.getHeight()/2);
        g2d.transform(at);
        g2d.drawImage(this.image,locationX + XOffset + 50,locationY + YOffset + 50,null);
        g2d.setTransform(backup);
    }

    /**
     * in each turn each of the participants
     * have right to play and enjoy :)
     */
    @Override
    public void act() {

    }
}
