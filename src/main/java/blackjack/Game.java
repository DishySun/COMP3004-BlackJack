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
	
	
	//console deck
	public void iniDeck() {deck.iniCDeck();};
	//file deck
	//public void iniDeck(String fileName) {deck.iniFDeck(fileName);}
	
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
	
	public Participant determineWinner() {}
	
	
}
