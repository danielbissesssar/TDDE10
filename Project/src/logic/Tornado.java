package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tornado {
	private int x;
	private int y;
	private int width;
	private int height;
	private int dir = 1;
	private Image image;
	private long spawned =0L;


	public Tornado(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 80;
		this.height = 120;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\tornado.gif").getImage()
				.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT));
		this.image = icon.getImage();
		this.spawned=System.currentTimeMillis();
	}
	public Long getSpawned() {
		return this.spawned;
	}

	public void moveDirection() {
		if (dir == 5) {
			dir = 1;
		}
		switch (dir) {
		case (1):
			y = y - 10;
			dir++;
			break;
		case (2):
			x = x + 10;
			dir++;
			break;
		case (3):
			y = y + 10;
			dir++;
			break;
		case (4):
			x = x - 10;
			dir++;
			break;
		}
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

	public Image getImage() {
		return this.image;
	}

}
