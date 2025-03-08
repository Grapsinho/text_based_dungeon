package com.room_app;

import java.util.Scanner;

import com.player_app.*;

public class TrapRoom extends Room {

    private final String[] riddles = {
        "I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?",
        "What has keys but can't open locks?",
        "The more of this there is, the less you see. What is it?",
        "I can be cracked, I can be made, I can be told, I can be played. What am I?"
    };

    private final String[] riddle_answers = {"echo", "piano", "darkness", "joke"};    
    
    public TrapRoom() {
        super("You cautiously step into a narrow corridor... Suddenly, a trap is triggered!");
    }

    @Override
    public void enterRoom(Player player, Scanner scanner) {
        System.out.println(description);

        int randomNumber = (int) (Math.random() * riddles.length);
        String randomRiddle = riddles[randomNumber];
        String riddleAnswer = riddle_answers[randomNumber];
        
        System.out.println("You can avoid it if you can answer this riddle: \n");
        System.out.println("Riddle: " + randomRiddle);

        String userAnswer = scanner.nextLine();

        if (userAnswer.equalsIgnoreCase(riddleAnswer)) {
            System.out.println("Answer is correct! Congratulations you successfully avoided trap");

            player.showStats();
        } else {
            System.out.println("\nWrong Answer!");
            int trapDamage = 20;
            System.out.println("A hidden arrow strikes you! You lose " + trapDamage + " health.");
            player.health -= trapDamage;
            player.showStats();
        }
    }
}