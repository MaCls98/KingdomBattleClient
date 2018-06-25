package views;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import model.Player;
import model.Shoot;

public class GameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon warrior = new ImageIcon(getClass().getResource("/img/warrior.png"));
	private ImageIcon shoot = new ImageIcon(getClass().getResource("/img/attack.gif"));
	private ImageIcon blood = new ImageIcon(getClass().getResource("/img/blood.png"));
	private ImageIcon bg = new ImageIcon(getClass().getResource("/img/background.jpg"));
	private ArrayList<Player> players;
	private ArrayList<Shoot> shoots;
	
	public GameScreen(Controller controller) {
		addKeyListener(controller);
		setFocusable(true);
	}

	public void paintPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void paintShoots(ArrayList<Shoot> shoots){
		this.shoots = shoots;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(bg.getImage(), 0, 0, this);
		
		drawShoots(g);
		drawPlayers(g);
		
		
	}

	public void drawShoots(Graphics g) {
		ArrayList<Shoot> tmpShoots = shoots;
		if (tmpShoots != null) {
			for (Shoot shoot : tmpShoots) {
				try {
					if (shoot.isActive()) {
						g.drawImage(this.shoot.getImage(), shoot.getX(), shoot.getY(), this);
					}else if (!shoot.isActive()) {
						g.drawImage(blood.getImage(), shoot.getX(), shoot.getY(), this);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	public void drawPlayers(Graphics g) {
		ArrayList<Player> tmpPlayers = players;
		if (tmpPlayers != null) {
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
