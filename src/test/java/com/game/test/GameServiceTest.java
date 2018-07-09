package com.game.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.entity.GameBoard;
import com.game.entity.Pit;
import com.game.service.GameServiceImpl;
import com.game.util.ApplicationMessages;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
	
	@InjectMocks
	private GameServiceImpl gameService;

	@Test
	public void testPlayer1Wins() {
		GameBoard board = new GameBoard(6, 6);
		arrayToBoard(new int[] { 0, 0, 0, 0, 0, 1, 30, 0, 1, 1, 1, 1, 1, 16 }, board);

        gameService.move(board, 6);

		assertEquals(ApplicationMessages.PLAYER1_IS_THE_WINNER, board.getMessages().get(0));
		assertArrayEquals("Result", new int[] { 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0, 21 }, boardToArray(board));
	}

	@Test
	public void testPlayer2Wins() {
		GameBoard board = new GameBoard(6, 6);
		arrayToBoard(new int[] { 0, 0, 0, 0, 0, 1, 16, 0, 1, 1, 1, 1, 1, 16 }, board);
		gameService.move(board, 6);
		assertEquals("Message", ApplicationMessages.PLAYER2_IS_THE_WINNER, board.getMessages().get(0));
		assertArrayEquals("Result", new int[] { 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 21 }, boardToArray(board));
	}

	@Test
	public void testPlayer1GetsAnotherRound() {
		GameBoard board = new GameBoard(6, 6);
		arrayToBoard(new int[] { 0, 0, 1, 0, 0, 1, 16, 0, 1, 1, 1, 1, 1, 16 }, board);
		gameService.move(board, 6);
		assertEquals("Message", "Player 1" + ApplicationMessages.ANOTHER_ROUND, board.getMessages().get(0));
		assertArrayEquals("Result", new int[] { 0, 0, 1, 0, 0, 0, 17, 0, 1, 1, 1, 1, 1, 16 }, boardToArray(board));
	}

	// Convert the count of stones to an array, use to verify board state
	private int[] boardToArray(GameBoard board) {
		int[] stonesArray = new int[board.getPits().size()];
		for (int i = 0; i < stonesArray.length; i++) {
			stonesArray[i] = board.getPits().get(i).getStones();
		}
		return stonesArray;
	}

	private void arrayToBoard(int[] stones, GameBoard board) {
		Iterator<Pit> pits = board.getPits().iterator();
		for (int stone : stones) {
			pits.next().setStones(stone);
		}
	}
}
