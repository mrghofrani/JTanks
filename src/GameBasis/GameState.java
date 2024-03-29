/*** In The Name of Allah ***/
package GameBasis;

import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

    public int locX, locY;

    public static boolean gameOver;
    public static boolean gameWon;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private static boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private BattleField battleField;
    private PlayerTank playerTank;
    private long clickTime;
    private int difference;

    public GameState(BattleField battleField) {

        gameOver = false;
        gameWon = false;

        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();

        this.battleField = battleField;
        playerTank = battleField.getPlayerTank();
    }


    /**
     * The method which updates the game state.
     */
    public void update() {
        playerTank.move(keyUP,keyDOWN,keyRIGHT,keyLEFT);
        battleField.changeSeenArea(keyUP,keyDOWN,keyRIGHT,keyLEFT);



//		if(playerTank.gunAngle < 0)
//		    playerTank.gunAngle += Math.PI;
//        System.out.println(this.getClass().getName() + "line 266" +  " mouseX " + mouseX + " mouseY " + mouseY);
//        System.out.println(this.getClass().getName() + " line 158 " + " Angle is " + playerTank.angle);
//		locX = Math.max(locX, 0);
////		locX = Math.min(locX, GameBasis.GameFrame.GAME_WIDTH - (int)tankWidth);
//		locY = Math.max(locY, 0);
////		locY = Math.min(locY, GameBasis.GameFrame.GAME_HEIGHT - (int)tankHeight);
    }

    /**
     * get's key listener
     * @return keyHandler
     */
    public KeyListener getKeyListener() {
        return keyHandler;
    }
    /**
     * get's mouse listener
     * @return keyHandler
     */
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    /**
     * get's mouse handler
     * @return mouse handler
     */
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
		}
        /**
         * @param e is event of pressing a key
         */
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
					keyRIGHT = false;
					break;
                case KeyEvent.VK_SHIFT:
//                    System.out.println("cheat mode is on!");
//                    battleField.isCheatMode = true;
                    break;
                case KeyEvent.VK_P:
                    playerTank.promoteWeapon();
                    break;
                case KeyEvent.VK_T:
                    playerTank.getGun().setNumberOfBulletsToDefault();
                    break;
                case KeyEvent.VK_I:
                    playerTank.getGun().setNumberOfBulletsToInfinite();
                    break;
                case KeyEvent.VK_L:
                    playerTank.setDefaultLife();
                    System.out.println("life was reset");
                    break;
                case KeyEvent.VK_H:
                    playerTank.setDefaultHealth();
                    System.out.println("health is in default value");
                    break;

            }
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == 3){
                playerTank.changeGun();
            }else if(e.getButton() == 1){
                if(playerTank.isMainGun())
                    difference = 700;
                else
                    difference = 200;
                if(clickTime == 0){
                    clickTime = System.currentTimeMillis();
                    playerTank.aim(e.getX(),e.getY());
                    playerTank.shot();
                } else if (System.currentTimeMillis() - clickTime > difference){
                    playerTank.aim(e.getX(),e.getY());
                    playerTank.shot();
                    mousePress = true;
                    clickTime = System.currentTimeMillis();
                }
            }

        }
//
//        public void mouseClicked(MouseEvent e){
//            if(e.getButton() == 1){
//                BattleField.add(new MyCannon1(playerTank.getLocationX(), e.getX(),playerTank.getLocationY(),e.getY()));
//                mousePress = true;
//            }
//        }
        /**
         * @param e is mouse releasing event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }
//
        /**
         *
         * @param e is mouse drag event
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            playerTank.aim(e.getX(),e.getY());
            if(playerTank.isMainGun())
                difference = 700;
            else
                difference = 200;
            if(clickTime == 0){
                clickTime = System.currentTimeMillis();
                playerTank.shot();
            } else if (System.currentTimeMillis() - clickTime > difference){
                playerTank.shot();
                mousePress = true;
                clickTime = System.currentTimeMillis();
            }
        }
        /**
         *
         * @param e is mouse event that occurred
         */
        @Override
        public void mouseMoved(MouseEvent e){
            playerTank.aim(e.getX(),e.getY());
            mouseX = e.getX();
            mouseY = e.getY();
        }


    }

}


