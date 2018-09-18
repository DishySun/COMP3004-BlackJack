package blackjack;
import junit.framework.TestCase;

public class GameTest extends TestCase{
	//player get Black Jack but dealer not. Win: player
	public void testDetermineWinner1() {
		Game testGame = new Game();
		Player p = testGame.getPlayer();
		testGame.playerDrawTwo(new Card("SA"), new Card("DQ"));
		testGame.dealerDrawTwo(new Card("C9"), new Card("H2"));
		assertEquals(p, testGame.determineWinner());
		assertEquals(21, testGame.determineWinner().findBestHand().getScore());	
	}
	//player and dealer both get Black Jack. Win: dealer
	public void testDetermineWinner2() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(new Card("SA"), new Card("DQ"));
		testGame.dealerDrawTwo(new Card("CA"), new Card("H10"));
		assertEquals(d, testGame.determineWinner());
		assertEquals(21, testGame.determineWinner().findBestHand().getScore());	
	}
	//player get higher score than dealer without busting Win: player
	public void testDetermineWinner3() {
		Game testGame = new Game();
		Player p = testGame.getPlayer();
		testGame.playerDrawTwo(new Card("C10"), new Card("D6"));
		testGame.dealerDrawTwo(new Card("C6"), new Card("HQ"));
		testGame.getPlayer().hit(new Card("H3"));
		testGame.getDealer().hit(new Card("C2"));
		assertEquals(p, testGame.determineWinner());
		assertEquals(19, testGame.determineWinner().findBestHand().getScore());	
	}
	//player get same score with dealer. Win: dealer
	public void testDetermineWinner4() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(new Card("C10"), new Card("D6"));
		testGame.dealerDrawTwo(new Card("C6"), new Card("HQ"));
		testGame.getPlayer().hit(new Card("H3"));
		testGame.getDealer().hit(new Card("C3"));
		assertEquals(d, testGame.determineWinner());
		assertEquals(19, testGame.determineWinner().findBestHand().getScore());	
	}
	//player lost because of busting, without caring about what dealer got. Win: dealer
	public void testDetermineWinner5() {
		Game testGame = new Game();
		Dealer d = testGame.getDealer();
		testGame.playerDrawTwo(new Card("C10"), new Card("D6"));
		testGame.getPlayer().hit(new Card("H9"));
		assertEquals(d, testGame.determineWinner());
		assertEquals(0, testGame.determineWinner().findBestHand().getScore());	
	}
}
