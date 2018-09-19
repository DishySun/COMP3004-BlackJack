package blackjack;
import junit.framework.TestCase;

public class GameTest extends TestCase{
	private Card newCard(String s) {
		try {return new Card(s);}
		catch(InvalidCardException e){
			System.out.println(e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}
	private void hit(Player p, String s) {
		try {
			p.hit(newCard(s));
		}catch (DuplicateCardException e) {
			e.printStackTrace();
		}
	}
	private void hit(Dealer d, String s) {
		try {
			d.hit(newCard(s));
		}catch (DuplicateCardException e) {
			e.printStackTrace();
		}
	}
	
	//player get Black Jack but dealer not. Win: player
	public void testDetermineWinner1() {
		Game testGame = new Game();
		Player p = testGame.getPlayer();
		testGame.playerDrawTwo(newCard("SA"), newCard("DQ"));
		testGame.dealerDrawTwo(newCard("C9"), newCard("H2"));
		assertEquals(p, testGame.determineWinner());
		assertEquals(21, testGame.determineWinner().findBestHand().getScore());	
	}
	//player and dealer both get Black Jack. Win: dealer
	public void testDetermineWinner2() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(newCard("SA"), newCard("DQ"));
		testGame.dealerDrawTwo(newCard("CA"), newCard("H10"));
		assertEquals(d, testGame.determineWinner());
		assertEquals(21, testGame.determineWinner().findBestHand().getScore());	
	}
	//player get higher score than dealer without busting Win: player
	public void testDetermineWinner3() {
		Game testGame = new Game();
		Player p = testGame.getPlayer();
		testGame.playerDrawTwo(newCard("C10"), newCard("D6"));
		testGame.dealerDrawTwo(newCard("C6"), newCard("HQ"));
		hit(testGame.getPlayer(),"H3");
		hit(testGame.getDealer(),"C2");
		testGame.getDealer().stand();
		testGame.getPlayer().stand();
		assertEquals(p, testGame.determineWinner());
		assertEquals(19, testGame.determineWinner().findBestHand().getScore());	
	}
	//player get same score with dealer. Win: dealer
	public void testDetermineWinner4() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(newCard("C10"), newCard("D6"));
		testGame.dealerDrawTwo(newCard("C6"), newCard("HQ"));
		hit(testGame.getPlayer(),"H3");
		hit(testGame.getDealer(),"C3");
		testGame.getDealer().stand();
		testGame.getPlayer().stand();
		assertEquals(d, testGame.determineWinner());
		assertEquals(19, testGame.determineWinner().findBestHand().getScore());	
	}
	//player lost because of busting, without caring about what dealer got. Win: dealer
	public void testDetermineWinner5() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(newCard("C10"), newCard("D6"));
		hit(testGame.getPlayer(),"H9");
		assertEquals(d, testGame.determineWinner());
		assertEquals(null, testGame.determineWinner().findBestHand());	
	}
	//player wins because dealer bust, player no
	public void testDetermineWinner6() {
		Game testGame = new Game();
		Player d = testGame.getPlayer();
		testGame.playerDrawTwo(newCard("C10"), newCard("D6"));
		testGame.dealerDrawTwo(newCard("C6"), newCard("HQ"));
		hit(testGame.getDealer(),"SQ");
		assertEquals(d, testGame.determineWinner());
		assertEquals(16, testGame.determineWinner().findBestHand().getScore());
	}
}
