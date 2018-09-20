package blackjack;
import java.util.ArrayList;

public abstract class Participant {
	protected String name;
	protected ArrayList<Hand> hands;
	protected Boolean finish;
	
	protected Participant() {
		name = "No Name";
		hands = new ArrayList<Hand>();
		finish = false;
	}
	public Participant(String name) {
		this.name = name;
		hands = new ArrayList<Hand>();
		finish = false;
	}
	
	//getters
	public String getName() {return name;}
	public ArrayList<Hand> getHands(){return hands;}
	public Hand getFirstHand() {return hands.get(0);}
	public Hand getSplitHand() {return hands.get(1);}
	public Boolean isFinish() {return finish;}
	
	//algorithm
	public void drawTwoToStart(Card c1, Card c2) throws DuplicateCardException{
		hands.add(new Hand());
		if (c1.compareTo(c2)) throw new DuplicateCardException(c1);
		getFirstHand().add(c1);
		getFirstHand().add(c2);
		if (getFirstHand().getScore() == 21) finish = true;
		else finish = false;
	}
	//protected abstract Boolean canSplit();
	public void split(Card c1, Card c2) throws DuplicateCardException{
		hands.add(new Hand());
		if (c1.compareTo(c2)) throw new DuplicateCardException(c1);
		for (Card c : getFirstHand().getHand()) {
			if (c.compareTo(c1) || c.compareTo(c2)) throw new DuplicateCardException(c);
		}
		getSplitHand().add(getFirstHand().remove());
		getFirstHand().add(c1);
		getSplitHand().add(c2);
		if(getFirstHand().getScore() == 21 || getSplitHand().getScore() == 21) finish = true;
	}
	public int hit(Card c) throws DuplicateCardException{
		for (Card card : getFirstHand().getHand()) {
			if (c.compareTo(card)) throw new DuplicateCardException(c);
		}
		if(hands.size()>1) {
			for (Card card : getSplitHand().getHand()) {
				if (c.compareTo(card)) throw new DuplicateCardException(c);
			}
		}
		if (getFirstHand().isBust() || getFirstHand().isStand()) {
			getSplitHand().add(c);
			if (getSplitHand().isBust()) finish = true;
			return 1;
		} else {
			getFirstHand().add(c);
			if (getFirstHand().isBust() && hands.size()<2) finish = true;
			return 0;
		}
	}
	public int stand() {
		if (hands.size() > 1 && !getFirstHand().isBust() && !getFirstHand().isStand()) {
			getFirstHand().standThis();
			return 0;
		}
		else {
			finish = true;
			return 1;
		}
	}
	public Hand findBestHand() {
		if (hands.size() == 0) return null;
		Hand h1 = getFirstHand();
		if (hands.size()<2) {
			if (h1.getScore() <= 21) return h1;
			else return null;
		}
		Hand h2 = getSplitHand();
		if (h1.getScore() > 21 && h2.getScore() > 21) return null;
		if (h1.getScore() > 21) return h2;
		if (h2.getScore() > 21) return h1;
		if (h1.getScore() > h2.getScore()) return h1;
		else return h2;
	}
	public abstract String toString();
	
}
