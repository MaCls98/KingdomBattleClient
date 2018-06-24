package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;
import model.Player;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelGame panelGame;
	
	public MainWindow(Controller controller) {
		setTitle("Kingdom Battle");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		
		panelGame = new PanelGame(controller);
		add(panelGame, BorderLayout.CENTER);
	}
	
	public void paintPlayers(ArrayList<Player> players){
		panelGame.paintPlayers(players);
	}
	
	public void refreshUI(){
		panelGame.refreshUI();
	}
}
