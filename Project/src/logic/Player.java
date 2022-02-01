package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.text.Position;

public class Player {
	private Image image;
	private String name;
	private Location currentLocation;
	private double velX = 0;
	private double velY = 0;
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	private int deaths;
	private int facing;
	private long fired = 0L;
	private Weapon weapon = new RangedWeapon(null, 25, 300, 600, 1);
	private Boolean[] allowDir = new Boolean[4];
	ArrayList<Item> items = new ArrayList<Item>();
	private ImageIcon icon1;
	private ImageIcon icon2;
	
	public Player(int X, int Y) {
		this.deaths = 0;
		this.x = X;
		this.y = Y;
		this.width = 40;
		this.height = 80;
		this.health = 100;
		allowDir[0] = true;
		allowDir[1] = true;
		allowDir[2] = true;
		allowDir[3] = true;
		facing = 2;
		icon1 = new ImageIcon(new ImageIcon("game_graphics\\\\character.gif").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
		icon2 = new ImageIcon(new ImageIcon("game_graphics\\\\character2.gif").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
	}

	public boolean checkDead() {
		if (this.health <= 0) {
			return true;
		}
		return false;
	}

	public void swapWeapon() {
		if (this.getOtherWeapon() != null) {
			Weapon temp;
			temp = weapon;
			weapon = this.getOtherWeapon();
			items.remove(this.getOtherWeapon());
			items.add(temp);
		}
	}

	public int getFacing() {
		return this.facing;
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

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public Image getImage() {
			if(facing <= 2) {
				this.image = icon1.getImage();
			} else {
				this.image = icon2.getImage();
	
			}
		return this.image;
	}

	public Location getCurrentPosition() {
		return this.currentLocation;
	}

	public void setCurrentPosition(Location pos) {
		currentLocation = pos;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void attack() {
		this.weapon.attack(this);
	}

	public void checkCollision(Door door) {
		Rectangle r1 = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		Rectangle r2 = new Rectangle(door.getX(), door.getY(), door.getWidth(), door.getHeight());
		if (r1.intersects(r2) && door.getOpen()) {
			this.currentLocation = door.getNextLoc();
			this.x=80;
			this.y=880;
		}
	}

	public void checkCollision(NPC npc) {
		Rectangle r1 = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		Rectangle r2 = new Rectangle(npc.getX(), npc.getY(), npc.getWidth(), npc.getHeight());
		if (r1.intersects(r2)) {
			if (System.currentTimeMillis() - this.fired > 1000 && (npc instanceof NPCMelee || npc instanceof NPCBoss)) {
				health = health - npc.getDmg();
				this.fired = System.currentTimeMillis();
			}
		}
	}

	public void checkCollision(Item item) {
		Rectangle r1 = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		Rectangle r2 = new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight());
		if (r1.intersects(r2)) {
			giveItem(item);
		}
	}

	public void giveItem(Item it) {
		if (it != null) {
			items.add(it);
			if (it instanceof StatBonusItem) {
				((StatBonusItem) it).equip(this);
			}
		}
	}

	public void removePicked() {
		for (Item item : items) {
			this.currentLocation.getItems().remove(item);
		}
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Weapon getOtherWeapon() {
		for (Item item : items) {
			if (item != null) {
				if (item instanceof Weapon) {
					return (Weapon) item;
				}
			}
		}
		return null;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void cleanAllow() {
		allowDir[0] = true;
		allowDir[1] = true;
		allowDir[2] = true;
		allowDir[3] = true;
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

	public void stopDir(int i) {
		switch (i) {
		case (1):
			this.stopY();
			break;
		case (2):
			this.stopX();
			break;
		case (3):
			this.stopY();
			break;
		case (4):
			this.stopX();
			break;
		}
	}

	private void stopY() {
		velY = 0;
	}

	private void stopX() {
		velX = 0;
	}

	public void moveDir(int i) {
		switch (i) {
		case (1):
			this.moveUp();
			break;
		case (2):
			this.moveRight();
			break;
		case (3):
			this.moveDown();
			break;
		case (4):
			this.moveLeft();
			break;
		}
	}

	public void moveUp() {
		facing = 1;
		velY = -5;
	}

	public void moveDown() {
		facing = 3;
		velY = 5;
	}

	public void moveRight() {
		facing = 2;
		velX = 5;
	}

	public void moveLeft() {
		facing = 4;
		velX = -5;
	}

	public void addHP(int change) {
		this.health = this.health + change;
	}

	public int getHP() {
		return this.health;
	}

}
