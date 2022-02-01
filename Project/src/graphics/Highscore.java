package graphics;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import game.Game;
import game.MenuBtn;
import logic.Player;

public class Highscore extends JComponent{
	private Player player;
	private Image background;
	private Image background3;
	private Dimension size = new Dimension(1920 ,1080);
	private JComponent menu;
	private int[] highscores = new int[10]; 
	
	public Highscore(Player player, int[] highscores, JComponent menu) {
		this.menu = menu;
		this.highscores = highscores;
		this.player = player;
		setOpaque(true);
		this.setSize(size);
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\highscore.gif").getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT));
		background = icon.getImage();
		ImageIcon icon3 = new ImageIcon(new ImageIcon("game_graphics\\\\doge.gif").getImage()
				.getScaledInstance(81, 80, Image.SCALE_DEFAULT));
		background3 = icon3.getImage();
		MenuBtn btnMenu = new MenuBtn(this, menu, "MENU", Game.State.MENU, 200, 50, 300);
		this.add(btnMenu);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		
		Font fnt1 = new Font("verdana", Font.BOLD, 70);
		g.setColor(Color.MAGENTA);
		g.setFont(fnt1);
		g.drawImage(background, 0, 0, null);
		g.drawString("HIGHSCORE", 730, 70);
		g.drawImage(background3, 975, 395, null);
		
		Font fnt2 = new Font("verdana", Font.BOLD, 40);
		g.setFont(fnt2);
		for (int i = 0; i < 10; i++) {
			g.drawString(i+".      "+highscores[i] + " seconds", 730, 170+i*80);
		}
 

	}
}
