package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;

public class EnemyTank2 extends EnemyTankTemplate implements HardObject {

    public EnemyTank2(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        isNear = false;
        setImage();
        setGunImage();
//        act();
    }

    @Override
    protected void shot() {
        new EnemyBullet(battleField,locationX,locationY,battleField.getPlayerTank().getLocationX(),battleField.getPlayerTank().getLocationY());
    }

    @Override
    public void damage(double value) {

    }

    @Override
    public void explode() {

    }
}
