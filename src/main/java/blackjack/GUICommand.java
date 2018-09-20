package blackjack;

public class GUICommand {
	public enum Type {SYSTEM, PARTICIPANT}
	public Type type;
	public enum Object {PLAYER,DEALER}
	private Object object;
	public enum Action {HIT, STAND, SPLIT, DRAW}
	private Action action;
	public enum SystemAction {END, WINNER}
	private SystemAction systemAction;
	private Card card;
	private int toHand;
	private static Type SYSTEM = Type.SYSTEM;
	private static Type PARTICIPANT = Type.PARTICIPANT;
	private static SystemAction END = SystemAction.END;
	private static SystemAction WINNER = SystemAction.WINNER;
	
	public GUICommand(Object obj, Action act, Card c, int to) {
		object = obj;
		action = act;
		card = c;
		toHand = to;
		type = PARTICIPANT;
	}
	
	public GUICommand(SystemAction sysAct, Object obj) {
		
			systemAction = sysAct;
			object = obj;
			type = SYSTEM;
		
	}
	
	
	public Type getType() {return type;}
	public Object getObj() {return object;}
	public Action getAction() {return action;}
	public Card getCard() {return card;}
	public SystemAction getSystemAction() {return systemAction;}
	public int to() {return toHand;}
	public String toString() {
		String result="";
		if (type == SYSTEM) {
			result += "System: ";
			if(systemAction == END) { 
				result += "ends ";
				if (object == Object.PLAYER) result+="Player's turn";
				else result +="Dealer's turn";
			}else if (systemAction == WINNER) {
				if (object == Object.PLAYER) result+="Player ";
				else result +="Dealer ";
				result += "won";
			}
		}else {
			if (object == Object.PLAYER) result += "Player ";
			else result+= "Dealer ";
			if (action == Action.STAND) result += "stands.";
			else if(action == Action.SPLIT) result += "splits.";
			else if (action == Action.DRAW) result += " has drew " + card;
			else result += " has hit "+card;
		}
		return result;
	}
}
