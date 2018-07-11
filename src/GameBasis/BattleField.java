package GameBasis;

import GameObjects.BottomPart.BottomPart;
import GameObjects.BottomPart.Ground;
import GameObjects.MiddlePart.Items.CannonBulletCartridgeItem;
import GameObjects.MiddlePart.Items.MachineGunCartridgeItem;
import GameObjects.MiddlePart.Items.RepairItem;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;
import GameObjects.MiddlePart.Walls.HardWall;
import GameObjects.MiddlePart.Walls.SoftWall;
import GameObjects.GameObject;
import GameObjects.TopPart.*;
import GameObjects.MiddlePart.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class BattleField {
    private String FILE_PATH = "files" + File.separator + "Texts" + File.separator;
    private static ArrayList<GameObject> everything;
    private static ArrayList<GameObject> bottomPart;
    private static ArrayList<GameObject> middlePart;
    private static ArrayList<GameObject> topPart;
    private PlayerTank playerTank;
    private boolean soundState;
    private int courserX;
    private int courserY;
    public static int XOffset;
    public static int YOffset;

    public BattleField(){
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
    private void initialize(){
        // initialization of Map
       initializeGameLevel();
//       initializeSound();

        File file = new File(FILE_PATH);
        try {
            String line;
            String[] objects;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                objects = line.split("[|]");
                for (String object: objects) {
                    String[] tmp = object.split(":");
                    for (int i = 0; i < tmp.length ; i++) {
                        if(i == 0) {
                            makeObject(tmp[i]);
                        }
                        else {
                            if(tmp[i].equals("S")) {
                                ((Ground) everything.get(everything.size() - 1)).setStartingPoint();
                                playerTank = new PlayerTank(200,200);
                                everything.add(playerTank);
                                middlePart.add(playerTank);
                            }
                            else if(tmp[i].equals("F"))
                                ((Ground)everything.get(everything.size()-1)).setFinishingPoint();
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
     * @param object objects name
     * @throws Exception if he can't find the given name object it throws this exception
     */
    private void makeObject(String object) throws Exception {
        switch(object){
            case " ":
                everything.add(new Ground(courserX,courserY));
                break;
            case "#":
                everything.add(new HardWall(courserX,courserY));
                break;
            case "&":
                everything.add(new SoftWall(courserX,courserY));
                break;
            case "*":
                System.out.println("here");
                everything.add(new Grass(courserX, courserY));
                break;
            case "MI":
                everything.add(new MachineGunCartridgeItem(courserX,courserY));
                break;
            case "RI":
                everything.add(new RepairItem(courserX,courserY));
                break;
            case "CI":
                everything.add(new CannonBulletCartridgeItem(courserX,courserY));
                break;
            case "S":
                ((Ground)everything.get(everything.size() - 1)).setStartingPoint();
                break;
            case "F":
                ((Ground)everything.get(everything.size() - 1)).setFinishingPoint();
                break;
//            case "player GameObjects.MiddlePart.Tank.EnemyTanks.Tank":
//                everything.add(new UserTank(courserX,courserY));
//                break;
//            case "Enemy Tank1":
//                everything.add(new EnemyTank1(courserX,courserY));
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
                System.out.println(object);
                throw new Exception("Object not found");
        }

        GameObject gameObject = everything.get(everything.size() - 1);
        if(gameObject instanceof BottomPart)
            bottomPart.add(gameObject);
        else if(gameObject instanceof MiddlePart)
            middlePart.add(gameObject);
        else if(gameObject instanceof TopPart)
            topPart.add(gameObject);
    }

    /**
     * This method is used to clear the screen
     * the following items are cleared and the later objects substitute them:
     * SoftWall -> Ground
     * Tank -> ExplodedGround
     */
    public static void clearScreen(){
        for (GameObject gameObject: everything) {
            if(gameObject.getHealth() == 0){
                if(gameObject instanceof SoftWall){
                    GameObject tmp = new Ground(gameObject.getLocationX(),gameObject.getLocationY());
                    gameObject = tmp;
                }
//                if(gameObject instanceof Tank){
//                    GameObject tmp = new BottomPart.ExplodedGround(gameObject.getLocationX(),gameObject.getLocationY());
//                    gameObject = tmp;
//                }
            }
        }
    }

    /**
     * This is used to draw all objects of the game
     */
    public void drawAllObjects(Graphics2D g2d){
        if(XOffset > 0) XOffset = 0;
        if(YOffset > 0) YOffset = 0;
        if(XOffset + 1200 < 600) XOffset = -600;
        if(YOffset + 1200 < 600) YOffset = -600;

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
        for (GameObject object: bottomPart) {
            object.doRendering(g2d,XOffset,YOffset);
        }
        for (GameObject object: middlePart) {
            object.doRendering(g2d,XOffset,YOffset);
        }
        for (GameObject object: topPart) {
            object.doRendering(g2d,XOffset,YOffset);
        }
    }

    public void initializeGameLevel(){
        switch (Main.getGameLevel()){
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

    public static ArrayList<GameObject> getEverything() {
        return everything;
    }

//    public void initializeSound(){
//        soundState = Main.getSoundState();
//    }

    /**
     * This method checks whether the collision occurred
     * or not but the {@code realCollision} method simulates the
     * collision by invoking objects' methods
     * @param thing Does this object collide to any object in screen
     * @return true if the collision have occurred otherwise returns false
     */
    public boolean collisionTest(GameObject thing){
        ArrayList<GameObject> collidedObjects = new ArrayList<>();
        for (GameObject object: middlePart) {
            if(thing.getBounds().intersects(thing.getBounds())){
                collidedObjects.add(object);
                break;
            }
        }
        return !collidedObjects.isEmpty();
    }

    /**
     * This simulate collision in the code by invoking
     * the objects' methods but {@code collisionTest} checks
     * whether the collision occurred or not
     * @param thing Game object that is going to collide
     */
    public void realCollision(GameObject thing){
        ArrayList<GameObject> collidedObjects = new ArrayList<>();
        for (GameObject object: middlePart) {
            if(thing.getBounds().intersects(thing.getBounds())){
                collidedObjects.add(object);
                break;
            }
        }

//        if(thing instanceof UserTank) {
//            for (GameObject object : collidedObjects)
//                if(object instanceof Item)
//                    UserTank.eatItem(object);
//        }
//        if(thing instanceof Exploder){
//            for (GameObject object: collidedObjects) {
//                if(object instanceof Explosive){
//                    ((Explosive) object).damage(thing.getDamage());
//                }
//            }
//        }
//        thing = null;
    }

    public static void add(GameObject gameObject){
        everything.add(gameObject);
        if(gameObject instanceof BottomPart){
           bottomPart.add(gameObject);
        }
        else if(gameObject instanceof MiddlePart){
            middlePart.add(gameObject);
        }else if(gameObject instanceof TopPart){
            topPart.add(gameObject);
        }
    }

}

