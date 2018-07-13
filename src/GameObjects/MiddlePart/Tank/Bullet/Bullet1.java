package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

public class Bullet1 extends Bullet {

    public Bullet1(BattleField battleField, int locationX, int locationY, double angle) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 10;
        this.angle = angle;
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("mashinegun.wav");
        move();
    }


}
