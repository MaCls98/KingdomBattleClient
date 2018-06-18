package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import connection.ClientPlayer;
import model.ManagerPlayer;
import model.Player;
import persistence.JSonPlayer;
import views.MainWindow;

public class Controller implements KeyListener {

	private ClientPlayer clientPlayer;
	private ManagerPlayer managerPlayer;
	private MainWindow mainWindow;
	private Timer timer;

	public Controller() throws IOException, InterruptedException {
		mainWindow = new MainWindow(this);
		managerPlayer = new ManagerPlayer();
		createLocalPlayer();
		clientPlayer = new ClientPlayer();
		refreshLocalPlayerInfo();
		mainWindow.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			managerPlayer.setDirection(1);
			managerPlayer.moveRigth();
			break;
		case KeyEvent.VK_LEFT:
			managerPlayer.setDirection(2);
			managerPlayer.moveLeft();
			break;
		case KeyEvent.VK_UP:
			managerPlayer.setDirection(3);
			managerPlayer.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			managerPlayer.setDirection(4);
			managerPlayer.moveDown();
			break;
		// DISPARAR
		// case KeyEvent.VK_SPACE:
		// managerPlayer.createShot(managerPlayer.getCharacterPlayer().getX(),
		// managerPlayer.getCharacterPlayer().getY(),
		// managerPlayer.getDirection());
		// break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			managerPlayer.setDirection(1);
			managerPlayer.moveRigth();
			break;
		case KeyEvent.VK_LEFT:
			managerPlayer.setDirection(2);
			managerPlayer.moveLeft();
			break;
		case KeyEvent.VK_UP:
			managerPlayer.setDirection(3);
			managerPlayer.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			managerPlayer.setDirection(4);
			managerPlayer.moveDown();
			break;
		// DISPARAR
		// case KeyEvent.VK_SPACE:
		// managerPlayer.createShot(managerPlayer.getCharacterPlayer().getX(),
		// managerPlayer.getCharacterPlayer().getY(),
		// managerPlayer.getDirection());
		// break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			managerPlayer.setDirection(1);
			managerPlayer.moveRigth();
			break;
		case KeyEvent.VK_LEFT:
			managerPlayer.setDirection(2);
			managerPlayer.moveLeft();
			break;
		case KeyEvent.VK_UP:
			managerPlayer.setDirection(3);
			managerPlayer.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			managerPlayer.setDirection(4);
			managerPlayer.moveDown();
			break;
		// DISPARAR
		// case KeyEvent.VK_SPACE:
		// managerPlayer.createShot(managerPlayer.getCharacterPlayer().getX(),
		// managerPlayer.getCharacterPlayer().getY(),
		// managerPlayer.getDirection());
		// break;
		default:
			break;
		}
	}

	private void createLocalPlayer() {
		managerPlayer.createLocalPlayer(
				new Player(JOptionPane.showInputDialog("Name"), "a123"));
	}

	private void refreshLocalPlayerInfo() {
		timer = new Timer(150, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (managerPlayer.getLocalPlayer() != null) {
					clientPlayer.setLocalPlayer(managerPlayer.getLocalPlayer());
					try {
						clientPlayer.sendLocalPlayer();
						mainWindow.paintPlayers(clientPlayer.getPlayers());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		timer.start();
	}
}
