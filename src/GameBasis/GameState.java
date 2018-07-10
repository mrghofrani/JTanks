package GameBasis; /*** In The Name of Allah ***/


import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
    private boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GameState() {
//        UserTank.init(this,100,100);

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
    }


    /**
     * The method which updates the game state.
     */
    public void update() {
        double mainAngle;
        if(keyUP && !keyLEFT && !keyRIGHT){

            if(Math.abs(PlayerTank.angle - (3*Math.PI/2)) < Math.abs(PlayerTank.angle + Math.PI/2 )){
                mainAngle = 3*Math.PI/2;
            }
            else {
                mainAngle = -Math.PI/2;
            }

            if(PlayerTank.angle - mainAngle > 0)
                PlayerTank.angle -= 0.05;
            else
                PlayerTank.angle += 0.05;

            if(Math.abs(PlayerTank.angle - mainAngle) < 0.1 )
                PlayerTank.angle = mainAngle;

            PlayerTank.YLocation -= 1;
            if(PlayerTank.angle == mainAngle)
                PlayerTank.YLocation -= 4;
        }

        else if(keyDOWN && !keyLEFT && !keyRIGHT){

            if(Math.abs(PlayerTank.angle + (3*Math.PI/2)) < Math.abs(PlayerTank.angle - Math.PI/2 )){
                mainAngle = -(3*Math.PI/2);
            }
            else {
                mainAngle = Math.PI/2;
            }
            if(PlayerTank.angle > mainAngle){
                PlayerTank.angle -= 0.05;
            }
            else
                PlayerTank.angle += 0.05;
            if(Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;
            PlayerTank.YLocation -= 1;
            if(PlayerTank.angle == mainAngle)
                PlayerTank.YLocation += 4;
        }

        else if(keyRIGHT && !keyUP && !keyDOWN){
            double difference = Math.abs(PlayerTank.angle);
            mainAngle = 0;
            if(Math.abs(PlayerTank.angle - 2 * Math.PI) < difference) {
                mainAngle = 2 * Math.PI;
                difference = Math.abs(PlayerTank.angle - 2 * Math.PI);
            }
            if(Math.abs(PlayerTank.angle + 2 * Math.PI) < difference){
                mainAngle = -2*Math.PI;
                difference = Math.abs(PlayerTank.angle + 2 * Math.PI);
            }


            if(PlayerTank.angle - mainAngle> 0)
                PlayerTank.angle -= 0.05;
            else
                PlayerTank.angle += 0.05;

            if(Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = 0;

            PlayerTank.XLocation -= 1;
            if(PlayerTank.angle == 0)
                PlayerTank.XLocation -= 4;
        }

        else if(keyLEFT && !keyUP && !keyDOWN){
            if(Math.abs(PlayerTank.angle - (Math.PI)) < Math.abs(PlayerTank.angle + Math.PI ))
                mainAngle = Math.PI;
            else
                mainAngle = -Math.PI;

            if(PlayerTank.angle > mainAngle)
                PlayerTank.angle -= 0.05;
            else
                PlayerTank.angle += 0.05;

            if(Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;
            PlayerTank.XLocation -= 1;
            if(PlayerTank.angle == mainAngle)
                PlayerTank.XLocation -= 4;
        }

        else if(keyLEFT && keyUP) {

            if(Math.abs(PlayerTank.angle + (3*Math.PI/4)) < Math.abs(PlayerTank.angle - 5*Math.PI/4 )){
                mainAngle = -3*Math.PI/4;
            }
            else {
                mainAngle = 5*Math.PI/4;
            }
            if (PlayerTank.angle > mainAngle) {
                PlayerTank.angle -= 0.05;
            } else {
                PlayerTank.angle += 0.05;
            }

            if (Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;

            if(PlayerTank.angle == mainAngle){
                PlayerTank.YLocation -= 2;
                PlayerTank.XLocation += 2;
            }
            else {
                PlayerTank.YLocation -= 1;
                PlayerTank.XLocation += 1;
            }
        }

        else if(keyLEFT && keyDOWN){
            if(Math.abs(PlayerTank.angle - (3*Math.PI/4)) < Math.abs(PlayerTank.angle + 5*Math.PI/4 )){
                mainAngle = 3*Math.PI/4;
            }
            else {
                mainAngle = -5*Math.PI/4;
            }
            if (PlayerTank.angle > mainAngle) {
                PlayerTank.angle -= 0.05;
            } else {
                PlayerTank.angle += 0.05;
            }

            if (Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;

            if(PlayerTank.angle == mainAngle) {
                PlayerTank.XLocation -= 2;
                PlayerTank.YLocation += 2;
            }
            else{
                PlayerTank.XLocation -= 1;
                PlayerTank.YLocation += 1;
            }
        }

        else if(keyRIGHT && keyDOWN){
            if(Math.abs(PlayerTank.angle - (Math.PI/4)) < Math.abs(PlayerTank.angle + 7*Math.PI/4 )){
                mainAngle = Math.PI/4;
            }
            else {
                mainAngle = -7*Math.PI/4;
            }
            if (PlayerTank.angle > mainAngle) {
                PlayerTank.angle -= 0.05;
            } else {
                PlayerTank.angle += 0.05;
            }

            if (Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;

            if(Math.abs(PlayerTank.angle - (1d/4)*Math.PI) < 0.1)
                PlayerTank.angle = (1d/4)*Math.PI;
            if(PlayerTank.angle == (Math.PI/4)) {
                PlayerTank.XLocation += 2;
                PlayerTank.YLocation += 2;
            }
            else{
                PlayerTank.XLocation += 1;
                PlayerTank.YLocation += 1;
            }
        }

        else if(keyRIGHT && keyUP) {
            if(Math.abs(PlayerTank.angle - (7*Math.PI/4)) < Math.abs(PlayerTank.angle + Math.PI/4 )){
                mainAngle = 7*Math.PI/4;
            }
            else {
                mainAngle = -Math.PI/4;
            }
            if (PlayerTank.angle > mainAngle) {
                PlayerTank.angle -= 0.05;
            } else {
                PlayerTank.angle += 0.05;
            }

            if (Math.abs(PlayerTank.angle - mainAngle) < 0.1)
                PlayerTank.angle = mainAngle;

            if (Math.abs(PlayerTank.angle + (1d / 4) * Math.PI) < 0.1)
                PlayerTank.angle = -(1d / 4) * Math.PI;
            if(PlayerTank.angle == -(Math.PI/4)) {
                PlayerTank.XLocation += 2;
                PlayerTank.YLocation -= 2;
            }
            else{
                PlayerTank.XLocation += 1;
                PlayerTank.YLocation -= 1;
            }
        }




//        System.out.println("GameState line 148" + " YLocation " + PlayerTank.YLocation + " YLocation " + PlayerTank.YLocation );

		if (keyUP)
			BattleField.YOffset += 4;
		if (keyDOWN)
			BattleField.YOffset -= 4;
		if (keyLEFT)
			BattleField.XOffset += 4;
		if (keyRIGHT)
			BattleField.XOffset -= 4;

		PlayerTank.gunAngle = Math.atan2(mouseX - PlayerTank.gunLocationX, mouseY - PlayerTank.gunLocationY);
//        System.out.println(this.getClass().getName() + " line 158 " + " Angle is " + PlayerTank.angle);
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
//
//
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
			}
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler extends MouseAdapter implements MouseMotionListener{

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == 3){
//                System.out.println("click right");
//			    playerTank.switchGun();
            }else if(e.getButton() == 1){
//                playerTank.fire(mouseX = e.getX(),mouseY = e.getY());
                mousePress = true;
            }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mousePress = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}


    }

}


