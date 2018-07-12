package GameObjects.MiddlePart.Tank.UserTank;


import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Bullet1;


public class MachineGun extends PlayerGun {

    private int numberOfBullet;

    MachineGun(){
        this.IMAGE_PATH += "playerMachineGun0.png";
        setImage();
        numberOfBullet = 200;
    }

    @Override
    public void shot(BattleField battleField, int locationX,int locationY,int mouseX, int mouseY) {
//     TODO:   System.out.println(this.getClass().getName() + " mouseX " + mouseX);
//     TODO:   System.out.println(this.getClass().getName() + " locationX " + locationX);
        if(numberOfBullet > 0) {
            battleField.add(new Bullet1(battleField,locationX, locationY, mouseX, mouseY));
            numberOfBullet--;
        }
        else
            outOfBulletSound();

    }

    @Override
    public void aim(int locationX, int locationY, int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
    }

}
