package GameBasis; /*** In The Name of Allah ***/
import ThreadPool.ThreadPool;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {

	private static MainMenu mainMenu;
    public static void main(String[] args) {
		// Initialize the global thread-pool
		ThreadPool.init();

		// MainMenu of the game which is used to determine the hard level of the game
		mainMenu = new MainMenu();
		mainMenu.show();
		
		// After the player clicks 'PLAY' ...
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame frame = new GameFrame("Normal Tanks");
				frame.setLocationRelativeTo(null); // put frame at center of screen
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();
				// Create and execute the game-loop
				GameLoop game = new GameLoop(frame);
				game.init();
				ThreadPool.execute(game);
				// and the game starts ...
			}
		});
    }

    /**
     * @return the gameLevel that has gotten from MainMenu
     */
    public static int getGameLevel(){
    	return mainMenu.getGameLevel();
	}

    /**
     * This method gets the sound state of the game
     * from MainMenu and returns it
     * @return the sound state that has gotten from MainMenu
     */
	public static boolean getSoundState(){
    	return mainMenu.getSoundState();
	}
}
