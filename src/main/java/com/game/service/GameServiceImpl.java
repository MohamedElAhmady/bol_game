package com.game.service;

import org.springframework.stereotype.Service;

import com.game.entity.SmallPit;
import com.game.util.ApplicationMessages;
import com.game.util.ListCursor;
import com.game.entity.Pit;
import com.game.entity.Player;
import com.game.entity.BigPit;
import com.game.entity.GameBoard;

@Service
public class GameServiceImpl implements GameSevice {

	// checks for the winner by counting the final number of stones in each big pit
	private Player checkForWinner(GameBoard board) {
		if (board.getPlayer1().getBigPit().getStones() > board.getPlayer2().getBigPit().getStones()) {
			board.addMessage(ApplicationMessages.PLAYER1_IS_THE_WINNER);
			return board.getPlayer1();
		} else if (board.getPlayer1().getBigPit().getStones() < board.getPlayer2().getBigPit().getStones()) {
			board.addMessage(ApplicationMessages.PLAYER2_IS_THE_WINNER);
			return board.getPlayer2();
		} else {
			board.addMessage(ApplicationMessages.RESULT_IS_DRAW);
			return null;
		}
	}

	@Override
	public void move(GameBoard board, int pitNumber) {
		ListCursor<Pit> cursor = board.getPitCursor(pitNumber - 1);

		// On a turn, the player removes all
		// seeds from one of his own pit. Moving counter-clockwise,
		// the player drops one stone in each pit in turn.

		Pit pit = board.getPits().get(pitNumber - 1);
		if (pit.getPlayer() != board.getCurrentPlayer()) {
			board.addMessage(ApplicationMessages.WRONG_PLAYER);
			return;
		}

		// I'm not sure if the player has the right to skip his turn by clicking on
		// empty pit or not
		// I supposed no so he must use non empty pits in his move and stop switching
		// players until he moves
		if (pit.isEmpty() && pit.getPlayer() == board.getCurrentPlayer()) {
			board.addMessage(ApplicationMessages.EMPTY_PIT);
			return;
		}
		board.clearMessages();

		int stones = pit.takeStones();
		for (int i = stones; i > 0; i--) {
			pit = cursor.next();
			if (pit instanceof BigPit && pit.getPlayer() != board.getCurrentPlayer()) {
				// and also for his pig pit not the other player big pit
				pit = cursor.next();
			}

			// If the last stone lands in an empty pit owned by the player, and the
			// opposite pit contains stones both the last stone and the opposite stones are
			// captured and put into the current player big pit.
			if (pit instanceof SmallPit && i == 1 && pit.isEmpty() && pit.getPlayer() == board.getCurrentPlayer()
					&& !((SmallPit) pit).getOtherPlayerPit().isEmpty()) {
				board.addMessage(board.getCurrentPlayer() + " captures "
						+ ((SmallPit) pit).getOtherPlayerPit().getStones() + " stones");
				board.getCurrentPlayer().getBigPit().addStones(1 + ((SmallPit) pit).getOtherPlayerPit().takeStones());

			} else {
				pit.addStone();
			}
		}

		// If the last stone
		// When all the small pits of one player are empty, the game
		// ends. The other player moves all remaining seeds to their big pit and the
		// player with larger number of stones in his big pit wins.

		int player1Stones = board.getPlayer1().countStones();
		int player2Stones = board.getPlayer2().countStones();
		if (player1Stones == 0) {
			board.getPlayer2().getBigPit().addStones(player2Stones);
			board.getPlayer2().getPits().forEach((player2pit) -> {
				player2pit.setStones(0);
			});

			checkForWinner(board);
		}
		if (player2Stones == 0) {
			board.getPlayer1().getBigPit().addStones(player1Stones);
			board.getPlayer2().getPits().forEach((player1pit) -> {
				player1pit.setStones(0);
			});
			checkForWinner(board);
		}

		// If the last stone comes into the current player big pit, the player gets
		// another round to play
		if (cursor.getCurrentElement() == board.getCurrentPlayer().getBigPit()) {
			board.addMessage(board.getCurrentPlayer() + ApplicationMessages.ANOTHER_ROUND);
		} else {
			board.switchPlayer();
		}
	}

}
