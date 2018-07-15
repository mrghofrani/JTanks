package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;

public class MyBullet1 extends MyBullet {
    /**
     * this object is a kind of bullets of tank
     * @param battleField is main field
     * @param locationX is x coordinates
     * @param locationY is y coordinates
     * @param angle is angle between two objects
     */
    public MyBullet1(BattleField battleField, int locationX, int locationY, double angle) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 10;
        damage = 2;
        this.angle = angle;
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("mashinegun.wav");
        act();
    }


}
