/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author alexhernandez
 */
public class PruebaNapakalaki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Prize pr1 = new Prize(2, 3);
       Prize pr2 = new Prize(4, 1);
       Prize pr3 = new Prize(7, 2);
       
       ArrayList<TreasureKind> tV1 = new ArrayList();
       ArrayList<TreasureKind> tH1 = new ArrayList();
       ArrayList<TreasureKind> tV2 = new ArrayList();
       ArrayList<TreasureKind> tH2 = new ArrayList();
       ArrayList<TreasureKind> tV3 = new ArrayList();
       ArrayList<TreasureKind> tH3 = new ArrayList();
       
       BadConsequence bc1 = new BadConsequence("bad consecuence 1", 3, tV1, tH2);
       BadConsequence bc2 = new BadConsequence("bad 2", 4, tV2, tH2);
       BadConsequence bc3 = new BadConsequence("bad 3", 2, tV3, tH3);
       
       Monster monster1 = new Monster("pepeluis", 12, pr1, bc1);
       Monster monster2 = new Monster("ramon", 11, pr2, bc2);
       Monster monster3 = new Monster("emiliano", 5, pr3, bc3);
       
       System.out.println("Prize 1 -> " + pr1.toString());
       System.out.println("Prize 2 -> " + pr2.toString());
       System.out.println("Prize 3 -> " + pr3.toString());
       
       System.out.println("Bad consequence 1 -> " + bc1.toString());
       System.out.println("Bad consequence 2 -> " + bc2.toString());
       System.out.println("Bad consequence 3 -> " + bc3.toString());
       
       System.out.println("Monster 1 -> " + monster1.toString());
       System.out.println("Monster 2 -> " + monster2.toString());
       System.out.println("Monster 3 -> " + monster3.toString());
    }
    
}
