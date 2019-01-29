//-----------------------------------------
// Assignment 4
// Written by: Weiliang Xie (40100475)
// For COMP 248 Section P - Fall 2018
//-----------------------------------------

// Though surely unconventional, this class is purely made for readability reasons.
// Until we learn how to implement text via .txt files, this was the best way I could come up with.
// Not the most elegant of ways, but it gets the job done for now.

public class PrintText {
		
	private String text;
		
	public PrintText() {
		text = "";
	}
		
	public void printRules() {
		System.out.println("_______________________________________________________________________________________________");
		System.out.println("                    | WELCOME ONE, WELCOME ALL, TO NANCY'S CRAZY GARDEN GAME |");
		System.out.println("                    ----------------------------------------------------------");
			
		// Displaying the rules.
		System.out.println("\nTHE RULES ARE SIMPLE:");
		System.out.println("\n    1. This game can be played by 2 to 10 people!");
		System.out.println("    2. Each player's garden will be a square of dimension NxN (N must be at least 3!).");
		System.out.println("    3. Each player's garden starts empty, but will gradually be filled by flowers (1 square)"
						 + "\n       or by trees (4 squares - 2x2).");
		System.out.println("    4. Each player will roll 2 dice. The player with the highest roll goes first!"
						 + "\n       (In the event of a tie, EVERYONE rolls again!)");
		System.out.println("    5. Players alternate turns one after the other until there is a winner.");
		System.out.println("    6. During a player's turn, they will roll 2 dice.");
		System.out.println("          a. Based on the roll, there might be a special event!");
		System.out.println("\n          Total of both dice                   Special Event");
		System.out.println("\n                  3                Plant a tree (2x2) AND a flower (1x1).");
		System.out.println("\n                  6                     Plant 2 flowers (1x1 twice).");
		System.out.println("\n               5 or 10             A rabbit will come eat a random flower"
						 + "\n                                          or part of a tree! (1x1).");
		System.out.println("\n        Any other EVEN number                Plant a tree (2x2).");
		System.out.println("             (2, 4 or 8)");
		System.out.println("\n         Any other ODD number               Plant a flower (1x1).");
		System.out.println("            (7, 9 or 11)");
		System.out.println("\n    7. If there are no moves available for a player, their turn is skipped!");
		System.out.println("    8. As soon as a player fills their garden, they win!");
		System.out.println("\nIMPORTANT NOTE: To plant a flower, indicate the coordinates (row, column)."
						 + "\n                To plant a tree, indicate the top left coordinate (row, column).\n");
		System.out.println("_______________________________________________________________________________________________\n");
	}
		
	public void gameStart() {
		System.out.println("_______________________________________________________________________________________________");
		System.out.println("                                       | GAME START |");
		System.out.println("                                       --------------\n");
	}
		
	public void finalResults() {
		System.out.println("_______________________________________________________________________________________________");
		System.out.println("                                      | FINAL RESULTS |");
		System.out.println("                                      -----------------\n");
	}

}


