/**
 * @author Álex Hernández - www.github.com/alexhzr
 */

package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;

public class BadConsequence {
    static final int MAX_TREASURES = 20;
    private String text;
    private int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificHiddenTreasures;
    private ArrayList<TreasureKind> specificVisibleTreasures;

    public BadConsequence(String text, int levels, int nVisibleTreasures, int nHiddenTreasures) {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
        specificHiddenTreasures = new ArrayList();
        specificVisibleTreasures = new ArrayList();
    }

    public BadConsequence(String text, boolean death) {
        this.text = text;
        this.death = death;
        levels = 0;
        specificHiddenTreasures = new ArrayList();
        specificVisibleTreasures = new ArrayList();
    }
    
    public BadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        specificHiddenTreasures = new ArrayList();
        specificVisibleTreasures = new ArrayList();
        nVisibleTreasures = 0;
        nHiddenTreasures = 0;
        
        this.text = text;
        this.levels = levels;
        specificVisibleTreasures = tVisible;
        specificHiddenTreasures = tHidden;
    }
    
    public String toString() {
        String sVisibleTreasures;
        String sHiddenTreasures;
        
        if (specificHiddenTreasures.isEmpty()) {
            sHiddenTreasures = " None";
        } else {
            sHiddenTreasures = "";
            
            for (TreasureKind tk: specificHiddenTreasures)
                sHiddenTreasures += tk + ", ";
            
            sHiddenTreasures = sHiddenTreasures.substring(0, sHiddenTreasures.length()-2) + "";
        }
        
        if (specificVisibleTreasures.isEmpty()) {
            sVisibleTreasures = " None";
        } else {
            sVisibleTreasures = "\n";
            
            for (TreasureKind tk: specificVisibleTreasures)
                sVisibleTreasures += tk + ", ";
            
            sVisibleTreasures = sVisibleTreasures.substring(0, sVisibleTreasures.length()-1) + "\n";
        }
        
        return "Text: " + text + " || Levels: " + Integer.toString(levels) + " || Nº of hidden treasures: " + Integer.toString(nHiddenTreasures)
                + " || Nº of visible treasures: " + Integer.toString(nVisibleTreasures) + " || Specific hidden treasures:" + sHiddenTreasures
                + " || Specific visible treasures:" + sVisibleTreasures;
    }
    
    public int getLevels() {
        return this.levels;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }

    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }

    public int getnHiddenTreasures() {
        return nHiddenTreasures;
    }
    
    public boolean isEmpty() {
        if (nHiddenTreasures == 0
            && nVisibleTreasures == 0
            && specificHiddenTreasures.isEmpty()
            && specificVisibleTreasures.isEmpty())
            return true;
        else 
            return false;
    }
    
    public void substractVisibleTreasure(Treasure t) {
        
        // Treasures in BadConsequence are specific (array)
        if (nVisibleTreasures == 0 && nHiddenTreasures == 0) {
            TreasureKind treasureKind;
            boolean found = false;
            Iterator<TreasureKind> it = specificVisibleTreasures.iterator();
            
            while (!found && it.hasNext()) {
                treasureKind = it.next();
                
                if (treasureKind == t.getType()) {
                    specificVisibleTreasures.remove(treasureKind);
                    found = true;
                }
            }
        }
        
        // Treasures in BadConsequence are non specific (number of)
        else {
            nVisibleTreasures--;
        }
    }
    
    public void substractHiddenTreasure(Treasure t) {
        // Treasures in BadConsequence are specific (array)
        if (nHiddenTreasures == 0 && nVisibleTreasures == 0) {
            TreasureKind treasureKind;
            boolean found = false;
            Iterator<TreasureKind> it = specificHiddenTreasures.iterator();
            
            while (!found && it.hasNext()) {
                treasureKind = it.next();
                if (treasureKind == t.getType()) {
                    specificHiddenTreasures.remove(treasureKind);
                    found = true;
                }
            }
        }
        
        // Treasures in BadConsequence are non specific (number of)
        else {
            nHiddenTreasures--;
        }
    }
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence fittedBadConsequence;
        int fittedNVisibles = 0;
        int fittedNHidden = 0;
        ArrayList<TreasureKind> fittedVisible = new ArrayList();
        ArrayList<TreasureKind> fittedHidden = new ArrayList();
        
        // Treasures in BadConsequence are specific (array)
        if (nHiddenTreasures == 0 && nVisibleTreasures == 0) {
            
            for (Treasure t: v)
                if (specificVisibleTreasures.contains(t.getType()))
                    fittedVisible.add(t.getType());
            
            for (Treasure t: h) 
                if (specificHiddenTreasures.contains(t.getType()))
                    fittedHidden.add(t.getType());
            
            fittedBadConsequence = new BadConsequence(this.text, this.levels, fittedVisible, fittedHidden);
        } else {
            
            // Treasures are non specific (number of)
            if (nVisibleTreasures >= v.size())
                fittedNVisibles = v.size();
            
            if (nHiddenTreasures >= h.size())
                fittedNHidden = h.size();
            
            fittedBadConsequence = new BadConsequence(this.text, this.levels, fittedNVisibles, fittedNHidden);
        }
        
        return fittedBadConsequence;
    }
    
    
}
