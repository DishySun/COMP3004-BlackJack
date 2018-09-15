package blackjack;
import junit.framework.TestCase;

public class CardTest extends TestCase{
	public void testConstructor() {
		Card c = new Card(Card.Suit.H, 13);
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(13, c.getRank());
	}
	
	public void testConstructor2() {
		Card c = new Card ("SK");
		assertEquals(Card.Suit.C, c.getSuit());
		assertEquals(13, c.getRank());
	}
	
	public void testToString() {
		Card c = new Card(Card.Suit.H,12);
		assertEquals("HQ", c.toString());
	}
}
