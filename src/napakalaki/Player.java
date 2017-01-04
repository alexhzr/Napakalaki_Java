/**
 * @author Álex Hernández - www.github.com/alexhzr
 */

package napakalaki;

import java.util.ArrayList;

public class Player {
    public static final int MAXLEVEL = 10;
    private String name;
    private int level;
    private boolean dead = true;
    private boolean canISteal = true;
    private BadConsequence pendingBadConsequence;
    private ArrayList<Treasure> hiddenTreasures;
    private ArrayList<Treasure> visibleTreasures;
    private Player enemy;
    
    public Player(String name) { 
        this.name = name;
        level = 0;
        dead = true;
        pendingBadConsequence = null;
        hiddenTreasures = new ArrayList();
        visibleTreasures = new ArrayList();
        enemy = null;
    } 
    
    public String getName() { 
        return name;
    } 
    
    private void bringToLife() {
        if (dead)
            dead = false;
        else
            System.out.println("Player already alive!");
    }
    
    private int getCombatLevel() { 
        int cLevel = level;
        for(int i = 0; i < visibleTreasures.size(); i++)
            cLevel += visibleTreasures.get(i).getBonus();
        
        return cLevel;
    } 
    
    private void incrementLevels(int I) {
        level += I;
    }
    
    private void decrementLevels(int I) {
        if (level - I <= 0)
            level = 0;
        else level -= I;
    }
    
    private void setPendingBadConsequence(BadConsequence b) {
        pendingBadConsequence = b;
    }
    
    private void applyPrize(Monster m) {
        int nLevels = m.getLevelsGained();
        incrementLevels(nLevels);
        int nTreasures = m.getTreasuresGained();
        
        if (nTreasures > 0) {
            CardDealer dealer = CardDealer.getInstance();
            Treasure t;
            
            for (int i = 0; i < nTreasures; i++) {
                t = dealer.nextTreasure();
                hiddenTreasures.add(t);
            }
        }
    }
    
    private void applyBadConsequence(Monster m) {
        BadConsequence badConsequence = m.getBadConsecuence();
        int nLevels = badConsequence.getLevels();
        decrementLevels(nLevels);
        setPendingBadConsequence(badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures));
    }
    
    private boolean canMakeTreasureVisible(Treasure t) {
        TreasureKind type = t.getType();
        
        // If treasure's kind is both hands, it means you won't be
        // able to equip any hand treasure
        if (type == TreasureKind.BOTHHANDS) {
            for (Treasure tr: visibleTreasures) {
                if (tr.getType() == TreasureKind.BOTHHANDS || tr.getType() == TreasureKind.ONEHAND) 
                    return false;
            }
            
            return true;
        }
        
        // If treasure's kind is one hand, you can only equip another
        // 'one hand' treasure
        else if (type == TreasureKind.ONEHAND) {
            for (Treasure tr: visibleTreasures) {
                if (tr.getType() == TreasureKind.BOTHHANDS)
                    return false;
            }
            
            return true;
        } 
        
        // Finally, for the rest of treasure kinds, you can only equip
        // the treasures you have not equipped at the moment
        else {
            for (Treasure tr: visibleTreasures) {
                if (tr.getType() == t.getType())
                    return false;
            }
            
            return true;
        }

    }
    
    private int howManyVisibleTreasures(TreasureKind tKind) {
        int counter = 0;
        
        for (int i = 0; i < visibleTreasures.size(); i++)
            if (visibleTreasures.get(i).getType() == tKind)
                counter++;
        
        return counter;
    }
    
    private void dieIfNoTreasures() {
        if (hiddenTreasures.isEmpty() && visibleTreasures.isEmpty())
            dead = true;
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public ArrayList<Treasure> getHiddenTreasures() {
        return hiddenTreasures;
    }
    
    public ArrayList<Treasure> getVisibleTreasures() {
        return visibleTreasures;
    }
    
    public CombatResult combat(Monster m) {
        int myLevel = getCombatLevel();
        int monsterLevel = m.getCombatLevel();
        CombatResult cr;
        
        if (!canISteal) {
            Dice dice = Dice.getInstance();
            int number = dice.nextNumber();
            
            if (number < 3) {
                int enemyLevel = enemy.getCombatLevel();
                monsterLevel += enemyLevel;
            }
        }
        
        if (myLevel > monsterLevel) {
            applyPrize(m);
            
            if (level >= MAXLEVEL)
                cr = CombatResult.WINGAME;
            else
                cr = CombatResult.WIN;
            
        } else {
            applyBadConsequence(m);
            cr = CombatResult.LOSE;
        }
        
        return cr;
    }
    
    public void makeTreasureVisible(Treasure t) {
        boolean canI = canMakeTreasureVisible(t);
        
        if (canI) {
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }
 
    public void discardVisibleTreasure(Treasure t) {
        visibleTreasures.remove(t);
        
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractVisibleTreasure(t);
        
        dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t) {
    hiddenTreasures.remove(t);
        
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractHiddenTreasure(t);
        
        dieIfNoTreasures();
    }
    
    public boolean validState() {
        if ((pendingBadConsequence == null || pendingBadConsequence.isEmpty())
            && hiddenTreasures.size() < 4)
            return true;
        else
            return false;        
    }
    
    public void initTreasures() {
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        int number;
        
        bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        number = dice.nextNumber();
        
        if (number > 1) {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        
        if (number == 6) {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }
    
    public int getLevels() {
        return level;
    }
    
    public Treasure stealTreasure() {
        boolean canI = canISteal();
        Treasure treasure = null;
        
        if (canI) {
            boolean canYou = enemy.canYouGiveMeATreasure();
            
            if (canYou) {
                treasure = enemy.giveMeATreasure();
                hiddenTreasures.add(treasure);
                haveStolen();
            }
        }
        
        return treasure;
    }
    
    public void setEnemyPlayer(Player enemy) {
        this.enemy = enemy;
    }
    
    private Treasure giveMeATreasure() {
        int i = (int) (Math.random() * hiddenTreasures.size() + 0);
        Treasure treasure = hiddenTreasures.get(i);
        hiddenTreasures.remove(treasure);
        
        return treasure;
    }
    
    public boolean canISteal() {
        return canISteal;
    }
    
    private boolean canYouGiveMeATreasure() {
        if (hiddenTreasures.size() > 0)
            return true;
        else
            return false;
    }
    
    private void haveStolen() {
        canISteal = false;
    }
    
    public void discardAllTreasures() {
        ArrayList<Treasure> visibleAux = new ArrayList(visibleTreasures);
        ArrayList<Treasure> hiddenAux  = new ArrayList(hiddenTreasures);
        
        for (Treasure t: visibleAux) 
            discardVisibleTreasure(t);
        
        for (Treasure t: hiddenAux)
            discardHiddenTreasure(t);
    }
    
    public String toString() {
        return this.name;
    }
}