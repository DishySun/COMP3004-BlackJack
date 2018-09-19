package blackjack;
import junit.framework.TestCase;

public class CardTest extends TestCase{
	private Card newCard(String s) {
		try {return new Card(s);}
		catch(InvalidCardException e){
			System.out.println(e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}
	
	public void testConstructor() {
		Card c = newCard("H13");
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(13, c.getRank());
	}
	
	public void testConstructor2() {
		Card c = newCard ("SK");
		assertEquals(Card.Suit.S, c.getSuit());
		assertEquals(13, c.getRank());
	}
	
	public void testConstructor3() {
		Card c = newCard ("H10");
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(10, c.getRank());
	}
	
	public void testToString() {
		Card c = newCard("H12");
		assertEquals("HQ", c.toString());
	}
}
