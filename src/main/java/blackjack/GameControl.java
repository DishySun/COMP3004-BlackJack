package blackjack;

public class GameControl {
	private Game game;
	private View view;
	
	public GameControl() {
		view = new View();
	}
	private void createGame() {
		String name = view.readString("Welcome to COMP3004 A1 Black Jack Game!\nI am your navigator Haiyue Sun\nCoule you tell me your name please?");
		if (name.equals("")) game = new Game();
		else game = new Game(name);
		System.out.println("OK "+ game.getPlayer().getName() + "Nice to meet you. Prepearing an opponent to you.");
	}
	public void launch() {
		createGame();
	}

}
