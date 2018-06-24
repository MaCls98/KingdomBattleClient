package model;

public class Shoot {
	
	private int damage;
	private int direction;
	private int x;
	private int y;
	private boolean isActive;

	public Shoot(int x, int y, int damage, int direction) {
		super();
		this.damage = damage;
		this.direction = direction;
		this.x = x;
		this.y = y;
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

	@Override
	public String toString() {
		return x + "," + y + "," + damage + "," + direction + "," + isActive;
	}
}
