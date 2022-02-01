package logic;

import java.awt.Image;

public abstract class Weapon extends Item {
	private int dmg;
	private double speed;
	private int range;
	
	public Weapon(Image image, int dmg, double speed, int range) {
		super(image);
		this.dmg=dmg;
		this.speed=speed;
		this.range=range;
	}
	
	public abstract void attack(Player player);
	public abstract void checkRange();
	public abstract void checkCollide(Wall wall);
	public abstract void checkCollide(NPC npc);
	public abstract void checkCollide(Door door);
	
	public int getdmg() {
		return this.dmg;
	}
	public double getSpeed() {
		return this.speed;
	}
	public int getRange() {
		return this.range;
	}
	public void setSpeed(double speed) {
		this.speed=speed;
	}
	public void setDmg(int dmg) {
		this.dmg=dmg;
	}
	public void setRange(int range) {
		this.range=range;
	}
}
