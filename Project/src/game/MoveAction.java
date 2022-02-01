package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import logic.*;

public class MoveAction extends AbstractAction {
	private int direction;
	private Player player;
	
	MoveAction(int direction, Player player) {
		this.direction = direction;
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		player.moveDir(direction);
	}
}
