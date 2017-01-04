/**
 * @author Álex Hernández - www.github.com/alexhzr
 */

package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDealer {
    private final static CardDealer INSTANCE = new CardDealer();
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Treasure> unusedTreasures;
    
    private CardDealer() {
    }
    
    
    private void initTreasureCardDeck() {
        addTreasuresToDeck();
        shuffleTreasures();
    }
    
    private void initMonsterCardDeck() {
        addMonstersToDeck();
        shuffleMonsters();
    }
    
    private void shuffleTreasures() {
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters() {
        Collections.shuffle(unusedMonsters);
    }
    
    public static CardDealer getInstance() {
        return INSTANCE;
    }
    
    public Treasure nextTreasure() {
        Treasure treasure;
        
        if (unusedTreasures.isEmpty()) {
            unusedTreasures = new ArrayList(usedTreasures);
            usedTreasures.clear();
            shuffleTreasures();
        }
        
        treasure = unusedTreasures.get(0);
        unusedTreasures.remove(treasure);
        usedTreasures.add(treasure);
        
        return treasure;
    }
    
    public Monster nextMonster() {
        Monster monster;
        
        if (unusedMonsters.isEmpty()) {
            unusedMonsters = new ArrayList(usedMonsters);
            usedMonsters.clear();
            shuffleMonsters();
        }
        
        monster = unusedMonsters.get(0);
        unusedMonsters.remove(monster);
        usedMonsters.add(monster);
        
        return monster;
    }
    
    public void giveTreasureBack(Treasure t) {
        usedTreasures.add(t);
    }
    
    public void giveMonsterBack(Monster m) {
        usedMonsters.add(m);
    }
    
    public void initCards() {
        initTreasureCardDeck();
        initMonsterCardDeck();
    }
    
    private void addTreasuresToDeck() {
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList();
        unusedTreasures.add(new Treasure("Sí mi amo!", 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación", 3, TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 1, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Garabato Místico", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metálica", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necro-playboycon", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-comicón", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón", 5, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-gnomicón", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentácula de pega", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
    }
    
    private void addMonstersToDeck() {
        unusedMonsters = new ArrayList();
        usedMonsters = new ArrayList();
        
        //Rey de rosa 
        BadConsequence bc = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        Prize prize = new Prize(4, 2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, prize, bc));

        //Ángeles de noche ibicenca
        bc = new BadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo."
                + "Descarta 1 mano visible y 1 mano oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Ángeles de la noche ibicenca", 14, prize, bc));

        //3 Byakhees de bonanza
        bc = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0,
                new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, prize, bc));

        //Tenochtitlan
        bc = new BadConsequence("Embobados con el lindo primigenio te descartas tu casco visible", 0,
                 new ArrayList(Arrays.asList(TreasureKind.HELMET)), 
                 new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Tenochtitlan", 2, prize, bc));

        //El sopor de Dunwich
        bc = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, 
                new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor de Dunwich",2, prize, bc));

        //El gorrón en el umbral
        bc = new BadConsequence("Pierdes todos los tesoros visibles", 0, BadConsequence.MAX_TREASURES, 0);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 13, prize, bc));

        //H.P. Munchcraft
        bc = new BadConsequence("Pierdes la armadura visible", 0, 
                 new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, prize, bc));

        //Necrófago
        bc = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0,
                 new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Necrófago", 13, prize, bc));

        //Flecher
        bc = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Flecher", 2, prize, bc));
        
        //Los Hondos
        bc = new BadConsequence("Estos unusedMonsters resultan bastante superficiales y te aburren mortalmente. Estás muerto.", true);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los Hondos", 8, prize, bc));
        
        //Semillas Cthulu
        bc = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulu", 4, prize, bc));
        
        //Dameargo
        bc = new BadConsequence("Te intentas escaquear. Pierdes una mano visible.", 0,
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, prize, bc));
        
        //Pollipólipo Volante
        bc = new BadConsequence("Da mucho asquito. Pierdes 3 niveles.", 3, new ArrayList(), new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Pollipólipo Volante", 3, prize, bc));
        
        //Yskhtihyssg-Goth
        bc = new BadConsequence("No le gusta que pronuncien mal su nombre. Estás muerto.", true);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 14, prize, bc));
        
        //Familia Feliz
        bc = new BadConsequence("La familia te atrapa. Estás muerto.", true);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Familia Feliz", 1, prize, bc));
        
        //Roboggoth
        bc = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.", 2,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, prize, bc));
        
        //El espía sordo
        bc = new BadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0, 
            new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espía sordo", 5, prize, bc));
        
        //Tongue
        bc = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.", 2, 5, 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Tongue", 19, prize, bc));
        
        //Bicéfalo
        bc = new BadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, 
            new ArrayList((Arrays.asList(TreasureKind.ONEHAND, TreasureKind.ONEHAND))), new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Bicéfalo", 21, prize, bc));
    }
}
