package blackjack;
import junit.framework.TestCase;

public class BlackJackGameTest extends TestCase{
	public void testScenario1() {
		//SK HA HQ CA
		GameControl testGame = new GameControl();
		Participant winner = testGame.testFileGame("File 1.txt");
		assertEquals(testGame.getDealer(), winner);
		assertEquals(21, winner.findBestHand().getScore());
	}
	public void testScenario2() {
		//SK HQ SQ C5 S DJ
		//SK HQ SQ C5 S DJ
		GameControl testGame = new GameControl();
		Participant winner = testGame.testFileGame("File 2.txt");
		assertEquals(testGame.getPlayer(), winner);
		assertEquals(15, winner.findBestHand().getScore());
	}
	public void testScenario3() {
		//S10 D3 SQ C5 H H5 H SA S CA D2
		GameControl testGame = new GameControl();
		Participant winner = testGame.testFileGame("File 3.txt");
		assertEquals(testGame.getPlayer(), winner);
		assertEquals(19, winner.findBestHand().getScore());
	}
	public void testScenario4() {
		//SK HK CQ D9 D H6 C5 H D3 S H D5 S
		GameControl testGame = new GameControl();
		Participant winner = testGame.testFileGame("File 4.txt");
		assertEquals(testGame.getPlayer(), winner);
		assertEquals(20, winner.findBestHand().getScore());
	}
	public void TestScenario5() {
		//SK H9 C5 D5 S H7 CQ SA SQ D2
		GameControl testGame = new GameControl();
		Participant winner = testGame.testFileGame("File 5.txt");
		assertEquals(testGame.getPlayer(), winner);
		assertEquals(19, winner.findBestHand().getScore());
	}
	
}
