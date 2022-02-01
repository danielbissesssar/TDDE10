package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class NPCMelee extends NPC {
	public NPCMelee(int X, int Y, int damage) {
		super(X, Y, damage);
		this.setWidth(60);
		this.setHeight(60);
		ImageIcon icon = new ImageIcon(new ImageIcon("game_graphics\\\\meleemob.gif").getImage().getScaledInstance(this.getWidth(),
				this.getHeight(), Image.SCALE_DEFAULT));
		this.setImage(icon.getImage());

	}

	public void move(Player player) {
		int xDiff = this.getX() - player.getX();
		int yDiff = this.getY() - player.getY();
		Random random = new Random();
		int n = random.nextInt(4);
		n = n + 1;
		// satsen kollar ifall NPCn går mot spelaren och ifall detta inte är fallet så
		// slumpar den om
		// riktning i samma led som den redan skulle ha gått
		// den kommer alltså att gå mot spelaren 3/4 gånger av tiden
		switch (n) {
		case (1):
			if (xDiff >= 0) {
				n = random.nextInt(2);
				n = n + 1;
				moveMelee(n);
				break;
			} else {
				moveMelee(n);
				break;
			}
		case (2):
			if (xDiff <= 0) {
				n = random.nextInt(2);
				n = n + 1;
				moveMelee(n);
				break;
			} else {
				moveMelee(n);
				break;
			}
		case (3):
			if (yDiff >= 0) {
				n = random.nextInt(2);
				n = n + 3;
				moveMelee(n);
				break;
			} else {
				moveMelee(n);
				break;
			}
		case (4):
			if (yDiff <= 0) {
				n = random.nextInt(2);
				n = n + 3;
				moveMelee(n);
				break;
			} else {
				moveMelee(n);
				break;
			}
		}
	}

	private void moveMelee(int n) {
		switch (n) {
		case (1):
			this.setVelX(4);
			break;
		case (2):
			this.setVelX(-4);
			break;
		case (3):
			this.setVelY(4);
			break;
		case (4):
			this.setVelY(-4);
			break;

		}
	}

}
