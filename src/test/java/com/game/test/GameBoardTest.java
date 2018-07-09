package com.game.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.game.entity.GameBoard;

public class GameBoardTest {

	@Test
	public void testPitsFor4x6Board() {
		GameBoard board = new GameBoard(4, 6);
		int sum = board.getPits().stream().mapToInt((h) -> h.getStones()).sum();
		assertEquals("All Stones", 48, sum);
	}

	@Test
	public void testPitsFor6x6Board() {
		GameBoard board = new GameBoard(6, 6);
		int sum = board.getPits().stream().mapToInt((h) -> h.getStones()).sum();
		assertEquals("All Stones", 72, sum);
	}
}
