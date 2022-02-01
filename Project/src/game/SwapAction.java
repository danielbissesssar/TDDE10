package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import logic.Player;

public class SwapAction extends AbstractAction {
	private Player player;
	
	public SwapAction(Player player) {
		this.player=player;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.swapWeapon();
		
	}

}
