//file name Card.java
package blackjack;

public class InvalidCardException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rank;
	private String suit;
	private String errMsg;
	
	public InvalidCardException(String s) {
		suit = s;
		errMsg = s + " is not a valid suit.";
	}
	
	public InvalidCardException(int r) {
		rank = r;
		errMsg = r + " is not a valid rank.";
	}

	public InvalidCardException(int r, String s) {
		rank = r;
		suit = s;
		errMsg = s + " is not a valid suit and "+r+" is not a valid rank.";
	}
	public int getRank() {return rank;}
	public String getSuit() {return suit;}
	public String getErrMsg() {return errMsg;}

}
