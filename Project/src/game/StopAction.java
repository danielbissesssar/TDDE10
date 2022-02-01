package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import logic.Player;

public class StopAction extends AbstractAction {
	private int direction;
	private Player player;
	
	StopAction(int direction, Player player) {
		this.direction = direction;
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		player.stopDir(direction);
	}
}
