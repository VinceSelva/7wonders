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
        participant.setName("Test");
        assertEquals(participant.getName(), "Test");
    }
/*
    @Test
    public void testAddCard() {
        participant = new Participant(socketIOClient);
        int avantAjout = participant.getHand().size();
        ArrayList<Card> cards = participant.getHand();
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterial.WOOD));
        participant.setHand(cards);
        assertNotEquals(avantAjout, cards.size());
        assertEquals(cards.toString(), "[TEST, type : RAW_MATERIAL, valeur : 0]");
    }

    @Test
    public void testClearCards() {
        participant = new Participant(socketIOClient);
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterial.WOOD));
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST2", RawMaterial.BRICK));
        int avantClear = participant.getHand().size();
        participant.clearCards();
        assertNotEquals(avantClear, participant.getHand().size());
        assertEquals(participant.getHand().toString(), "[]");
    }

    @Test
    public void testRemoveCards() {
        participant = new Participant(socketIOClient);
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST", RawMaterial.WOOD));
        participant.addCard(new Card(CardType.RAW_MATERIAL, "TEST2", RawMaterial.BRICK));
        int avantRemove = participant.getHand().size();
        participant.discard(participant.getHand().size()-1);
        assertNotEquals(avantRemove, participant.getHand().size());
        assertEquals(participant.getHand().toString(), "[TEST, type : RAW_MATERIAL, valeur : 0]");
    }*/

    @Test
    public void testAddWonder() {
        participant = new Participant(socketIOClient);
        Wonder avantAjout = participant.getWonder();
        assertNull(avantAjout);
        participant.setWonder(new Wonder("TEST", RawMaterial.STONE));
        assertEquals(participant.getWonder().toString(), "wonder TEST");
    }
}
