package blackjack;
import java.util.HashSet;
import java.util.Scanner;

public class View {
	public int readInt(String s) {
		Scanner input = new Scanner(System.in);
		System.out.print(s);
		while (true) {
	        try {
	            return input.nextInt();
	        }
	        catch (java.util.InputMismatchException e) {
	        		System.out.print("An integer input is expected: ");
	            input.nextLine();
	        }
	    }
	}
	
	
	public String readString(String s) {
		Scanner input = new Scanner(System.in);
		System.out.print(s);
		return input.next();
	}
	
	public int readGameType() {
		System.out.println("What game type would you like?");
		System.out.println("Console Input (Enter \"1\" or \"C\")");
		System.out.println("File Input (Enter \"2\" or \"F\")");
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while(true) {
			try {
				choice = input.nextInt();
				switch (choice) {
				case 1: return 1;
				case 2: return 2;
				default: System.out.print("You can only choose from 1 - 2: ");
				}
			}catch(java.util.InputMismatchException e) {
				String c = input.next();
				if (c.equalsIgnoreCase("c") ) return 1;
				if (c.equalsIgnoreCase("f") ) return 2;
				System.out.print("You can only choose from \"C\" or \"F\": ");
			}
			input.nextLine();
		}
	}
	
	public int readChoice(Boolean canSplit) {
		System.out.println("What you gonna do?");
		System.out.println("Hit (Enter \"1\" or \"H\")");
		System.out.println("Stand (Enter \"2\" or \"S\")");
		if (canSplit) System.out.println("Hit (Enter \"3\" or \"D\")");
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while (true) {
			try {
				choice = input.nextInt();
				switch(choice) {
				case 1: return 1;
				case 2: return 2;
				case 3: {if(canSplit) return 3;}
				default: System.out.print("You can only choose from 1 - 2: ");
				}
			}catch(java.util.InputMismatchException e) {
				String c = input.next();
				if (c.equalsIgnoreCase("h") ) return 1;
				if (c.equalsIgnoreCase("s") ) return 2;
				if (c.equalsIgnoreCase("d") && canSplit) return 3;
				System.out.print("You can only choose from \"H\" \"S\"");
				if(canSplit) System.out.print(" \"D\": ");
				else System.out.print(": ");
			}
			input.nextLine();
		}
	}
	
	public void pause() {System.out.print(" ---- Press enter to continue...");}
	public void pause(String s) {
		System.out.print(s + " ---- Press enter to continue...");
	}

}
