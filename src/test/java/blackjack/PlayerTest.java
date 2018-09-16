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
	public void testHit() {}
	public void testStand() {}
	public void testFindBestHand() {}
	public void testToString() {}
}
