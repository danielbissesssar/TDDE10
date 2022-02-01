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

public class Menu extends JComponent{
	private Player player;
	Image background;
	private Dimension size = new Dimension(1920,1080);	
	private JComponent highscore;
	private JComponent game;
	public Menu(Player player, JComponent hs, JComponent g) {
		this.highscore = hs;
		this.game = g;
		this.player = player;
		setOpaque(true);
//		setBackground(Color.BLACK);

		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\hld_vid_30.gif").getImage().getScaledInstance(1920,
				1080, Image.SCALE_DEFAULT));
		background = icon.getImage();
		
//		Border border;
//		border = BorderFactory.createLineBorder(Color.black);
		this.setSize(size);
//		BoxLayout boxlayoutY = new BoxLayout(this, BoxLayout.Y_AXIS);
		

		MenuBtn btnStart = new MenuBtn(this, game, "START", Game.State.GAME, 200, 100, 300);
		MenuBtn btnHighscore = new MenuBtn(this, highscore, "HIGHSCORE", Game.State.HIGHSCORE, 325, 100, 400);
		this.add(btnStart);
		this.add(btnHighscore);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font fnt1 = new Font("verdana", Font.BOLD, 70);
		g.setColor(Color.MAGENTA);
		g.setFont(fnt1);
		g.drawImage(background, 0, 0, null);
		g.drawString("KNASKALAS", 730, 70);
	}
}
