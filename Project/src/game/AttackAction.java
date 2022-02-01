package game;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import logic.*;
public class AttackAction extends AbstractAction {
	private Player player;
	private long fired =0L;
	
	public AttackAction(Player player) {
		this.player=player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (System.currentTimeMillis() - this.fired > player.getWeapon().getSpeed()) {
		player.attack();
		this.fired=System.currentTimeMillis();
		}
	}

}
