package GameObjects.MiddlePart.Tank;

import Bullet.Cannon.MyCannonBullet2;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PlayerTank extends Tank {


    private GameState state;
    private boolean UP_Down = false;    // is the direction of tank in y axis
    private boolean Right_Left = true;   // is the direction of tank in x axis



        private PlayerTank(GameState state, int X, int Y) throws IOException {
        super(X, Y);
        this.state = state;
        tankX = state.locX;
        tankY = state.locY;
        gunPosX = tankX + tankGun.getWidth() / 4;
        gunPosY = tankY + tankGun.getHeight() / 4 - 10;
    }

    public void init(GameState state, int X, int Y) {
        try {
            playerTank = new PlayerTank(state, X, Y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



       public Tank  getTank(){
            return playerTank;
       }


    public void fire(int mouseX,int mouseY) {
        int rotationCenterX = tankX + tankBody.getWidth()/2;
        int rotationCenterY = tankY + tankBody.getHeight()/2;
//        double y = 90  + (rotationCenterY - state.getMouseY());
//        double x = Math.sqrt((rotationCenterX - state.getMouseX()) * (rotationCenterX - state.getMouseX()) + ((rotationCenterY - state.getMouseY()) *(rotationCenterY - state.getMouseY()) ));
        double angle = Math.atan2(mouseY - rotationCenterY  ,mouseX - rotationCenterX) * (180/Math.PI);
        System.out.println("angle : " + (int)angle);
        MyCannonBullet2 bullet = new MyCannonBullet2(rotationCenterX,rotationCenterY,(int)angle);
//        AffineTransform tx = AffineTransform.getRotateInstance(-angle, rotationCenterX, rotationCenterY);
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//        bullet.setBullet(op.filter(bullet.getBullet(),null));
////        bullet.move();
        System.out.println("fire method");
        missiles.add(bullet);
        bullet.move();

    }

    @Override
    public void rotateTank(boolean direction) {
        if(UP_Down && !direction){
            UP_Down = false;
            Right_Left = true;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90),tankBody.getWidth()/2 , tankBody.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        tankBody = op.filter(tankBody,null);
        }else if(Right_Left && direction){
            UP_Down = true;
            Right_Left = false;
            AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90),tankBody.getWidth()/2 , tankBody.getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            tankBody = op.filter(tankBody,null);
        }
    }

    @Override
    public void rotateGun() {


//calculate the tank rotation center
//        int tankRotationsCenterX = tankX + tankBody.getWidth();
//        int tankRotationsCenterY = tankY + tankBody.getWidth();
//
////calculate distance between gun position and tank rotation center
//        int dx = tankRotationsCenterX - gunPosX ;
//        int dy = tankRotationsCenterY - gunPosY ;
//        double dis = Math.sqrt(dx * dx + dy * dy);

//calculate the offset based on the rotation of the tank
//rotation offset for the gun placement
//        int gunRotaOff = 50;
//
//        double gunX_offset = dis*Math.cos(Math.toRadians(gunRotaOff));
//        double gunY_offset = dis*Math.sin(Math.toRadians(gunRotaOff));
//
//        int gunXhalf = tankGun.getWidth() / 2;
//        int gunYhalf = tankGun.getHeight() / 2;
//
//        Image image = Toolkit.getDefaultToolkit().getImage("Images\\tankGun01.png");
//        int x = (int)(tankRotationsCenterX - gunX_offset-gunXhalf);
//        int y = (int)(tankRotationsCenterY - gunY_offset) - gunYhalf;
//        g2d.drawImage(image,x,y,null);

//draws the gun dependend on the ship position and the ship rotation
//don't forget to subtract half the width/height for exact positioning

        int rotationCenterX = tankX + tankBody.getWidth()/2;
        int rotationCenterY = tankY + tankBody.getHeight()/2;

        double rotationRequired = Math.toRadians (10);
        AffineTransform tx = new AffineTransform();
        tx.rotate(rotationRequired, rotationCenterX, rotationCenterY);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BICUBIC);
        tankGun = op.filter(tankGun, null);

//
//       gunPosX =(int) (gunPosX*Math.cos(Math.toRadians(45)) + gunPosX*Math.sin(Math.toRadians(45)));
//       gunPosY = (int)(gunPosY*Math.cos(Math.toRadians(45)) + gunPosY*Math.sin(Math.toRadians(45)));
////
//calculate the gun position on top of the tank
//        gunPosX = tankX /*+ gunPosOffsetX*/;
//        gunPosY = tankY /*+ gunPosOffsetY*/;
//
////calculate the tank rotation center
//        int tankRotationsCenterX = tankX + tankBody.getWidth()/2;
//        int tankRotationsCenterY = tankY + tankBody.getHeight()/2;
//
////calculate distance between gun position and tank rotation center
//        double dx = tankRotationsCenterX  - gunPosX ;
//        double dy = tankRotationsCenterY - gunPosY ;
//        double dis = Math.sqrt(dx * dx + dy * dy);
//
////calculate the offset based on the rotation of the tank
////rotation offset for the gun placement
//        int gunRotaOff = 20;
//
//        double gunX_offset = dis*Math.cos(Math.toRadians(tankBody.+gunRotaOff));
//        double gunY_offset = dis*Math.sin(Math.toRadians(tankImage.getRotation()+gunRotaOff));
//
//        gunXhalf = gun.getImage().getWidth() / 2;
//        gunYhalf = gun.getImage().getHeight() / 2;
//
////draws the gun dependend on the ship position and the ship rotation
////don't forget to subtract half the width/height for exact positioning
//        gun.drawIngame(tankRotationsCenterX - gunX_offset)-gunXhalf , (tankRotationsCenterY - gun_y_offset) - gunYhalf ));

    }

    @Override
    public void promoteWeapon() {

    }

    @Override
    public void switchGun() {
        if(gunType == 1){
            System.out.println("switch gun method");
            gunType = 2 ;
            MyCannonBullet2.changeBulletType(2);
            try {
                tankGun = ImageIO.read(new File(IMAGE_PATH + "tankGun1.png"));
            } catch (IOException e) {
                System.out.println("tankGun1.png not found!");
            }
        }else{
            gunType = 1;
            MyCannonBullet2.changeBulletType(1);
            try {
                tankGun = ImageIO.read(new File(IMAGE_PATH + "tankGun01.png"));
            } catch (IOException e) {
                System.out.println("tankGun01.png not found!");
            }
        }
    }

    @Override
    public void destruct() {

    }

    @Override
    public void fullDestruct() {

    }

    @Override
    public void reNewTank() {

    }

    public ArrayList<MyCannonBullet2> getMissiles() {
        return missiles;
    }







}
