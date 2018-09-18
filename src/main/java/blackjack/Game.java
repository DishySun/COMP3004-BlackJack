package blackjack;

public class Game {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	
	public Game() {
		player = new Player();
		dealer = new Dealer();
	}
	public Game(Player player) {
		this.player = player;
		dealer = new Dealer();
		deck = new Deck();
	}
	
	
	//getters
	public Player getPlayer(){return player;}
	public Dealer getDealer(){return dealer;}
	public Deck getDeck() {return deck;}
	
	//methods from Deck
	public void iniDeck() {deck.iniDeck();}
	
	//methods from Participant
	public void playerDrawTwo(Card c1, Card c2) {player.drawTwoToStart(c1, c2);}
	public void dealerDrawTwo(Card c1, Card c2) {dealer.drawTwoToStart(c1, c2);}
	public void playerSplit() {player.split(deck.draw(), deck.draw());}
	public void dealerSplit() {dealer.split(deck.draw(), deck.draw());}
	public void playerHit() {player.hit(deck.draw());}
	public void dealerHit() {dealer.hit(deck.draw());}
	public void playerStand() {player.stand();}
	public void dealerStand() {dealer.stand();}
	
	//Game methods
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
	
	
	public Participant determineWinner() {
		if (player.findBestHand() == null) return dealer;
		if (dealer.findBestHand() == null) return player;
		if (player.findBestHand().getScore() > dealer.findBestHand().getScore()) return player;	
		else return dealer;
	}

	
	
}
