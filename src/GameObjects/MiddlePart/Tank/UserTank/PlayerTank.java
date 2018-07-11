package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.GameState;
import GameObjects.GameObject;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayerTank extends GameObject {
    public static int YLocation;
    public static int XLocation;
    public static double angle = -3 * Math.PI/2;
    public static double gunAngle = 0;
    public static int gunLocationX;
    public static int gunLocationY;
    public static boolean isCannonGun;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;

    public PlayerTank(int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        XLocation = locationX;
        YLocation = locationY;
        gunLocationX = XLocation;
        gunLocationY = YLocation;
        setImage();

        cannonGun = new CannonGun();
        machineGun = new MachineGun();
        gun = cannonGun;
        isCannonGun = true;
    }


    @Override
    public void act() {

    }


    public void doRendering(Graphics2D g2d, int XOffset, int YOffset){
        if(XLocation < 0 - image.getWidth()/2) XLocation = -image.getWidth()/2;
        if(YLocation < 0 - image.getHeight()/2) YLocation = -image.getHeight()/2;
        if(XLocation > 600 + image.getWidth()/2) XLocation = 600 + image.getWidth()/2;
        if(YLocation > 600 + image.getHeight()/2) YLocation = 600 + image.getHeight()/2;
//        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 105 " + " locationX: " + XLocation  + " locationY: " + YLocation );
        System.out.println(this.getClass().getName() + " line 98 " + " gunAngle " + (gunAngle/Math.PI) + "PI");
        paintTank(g2d);
//        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 113" + " gunLocationX: " + gunLocationX  + " gunLocationY: " + gunLocationY );
//        System.out.println(this.getClass().getName() + " line 114 " + " angle " + (gunAngle/Math.PI) + "PI");
        if(isCannonGun)
            gun = cannonGun;
        else
            gun = machineGun;
        System.out.println(this.getClass().getName() + " line 59 " + " isCannonGun " + isCannonGun);
        gun.doRendering(g2d,XLocation ,YLocation,gunAngle);
    }

    private void paintTank(Graphics2D g2d){
        AffineTransform at = new AffineTransform();
        at.setToTranslation(XLocation + 50,YLocation + 50);
        at.rotate(angle);
        at.translate(-50,-50);
        g2d.drawImage(image,at,null);
    }
}
