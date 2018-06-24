package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;
import model.Player;

public class PanelGame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameScreen gameScreen;
	
	public PanelGame(Controller controller) {
		setLayout(new BorderLayout());
		
		gameScreen = new GameScreen(controller);
		add(gameScreen, BorderLayout.CENTER);
	}
	
	public void paintPlayers(ArrayList<Player> players){
		gameScreen.paintPlayers(players);
	}

	public void refreshUI() {
		gameScreen.repaint();
	}
}
