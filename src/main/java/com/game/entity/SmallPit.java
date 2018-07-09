package com.game.entity;

public class SmallPit extends Pit {
	private SmallPit otherPlayerPit;

	public SmallPit(Player player, int stonesPerPit) {
		super(player, stonesPerPit);
	}

	public SmallPit getOtherPlayerPit() {
		return otherPlayerPit;
	}

	public void setOtherPlayerPit(SmallPit otherPlayerPit) {
		this.otherPlayerPit = otherPlayerPit;
	}
}
