package persistence;

import model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonPlayer {
	
	private ArrayList<Player> players;
	
	@SuppressWarnings("unchecked")
	public ArrayList<Player> getPlayersFromJSon() throws FileNotFoundException, IOException, ParseException{
		players = new ArrayList<>();
		File jsonPlayers = new File("./players.json");
		if (jsonPlayers.exists()) {
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(new FileReader(jsonPlayers));
			JSONObject dataObj = (JSONObject) root.get("data");
			JSONArray players = (JSONArray) dataObj.get("players");
			for (int i = 0; i < players.size(); i++) {
				JSONObject obj = (JSONObject) players.get(i);
				String name = (String) obj.get("name");
				long xAxis = (long) obj.get("xAxis");
				long yAxis = (long) obj.get("yAxis");
				long attack = (long) obj.get("attack");
				long health = (long) obj.get("health");
				long direction = (long) obj.get("direction");
				Player player = new Player(name, (int) xAxis, (int) yAxis, (int) direction, (int)health, (int)attack);
				System.out.println(player);
				players.add(player);
			}
		}
		return this.players;
	}
}
