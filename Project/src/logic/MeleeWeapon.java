package logic;

import java.awt.Image;
import java.awt.Rectangle;

public class MeleeWeapon extends Weapon {
	private MeleeProjectile proj = null;
	private long fired =0L;

	public MeleeWeapon(Image image, int dmg, double speed, int range) {
		super(image, dmg, speed, range);
		this.setSpeed(600);
	}

	@Override
	public void attack(Player player) {
		proj = new MeleeProjectile(player, this.getRange());
	}

	@Override
	public void checkRange() {
		if (proj != null) {
			if (Math.abs(proj.getHeight() - proj.getInitH()) >= this.getRange()
					|| Math.abs(proj.getWidth() - proj.getInitW()) >= this.getRange()) {
				if (proj.getDir() > 2) {
					proj.setDir(proj.getDir() - 2);
					proj.projKill();
				} else {
					proj.setDir(proj.getDir() + 2);
					proj.projKill();
				}
			}
			if (Math.abs(proj.getHeight() - proj.getInitH()) == 0 && Math.abs(proj.getWidth() - proj.getInitW()) == 0
					&& proj.getKill()) {
				proj = null;

			}
		}

	}

	public MeleeProjectile getProj() {
		return this.proj;
	}

	@Override
	public void checkCollide(Wall wall) {
		if (proj != null) {
			Rectangle r2 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			Rectangle r1;
			if (proj.getWidth() < 0) {
				r1 = new Rectangle(proj.getX() + proj.getWidth(), proj.getY(), -proj.getWidth(), proj.getHeight());
			} else if (proj.getHeight() < 0) {
				r1 = new Rectangle(proj.getX(), proj.getY() + proj.getHeight(), proj.getWidth(), -proj.getHeight());
			} else {
				r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());

			}
			if (r1.intersects(r2) && !proj.getKill()) {
				if (proj.getDir() > 2) {
					proj.setDir(proj.getDir() - 2);
					proj.projKill();
				} else {
					proj.setDir(proj.getDir() + 2);
					proj.projKill();
				}
			}
			if (Math.abs(proj.getHeight() - proj.getInitH()) == 0 && Math.abs(proj.getWidth() - proj.getInitW()) == 0
					&& proj.getKill()) {
				proj = null;

			}
		}

	}
	
	@Override
	public void checkCollide(Door door) {
		if (proj != null) {
			Rectangle r2 = new Rectangle(door.getX(), door.getY(), door.getWidth(), door.getHeight());
			Rectangle r1;
			if (proj.getWidth() < 0) {
				r1 = new Rectangle(proj.getX() + proj.getWidth(), proj.getY(), -proj.getWidth(), proj.getHeight());
			} else if (proj.getHeight() < 0) {
				r1 = new Rectangle(proj.getX(), proj.getY() + proj.getHeight(), proj.getWidth(), -proj.getHeight());
			} else {
				r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());

			}
			if (r1.intersects(r2) && !proj.getKill()) {
				if (proj.getDir() > 2) {
					proj.setDir(proj.getDir() - 2);
					proj.projKill();
				} else {
					proj.setDir(proj.getDir() + 2);
					proj.projKill();
				}
			}
			if (Math.abs(proj.getHeight() - proj.getInitH()) == 0 && Math.abs(proj.getWidth() - proj.getInitW()) == 0
					&& proj.getKill()) {
				proj = null;

			}
		}

	}

	@Override
	public void checkCollide(NPC npc) {
		if (proj != null) {
			Rectangle r2 = new Rectangle(npc.getX(), npc.getY(), npc.getWidth(), npc.getHeight());
			Rectangle r1;
			if (proj.getWidth() < 0) {
				r1 = new Rectangle(proj.getX() + proj.getWidth(), proj.getY(), -proj.getWidth(), proj.getHeight());
			} else if (proj.getHeight() < 0) {
				r1 = new Rectangle(proj.getX(), proj.getY() + proj.getHeight(), proj.getWidth(), -proj.getHeight());
			} else {
				r1 = new Rectangle(proj.getX(), proj.getY(), proj.getWidth(), proj.getHeight());

			}

			if (r1.intersects(r2)) {
				if (System.currentTimeMillis() - this.fired > 400) {
				npc.addHealth(-this.getdmg());
				this.fired=System.currentTimeMillis();
				}

			}
		}
	}

}
