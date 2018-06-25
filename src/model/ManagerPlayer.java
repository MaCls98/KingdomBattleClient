package model;

import views.JPanelStore;

public class ManagerPlayer {
	
	private Player localPlayer;
	private int gameTime;
	
	public ManagerPlayer() {
	}
	
	public void setItems(JPanelStore store){
		if (store.getHealth()) {
			if (localPlayer.getMoney() > 10) {
				localPlayer.itemHealth();
				localPlayer.restMoney();
			}
		}
		if (store.getAttack()) {
			if (localPlayer.getMoney() > 10) {
				localPlayer.itemAttack();
				localPlayer.restMoney();
			}
		}
		if (store.getMove()) {
			if (localPlayer.getMoney() > 10) {
				localPlayer.itemMove();
				localPlayer.restMoney();
			}
		}
		System.out.println(localPlayer);
	}
	
	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
	
	public void setHealth(int health){
		localPlayer.setHealth(health);
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
