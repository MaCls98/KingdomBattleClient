package model;

public class Shoot {
	
	private String name;
	private int damage;
	private int direction;
	private int x;
	private int y;
	private boolean isActive;
	
	public Shoot() {
		// TODO Auto-generated constructor stub
	}

	public Shoot(int x, int y, int damage, int direction, String name) {
		super();
		this.damage = damage;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + "," + y + "," + damage + "," + direction + "," + isActive + "," + name;
	}
}
