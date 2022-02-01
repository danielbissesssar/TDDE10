package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

public class RangedWeapon extends Weapon {
	private int projectiles;
	private ArrayList<Projectile> firedProj = new ArrayList<Projectile>();
	private ArrayList<Projectile> removeList = new ArrayList<Projectile>();

	public RangedWeapon(Image image, int dmg, double speed, int range, int projectiles) {
		super(image, dmg, speed, range);
		this.projectiles = projectiles;

	}

	public ArrayList<Projectile> getList() {
		return this.firedProj;
	}

	@Override
	public void checkRange() {
		int num = 0;
		for (Projectile proj : firedProj) {
			if ((Math.abs(proj.getInitX() - proj.getX()) >= this.getRange())
					|| Math.abs(proj.getInitY() - proj.getY()) >= this.getRange()) {
				removeList.add(proj);
			}
			num = num + 1;
		}
		firedProj.removeAll(removeList);
		removeList.clear();
	}

	@Override
	public void checkCollide(Wall wall) {
		for (Projectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);

			}		
			}
		firedProj.removeAll(removeList);
		removeList.clear();

	}
	
	@Override
	public void checkCollide(Door door) {
		for (Projectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(door.getX(), door.getY(), door.getWidth(), door.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);

			}
		}
		firedProj.removeAll(removeList);
		removeList.clear();

	}
	
	@Override
	public void checkCollide(NPC npc) {
		for (Projectile proj : firedProj) {
			Rectangle r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());
			Rectangle r2 = new Rectangle(npc.getX(), npc.getY(), npc.getWidth(), npc.getHeight());
			if (r1.intersects(r2)) {
				removeList.add(proj);
				npc.addHealth(-this.getdmg());

			}
		}
		firedProj.removeAll(removeList);
		removeList.clear();
	}

	@Override
	public void attack(Player player) {
		int u = 0;
		int v = 0;
		if (player.getFacing() == 1 || player.getFacing() == 3) {
			u = player.getWidth() / (projectiles);
		}
		if (player.getFacing() == 2 || player.getFacing() == 4) {
			v = player.getHeight() / (2 * (projectiles));
		}
		for (int i = 0; i <= projectiles - 1; i++) {
			Projectile proj = new Projectile(player, this.getSpeed(), player.getX() + i * u, player.getY() + i * v, projectiles);
			firedProj.add(proj);
			proj.setX(proj.getX()+proj.fixX());
			proj.setY(proj.getY()+proj.fixY());
			proj.setInitX(player.getX() + i * u + proj.fixX());
			proj.setInitY(player.getY() + i * v + proj.fixY());
		}

	}

}
