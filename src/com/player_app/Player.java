package com.player_app;

import java.util.ArrayList;

// The Player class holds the player's stats and inventory.
public class Player {
    int health;
    ArrayList<String> inventory;
    private final int MaxHealth = 100;

    public Player() {
        this.health = MaxHealth;
        this.inventory = new ArrayList<>();
    }

    // A method to display the player's current stats.
    public void showStats() {
        System.out.println("Player Health: " + health);
        System.out.println("Inventory: " + inventory);
    }
}