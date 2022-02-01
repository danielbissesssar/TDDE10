package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.MyFrame;
import logic.*;

public class GamingWindow extends JComponent {
	private Player player;
	Image background1;
	Image background2;
	Image background3;
	Image hpBar;

	private Dimension size = new Dimension(1920, 1080);
	private JComponent menu;

	public GamingWindow(Player player, JComponent menu) {
		this.menu = menu;
		this.player = player;
		setOpaque(true);
		setBackground(Color.RED);

		ImageIcon icon1 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles.png").getImage()
				.getScaledInstance(81, 93, Image.SCALE_DEFAULT));
		background1 = icon1.getImage();

		ImageIcon icon2 = new ImageIcon(new ImageIcon("game_graphics\\\\Tileset\\\\dungeon_tiles_water.png").getImage()
				.getScaledInstance(81, 80, Image.SCALE_DEFAULT));
		background2 = icon2.getImage();

		ImageIcon icon3 = new ImageIcon(
				new ImageIcon("game_graphics\\\\doge.gif").getImage().getScaledInstance(81, 80, Image.SCALE_DEFAULT));
		background3 = icon3.getImage();

		ImageIcon icon4 = new ImageIcon(
				new ImageIcon("game_graphics\\\\hp.png").getImage().getScaledInstance(360, 270, Image.SCALE_DEFAULT));
		hpBar = icon4.getImage();
		
		this.setSize(size.width, size.height);

	}

	public JComponent getWindow() {
		return this;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 14; j++) {
				g.drawImage(player.getCurrentPosition().getImage(), (i - 1) * 80, (j - 1) * 80, null);

			}
		}
//		g.drawImage(background2, (10 - 1) * 80, (10 - 1) * 80, null);
//		g.drawImage(background2, (11 - 1) * 80, (10 - 1) * 80, null);
//		g.drawImage(background2, (12 - 1) * 80, (10 - 1) * 80, null);
//
//		g.drawImage(background3, (10 - 1) * 80, (10 - 1) * 80, null);
		for (Wall wall : player.getCurrentPosition().getWalls()) {
			for (int i = 0; i <= wall.getWidth() / 80 - 1; i++) {
				for (int j = 0; j <= wall.getHeight() / 80 - 1; j++) {
					g.drawImage(wall.getImage(), wall.getX() + 80 * i, wall.getY() + 80 * j, null);
				}
			}
		}
		g.setColor(Color.RED);
		g.fillRect(160, 20, (262*player.getHP())/100, 40);
		g.drawImage(hpBar, 100, -80, null);
		
		
		for (Door door : player.getCurrentPosition().getDoors()) {
			g.drawImage(door.getImage(), door.getX(), door.getY(), null);
		}
		for (NPC mob : player.getCurrentPosition().getMobs()) {
			g.drawImage(mob.getImage(), mob.getX(), mob.getY(), null);
			if (mob instanceof NPCRanged) {
				for (NPCProjectile proj : ((NPCRanged) mob).getFiredProj()) {
					g.drawImage(proj.getImage(), proj.getX(), proj.getY(), null);
				}
			}
			if (mob instanceof NPCBoss) {
				for (Tornado tornado : ((NPCBoss) mob).getTornados()) {
					g.drawImage(tornado.getImage(), tornado.getX(), tornado.getY(), null);
				}
			}
		}
		for (Item item : player.getCurrentPosition().getItems()) {
			g.drawImage(item.getImage(), item.getX(), item.getY(), null);
		}
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		RangedWeapon ranged = new RangedWeapon(null, 0, 0, 0, 0);
		if (player.getWeapon().getClass().equals(ranged.getClass())) {
			for (Projectile proj : ((RangedWeapon) player.getWeapon()).getList()) {
				g.drawImage(proj.getImage(), proj.getX(), proj.getY(), null);
			}
		} else if (player.getOtherWeapon() != null && player.getOtherWeapon() instanceof RangedWeapon) {
			for (Projectile proj : ((RangedWeapon) player.getOtherWeapon()).getList()) {
				g.drawImage(proj.getImage(), proj.getX(), proj.getY(), null);
			}
		}
		MeleeWeapon melee = new MeleeWeapon(null, 0, 0, 0);
		if (player.getWeapon().getClass().equals(melee.getClass())
				&& (((MeleeWeapon) player.getWeapon()).getProj()) != null) {
			Rectangle r1;
			if (((MeleeWeapon) player.getWeapon()).getProj().getWidth() < 0) {
				r1 = new Rectangle(
						((MeleeWeapon) player.getWeapon()).getProj().getX()
								+ ((MeleeWeapon) player.getWeapon()).getProj().getWidth(),
						((MeleeWeapon) player.getWeapon()).getProj().getY(),
						-((MeleeWeapon) player.getWeapon()).getProj().getWidth(),
						((MeleeWeapon) player.getWeapon()).getProj().getHeight());
			} else if (((MeleeWeapon) player.getWeapon()).getProj().getHeight() < 0) {
				r1 = new Rectangle(((MeleeWeapon) player.getWeapon()).getProj().getX(),
						((MeleeWeapon) player.getWeapon()).getProj().getY()
								+ ((MeleeWeapon) player.getWeapon()).getProj().getHeight(),
						((MeleeWeapon) player.getWeapon()).getProj().getWidth(),
						-((MeleeWeapon) player.getWeapon()).getProj().getHeight());
			} else {
				r1 = new Rectangle(((MeleeWeapon) player.getWeapon()).getProj().getX(),
						((MeleeWeapon) player.getWeapon()).getProj().getY(),
						((MeleeWeapon) player.getWeapon()).getProj().getWidth(),
						((MeleeWeapon) player.getWeapon()).getProj().getHeight());

			}
			if (player.getFacing() == 1) {
				g.drawImage(((MeleeWeapon) player.getWeapon()).getProj().getImage(),
						((MeleeWeapon) player.getWeapon()).getProj().getX(),
						((MeleeWeapon) player.getWeapon()).getProj().getY()
								+ ((MeleeWeapon) player.getWeapon()).getProj().getHeight(),
						null);
			} else if (player.getFacing() == 4) {
				g.drawImage(((MeleeWeapon) player.getWeapon()).getProj().getImage(),
						((MeleeWeapon) player.getWeapon()).getProj().getX()
								+ ((MeleeWeapon) player.getWeapon()).getProj().getWidth(),
						((MeleeWeapon) player.getWeapon()).getProj().getY(), null);
			} else {
				g.drawImage(((MeleeWeapon) player.getWeapon()).getProj().getImage(),
						((MeleeWeapon) player.getWeapon()).getProj().getX(),
						((MeleeWeapon) player.getWeapon()).getProj().getY(), null);
			}
		}

	}
}
