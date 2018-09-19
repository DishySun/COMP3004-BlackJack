package blackjack;
import java.util.Stack;

public class Game {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	
	public Game() {
		player = new Player();
		dealer = new Dealer();
	}
	public Game(String name) {
		player = new Player(name);
		dealer = new Dealer();
		
	}
	
	
	//getters
	public Player getPlayer(){return player;}
	public Dealer getDealer(){return dealer;}
	public Deck getDeck() {return deck;}
	
	//methods from Deck
	public void iniDeck() {
		deck = new Deck();
		deck.iniDeck();
	}
	public void iniDeck(Stack<String> s) {
		deck = new Deck();
		deck.iniDeck(s);
	}
	
	/*private Card draw() {
		try {
			return deck.draw();
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			return null;
		}
	}*/
	//methods from Participant
	public void playerDrawTwo(Card c1, Card c2) {
		try{
			player.drawTwoToStart(c1, c2);
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
		}
	}
	public void dealerDrawTwo(Card c1, Card c2) {
		try{
			dealer.drawTwoToStart(c1, c2);
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
			dealer.stand();
		}
	}
	public void playerSplit() {
		try{
			player.split(deck.draw(), deck.draw());
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			player.stand();
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
		}
	}
	public void dealerSplit() {
		try{
			dealer.split(deck.draw(), deck.draw());
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			dealer.stand();
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
			dealer.stand();
		}
	}
	public void playerHit() {
		try{
			player.hit(deck.draw());
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			player.stand();
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
		}
	}
	public void dealerHit() {
		try{
			dealer.hit(deck.draw());
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			dealer.stand();
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
			dealer.stand();
		}
	}

	public void playerStand() {player.stand();}
	public void dealerStand() {dealer.stand();}
	
	//Game methods
	public void drawTwoAtBeginnin() {
		try{
			player.drawTwoToStart(deck.draw(), deck.draw());
			dealer.drawTwoToStart(deck.draw(), deck.draw());
		}catch (DrawEmptyDeckException e) {
			System.out.println("Trying to draw from an empty deck.");
			e.printStackTrace();
			player.stand();
			dealer.stand();
		}catch (DuplicateCardException e) {
			System.out.println(e.getCard() + " is duplicated.");
			e.printStackTrace();
		}
	}
	public Boolean canPlayerSplit() {
		return player.canSplit();
	}
	/*public Boolean canDealerSplit() {
		if(dealer.getFirstHand().getHand().get(0).getRank() == dealer.getFirstHand().getHand().get(1).getRank())return true;
		else return false;
	}*/
	private Boolean isGameFinish() {
		if (player.getFirstHand().getHand().size() < 3 && player.getFirstHand().getScore() == 21) return true;
		if (dealer.getFirstHand().getHand().size() < 3 && dealer.getFirstHand().getScore() == 21) return true;
		if (player.isFinish() && dealer.isFinish()) return true;
		return false;
	}
	
	
	public Participant determineWinner() {
		if (player.findBestHand() == null) return dealer;
		if (dealer.findBestHand() == null) return player;
		if (!isGameFinish() && player.findBestHand().getScore() < 21 && dealer.findBestHand().getScore() <21) return null;
		if (player.findBestHand().getScore() > dealer.findBestHand().getScore()) return player;	
		else return dealer;
	}

	public String toString() {
		String result = player.toString() + "\n" ;
		if (player.isFinish()) result += dealer.toString() ;
		else result += dealer.hideFirstCard();
		result += "\n================================";
		return result;
	}
	
}
