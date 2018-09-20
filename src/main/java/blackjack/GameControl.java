package blackjack;
import java.io.*;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.ArrayList;

public class GameControl {
	private Game game;
	private View view;
	
	public GameControl() {
		view = new View();
	}
	
	private void createGame() {
		String name = view.readString("Coule you tell me your name please? ");
		if (name.equals("")) game = new Game();
		else game = new Game(name);
		System.out.println("OK "+ game.getPlayer().getName() + ". Nice to meet you.");
	}
	private void selectGameInput() {
		System.out.println("Welcome to COMP3004 A1 Black Jack Game!\nI am your navigator Haiyue Sun.");
		int i = view.readGameType();
		Participant winner = null;
		switch (i) {
		case 1: 	winner = startConsoleGame();
				break;
		case 2: 	ArrayList<String> scenario = readFile(view.readString("Please enter the file name you want to load: "));
				if (scenario != null) {
					winner = startFileGame(scenario);
					break;
				}
		default: winner = startConsoleGame();
		}
		announceWinner(winner);
	}
	
	private void playerTurn(Stack<String> choice) {
		while (!game.getPlayer().isFinish()) {
			String c;
			try{
				c = choice.pop();
			}catch(EmptyStackException e) {
				c = "S";
				e.printStackTrace();
			}
			if (c.equals("H")) {
				System.out.println("Player chooses to Hit.");
				game.playerHit();
			}
			if (c.equals("S")) {
				System.out.println("Player chooses to Stand.");
				game.playerStand();
			}
			if (c.equals("D")) {
				System.out.println("Player chooses to Split.");
				game.playerSplit();
			}
			printGame();
		}
		System.out.println("Player's turn finished. Now is dealer's turn.");
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
		view.pause("Player's turn finished. Now is dealer's turn.");
	}
	
	private void dealerTurn(Boolean isTest) {
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
			if(!isTest)view.pause();
		}
	}
	
	private Participant startConsoleGame() {
		System.out.println("\n--------Start a game with console input--------");
		createGame();
		game.iniDeck();
		game.drawTwoAtBeginnin();
		printGame();
		Participant a = game.determineWinner();
		if(a != null) return a;
		playerTurn();
		dealerTurn(false);
		return game.determineWinner();
	}
	
	
	
	private Participant startFileGame(ArrayList<String> scenario) {
		ArrayList<String> d = new ArrayList<String>();
		Stack<String> scenarioDeck = new Stack<String>();
		Stack<String> scenarioPlayerChoice = new Stack<String>();
		for (String s : scenario) {
			if (s.length() < 2) scenarioPlayerChoice.push(s);
			else d.add(s);
		}
		ArrayList<String> c = new ArrayList<String>();
		while (scenarioPlayerChoice.size() > 0) c.add(scenarioPlayerChoice.pop());
		scenarioDeck.addAll(d);
		scenarioPlayerChoice.addAll(c);
		game = new Game("Puppet Player(AI)");
		System.out.println("\n--------Start a game with file input--------");
		game.iniDeck(scenarioDeck);
		game.drawTwoAtBeginnin();
		printGame();
		Participant a = game.determineWinner();
		if(a != null) return a;
		playerTurn(scenarioPlayerChoice);
		dealerTurn(true);
		return game.determineWinner();
	}
	
	private void announceWinner(Participant p) {
		System.out.println("\nThe winner is " + p.getName() + "!");
		if (p == game.getPlayer()) System.out.println("Congratulations! You win!");
		else System.out.println(p.getName() + " beats you.");
	}
	private void printGame() {
		System.out.println(game);
	}
	
	private ArrayList<String> readFile(String fileName) {
		//String fileName = view.readString("Please enter the file name you want to load: ");
		while(true) {
			String path = "src/main/resources/"+fileName;
			String line = null;
			String a = "";
			try {
		        // FileReader reads text files in the default encoding.
		        FileReader fileReader = new FileReader(path);
		        // Always wrap FileReader in BufferedReader.
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        	while((line = bufferedReader.readLine()) != null) {
		            a += line;
	        		}
		        // Always close files.
		        bufferedReader.close();
		        String[] arr = a.split("\\s+");
			    ArrayList<String> strList = new ArrayList<String>();
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
		selectGameInput();
	}
	
	public static void main(String[] args) {
		GameControl bjgame = new GameControl();
		bjgame .launch();
	}
	
	public Participant testFileGame(String fileName) {
		return this.startFileGame(this.readFile(fileName));
	}
	public Player getPlayer() {return game.getPlayer();}
	public Dealer getDealer() {return game.getDealer();}

}
