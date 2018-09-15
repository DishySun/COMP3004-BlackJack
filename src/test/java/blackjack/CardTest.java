package blackjack;
import junit.framework.TestCase;

public class CardTest extends TestCase{
	public void testConstructor() {
		Card c = new Card(Card.Suit.H, 13);
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(13, c.getRank());
	}
}
