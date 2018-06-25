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
import views.MainWindow;

public class Controller implements KeyListener, ActionListener {

	private ClientPlayer clientPlayer;
	private ManagerPlayer managerPlayer;
	private MainWindow mainWindow;
	private Timer timer;

	public Controller() throws IOException, InterruptedException {
		mainWindow = new MainWindow(this);
		managerPlayer = new ManagerPlayer();
		refreshLocalPlayerInfo();
		refreshUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		switch (EVENTS.valueOf(a.getActionCommand())) {
		case LOGIN:
			createLocalPlayer(mainWindow.getFieldName());
			try {
				clientPlayer = new ClientPlayer(mainWindow.getFieldIPAddress(), mainWindow.getFieldIPPort());
			} catch (IOException | InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Wrong IP Address or IP Port", "Error", JOptionPane.WARNING_MESSAGE);
			}
			mainWindow.hideLogin();
			mainWindow.setVisible(true);
			break;

		default:
			break;
		}
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
		case KeyEvent.VK_SPACE:
			try {
				shoot();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
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
		
		default:
			break;
		}
	}

	public void shoot() throws IOException {
		managerPlayer.getLocalPlayer().createShoot(managerPlayer.getLocalPlayer().getxAxis() + 15, managerPlayer.getLocalPlayer().getyAxis() + 15, managerPlayer.getLocalPlayer().getAttack(), managerPlayer.getLocalPlayer().getDirection());
		managerPlayer.getLocalPlayer().fixHealth();
		clientPlayer.sendShoot();
	}

	private void createLocalPlayer(String userName) {
		managerPlayer.createLocalPlayer(new Player(userName , 700, 500));
	}

	private void refreshUI() {
		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.refreshUI();
			}
		});
		timer.start();
	}

	private void refreshLocalPlayerInfo() {
		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (managerPlayer.getLocalPlayer() != null) {
					if (clientPlayer.getLocalPlayer() != null) {
						managerPlayer.setHealth(clientPlayer.getLocalPlayer().getHealth());
					}
					clientPlayer.setLocalPlayer(managerPlayer.getLocalPlayer());
					try {
						clientPlayer.sendLocalPlayer();
						if (clientPlayer.isOk()) {
							mainWindow.paintPlayers(clientPlayer.getPlayersList());
							mainWindow.paintShoots(clientPlayer.getShootList());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					
				}
			}
		});
		timer.start();
	}
}
