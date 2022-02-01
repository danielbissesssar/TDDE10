package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MeleeProjectile {
	private Image image;
	private int direction;
	private int startDirection;
	private int range;
	private int initW;
	private int initH;
	private int width;
	private int height;
	private Player player;
	private boolean kill;

	public MeleeProjectile(Player player, int range) {
		this.player = player;
		this.direction = player.getFacing();
		this.startDirection = player.getFacing();
		this.range = range;
		if (direction % 2 == 0) {
			this.width = 1;
			this.height = 40;
		} else {
			this.width = 40;
			this.height = 1;
		}
		this.initW = this.width;
		this.initH = this.height;
		this.kill = false;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\sword.png").getImage()
				.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT));
		this.image = icon.getImage();
	}

	public Image getImage() {
		return this.image;
	}

	public void projKill() {
		this.kill = true;
	}

	public Boolean getKill() {
		return kill;
	}

	private int fixX() {
		switch (player.getFacing()) {
		case (1):
			return (player.getWidth() / 2 - this.initW / 2);
		case (2):
			return player.getWidth();
		case (3):
			return (player.getWidth() / 2 - this.initW / 2);

		}
		return 0;
	}

	public int getX() {
		return (this.player.getX() + this.fixX());
	}

	private int fixY() {
		switch (player.getFacing()) {
		case (1):
			return 0;
		case (2):
			return (player.getHeight() / 2 - this.initH / 2);
		case (3):
			return player.getHeight();
		case (4):
			return (player.getHeight() / 2 - this.initH / 2);

		}
		return 0;
	}

	public int getY() {
		return (this.player.getY() + this.fixY());
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getInitW() {
		return this.initW;
	}

	public int getInitH() {
		return this.initH;
	}

	public void setDir(int dir) {
		this.direction = dir;
	}

	public int getDir() {
		return this.direction;
	}

	private void updateImage() {
		ImageIcon icon;
		if (this.startDirection == 1) {
			icon = new ImageIcon(new ImageIcon("game_graphics\\\\sword1.png").getImage().getScaledInstance(this.width,
					-this.height, Image.SCALE_DEFAULT));
		} else if (this.startDirection == 2) {
			icon = new ImageIcon(new ImageIcon("game_graphics\\\\sword2.png").getImage().getScaledInstance(this.width,
					this.height, Image.SCALE_DEFAULT));
		} else if (this.startDirection == 3) {
			icon = new ImageIcon(new ImageIcon("game_graphics\\\\sword3.png").getImage().getScaledInstance(this.width,
					this.height, Image.SCALE_DEFAULT));
		} else if (this.startDirection == 4) {
			icon = new ImageIcon(new ImageIcon("game_graphics\\\\sword4.png").getImage().getScaledInstance(-this.width,
					this.height, Image.SCALE_DEFAULT));
		} else {
			icon = null;
		}
		
		this.image = icon.getImage();
	}

	public void moveDirection() {
		switch (direction) {
		case (1):
			height = height - this.range / 5;
			this.updateImage();
			break;
		case (2):
			width = width + this.range / 5;
			this.updateImage();
			break;
		case (3):
			height = height + this.range / 5;
			this.updateImage();
			break;
		case (4):
			width = width - this.range / 5;
			this.updateImage();
			break;
		}
	}

}
