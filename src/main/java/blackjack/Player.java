package blackjack;

public class Player extends Participant{
	public Player() {
		super();
	}
	public Player(String name) {
		super(name);
	}
	
	
	public String toString() {
		String result = "Player " + super.name +":\n";
		result += "Hand";
		if (super.getHands().size() > 1) result += " 1: ";
		else result += ": ";
		result += super.getFirstHand().toString();
		if (super.findBestHand() == super.getFirstHand()) result += " (Highest)";
		result +="\n";
		if (super.getHands().size() > 1) {
			result +="Hand 2: " + super.getSplitHand().toString();
			if (super.findBestHand() == super.getSplitHand()) result += " (Highest)";
			result +="\n";
		}
		return result;
	}

}
