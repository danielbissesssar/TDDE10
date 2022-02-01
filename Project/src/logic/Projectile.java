package logic;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Projectile {
	private Player player;
	private int direction;
	private double speed;
	private int x;
	private int y;
	private int initX;
	private int initY;
	private int width;
	private int height;
	private int projectiles;
	private Image image;
	
	public Projectile(Player player, double speed, int x, int y,int projectiles) {
		this.player=player;
		this.direction=player.getFacing();
		this.speed=speed;
		this.x=x;
		this.y=y;
		this.width=30/projectiles + 5*projectiles;
		this.height=30/projectiles + 5*projectiles;
		this.projectiles=projectiles;
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\iceball.gif").getImage().getScaledInstance(this.width,
				this.height, Image.SCALE_DEFAULT));
		this.image=icon.getImage();
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
	public int getInitX() {
		return this.initX;
	}
	public int getInitY() {
		return this.initY;
	}
	public void setX(int X) {
		this.x=X;
	}
	public void setY(int Y) {
		this.y=Y;
	}
	public void setInitX(int initX) {
		this.initX=initX;
	}
	public void setInitY(int initY) {
		this.initY=initY;
	}
	
	public int fixY() {
		switch(player.getFacing()) {
		case(1): 
			return 0;
		case(2):
			return (player.getHeight()/2-this.height*projectiles/2);
		case(3):
			return player.getHeight();
		case(4):
			return (player.getHeight()/2-this.height*projectiles/2);

		}
		return 0;
	}
	
	public int fixX() {
		switch(player.getFacing()) {
		case(1):
			return (player.getWidth()/2-this.width*projectiles/2);
		case(2):
			return player.getWidth();
		case(3):
			return (player.getWidth()/2-this.width*projectiles/2);

		}
		return 0;
	}
	

	
	public void moveDirection() {
		switch(direction) {
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

}
