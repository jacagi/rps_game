package com.jacagi.rockpaperscissors.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.enums.RockPaperScissorsEnum;

public class GameMovesUtil {

	public static GameMovesDTO getRandomMove() {
		List<RockPaperScissorsEnum> rpsList = Arrays.asList(RockPaperScissorsEnum.values());
        return new GameMovesDTO(rpsList.get(ThreadLocalRandom.current().nextInt(rpsList.size())).name());
	}
	
	public static String checkIfWin(String humanMove, String computerMove) {
		if(humanMove.equals(computerMove)) {
			return "DRAW";
		}
		
		switch (humanMove) {
            case "ROCK":
                return (computerMove == "SCISSORS") ? "WIN" : "LOSE";
            case "PAPER":
                return (computerMove == "ROCK") ? "WIN" : "LOSE";
            case "SCISSORS":
                return (computerMove == "PAPER") ? "WIN" : "LOSE";
            default:
                return "Invalid move!";
        }
	}
}
