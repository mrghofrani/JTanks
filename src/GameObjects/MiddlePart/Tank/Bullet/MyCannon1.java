package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;

public class MyCannon1 extends MyBullet {
    /**
     * represent's canon bullet
     * @param battleField is main field
     * @param locationX is x coordinates
     * @param locationY is y coordinates
     * @param angle is angle between two objects
     */
    public MyCannon1(BattleField battleField, int locationX, int locationY, double angle){
        super();
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        speed = 5;
        damage = 10;
        this.angle = angle;
        this.IMAGE_PATH += "MyCannon1.png";
        setImage();
        playSound("cannon.wav");
        act();
    }
}
