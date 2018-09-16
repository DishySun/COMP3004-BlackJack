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
	//public ArrayList<Card> getHand() {return hand;}
	public int getScore() {return score;}
	public Boolean isStand() {return stand;}
	public Boolean isBust() {return bust;}
	
	private void calculateScore() {}
	
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
	public String toString() {}
}
