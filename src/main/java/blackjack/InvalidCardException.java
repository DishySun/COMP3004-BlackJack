//file name Card.java
package blackjack;

public class InvalidCardException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	
	public InvalidCardException(int r) {
		errMsg = r + " is not a valid rank.";
	}
	
	public InvalidCardException(String s) {
		errMsg = s + " is not a valid suit.";
	}
	
	public InvalidCardException(String s,boolean b) {
		errMsg = s + " is not a valid rank.";
	}

	public InvalidCardException(String s, String r) {
		errMsg = s + " is not a valid suit and "+r+" is not a valid rank.";
	}
	
	public InvalidCardException(String s, int r) {
		errMsg = s + " is not a valid suit and "+r+" is not a valid rank.";
	}
	public String getErrMsg() {return errMsg;}

}
