package views;

import java.awt.Color;
import java.awt.Font;
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
	private ImageIcon dead = new ImageIcon(getClass().getResource("/img/dead.png"));
	private ImageIcon dance = new ImageIcon(getClass().getResource("/img/winner.gif"));
	private ImageIcon danceTwo = new ImageIcon(getClass().getResource("/img/winnerD.gif"));
	private ImageIcon king = new ImageIcon(getClass().getResource("/img/king.png"));
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
					if (player.isAlive()) {
						g.drawImage(warrior.getImage(), player.getxAxis(), player.getyAxis(), this);
						g.setColor(Color.WHITE);
						g.setFont(new Font("Arial", Font.BOLD, 20));
						g.drawString(player.getUserName(), player.getxAxis() + 5, player.getyAxis() - 12);
						g.setColor(Color.GREEN);
						g.drawRect(player.getxAxis(), player.getyAxis() + 68, 100, 10);
						g.setColor(Color.GREEN);
						g.fillRect(player.getxAxis(), player.getyAxis() + 68, player.getHealth(), 10);
					}else {
						g.drawImage(dead.getImage(), player.getxAxis(), player.getyAxis(), this);
						g.setColor(Color.WHITE);
						g.setFont(new Font("Arial", Font.BOLD, 20));
						g.drawString(player.getUserName(), player.getxAxis(), player.getyAxis() - 12);
					}
					if (player.isWinner()) {
						g.drawImage(king.getImage(), player.getxAxis(), player.getyAxis(), this);
						g.setColor(Color.WHITE);
						g.setFont(new Font("Arial", Font.BOLD, 20));
						g.drawString(player.getUserName(), player.getxAxis() + 5, player.getyAxis() - 12);
						g.drawImage(dance.getImage(), 100, 400, this);
						g.drawImage(danceTwo.getImage(), 400, 200, this);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
