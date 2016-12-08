/**
 * @author Álex Hernández - www.github.com/alexhzr
 */

package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;

public class Napakalaki {
    private static final Napakalaki instance = new Napakalaki();
    private Player currentPlayer = null;
    private ArrayList<Player> players;
    private CardDealer dealer;
    private Monster currentMonster = null;
    
    private Napakalaki() {
        dealer = CardDealer.getInstance();
    }
    
    private void initPLayers(ArrayList<String> names) {
        players = new ArrayList();
        
        for (String name: names) 
            players.add(new Player(name));
    }
    
    private Player nextPlayer() {
        if (currentPlayer == null) {
            int firstPlayer = (int) (Math.random() * (players.size() - 1) + 0);
            currentPlayer = players.get(firstPlayer);
        } else {
            int pos = players.indexOf(currentPlayer);
            
            if (pos + 1 < players.size())
                currentPlayer = players.get(pos + 1);
            
            else 
                currentPlayer = players.get(0);
        }   
        
        return currentPlayer;
    }
    
    private boolean nextTurnAllowed() {
        if (currentPlayer == null)
            return true;
        
        else {
            return currentPlayer.validState();
        }
    }
    
    private void setEnemies() {
        int index;
        Player enemyPlayer;
        
        for (Player p: players) {
            do {
                index = (int) (Math.random() * players.size() + 0);
                enemyPlayer = players.get(index);
            } while (enemyPlayer == p);
            
            p.setEnemyPlayer(p);
        }
    }
    
    public static Napakalaki getInstance() {
        return instance;
    }
    
    public CombatResult developCombat() {
        return currentPlayer.combat(currentMonster);
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {
        for (Treasure treasure: treasures) {
            currentPlayer.discardVisibleTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for (Treasure treasure: treasures) {
            currentPlayer.discardHiddenTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures) {
        Iterator<Treasure> it = treasures.iterator();
        Treasure t;
        
        while (it.hasNext()) {
            t = it.next();
            currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public void initGame(ArrayList<String> names) {
        initPLayers(names);
        setEnemies();
        dealer.initCards();
        nextTurn();
        
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Monster getCurrentMonster() {
        return currentMonster;
    }
    
    public boolean nextTurn() {
        boolean stateOK = nextTurnAllowed();
        
        if (stateOK) {
            currentMonster = dealer.nextMonster();
            currentPlayer = nextPlayer();
            boolean dead = currentPlayer.isDead();
            
            if (dead)
                currentPlayer.initTreasures();
        }
        
        return stateOK;
    }
    
    public boolean endOfGame(CombatResult result) {
        if (result == CombatResult.WINGAME)
            return true;
        
        else return false;
    }
}
