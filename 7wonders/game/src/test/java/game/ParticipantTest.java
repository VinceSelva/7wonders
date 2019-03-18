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

    @Test
    public void testSetName() {
        participant = new Participant(socketIOClient);
        participant.setname("Test");
        assertEquals(participant.getname(), "Test");
    }

    @Test
    public void testAddCard() {
        participant = new Participant(socketIOClient);
        int avantAjout = participant.getCards().size();
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterials.WOOD));
        ArrayList<Card> cards = participant.getCards();
        assertNotEquals(avantAjout, cards.size());
        assertEquals(cards.toString(), "[TEST, type : RAW_MATERIAL, valeur : 0]");
    }

    @Test
    public void testClearCards() {
        participant = new Participant(socketIOClient);
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterials.WOOD));
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST2", RawMaterials.BRICK));
        int avantClear = participant.getCards().size();
        participant.clearCards();
        assertNotEquals(avantClear, participant.getCards().size());
        assertEquals(participant.getCards().toString(), "[]");
    }

    @Test
    public void testRemoveCards() {
        participant = new Participant(socketIOClient);
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterials.WOOD));
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST2", RawMaterials.BRICK));
        int avantRemove = participant.getCards().size();
        participant.removeCard(participant.getCards().size()-1);
        assertNotEquals(avantRemove, participant.getCards().size());
        assertEquals(participant.getCards().toString(), "[TEST, type : RAW_MATERIAL, valeur : 0]");
    }

    @Test
    public void testAddWonder() {
        participant = new Participant(socketIOClient);
        Wonder avantAjout = participant.getwonder();
        assertNull(avantAjout);
        participant.setwonder(new Wonder("TEST", RawMaterials.STONE));
        assertEquals(participant.getwonder().toString(), "wonder TEST");
    }
}