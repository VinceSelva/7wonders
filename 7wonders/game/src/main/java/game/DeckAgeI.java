package game;
import java.util.ArrayList;
import game.Card;

public class DeckAgeI extends Deck {

	public DeckAgeI(){
		super();

		// Ajout des cartes Matières Premières
		addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD));
		addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
		addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));

		// Ajout des cartes Produits Manufacturés
		addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", ManufacturedGood.TEXTILES));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", ManufacturedGood.GLASS));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "PRESSE", ManufacturedGood.PAPYRUS));

		// Doublage des cartes pour avoir le bon nombre de cartes
		addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD));
		addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
		addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
		addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
		addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", ManufacturedGood.TEXTILES));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", ManufacturedGood.GLASS));
		addCard(new Card(CardType.MANUFACTURED_GOOD, "PRESSE", ManufacturedGood.PAPYRUS));

		// Ajout des cartes Bâtiments Civils
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "PRÊTEUR SUR GAGES", 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "BAINS", 3));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "AUTEL", RawMaterials.BRICK, 2));
		addCard(new Card(CardType.CIVILIAN_STRUCTURE, "THÉÂTRE", 2));

        	// Ajout des cartes Bâtiments Commerciaux
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "TAVERNE",5, new RawMaterials[]{}));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR EST",1, new RawMaterials[]{RawMaterials.STONE, RawMaterials.WOOD,RawMaterials.ORE,RawMaterials.BRICK}));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR OUEST",1, new RawMaterials[]{RawMaterials.STONE, RawMaterials.WOOD,RawMaterials.ORE,RawMaterials.BRICK}));
		addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "MARCHE",1, new RawMaterials[]{}, new Cost(new RawMaterials[]{}, new ManufacturedGood[]{ManufacturedGood.GLASS, ManufacturedGood.PAPYRUS, ManufacturedGood.TEXTILES})));
	}

}