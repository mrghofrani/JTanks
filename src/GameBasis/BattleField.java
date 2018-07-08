package GameBasis;

import GameObjects.BottomPart.BottomPart;
import GameObjects.BottomPart.ExplodedGround;
import GameObjects.BottomPart.Ground;
import GameObjects.MiddlePart.Items.Item;
import GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank1Package.EnemyTank1;
import GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank2Package.EnemyTank2;
import GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank3Package.EnemyTank3;
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
    private static ArrayList<GameObject> players;
    private boolean soundState;
    private int courserX;
    private int courserY;

    public BattleField(){
        everything = new ArrayList<>();
        middlePart = new ArrayList<>();
        topPart = new ArrayList<>();
        players = new ArrayList<>();
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
       initializeSound();

        File file = new File(FILE_PATH);
        try {
            String line;
            String[] objects;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(!(line = bufferedReader.readLine()).isEmpty()){
                objects = line.split("|");
                for (String object: objects) {
                    makeObject(object);
                    courserX += 100;
                    if(courserX == 500){
                        courserY += 100;
                        courserX =0;
                    }
                }
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
            case "ground":
                everything.add(new Ground(courserX,courserY));
                break;
            case "hardWall":
                everything.add(new HardWall(courserX,courserY));
                break;
            case "softWall":
                everything.add(new SoftWall(courserX,courserY));
            case "grass":
                everything.add(new Grass(courserX, courserY));
                break;
//            case "player GameObjects.MiddlePart.Tank.Tank":
//                everything.add(new PlayerTank(courserX,courserY));
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
                throw new Exception("Object not found");
        }

        courserX += everything.get(everything.size()-1).getBoundX();
        courserY += everything.get(everything.size()-1).getBoundY();

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
                if(gameObject instanceof Tank){
                    GameObject tmp = new ExplodedGround(gameObject.getLocationX(),gameObject.getLocationY());
                    gameObject = tmp;
                }
            }
        }
    }

    /**
     * This is used to draw all objects of the game
     */
    public void drawAllObjects(Graphics2D g){
        for (GameObject object: everything) {
            object.doRendering(g);
        }
    }

    public void initializeGameLevel(){
        switch (Main.getGameLevel()){
            case 1:
                FILE_PATH += "easyMap.txt";
                break;
            case 2:
                FILE_PATH += "mediumMap.txt";
                break;
            case 3:
                FILE_PATH += "hardMap.txt";
                break;
        }
    }

    public void initializeSound(){
        soundState = Main.getSoundState();
    }

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
        if(thing instanceof PlayerTank) {
            for (GameObject object : collidedObjects)
                if(object instanceof Item)
                    PlayerTank.eatItem(object);
        }
        else if(thing instanceof Exploder){
            for (GameObject object: collidedObjects) {
                if(object instanceof Explosive){
                    ((Explosive) object).damage(thing.getDamage());
                }
            }
        }
        thing = null;
    }

}
