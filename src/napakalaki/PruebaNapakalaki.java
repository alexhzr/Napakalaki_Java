/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author alexhernandez
 */
public class PruebaNapakalaki {
    static ArrayList<Monster> monstruos = new ArrayList();
    static final int MAX_TREASURES = 20;
    
    public static void main(String[] args) {
       inicializarCartas();
       
       
    }
    
    private static ArrayList<Monster> monstruosNivelSuperior10() {
        ArrayList<Monster> retMonsters = new ArrayList();
        
        for (int i = 0; i <= monstruos.size(); i++) {
            
        }
        
        return retMonsters;
    }
    
    //Faltan 2 páginas de monstruos
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
