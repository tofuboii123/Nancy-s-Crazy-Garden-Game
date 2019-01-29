//-----------------------------------------
// Assignment 4
// Written by: Weiliang Xie (40100475)
// For COMP 248 Section P - Fall 2018
//-----------------------------------------

// Main driver file for the actual game.
// The driver file starts by determining the number of players, their names, and the size of the gardens, all of which
// have measures to keep the value in bounds.
// Each player will roll to see who goes first, and whenever there is a tie, everyone rerolls.
// This is done internally purely for readability, because when there are more than 3-4 players, the rerolls
// can be come very numerous which would bog down the presentation.
// The game starts with the player who has the highest roll and depending on the different rolls, different events happen (refer to Player.class).
// The game ends once a player's garden is full.
// Closing message includes the final results which displays each player's garden and the number of rounds it took.

import java.util.Scanner;

public class LetsPlay {
	
	public static Scanner keyboard = new Scanner(System.in);
	
	// If the user enters something that isn't an integer, it asks again (loop).
	public static void checkInt() {
		
		while(!keyboard.hasNextInt()) {
		System.out.print("I'm not sure that was a number... Please try again: ");
		keyboard.next(); // Goes to the next input.
		}
	}
	
	public static void main(String[] args) {
		
		int boardSize = 3; // Used to determine the size of the garden.
		boolean sizeChange = false; // Used to determine whether the user wants to use the default size or not.
		boolean rightSize = true; // Used to determine if the user entered the right size.
		String boardChoice = ""; // Used to check if the user wants to change the board size.
		int playerNumber = 0; // Used do determine the number of players.
		Dice gameDice = new Dice(); // Dice object.
		int[] diceResults; // Stores the results of the dice at the same index as the player who rolled it.
		boolean sameDice = true; // Used to check if two players rolled the same number.
		int largestDice = 0; // Used to keep track of the largest roll.
		PrintText display = new PrintText(); // Object to keep track of text.
		
		display.printRules(); // Displays the rules on screen.
		
		// Asking users for board size. Will loop if the choice isn't "yes" or "no".
		do {
			System.out.print("\nThe default garden size is 3x3. Would you like to change sizes? (yes/no): ");
			
			boardChoice = keyboard.next();
		
			if(boardChoice.equalsIgnoreCase("yes")) // If the choice is "yes", the user can enter a new size.
			{
				do {	
					System.out.print("\nPlease enter a new garden size: ");
					
					LetsPlay.checkInt(); // Checking if it was an integer.
					
					boardSize = keyboard.nextInt();
			
					if(boardSize < 3) // If the size is smaller than the default size, the user tries again.
					{
						System.out.println("Sorry, that size is too small. Please try again.");
						rightSize = false;
					}
					else if(boardSize == 3) // Teasing the user if they enter the same size as the default one.
					{
						System.out.println("\nThat was the default size... Pay attention next time!");
						rightSize = true;
						sizeChange = false;
					}
					else // Any other size is good!
					{
						System.out.println("\nOkay! The new garden size is " + boardSize + "x" + boardSize + "!");
						rightSize = true;
						sizeChange = false;
					}
						
				} while(!rightSize); // Will loop if the size is less than 3.
			}
			else if(boardChoice.equalsIgnoreCase("no")) // This means the user is sticking to the default size.
			{
				System.out.println("\nOkay! We're sticking to the default size!");
				sizeChange = false;
			}
			else // If the user makes a mistake, they can try again.
			{
				System.out.println("I'm sorry, I didn't quite get that... Please try again.");
				sizeChange = true;
			}
			
		}while(sizeChange);
		
		System.out.print("\nNow, please enter the number of gardeners (From 2 to 10): ");
		
		LetsPlay.checkInt();
		
		playerNumber = keyboard.nextInt();
		
		if(playerNumber < 2 || playerNumber > 10) // If the user enters an invalid size, it will prompt them again.
		{
			rightSize = false;
			
			while(rightSize == false) {
				System.out.print("That is an invalid number of gardeners, please try again: ");
				playerNumber = keyboard.nextInt();
				
				if(playerNumber < 2 || playerNumber > 10) // This continues until the user enters a correct size.
				{
					rightSize = false;
				}
				else
					rightSize = true;
			}
		}
		
		// Confirms the number of players.
		System.out.println("\nOkay! Getting the game ready for " + playerNumber + " gardeners!\n");
		
		Player[] players = new Player[playerNumber]; // Array that keeps track of all players.
		
		for(int i = 0; i < players.length; i++) {
			System.out.print("Gardener " + (i + 1) + " enter your first name (No spaces): ");
			String name = keyboard.next();
			
			players[i] = new Player(name, boardSize); // Gives each player their respective name and the same board size.
		}
		
		System.out.println("\nNow let's see who goes first!\n");
		
		diceResults = new int[playerNumber]; // Initialized the size of the dice array.
	
		// Will loop to reroll the dice if two players have the same result.
		do {
			sameDice = false;
			
			// Gives each player a roll.
			for(int i = 0; i < players.length; i++) {
				diceResults[i] = gameDice.rollDice();
			}
			
			int k = 0; // Index used to compare which result is the largest.
			
			for(int i = 1; i < players.length; i++) {
				
				// Loops to check whether two results are the same. If so, we will reroll.
				for(int j = 0; j < players.length - 1; j++) {
					if(diceResults[i] == diceResults[j] && (i != j))
						sameDice = true;
				}
				
				// Compares the the first dice value with the others.
				if(diceResults[k] > diceResults[i])
					largestDice = diceResults[k]; // If the first value is the largest, that is stored as the largest.
				// If it isn't, the largest one is stored as the largest and we keep comparing from that point on.
				else
				{
					largestDice = diceResults[i];
					k = i;			
				}
			}
			
		}while(sameDice);
			
			
		// Displays what each player rolled.
		for(int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName() + " rolled " + diceResults[i]);
		}
		
		int winnerIndex = 0;
		
		// Checks who had the largest roll and makes them go first.
		for(int i = 0; i < players.length; i++) {
			if(diceResults[i] == largestDice)
			{
				System.out.println("\n" + players[i].getName() + " goes first!");
				
				winnerIndex = i;
			}
			
		}
		
		display.gameStart(); // Game starts!
		
		boolean winner = false; // Used to determine whether there is a winner or not.
		int roundCounter = 0; // Counts how many rounds it took for a game.
		String winnerName = ""; // The winner's name.
		
		int turn = winnerIndex; // Determines who starts. Resets every new round.
		
		// While there is no winner, the game continues.
		while(winner == false) {
			
			// If everyone has played, we reset the counter.
			if(turn == players.length)
					turn = 0;
				
			System.out.println(players[turn].getName() + "'s turn!");
			diceResults[turn] = gameDice.rollDice(); // Rolls the dice.
				
			// Displays dice result.
			System.out.println(players[turn].getName() + " rolled " + diceResults[turn] + gameDice.toString());
				
			players[turn].diceEvent(diceResults[turn]); // Chooses the event depending on the roll.
				
			// If the player's garden is full, that means he is the winner.
			if(players[turn].isGardenFull())
			{
				winner = true;
				winnerName = players[turn].getName();
				turn = players.length;
			}
				
			System.out.println("_______________________________________________________________________________________________");
			System.out.println();
			turn++; // Increments to go to the next player.
			roundCounter++; // Increments to keep track of the number of rounds.
		}
		
		display.finalResults(); // Final result banner.
		
		// Displays the number of total rounds.
		System.out.println("Here are the results after " + roundCounter + " rounds!");
		
		// Displays everyone's garden.
		for(int i = 0; i < players.length; i++) {
			System.out.println("\n" + players[i].getName() + "'s garden: ");
			players[i].showGarden();
		}
		
		// Closing message.
		System.out.println("\nThe winner is... " + winnerName + "!!!");
		System.out.println("You are one heck of a gardener!");
		System.out.println("\nHope you had a fun time with your friends!");
		
		LetsPlay.keyboard.close();
	}
}