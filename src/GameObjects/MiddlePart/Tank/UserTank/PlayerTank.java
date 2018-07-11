package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameBasis.GameFrame;
import GameObjects.GameObject;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayerTank extends GameObject {
    public int YLocation;
    public int XLocation;
    public double angle = -3 * Math.PI/2;
    public double gunAngle = 0;
    public int gunLocationX;
    public int gunLocationY;
    public boolean isCannonGun;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;
    private BattleField battleField;

    public PlayerTank(BattleField battleField, int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        XLocation = locationX;
        YLocation = locationY;
        this.battleField = battleField;
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

    /**
     * This method is related to movements of PlayerTank.
     * it gets the following parameters as descripted:
     * @param keyUP is going to upward
     * @param keyDOWN is going to downward
     * @param keyRIGHT is going to go rightward
     * @param keyLEFT is going to go leftward
     * and then decides to go in which way and have a rotation or not
     */
    public void move(boolean keyUP,boolean keyDOWN,boolean keyRIGHT,boolean keyLEFT){
        double mainAngle;
        if(keyUP && !keyLEFT && !keyRIGHT){

            if(Math.abs(angle - (3*Math.PI/2)) < Math.abs(angle + Math.PI/2 )){
                mainAngle = 3*Math.PI/2;
            }
            else {
                mainAngle = -Math.PI/2;
            }

            if(angle - mainAngle > 0)
                angle -= 0.05;
            else
                angle += 0.05;

            if(Math.abs(angle - mainAngle) < 0.1 )
                angle = mainAngle;

            YLocation -= 1;
            if(angle == mainAngle)
                YLocation -= 4;
        }

        else if(keyDOWN && !keyLEFT && !keyRIGHT){

            if(Math.abs(angle + (3*Math.PI/2)) < Math.abs(angle - Math.PI/2 )){
                mainAngle = -(3*Math.PI/2);
            }
            else {
                mainAngle = Math.PI/2;
            }
            if(angle > mainAngle){
                angle -= 0.05;
            }
            else
                angle += 0.05;
            if(Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;
            YLocation -= 1;
            if(angle == mainAngle)
                YLocation += 4;
        }

        else if(keyRIGHT && !keyUP && !keyDOWN){
            double difference = Math.abs(angle);
            mainAngle = 0;
            if(Math.abs(angle - 2 * Math.PI) < difference) {
                mainAngle = 2 * Math.PI;
                difference = Math.abs(angle - 2 * Math.PI);
            }
            if(Math.abs(angle + 2 * Math.PI) < difference){
                mainAngle = -2*Math.PI;
            }


            if(angle - mainAngle> 0)
                angle -= 0.05;
            else
                angle += 0.05;

            if(Math.abs(angle - mainAngle) < 0.1)
                angle = 0;

            XLocation += 1;
            if(angle == 0)
                XLocation += 4;
        }

        else if(keyLEFT && !keyUP && !keyDOWN){
            if(Math.abs(angle - (Math.PI)) < Math.abs(angle + Math.PI ))
                mainAngle = Math.PI;
            else
                mainAngle = -Math.PI;

            if(angle > mainAngle)
                angle -= 0.05;
            else
                angle += 0.05;

            if(Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;
            XLocation -= 1;
            if(angle == mainAngle)
                XLocation -= 4;
        }

        else if(keyLEFT && keyUP) {

            if(Math.abs(angle + (3*Math.PI/4)) < Math.abs(angle - 5*Math.PI/4 )){
                mainAngle = -3*Math.PI/4;
            }
            else {
                mainAngle = 5*Math.PI/4;
            }
            if (angle > mainAngle) {
                angle -= 0.05;
            } else {
                angle += 0.05;
            }

            if (Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;

            if(angle == mainAngle){
                XLocation += 2;
                YLocation -= 2;
            }
            else {
                XLocation += 1;
                YLocation -= 1;
            }
        }

        else if(keyLEFT && keyDOWN){
            if(Math.abs(angle - (3*Math.PI/4)) < Math.abs(angle + 5*Math.PI/4 )){
                mainAngle = 3*Math.PI/4;
            }
            else {
                mainAngle = -5*Math.PI/4;
            }
            if (angle > mainAngle) {
                angle -= 0.05;
            } else {
                angle += 0.05;
            }

            if (Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;

            if(angle == mainAngle) {
                XLocation -= 2;
                YLocation += 2;
            }
            else{
                XLocation -= 1;
                YLocation += 1;
            }
        }

        else if(keyRIGHT && keyDOWN){
            if(Math.abs(angle - (Math.PI/4)) < Math.abs(angle + 7*Math.PI/4 )){
                mainAngle = Math.PI/4;
            }
            else {
                mainAngle = -7*Math.PI/4;
            }
            if (angle > mainAngle) {
                angle -= 0.05;
            } else {
                angle += 0.05;
            }

            if (Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;

            if(Math.abs(angle - (1d/4)*Math.PI) < 0.1)
                angle = (1d/4)*Math.PI;
            if(angle == (Math.PI/4)) {
                XLocation += 2;
                YLocation += 2;
            }
            else{
                XLocation += 1;
                YLocation += 1;
            }
        }

        else if(keyRIGHT && keyUP) {
            if(Math.abs(angle - (7*Math.PI/4)) < Math.abs(angle + Math.PI/4 )){
                mainAngle = 7*Math.PI/4;
            }
            else {
                mainAngle = -Math.PI/4;
            }
            if (angle > mainAngle) {
                angle -= 0.05;
            } else {
                angle += 0.05;
            }

            if (Math.abs(angle - mainAngle) < 0.1)
                angle = mainAngle;

            if (Math.abs(angle + (1d / 4) * Math.PI) < 0.1)
                angle = -(1d / 4) * Math.PI;
            if(angle == -(Math.PI/4)) {
                XLocation += 2;
                YLocation -= 2;
            }
            else{
                XLocation += 1;
                YLocation -= 1;
            }
        }
    }

    /**
     * This method uses the following arguments to
     * aim to the target by his gun
     * @param mouseX X location of mouse
     * @param mouseY Y location of mouse
     */
    public void aim(int mouseX,int mouseY){
        gunAngle = Math.atan2(mouseX - (XLocation + 50),mouseY - (YLocation + 50));
        gunAngle *=(-1);
        gunAngle += Math.PI/2;
    }

    public void shot(int mouseX,int mouseY){
        gun.shot(battleField,mouseX,mouseY);
    }

    /**
     * This method is supposed to
     * change the gun of the player
     */
    public void changeGun(){
        if(gun == cannonGun)
            gun = machineGun;
        else
            gun = cannonGun;
    }

    public void doRendering(Graphics2D g2d, int XOffset, int YOffset){
        if(XLocation < 0 - image.getWidth()/2) XLocation = -image.getWidth()/2;
        if(YLocation < 0 - image.getHeight()/2) YLocation = -image.getHeight()/2;
        if(XLocation > GameFrame.GAME_WIDTH - image.getWidth()/2) XLocation = GameFrame.GAME_WIDTH - image.getWidth()/2;
        if(YLocation > GameFrame.GAME_HEIGHT - image.getHeight()/2 ) YLocation = GameFrame.GAME_HEIGHT - image.getHeight()/2;
//        System.out.println(this.getClass().getName() + " line 49 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 105 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 98 " + " gunAngle " + (gunAngle/Math.PI) + "PI");
        paintTank(g2d);
//        System.out.println(this.getClass().getName() + " line 95 " + " locationX: " + XLocation  + " locationY: " + YLocation );
//        System.out.println(this.getClass().getName() + " line 113" + " gunLocationX: " + gunLocationX  + " gunLocationY: " + gunLocationY );
//        System.out.println(this.getClass().getName() + " line 114 " + " angle " + (gunAngle/Math.PI) + "PI");
        if(isCannonGun)
            gun = cannonGun;
        else
            gun = machineGun;
//        System.out.println(this.getClass().getName() + " line 59 " + " isCannonGun " + isCannonGun);
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
