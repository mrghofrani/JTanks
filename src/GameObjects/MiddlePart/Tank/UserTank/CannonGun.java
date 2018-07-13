package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Cannon1;


public class CannonGun extends PlayerGun {




    public CannonGun(){
        this.IMAGE_PATH += "playerCannonGun0.png";
        setImage();
        numberOfBullet = 100;
        damgePower = 5; //TODO may need to change
    }

    @Override
    public void shot(BattleField battleField,int locationX,int locationY, int mouseX, int mouseY) {
        if(numberOfBullet > 0) {
            battleField.add(new Cannon1(battleField,locationX,locationY ,mouseX, mouseY));
            numberOfBullet--;
        }else{
            outOfBulletSound();
        }
    }

    @Override
    public void promote(){
        IMAGE_PATH = "files\\Images\\playerCannonGun1.png";
        damgePower = 10; //TODO may be changed
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
