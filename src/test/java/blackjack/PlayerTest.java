package blackjack;
import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	private Card newCard(String s) {
		try {return new Card(s);}
		catch(InvalidCardException e){
			System.out.println(e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}
	private void drawTwo(Dealer p, String s1, String s2) {
		try {p.drawTwoToStart(newCard(s1), newCard(s2));}
		catch(DuplicateCardException e) {e.printStackTrace();}
	}
	private void drawTwo(Player p, String s1, String s2) {
		try {p.drawTwoToStart(newCard(s1), newCard(s2));}
		catch(DuplicateCardException e) {e.printStackTrace();}
	}
	private void split(Player p, String s1, String s2) {
		try {p.split(newCard(s1), newCard(s2));}
		catch(DuplicateCardException e) {e.printStackTrace();}
	}
	private void split(Dealer p, String s1, String s2) {
		try {p.split(newCard(s1), newCard(s2));}
		catch(DuplicateCardException e) {e.printStackTrace();}
	}
	private void hit(Player p, String s1) {
		try {p.hit(newCard(s1));}
		catch(DuplicateCardException e) {e.printStackTrace();}
	}
	
	public void testDrawTwoToStart() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D3");
		assertFalse(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testDrawTwoToStart2() {
		//SQ DA SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"SQ","DA");
		assertTrue(testPlayer.isFinish());
		assertEquals(21, testPlayer.getFirstHand().getScore());
	}
	public void testSplit() {
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
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
		drawTwo(testPlayer,"SA","DA");
		split(testPlayer,"SQ","C5");
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
		drawTwo(testPlayer,"S10","D3");
		hit(testPlayer,"SQ");
		assertTrue(testPlayer.isFinish());
		assertEquals(23, testPlayer.getFirstHand().getScore());
	}
	
	public void testHit2() {
		//S10 D10 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ", "C5");
		hit(testPlayer,"H5");
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.H,testPlayer.getFirstHand().getHand().get(2).getSuit());
		assertEquals(5,testPlayer.getFirstHand().getHand().get(2).getRank());
		assertEquals(25, testPlayer.getFirstHand().getScore());
	}
	public void testHit3() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
		hit(testPlayer,"H5");
		hit(testPlayer,"SA");
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.S,testPlayer.getSplitHand().getHand().get(2).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(2).getRank());
		assertEquals(16, testPlayer.getSplitHand().getScore());
		hit(testPlayer,"CA");
		assertFalse(testPlayer.isFinish());
		assertEquals(Card.Suit.C,testPlayer.getSplitHand().getHand().get(3).getSuit());
		assertEquals(1,testPlayer.getSplitHand().getHand().get(3).getRank());
		assertEquals(17, testPlayer.getSplitHand().getScore());
		hit(testPlayer,"D9");
		assertTrue(testPlayer.isFinish());
		assertEquals(Card.Suit.D,testPlayer.getSplitHand().getHand().get(4).getSuit());
		assertEquals(9,testPlayer.getSplitHand().getHand().get(4).getRank());
		assertEquals(26, testPlayer.getSplitHand().getScore());
	}
	public void testStand() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10", "D3");
		testPlayer.stand();
		assertTrue(testPlayer.isFinish());
		assertEquals(13, testPlayer.getFirstHand().getScore());
	}
	public void testStand2() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
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
		drawTwo(testPlayer,"S10","D3");
		testPlayer.stand();
		//no split no bust
		//S10 D3
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(13, bestHand.getScore());
	}
	public void testFindBestHand2() {
		//S10 D3 SQ C5 H5 SA CA D2
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D3");
		hit(testPlayer,"SQ");
		testPlayer.stand();
		//no split busted
		//S10 D3 SQ
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(null, bestHand);
	}
	public void testFindBestHand3() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
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
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
		hit(testPlayer,"H5");
		hit(testPlayer,"SA");
		hit(testPlayer,"CA");
		hit(testPlayer,"D9");
		Hand bestHand = testPlayer.findBestHand();
		//split both bust
		//10 10 5 = 25
		//10 5 1 1 9 = 26
		assertEquals(null, bestHand);
	}
	public void testFindBestHand5() {
		//S10 D10 SQ C5 H5 S7 CA D9
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
		testPlayer.stand();
		hit(testPlayer,"H5");
		hit(testPlayer,"S7");
		//split one bust
		//S10 SQ = 20
		//D10 C5 H5 S7 = 27 bust
		Hand bestHand = testPlayer.findBestHand();
		assertEquals(20, bestHand.getScore());
	}
	public void testToString() {
		//S10 D10 SQ C5 H5 SA CA D9
		Player testPlayer = new Player();
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
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
		drawTwo(testPlayer,"S10","D10");
		split(testPlayer,"SQ","C5");
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
