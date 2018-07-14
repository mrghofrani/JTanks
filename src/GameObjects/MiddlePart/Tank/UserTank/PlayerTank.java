package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameBasis.GameFrame;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Items.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerTank extends GameObject implements Explosive,HardObject{
    public double angle = -3 * Math.PI/2;
    private MachineGun machineGun;
    private CannonGun cannonGun;
    private PlayerGun gun;
    private BattleField battleField;
    private int savedLocationX;
    private int savedLocationY;
    private int delay;
    private int life = 3;
    private int health = 50;
    private ArrayList<String> explodeImages = new ArrayList<>(9);

    public PlayerTank(BattleField battleField, int locationX, int locationY) {
        this.IMAGE_PATH += "playerTank.png";
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        setImage();
        cannonGun = new CannonGun();
        machineGun = new MachineGun();
        gun = cannonGun;
        for(int i = 0 ; i < 9; i++) {
            explodeImages.add("files" + File.separator + "Images" + File.separator + "explode" + File.separator + "f" + (i + 1) + ".png");
        }
        health = 30;
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
        playSound("recosh.wav");
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

        if(savedHealth == 50) {
            g2d.drawImage(image, 200, 50, null);
            savedHealth -= 10;
        }if(savedHealth == 40) {
            g2d.drawImage(image, 200 + 50, 50, null);
            savedHealth -= 10;
        }if(savedHealth == 30) {
            g2d.drawImage(image, 200 + 100, 50, null);
            savedHealth -= 10;
        }if(savedHealth == 20) {
            g2d.drawImage(image, 200 + 150, 50, null);
            savedHealth -= 10;
        }if(savedHealth == 10) {
            g2d.drawImage(image, 200 + 200, 50, null);
        }
    }

    public void setDefaultLife(){
        life = 3;
    }

    public boolean repair(){
        int tmp = health;
        if(health != 50)
            health += 10;
        return tmp != health;
    }

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


    public boolean isMainGun(){
        return gun == cannonGun;
    }

    public int getLocationX(){
        return this.locationX;
    }

    public int getLocationY(){
        return this.locationY;
    }

    public PlayerGun getGun() {
        return gun;
    }
    private void promoteWeapon(){
        gun.promote();
    }


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
        for (String item: explodeImages) {
            IMAGE_PATH = item;
            setImage();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        playSound("EnemyBulletToMyTank.wav");
        stop();
    }

    @Override
    public void stop() {
        locationX = savedLocationX;
        locationY = savedLocationY;
        battleField.stop();
    }


}
