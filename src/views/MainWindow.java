package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Player;
import model.Shoot;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelGame panelGame;
	private JPanelLogin login;
	private JDialogIntro intro;
	private JPanelStore store;
	
	public MainWindow(Controller controller) {
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		
		intro = new JDialogIntro(controller);
		
		login = new JPanelLogin(controller);
		login.setVisible(true);
		
		panelGame = new PanelGame(controller);
		add(panelGame, BorderLayout.CENTER);
	}
	
	public void paintShoots(ArrayList<Shoot> shoots){
		panelGame.paintShoots(shoots);
	}
	
	public void paintPlayers(ArrayList<Player> players){
		panelGame.paintPlayers(players);
	}
	
	public void refreshUI(){
		panelGame.refreshUI();
	}
	
	public String getFieldName() {
		return login.getFieldName();
	}

	public int getFieldIPAddress() {
		return login.getFieldIPAddress();
	}

	public int getFieldIPPort() {
		return login.getFieldIPPort();
	}
	
	public JPanelStore getBuy(){
		return store;
	}

	public void hideLogin() {
		login.setVisible(false);
	}
	
	public void hideIntro(){
		intro.setVisible(false);
	}

	public void showIntro() {
		intro.setVisible(true);
	}
}
