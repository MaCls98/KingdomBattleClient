package connection;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import model.Player;
import persistence.JSonPlayer;

public class ClientPlayer extends Thread {

	private Socket connection;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean stop;
	private Player localPlayer;
	private JSonPlayer jSonPlayer;
	private ArrayList<Player> players;
	public final static Logger LOGGER = Logger.getGlobal();

	public ClientPlayer() throws IOException, InterruptedException {
		jSonPlayer = new JSonPlayer();
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
		if (response.equals(REQUEST.SEND_PLAYERS.toString())) {
			updatePlayers();
		}
	}

	private void updatePlayers() throws IOException, ParseException {
		FileOutputStream outputStream = new FileOutputStream("players.json");
		int length = Integer.parseInt(inputStream.readUTF());
		byte[] buffer = new byte[4096];
		int fileSize = length;
		int read = 0;
		int remaining = fileSize;
		while ((read = inputStream.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			remaining -= read;
			outputStream.write(buffer, 0, read);
		}
		outputStream.close();
		players = jSonPlayer.getPlayersFromJSon();
	}

	public void sendLocalPlayer() throws IOException {
		String strPlayer = playerToString();
		outputStream.writeUTF(REQUEST.SEND_PLAYERS.toString());
		outputStream.writeUTF(strPlayer);
	}

	public String playerToString() {
		return localPlayer.getName() + "," + localPlayer.getxAxis() + "," + localPlayer.getyAxis() + "," + localPlayer.getDirection() + ","
				+ localPlayer.getHealth() + "," + localPlayer.getAttack();
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
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
}
