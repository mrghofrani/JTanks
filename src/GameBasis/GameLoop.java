package GameBasis; /*** In The Name of Allah ***/

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods 
 * in the while loop (update() and render()) should be 
 * long running! Both must execute very quickly, without 
 * any waiting and blocking!
 * 
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {
	
	/**
	 * Frame Per Second.
	 * Higher is better, but any value above 24 is fine.
	 */
	public static final int FPS = 30;
	public static boolean gameOver = false;
	public static boolean gameWon = false;
	
	private GameFrame canvas;
	private GameState state;
	private BattleField battleField;

	public GameLoop(GameFrame frame) {
		canvas = frame;
	}
	
	/**
	 * This must be called before the game loop starts.
	 */
	public void init() {
		battleField = new BattleField();
		state = new GameState(battleField);
		canvas.setBattleField(battleField);
		canvas.addKeyListener(state.getKeyListener());
		canvas.addMouseListener(state.getMouseListener());
		canvas.addMouseMotionListener(state.getMouseMotionListener());

	}

	@Override
	public void run() {
		while (!gameOver && !gameWon) {
			try {
				long start = System.currentTimeMillis();
				//
				state.update();
				canvas.render(state);
				gameOver = state.gameOver;
				gameWon = state.gameWon;

				//
				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);
			} catch (InterruptedException ignored) {
			}
		}
		canvas.stop();
		playSound();
		canvas.render(state);
	}
	private synchronized void playSound(){
		if(gameWon){
			new Thread(new Runnable() {
				public void run() {
					try {
						File soundFile = new File("files\\Sounds\\endOfGame.wav");
						AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
						Clip clip = AudioSystem.getClip();
						clip.open(audioIn);
						clip.start();
					} catch (UnsupportedAudioFileException e) {
						//e.printStackTrace();
					} catch (IOException e) {
						// e.printStackTrace();
					} catch (LineUnavailableException e) {
						// e.printStackTrace();
					}
				}
			}).start();
		}
		if(gameOver){
			new Thread(new Runnable() {
				public void run() {
					try {
						File soundFile = new File("files\\Sounds\\gameOver.wav");
						AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
						Clip clip = AudioSystem.getClip();
						clip.open(audioIn);
						clip.start();
					} catch (UnsupportedAudioFileException e) {
						//e.printStackTrace();
					} catch (IOException e) {
						// e.printStackTrace();
					} catch (LineUnavailableException e) {
						// e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
