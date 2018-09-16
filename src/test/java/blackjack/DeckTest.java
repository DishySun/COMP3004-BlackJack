package blackjack;
import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testConstructor() {
		Deck testDeck = new Deck();
		assertEquals(0, testDeck.size());
	}
	
	public void testIniCDeck() {
		Deck testDeck = new Deck();
		testDeck.iniDeck();
		assertEquals(52, testDeck.size());
	}
	
	public void testCDeckIsShuffled() {
		Deck testDeck1 = new Deck();
		Deck testDeck2 = new Deck();
		testDeck1.iniDeck();
		testDeck2.iniDeck();
		int timesDrewTwoSameCard = 0;
		for (int i = 0; i < 50; i++) {
			Card c1 = testDeck1.draw();
			Card c2 = testDeck2.draw();
			if(c1.getSuit() == c2.getSuit() && c1.getRank() == c2.getRank())
				timesDrewTwoSameCard++;
		}
		assertTrue(timesDrewTwoSameCard<10);
	}
}
