package blackjack;
import junit.framework.TestCase;

public class DealerTest extends TestCase{
	public void testGetChoice1() {
		Dealer testDealer = new Dealer();
		testDealer.drawTwoToStart(new Card("D2"), new Card("H7"));
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
		testDealer.drawTwoToStart(new Card("D2"), new Card("H4"));
		//score < 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("C1"));
		//soft 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("S2"));
		//score > 17
		assertEquals("S", testDealer.getChoice());
	}
	public void testGetChoice3() {
		//with splitting
		Dealer testDealer = new Dealer();
		testDealer.drawTwoToStart(new Card("D5"), new Card("H5"));
		//split
		assertEquals("D", testDealer.getChoice());
		
		testDealer.split(new Card("CA"), new Card("C8"));
		//current on hand1 (D5 CA = 16 which < 17) will hit on hand 1
		assertEquals("H", testDealer.getChoice());
		assertEquals(16, testDealer.getFirstHand().getScore());
		
		testDealer.hit(new Card("SA"));
		//current on hand1 (D5 CA SA Soft 17 with 2 Aces) will hit on hand 1
		assertEquals("H", testDealer.getChoice());
		assertEquals(17, testDealer.getFirstHand().getScore());
		
		testDealer.hit(new Card("S7"));
		//hand 1 (D5 CA SA S7 = 14 < 17) will hit on hand 1 
		assertEquals("H", testDealer.getChoice());
		assertEquals(14, testDealer.getFirstHand().getScore());
		
		testDealer.hit(new Card("HK"));
		//hand 1(D5 CA SA S7 HK = 24)
		assertEquals(24, testDealer.getFirstHand().getScore());
		
		//dealer will work on hand 2 automatically
		//current on hand 2(H5 C8 = 13 < 17) will hit on hand2
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(new Card("S8"));
		//hand 2 (H5 C8 S8 = 21 > 17) will stand on hand 2
		assertEquals(21, testDealer.getSplitHand().getScore());
		assertEquals("S", testDealer.getChoice());
	}
}
