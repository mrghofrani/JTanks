package GameBasis;
/**
 * @Authors Seyyed hassan saadat and mohamadreza ghofrani
 *
 */
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
/**
 * BattleField class controls battlefield and objects on it
 */
public class BattleField {
    private String FILE_PATH = "files" + File.separator + "Texts" + File.separator;
    private ArrayList<GameObject> everything;
    private ArrayList<GameObject> bottomPart;
    private ArrayList<GameObject> middlePart;
    private ArrayList<GameObject> topPart;
    private PlayerTank playerTank;
    private int courserX;
    private int courserY;
    public int XOffset;
    public int YOffset;
    private boolean stop;
    private final int MAP_HEIGHT = 1200;
    private final int MAP_WIDTH = 1200;
    /**
     * first initializing
     */
    public BattleField() {
        // Hi Mahandes !!!
        // YaAllah !!!
        everything = new ArrayList<>();
        bottomPart = new ArrayList<>();
        middlePart = new ArrayList<>();
        topPart = new ArrayList<>();
        initialize();
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

        playerTank = new PlayerTank(this,100,100);
        everything.add(playerTank);
        middlePart.add(playerTank);

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
                            if (tmp[i].equals("F"))
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
            int i = 3;
            GameObject gameObjectTmp = null;
            Iterator<GameObject> iterator = middlePart.iterator();
            while (iterator.hasNext()) {
                GameObject gameObject = (GameObject) iterator.next();
                if (gameObject.isDeleted) {
                    if (gameObject instanceof SoftWall) {
                        if((new Random().nextInt(100))%10 == 0 ){
                            gameObjectTmp = gameObject;
                            i = (new Random().nextInt(100))%3;
                        }
                        else
                            i = 3;
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
            if(gameObjectTmp != null) {
                switch (i) {
                    case 1:
                        middlePart.add(new CannonBulletCartridgeItem(this, gameObjectTmp.getLocationX(), gameObjectTmp.getLocationY()));
                        break;
                    case 2:
                        middlePart.add(new MachineGunCartridgeItem(this,gameObjectTmp.getLocationX(),gameObjectTmp.getLocationY()));
                        break;
                    case 3:
                        middlePart.add(new RepairItem(this,gameObjectTmp.getLocationX(),gameObjectTmp.getLocationY()));
                        break;
                    case 4:
                        break;
                        default:
                            break;
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

        clearScreen();
        synchronized (bottomPart) {
            for (GameObject object : bottomPart) {
                object.doRendering(g2d, XOffset, YOffset);
            }
        }
        synchronized (middlePart) {
            int size = middlePart.size();
            for (int i = 0; i < size - 1; i++) {
                if(!middlePart.get(i).isDeleted)
                    middlePart.get(i).doRendering(g2d, XOffset, YOffset);
            }
            if (size - 1 < middlePart.size()) {
                for (int i = size; i < middlePart.size(); i++) {
                    if(!middlePart.get(i).isDeleted)
                        middlePart.get(i).doRendering(g2d, XOffset, YOffset);
                }
            }
        }
        synchronized (topPart) {
            for (GameObject object : topPart) {
                object.doRendering(g2d, XOffset, YOffset);
            }
        }
        playerTank.drawDetails(g2d);
    }
    /**
     * a method for defining game level of difficulty
     */
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
    /**
     * a method that works like camera
     * @param keyUP defines pressing up arrow key
     * @param keyDOWN defines pressing down arrow key
     * @param keyRIGHT defines pressing right arrow key
     * @param keyLEFT   defines pressing left arrow key
     */
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
    /**
     * stops battlefield move(camera move in fact)
     */
    public void stop(){
        stop = true;
    }
    /**
     * moves camera
     */
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
                                ((Explosive) thing).explode(((Exploder) object).getDamage());
                                ((Exploder) object).explode();
                            }
                        } else if (thing instanceof Exploder && object instanceof Explosive) {
                            if (!(object instanceof PlayerTank && thing instanceof MyBullet) && !(object instanceof EnemyTankTemplate && thing instanceof EBullet) && !(object instanceof EnemyTankTemplate && thing instanceof ESBullet)) {
                                ((Explosive) object).explode((((Exploder) thing).getDamage()));
                                ((Exploder) thing).explode();
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
        synchronized (bottomPart){
            for (GameObject gameObject:bottomPart) {
                if(gameObject instanceof Ground && ((Ground)gameObject).isFinishingPoint()){
                    if(playerTank.getBounds().intersects(gameObject.getBounds()))
                        GameState.gameWon = true;
                }
            }
        }
    }

    /**
     * a method to add gameobjects to battlefield
     * @param gameObject is object that must be added to field
     */
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
    /**
     * @return a reference of user tank
     */
    public PlayerTank getPlayerTank() {
        return playerTank;
    }

}

