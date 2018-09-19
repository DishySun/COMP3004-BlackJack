package blackjack;
import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int score;
	private Boolean stand;
	private Boolean bust;
	
	//constructor
	public Hand() {
		hand = new ArrayList<Card>();
		score = 0;
		stand = false;
		bust = false;
	}
	
	//getters
	public ArrayList<Card> getHand() {return hand;}
	public int getScore() {return score;}
	public Boolean isStand() {return stand;}
	public Boolean isBust() {return bust;}
	public Boolean isFinish() {return (bust || stand);}
	public Boolean isSoft17() {
		if (score == 17) {
			int i = 0;
			for (Card c : hand) {i += c.getRank();}
			if (i <= 7) return true;
		}
		return false;
	}
	
	private void calculateScore() {
		int amountOfAce = 0;
		int s = 0;
		for (Card c : this.hand) {
			if (c == null) break;
			if (c.getRank() == 1) { 
				s += 11;
				amountOfAce += 1;
			} else if(c.getRank() > 10) s+= 10;
			else s += c.getRank();
		}
		if (s > 21 && amountOfAce > 0) {
			while (amountOfAce > 0) {
				amountOfAce -=1;
				s -= 10;
				if (s <= 21) break;
			}
		}
		score = s;
		if (score > 21) bust = true;
	}
	
	public void add(Card c) {
		hand.add(c);
		calculateScore();
	}
	public Card remove() {
		Card c = hand.remove(hand.size() -1);
		calculateScore();
		return c;
	}
	public void standThis() {stand = true;}
	public String toString() {
		String result = "";
		result += hand.toString();
		if (score == 21 && hand.size() < 3) result += " BLACK JACK! ";
		else result+= (" Score: " + score);
		if (bust) result += "(Bust!)";
		return result;
	}
}
