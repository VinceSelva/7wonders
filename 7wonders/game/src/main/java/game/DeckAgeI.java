package game;

import java.util.HashMap;

import game.Card;

public class DeckAgeI extends Deck {

	public DeckAgeI(){
		super();

		// Ajout des cartes Matières Premières
		addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", new HashMap<String, Integer>(){{
			put(RawMaterial.WOOD.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", new HashMap<String, Integer>(){{
			put(RawMaterial.BRICK.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "FILON", new HashMap<String, Integer>(){{
			put(RawMaterial.ORE.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}, new Cost(1)));
		addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", new HashMap<String, Integer>(){{
			put(RawMaterial.BRICK.toString(), 1);
		}}, new Cost(1)));
		addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}, new Cost(1)));

		// Ajout des cartes Produits Manufacturés
		addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", new HashMap<String, Integer>(){{
			put(ManufacturedGood.TEXTILES.toString(), 1);
		}}));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", new HashMap<String, Integer>(){{
			put(ManufacturedGood.GLASS.toString(), 1);
		}}));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "PRESSE", new HashMap<String, Integer>(){{
			put(ManufacturedGood.PAPYRUS.toString(), 1);
		}}));

		// Doublage des cartes pour avoir le bon nombre de cartes
		addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", new HashMap<String, Integer>(){{
			put(RawMaterial.WOOD.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", new HashMap<String, Integer>(){{
			put(RawMaterial.BRICK.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "FILON", new HashMap<String, Integer>(){{
			put(RawMaterial.ORE.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", new HashMap<String, Integer>(){{
			put(RawMaterial.BRICK.toString(), 1);
		}}));
		addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", new HashMap<String, Integer>(){{
			put(RawMaterial.STONE.toString(), 1);
		}}));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", new HashMap<String, Integer>(){{
			put(ManufacturedGood.TEXTILES.toString(), 1);
		}}));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", new HashMap<String, Integer>(){{
			put(ManufacturedGood.GLASS.toString(), 1);
		}}));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "PRESSE", new HashMap<String, Integer>(){{
			put(ManufacturedGood.PAPYRUS.toString(), 1);
		}}));

		// Ajout des cartes Bâtiments Civils
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "PRÊTEUR SUR GAGES", 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "BAINS", 3, new Cost(3)));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AUTEL", 2));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "THÉÂTRE", 2));

        // Ajout des cartes Bâtiments Commerciaux
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "TAVERNE", new HashMap<String, Integer>(){{
			put("COIN", 5);
		}}));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR EST"));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR OUEST"));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "MARCHE"));
	}
}