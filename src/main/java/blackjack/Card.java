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
	public Card(String c) {
		String s = c.substring(0,1);
		String r = c.substring(1);
		suit = Suit.valueOf(s);
		if (r.equals("A")) rank = 1;
		else if (r.equals("J")) rank = 11;
		else if (r.equals("Q")) rank = 12;
		else if (r.equals("K")) rank = 13;
		else rank = Integer.parseInt(r);
	}

	//getters
	public Suit getSuit() {return suit;}
	public int getRank() {return rank;}
	
	//toString
	public String toString() {
		String c = suit.toString();
		switch (rank) {
			case 1: c += "A";
					break;
			case 11: c += "J";
					break;
			case 12: c += "Q";
					break;
			case 13: c += "K";
					break;
			default: c += Integer.toString(rank);
		}
		return c;
	}
}
