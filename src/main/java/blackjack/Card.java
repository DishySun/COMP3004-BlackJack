package blackjack;

public class Card {
	public enum Suit {S,H,C,D};
	private Suit suit;
	private int rank;
	
	//constructer
	public Card(Suit suit, int rank) throws InvalidCardException{
		if (rank < 1 || rank > 13) throw new InvalidCardException(rank);
		else this.rank = rank;
		this.suit = suit;
	}
	public Card(String c) throws InvalidCardException{
		String s = c.substring(0,1);
		String r = c.substring(1);
		if (r.equals("A")) rank = 1;
		else if (r.equals("J")) rank = 11;
		else if (r.equals("Q")) rank = 12;
		else if (r.equals("K")) rank = 13;
		else if (Integer.parseInt(r) > 13) rank = -1;
		else rank = Integer.parseInt(r);
		
		if (!validSuit(s) && !validRank(rank))  throw new InvalidCardException(rank ,s);
		else if (!validSuit(s)) throw new InvalidCardException(s);
		else if (!validRank(rank)) throw new InvalidCardException(r);
		suit = Suit.valueOf(s);
		
	}
	private boolean validSuit(String test) {
	    for (Suit c : Suit.values()) {
	        if (c.name().equalsIgnoreCase(test)) {
	            return true;
	        }
	    }
	    return false;
	}
	private boolean validRank(int test) {
		if (test > 0 && test < 14) return true;
		return false;
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
