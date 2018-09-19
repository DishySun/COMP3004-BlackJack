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
	
	/*private Card newCard(Card.Suit suit, int rank) {
		Card card;
		try {
			card = new Card(suit, rank);
			return card;
		}catch(InvalidCardException e) {
			return null;
		}
		
	}*/
	//private Card newCard(String str) {}
	
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
	/*public void iniFDeck(String fileName) {
		String path = "src/main/resources/" + fileName;
		String line = null;
		String a = null;
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader =
	            new FileReader(path);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader =
	            new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	            a = line;
	        }

	        // Always close files.
	        bufferedReader.close();
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" +
	            fileName + "'");
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file '"
	            + fileName + "'");
	        // Or we could just do this:
	        // ex.printStackTrace();
	    }
	    //Stack<String> arr = new Stack<String>(Arrays.asList(a.split("\\s+"));
	    String[] arr = a.split("\\s+");
	    Stack<String> strList = new Stack<String>();
	    strList.addAll(Arrays.asList(arr));
	    while (strList.size() > 0) {deck.push(new Card(strList.pop()));}
	}*/
	
	public Card draw() throws DrawEmptyDeckException{
		if (deck.size() == 0)throw new DrawEmptyDeckException();
		return deck.pop();
		
	}
	
}
