package blackjack;

public class Game {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	
	public Game(Player player) {
		this.player = player;
		dealer = new Dealer();
		deck = new Deck();
	}
	
	//getters
	public Player getPlayer(){return player;}
	public Dealer getDealer(){return dealer;}
	public Deck getDeck() {return deck;}
	
	//deck methods
	public void iniDeck() {deck.iniDeck();}
	
	public Card draw() {return deck.draw();}
	public void drawTwoAtBeginnin() {
		player.drawTwoToStart(deck.draw(), deck.draw());
		dealer.drawTwoToStart(deck.draw(), deck.draw());
	}
	public Boolean canPlayerSplit() {
		if(player.getFirstHand().getHand().get(0).getRank() == player.getFirstHand().getHand().get(1).getRank())return true;
		else return false;
	}
	public Boolean canDealerSplit() {
		if(dealer.getFirstHand().getHand().get(0).getRank() == dealer.getFirstHand().getHand().get(1).getRank())return true;
		else return false;
	}
	public Boolean isGameFinish() {
		if (player.isFinish() && dealer.isFinish()) return true;
		else return false;
	}
	public void playerSplit() {player.split(deck.draw(), deck.draw());}
	public void dealerSplit() {dealer.split(deck.draw(), deck.draw());}
	public void playerHit() {player.hit(deck.draw());}
	public void dealerHit() {dealer.hit(deck.draw());}
	public void playerStand() {player.stand();}
	public void dealerStand() {dealer.stand();}
	
	public Participant determineWinner() {}

	
	
}
