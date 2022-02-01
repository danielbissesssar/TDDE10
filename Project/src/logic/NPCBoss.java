package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class NPCBoss extends NPC {
	private int facing = 1;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private ImageIcon icon4;
	private long fired = 0L;
	private long cooldown = 0L;
	private int ms = 1;
	private boolean angry = false;
	private ArrayList<Tornado> tornados = new ArrayList<Tornado>();
	private ArrayList<Tornado> removeList = new ArrayList<Tornado>();

	public NPCBoss(int X, int Y, int damage) {
		super(X, Y, damage);
		this.setHealth(1000);
		this.setWidth(100);
		this.setHeight(100);
		icon1 = new ImageIcon(new ImageIcon("game_graphics\\\\bossR.gif").getImage().getScaledInstance(this.getWidth(),
				this.getHeight(), Image.SCALE_DEFAULT));
		icon2 = new ImageIcon(new ImageIcon("game_graphics\\\\bossL.gif").getImage().getScaledInstance(this.getWidth(),
				this.getHeight(), Image.SCALE_DEFAULT));
		icon3 = new ImageIcon(new ImageIcon("game_graphics\\\\angryR.gif").getImage().getScaledInstance(this.getWidth(),
				this.getHeight(), Image.SCALE_DEFAULT));
		icon4 = new ImageIcon(new ImageIcon("game_graphics\\\\angryL.gif").getImage().getScaledInstance(this.getWidth(),
				this.getHeight(), Image.SCALE_DEFAULT));
		this.setImage(icon1.getImage());
	}

	public ArrayList<Tornado> getTornados() {
		return this.tornados;
	}
	public boolean checkDead() {
		if (this.getHealth() <= 0) {
			return true;
		}
		return false;
	}

	private void spawnTornado(Player player) {
		Random random = new Random();
		int x = random.nextInt(1920);
		int y = random.nextInt(1080);
		Boolean allow = true;
		for (Wall wall : player.getCurrentPosition().getWalls()) {
			Rectangle r1 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			Rectangle r2 = new Rectangle(x, y, 100, 100);
			if (r1.intersects(r2)) {
				allow = false;
			}
		}
		if (allow) {
			Tornado tornado = new Tornado(x, y);
			tornados.add(tornado);
		} else {
			this.spawnTornado(player);
		}
	}

	public void timeoutTornado() {
		for (Tornado tornado : tornados) {
			if (System.currentTimeMillis() - tornado.getSpawned() > 30000) {
				removeList.add(tornado);
			}
		}
		tornados.removeAll(removeList);
		removeList.clear();
	}

	public void checkCollide(Player player) {
		for (Tornado tornado : tornados) {
			Rectangle r1 = new Rectangle(tornado.getX(), tornado.getY(), tornado.getWidth(), tornado.getHeight());
			Rectangle r2 = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
			if (r1.intersects(r2) && (System.currentTimeMillis() - cooldown > 500)) {
				player.addHP(-10);
				this.cooldown = System.currentTimeMillis();

			}
		}
	}

	public void move(Player player) {
		int xDiff = this.getX() - player.getX();
		int yDiff = this.getY() - player.getY();
		timeoutTornado();
		if (System.currentTimeMillis() - this.fired > 5000) {
			spawnTornado(player);
			ms = 7;
			this.fired = System.currentTimeMillis();
			angry = true;

		} else if (System.currentTimeMillis() - this.fired > 1000) {
			ms = 1;
			angry = false;
		}
		if (yDiff >= 0) {
			moveBoss(1);
		} else {
			moveBoss(3);
		}
		if (xDiff <= 0) {
			moveBoss(2);
		} else {
			moveBoss(4);
		}
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			if (xDiff <= 0) {
				this.facing = 2;
			} else {
				this.facing = 4;
			}
		} else {
			if (yDiff >= 0) {
				this.facing = 1;
			} else {
				this.facing = 3;
			}
		}
		if (xDiff < -10) {
			if (angry) {
				setImage(icon3.getImage());
			} else {
				setImage(icon1.getImage());
			}
		} else if (xDiff > 10) {
			if (angry) {
				setImage(icon4.getImage());
			} else {
				setImage(icon2.getImage());
			}
		}

	}

	private void moveBoss(int n) {
		switch (n) {
		case (1):
			this.setVelY(-ms);
			break;
		case (2):
			this.setVelX(ms);
			break;

		case (3):
			this.setVelY(ms);
			break;
		case (4):
			this.setVelX(-ms);
			break;

		}

	}

}
