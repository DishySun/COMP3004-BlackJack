package blackjack;
import junit.framework.TestCase;

public class HandTest extends TestCase{
	public void testCalculateScore1() {
		Hand testHand = new Hand();
		testHand.add(new Card(Card.Suit.H, 2));
		assertEquals(2, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.D, 6));
		assertEquals(8, testHand.getScore());
		assertFalse(testHand.isBust());
		//test toString()
		//assertTrue(testHand.toString().equals("[H2, D6] Socre: 8"));
		assertEquals("[H2, D6] Score: 8", testHand.toString());
	}
	public void testCalculateScore2() {
		Hand testHand = new Hand();
		testHand.add(new Card(Card.Suit.H, 1));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.D, 6));
		assertEquals(17, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.C, 8));
		assertEquals(15, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.S, 9));
		assertEquals(24, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, D6, C8, S9] Score: 24(Bust!)", testHand.toString());
	}
	public void testCalculateScore3() {
		Hand testHand = new Hand();
		testHand.add(new Card(Card.Suit.H, 1));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.D, 1));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.C, 1));
		assertEquals(13, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.S, 9));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card(Card.Suit.S, 10));
		assertEquals(22, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, DA, CA, S9, S10] Score: 22(Bust!)", testHand.toString());
	}
	public void testCalculateScore4() {
		Hand testHand = new Hand();
		testHand.add(new Card(Card.Suit.H, 1));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card("DA"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card("CK"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(new Card("S10"));
		assertEquals(22, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, DA, CK, S10] Score: 22(Bust!)", testHand.toString());
	}
}
