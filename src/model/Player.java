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
	private int money;
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
		money = 10;
	}
	
	public Player(String name, int screenWidth, int screenHeigth) {
		this.name = name;
		health = 100;
		attack = 10;
		move = 7;
		money = 10;
		isAlive = true;
		this.screenWidth = screenWidth;
		this.screenHeigth = screenHeigth;
	}
	
	public void itemHealth(){
		health = health + 10;
	}
	
	public void itemMove(){
		move = move + 10;
	}
	
	public void itemAttack(){
		attack = attack + 10;
	}
	
	public void restMoney(){
		money = money - 10;
	}
	
	public void checkAlive(){
		if (health <= 0) {
			isAlive = false;
		}
	}
	
	public void fixHealth(){
		health = health + attack;
	}
	
	public void removeShoot(){
		shoot = null;
	}
	
	public void createShoot(int x, int y, int damage, int direction){
		shoot = new Shoot(x, y, damage, direction, getUserName());
	}
	
	public Shoot getShoot() {
		return shoot;
	}

	public void moveLeft(){
		if (isAlive) {
			if (xAxis <= 0) {
				xAxis = 0;
			}else {
				xAxis = xAxis - move;
			}
		}
	}
	
	public void moveRigth(){
		if (isAlive) {
			if (xAxis >= screenWidth) {
				xAxis = screenWidth;
			}else {
				xAxis = xAxis + move;
			}
		}
	}
	
	public void moveUp(){
		if (isAlive) {
			if (yAxis <= 0) {
				yAxis = 0;
			}else {
				yAxis = yAxis - move;
			}
		}
	}
	
	public void moveDown(){
		if (isAlive) {
			if (yAxis >= screenHeigth) {
				yAxis = screenHeigth;
			}else {
				yAxis = yAxis + move;
			}
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", health=" + health + ", isAlive=" + isAlive + "]";
	}
}
