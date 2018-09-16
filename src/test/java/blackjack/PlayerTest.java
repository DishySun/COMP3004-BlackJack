package blackjack;
import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testDrawTwoToStart() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test.txt");
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		assertFalse(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testDrawTwoToStart2() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with BJ.txt");
		//SQ DA SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		assertTrue(testPlayer.isFinish());
		assertEquals(21, testPlayer.getFirstHand().getScore());
	}
	public void testSplit() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
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
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with BJ after split.txt");
		//SA DA SQ C5 H5 H SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
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
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test.txt");
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.hit(testDeck.draw());
		assertTrue(testPlayer.isFinish());
		assertEquals(23, testPlayer.getFirstHand().getScore());
	}
	
	public void testHit2() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
		testPlayer.hit(testDeck.draw());
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.H,testPlayer.getFirstHand().getHand().get(2).getSuit());
		assertEquals(5,testPlayer.getFirstHand().getHand().get(2).getRank());
		assertEquals(25, testPlayer.getFirstHand().getScore());
	}
	public void testHit3() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
		testPlayer.hit(testDeck.draw());
		testPlayer.hit(testDeck.draw());
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.S,testPlayer.getSplitHand().getHand().get(2).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(2).getRank());
		assertEquals(16, testPlayer.getSplitHand().getScore());
		testPlayer.hit(testDeck.draw());
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.C,testPlayer.getSplitHand().getHand().get(3).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(3).getRank());
		assertEquals(17, testPlayer.getSplitHand().getScore());
		testPlayer.hit(testDeck.draw());
		assertTrue(testPlayer.isFinish());
		assertEquals(Card.Suit.D,testPlayer.getSplitHand().getHand().get(4).getSuit());
		assertEquals(9,testPlayer.getSplitHand().getHand().get(4).getRank());
		assertEquals(26, testPlayer.getSplitHand().getScore());
	}
	public void testStand() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test.txt");
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.stand();
		assertTrue(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testStand2() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
		testPlayer.stand();
		assertFalse(testPlayer.isFinish());
		assertEquals(20, testPlayer.getFirstHand().getScore());
		testPlayer.stand();
		assertTrue(testPlayer.isFinish());
		assertEquals(15, testPlayer.getSplitHand().getScore());
	}
	public void testFindBestHand() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test.txt");
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.stand();
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(13, bestHand.getScore());
	}
	public void testFindBestHand2() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
		testPlayer.stand();
		testPlayer.stand();
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(20, bestHand.getScore());
	}
	public void testFindBestHand3() {
		Deck testDeck = new Deck();
		testDeck.iniFDeck("Player Test with split hand.txt");
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		testPlayer.drawTwoToStart(testDeck.draw(), testDeck.draw());
		testPlayer.split(testDeck.draw(), testDeck.draw());
		testPlayer.hit(testDeck.draw());
		testPlayer.hit(testDeck.draw());
		testPlayer.hit(testDeck.draw());
		testPlayer.hit(testDeck.draw());
		Hand bestHand = testPlayer.findBestHand();
		//10 10 5 = 25
		//10 5 1 1 9 = 26
		assertEquals(25, bestHand.getScore());
	}
	public void testToString() {}
}
