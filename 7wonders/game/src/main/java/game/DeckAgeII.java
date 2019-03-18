package game;

import java.util.ArrayList;
import game.Card;

public class DeckAgeII extends Deck {

	public DeckAgeII(){
		super();

 		//CardType type, String name, RawMaterials ressourcesProduites[], Cost cost
		addCard(new Card(CardType.RAW_MATERIAL, "SCIERIE", new RawMaterials[]{RawMaterials.WOOD, RawMaterials.WOOD}, new Cost(1))); //La carte Scierie produit 2 de bois et coute 1 piece
		addCard(new Card(CardType.RAW_MATERIAL, "CARRIERE", new RawMaterials[]{RawMaterials.STONE, RawMaterials.STONE}, new Cost(1)));
		addCard(new Card(CardType.RAW_MATERIAL, "BRIQUETERIE", new RawMaterials[]{RawMaterials.BRICK, RawMaterials.BRICK}, new Cost(1)));
		addCard(new Card(CardType.RAW_MATERIAL, "FONDERIE", new RawMaterials[]{RawMaterials.ORE, RawMaterials.ORE},new Cost(1)));


		//CardType type, String name, ManufacturedGood ressourcesNecess[], int points
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AQUEDUC", new ManufacturedGood[]{RawMaterials.STONE, RawMaterials.STONE, RawMaterials.STONE}, 5)); //La carte Aqueduc coute 3 de pierres et rapporte 5 pts
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TEMPLE", new ManufacturedGood[]{RawMaterials.WOOD, RawMaterials.BRICK, ManufacturedGood.GLASS}, 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "STATUE", new ManufacturedGood[]{RawMaterials.ORE, RawMaterials.ORE, RawMaterials.WOOD}, 4));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TRIBUNAL", new ManufacturedGood[]{RawMaterials.BRICK, RawMaterials.BRICK, ManufacturedGood.TEXTILES}, 4));
	
		

	}

}