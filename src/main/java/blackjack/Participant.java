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
	public Boolean isFinish() {return finish;}
	
	//algorithm
	public void drawTwoToStart() {}
	public void split() {}
	public void hit() {}
	public void stand() {}
	public Hand findBestHand() {}
	public abstract String toString() {}
	
}
