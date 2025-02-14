package com.jacagi.rockpaperscissors_producer.enums;

import java.util.Arrays;


public enum RockPaperScissorsEnum {

	ROCK, PAPER, SCISSORS;
	
	public static boolean isValidMove(String input) {
        return Arrays.stream(RockPaperScissorsEnum.values())
                .anyMatch(move -> move.name().equalsIgnoreCase(input));
    }
}
