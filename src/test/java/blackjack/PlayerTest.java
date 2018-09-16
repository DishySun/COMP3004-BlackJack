package blackjack;
import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testDrawTwoToStart() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D3"));
		assertFalse(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testDrawTwoToStart2() {
		//SQ DA SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("SQ"), new Card("DA"));
		assertTrue(testPlayer.isFinish());
		assertEquals(21, testPlayer.getFirstHand().getScore());
	}
	public void testSplit() {
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.S,testPlayer.getFirstHand().getHand().get(0).getSuit());
		assertEquals(10,testPlayer.getFirstHand().getHand().get(0).getRank());
		assertEquals(Card.Suit.S,testPlayer.getFirstHand().getHand().get(1).getSuit());
		assertEquals(12,testPlayer.getFirstHand().getHand().get(1).getRank());
		assertEquals(20, testPlayer.getFirstHand().getScore());
		assertEquals(Card.Suit.D,testPlayer.getSplitHand().getHand().get(0).getSuit());
		assertEquals(10,testPlayer.getSplitHand().getHand().get(0).getRank());
		assertEquals(Card.Suit.C,testPlayer.getSplitHand().getHand().get(1).getSuit());
		assertEquals(5,testPlayer.getSplitHand().getHand().get(1).getRank());
		assertEquals(15, testPlayer.getSplitHand().getScore());
	}
	public void testSplit2() {
		//SA DA SQ C5 H5 H SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("SA"), new Card("DA"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		assertTrue(testPlayer.isFinish());
		assertEquals(Card.Suit.S,testPlayer.getFirstHand().getHand().get(0).getSuit());
		assertEquals(1,testPlayer.getFirstHand().getHand().get(0).getRank());
		assertEquals(Card.Suit.S,testPlayer.getFirstHand().getHand().get(1).getSuit());
		assertEquals(12,testPlayer.getFirstHand().getHand().get(1).getRank());
		assertEquals(21, testPlayer.getFirstHand().getScore());
		assertEquals(Card.Suit.D,testPlayer.getSplitHand().getHand().get(0).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(0).getRank());
		assertEquals(Card.Suit.C,testPlayer.getSplitHand().getHand().get(1).getSuit());
		assertEquals(5,testPlayer.getSplitHand().getHand().get(1).getRank());
		assertEquals(16, testPlayer.getSplitHand().getScore());
	}
	public void testHit() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D3"));
		testPlayer.hit(new Card("SQ"));
		assertTrue(testPlayer.isFinish());
		assertEquals(23, testPlayer.getFirstHand().getScore());
	}
	
	public void testHit2() {
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.hit(new Card("H5"));
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.H,testPlayer.getFirstHand().getHand().get(2).getSuit());
		assertEquals(5,testPlayer.getFirstHand().getHand().get(2).getRank());
		assertEquals(25, testPlayer.getFirstHand().getScore());
	}
	public void testHit3() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.hit(new Card("H5"));
		testPlayer.hit(new Card("SA"));
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.S,testPlayer.getSplitHand().getHand().get(2).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(2).getRank());
		assertEquals(16, testPlayer.getSplitHand().getScore());
		testPlayer.hit(new Card("CA"));
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.C,testPlayer.getSplitHand().getHand().get(3).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(3).getRank());
		assertEquals(17, testPlayer.getSplitHand().getScore());
		testPlayer.hit(new Card("D9"));
		assertTrue(testPlayer.isFinish());
		assertEquals(Card.Suit.D,testPlayer.getSplitHand().getHand().get(4).getSuit());
		assertEquals(9,testPlayer.getSplitHand().getHand().get(4).getRank());
		assertEquals(26, testPlayer.getSplitHand().getScore());
	}
	public void testStand() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D3"));
		testPlayer.stand();
		assertTrue(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testStand2() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.stand();
		assertFalse(testPlayer.isFinish());
		assertEquals(20, testPlayer.getFirstHand().getScore());
		testPlayer.stand();
		assertTrue(testPlayer.isFinish());
		assertEquals(15, testPlayer.getSplitHand().getScore());
	}
	public void testFindBestHand() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D3"));
		testPlayer.stand();
		//no split no bust
		//S10 D3
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(13, bestHand.getScore());
	}
	public void testFindBestHand2() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D3"));
		testPlayer.hit(new Card("SQ"));
		testPlayer.stand();
		//no split busted
		//S10 D3 SQ
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(null, bestHand);
	}
	public void testFindBestHand3() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.stand();
		testPlayer.stand();
		//split no bust
		//S10 SQ = 20
		//D10 C5 = 15
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(20, bestHand.getScore());
	}
	public void testFindBestHand4() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.hit(new Card("H5"));
		testPlayer.hit(new Card("SA"));
		testPlayer.hit(new Card("CA"));
		testPlayer.hit(new Card("D9"));
		Hand bestHand = testPlayer.findBestHand();
		//split both bust
		//10 10 5 = 25
		//10 5 1 1 9 = 26
		assertEquals(null, bestHand);
	}
	public void testFindBestHand5() {
		//S10 D10 SQ C5 H5 S7 CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.stand();
		testPlayer.hit(new Card("H5"));
		testPlayer.hit(new Card("S7"));
		//split one bust
		//S10 SQ = 20
		//D10 C5 H5 S7 = 27 bust
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(20, bestHand.getScore());
	}
	public void testToString() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.stand();
		testPlayer.stand();
		//S10 SQ
		//D10 C5
		String expectedString = "Player No Name:\n";
		expectedString += "Hand 1: [S10, SQ] Score: 20 (Highest)\n";
		expectedString += "Hand 2: [D10, C5] Score: 15\n";
		assertEquals(expectedString, testPlayer.toString());
	}
	public void testToString2() {
		//S10 D10 SQ C5 H5 SA CA D9
		Dealer testPlayer = new Dealer();
		testPlayer.drawTwoToStart(new Card("S10"), new Card("D10"));
		testPlayer.split(new Card("SQ"), new Card("C5"));
		testPlayer.stand();
		testPlayer.stand();
		//S10 SQ
		//D10 C5
		String expectedString = "Dealer Computer(Very Hard):\n";
		expectedString += "Hand 1: [S10, SQ] Score: 20 (Highest)\n";
		expectedString += "Hand 2: [D10, C5] Score: 15\n";
		assertEquals(expectedString, testPlayer.toString());
	}
}