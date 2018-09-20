package blackjack;

public class FileIntegrityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number;
	private String suit;
	public FileIntegrityException(String suit, int i) {
		number = i;
		this.suit = suit;
	}
	public int getNumber() {return number;}
	public String getSuit() {return suit;}

}
