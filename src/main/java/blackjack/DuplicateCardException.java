package blackjack;

public class DuplicateCardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Card card;
	public DuplicateCardException(Card card) {
		this.card = card;
	}
	public Card getCard() {return card;}
}
