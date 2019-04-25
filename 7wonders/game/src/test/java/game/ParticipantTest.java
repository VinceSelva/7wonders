package game;

import com.corundumstudio.socketio.SocketIOClient;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class ParticipantTest {
    private Participant participant;
    private SocketIOClient socketIOClient;
    private DeckAgeI deckAgeI;

    @Test
    public void testSetName() {
        participant = new Participant(socketIOClient);

        participant.setName("Test");

        assertEquals(participant.getName(), "Test");
    }

    @Test
    public void testHand() {
        participant = new Participant(socketIOClient);
        deckAgeI = new DeckAgeI();

        int avantAjout = participant.getHand().size();
        ArrayList<Card> cards = participant.getHand();
        cards.add(deckAgeI.getCard(0));
        participant.setHand(cards);

        assertNotEquals(avantAjout, participant.getHand().size());
        assertEquals(participant.getHand().toString(), "[[Card CHANTIER]]");
    }

    @Test
    public void testBuildings() {
        participant = new Participant(socketIOClient);
        deckAgeI = new DeckAgeI();

        int avantConstruction = participant.getBuildings().size();
        participant.setHand(deckAgeI.getCards());
        participant.build(participant.getHand().get(0));

        assertNotEquals(avantConstruction, participant.getBuildings().size());
        assertEquals(participant.getBuildings().toString(), "[[Card CHANTIER]]");
    }

    @Test
    public void testWonder() {
        participant = new Participant(socketIOClient);

        Wonder avantAjout = participant.getWonder();
        assertNull(avantAjout);
        participant.setWonder(new Wonder("TEST", RawMaterial.STONE));

        assertEquals(participant.getWonder().toString(), "[Wonder TEST, produces STONE]");
    }

    @Test
    public void testScore() {
        participant = new Participant(socketIOClient);
        deckAgeI = new DeckAgeI();

        assertEquals(participant.getScore(), 0);

        participant.setHand(deckAgeI.getCards());
        participant.build(participant.getHand().get(0));
        participant.computeScore();

        assertEquals(participant.getScore(), 1);
        assertEquals(participant.getScoreExplanations(), "[3 coins (1 points)]");
    }

    @Test
    public void testBestPlay() {
        participant = new Participant(socketIOClient);
        participant.setWonder(new Wonder("Test", RawMaterial.WOOD));
        deckAgeI = new DeckAgeI();

        participant.setHand(deckAgeI.getCards());

        assertEquals(participant.bestPlay(), 0);
    }
}
