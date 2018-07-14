package GameBasis;

import GameObjects.BottomPart.BottomPart;
import GameObjects.BottomPart.ExplodedGround;
import GameObjects.BottomPart.Ground;
import GameObjects.MiddlePart.Items.*;
import GameObjects.MiddlePart.Tank.Bullet.Bullet;
import GameObjects.MiddlePart.Tank.Bullet.ESBullet;
import GameObjects.MiddlePart.Tank.Bullet.MyBullet;
import GameObjects.MiddlePart.Tank.Bullet.EBullet;
import GameObjects.MiddlePart.Tank.EnemyTanks.*;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;
import GameObjects.MiddlePart.Walls.HardWall;
import GameObjects.MiddlePart.Walls.SoftWall;
import GameObjects.GameObject;
import GameObjects.TopPart.*;
import GameObjects.MiddlePart.*;
import javafx.collections.transformation.TransformationList;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BattleField {
    private String FILE_PATH = "files" + File.separator + "Texts" + File.separator;
    private ArrayList<GameObject> everything;
    private ArrayList<GameObject> bottomPart;
    private ArrayList<GameObject> middlePart;
    private ArrayList<GameObject> topPart;
    private PlayerTank playerTank;
    private boolean soundState;
    private int courserX;
    private int courserY;
    public int XOffset;
    public int YOffset;
    private boolean stop;
    private final int MAP_HEIGTH = 1200;
    private final int MAP_WIDTH = 1200;
    private Camera camera;

    public BattleField() {
        // Hi Mahandes !!!
        // YaAllah !!!
        everything = new ArrayList<>();
        bottomPart = new ArrayList<>();
//        middlePart = new ArrayList<>();
        middlePart = new ArrayList<GameObject>();
        topPart = new ArrayList<>();
        initialize();
        camera = new Camera(0,0);
    }

    /**
     * This is used to initialize the battleField
     * at the first time and hold a pointer t each
     * of the gameObjects so that we can use them later
     */
    private void initialize() {
        // initialization of Map
        initializeGameLevel();
//       initializeSound();

        File file = new File(FILE_PATH);
        try {
            String line;
            String[] objects;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                objects = line.split("[|]");
                for (String object : objects) {
                    String[] tmp = object.split(":");
                    for (int i = 0; i < tmp.length; i++) {
                        if (i == 0) {
                            makeObject(tmp[i]);
                        } else {
                            if (tmp[i].equals("S")) {
                                ((Ground) everything.get(everything.size() - 1)).setStartingPoint();
                                playerTank = new PlayerTank(this,200, 200);
                                middlePart.add(new EnemyTank1(this,300,500));
                                everything.add(new EnemyTank1(this,300,500));
                                middlePart.add(new UpgradeGunItem(this,100,100));
                                everything.add(new UpgradeGunItem(this,100,100));
                                everything.add(playerTank);
                                middlePart.add(playerTank);
                            } else if (tmp[i].equals("F"))
                                ((Ground) everything.get(everything.size() - 1)).setFinishingPoint();
                            else
                                makeObject(tmp[i]);
                        }
                    }
                    courserX += 100;
                }
                courserX = 0;
                courserY += 100;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets each objects name and creates an
     * object in the given locationX and locationY which is
     * courserX and courserY named here.
     *
     * @param object objects name
     * @throws Exception if he can't find the given name object it throws this exception
     */
    private void makeObject(String object) throws Exception {
        switch (object) {
            case " ":
                everything.add(new Ground(courserX, courserY));
                break;
            case "#":
                everything.add(new HardWall(courserX, courserY));
                break;
            case "&":
                everything.add(new SoftWall(this,courserX, courserY));
                break;
            case "*":
                everything.add(new Grass(courserX, courserY));
                break;
            case "MI":
                everything.add(new MachineGunCartridgeItem(this,courserX, courserY));
                break;
            case "RI":
                everything.add(new RepairItem(this,courserX, courserY));
                break;
            case "CI":
                everything.add(new CannonBulletCartridgeItem(this,courserX, courserY));
                break;
            case "S":
                ((Ground) everything.get(everything.size() - 1)).setStartingPoint();
                break;
            case "F":
                ((Ground) everything.get(everything.size() - 1)).setFinishingPoint();
                break;
//            case "player GameObjects.MiddlePart.Tank.EnemyTank2.Tank":
//                everything.add(new UserTank(courserX,courserY));
//                break;
//            case "Enemy Tank1":
//                everything.add(new EnemyTank1Pack(courserX,courserY));
//                break;
//            case "Enemy Tank2":
//                everything.add(new EnemyTank2(courserX,courserY));
//                break;
//            case "Enemy Tank3":
//                everything.add(new EnemyTank3(courserX,courserY));
//                break;
//            case "Enemy Tank4":
//                everything.add(new EnemyTank4(courserX,courserY));
//                break;
//            case "Enemy Tank5":
//                everything.add(new EnemyTank5(courserX,courserY));
//                break;
            default:
                throw new Exception("Object not found");
        }

        GameObject gameObject = everything.get(everything.size() - 1);
        if (gameObject instanceof BottomPart)
            bottomPart.add(gameObject);
        else if (gameObject instanceof MiddlePart)
            middlePart.add(gameObject);
        else if (gameObject instanceof TopPart)
            topPart.add(gameObject);
    }

    /**
     * This method is used to clear the screen
     * the following items are cleared and the later objects substitute them:
     * SoftWall -> Ground
     * Tank -> ExplodedGround
     */
    public void clearScreen() {
        synchronized (everything) {
            Iterator<GameObject> iterator = everything.iterator();
            while (iterator.hasNext()) {
                GameObject gameObject = iterator.next();
                if (gameObject.isDeleted) {
                    if (gameObject instanceof SoftWall) {
                        iterator.remove();
                    }
                    if (gameObject instanceof EnemyTankTemplate) {
                        bottomPart.add(new ExplodedGround(gameObject.getLocationX(), gameObject.getLocationY(), ((EnemyTankTemplate) gameObject).getAngle()));
                        gameObject = null;
                        iterator.remove();
                    }
                    if (gameObject instanceof Item) {
                        iterator.remove();
                    }
                    if (gameObject instanceof Bullet) {
                        iterator.remove();
                    }
                }
            }
        }
        synchronized (middlePart){
            Iterator<GameObject> iterator = middlePart.iterator();
            while (iterator.hasNext()) {
                GameObject gameObject = (GameObject) iterator.next();

                if (gameObject.isDeleted) {
                    if (gameObject instanceof SoftWall) {
                        iterator.remove();
                    }
                    if (gameObject instanceof EnemyTankTemplate) {
                        bottomPart.add(new ExplodedGround(gameObject.getLocationX(), gameObject.getLocationY(), ((EnemyTankTemplate) gameObject).getAngle()));
                        iterator.remove();
                    }
                    if (gameObject instanceof Item) {
                        iterator.remove();
                    }
                    if (gameObject instanceof Bullet) {
                        iterator.remove();
                    }
                }
            }
        }

    }

    /**
     * This is used to draw all objects of the game
     */
    public void drawAllObjects(Graphics2D g2d) {
        if (XOffset > 0) XOffset = 0;
        if (YOffset > 0) YOffset = 0;
        if (XOffset + 1200 < 600) XOffset = -600;
        if (YOffset + 1200 < 600) YOffset = -600;

        /*int viewRangeXRight = 600;
        int viewRangeXLeft = 600;
        int viewRangeYUp = 600;
        int viewRangeYDown = 600;
        if(playerTank.getLocationX() + viewRangeXRight > 1200) viewRangeXRight = GameFrame.GAME_WIDTH - playerTank.getLocationX();
        if(playerTank.getLocationX() - viewRangeXLeft < 0) viewRangeXLeft = playerTank.getLocationX();
        if(playerTank.getLocationY() - viewRangeYUp > 1200) viewRangeYUp = GameFrame.GAME_HEIGHT - playerTank.getLocationY();
        if(playerTank.getLocationY() - viewRangeYDown < 0) viewRangeYDown = playerTank.getLocationY();
        Rectangle viewPoint = new Rectangle (playerTank.getLocationX() - viewRangeXLeft, playerTank.getLocationY() - viewRangeYUp
                                            , viewRangeXLeft + viewRangeXRight , viewRangeYDown + viewRangeYUp);*/
        clearScreen();
        synchronized (bottomPart) {
            for (GameObject object : bottomPart) {
                object.doRendering(g2d, XOffset, YOffset);
            }
        }
        synchronized (middlePart) {
            try {
                int size = middlePart.size();
                for (int i = 0; i < size - 1; i++) {
                    middlePart.get(i).doRendering(g2d, XOffset, YOffset);
                }
                if (size - 1 < middlePart.size()) {
                    for (int i = size; i < middlePart.size(); i++) {
                        middlePart.get(i).doRendering(g2d, XOffset, YOffset);
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e){

            }
        }
        synchronized (topPart) {
            for (GameObject object : topPart) {
                object.doRendering(g2d, XOffset, YOffset);
            }
        }
        playerTank.drawDetails(g2d);
    }

    public void initializeGameLevel() {
        switch (Main.getGameLevel()) {
            case 1:
                FILE_PATH += "easyMap.txt";
                break;
            case 2:
                FILE_PATH += "JustTesting.txt";
                break;
            case 3:
                FILE_PATH += "hardMap.txt";
                break;
        }
    }

    public void changeSeenArea(boolean keyUP,boolean keyDOWN,boolean keyRIGHT,boolean keyLEFT){
        if(!stop) {
            if (keyUP)
                YOffset += 2;
            if (keyDOWN)
                YOffset -= 2;
            if (keyLEFT)
                XOffset += 2;
            if (keyRIGHT)
                XOffset -= 2;
        }
    }

    public void stop(){
        stop = true;
    }

    public void move(){
        stop = false;
    }


//    public void initializeSound(){
//        soundState = Main.getSoundState();
//    }

    /**
     * This simulate collision in the code by invoking
     * the objects' methods but {@code collisionTest} checks
     * whether the collision occurred or not
     *
     * @param thing Game object that is going to collide
     */

    public void collision(GameObject thing) {
        ArrayList<GameObject> collidedObjects = new ArrayList<>();
        synchronized (middlePart) {
            for (GameObject object : middlePart) {
                if (object.getBounds().intersects(thing.getBounds()) && !(object instanceof PlayerTank) && !object.equals(thing))
                    collidedObjects.add(object);
            }
            if (!collidedObjects.isEmpty()) {
                for (GameObject object : collidedObjects) {
                    if (!thing.equals(object)) {
                        if (thing instanceof Explosive && object instanceof Exploder) {
                            if (!(thing instanceof PlayerTank && object instanceof MyBullet) && !(thing instanceof EnemyTankTemplate && object instanceof EBullet) && !(thing instanceof EnemyTankTemplate && object instanceof ESBullet)) {
                                System.out.println("UP");
                                ((Explosive) thing).explode(((Exploder) object).getDamage());
                                ((Exploder) object).explode();
                            }
                        } else if (thing instanceof Exploder && object instanceof Explosive) {
                            if (!(object instanceof PlayerTank && thing instanceof MyBullet) && !(object instanceof EnemyTankTemplate && thing instanceof EBullet) && !(object instanceof EnemyTankTemplate && thing instanceof ESBullet)) {
                                ((Explosive) object).explode((((Exploder) thing).getDamage()));
                                ((Exploder) thing).explode();
                                System.out.println("Down");
                            }
                        } else if (thing instanceof HardObject && object instanceof HardObject) {
                            ((HardObject) thing).stop();
                            ((HardObject) object).stop();
                        } else if ((thing instanceof PlayerTank && object instanceof Item)) {
                            ((PlayerTank) thing).eatItem((Item) object);
                        } else if (thing instanceof Item && object instanceof PlayerTank) {
                            ((PlayerTank) object).eatItem((Item) thing);
                        } else if (thing instanceof Bullet && object instanceof HardWall) {
                            ((Bullet) thing).dispose();
                        } else if (thing instanceof HardWall && object instanceof Bullet) {
                            ((Bullet) object).dispose();
                        }
//                        clearScreen();
                    }
                }
            }
        }
    }


    public void add(GameObject gameObject) {
        ArrayList<GameObject> everythingTmp = new ArrayList(everything);
        everythingTmp.add(gameObject);
        everything = everythingTmp;

        ArrayList<GameObject> tmp;
        if (gameObject instanceof BottomPart) {
            synchronized (bottomPart) {
                tmp = new ArrayList<>(bottomPart);
                tmp.add(gameObject);
                bottomPart = tmp;
            }
        } else if (gameObject instanceof MiddlePart) {
            synchronized (middlePart) {
                tmp = new ArrayList<>(middlePart);
                tmp.add(gameObject);
                middlePart = tmp;
            }
        } else if (gameObject instanceof TopPart) {
            synchronized (topPart) {
                tmp = new ArrayList<>(topPart);
                tmp.add(gameObject);
                topPart = tmp;
            }
        }
    }

    public PlayerTank getPlayerTank() {
        return playerTank;
    }

    private class Camera {

        private int x;
        private int y;

        private int xmov;
        private int ymov;

        Camera(int x, int y) {
            this.x = x;
            this.y = y;
            xmov = 0;
            ymov = 0;
        }

        private void update(PlayerTank playerTank) {
            int xnew = playerTank.getLocationX();
            int ynew = playerTank.getLocationY();
            int xCam = Math.min(Math.max(0, (xnew + playerTank.getWidth() / 2) - GameFrame.GAME_WIDTH / 2),
                     MAP_WIDTH - GameFrame.GAME_WIDTH);
            int yCam = Math.min(Math.max(0, (ynew + playerTank.getHeight() / 2) - GameFrame.GAME_HEIGHT / 2),
                    MAP_HEIGTH - GameFrame.GAME_HEIGHT);

            xmov = xCam - x;
            x = xCam;

            ymov = yCam - y;
            y = yCam;
        }
    }
}

