package game;

import java.util.ArrayList;
import game.Card;

public class DeckAgeI extends Deck {

	public DeckAgeI(){
		super();

		addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD));
		addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
		addCard(new Card(CardType.RAW_MATERIAL, "FRICHE", RawMaterials.WOOD));
		addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "GISEMENT", RawMaterials.WOOD));
		addCard(new Card(CardType.RAW_MATERIAL, "MINE", RawMaterials.ORE));

		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "PRÊTEUR SUR GAGES", 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "BAINS", 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AUTEL", 2));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "THÉÂTRE", 2));
	}

}