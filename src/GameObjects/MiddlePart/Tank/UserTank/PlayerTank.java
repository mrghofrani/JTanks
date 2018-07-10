package GameObjects.MiddlePart.Tank.UserTank;

import GameObjects.MiddlePart.Tank.Gun;
import GameObjects.MiddlePart.Tank.Tank;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class PlayerTank extends Tank {
    public static int XOffset;
    public static int YOffset;
    public static int YLocation;
    public static int XLocation;
    public static double angle = -3 * Math.PI/2;
    public static double gunAngle = 0;
    public static int gunLocationX;
    public static int gunLocationY;
    private boolean isCannonGun;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;

    public PlayerTank(int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        XLocation = locationX;
        YLocation = locationY;

        setImage();

        cannonGun = new CannonGun();
//        machineGun = new MachineGun();
        gun = cannonGun;
        isCannonGun = true;
    }

    /**
     * This method is supposed to
     * act when player presses the
     * leftClick
     */
    @Override
    public void fire() {

//        gun.fire();
    }

    @Override
    public void rotateTank(boolean direction) {

    }

    @Override
    public void rotateGun() {

    }

    @Override
    public void promoteWeapon() {

    }

    /**
     * This method is supposed to
     * change the gun of the Tank
     * when player pressed right click
     */
    @Override
    public void switchGun() {
        isCannonGun = !isCannonGun;
        if(isCannonGun)
            gun = cannonGun;
//        else
////            gun = machineGun;
    }

    @Override
    public void destruct() {

    }

    @Override
    public void fullDestruct() {

    }

    @Override
    public void reNewTank() {

    }

    @Override
    public void act() {

    }

    public void doRendering(Graphics2D g2d,int XOffset,int YOffset){
        if(XLocation < 0 - image.getWidth()/2) XLocation = -image.getWidth()/2;
        if(YLocation < 0 - image.getHeight()/2) YLocation = -image.getHeight()/2;
        if(XLocation > 600 + image.getWidth()/2) XLocation = 600 + image.getWidth()/2;
        if(YLocation > 600 + image.getHeight()/2) YLocation = 600 + image.getHeight()/2;
//        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 105 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 98 " + " angle " + (angle/Math.PI) + "PI");
        AffineTransform at = new AffineTransform();
        at.setToTranslation(XLocation + 50,YLocation - 50);
        at.rotate(angle);
        at.translate(-50,-50);

        g2d.drawImage(image,at,null);

        gun.doRendering(g2d,XLocation + 10, YLocation - 100,gunAngle);
    }
}
