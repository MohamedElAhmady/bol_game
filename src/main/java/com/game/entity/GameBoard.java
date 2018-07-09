package com.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.game.util.ListCursor;

public class GameBoard {
	
	private final List<String> messages = new ArrayList<String>();

	private final List<Pit> pits = new ArrayList<Pit>();

	private Player currentPlayer;

	private Player player1;

	private Player player2;

	public final int numberOfPits;

	public final int stonesPerPit;

	public GameBoard(int stonesPerPit, int numberOfPits) {
		this.stonesPerPit = stonesPerPit;
		this.numberOfPits = numberOfPits;
		initializeGameBoard();
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public void switchPlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	public void clearMessages() {
		messages.clear();
	}

	public int getNumberOfPits() {
		return numberOfPits;
	}

	public int getStonesPerPit() {
		return stonesPerPit;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public ListCursor<Pit> getPitCursor(int index) {
		return new ListCursor<Pit>(pits, index);
	}

	public List<String> getMessages() {
		return messages;
	}

	public List<Pit> getPits() {
		return pits;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	private void initializeGameBoard() {

		// initialize first player
		player1 = new Player("Player 1");
		for (int i = 0; i < numberOfPits; i++) {
			player1.getPits().add(new SmallPit(player1, stonesPerPit));
		}
		player1.setBigPit(new BigPit(player1, 0));
		pits.addAll(player1.getPits());
		pits.add(player1.getBigPit());

		// Initialize second player
		player2 = new Player("Player 2");
		for (int i = 0; i < numberOfPits; i++) {
			SmallPit secondPlayerPit = new SmallPit(player2, stonesPerPit);
			SmallPit firstPlayerPit = player1.getPits().get(numberOfPits - i - 1);
			secondPlayerPit.setOtherPlayerPit(firstPlayerPit);
			firstPlayerPit.setOtherPlayerPit(secondPlayerPit);
			player2.getPits().add(secondPlayerPit);
		}
		player2.setBigPit(new BigPit(player2, 0));
		pits.addAll(player2.getPits());
		pits.add(player2.getBigPit());

		messages.add("New Board");
		switchPlayer();
	}

}
