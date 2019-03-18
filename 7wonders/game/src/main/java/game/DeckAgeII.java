package game;

import java.util.ArrayList;
import game.Card;

public class DeckAgeII extends Deck {

	public DeckAgeII(){
		super();

 		//CardType type, String name, RawMaterials ressourcesProduites[], Cost cost
		addCard(new Card(CardType.RAW_MATERIAL, "SCIERIE", new RawMaterials[]{RawMaterials.WOOD, RawMaterials.WOOD}, 1)); //La carte Scierie produit 2 de bois et coute 1 piece
		addCard(new Card(CardType.RAW_MATERIAL, "CARRIERE", new RawMaterials[]{RawMaterials.STONE, RawMaterials.STONE}, 1));));
		addCard(new Card(CardType.RAW_MATERIAL, "BRIQUETERIE", new RawMaterials[]{RawMaterials.BRICK, RawMaterials.BRICK}, 1));));
		addCard(new Card(CardType.RAW_MATERIAL, "FONDERIE", new RawMaterials[]{RawMaterials.ORE, RawMaterials.ORE}, 1));));


		//CardType type, String name, ManufacturedGood ressourcesNecess[], int points
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AQUEDUC", new ManufacturedGood[]{ManufacturedGood.STONE, ManufacturedGood.STONE, ManufacturedGood.STONE}, 5)); //La carte Aqueduc coute 3 de pierres et rapporte 5 pts
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TEMPLE", new ManufacturedGood[]{ManufacturedGood.WOOD, ManufacturedGood.BRICK, ManufacturedGood.GLASS}, 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "STATUE", new ManufacturedGood[]{ManufacturedGood.ORE, ManufacturedGood.ORE, ManufacturedGood.WOOD}, 4));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "TRIBUNAL", new ManufacturedGood[]{ManufacturedGood.BRICK, ManufacturedGood.BRICK, ManufacturedGood.TEXTILES}, 4));
	
		

	}

}