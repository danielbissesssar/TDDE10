package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import graphics.*;
import logic.*;

public class Game extends JFrame {
	private Player player = new Player(80, 880);
	private ArrayList<Location> locations = new ArrayList<Location>();
	private int deaths = 0;
	private int[] highscores = new int[10];
	private JFrame f;
	GamingWindow gamingWindow;
	Menu menu;
	Highscore highscore;
	DeathScreen deathScreen;

	public static enum STATE {
		MENU, GAME, HIGHSCORE, DEATHSCREEN
	}

	public static STATE State = STATE.MENU;

	public Game() {
		f = new MyFrame();
		f.setSize(1920, 1080);
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

		createRooms();
		addNPCs();
		addWalls();
		addItems();
		addDoors();
		

		gamingWindow = new GamingWindow(player, menu);
		f.add(gamingWindow, BorderLayout.WEST);

		highscore = new Highscore(player, highscores, menu);
		f.add(highscore, new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		System.out.println(highscore.toString());

		menu = new Menu(player, highscore, gamingWindow);
		f.add(menu, new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

		deathScreen = new DeathScreen(this, gamingWindow);
		f.add(deathScreen, new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		createKeybinds();

		gamingWindow.setVisible(false);
		menu.setVisible(true);
		highscore.setVisible(false);
		deathScreen.setVisible(false);
		f.setVisible(true);
		for (int i = 0; i < 10; i++) {
			highscores[i] = i+10;
		}
		
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("highscores")));
			highscores = (int[]) stream.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(highscores[i]);
		}

	}

	public void addItems() {
		ImageIcon mwepimg = new ImageIcon(
				new ImageIcon("game_graphics\\\\sword1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		MeleeWeapon mwep = new MeleeWeapon(mwepimg.getImage(), 50, 100, 40);
		mwep.setPos(1730, 850, 50, 50);
		locations.get(0).addItem(mwep);

		ImageIcon statbonusimg = new ImageIcon(new ImageIcon("game_graphics\\\\statbonus.png").getImage()
				.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		Random random = new Random();
		StatBonusItem item1 = new StatBonusItem(statbonusimg.getImage(), random.nextInt(20), random.nextInt(100), random.nextInt(25), random.nextInt(15), -random.nextInt(50));
		item1.setPos(1730, 910, 50, 50);
		locations.get(0).addItem(item1);
		StatBonusItem item2 = new StatBonusItem(statbonusimg.getImage(), random.nextInt(20), random.nextInt(100), random.nextInt(25), random.nextInt(15), -random.nextInt(50));
		item2.setPos(680, 500, 50, 50);
		locations.get(1).addItem(item2);
		StatBonusItem item3 = new StatBonusItem(statbonusimg.getImage(), random.nextInt(20), random.nextInt(100), random.nextInt(25), random.nextInt(15), -random.nextInt(50));
		item3.setPos(1400, 120, 50, 50);
		locations.get(2).addItem(item3);
		StatBonusItem item4 = new StatBonusItem(statbonusimg.getImage(), random.nextInt(20), random.nextInt(100), random.nextInt(25), random.nextInt(15), -random.nextInt(50));
		item4.setPos(960, 540, 50, 50);
		locations.get(3).addItem(item4);
		StatBonusItem item5 = new StatBonusItem(statbonusimg.getImage(), random.nextInt(20), random.nextInt(100), random.nextInt(25), random.nextInt(15), -random.nextInt(50));
		item5.setPos(800, 500, 50, 50);
		locations.get(4).addItem(item5);

	}

	public void addDoors() {
		Door door1 = new Door(1740, 0, locations.get(1));
		locations.get(0).addDoor(door1);
		Door door2 = new Door(1740, 0, locations.get(2));
		locations.get(1).addDoor(door2);
		Door door3 = new Door(1740, 0, locations.get(3));
		locations.get(2).addDoor(door3);
		Door door4 = new Door(1740, 0, locations.get(4));
		locations.get(3).addDoor(door4);
		Door door5 = new Door(1740, 0, locations.get(5));
		locations.get(4).addDoor(door5);
	}

	public void addWalls() {
		ImageIcon icon1 = new ImageIcon(new ImageIcon("game_graphics\\\\wall3UD.png").getImage()
				.getScaledInstance(160, 80, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(new ImageIcon("game_graphics\\\\wall3R.png").getImage()
				.getScaledInstance(80, 160, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(new ImageIcon("game_graphics\\\\wall3L.png").getImage()
				.getScaledInstance(80, 160, Image.SCALE_DEFAULT));
		Wall wall11 = new Wall(0, 0, 80, 1080, 2, icon1, icon2, icon3);
		locations.get(0).addWall(wall11);
		Wall wall16 = new Wall(1740, 0, 200, 40 , 4, icon1, icon2, icon3);
		locations.get(0).addWall(wall16);
		Wall wall12 = new Wall(1820, 0, 80, 1080, 4, icon1, icon2, icon3);
		locations.get(0).addWall(wall12);
		Wall wall15 = new Wall(1600, 560, 80, 400, 4, icon1, icon2, icon3);
		locations.get(0).addWall(wall15);
		Wall wall13 = new Wall(0, 0, 1740, 80, 1, icon1, icon2, icon3);
		locations.get(0).addWall(wall13);
		Wall wall14 = new Wall(0, 960, 1920, 80, 3, icon1, icon2, icon3);
		locations.get(0).addWall(wall14);
		
		locations.get(1).addWall(wall11);
		locations.get(1).addWall(wall16);
		locations.get(1).addWall(wall12);
		locations.get(1).addWall(wall13);
		locations.get(1).addWall(wall14);
		Wall wall21 = new Wall(600 , 300, 80 , 400, 2, icon1, icon2, icon3);
		locations.get(1).addWall(wall21);
		Wall wall22 = new Wall(600 , 300, 340 , 80, 1, icon1, icon2, icon3);
		locations.get(1).addWall(wall22);
		Wall wall23 = new Wall(600 , 700, 340 , 80, 3, icon1, icon2, icon3);
		locations.get(1).addWall(wall23);

		Wall wall32 = new Wall(1300, 200, 340, 80, 1, icon1, icon2, icon3);
		locations.get(2).addWall(wall32);
		Wall wall33 = new Wall(1500, 400, 340, 80, 1, icon1, icon2, icon3);
		locations.get(2).addWall(wall33);
		Wall wall31 = new Wall(1300, 0, 80, 400, 4, icon1, icon2, icon3);
		locations.get(2).addWall(wall31);
		locations.get(2).addWall(wall11);
		locations.get(2).addWall(wall16);
		locations.get(2).addWall(wall12);
		locations.get(2).addWall(wall13);
		locations.get(2).addWall(wall14);
		
		
		
		locations.get(3).addWall(wall11);
		locations.get(3).addWall(wall16);
		locations.get(3).addWall(wall12);
		locations.get(3).addWall(wall13);
		locations.get(3).addWall(wall14);
		
		Wall wall44 = new Wall(1000, 300, 80, 400, 4, icon1, icon2, icon3);
		locations.get(4).addWall(wall44);
		Wall wall41 = new Wall(600, 300, 80, 400, 2, icon1, icon2, icon3);
		locations.get(4).addWall(wall41);
		Wall wall42 = new Wall(600, 300, 400, 80, 1, icon1, icon2, icon3);
		locations.get(4).addWall(wall42);
		Wall wall43 = new Wall(600, 700, 200, 80, 1, icon1, icon2, icon3);
		locations.get(4).addWall(wall43);
		Wall wall45 = new Wall(840, 700, 160, 80, 1, icon1, icon2, icon3);
		locations.get(4).addWall(wall45);
		locations.get(4).addWall(wall11);
		locations.get(4).addWall(wall16);
		locations.get(4).addWall(wall12);
		locations.get(4).addWall(wall13);
		locations.get(4).addWall(wall14);

		locations.get(5).addWall(wall11);
		locations.get(5).addWall(wall16);
		locations.get(5).addWall(wall12);
		locations.get(5).addWall(wall13);
		locations.get(5).addWall(wall14);
		

	}

	public void addNPCs() {
		NPCMelee monster11 = new NPCMelee(1730, 800, 50);
		locations.get(0).addNPC(monster11);
		NPCMelee monster12 = new NPCMelee(1730, 750, 50);
		locations.get(0).addNPC(monster12);
		NPCMelee monster13 = new NPCMelee(100, 300, 50);
		locations.get(0).addNPC(monster13);
		NPCMelee monster14 = new NPCMelee(400, 600, 50);
		locations.get(0).addNPC(monster14);
		
		NPCMelee monster21 = new NPCMelee(80, 200, 50);
		locations.get(1).addNPC(monster21);
		NPCMelee monster22 = new NPCMelee(140, 200, 50);
		locations.get(1).addNPC(monster22);
		NPCMelee monster23 = new NPCMelee(900, 900, 50);
		locations.get(1).addNPC(monster23);
		NPCRanged monster24 = new NPCRanged(700, 500, 100);
		locations.get(1).addNPC(monster24);
		
		NPCRanged monster31 = new NPCRanged(80, 300, 100);
		locations.get(2).addNPC(monster31);
		NPCRanged monster32 = new NPCRanged(200, 200, 100);
		locations.get(2).addNPC(monster32);
		NPCMelee monster33 = new NPCMelee(1550, 100, 50);
		locations.get(2).addNPC(monster33);
		NPCMelee monster34 = new NPCMelee(1620, 100, 50);
		locations.get(2).addNPC(monster34);
		NPCMelee monster35 = new NPCMelee(1550, 300, 50);
		locations.get(2).addNPC(monster35);
		NPCMelee monster36 = new NPCMelee(1620, 300, 50);
		locations.get(2).addNPC(monster36);
		
		NPCMelee monster41 = new NPCMelee(1740, 100, 50);
		locations.get(3).addNPC(monster41);
		NPCMelee monster42 = new NPCMelee(1740, 200, 50);
		locations.get(3).addNPC(monster42);
		NPCMelee monster43 = new NPCMelee(1740, 300, 50);
		locations.get(3).addNPC(monster43);
		NPCMelee monster44 = new NPCMelee(1740, 700, 50);
		locations.get(3).addNPC(monster44);
		NPCMelee monster45 = new NPCMelee(1740, 800, 50);
		locations.get(3).addNPC(monster45);
		NPCMelee monster46 = new NPCMelee(1740, 900, 50);
		locations.get(3).addNPC(monster46);
		NPCMelee monster47 = new NPCMelee(1000, 100, 50);
		locations.get(3).addNPC(monster47);
		NPCMelee monster48 = new NPCMelee(1100, 100, 50);
		locations.get(3).addNPC(monster48);
		NPCMelee monster49 = new NPCMelee(1200, 100, 50);
		locations.get(3).addNPC(monster49);
		
		NPCMelee monster51 = new NPCMelee(80, 200, 50);
		locations.get(4).addNPC(monster51);
		NPCMelee monster52 = new NPCMelee(140, 200, 50);
		locations.get(4).addNPC(monster52);
		NPCRanged monster53 = new NPCRanged(200, 200,100);
		locations.get(4).addNPC(monster53);
		NPCMelee monster54 = new NPCMelee(1580, 200, 50);
		locations.get(4).addNPC(monster54);
		NPCMelee monster55 = new NPCMelee(1640, 200, 50);
		locations.get(4).addNPC(monster55);
		NPCRanged monster56 = new NPCRanged(1700, 200,100);
		locations.get(4).addNPC(monster56);
		NPCMelee monster57 = new NPCMelee(800, 900, 50);
		locations.get(4).addNPC(monster57);
		
		
		
		NPCBoss boss = new NPCBoss(1700, 600, 100);
		locations.get(5).addNPC(boss);
	}

	public void createRooms() {
		ImageIcon icon1 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room1 = new Location(icon1.getImage());
		locations.add(room1);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles2.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room2 = new Location(icon2.getImage());
		locations.add(room2);
		ImageIcon icon3 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles3.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room3 = new Location(icon3.getImage());
		locations.add(room3);
		ImageIcon icon4 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles4.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room4 = new Location(icon4.getImage());
		locations.add(room4);
		ImageIcon icon5 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles5.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room5 = new Location(icon5.getImage());
		locations.add(room5);
		ImageIcon icon6 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles6.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		Location room6 = new Location(icon6.getImage());
		locations.add(room6);
		player.setCurrentPosition(locations.get(5));

	}

	public void createKeybinds() {
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "MoveUp");
		gamingWindow.getActionMap().put("MoveUp", new MoveAction(1, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "MoveRight");
		gamingWindow.getActionMap().put("MoveRight", new MoveAction(2, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "MoveDown");
		gamingWindow.getActionMap().put("MoveDown", new MoveAction(3, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "MoveLeft");
		gamingWindow.getActionMap().put("MoveLeft", new MoveAction(4, player));

		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"),
				"stopUp");
		gamingWindow.getActionMap().put("stopUp", new StopAction(1, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"),
				"stopRight");
		gamingWindow.getActionMap().put("stopRight", new StopAction(2, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"),
				"stopDown");
		gamingWindow.getActionMap().put("stopDown", new StopAction(3, player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"),
				"stopLeft");
		gamingWindow.getActionMap().put("stopLeft", new StopAction(4, player));

		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "Attack");
		gamingWindow.getActionMap().put("Attack", new AttackAction(player));
		gamingWindow.getInputMap(gamingWindow.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "Swap");
		gamingWindow.getActionMap().put("Swap", new SwapAction(player));
	}
	public int getDeaths() {
		return this.deaths;
	}
	public void restartGame() {
		player = new Player(80,880);
		f.remove(gamingWindow);
		gamingWindow = new GamingWindow(player, menu);
		f.add(gamingWindow, BorderLayout.WEST);
		locations.clear();
		createRooms();
		addNPCs();
		addWalls();
		addItems();
		addDoors();
		createKeybinds();
	}
	public void Win() {
//		DEN HÄR KODEN SKA KÖRAS DÅ MAN DÖDAT BOSSEN

		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("highscores")));
			stream.writeObject(highscores);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

	public void run() {
		MeleeWeapon meleew = new MeleeWeapon(null, 0, 0, 0);
		RangedWeapon rangedw = new RangedWeapon(null, 0, 0, 0, 0);
		NPCMelee melee = new NPCMelee(0, 0, 0);
		while (true) {
			while (State == STATE.MENU) {
				gamingWindow.setVisible(false);
				menu.setVisible(true);
				menu.repaint();
			}

			while (State == STATE.HIGHSCORE) {
				highscore.repaint();
			}
			while (State == STATE.DEATHSCREEN) {
				deathScreen.repaint();
			}

			while (State == STATE.GAME) {
				gamingWindow.setVisible(true);
				player.cleanAllow();
				for (Wall w : player.getCurrentPosition().getWalls()) {
					player.checkCollision(w);
					player.getWeapon().checkCollide(w);
					if (player.getOtherWeapon() != null) {
						player.getOtherWeapon().checkCollide(w);
					}
					for (NPC mob : player.getCurrentPosition().getMobs()) {
						if (mob instanceof NPCRanged) {
							((NPCRanged) mob).checkCollide(w);
						}
					}
				}
				player.tick();
				if (player.getWeapon().getClass().equals(rangedw.getClass())) {
					for (Projectile proj : ((RangedWeapon) player.getWeapon()).getList()) {
						proj.moveDirection();
					}
				} else if (player.getOtherWeapon() != null && player.getOtherWeapon() instanceof RangedWeapon) {
					for (Projectile proj : ((RangedWeapon) player.getOtherWeapon()).getList()) {
						proj.moveDirection();
					}
				}
				if (player.getWeapon().getClass().equals(meleew.getClass())
						&& (((MeleeWeapon) player.getWeapon()).getProj()) != null) {
					((MeleeWeapon) player.getWeapon()).getProj().moveDirection();
				}
				for (NPC mob : player.getCurrentPosition().getMobs()) {
					mob.cleanAllow();
					for (Wall wall : player.getCurrentPosition().getWalls()) {
						mob.checkCollision(wall);

					}
					if (mob instanceof NPCBoss) {
						((NPCBoss) mob).checkCollide(player);
						if (((NPCBoss) mob).checkDead()) {
							Win();
							State = STATE.MENU;
							gamingWindow.setVisible(false);
							gamingWindow.repaint();
							menu.setVisible(true);
							menu.repaint();
						}
					}
					if (mob instanceof NPCRanged) {
						((NPCRanged) mob).attack();
						for (NPCProjectile proj : ((NPCRanged) mob).getFiredProj()) {
							proj.moveDirection();
						}
						((NPCRanged) mob).checkCollide(player);
					}
					mob.move(player);
					mob.tick();
					player.checkCollision(mob);
					player.getWeapon().checkCollide(mob);
					if (player.getOtherWeapon() != null) {
						player.getOtherWeapon().checkCollide(mob);
					}
				}
				for (Door door : player.getCurrentPosition().getDoors()) {
					player.checkCollision(door);
					player.getWeapon().checkCollide(door);
					if (player.getOtherWeapon() != null) {
						player.getOtherWeapon().checkCollide(door);
					}
					for (NPC mob : player.getCurrentPosition().getMobs()) {
						if (mob instanceof NPCRanged) {
							((NPCRanged) mob).checkCollide(door);
						}
					}
				}
				for (Item item : player.getCurrentPosition().getItems()) {
					player.checkCollision(item);
				}
				player.removePicked();
				player.getWeapon().checkRange();
				if (player.getOtherWeapon() != null) {
					player.getOtherWeapon().checkRange();
				}
				
				player.getCurrentPosition().checkDead();
				if (player.checkDead()) {
					deaths = deaths + 1;
					restartGame();
					State = STATE.DEATHSCREEN;
					gamingWindow.setVisible(false);
					gamingWindow.repaint();
					deathScreen.setVisible(true);
					deathScreen.repaint();
				}
				gamingWindow.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
