package logic;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Location {
	private Image background;
	private ArrayList<NPC> mobs = new ArrayList<NPC>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Door> doors = new ArrayList<Door>();
	ArrayList<Item> items = new ArrayList<Item>();

	
	public Location(Image background) {
		this.background = background;
	}
	public Image getImage() {
		return this.background;
	}
	public void checkDead() {
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		int q=0;
		for (NPC mob : mobs) {
			if (mob.getHealth() <= 0 ) {
				removeArray.add(q);
			}
			q = q + 1;
		}
		for (Integer remove : removeArray) {
			mobs.remove(mobs.get(remove));
		}
		if (mobs.size() == 0) {
			for (Door door : doors) {
				door.setOpen();
			}
		}
		
	}

	public ArrayList<NPC> getMobs() {
		return this.mobs;
	}
	public ArrayList<Wall> getWalls() {
		return this.walls;
	}
	public ArrayList<Door> getDoors() {
		return this.doors;
	}
	public ArrayList<Item> getItems() {
		return this.items;
	}
	public void addWall(Wall wall) {
		walls.add(wall);
	}
	public void addNPC(NPC npc) {
		mobs.add(npc);
	}
	public void addDoor(Door door) {
		doors.add(door);
	}
	public void addItem(Item item) {
		items.add(item);
	}
}
