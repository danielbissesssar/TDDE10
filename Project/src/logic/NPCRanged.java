package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class NPCRanged extends NPC {
	private int facing;
	private long fired = 0L;
	private ArrayList<NPCProjectile> firedProj = new ArrayList<NPCProjectile>();
	private ArrayList<NPCProjectile> removeList = new ArrayList<NPCProjectile>();

	public NPCRanged(int X, int Y, int damage) {
		super(X, Y, damage);
		this.facing = 1;
		this.setWidth(40);
		this.setHeight(40);
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\rangedmob.gif").getImage()
				.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setImage(icon.getImage());
	}

	public void checkCollide(Door door) {
		for (NPCProjectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(door.getX(), door.getY(), door.getWidth(), door.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);

			}
		}
		firedProj.removeAll(removeList);
		removeList.clear();

	}

	public void checkCollide(Player player) {
		for (NPCProjectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);
				player.addHP(-this.getDmg());

			}
		}
		firedProj.removeAll(removeList);
		removeList.clear();
	}

	public void checkCollide(Wall wall) {
		for (NPCProjectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);

			}
		}
		firedProj.removeAll(removeList);
		removeList.clear();

	}

	public ArrayList<NPCProjectile> getFiredProj() {
		return this.firedProj;
	}

	public void attack() {
		if (System.currentTimeMillis() - this.fired > 2000) {
			NPCProjectile proj = new NPCProjectile(this.getX(), this.getY(), this.facing);
			firedProj.add(proj);
			this.fired = System.currentTimeMillis();
		}

	}

	public void move(Player player) {
		int xDiff = this.getX() - player.getX();
		int yDiff = this.getY() - player.getY();
		if (yDiff >= 0) {
			moveRanged(1);
		} else {
			moveRanged(3);
		}
		if (xDiff <= 0) {
			moveRanged(2);
		} else {
			moveRanged(4);
		}
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			if (xDiff <= 0) {
				this.facing=2;
			} else {
				this.facing=4;
			}
		} else {
			if (yDiff >= 0) {
				this.facing=1;
			} else {
				this.facing=3;
			}
		}

	}

	private void moveRanged(int n) {
		switch (n) {
		case (1):
			this.setVelY(-2);
			break;
		case (2):
			this.setVelX(2);
			break;

		case (3):
			this.setVelY(2);
			break;
		case (4):
			this.setVelX(-2);
			break;

		}

	}
}
