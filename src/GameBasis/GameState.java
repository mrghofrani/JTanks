/*** In The Name of Allah ***/
package GameBasis;

import GameObjects.MiddlePart.Tank.Bullet.Cannon1;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import javax.swing.*;
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

    public boolean gameOver;

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


    public KeyListener getKeyListener() {
        return keyHandler;
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

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
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
                    keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
                case KeyEvent.VK_SHIFT:
                    System.out.println("cheat mode is on!");
                    battleField.isCheatMode = true;
                    break;
                case KeyEvent.VK_P:
                    //TODO playerTank.promoteWeapon();
                    break;
                case KeyEvent.VK_T:
                    playerTank.getGun().setNumberOfBulletsToDefault();
                    break;
                case KeyEvent.VK_I:
                    playerTank.getGun().setNumberOfBulletsToInfinite();
                    System.out.println("rey gamaj!");
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
                    difference = 800;
                else
                    difference = 200;
                if(clickTime == 0){
                    clickTime = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - clickTime > difference){
                    playerTank.shot();
                    mousePress = true;
                    clickTime = System.currentTimeMillis();
                }
                System.out.println(clickTime + "pressed");
//              TODO:  System.out.println(clickTime);
            }

        }
//
//        public void mouseClicked(MouseEvent e){
//            if(e.getButton() == 1){
//                BattleField.add(new Cannon1(playerTank.getLocationX(), e.getX(),playerTank.getLocationY(),e.getY()));
//                mousePress = true;
//            }
//        }
        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }
//

        @Override
        public void mouseDragged(MouseEvent e) {
            playerTank.aim(e.getX(),e.getY());
            if(playerTank.isMainGun())
                difference = 800;
            else
                difference = 200;
            if(clickTime == 0){
                clickTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - clickTime > difference){
                playerTank.shot();
                mousePress = true;
                clickTime = System.currentTimeMillis();
            }
            System.out.println(clickTime + "drag");
//          TODO:  System.out.println(clickTime);
        }

        @Override
        public void mouseMoved(MouseEvent e){
            playerTank.aim(e.getX(),e.getY());
            mouseX = e.getX();
            mouseY = e.getY();
        }


    }

}


