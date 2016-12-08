/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author alexhernandez
 */
public class PruebaNapakalaki {
    static ArrayList<Monster> monstruos = new ArrayList();
    static final int MAX_TREASURES = 20;
    
    public static void main(String[] args) {
        ArrayList<Treasure> v1 = new ArrayList();
        ArrayList<Treasure> h1 = new ArrayList();
        ArrayList<Treasure> v2 = new ArrayList();
        ArrayList<Treasure> h2 = new ArrayList();
        ArrayList<TreasureKind> t1 = new ArrayList();
        ArrayList<TreasureKind> t2 = new ArrayList();
        
        v1.add(new Treasure("2", MAX_TREASURES, TreasureKind.ONEHAND));
        v1.add(new Treasure("2", MAX_TREASURES, TreasureKind.HELMET));
        h1.add(new Treasure("2", MAX_TREASURES, TreasureKind.SHOES));

        t2.add(TreasureKind.ONEHAND);
        t2.add(TreasureKind.HELMET);
        
        v2.add(new Treasure("2", MAX_TREASURES, TreasureKind.ONEHAND));
        h2.add(new Treasure("2", MAX_TREASURES, TreasureKind.ONEHAND));
        h2.add(new Treasure("2", MAX_TREASURES, TreasureKind.HELMET));
        h2.add(new Treasure("2", MAX_TREASURES, TreasureKind.ARMOR));
        
        BadConsequence bc1 = new BadConsequence("prueba", 2, 0, 3);
        BadConsequence bc2 = new BadConsequence("prueba", 2, t1, t2);
        BadConsequence bc1a = bc1.adjustToFitTreasureLists(v1, h1);
        BadConsequence bc2a = bc2.adjustToFitTreasureLists(v2, h2);
        
        System.out.println("BC1 original:\n"+bc1);
        System.out.println("BC1 ajustada:\n"+bc1a);
        System.out.println("BC2 original:\n"+bc2);
        System.out.println("BC2 ajustada:\n"+bc2a);
        /*inicializarCartas();
       
        ArrayList<Monster> nivelSup10, perdidaNiveles, gananciaNivSup1,
            perdidaOneHand, perdidaHelmet, perdidaArmor, perdidaBothHands, perdidaShoes;
       
        nivelSup10 = monstruosNivelSuperior10();
        perdidaNiveles = monstruosSoloPerdidaNiveles();
        gananciaNivSup1 = monstruosGananciaNivelesSuperiorA1();
       
        perdidaOneHand = monstruosPerdidaTipoObjeto(TreasureKind.ONEHAND);
        perdidaHelmet = monstruosPerdidaTipoObjeto(TreasureKind.HELMET);
        perdidaArmor = monstruosPerdidaTipoObjeto(TreasureKind.ARMOR);
        perdidaBothHands = monstruosPerdidaTipoObjeto(TreasureKind.BOTHHANDS);
        perdidaShoes = monstruosPerdidaTipoObjeto(TreasureKind.SHOES);
       
        imprimirMonstruos("\nMonstruos con nivel superior a 10:", nivelSup10);
        imprimirMonstruos("\nMonstruos que impliquen sólo pérdida de niveles:", perdidaNiveles);
        imprimirMonstruos("\nMonstruos con ganancia de nivel superior a 1:", gananciaNivSup1);
        imprimirMonstruos("\nMonstruos con pérdida de tesoro ONEHAND:", perdidaOneHand);
        imprimirMonstruos("\nMonstruos con pérdida de tesoro HELMET:", perdidaHelmet);
        imprimirMonstruos("\nMonstruos con pérdida de tesoro ARMOR:", perdidaArmor);
        imprimirMonstruos("\nMonstruos con pérdida de tesoro BOTHHANDS:", perdidaBothHands);
        imprimirMonstruos("\nMonstruos con pérdida de tesoro SHOES:", perdidaShoes);
        */
        
        
        
    }
    
    private static ArrayList<Monster> monstruosNivelSuperior10() {
        ArrayList<Monster> retMonsters = new ArrayList();
        
        for (int i = 0; i < monstruos.size(); i++) {
            if (monstruos.get(i).getCombatLevel() > 10)
                retMonsters.add(monstruos.get(i));
        }
        
        return retMonsters;
    }
    
    private static ArrayList<Monster> monstruosSoloPerdidaNiveles() {
        ArrayList<Monster> retMonsters = new ArrayList();
        BadConsequence bc;
        
        for (int i = 0; i < monstruos.size(); i++) {
            bc = monstruos.get(i).getBadConsecuence();
            if      ((bc.getLevels() != 0) 
                    && (bc.getSpecificHiddenTreasures().size() == 0) 
                    && (bc.getSpecificVisibleTreasures().size() == 0)
                    && (bc.getnHiddenTreasures() == 0)
                    && (bc.getnVisibleTreasures() == 0))
                retMonsters.add(monstruos.get(i));
        }
        
        return retMonsters;
    }
    
    private static ArrayList<Monster> monstruosGananciaNivelesSuperiorA1() {
        ArrayList<Monster> retMonsters = new ArrayList();
        
        for (int i = 0; i < monstruos.size(); i++) {
            if (monstruos.get(i).getPrize().getLevel() > 1)
                retMonsters.add(monstruos.get(i));
        }
        
        return retMonsters;
    }
    
    private static ArrayList<Monster> monstruosPerdidaTipoObjeto(TreasureKind tipoTesoro) {
        ArrayList<Monster> retMonsters = new ArrayList();
        ArrayList<TreasureKind> tesorosOcultos;
        ArrayList<TreasureKind> tesorosVisibles;
        boolean encontrado;
        
        for (int i = 0; i < monstruos.size(); i++) {
            encontrado = false;
            tesorosOcultos = monstruos.get(i).getBadConsecuence().getSpecificHiddenTreasures();
            
            for (int j = 0; j < tesorosOcultos.size(); j++) {
                if (tesorosOcultos.get(j) == tipoTesoro) {
                    j = tesorosOcultos.size() + 1;
                    encontrado = true;
                    retMonsters.add(monstruos.get(i));
                }
            }
            
            if (!encontrado) {
                tesorosVisibles = monstruos.get(i).getBadConsecuence().getSpecificVisibleTreasures();
                for (int k = 0; k < tesorosVisibles.size(); k++) {
                    if (tesorosVisibles.get(k) == tipoTesoro) {
                        k = tesorosVisibles.size() + 1;
                        retMonsters.add(monstruos.get(i));
                    }
                }
            }
            
         
        }
        
        return retMonsters;
    }
    
    private static void imprimirMonstruos(String mensajeInicial, ArrayList<Monster> monsters) {        
        for (int i = 0; i < monsters.size(); i++) 
            mensajeInicial += "\n" + monsters.get(i).toString();
        
        System.out.println(mensajeInicial);
    }
    
    private static void inicializarCartas() {
        //Rey de rosa 
        BadConsequence bc = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        Prize prize = new Prize(4, 2);
        monstruos.add(new Monster("El rey de rosa", 13, prize, bc));

        //Ángeles de noche ibicenca
        bc = new BadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo."
                + "Descarta 1 mano visible y 1 mano oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4, 1);
        monstruos.add(new Monster("Ángeles de la noche ibicenca", 14, prize, bc));

        //3 Byakhees de bonanza
        bc = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0,
                new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        prize = new Prize(2, 1);
        monstruos.add(new Monster("3 Byakhees de bonanza", 8, prize, bc));

        //Tenochtitlan
        bc = new BadConsequence("Embobados con el lindo primigenio te descartas tu casco visible", 0,
                 new ArrayList(Arrays.asList(TreasureKind.HELMET)), 
                 new ArrayList());
        prize = new Prize(1, 1);
        monstruos.add(new Monster("Tenochtitlan", 2, prize, bc));

        //El sopor de Dunwich
        bc = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, 
                new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList());
        prize = new Prize(1, 1);
        monstruos.add(new Monster("El sopor de Dunwich",2, prize, bc));

        //El gorrón en el umbral
        bc = new BadConsequence("Pierdes todos los tesoros visibles", 0, MAX_TREASURES, 0);
        prize = new Prize(3, 1);
        monstruos.add(new Monster("El gorrón en el umbral", 13, prize, bc));

        //H.P. Munchcraft
        bc = new BadConsequence("Pierdes la armadura visible", 0, 
                 new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        prize = new Prize(2, 1);
        monstruos.add(new Monster("H.P. Munchcraft", 6, prize, bc));

        //Necrófago
        bc = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0,
                 new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        prize = new Prize(1, 1);
        monstruos.add(new Monster("Necrófago", 13, prize, bc));

        //Flecher
        bc = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize(1, 1);
        monstruos.add(new Monster("Flecher", 2, prize, bc));
        
        //Los Hondos
        bc = new BadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estás muerto.", true);
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Los Hondos", 8, prize, bc));
        
        //Semillas Cthulu
        bc = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Semillas Cthulu", 4, prize, bc));
        
        //Dameargo
        bc = new BadConsequence("Te intentas escaquear. Pierdes una mano visible.", 0,
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Dameargo", 1, prize, bc));
        
        //Pollipólipo Volante
        bc = new BadConsequence("Da mucho asquito. Pierdes 3 niveles.", 3, new ArrayList(), new ArrayList());
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Pollipólipo Volante", 3, prize, bc));
        
        //Yskhtihyssg-Goth
        bc = new BadConsequence("No le gusta que pronuncien mal su nombre. Estás muerto.", true);
        prize = new Prize(3, 1);
        monstruos.add(new Monster("Yskhtihyssg-Goth", 14, prize, bc));
        
        //Familia Feliz
        bc = new BadConsequence("La familia te atrapa. Estás muerto.", true);
        prize = new Prize(3, 1);
        monstruos.add(new Monster("Familia Feliz", 1, prize, bc));
        
        //Roboggoth
        bc = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.", 2,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList());
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Roboggoth", 8, prize, bc));
        
        //El espía sordo
        bc = new BadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0, 
            new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        prize = new Prize(1, 1);
        monstruos.add(new Monster("El espía sordo", 5, prize, bc));
        
        //Tongue
        bc = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.", 2, 5, 0);
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Tongue", 19, prize, bc));
        
        //Bicéfalo
        bc = new BadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, 
            new ArrayList((Arrays.asList(TreasureKind.ONEHAND, TreasureKind.ONEHAND))), new ArrayList());
        prize = new Prize(2, 1);
        monstruos.add(new Monster("Bicéfalo", 21, prize, bc));
       }
    
}
