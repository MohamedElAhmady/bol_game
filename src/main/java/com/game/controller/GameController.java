package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.game.entity.GameBoard;
import com.game.service.GameSevice;

@Controller
@SessionAttributes("board")
public class GameController {

	@Autowired
	private GameSevice gameService;

	@RequestMapping(value = "/newGame")
	public final String newGame(ModelMap model) throws Exception {
		model.addAttribute("board", new GameBoard(6, 6));
		return "game";
	}

	@ModelAttribute("board")
	public GameBoard initializeBoard() {
		return new GameBoard(6, 6);
	}

	@RequestMapping("/play")
	public String welcome(@ModelAttribute("board") GameBoard board) {
		return "game";
	}

	@RequestMapping("/play/move/{pitNumber}")
	public String go(@PathVariable int pitNumber, @ModelAttribute("board") GameBoard board) {
		gameService.move(board, pitNumber);
		return "redirect:/play";
	}

}