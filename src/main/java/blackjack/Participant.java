package blackjack;
import java.util.ArrayList;

public abstract class Participant {
	protected String name;
	protected ArrayList<Hand> hands;
	protected Boolean finish;
	
	public Participant() {
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
	public void drawTwoToStart(Card c1, Card c2) {
		hands.add(new Hand());
		getFirstHand().add(c1);
		getFirstHand().add(c2);
		if (getFirstHand().getScore() == 21) finish = true;
		else finish = false;
	}
	public void split(Card c1, Card c2) {
		hands.add(new Hand());
		getSplitHand().add(getFirstHand().remove());
		getFirstHand().add(c1);
		getSplitHand().add(c2);
		if(getFirstHand().getScore() == 21 || getSplitHand().getScore() == 21) finish = true;
	}
	public void hit(Card c) {
		if (getFirstHand().isBust() || getFirstHand().isStand()) {
			getSplitHand().add(c);
			if (getSplitHand().isBust()) finish = true;
		} else {
			getFirstHand().add(c);
			if (getFirstHand().isBust() && hands.size()<2) finish = true;
		}
	}
	public void stand() {}
	public Hand findBestHand() {}
	public abstract String toString() {}
	
}
