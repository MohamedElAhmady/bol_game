package com.game.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int stones;

	private final String name;

	private BigPit bigPit;

	private final List<SmallPit> pits = new ArrayList<SmallPit>();

	public Player(String name) {
		this.name = name;
	}

	/**
	 * Count of all stones in pits
	 */
	public int countStones() {
		return pits.stream().mapToInt((h) -> h.getStones()).sum();
	}

	public List<SmallPit> getPits() {
		return pits;
	}

	public String getName() {
		return name;
	}

	public int getStones() {
		return stones;
	}

	public BigPit getBigPit() {
		return bigPit;
	}

	public void setStones(int stones) {
		this.stones = stones;
	}

	public void setBigPit(BigPit bigPit) {
		this.bigPit = bigPit;
	}

	@Override
	public String toString() {
		return name;
	}

}
