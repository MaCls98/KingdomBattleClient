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

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Player;

public class ClientPlayer extends Thread {

	private Socket connection;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean stop;
	private Player localPlayer;
	private ArrayList<Player> playersList;
	private boolean isOk;
	public final static Logger LOGGER = Logger.getGlobal();

	public ClientPlayer() throws IOException, InterruptedException {
		createConnection();
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

	private void updateShoots() {
		
	}

	private void updatePlayers() throws IOException, ParseException {
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
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
		}
	}

	private Player addPlayersToList(String string) {
		String[] tempPStr = string.split(",");
		try {
			Player tempP = new Player();
			tempP = new Player();
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

	public void createConnection() {
		try {
			this.connection = new Socket(JOptionPane.showInputDialog("IP Address"),
					Integer.parseInt(JOptionPane.showInputDialog("IP Port")));
		} catch (HeadlessException | NumberFormatException | IOException e) {
			JOptionPane.showMessageDialog(null, "IP Addres or IP Port Wrong // Connection Rejected", "Error Connection",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public ArrayList<Player> getPlayersList() {
		return playersList;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
}
