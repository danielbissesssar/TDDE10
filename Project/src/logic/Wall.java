package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall {
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	private int dir;
	ImageIcon icon;
	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3;



	public Wall(int x, int y, int width, int height, int dir, ImageIcon icon1, ImageIcon icon2, ImageIcon icon3) {
		this.dir=dir;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.icon1 = icon1;
		this.icon2 = icon2;
		this.icon3 = icon3;


		if (dir == 1 || dir == 3) {
		icon = icon1;
		} else if(dir == 2) {
			icon = icon2;
		} else {
			icon = icon3;
		}
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

}
