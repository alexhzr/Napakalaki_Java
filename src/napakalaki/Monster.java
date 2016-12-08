/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author alexhernandez
 */
public class Monster {
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsecuence;

    public Monster(String name, int combatLevel, Prize prize, BadConsequence badConsecuence) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.prize = prize;
        this.badConsecuence = badConsecuence;
    }

    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public Prize getPrize() {
        return prize;
    }

    public BadConsequence getBadConsecuence() {
        return badConsecuence;
    }
    
    public String toString() {
        return "Name: " + name + " || Level: " + Integer.toString(combatLevel) + " || Bad consequence:\n" + badConsecuence;
    }
    
    public int getLevelsGained() {
        return prize.getLevel();
    }
    
    public int getTreasuresGained() {
        return prize.getTreasures();
    }
}
