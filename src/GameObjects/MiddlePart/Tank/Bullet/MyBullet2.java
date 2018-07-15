package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class MyBullet2 extends MyBullet implements Bullet {
    /**
     * this class is promoted version of bullets of tank
     * @param battleField is main field
     * @param locationX is x coordinates
     * @param locationY is y coordinates
     * @param angle is angle between two objects
     */
    public MyBullet2(BattleField battleField, int locationX, int locationY, double angle) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 15;
        damage = 4;
        this.angle = angle;
        this.IMAGE_PATH += "Mybullet1.png";
        setImage();
        playSound("mashinegun.wav");
        act();
    }
}
