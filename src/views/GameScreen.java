package views;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import model.Player;

public class GameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon warrior = new ImageIcon(getClass().getResource("/img/warrior.png"));
	private ArrayList<Player> players;
	
	public GameScreen(Controller controller) {
		addKeyListener(controller);
		setFocusable(true);
	}

	public void paintPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	@Override
	public synchronized void paint(Graphics g) {
		super.paint(g);
		ArrayList<Player> tmpPlayers = players;
		if (tmpPlayers != null) {
			Iterator<Player> playerIterator = tmpPlayers.iterator();
			while (playerIterator.hasNext()) {
				Player tmpPlayer = playerIterator.next();
				g.drawImage(warrior.getImage(), tmpPlayer.getyAxis(), tmpPlayer.getxAxis(), this);
			}
		}
		repaint();
	}
}
