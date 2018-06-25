package connection;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import model.Player;
import model.Shoot;

public class ClientPlayer extends Thread {

	private Socket connection;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean stop;
	private Player localPlayer;
	private ArrayList<Player> playersList;
	private ArrayList<Shoot> shootList;
	private boolean isOk;
	public final static Logger LOGGER = Logger.getGlobal();

	public ClientPlayer(int ipAddress, int ipPort) throws IOException, InterruptedException {
		connection = new Socket(String.valueOf(ipAddress), ipPort);
		inputStream = new DataInputStream(connection.getInputStream());
		outputStream = new DataOutputStream(connection.getOutputStream());
		start();
	}

	@Override
	public void run() {
		while (!stop) {
			String response;
			try {
				response = inputStream.readUTF();
				if (response != null) {
					manageRequest(response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Connection Lost with the Server");
				stop = true;
			}
		}
	}

	public void manageRequest(String response) throws IOException, ParseException {
		if (response.equals(REQUEST.UPDATE_PLAYER.toString())) {
			updatePlayers();
		} else if (response.equals(REQUEST.UPDATE_SHOOTS.toString())) {
			updateShoots();
		}
	}

	private void updateShoots() throws IOException {
		String shootsStr = inputStream.readUTF();
		String[] shootStr = shootsStr.split(":");
		ArrayList<String> shootsTmp = new ArrayList<>();
		for (String string : shootStr) {
			shootsTmp.add(string);
		}
		try {
			shootList = new ArrayList<>();
			for (String string : shootsTmp) {
				Shoot s = addShootToList(string);
				if (s != null) {
					shootList.add(s);
				}
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
		}
	}

	private void updatePlayers() throws IOException, ParseException {
		String playersStr = inputStream.readUTF();
		String[] playerStr = playersStr.split(":");
		ArrayList<String> playersTmp = new ArrayList<>();
		for (String string : playerStr) {
			playersTmp.add(string);
		}
		try {
			playersList = new ArrayList<>();
			for (String string : playersTmp) {
				Player p = addPlayersToList(string);
				if (p != null) {
					playersList.add(p);
				}
			}
			for (Player player : playersList) {
				if (player.getUserName().equals(localPlayer.getUserName())) {
					localPlayer.setHealth(player.getHealth());
					System.out.println(localPlayer);
				}
			}
			isOk = true;
		} catch (Exception e) {
			isOk = false;
		}
		// FileOutputStream outputStream = new FileOutputStream("players.json");
		// int length = Integer.parseInt(inputStream.readUTF());
		// byte[] buffer = new byte[4096];
		// int fileSize = length;
		// int read = 0;
		// int remaining = fileSize;
		// while ((read = inputStream.read(buffer, 0, Math.min(buffer.length,
		// remaining))) > 0) {
		// remaining -= read;
		// outputStream.write(buffer, 0, read);
		// }
		// outputStream.close();
		// players = jSonPlayer.getPlayersFromJSon();
	}
	
	private Shoot addShootToList(String string) {
		String[] tempPStr = string.split(",");
		try {
			Shoot tempS = new Shoot();
			tempS.setX(Integer.parseInt(tempPStr[0]));
			tempS.setY(Integer.parseInt(tempPStr[1]));
			tempS.setDamage(Integer.parseInt(tempPStr[2]));
			tempS.setDirection(Integer.parseInt(tempPStr[3]));
			tempS.setActive(new Boolean(tempPStr[4]));
			return tempS;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private Player addPlayersToList(String string) {
		String[] tempPStr = string.split(",");
		try {
			Player tempP = new Player();
			tempP.setName(tempPStr[0]);
			tempP.setDirection(Integer.parseInt(tempPStr[1]));
			tempP.setxAxis(Integer.parseInt(tempPStr[2]));
			tempP.setyAxis(Integer.parseInt(tempPStr[3]));
			tempP.setHealth(Integer.parseInt(tempPStr[4]));
			tempP.setAttack(Integer.parseInt(tempPStr[5]));
			return tempP;
		} catch (Exception e) {

		}
		return null;
	}
	
	public void sendShoot() throws IOException {
		outputStream.writeUTF(REQUEST.SEND_SHOOTS.toString());
		outputStream.writeUTF(localPlayer.getShoot().toString());
	}

	public void sendLocalPlayer() throws IOException {
		String strPlayer = playerToString();
		outputStream.writeUTF(REQUEST.SEND_PLAYERS.toString());
		outputStream.writeUTF(strPlayer);
	}

	public String playerToString() {
		return localPlayer.getUserName() + "," + localPlayer.getxAxis() + "," + localPlayer.getyAxis() + ","
				+ localPlayer.getDirection() + "," + localPlayer.getHealth() + "," + localPlayer.getAttack();
	}

	public boolean isOk() {
		return isOk;
	}
	
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	public ArrayList<Shoot> getShootList() {
		return shootList;
	}

	public ArrayList<Player> getPlayersList() {
		return playersList;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
}
