package model;

public class Player {
	
	private String name;
	private int screenWidth;
	private int screenHeigth;
	private int xAxis;
	private int yAxis;
	private int direction;
	private int health;
	private int attack;
	private int move;
	private boolean isAlive;
	private Shoot shoot;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String name, int xAxis, int yAxis, int direction, int health, int attack) {
		this.name = name;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.direction = direction;
		this.health = health;
		this.attack = attack;
		isAlive = true;
	}
	
	public Player(String name, int screenWidth, int screenHeigth) {
		this.name = name;
		health = 100;
		attack = 5;
		move = 5;
		this.screenWidth = screenWidth;
		this.screenHeigth = screenHeigth;
	}
	
	public void fixHealth(){
		health = health + attack;
	}
	
	public void removeShoot(){
		shoot = null;
	}
	
	public void createShoot(int x, int y, int damage, int direction){
		shoot = new Shoot(x, y, damage, direction);
	}
	
	public Shoot getShoot() {
		return shoot;
	}

	public void moveLeft(){
		if (xAxis <= 0) {
			xAxis = 0;
		}else {
			xAxis = xAxis - move;
		}
	}
	
	public void moveRigth(){
		if (xAxis >= screenWidth) {
			xAxis = screenWidth;
		}else {
			xAxis = xAxis + move;
		}
	}
	
	public void moveUp(){
		if (yAxis <= 0) {
			yAxis = 0;
		}else {
			yAxis = yAxis - move;
		}
	}
	
	public void moveDown(){
		if (yAxis >= screenHeigth) {
			yAxis = screenHeigth;
		}else {
			yAxis = yAxis + move;
		}
	}
	
	public String getUserName() {
		return name;
	}
	
	public int getxAxis() {
		return xAxis;
	}
	
	public int getyAxis() {
		return yAxis;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	public void setMove(int move) {
		this.move = move;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public void setScreenHeigth(int screenHeigth) {
		this.screenHeigth = screenHeigth;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", direction=" + direction
				+ ", health=" + health + ", attack=" + attack + "]";
	}
}
