package Bullet.Cannon;

//import Bullet.Explosive; // what is explosive????

public abstract class EnemyCannonBullet extends CannonBullet{
    protected abstract void initialize(int locationX,int locationY, int degree);
    public void move(){
//        while(Board.isEmpty()){
//            locationX += speed * Math.cos(degree);
//            locationY += speed * Math.sin(degree);
//        }
//        if(Board.getObject() instanceof Explosive){
//            // ACT
//        }
//        else {
//            // Remove from the scenesgfas
//        }
    }
}
