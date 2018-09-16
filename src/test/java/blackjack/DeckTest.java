package blackjack;
import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testConstructor() {
		Deck testDeck = new Deck();
		assertEquals(0, testDeck.size());
	}
	
	public void testIniCDeck() {
		Deck testDeck = new Deck();
		testDeck.iniCDeck();
		assertEquals(52, testDeck.size());
	}
	
	public void testCDeckIsShuffled() {
		Deck testDeck1 = new Deck();
		Deck testDeck2 = new Deck();
		testDeck1.iniCDeck();
		testDeck2.iniCDeck();
		int timesDrewTwoSameCard = 0;
		for (int i = 0; i < 50; i++) {
			Card c1 = testDeck1.draw();
			Card c2 = testDeck2.draw();
			if(c1.getSuit() == c2.getSuit() && c1.getRank() == c2.getRank())
				timesDrewTwoSameCard++;
		}
		assertTrue(timesDrewTwoSameCard<10);
	}
	
	public void testIniFDeck() {
		Deck testDeck = new Deck();
		String fileName = "test1.txt";
		testDeck.iniFDeck(fileName);
		
		assertEquals(4, testDeck.size());
		
		Card c = testDeck.draw();
		assertEquals(Card.Suit.S, c.getSuit());
		assertEquals(13, c.getRank());
		assertEquals(3, testDeck.size());
		//assertTrue(testText.equals("SK HA HQ DJ"));
		c = testDeck.draw();
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(1, c.getRank());
		assertEquals(2, testDeck.size());
		
		c = testDeck.draw();
		assertEquals(Card.Suit.H, c.getSuit());
		assertEquals(12, c.getRank());
		assertEquals(1, testDeck.size());
		
		c = testDeck.draw();
		assertEquals(Card.Suit.D, c.getSuit());
		assertEquals(11, c.getRank());
		assertEquals(0, testDeck.size());
	}
}
