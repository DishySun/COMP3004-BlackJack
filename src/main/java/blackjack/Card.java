package blackjack;

public class Card {
	public enum Suit {S,H,C,D};
	private Suit suit;
	private int rank;
	
	//constructer
	public Card(Suit suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	//getters
	public Suit getSuit() {return suit;}
	public int getRank() {return rank;}
	
	//toString
	public String toString() {
		String c = suit.toString();
		switch (rank) {
			case 1: c += "A";
			case 11: c += "J";
			case 12: c += "Q";
			case 13: c += "K";
			default: c += Integer.toString(rank);
		}
		return c;
	}
}
