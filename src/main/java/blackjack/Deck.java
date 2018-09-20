package blackjack;
import java.util.Stack;
import java.util.Collections;


public class Deck {
	private Stack<Card> deck;
	
	//constructor 
	public Deck() {
		deck = new Stack<Card>();
	}
	
	public int size() {return deck.size();}
	
	
	//deck initialization
	//console: add all 52 card and shuffle
	public void iniDeck() {
		//initial deck with 52 card and randomly shuffle
		for(Card.Suit s: Card.Suit.values()) {
			for (int i = 1; i < 14; i++) {
				try {
					Card c = new Card(s, i);
					deck.push(c);
				}catch (InvalidCardException e) {
					System.out.println(e.getErrMsg());
				}
				
			}
		}
		Collections.shuffle(deck);
	}
	
	//file: read from a file
	public void iniDeck(Stack<String> s) {
		while (s.size() > 0) {
			try{
				deck.push(new Card(s.pop()));
			}catch (InvalidCardException e) {
				System.out.println(e.getErrMsg());
				e.printStackTrace();
			}
		}
	}

	
	public Card draw() throws DrawEmptyDeckException{
		if (deck.size() == 0)throw new DrawEmptyDeckException();
		return deck.pop();
		
	}
	
}
