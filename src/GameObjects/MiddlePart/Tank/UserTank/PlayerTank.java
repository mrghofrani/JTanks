package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameBasis.GameFrame;
import GameObjects.GameObject;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayerTank extends GameObject {
    public double angle = -3 * Math.PI/2;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;
    private BattleField battleField;
    private int savedLocationX;
    private int savedLocationY;
    private int delay;

    public PlayerTank(BattleField battleField, int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        setImage();
        cannonGun = new CannonGun();
        machineGun = new MachineGun();
        gun = cannonGun;
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
        savedLocationX = locationX;
        savedLocationY = locationY;
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

            if(angle == mainAngle)
                this.locationY -= 4;
            else
                this.locationY -= 1;
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

            if(angle == mainAngle)
                this.locationY += 4;
            else
                this.locationY += 1;
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

            if(angle == 0)
                this.locationX += 4;
            else
                this.locationX += 1;
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

            if(angle == mainAngle)
                this.locationX -= 4;
            else
                this.locationX -= 1;
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
                this.locationX -= 4;
                this.locationY -= 4;
            }
            else {
                this.locationX -= 2;
                this.locationY -= 2;
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
                this.locationX -= 4;
                this.locationY += 4;
            }
            else{
                this.locationX -= 1;
                this.locationY += 1;
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
                this.locationX += 4;
                this.locationY += 4;
            }
            else{
                this.locationX += 1;
                this.locationY += 1;
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
                this.locationX += 4;
                this.locationY -= 4;
            }
            else{
                this.locationX += 1;
                this.locationY -= 1;
            }
        }
        restrictMovement();
    }


    /**
     * This method is supposed to used
     * for restrictions of tank's movement
     */
    private void restrictMovement(){
        // The condition that allows the movement of tank or not
        if(locationX < 0 - image.getWidth()/2 || locationX > 2*GameFrame.GAME_WIDTH - image.getWidth()/2)
            locationX = savedLocationX;
        if(locationY < 0 - image.getHeight()/2 || locationY > 2*GameFrame.GAME_HEIGHT - image.getHeight()/2)
            locationY = savedLocationY;

        if(battleField.collisionTest(this)) {
            locationX = savedLocationX;
            locationY = savedLocationY;
            battleField.stop();
        } else
            battleField.move();

    }

    /**
     * This method uses the following arguments to
     * aim to the target by his gun
     * @param mouseX X location of mouse
     * @param mouseY Y location of mouse
     */
    public void aim(int mouseX,int mouseY){
        gun.aim(locationX + battleField.XOffset,locationY + battleField.YOffset,mouseX,mouseY);
    }

    public void shot(int mouseX,int mouseY){
        gun.shot(battleField,locationX + 50,locationY + 50 ,mouseX,mouseY);
//        try {
//            wait(100);
//        } catch (InterruptedException e) {
////            e.printStackTrace();
//        }
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
        paintTank(g2d,XOffset,YOffset);
        gun.doRendering(g2d,locationX + XOffset,locationY + YOffset);
    }


    private void paintTank(Graphics2D g2d,int XOffset,int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle,this.locationX + XOffset + 50, this.locationY + YOffset + 50);
        g2d.transform(at);
        g2d.drawImage(image,this.locationX + XOffset ,this.locationY + YOffset ,null);
        g2d.setTransform(backup);
    }

    public int getLocationX(){
        return this.locationX;
    }

    public int getLocationY(){
        return this.locationY;
    }
}
