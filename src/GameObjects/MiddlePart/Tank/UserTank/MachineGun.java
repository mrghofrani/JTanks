package GameObjects.MiddlePart.Tank.UserTank;


import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Bullet1;


public class MachineGun extends PlayerGun {


    MachineGun(){
        this.IMAGE_PATH += "playerMachineGun0.png";
        setImage();
        numberOfBullet = 200;
        damagePower = 2; // TODO this may be changed
    }

    @Override
    public void shot(BattleField battleField, int locationX,int locationY) {
        if(numberOfBullet > 0) {
            battleField.add(new Bullet1(battleField,locationX, locationY, angle));
            numberOfBullet--;
        }
        else
            outOfBulletSound();

    }

    @Override
    public void promote(){
        IMAGE_PATH = "files\\Images\\playerBulletGun1.png";
        damagePower = 4; // TODO may be changed
    }

    @Override
    public void aim(int locationX, int locationY, int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }

    @Override
    public void setNumberOfBulletsToDefault() {
        numberOfBullet += 200;
    }

}
