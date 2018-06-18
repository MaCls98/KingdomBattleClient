package model;

public class ManagerPlayer {
	
	private Player localPlayer;
	private int gameTime;
	
	public ManagerPlayer() {
	}
	
	public void setDirection(int direction){
		localPlayer.setDirection(direction);
	}
	
	public void moveRigth(){
		localPlayer.moveRigth();
	}
	
	public void moveLeft() {
		localPlayer.moveLeft();
	}
	
	public void moveUp(){
		localPlayer.moveUp();
	}
	
	public void moveDown(){
		localPlayer.moveDown();
	}
	
	public void createLocalPlayer(Player player){
		this.localPlayer = player;
	}
	
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	public int getGameTime() {
		return gameTime;
	}
	
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
}
