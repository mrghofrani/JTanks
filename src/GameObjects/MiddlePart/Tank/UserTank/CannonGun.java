package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet1;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet2;
import GameObjects.MiddlePart.Tank.Bullet.MyCannon1;
import GameObjects.MiddlePart.Tank.Bullet.MyCannon2;

import java.io.File;


public class CannonGun extends PlayerGun {


    public CannonGun(){
        this.IMAGE_PATH += "playerCannonGun0.png";
        this.level = 1;
        setImage();
        numberOfBullet = 100;
    }

    @Override
    public void shot(BattleField battleField,int locationX,int locationY) {
//        double angle = Math.atan2(mouseY - locationY, mouseX - locationX);
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

    @Override
    public void promote(){
        IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "playerCannonGun1.png";
        setImage();
        this.level = 2;
    }

    @Override
    public void aim(int locationX,int locationY,int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }

    @Override
    public void setNumberOfBulletsToDefault() {
        numberOfBullet += 100;
    }

}
