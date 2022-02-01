package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import game.Game.STATE;
import game.*;

public class MenuBtn extends JComponent implements MouseListener {
	private JComponent windowCurrent;
	private JComponent windowNext;
	private Dimension size;
	private Game game;
	private String name;
	private STATE s;
	private int x;
	private int y;

	public MenuBtn(JComponent jp1, JComponent jp2, String na, STATE s, int w, int x, int y) {
		super();
		this.windowCurrent = jp1;
		this.windowNext = jp2;
		this.name = na;
		this.s = s;
		this.x = x;
		this.y = y;
		this.size = new Dimension(w, 50);
		System.out.println(na);
		enableInputMethods(true);
		addMouseListener(this);
		setSize(size.width, size.height);
		this.setLocation(x, y);
//		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(Color.RED);
//		g.fillRect(0, 0, size.width, size.height);
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setColor(Color.RED);
		g.setFont(fnt1);
		g.drawString(name, 10, 43);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("KLICKETY KLACK");

		// Ändrar state och "plockar" bort menyn
		Game.State = s;
		windowCurrent.setVisible(false);
		windowCurrent.repaint();
		if (windowNext != null) {
		windowNext.setVisible(true);
		windowNext.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

}