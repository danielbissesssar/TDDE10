package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import game.Game;
import game.MenuBtn;
import logic.Player;

public class DeathScreen extends JComponent {
	private Game game;
	Image background;
	Image background3;
	private Dimension size = new Dimension(1920 ,1080);
	private JComponent menu;
	public DeathScreen(Game game, JComponent menu) {
		this.menu = menu;
		this.game = game;
		setOpaque(true);
		this.setSize(size);
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\deathscreen.jpg").getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT));
		background = icon.getImage();
		MenuBtn btnPlay = new MenuBtn(this, menu, "PLAY AGAIN", Game.State.GAME, 325, 760, 756);
		this.add(btnPlay);
		MenuBtn btnMenu = new MenuBtn(this, menu, "MAIN MENU", Game.State.MENU, 325, 760, 856);
		this.add(btnMenu);

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font fnt1 = new Font("verdana", Font.BOLD, 70);
		g.setColor(Color.RED);
		g.setFont(fnt1);
		g.drawImage(background, 0, 0, null);
		g.drawString("DEATHS: " + game.getDeaths() , 730, 70);

	}

}
