package blackjack;
import junit.framework.TestCase;

public class DealerTest extends TestCase{
	public void testGetChoice1() {
		Dealer testDealer = new Dealer();
		testDealer.hit(new Card("D2"));
		testDealer.hit(new Card("H7"));
		//score < 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("C8"));
		//score = 17 but not soft 17
		assertEquals("S", testDealer.getChoice());
		testDealer.hit(new Card("S2"));
		//score > 17
		assertEquals("S", testDealer.getChoice());
	}
	public void testGetChoice2() {
		Dealer testDealer = new Dealer();
		testDealer.hit(new Card("D2"));
		testDealer.hit(new Card("H4"));
		//score < 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("C1"));
		//soft 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("S2"));
		//score > 17
		assertEquals("S", testDealer.getChoice());
	}
}
