package com.enemy_app;

// The Enemy class represents an enemy with a name, health, and damage.
public class Enemy {
    public String name;
    public int health;
    public int damage;

    // Constructor: initializes the enemy's name, health, and damage.
    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
}
