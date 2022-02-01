package logic;

import java.awt.Image;

public class Item {
	private Image image;
	private String name;
	private double weight;
	private int price;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Item(Image image) {
		this.image = image;
	}
	public Image getImage() {
		return this.image;
	}
	
	public void setPos(int x,int y,int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return this.name;
	}
	public double getWeight() {
		return this.weight;
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

