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
			login();
			break;
		case BUY:
			break;
		default:
			break;
		}
	}

	public void login() {
		createLocalPlayer(mainWindow.getFieldName());
		try {
			clientPlayer = new ClientPlayer(mainWindow.getFieldIPAddress(), mainWindow.getFieldIPPort());
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Wrong IP Address or IP Port", "Error", JOptionPane.WARNING_MESSAGE);
		}
		mainWindow.setTitle("Kingdom Battle -" + managerPlayer.getLocalPlayer().getUserName());
		mainWindow.hideLogin();
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
		if (clientPlayer.isOk()) {
			if (managerPlayer.getLocalPlayer().isAlive()) {
				managerPlayer.getLocalPlayer().createShoot(managerPlayer.getLocalPlayer().getxAxis(), managerPlayer.getLocalPlayer().getyAxis(), managerPlayer.getLocalPlayer().getAttack(), managerPlayer.getLocalPlayer().getDirection());
				clientPlayer.sendShoot();
			}
		}
	}

	private void createLocalPlayer(String userName) {
		managerPlayer.createLocalPlayer(new Player(userName , 800, 600));
	}

	private void refreshUI() {
		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clientPlayer != null) {
					if (clientPlayer.isWaiting()) {
						mainWindow.showIntro();
					}else if (clientPlayer.isOk()) {
						mainWindow.hideIntro();
						mainWindow.setVisible(true);
						mainWindow.paintPlayers(clientPlayer.getPlayersList());
						mainWindow.paintShoots(clientPlayer.getShootList());
					}
					mainWindow.refreshUI();
				}
			}
		});
		timer.start();
	}

	private void refreshLocalPlayerInfo() {
		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (managerPlayer.getLocalPlayer() != null) {
					if (clientPlayer.isOk()) {
						clientPlayer.setLocalPlayer(managerPlayer.getLocalPlayer());
						try {
							clientPlayer.sendLocalPlayer();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		timer.start();
	}
}
