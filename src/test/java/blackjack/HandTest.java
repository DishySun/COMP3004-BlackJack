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
	//from prof email
	//two Ace: SA H8 DA 20
	public void testFromEmail() {
		Hand testHand = new Hand();
		testHand.add(new Card("SA"));
		testHand.add(new Card("H8"));
		testHand.add(new Card("DA"));
		assertEquals(20, testHand.getScore());
	}
	//two Ace: H8 S9 SA HA
	public void testFromEmail2() {
		Hand testHand = new Hand();
		testHand.add(new Card("H8"));
		testHand.add(new Card("S9"));
		testHand.add(new Card("SA"));
		testHand.add(new Card("HA"));
		assertEquals(19, testHand.getScore());
	}
	//Ace changed from 11 to 1
	//SA H5 DA C10 = 17
	public void testFromEmail3() {
		Hand testHand = new Hand();
		testHand.add(new Card("SA"));
		testHand.add(new Card("H5"));
		testHand.add(new Card("DA"));
		testHand.add(new Card("C10"));
		assertEquals(17, testHand.getScore());
	}
	
	public void testIsSoft17_1() {
		//score is not 17
		Hand testHand = new Hand();
		testHand.add(new Card("SA"));
		assertFalse(testHand.isSoft17());
	}
	
	public void testIsSoft17_2() {
		//score is 17 but not Soft 17
		Hand testHand = new Hand();
		testHand.add(new Card("SA"));
		assertFalse(testHand.isSoft17());
	}
	
	public void testIsSoft17_3() {
		//it is Soft 17
		Hand testHand = new Hand();
		testHand.add(new Card("SA"));
		assertTrue(testHand.isSoft17());
	}
}
