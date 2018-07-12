package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;

public class Cannon1 extends Bullet{


    public Cannon1(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
        this.IMAGE_PATH += "Cannon1.png";
        setImage();
        playSound("cannon.wav");
        act();
        move();
    }


    @Override
    public void act() {
    }




}
