package blackjack;

import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class BlackJackControl {
	private Game game;
	private LinkedList<GUICommand> list;
	private static GUICommand.Object PLAYER = GUICommand.Object.PLAYER;
	private static GUICommand.Object DEALER = GUICommand.Object.DEALER;
	private static GUICommand.Action HIT = GUICommand.Action.HIT;
	private static GUICommand.Action STAND = GUICommand.Action.STAND;
	private static GUICommand.Action DRAW = GUICommand.Action.DRAW;
	private static GUICommand.Action SPLIT = GUICommand.Action.SPLIT;
	private static GUICommand.SystemAction END = GUICommand.SystemAction.END;
	private static GUICommand.SystemAction WINNER = GUICommand.SystemAction.WINNER;
	
	public void creatConsoleGame(String name, LinkedList<GUICommand> l) {
		list = l;
		game = new Game(name);
		game.iniDeck();
		playerDrawTwo();
		dealerDrawTwo();
		Participant winner = game.determineWinner();
		if (winner != null) {
			if (winner == game.getPlayer()) list.push(new GUICommand(WINNER,PLAYER));
			else list.push(new GUICommand(WINNER,DEALER));
			return;
		}
		//for (GUICommand g : list) {System.out.println(g);}
	}
	public void creatFileGame(ArrayList<String> scenario, LinkedList<GUICommand> l) {
		list = l;
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
		game.iniDeck(scenarioDeck);
		playerDrawTwo();
		dealerDrawTwo();
		Participant winner = game.determineWinner();
		if (winner != null) {
			if (winner == game.getPlayer()) list.push(new GUICommand(WINNER,PLAYER));
			else list.push(new GUICommand(WINNER,DEALER));
			return;
		}
		playerTurn(scenarioPlayerChoice);
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
				playerHit();
			}
			if (c.equals("S")) {
				System.out.println("Player chooses to Stand.");
				playerStand();
			}
			if (c.equals("D")) {
				System.out.println("Player chooses to Split.");
				playerSplit();
			}
		}
		dealerTurn();
	}
	private void playerDrawTwo() {
		try {
			Card c1 = game.getDeck().draw();
			Card c2 = game.getDeck().draw();
			game.getPlayer().drawTwoToStart(c1, c2);
			list.push(new GUICommand(PLAYER, DRAW, c1, 0));
			list.push(new GUICommand(PLAYER, DRAW, c2 ,0));
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	private void dealerDrawTwo() {
		try {
			Card c1 = game.getDeck().draw();
			Card c2 = game.getDeck().draw();
			game.getDealer().drawTwoToStart(c1, c2);
			list.push(new GUICommand(DEALER, DRAW, null, 0));
			list.push(new GUICommand(DEALER, DRAW, c2 ,0));
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	public void playerSplit() {
		try {
			Card c1 = game.getDeck().draw();
			Card c2 = game.getDeck().draw();
			game.getPlayer().split(c1, c2);
			list.push(new GUICommand(PLAYER, SPLIT, null, -1));
			list.push(new GUICommand(PLAYER, DRAW, c1, 0));
			list.push(new GUICommand(PLAYER, DRAW, c2 ,1));
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	private void dealerSplit() {
		try {
			Card c1 = game.getDeck().draw();
			Card c2 = game.getDeck().draw();
			game.getDealer().split(c1, c2);
			list.push(new GUICommand(DEALER, SPLIT, null, -1));
			list.push(new GUICommand(DEALER, DRAW, c1, 0));
			list.push(new GUICommand(DEALER, DRAW, c2 ,1));
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	public void playerHit() {
		try {
			Card c1 = game.getDeck().draw();
			int to = game.getPlayer().hit(c1);
			list.push(new GUICommand(PLAYER, HIT, c1, to));
			if (game.getPlayer().getHands().get(to).getScore() > 21)
				list.push(new GUICommand(END,PLAYER));
			update();
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	private void dealerHit() {
		try {
			Card c1 = game.getDeck().draw();
			int to = game.getDealer().hit(c1);
			list.push(new GUICommand(DEALER, HIT, c1, to));
			if (game.getDealer().getHands().get(to).getScore() > 21)
				list.push(new GUICommand(END,DEALER));
		} catch (DuplicateCardException e) {
			e.printStackTrace();
		}catch(DrawEmptyDeckException e) {
			e.printStackTrace();
		}
	}
	public void playerStand() {
		int to = game.getPlayer().stand();
		list.push(new GUICommand(PLAYER, STAND, null, to));
		update();
	}
	private void dealerStand() {
		int to = game.getDealer().stand();
		list.push(new GUICommand(DEALER, STAND, null, to));
	}
	private void update() {
		if (game.getPlayer().isFinish()) dealerTurn();
	}
	private void dealerTurn() {
		while(!game.getDealer().isFinish()) {
			String s = game.getDealer().getChoice();
			if (s.equalsIgnoreCase("D")) {
				System.out.println("Dealer decided to Split.");
				dealerSplit();
			}
			else if (s.equalsIgnoreCase("H")) {
				System.out.println("Dealer decided to Hit.");
				dealerHit();
			}
			else {
				System.out.println("Dealer decided to Stand.");
				dealerStand();
			}
		}
		endGame();
	}
	private void endGame() {
		Participant winner = game.determineWinner();
			if (winner == game.getPlayer()) list.push(new GUICommand(WINNER,PLAYER));
			else list.push(new GUICommand(WINNER,DEALER));
			return;
	}
	
	public boolean canPlayerSplit() {return game.canPlayerSplit();}
	public Card getDealerFirstCard() {return game.getDealer().getFirstHand().getHand().get(0);}
	
}
