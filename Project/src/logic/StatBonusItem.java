package logic;

import java.awt.Image;

public class StatBonusItem extends Item {
	private int meleeRange;
	private int rangedRange;
	private int meleeDmg;
	private int rangedDmg;
	private double rangedSpeed;
	
	public StatBonusItem(Image image, int meleeRange, int rangedRange, int meleeDmg, int rangedDmg, double rangedSpeed) {
		super(image);
		this.meleeRange=meleeRange;
		this.rangedRange=rangedRange;
		this.meleeDmg=meleeDmg;
		this.rangedDmg=rangedDmg;
		this.rangedSpeed=rangedSpeed;
	}
	
	public void equip(Player player) {
		if (player.getOtherWeapon() != null) {
		if (player.getWeapon() instanceof MeleeWeapon) {
			player.getWeapon().setDmg(meleeDmg + player.getWeapon().getdmg());
			player.getWeapon().setRange(meleeRange + player.getWeapon().getRange());
			player.getOtherWeapon().setDmg(rangedDmg + player.getOtherWeapon().getdmg());
			player.getOtherWeapon().setRange(rangedRange + player.getOtherWeapon().getRange());
			player.getOtherWeapon().setSpeed(rangedSpeed + player.getOtherWeapon().getSpeed());
		} else {
			player.getWeapon().setDmg(rangedDmg + player.getWeapon().getdmg());
			player.getWeapon().setRange(rangedRange + player.getWeapon().getRange());
			player.getWeapon().setSpeed(rangedSpeed + player.getWeapon().getRange());
			player.getOtherWeapon().setDmg(meleeDmg + player.getOtherWeapon().getdmg());
			player.getOtherWeapon().setRange(meleeRange + player.getOtherWeapon().getRange());
		}
		} else {
			player.getWeapon().setDmg(meleeDmg + player.getWeapon().getdmg());
			player.getWeapon().setRange(meleeRange + player.getWeapon().getRange());
		}
		
		
	}

}
