package logic;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class NPC {
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	private String name;
	private int health;
	private int damage;
	private int speed;
	private double velX;
	private double velY;
	private Boolean[] allowDir = new Boolean[4];

	public NPC(int X, int Y,int damage) {
		this.x = X;
		this.y = Y;
		this.health = 100;
		this.damage=damage;
		allowDir[0]=true;
		allowDir[1]=true;
		allowDir[2]=true;
		allowDir[3]=true;

	}
	public abstract void move(Player player);
	public void setWidth(int w) {
		this.width=w;
	}
	public double getVelX() {
		return this.velX;
	}
	public void setHeight(int h) {
		this.height=h;
	}
	public void setHealth(int health) {
		this.health=health;
	}
	public void cleanAllow() {
		allowDir[0] = true;
		allowDir[1] = true;
		allowDir[2] = true;
		allowDir[3] = true;
	}
	public int getDmg() {
		return this.damage;
	}

	public void checkCollision(Wall wall) {
		Rectangle r1 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
		if (r1.intersects(x, y - 5, width, height)) {
			allowDir[0] = false;
		}

		if (r1.intersects(x + 5, y, width, height)) {
			allowDir[1] = false;
		}
		if (r1.intersects(x, y + 5, width, height)) {
			allowDir[2] = false;
		}
		if (r1.intersects(x - 5, y, width, height)) {
			allowDir[3] = false;
		}

	}

	public void addHealth(int add) {
		this.health = this.health + add;
	}

	public int getHealth() {
		return this.health;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void addX(int add) {
		this.x = this.x + add;
	}

	public void addY(int add) {
		this.y = this.y + add;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void tick() {
		if ((velX >= 0) && (allowDir[1])) {
			x += velX;
		} else if ((velX <= 0) && (allowDir[3])) {
			x += velX;
		}
		if ((velY >= 0) && (allowDir[2])) {
			y += velY;
		} else if ((velY <= 0) && (allowDir[0])) {
			y += velY;
		}
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
