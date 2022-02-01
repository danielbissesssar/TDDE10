package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Door {
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	private Location nextLoc;
	private boolean open;
	
	public Door(int x, int y,Location nextLoc) {
		this.open=false;
		this.x = x;
		this.y = y;
		this.width = 80;
		this.height = 80;
		this.nextLoc=nextLoc;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\closeddoor.png").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
		this.image = icon.getImage();
	}
	public Image getImage() {
		return this.image;
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
	public Location getNextLoc() {
		return this.nextLoc;
	}
	public boolean getOpen() {
		return this.open;
	}
	public void setOpen() {
		this.open=true;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\opendoor.png").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
		this.image = icon.getImage();
	}

}
