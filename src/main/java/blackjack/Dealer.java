package blackjack;

public class Dealer extends Participant{
	public Dealer() {
		super("Computer(Very Hard)");
	}
	public String toString() {
		String result = "Dealer " + super.name +":\n";
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
