package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet1;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet2;
import GameObjects.MiddlePart.Tank.Bullet.MyCannon1;
import GameObjects.MiddlePart.Tank.Bullet.MyCannon2;

import java.io.File;

/**
 * This class represent's a canon gun
 */
public class CannonGun extends PlayerGun {


    public CannonGun(){
        this.IMAGE_PATH += "playerCannonGun0.png";
        this.level = 1;
        setImage();
        numberOfBullet = 100;
    }
    /**
     * this method job is shooting a cannon bullet
     * @param battleField
     * @param locationX
     * @param locationY
     */
    @Override
    public void shot(BattleField battleField,int locationX,int locationY) {
        if(numberOfBullet > 0) {
            switch (level){
                case 1:
                    battleField.add(new MyCannon1(battleField,locationX,locationY ,angle));
                    break;
                case 2:
                    battleField.add(new MyCannon2(battleField,locationX,locationY,angle));
                    break;
            }
            numberOfBullet--;
        }else{
            outOfBulletSound();
        }
    }
    /**
     * manage's promotion
     */
    @Override
    public void promote(){
        IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "playerCannonGun1.png";
        setImage();
        this.level = 2;
    }
    /**
     * manage's aiming
     * @param locationX
     * @param locationY
     * @param mouseX
     * @param mouseY
     */
    @Override
    public void aim(int locationX,int locationY,int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }
    /**
     * increased number of bullets of tank
     */
    @Override
    public void setNumberOfBulletsToDefault() {
        numberOfBullet += 100;
    }

}
