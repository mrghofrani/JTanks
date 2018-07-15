package GameObjects.MiddlePart.Tank.UserTank;


import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet1;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet2;

import java.io.File;

/**
 * This class represent's machine gun
 */
public class MachineGun extends PlayerGun {


    MachineGun(){
        this.IMAGE_PATH += "playerMachineGun0.png";
        this.level = 1;
        setImage();
        numberOfBullet = 200;
    }
    /**
     * manage's shooting bullets
     * @param battleField
     * @param locationX
     * @param locationY
     */
    @Override
    public void shot(BattleField battleField, int locationX,int locationY) {
//        aim(locationX,locationY);
        if(numberOfBullet > 0) {
            switch (level){
                case 1:
                    battleField.add(new MyBullet1(battleField,locationX, locationY, angle));
                    break;
                case 2:
                    battleField.add(new MyBullet2(battleField,locationX,locationY,angle));
                    break;
            }
            numberOfBullet--;
        }
        else
            outOfBulletSound();

    }
    /**
     * manages promotion
     */
    @Override
    public void promote(){
        IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "playerBulletGun1.png";
        setImage();
        this.level = 2;
    }
    /**
     * handles aiming
     * @param locationX is x coordinate
     * @param locationY is y coordinate
     * @param mouseX is mouse x
     * @param mouseY is mouse y
     */
    @Override
    public void aim(int locationX, int locationY, int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }
    /**
     * increases number of bullets
     */
    @Override
    public void setNumberOfBulletsToDefault() {
        numberOfBullet += 200;
    }

}
