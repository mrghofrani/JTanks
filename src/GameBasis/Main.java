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
    }

    /**
     * @return the gameLevel that has gotten from MainMenu
     */
    public static int getGameLevel(){
    	return mainMenu.getGameLevel();
	}

//    /**
//     * This method gets the sound state of the game
//     * from MainMenu and returns it
//     * @return the sound state that has gotten from MainMenu
//     */
//	public static boolean getSoundState(){
//    	return mainMenu.getSoundState();
//	}
}
