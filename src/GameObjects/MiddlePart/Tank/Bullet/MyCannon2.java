package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class MyCannon2 extends MyBullet implements Bullet {
    /**
     * represent's promoted canon bullet
     * @param battleField is main field
     * @param locationX is x coordinates
     * @param locationY is y coordinates
     * @param angle is angle between two objects
     */
    public MyCannon2(BattleField battleField, int locationX, int locationY, double angle){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        speed = 10;
        damage = 20;
        this.angle = angle;
        this.IMAGE_PATH += "MyCannon1.png";
        setImage();
        playSound("cannon.wav");
        act();
    }
}
