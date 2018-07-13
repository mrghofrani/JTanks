package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;

public class Cannon1 extends Bullet{
    public Cannon1(BattleField battleField, int locationX, int locationY, double angle){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        speed = 10;
        this.angle = angle;
        this.IMAGE_PATH += "Cannon1.png";
        setImage();
        playSound("cannon.wav");
        act();
        move();
    }
}
