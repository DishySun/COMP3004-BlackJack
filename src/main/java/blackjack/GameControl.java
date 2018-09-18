package blackjack;

public class GameControl {
	private Game game;
	private View view;
	
	public GameControl() {
		view = new View();
	}
	
	private void createGame() {
		String name = view.readString("Welcome to COMP3004 A1 Black Jack Game!\nI am your navigator Haiyue Sun\nCoule you tell me your name please? ");
		if (name.equals("")) game = new Game();
		else game = new Game(name);
		System.out.println("OK "+ game.getPlayer().getName() + ". Nice to meet you.");
		selectGameInput();
	}
	private void selectGameInput() {
		int i = view.readGameType();
		Participant winner = null;
		switch (i) {
		case 1: 	winner = startConsoleGame();
				break;
		case 2: 	winner = startFileGame();
				break;
		default: winner = startConsoleGame();
		}
		announceWinner(winner);
	}
	
	private void playerTurn() {
		while(!game.getPlayer().isFinish()) {
			int i = view.readChoice(game.canPlayerSplit());
			switch(i) {
			case 1:	game.playerHit();
					break;
			case 2:	game.playerStand();
					break;
			case 3: game.playerSplit();
					break;
			default:	game.playerStand();
			}
			printGame();
		}
	}
	private void dealerTurn() {
		while(!game.getDealer().isFinish()) {
			String s = game.getDealer().getChoice();
			if (s.equalsIgnoreCase("D")) {
				System.out.println("Dealer decided to Split.");
				game.dealerSplit();
			}
			else if (s.equalsIgnoreCase("H")) {
				System.out.println("Dealer decided to Hit.");
				game.dealerHit();
			}
			else {
				System.out.println("Dealer decided to Stand.");
				game.dealerStand();
			}
		}
		printGame();
		view.pause();
	}
	
	private Participant startConsoleGame() {
		game.iniDeck();
		game.drawTwoAtBeginnin();
		printGame();
		Participant a = game.determineWinner();
		if(a != null) return a;
		playerTurn();
		dealerTurn();
		return game.determineWinner();
	}
	private Participant startFileGame() {return null;}
	private void announceWinner(Participant p) {
		System.out.println("\nThe winner is " + p.getName() + "!");
		if (p == game.getPlayer()) System.out.println("Congratulations! You win!");
		else System.out.println(p.getName() + " beats you.");
	}
	private void printGame() {
		System.out.println(game);
	}
	
	public void launch() {
		createGame();
	}
	
	public static void main(String[] args) {
		GameControl bjgame = new GameControl();
		bjgame .launch();
	}

}
