package blackjack;
import junit.framework.TestCase;

public class HandTest extends TestCase{
	private Card newCard(String s) {
		try {return new Card(s);}
		catch(InvalidCardException e){
			System.out.println(e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}
	public void testCalculateScore1() {
		Hand testHand = new Hand();
		testHand.add(newCard("H13"));
		assertEquals(10, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("D6"));
		assertEquals(16, testHand.getScore());
		assertFalse(testHand.isBust());
		//test toString()
		//assertTrue(testHand.toString().equals("[H2, D6] Score: 8"));
		assertEquals("[HK, D6] Score: 16", testHand.toString());
	}
	public void testCalculateScore2() {
		Hand testHand = new Hand();
		testHand.add(newCard("H1"));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("D6"));
		assertEquals(17, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("C8"));
		assertEquals(15, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("S9"));
		assertEquals(24, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, D6, C8, S9] Score: 24(Bust!)", testHand.toString());
	}
	public void testCalculateScore3() {
		Hand testHand = new Hand();
		testHand.add(newCard("H1"));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("D1"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("C1"));
		assertEquals(13, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("S9"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("S10"));
		assertEquals(22, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, DA, CA, S9, S10] Score: 22(Bust!)", testHand.toString());
	}
	public void testCalculateScore4() {
		Hand testHand = new Hand();
		testHand.add(newCard("H1"));
		assertEquals(11, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("DA"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("CK"));
		assertEquals(12, testHand.getScore());
		assertFalse(testHand.isBust());
		testHand.add(newCard("S10"));
		assertEquals(22, testHand.getScore());
		assertTrue(testHand.isBust());
		assertEquals("[HA, DA, CK, S10] Score: 22(Bust!)", testHand.toString());
	}
	//from prof email
	//two Ace: SA H8 DA 20
	public void testFromEmail() {
		Hand testHand = new Hand();
		testHand.add(newCard("SA"));
		testHand.add(newCard("H8"));
		testHand.add(newCard("DA"));
		assertEquals(20, testHand.getScore());
	}
	//two Ace: H8 S9 SA HA
	public void testFromEmail2() {
		Hand testHand = new Hand();
		testHand.add(newCard("H8"));
		testHand.add(newCard("S9"));
		testHand.add(newCard("SA"));
		testHand.add(newCard("HA"));
		assertEquals(19, testHand.getScore());
	}
	//Ace changed from 11 to 1
	//SA H5 DA C10 = 17
	public void testFromEmail3() {
		Hand testHand = new Hand();
		testHand.add(newCard("SA"));
		testHand.add(newCard("H5"));
		testHand.add(newCard("DA"));
		testHand.add(newCard("C10"));
		assertEquals(17, testHand.getScore());
	}
	
	public void testIsSoft17_1() {
		//score is not 17
		Hand testHand = new Hand();
		testHand.add(newCard("SA"));
		assertFalse(testHand.isSoft17());
	}
	
	public void testIsSoft17_2() {
		//score is 17 but not Soft 17
		Hand testHand = new Hand();
		testHand.add(newCard("S10"));
		testHand.add(newCard("HA"));
		testHand.add(newCard("D6"));
		assertFalse(testHand.isSoft17());
	}
	
	public void testIsSoft17_3() {
		//it is Soft 17
		Hand testHand = new Hand();
		testHand.add(newCard("SA"));
		testHand.add(newCard("C6"));
		assertTrue(testHand.isSoft17());
	}
}
