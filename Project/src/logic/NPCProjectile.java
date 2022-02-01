package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class NPCProjectile {
	private int x;
	private int y;
	private int initX;
	private int initY;
	private int width;
	private int height;
	private int dir;
	private Image image;
	
	public NPCProjectile(int x,int y,int dir) {
		this.x=x;
		this.y=y;
		this.width=30;
		this.height=30;
		this.dir=dir;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\fireball.gif_c200").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
		this.image=icon.getImage();
	}
	public void moveDirection() {
		switch(dir) {
		case(1): 
			y=y-5;
			break;
		case(2):
			x=x+5;
			break;
		case(3):
			y=y+5;
			break;
		case(4):
			x=x-5;
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
