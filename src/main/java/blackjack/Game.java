package blackjack;

public class Game {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	
	public Game(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
		deck = new Deck();
	}
	
	//getters
	public Player getPlayer(){return player;}
	public Dealer getDealer(){return dealer;}
	public Deck getDeck() {return deck;}
	
	
	
	private void drawTwoAtBeginnin() {
		player.drawTwoToStart(deck.draw(), deck.draw());
		dealer.drawTwoToStart(deck.draw(), deck.draw());
	}
	private Boolean canPlayerSplit() {
		if(player.getFirstHand().getHand().get(0).getRank() == player.getFirstHand().getHand().get(1).getRank())return true;
		else return false;
	}
	private Boolean canDealerSplit() {
		if(dealer.getFirstHand().getHand().get(0).getRank() == dealer.getFirstHand().getHand().get(1).getRank())return true;
		else return false;
	}
	private Boolean isGameFinish() {
		if (player.isFinish() && dealer.isFinish()) return true;
		else return false;
	}
	private void playerSplit() {player.split(deck.draw(), deck.draw());}
	private void dealerSplit() {dealer.split(deck.draw(), deck.draw());}
	private void playerHit() {player.hit(deck.draw());}
	private void dealerHit() {dealer.hit(deck.draw());}
	private void playerStand() {player.stand();}
	private void dealerStand() {dealer.stand();}
	
	private void playerTurn() {}
	private void dealerTurn() {}
	private Participant determineWinner() {}
	public Participant startConsoleGame() {}
	public Participant startFileGame(String fileName) {}
	
	
}
