package com.game.entity;

public class Pit {

	private final Player player;
	private int stones;

	public Pit(Player player, int stonesPerPit) {
		this.player = player;
		this.stones = stonesPerPit;
	}

	public int getStones() {
		return stones;
	}

	public int takeStones() {
		int currentStones = stones;
		stones = 0;
		return currentStones;
	}

	public void setStones(int stones) {
		this.stones = stones;
	}

	public Player getPlayer() {
		return player;
	}

	public void addStone() {
		stones++;
	}

	public void addStones(int newStones) {
		stones += newStones;
	}

	public boolean isEmpty() {
		return stones == 0;
	}
}
