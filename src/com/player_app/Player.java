package com.player_app;

import java.util.ArrayList;

// The Player class holds the player's stats and inventory.
public class Player {
    public byte health;
    public ArrayList<String> inventory;
    private final byte MaxHealth = 100;
    public int power;
    public byte swordLvl;

    public Player() {
        this.health = MaxHealth;
        this.inventory = new ArrayList<>();
        this.power = 10;
        this.swordLvl = 1;
    }

    // A method to display the player's current stats.
    public void showStats() {
        System.out.println("Player Health: " + health);
        System.out.println("Inventory: " + inventory);
    }
}