package blackjack;
import junit.framework.TestCase;

public class BlackJackGameTest extends TestCase{
	public void scenario1() {
		//SK HA HQ CA
		Player testPlayer = new Player();
		Dealer testDealer = new Dealer();
		Game testGame = new Game();
		Participant winner = testGame.startFileGame("File 1.txt");
		assertEquals(testDealer, winner);
		assertEquals(21, winner.findBestHand().getScore());
	}
	public void scenario2() {
		//SK HQ SQ C5 S DJ
		Player testPlayer = new Player();
		Dealer testDealer = new Dealer();
		Game testGame = new Game();
		Participant winner = testGame.startFileGame("File 2.txt");
		assertEquals(testPlayer, winner);
		assertEquals(15, winner.findBestHand().getScore());
	}
	public void scenario3() {
		//S10 D3 SQ C5 H H5 H SA S CA D2
		Player testPlayer = new Player();
		Dealer testDealer = new Dealer();
		Game testGame = new Game();
		Participant winner = testGame.startFileGame("File 3.txt");
		assertEquals(testPlayer, winner);
		assertEquals(19, winner.findBestHand().getScore());
	}
	public void scenario4() {
		//SK HK CQ D9 D H6 C5 H D3 S H D5 S
		Player testPlayer = new Player();
		Dealer testDealer = new Dealer();
		Game testGame = new Game();
		Participant winner = testGame.startFileGame("File 4.txt");
		assertEquals(testPlayer, winner);
		assertEquals(20, winner.findBestHand().getScore());
	}
	public void scenario5() {
		//SK H9 C5 D5 S H7 CQ SA SQ D2
		Player testPlayer = new Player();
		Dealer testDealer = new Dealer();
		Game testGame = new Game();
		Participant winner = testGame.startFileGame("File 4.txt");
		assertEquals(testPlayer, winner);
		assertEquals(19, winner.findBestHand().getScore());
	}
}
