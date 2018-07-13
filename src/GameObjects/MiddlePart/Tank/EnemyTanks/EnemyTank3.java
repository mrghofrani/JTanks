package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;

public class EnemyTank3 extends EnemyTankTemplate implements HardObject {

    public EnemyTank3(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank3.png";
        this.GUN_IMAGE += "enemyGun3.png";
        isNear = false;
        setImage();
        setGunImage();
//        act();
    }

    @Override
    protected void shot() {
        new EnemyBullet(battleField,locationX,locationY,battleField.getPlayerTank().getLocationX(),battleField.getPlayerTank().getLocationY());
    }
}
