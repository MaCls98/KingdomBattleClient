package model;

public class Player {
	
	private String name;
	private String password;
	private int screenWidth;
	private int screenHeigth;
	private int xAxis;
	private int yAxis;
	private int direction;
	private int health;
	private int attack;
	private int move;

	public Player(String name, int xAxis, int yAxis, int direction, int health, int attack) {
		super();
		this.name = name;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.direction = direction;
		this.health = health;
		this.attack = attack;
	}

	public Player(String name, String password) {
		this.name = name;
		this.password = password;
		this.screenWidth = 900;
		this.screenHeigth = 600;
		health = 100;
		attack = 5;
		move = 10;
		System.out.println(screenWidth);
		System.out.println(screenHeigth);
	}

	public void moveLeft(){
		if (xAxis <= 40) {
			xAxis = 40;
		}else {
			xAxis = xAxis - move;
		}
	}
	
	public void moveRigth(){
		if (xAxis >= screenWidth - 40) {
			xAxis = screenWidth - 40;
		}else {
			xAxis = xAxis + move;
		}
	}
	
	public void moveUp(){
		if (yAxis <= 40) {
			yAxis = 40;
		}else {
			yAxis = yAxis - move;
		}
	}
	
	public void moveDown(){
		if (yAxis >= screenHeigth - 40) {
			yAxis = screenHeigth - 40;
		}else {
			yAxis = yAxis + move;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
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

	@Override
	public String toString() {
		return "Player [name=" + name + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", direction=" + direction
				+ ", health=" + health + ", attack=" + attack + "]";
	}
}
