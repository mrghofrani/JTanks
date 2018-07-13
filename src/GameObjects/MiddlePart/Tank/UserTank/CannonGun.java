package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Cannon1;


public class CannonGun extends PlayerGun {




    public CannonGun(){
        this.IMAGE_PATH += "playerCannonGun0.png";
        setImage();
        numberOfBullet = 100;
    }

    @Override
    public void shot(BattleField battleField,int locationX,int locationY) {
        if(numberOfBullet > 0) {
            battleField.add(new Cannon1(battleField,locationX,locationY ,angle));
            numberOfBullet--;
        }else{
            outOfBulletSound();
        }
    }

    @Override
    public void aim(int locationX,int locationY,int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }

}
