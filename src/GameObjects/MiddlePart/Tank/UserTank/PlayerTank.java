package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameBasis.GameFrame;
import GameBasis.GameLoop;
import GameBasis.GameState;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Items.*;
import GameObjects.MiddlePart.MiddlePart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This is our main player gun
 */
public class PlayerTank extends GameObject implements Explosive,HardObject,MiddlePart {
    public double angle = -3 * Math.PI/2;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;
    private BattleField battleField;
    private int savedLocationX;
    private int savedLocationY;
    private int life = 3;
    private int health ;

    public PlayerTank(BattleField battleField, int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        setImage();
        cannonGun = new CannonGun();
        machineGun = new MachineGun();
        gun = cannonGun;
        health = 40;
    }

    /**
     * This method is useless
     */
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
                this.locationY -= 2;
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
                this.locationY += 2;
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
                this.locationX += 2;
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
                this.locationX -= 2;
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
                this.locationX -= 2;
                this.locationY += 2;
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
                this.locationX += 2;
                this.locationY += 2;
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
                this.locationX += 2;
                this.locationY -= 2;
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
        battleField.move();
        battleField.collision(this);
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

    public void shot(){
        gun.shot(battleField,locationX + 50,locationY + 50);
//        playSound("recosh.wav");
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

    /**
     * Using this method player gun paints itself
     * on the screen
     * @param g2d the brush used to draw
     * @param XOffset how long does the map moved in X axis
     * @param YOffset how long does the map moved in Y axis
     */
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset){
        paintTank(g2d,XOffset,YOffset);
        gun.doRendering(g2d,locationX + XOffset,locationY + YOffset);
    }

    /**
     * This method draws the body of the tank to the screen
     * @param g2d the brush used to draw
     * @param XOffset how long does the map moved in X axis
     * @param YOffset how long does the map moved in Y axis
     */
    private void paintTank(Graphics2D g2d,int XOffset,int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(angle,this.locationX + XOffset + 50, this.locationY + YOffset + 50);
        g2d.transform(at);
        g2d.drawImage(image,this.locationX + XOffset ,this.locationY + YOffset ,null);
        g2d.setTransform(backup);
    }

    /**
     * draws details of the tank such as
     * number of CannonBullet
     * number of MachineGunBullet
     * health of the tank
     * @param g2d brush used to paint
     */
    public void drawDetails(Graphics2D g2d){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("files" + File.separator + "Images" + File.separator + "NumberOfHeavyBullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.drawImage(image,50,50,null);
        g2d.setColor(Color.YELLOW);
        g2d.drawString(cannonGun.getNumberOfBullet() + "",110,100);

        try{
            image = ImageIO.read(new File("files" + File.separator + "Images" + File.separator + "NumberOfMachinGun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.drawImage(image,50,100,null);
        g2d.setColor(Color.YELLOW);
        g2d.drawString(machineGun.getNumberOfBullet() + "",110,150);


        try {
            image = ImageIO.read(new File("files" + File.separator + "Images" + File.separator + "health.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int savedHealth = health;

        if(savedHealth == 40) {
            g2d.drawImage(image, 150, 50, null);
            savedHealth -= 10;
        }if(savedHealth >= 30 && savedHealth < 40) {
            g2d.drawImage(image, 150 + 50, 50, null);
            savedHealth -= 10;
        }if(savedHealth >= 20 && savedHealth < 30) {
            g2d.drawImage(image, 150 + 100, 50, null);
            savedHealth -= 10;
        }if(savedHealth >= 10 && savedHealth < 20) {
            g2d.drawImage(image, 150 + 150, 50, null);
            savedHealth -= 10;
        }if(savedHealth >= 0 && savedHealth < 10 && health == 40) {
            g2d.drawImage(image, 150 + 200, 50, null);
        } else if(savedHealth > 0 && savedHealth < 10){
            g2d.drawImage(image, 150 + 200, 50, null);
        }

        try {
            image = ImageIO.read(new File("files" + File.separator + "Images" + File.separator + "heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lifeTmp = life;
        if(lifeTmp == 3) {
            g2d.drawImage(image, 450, 50, null);
            lifeTmp--;
        }if(lifeTmp == 2) {
            g2d.drawImage(image, 500, 50, null);
            lifeTmp--;
        }if(lifeTmp== 1) {
            g2d.drawImage(image, 550, 50, null);
            lifeTmp--;
        }


    }
    /**
     * set the life to its initial value
     */
    public void setDefaultLife(){
        life = 3;
    }
    /**
     * repairs the tank that's it.
     */
    public boolean repair(){
        int tmp = health;
        if(health != 50)
            health += 10;
        return tmp != health;
    }
    /**
     * used to eat an item
     * @param item item supposed to be eaten
     */
    public void eatItem(Item item){
        if(item instanceof CannonBulletCartridgeItem){
            cannonGun.numberOfBullet += item.getGift();
            item.dispose();
        }else if(item instanceof MachineGunCartridgeItem){
            machineGun.numberOfBullet += item.getGift();
            item.dispose();
        }else if(item instanceof RepairItem){
            if(repair())
                item.dispose();
        }else if(item instanceof UpgradeGunItem){
            promoteWeapon();
            item.dispose();
        }
    }
    /**
     * @return true if player is using the main gun
     * if the player uses secondary gun returns false
     */
    public boolean isMainGun(){
        return gun == cannonGun;
    }

    /**
     * @return location x
     */
    public int getLocationX(){
        return this.locationX;
    }

    /**
     * @return location y
     */
    public int getLocationY(){
        return this.locationY;
    }
    /**
     * @returns current gun of the player
     */
    public PlayerGun getGun() {
        return gun;
    }
    /**
     * upgrades the weapon
     */
    public void promoteWeapon(){
        gun.promote();
    }
    /**
     * resets the health to default values
     */

    public void setDefaultHealth(){
        health = 50;
    }

    /**
     * This method runs when
     * a explosive object is
     * going to be damaged
     *
     * @param value
     */
    @Override
    public void explode(int value) {
        if(health - value > 0){
            health -= value;
            System.out.println(value);
            System.out.println(health);
        }else if(life - 1 >= 0){
            life--;
            health = 40;
        } else{
            GameLoop.gameOver = true;
            GameState.gameOver = true;
            System.out.println("here");
        }
        playSound("EnemyBulletToMyTank.wav");
    }
    /**
     * stop the tank from moving
     */
    @Override
    public void stop() {
        locationX = savedLocationX;
        locationY = savedLocationY;
        battleField.stop();
    }
    /**
     * @returns the width of the tank's body
     */
    public int getWidth(){
        return image.getWidth();
    }

    /**
     * @returns the height of the tank's body
     */
    public int getHeight(){
        return image.getHeight();
    }
}
