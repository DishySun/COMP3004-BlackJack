package blackjack;

public class Dealer extends Participant{
	public Dealer() {
		super("Computer(Very Hard)");
	}
	public String getChoice() {
		if (super.canSplit()) return "D";
		Hand currentHand;
		if (hands.size() < 2) currentHand = this.getFirstHand();
		else if (this.getFirstHand().isFinish()) currentHand = this.getSplitHand();
		else currentHand = this.getFirstHand();
		if (currentHand.getScore() < 17) return "H";
		if (currentHand.getScore() > 17) return "S";
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
