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
		addCard(new Card(CardType.RAW_MATERIAL, "SCIERIE", new RawMaterials[]{RawMaterials.WOOD, RawMaterials.WOOD}, new Cost(new RawMaterials[]{RawMaterials.STONE}, new ManufacturedGood[]{ManufacturedGood.TEXTILES}))); //La carte Scierie produit 2 de bois et coute 1 piece
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AQUEDUC", new Cost(new RawMaterials[]{RawMaterials.STONE, RawMaterials.STONE, RawMaterials.STONE}, new ManufacturedGood[]{NULL}, 5))); //La carte Aqueduc coute 3 de pierres et rapporte 5 pts
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TEMPLE", new Cost(new RawMaterials[]{RawMaterials.WOOD, RawMaterials.BRICK, }, new ManufacturedGood[]{ManufacturedGood.GLASS}, 3)));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "STATUE", new Cost(new RawMaterials[]{{RawMaterials.ORE, RawMaterials.ORE, RawMaterials.WOOD}, new ManufacturedGood[]{NULL}, 4)));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TRIBUNAL", new Cost(new RawMaterials[]{RawMaterials.BRICK, RawMaterials.BRICK}, new ManufacturedGood[]{ManufacturedGood.TEXTILES}, 4)));
	}
}