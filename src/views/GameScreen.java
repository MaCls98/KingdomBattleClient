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
	public void paint(Graphics g) {
		super.paint(g);
		ArrayList<Player> tmpPlayers = players;
		if (tmpPlayers != null) {
			System.out.println(players);
			for (Player player : tmpPlayers) {
				try {
					g.drawImage(warrior.getImage(), player.getxAxis(), player.getyAxis(), this);
					g.drawString(player.getUserName(), player.getxAxis(), player.getyAxis() - 15);
					g.drawString(String.valueOf(player.getHealth()), player.getxAxis(), player.getyAxis() + 30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
