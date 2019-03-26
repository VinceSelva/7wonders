package game;

import java.util.ArrayList;
import game.Card;

import static game.RawMaterials.*;

public class DeckAgeII extends Deck {

    public DeckAgeII(){
        super();

        //CardType type, String name, RawMaterials ressourcesProduites[], Cost cost
        addCard(new Card(CardType.RAW_MATERIAL, "SCIERIE", new RawMaterials[]{WOOD, WOOD}, new Cost(1))); //La carte Scierie produit 2 de bois et coute 1 piece
        addCard(new Card(CardType.RAW_MATERIAL, "CARRIERE", new RawMaterials[]{STONE, STONE}, new Cost(1)));
        addCard(new Card(CardType.RAW_MATERIAL, "BRIQUETERIE", new RawMaterials[]{BRICK, BRICK}, new Cost(1)));
        addCard(new Card(CardType.RAW_MATERIAL, "FONDERIE", new RawMaterials[]{ORE, ORE},new Cost(1)));


        //CardType type, String name, ManufacturedGood ressourcesNecess[], int points
        addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AQUEDUC", new Cost(new RawMaterials[]{STONE, STONE, STONE}, null, 5))); //La carte Aqueduc coute 3 de pierres et rapporte 5 pts
        addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TEMPLE", new Cost(new RawMaterials[]{WOOD, BRICK, }, new ManufacturedGood[]{ManufacturedGood.GLASS}, 3)));
        addCard(new Card(CardType.CIVILIAN_STRUCTURE, "STATUE", new Cost(new RawMaterials[]{ORE, ORE, WOOD}, null, 4)));
        addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TRIBUNAL", new Cost(new RawMaterials[]{BRICK, BRICK}, new ManufacturedGood[]{ManufacturedGood.TEXTILES}, 4)));
    }
}