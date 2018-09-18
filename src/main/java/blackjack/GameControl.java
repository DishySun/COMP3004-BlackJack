package blackjack;
import java.io.*;
import java.util.Arrays;
import java.util.Stack;

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
		case 2: 	Stack<String> scenario = readFile(view.readString("Please enter the file name you want to load: "));
				if (scenario != null) {
					winner = startFileGame(scenario);
					break;
				}
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
		printGame();
		view.pause("Player's turn finished. Now is dealer's turn.");
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
			printGame();
			view.pause();
		}
	}
	
	private Participant startConsoleGame() {
		System.out.println("\n--------Start a game with console input--------");
		game.iniDeck();
		game.drawTwoAtBeginnin();
		printGame();
		Participant a = game.determineWinner();
		if(a != null) return a;
		playerTurn();
		dealerTurn();
		return game.determineWinner();
	}
	
	private Participant startFileGame(Stack<String> scenario) {
		return null;
	}
	
	private void announceWinner(Participant p) {
		System.out.println("\nThe winner is " + p.getName() + "!");
		if (p == game.getPlayer()) System.out.println("Congratulations! You win!");
		else System.out.println(p.getName() + " beats you.");
	}
	private void printGame() {
		System.out.println(game);
	}
	
	private Stack<String> readFile(String fileName) {
		//String fileName = view.readString("Please enter the file name you want to load: ");
		while(true) {
			String path = "src/main/resources/"+fileName;
			String line = null;
			String a = null;
			try {
		        // FileReader reads text files in the default encoding.
		        FileReader fileReader =
		            new FileReader(path);
	
		        // Always wrap FileReader in BufferedReader.
		        BufferedReader bufferedReader =
		            new BufferedReader(fileReader);
		            a = line;
		        // Always close files.
		        bufferedReader.close();
		        String[] arr = a.split("\\s+");
			    Stack<String> strList = new Stack<String>();
			    strList.addAll(Arrays.asList(arr));
				return strList;
		    }
		    catch(FileNotFoundException ex) {
		        System.out.println("Unable to open file '" + fileName + "'");
		        fileName = view.readString("Enter again or enter '-1' to start a console game: ");
		        if (fileName.equals("-1")) break;
		    }
		    catch(IOException ex) {
		        System.out.println("Error reading file '"+ fileName + "'");
		    }
		}

		return null;
	}
	
	public void launch() {
		createGame();
	}
	
	public static void main(String[] args) {
		GameControl bjgame = new GameControl();
		bjgame .launch();
	}

}
