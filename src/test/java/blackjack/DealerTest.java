package blackjack;
import junit.framework.TestCase;

public class DealerTest extends TestCase{
	private Card newCard(String s) {
		try {return new Card(s);}
		catch(InvalidCardException e){
			System.out.println(e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}
	
	public void testGetChoice1() {
		Dealer testDealer = new Dealer();
		testDealer.drawTwoToStart(newCard("D2"), newCard("H7"));
		//score < 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(newCard("C8"));
		//score = 17 but not soft 17
		assertEquals("S", testDealer.getChoice());
		testDealer.hit(newCard("S2"));
		//score > 17
		assertEquals("S", testDealer.getChoice());
	}
	public void testGetChoice2() {
		Dealer testDealer = new Dealer();
		testDealer.drawTwoToStart(newCard("D2"), newCard("H4"));
		//score < 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(newCard("C1"));
		//soft 17
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(newCard("S2"));
		//score > 17
		assertEquals("S", testDealer.getChoice());
	}
	public void testGetChoice3() {
		//with splitting
		Dealer testDealer = new Dealer();
		testDealer.drawTwoToStart(newCard("D5"), newCard("H5"));
		//split
		assertEquals("D", testDealer.getChoice());
		
		testDealer.split(newCard("CA"), newCard("C8"));
		//current on hand1 (D5 CA = 16 which < 17) will hit on hand 1
		assertEquals("H", testDealer.getChoice());
		assertEquals(16, testDealer.getFirstHand().getScore());
		
		testDealer.hit(newCard("SA"));
		//current on hand1 (D5 CA SA Soft 17 with 2 Aces) will hit on hand 1
		assertEquals("H", testDealer.getChoice());
		assertEquals(17, testDealer.getFirstHand().getScore());
		
		testDealer.hit(newCard("S7"));
		//hand 1 (D5 CA SA S7 = 14 < 17) will hit on hand 1 
		assertEquals("H", testDealer.getChoice());
		assertEquals(14, testDealer.getFirstHand().getScore());
		
		testDealer.hit(newCard("HK"));
		//hand 1(D5 CA SA S7 HK = 24)
		assertEquals(24, testDealer.getFirstHand().getScore());
		
		//dealer will work on hand 2 automatically
		//current on hand 2(H5 C8 = 13 < 17) will hit on hand2
		assertEquals("H", testDealer.getChoice());
		testDealer.hit(newCard("S8"));
		//hand 2 (H5 C8 S8 = 21 > 17) will stand on hand 2
		assertEquals(21, testDealer.getSplitHand().getScore());
		assertEquals("S", testDealer.getChoice());
	}
}
